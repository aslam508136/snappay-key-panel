package d;

import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class s extends b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f718a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f719b;

    public /* synthetic */ s(Object obj, int i2) {
        this.f718a = i2;
        this.f719b = obj;
    }

    @Override // x.z
    public final void a() {
        int i2 = this.f718a;
        Object obj = this.f719b;
        switch (i2) {
            case 0:
                q qVar = (q) obj;
                qVar.f715b.f593p.setAlpha(1.0f);
                a0 a0Var = qVar.f715b;
                a0Var.f596s.d(null);
                a0Var.f596s = null;
                break;
            case 1:
                a0 a0Var2 = (a0) obj;
                a0Var2.f593p.setAlpha(1.0f);
                a0Var2.f596s.d(null);
                a0Var2.f596s = null;
                break;
            default:
                t tVar = (t) obj;
                tVar.f721b.f593p.setVisibility(8);
                a0 a0Var3 = tVar.f721b;
                PopupWindow popupWindow = a0Var3.f594q;
                if (popupWindow != null) {
                    popupWindow.dismiss();
                } else if (a0Var3.f593p.getParent() instanceof View) {
                    View view = (View) a0Var3.f593p.getParent();
                    WeakHashMap weakHashMap = x.u.f2012a;
                    view.requestApplyInsets();
                }
                a0Var3.f593p.e();
                a0Var3.f596s.d(null);
                a0Var3.f596s = null;
                ViewGroup viewGroup = a0Var3.f599v;
                WeakHashMap weakHashMap2 = x.u.f2012a;
                viewGroup.requestApplyInsets();
                break;
        }
    }

    @Override // b.a, x.z
    public final void c() {
        int i2 = this.f718a;
        Object obj = this.f719b;
        switch (i2) {
            case 0:
                ((q) obj).f715b.f593p.setVisibility(0);
                break;
            case 1:
                a0 a0Var = (a0) obj;
                a0Var.f593p.setVisibility(0);
                a0Var.f593p.sendAccessibilityEvent(32);
                if (a0Var.f593p.getParent() instanceof View) {
                    View view = (View) a0Var.f593p.getParent();
                    WeakHashMap weakHashMap = x.u.f2012a;
                    view.requestApplyInsets();
                }
                break;
        }
    }
}
