package i;

import android.view.MenuItem;

/* JADX INFO: loaded from: classes.dex */
public final class u implements MenuItem.OnActionExpandListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final MenuItem.OnActionExpandListener f1117a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ w f1118b;

    public u(w wVar, MenuItem.OnActionExpandListener onActionExpandListener) {
        this.f1118b = wVar;
        this.f1117a = onActionExpandListener;
    }

    @Override // android.view.MenuItem.OnActionExpandListener
    public final boolean onMenuItemActionCollapse(MenuItem menuItem) {
        return this.f1117a.onMenuItemActionCollapse(this.f1118b.m(menuItem));
    }

    @Override // android.view.MenuItem.OnActionExpandListener
    public final boolean onMenuItemActionExpand(MenuItem menuItem) {
        return this.f1117a.onMenuItemActionExpand(this.f1118b.m(menuItem));
    }
}
