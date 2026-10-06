package d;

import android.view.MenuItem;
import android.view.ViewGroup;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class t implements h.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final h.b f720a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ a0 f721b;

    public t(a0 a0Var, h.g gVar) {
        this.f721b = a0Var;
        this.f720a = gVar;
    }

    @Override // h.b
    public final boolean a(h.c cVar, MenuItem menuItem) {
        return this.f720a.a(cVar, menuItem);
    }

    @Override // h.b
    public final boolean b(h.c cVar, i.o oVar) {
        ViewGroup viewGroup = this.f721b.f599v;
        WeakHashMap weakHashMap = x.u.f2012a;
        viewGroup.requestApplyInsets();
        return this.f720a.b(cVar, oVar);
    }

    @Override // h.b
    public final void c(h.c cVar) {
        this.f720a.c(cVar);
        a0 a0Var = this.f721b;
        if (a0Var.f594q != null) {
            a0Var.f583f.getDecorView().removeCallbacks(a0Var.f595r);
        }
        if (a0Var.f593p != null) {
            x.y yVar = a0Var.f596s;
            if (yVar != null) {
                yVar.b();
            }
            x.y yVarA = x.u.a(a0Var.f593p);
            yVarA.a(0.0f);
            a0Var.f596s = yVarA;
            yVarA.d(new s(this, 2));
        }
        o oVar = a0Var.f585h;
        if (oVar != null) {
            oVar.g();
        }
        a0Var.f592o = null;
        ViewGroup viewGroup = a0Var.f599v;
        WeakHashMap weakHashMap = x.u.f2012a;
        viewGroup.requestApplyInsets();
    }

    @Override // h.b
    public final boolean d(h.c cVar, i.o oVar) {
        return this.f720a.d(cVar, oVar);
    }
}
