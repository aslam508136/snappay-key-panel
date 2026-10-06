package a0;

import android.security.keystore.KeyGenParameterSpec;
import android.widget.ThemedSpinnerAdapter;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class c {
    public static /* synthetic */ KeyGenParameterSpec.Builder h(String str) {
        return new KeyGenParameterSpec.Builder(str, 3);
    }

    public static /* bridge */ /* synthetic */ KeyGenParameterSpec j(Object obj) {
        return (KeyGenParameterSpec) obj;
    }

    public static /* bridge */ /* synthetic */ ThemedSpinnerAdapter m(Object obj) {
        return (ThemedSpinnerAdapter) obj;
    }

    public static /* bridge */ /* synthetic */ boolean x(Object obj) {
        return obj instanceof ThemedSpinnerAdapter;
    }
}
