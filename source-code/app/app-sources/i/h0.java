package i;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class h0 extends o implements SubMenu {
    public final q A;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final o f1020z;

    public h0(Context context, o oVar, q qVar) {
        super(context);
        this.f1020z = oVar;
        this.A = qVar;
    }

    @Override // i.o
    public final boolean d(q qVar) {
        return this.f1020z.d(qVar);
    }

    @Override // i.o
    public final boolean e(o oVar, MenuItem menuItem) {
        return super.e(oVar, menuItem) || this.f1020z.e(oVar, menuItem);
    }

    @Override // i.o
    public final boolean f(q qVar) {
        return this.f1020z.f(qVar);
    }

    @Override // android.view.SubMenu
    public final MenuItem getItem() {
        return this.A;
    }

    @Override // i.o
    public final String j() {
        q qVar = this.A;
        int i2 = qVar != null ? qVar.f1087a : 0;
        if (i2 == 0) {
            return null;
        }
        return "android:menu:actionviewstates:" + i2;
    }

    @Override // i.o
    public final o k() {
        return this.f1020z.k();
    }

    @Override // i.o
    public final boolean m() {
        return this.f1020z.m();
    }

    @Override // i.o
    public final boolean n() {
        return this.f1020z.n();
    }

    @Override // i.o
    public final boolean o() {
        return this.f1020z.o();
    }

    @Override // i.o, android.view.Menu
    public final void setGroupDividerEnabled(boolean z2) {
        this.f1020z.setGroupDividerEnabled(z2);
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderIcon(int i2) {
        u(0, null, i2, null, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderTitle(int i2) {
        u(i2, null, 0, null, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderView(View view) {
        u(0, null, 0, null, view);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setIcon(int i2) {
        this.A.setIcon(i2);
        return this;
    }

    @Override // i.o, android.view.Menu
    public final void setQwertyMode(boolean z2) {
        this.f1020z.setQwertyMode(z2);
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderIcon(Drawable drawable) {
        u(0, null, 0, drawable, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderTitle(CharSequence charSequence) {
        u(0, charSequence, 0, null, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setIcon(Drawable drawable) {
        this.A.setIcon(drawable);
        return this;
    }
}
