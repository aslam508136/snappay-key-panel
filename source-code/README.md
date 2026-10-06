# Source code

- `website/` — SnapPay key-activation panel (Node/Express + SQLite).
  Deployed on Render from `render.yaml` (root `panel/` in live repo).
- `app/` — SnapPay APK source:
  - `app-sources/` + `app-resources/` — decompiled from `app.apk` (jadx 1.5.6)
  - `patch_apk.sh` + `patch_apk.py` — patch validate-URL → re-sign pipeline
  - `app.apk` — original APK
