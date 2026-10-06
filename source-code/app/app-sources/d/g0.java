package d;

import android.view.View;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class g0 extends b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f645a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ i0 f646b;

    public /* synthetic */ g0(i0 i0Var, int i2) {
        this.f645a = i2;
        this.f646b = i0Var;
    }

    @Override // x.z
    public final void a() {
        View view;
        int i2 = this.f645a;
        i0 i0Var = this.f646b;
        switch (i2) {
            case 0:
                if (i0Var.f694o && (view = i0Var.f686g) != null) {
                    view.setTranslationY(0.0f);
                    i0Var.f683d.setTranslationY(0.0f);
                }
                i0Var.f683d.setVisibility(8);
                i0Var.f683d.setTransitioning(false);
                i0Var.f698s = null;
                h.b bVar = i0Var.f690k;
                if (bVar != null) {
                    bVar.c(i0Var.f689j);
                    i0Var.f689j = null;
                    i0Var.f690k = null;
                }
                ActionBarOverlayLayout actionBarOverlayLayout = i0Var.f682c;
                if (actionBarOverlayLayout != null) {
                    WeakHashMap weakHashMap = x.u.f2012a;
                    actionBarOverlayLayout.requestApplyInsets();
                }
                break;
            default:
                i0Var.f698s = null;
                i0Var.f683d.requestLayout();
                break;
        }
    }
}
