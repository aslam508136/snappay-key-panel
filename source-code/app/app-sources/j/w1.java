package j;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.MenuItem;
import android.widget.PopupWindow;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class w1 extends t1 implements u1 {
    public static final Method E;
    public u1 D;

    static {
        try {
            if (Build.VERSION.SDK_INT <= 28) {
                E = PopupWindow.class.getDeclaredMethod("setTouchModal", Boolean.TYPE);
            }
        } catch (NoSuchMethodException unused) {
            Log.i("MenuPopupWindow", "Could not find method setTouchModal() on PopupWindow. Oh well.");
        }
    }

    public w1(Context context, int i2, int i3) {
        super(context, null, i2, i3);
    }

    @Override // j.u1
    public final void e(i.o oVar, i.q qVar) {
        u1 u1Var = this.D;
        if (u1Var != null) {
            u1Var.e(oVar, qVar);
        }
    }

    @Override // j.u1
    public final void k(i.o oVar, MenuItem menuItem) {
        u1 u1Var = this.D;
        if (u1Var != null) {
            u1Var.k(oVar, menuItem);
        }
    }

    @Override // j.t1
    public final i1 q(Context context, boolean z2) {
        v1 v1Var = new v1(context, z2);
        v1Var.setHoverListener(this);
        return v1Var;
    }
}
