package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.snapay.app.R;
import j.c2;
import java.util.WeakHashMap;
import x.u;

/* JADX INFO: loaded from: classes.dex */
public class ActionBarContainer extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f112b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public View f113c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public View f114d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Drawable f115e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Drawable f116f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Drawable f117g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f118h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f119i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f120j;

    public ActionBarContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        j.b bVar = new j.b(this);
        WeakHashMap weakHashMap = u.f2012a;
        setBackground(bVar);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, c.a.f481a);
        boolean z2 = false;
        this.f115e = typedArrayObtainStyledAttributes.getDrawable(0);
        this.f116f = typedArrayObtainStyledAttributes.getDrawable(2);
        this.f120j = typedArrayObtainStyledAttributes.getDimensionPixelSize(13, -1);
        if (getId() == R.id.split_action_bar) {
            this.f118h = true;
            this.f117g = typedArrayObtainStyledAttributes.getDrawable(1);
        }
        typedArrayObtainStyledAttributes.recycle();
        if (!this.f118h ? !(this.f115e != null || this.f116f != null) : this.f117g == null) {
            z2 = true;
        }
        setWillNotDraw(z2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.f115e;
        if (drawable != null && drawable.isStateful()) {
            this.f115e.setState(getDrawableState());
        }
        Drawable drawable2 = this.f116f;
        if (drawable2 != null && drawable2.isStateful()) {
            this.f116f.setState(getDrawableState());
        }
        Drawable drawable3 = this.f117g;
        if (drawable3 == null || !drawable3.isStateful()) {
            return;
        }
        this.f117g.setState(getDrawableState());
    }

    public View getTabContainer() {
        return null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f115e;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.f116f;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        Drawable drawable3 = this.f117g;
        if (drawable3 != null) {
            drawable3.jumpToCurrentState();
        }
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.f113c = findViewById(R.id.action_bar);
        this.f114d = findViewById(R.id.action_context_bar);
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        super.onHoverEvent(motionEvent);
        return true;
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.f112b || super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        Drawable drawable;
        int left;
        int top;
        int right;
        View view;
        super.onLayout(z2, i2, i3, i4, i5);
        boolean z3 = true;
        if (this.f118h) {
            Drawable drawable2 = this.f117g;
            if (drawable2 != null) {
                drawable2.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            } else {
                z3 = false;
            }
        } else {
            if (this.f115e != null) {
                if (this.f113c.getVisibility() == 0) {
                    drawable = this.f115e;
                    left = this.f113c.getLeft();
                    top = this.f113c.getTop();
                    right = this.f113c.getRight();
                    view = this.f113c;
                } else {
                    View view2 = this.f114d;
                    if (view2 == null || view2.getVisibility() != 0) {
                        this.f115e.setBounds(0, 0, 0, 0);
                    } else {
                        drawable = this.f115e;
                        left = this.f114d.getLeft();
                        top = this.f114d.getTop();
                        right = this.f114d.getRight();
                        view = this.f114d;
                    }
                }
                drawable.setBounds(left, top, right, view.getBottom());
            } else {
                z3 = false;
            }
            this.f119i = false;
        }
        if (z3) {
            invalidate();
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i2, int i3) {
        int i4;
        if (this.f113c == null && View.MeasureSpec.getMode(i3) == Integer.MIN_VALUE && (i4 = this.f120j) >= 0) {
            i3 = View.MeasureSpec.makeMeasureSpec(Math.min(i4, View.MeasureSpec.getSize(i3)), Integer.MIN_VALUE);
        }
        super.onMeasure(i2, i3);
        if (this.f113c == null) {
            return;
        }
        View.MeasureSpec.getMode(i3);
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    public void setPrimaryBackground(Drawable drawable) {
        Drawable drawable2 = this.f115e;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.f115e);
        }
        this.f115e = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            View view = this.f113c;
            if (view != null) {
                this.f115e.setBounds(view.getLeft(), this.f113c.getTop(), this.f113c.getRight(), this.f113c.getBottom());
            }
        }
        boolean z2 = true;
        if (!this.f118h ? this.f115e != null || this.f116f != null : this.f117g != null) {
            z2 = false;
        }
        setWillNotDraw(z2);
        invalidate();
        invalidateOutline();
    }

    public void setSplitBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.f117g;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.f117g);
        }
        this.f117g = drawable;
        boolean z2 = this.f118h;
        boolean z3 = false;
        if (drawable != null) {
            drawable.setCallback(this);
            if (z2 && (drawable2 = this.f117g) != null) {
                drawable2.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            }
        }
        if (!z2 ? !(this.f115e != null || this.f116f != null) : this.f117g == null) {
            z3 = true;
        }
        setWillNotDraw(z3);
        invalidate();
        invalidateOutline();
    }

    public void setStackedBackground(Drawable drawable) {
        Drawable drawable2 = this.f116f;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.f116f);
        }
        this.f116f = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.f119i && this.f116f != null) {
                throw null;
            }
        }
        setWillNotDraw(!this.f118h ? !(this.f115e == null && this.f116f == null) : this.f117g != null);
        invalidate();
        invalidateOutline();
    }

    public void setTabContainer(c2 c2Var) {
    }

    public void setTransitioning(boolean z2) {
        this.f112b = z2;
        setDescendantFocusability(z2 ? 393216 : 262144);
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        super.setVisibility(i2);
        boolean z2 = i2 == 0;
        Drawable drawable = this.f115e;
        if (drawable != null) {
            drawable.setVisible(z2, false);
        }
        Drawable drawable2 = this.f116f;
        if (drawable2 != null) {
            drawable2.setVisible(z2, false);
        }
        Drawable drawable3 = this.f117g;
        if (drawable3 != null) {
            drawable3.setVisible(z2, false);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final ActionMode startActionModeForChild(View view, ActionMode.Callback callback) {
        return null;
    }

    @Override // android.view.View
    public final boolean verifyDrawable(Drawable drawable) {
        Drawable drawable2 = this.f115e;
        boolean z2 = this.f118h;
        return (drawable == drawable2 && !z2) || (drawable == this.f116f && this.f119i) || ((drawable == this.f117g && z2) || super.verifyDrawable(drawable));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final ActionMode startActionModeForChild(View view, ActionMode.Callback callback, int i2) {
        if (i2 != 0) {
            return super.startActionModeForChild(view, callback, i2);
        }
        return null;
    }
}
