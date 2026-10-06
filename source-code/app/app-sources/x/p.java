package x;

import android.os.Build;
import android.view.View;
import android.view.WindowInsets;

/* JADX INFO: loaded from: classes.dex */
public final class p implements View.OnApplyWindowInsetsListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public l0 f2005a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ View f2006b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ k f2007c;

    public p(View view, k kVar) {
        this.f2006b = view;
        this.f2007c = kVar;
    }

    @Override // android.view.View.OnApplyWindowInsetsListener
    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        l0 l0VarC = l0.c(windowInsets, view);
        int i2 = Build.VERSION.SDK_INT;
        k kVar = this.f2007c;
        if (i2 < 30) {
            q.a(windowInsets, this.f2006b);
            if (l0VarC.equals(this.f2005a)) {
                return ((d.r) kVar).b(view, l0VarC).b();
            }
        }
        this.f2005a = l0VarC;
        l0 l0VarB = ((d.r) kVar).b(view, l0VarC);
        if (i2 >= 30) {
            return l0VarB.b();
        }
        view.requestApplyInsets();
        return l0VarB.b();
    }
}
