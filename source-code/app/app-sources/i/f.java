package i;

import android.view.View;
import android.view.ViewTreeObserver;

/* JADX INFO: loaded from: classes.dex */
public final class f implements View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f991a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ x f992b;

    public /* synthetic */ f(x xVar, int i2) {
        this.f991a = i2;
        this.f992b = xVar;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        int i2 = this.f991a;
        x xVar = this.f992b;
        switch (i2) {
            case 0:
                i iVar = (i) xVar;
                ViewTreeObserver viewTreeObserver = iVar.f1044z;
                if (viewTreeObserver != null) {
                    if (!viewTreeObserver.isAlive()) {
                        iVar.f1044z = view.getViewTreeObserver();
                    }
                    iVar.f1044z.removeGlobalOnLayoutListener(iVar.f1029k);
                }
                view.removeOnAttachStateChangeListener(this);
                break;
            default:
                g0 g0Var = (g0) xVar;
                ViewTreeObserver viewTreeObserver2 = g0Var.f1011q;
                if (viewTreeObserver2 != null) {
                    if (!viewTreeObserver2.isAlive()) {
                        g0Var.f1011q = view.getViewTreeObserver();
                    }
                    g0Var.f1011q.removeGlobalOnLayoutListener(g0Var.f1005k);
                }
                view.removeOnAttachStateChangeListener(this);
                break;
        }
    }
}
