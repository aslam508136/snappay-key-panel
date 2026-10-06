package androidx.activity;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.lifecycle.l;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
final class ImmLeaksCleaner implements androidx.lifecycle.j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static int f40b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Field f41c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Field f42d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static Field f43e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Activity f44a;

    public ImmLeaksCleaner(androidx.fragment.app.h hVar) {
        this.f44a = hVar;
    }

    @Override // androidx.lifecycle.j
    public final void a(l lVar, androidx.lifecycle.g gVar) {
        if (gVar != androidx.lifecycle.g.ON_DESTROY) {
            return;
        }
        if (f40b == 0) {
            try {
                f40b = 2;
                Field declaredField = InputMethodManager.class.getDeclaredField("mServedView");
                f42d = declaredField;
                declaredField.setAccessible(true);
                Field declaredField2 = InputMethodManager.class.getDeclaredField("mNextServedView");
                f43e = declaredField2;
                declaredField2.setAccessible(true);
                Field declaredField3 = InputMethodManager.class.getDeclaredField("mH");
                f41c = declaredField3;
                declaredField3.setAccessible(true);
                f40b = 1;
            } catch (NoSuchFieldException unused) {
            }
        }
        if (f40b == 1) {
            InputMethodManager inputMethodManager = (InputMethodManager) this.f44a.getSystemService("input_method");
            try {
                Object obj = f41c.get(inputMethodManager);
                if (obj == null) {
                    return;
                }
                synchronized (obj) {
                    try {
                        try {
                            View view = (View) f42d.get(inputMethodManager);
                            if (view == null) {
                                return;
                            }
                            if (view.isAttachedToWindow()) {
                                return;
                            }
                            try {
                                f43e.set(inputMethodManager, null);
                                inputMethodManager.isActive();
                            } catch (IllegalAccessException unused2) {
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    } catch (ClassCastException unused3) {
                    } catch (IllegalAccessException unused4) {
                    }
                }
            } catch (IllegalAccessException unused5) {
            }
        }
    }
}
