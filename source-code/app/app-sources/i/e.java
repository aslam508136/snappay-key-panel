package i;

import android.view.View;
import android.view.ViewTreeObserver;
import j.o0;
import j.r0;
import j.w1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class e implements ViewTreeObserver.OnGlobalLayoutListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f988b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f989c;

    public /* synthetic */ e(Object obj, int i2) {
        this.f988b = i2;
        this.f989c = obj;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        int i2 = this.f988b;
        boolean z2 = false;
        Object obj = this.f989c;
        switch (i2) {
            case 0:
                i iVar = (i) obj;
                if (iVar.b()) {
                    ArrayList arrayList = iVar.f1028j;
                    if (arrayList.size() > 0 && !((h) arrayList.get(0)).f1017a.f1440y) {
                        View view = iVar.f1035q;
                        if (view != null && view.isShown()) {
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                ((h) it.next()).f1017a.f();
                            }
                        } else {
                            iVar.dismiss();
                        }
                        break;
                    }
                }
                break;
            case 1:
                g0 g0Var = (g0) obj;
                if (g0Var.b()) {
                    w1 w1Var = g0Var.f1004j;
                    if (!w1Var.f1440y) {
                        View view2 = g0Var.f1009o;
                        if (view2 != null && view2.isShown()) {
                            w1Var.f();
                        } else {
                            g0Var.dismiss();
                        }
                    }
                }
                break;
            case 2:
                r0 r0Var = (r0) obj;
                if (!r0Var.getInternalPopup().b()) {
                    r0Var.f1389g.d(r0Var.getTextDirection(), r0Var.getTextAlignment());
                }
                ViewTreeObserver viewTreeObserver = r0Var.getViewTreeObserver();
                if (viewTreeObserver != null) {
                    viewTreeObserver.removeOnGlobalLayoutListener(this);
                }
                break;
            default:
                o0 o0Var = (o0) obj;
                r0 r0Var2 = o0Var.H;
                WeakHashMap weakHashMap = x.u.f2012a;
                if (r0Var2.isAttachedToWindow() && r0Var2.getGlobalVisibleRect(o0Var.F)) {
                    z2 = true;
                }
                if (!z2) {
                    o0Var.dismiss();
                } else {
                    o0Var.s();
                    o0Var.f();
                }
                break;
        }
    }
}
