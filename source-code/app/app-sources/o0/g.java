package o0;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static g f1778i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SharedPreferences f1779a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile boolean f1780b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile String f1781c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public volatile String f1782d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile String f1783e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public volatile String f1784f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public volatile String f1785g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public volatile String f1786h;

    public g(Context context) {
        SharedPreferences sharedPreferences;
        try {
            sharedPreferences = EncryptedSharedPreferences.create(context, "PhonePePrefs", new MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build(), EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
        } catch (Exception unused) {
            sharedPreferences = context.getSharedPreferences("PhonePePrefs_fallback", 0);
        }
        this.f1779a = sharedPreferences;
        String str = new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(new Date());
        if (!str.equals(sharedPreferences.getString("last_reset_date", ""))) {
            c();
            sharedPreferences.edit().putString("last_reset_date", str).apply();
        }
        this.f1780b = sharedPreferences.getBoolean("autopay_active", false);
        this.f1781c = sharedPreferences.getString("payment_mode", "ONE_TIME");
        this.f1782d = sharedPreferences.getString("app_mode", "QR_SCANNER");
        this.f1783e = sharedPreferences.getString("upi_pin_upi_autopay", "");
        this.f1784f = sharedPreferences.getString("upi_pin_qr_scanner", "");
        this.f1785g = sharedPreferences.getString("preferred_bank", "NONE");
        this.f1786h = sharedPreferences.getString("custom_bank_name", "");
    }

    public static synchronized g a(Context context) {
        if (f1778i == null) {
            f1778i = new g(context.getApplicationContext());
        }
        return f1778i;
    }

    public final boolean b() {
        return !("UPI_AUTOPAY".equals(this.f1782d) ? this.f1783e.isEmpty() : this.f1784f.isEmpty());
    }

    public final void c() {
        this.f1779a.edit().putLong("last_payment_time", 0L).putLong("last_click_time", 0L).putInt("total_payments", 0).putLong("total_time", 0L).putLong("total_click_time", 0L).apply();
    }

    public final void d(long j2) {
        SharedPreferences sharedPreferences = this.f1779a;
        sharedPreferences.edit().putLong("last_click_time", j2).putLong("total_click_time", sharedPreferences.getLong("total_click_time", 0L) + j2).apply();
    }

    public final void e(long j2) {
        SharedPreferences sharedPreferences = this.f1779a;
        sharedPreferences.edit().putLong("last_payment_time", j2).putInt("total_payments", sharedPreferences.getInt("total_payments", 0) + 1).putLong("total_time", sharedPreferences.getLong("total_time", 0L) + j2).apply();
    }

    public final void f(boolean z2) {
        this.f1779a.edit().putBoolean("autopay_active", z2).apply();
        this.f1780b = z2;
    }
}
