package d;

import android.view.ViewGroup;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class q implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f714a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ a0 f715b;

    public /* synthetic */ q(a0 a0Var, int i2) {
        this.f714a = i2;
        this.f715b = a0Var;
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0043  */
    @Override // java.lang.Runnable
    public final void run() {
        boolean z2;
        ViewGroup viewGroup;
        int i2 = this.f714a;
        int i3 = 0;
        a0 a0Var = this.f715b;
        switch (i2) {
            case 0:
                if ((1 & a0Var.U) != 0) {
                    a0Var.q(0);
                }
                if ((a0Var.U & 4096) != 0) {
                    a0Var.q(108);
                }
                a0Var.T = false;
                a0Var.U = 0;
                break;
            default:
                a0Var.f594q.showAtLocation(a0Var.f593p, 55, 0, 0);
                x.y yVar = a0Var.f596s;
                if (yVar != null) {
                    yVar.b();
                }
                if (a0Var.f598u && (viewGroup = a0Var.f599v) != null) {
                    WeakHashMap weakHashMap = x.u.f2012a;
                    z2 = viewGroup.isLaidOut();
                }
                if (!z2) {
                    a0Var.f593p.setAlpha(1.0f);
                    a0Var.f593p.setVisibility(0);
                } else {
                    a0Var.f593p.setAlpha(0.0f);
                    x.y yVarA = x.u.a(a0Var.f593p);
                    yVarA.a(1.0f);
                    a0Var.f596s = yVarA;
                    yVarA.d(new s(this, i3));
                }
                break;
        }
    }
}
