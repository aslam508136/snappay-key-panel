package d;

import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import j.d1;
import j.j1;
import java.util.WeakHashMap;
import x.k0;
import x.l0;

/* JADX INFO: loaded from: classes.dex */
public final class r implements x.k, j1, d1, i.a0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f716b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ a0 f717c;

    public /* synthetic */ r(a0 a0Var, int i2) {
        this.f716b = i2;
        this.f717c = a0Var;
    }

    @Override // i.a0
    public final void a(i.o oVar, boolean z2) {
        z zVar;
        int i2 = this.f716b;
        a0 a0Var = this.f717c;
        switch (i2) {
            case 3:
                a0Var.m(oVar);
                break;
            default:
                i.o oVarK = oVar.k();
                int i3 = 0;
                boolean z3 = oVarK != oVar;
                if (z3) {
                    oVar = oVarK;
                }
                z[] zVarArr = a0Var.G;
                int length = zVarArr != null ? zVarArr.length : 0;
                while (true) {
                    if (i3 >= length) {
                        zVar = null;
                    } else {
                        zVar = zVarArr[i3];
                        if (zVar == null || zVar.f739h != oVar) {
                            i3++;
                        }
                    }
                }
                if (zVar != null) {
                    if (!z3) {
                        a0Var.n(zVar, z2);
                    } else {
                        a0Var.l(zVar.f732a, zVar, oVarK);
                        a0Var.n(zVar, true);
                    }
                }
                break;
        }
    }

    public final l0 b(View view, l0 l0Var) {
        x.e0 c0Var;
        k0 k0Var = l0Var.f2000a;
        int i2 = k0Var.g().f1891b;
        int iC = this.f717c.C(l0Var, null);
        if (i2 != iC) {
            int i3 = k0Var.g().f1890a;
            int i4 = k0Var.g().f1892c;
            int i5 = k0Var.g().f1893d;
            int i6 = Build.VERSION.SDK_INT;
            if (i6 >= 30) {
                c0Var = new x.d0(l0Var);
            } else {
                c0Var = i6 >= 29 ? new x.c0(l0Var) : new x.b0(l0Var);
            }
            c0Var.d(r.b.a(i3, iC, i4, i5));
            l0Var = c0Var.b();
        }
        WeakHashMap weakHashMap = x.u.f2012a;
        WindowInsets windowInsetsB = l0Var.b();
        if (windowInsetsB == null) {
            return l0Var;
        }
        WindowInsets windowInsetsOnApplyWindowInsets = view.onApplyWindowInsets(windowInsetsB);
        return !windowInsetsOnApplyWindowInsets.equals(windowInsetsB) ? l0.c(windowInsetsOnApplyWindowInsets, view) : l0Var;
    }

    @Override // i.a0
    public final boolean c(i.o oVar) {
        Window.Callback callbackV;
        int i2 = this.f716b;
        a0 a0Var = this.f717c;
        switch (i2) {
            case 3:
                Window.Callback callbackV2 = a0Var.v();
                if (callbackV2 != null) {
                    callbackV2.onMenuOpened(108, oVar);
                }
                break;
            default:
                if (oVar == oVar.k() && a0Var.A && (callbackV = a0Var.v()) != null && !a0Var.M) {
                    callbackV.onMenuOpened(108, oVar);
                }
                break;
        }
        return true;
    }
}
