package j;

import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class o1 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1339a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ t1 f1340b;

    public /* synthetic */ o1(t1 t1Var, int i2) {
        this.f1339a = i2;
        this.f1340b = t1Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i2 = this.f1339a;
        t1 t1Var = this.f1340b;
        switch (i2) {
            case 1:
                i1 i1Var = t1Var.f1419d;
                if (i1Var != null) {
                    i1Var.setListSelectionHidden(true);
                    i1Var.requestLayout();
                }
                break;
            default:
                i1 i1Var2 = t1Var.f1419d;
                if (i1Var2 != null) {
                    WeakHashMap weakHashMap = x.u.f2012a;
                    if (i1Var2.isAttachedToWindow() && t1Var.f1419d.getCount() > t1Var.f1419d.getChildCount() && t1Var.f1419d.getChildCount() <= t1Var.f1429n) {
                        t1Var.f1441z.setInputMethodMode(2);
                        t1Var.f();
                        break;
                    }
                }
                break;
        }
    }
}
