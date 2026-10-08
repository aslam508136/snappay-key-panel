/**
 * SnapPay key activation panel (self-hosted)
 *
 * App-facing contract (matches the original panel exactly):
 *   POST /api/keys/validate
 *     request : { key, deviceInfo }   (deviceInfo is a JSON *string*; MainActivity
 *               also calls it with only { key })
 *     response: { valid: bool, status: string, message: string, expiresAt: string }
 *     status  : active | activated | expired | revoked | not_found | device_mismatch
 *
 *   GET /api/app/version  -> STATIC v1.4.6/146 — blocks the in-app update dialog forever
 *   GET /api/app/download -> redirect to the original panel
 *
 * Admin:
 *   GET  /                          admin dashboard
 *   POST /api/admin/login           { password } -> signed session cookie
 *   GET  /api/admin/keys            list keys
 *   POST /api/admin/keys            { count, days, note, prefix } -> create keys
 *   POST /api/admin/keys/:key/state { state }  -> activate | revoke
 *   DELETE /api/admin/keys/:key
 *
 * Telegram bot integration:
 *   POST /api/keys/create           header X-Panel-Token -> { count, days, note }
 */

const express = require('express');
const crypto = require('crypto');
const fs = require('fs');
const path = require('path');
const { DatabaseSync } = require('node:sqlite');

const PORT = process.env.PORT || 3000;
const ADMIN_PASSWORD = process.env.ADMIN_PASSWORD || 'admin123';
const SESSION_SECRET = process.env.SESSION_SECRET || crypto.randomBytes(32).toString('hex');
const BOT_TOKEN = process.env.BOT_TOKEN || '';           // optional: for Telegram bot key creation
const ORIGINAL_PANEL = process.env.ORIGINAL_PANEL || 'https://snappay-web.vercel.app';

const DATA_DIR = process.env.DATA_DIR || path.join(__dirname, 'data');
fs.mkdirSync(DATA_DIR, { recursive: true });

const db = new DatabaseSync(path.join(DATA_DIR, 'keys.db'));
db.exec('PRAGMA journal_mode = WAL');
db.exec(`
  CREATE TABLE IF NOT EXISTS keys (
    key         TEXT PRIMARY KEY,
    status      TEXT NOT NULL DEFAULT 'active',   -- active | revoked
    note        TEXT DEFAULT '',
    device_id   TEXT DEFAULT '',
    created_at  TEXT NOT NULL,
    expires_at  TEXT NOT NULL,
    last_seen   TEXT DEFAULT ''
  );
  CREATE TABLE IF NOT EXISTS audit (
    id       INTEGER PRIMARY KEY AUTOINCREMENT,
    key      TEXT,
    event    TEXT,
    device   TEXT,
    at       TEXT NOT NULL
  );
`);

const app = express();
app.use(express.json({ limit: '64kb' }));

/* ------------------------------------------------------------- apk download
 * Registered BEFORE express.static so the directory redirect (301 /download ->
 * /download/) never swallows it.
 *   GET /download              -> streams public/download/Snappay.apk as an
 *                                 attachment named Snappay.apk
 *   GET /download/Snappay.apk  -> same file, served statically from public/
 */
const PATCHED_APK = path.join(__dirname, 'public', 'download', 'Snappay.apk');
app.get(['/download', '/download/'], (_req, res) => {
  if (!fs.existsSync(PATCHED_APK)) return res.status(404).json({ error: 'apk not uploaded' });
  res.setHeader('Content-Type', 'application/vnd.android.package-archive');
  res.download(PATCHED_APK, 'Snappay.apk');
});

app.use(express.static(path.join(__dirname, 'public')));

const nowIso = () => new Date().toISOString();
const addDays = (d) => new Date(Date.now() + d * 86400000).toISOString();

function audit(key, event, device) {
  db.prepare('INSERT INTO audit (key, event, device, at) VALUES (?,?,?,?)')
    .run(key || '', event, device || '', nowIso());
}

/* ---------------------------------------------------------------- validate */

function makeKey(prefix) {
  const alphabet = 'ABCDEFGHJKLMNPQRSTUVWXYZ23456789';
  const grp = () => Array.from(crypto.randomBytes(4)).map((b) => alphabet[b % 36]).join('');
  return `${(prefix || 'SNP').toUpperCase()}-${grp()}-${grp()}-${grp()}`;
}

