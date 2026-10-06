package h;

import android.content.Context;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.ActionBarContextView;
import i.o;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class f extends c implements i.m {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Context f900d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ActionBarContextView f901e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final b f902f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public WeakReference f903g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f904h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final o f905i;

    public f(Context context, ActionBarContextView actionBarContextView, b bVar) {
        this.f900d = context;
        this.f901e = actionBarContextView;
        this.f902f = bVar;
        o oVar = new o(actionBarContextView.getContext());
        oVar.f1071l = 1;
        this.f905i = oVar;
        oVar.f1064e = this;
    }

    @Override // h.c
    public final void a() {
        if (this.f904h) {
            return;
        }
        this.f904h = true;
        this.f901e.sendAccessibilityEvent(32);
        this.f902f.c(this);
    }

    @Override // i.m
    public final void b(o oVar) {
        i();
        j.m mVar = this.f901e.f124e;
        if (mVar != null) {
            mVar.l();
        }
    }

    @Override // h.c
    public final View c() {
        WeakReference weakReference = this.f903g;
        if (weakReference != null) {
            return (View) weakReference.get();
        }
        return null;
    }

    @Override // i.m
    public final boolean d(o oVar, MenuItem menuItem) {
        return this.f902f.a(this, menuItem);
    }

    @Override // h.c
    public final o e() {
        return this.f905i;
    }

    @Override // h.c
    public final MenuInflater f() {
        return new k(this.f901e.getContext());
    }

    @Override // h.c
    public final CharSequence g() {
        return this.f901e.getSubtitle();
    }

    @Override // h.c
    public final CharSequence h() {
        return this.f901e.getTitle();
    }

    @Override // h.c
    public final void i() {
        this.f902f.b(this, this.f905i);
    }

    @Override // h.c
    public final boolean j() {
        return this.f901e.f139t;
    }

    @Override // h.c
    public final void k(View view) {
        this.f901e.setCustomView(view);
        this.f903g = view != null ? new WeakReference(view) : null;
    }

    @Override // h.c
    public final void l(int i2) {
        m(this.f900d.getString(i2));
    }

    @Override // h.c
    public final void m(CharSequence charSequence) {
        this.f901e.setSubtitle(charSequence);
    }

    @Override // h.c
    public final void n(int i2) {
        o(this.f900d.getString(i2));
    }

    @Override // h.c
    public final void o(CharSequence charSequence) {
        this.f901e.setTitle(charSequence);
    }

    @Override // h.c
    public final void p(boolean z2) {
        this.f894c = z2;
        this.f901e.setTitleOptional(z2);
    }
}
