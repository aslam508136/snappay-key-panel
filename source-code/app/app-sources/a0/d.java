package a0;

import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import java.util.WeakHashMap;
import x.u;

/* JADX INFO: loaded from: classes.dex */
public final class d implements View.OnTouchListener {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final int f12r = ViewConfiguration.getTapTimeout();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f13a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AccelerateInterpolator f14b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final View f15c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public androidx.activity.b f16d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float[] f17e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final float[] f18f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f19g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f20h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final float[] f21i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final float[] f22j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final float[] f23k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f24l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f25m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f26n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f27o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f28p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final ListView f29q;

    public d(ListView listView) {
        a aVar = new a();
        this.f13a = aVar;
        this.f14b = new AccelerateInterpolator();
        float[] fArr = {0.0f, 0.0f};
        this.f17e = fArr;
        float[] fArr2 = {Float.MAX_VALUE, Float.MAX_VALUE};
        this.f18f = fArr2;
        float[] fArr3 = {0.0f, 0.0f};
        this.f21i = fArr3;
        float[] fArr4 = {0.0f, 0.0f};
        this.f22j = fArr4;
        float[] fArr5 = {Float.MAX_VALUE, Float.MAX_VALUE};
        this.f23k = fArr5;
        this.f15c = listView;
        float f2 = Resources.getSystem().getDisplayMetrics().density;
        float f3 = ((int) ((1575.0f * f2) + 0.5f)) / 1000.0f;
        fArr5[0] = f3;
        fArr5[1] = f3;
        float f4 = ((int) ((f2 * 315.0f) + 0.5f)) / 1000.0f;
        fArr4[0] = f4;
        fArr4[1] = f4;
        this.f19g = 1;
        fArr2[0] = Float.MAX_VALUE;
        fArr2[1] = Float.MAX_VALUE;
        fArr[0] = 0.2f;
        fArr[1] = 0.2f;
        fArr3[0] = 0.001f;
        fArr3[1] = 0.001f;
        this.f20h = f12r;
        aVar.f2a = 500;
        aVar.f3b = 500;
        this.f29q = listView;
    }

    public static float b(float f2, float f3, float f4) {
        if (f2 > f4) {
            return f4;
        }
        return f2 < f3 ? f3 : f2;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x003d  */
    /* JADX WARN: Code duplicated, block: B:15:0x004d  */
    /* JADX WARN: Code duplicated, block: B:16:0x0054  */
    /* JADX WARN: Code duplicated, block: B:19:? A[RETURN, SYNTHETIC] */
    public final float a(int i2, float f2, float f3, float f4) {
        float fB;
        float interpolation;
        float fB2 = b(this.f17e[i2] * f3, 0.0f, this.f18f[i2]);
        float fC = c(f3 - f2, fB2) - c(f2, fB2);
        AccelerateInterpolator accelerateInterpolator = this.f14b;
        if (fC >= 0.0f) {
            if (fC > 0.0f) {
                interpolation = accelerateInterpolator.getInterpolation(fC);
            } else {
                fB = 0.0f;
            }
            if (fB == 0.0f) {
                return 0.0f;
            }
            float f5 = this.f21i[i2];
            float f6 = this.f22j[i2];
            float f7 = this.f23k[i2];
            float f8 = f5 * f4;
            return fB > 0.0f ? b(fB * f8, f6, f7) : -b((-fB) * f8, f6, f7);
        }
        interpolation = -accelerateInterpolator.getInterpolation(-fC);
        fB = b(interpolation, -1.0f, 1.0f);
        if (fB == 0.0f) {
            return 0.0f;
        }
        float f9 = this.f21i[i2];
        float f10 = this.f22j[i2];
        float f11 = this.f23k[i2];
        float f12 = f9 * f4;
        if (fB > 0.0f) {
        }
    }

    public final float c(float f2, float f3) {
        if (f3 == 0.0f) {
            return 0.0f;
        }
        int i2 = this.f19g;
        if (i2 != 0 && i2 != 1) {
            if (i2 == 2 && f2 < 0.0f) {
                return f2 / (-f3);
            }
            return 0.0f;
        }
        if (f2 >= f3) {
            return 0.0f;
        }
        if (f2 >= 0.0f) {
            return 1.0f - (f2 / f3);
        }
        return (this.f27o && i2 == 1) ? 1.0f : 0.0f;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0016  */
    public final boolean d(View view, MotionEvent motionEvent) {
        int i2;
        if (!this.f28p) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked == 1) {
                e();
            } else if (actionMasked != 2) {
                if (actionMasked == 3) {
                    e();
                }
            }
            return false;
        }
        this.f26n = true;
        this.f24l = false;
        float x2 = motionEvent.getX();
        float width = view.getWidth();
        View view2 = this.f15c;
        float fA = a(0, x2, width, view2.getWidth());
        float fA2 = a(1, motionEvent.getY(), view.getHeight(), view2.getHeight());
        a aVar = this.f13a;
        aVar.f4c = fA;
        aVar.f5d = fA2;
        if (!this.f27o && f()) {
            if (this.f16d == null) {
                this.f16d = new androidx.activity.b(this, 5);
            }
            this.f27o = true;
            this.f25m = true;
            if (this.f24l || (i2 = this.f20h) <= 0) {
                this.f16d.run();
            } else {
                androidx.activity.b bVar = this.f16d;
                long j2 = i2;
                WeakHashMap weakHashMap = u.f2012a;
                view2.postOnAnimationDelayed(bVar, j2);
            }
            this.f24l = true;
        }
        return false;
    }

    public final void e() {
        int i2 = 0;
        if (this.f25m) {
            this.f27o = false;
            return;
        }
        a aVar = this.f13a;
        aVar.getClass();
        long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        int i3 = (int) (jCurrentAnimationTimeMillis - aVar.f6e);
        int i4 = aVar.f3b;
        if (i3 > i4) {
            i2 = i4;
        } else if (i3 >= 0) {
            i2 = i3;
        }
        aVar.f10i = i2;
        aVar.f9h = aVar.a(jCurrentAnimationTimeMillis);
        aVar.f8g = jCurrentAnimationTimeMillis;
    }

    /* JADX WARN: Code duplicated, block: B:19:0x004b  */
    public final boolean f() {
        boolean z2;
        a aVar = this.f13a;
        float f2 = aVar.f5d;
        int iAbs = (int) (f2 / Math.abs(f2));
        Math.abs(aVar.f4c);
        if (iAbs == 0) {
            return false;
        }
        ListView listView = this.f29q;
        int count = listView.getCount();
        if (count == 0) {
            z2 = false;
        } else {
            int childCount = listView.getChildCount();
            int firstVisiblePosition = listView.getFirstVisiblePosition();
            int i2 = firstVisiblePosition + childCount;
            if (iAbs <= 0 ? iAbs >= 0 || (firstVisiblePosition <= 0 && listView.getChildAt(0).getTop() >= 0) : i2 >= count && listView.getChildAt(childCount - 1).getBottom() <= listView.getHeight()) {
                z2 = false;
            } else {
                z2 = true;
            }
        }
        return z2;
    }

    @Override // android.view.View.OnTouchListener
    public final /* bridge */ /* synthetic */ boolean onTouch(View view, MotionEvent motionEvent) {
        d(view, motionEvent);
        return false;
    }
}
