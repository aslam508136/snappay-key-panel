package i;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import androidx.appcompat.view.menu.ExpandedMenuView;

/* JADX INFO: loaded from: classes.dex */
public final class k implements b0, AdapterView.OnItemClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f1047b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public LayoutInflater f1048c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public o f1049d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ExpandedMenuView f1050e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public a0 f1051f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public j f1052g;

    public k(Context context) {
        this.f1047b = context;
        this.f1048c = LayoutInflater.from(context);
    }

    @Override // i.b0
    public final void a(o oVar, boolean z2) {
        a0 a0Var = this.f1051f;
        if (a0Var != null) {
            a0Var.a(oVar, z2);
        }
    }

    @Override // i.b0
    public final boolean c(q qVar) {
        return false;
    }

    @Override // i.b0
    public final boolean d() {
        return false;
    }

    @Override // i.b0
    public final void e(Context context, o oVar) {
        if (this.f1047b != null) {
            this.f1047b = context;
            if (this.f1048c == null) {
                this.f1048c = LayoutInflater.from(context);
            }
        }
        this.f1049d = oVar;
        j jVar = this.f1052g;
        if (jVar != null) {
            jVar.notifyDataSetChanged();
        }
    }

    @Override // i.b0
    public final void g(a0 a0Var) {
        this.f1051f = a0Var;
    }

    @Override // i.b0
    public final boolean h(q qVar) {
        return false;
    }

    @Override // i.b0
    public final void i() {
        j jVar = this.f1052g;
        if (jVar != null) {
            jVar.notifyDataSetChanged();
        }
    }

    @Override // i.b0
    public final boolean k(h0 h0Var) {
        if (!h0Var.hasVisibleItems()) {
            return false;
        }
        p pVar = new p(h0Var);
        Context context = h0Var.f1060a;
        d.j jVar = new d.j(context);
        Object obj = jVar.f705b;
        d.f fVar = (d.f) obj;
        k kVar = new k(fVar.f628a);
        pVar.f1086d = kVar;
        kVar.f1051f = pVar;
        h0Var.b(kVar, context);
        k kVar2 = pVar.f1086d;
        if (kVar2.f1052g == null) {
            kVar2.f1052g = new j(kVar2);
        }
        fVar.f638k = kVar2.f1052g;
        fVar.f639l = pVar;
        View view = h0Var.f1074o;
        if (view != null) {
            fVar.f632e = view;
        } else {
            fVar.f630c = h0Var.f1073n;
            ((d.f) obj).f631d = h0Var.f1072m;
        }
        fVar.f637j = pVar;
        d.k kVarA = jVar.a();
        pVar.f1085c = kVarA;
        kVarA.setOnDismissListener(pVar);
        WindowManager.LayoutParams attributes = pVar.f1085c.getWindow().getAttributes();
        attributes.type = 1003;
        attributes.flags |= 131072;
        pVar.f1085c.show();
        a0 a0Var = this.f1051f;
        if (a0Var == null) {
            return true;
        }
        a0Var.c(h0Var);
        return true;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
        this.f1049d.q(this.f1052g.getItem(i2), this, 0);
    }
}