const validateHandler = (req, res) => {
  const body = req.body || {};
  const key = String(body.key || '').trim();
  let deviceId = '';
  try {
    const info = typeof body.deviceInfo === 'string' ? JSON.parse(body.deviceInfo) : body.deviceInfo;
    if (info && info.deviceId) deviceId = String(info.deviceId);
  } catch (_) { /* deviceInfo malformed: treat as no device */ }

  if (!key) {
    return res.json({ valid: false, status: 'not_found', message: 'Key not found. Please check and try again', expiresAt: '' });
  }

  const row = db.prepare('SELECT * FROM keys WHERE key = ? COLLATE NOCASE').get(key);

  if (!row) {
    audit(key, 'not_found', deviceId);
    return res.json({ valid: false, status: 'not_found', message: 'Key not found. Please check and try again', expiresAt: '' });
  }
  if (row.status === 'revoked') {
    audit(row.key, 'revoked', deviceId);
    return res.json({ valid: false, status: 'revoked', message: 'This key has been revoked by admin', expiresAt: row.expires_at });
  }
  if (new Date(row.expires_at).getTime() < Date.now()) {
    audit(row.key, 'expired', deviceId);
    return res.json({ valid: false, status: 'expired', message: 'This key has expired', expiresAt: row.expires_at });
  }
  if (row.device_id && deviceId && row.device_id !== deviceId) {
    audit(row.key, 'device_mismatch', deviceId);
    return res.json({ valid: false, status: 'device_mismatch', message: 'This key is registered to another device', expiresAt: row.expires_at });
  }

  // bind device on first use, refresh last_seen
  const deviceToStore = row.device_id || deviceId || '';
  db.prepare('UPDATE keys SET device_id = ?, last_seen = ? WHERE key = ?')
    .run(deviceToStore, nowIso(), row.key);
  audit(row.key, 'valid', deviceId);

  res.json({ valid: true, status: 'active', message: 'Key is active', expiresAt: row.expires_at });
};

// registered on both paths: /v is a 2-byte alias so the URL still fits the
// 48-byte slot inside the APK's dex even when the panel domain is long
app.post(['/v', '/api/keys/validate'], validateHandler);

/* ------------------------------------------------- app update (DELIBERATELY STATIC)
 * The patched APK's version-check URL points HERE instead of the OG panel.
 * The app shows a full-screen "UPDATE NOW" dialog whenever server versionCode >
 * installed versionCode — and that update installs the ORIGINAL APK over ours,
 * switching key validation back to the OG server (our keys would die).
 * So we always report the currently installed version and never proxy the OG
 * panel: no matter what OG releases, the dialog can never appear.
 * Bump version/versionCode here ONLY if you ship a patched APK with a higher
 * versionCode (must stay == installed APK's android:versionCode).
 */

app.get('/api/app/version', (_req, res) => {
  res.json({
    version: '1.4.6',
    versionCode: 146,
    releaseNotes: 'Bug fixes and improvements.',
    forceUpdate: false,
    minSupportedVersion: 0,
  });
});

app.get('/api/app/download', (req, res) => {
  const v = req.query.v ? `?v=${encodeURIComponent(String(req.query.v))}` : '';
  res.redirect(302, `${ORIGINAL_PANEL}/api/app/download${v}`);
});

/* (apk download route is registered above express.static — see top of file) */

/* ------------------------------------------------------------------ session */

function sign(val) {
  return crypto.createHmac('sha256', SESSION_SECRET).update(val).digest('base64url');
}
function sessionCookie(val) {
  return `panel_session=${Buffer.from(val).toString('base64url')}.${sign(val)}; Path=/; HttpOnly; SameSite=Strict; Max-Age=604800`;
}
function readSession(req) {
  const raw = (req.headers.cookie || '').split('; ').find((c) => c.startsWith('panel_session='));
  if (!raw) return null;
  const parts = raw.slice('panel_session='.length).split('.');
  if (parts.length !== 2) return null;
  const expected = sign(Buffer.from(parts[0], 'base64url').toString());
  const a = Buffer.from(parts[1]);
  const b = Buffer.from(expected);
  return a.length === b.length && crypto.timingSafeEqual(a, b) ? Buffer.from(parts[0], 'base64url').toString() : null;
}
function requireAdmin(req, res, next) {
  if (readSession(req) === 'admin') return next();
  res.status(401).json({ error: 'unauthorized' });
}

