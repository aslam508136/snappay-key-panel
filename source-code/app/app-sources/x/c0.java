package x;

import android.view.WindowInsets;

/* JADX INFO: loaded from: classes.dex */
public class c0 extends e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final WindowInsets.Builder f1977a;

    public c0() {
        this.f1977a = androidx.lifecycle.q.d();
    }

    @Override // x.e0
    public l0 b() {
        a();
        l0 l0VarC = l0.c(this.f1977a.build(), null);
        l0VarC.f2000a.k(null);
        return l0VarC;
    }

    @Override // x.e0
    public void c(r.b bVar) {
        this.f1977a.setStableInsets(bVar.b());
    }

    @Override // x.e0
    public void d(r.b bVar) {
        this.f1977a.setSystemWindowInsets(bVar.b());
    }

    public c0(l0 l0Var) {
        WindowInsets windowInsetsB = l0Var.b();
        this.f1977a = windowInsetsB != null ? androidx.lifecycle.q.e(windowInsetsB) : androidx.lifecycle.q.d();
    }
}
