package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import d.a0;
import d.r;
import i.o;
import j.a3;
import j.d1;
import j.e1;
import j.h;
import j.m;
import x.y;

/* JADX INFO: loaded from: classes.dex */
public class ContentFrameLayout extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TypedValue f179b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TypedValue f180c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TypedValue f181d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TypedValue f182e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public TypedValue f183f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public TypedValue f184g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Rect f185h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public d1 f186i;

    public ContentFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.f185h = new Rect();
    }

    public TypedValue getFixedHeightMajor() {
        if (this.f183f == null) {
            this.f183f = new TypedValue();
        }
        return this.f183f;
    }

    public TypedValue getFixedHeightMinor() {
        if (this.f184g == null) {
            this.f184g = new TypedValue();
        }
        return this.f184g;
    }

    public TypedValue getFixedWidthMajor() {
        if (this.f181d == null) {
            this.f181d = new TypedValue();
        }
        return this.f181d;
    }

    public TypedValue getFixedWidthMinor() {
        if (this.f182e == null) {
            this.f182e = new TypedValue();
        }
        return this.f182e;
    }

    public TypedValue getMinWidthMajor() {
        if (this.f179b == null) {
            this.f179b = new TypedValue();
        }
        return this.f179b;
    }

    public TypedValue getMinWidthMinor() {
        if (this.f180c == null) {
            this.f180c = new TypedValue();
        }
        return this.f180c;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        d1 d1Var = this.f186i;
        if (d1Var != null) {
            d1Var.getClass();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        m mVar;
        super.onDetachedFromWindow();
        d1 d1Var = this.f186i;
        if (d1Var != null) {
            a0 a0Var = ((r) d1Var).f717c;
            e1 e1Var = a0Var.f589l;
            if (e1Var != null) {
                ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) e1Var;
                actionBarOverlayLayout.l();
                ActionMenuView actionMenuView = ((a3) actionBarOverlayLayout.f145f).f1157a.f210b;
                if (actionMenuView != null && (mVar = actionMenuView.f170u) != null) {
                    mVar.f();
                    h hVar = mVar.f1313u;
                    if (hVar != null && hVar.b()) {
                        hVar.f1134j.dismiss();
                    }
                }
            }
            if (a0Var.f594q != null) {
                a0Var.f583f.getDecorView().removeCallbacks(a0Var.f595r);
                if (a0Var.f594q.isShowing()) {
                    try {
                        a0Var.f594q.dismiss();
                    } catch (IllegalArgumentException unused) {
                    }
                }
                a0Var.f594q = null;
            }
            y yVar = a0Var.f596s;
            if (yVar != null) {
                yVar.b();
            }
            o oVar = a0Var.u(0).f739h;
            if (oVar != null) {
                oVar.c(true);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:21:0x004e  */
    /* JADX WARN: Code duplicated, block: B:22:0x0062  */
    /* JADX WARN: Code duplicated, block: B:37:0x008a  */
    /* JADX WARN: Code duplicated, block: B:38:0x009d  */
    /* JADX WARN: Code duplicated, block: B:55:0x00d1  */
    /* JADX WARN: Code duplicated, block: B:57:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:58:0x00de  */
    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i2, int i3) {
        int iMakeMeasureSpec;
        boolean z2;
        int iMakeMeasureSpec2;
        int i4;
        int i5;
        float fraction;
        int i6;
        int i7;
        float fraction2;
        int i8;
        int i9;
        float fraction3;
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        boolean z3 = true;
        boolean z4 = displayMetrics.widthPixels < displayMetrics.heightPixels;
        int mode = View.MeasureSpec.getMode(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        Rect rect = this.f185h;
        if (mode != Integer.MIN_VALUE) {
            iMakeMeasureSpec = i2;
            z2 = false;
        } else {
            TypedValue typedValue = z4 ? this.f182e : this.f181d;
            if (typedValue == null || (i8 = typedValue.type) == 0) {
                iMakeMeasureSpec = i2;
                z2 = false;
            } else {
                if (i8 == 5) {
                    fraction3 = typedValue.getDimension(displayMetrics);
                } else {
                    if (i8 == 6) {
                        int i10 = displayMetrics.widthPixels;
                        fraction3 = typedValue.getFraction(i10, i10);
                    } else {
                        i9 = 0;
                    }
                    if (i9 > 0) {
                        iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.min(i9 - (rect.left + rect.right), View.MeasureSpec.getSize(i2)), 1073741824);
                        z2 = true;
                    } else {
                        iMakeMeasureSpec = i2;
                        z2 = false;
                    }
                }
                i9 = (int) fraction3;
                if (i9 > 0) {
                    iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.min(i9 - (rect.left + rect.right), View.MeasureSpec.getSize(i2)), 1073741824);
                    z2 = true;
                } else {
                    iMakeMeasureSpec = i2;
                    z2 = false;
                }
            }
        }
        if (mode2 != Integer.MIN_VALUE) {
            iMakeMeasureSpec2 = i3;
        } else {
            TypedValue typedValue2 = z4 ? this.f183f : this.f184g;
            if (typedValue2 == null || (i6 = typedValue2.type) == 0) {
                iMakeMeasureSpec2 = i3;
            } else {
                if (i6 == 5) {
                    fraction2 = typedValue2.getDimension(displayMetrics);
                } else {
                    if (i6 == 6) {
                        int i11 = displayMetrics.heightPixels;
                        fraction2 = typedValue2.getFraction(i11, i11);
                    } else {
                        i7 = 0;
                    }
                    if (i7 > 0) {
                        iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(Math.min(i7 - (rect.top + rect.bottom), View.MeasureSpec.getSize(i3)), 1073741824);
                    } else {
                        iMakeMeasureSpec2 = i3;
                    }
                }
                i7 = (int) fraction2;
                if (i7 > 0) {
                    iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(Math.min(i7 - (rect.top + rect.bottom), View.MeasureSpec.getSize(i3)), 1073741824);
                } else {
                    iMakeMeasureSpec2 = i3;
                }
            }
        }
        super.onMeasure(iMakeMeasureSpec, iMakeMeasureSpec2);
        int measuredWidth = getMeasuredWidth();
        int iMakeMeasureSpec3 = View.MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824);
        if (z2 || mode != Integer.MIN_VALUE) {
            z3 = false;
        } else {
            TypedValue typedValue3 = z4 ? this.f180c : this.f179b;
            if (typedValue3 == null || (i4 = typedValue3.type) == 0) {
                z3 = false;
            } else {
                if (i4 == 5) {
                    fraction = typedValue3.getDimension(displayMetrics);
                } else {
                    if (i4 == 6) {
                        int i12 = displayMetrics.widthPixels;
                        fraction = typedValue3.getFraction(i12, i12);
                    } else {
                        i5 = 0;
                    }
                    if (i5 > 0) {
                        i5 -= rect.left + rect.right;
                    }
                    if (measuredWidth < i5) {
                        iMakeMeasureSpec3 = View.MeasureSpec.makeMeasureSpec(i5, 1073741824);
                    } else {
                        z3 = false;
                    }
                }
                i5 = (int) fraction;
                if (i5 > 0) {
                    i5 -= rect.left + rect.right;
                }
                if (measuredWidth < i5) {
                    iMakeMeasureSpec3 = View.MeasureSpec.makeMeasureSpec(i5, 1073741824);
                } else {
                    z3 = false;
                }
            }
        }
        if (z3) {
            super.onMeasure(iMakeMeasureSpec3, iMakeMeasureSpec2);
        }
    }

    public void setAttachListener(d1 d1Var) {
        this.f186i = d1Var;
    }
}
