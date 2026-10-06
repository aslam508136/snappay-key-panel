package i;

import android.view.MenuItem;

/* JADX INFO: loaded from: classes.dex */
public final class v implements MenuItem.OnMenuItemClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final MenuItem.OnMenuItemClickListener f1119a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ w f1120b;

    public v(w wVar, MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.f1120b = wVar;
        this.f1119a = onMenuItemClickListener;
    }

    @Override // android.view.MenuItem.OnMenuItemClickListener
    public final boolean onMenuItemClick(MenuItem menuItem) {
        return this.f1119a.onMenuItemClick(this.f1120b.m(menuItem));
    }
}