app.post('/api/admin/login', (req, res) => {
  const pw = String((req.body || {}).password || '');
  const h = (s) => crypto.createHash('sha256').update(s).digest();
  if (!crypto.timingSafeEqual(h(pw), h(ADMIN_PASSWORD))) {
    return res.status(401).json({ error: 'wrong password' });
  }
  res.setHeader('Set-Cookie', sessionCookie('admin'));
  res.json({ ok: true });
});

/* --------------------------------------------------------------------- admin */

app.get('/api/admin/keys', requireAdmin, (req, res) => {
  const rows = db.prepare('SELECT * FROM keys ORDER BY created_at DESC LIMIT 500').all();
  const now = Date.now();
  res.json(rows.map((r) => ({ ...r, live: r.status === 'active' && new Date(r.expires_at).getTime() > now })));
});

app.get('/api/admin/stats', requireAdmin, (_req, res) => {
  const total = db.prepare('SELECT COUNT(*) c FROM keys').get().c;
  const active = db.prepare("SELECT COUNT(*) c FROM keys WHERE status='active' AND expires_at > ?").get(nowIso()).c;
  const revoked = db.prepare("SELECT COUNT(*) c FROM keys WHERE status='revoked'").get().c;
  const bound = db.prepare("SELECT COUNT(*) c FROM keys WHERE device_id != ''").get().c;
  res.json({ total, active, revoked, bound });
});

app.post('/api/admin/keys', requireAdmin, (req, res) => {
  const b = req.body || {};
  const count = Math.min(Math.max(parseInt(b.count, 10) || 1, 1), 100);
  const days = Math.min(Math.max(parseInt(b.days, 10) || 30, 1), 3650);
  const note = String(b.note || '').slice(0, 120);
  const prefix = String(b.prefix || 'SNP').replace(/[^A-Za-z0-9]/g, '').slice(0, 6) || 'SNP';
  const expires = addDays(days);
  const created = [];
  const stmt = db.prepare('INSERT INTO keys (key, status, note, created_at, expires_at) VALUES (?,?,?,?,?)');
  for (let i = 0; i < count; i++) {
    let k = makeKey(prefix);
    while (db.prepare('SELECT 1 FROM keys WHERE key = ?').get(k)) k = makeKey(prefix);
    stmt.run(k, 'active', note, nowIso(), expires);
    audit(k, 'created', '');
    created.push(k);
  }
  scheduleGitBackup();
  res.json({ created, expires });
});

app.post('/api/admin/keys/:key/state', requireAdmin, (req, res) => {
  const key = String(req.params.key);
  const state = String((req.body || {}).state || '');
  if (!['active', 'revoked'].includes(state)) return res.status(400).json({ error: 'bad state' });
  const r = db.prepare('UPDATE keys SET status = ? WHERE key = ? COLLATE NOCASE').run(state, key);
  if (!r.changes) return res.status(404).json({ error: 'not found' });
  audit(key, state, '');
  scheduleGitBackup();
  res.json({ ok: true });
});

app.delete('/api/admin/keys/:key', requireAdmin, (req, res) => {
  const r = db.prepare('DELETE FROM keys WHERE key = ? COLLATE NOCASE').run(String(req.params.key));
  if (!r.changes) return res.status(404).json({ error: 'not found' });
  audit(String(req.params.key), 'deleted', '');
  scheduleGitBackup();
  res.json({ ok: true });
});

/* ------------------------------------------------- bot (telegram) integration */

app.post('/api/keys/create', (req, res) => {
  if (!BOT_TOKEN || req.get('X-Panel-Token') !== BOT_TOKEN) return res.status(401).json({ error: 'unauthorized' });
  const b = req.body || {};
  const count = Math.min(Math.max(parseInt(b.count, 10) || 1, 1), 50);
  const days = Math.min(Math.max(parseInt(b.days, 10) || 30, 1), 3650);
  const note = String(b.note || '').slice(0, 120);
  const expires = addDays(days);
  const created = [];
  const stmt = db.prepare('INSERT INTO keys (key, status, note, created_at, expires_at) VALUES (?,?,?,?,?)');
  for (let i = 0; i < count; i++) {
    const k = makeKey('SNP');
    stmt.run(k, 'active', note, nowIso(), expires);
    audit(k, 'created', 'bot');
    created.push(k);
  }
  res.json({ created, expires });
});

