package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import androidx.security.crypto.MasterKey;
import com.google.crypto.tink.shaded.protobuf.Reader;
import com.snapay.app.R;
import d.i0;
import d.r;
import i.o;
import j.a3;
import j.d;
import j.e;
import j.e1;
import j.f;
import j.f1;
import j.g;
import j.m;
import j.w2;
import java.util.WeakHashMap;
import x.b0;
import x.c0;
import x.d0;
import x.e0;
import x.h;
import x.i;
import x.j;
import x.k0;
import x.l0;
import x.q;
import x.u;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"UnknownNullness"})
public class ActionBarOverlayLayout extends ViewGroup implements e1, h, i {
    public static final int[] C = {R.attr.actionBarSize, android.R.attr.windowContentOverlay};
    public final e A;
    public final j B;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f141b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f142c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ContentFrameLayout f143d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ActionBarContainer f144e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public f1 f145f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Drawable f146g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f147h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f148i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f149j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f150k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f151l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f152m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f153n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final Rect f154o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Rect f155p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Rect f156q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public l0 f157r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public l0 f158s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public l0 f159t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public l0 f160u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public f f161v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public OverScroller f162w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public ViewPropertyAnimator f163x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final d f164y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final e f165z;

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f142c = 0;
        this.f154o = new Rect();
        this.f155p = new Rect();
        this.f156q = new Rect();
        new Rect();
        new Rect();
        new Rect();
        new Rect();
        l0 l0Var = l0.f1999b;
        this.f157r = l0Var;
        this.f158s = l0Var;
        this.f159t = l0Var;
        this.f160u = l0Var;
        this.f164y = new d(this);
        this.f165z = new e(this, 0);
        this.A = new e(this, 1);
        j(context);
        this.B = new j();
    }

    public static boolean g(FrameLayout frameLayout, Rect rect, boolean z2) {
        boolean z3;
        g gVar = (g) frameLayout.getLayoutParams();
        int i2 = ((ViewGroup.MarginLayoutParams) gVar).leftMargin;
        int i3 = rect.left;
        if (i2 != i3) {
            ((ViewGroup.MarginLayoutParams) gVar).leftMargin = i3;
            z3 = true;
        } else {
            z3 = false;
        }
        int i4 = ((ViewGroup.MarginLayoutParams) gVar).topMargin;
        int i5 = rect.top;
        if (i4 != i5) {
            ((ViewGroup.MarginLayoutParams) gVar).topMargin = i5;
            z3 = true;
        }
        int i6 = ((ViewGroup.MarginLayoutParams) gVar).rightMargin;
        int i7 = rect.right;
        if (i6 != i7) {
            ((ViewGroup.MarginLayoutParams) gVar).rightMargin = i7;
            z3 = true;
        }
        if (z2) {
            int i8 = ((ViewGroup.MarginLayoutParams) gVar).bottomMargin;
            int i9 = rect.bottom;
            if (i8 != i9) {
                ((ViewGroup.MarginLayoutParams) gVar).bottomMargin = i9;
                return true;
            }
        }
        return z3;
    }

    @Override // x.h
    public final void a(View view, View view2, int i2, int i3) {
        if (i3 == 0) {
            onNestedScrollAccepted(view, view2, i2);
        }
    }

    @Override // x.h
    public final void b(View view, int i2) {
        if (i2 == 0) {
            onStopNestedScroll(view);
        }
    }

    @Override // x.h
    public final void c(View view, int i2, int i3, int[] iArr, int i4) {
        if (i4 == 0) {
            onNestedPreScroll(view, i2, i3, iArr);
        }
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof g;
    }

    @Override // x.i
    public final void d(View view, int i2, int i3, int i4, int i5, int i6, int[] iArr) {
        e(view, i2, i3, i4, i5, i6);
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        int translationY;
        super.draw(canvas);
        if (this.f146g == null || this.f147h) {
            return;
        }
        if (this.f144e.getVisibility() == 0) {
            translationY = (int) (this.f144e.getTranslationY() + this.f144e.getBottom() + 0.5f);
        } else {
            translationY = 0;
        }
        this.f146g.setBounds(0, translationY, getWidth(), this.f146g.getIntrinsicHeight() + translationY);
        this.f146g.draw(canvas);
    }

    @Override // x.h
    public final void e(View view, int i2, int i3, int i4, int i5, int i6) {
        if (i6 == 0) {
            onNestedScroll(view, i2, i3, i4, i5);
        }
    }

    @Override // x.h
    public final boolean f(View view, View view2, int i2, int i3) {
        return i3 == 0 && onStartNestedScroll(view, view2, i2);
    }

    @Override // android.view.View
    public final boolean fitSystemWindows(Rect rect) {
        return super.fitSystemWindows(rect);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new g();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new g(getContext(), attributeSet);
    }

    public int getActionBarHideOffset() {
        ActionBarContainer actionBarContainer = this.f144e;
        if (actionBarContainer != null) {
            return -((int) actionBarContainer.getTranslationY());
        }
        return 0;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        j jVar = this.B;
        return jVar.f1995b | jVar.f1994a;
    }

    public CharSequence getTitle() {
        l();
        return ((a3) this.f145f).f1157a.getTitle();
    }

    public final void h() {
        removeCallbacks(this.f165z);
        removeCallbacks(this.A);
        ViewPropertyAnimator viewPropertyAnimator = this.f163x;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
    }

    public final boolean i() {
        l();
        ActionMenuView actionMenuView = ((a3) this.f145f).f1157a.f210b;
        if (actionMenuView == null) {
            return false;
        }
        m mVar = actionMenuView.f170u;
        return mVar != null && mVar.f();
    }

    public final void j(Context context) {
        TypedArray typedArrayObtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(C);
        this.f141b = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(1);
        this.f146g = drawable;
        setWillNotDraw(drawable == null);
        typedArrayObtainStyledAttributes.recycle();
        this.f147h = context.getApplicationInfo().targetSdkVersion < 19;
        this.f162w = new OverScroller(context);
    }

    public final void k(int i2) {
        l();
        if (i2 == 2) {
            ((a3) this.f145f).getClass();
            Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
        } else if (i2 == 5) {
            ((a3) this.f145f).getClass();
            Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
        } else {
            if (i2 != 109) {
                return;
            }
            setOverlayMode(true);
        }
    }

    public final void l() {
        f1 wrapper;
        if (this.f143d == null) {
            this.f143d = (ContentFrameLayout) findViewById(R.id.action_bar_activity_content);
            this.f144e = (ActionBarContainer) findViewById(R.id.action_bar_container);
            KeyEvent.Callback callbackFindViewById = findViewById(R.id.action_bar);
            if (callbackFindViewById instanceof f1) {
                wrapper = (f1) callbackFindViewById;
            } else {
                if (!(callbackFindViewById instanceof Toolbar)) {
                    throw new IllegalStateException("Can't make a decor toolbar out of ".concat(callbackFindViewById.getClass().getSimpleName()));
                }
                wrapper = ((Toolbar) callbackFindViewById).getWrapper();
            }
            this.f145f = wrapper;
        }
    }

    public final void m(o oVar, r rVar) {
        l();
        a3 a3Var = (a3) this.f145f;
        m mVar = a3Var.f1169m;
        Toolbar toolbar = a3Var.f1157a;
        if (mVar == null) {
            a3Var.f1169m = new m(toolbar.getContext());
        }
        m mVar2 = a3Var.f1169m;
        mVar2.f1298f = rVar;
        if (oVar == null && toolbar.f210b == null) {
            return;
        }
        toolbar.e();
        o oVar2 = toolbar.f210b.f166q;
        if (oVar2 == oVar) {
            return;
        }
        if (oVar2 != null) {
            oVar2.r(toolbar.J);
            oVar2.r(toolbar.K);
        }
        if (toolbar.K == null) {
            toolbar.K = new w2(toolbar);
        }
        mVar2.f1310r = true;
        if (oVar != null) {
            oVar.b(mVar2, toolbar.f219k);
            oVar.b(toolbar.K, toolbar.f219k);
        } else {
            mVar2.e(toolbar.f219k, null);
            toolbar.K.e(toolbar.f219k, null);
            mVar2.i();
            toolbar.K.i();
        }
        toolbar.f210b.setPopupTheme(toolbar.f220l);
        toolbar.f210b.setPresenter(mVar2);
        toolbar.J = mVar2;
    }

    @Override // android.view.View
    public final WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        l();
        l0 l0VarC = l0.c(windowInsets, this);
        k0 k0Var = l0VarC.f2000a;
        boolean zG = g(this.f144e, new Rect(k0Var.g().f1890a, k0Var.g().f1891b, k0Var.g().f1892c, k0Var.g().f1893d), false);
        WeakHashMap weakHashMap = u.f2012a;
        Rect rect = this.f154o;
        q.b(this, l0VarC, rect);
        l0 l0VarH = k0Var.h(rect.left, rect.top, rect.right, rect.bottom);
        this.f157r = l0VarH;
        boolean z2 = true;
        if (!this.f158s.equals(l0VarH)) {
            this.f158s = this.f157r;
            zG = true;
        }
        Rect rect2 = this.f155p;
        if (rect2.equals(rect)) {
            z2 = zG;
        } else {
            rect2.set(rect);
        }
        if (z2) {
            requestLayout();
        }
        return k0Var.a().f2000a.c().f2000a.b().b();
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        j(getContext());
        WeakHashMap weakHashMap = u.f2012a;
        requestApplyInsets();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        h();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                g gVar = (g) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i7 = ((ViewGroup.MarginLayoutParams) gVar).leftMargin + paddingLeft;
                int i8 = ((ViewGroup.MarginLayoutParams) gVar).topMargin + paddingTop;
                childAt.layout(i7, i8, measuredWidth + i7, measuredHeight + i8);
            }
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i2, int i3) {
        int measuredHeight;
        e0 c0Var;
        l0 l0VarB;
        l();
        measureChildWithMargins(this.f144e, i2, 0, i3, 0);
        g gVar = (g) this.f144e.getLayoutParams();
        int iMax = Math.max(0, this.f144e.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) gVar).leftMargin + ((ViewGroup.MarginLayoutParams) gVar).rightMargin);
        int iMax2 = Math.max(0, this.f144e.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) gVar).topMargin + ((ViewGroup.MarginLayoutParams) gVar).bottomMargin);
        int iCombineMeasuredStates = View.combineMeasuredStates(0, this.f144e.getMeasuredState());
        WeakHashMap weakHashMap = u.f2012a;
        boolean z2 = (getWindowSystemUiVisibility() & MasterKey.DEFAULT_AES_GCM_MASTER_KEY_SIZE) != 0;
        if (z2) {
            measuredHeight = this.f141b;
            if (this.f149j && this.f144e.getTabContainer() != null) {
                measuredHeight += this.f141b;
            }
        } else {
            measuredHeight = this.f144e.getVisibility() != 8 ? this.f144e.getMeasuredHeight() : 0;
        }
        Rect rect = this.f154o;
        Rect rect2 = this.f156q;
        rect2.set(rect);
        l0 l0Var = this.f157r;
        this.f159t = l0Var;
        if (this.f148i || z2) {
            r.b bVarA = r.b.a(l0Var.f2000a.g().f1890a, this.f159t.f2000a.g().f1891b + measuredHeight, this.f159t.f2000a.g().f1892c, this.f159t.f2000a.g().f1893d + 0);
            l0 l0Var2 = this.f159t;
            int i4 = Build.VERSION.SDK_INT;
            if (i4 >= 30) {
                c0Var = new d0(l0Var2);
            } else {
                c0Var = i4 >= 29 ? new c0(l0Var2) : new b0(l0Var2);
            }
            c0Var.d(bVarA);
            l0VarB = c0Var.b();
        } else {
            rect2.top += measuredHeight;
            rect2.bottom += 0;
            l0VarB = l0Var.f2000a.h(0, measuredHeight, 0, 0);
        }
        this.f159t = l0VarB;
        g(this.f143d, rect2, true);
        if (!this.f160u.equals(this.f159t)) {
            l0 l0Var3 = this.f159t;
            this.f160u = l0Var3;
            ContentFrameLayout contentFrameLayout = this.f143d;
            WindowInsets windowInsetsB = l0Var3.b();
            if (windowInsetsB != null) {
                WindowInsets windowInsetsDispatchApplyWindowInsets = contentFrameLayout.dispatchApplyWindowInsets(windowInsetsB);
                if (!windowInsetsDispatchApplyWindowInsets.equals(windowInsetsB)) {
                    l0.c(windowInsetsDispatchApplyWindowInsets, contentFrameLayout);
                }
            }
        }
        measureChildWithMargins(this.f143d, i2, 0, i3, 0);
        g gVar2 = (g) this.f143d.getLayoutParams();
        int iMax3 = Math.max(iMax, this.f143d.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) gVar2).leftMargin + ((ViewGroup.MarginLayoutParams) gVar2).rightMargin);
        int iMax4 = Math.max(iMax2, this.f143d.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) gVar2).topMargin + ((ViewGroup.MarginLayoutParams) gVar2).bottomMargin);
        int iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.f143d.getMeasuredState());
        setMeasuredDimension(View.resolveSizeAndState(Math.max(getPaddingRight() + getPaddingLeft() + iMax3, getSuggestedMinimumWidth()), i2, iCombineMeasuredStates2), View.resolveSizeAndState(Math.max(getPaddingBottom() + getPaddingTop() + iMax4, getSuggestedMinimumHeight()), i3, iCombineMeasuredStates2 << 16));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedFling(View view, float f2, float f3, boolean z2) {
        if (!this.f150k || !z2) {
            return false;
        }
        this.f162w.fling(0, 0, 0, (int) f3, 0, 0, Integer.MIN_VALUE, Reader.READ_DONE);
        if (this.f162w.getFinalY() > this.f144e.getHeight()) {
            h();
            this.A.run();
        } else {
            h();
            this.f165z.run();
        }
        this.f151l = true;
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedPreFling(View view, float f2, float f3) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedPreScroll(View view, int i2, int i3, int[] iArr) {
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScroll(View view, int i2, int i3, int i4, int i5) {
        int i6 = this.f152m + i3;
        this.f152m = i6;
        setActionBarHideOffset(i6);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScrollAccepted(View view, View view2, int i2) {
        i0 i0Var;
        h.m mVar;
        this.B.f1994a = i2;
        this.f152m = getActionBarHideOffset();
        h();
        f fVar = this.f161v;
        if (fVar == null || (mVar = (i0Var = (i0) fVar).f698s) == null) {
            return;
        }
        mVar.a();
        i0Var.f698s = null;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onStartNestedScroll(View view, View view2, int i2) {
        if ((i2 & 2) == 0 || this.f144e.getVisibility() != 0) {
            return false;
        }
        return this.f150k;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onStopNestedScroll(View view) {
        if (!this.f150k || this.f151l) {
            return;
        }
        if (this.f152m <= this.f144e.getHeight()) {
            h();
            postDelayed(this.f165z, 600L);
        } else {
            h();
            postDelayed(this.A, 600L);
        }
    }

    @Override // android.view.View
    public final void onWindowSystemUiVisibilityChanged(int i2) {
        super.onWindowSystemUiVisibilityChanged(i2);
        l();
        int i3 = this.f153n ^ i2;
        this.f153n = i2;
        boolean z2 = (i2 & 4) == 0;
        boolean z3 = (i2 & MasterKey.DEFAULT_AES_GCM_MASTER_KEY_SIZE) != 0;
        f fVar = this.f161v;
        if (fVar != null) {
            ((i0) fVar).f694o = !z3;
            if (z2 || !z3) {
                i0 i0Var = (i0) fVar;
                if (i0Var.f695p) {
                    i0Var.f695p = false;
                    i0Var.o(true);
                }
            } else {
                i0 i0Var2 = (i0) fVar;
                if (!i0Var2.f695p) {
                    i0Var2.f695p = true;
                    i0Var2.o(true);
                }
            }
        }
        if ((i3 & MasterKey.DEFAULT_AES_GCM_MASTER_KEY_SIZE) == 0 || this.f161v == null) {
            return;
        }
        WeakHashMap weakHashMap = u.f2012a;
        requestApplyInsets();
    }

    @Override // android.view.View
    public final void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
        this.f142c = i2;
        f fVar = this.f161v;
        if (fVar != null) {
            ((i0) fVar).f693n = i2;
        }
    }

    public void setActionBarHideOffset(int i2) {
        h();
        this.f144e.setTranslationY(-Math.max(0, Math.min(i2, this.f144e.getHeight())));
    }

    public void setActionBarVisibilityCallback(f fVar) {
        this.f161v = fVar;
        if (getWindowToken() != null) {
            ((i0) this.f161v).f693n = this.f142c;
            int i2 = this.f153n;
            if (i2 != 0) {
                onWindowSystemUiVisibilityChanged(i2);
                WeakHashMap weakHashMap = u.f2012a;
                requestApplyInsets();
            }
        }
    }

    public void setHasNonEmbeddedTabs(boolean z2) {
        this.f149j = z2;
    }

    public void setHideOnContentScrollEnabled(boolean z2) {
        if (z2 != this.f150k) {
            this.f150k = z2;
            if (z2) {
                return;
            }
            h();
            setActionBarHideOffset(0);
        }
    }

    public void setIcon(int i2) {
        l();
        a3 a3Var = (a3) this.f145f;
        a3Var.f1160d = i2 != 0 ? e.b.c(a3Var.f1157a.getContext(), i2) : null;
        a3Var.b();
    }

    public void setLogo(int i2) {
        l();
        a3 a3Var = (a3) this.f145f;
        a3Var.f1161e = i2 != 0 ? e.b.c(a3Var.f1157a.getContext(), i2) : null;
        a3Var.b();
    }

    public void setOverlayMode(boolean z2) {
        this.f148i = z2;
        this.f147h = z2 && getContext().getApplicationInfo().targetSdkVersion < 19;
    }

    public void setShowingForActionMode(boolean z2) {
    }

    public void setUiOptions(int i2) {
    }

    @Override // j.e1
    public void setWindowCallback(Window.Callback callback) {
        l();
        ((a3) this.f145f).f1167k = callback;
    }

    @Override // j.e1
    public void setWindowTitle(CharSequence charSequence) {
        l();
        a3 a3Var = (a3) this.f145f;
        if (a3Var.f1163g) {
            return;
        }
        a3Var.f1164h = charSequence;
        if ((a3Var.f1158b & 8) != 0) {
            a3Var.f1157a.setTitle(charSequence);
        }
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new g(layoutParams);
    }

    public void setIcon(Drawable drawable) {
        l();
        a3 a3Var = (a3) this.f145f;
        a3Var.f1160d = drawable;
        a3Var.b();
    }
}
