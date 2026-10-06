package x;

import android.graphics.Rect;
import android.util.Log;
import android.view.WindowInsets;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public final class b0 extends e0 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Field f1965c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static boolean f1966d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static Constructor f1967e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static boolean f1968f = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public WindowInsets f1969a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public r.b f1970b;

    public b0() {
        this.f1969a = e();
    }

    private static WindowInsets e() {
        if (!f1966d) {
            try {
                f1965c = WindowInsets.class.getDeclaredField("CONSUMED");
            } catch (ReflectiveOperationException e2) {
                Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets.CONSUMED field", e2);
            }
            f1966d = true;
        }
        Field field = f1965c;
        if (field != null) {
            try {
                WindowInsets windowInsets = (WindowInsets) field.get(null);
                if (windowInsets != null) {
                    return new WindowInsets(windowInsets);
                }
            } catch (ReflectiveOperationException e3) {
                Log.i("WindowInsetsCompat", "Could not get value from WindowInsets.CONSUMED field", e3);
            }
        }
        if (!f1968f) {
            try {
                f1967e = WindowInsets.class.getConstructor(Rect.class);
            } catch (ReflectiveOperationException e4) {
                Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets(Rect) constructor", e4);
            }
            f1968f = true;
        }
        Constructor constructor = f1967e;
        if (constructor != null) {
            try {
                return (WindowInsets) constructor.newInstance(new Rect());
            } catch (ReflectiveOperationException e5) {
                Log.i("WindowInsetsCompat", "Could not invoke WindowInsets(Rect) constructor", e5);
            }
        }
        return null;
    }

    @Override // x.e0
    public l0 b() {
        a();
        l0 l0VarC = l0.c(this.f1969a, null);
        k0 k0Var = l0VarC.f2000a;
        k0Var.k(null);
        k0Var.m(this.f1970b);
        return l0VarC;
    }

    @Override // x.e0
    public void c(r.b bVar) {
        this.f1970b = bVar;
    }

    @Override // x.e0
    public void d(r.b bVar) {
        WindowInsets windowInsets = this.f1969a;
        if (windowInsets != null) {
            this.f1969a = windowInsets.replaceSystemWindowInsets(bVar.f1890a, bVar.f1891b, bVar.f1892c, bVar.f1893d);
        }
    }

    public b0(l0 l0Var) {
        this.f1969a = l0Var.b();
    }
}
