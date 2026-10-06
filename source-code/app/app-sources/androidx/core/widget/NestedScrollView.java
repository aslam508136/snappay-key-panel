package androidx.core.widget;

import a0.e;
import a0.f;
import a0.g;
import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AnimationUtils;
import android.widget.EdgeEffect;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import com.google.crypto.tink.shaded.protobuf.Reader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.WeakHashMap;
import x.a;
import x.b;
import x.h;
import x.i;
import x.j;
import x.u;

/* JADX INFO: loaded from: classes.dex */
public class NestedScrollView extends FrameLayout implements i {
    public static final e B = new e();
    public static final int[] C = {R.attr.fillViewport};
    public f A;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f263b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Rect f264c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public OverScroller f265d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public EdgeEffect f266e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public EdgeEffect f267f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f268g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f269h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f270i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public View f271j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f272k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public VelocityTracker f273l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f274m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f275n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f276o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f277p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f278q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f279r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final int[] f280s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final int[] f281t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f282u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f283v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public g f284w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final j f285x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final x.g f286y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public float f287z;

    /* JADX WARN: Code duplicated, block: B:15:0x00a5 A[Catch: all -> 0x00a8, TRY_LEAVE, TryCatch #0 {all -> 0x00a8, blocks: (B:12:0x008e, B:13:0x009b, B:15:0x00a5), top: B:27:0x008e }] */
    /* JADX WARN: Code duplicated, block: B:18:0x00aa  */
    public NestedScrollView(Context context, AttributeSet attributeSet) {
        View.AccessibilityDelegate accessibilityDelegate;
        Object obj;
        super(context, attributeSet, 0);
        this.f264c = new Rect();
        this.f269h = true;
        this.f270i = false;
        this.f271j = null;
        this.f272k = false;
        this.f275n = true;
        this.f279r = -1;
        this.f280s = new int[2];
        this.f281t = new int[2];
        this.f265d = new OverScroller(getContext());
        setFocusable(true);
        setDescendantFocusability(262144);
        setWillNotDraw(false);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.f276o = viewConfiguration.getScaledTouchSlop();
        this.f277p = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f278q = viewConfiguration.getScaledMaximumFlingVelocity();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C, 0, 0);
        setFillViewport(typedArrayObtainStyledAttributes.getBoolean(0, false));
        typedArrayObtainStyledAttributes.recycle();
        this.f285x = new j();
        this.f286y = new x.g(this);
        setNestedScrollingEnabled(true);
        WeakHashMap weakHashMap = u.f2012a;
        b bVar = B;
        if (bVar == null) {
            if (Build.VERSION.SDK_INT >= 29) {
                accessibilityDelegate = getAccessibilityDelegate();
            } else if (u.f2014c) {
                accessibilityDelegate = null;
            } else if (u.f2013b == null) {
                try {
                    Field declaredField = View.class.getDeclaredField("mAccessibilityDelegate");
                    u.f2013b = declaredField;
                    declaredField.setAccessible(true);
                    obj = u.f2013b.get(this);
                    if (obj instanceof View.AccessibilityDelegate) {
                        accessibilityDelegate = (View.AccessibilityDelegate) obj;
                    } else {
                        accessibilityDelegate = null;
                    }
                } catch (Throwable unused) {
                    u.f2014c = true;
                }
            } else {
                obj = u.f2013b.get(this);
                if (obj instanceof View.AccessibilityDelegate) {
                    accessibilityDelegate = (View.AccessibilityDelegate) obj;
                } else {
                    accessibilityDelegate = null;
                }
            }
            if (accessibilityDelegate instanceof a) {
                bVar = new b();
            }
        }
        setAccessibilityDelegate(bVar != null ? bVar.f1964b : null);
    }

    private float getVerticalScrollFactorCompat() {
        if (this.f287z == 0.0f) {
            TypedValue typedValue = new TypedValue();
            Context context = getContext();
            if (!context.getTheme().resolveAttribute(R.attr.listPreferredItemHeight, typedValue, true)) {
                throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
            }
            this.f287z = typedValue.getDimension(context.getResources().getDisplayMetrics());
        }
        return this.f287z;
    }

    public static boolean p(View view, View view2) {
        if (view == view2) {
            return true;
        }
        Object parent = view.getParent();
        return (parent instanceof ViewGroup) && p((View) parent, view2);
    }

    @Override // x.h
    public final void a(View view, View view2, int i2, int i3) {
        j jVar = this.f285x;
        if (i3 == 1) {
            jVar.f1995b = i2;
        } else {
            jVar.f1994a = i2;
        }
        w(2, i3);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view);
    }

    @Override // x.h
    public final void b(View view, int i2) {
        j jVar = this.f285x;
        if (i2 == 1) {
            jVar.f1995b = 0;
        } else {
            jVar.f1994a = 0;
        }
        x(i2);
    }

    @Override // x.h
    public final void c(View view, int i2, int i3, int[] iArr, int i4) {
        i(i2, i3, iArr, null, i4);
    }

    @Override // android.view.View
    public final int computeHorizontalScrollExtent() {
        return super.computeHorizontalScrollExtent();
    }

    @Override // android.view.View
    public final int computeHorizontalScrollOffset() {
        return super.computeHorizontalScrollOffset();
    }

    @Override // android.view.View
    public final int computeHorizontalScrollRange() {
        return super.computeHorizontalScrollRange();
    }

    @Override // android.view.View
    public final void computeScroll() {
        EdgeEffect edgeEffect;
        if (this.f265d.isFinished()) {
            return;
        }
        this.f265d.computeScrollOffset();
        int currY = this.f265d.getCurrY();
        int i2 = currY - this.f283v;
        this.f283v = currY;
        int[] iArr = this.f281t;
        boolean z2 = false;
        iArr[1] = 0;
        i(0, i2, iArr, null, 1);
        int i3 = i2 - iArr[1];
        int scrollRange = getScrollRange();
        if (i3 != 0) {
            int scrollY = getScrollY();
            t(i3, getScrollX(), scrollY, scrollRange);
            int scrollY2 = getScrollY() - scrollY;
            int i4 = i3 - scrollY2;
            iArr[1] = 0;
            this.f286y.a(0, scrollY2, 0, i4, this.f280s, 1, iArr);
            i3 = i4 - iArr[1];
        }
        if (i3 != 0) {
            int overScrollMode = getOverScrollMode();
            if (overScrollMode == 0 || (overScrollMode == 1 && scrollRange > 0)) {
                z2 = true;
            }
            if (z2) {
                l();
                if (i3 < 0) {
                    if (this.f266e.isFinished()) {
                        edgeEffect = this.f266e;
                        edgeEffect.onAbsorb((int) this.f265d.getCurrVelocity());
                    }
                } else if (this.f267f.isFinished()) {
                    edgeEffect = this.f267f;
                    edgeEffect.onAbsorb((int) this.f265d.getCurrVelocity());
                }
            }
            this.f265d.abortAnimation();
            x(1);
        }
        if (this.f265d.isFinished()) {
            x(1);
        } else {
            WeakHashMap weakHashMap = u.f2012a;
            postInvalidateOnAnimation();
        }
    }

    @Override // android.view.View
    public final int computeVerticalScrollExtent() {
        return super.computeVerticalScrollExtent();
    }

    @Override // android.view.View
    public final int computeVerticalScrollOffset() {
        return Math.max(0, super.computeVerticalScrollOffset());
    }

    @Override // android.view.View
    public final int computeVerticalScrollRange() {
        int childCount = getChildCount();
        int height = (getHeight() - getPaddingBottom()) - getPaddingTop();
        if (childCount == 0) {
            return height;
        }
        View childAt = getChildAt(0);
        int bottom = childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin;
        int scrollY = getScrollY();
        int iMax = Math.max(0, bottom - height);
        if (scrollY < 0) {
            return bottom - scrollY;
        }
        return scrollY > iMax ? bottom + (scrollY - iMax) : bottom;
    }

    @Override // x.i
    public final void d(View view, int i2, int i3, int i4, int i5, int i6, int[] iArr) {
        r(i5, i6, iArr);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || m(keyEvent);
    }

    @Override // android.view.View
    public final boolean dispatchNestedFling(float f2, float f3, boolean z2) {
        ViewParent viewParentB;
        x.g gVar = this.f286y;
        if (!gVar.f1991d || (viewParentB = gVar.b(0)) == null) {
            return false;
        }
        return androidx.lifecycle.i.Q(viewParentB, gVar.f1990c, f2, f3, z2);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreFling(float f2, float f3) {
        ViewParent viewParentB;
        x.g gVar = this.f286y;
        if (!gVar.f1991d || (viewParentB = gVar.b(0)) == null) {
            return false;
        }
        return androidx.lifecycle.i.R(viewParentB, gVar.f1990c, f2, f3);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreScroll(int i2, int i3, int[] iArr, int[] iArr2) {
        return i(i2, i3, iArr, iArr2, 0);
    }

    @Override // android.view.View
    public final boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, int[] iArr) {
        return this.f286y.a(i2, i3, i4, i5, iArr, 0, null);
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        int paddingLeft;
        super.draw(canvas);
        if (this.f266e != null) {
            int scrollY = getScrollY();
            int paddingLeft2 = 0;
            if (!this.f266e.isFinished()) {
                int iSave = canvas.save();
                int width = getWidth();
                int height = getHeight();
                int iMin = Math.min(0, scrollY);
                if (getClipToPadding()) {
                    width -= getPaddingRight() + getPaddingLeft();
                    paddingLeft = getPaddingLeft() + 0;
                } else {
                    paddingLeft = 0;
                }
                if (getClipToPadding()) {
                    height -= getPaddingBottom() + getPaddingTop();
                    iMin += getPaddingTop();
                }
                canvas.translate(paddingLeft, iMin);
                this.f266e.setSize(width, height);
                if (this.f266e.draw(canvas)) {
                    WeakHashMap weakHashMap = u.f2012a;
                    postInvalidateOnAnimation();
                }
                canvas.restoreToCount(iSave);
            }
            if (this.f267f.isFinished()) {
                return;
            }
            int iSave2 = canvas.save();
            int width2 = getWidth();
            int height2 = getHeight();
            int iMax = Math.max(getScrollRange(), scrollY) + height2;
            if (getClipToPadding()) {
                width2 -= getPaddingRight() + getPaddingLeft();
                paddingLeft2 = 0 + getPaddingLeft();
            }
            if (getClipToPadding()) {
                height2 -= getPaddingBottom() + getPaddingTop();
                iMax -= getPaddingBottom();
            }
            canvas.translate(paddingLeft2 - width2, iMax);
            canvas.rotate(180.0f, width2, 0.0f);
            this.f267f.setSize(width2, height2);
            if (this.f267f.draw(canvas)) {
                WeakHashMap weakHashMap2 = u.f2012a;
                postInvalidateOnAnimation();
            }
            canvas.restoreToCount(iSave2);
        }
    }

    @Override // x.h
    public final void e(View view, int i2, int i3, int i4, int i5, int i6) {
        r(i5, i6, null);
    }

    @Override // x.h
    public final boolean f(View view, View view2, int i2, int i3) {
        return (i2 & 2) != 0;
    }

    public final boolean g(int i2) {
        View viewFindFocus = findFocus();
        if (viewFindFocus == this) {
            viewFindFocus = null;
        }
        View viewFindNextFocus = FocusFinder.getInstance().findNextFocus(this, viewFindFocus, i2);
        int maxScrollAmount = getMaxScrollAmount();
        if (viewFindNextFocus == null || !q(viewFindNextFocus, maxScrollAmount, getHeight())) {
            if (i2 == 33 && getScrollY() < maxScrollAmount) {
                maxScrollAmount = getScrollY();
            } else if (i2 == 130 && getChildCount() > 0) {
                View childAt = getChildAt(0);
                maxScrollAmount = Math.min((childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin) - ((getHeight() + getScrollY()) - getPaddingBottom()), maxScrollAmount);
            }
            if (maxScrollAmount == 0) {
                return false;
            }
            if (i2 != 130) {
                maxScrollAmount = -maxScrollAmount;
            }
            j(maxScrollAmount);
        } else {
            Rect rect = this.f264c;
            viewFindNextFocus.getDrawingRect(rect);
            offsetDescendantRectToMyCoords(viewFindNextFocus, rect);
            j(h(rect));
            viewFindNextFocus.requestFocus(i2);
        }
        if (viewFindFocus != null && viewFindFocus.isFocused() && (!q(viewFindFocus, 0, getHeight()))) {
            int descendantFocusability = getDescendantFocusability();
            setDescendantFocusability(131072);
            requestFocus();
            setDescendantFocusability(descendantFocusability);
        }
        return true;
    }

    @Override // android.view.View
    public float getBottomFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int bottom = ((childAt.getBottom() + layoutParams.bottomMargin) - getScrollY()) - (getHeight() - getPaddingBottom());
        if (bottom < verticalFadingEdgeLength) {
            return bottom / verticalFadingEdgeLength;
        }
        return 1.0f;
    }

    public int getMaxScrollAmount() {
        return (int) (getHeight() * 0.5f);
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        j jVar = this.f285x;
        return jVar.f1995b | jVar.f1994a;
    }

    public int getScrollRange() {
        if (getChildCount() <= 0) {
            return 0;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        return Math.max(0, ((childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin) - ((getHeight() - getPaddingTop()) - getPaddingBottom()));
    }

    @Override // android.view.View
    public float getTopFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int scrollY = getScrollY();
        if (scrollY < verticalFadingEdgeLength) {
            return scrollY / verticalFadingEdgeLength;
        }
        return 1.0f;
    }

    public final int h(Rect rect) {
        if (getChildCount() == 0) {
            return 0;
        }
        int height = getHeight();
        int scrollY = getScrollY();
        int i2 = scrollY + height;
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        if (rect.top > 0) {
            scrollY += verticalFadingEdgeLength;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        int i3 = rect.bottom < (childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin ? i2 - verticalFadingEdgeLength : i2;
        int i4 = rect.bottom;
        if (i4 > i3 && rect.top > scrollY) {
            return Math.min((rect.height() > height ? rect.top - scrollY : rect.bottom - i3) + 0, (childAt.getBottom() + layoutParams.bottomMargin) - i2);
        }
        if (rect.top >= scrollY || i4 >= i3) {
            return 0;
        }
        return Math.max(rect.height() > height ? 0 - (i3 - rect.bottom) : 0 - (scrollY - rect.top), -getScrollY());
    }

    @Override // android.view.View
    public final boolean hasNestedScrollingParent() {
        return this.f286y.b(0) != null;
    }

    public final boolean i(int i2, int i3, int[] iArr, int[] iArr2, int i4) {
        ViewParent viewParentB;
        int i5;
        int i6;
        int[] iArr3;
        x.g gVar = this.f286y;
        if (!gVar.f1991d || (viewParentB = gVar.b(i4)) == null) {
            return false;
        }
        if (i2 == 0 && i3 == 0) {
            if (iArr2 == null) {
                return false;
            }
            iArr2[0] = 0;
            iArr2[1] = 0;
            return false;
        }
        View view = gVar.f1990c;
        if (iArr2 != null) {
            view.getLocationInWindow(iArr2);
            i5 = iArr2[0];
            i6 = iArr2[1];
        } else {
            i5 = 0;
            i6 = 0;
        }
        if (iArr == null) {
            if (gVar.f1992e == null) {
                gVar.f1992e = new int[2];
            }
            iArr3 = gVar.f1992e;
        } else {
            iArr3 = iArr;
        }
        iArr3[0] = 0;
        iArr3[1] = 0;
        androidx.lifecycle.i.S(viewParentB, gVar.f1990c, i2, i3, iArr3, i4);
        if (iArr2 != null) {
            view.getLocationInWindow(iArr2);
            iArr2[0] = iArr2[0] - i5;
            iArr2[1] = iArr2[1] - i6;
        }
        return (iArr3[0] == 0 && iArr3[1] == 0) ? false : true;
    }

    @Override // android.view.View
    public final boolean isNestedScrollingEnabled() {
        return this.f286y.f1991d;
    }

    public final void j(int i2) {
        if (i2 != 0) {
            if (this.f275n) {
                v(0, i2, false);
            } else {
                scrollBy(0, i2);
            }
        }
    }

    public final void k() {
        this.f272k = false;
        VelocityTracker velocityTracker = this.f273l;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.f273l = null;
        }
        x(0);
        EdgeEffect edgeEffect = this.f266e;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            this.f267f.onRelease();
        }
    }

    public final void l() {
        if (getOverScrollMode() == 2) {
            this.f266e = null;
            this.f267f = null;
        } else if (this.f266e == null) {
            Context context = getContext();
            this.f266e = new EdgeEffect(context);
            this.f267f = new EdgeEffect(context);
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0033  */
    public final boolean m(KeyEvent keyEvent) {
        boolean z2;
        Rect rect = this.f264c;
        rect.setEmpty();
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            if (childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin > (getHeight() - getPaddingTop()) - getPaddingBottom()) {
                z2 = true;
            } else {
                z2 = false;
            }
        } else {
            z2 = false;
        }
        if (!z2) {
            if (!isFocused() || keyEvent.getKeyCode() == 4) {
                return false;
            }
            View viewFindFocus = findFocus();
            if (viewFindFocus == this) {
                viewFindFocus = null;
            }
            View viewFindNextFocus = FocusFinder.getInstance().findNextFocus(this, viewFindFocus, 130);
            return (viewFindNextFocus == null || viewFindNextFocus == this || !viewFindNextFocus.requestFocus(130)) ? false : true;
        }
        if (keyEvent.getAction() != 0) {
            return false;
        }
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 19) {
            return !keyEvent.isAltPressed() ? g(33) : o(33);
        }
        if (keyCode == 20) {
            return !keyEvent.isAltPressed() ? g(130) : o(130);
        }
        if (keyCode != 62) {
            return false;
        }
        int i2 = keyEvent.isShiftPressed() ? 33 : 130;
        boolean z3 = i2 == 130;
        int height = getHeight();
        if (z3) {
            rect.top = getScrollY() + height;
            int childCount = getChildCount();
            if (childCount > 0) {
                View childAt2 = getChildAt(childCount - 1);
                int paddingBottom = getPaddingBottom() + childAt2.getBottom() + ((FrameLayout.LayoutParams) childAt2.getLayoutParams()).bottomMargin;
                if (rect.top + height > paddingBottom) {
                    rect.top = paddingBottom - height;
                }
            }
        } else {
            int scrollY = getScrollY() - height;
            rect.top = scrollY;
            if (scrollY < 0) {
                rect.top = 0;
            }
        }
        int i3 = rect.top;
        int i4 = height + i3;
        rect.bottom = i4;
        u(i2, i3, i4);
        return false;
    }

    @Override // android.view.ViewGroup
    public final void measureChild(View view, int i2, int i3) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        view.measure(ViewGroup.getChildMeasureSpec(i2, getPaddingRight() + getPaddingLeft(), layoutParams.width), View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    @Override // android.view.ViewGroup
    public final void measureChildWithMargins(View view, int i2, int i3, int i4, int i5) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(ViewGroup.getChildMeasureSpec(i2, getPaddingRight() + getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i3, marginLayoutParams.width), View.MeasureSpec.makeMeasureSpec(marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, 0));
    }

    public final void n(int i2) {
        if (getChildCount() > 0) {
            this.f265d.fling(getScrollX(), getScrollY(), 0, i2, 0, 0, Integer.MIN_VALUE, Reader.READ_DONE, 0, 0);
            w(2, 1);
            this.f283v = getScrollY();
            WeakHashMap weakHashMap = u.f2012a;
            postInvalidateOnAnimation();
        }
    }

    public final boolean o(int i2) {
        int childCount;
        boolean z2 = i2 == 130;
        int height = getHeight();
        Rect rect = this.f264c;
        rect.top = 0;
        rect.bottom = height;
        if (z2 && (childCount = getChildCount()) > 0) {
            View childAt = getChildAt(childCount - 1);
            rect.bottom = getPaddingBottom() + childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin;
            rect.top = rect.bottom - height;
        }
        return u(i2, rect.top, rect.bottom);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f270i = false;
    }

    @Override // android.view.View
    public final boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if ((motionEvent.getSource() & 2) != 0 && motionEvent.getAction() == 8 && !this.f272k) {
            float axisValue = motionEvent.getAxisValue(9);
            if (axisValue != 0.0f) {
                int verticalScrollFactorCompat = (int) (axisValue * getVerticalScrollFactorCompat());
                int scrollRange = getScrollRange();
                int scrollY = getScrollY();
                int i2 = scrollY - verticalScrollFactorCompat;
                if (i2 < 0) {
                    scrollRange = 0;
                } else if (i2 <= scrollRange) {
                    scrollRange = i2;
                }
                if (scrollRange != scrollY) {
                    super.scrollTo(getScrollX(), scrollRange);
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:34:0x0083  */
    /* JADX WARN: Code duplicated, block: B:36:0x008b  */
    /* JADX WARN: Code duplicated, block: B:39:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:52:0x00e5  */
    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z2;
        VelocityTracker velocityTracker;
        int action = motionEvent.getAction();
        if (action == 2 && this.f272k) {
            return true;
        }
        int i2 = action & 255;
        if (i2 == 0) {
            int y2 = (int) motionEvent.getY();
            int x2 = (int) motionEvent.getX();
            if (getChildCount() > 0) {
                int scrollY = getScrollY();
                View childAt = getChildAt(0);
                if (y2 < childAt.getTop() - scrollY || y2 >= childAt.getBottom() - scrollY || x2 < childAt.getLeft() || x2 >= childAt.getRight()) {
                    z2 = false;
                } else {
                    z2 = true;
                }
            } else {
                z2 = false;
            }
            if (z2) {
                this.f268g = y2;
                this.f279r = motionEvent.getPointerId(0);
                VelocityTracker velocityTracker2 = this.f273l;
                if (velocityTracker2 == null) {
                    this.f273l = VelocityTracker.obtain();
                } else {
                    velocityTracker2.clear();
                }
                this.f273l.addMovement(motionEvent);
                this.f265d.computeScrollOffset();
                this.f272k = !this.f265d.isFinished();
                w(2, 0);
            } else {
                this.f272k = false;
                VelocityTracker velocityTracker3 = this.f273l;
                if (velocityTracker3 != null) {
                    velocityTracker3.recycle();
                    this.f273l = null;
                }
            }
        } else if (i2 == 1) {
            this.f272k = false;
            this.f279r = -1;
            velocityTracker = this.f273l;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.f273l = null;
            }
            if (this.f265d.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                WeakHashMap weakHashMap = u.f2012a;
                postInvalidateOnAnimation();
            }
            x(0);
        } else if (i2 == 2) {
            int i3 = this.f279r;
            if (i3 != -1) {
                int iFindPointerIndex = motionEvent.findPointerIndex(i3);
                if (iFindPointerIndex == -1) {
                    Log.e("NestedScrollView", "Invalid pointerId=" + i3 + " in onInterceptTouchEvent");
                } else {
                    int y3 = (int) motionEvent.getY(iFindPointerIndex);
                    if (Math.abs(y3 - this.f268g) > this.f276o && (2 & getNestedScrollAxes()) == 0) {
                        this.f272k = true;
                        this.f268g = y3;
                        if (this.f273l == null) {
                            this.f273l = VelocityTracker.obtain();
                        }
                        this.f273l.addMovement(motionEvent);
                        this.f282u = 0;
                        ViewParent parent = getParent();
                        if (parent != null) {
                            parent.requestDisallowInterceptTouchEvent(true);
                        }
                    }
                }
            }
        } else if (i2 == 3) {
            this.f272k = false;
            this.f279r = -1;
            velocityTracker = this.f273l;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.f273l = null;
            }
            if (this.f265d.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                WeakHashMap weakHashMap2 = u.f2012a;
                postInvalidateOnAnimation();
            }
            x(0);
        } else if (i2 == 6) {
            s(motionEvent);
        }
        return this.f272k;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int measuredHeight;
        super.onLayout(z2, i2, i3, i4, i5);
        int i6 = 0;
        this.f269h = false;
        View view = this.f271j;
        if (view != null && p(view, this)) {
            View view2 = this.f271j;
            Rect rect = this.f264c;
            view2.getDrawingRect(rect);
            offsetDescendantRectToMyCoords(view2, rect);
            int iH = h(rect);
            if (iH != 0) {
                scrollBy(0, iH);
            }
        }
        this.f271j = null;
        if (!this.f270i) {
            if (this.f284w != null) {
                scrollTo(getScrollX(), this.f284w.f30a);
                this.f284w = null;
            }
            if (getChildCount() > 0) {
                View childAt = getChildAt(0);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                measuredHeight = childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            } else {
                measuredHeight = 0;
            }
            int paddingTop = ((i5 - i3) - getPaddingTop()) - getPaddingBottom();
            int scrollY = getScrollY();
            if (paddingTop < measuredHeight && scrollY >= 0) {
                i6 = paddingTop + scrollY > measuredHeight ? measuredHeight - paddingTop : scrollY;
            }
            if (i6 != scrollY) {
                scrollTo(getScrollX(), i6);
            }
        }
        scrollTo(getScrollX(), getScrollY());
        this.f270i = true;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (this.f274m && View.MeasureSpec.getMode(i3) != 0 && getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int measuredHeight2 = (((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom()) - layoutParams.topMargin) - layoutParams.bottomMargin;
            if (measuredHeight < measuredHeight2) {
                childAt.measure(ViewGroup.getChildMeasureSpec(i2, getPaddingRight() + getPaddingLeft() + layoutParams.leftMargin + layoutParams.rightMargin, layoutParams.width), View.MeasureSpec.makeMeasureSpec(measuredHeight2, 1073741824));
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedFling(View view, float f2, float f3, boolean z2) {
        if (z2) {
            return false;
        }
        dispatchNestedFling(0.0f, f3, true);
        n((int) f3);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedPreFling(View view, float f2, float f3) {
        return dispatchNestedPreFling(f2, f3);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedPreScroll(View view, int i2, int i3, int[] iArr) {
        c(view, i2, i3, iArr, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScroll(View view, int i2, int i3, int i4, int i5) {
        r(i5, 0, null);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScrollAccepted(View view, View view2, int i2) {
        a(view, view2, i2, 0);
    }

    @Override // android.view.View
    public final void onOverScrolled(int i2, int i3, boolean z2, boolean z3) {
        super.scrollTo(i2, i3);
    }

    @Override // android.view.ViewGroup
    public final boolean onRequestFocusInDescendants(int i2, Rect rect) {
        if (i2 == 2) {
            i2 = 130;
        } else if (i2 == 1) {
            i2 = 33;
        }
        FocusFinder focusFinder = FocusFinder.getInstance();
        View viewFindNextFocus = rect == null ? focusFinder.findNextFocus(this, null, i2) : focusFinder.findNextFocusFromRect(this, rect, i2);
        if (viewFindNextFocus == null || (true ^ q(viewFindNextFocus, 0, getHeight()))) {
            return false;
        }
        return viewFindNextFocus.requestFocus(i2, rect);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof g)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        g gVar = (g) parcelable;
        super.onRestoreInstanceState(gVar.getSuperState());
        this.f284w = gVar;
        requestLayout();
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        g gVar = new g(super.onSaveInstanceState());
        gVar.f30a = getScrollY();
        return gVar;
    }

    @Override // android.view.View
    public final void onScrollChanged(int i2, int i3, int i4, int i5) {
        super.onScrollChanged(i2, i3, i4, i5);
        f fVar = this.A;
        if (fVar != null) {
            m0.a aVar = (m0.a) fVar;
            d.i.a(this, (View) aVar.f1642a, (View) aVar.f1643b);
        }
    }

    @Override // android.view.View
    public final void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        View viewFindFocus = findFocus();
        if (viewFindFocus == null || this == viewFindFocus || !q(viewFindFocus, 0, i5)) {
            return;
        }
        Rect rect = this.f264c;
        viewFindFocus.getDrawingRect(rect);
        offsetDescendantRectToMyCoords(viewFindFocus, rect);
        j(h(rect));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onStartNestedScroll(View view, View view2, int i2) {
        return f(view, view2, i2, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onStopNestedScroll(View view) {
        b(view, 0);
    }

    /* JADX WARN: Code duplicated, block: B:89:0x01f5  */
    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        ViewParent parent;
        EdgeEffect edgeEffect;
        if (this.f273l == null) {
            this.f273l = VelocityTracker.obtain();
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f282u = 0;
        }
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        motionEventObtain.offsetLocation(0.0f, this.f282u);
        if (actionMasked != 0) {
            if (actionMasked == 1) {
                VelocityTracker velocityTracker = this.f273l;
                velocityTracker.computeCurrentVelocity(1000, this.f278q);
                int yVelocity = (int) velocityTracker.getYVelocity(this.f279r);
                if (Math.abs(yVelocity) >= this.f277p) {
                    int i2 = -yVelocity;
                    float f2 = i2;
                    if (!dispatchNestedPreFling(0.0f, f2)) {
                        dispatchNestedFling(0.0f, f2, true);
                        n(i2);
                    }
                } else if (this.f265d.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                    WeakHashMap weakHashMap = u.f2012a;
                    postInvalidateOnAnimation();
                }
                this.f279r = -1;
                k();
            } else if (actionMasked == 2) {
                int iFindPointerIndex = motionEvent.findPointerIndex(this.f279r);
                if (iFindPointerIndex == -1) {
                    Log.e("NestedScrollView", "Invalid pointerId=" + this.f279r + " in onTouchEvent");
                } else {
                    int y2 = (int) motionEvent.getY(iFindPointerIndex);
                    int i3 = this.f268g - y2;
                    if (!this.f272k && Math.abs(i3) > this.f276o) {
                        ViewParent parent2 = getParent();
                        if (parent2 != null) {
                            parent2.requestDisallowInterceptTouchEvent(true);
                        }
                        this.f272k = true;
                        int i4 = this.f276o;
                        i3 = i3 > 0 ? i3 - i4 : i3 + i4;
                    }
                    int i5 = i3;
                    if (this.f272k) {
                        boolean zI = i(0, i5, this.f281t, this.f280s, 0);
                        int[] iArr = this.f281t;
                        int[] iArr2 = this.f280s;
                        if (zI) {
                            i5 -= iArr[1];
                            this.f282u += iArr2[1];
                        }
                        this.f268g = y2 - iArr2[1];
                        int scrollY = getScrollY();
                        int scrollRange = getScrollRange();
                        int overScrollMode = getOverScrollMode();
                        boolean z2 = overScrollMode == 0 || (overScrollMode == 1 && scrollRange > 0);
                        if (t(i5, 0, getScrollY(), scrollRange)) {
                            if (!(this.f286y.b(0) != null)) {
                                this.f273l.clear();
                            }
                        }
                        int scrollY2 = getScrollY() - scrollY;
                        iArr[1] = 0;
                        this.f286y.a(0, scrollY2, 0, i5 - scrollY2, this.f280s, 0, iArr);
                        int i6 = this.f268g;
                        int i7 = iArr2[1];
                        this.f268g = i6 - i7;
                        this.f282u += i7;
                        if (z2) {
                            int i8 = i5 - iArr[1];
                            l();
                            int i9 = scrollY + i8;
                            if (i9 < 0) {
                                this.f266e.onPull(i8 / getHeight(), motionEvent.getX(iFindPointerIndex) / getWidth());
                                if (!this.f267f.isFinished()) {
                                    edgeEffect = this.f267f;
                                    edgeEffect.onRelease();
                                }
                            } else if (i9 > scrollRange) {
                                this.f267f.onPull(i8 / getHeight(), 1.0f - (motionEvent.getX(iFindPointerIndex) / getWidth()));
                                if (!this.f266e.isFinished()) {
                                    edgeEffect = this.f266e;
                                    edgeEffect.onRelease();
                                }
                            }
                            EdgeEffect edgeEffect2 = this.f266e;
                            if (edgeEffect2 != null && (!edgeEffect2.isFinished() || !this.f267f.isFinished())) {
                                WeakHashMap weakHashMap2 = u.f2012a;
                                postInvalidateOnAnimation();
                            }
                        }
                    }
                }
            } else if (actionMasked == 3) {
                if (this.f272k && getChildCount() > 0 && this.f265d.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                    WeakHashMap weakHashMap3 = u.f2012a;
                    postInvalidateOnAnimation();
                }
                this.f279r = -1;
                k();
            } else if (actionMasked == 5) {
                int actionIndex = motionEvent.getActionIndex();
                this.f268g = (int) motionEvent.getY(actionIndex);
                this.f279r = motionEvent.getPointerId(actionIndex);
            } else if (actionMasked == 6) {
                s(motionEvent);
                this.f268g = (int) motionEvent.getY(motionEvent.findPointerIndex(this.f279r));
            }
        } else {
            if (getChildCount() == 0) {
                return false;
            }
            boolean z3 = !this.f265d.isFinished();
            this.f272k = z3;
            if (z3 && (parent = getParent()) != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
            if (!this.f265d.isFinished()) {
                this.f265d.abortAnimation();
                x(1);
            }
            this.f268g = (int) motionEvent.getY();
            this.f279r = motionEvent.getPointerId(0);
            w(2, 0);
        }
        VelocityTracker velocityTracker2 = this.f273l;
        if (velocityTracker2 != null) {
            velocityTracker2.addMovement(motionEventObtain);
        }
        motionEventObtain.recycle();
        return true;
    }

    public final boolean q(View view, int i2, int i3) {
        Rect rect = this.f264c;
        view.getDrawingRect(rect);
        offsetDescendantRectToMyCoords(view, rect);
        return rect.bottom + i2 >= getScrollY() && rect.top - i2 <= getScrollY() + i3;
    }

    public final void r(int i2, int i3, int[] iArr) {
        int scrollY = getScrollY();
        scrollBy(0, i2);
        int scrollY2 = getScrollY() - scrollY;
        if (iArr != null) {
            iArr[1] = iArr[1] + scrollY2;
        }
        this.f286y.a(0, scrollY2, 0, i2 - scrollY2, null, i3, iArr);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestChildFocus(View view, View view2) {
        if (this.f269h) {
            this.f271j = view2;
        } else {
            Rect rect = this.f264c;
            view2.getDrawingRect(rect);
            offsetDescendantRectToMyCoords(view2, rect);
            int iH = h(rect);
            if (iH != 0) {
                scrollBy(0, iH);
            }
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z2) {
        rect.offset(view.getLeft() - view.getScrollX(), view.getTop() - view.getScrollY());
        int iH = h(rect);
        boolean z3 = iH != 0;
        if (z3) {
            if (z2) {
                scrollBy(0, iH);
            } else {
                v(0, iH, false);
            }
        }
        return z3;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestDisallowInterceptTouchEvent(boolean z2) {
        VelocityTracker velocityTracker;
        if (z2 && (velocityTracker = this.f273l) != null) {
            velocityTracker.recycle();
            this.f273l = null;
        }
        super.requestDisallowInterceptTouchEvent(z2);
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        this.f269h = true;
        super.requestLayout();
    }

    public final void s(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.f279r) {
            int i2 = actionIndex == 0 ? 1 : 0;
            this.f268g = (int) motionEvent.getY(i2);
            this.f279r = motionEvent.getPointerId(i2);
            VelocityTracker velocityTracker = this.f273l;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    @Override // android.view.View
    public final void scrollTo(int i2, int i3) {
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
            int width2 = childAt.getWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
            int height2 = childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            if (width >= width2 || i2 < 0) {
                i2 = 0;
            } else if (width + i2 > width2) {
                i2 = width2 - width;
            }
            if (height >= height2 || i3 < 0) {
                i3 = 0;
            } else if (height + i3 > height2) {
                i3 = height2 - height;
            }
            if (i2 == getScrollX() && i3 == getScrollY()) {
                return;
            }
            super.scrollTo(i2, i3);
        }
    }

    public void setFillViewport(boolean z2) {
        if (z2 != this.f274m) {
            this.f274m = z2;
            requestLayout();
        }
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z2) {
        x.g gVar = this.f286y;
        if (gVar.f1991d) {
            WeakHashMap weakHashMap = u.f2012a;
            gVar.f1990c.stopNestedScroll();
        }
        gVar.f1991d = z2;
    }

    public void setOnScrollChangeListener(f fVar) {
        this.A = fVar;
    }

    public void setSmoothScrollingEnabled(boolean z2) {
        this.f275n = z2;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return true;
    }

    @Override // android.view.View
    public final boolean startNestedScroll(int i2) {
        return w(i2, 0);
    }

    @Override // android.view.View
    public final void stopNestedScroll() {
        x(0);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x002a  */
    /* JADX WARN: Code duplicated, block: B:17:0x0032  */
    /* JADX WARN: Code duplicated, block: B:18:0x0034  */
    /* JADX WARN: Code duplicated, block: B:20:0x0037  */
    public final boolean t(int i2, int i3, int i4, int i5) {
        boolean z2;
        boolean z3;
        boolean z4;
        getOverScrollMode();
        computeHorizontalScrollRange();
        computeHorizontalScrollExtent();
        computeVerticalScrollRange();
        computeVerticalScrollExtent();
        int i6 = i3 + 0;
        int i7 = i4 + i2;
        int i8 = i5 + 0;
        if (i6 <= 0 && i6 >= 0) {
            z2 = false;
        } else {
            z2 = true;
            i6 = 0;
        }
        if (i7 <= i8) {
            if (i7 < 0) {
                i7 = 0;
            } else {
                z3 = false;
            }
            if (z3) {
                if (this.f286y.b(1) != null) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (!z4) {
                    this.f265d.springBack(i6, i7, 0, 0, 0, getScrollRange());
                }
            }
            onOverScrolled(i6, i7, z2, z3);
            return z2 || z3;
        }
        i7 = i8;
        z3 = true;
        if (z3) {
            if (this.f286y.b(1) != null) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (!z4) {
                this.f265d.springBack(i6, i7, 0, 0, 0, getScrollRange());
            }
        }
        onOverScrolled(i6, i7, z2, z3);
        if (z2) {
            return true;
        }
    }

    /* JADX WARN: Code duplicated, block: B:32:0x0068  */
    public final boolean u(int i2, int i3, int i4) {
        boolean z2;
        int height = getHeight();
        int scrollY = getScrollY();
        int i5 = height + scrollY;
        boolean z3 = i2 == 33;
        ArrayList<View> focusables = getFocusables(2);
        int size = focusables.size();
        View view = null;
        boolean z4 = false;
        for (int i6 = 0; i6 < size; i6++) {
            View view2 = focusables.get(i6);
            int top = view2.getTop();
            int bottom = view2.getBottom();
            if (i3 < bottom && top < i4) {
                boolean z5 = i3 < top && bottom < i4;
                if (view == null) {
                    view = view2;
                    z4 = z5;
                } else {
                    boolean z6 = (z3 && top < view.getTop()) || (!z3 && bottom > view.getBottom());
                    if (z4) {
                        if (z5 && z6) {
                            view = view2;
                        }
                    } else if (z5) {
                        view = view2;
                        z4 = true;
                    } else if (z6) {
                        view = view2;
                    }
                }
            }
        }
        if (view == null) {
            view = this;
        }
        if (i3 < scrollY || i4 > i5) {
            j(z3 ? i3 - scrollY : i4 - i5);
            z2 = true;
        } else {
            z2 = false;
        }
        if (view != findFocus()) {
            view.requestFocus(i2);
        }
        return z2;
    }

    public final void v(int i2, int i3, boolean z2) {
        if (getChildCount() == 0) {
            return;
        }
        if (AnimationUtils.currentAnimationTimeMillis() - this.f263b > 250) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int height = childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
            int scrollY = getScrollY();
            this.f265d.startScroll(getScrollX(), scrollY, 0, Math.max(0, Math.min(i3 + scrollY, Math.max(0, height - height2))) - scrollY, 250);
            if (z2) {
                w(2, 1);
            } else {
                x(1);
            }
            this.f283v = getScrollY();
            WeakHashMap weakHashMap = u.f2012a;
            postInvalidateOnAnimation();
        } else {
            if (!this.f265d.isFinished()) {
                this.f265d.abortAnimation();
                x(1);
            }
            scrollBy(i2, i3);
        }
        this.f263b = AnimationUtils.currentAnimationTimeMillis();
    }

    public final boolean w(int i2, int i3) {
        boolean zOnStartNestedScroll;
        x.g gVar = this.f286y;
        if (gVar.b(i3) != null) {
            return true;
        }
        if (gVar.f1991d) {
            View view = gVar.f1990c;
            View view2 = view;
            for (ViewParent parent = view.getParent(); parent != null; parent = parent.getParent()) {
                boolean z2 = parent instanceof h;
                if (z2) {
                    zOnStartNestedScroll = ((h) parent).f(view2, view, i2, i3);
                } else if (i3 == 0) {
                    try {
                        zOnStartNestedScroll = parent.onStartNestedScroll(view2, view, i2);
                    } catch (AbstractMethodError e2) {
                        Log.e("ViewParentCompat", "ViewParent " + parent + " does not implement interface method onStartNestedScroll", e2);
                        zOnStartNestedScroll = false;
                    }
                } else {
                    zOnStartNestedScroll = false;
                }
                if (zOnStartNestedScroll) {
                    if (i3 == 0) {
                        gVar.f1988a = parent;
                    } else if (i3 == 1) {
                        gVar.f1989b = parent;
                    }
                    if (z2) {
                        ((h) parent).a(view2, view, i2, i3);
                        return true;
                    }
                    if (i3 != 0) {
                        return true;
                    }
                    try {
                        parent.onNestedScrollAccepted(view2, view, i2);
                        return true;
                    } catch (AbstractMethodError e3) {
                        Log.e("ViewParentCompat", "ViewParent " + parent + " does not implement interface method onNestedScrollAccepted", e3);
                        return true;
                    }
                }
                if (parent instanceof View) {
                    view2 = (View) parent;
                }
            }
        }
        return false;
    }

    public final void x(int i2) {
        x.g gVar = this.f286y;
        ViewParent viewParentB = gVar.b(i2);
        if (viewParentB != null) {
            boolean z2 = viewParentB instanceof h;
            View view = gVar.f1990c;
            if (z2) {
                ((h) viewParentB).b(view, i2);
            } else if (i2 == 0) {
                try {
                    viewParentB.onStopNestedScroll(view);
                } catch (AbstractMethodError e2) {
                    Log.e("ViewParentCompat", "ViewParent " + viewParentB + " does not implement interface method onStopNestedScroll", e2);
                }
            }
            if (i2 == 0) {
                gVar.f1988a = null;
            } else {
                if (i2 != 1) {
                    return;
                }
                gVar.f1989b = null;
            }
        }
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i2) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view, i2);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view, i2, layoutParams);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public final void addView(View view, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view, layoutParams);
    }
}
