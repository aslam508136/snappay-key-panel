package x;

import android.view.View;
import android.view.WindowInsets;

/* JADX INFO: loaded from: classes.dex */
public abstract class r {
    public static l0 a(View view) {
        WindowInsets rootWindowInsets = view.getRootWindowInsets();
        if (rootWindowInsets == null) {
            return null;
        }
        l0 l0VarC = l0.c(rootWindowInsets, null);
        k0 k0Var = l0VarC.f2000a;
        k0Var.l(l0VarC);
        k0Var.d(view.getRootView());
        return l0VarC;
    }
}
