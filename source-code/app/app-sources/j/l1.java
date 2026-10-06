package j;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/* JADX INFO: loaded from: classes.dex */
public abstract class l1 implements View.OnTouchListener, View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f1285a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1286b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1287c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final View f1288d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public k1 f1289e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public k1 f1290f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1291g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1292h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int[] f1293i = new int[2];

    public l1(View view) {
        this.f1288d = view;
        view.setLongClickable(true);
        view.addOnAttachStateChangeListener(this);
        this.f1285a = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        int tapTimeout = ViewConfiguration.getTapTimeout();
        this.f1286b = tapTimeout;
        this.f1287c = (ViewConfiguration.getLongPressTimeout() + tapTimeout) / 2;
    }

    public final void a() {
        k1 k1Var = this.f1290f;
        View view = this.f1288d;
        if (k1Var != null) {
            view.removeCallbacks(k1Var);
        }
        k1 k1Var2 = this.f1289e;
        if (k1Var2 != null) {
            view.removeCallbacks(k1Var2);
        }
    }

    public abstract i.f0 b();

    public abstract boolean c();

    public boolean d() {
        i.f0 f0VarB = b();
        if (f0VarB == null || !f0VarB.b()) {
            return true;
        }
        f0VarB.dismiss();
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:22:0x005d  */
    /* JADX WARN: Code duplicated, block: B:53:0x00ce  */
    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z2;
        boolean z3;
        boolean z4;
        i1 i1VarJ;
        boolean z5 = this.f1291g;
        View view2 = this.f1288d;
        if (z5) {
            i.f0 f0VarB = b();
            if (f0VarB == null || !f0VarB.b() || (i1VarJ = f0VarB.j()) == null || !i1VarJ.isShown()) {
                z4 = false;
            } else {
                MotionEvent motionEventObtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
                int[] iArr = this.f1293i;
                view2.getLocationOnScreen(iArr);
                motionEventObtainNoHistory.offsetLocation(iArr[0], iArr[1]);
                i1VarJ.getLocationOnScreen(iArr);
                motionEventObtainNoHistory.offsetLocation(-iArr[0], -iArr[1]);
                boolean zB = i1VarJ.b(motionEventObtainNoHistory, this.f1292h);
                motionEventObtainNoHistory.recycle();
                int actionMasked = motionEvent.getActionMasked();
                boolean z6 = (actionMasked == 1 || actionMasked == 3) ? false : true;
                if (zB && z6) {
                    z4 = true;
                } else {
                    z4 = false;
                }
            }
            z3 = z4 || !d();
        } else {
            if (view2.isEnabled()) {
                int actionMasked2 = motionEvent.getActionMasked();
                if (actionMasked2 == 0) {
                    this.f1292h = motionEvent.getPointerId(0);
                    if (this.f1289e == null) {
                        this.f1289e = new k1(this, 0);
                    }
                    view2.postDelayed(this.f1289e, this.f1286b);
                    if (this.f1290f == null) {
                        this.f1290f = new k1(this, 1);
                    }
                    view2.postDelayed(this.f1290f, this.f1287c);
                } else if (actionMasked2 == 1) {
                    a();
                } else if (actionMasked2 == 2) {
                    int iFindPointerIndex = motionEvent.findPointerIndex(this.f1292h);
                    if (iFindPointerIndex >= 0) {
                        float x2 = motionEvent.getX(iFindPointerIndex);
                        float y2 = motionEvent.getY(iFindPointerIndex);
                        float f2 = this.f1285a;
                        float f3 = -f2;
                        if (!(x2 >= f3 && y2 >= f3 && x2 < ((float) (view2.getRight() - view2.getLeft())) + f2 && y2 < ((float) (view2.getBottom() - view2.getTop())) + f2)) {
                            a();
                            view2.getParent().requestDisallowInterceptTouchEvent(true);
                            z2 = true;
                        }
                    }
                } else if (actionMasked2 == 3) {
                    a();
                }
                z2 = false;
            } else {
                z2 = false;
            }
            z3 = z2 && c();
            if (z3) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                view2.onTouchEvent(motionEventObtain);
                motionEventObtain.recycle();
            }
        }
        this.f1291g = z3;
        return z3 || z5;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        this.f1291g = false;
        this.f1292h = -1;
        k1 k1Var = this.f1289e;
        if (k1Var != null) {
            this.f1288d.removeCallbacks(k1Var);
        }
    }
}
