package j;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;

/* JADX INFO: loaded from: classes.dex */
public final class k1 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1278a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ l1 f1279b;

    public /* synthetic */ k1(l1 l1Var, int i2) {
        this.f1278a = i2;
        this.f1279b = l1Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i2 = this.f1278a;
        l1 l1Var = this.f1279b;
        switch (i2) {
            case 0:
                ViewParent parent = l1Var.f1288d.getParent();
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
                break;
            default:
                l1Var.a();
                View view = l1Var.f1288d;
                if (view.isEnabled() && !view.isLongClickable() && l1Var.c()) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    long jUptimeMillis = SystemClock.uptimeMillis();
                    MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                    view.onTouchEvent(motionEventObtain);
                    motionEventObtain.recycle();
                    l1Var.f1291g = true;
                    break;
                }
                break;
        }
    }
}
