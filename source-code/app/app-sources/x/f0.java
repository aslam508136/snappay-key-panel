package x;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public abstract class f0 extends k0 {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static boolean f1979f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static Method f1980g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static Class f1981h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static Class f1982i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static Field f1983j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static Field f1984k;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final WindowInsets f1985c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public r.b f1986d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public r.b f1987e;

    public f0(l0 l0Var, WindowInsets windowInsets) {
        super(l0Var);
        this.f1986d = null;
        this.f1985c = windowInsets;
    }

    private r.b n(View view) {
        if (Build.VERSION.SDK_INT >= 30) {
            throw new UnsupportedOperationException("getVisibleInsets() should not be called on API >= 30. Use WindowInsets.isVisible() instead.");
        }
        if (!f1979f) {
            o();
        }
        Method method = f1980g;
        if (method != null && f1982i != null && f1983j != null) {
            try {
                Object objInvoke = method.invoke(view, new Object[0]);
                if (objInvoke == null) {
                    Log.w("WindowInsetsCompat", "Failed to get visible insets. getViewRootImpl() returned null from the provided view. This means that the view is either not attached or the method has been overridden", new NullPointerException());
                    return null;
                }
                Rect rect = (Rect) f1983j.get(f1984k.get(objInvoke));
                if (rect != null) {
                    return r.b.a(rect.left, rect.top, rect.right, rect.bottom);
                }
                return null;
            } catch (ReflectiveOperationException e2) {
                Log.e("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + e2.getMessage(), e2);
            }
        }
        return null;
    }

    @SuppressLint({"PrivateApi"})
    private static void o() {
        try {
            f1980g = View.class.getDeclaredMethod("getViewRootImpl", new Class[0]);
            f1981h = Class.forName("android.view.ViewRootImpl");
            Class<?> cls = Class.forName("android.view.View$AttachInfo");
            f1982i = cls;
            f1983j = cls.getDeclaredField("mVisibleInsets");
            f1984k = f1981h.getDeclaredField("mAttachInfo");
            f1983j.setAccessible(true);
            f1984k.setAccessible(true);
        } catch (ReflectiveOperationException e2) {
            Log.e("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + e2.getMessage(), e2);
        }
        f1979f = true;
    }

    @Override // x.k0
    public void d(View view) {
        r.b bVarN = n(view);
        if (bVarN == null) {
            bVarN = r.b.f1889e;
        }
        p(bVarN);
    }

    @Override // x.k0
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            return Objects.equals(this.f1987e, ((f0) obj).f1987e);
        }
        return false;
    }

    @Override // x.k0
    public final r.b g() {
        if (this.f1986d == null) {
            WindowInsets windowInsets = this.f1985c;
            this.f1986d = r.b.a(windowInsets.getSystemWindowInsetLeft(), windowInsets.getSystemWindowInsetTop(), windowInsets.getSystemWindowInsetRight(), windowInsets.getSystemWindowInsetBottom());
        }
        return this.f1986d;
    }

    @Override // x.k0
    public l0 h(int i2, int i3, int i4, int i5) {
        e0 c0Var;
        l0 l0VarC = l0.c(this.f1985c, null);
        int i6 = Build.VERSION.SDK_INT;
        if (i6 >= 30) {
            c0Var = new d0(l0VarC);
        } else {
            c0Var = i6 >= 29 ? new c0(l0VarC) : new b0(l0VarC);
        }
        c0Var.d(l0.a(g(), i2, i3, i4, i5));
        c0Var.c(l0.a(f(), i2, i3, i4, i5));
        return c0Var.b();
    }

    @Override // x.k0
    public boolean j() {
        return this.f1985c.isRound();
    }

    @Override // x.k0
    public void k(r.b[] bVarArr) {
    }

    @Override // x.k0
    public void l(l0 l0Var) {
    }

    public void p(r.b bVar) {
        this.f1987e = bVar;
    }
}
