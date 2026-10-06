package i;

import android.view.MenuItem;
import j.u1;

/* JADX INFO: loaded from: classes.dex */
public final class g implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Object f993a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f994b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f995c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f996d;

    public /* synthetic */ g(u1 u1Var, h hVar, q qVar, Object obj) {
        this.f996d = u1Var;
        this.f993a = hVar;
        this.f994b = qVar;
        this.f995c = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        h hVar = (h) this.f993a;
        if (hVar != null) {
            h.a aVar = (h.a) this.f996d;
            ((i) aVar.f892c).B = true;
            hVar.f1018b.c(false);
            ((i) aVar.f892c).B = false;
        }
        MenuItem menuItem = (MenuItem) this.f994b;
        if (menuItem.isEnabled() && menuItem.hasSubMenu()) {
            ((o) this.f995c).q(menuItem, null, 4);
        }
    }
}
