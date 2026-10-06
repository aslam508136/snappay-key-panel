package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import androidx.appcompat.view.menu.ActionMenuItemView;
import com.google.crypto.tink.shaded.protobuf.Reader;
import i.a0;
import i.d0;
import i.n;
import i.o;
import j.g3;
import j.h;
import j.l;
import j.m;
import j.m1;
import j.n1;
import j.p;
import j.q;

/* JADX INFO: loaded from: classes.dex */
public class ActionMenuView extends n1 implements n, d0 {
    public final int A;
    public q B;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public o f166q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Context f167r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f168s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f169t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public m f170u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public a0 f171v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public i.m f172w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f173x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f174y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final int f175z;

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        setBaselineAligned(false);
        float f2 = context.getResources().getDisplayMetrics().density;
        this.f175z = (int) (56.0f * f2);
        this.A = (int) (f2 * 4.0f);
        this.f167r = context;
        this.f168s = 0;
    }

    public static p k(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams == null) {
            p pVar = new p();
            ((LinearLayout.LayoutParams) pVar).gravity = 16;
            return pVar;
        }
        p pVar2 = layoutParams instanceof p ? new p((p) layoutParams) : new p(layoutParams);
        if (((LinearLayout.LayoutParams) pVar2).gravity <= 0) {
            ((LinearLayout.LayoutParams) pVar2).gravity = 16;
        }
        return pVar2;
    }

    @Override // i.n
    public final boolean a(i.q qVar) {
        return this.f166q.q(qVar, null, 0);
    }

    @Override // i.d0
    public final void c(o oVar) {
        this.f166q = oVar;
    }

    @Override // j.n1, android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof p;
    }

    @Override // android.view.View
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    @Override // j.n1
    /* JADX INFO: renamed from: g */
    public final m1 generateDefaultLayoutParams() {
        p pVar = new p();
        ((LinearLayout.LayoutParams) pVar).gravity = 16;
        return pVar;
    }

    @Override // j.n1, android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        p pVar = new p();
        ((LinearLayout.LayoutParams) pVar).gravity = 16;
        return pVar;
    }

    @Override // j.n1, android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new p(getContext(), attributeSet);
    }

    public Menu getMenu() {
        if (this.f166q == null) {
            Context context = getContext();
            o oVar = new o(context);
            this.f166q = oVar;
            oVar.f1064e = new h.a(this, 4);
            m mVar = new m(context);
            this.f170u = mVar;
            mVar.f1305m = true;
            mVar.f1306n = true;
            a0 oVar2 = this.f171v;
            if (oVar2 == null) {
                oVar2 = new j.o(0);
            }
            mVar.f1298f = oVar2;
            this.f166q.b(mVar, this.f167r);
            m mVar2 = this.f170u;
            mVar2.f1301i = this;
            this.f166q = mVar2.f1296d;
        }
        return this.f166q;
    }

    public Drawable getOverflowIcon() {
        getMenu();
        m mVar = this.f170u;
        l lVar = mVar.f1302j;
        if (lVar != null) {
            return lVar.getDrawable();
        }
        if (mVar.f1304l) {
            return mVar.f1303k;
        }
        return null;
    }

    public int getPopupTheme() {
        return this.f168s;
    }

    public int getWindowAnimations() {
        return 0;
    }

    @Override // j.n1
    /* JADX INFO: renamed from: h */
    public final m1 generateLayoutParams(AttributeSet attributeSet) {
        return new p(getContext(), attributeSet);
    }

    @Override // j.n1
    /* JADX INFO: renamed from: i */
    public final /* bridge */ /* synthetic */ m1 generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return k(layoutParams);
    }

    public final boolean l(int i2) {
        boolean zA = false;
        if (i2 == 0) {
            return false;
        }
        KeyEvent.Callback childAt = getChildAt(i2 - 1);
        KeyEvent.Callback childAt2 = getChildAt(i2);
        if (i2 < getChildCount() && (childAt instanceof j.n)) {
            zA = false | ((j.n) childAt).a();
        }
        return (i2 <= 0 || !(childAt2 instanceof j.n)) ? zA : zA | ((j.n) childAt2).b();
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        m mVar = this.f170u;
        if (mVar != null) {
            mVar.i();
            if (this.f170u.j()) {
                this.f170u.f();
                this.f170u.l();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m mVar = this.f170u;
        if (mVar != null) {
            mVar.f();
            h hVar = mVar.f1313u;
            if (hVar == null || !hVar.b()) {
                return;
            }
            hVar.f1134j.dismiss();
        }
    }

    @Override // j.n1, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int width;
        int paddingLeft;
        if (!this.f173x) {
            super.onLayout(z2, i2, i3, i4, i5);
            return;
        }
        int childCount = getChildCount();
        int i6 = (i5 - i3) / 2;
        int dividerWidth = getDividerWidth();
        int i7 = i4 - i2;
        int paddingRight = (i7 - getPaddingRight()) - getPaddingLeft();
        boolean zA = g3.a(this);
        int i8 = 0;
        int i9 = 0;
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt = getChildAt(i10);
            if (childAt.getVisibility() != 8) {
                p pVar = (p) childAt.getLayoutParams();
                if (pVar.f1347a) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    if (l(i10)) {
                        measuredWidth += dividerWidth;
                    }
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (zA) {
                        paddingLeft = getPaddingLeft() + ((LinearLayout.LayoutParams) pVar).leftMargin;
                        width = paddingLeft + measuredWidth;
                    } else {
                        width = (getWidth() - getPaddingRight()) - ((LinearLayout.LayoutParams) pVar).rightMargin;
                        paddingLeft = width - measuredWidth;
                    }
                    int i11 = i6 - (measuredHeight / 2);
                    childAt.layout(paddingLeft, i11, width, measuredHeight + i11);
                    paddingRight -= measuredWidth;
                    i8 = 1;
                } else {
                    paddingRight -= (childAt.getMeasuredWidth() + ((LinearLayout.LayoutParams) pVar).leftMargin) + ((LinearLayout.LayoutParams) pVar).rightMargin;
                    l(i10);
                    i9++;
                }
            }
        }
        if (childCount == 1 && i8 == 0) {
            View childAt2 = getChildAt(0);
            int measuredWidth2 = childAt2.getMeasuredWidth();
            int measuredHeight2 = childAt2.getMeasuredHeight();
            int i12 = (i7 / 2) - (measuredWidth2 / 2);
            int i13 = i6 - (measuredHeight2 / 2);
            childAt2.layout(i12, i13, measuredWidth2 + i12, measuredHeight2 + i13);
            return;
        }
        int i14 = i9 - (i8 ^ 1);
        int iMax = Math.max(0, i14 > 0 ? paddingRight / i14 : 0);
        if (zA) {
            int width2 = getWidth() - getPaddingRight();
            for (int i15 = 0; i15 < childCount; i15++) {
                View childAt3 = getChildAt(i15);
                p pVar2 = (p) childAt3.getLayoutParams();
                if (childAt3.getVisibility() != 8 && !pVar2.f1347a) {
                    int i16 = width2 - ((LinearLayout.LayoutParams) pVar2).rightMargin;
                    int measuredWidth3 = childAt3.getMeasuredWidth();
                    int measuredHeight3 = childAt3.getMeasuredHeight();
                    int i17 = i6 - (measuredHeight3 / 2);
                    childAt3.layout(i16 - measuredWidth3, i17, i16, measuredHeight3 + i17);
                    width2 = i16 - ((measuredWidth3 + ((LinearLayout.LayoutParams) pVar2).leftMargin) + iMax);
                }
            }
            return;
        }
        int paddingLeft2 = getPaddingLeft();
        for (int i18 = 0; i18 < childCount; i18++) {
            View childAt4 = getChildAt(i18);
            p pVar3 = (p) childAt4.getLayoutParams();
            if (childAt4.getVisibility() != 8 && !pVar3.f1347a) {
                int i19 = paddingLeft2 + ((LinearLayout.LayoutParams) pVar3).leftMargin;
                int measuredWidth4 = childAt4.getMeasuredWidth();
                int measuredHeight4 = childAt4.getMeasuredHeight();
                int i20 = i6 - (measuredHeight4 / 2);
                childAt4.layout(i19, i20, i19 + measuredWidth4, measuredHeight4 + i20);
                paddingLeft2 = measuredWidth4 + ((LinearLayout.LayoutParams) pVar3).rightMargin + iMax + i19;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r4v23 */
    /* JADX WARN: Type inference failed for: r4v24, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r4v26 */
    /* JADX WARN: Type inference failed for: r4v31 */
    @Override // j.n1, android.view.View
    public final void onMeasure(int i2, int i3) {
        int i4;
        boolean z2;
        int i5;
        boolean z3;
        int i6;
        ?? r4;
        int i7;
        o oVar;
        boolean z4 = this.f173x;
        boolean z5 = View.MeasureSpec.getMode(i2) == 1073741824;
        this.f173x = z5;
        if (z4 != z5) {
            this.f174y = 0;
        }
        int size = View.MeasureSpec.getSize(i2);
        if (this.f173x && (oVar = this.f166q) != null && size != this.f174y) {
            this.f174y = size;
            oVar.p(true);
        }
        int childCount = getChildCount();
        if (!this.f173x || childCount <= 0) {
            for (int i8 = 0; i8 < childCount; i8++) {
                p pVar = (p) getChildAt(i8).getLayoutParams();
                ((LinearLayout.LayoutParams) pVar).rightMargin = 0;
                ((LinearLayout.LayoutParams) pVar).leftMargin = 0;
            }
            super.onMeasure(i2, i3);
            return;
        }
        int mode = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i2);
        int size3 = View.MeasureSpec.getSize(i3);
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i3, paddingBottom, -2);
        int i9 = size2 - paddingRight;
        int i10 = this.f175z;
        int i11 = i9 / i10;
        int i12 = i9 % i10;
        if (i11 == 0) {
            setMeasuredDimension(i9, 0);
            return;
        }
        int i13 = (i12 / i11) + i10;
        int childCount2 = getChildCount();
        int iMax = 0;
        int i14 = 0;
        int iMax2 = 0;
        int i15 = 0;
        boolean z6 = false;
        long j2 = 0;
        int i16 = 0;
        while (true) {
            i4 = this.A;
            if (i15 >= childCount2) {
                break;
            }
            View childAt = getChildAt(i15);
            int i17 = size3;
            int i18 = i9;
            if (childAt.getVisibility() != 8) {
                boolean z7 = childAt instanceof ActionMenuItemView;
                int i19 = i14 + 1;
                if (z7) {
                    childAt.setPadding(i4, 0, i4, 0);
                }
                p pVar2 = (p) childAt.getLayoutParams();
                pVar2.f1352f = false;
                pVar2.f1349c = 0;
                pVar2.f1348b = 0;
                pVar2.f1350d = false;
                ((LinearLayout.LayoutParams) pVar2).leftMargin = 0;
                ((LinearLayout.LayoutParams) pVar2).rightMargin = 0;
                pVar2.f1351e = z7 && ((ActionMenuItemView) childAt).d();
                int i20 = pVar2.f1347a ? 1 : i11;
                p pVar3 = (p) childAt.getLayoutParams();
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(childMeasureSpec) - paddingBottom, View.MeasureSpec.getMode(childMeasureSpec));
                ActionMenuItemView actionMenuItemView = z7 ? (ActionMenuItemView) childAt : null;
                boolean z8 = actionMenuItemView != null && actionMenuItemView.d();
                if (i20 <= 0 || (z8 && i20 < 2)) {
                    i7 = 0;
                } else {
                    childAt.measure(View.MeasureSpec.makeMeasureSpec(i20 * i13, Integer.MIN_VALUE), iMakeMeasureSpec);
                    int measuredWidth = childAt.getMeasuredWidth();
                    i7 = measuredWidth / i13;
                    if (measuredWidth % i13 != 0) {
                        i7++;
                    }
                    if (z8 && i7 < 2) {
                        i7 = 2;
                    }
                }
                pVar3.f1350d = !pVar3.f1347a && z8;
                pVar3.f1348b = i7;
                childAt.measure(View.MeasureSpec.makeMeasureSpec(i13 * i7, 1073741824), iMakeMeasureSpec);
                iMax2 = Math.max(iMax2, i7);
                if (pVar2.f1350d) {
                    i16++;
                }
                if (pVar2.f1347a) {
                    z6 = true;
                }
                i11 -= i7;
                iMax = Math.max(iMax, childAt.getMeasuredHeight());
                if (i7 == 1) {
                    j2 |= (long) (1 << i15);
                }
                i14 = i19;
            }
            i15++;
            size3 = i17;
            i9 = i18;
            paddingBottom = paddingBottom;
            mode = mode;
        }
        int i21 = mode;
        int i22 = i9;
        int i23 = size3;
        boolean z9 = z6 && i14 == 2;
        boolean z10 = false;
        while (true) {
            if (i16 <= 0 || i11 <= 0) {
                z2 = z10;
                break;
            }
            int i24 = Reader.READ_DONE;
            int i25 = 0;
            int i26 = 0;
            long j3 = 0;
            while (i26 < childCount2) {
                p pVar4 = (p) getChildAt(i26).getLayoutParams();
                boolean z11 = z10;
                if (pVar4.f1350d) {
                    int i27 = pVar4.f1348b;
                    if (i27 < i24) {
                        j3 = 1 << i26;
                        i24 = i27;
                        i25 = 1;
                    } else if (i27 == i24) {
                        j3 |= 1 << i26;
                        i25++;
                    }
                }
                i26++;
                z10 = z11;
            }
            z2 = z10;
            j2 |= j3;
            if (i25 > i11) {
                break;
            }
            int i28 = i24 + 1;
            int i29 = 0;
            while (i29 < childCount2) {
                View childAt2 = getChildAt(i29);
                p pVar5 = (p) childAt2.getLayoutParams();
                int i30 = iMax;
                int i31 = childMeasureSpec;
                int i32 = childCount2;
                long j4 = 1 << i29;
                if ((j3 & j4) != 0) {
                    if (z9 && pVar5.f1351e) {
                        r4 = 1;
                        r4 = 1;
                        if (i11 == 1) {
                            childAt2.setPadding(i4 + i13, 0, i4, 0);
                        }
                    } else {
                        r4 = 1;
                    }
                    pVar5.f1348b += r4;
                    pVar5.f1352f = r4;
                    i11--;
                } else if (pVar5.f1348b == i28) {
                    j2 |= j4;
                }
                i29++;
                childMeasureSpec = i31;
                iMax = i30;
                childCount2 = i32;
            }
            z10 = true;
        }
        int i33 = iMax;
        int i34 = childMeasureSpec;
        int i35 = childCount2;
        boolean z12 = !z6 && i14 == 1;
        if (i11 <= 0 || j2 == 0 || (i11 >= i14 - 1 && !z12 && iMax2 <= 1)) {
            i5 = i35;
            z3 = z2;
        } else {
            float fBitCount = Long.bitCount(j2);
            if (!z12) {
                if ((j2 & 1) != 0 && !((p) getChildAt(0).getLayoutParams()).f1351e) {
                    fBitCount -= 0.5f;
                }
                int i36 = i35 - 1;
                if ((j2 & ((long) (1 << i36))) != 0 && !((p) getChildAt(i36).getLayoutParams()).f1351e) {
                    fBitCount -= 0.5f;
                }
            }
            int i37 = fBitCount > 0.0f ? (int) ((i11 * i13) / fBitCount) : 0;
            boolean z13 = z2;
            i5 = i35;
            for (int i38 = 0; i38 < i5; i38++) {
                if ((j2 & ((long) (1 << i38))) != 0) {
                    View childAt3 = getChildAt(i38);
                    p pVar6 = (p) childAt3.getLayoutParams();
                    if (childAt3 instanceof ActionMenuItemView) {
                        pVar6.f1349c = i37;
                        pVar6.f1352f = true;
                        if (i38 == 0 && !pVar6.f1351e) {
                            ((LinearLayout.LayoutParams) pVar6).leftMargin = (-i37) / 2;
                        }
                    } else if (pVar6.f1347a) {
                        pVar6.f1349c = i37;
                        pVar6.f1352f = true;
                        ((LinearLayout.LayoutParams) pVar6).rightMargin = (-i37) / 2;
                    } else {
                        if (i38 != 0) {
                            ((LinearLayout.LayoutParams) pVar6).leftMargin = i37 / 2;
                        }
                        if (i38 != i5 - 1) {
                            ((LinearLayout.LayoutParams) pVar6).rightMargin = i37 / 2;
                        }
                    }
                    z13 = true;
                }
            }
            z3 = z13;
        }
        if (z3) {
            int i39 = 0;
            while (i39 < i5) {
                View childAt4 = getChildAt(i39);
                p pVar7 = (p) childAt4.getLayoutParams();
                if (pVar7.f1352f) {
                    i6 = i34;
                    childAt4.measure(View.MeasureSpec.makeMeasureSpec((pVar7.f1348b * i13) + pVar7.f1349c, 1073741824), i6);
                } else {
                    i6 = i34;
                }
                i39++;
                i34 = i6;
            }
        }
        setMeasuredDimension(i22, i21 != 1073741824 ? i33 : i23);
    }

    public void setExpandedActionViewsExclusive(boolean z2) {
        this.f170u.f1310r = z2;
    }

    public void setOnMenuItemClickListener(q qVar) {
        this.B = qVar;
    }

    public void setOverflowIcon(Drawable drawable) {
        getMenu();
        m mVar = this.f170u;
        l lVar = mVar.f1302j;
        if (lVar != null) {
            lVar.setImageDrawable(drawable);
        } else {
            mVar.f1304l = true;
            mVar.f1303k = drawable;
        }
    }

    public void setOverflowReserved(boolean z2) {
        this.f169t = z2;
    }

    public void setPopupTheme(int i2) {
        if (this.f168s != i2) {
            this.f168s = i2;
            if (i2 == 0) {
                this.f167r = getContext();
            } else {
                this.f167r = new ContextThemeWrapper(getContext(), i2);
            }
        }
    }

    public void setPresenter(m mVar) {
        this.f170u = mVar;
        mVar.f1301i = this;
        this.f166q = mVar.f1296d;
    }

    @Override // j.n1, android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return k(layoutParams);
    }
}
