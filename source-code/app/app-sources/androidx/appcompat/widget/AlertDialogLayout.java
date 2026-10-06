package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.snapay.app.R;
import j.m1;
import j.n1;
import java.util.WeakHashMap;
import x.u;

/* JADX INFO: loaded from: classes.dex */
public class AlertDialogLayout extends n1 {
    public AlertDialogLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
    }

    public static int k(View view) {
        WeakHashMap weakHashMap = u.f2012a;
        int minimumHeight = view.getMinimumHeight();
        if (minimumHeight > 0) {
            return minimumHeight;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (viewGroup.getChildCount() == 1) {
                return k(viewGroup.getChildAt(0));
            }
        }
        return 0;
    }

    /* JADX WARN: Code duplicated, block: B:31:0x009d  */
    @Override // j.n1, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        int paddingLeft = getPaddingLeft();
        int i8 = i4 - i2;
        int paddingRight = i8 - getPaddingRight();
        int paddingRight2 = (i8 - paddingLeft) - getPaddingRight();
        int measuredHeight = getMeasuredHeight();
        int childCount = getChildCount();
        int gravity = getGravity();
        int i9 = gravity & 112;
        int i10 = gravity & 8388615;
        int paddingTop = i9 != 16 ? i9 != 80 ? getPaddingTop() : ((getPaddingTop() + i5) - i3) - measuredHeight : (((i5 - i3) - measuredHeight) / 2) + getPaddingTop();
        Drawable dividerDrawable = getDividerDrawable();
        int intrinsicHeight = dividerDrawable == null ? 0 : dividerDrawable.getIntrinsicHeight();
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            if (childAt != null && childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight2 = childAt.getMeasuredHeight();
                m1 m1Var = (m1) childAt.getLayoutParams();
                int i12 = ((LinearLayout.LayoutParams) m1Var).gravity;
                if (i12 < 0) {
                    i12 = i10;
                }
                WeakHashMap weakHashMap = u.f2012a;
                int absoluteGravity = Gravity.getAbsoluteGravity(i12, getLayoutDirection()) & 7;
                if (absoluteGravity != 1) {
                    if (absoluteGravity != 5) {
                        i7 = ((LinearLayout.LayoutParams) m1Var).leftMargin + paddingLeft;
                    } else {
                        i6 = paddingRight - measuredWidth;
                    }
                    if (j(i11)) {
                        paddingTop += intrinsicHeight;
                    }
                    int i13 = paddingTop + ((LinearLayout.LayoutParams) m1Var).topMargin;
                    childAt.layout(i7, i13, measuredWidth + i7, measuredHeight2 + i13);
                    paddingTop = measuredHeight2 + ((LinearLayout.LayoutParams) m1Var).bottomMargin + i13;
                } else {
                    i6 = ((paddingRight2 - measuredWidth) / 2) + paddingLeft + ((LinearLayout.LayoutParams) m1Var).leftMargin;
                }
                i7 = i6 - ((LinearLayout.LayoutParams) m1Var).rightMargin;
                if (j(i11)) {
                    paddingTop += intrinsicHeight;
                }
                int i14 = paddingTop + ((LinearLayout.LayoutParams) m1Var).topMargin;
                childAt.layout(i7, i14, measuredWidth + i7, measuredHeight2 + i14);
                paddingTop = measuredHeight2 + ((LinearLayout.LayoutParams) m1Var).bottomMargin + i14;
            }
        }
    }

    @Override // j.n1, android.view.View
    public final void onMeasure(int i2, int i3) {
        int iCombineMeasuredStates;
        int iK;
        int measuredHeight;
        int measuredHeight2;
        int childCount = getChildCount();
        boolean z2 = false;
        View view = null;
        View view2 = null;
        View view3 = null;
        int i4 = 0;
        while (true) {
            if (i4 >= childCount) {
                int mode = View.MeasureSpec.getMode(i3);
                int size = View.MeasureSpec.getSize(i3);
                int mode2 = View.MeasureSpec.getMode(i2);
                int paddingBottom = getPaddingBottom() + getPaddingTop();
                if (view != null) {
                    view.measure(i2, 0);
                    paddingBottom += view.getMeasuredHeight();
                    iCombineMeasuredStates = View.combineMeasuredStates(0, view.getMeasuredState());
                } else {
                    iCombineMeasuredStates = 0;
                }
                if (view2 != null) {
                    view2.measure(i2, 0);
                    iK = k(view2);
                    measuredHeight = view2.getMeasuredHeight() - iK;
                    paddingBottom += iK;
                    iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, view2.getMeasuredState());
                } else {
                    iK = 0;
                    measuredHeight = 0;
                }
                if (view3 != null) {
                    view3.measure(i2, mode == 0 ? 0 : View.MeasureSpec.makeMeasureSpec(Math.max(0, size - paddingBottom), mode));
                    measuredHeight2 = view3.getMeasuredHeight();
                    paddingBottom += measuredHeight2;
                    iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, view3.getMeasuredState());
                } else {
                    measuredHeight2 = 0;
                }
                int i5 = size - paddingBottom;
                if (view2 != null) {
                    int i6 = paddingBottom - iK;
                    int iMin = Math.min(i5, measuredHeight);
                    if (iMin > 0) {
                        i5 -= iMin;
                        iK += iMin;
                    }
                    view2.measure(i2, View.MeasureSpec.makeMeasureSpec(iK, 1073741824));
                    paddingBottom = i6 + view2.getMeasuredHeight();
                    iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, view2.getMeasuredState());
                }
                if (view3 != null && i5 > 0) {
                    view3.measure(i2, View.MeasureSpec.makeMeasureSpec(measuredHeight2 + i5, mode));
                    paddingBottom = (paddingBottom - measuredHeight2) + view3.getMeasuredHeight();
                    iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, view3.getMeasuredState());
                }
                int iMax = 0;
                for (int i7 = 0; i7 < childCount; i7++) {
                    View childAt = getChildAt(i7);
                    if (childAt.getVisibility() != 8) {
                        iMax = Math.max(iMax, childAt.getMeasuredWidth());
                    }
                }
                setMeasuredDimension(View.resolveSizeAndState(getPaddingRight() + getPaddingLeft() + iMax, i2, iCombineMeasuredStates), View.resolveSizeAndState(paddingBottom, i3, 0));
                if (mode2 != 1073741824) {
                    int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
                    for (int i8 = 0; i8 < childCount; i8++) {
                        View childAt2 = getChildAt(i8);
                        if (childAt2.getVisibility() != 8) {
                            m1 m1Var = (m1) childAt2.getLayoutParams();
                            if (((LinearLayout.LayoutParams) m1Var).width == -1) {
                                int i9 = ((LinearLayout.LayoutParams) m1Var).height;
                                ((LinearLayout.LayoutParams) m1Var).height = childAt2.getMeasuredHeight();
                                measureChildWithMargins(childAt2, iMakeMeasureSpec, 0, i3, 0);
                                ((LinearLayout.LayoutParams) m1Var).height = i9;
                            }
                        }
                    }
                }
                z2 = true;
                break;
            }
            View childAt3 = getChildAt(i4);
            if (childAt3.getVisibility() != 8) {
                int id = childAt3.getId();
                if (id == R.id.topPanel) {
                    view = childAt3;
                } else if (id == R.id.buttonPanel) {
                    view2 = childAt3;
                } else if ((id != R.id.contentPanel && id != R.id.customPanel) || view3 != null) {
                    break;
                } else {
                    view3 = childAt3;
                }
            }
            i4++;
        }
        if (z2) {
            return;
        }
        super.onMeasure(i2, i3);
    }
}
