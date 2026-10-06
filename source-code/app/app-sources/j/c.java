package j;

import android.view.View;
import android.view.Window;
import androidx.appcompat.widget.ActionBarContextView;

/* JADX INFO: loaded from: classes.dex */
public final class c implements View.OnClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f1198b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f1199c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f1200d;

    public c(ActionBarContextView actionBarContextView, h.c cVar) {
        this.f1200d = actionBarContextView;
        this.f1199c = cVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i2 = this.f1198b;
        Object obj = this.f1199c;
        switch (i2) {
            case 0:
                ((h.c) obj).a();
                break;
            default:
                a3 a3Var = (a3) this.f1200d;
                Window.Callback callback = a3Var.f1167k;
                if (callback != null && a3Var.f1168l) {
                    callback.onMenuItemSelected(0, (i.a) obj);
                    break;
                }
                break;
        }
    }

    public c(a3 a3Var) {
        this.f1200d = a3Var;
        this.f1199c = new i.a(a3Var.f1157a.getContext(), a3Var.f1164h);
    }
}
