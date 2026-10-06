package h;

import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import i.e0;

/* JADX INFO: loaded from: classes.dex */
public final class h extends ActionMode {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f910a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c f911b;

    public h(Context context, c cVar) {
        this.f910a = context;
        this.f911b = cVar;
    }

    @Override // android.view.ActionMode
    public final void finish() {
        this.f911b.a();
    }

    @Override // android.view.ActionMode
    public final View getCustomView() {
        return this.f911b.c();
    }

    @Override // android.view.ActionMode
    public final Menu getMenu() {
        return new e0(this.f910a, this.f911b.e());
    }

    @Override // android.view.ActionMode
    public final MenuInflater getMenuInflater() {
        return this.f911b.f();
    }

    @Override // android.view.ActionMode
    public final CharSequence getSubtitle() {
        return this.f911b.g();
    }

    @Override // android.view.ActionMode
    public final Object getTag() {
        return this.f911b.f893b;
    }

    @Override // android.view.ActionMode
    public final CharSequence getTitle() {
        return this.f911b.h();
    }

    @Override // android.view.ActionMode
    public final boolean getTitleOptionalHint() {
        return this.f911b.f894c;
    }

    @Override // android.view.ActionMode
    public final void invalidate() {
        this.f911b.i();
    }

    @Override // android.view.ActionMode
    public final boolean isTitleOptional() {
        return this.f911b.j();
    }

    @Override // android.view.ActionMode
    public final void setCustomView(View view) {
        this.f911b.k(view);
    }

    @Override // android.view.ActionMode
    public final void setSubtitle(int i2) {
        this.f911b.l(i2);
    }

    @Override // android.view.ActionMode
    public final void setTag(Object obj) {
        this.f911b.f893b = obj;
    }

    @Override // android.view.ActionMode
    public final void setTitle(int i2) {
        this.f911b.n(i2);
    }

    @Override // android.view.ActionMode
    public final void setTitleOptionalHint(boolean z2) {
        this.f911b.p(z2);
    }

    @Override // android.view.ActionMode
    public final void setSubtitle(CharSequence charSequence) {
        this.f911b.m(charSequence);
    }

    @Override // android.view.ActionMode
    public final void setTitle(CharSequence charSequence) {
        this.f911b.o(charSequence);
    }
}
