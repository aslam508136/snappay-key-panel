package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.snapay.app.R;
import h.k;
import i.o;
import i.q;
import j.a0;
import j.a3;
import j.b0;
import j.b2;
import j.f1;
import j.g3;
import j.m;
import j.v2;
import j.w0;
import j.w2;
import j.x2;
import j.y2;
import j.z2;
import java.util.ArrayList;
import java.util.WeakHashMap;
import x.u;

/* JADX INFO: loaded from: classes.dex */
public class Toolbar extends ViewGroup {
    public ColorStateList A;
    public ColorStateList B;
    public boolean C;
    public boolean D;
    public final ArrayList E;
    public final ArrayList F;
    public final int[] G;
    public final h.a H;
    public a3 I;
    public m J;
    public w2 K;
    public boolean L;
    public final androidx.activity.b M;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ActionMenuView f210b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public w0 f211c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public w0 f212d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a0 f213e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public b0 f214f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Drawable f215g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final CharSequence f216h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public a0 f217i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public View f218j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Context f219k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f220l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f221m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f222n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final int f223o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f224p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f225q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f226r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f227s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f228t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public b2 f229u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f230v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f231w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final int f232x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public CharSequence f233y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public CharSequence f234z;

    public Toolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.toolbarStyle);
        this.f232x = 8388627;
        this.E = new ArrayList();
        this.F = new ArrayList();
        this.G = new int[2];
        this.H = new h.a(this, 6);
        this.M = new androidx.activity.b(this, 3);
        Context context2 = getContext();
        int[] iArr = c.a.f503w;
        m0.a aVarU = m0.a.u(context2, attributeSet, iArr, R.attr.toolbarStyle);
        Object obj = aVarU.f1643b;
        u.d(this, context, iArr, attributeSet, (TypedArray) obj, R.attr.toolbarStyle);
        this.f221m = aVarU.p(28, 0);
        this.f222n = aVarU.p(19, 0);
        this.f232x = ((TypedArray) obj).getInteger(0, 8388627);
        this.f223o = ((TypedArray) obj).getInteger(2, 48);
        int i2 = aVarU.i(22, 0);
        i2 = aVarU.s(27) ? aVarU.i(27, i2) : i2;
        this.f228t = i2;
        this.f227s = i2;
        this.f226r = i2;
        this.f225q = i2;
        int i3 = aVarU.i(25, -1);
        if (i3 >= 0) {
            this.f225q = i3;
        }
        int i4 = aVarU.i(24, -1);
        if (i4 >= 0) {
            this.f226r = i4;
        }
        int i5 = aVarU.i(26, -1);
        if (i5 >= 0) {
            this.f227s = i5;
        }
        int i6 = aVarU.i(23, -1);
        if (i6 >= 0) {
            this.f228t = i6;
        }
        this.f224p = aVarU.j(13, -1);
        int i7 = aVarU.i(9, Integer.MIN_VALUE);
        int i8 = aVarU.i(5, Integer.MIN_VALUE);
        int iJ = aVarU.j(7, 0);
        int iJ2 = aVarU.j(8, 0);
        if (this.f229u == null) {
            this.f229u = new b2();
        }
        b2 b2Var = this.f229u;
        b2Var.f1195h = false;
        if (iJ != Integer.MIN_VALUE) {
            b2Var.f1192e = iJ;
            b2Var.f1188a = iJ;
        }
        if (iJ2 != Integer.MIN_VALUE) {
            b2Var.f1193f = iJ2;
            b2Var.f1189b = iJ2;
        }
        if (i7 != Integer.MIN_VALUE || i8 != Integer.MIN_VALUE) {
            b2Var.a(i7, i8);
        }
        this.f230v = aVarU.i(10, Integer.MIN_VALUE);
        this.f231w = aVarU.i(6, Integer.MIN_VALUE);
        this.f215g = aVarU.k(4);
        this.f216h = aVarU.r(3);
        CharSequence charSequenceR = aVarU.r(21);
        if (!TextUtils.isEmpty(charSequenceR)) {
            setTitle(charSequenceR);
        }
        CharSequence charSequenceR2 = aVarU.r(18);
        if (!TextUtils.isEmpty(charSequenceR2)) {
            setSubtitle(charSequenceR2);
        }
        this.f219k = getContext();
        setPopupTheme(aVarU.p(17, 0));
        Drawable drawableK = aVarU.k(16);
        if (drawableK != null) {
            setNavigationIcon(drawableK);
        }
        CharSequence charSequenceR3 = aVarU.r(15);
        if (!TextUtils.isEmpty(charSequenceR3)) {
            setNavigationContentDescription(charSequenceR3);
        }
        Drawable drawableK2 = aVarU.k(11);
        if (drawableK2 != null) {
            setLogo(drawableK2);
        }
        CharSequence charSequenceR4 = aVarU.r(12);
        if (!TextUtils.isEmpty(charSequenceR4)) {
            setLogoDescription(charSequenceR4);
        }
        if (aVarU.s(29)) {
            setTitleTextColor(aVarU.h(29));
        }
        if (aVarU.s(20)) {
            setSubtitleTextColor(aVarU.h(20));
        }
        if (aVarU.s(14)) {
            getMenuInflater().inflate(aVarU.p(14, 0), getMenu());
        }
        aVarU.w();
    }

    public static x2 g(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof x2) {
            return new x2((x2) layoutParams);
        }
        if (layoutParams instanceof d.a) {
            return new x2((d.a) layoutParams);
        }
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new x2((ViewGroup.MarginLayoutParams) layoutParams) : new x2(layoutParams);
    }

    private MenuInflater getMenuInflater() {
        return new k(getContext());
    }

    public static int i(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.getMarginEnd() + marginLayoutParams.getMarginStart();
    }

    public static int j(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    public final void a(int i2, ArrayList arrayList) {
        WeakHashMap weakHashMap = u.f2012a;
        boolean z2 = getLayoutDirection() == 1;
        int childCount = getChildCount();
        int absoluteGravity = Gravity.getAbsoluteGravity(i2, getLayoutDirection());
        arrayList.clear();
        if (!z2) {
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                x2 x2Var = (x2) childAt.getLayoutParams();
                if (x2Var.f1494b == 0 && p(childAt)) {
                    int i4 = x2Var.f576a;
                    WeakHashMap weakHashMap2 = u.f2012a;
                    int layoutDirection = getLayoutDirection();
                    int absoluteGravity2 = Gravity.getAbsoluteGravity(i4, layoutDirection) & 7;
                    if (absoluteGravity2 != 1 && absoluteGravity2 != 3 && absoluteGravity2 != 5) {
                        absoluteGravity2 = layoutDirection == 1 ? 5 : 3;
                    }
                    if (absoluteGravity2 == absoluteGravity) {
                        arrayList.add(childAt);
                    }
                }
            }
            return;
        }
        for (int i5 = childCount - 1; i5 >= 0; i5--) {
            View childAt2 = getChildAt(i5);
            x2 x2Var2 = (x2) childAt2.getLayoutParams();
            if (x2Var2.f1494b == 0 && p(childAt2)) {
                int i6 = x2Var2.f576a;
                WeakHashMap weakHashMap3 = u.f2012a;
                int layoutDirection2 = getLayoutDirection();
                int absoluteGravity3 = Gravity.getAbsoluteGravity(i6, layoutDirection2) & 7;
                if (absoluteGravity3 != 1 && absoluteGravity3 != 3 && absoluteGravity3 != 5) {
                    absoluteGravity3 = layoutDirection2 == 1 ? 5 : 3;
                }
                if (absoluteGravity3 == absoluteGravity) {
                    arrayList.add(childAt2);
                }
            }
        }
    }

    public final void b(View view, boolean z2) {
        x2 x2VarG;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            x2VarG = new x2();
        } else {
            x2VarG = !checkLayoutParams(layoutParams) ? g(layoutParams) : (x2) layoutParams;
        }
        x2VarG.f1494b = 1;
        if (!z2 || this.f218j == null) {
            addView(view, x2VarG);
        } else {
            view.setLayoutParams(x2VarG);
            this.F.add(view);
        }
    }

    public final void c() {
        if (this.f217i == null) {
            a0 a0Var = new a0(getContext(), null, R.attr.toolbarNavigationButtonStyle);
            this.f217i = a0Var;
            a0Var.setImageDrawable(this.f215g);
            this.f217i.setContentDescription(this.f216h);
            x2 x2Var = new x2();
            x2Var.f576a = (this.f223o & 112) | 8388611;
            x2Var.f1494b = 2;
            this.f217i.setLayoutParams(x2Var);
            this.f217i.setOnClickListener(new v2(this, 0));
        }
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof x2);
    }

    public final void d() {
        e();
        ActionMenuView actionMenuView = this.f210b;
        if (actionMenuView.f166q == null) {
            o oVar = (o) actionMenuView.getMenu();
            if (this.K == null) {
                this.K = new w2(this);
            }
            this.f210b.setExpandedActionViewsExclusive(true);
            oVar.b(this.K, this.f219k);
        }
    }

    public final void e() {
        if (this.f210b == null) {
            ActionMenuView actionMenuView = new ActionMenuView(getContext(), null);
            this.f210b = actionMenuView;
            actionMenuView.setPopupTheme(this.f220l);
            this.f210b.setOnMenuItemClickListener(this.H);
            ActionMenuView actionMenuView2 = this.f210b;
            actionMenuView2.f171v = null;
            actionMenuView2.f172w = null;
            x2 x2Var = new x2();
            x2Var.f576a = (this.f223o & 112) | 8388613;
            this.f210b.setLayoutParams(x2Var);
            b(this.f210b, false);
        }
    }

    public final void f() {
        if (this.f213e == null) {
            this.f213e = new a0(getContext(), null, R.attr.toolbarNavigationButtonStyle);
            x2 x2Var = new x2();
            x2Var.f576a = (this.f223o & 112) | 8388611;
            this.f213e.setLayoutParams(x2Var);
        }
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new x2();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new x2(getContext(), attributeSet);
    }

    public CharSequence getCollapseContentDescription() {
        a0 a0Var = this.f217i;
        if (a0Var != null) {
            return a0Var.getContentDescription();
        }
        return null;
    }

    public Drawable getCollapseIcon() {
        a0 a0Var = this.f217i;
        if (a0Var != null) {
            return a0Var.getDrawable();
        }
        return null;
    }

    public int getContentInsetEnd() {
        b2 b2Var = this.f229u;
        if (b2Var != null) {
            return b2Var.f1194g ? b2Var.f1188a : b2Var.f1189b;
        }
        return 0;
    }

    public int getContentInsetEndWithActions() {
        int i2 = this.f231w;
        return i2 != Integer.MIN_VALUE ? i2 : getContentInsetEnd();
    }

    public int getContentInsetLeft() {
        b2 b2Var = this.f229u;
        if (b2Var != null) {
            return b2Var.f1188a;
        }
        return 0;
    }

    public int getContentInsetRight() {
        b2 b2Var = this.f229u;
        if (b2Var != null) {
            return b2Var.f1189b;
        }
        return 0;
    }

    public int getContentInsetStart() {
        b2 b2Var = this.f229u;
        if (b2Var != null) {
            return b2Var.f1194g ? b2Var.f1189b : b2Var.f1188a;
        }
        return 0;
    }

    public int getContentInsetStartWithNavigation() {
        int i2 = this.f230v;
        return i2 != Integer.MIN_VALUE ? i2 : getContentInsetStart();
    }

    public int getCurrentContentInsetEnd() {
        o oVar;
        ActionMenuView actionMenuView = this.f210b;
        return actionMenuView != null && (oVar = actionMenuView.f166q) != null && oVar.hasVisibleItems() ? Math.max(getContentInsetEnd(), Math.max(this.f231w, 0)) : getContentInsetEnd();
    }

    public int getCurrentContentInsetLeft() {
        WeakHashMap weakHashMap = u.f2012a;
        return getLayoutDirection() == 1 ? getCurrentContentInsetEnd() : getCurrentContentInsetStart();
    }

    public int getCurrentContentInsetRight() {
        WeakHashMap weakHashMap = u.f2012a;
        return getLayoutDirection() == 1 ? getCurrentContentInsetStart() : getCurrentContentInsetEnd();
    }

    public int getCurrentContentInsetStart() {
        return getNavigationIcon() != null ? Math.max(getContentInsetStart(), Math.max(this.f230v, 0)) : getContentInsetStart();
    }

    public Drawable getLogo() {
        b0 b0Var = this.f214f;
        if (b0Var != null) {
            return b0Var.getDrawable();
        }
        return null;
    }

    public CharSequence getLogoDescription() {
        b0 b0Var = this.f214f;
        if (b0Var != null) {
            return b0Var.getContentDescription();
        }
        return null;
    }

    public Menu getMenu() {
        d();
        return this.f210b.getMenu();
    }

    public CharSequence getNavigationContentDescription() {
        a0 a0Var = this.f213e;
        if (a0Var != null) {
            return a0Var.getContentDescription();
        }
        return null;
    }

    public Drawable getNavigationIcon() {
        a0 a0Var = this.f213e;
        if (a0Var != null) {
            return a0Var.getDrawable();
        }
        return null;
    }

    public m getOuterActionMenuPresenter() {
        return this.J;
    }

    public Drawable getOverflowIcon() {
        d();
        return this.f210b.getOverflowIcon();
    }

    public Context getPopupContext() {
        return this.f219k;
    }

    public int getPopupTheme() {
        return this.f220l;
    }

    public CharSequence getSubtitle() {
        return this.f234z;
    }

    public final TextView getSubtitleTextView() {
        return this.f212d;
    }

    public CharSequence getTitle() {
        return this.f233y;
    }

    public int getTitleMarginBottom() {
        return this.f228t;
    }

    public int getTitleMarginEnd() {
        return this.f226r;
    }

    public int getTitleMarginStart() {
        return this.f225q;
    }

    public int getTitleMarginTop() {
        return this.f227s;
    }

    public final TextView getTitleTextView() {
        return this.f211c;
    }

    public f1 getWrapper() {
        if (this.I == null) {
            this.I = new a3(this);
        }
        return this.I;
    }

    public final int h(View view, int i2) {
        x2 x2Var = (x2) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        int i3 = i2 > 0 ? (measuredHeight - i2) / 2 : 0;
        int i4 = x2Var.f576a & 112;
        if (i4 != 16 && i4 != 48 && i4 != 80) {
            i4 = this.f232x & 112;
        }
        if (i4 == 48) {
            return getPaddingTop() - i3;
        }
        if (i4 == 80) {
            return (((getHeight() - getPaddingBottom()) - measuredHeight) - ((ViewGroup.MarginLayoutParams) x2Var).bottomMargin) - i3;
        }
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int height = getHeight();
        int iMax = (((height - paddingTop) - paddingBottom) - measuredHeight) / 2;
        int i5 = ((ViewGroup.MarginLayoutParams) x2Var).topMargin;
        if (iMax < i5) {
            iMax = i5;
        } else {
            int i6 = (((height - paddingBottom) - measuredHeight) - iMax) - paddingTop;
            int i7 = ((ViewGroup.MarginLayoutParams) x2Var).bottomMargin;
            if (i6 < i7) {
                iMax = Math.max(0, iMax - (i7 - i6));
            }
        }
        return paddingTop + iMax;
    }

    public final boolean k(View view) {
        return view.getParent() == this || this.F.contains(view);
    }

    public final int l(View view, int i2, int i3, int[] iArr) {
        x2 x2Var = (x2) view.getLayoutParams();
        int i4 = ((ViewGroup.MarginLayoutParams) x2Var).leftMargin - iArr[0];
        int iMax = Math.max(0, i4) + i2;
        iArr[0] = Math.max(0, -i4);
        int iH = h(view, i3);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax, iH, iMax + measuredWidth, view.getMeasuredHeight() + iH);
        return measuredWidth + ((ViewGroup.MarginLayoutParams) x2Var).rightMargin + iMax;
    }

    public final int m(View view, int i2, int i3, int[] iArr) {
        x2 x2Var = (x2) view.getLayoutParams();
        int i4 = ((ViewGroup.MarginLayoutParams) x2Var).rightMargin - iArr[1];
        int iMax = i2 - Math.max(0, i4);
        iArr[1] = Math.max(0, -i4);
        int iH = h(view, i3);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax - measuredWidth, iH, iMax, view.getMeasuredHeight() + iH);
        return iMax - (measuredWidth + ((ViewGroup.MarginLayoutParams) x2Var).leftMargin);
    }

    public final int n(View view, int i2, int i3, int i4, int i5, int[] iArr) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i6 = marginLayoutParams.leftMargin - iArr[0];
        int i7 = marginLayoutParams.rightMargin - iArr[1];
        int iMax = Math.max(0, i7) + Math.max(0, i6);
        iArr[0] = Math.max(0, -i6);
        iArr[1] = Math.max(0, -i7);
        view.measure(ViewGroup.getChildMeasureSpec(i2, getPaddingRight() + getPaddingLeft() + iMax + i3, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i4, getPaddingBottom() + getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i5, marginLayoutParams.height));
        return view.getMeasuredWidth() + iMax;
    }

    public final void o(View view, int i2, int i3, int i4, int i5) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, getPaddingRight() + getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i3, marginLayoutParams.width);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i4, getPaddingBottom() + getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + 0, marginLayoutParams.height);
        int mode = View.MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i5 >= 0) {
            if (mode != 0) {
                i5 = Math.min(View.MeasureSpec.getSize(childMeasureSpec2), i5);
            }
            childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i5, 1073741824);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.M);
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.D = false;
        }
        if (!this.D) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.D = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.D = false;
        }
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x024e  */
    /* JADX WARN: Code duplicated, block: B:102:0x0251  */
    /* JADX WARN: Code duplicated, block: B:103:0x0273  */
    /* JADX WARN: Code duplicated, block: B:105:0x0276  */
    /* JADX WARN: Code duplicated, block: B:108:0x0288 A[LOOP:0: B:107:0x0286->B:108:0x0288, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:111:0x02a4 A[LOOP:1: B:110:0x02a2->B:111:0x02a4, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:114:0x02c3 A[LOOP:2: B:113:0x02c1->B:114:0x02c3, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:118:0x0304 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:119:0x0306  */
    /* JADX WARN: Code duplicated, block: B:120:0x030a  */
    /* JADX WARN: Code duplicated, block: B:123:0x0311 A[LOOP:3: B:122:0x030f->B:123:0x0311, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:20:0x005f  */
    /* JADX WARN: Code duplicated, block: B:22:0x0063  */
    /* JADX WARN: Code duplicated, block: B:23:0x0068  */
    /* JADX WARN: Code duplicated, block: B:26:0x0074  */
    /* JADX WARN: Code duplicated, block: B:28:0x0078  */
    /* JADX WARN: Code duplicated, block: B:29:0x007d  */
    /* JADX WARN: Code duplicated, block: B:32:0x00af  */
    /* JADX WARN: Code duplicated, block: B:34:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:35:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:38:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:40:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:41:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:44:0x00df  */
    /* JADX WARN: Code duplicated, block: B:45:0x00f8  */
    /* JADX WARN: Code duplicated, block: B:47:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:48:0x0115  */
    /* JADX WARN: Code duplicated, block: B:53:0x0122 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:54:0x0124  */
    /* JADX WARN: Code duplicated, block: B:55:0x0127  */
    /* JADX WARN: Code duplicated, block: B:57:0x012b  */
    /* JADX WARN: Code duplicated, block: B:58:0x012e  */
    /* JADX WARN: Code duplicated, block: B:61:0x013e  */
    /* JADX WARN: Code duplicated, block: B:63:0x0146 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:70:0x015f  */
    /* JADX WARN: Code duplicated, block: B:72:0x0163  */
    /* JADX WARN: Code duplicated, block: B:74:0x0172  */
    /* JADX WARN: Code duplicated, block: B:75:0x0174  */
    /* JADX WARN: Code duplicated, block: B:77:0x017f  */
    /* JADX WARN: Code duplicated, block: B:79:0x018b  */
    /* JADX WARN: Code duplicated, block: B:80:0x0197  */
    /* JADX WARN: Code duplicated, block: B:82:0x01a6 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:83:0x01a8  */
    /* JADX WARN: Code duplicated, block: B:84:0x01ab  */
    /* JADX WARN: Code duplicated, block: B:87:0x01bf  */
    /* JADX WARN: Code duplicated, block: B:88:0x01e3  */
    /* JADX WARN: Code duplicated, block: B:90:0x01e6  */
    /* JADX WARN: Code duplicated, block: B:91:0x020a  */
    /* JADX WARN: Code duplicated, block: B:93:0x020d  */
    /* JADX WARN: Code duplicated, block: B:94:0x0213 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:95:0x0215  */
    /* JADX WARN: Code duplicated, block: B:96:0x0218  */
    /* JADX WARN: Code duplicated, block: B:99:0x022b  */
    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int iL;
        int iM;
        int iMax;
        int iMin;
        boolean zP;
        boolean zP2;
        int measuredHeight;
        w0 w0Var;
        w0 w0Var2;
        x2 x2Var;
        x2 x2Var2;
        boolean z3;
        int i6;
        int i7;
        int paddingTop;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int iMax2;
        int i14;
        int i15;
        int i16;
        int i17;
        ArrayList arrayList;
        int size;
        int iL2;
        int i18;
        int i19;
        int size2;
        int i20;
        int i21;
        int size3;
        int i22;
        int i23;
        int measuredWidth;
        int i24;
        int i25;
        int i26;
        int size4;
        b0 b0Var;
        View view;
        ActionMenuView actionMenuView;
        a0 a0Var;
        WeakHashMap weakHashMap = u.f2012a;
        boolean z4 = getLayoutDirection() == 1;
        int width = getWidth();
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop2 = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i27 = width - paddingRight;
        int[] iArr = this.G;
        iArr[1] = 0;
        iArr[0] = 0;
        int minimumHeight = getMinimumHeight();
        int iMin2 = minimumHeight >= 0 ? Math.min(minimumHeight, i5 - i3) : 0;
        if (p(this.f213e)) {
            a0 a0Var2 = this.f213e;
            if (z4) {
                iM = m(a0Var2, i27, iMin2, iArr);
                iL = paddingLeft;
            } else {
                iL = l(a0Var2, paddingLeft, iMin2, iArr);
            }
            if (p(this.f217i)) {
                a0Var = this.f217i;
                if (z4) {
                    iM = m(a0Var, iM, iMin2, iArr);
                } else {
                    iL = l(a0Var, iL, iMin2, iArr);
                }
            }
            if (p(this.f210b)) {
                actionMenuView = this.f210b;
                if (z4) {
                    iL = l(actionMenuView, iL, iMin2, iArr);
                } else {
                    iM = m(actionMenuView, iM, iMin2, iArr);
                }
            }
            int currentContentInsetLeft = getCurrentContentInsetLeft();
            int currentContentInsetRight = getCurrentContentInsetRight();
            iArr[0] = Math.max(0, currentContentInsetLeft - iL);
            iArr[1] = Math.max(0, currentContentInsetRight - (i27 - iM));
            iMax = Math.max(iL, currentContentInsetLeft);
            iMin = Math.min(iM, i27 - currentContentInsetRight);
            if (p(this.f218j)) {
                view = this.f218j;
                if (z4) {
                    iMin = m(view, iMin, iMin2, iArr);
                } else {
                    iMax = l(view, iMax, iMin2, iArr);
                }
            }
            if (p(this.f214f)) {
                b0Var = this.f214f;
                if (z4) {
                    iMin = m(b0Var, iMin, iMin2, iArr);
                } else {
                    iMax = l(b0Var, iMax, iMin2, iArr);
                }
            }
            zP = p(this.f211c);
            zP2 = p(this.f212d);
            if (zP) {
                x2 x2Var3 = (x2) this.f211c.getLayoutParams();
                measuredHeight = this.f211c.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) x2Var3).topMargin + ((ViewGroup.MarginLayoutParams) x2Var3).bottomMargin + 0;
            } else {
                measuredHeight = 0;
            }
            if (zP2) {
                x2 x2Var4 = (x2) this.f212d.getLayoutParams();
                measuredHeight += this.f212d.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) x2Var4).topMargin + ((ViewGroup.MarginLayoutParams) x2Var4).bottomMargin;
            }
            if (!zP || zP2) {
                if (zP) {
                    w0Var = this.f211c;
                } else {
                    w0Var = this.f212d;
                }
                if (zP2) {
                    w0Var2 = this.f212d;
                } else {
                    w0Var2 = this.f211c;
                }
                x2Var = (x2) w0Var.getLayoutParams();
                x2Var2 = (x2) w0Var2.getLayoutParams();
                z3 = (!zP && this.f211c.getMeasuredWidth() > 0) || (zP2 && this.f212d.getMeasuredWidth() > 0);
                i6 = this.f232x & 112;
                i7 = paddingLeft;
                if (i6 == 48) {
                    paddingTop = getPaddingTop() + ((ViewGroup.MarginLayoutParams) x2Var).topMargin + this.f227s;
                } else if (i6 != 80) {
                    iMax2 = (((height - paddingTop2) - paddingBottom) - measuredHeight) / 2;
                    i14 = ((ViewGroup.MarginLayoutParams) x2Var).topMargin + this.f227s;
                    if (iMax2 < i14) {
                        iMax2 = i14;
                    } else {
                        i15 = (((height - paddingBottom) - measuredHeight) - iMax2) - paddingTop2;
                        i16 = ((ViewGroup.MarginLayoutParams) x2Var).bottomMargin;
                        i17 = this.f228t;
                        if (i15 < i16 + i17) {
                            iMax2 = Math.max(0, iMax2 - ((((ViewGroup.MarginLayoutParams) x2Var2).bottomMargin + i17) - i15));
                        }
                    }
                    paddingTop = paddingTop2 + iMax2;
                } else {
                    paddingTop = (((height - paddingBottom) - ((ViewGroup.MarginLayoutParams) x2Var2).bottomMargin) - this.f228t) - measuredHeight;
                }
                if (z4) {
                    if (z3) {
                        i11 = this.f225q;
                    } else {
                        i11 = 0;
                    }
                    int i28 = i11 - iArr[1];
                    iMin -= Math.max(0, i28);
                    iArr[1] = Math.max(0, -i28);
                    if (zP) {
                        x2 x2Var5 = (x2) this.f211c.getLayoutParams();
                        int measuredWidth2 = iMin - this.f211c.getMeasuredWidth();
                        int measuredHeight2 = this.f211c.getMeasuredHeight() + paddingTop;
                        this.f211c.layout(measuredWidth2, paddingTop, iMin, measuredHeight2);
                        i12 = measuredWidth2 - this.f226r;
                        paddingTop = measuredHeight2 + ((ViewGroup.MarginLayoutParams) x2Var5).bottomMargin;
                    } else {
                        i12 = iMin;
                    }
                    if (zP2) {
                        int i29 = paddingTop + ((ViewGroup.MarginLayoutParams) ((x2) this.f212d.getLayoutParams())).topMargin;
                        this.f212d.layout(iMin - this.f212d.getMeasuredWidth(), i29, iMin, this.f212d.getMeasuredHeight() + i29);
                        i13 = iMin - this.f226r;
                    } else {
                        i13 = iMin;
                    }
                    if (z3) {
                        iMin = Math.min(i12, i13);
                    }
                } else {
                    if (z3) {
                        i8 = this.f225q;
                    } else {
                        i8 = 0;
                    }
                    int i30 = i8 - iArr[0];
                    iMax += Math.max(0, i30);
                    iArr[0] = Math.max(0, -i30);
                    if (zP) {
                        x2 x2Var6 = (x2) this.f211c.getLayoutParams();
                        int measuredWidth3 = this.f211c.getMeasuredWidth() + iMax;
                        int measuredHeight3 = this.f211c.getMeasuredHeight() + paddingTop;
                        this.f211c.layout(iMax, paddingTop, measuredWidth3, measuredHeight3);
                        i9 = measuredWidth3 + this.f226r;
                        paddingTop = measuredHeight3 + ((ViewGroup.MarginLayoutParams) x2Var6).bottomMargin;
                    } else {
                        i9 = iMax;
                    }
                    if (zP2) {
                        int i31 = paddingTop + ((ViewGroup.MarginLayoutParams) ((x2) this.f212d.getLayoutParams())).topMargin;
                        int measuredWidth4 = this.f212d.getMeasuredWidth() + iMax;
                        this.f212d.layout(iMax, i31, measuredWidth4, this.f212d.getMeasuredHeight() + i31);
                        i10 = measuredWidth4 + this.f226r;
                    } else {
                        i10 = iMax;
                    }
                    if (z3) {
                        iMax = Math.max(i9, i10);
                    }
                }
            } else {
                i7 = paddingLeft;
                iMin2 = iMin2;
            }
            arrayList = this.E;
            a(3, arrayList);
            size = arrayList.size();
            iL2 = iMax;
            for (i18 = 0; i18 < size; i18++) {
                iL2 = l((View) arrayList.get(i18), iL2, iMin2, iArr);
            }
            i19 = iMin2;
            a(5, arrayList);
            size2 = arrayList.size();
            for (i20 = 0; i20 < size2; i20++) {
                iMin = m((View) arrayList.get(i20), iMin, i19, iArr);
            }
            a(1, arrayList);
            int i32 = iArr[0];
            i21 = iArr[1];
            size3 = arrayList.size();
            i22 = i32;
            i23 = 0;
            measuredWidth = 0;
            while (i23 < size3) {
                View view2 = (View) arrayList.get(i23);
                x2 x2Var7 = (x2) view2.getLayoutParams();
                int i33 = ((ViewGroup.MarginLayoutParams) x2Var7).leftMargin - i22;
                int i34 = ((ViewGroup.MarginLayoutParams) x2Var7).rightMargin - i21;
                int iMax3 = Math.max(0, i33);
                int iMax4 = Math.max(0, i34);
                int iMax5 = Math.max(0, -i33);
                int iMax6 = Math.max(0, -i34);
                measuredWidth += view2.getMeasuredWidth() + iMax3 + iMax4;
                i23++;
                i21 = iMax6;
                i22 = iMax5;
            }
            i25 = ((((width - i7) - paddingRight) / 2) + i7) - (measuredWidth / 2);
            i26 = measuredWidth + i25;
            if (i25 >= iL2) {
                if (i26 > iMin) {
                    iL2 = i25 - (i26 - iMin);
                } else {
                    iL2 = i25;
                }
            }
            size4 = arrayList.size();
            for (i24 = 0; i24 < size4; i24++) {
                iL2 = l((View) arrayList.get(i24), iL2, i19, iArr);
            }
            arrayList.clear();
        }
        iL = paddingLeft;
        iM = i27;
        if (p(this.f217i)) {
            a0Var = this.f217i;
            if (z4) {
                iM = m(a0Var, iM, iMin2, iArr);
            } else {
                iL = l(a0Var, iL, iMin2, iArr);
            }
        }
        if (p(this.f210b)) {
            actionMenuView = this.f210b;
            if (z4) {
                iL = l(actionMenuView, iL, iMin2, iArr);
            } else {
                iM = m(actionMenuView, iM, iMin2, iArr);
            }
        }
        int currentContentInsetLeft2 = getCurrentContentInsetLeft();
        int currentContentInsetRight2 = getCurrentContentInsetRight();
        iArr[0] = Math.max(0, currentContentInsetLeft2 - iL);
        iArr[1] = Math.max(0, currentContentInsetRight2 - (i27 - iM));
        iMax = Math.max(iL, currentContentInsetLeft2);
        iMin = Math.min(iM, i27 - currentContentInsetRight2);
        if (p(this.f218j)) {
            view = this.f218j;
            if (z4) {
                iMin = m(view, iMin, iMin2, iArr);
            } else {
                iMax = l(view, iMax, iMin2, iArr);
            }
        }
        if (p(this.f214f)) {
            b0Var = this.f214f;
            if (z4) {
                iMin = m(b0Var, iMin, iMin2, iArr);
            } else {
                iMax = l(b0Var, iMax, iMin2, iArr);
            }
        }
        zP = p(this.f211c);
        zP2 = p(this.f212d);
        if (zP) {
            x2 x2Var8 = (x2) this.f211c.getLayoutParams();
            measuredHeight = this.f211c.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) x2Var8).topMargin + ((ViewGroup.MarginLayoutParams) x2Var8).bottomMargin + 0;
        } else {
            measuredHeight = 0;
        }
        if (zP2) {
            x2 x2Var9 = (x2) this.f212d.getLayoutParams();
            measuredHeight += this.f212d.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) x2Var9).topMargin + ((ViewGroup.MarginLayoutParams) x2Var9).bottomMargin;
        }
        if (zP) {
            if (zP) {
                w0Var = this.f211c;
            } else {
                w0Var = this.f212d;
            }
            if (zP2) {
                w0Var2 = this.f212d;
            } else {
                w0Var2 = this.f211c;
            }
            x2Var = (x2) w0Var.getLayoutParams();
            x2Var2 = (x2) w0Var2.getLayoutParams();
            if (zP) {
            }
            i6 = this.f232x & 112;
            i7 = paddingLeft;
            if (i6 == 48) {
                paddingTop = getPaddingTop() + ((ViewGroup.MarginLayoutParams) x2Var).topMargin + this.f227s;
            } else if (i6 != 80) {
                iMax2 = (((height - paddingTop2) - paddingBottom) - measuredHeight) / 2;
                i14 = ((ViewGroup.MarginLayoutParams) x2Var).topMargin + this.f227s;
                if (iMax2 < i14) {
                    iMax2 = i14;
                } else {
                    i15 = (((height - paddingBottom) - measuredHeight) - iMax2) - paddingTop2;
                    i16 = ((ViewGroup.MarginLayoutParams) x2Var).bottomMargin;
                    i17 = this.f228t;
                    if (i15 < i16 + i17) {
                        iMax2 = Math.max(0, iMax2 - ((((ViewGroup.MarginLayoutParams) x2Var2).bottomMargin + i17) - i15));
                    }
                }
                paddingTop = paddingTop2 + iMax2;
            } else {
                paddingTop = (((height - paddingBottom) - ((ViewGroup.MarginLayoutParams) x2Var2).bottomMargin) - this.f228t) - measuredHeight;
            }
            if (z4) {
                if (z3) {
                    i11 = this.f225q;
                } else {
                    i11 = 0;
                }
                int i210 = i11 - iArr[1];
                iMin -= Math.max(0, i210);
                iArr[1] = Math.max(0, -i210);
                if (zP) {
                    x2 x2Var10 = (x2) this.f211c.getLayoutParams();
                    int measuredWidth5 = iMin - this.f211c.getMeasuredWidth();
                    int measuredHeight4 = this.f211c.getMeasuredHeight() + paddingTop;
                    this.f211c.layout(measuredWidth5, paddingTop, iMin, measuredHeight4);
                    i12 = measuredWidth5 - this.f226r;
                    paddingTop = measuredHeight4 + ((ViewGroup.MarginLayoutParams) x2Var10).bottomMargin;
                } else {
                    i12 = iMin;
                }
                if (zP2) {
                    int i211 = paddingTop + ((ViewGroup.MarginLayoutParams) ((x2) this.f212d.getLayoutParams())).topMargin;
                    this.f212d.layout(iMin - this.f212d.getMeasuredWidth(), i211, iMin, this.f212d.getMeasuredHeight() + i211);
                    i13 = iMin - this.f226r;
                } else {
                    i13 = iMin;
                }
                if (z3) {
                    iMin = Math.min(i12, i13);
                }
            } else {
                if (z3) {
                    i8 = this.f225q;
                } else {
                    i8 = 0;
                }
                int i35 = i8 - iArr[0];
                iMax += Math.max(0, i35);
                iArr[0] = Math.max(0, -i35);
                if (zP) {
                    x2 x2Var11 = (x2) this.f211c.getLayoutParams();
                    int measuredWidth6 = this.f211c.getMeasuredWidth() + iMax;
                    int measuredHeight5 = this.f211c.getMeasuredHeight() + paddingTop;
                    this.f211c.layout(iMax, paddingTop, measuredWidth6, measuredHeight5);
                    i9 = measuredWidth6 + this.f226r;
                    paddingTop = measuredHeight5 + ((ViewGroup.MarginLayoutParams) x2Var11).bottomMargin;
                } else {
                    i9 = iMax;
                }
                if (zP2) {
                    int i36 = paddingTop + ((ViewGroup.MarginLayoutParams) ((x2) this.f212d.getLayoutParams())).topMargin;
                    int measuredWidth7 = this.f212d.getMeasuredWidth() + iMax;
                    this.f212d.layout(iMax, i36, measuredWidth7, this.f212d.getMeasuredHeight() + i36);
                    i10 = measuredWidth7 + this.f226r;
                } else {
                    i10 = iMax;
                }
                if (z3) {
                    iMax = Math.max(i9, i10);
                }
            }
        } else {
            if (zP) {
                w0Var = this.f211c;
            } else {
                w0Var = this.f212d;
            }
            if (zP2) {
                w0Var2 = this.f212d;
            } else {
                w0Var2 = this.f211c;
            }
            x2Var = (x2) w0Var.getLayoutParams();
            x2Var2 = (x2) w0Var2.getLayoutParams();
            if (zP) {
            }
            i6 = this.f232x & 112;
            i7 = paddingLeft;
            if (i6 == 48) {
                paddingTop = getPaddingTop() + ((ViewGroup.MarginLayoutParams) x2Var).topMargin + this.f227s;
            } else if (i6 != 80) {
                iMax2 = (((height - paddingTop2) - paddingBottom) - measuredHeight) / 2;
                i14 = ((ViewGroup.MarginLayoutParams) x2Var).topMargin + this.f227s;
                if (iMax2 < i14) {
                    iMax2 = i14;
                } else {
                    i15 = (((height - paddingBottom) - measuredHeight) - iMax2) - paddingTop2;
                    i16 = ((ViewGroup.MarginLayoutParams) x2Var).bottomMargin;
                    i17 = this.f228t;
                    if (i15 < i16 + i17) {
                        iMax2 = Math.max(0, iMax2 - ((((ViewGroup.MarginLayoutParams) x2Var2).bottomMargin + i17) - i15));
                    }
                }
                paddingTop = paddingTop2 + iMax2;
            } else {
                paddingTop = (((height - paddingBottom) - ((ViewGroup.MarginLayoutParams) x2Var2).bottomMargin) - this.f228t) - measuredHeight;
            }
            if (z4) {
                if (z3) {
                    i11 = this.f225q;
                } else {
                    i11 = 0;
                }
                int i212 = i11 - iArr[1];
                iMin -= Math.max(0, i212);
                iArr[1] = Math.max(0, -i212);
                if (zP) {
                    x2 x2Var12 = (x2) this.f211c.getLayoutParams();
                    int measuredWidth8 = iMin - this.f211c.getMeasuredWidth();
                    int measuredHeight6 = this.f211c.getMeasuredHeight() + paddingTop;
                    this.f211c.layout(measuredWidth8, paddingTop, iMin, measuredHeight6);
                    i12 = measuredWidth8 - this.f226r;
                    paddingTop = measuredHeight6 + ((ViewGroup.MarginLayoutParams) x2Var12).bottomMargin;
                } else {
                    i12 = iMin;
                }
                if (zP2) {
                    int i213 = paddingTop + ((ViewGroup.MarginLayoutParams) ((x2) this.f212d.getLayoutParams())).topMargin;
                    this.f212d.layout(iMin - this.f212d.getMeasuredWidth(), i213, iMin, this.f212d.getMeasuredHeight() + i213);
                    i13 = iMin - this.f226r;
                } else {
                    i13 = iMin;
                }
                if (z3) {
                    iMin = Math.min(i12, i13);
                }
            } else {
                if (z3) {
                    i8 = this.f225q;
                } else {
                    i8 = 0;
                }
                int i37 = i8 - iArr[0];
                iMax += Math.max(0, i37);
                iArr[0] = Math.max(0, -i37);
                if (zP) {
                    x2 x2Var13 = (x2) this.f211c.getLayoutParams();
                    int measuredWidth9 = this.f211c.getMeasuredWidth() + iMax;
                    int measuredHeight7 = this.f211c.getMeasuredHeight() + paddingTop;
                    this.f211c.layout(iMax, paddingTop, measuredWidth9, measuredHeight7);
                    i9 = measuredWidth9 + this.f226r;
                    paddingTop = measuredHeight7 + ((ViewGroup.MarginLayoutParams) x2Var13).bottomMargin;
                } else {
                    i9 = iMax;
                }
                if (zP2) {
                    int i38 = paddingTop + ((ViewGroup.MarginLayoutParams) ((x2) this.f212d.getLayoutParams())).topMargin;
                    int measuredWidth10 = this.f212d.getMeasuredWidth() + iMax;
                    this.f212d.layout(iMax, i38, measuredWidth10, this.f212d.getMeasuredHeight() + i38);
                    i10 = measuredWidth10 + this.f226r;
                } else {
                    i10 = iMax;
                }
                if (z3) {
                    iMax = Math.max(i9, i10);
                }
            }
        }
        arrayList = this.E;
        a(3, arrayList);
        size = arrayList.size();
        iL2 = iMax;
        while (i18 < size) {
            iL2 = l((View) arrayList.get(i18), iL2, iMin2, iArr);
        }
        i19 = iMin2;
        a(5, arrayList);
        size2 = arrayList.size();
        while (i20 < size2) {
            iMin = m((View) arrayList.get(i20), iMin, i19, iArr);
        }
        a(1, arrayList);
        int i39 = iArr[0];
        i21 = iArr[1];
        size3 = arrayList.size();
        i22 = i39;
        i23 = 0;
        measuredWidth = 0;
        while (i23 < size3) {
            View view3 = (View) arrayList.get(i23);
            x2 x2Var14 = (x2) view3.getLayoutParams();
            int i310 = ((ViewGroup.MarginLayoutParams) x2Var14).leftMargin - i22;
            int i311 = ((ViewGroup.MarginLayoutParams) x2Var14).rightMargin - i21;
            int iMax7 = Math.max(0, i310);
            int iMax8 = Math.max(0, i311);
            int iMax9 = Math.max(0, -i310);
            int iMax10 = Math.max(0, -i311);
            measuredWidth += view3.getMeasuredWidth() + iMax7 + iMax8;
            i23++;
            i21 = iMax10;
            i22 = iMax9;
        }
        i25 = ((((width - i7) - paddingRight) / 2) + i7) - (measuredWidth / 2);
        i26 = measuredWidth + i25;
        if (i25 >= iL2) {
            if (i26 > iMin) {
                iL2 = i25 - (i26 - iMin);
            } else {
                iL2 = i25;
            }
        }
        size4 = arrayList.size();
        while (i24 < size4) {
            iL2 = l((View) arrayList.get(i24), iL2, i19, iArr);
        }
        arrayList.clear();
    }

    @Override // android.view.View
    public final void onMeasure(int i2, int i3) {
        int i4;
        int iMax;
        int iCombineMeasuredStates;
        int i5;
        int iCombineMeasuredStates2;
        int iMax2;
        int iJ;
        boolean z2;
        boolean zA = g3.a(this);
        int i6 = !zA ? 1 : 0;
        if (p(this.f213e)) {
            o(this.f213e, i2, 0, i3, this.f224p);
            i4 = i(this.f213e) + this.f213e.getMeasuredWidth();
            iMax = Math.max(0, j(this.f213e) + this.f213e.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(0, this.f213e.getMeasuredState());
        } else {
            i4 = 0;
            iMax = 0;
            iCombineMeasuredStates = 0;
        }
        if (p(this.f217i)) {
            o(this.f217i, i2, 0, i3, this.f224p);
            i4 = i(this.f217i) + this.f217i.getMeasuredWidth();
            iMax = Math.max(iMax, j(this.f217i) + this.f217i.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f217i.getMeasuredState());
        }
        int currentContentInsetStart = getCurrentContentInsetStart();
        int iMax3 = Math.max(currentContentInsetStart, i4) + 0;
        int iMax4 = Math.max(0, currentContentInsetStart - i4);
        int[] iArr = this.G;
        iArr[zA ? 1 : 0] = iMax4;
        if (p(this.f210b)) {
            o(this.f210b, i2, iMax3, i3, this.f224p);
            i5 = i(this.f210b) + this.f210b.getMeasuredWidth();
            iMax = Math.max(iMax, j(this.f210b) + this.f210b.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f210b.getMeasuredState());
        } else {
            i5 = 0;
        }
        int currentContentInsetEnd = getCurrentContentInsetEnd();
        int iMax5 = iMax3 + Math.max(currentContentInsetEnd, i5);
        iArr[i6] = Math.max(0, currentContentInsetEnd - i5);
        if (p(this.f218j)) {
            iMax5 += n(this.f218j, i2, iMax5, i3, 0, iArr);
            iMax = Math.max(iMax, j(this.f218j) + this.f218j.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f218j.getMeasuredState());
        }
        if (p(this.f214f)) {
            iMax5 += n(this.f214f, i2, iMax5, i3, 0, iArr);
            iMax = Math.max(iMax, j(this.f214f) + this.f214f.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.f214f.getMeasuredState());
        }
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (((x2) childAt.getLayoutParams()).f1494b == 0 && p(childAt)) {
                iMax5 += n(childAt, i2, iMax5, i3, 0, iArr);
                iMax = Math.max(iMax, j(childAt) + childAt.getMeasuredHeight());
                iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, childAt.getMeasuredState());
            }
        }
        int i8 = this.f227s + this.f228t;
        int i9 = this.f225q + this.f226r;
        if (p(this.f211c)) {
            n(this.f211c, i2, iMax5 + i9, i3, i8, iArr);
            int i10 = i(this.f211c) + this.f211c.getMeasuredWidth();
            iJ = j(this.f211c) + this.f211c.getMeasuredHeight();
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.f211c.getMeasuredState());
            iMax2 = i10;
        } else {
            iCombineMeasuredStates2 = iCombineMeasuredStates;
            iMax2 = 0;
            iJ = 0;
        }
        if (p(this.f212d)) {
            iMax2 = Math.max(iMax2, n(this.f212d, i2, iMax5 + i9, i3, iJ + i8, iArr));
            iJ += j(this.f212d) + this.f212d.getMeasuredHeight();
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates2, this.f212d.getMeasuredState());
        }
        int iMax6 = Math.max(iMax, iJ);
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop() + iMax6;
        int iResolveSizeAndState = View.resolveSizeAndState(Math.max(paddingRight + iMax5 + iMax2, getSuggestedMinimumWidth()), i2, (-16777216) & iCombineMeasuredStates2);
        int iResolveSizeAndState2 = View.resolveSizeAndState(Math.max(paddingBottom, getSuggestedMinimumHeight()), i3, iCombineMeasuredStates2 << 16);
        if (!this.L) {
            z2 = false;
            break;
        }
        int childCount2 = getChildCount();
        int i11 = 0;
        while (true) {
            if (i11 >= childCount2) {
                z2 = true;
                break;
            }
            View childAt2 = getChildAt(i11);
            if (p(childAt2) && childAt2.getMeasuredWidth() > 0 && childAt2.getMeasuredHeight() > 0) {
                z2 = false;
                break;
            }
            i11++;
        }
        setMeasuredDimension(iResolveSizeAndState, z2 ? 0 : iResolveSizeAndState2);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        MenuItem menuItemFindItem;
        if (!(parcelable instanceof z2)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        z2 z2Var = (z2) parcelable;
        super.onRestoreInstanceState(z2Var.f508a);
        ActionMenuView actionMenuView = this.f210b;
        o oVar = actionMenuView != null ? actionMenuView.f166q : null;
        int i2 = z2Var.f1502c;
        if (i2 != 0 && this.K != null && oVar != null && (menuItemFindItem = oVar.findItem(i2)) != null) {
            menuItemFindItem.expandActionView();
        }
        if (z2Var.f1503d) {
            androidx.activity.b bVar = this.M;
            removeCallbacks(bVar);
            post(bVar);
        }
    }

    @Override // android.view.View
    public final void onRtlPropertiesChanged(int i2) {
        int i3;
        super.onRtlPropertiesChanged(i2);
        if (this.f229u == null) {
            this.f229u = new b2();
        }
        b2 b2Var = this.f229u;
        boolean z2 = i2 == 1;
        if (z2 == b2Var.f1194g) {
            return;
        }
        b2Var.f1194g = z2;
        if (b2Var.f1195h) {
            if (z2) {
                int i4 = b2Var.f1191d;
                if (i4 == Integer.MIN_VALUE) {
                    i4 = b2Var.f1192e;
                }
                b2Var.f1188a = i4;
                i3 = b2Var.f1190c;
                if (i3 == Integer.MIN_VALUE) {
                }
            } else {
                int i5 = b2Var.f1190c;
                if (i5 == Integer.MIN_VALUE) {
                    i5 = b2Var.f1192e;
                }
                b2Var.f1188a = i5;
                i3 = b2Var.f1191d;
                if (i3 == Integer.MIN_VALUE) {
                }
            }
            b2Var.f1189b = i3;
        }
        b2Var.f1188a = b2Var.f1192e;
        i3 = b2Var.f1193f;
        b2Var.f1189b = i3;
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        q qVar;
        z2 z2Var = new z2(super.onSaveInstanceState());
        w2 w2Var = this.K;
        if (w2Var != null && (qVar = w2Var.f1485c) != null) {
            z2Var.f1502c = qVar.f1087a;
        }
        ActionMenuView actionMenuView = this.f210b;
        boolean z2 = false;
        if (actionMenuView != null) {
            m mVar = actionMenuView.f170u;
            if (mVar != null && mVar.j()) {
                z2 = true;
            }
        }
        z2Var.f1503d = z2;
        return z2Var;
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.C = false;
        }
        if (!this.C) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.C = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.C = false;
        }
        return true;
    }

    public final boolean p(View view) {
        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
    }

    public void setCollapseContentDescription(int i2) {
        setCollapseContentDescription(i2 != 0 ? getContext().getText(i2) : null);
    }

    public void setCollapseIcon(int i2) {
        setCollapseIcon(e.b.c(getContext(), i2));
    }

    public void setCollapsible(boolean z2) {
        this.L = z2;
        requestLayout();
    }

    public void setContentInsetEndWithActions(int i2) {
        if (i2 < 0) {
            i2 = Integer.MIN_VALUE;
        }
        if (i2 != this.f231w) {
            this.f231w = i2;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetStartWithNavigation(int i2) {
        if (i2 < 0) {
            i2 = Integer.MIN_VALUE;
        }
        if (i2 != this.f230v) {
            this.f230v = i2;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setLogo(int i2) {
        setLogo(e.b.c(getContext(), i2));
    }

    public void setLogoDescription(int i2) {
        setLogoDescription(getContext().getText(i2));
    }

    public void setNavigationContentDescription(int i2) {
        setNavigationContentDescription(i2 != 0 ? getContext().getText(i2) : null);
    }

    public void setNavigationIcon(int i2) {
        setNavigationIcon(e.b.c(getContext(), i2));
    }

    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        f();
        this.f213e.setOnClickListener(onClickListener);
    }

    public void setOnMenuItemClickListener(y2 y2Var) {
    }

    public void setOverflowIcon(Drawable drawable) {
        d();
        this.f210b.setOverflowIcon(drawable);
    }

    public void setPopupTheme(int i2) {
        if (this.f220l != i2) {
            this.f220l = i2;
            if (i2 == 0) {
                this.f219k = getContext();
            } else {
                this.f219k = new ContextThemeWrapper(getContext(), i2);
            }
        }
    }

    public void setSubtitle(int i2) {
        setSubtitle(getContext().getText(i2));
    }

    public void setSubtitleTextColor(int i2) {
        setSubtitleTextColor(ColorStateList.valueOf(i2));
    }

    public void setTitle(int i2) {
        setTitle(getContext().getText(i2));
    }

    public void setTitleMarginBottom(int i2) {
        this.f228t = i2;
        requestLayout();
    }

    public void setTitleMarginEnd(int i2) {
        this.f226r = i2;
        requestLayout();
    }

    public void setTitleMarginStart(int i2) {
        this.f225q = i2;
        requestLayout();
    }

    public void setTitleMarginTop(int i2) {
        this.f227s = i2;
        requestLayout();
    }

    public void setTitleTextColor(int i2) {
        setTitleTextColor(ColorStateList.valueOf(i2));
    }

    @Override // android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return g(layoutParams);
    }

    public void setCollapseContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            c();
        }
        a0 a0Var = this.f217i;
        if (a0Var != null) {
            a0Var.setContentDescription(charSequence);
        }
    }

    public void setCollapseIcon(Drawable drawable) {
        if (drawable != null) {
            c();
            this.f217i.setImageDrawable(drawable);
        } else {
            a0 a0Var = this.f217i;
            if (a0Var != null) {
                a0Var.setImageDrawable(this.f215g);
            }
        }
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            if (this.f214f == null) {
                this.f214f = new b0(getContext(), null, 0);
            }
            if (!k(this.f214f)) {
                b(this.f214f, true);
            }
        } else {
            b0 b0Var = this.f214f;
            if (b0Var != null && k(b0Var)) {
                removeView(this.f214f);
                this.F.remove(this.f214f);
            }
        }
        b0 b0Var2 = this.f214f;
        if (b0Var2 != null) {
            b0Var2.setImageDrawable(drawable);
        }
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence) && this.f214f == null) {
            this.f214f = new b0(getContext(), null, 0);
        }
        b0 b0Var = this.f214f;
        if (b0Var != null) {
            b0Var.setContentDescription(charSequence);
        }
    }

    public void setNavigationContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            f();
        }
        a0 a0Var = this.f213e;
        if (a0Var != null) {
            a0Var.setContentDescription(charSequence);
        }
    }

    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null) {
            f();
            if (!k(this.f213e)) {
                b(this.f213e, true);
            }
        } else {
            a0 a0Var = this.f213e;
            if (a0Var != null && k(a0Var)) {
                removeView(this.f213e);
                this.F.remove(this.f213e);
            }
        }
        a0 a0Var2 = this.f213e;
        if (a0Var2 != null) {
            a0Var2.setImageDrawable(drawable);
        }
    }

    public void setSubtitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            w0 w0Var = this.f212d;
            if (w0Var != null && k(w0Var)) {
                removeView(this.f212d);
                this.F.remove(this.f212d);
            }
        } else {
            if (this.f212d == null) {
                Context context = getContext();
                w0 w0Var2 = new w0(context, null);
                this.f212d = w0Var2;
                w0Var2.setSingleLine();
                this.f212d.setEllipsize(TextUtils.TruncateAt.END);
                int i2 = this.f222n;
                if (i2 != 0) {
                    this.f212d.setTextAppearance(context, i2);
                }
                ColorStateList colorStateList = this.B;
                if (colorStateList != null) {
                    this.f212d.setTextColor(colorStateList);
                }
            }
            if (!k(this.f212d)) {
                b(this.f212d, true);
            }
        }
        w0 w0Var3 = this.f212d;
        if (w0Var3 != null) {
            w0Var3.setText(charSequence);
        }
        this.f234z = charSequence;
    }

    public void setSubtitleTextColor(ColorStateList colorStateList) {
        this.B = colorStateList;
        w0 w0Var = this.f212d;
        if (w0Var != null) {
            w0Var.setTextColor(colorStateList);
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            w0 w0Var = this.f211c;
            if (w0Var != null && k(w0Var)) {
                removeView(this.f211c);
                this.F.remove(this.f211c);
            }
        } else {
            if (this.f211c == null) {
                Context context = getContext();
                w0 w0Var2 = new w0(context, null);
                this.f211c = w0Var2;
                w0Var2.setSingleLine();
                this.f211c.setEllipsize(TextUtils.TruncateAt.END);
                int i2 = this.f221m;
                if (i2 != 0) {
                    this.f211c.setTextAppearance(context, i2);
                }
                ColorStateList colorStateList = this.A;
                if (colorStateList != null) {
                    this.f211c.setTextColor(colorStateList);
                }
            }
            if (!k(this.f211c)) {
                b(this.f211c, true);
            }
        }
        w0 w0Var3 = this.f211c;
        if (w0Var3 != null) {
            w0Var3.setText(charSequence);
        }
        this.f233y = charSequence;
    }

    public void setTitleTextColor(ColorStateList colorStateList) {
        this.A = colorStateList;
        w0 w0Var = this.f211c;
        if (w0Var != null) {
            w0Var.setTextColor(colorStateList);
        }
    }
}
