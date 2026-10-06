package i;

import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

/* JADX INFO: loaded from: classes.dex */
public final class p implements DialogInterface.OnKeyListener, DialogInterface.OnClickListener, DialogInterface.OnDismissListener, a0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final o f1084b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public d.k f1085c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public k f1086d;

    public p(o oVar) {
        this.f1084b = oVar;
    }

    @Override // i.a0
    public final void a(o oVar, boolean z2) {
        d.k kVar;
        if ((z2 || oVar == this.f1084b) && (kVar = this.f1085c) != null) {
            kVar.dismiss();
        }
    }

    @Override // i.a0
    public final boolean c(o oVar) {
        return false;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i2) {
        k kVar = this.f1086d;
        if (kVar.f1052g == null) {
            kVar.f1052g = new j(kVar);
        }
        this.f1084b.q(kVar.f1052g.getItem(i2), null, 0);
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        this.f1086d.a(this.f1084b, true);
    }

    @Override // android.content.DialogInterface.OnKeyListener
    public final boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
        Window window;
        View decorView;
        KeyEvent.DispatcherState keyDispatcherState;
        View decorView2;
        KeyEvent.DispatcherState keyDispatcherState2;
        o oVar = this.f1084b;
        if (i2 == 82 || i2 == 4) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                Window window2 = this.f1085c.getWindow();
                if (window2 != null && (decorView2 = window2.getDecorView()) != null && (keyDispatcherState2 = decorView2.getKeyDispatcherState()) != null) {
                    keyDispatcherState2.startTracking(keyEvent, this);
                    return true;
                }
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled() && (window = this.f1085c.getWindow()) != null && (decorView = window.getDecorView()) != null && (keyDispatcherState = decorView.getKeyDispatcherState()) != null && keyDispatcherState.isTracking(keyEvent)) {
                oVar.c(true);
                dialogInterface.dismiss();
                return true;
            }
        }
        return oVar.performShortcut(i2, keyEvent, 0);
    }
}
