package i;

import androidx.appcompat.view.menu.ActionMenuItemView;
import j.l1;

/* JADX INFO: loaded from: classes.dex */
public final class b extends l1 {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ ActionMenuItemView f984j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(ActionMenuItemView actionMenuItemView) {
        super(actionMenuItemView);
        this.f984j = actionMenuItemView;
    }

    @Override // j.l1
    public final f0 b() {
        j.h hVar;
        c cVar = this.f984j.f87l;
        if (cVar == null || (hVar = ((j.i) cVar).f1250a.f1313u) == null) {
            return null;
        }
        return hVar.a();
    }

    @Override // j.l1
    public final boolean c() {
        f0 f0VarB;
        ActionMenuItemView actionMenuItemView = this.f984j;
        n nVar = actionMenuItemView.f85j;
        return nVar != null && nVar.a(actionMenuItemView.f82g) && (f0VarB = b()) != null && f0VarB.b();
    }
}
