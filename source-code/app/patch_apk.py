#!/usr/bin/env python3
"""
Patch the SnapPay APK so /api/keys/validate points at your own panel,
while everything else (version check, download) keeps using the original panel.

Why in-place byte patch instead of apktool/smali:
  DEX stores string_data at absolute file offsets referenced by string_ids.
  Rewriting a string with a different length would shift every later offset and
  require rebuilding the whole file. We therefore keep the byte length identical:
  a shorter URL is padded with an ignored query string ("?aaa..."), which Express
  (and any HTTP server) happily ignores.

  DEX header SHA-1 signature + Adler32 checksum are recomputed after the patch,
  otherwise ART refuses to load the dex.

Usage:
  python patch_apk.py --in app.apk --out app-patched-unsigned.apk \
                      --url https://your-panel.onrender.com/api/keys/validate
"""

import argparse
import hashlib
import struct
import sys
import zlib
import zipfile

OLD_URL = b"https://snappay-web.vercel.app/api/keys/validate"


def iter_dex_strings(d):
    """Yield (content_start_offset, content_end_offset, content_bytes) for every dex string."""
    string_ids_size, string_ids_off = struct.unpack_from("<II", d, 56)
    for i in range(string_ids_size):
        (data_off,) = struct.unpack_from("<I", d, string_ids_off + 4 * i)
        p = data_off
        # uleb128 utf16_size
        while True:
            b = d[p]
            p += 1
            if not (b & 0x80):
                break
        end = d.index(b"\x00", p)
        yield p, end, d[p:end]


def patch_dex(data, new_url_bytes):
    if b"\x00" in new_url_bytes:
        sys.exit("ERROR: URL contains NUL byte")
    if len(new_url_bytes) != len(OLD_URL):
        sys.exit(
            f"ERROR: URL is {len(new_url_bytes)} bytes, must be exactly {len(OLD_URL)}.\n"
            "       Use a shorter path (the panel also serves /v) or a shorter domain."
        )
    buf = bytearray(data)
    hits = 0
    for start, end, content in iter_dex_strings(bytes(buf)):
        if content == OLD_URL:
            buf[start:end] = new_url_bytes
            hits += 1
    if hits == 0:
        sys.exit("ERROR: original URL not found in dex — wrong APK?")
    # recompute header signature (SHA-1 of bytes[32:]) and checksum (Adler32 of bytes[12:])
    sig = hashlib.sha1(bytes(buf[32:])).digest()
    buf[12:32] = sig
    struct.pack_into("<I", buf, 8, zlib.adler32(bytes(buf[12:])) & 0xFFFFFFFF)
    return bytes(buf), hits


def verify_dex(data, expected_url):
    strings = [c for _, _, c in iter_dex_strings(data)]
    old = sum(1 for c in strings if c == OLD_URL)
    new = sum(1 for c in strings if c == expected_url)
    adler = struct.unpack_from("<I", data, 8)[0]
    sig = data[12:32]
    ok_sig = sig == hashlib.sha1(data[32:]).digest()
    ok_adler = adler == (zlib.adler32(data[12:]) & 0xFFFFFFFF)
    return old, new, ok_sig, ok_adler


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--in", dest="inp", default="app.apk")
    ap.add_argument("--out", dest="out", default="app-patched-unsigned.apk")
    ap.add_argument("--url", required=True, help="full validate URL on YOUR panel")
    a = ap.parse_args()

    url = a.url.strip()
    target = url.encode("ascii")
    if len(target) < len(OLD_URL):
        need = len(OLD_URL) - len(target)
        target = target + b"?" + b"a" * (need - 1) if need > 1 else target + b"?"
        print(f"[*] padded URL to {len(target)} bytes with an ignored query string")
    print(f"[*] {url}  ({len(target)} bytes)")

    zin = zipfile.ZipFile(a.inp, "r")
    if "classes.dex" not in zin.namelist():
        sys.exit("ERROR: classes.dex not found in APK")

    patched_dex, hits = patch_dex(zin.read("classes.dex"), target)
    old, new, ok_sig, ok_adler = verify_dex(dex := patched_dex, target)
    print(f"[*] patched {hits} string occurrence(s); verify -> old={old} new={new} "
          f"sha1={'ok' if ok_sig else 'BAD'} adler32={'ok' if ok_adler else 'BAD'}")
    if old or new != hits or not ok_sig or not ok_adler:
        sys.exit("ERROR: patched dex failed verification")

    with zipfile.ZipFile(a.out, "w") as zout:
        for info in zin.infolist():
            name = info.filename
            upper = name.upper()
            if upper.startswith("META-INF/") and (
                upper.endswith(".SF") or upper.endswith(".RSA")
                or upper.endswith(".DSA") or upper.endswith(".EC")
                or upper.endswith("MANIFEST.MF")
            ):
                print(f"[-] stripped old signature entry: {name}")
                continue
            data = patched_dex if name == "classes.dex" else zin.read(name)
            new_info = zipfile.ZipInfo(name, date_time=info.date_time)
            new_info.compress_type = info.compress_type
            new_info.external_attr = info.external_attr
            new_info.create_system = info.create_system
            zout.writestr(new_info, data)
    zin.close()
    print(f"[+] wrote {a.out} (unsigned — sign it next)")


if __name__ == "__main__":
    main()
