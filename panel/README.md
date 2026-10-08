# SnapPay Key Panel (self-hosted)

Free-host panel that your patched APK talks to for **key activation only**.
Two builds are published:

- `/download/Snappay.apk` — the **currently working** build (validate-only patch);
  untouched, keeps its original version-check URL on the OG panel.
- `/download/Snappay-noupdate.apk` — **backup build**: identical, plus the
  version-check URL repointed here (static 146) so the in-app "UPDATE NOW"
  dialog can never install the original APK over it and kill your keys.

## Endpoints

| Route | Who calls it | What it does |
|---|---|---|
| `POST /api/keys/validate` (alias `POST /v`) | APK (Splash / Login / MainActivity) | `{valid, status, message, expiresAt}` — device-bound, exact original contract |
| `GET /api/app/version` | APK (update check) | **static** `1.4.6` / code `146` — deliberately never proxies OG, so the in-app "UPDATE NOW" dialog can never install the original APK over the patched one (your keys keep working)
| `GET /api/app/download?v=` | optional | 302 → original panel download |
| `GET /download` | anyone | downloads `Snappay.apk` — the currently working build (also at `/download/Snappay.apk`) |
| `GET /download/Snappay-noupdate.apk` | anyone | backup build: + version check pinned here, in-app update dialog permanently blocked |
| auto-backup (server) | automatic | every key change → encrypted `keys/keys-backup.json` committed to the GitHub repo (`GITHUB_PAT` + `GITHUB_REPO` env) |
| auto-restore (server) | automatic | on boot, if the DB is empty (Render wiped it) → keys re-imported from git before the dashboard loads |
| auto-restore (browser) | automatic | if the server still shows empty, the dashboard restores from its own `localStorage` snapshot |
| `POST /api/keys/create` | your Telegram bot | header `X-Panel-Token` |
| `GET /`, `POST /api/admin/login`, `GET/POST/DELETE /api/admin/...` | you (browser) | dashboard: generate / revoke / device view |

Statuses the app understands: `active`, `activated`, `expired`, `revoked`,
`not_found`, `device_mismatch`.

## Run locally

```bash
npm install
ADMIN_PASSWORD=CHANGE_ME PORT=3000 node server.js
# open http://127.0.0.1:3000
```

## Deploy FREE on Render

1. Push this `panel/` folder to a GitHub repo.
2. render.com → **New → Web Service** → pick the repo.
3. Settings:
   - Runtime: **Node**
   - Build command: `npm install`
   - Start command: `node server.js`
   - Instance type: **Free**
4. Environment variables:
   - `ADMIN_PASSWORD` = your dashboard password
   - `SESSION_SECRET` = random 64-char string
   - `BOT_TOKEN` = (optional) token your Telegram bot sends as `X-Panel-Token`
   - `ORIGINAL_PANEL` = `https://snappay-web.vercel.app`
5. You get `https://<your-service-name>.onrender.com` with HTTPS automatically.
   Pick a **short** service name (must keep the total URL ≤ 48 bytes, otherwise
   use the `/v` alias — e.g. `https://snappay-key.onrender.com/v` = 34 bytes).

> Do NOT name the service `snappay-b414` — that exact hostname is certificate-
> pinned inside the APK to the old (dead) backend.

Free-tier note: Render's free plan has two traps, both handled below.
`render.yaml` in the repo root pre-fills the service config.

## Keeping it alive + not losing keys (Render free)

Official Render docs say:

1. **Spin-down** — a Free web service spins down after **15 minutes without
   inbound traffic**; the next request takes **~1 minute** to wake it.
2. **Filesystem wipe** — *"any changes to its local filesystem (uploaded
   images, local SQLite databases, etc.) are lost every time the service
   redeploys, restarts, or spins down"*. So the SQLite file with your keys is
   **not safe by itself**.
3. **750 free instance hours / workspace / month** — spun-down time is free.

This panel handles both:

- **Keep-alive**: set `KEEPALIVE_URL=https://<your-service>.onrender.com/api/health`
  (and `KEEPALIVE_MIN=9`). The service pings its own public URL every 9 minutes,
  so it never idles out. Cost: ~744 h/month, inside the 750 h allowance — keep it
  as the only Free web service in the workspace. Verified locally: heartbeat
  logged every interval, `keepalive ok`.
- **Backup / Restore**: dashboard **⤓ Backup** downloads every key as JSON;
  **⤒ Restore** uploads it back. Use it after any redeploy. Verified round-trip:
  delete a key → restore → key validates again (`valid:true`), and restore
  without a session returns 401.
- Even with keep-alive Render says it *may* restart a Free service at any time —
  so keep the JSON backup somewhere safe (it's small, it's just your keys).

Honest limits of "free": if `KEEPALIVE_URL` is missing, the service sleeps and
the first app request after that pays a ~60 s wake-up (the APK will show
"Connecting..." and retry). If you want zero sleep *and* zero data-wipe risk,
move to a paid plan or an external DB (Render Postgres / Upstash) — that is a
storage swap in `server.js`, not a rewrite.

## Deploy FREE on Vercel (alternative)

Same repo, import as a Node project, framework preset **Other**, build `npm install`,
start `node server.js`. Vercel only allows serverless-friendly paths — if the
deploy complains, keep `express` on a plain Node service (Render is the easy path).

## After deploying: re-point the APK

```bash
./patch_apk.sh https://<your-service>.onrender.com/v
# -> dist/snapay-patched.apk   (signed, installable)
```

The script patches the `validate` URL **and** the version-check URL inside
`classes.dex` (same-length in-place bytes, keeps the 48/46-byte slots,
recomputes dex SHA-1 + Adler32 and verifies `string_ids` stay sorted), strips
the old signature and re-signs (v1/v2/v3 verified). The result is published as
the **backup** `/download/Snappay-noupdate.apk`; the working build at
`/download/Snappay.apk` is left exactly as it was.

Uninstall the original app first — the signature differs, Android will refuse
an in-place update.

## Selling keys

Dashboard: open `/`, log in, generate keys (count / days / prefix / note),
copy them out. Revoke anytime; device binding is automatic on first use.

Telegram bot → `POST /api/keys/create` with `X-Panel-Token: <BOT_TOKEN>`
body `{"count":1,"days":30,"note":"@buyer"}` → `{created:[...], expires}`.