/* ------------------------------------------------------------- backup/restore
 * Render Free's filesystem is EPHEMERAL: docs say "any changes to its local
 * filesystem (uploaded images, local SQLite databases, etc.) are lost every
 * time the service redeploys, restarts, or spins down".
 * So keys must be exportable/restorable from outside the box.
 */
app.get('/api/admin/backup', requireAdmin, (_req, res) => {
  const keys = db.prepare('SELECT * FROM keys').all();
  res.setHeader('Content-Disposition', 'attachment; filename=snappay-keys-backup.json');
  res.json({ version: 1, exportedAt: nowIso(), keys });
});

app.post('/api/admin/restore', requireAdmin, (req, res) => {
  const rows = (req.body || {}).keys;
  if (!Array.isArray(rows)) return res.status(400).json({ error: 'keys[] required' });
  const stmt = db.prepare(
    'INSERT OR REPLACE INTO keys (key,status,note,device_id,created_at,expires_at,last_seen) VALUES (?,?,?,?,?,?,?)'
  );
  let n = 0;
  for (const r of rows) {
    if (!r || !r.key) continue;
    stmt.run(String(r.key), r.status === 'revoked' ? 'revoked' : 'active',
      String(r.note || ''), String(r.device_id || ''),
      r.created_at || nowIso(), r.expires_at || addDays(30), r.last_seen || '');
    n++;
  }
  scheduleGitBackup();
  res.json({ restored: n });
});

/* ------------------------------------------------- auto backup -> GitHub
 * Render Free wipes its disk on EVERY deploy/restart, so keys are mirrored to
 * `<repo>/keys/keys-backup.json` after every change and re-imported on boot
 * when the DB is empty — the dashboard then looks like keys were never lost.
 * The repo is public, so the JSON is encrypted (AES-256-GCM, key derived from
 * SESSION_SECRET which persists in Render's env across deploys).
 * Optional env: GITHUB_PAT (repo write), GITHUB_REPO, GITHUB_REF (default master).
 */
const GITHUB_REPO = process.env.GITHUB_REPO || 'aslam508136/snappay-key-panel';
const GITHUB_PAT = process.env.GITHUB_PAT || '';
const GITHUB_REF = process.env.GITHUB_REF || 'master';
const BACKUP_PATH = 'keys/keys-backup.json';

function backupKey() {
  return crypto.createHash('sha256').update('snappay-keys-backup:' + SESSION_SECRET).digest();
}
function encryptBackup(obj) {
  const iv = crypto.randomBytes(12);
  const cipher = crypto.createCipheriv('aes-256-gcm', backupKey(), iv);
  const ct = Buffer.concat([cipher.update(JSON.stringify(obj), 'utf8'), cipher.final()]);
  return JSON.stringify({ v: 1, alg: 'aes-256-gcm', iv: iv.toString('base64'),
    tag: cipher.getAuthTag().toString('base64'), data: ct.toString('base64') });
}
function decryptBackup(txt) {
  const o = JSON.parse(txt);
  if (o.alg !== 'aes-256-gcm') throw new Error('unexpected backup format');
  const d = crypto.createDecipheriv('aes-256-gcm', backupKey(), Buffer.from(o.iv, 'base64'));
  d.setAuthTag(Buffer.from(o.tag, 'base64'));
  return JSON.parse(Buffer.concat([d.update(Buffer.from(o.data, 'base64')), d.final()]).toString('utf8'));
}

async function pushBackup() {
  if (!GITHUB_PAT) return;
  try {
    const rows = db.prepare('SELECT * FROM keys').all();
    const payload = encryptBackup({ exportedAt: nowIso(), keys: rows });
    const url = `https://api.github.com/repos/${GITHUB_REPO}/contents/${BACKUP_PATH}`;
    const headers = { Authorization: `Bearer ${GITHUB_PAT}`, Accept: 'application/vnd.github+json',
      'X-GitHub-Api-Version': '2022-11-28' };
    let sha;
    const cur = await fetch(url, { headers, signal: AbortSignal.timeout(10000) });
    if (cur.ok) sha = (await cur.json()).sha;
    const put = await fetch(url, { method: 'PUT', headers, body: JSON.stringify({
      message: `keys auto-backup ${nowIso()}`,
      content: Buffer.from(payload, 'utf8').toString('base64'),
      ...(sha ? { sha } : {}), branch: GITHUB_REF,
    }), signal: AbortSignal.timeout(15000) });
    if (!put.ok) console.error('[git-backup] push failed', put.status, (await put.text()).slice(0, 200));
    else console.log(`[git-backup] ok (${rows.length} keys)`);
  } catch (e) { console.error('[git-backup] error:', e.message); }
}

