package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.snapay.app.R;
import java.util.WeakHashMap;
import x.u;

/* JADX INFO: loaded from: classes.dex */
public class ButtonBarLayout extends LinearLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f177b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f178c;

    public ButtonBarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f178c = -1;
        int[] iArr = c.a.f491k;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr);
        u.d(this, context, iArr, attributeSet, typedArrayObtainStyledAttributes, 0);
        this.f177b = typedArrayObtainStyledAttributes.getBoolean(0, true);
        typedArrayObtainStyledAttributes.recycle();
    }

    private void setStacked(boolean z2) {
        setOrientation(z2 ? 1 : 0);
        setGravity(z2 ? 8388613 : 80);
        View viewFindViewById = findViewById(R.id.spacer);
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(z2 ? 8 : 4);
        }
        for (int childCount = getChildCount() - 2; childCount >= 0; childCount--) {
            bringChildToFront(getChildAt(childCount));
        }
    }

    @Override // android.view.View
    public int getMinimumHeight() {
        return Math.max(0, super.getMinimumHeight());
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i2, int i3) {
        int iMakeMeasureSpec;
        boolean z2;
        int i4;
        int paddingBottom;
        int size = View.MeasureSpec.getSize(i2);
        int i5 = 0;
        if (this.f177b) {
            if (size > this.f178c) {
                if (getOrientation() == 1) {
                    setStacked(false);
                }
            }
            this.f178c = size;
        }
        if ((getOrientation() == 1) || View.MeasureSpec.getMode(i2) != 1073741824) {
            iMakeMeasureSpec = i2;
            z2 = false;
        } else {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE);
            z2 = true;
        }
        super.onMeasure(iMakeMeasureSpec, i3);
        if (this.f177b) {
            if (!(getOrientation() == 1)) {
                if ((getMeasuredWidthAndState() & (-16777216)) == 16777216) {
                    setStacked(true);
                    z2 = true;
                }
            }
        }
        if (z2) {
            super.onMeasure(i2, i3);
        }
        int childCount = getChildCount();
        int i6 = 0;
        while (true) {
            i4 = -1;
            if (i6 >= childCount) {
                i6 = -1;
                break;
            } else if (getChildAt(i6).getVisibility() == 0) {
                break;
            } else {
                i6++;
            }
        }
        if (i6 >= 0) {
            View childAt = getChildAt(i6);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight() + getPaddingTop() + layoutParams.topMargin + layoutParams.bottomMargin + 0;
            if ((getOrientation() == 1 ? 1 : 0) != 0) {
                int childCount2 = getChildCount();
                for (int i7 = i6 + 1; i7 < childCount2; i7++) {
                    if (getChildAt(i7).getVisibility() == 0) {
                        i4 = i7;
                        break;
                    }
                }
                if (i4 >= 0) {
                    paddingBottom = getChildAt(i4).getPaddingTop() + ((int) (getResources().getDisplayMetrics().density * 16.0f));
                } else {
                    i5 = measuredHeight;
                }
            } else {
                paddingBottom = getPaddingBottom();
            }
            i5 = paddingBottom + measuredHeight;
        }
        WeakHashMap weakHashMap = u.f2012a;
        if (getMinimumHeight() != i5) {
            setMinimumHeight(i5);
        }
    }

    public void setAllowStacking(boolean z2) {
        if (this.f177b != z2) {
            this.f177b = z2;
            if (!z2 && getOrientation() == 1) {
                setStacked(false);
            }
            requestLayout();
        }
    }
}
