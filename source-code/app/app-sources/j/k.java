package j;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class k extends l1 {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ int f1269j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ Object f1270k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ View f1271l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ k(View view, View view2, Object obj, int i2) {
        super(view2);
        this.f1269j = i2;
        this.f1271l = view;
        this.f1270k = obj;
    }

    @Override // j.l1
    public final i.f0 b() {
        switch (this.f1269j) {
            case 0:
                h hVar = ((l) this.f1271l).f1280d.f1312t;
                if (hVar == null) {
                    return null;
                }
                return hVar.a();
            default:
                return (o0) this.f1270k;
        }
    }

    @Override // j.l1
    public final boolean c() {
        int i2 = this.f1269j;
        View view = this.f1271l;
        switch (i2) {
            case 0:
                ((l) view).f1280d.l();
                break;
            default:
                r0 r0Var = (r0) view;
                if (!r0Var.getInternalPopup().b()) {
                    r0Var.f1389g.d(r0Var.getTextDirection(), r0Var.getTextAlignment());
                }
                break;
        }
        return true;
    }

    @Override // j.l1
    public final boolean d() {
        switch (this.f1269j) {
            case 0:
                m mVar = ((l) this.f1271l).f1280d;
                if (mVar.f1314v != null) {
                    return false;
                }
                mVar.f();
                return true;
            default:
                super.d();
                return true;
        }
    }
}
