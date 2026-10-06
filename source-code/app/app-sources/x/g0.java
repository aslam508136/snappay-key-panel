package x;

import android.view.WindowInsets;

/* JADX INFO: loaded from: classes.dex */
public class g0 extends f0 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public r.b f1993l;

    public g0(l0 l0Var, WindowInsets windowInsets) {
        super(l0Var, windowInsets);
        this.f1993l = null;
    }

    @Override // x.k0
    public l0 b() {
        return l0.c(this.f1985c.consumeStableInsets(), null);
    }

    @Override // x.k0
    public l0 c() {
        return l0.c(this.f1985c.consumeSystemWindowInsets(), null);
    }

    @Override // x.k0
    public final r.b f() {
        if (this.f1993l == null) {
            WindowInsets windowInsets = this.f1985c;
            this.f1993l = r.b.a(windowInsets.getStableInsetLeft(), windowInsets.getStableInsetTop(), windowInsets.getStableInsetRight(), windowInsets.getStableInsetBottom());
        }
        return this.f1993l;
    }

    @Override // x.k0
    public boolean i() {
        return this.f1985c.isConsumed();
    }

    @Override // x.k0
    public void m(r.b bVar) {
        this.f1993l = bVar;
    }
}
