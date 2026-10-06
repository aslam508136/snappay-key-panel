package androidx.fragment.app;

/* JADX INFO: loaded from: classes.dex */
public final class g extends l implements androidx.lifecycle.x, androidx.activity.k, androidx.activity.result.f, u {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ h f321e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(d.n nVar) {
        super(nVar);
        this.f321e = nVar;
    }

    @Override // androidx.activity.k
    public final androidx.activity.j a() {
        return this.f321e.f67g;
    }

    @Override // androidx.activity.result.f
    public final androidx.activity.d c() {
        return this.f321e.f68h;
    }

    @Override // androidx.lifecycle.x
    public final androidx.lifecycle.w e() {
        return this.f321e.e();
    }

    @Override // androidx.lifecycle.l
    public final androidx.lifecycle.n h() {
        return this.f321e.f323j;
    }
}