let backupTimer = null;
function scheduleGitBackup() {
  if (!GITHUB_PAT) return;
  clearTimeout(backupTimer);
  backupTimer = setTimeout(pushBackup, 3000); // coalesce bursts of changes
}

async function fetchBackupText() {
  const tries = [];
  if (GITHUB_PAT) {
    tries.push({
      url: `https://api.github.com/repos/${GITHUB_REPO}/contents/${BACKUP_PATH}?ref=${GITHUB_REF}`,
      headers: { Authorization: `Bearer ${GITHUB_PAT}`, Accept: 'application/vnd.github+json' },
      json: true,
    });
  }
  tries.push({ url: `https://github.com/${GITHUB_REPO}/raw/${GITHUB_REF}/${BACKUP_PATH}?cb=${Date.now()}`, headers: {} });
  tries.push({ url: `https://raw.githubusercontent.com/${GITHUB_REPO}/${GITHUB_REF}/${BACKUP_PATH}?cb=${Date.now()}`, headers: {} });
  for (const t of tries) {
    try {
      const r = await fetch(t.url, { headers: t.headers, signal: AbortSignal.timeout(10000) });
      if (!r.ok) continue;
      if (t.json) {
        const j = await r.json();
        if (j.content) return Buffer.from(j.content, 'base64').toString('utf8');
        continue;
      }
      return await r.text();
    } catch (_) { /* try next source */ }
  }
  return null;
}

async function restoreFromGit() {
  try {
    if (db.prepare('SELECT COUNT(*) c FROM keys').get().c > 0) return; // nothing wiped
    const txt = await fetchBackupText();
    if (txt === null) { console.log('[git-restore] no backup yet (404 on all sources)'); return; }
    const payload = decryptBackup(txt);
    const stmt = db.prepare(
      'INSERT OR REPLACE INTO keys (key,status,note,device_id,created_at,expires_at,last_seen) VALUES (?,?,?,?,?,?,?)');
    let n = 0;
    for (const row of payload.keys || []) {
      if (!row || !row.key) continue;
      stmt.run(String(row.key), row.status === 'revoked' ? 'revoked' : 'active',
        String(row.note || ''), String(row.device_id || ''),
        row.created_at || nowIso(), row.expires_at || addDays(30), row.last_seen || '');
      n++;
    }
    console.log(`[git-restore] restored ${n} keys from ${GITHUB_REPO}`);
  } catch (e) { console.error('[git-restore] failed:', e.message); }
}

/* ------------------------------------------------------------------ keep-alive
 * Render Free spins a service down after 15 min without INBOUND traffic
 * (~1 min to wake up, and the local disk is wiped). Hitting our own public
 * URL every KEEPALIVE_MIN minutes keeps it warm 24/7 — that is ~744 h/month,
 * inside Render's 750 free instance hours per workspace per month.
 */
const KEEPALIVE_URL = process.env.KEEPALIVE_URL || '';
const KEEPALIVE_MIN = parseFloat(process.env.KEEPALIVE_MIN || '9');
if (KEEPALIVE_URL && KEEPALIVE_MIN > 0) {
  const beat = async () => {
    try {
      await fetch(KEEPALIVE_URL, { signal: AbortSignal.timeout(15000) });
      console.log('[keepalive] ok', new Date().toISOString());
    } catch (e) {
      console.error('[keepalive] failed:', e.message);
    }
  };
  setInterval(beat, Math.max(0.05, KEEPALIVE_MIN) * 60 * 1000);
  console.log(`[keepalive] pinging ${KEEPALIVE_URL} every ${KEEPALIVE_MIN} min`);
}

app.get('/api/health', (_req, res) => res.json({ ok: true, at: nowIso() }));

// restore BEFORE listening so the dashboard never sees a wiped-empty state
restoreFromGit().finally(() =>
  app.listen(PORT, () => console.log(`panel listening on :${PORT}`)));
