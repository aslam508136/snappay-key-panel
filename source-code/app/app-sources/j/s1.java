package j;

import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class s1 implements View.OnTouchListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ t1 f1409a;

    public s1(t1 t1Var) {
        this.f1409a = t1Var;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        d0 d0Var;
        int action = motionEvent.getAction();
        int x2 = (int) motionEvent.getX();
        int y2 = (int) motionEvent.getY();
        t1 t1Var = this.f1409a;
        if (action == 0 && (d0Var = t1Var.f1441z) != null && d0Var.isShowing() && x2 >= 0) {
            d0 d0Var2 = t1Var.f1441z;
            if (x2 < d0Var2.getWidth() && y2 >= 0 && y2 < d0Var2.getHeight()) {
                t1Var.f1437v.postDelayed(t1Var.f1433r, 250L);
                return false;
            }
        }
        if (action != 1) {
            return false;
        }
        t1Var.f1437v.removeCallbacks(t1Var.f1433r);
        return false;
    }
}
