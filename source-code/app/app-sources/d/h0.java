package d;

import android.content.Context;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.ActionBarContextView;
import j.a3;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class h0 extends h.c implements i.m {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Context f647d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final i.o f648e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public h.b f649f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public WeakReference f650g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ i0 f651h;

    public h0(i0 i0Var, Context context, t tVar) {
        this.f651h = i0Var;
        this.f647d = context;
        this.f649f = tVar;
        i.o oVar = new i.o(context);
        oVar.f1071l = 1;
        this.f648e = oVar;
        oVar.f1064e = this;
    }

    @Override // h.c
    public final void a() {
        i0 i0Var = this.f651h;
        if (i0Var.f688i != this) {
            return;
        }
        if (!i0Var.f695p) {
            this.f649f.c(this);
        } else {
            i0Var.f689j = this;
            i0Var.f690k = this.f649f;
        }
        this.f649f = null;
        i0Var.i(false);
        ActionBarContextView actionBarContextView = i0Var.f685f;
        if (actionBarContextView.f131l == null) {
            actionBarContextView.e();
        }
        ((a3) i0Var.f684e).f1157a.sendAccessibilityEvent(32);
        i0Var.f682c.setHideOnContentScrollEnabled(i0Var.f700u);
        i0Var.f688i = null;
    }

    @Override // i.m
    public final void b(i.o oVar) {
        if (this.f649f == null) {
            return;
        }
        i();
        j.m mVar = this.f651h.f685f.f124e;
        if (mVar != null) {
            mVar.l();
        }
    }

    @Override // h.c
    public final View c() {
        WeakReference weakReference = this.f650g;
        if (weakReference != null) {
            return (View) weakReference.get();
        }
        return null;
    }

    @Override // i.m
    public final boolean d(i.o oVar, MenuItem menuItem) {
        h.b bVar = this.f649f;
        if (bVar != null) {
            return bVar.a(this, menuItem);
        }
        return false;
    }

    @Override // h.c
    public final i.o e() {
        return this.f648e;
    }

    @Override // h.c
    public final MenuInflater f() {
        return new h.k(this.f647d);
    }

    @Override // h.c
    public final CharSequence g() {
        return this.f651h.f685f.getSubtitle();
    }

    @Override // h.c
    public final CharSequence h() {
        return this.f651h.f685f.getTitle();
    }

    @Override // h.c
    public final void i() {
        if (this.f651h.f688i != this) {
            return;
        }
        i.o oVar = this.f648e;
        oVar.w();
        try {
            this.f649f.b(this, oVar);
        } finally {
            oVar.v();
        }
    }

    @Override // h.c
    public final boolean j() {
        return this.f651h.f685f.f139t;
    }

    @Override // h.c
    public final void k(View view) {
        this.f651h.f685f.setCustomView(view);
        this.f650g = new WeakReference(view);
    }

    @Override // h.c
    public final void l(int i2) {
        m(this.f651h.f680a.getResources().getString(i2));
    }

    @Override // h.c
    public final void m(CharSequence charSequence) {
        this.f651h.f685f.setSubtitle(charSequence);
    }

    @Override // h.c
    public final void n(int i2) {
        o(this.f651h.f680a.getResources().getString(i2));
    }

    @Override // h.c
    public final void o(CharSequence charSequence) {
        this.f651h.f685f.setTitle(charSequence);
    }

    @Override // h.c
    public final void p(boolean z2) {
        this.f894c = z2;
        this.f651h.f685f.setTitleOptional(z2);
    }
}
