package x;

import android.util.Log;
import android.view.View;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public abstract class a0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Field f1958a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Field f1959b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Field f1960c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final boolean f1961d;

    static {
        try {
            Field declaredField = View.class.getDeclaredField("mAttachInfo");
            f1958a = declaredField;
            declaredField.setAccessible(true);
            Class<?> cls = Class.forName("android.view.View$AttachInfo");
            Field declaredField2 = cls.getDeclaredField("mStableInsets");
            f1959b = declaredField2;
            declaredField2.setAccessible(true);
            Field declaredField3 = cls.getDeclaredField("mContentInsets");
            f1960c = declaredField3;
            declaredField3.setAccessible(true);
            f1961d = true;
        } catch (ReflectiveOperationException e2) {
            Log.w("WindowInsetsCompat", "Failed to get visible insets from AttachInfo " + e2.getMessage(), e2);
        }
    }
}
