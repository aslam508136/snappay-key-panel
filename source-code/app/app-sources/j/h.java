package j;

import android.content.Context;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class h extends i.z {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f1245m = 0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ m f1246n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(m mVar, Context context, i.o oVar, l lVar) {
        super(context, oVar, lVar, true);
        this.f1246n = mVar;
        this.f1131g = 8388613;
        h.a aVar = mVar.f1316x;
        this.f1133i = aVar;
        i.x xVar = this.f1134j;
        if (xVar != null) {
            xVar.g(aVar);
        }
    }

    @Override // i.z
    public final void c() {
        int i2 = this.f1245m;
        m mVar = this.f1246n;
        switch (i2) {
            case 0:
                mVar.f1313u = null;
                super.c();
                break;
            default:
                i.o oVar = mVar.f1296d;
                if (oVar != null) {
                    oVar.c(true);
                }
                mVar.f1312t = null;
                super.c();
                break;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(m mVar, Context context, i.h0 h0Var, View view) {
        super(context, h0Var, view, false);
        this.f1246n = mVar;
        if (!h0Var.A.f()) {
            View view2 = mVar.f1302j;
            this.f1130f = view2 == null ? (View) mVar.f1301i : view2;
        }
        h.a aVar = mVar.f1316x;
        this.f1133i = aVar;
        i.x xVar = this.f1134j;
        if (xVar != null) {
            xVar.g(aVar);
        }
    }
}
