package j;

import android.os.Handler;
import android.widget.AbsListView;

/* JADX INFO: loaded from: classes.dex */
public final class r1 implements AbsListView.OnScrollListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ t1 f1392a;

    public r1(t1 t1Var) {
        this.f1392a = t1Var;
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public final void onScroll(AbsListView absListView, int i2, int i3, int i4) {
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public final void onScrollStateChanged(AbsListView absListView, int i2) {
        if (i2 == 1) {
            t1 t1Var = this.f1392a;
            if ((t1Var.f1441z.getInputMethodMode() == 2) || t1Var.f1441z.getContentView() == null) {
                return;
            }
            Handler handler = t1Var.f1437v;
            o1 o1Var = t1Var.f1433r;
            handler.removeCallbacks(o1Var);
            o1Var.run();
        }
    }
}
