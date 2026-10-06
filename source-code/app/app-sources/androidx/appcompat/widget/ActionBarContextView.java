package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.snapay.app.R;
import i.d0;
import i.o;
import j.g3;
import j.h;
import j.m;
import java.util.WeakHashMap;
import x.u;
import x.y;

/* JADX INFO: loaded from: classes.dex */
public class ActionBarContextView extends ViewGroup {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final j.a f121b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Context f122c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ActionMenuView f123d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public m f124e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f125f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public y f126g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f127h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f128i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public CharSequence f129j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public CharSequence f130k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public View f131l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public View f132m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public View f133n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public LinearLayout f134o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public TextView f135p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public TextView f136q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f137r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final int f138s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f139t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final int f140u;

    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        int resourceId;
        super(context, attributeSet, R.attr.actionModeStyle);
        this.f121b = new j.a(this);
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(R.attr.actionBarPopupTheme, typedValue, true) || typedValue.resourceId == 0) {
            this.f122c = context;
        } else {
            this.f122c = new ContextThemeWrapper(context, typedValue.resourceId);
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, c.a.f484d, R.attr.actionModeStyle, 0);
        Drawable drawable = (!typedArrayObtainStyledAttributes.hasValue(0) || (resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0)) == 0) ? typedArrayObtainStyledAttributes.getDrawable(0) : e.b.c(context, resourceId);
        WeakHashMap weakHashMap = u.f2012a;
        setBackground(drawable);
        this.f137r = typedArrayObtainStyledAttributes.getResourceId(5, 0);
        this.f138s = typedArrayObtainStyledAttributes.getResourceId(4, 0);
        this.f125f = typedArrayObtainStyledAttributes.getLayoutDimension(3, 0);
        this.f140u = typedArrayObtainStyledAttributes.getResourceId(2, R.layout.abc_action_mode_close_item_material);
        typedArrayObtainStyledAttributes.recycle();
    }

    public static int f(View view, int i2, int i3) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i2, Integer.MIN_VALUE), i3);
        return Math.max(0, (i2 - view.getMeasuredWidth()) - 0);
    }

    public static int j(View view, int i2, int i3, int i4, boolean z2) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i5 = ((i4 - measuredHeight) / 2) + i3;
        if (z2) {
            view.layout(i2 - measuredWidth, i5, i2, measuredHeight + i5);
        } else {
            view.layout(i2, i5, i2 + measuredWidth, measuredHeight + i5);
        }
        return z2 ? -measuredWidth : measuredWidth;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x003c  */
    /* JADX WARN: Code duplicated, block: B:18:0x0072  */
    /* JADX WARN: Code duplicated, block: B:21:0x008a  */
    public final void c(h.c cVar) {
        View viewInflate;
        m mVar;
        m mVar2;
        d0 d0Var;
        d0 d0Var2;
        h hVar;
        View view = this.f131l;
        if (view != null) {
            if (view.getParent() == null) {
                viewInflate = this.f131l;
            }
            View viewFindViewById = this.f131l.findViewById(R.id.action_mode_close_button);
            this.f132m = viewFindViewById;
            viewFindViewById.setOnClickListener(new j.c(this, cVar));
            o oVarE = cVar.e();
            mVar = this.f124e;
            if (mVar != null) {
                mVar.f();
                hVar = mVar.f1313u;
                if (hVar != null && hVar.b()) {
                    hVar.f1134j.dismiss();
                }
            }
            m mVar3 = new m(getContext());
            this.f124e = mVar3;
            mVar3.f1305m = true;
            mVar3.f1306n = true;
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -1);
            oVarE.b(this.f124e, this.f122c);
            mVar2 = this.f124e;
            d0Var = mVar2.f1301i;
            if (d0Var == null) {
                d0 d0Var3 = (d0) mVar2.f1297e.inflate(mVar2.f1299g, (ViewGroup) this, false);
                mVar2.f1301i = d0Var3;
                d0Var3.c(mVar2.f1296d);
                mVar2.i();
            }
            d0Var2 = mVar2.f1301i;
            if (d0Var != d0Var2) {
                ((ActionMenuView) d0Var2).setPresenter(mVar2);
            }
            ActionMenuView actionMenuView = (ActionMenuView) d0Var2;
            this.f123d = actionMenuView;
            WeakHashMap weakHashMap = u.f2012a;
            actionMenuView.setBackground(null);
            addView(this.f123d, layoutParams);
        }
        viewInflate = LayoutInflater.from(getContext()).inflate(this.f140u, (ViewGroup) this, false);
        this.f131l = viewInflate;
        addView(viewInflate);
        View viewFindViewById2 = this.f131l.findViewById(R.id.action_mode_close_button);
        this.f132m = viewFindViewById2;
        viewFindViewById2.setOnClickListener(new j.c(this, cVar));
        o oVarE2 = cVar.e();
        mVar = this.f124e;
        if (mVar != null) {
            mVar.f();
            hVar = mVar.f1313u;
            if (hVar != null) {
                hVar.f1134j.dismiss();
            }
        }
        m mVar4 = new m(getContext());
        this.f124e = mVar4;
        mVar4.f1305m = true;
        mVar4.f1306n = true;
        ViewGroup.LayoutParams layoutParams2 = new ViewGroup.LayoutParams(-2, -1);
        oVarE2.b(this.f124e, this.f122c);
        mVar2 = this.f124e;
        d0Var = mVar2.f1301i;
        if (d0Var == null) {
            d0 d0Var4 = (d0) mVar2.f1297e.inflate(mVar2.f1299g, (ViewGroup) this, false);
            mVar2.f1301i = d0Var4;
            d0Var4.c(mVar2.f1296d);
            mVar2.i();
        }
        d0Var2 = mVar2.f1301i;
        if (d0Var != d0Var2) {
            ((ActionMenuView) d0Var2).setPresenter(mVar2);
        }
        ActionMenuView actionMenuView2 = (ActionMenuView) d0Var2;
        this.f123d = actionMenuView2;
        WeakHashMap weakHashMap2 = u.f2012a;
        actionMenuView2.setBackground(null);
        addView(this.f123d, layoutParams2);
    }

    public final void d() {
        if (this.f134o == null) {
            LayoutInflater.from(getContext()).inflate(R.layout.abc_action_bar_title_item, this);
            LinearLayout linearLayout = (LinearLayout) getChildAt(getChildCount() - 1);
            this.f134o = linearLayout;
            this.f135p = (TextView) linearLayout.findViewById(R.id.action_bar_title);
            this.f136q = (TextView) this.f134o.findViewById(R.id.action_bar_subtitle);
            int i2 = this.f137r;
            if (i2 != 0) {
                this.f135p.setTextAppearance(getContext(), i2);
            }
            int i3 = this.f138s;
            if (i3 != 0) {
                this.f136q.setTextAppearance(getContext(), i3);
            }
        }
        this.f135p.setText(this.f129j);
        this.f136q.setText(this.f130k);
        boolean z2 = !TextUtils.isEmpty(this.f129j);
        boolean z3 = !TextUtils.isEmpty(this.f130k);
        int i4 = 0;
        this.f136q.setVisibility(z3 ? 0 : 8);
        LinearLayout linearLayout2 = this.f134o;
        if (!z2 && !z3) {
            i4 = 8;
        }
        linearLayout2.setVisibility(i4);
        if (this.f134o.getParent() == null) {
            addView(this.f134o);
        }
    }

    public final void e() {
        removeAllViews();
        this.f133n = null;
        this.f123d = null;
        this.f124e = null;
        View view = this.f132m;
        if (view != null) {
            view.setOnClickListener(null);
        }
    }

    @Override // android.view.View
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public final void onConfigurationChanged(Configuration configuration) {
        int i2;
        super.onConfigurationChanged(configuration);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(null, c.a.f481a, R.attr.actionBarStyle, 0);
        setContentHeight(typedArrayObtainStyledAttributes.getLayoutDimension(13, 0));
        typedArrayObtainStyledAttributes.recycle();
        m mVar = this.f124e;
        if (mVar != null) {
            Configuration configuration2 = mVar.f1295c.getResources().getConfiguration();
            int i3 = configuration2.screenWidthDp;
            int i4 = configuration2.screenHeightDp;
            if (configuration2.smallestScreenWidthDp > 600 || i3 > 600 || ((i3 > 960 && i4 > 720) || (i3 > 720 && i4 > 960))) {
                i2 = 5;
            } else if (i3 >= 500 || ((i3 > 640 && i4 > 480) || (i3 > 480 && i4 > 640))) {
                i2 = 4;
            } else {
                i2 = i3 >= 360 ? 3 : 2;
            }
            mVar.f1309q = i2;
            o oVar = mVar.f1296d;
            if (oVar != null) {
                oVar.p(true);
            }
        }
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -2);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    public int getAnimatedVisibility() {
        return this.f126g != null ? this.f121b.f1143b : getVisibility();
    }

    public int getContentHeight() {
        return this.f125f;
    }

    public CharSequence getSubtitle() {
        return this.f130k;
    }

    public CharSequence getTitle() {
        return this.f129j;
    }

    @Override // android.view.View
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.f128i = false;
        }
        if (!this.f128i) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.f128i = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.f128i = false;
        }
        return true;
    }

    @Override // android.view.View
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f127h = false;
        }
        if (!this.f127h) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.f127h = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.f127h = false;
        }
        return true;
    }

    @Override // android.view.View
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public final void setVisibility(int i2) {
        if (i2 != getVisibility()) {
            y yVar = this.f126g;
            if (yVar != null) {
                yVar.b();
            }
            super.setVisibility(i2);
        }
    }

    public final y l(int i2, long j2) {
        y yVar = this.f126g;
        if (yVar != null) {
            yVar.b();
        }
        j.a aVar = this.f121b;
        if (i2 != 0) {
            y yVarA = u.a(this);
            yVarA.a(0.0f);
            yVarA.c(j2);
            aVar.f1144c.f126g = yVarA;
            aVar.f1143b = i2;
            yVarA.d(aVar);
            return yVarA;
        }
        if (getVisibility() != 0) {
            setAlpha(0.0f);
        }
        y yVarA2 = u.a(this);
        yVarA2.a(1.0f);
        yVarA2.c(j2);
        aVar.f1144c.f126g = yVarA2;
        aVar.f1143b = i2;
        yVarA2.d(aVar);
        return yVarA2;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m mVar = this.f124e;
        if (mVar != null) {
            mVar.f();
            h hVar = this.f124e.f1313u;
            if (hVar == null || !hVar.b()) {
                return;
            }
            hVar.f1134j.dismiss();
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() != 32) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            return;
        }
        accessibilityEvent.setSource(this);
        accessibilityEvent.setClassName(getClass().getName());
        accessibilityEvent.setPackageName(getContext().getPackageName());
        accessibilityEvent.setContentDescription(this.f129j);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        boolean zA = g3.a(this);
        int paddingRight = zA ? (i4 - i2) - getPaddingRight() : getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingTop2 = ((i5 - i3) - getPaddingTop()) - getPaddingBottom();
        View view = this.f131l;
        if (view != null && view.getVisibility() != 8) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f131l.getLayoutParams();
            int i6 = zA ? marginLayoutParams.rightMargin : marginLayoutParams.leftMargin;
            int i7 = zA ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
            int i8 = zA ? paddingRight - i6 : paddingRight + i6;
            int iJ = j(this.f131l, i8, paddingTop, paddingTop2, zA) + i8;
            paddingRight = zA ? iJ - i7 : iJ + i7;
        }
        LinearLayout linearLayout = this.f134o;
        if (linearLayout != null && this.f133n == null && linearLayout.getVisibility() != 8) {
            paddingRight += j(this.f134o, paddingRight, paddingTop, paddingTop2, zA);
        }
        View view2 = this.f133n;
        if (view2 != null) {
            j(view2, paddingRight, paddingTop, paddingTop2, zA);
        }
        int paddingLeft = zA ? getPaddingLeft() : (i4 - i2) - getPaddingRight();
        ActionMenuView actionMenuView = this.f123d;
        if (actionMenuView != null) {
            j(actionMenuView, paddingLeft, paddingTop, paddingTop2, !zA);
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i2, int i3) {
        if (View.MeasureSpec.getMode(i2) != 1073741824) {
            throw new IllegalStateException(getClass().getSimpleName().concat(" can only be used with android:layout_width=\"match_parent\" (or fill_parent)"));
        }
        if (View.MeasureSpec.getMode(i3) == 0) {
            throw new IllegalStateException(getClass().getSimpleName().concat(" can only be used with android:layout_height=\"wrap_content\""));
        }
        int size = View.MeasureSpec.getSize(i2);
        int size2 = this.f125f;
        if (size2 <= 0) {
            size2 = View.MeasureSpec.getSize(i3);
        }
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
        int iMin = size2 - paddingBottom;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iMin, Integer.MIN_VALUE);
        View view = this.f131l;
        if (view != null) {
            int iF = f(view, paddingLeft, iMakeMeasureSpec);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f131l.getLayoutParams();
            paddingLeft = iF - (marginLayoutParams.leftMargin + marginLayoutParams.rightMargin);
        }
        ActionMenuView actionMenuView = this.f123d;
        if (actionMenuView != null && actionMenuView.getParent() == this) {
            paddingLeft = f(this.f123d, paddingLeft, iMakeMeasureSpec);
        }
        LinearLayout linearLayout = this.f134o;
        if (linearLayout != null && this.f133n == null) {
            if (this.f139t) {
                this.f134o.measure(View.MeasureSpec.makeMeasureSpec(0, 0), iMakeMeasureSpec);
                int measuredWidth = this.f134o.getMeasuredWidth();
                boolean z2 = measuredWidth <= paddingLeft;
                if (z2) {
                    paddingLeft -= measuredWidth;
                }
                this.f134o.setVisibility(z2 ? 0 : 8);
            } else {
                paddingLeft = f(linearLayout, paddingLeft, iMakeMeasureSpec);
            }
        }
        View view2 = this.f133n;
        if (view2 != null) {
            ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
            int i4 = layoutParams.width;
            int i5 = i4 != -2 ? 1073741824 : Integer.MIN_VALUE;
            if (i4 >= 0) {
                paddingLeft = Math.min(i4, paddingLeft);
            }
            int i6 = layoutParams.height;
            int i7 = i6 == -2 ? Integer.MIN_VALUE : 1073741824;
            if (i6 >= 0) {
                iMin = Math.min(i6, iMin);
            }
            this.f133n.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, i5), View.MeasureSpec.makeMeasureSpec(iMin, i7));
        }
        if (this.f125f <= 0) {
            int childCount = getChildCount();
            size2 = 0;
            for (int i8 = 0; i8 < childCount; i8++) {
                int measuredHeight = getChildAt(i8).getMeasuredHeight() + paddingBottom;
                if (measuredHeight > size2) {
                    size2 = measuredHeight;
                }
            }
        }
        setMeasuredDimension(size, size2);
    }

    public void setContentHeight(int i2) {
        this.f125f = i2;
    }

    public void setCustomView(View view) {
        LinearLayout linearLayout;
        View view2 = this.f133n;
        if (view2 != null) {
            removeView(view2);
        }
        this.f133n = view;
        if (view != null && (linearLayout = this.f134o) != null) {
            removeView(linearLayout);
            this.f134o = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public void setSubtitle(CharSequence charSequence) {
        this.f130k = charSequence;
        d();
    }

    public void setTitle(CharSequence charSequence) {
        this.f129j = charSequence;
        d();
    }

    public void setTitleOptional(boolean z2) {
        if (z2 != this.f139t) {
            requestLayout();
        }
        this.f139t = z2;
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }
}
