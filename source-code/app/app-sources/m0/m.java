package m0;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

/* JADX INFO: loaded from: classes.dex */
public abstract class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static SharedPreferences f1678a;

    public static synchronized SharedPreferences a(Context context) {
        SharedPreferences sharedPreferences;
        if (f1678a == null) {
            Context applicationContext = context.getApplicationContext();
            try {
                sharedPreferences = EncryptedSharedPreferences.create(applicationContext, "SnapPayPrefs", new MasterKey.Builder(applicationContext).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build(), EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
            } catch (Exception unused) {
                sharedPreferences = applicationContext.getSharedPreferences("SnapPayPrefs_plain", 0);
            }
            f1678a = sharedPreferences;
        }
        return f1678a;
    }
}
