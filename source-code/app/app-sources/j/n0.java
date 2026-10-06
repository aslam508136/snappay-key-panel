package j;

import android.view.ViewTreeObserver;
import android.widget.PopupWindow;

/* JADX INFO: loaded from: classes.dex */
public final class n0 implements PopupWindow.OnDismissListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ViewTreeObserver.OnGlobalLayoutListener f1320b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ o0 f1321c;

    public n0(o0 o0Var, i.e eVar) {
        this.f1321c = o0Var;
        this.f1320b = eVar;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        ViewTreeObserver viewTreeObserver = this.f1321c.H.getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.removeGlobalOnLayoutListener(this.f1320b);
        }
    }
}
