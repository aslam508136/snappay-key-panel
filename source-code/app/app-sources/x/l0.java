package x;

import android.os.Build;
import android.view.View;
import android.view.WindowInsets;
import java.util.Objects;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class l0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final l0 f1999b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final k0 f2000a;

    static {
        f1999b = Build.VERSION.SDK_INT >= 30 ? j0.f1996m : k0.f1997b;
    }

    public l0() {
        this.f2000a = new k0(this);
    }

    public static r.b a(r.b bVar, int i2, int i3, int i4, int i5) {
        int iMax = Math.max(0, bVar.f1890a - i2);
        int iMax2 = Math.max(0, bVar.f1891b - i3);
        int iMax3 = Math.max(0, bVar.f1892c - i4);
        int iMax4 = Math.max(0, bVar.f1893d - i5);
        return (iMax == i2 && iMax2 == i3 && iMax3 == i4 && iMax4 == i5) ? bVar : r.b.a(iMax, iMax2, iMax3, iMax4);
    }

    public static l0 c(WindowInsets windowInsets, View view) {
        windowInsets.getClass();
        l0 l0Var = new l0(windowInsets);
        if (view != null && view.isAttachedToWindow()) {
            WeakHashMap weakHashMap = u.f2012a;
            l0 l0VarA = Build.VERSION.SDK_INT >= 23 ? r.a(view) : q.c(view);
            k0 k0Var = l0Var.f2000a;
            k0Var.l(l0VarA);
            k0Var.d(view.getRootView());
        }
        return l0Var;
    }

    public final WindowInsets b() {
        k0 k0Var = this.f2000a;
        if (k0Var instanceof f0) {
            return ((f0) k0Var).f1985c;
        }
        return null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof l0)) {
            return false;
        }
        return Objects.equals(this.f2000a, ((l0) obj).f2000a);
    }

    public final int hashCode() {
        k0 k0Var = this.f2000a;
        if (k0Var == null) {
            return 0;
        }
        return k0Var.hashCode();
    }

    public l0(WindowInsets windowInsets) {
        int i2 = Build.VERSION.SDK_INT;
        this.f2000a = i2 >= 30 ? new j0(this, windowInsets) : i2 >= 29 ? new i0(this, windowInsets) : i2 >= 28 ? new h0(this, windowInsets) : new g0(this, windowInsets);
    }
}
