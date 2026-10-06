package j;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class n1 extends ViewGroup {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f1322b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1323c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1324d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1325e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1326f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1327g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f1328h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1329i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int[] f1330j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int[] f1331k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Drawable f1332l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f1333m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f1334n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f1335o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f1336p;

    public n1(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f1322b = true;
        this.f1323c = -1;
        this.f1324d = 0;
        this.f1326f = 8388659;
        int[] iArr = c.a.f493m;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i2, 0);
        m0.a aVar = new m0.a(context, typedArrayObtainStyledAttributes);
        x.u.d(this, context, iArr, attributeSet, typedArrayObtainStyledAttributes, i2);
        int iN = aVar.n(1, -1);
        if (iN >= 0) {
            setOrientation(iN);
        }
        int iN2 = aVar.n(0, -1);
        if (iN2 >= 0) {
            setGravity(iN2);
        }
        boolean zG = aVar.g(2, true);
        if (!zG) {
            setBaselineAligned(zG);
        }
        this.f1328h = typedArrayObtainStyledAttributes.getFloat(4, -1.0f);
        this.f1323c = aVar.n(3, -1);
        this.f1329i = aVar.g(7, false);
        setDividerDrawable(aVar.k(5));
        this.f1335o = aVar.n(8, 0);
        this.f1336p = aVar.j(6, 0);
        aVar.w();
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof m1;
    }

    public final void e(Canvas canvas, int i2) {
        this.f1332l.setBounds(getPaddingLeft() + this.f1336p, i2, (getWidth() - getPaddingRight()) - this.f1336p, this.f1334n + i2);
        this.f1332l.draw(canvas);
    }

    public final void f(Canvas canvas, int i2) {
        this.f1332l.setBounds(i2, getPaddingTop() + this.f1336p, this.f1333m + i2, (getHeight() - getPaddingBottom()) - this.f1336p);
        this.f1332l.draw(canvas);
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public m1 generateDefaultLayoutParams() {
        int i2 = this.f1325e;
        if (i2 == 0) {
            return new m1(-2);
        }
        if (i2 == 1) {
            return new m1(-1);
        }
        return null;
    }

    @Override // android.view.View
    public int getBaseline() {
        int i2;
        if (this.f1323c < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i3 = this.f1323c;
        if (childCount <= i3) {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
        View childAt = getChildAt(i3);
        int baseline = childAt.getBaseline();
        if (baseline == -1) {
            if (this.f1323c == 0) {
                return -1;
            }
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
        }
        int bottom = this.f1324d;
        if (this.f1325e == 1 && (i2 = this.f1326f & 112) != 48) {
            if (i2 == 16) {
                bottom += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.f1327g) / 2;
            } else if (i2 == 80) {
                bottom = ((getBottom() - getTop()) - getPaddingBottom()) - this.f1327g;
            }
        }
        return bottom + ((LinearLayout.LayoutParams) ((m1) childAt.getLayoutParams())).topMargin + baseline;
    }

    public int getBaselineAlignedChildIndex() {
        return this.f1323c;
    }

    public Drawable getDividerDrawable() {
        return this.f1332l;
    }

    public int getDividerPadding() {
        return this.f1336p;
    }

    public int getDividerWidth() {
        return this.f1333m;
    }

    public int getGravity() {
        return this.f1326f;
    }

    public int getOrientation() {
        return this.f1325e;
    }

    public int getShowDividers() {
        return this.f1335o;
    }

    public int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.f1328h;
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public m1 generateLayoutParams(AttributeSet attributeSet) {
        return new m1(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public m1 generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new m1(layoutParams);
    }

    public final boolean j(int i2) {
        if (i2 == 0) {
            return (this.f1335o & 1) != 0;
        }
        if (i2 == getChildCount()) {
            return (this.f1335o & 4) != 0;
        }
        if ((this.f1335o & 2) == 0) {
            return false;
        }
        for (int i3 = i2 - 1; i3 >= 0; i3--) {
            if (getChildAt(i3).getVisibility() != 8) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        int right;
        int left;
        int paddingRight;
        int bottom;
        if (this.f1332l == null) {
            return;
        }
        int i2 = 0;
        if (this.f1325e == 1) {
            int virtualChildCount = getVirtualChildCount();
            while (i2 < virtualChildCount) {
                View childAt = getChildAt(i2);
                if (childAt != null && childAt.getVisibility() != 8 && j(i2)) {
                    e(canvas, (childAt.getTop() - ((LinearLayout.LayoutParams) ((m1) childAt.getLayoutParams())).topMargin) - this.f1334n);
                }
                i2++;
            }
            if (j(virtualChildCount)) {
                View childAt2 = getChildAt(virtualChildCount - 1);
                if (childAt2 == null) {
                    bottom = (getHeight() - getPaddingBottom()) - this.f1334n;
                } else {
                    bottom = childAt2.getBottom() + ((LinearLayout.LayoutParams) ((m1) childAt2.getLayoutParams())).bottomMargin;
                }
                e(canvas, bottom);
                return;
            }
            return;
        }
        int virtualChildCount2 = getVirtualChildCount();
        boolean zA = g3.a(this);
        while (i2 < virtualChildCount2) {
            View childAt3 = getChildAt(i2);
            if (childAt3 != null && childAt3.getVisibility() != 8 && j(i2)) {
                m1 m1Var = (m1) childAt3.getLayoutParams();
                f(canvas, zA ? childAt3.getRight() + ((LinearLayout.LayoutParams) m1Var).rightMargin : (childAt3.getLeft() - ((LinearLayout.LayoutParams) m1Var).leftMargin) - this.f1333m);
            }
            i2++;
        }
        if (j(virtualChildCount2)) {
            View childAt4 = getChildAt(virtualChildCount2 - 1);
            if (childAt4 != null) {
                m1 m1Var2 = (m1) childAt4.getLayoutParams();
                if (zA) {
                    left = childAt4.getLeft();
                    paddingRight = ((LinearLayout.LayoutParams) m1Var2).leftMargin;
                    right = (left - paddingRight) - this.f1333m;
                } else {
                    right = childAt4.getRight() + ((LinearLayout.LayoutParams) m1Var2).rightMargin;
                }
            } else if (zA) {
                right = getPaddingLeft();
            } else {
                left = getWidth();
                paddingRight = getPaddingRight();
                right = (left - paddingRight) - this.f1333m;
            }
            f(canvas, right);
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("androidx.appcompat.widget.LinearLayoutCompat");
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("androidx.appcompat.widget.LinearLayoutCompat");
    }

    /* JADX WARN: Code duplicated, block: B:29:0x009c  */
    /* JADX WARN: Code duplicated, block: B:60:0x015c  */
    /* JADX WARN: Code duplicated, block: B:63:0x0165  */
    /* JADX WARN: Code duplicated, block: B:65:0x0169  */
    /* JADX WARN: Code duplicated, block: B:67:0x016d  */
    /* JADX WARN: Code duplicated, block: B:68:0x0170  */
    /* JADX WARN: Code duplicated, block: B:70:0x0178  */
    /* JADX WARN: Code duplicated, block: B:71:0x0186  */
    /* JADX WARN: Code duplicated, block: B:73:0x018c  */
    /* JADX WARN: Code duplicated, block: B:74:0x0195  */
    /* JADX WARN: Code duplicated, block: B:77:0x01a7  */
    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int paddingLeft;
        int i6;
        int i7;
        int i8;
        int baseline;
        int i9;
        int i10;
        int measuredHeight;
        int paddingTop;
        int i11;
        int i12;
        int i13 = 8;
        int i14 = 5;
        if (this.f1325e == 1) {
            int paddingLeft2 = getPaddingLeft();
            int i15 = i4 - i2;
            int paddingRight = i15 - getPaddingRight();
            int paddingRight2 = (i15 - paddingLeft2) - getPaddingRight();
            int virtualChildCount = getVirtualChildCount();
            int i16 = this.f1326f;
            int i17 = i16 & 112;
            int i18 = 8388615 & i16;
            if (i17 != 16) {
                paddingTop = i17 != 80 ? getPaddingTop() : ((getPaddingTop() + i5) - i3) - this.f1327g;
            } else {
                paddingTop = getPaddingTop() + (((i5 - i3) - this.f1327g) / 2);
            }
            int i19 = 0;
            while (i19 < virtualChildCount) {
                View childAt = getChildAt(i19);
                if (childAt == null) {
                    paddingTop += 0;
                } else if (childAt.getVisibility() != i13) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    int measuredHeight2 = childAt.getMeasuredHeight();
                    m1 m1Var = (m1) childAt.getLayoutParams();
                    int i20 = ((LinearLayout.LayoutParams) m1Var).gravity;
                    if (i20 < 0) {
                        i20 = i18;
                    }
                    WeakHashMap weakHashMap = x.u.f2012a;
                    int absoluteGravity = Gravity.getAbsoluteGravity(i20, getLayoutDirection()) & 7;
                    if (absoluteGravity != 1) {
                        if (absoluteGravity != i14) {
                            i12 = ((LinearLayout.LayoutParams) m1Var).leftMargin + paddingLeft2;
                        } else {
                            i11 = paddingRight - measuredWidth;
                        }
                        if (j(i19)) {
                            paddingTop += this.f1334n;
                        }
                        int i21 = paddingTop + ((LinearLayout.LayoutParams) m1Var).topMargin;
                        int i22 = i21 + 0;
                        childAt.layout(i12, i22, measuredWidth + i12, measuredHeight2 + i22);
                        i19 += 0;
                        paddingTop = measuredHeight2 + ((LinearLayout.LayoutParams) m1Var).bottomMargin + 0 + i21;
                    } else {
                        i11 = ((paddingRight2 - measuredWidth) / 2) + paddingLeft2 + ((LinearLayout.LayoutParams) m1Var).leftMargin;
                    }
                    i12 = i11 - ((LinearLayout.LayoutParams) m1Var).rightMargin;
                    if (j(i19)) {
                        paddingTop += this.f1334n;
                    }
                    int i23 = paddingTop + ((LinearLayout.LayoutParams) m1Var).topMargin;
                    int i24 = i23 + 0;
                    childAt.layout(i12, i24, measuredWidth + i12, measuredHeight2 + i24);
                    i19 += 0;
                    paddingTop = measuredHeight2 + ((LinearLayout.LayoutParams) m1Var).bottomMargin + 0 + i23;
                }
                i19++;
                i13 = 8;
                i14 = 5;
            }
            return;
        }
        boolean zA = g3.a(this);
        int paddingTop2 = getPaddingTop();
        int i25 = i5 - i3;
        int paddingBottom = i25 - getPaddingBottom();
        int paddingBottom2 = (i25 - paddingTop2) - getPaddingBottom();
        int virtualChildCount2 = getVirtualChildCount();
        int i26 = this.f1326f;
        int i27 = 8388615 & i26;
        int i28 = i26 & 112;
        boolean z3 = this.f1322b;
        int[] iArr = this.f1330j;
        int[] iArr2 = this.f1331k;
        WeakHashMap weakHashMap2 = x.u.f2012a;
        int absoluteGravity2 = Gravity.getAbsoluteGravity(i27, getLayoutDirection());
        if (absoluteGravity2 != 1) {
            paddingLeft = absoluteGravity2 != 5 ? getPaddingLeft() : ((getPaddingLeft() + i4) - i2) - this.f1327g;
        } else {
            paddingLeft = getPaddingLeft() + (((i4 - i2) - this.f1327g) / 2);
        }
        if (zA) {
            i6 = virtualChildCount2 - 1;
            i7 = -1;
        } else {
            i6 = 0;
            i7 = 1;
        }
        int i29 = paddingLeft;
        int i30 = 0;
        while (i30 < virtualChildCount2) {
            int i31 = (i7 * i30) + i6;
            View childAt2 = getChildAt(i31);
            if (childAt2 == null) {
                i29 += 0;
            } else {
                if (childAt2.getVisibility() != 8) {
                    int measuredWidth2 = childAt2.getMeasuredWidth();
                    int measuredHeight3 = childAt2.getMeasuredHeight();
                    m1 m1Var2 = (m1) childAt2.getLayoutParams();
                    if (z3) {
                        i8 = virtualChildCount2;
                        baseline = ((LinearLayout.LayoutParams) m1Var2).height != -1 ? childAt2.getBaseline() : -1;
                        i9 = ((LinearLayout.LayoutParams) m1Var2).gravity;
                        if (i9 < 0) {
                            i9 = i28;
                        }
                        i10 = i9 & 112;
                        if (i10 != 16) {
                            measuredHeight = ((((paddingBottom2 - measuredHeight3) / 2) + paddingTop2) + ((LinearLayout.LayoutParams) m1Var2).topMargin) - ((LinearLayout.LayoutParams) m1Var2).bottomMargin;
                        } else if (i10 != 48) {
                            measuredHeight = ((LinearLayout.LayoutParams) m1Var2).topMargin + paddingTop2;
                            if (baseline != -1) {
                                measuredHeight = (iArr[1] - baseline) + measuredHeight;
                            }
                        } else if (i10 != 80) {
                            measuredHeight = paddingTop2;
                        } else {
                            measuredHeight = (paddingBottom - measuredHeight3) - ((LinearLayout.LayoutParams) m1Var2).bottomMargin;
                            if (baseline != -1) {
                                measuredHeight -= iArr2[2] - (childAt2.getMeasuredHeight() - baseline);
                            }
                        }
                        if (j(i31)) {
                            i29 += this.f1333m;
                        }
                        int i32 = i29 + ((LinearLayout.LayoutParams) m1Var2).leftMargin;
                        int i33 = i32 + 0;
                        childAt2.layout(i33, measuredHeight, measuredWidth2 + i33, measuredHeight3 + measuredHeight);
                        i29 = measuredWidth2 + ((LinearLayout.LayoutParams) m1Var2).rightMargin + 0 + i32;
                        i30 += 0;
                    } else {
                        i8 = virtualChildCount2;
                    }
                    i9 = ((LinearLayout.LayoutParams) m1Var2).gravity;
                    if (i9 < 0) {
                        i9 = i28;
                    }
                    i10 = i9 & 112;
                    if (i10 != 16) {
                        measuredHeight = ((((paddingBottom2 - measuredHeight3) / 2) + paddingTop2) + ((LinearLayout.LayoutParams) m1Var2).topMargin) - ((LinearLayout.LayoutParams) m1Var2).bottomMargin;
                    } else if (i10 != 48) {
                        measuredHeight = ((LinearLayout.LayoutParams) m1Var2).topMargin + paddingTop2;
                        if (baseline != -1) {
                            measuredHeight = (iArr[1] - baseline) + measuredHeight;
                        }
                    } else if (i10 != 80) {
                        measuredHeight = paddingTop2;
                    } else {
                        measuredHeight = (paddingBottom - measuredHeight3) - ((LinearLayout.LayoutParams) m1Var2).bottomMargin;
                        if (baseline != -1) {
                            measuredHeight -= iArr2[2] - (childAt2.getMeasuredHeight() - baseline);
                        }
                    }
                    if (j(i31)) {
                        i29 += this.f1333m;
                    }
                    int i34 = i29 + ((LinearLayout.LayoutParams) m1Var2).leftMargin;
                    int i35 = i34 + 0;
                    childAt2.layout(i35, measuredHeight, measuredWidth2 + i35, measuredHeight3 + measuredHeight);
                    i29 = measuredWidth2 + ((LinearLayout.LayoutParams) m1Var2).rightMargin + 0 + i34;
                    i30 += 0;
                }
                i30++;
                i6 = i6;
                virtualChildCount2 = i8;
                i28 = i28;
            }
            i8 = virtualChildCount2;
            i30++;
            i6 = i6;
            virtualChildCount2 = i8;
            i28 = i28;
        }
    }

    /* JADX WARN: Code duplicated, block: B:136:0x02b1  */
    /* JADX WARN: Code duplicated, block: B:147:0x02eb  */
    /* JADX WARN: Code duplicated, block: B:153:0x02f8  */
    /* JADX WARN: Code duplicated, block: B:214:0x048e  */
    /* JADX WARN: Code duplicated, block: B:215:0x0493  */
    /* JADX WARN: Code duplicated, block: B:218:0x04bb  */
    /* JADX WARN: Code duplicated, block: B:219:0x04c0  */
    /* JADX WARN: Code duplicated, block: B:222:0x04c8  */
    /* JADX WARN: Code duplicated, block: B:223:0x04d6  */
    /* JADX WARN: Code duplicated, block: B:225:0x04ea  */
    /* JADX WARN: Code duplicated, block: B:231:0x04fd  */
    /* JADX WARN: Code duplicated, block: B:240:0x0544  */
    /* JADX WARN: Code duplicated, block: B:246:0x0555  */
    /* JADX WARN: Code duplicated, block: B:249:0x055d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:250:0x055f  */
    /* JADX WARN: Code duplicated, block: B:252:0x0568  */
    /* JADX WARN: Code duplicated, block: B:254:0x056c  */
    /* JADX WARN: Code duplicated, block: B:281:0x05fc  */
    /* JADX WARN: Code duplicated, block: B:283:0x0602  */
    /* JADX WARN: Code duplicated, block: B:284:0x0608  */
    /* JADX WARN: Code duplicated, block: B:286:0x0610  */
    /* JADX WARN: Code duplicated, block: B:287:0x0613  */
    /* JADX WARN: Code duplicated, block: B:289:0x061b  */
    /* JADX WARN: Code duplicated, block: B:290:0x0629  */
    /* JADX WARN: Code duplicated, block: B:314:0x06b1  */
    /* JADX WARN: Code duplicated, block: B:316:0x06b8  */
    /* JADX WARN: Code duplicated, block: B:319:0x06d6  */
    /* JADX WARN: Code duplicated, block: B:321:0x06dc  */
    /* JADX WARN: Code duplicated, block: B:365:0x07e0  */
    /* JADX WARN: Code duplicated, block: B:367:0x07ee  */
    /* JADX WARN: Code duplicated, block: B:371:0x0819  */
    /* JADX WARN: Code duplicated, block: B:380:0x082c  */
    /* JADX WARN: Code duplicated, block: B:383:0x085b  */
    /* JADX WARN: Code duplicated, block: B:386:0x0860  */
    /* JADX WARN: Code duplicated, block: B:389:0x0883  */
    /* JADX WARN: Code duplicated, block: B:391:0x088f  */
    /* JADX WARN: Code duplicated, block: B:393:0x089b  */
    /* JADX WARN: Code duplicated, block: B:395:0x08a7  */
    /* JADX WARN: Code duplicated, block: B:396:0x08bc  */
    /* JADX WARN: Code duplicated, block: B:434:0x08bd A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:439:? A[RETURN, SYNTHETIC] */
    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        char c2;
        int iMax;
        int i4;
        float f2;
        int i5;
        int i6;
        int iCombineMeasuredStates;
        int i7;
        int i8;
        int i9;
        char c3;
        int i10;
        View childAt;
        int i11;
        int i12;
        int i13;
        int i14;
        int baseline;
        int iMakeMeasureSpec;
        View childAt2;
        m1 m1Var;
        int i15;
        int i16;
        View childAt3;
        m1 m1Var2;
        int i17;
        int i18;
        float f3;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        int i24;
        boolean z2;
        boolean z3;
        m1 m1Var3;
        int measuredWidth;
        boolean z4;
        int i25;
        boolean z5;
        int i26;
        int measuredHeight;
        boolean z6;
        int baseline2;
        int i27;
        int i28;
        boolean z7;
        boolean z8;
        int measuredHeight2;
        int i29;
        m1 m1Var4;
        boolean z9;
        int i30;
        boolean z10;
        int iCombineMeasuredStates2;
        int i31 = -2;
        int i32 = Integer.MIN_VALUE;
        int i33 = 8;
        float f4 = 0.0f;
        int i34 = 1073741824;
        int i35 = 0;
        if (this.f1325e == 1) {
            this.f1327g = 0;
            int virtualChildCount = getVirtualChildCount();
            int mode = View.MeasureSpec.getMode(i2);
            int mode2 = View.MeasureSpec.getMode(i3);
            int i36 = this.f1323c;
            boolean z11 = this.f1329i;
            int i37 = 0;
            int iMax2 = 0;
            int i38 = 0;
            int iMax3 = 0;
            int iMax4 = 0;
            int i39 = 0;
            float f5 = 0.0f;
            boolean z12 = false;
            boolean z13 = true;
            boolean z14 = false;
            while (i37 < virtualChildCount) {
                View childAt4 = getChildAt(i37);
                if (childAt4 == null) {
                    this.f1327g += i35;
                } else {
                    if (childAt4.getVisibility() == i33) {
                        i37 += 0;
                    } else {
                        if (j(i37)) {
                            this.f1327g += this.f1334n;
                        }
                        m1 m1Var5 = (m1) childAt4.getLayoutParams();
                        float f6 = ((LinearLayout.LayoutParams) m1Var5).weight;
                        f5 += f6;
                        if (mode2 == i34 && ((LinearLayout.LayoutParams) m1Var5).height == 0 && f6 > f4) {
                            int i40 = this.f1327g;
                            this.f1327g = Math.max(i40, ((LinearLayout.LayoutParams) m1Var5).topMargin + i40 + ((LinearLayout.LayoutParams) m1Var5).bottomMargin);
                            m1Var4 = m1Var5;
                            z9 = true;
                        } else {
                            if (((LinearLayout.LayoutParams) m1Var5).height != 0 || f6 <= f4) {
                                i29 = Integer.MIN_VALUE;
                            } else {
                                ((LinearLayout.LayoutParams) m1Var5).height = i31;
                                i29 = 0;
                            }
                            int i41 = f5 == f4 ? this.f1327g : 0;
                            m1Var4 = m1Var5;
                            measureChildWithMargins(childAt4, i2, 0, i3, i41);
                            if (i29 != i32) {
                                ((LinearLayout.LayoutParams) m1Var4).height = i29;
                            }
                            int measuredHeight3 = childAt4.getMeasuredHeight();
                            int i42 = this.f1327g;
                            this.f1327g = Math.max(i42, i42 + measuredHeight3 + ((LinearLayout.LayoutParams) m1Var4).topMargin + ((LinearLayout.LayoutParams) m1Var4).bottomMargin + 0);
                            int i43 = iMax3;
                            if (z11) {
                                iMax3 = Math.max(measuredHeight3, i43);
                            }
                            z9 = z12;
                        }
                        if (i36 >= 0 && i36 == i37 + 1) {
                            this.f1324d = this.f1327g;
                        }
                        if (i37 < i36 && ((LinearLayout.LayoutParams) m1Var4).weight > 0.0f) {
                            throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
                        }
                        i30 = mode;
                        if (i30 == 1073741824 || ((LinearLayout.LayoutParams) m1Var4).width != -1) {
                            z10 = false;
                        } else {
                            z10 = true;
                            z14 = true;
                        }
                        int i44 = ((LinearLayout.LayoutParams) m1Var4).leftMargin + ((LinearLayout.LayoutParams) m1Var4).rightMargin;
                        int measuredWidth2 = childAt4.getMeasuredWidth() + i44;
                        int iMax5 = Math.max(i39, measuredWidth2);
                        iCombineMeasuredStates2 = View.combineMeasuredStates(i38, childAt4.getMeasuredState());
                        boolean z15 = z13 && ((LinearLayout.LayoutParams) m1Var4).width == -1;
                        if (((LinearLayout.LayoutParams) m1Var4).weight > 0.0f) {
                            if (!z10) {
                                i44 = measuredWidth2;
                            }
                            iMax4 = Math.max(iMax4, i44);
                        } else {
                            int i45 = iMax4;
                            if (!z10) {
                                i44 = measuredWidth2;
                            }
                            iMax2 = Math.max(iMax2, i44);
                            iMax4 = i45;
                        }
                        i37 += 0;
                        i39 = iMax5;
                        z12 = z9;
                        z13 = z15;
                    }
                    i37++;
                    mode = i30;
                    i36 = i36;
                    i38 = iCombineMeasuredStates2;
                    mode2 = mode2;
                    virtualChildCount = virtualChildCount;
                    i35 = 0;
                    i31 = -2;
                    i32 = Integer.MIN_VALUE;
                    i33 = 8;
                    f4 = 0.0f;
                    i34 = 1073741824;
                }
                i36 = i36;
                mode2 = mode2;
                i30 = mode;
                virtualChildCount = virtualChildCount;
                iCombineMeasuredStates2 = i38;
                i37++;
                mode = i30;
                i36 = i36;
                i38 = iCombineMeasuredStates2;
                mode2 = mode2;
                virtualChildCount = virtualChildCount;
                i35 = 0;
                i31 = -2;
                i32 = Integer.MIN_VALUE;
                i33 = 8;
                f4 = 0.0f;
                i34 = 1073741824;
            }
            int i46 = mode2;
            int i47 = mode;
            int i48 = virtualChildCount;
            int iMax6 = iMax2;
            int iCombineMeasuredStates3 = i38;
            int i49 = iMax3;
            int i50 = iMax4;
            int i51 = i39;
            if (this.f1327g > 0 && j(i48)) {
                this.f1327g += this.f1334n;
            }
            int i52 = i46;
            if (z11 && (i52 == Integer.MIN_VALUE || i52 == 0)) {
                int i53 = 0;
                this.f1327g = 0;
                int i54 = 0;
                while (i54 < i48) {
                    View childAt5 = getChildAt(i54);
                    if (childAt5 == null) {
                        this.f1327g += i53;
                    } else if (childAt5.getVisibility() == 8) {
                        i54 += 0;
                    } else {
                        m1 m1Var6 = (m1) childAt5.getLayoutParams();
                        int i55 = this.f1327g;
                        this.f1327g = Math.max(i55, i55 + i49 + ((LinearLayout.LayoutParams) m1Var6).topMargin + ((LinearLayout.LayoutParams) m1Var6).bottomMargin + 0);
                    }
                    i54++;
                    i53 = 0;
                }
            }
            int paddingBottom = getPaddingBottom() + getPaddingTop() + this.f1327g;
            this.f1327g = paddingBottom;
            int iResolveSizeAndState = View.resolveSizeAndState(Math.max(paddingBottom, getSuggestedMinimumHeight()), i3, 0);
            int i56 = (16777215 & iResolveSizeAndState) - this.f1327g;
            if (z12 || (i56 != 0 && f5 > 0.0f)) {
                float f7 = this.f1328h;
                if (f7 > 0.0f) {
                    f5 = f7;
                }
                this.f1327g = 0;
                int i57 = 0;
                while (i57 < i48) {
                    View childAt6 = getChildAt(i57);
                    if (childAt6.getVisibility() != 8) {
                        m1 m1Var7 = (m1) childAt6.getLayoutParams();
                        float f8 = ((LinearLayout.LayoutParams) m1Var7).weight;
                        if (f8 > 0.0f) {
                            int i58 = (int) ((i56 * f8) / f5);
                            f5 -= f8;
                            int i59 = i56 - i58;
                            int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, getPaddingRight() + getPaddingLeft() + ((LinearLayout.LayoutParams) m1Var7).leftMargin + ((LinearLayout.LayoutParams) m1Var7).rightMargin, ((LinearLayout.LayoutParams) m1Var7).width);
                            if (((LinearLayout.LayoutParams) m1Var7).height != 0 || i52 != 1073741824) {
                                measuredHeight2 = childAt6.getMeasuredHeight() + i58;
                                if (measuredHeight2 < 0) {
                                    measuredHeight2 = 0;
                                }
                            } else if (i58 > 0) {
                                measuredHeight2 = i58;
                            } else {
                                measuredHeight2 = 0;
                            }
                            childAt6.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(measuredHeight2, 1073741824));
                            iCombineMeasuredStates3 = View.combineMeasuredStates(iCombineMeasuredStates3, childAt6.getMeasuredState() & (-256));
                            i56 = i59;
                        }
                        int i60 = ((LinearLayout.LayoutParams) m1Var7).leftMargin + ((LinearLayout.LayoutParams) m1Var7).rightMargin;
                        int measuredWidth3 = childAt6.getMeasuredWidth() + i60;
                        int iMax7 = Math.max(i51, measuredWidth3);
                        if (i47 != 1073741824) {
                            i27 = iMax7;
                            i28 = -1;
                            z7 = ((LinearLayout.LayoutParams) m1Var7).width == -1;
                            if (!z7) {
                                i60 = measuredWidth3;
                            }
                            int iMax8 = Math.max(iMax6, i60);
                            if (z13 || ((LinearLayout.LayoutParams) m1Var7).width != i28) {
                                z8 = false;
                            } else {
                                z8 = true;
                            }
                            int i61 = this.f1327g;
                            this.f1327g = Math.max(i61, childAt6.getMeasuredHeight() + i61 + ((LinearLayout.LayoutParams) m1Var7).topMargin + ((LinearLayout.LayoutParams) m1Var7).bottomMargin + 0);
                            z13 = z8;
                            i51 = i27;
                            iMax6 = iMax8;
                        } else {
                            i27 = iMax7;
                            i28 = -1;
                        }
                        if (!z7) {
                            i60 = measuredWidth3;
                        }
                        int iMax9 = Math.max(iMax6, i60);
                        if (z13) {
                            z8 = false;
                        } else {
                            z8 = false;
                        }
                        int i62 = this.f1327g;
                        this.f1327g = Math.max(i62, childAt6.getMeasuredHeight() + i62 + ((LinearLayout.LayoutParams) m1Var7).topMargin + ((LinearLayout.LayoutParams) m1Var7).bottomMargin + 0);
                        z13 = z8;
                        i51 = i27;
                        iMax6 = iMax9;
                    }
                    i57++;
                    i52 = i52;
                }
                this.f1327g = getPaddingBottom() + getPaddingTop() + this.f1327g;
            } else {
                iMax6 = Math.max(iMax6, i50);
                if (z11 && i52 != 1073741824) {
                    for (int i63 = 0; i63 < i48; i63++) {
                        View childAt7 = getChildAt(i63);
                        if (childAt7 != null && childAt7.getVisibility() != 8 && ((LinearLayout.LayoutParams) ((m1) childAt7.getLayoutParams())).weight > 0.0f) {
                            childAt7.measure(View.MeasureSpec.makeMeasureSpec(childAt7.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(i49, 1073741824));
                        }
                    }
                }
            }
            int i64 = i51;
            if (z13 || i47 == 1073741824) {
                iMax6 = i64;
            }
            setMeasuredDimension(View.resolveSizeAndState(Math.max(getPaddingRight() + getPaddingLeft() + iMax6, getSuggestedMinimumWidth()), i2, iCombineMeasuredStates3), iResolveSizeAndState);
            if (z14) {
                int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
                for (int i65 = 0; i65 < i48; i65++) {
                    View childAt8 = getChildAt(i65);
                    if (childAt8.getVisibility() != 8) {
                        m1 m1Var8 = (m1) childAt8.getLayoutParams();
                        if (((LinearLayout.LayoutParams) m1Var8).width == -1) {
                            int i66 = ((LinearLayout.LayoutParams) m1Var8).height;
                            ((LinearLayout.LayoutParams) m1Var8).height = childAt8.getMeasuredHeight();
                            measureChildWithMargins(childAt8, iMakeMeasureSpec2, 0, i3, 0);
                            ((LinearLayout.LayoutParams) m1Var8).height = i66;
                        }
                    }
                }
                return;
            }
            return;
        }
        this.f1327g = 0;
        int virtualChildCount2 = getVirtualChildCount();
        int mode3 = View.MeasureSpec.getMode(i2);
        int mode4 = View.MeasureSpec.getMode(i3);
        if (this.f1330j == null || this.f1331k == null) {
            this.f1330j = new int[4];
            this.f1331k = new int[4];
        }
        int[] iArr = this.f1330j;
        int[] iArr2 = this.f1331k;
        iArr[3] = -1;
        iArr[2] = -1;
        iArr[1] = -1;
        iArr[0] = -1;
        iArr2[3] = -1;
        iArr2[2] = -1;
        iArr2[1] = -1;
        iArr2[0] = -1;
        boolean z16 = this.f1322b;
        boolean z17 = this.f1329i;
        boolean z18 = mode3 == 1073741824;
        int i67 = 0;
        float f9 = 0.0f;
        int iMax10 = 0;
        int i68 = 0;
        int iMax11 = 0;
        int iMax12 = 0;
        boolean z19 = true;
        boolean z20 = false;
        boolean z21 = false;
        int i69 = 0;
        while (i68 < virtualChildCount2) {
            View childAt9 = getChildAt(i68);
            if (childAt9 == null) {
                this.f1327g += 0;
                i17 = i67;
                i18 = iMax10;
            } else {
                i17 = i67;
                i18 = iMax10;
                if (childAt9.getVisibility() == 8) {
                    i68 += 0;
                } else {
                    if (j(i68)) {
                        this.f1327g += this.f1333m;
                    }
                    m1 m1Var9 = (m1) childAt9.getLayoutParams();
                    float f10 = ((LinearLayout.LayoutParams) m1Var9).weight;
                    float f11 = f9 + f10;
                    if (mode3 == 1073741824 && ((LinearLayout.LayoutParams) m1Var9).width == 0 && f10 > 0.0f) {
                        if (z18) {
                            this.f1327g = ((LinearLayout.LayoutParams) m1Var9).leftMargin + ((LinearLayout.LayoutParams) m1Var9).rightMargin + this.f1327g;
                        } else {
                            int i70 = this.f1327g;
                            this.f1327g = Math.max(i70, ((LinearLayout.LayoutParams) m1Var9).leftMargin + i70 + ((LinearLayout.LayoutParams) m1Var9).rightMargin);
                        }
                        if (z16) {
                            int iMakeMeasureSpec3 = View.MeasureSpec.makeMeasureSpec(0, 0);
                            childAt9.measure(iMakeMeasureSpec3, iMakeMeasureSpec3);
                            m1Var3 = m1Var9;
                            i21 = i17;
                            i22 = i18;
                            i24 = i68;
                            z2 = z17;
                            z3 = z16;
                        } else {
                            m1Var3 = m1Var9;
                            i21 = i17;
                            i22 = i18;
                            i24 = i68;
                            i25 = 1073741824;
                            z2 = z17;
                            z3 = z16;
                            z4 = true;
                        }
                        if (mode4 == i25 && ((LinearLayout.LayoutParams) m1Var3).height == -1) {
                            z5 = true;
                            z21 = true;
                        } else {
                            z5 = false;
                        }
                        i26 = ((LinearLayout.LayoutParams) m1Var3).topMargin + ((LinearLayout.LayoutParams) m1Var3).bottomMargin;
                        measuredHeight = childAt9.getMeasuredHeight() + i26;
                        int iCombineMeasuredStates4 = View.combineMeasuredStates(i21, childAt9.getMeasuredState());
                        if (!z3 && (baseline2 = childAt9.getBaseline()) != -1) {
                            int i71 = ((LinearLayout.LayoutParams) m1Var3).gravity;
                            if (i71 < 0) {
                                i71 = this.f1326f;
                            }
                            int i72 = (((i71 & 112) >> 4) & (-2)) >> 1;
                            iArr[i72] = Math.max(iArr[i72], baseline2);
                            iArr2[i72] = Math.max(iArr2[i72], measuredHeight - baseline2);
                        }
                        int iMax13 = Math.max(i69, measuredHeight);
                        if (z19 || ((LinearLayout.LayoutParams) m1Var3).height != -1) {
                            z6 = false;
                        } else {
                            z6 = true;
                        }
                        if (((LinearLayout.LayoutParams) m1Var3).weight > 0.0f) {
                            if (z5) {
                                measuredHeight = i26;
                            }
                            iMax10 = Math.max(i22, measuredHeight);
                        } else {
                            int i73 = i22;
                            if (z5) {
                                measuredHeight = i26;
                            }
                            iMax12 = Math.max(iMax12, measuredHeight);
                            iMax10 = i73;
                        }
                        i69 = iMax13;
                        z19 = z6;
                        z20 = z4;
                        i67 = iCombineMeasuredStates4;
                        i68 = i24 + 0;
                        f9 = f11;
                    } else {
                        int i74 = i68;
                        if (((LinearLayout.LayoutParams) m1Var9).width == 0) {
                            f3 = 0.0f;
                            if (f10 > 0.0f) {
                                ((LinearLayout.LayoutParams) m1Var9).width = -2;
                                i19 = 0;
                            }
                            if (f11 == f3) {
                                i20 = this.f1327g;
                            } else {
                                i20 = 0;
                            }
                            i21 = i17;
                            i22 = i18;
                            i23 = i19;
                            i24 = i74;
                            z2 = z17;
                            z3 = z16;
                            measureChildWithMargins(childAt9, i2, i20, i3, 0);
                            if (i23 != Integer.MIN_VALUE) {
                                m1Var3 = m1Var9;
                                ((LinearLayout.LayoutParams) m1Var3).width = i23;
                            } else {
                                m1Var3 = m1Var9;
                            }
                            measuredWidth = childAt9.getMeasuredWidth();
                            if (z18) {
                                this.f1327g = ((LinearLayout.LayoutParams) m1Var3).leftMargin + measuredWidth + ((LinearLayout.LayoutParams) m1Var3).rightMargin + 0 + this.f1327g;
                            } else {
                                int i75 = this.f1327g;
                                this.f1327g = Math.max(i75, i75 + measuredWidth + ((LinearLayout.LayoutParams) m1Var3).leftMargin + ((LinearLayout.LayoutParams) m1Var3).rightMargin + 0);
                            }
                            if (z2) {
                                iMax11 = Math.max(measuredWidth, iMax11);
                            }
                        } else {
                            f3 = 0.0f;
                        }
                        i19 = Integer.MIN_VALUE;
                        if (f11 == f3) {
                            i20 = this.f1327g;
                        } else {
                            i20 = 0;
                        }
                        i21 = i17;
                        i22 = i18;
                        i23 = i19;
                        i24 = i74;
                        z2 = z17;
                        z3 = z16;
                        measureChildWithMargins(childAt9, i2, i20, i3, 0);
                        if (i23 != Integer.MIN_VALUE) {
                            m1Var3 = m1Var9;
                            ((LinearLayout.LayoutParams) m1Var3).width = i23;
                        } else {
                            m1Var3 = m1Var9;
                        }
                        measuredWidth = childAt9.getMeasuredWidth();
                        if (z18) {
                            this.f1327g = ((LinearLayout.LayoutParams) m1Var3).leftMargin + measuredWidth + ((LinearLayout.LayoutParams) m1Var3).rightMargin + 0 + this.f1327g;
                        } else {
                            int i76 = this.f1327g;
                            this.f1327g = Math.max(i76, i76 + measuredWidth + ((LinearLayout.LayoutParams) m1Var3).leftMargin + ((LinearLayout.LayoutParams) m1Var3).rightMargin + 0);
                        }
                        if (z2) {
                            iMax11 = Math.max(measuredWidth, iMax11);
                        }
                    }
                    z4 = z20;
                    i25 = 1073741824;
                    if (mode4 == i25) {
                        z5 = false;
                    } else {
                        z5 = false;
                    }
                    i26 = ((LinearLayout.LayoutParams) m1Var3).topMargin + ((LinearLayout.LayoutParams) m1Var3).bottomMargin;
                    measuredHeight = childAt9.getMeasuredHeight() + i26;
                    int iCombineMeasuredStates5 = View.combineMeasuredStates(i21, childAt9.getMeasuredState());
                    if (!z3) {
                    }
                    int iMax14 = Math.max(i69, measuredHeight);
                    if (z19) {
                        z6 = false;
                    } else {
                        z6 = false;
                    }
                    if (((LinearLayout.LayoutParams) m1Var3).weight > 0.0f) {
                        if (z5) {
                            measuredHeight = i26;
                        }
                        iMax10 = Math.max(i22, measuredHeight);
                    } else {
                        int i77 = i22;
                        if (z5) {
                            measuredHeight = i26;
                        }
                        iMax12 = Math.max(iMax12, measuredHeight);
                        iMax10 = i77;
                    }
                    i69 = iMax14;
                    z19 = z6;
                    z20 = z4;
                    i67 = iCombineMeasuredStates5;
                    i68 = i24 + 0;
                    f9 = f11;
                }
                i68++;
                z17 = z2;
                z16 = z3;
            }
            z3 = z16;
            i67 = i17;
            iMax10 = i18;
            z2 = z17;
            i68++;
            z17 = z2;
            z16 = z3;
        }
        int i78 = iMax10;
        boolean z22 = z17;
        boolean z23 = z16;
        int i79 = i69;
        if (this.f1327g > 0 && j(virtualChildCount2)) {
            this.f1327g += this.f1333m;
        }
        int i80 = iArr[1];
        int i81 = i67;
        if (i80 == -1 && iArr[0] == -1 && iArr[2] == -1) {
            c2 = 3;
            if (iArr[3] == -1) {
                iMax = i79;
            }
            if (z22 && (mode3 == Integer.MIN_VALUE || mode3 == 0)) {
                i15 = 0;
                this.f1327g = 0;
                i16 = 0;
                while (i16 < virtualChildCount2) {
                    childAt3 = getChildAt(i16);
                    if (childAt3 == null) {
                        this.f1327g += i15;
                    } else if (childAt3.getVisibility() == 8) {
                        i16 += 0;
                    } else {
                        m1Var2 = (m1) childAt3.getLayoutParams();
                        if (z18) {
                            this.f1327g = ((LinearLayout.LayoutParams) m1Var2).leftMargin + iMax11 + ((LinearLayout.LayoutParams) m1Var2).rightMargin + 0 + this.f1327g;
                        } else {
                            int i82 = this.f1327g;
                            this.f1327g = Math.max(i82, i82 + iMax11 + ((LinearLayout.LayoutParams) m1Var2).leftMargin + ((LinearLayout.LayoutParams) m1Var2).rightMargin + 0);
                        }
                    }
                    i16++;
                    i15 = 0;
                }
            }
            int paddingRight = getPaddingRight() + getPaddingLeft() + this.f1327g;
            this.f1327g = paddingRight;
            int iResolveSizeAndState2 = View.resolveSizeAndState(Math.max(paddingRight, getSuggestedMinimumWidth()), i2, 0);
            i4 = (16777215 & iResolveSizeAndState2) - this.f1327g;
            if (!z20 || (i4 != 0 && f9 > 0.0f)) {
                f2 = this.f1328h;
                if (f2 > 0.0f) {
                    f9 = f2;
                }
                iArr[3] = -1;
                iArr[2] = -1;
                iArr[1] = -1;
                iArr[0] = -1;
                iArr2[3] = -1;
                iArr2[2] = -1;
                iArr2[1] = -1;
                iArr2[0] = -1;
                this.f1327g = 0;
                i5 = i4;
                int iMax15 = -1;
                i6 = 0;
                float f12 = f9;
                iCombineMeasuredStates = i81;
                while (i6 < virtualChildCount2) {
                    childAt = getChildAt(i6);
                    if (childAt != null || childAt.getVisibility() == 8) {
                        i11 = i5;
                        i12 = mode4;
                    } else {
                        m1 m1Var10 = (m1) childAt.getLayoutParams();
                        float f13 = ((LinearLayout.LayoutParams) m1Var10).weight;
                        if (f13 > 0.0f) {
                            int measuredWidth4 = (int) ((i5 * f13) / f12);
                            float f14 = f12 - f13;
                            int i83 = i5 - measuredWidth4;
                            int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i3, getPaddingBottom() + getPaddingTop() + ((LinearLayout.LayoutParams) m1Var10).topMargin + ((LinearLayout.LayoutParams) m1Var10).bottomMargin, ((LinearLayout.LayoutParams) m1Var10).height);
                            if (((LinearLayout.LayoutParams) m1Var10).width != 0 || mode3 != 1073741824 ? (measuredWidth4 = measuredWidth4 + childAt.getMeasuredWidth()) < 0 : measuredWidth4 <= 0) {
                                measuredWidth4 = 0;
                            }
                            childAt.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth4, 1073741824), childMeasureSpec2);
                            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, childAt.getMeasuredState() & (-16777216));
                            f12 = f14;
                            i13 = i83;
                        } else {
                            i13 = i5;
                        }
                        if (z18) {
                            this.f1327g = childAt.getMeasuredWidth() + ((LinearLayout.LayoutParams) m1Var10).leftMargin + ((LinearLayout.LayoutParams) m1Var10).rightMargin + 0 + this.f1327g;
                        } else {
                            int i84 = this.f1327g;
                            this.f1327g = Math.max(i84, childAt.getMeasuredWidth() + i84 + ((LinearLayout.LayoutParams) m1Var10).leftMargin + ((LinearLayout.LayoutParams) m1Var10).rightMargin + 0);
                        }
                        i12 = mode4;
                        boolean z24 = i12 != 1073741824 && ((LinearLayout.LayoutParams) m1Var10).height == -1;
                        int i85 = i13;
                        int i86 = ((LinearLayout.LayoutParams) m1Var10).topMargin + ((LinearLayout.LayoutParams) m1Var10).bottomMargin;
                        int measuredHeight4 = childAt.getMeasuredHeight() + i86;
                        iMax15 = Math.max(iMax15, measuredHeight4);
                        if (!z24) {
                            i86 = measuredHeight4;
                        }
                        int iMax16 = Math.max(iMax12, i86);
                        if (z19) {
                            i14 = -1;
                            boolean z25 = ((LinearLayout.LayoutParams) m1Var10).height == -1;
                            if (!z23 && (baseline = childAt.getBaseline()) != i14) {
                                int i87 = ((LinearLayout.LayoutParams) m1Var10).gravity;
                                if (i87 < 0) {
                                    i87 = this.f1326f;
                                }
                                int i88 = (((i87 & 112) >> 4) & (-2)) >> 1;
                                iArr[i88] = Math.max(iArr[i88], baseline);
                                iArr2[i88] = Math.max(iArr2[i88], measuredHeight4 - baseline);
                            }
                            iMax12 = iMax16;
                            z19 = z25;
                            i11 = i85;
                            iCombineMeasuredStates = iCombineMeasuredStates;
                            f12 = f12;
                        } else {
                            i14 = -1;
                        }
                        if (!z23) {
                        }
                        iMax12 = iMax16;
                        z19 = z25;
                        i11 = i85;
                        iCombineMeasuredStates = iCombineMeasuredStates;
                        f12 = f12;
                    }
                    i6++;
                    i5 = i11;
                    mode4 = i12;
                }
                i7 = i3;
                i8 = mode4;
                this.f1327g = getPaddingRight() + getPaddingLeft() + this.f1327g;
                i9 = iArr[1];
                if (i9 != -1 && iArr[0] == -1 && iArr[2] == -1) {
                    c3 = 3;
                    if (iArr[3] == -1) {
                        iMax = iMax15;
                    }
                    if (z19 || i8 == 1073741824) {
                        iMax12 = iMax;
                    }
                    setMeasuredDimension(iResolveSizeAndState2 | ((-16777216) & iCombineMeasuredStates), View.resolveSizeAndState(Math.max(getPaddingBottom() + getPaddingTop() + iMax12, getSuggestedMinimumHeight()), i7, iCombineMeasuredStates << 16));
                    if (z21) {
                        iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
                        while (i10 < virtualChildCount2) {
                            childAt2 = getChildAt(i10);
                            if (childAt2.getVisibility() != 8) {
                                m1Var = (m1) childAt2.getLayoutParams();
                                if (((LinearLayout.LayoutParams) m1Var).height == -1) {
                                    int i89 = ((LinearLayout.LayoutParams) m1Var).width;
                                    ((LinearLayout.LayoutParams) m1Var).width = childAt2.getMeasuredWidth();
                                    measureChildWithMargins(childAt2, i2, 0, iMakeMeasureSpec, 0);
                                    ((LinearLayout.LayoutParams) m1Var).width = i89;
                                }
                            }
                            i10++;
                        }
                    }
                }
                c3 = 3;
                i10 = 0;
                iMax = Math.max(iMax15, Math.max(iArr2[c3], Math.max(iArr2[0], Math.max(iArr2[1], iArr2[2]))) + Math.max(iArr[c3], Math.max(iArr[0], Math.max(i9, iArr[2]))));
                if (z19) {
                    iMax12 = iMax;
                } else {
                    iMax12 = iMax;
                }
                setMeasuredDimension(iResolveSizeAndState2 | ((-16777216) & iCombineMeasuredStates), View.resolveSizeAndState(Math.max(getPaddingBottom() + getPaddingTop() + iMax12, getSuggestedMinimumHeight()), i7, iCombineMeasuredStates << 16));
                if (z21) {
                    iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
                    while (i10 < virtualChildCount2) {
                        childAt2 = getChildAt(i10);
                        if (childAt2.getVisibility() != 8) {
                            m1Var = (m1) childAt2.getLayoutParams();
                            if (((LinearLayout.LayoutParams) m1Var).height == -1) {
                                int i810 = ((LinearLayout.LayoutParams) m1Var).width;
                                ((LinearLayout.LayoutParams) m1Var).width = childAt2.getMeasuredWidth();
                                measureChildWithMargins(childAt2, i2, 0, iMakeMeasureSpec, 0);
                                ((LinearLayout.LayoutParams) m1Var).width = i810;
                            }
                        }
                        i10++;
                    }
                }
            }
            int iMax17 = Math.max(iMax12, i78);
            if (z22 && mode3 != 1073741824) {
                for (int i90 = 0; i90 < virtualChildCount2; i90++) {
                    View childAt10 = getChildAt(i90);
                    if (childAt10 != null && childAt10.getVisibility() != 8 && ((LinearLayout.LayoutParams) ((m1) childAt10.getLayoutParams())).weight > 0.0f) {
                        childAt10.measure(View.MeasureSpec.makeMeasureSpec(iMax11, 1073741824), View.MeasureSpec.makeMeasureSpec(childAt10.getMeasuredHeight(), 1073741824));
                    }
                }
            }
            i7 = i3;
            iMax12 = iMax17;
            iCombineMeasuredStates = i81;
            i8 = mode4;
            i10 = 0;
            if (z19) {
                iMax12 = iMax;
            } else {
                iMax12 = iMax;
            }
            setMeasuredDimension(iResolveSizeAndState2 | ((-16777216) & iCombineMeasuredStates), View.resolveSizeAndState(Math.max(getPaddingBottom() + getPaddingTop() + iMax12, getSuggestedMinimumHeight()), i7, iCombineMeasuredStates << 16));
            if (z21) {
                iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
                while (i10 < virtualChildCount2) {
                    childAt2 = getChildAt(i10);
                    if (childAt2.getVisibility() != 8) {
                        m1Var = (m1) childAt2.getLayoutParams();
                        if (((LinearLayout.LayoutParams) m1Var).height == -1) {
                            int i811 = ((LinearLayout.LayoutParams) m1Var).width;
                            ((LinearLayout.LayoutParams) m1Var).width = childAt2.getMeasuredWidth();
                            measureChildWithMargins(childAt2, i2, 0, iMakeMeasureSpec, 0);
                            ((LinearLayout.LayoutParams) m1Var).width = i811;
                        }
                    }
                    i10++;
                }
            }
        }
        c2 = 3;
        iMax = Math.max(i79, Math.max(iArr2[3], Math.max(iArr2[0], Math.max(iArr2[1], iArr2[2]))) + Math.max(iArr[c2], Math.max(iArr[0], Math.max(i80, iArr[2]))));
        if (z22) {
            i15 = 0;
            this.f1327g = 0;
            i16 = 0;
            while (i16 < virtualChildCount2) {
                childAt3 = getChildAt(i16);
                if (childAt3 == null) {
                    this.f1327g += i15;
                } else if (childAt3.getVisibility() == 8) {
                    i16 += 0;
                } else {
                    m1Var2 = (m1) childAt3.getLayoutParams();
                    if (z18) {
                        this.f1327g = ((LinearLayout.LayoutParams) m1Var2).leftMargin + iMax11 + ((LinearLayout.LayoutParams) m1Var2).rightMargin + 0 + this.f1327g;
                    } else {
                        int i812 = this.f1327g;
                        this.f1327g = Math.max(i812, i812 + iMax11 + ((LinearLayout.LayoutParams) m1Var2).leftMargin + ((LinearLayout.LayoutParams) m1Var2).rightMargin + 0);
                    }
                }
                i16++;
                i15 = 0;
            }
        }
        int paddingRight2 = getPaddingRight() + getPaddingLeft() + this.f1327g;
        this.f1327g = paddingRight2;
        int iResolveSizeAndState3 = View.resolveSizeAndState(Math.max(paddingRight2, getSuggestedMinimumWidth()), i2, 0);
        i4 = (16777215 & iResolveSizeAndState3) - this.f1327g;
        if (z20) {
            f2 = this.f1328h;
            if (f2 > 0.0f) {
                f9 = f2;
            }
            iArr[3] = -1;
            iArr[2] = -1;
            iArr[1] = -1;
            iArr[0] = -1;
            iArr2[3] = -1;
            iArr2[2] = -1;
            iArr2[1] = -1;
            iArr2[0] = -1;
            this.f1327g = 0;
            i5 = i4;
            int iMax18 = -1;
            i6 = 0;
            float f15 = f9;
            iCombineMeasuredStates = i81;
            while (i6 < virtualChildCount2) {
                childAt = getChildAt(i6);
                if (childAt != null) {
                    i11 = i5;
                    i12 = mode4;
                } else {
                    i11 = i5;
                    i12 = mode4;
                }
                i6++;
                i5 = i11;
                mode4 = i12;
            }
            i7 = i3;
            i8 = mode4;
            this.f1327g = getPaddingRight() + getPaddingLeft() + this.f1327g;
            i9 = iArr[1];
            if (i9 != -1) {
                c3 = 3;
            } else {
                c3 = 3;
            }
            i10 = 0;
            iMax = Math.max(iMax18, Math.max(iArr2[c3], Math.max(iArr2[0], Math.max(iArr2[1], iArr2[2]))) + Math.max(iArr[c3], Math.max(iArr[0], Math.max(i9, iArr[2]))));
        } else {
            f2 = this.f1328h;
            if (f2 > 0.0f) {
                f9 = f2;
            }
            iArr[3] = -1;
            iArr[2] = -1;
            iArr[1] = -1;
            iArr[0] = -1;
            iArr2[3] = -1;
            iArr2[2] = -1;
            iArr2[1] = -1;
            iArr2[0] = -1;
            this.f1327g = 0;
            i5 = i4;
            int iMax19 = -1;
            i6 = 0;
            float f16 = f9;
            iCombineMeasuredStates = i81;
            while (i6 < virtualChildCount2) {
                childAt = getChildAt(i6);
                if (childAt != null) {
                    i11 = i5;
                    i12 = mode4;
                } else {
                    i11 = i5;
                    i12 = mode4;
                }
                i6++;
                i5 = i11;
                mode4 = i12;
            }
            i7 = i3;
            i8 = mode4;
            this.f1327g = getPaddingRight() + getPaddingLeft() + this.f1327g;
            i9 = iArr[1];
            if (i9 != -1) {
                c3 = 3;
            } else {
                c3 = 3;
            }
            i10 = 0;
            iMax = Math.max(iMax19, Math.max(iArr2[c3], Math.max(iArr2[0], Math.max(iArr2[1], iArr2[2]))) + Math.max(iArr[c3], Math.max(iArr[0], Math.max(i9, iArr[2]))));
        }
        if (z19) {
            iMax12 = iMax;
        } else {
            iMax12 = iMax;
        }
        setMeasuredDimension(iResolveSizeAndState3 | ((-16777216) & iCombineMeasuredStates), View.resolveSizeAndState(Math.max(getPaddingBottom() + getPaddingTop() + iMax12, getSuggestedMinimumHeight()), i7, iCombineMeasuredStates << 16));
        if (z21) {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
            while (i10 < virtualChildCount2) {
                childAt2 = getChildAt(i10);
                if (childAt2.getVisibility() != 8) {
                    m1Var = (m1) childAt2.getLayoutParams();
                    if (((LinearLayout.LayoutParams) m1Var).height == -1) {
                        int i813 = ((LinearLayout.LayoutParams) m1Var).width;
                        ((LinearLayout.LayoutParams) m1Var).width = childAt2.getMeasuredWidth();
                        measureChildWithMargins(childAt2, i2, 0, iMakeMeasureSpec, 0);
                        ((LinearLayout.LayoutParams) m1Var).width = i813;
                    }
                }
                i10++;
            }
        }
    }

    public void setBaselineAligned(boolean z2) {
        this.f1322b = z2;
    }

    public void setBaselineAlignedChildIndex(int i2) {
        if (i2 >= 0 && i2 < getChildCount()) {
            this.f1323c = i2;
            return;
        }
        throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")");
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable == this.f1332l) {
            return;
        }
        this.f1332l = drawable;
        if (drawable != null) {
            this.f1333m = drawable.getIntrinsicWidth();
            this.f1334n = drawable.getIntrinsicHeight();
        } else {
            this.f1333m = 0;
            this.f1334n = 0;
        }
        setWillNotDraw(drawable == null);
        requestLayout();
    }

    public void setDividerPadding(int i2) {
        this.f1336p = i2;
    }

    public void setGravity(int i2) {
        if (this.f1326f != i2) {
            if ((8388615 & i2) == 0) {
                i2 |= 8388611;
            }
            if ((i2 & 112) == 0) {
                i2 |= 48;
            }
            this.f1326f = i2;
            requestLayout();
        }
    }

    public void setHorizontalGravity(int i2) {
        int i3 = i2 & 8388615;
        int i4 = this.f1326f;
        if ((8388615 & i4) != i3) {
            this.f1326f = i3 | ((-8388616) & i4);
            requestLayout();
        }
    }

    public void setMeasureWithLargestChildEnabled(boolean z2) {
        this.f1329i = z2;
    }

    public void setOrientation(int i2) {
        if (this.f1325e != i2) {
            this.f1325e = i2;
            requestLayout();
        }
    }

    public void setShowDividers(int i2) {
        if (i2 != this.f1335o) {
            requestLayout();
        }
        this.f1335o = i2;
    }

    public void setVerticalGravity(int i2) {
        int i3 = i2 & 112;
        int i4 = this.f1326f;
        if ((i4 & 112) != i3) {
            this.f1326f = i3 | (i4 & (-113));
            requestLayout();
        }
    }

    public void setWeightSum(float f2) {
        this.f1328h = Math.max(0.0f, f2);
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }
}
