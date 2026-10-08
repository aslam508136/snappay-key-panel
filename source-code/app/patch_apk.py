#!/usr/bin/env python3
"""
Patch the SnapPay APK so /api/keys/validate points at your own panel AND the
version check hits YOUR panel (static versionCode) — otherwise a future OG
release shows the in-app "UPDATE NOW" dialog, whose APK download installs the
ORIGINAL app over the patched one and switches key validation back to their
server (your keys stop working).

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
OLD_VERSION_URL = b"https://snappay-web.vercel.app/api/app/version"
# Same byte length as OLD_VERSION_URL (46) so the string stays in place, and it
# sorts BETWEEN "https://snappay-web.vercel.app" and the patched validate URL
# (snappay-w < snappay-z, path "api..." < "v..."), so dex string_ids stay sorted.
NEW_VERSION_URL = b"https://snappay-z.onrender.com/api/app/version"


def mutf8_units(b):
    """Decode MUTF-8 bytes to UTF-16 code units (the dex string_ids sort key)."""
    units = []
    i = 0
    while i < len(b):
        c = b[i]
        if c < 0x80:
            units.append(c)
            i += 1
        elif c & 0xE0 == 0xC0:
            units.append(((c & 0x1F) << 6) | (b[i + 1] & 0x3F))
            i += 2
        elif c & 0xF0 == 0xE0:
            units.append(((c & 0x0F) << 12) | ((b[i + 1] & 0x3F) << 6) | (b[i + 2] & 0x3F))
            i += 3
        else:
            sys.exit("ERROR: unexpected MUTF-8 lead byte while checking string order")
    return units


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
    pairs = [(OLD_URL, new_url_bytes), (OLD_VERSION_URL, NEW_VERSION_URL)]
    for old, new in pairs:
        if b"\x00" in new:
            sys.exit("ERROR: URL contains NUL byte")
        if len(new) != len(old):
            sys.exit(
                f"ERROR: {new.decode('ascii', 'replace')} is {len(new)} bytes, "
                f"must be exactly {len(old)} (same-length in-place patch).\n"
                "       Use a shorter path (the panel also serves /v) or a shorter domain."
            )
    buf = bytearray(data)
    counts = {old: 0 for old, _ in pairs}
    for start, end, content in iter_dex_strings(bytes(buf)):
        for old, new in pairs:
            if content == old:
                buf[start:end] = new
                counts[old] += 1
                break
    missing = [o.decode() for o, c in counts.items() if c == 0]
    if missing:
        sys.exit(f"ERROR: not found in dex — wrong APK? {missing}")
    # recompute header signature (SHA-1 of bytes[32:]) and checksum (Adler32 of bytes[12:])
    sig = hashlib.sha1(bytes(buf[32:])).digest()
    buf[12:32] = sig
    struct.pack_into("<I", buf, 8, zlib.adler32(bytes(buf[12:])) & 0xFFFFFFFF)
    return bytes(buf), sum(counts.values())


def verify_dex(data, expected_url):
    strings = [c for _, _, c in iter_dex_strings(data)]
    old = sum(1 for c in strings if c in (OLD_URL, OLD_VERSION_URL))
    new = sum(1 for c in strings if c in (expected_url, NEW_VERSION_URL))
    adler = struct.unpack_from("<I", data, 8)[0]
    sig = data[12:32]
    ok_sig = sig == hashlib.sha1(data[32:]).digest()
    ok_adler = adler == (zlib.adler32(data[12:]) & 0xFFFFFFFF)
    # dex string_ids MUST stay sorted by UTF-16 code units, else ART rejects the
    # dex at install ("Failure to verify dex file: Out-of-order string_ids")
    units = [mutf8_units(s) for s in strings]
    ok_sorted = all(units[i] <= units[i + 1] for i in range(len(units) - 1))
    return old, new, ok_sig, ok_adler, ok_sorted


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
    old, new, ok_sig, ok_adler, ok_sorted = verify_dex(patched_dex, target)
    print(f"[*] patched {hits} string occurrence(s); verify -> old={old} new={new} "
          f"sha1={'ok' if ok_sig else 'BAD'} adler32={'ok' if ok_adler else 'BAD'} "
          f"string_ids_sorted={'ok' if ok_sorted else 'OUT-OF-ORDER'}")
    if old or new != hits or not ok_sig or not ok_adler or not ok_sorted:
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
