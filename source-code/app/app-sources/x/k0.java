package x;

import android.os.Build;
import android.view.View;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class k0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final l0 f1997b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final l0 f1998a;

    static {
        e0 c0Var;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 30) {
            c0Var = new d0();
        } else {
            c0Var = i2 >= 29 ? new c0() : new b0();
        }
        f1997b = c0Var.b().f2000a.a().f2000a.b().f2000a.c();
    }

    public k0(l0 l0Var) {
        this.f1998a = l0Var;
    }

    public l0 a() {
        return this.f1998a;
    }

    public l0 b() {
        return this.f1998a;
    }

    public l0 c() {
        return this.f1998a;
    }

    public void d(View view) {
    }

    public d e() {
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof k0)) {
            return false;
        }
        k0 k0Var = (k0) obj;
        return j() == k0Var.j() && i() == k0Var.i() && Objects.equals(g(), k0Var.g()) && Objects.equals(f(), k0Var.f()) && Objects.equals(e(), k0Var.e());
    }

    public r.b f() {
        return r.b.f1889e;
    }

    public r.b g() {
        return r.b.f1889e;
    }

    public l0 h(int i2, int i3, int i4, int i5) {
        return f1997b;
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(j()), Boolean.valueOf(i()), g(), f(), e());
    }

    public boolean i() {
        return false;
    }

    public boolean j() {
        return false;
    }

    public void k(r.b[] bVarArr) {
    }

    public void l(l0 l0Var) {
    }

    public void m(r.b bVar) {
    }
}
