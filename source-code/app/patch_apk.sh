#!/usr/bin/env bash
# One command: patch app.apk so key validation hits YOUR panel, then re-sign.
#
#   ./patch_apk.sh https://your-panel.onrender.com/api/keys/validate
#
# Output: dist/snapay-patched.apk  (signed, installable)
# The panel must serve that exact path (it does; /v is a short alias if the
# URL would otherwise exceed 48 bytes).
set -euo pipefail
cd "$(dirname "$0")"

URL="${1:-}"
[ -n "$URL" ] || { echo "usage: ./patch_apk.sh <your-panel-validate-url>"; exit 1; }

PY=()
for cand in "python3" "/c/Users/suraj/AppData/Local/Programs/Python/Python312/python.exe" "python"; do
  if $cand -c "pass" >/dev/null 2>&1; then PY=($cand); break; fi
done
[ ${#PY[@]} -gt 0 ] || { echo "no working python found"; exit 1; }

mkdir -p dist tools

echo "== 1/3 patch dex =="
"${PY[@]}" tools/patch_apk.py --in app.apk --out dist/snapay-patched-unsigned.apk --url "$URL"

echo "== 2/3 sign (release keystore — NOT the Android Debug cert) =="
KT="tools/snapay-release.jks"
[ -f "$KT" ] || { echo "missing $KT — cannot sign consistently"; exit 1; }
java -jar tools/uber-apk-signer.jar -a dist/snapay-patched-unsigned.apk \
  --ks "$KT" --ksAlias snapay --ksPass SnapPay@2026 --ksKeyPass SnapPay@2026 -o dist >/dev/null

APK="$(ls dist/*.apk | grep -v -- '-unsigned' | head -1)"
mv "$APK" dist/snapay-patched.apk

echo "== 3/3 verify =="
java -jar tools/uber-apk-signer.jar --apks dist/snapay-patched.apk -y -o dist/verify 2>&1 | tail -12
"${PY[@]}" - "$URL" <<'PYEOF'
import sys, zipfile, hashlib, struct, zlib
url = sys.argv[1].encode()
z = zipfile.ZipFile('dist/snapay-patched.apk')
d = z.read('classes.dex')
# confirm patched URL survives inside the SIGNED apk
old = b'https://snappay-web.vercel.app/api/keys/validate'
print('signed apk contains new url :', url[:48] in d, '| old url gone :', old not in d)
PYEOF

echo
echo "DONE -> dist/snapay-patched.apk"
echo "Note: signature differs from the original APK, so uninstall the old app first."
