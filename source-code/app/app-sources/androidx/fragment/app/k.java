package androidx.fragment.app;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import com.snapay.app.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;
import x.l0;

/* JADX INFO: loaded from: classes.dex */
public final class k extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ArrayList f330b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ArrayList f331c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public View.OnApplyWindowInsetsListener f332d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f333e;

    public k(Context context, AttributeSet attributeSet, r rVar) {
        super(context, attributeSet);
        this.f333e = true;
        String classAttribute = attributeSet.getClassAttribute();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, d0.a.f749b);
        classAttribute = classAttribute == null ? typedArrayObtainStyledAttributes.getString(0) : classAttribute;
        String string = typedArrayObtainStyledAttributes.getString(1);
        typedArrayObtainStyledAttributes.recycle();
        int id = getId();
        rVar.g();
        if (classAttribute != null) {
            if (id <= 0) {
                throw new IllegalStateException(androidx.activity.c.a("FragmentContainerView must have an android:id to add Fragment ", classAttribute, string != null ? " with tag ".concat(string) : ""));
            }
            context.getClassLoader();
            rVar.f360n.a(classAttribute);
            throw null;
        }
        Iterator it = rVar.f349c.b().iterator();
        if (it.hasNext()) {
            androidx.activity.c.b(it.next());
            throw null;
        }
    }

    public final void a(View view) {
        ArrayList arrayList = this.f331c;
        if (arrayList == null || !arrayList.contains(view)) {
            return;
        }
        if (this.f330b == null) {
            this.f330b = new ArrayList();
        }
        this.f330b.add(view);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        view.getTag(R.id.fragment_container_view_tag);
        throw new IllegalStateException("Views added to a FragmentContainerView must be associated with a Fragment. View " + view + " is not associated with a Fragment.");
    }

    @Override // android.view.ViewGroup
    public final boolean addViewInLayout(View view, int i2, ViewGroup.LayoutParams layoutParams, boolean z2) {
        view.getTag(R.id.fragment_container_view_tag);
        throw new IllegalStateException("Views added to a FragmentContainerView must be associated with a Fragment. View " + view + " is not associated with a Fragment.");
    }

    @Override // android.view.ViewGroup, android.view.View
    public final WindowInsets dispatchApplyWindowInsets(WindowInsets windowInsets) {
        l0 l0VarC;
        l0 l0VarC2 = l0.c(windowInsets, null);
        View.OnApplyWindowInsetsListener onApplyWindowInsetsListener = this.f332d;
        if (onApplyWindowInsetsListener != null) {
            l0VarC = l0.c(onApplyWindowInsetsListener.onApplyWindowInsets(this, windowInsets), null);
        } else {
            WeakHashMap weakHashMap = x.u.f2012a;
            WindowInsets windowInsetsB = l0VarC2.b();
            if (windowInsetsB != null && !windowInsetsB.equals(windowInsetsB)) {
                l0VarC2 = l0.c(windowInsetsB, this);
            }
            l0VarC = l0VarC2;
        }
        if (!l0VarC.f2000a.i()) {
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                WeakHashMap weakHashMap2 = x.u.f2012a;
                WindowInsets windowInsetsB2 = l0VarC.b();
                if (windowInsetsB2 != null) {
                    WindowInsets windowInsetsDispatchApplyWindowInsets = childAt.dispatchApplyWindowInsets(windowInsetsB2);
                    if (!windowInsetsDispatchApplyWindowInsets.equals(windowInsetsB2)) {
                        l0.c(windowInsetsDispatchApplyWindowInsets, childAt);
                    }
                }
            }
        }
        return windowInsets;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        if (this.f333e && this.f330b != null) {
            for (int i2 = 0; i2 < this.f330b.size(); i2++) {
                super.drawChild(canvas, (View) this.f330b.get(i2), getDrawingTime());
            }
        }
        super.dispatchDraw(canvas);
    }

    @Override // android.view.ViewGroup
    public final boolean drawChild(Canvas canvas, View view, long j2) {
        ArrayList arrayList;
        if (!this.f333e || (arrayList = this.f330b) == null || arrayList.size() <= 0 || !this.f330b.contains(view)) {
            return super.drawChild(canvas, view, j2);
        }
        return false;
    }

    @Override // android.view.ViewGroup
    public final void endViewTransition(View view) {
        ArrayList arrayList = this.f331c;
        if (arrayList != null) {
            arrayList.remove(view);
            ArrayList arrayList2 = this.f330b;
            if (arrayList2 != null && arrayList2.remove(view)) {
                this.f333e = true;
            }
        }
        super.endViewTransition(view);
    }

    @Override // android.view.View
    public final WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        return windowInsets;
    }

    @Override // android.view.ViewGroup
    public final void removeAllViewsInLayout() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            a(getChildAt(childCount));
        }
        super.removeAllViewsInLayout();
    }

    @Override // android.view.ViewGroup
    public final void removeDetachedView(View view, boolean z2) {
        if (z2) {
            a(view);
        }
        super.removeDetachedView(view, z2);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public final void removeView(View view) {
        a(view);
        super.removeView(view);
    }

    @Override // android.view.ViewGroup
    public final void removeViewAt(int i2) {
        a(getChildAt(i2));
        super.removeViewAt(i2);
    }

    @Override // android.view.ViewGroup
    public final void removeViewInLayout(View view) {
        a(view);
        super.removeViewInLayout(view);
    }

    @Override // android.view.ViewGroup
    public final void removeViews(int i2, int i3) {
        for (int i4 = i2; i4 < i2 + i3; i4++) {
            a(getChildAt(i4));
        }
        super.removeViews(i2, i3);
    }

    @Override // android.view.ViewGroup
    public final void removeViewsInLayout(int i2, int i3) {
        for (int i4 = i2; i4 < i2 + i3; i4++) {
            a(getChildAt(i4));
        }
        super.removeViewsInLayout(i2, i3);
    }

    public void setDrawDisappearingViewsLast(boolean z2) {
        this.f333e = z2;
    }

    @Override // android.view.ViewGroup
    public void setLayoutTransition(LayoutTransition layoutTransition) {
        throw new UnsupportedOperationException("FragmentContainerView does not support Layout Transitions or animateLayoutChanges=\"true\".");
    }

    @Override // android.view.View
    public void setOnApplyWindowInsetsListener(View.OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        this.f332d = onApplyWindowInsetsListener;
    }

    @Override // android.view.ViewGroup
    public final void startViewTransition(View view) {
        if (view.getParent() == this) {
            if (this.f331c == null) {
                this.f331c = new ArrayList();
            }
            this.f331c.add(view);
        }
        super.startViewTransition(view);
    }
}
