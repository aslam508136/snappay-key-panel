package i;

import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class s extends r implements ActionProvider.VisibilityListener {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public h.a f1115c;

    @Override // i.r
    public final boolean a() {
        return this.f1113a.isVisible();
    }

    @Override // i.r
    public final View b(MenuItem menuItem) {
        return this.f1113a.onCreateActionView(menuItem);
    }

    @Override // i.r
    public final boolean c() {
        return this.f1113a.overridesItemVisibility();
    }

    @Override // i.r
    public final void d(h.a aVar) {
        this.f1115c = aVar;
        this.f1113a.setVisibilityListener(this);
    }

    @Override // android.view.ActionProvider.VisibilityListener
    public final void onActionProviderVisibilityChanged(boolean z2) {
        h.a aVar = this.f1115c;
        if (aVar != null) {
            o oVar = ((q) aVar.f892c).f1100n;
            oVar.f1067h = true;
            oVar.p(true);
        }
    }
}
