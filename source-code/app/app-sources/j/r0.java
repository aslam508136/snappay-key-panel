package j;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

/* JADX INFO: loaded from: classes.dex */
public final class r0 extends Spinner {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final int[] f1383j = {R.attr.spinnerMode};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s f1384b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Context f1385c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final k f1386d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public SpinnerAdapter f1387e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f1388f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final q0 f1389g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1390h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Rect f1391i;

    /* JADX WARN: Code duplicated, block: B:25:0x0062 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:27:0x0065  */
    /* JADX WARN: Code duplicated, block: B:28:0x0098  */
    /* JADX WARN: Code duplicated, block: B:31:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:34:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:38:0x00d2  */
    public r0(Context context, AttributeSet attributeSet) throws Throwable {
        Exception e2;
        TypedArray typedArrayObtainStyledAttributes;
        int i2;
        CharSequence[] textArray;
        SpinnerAdapter spinnerAdapter;
        super(context, attributeSet, com.snapay.app.R.attr.spinnerStyle);
        this.f1391i = new Rect();
        r2.a(this, getContext());
        int[] iArr = c.a.f501u;
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, iArr, com.snapay.app.R.attr.spinnerStyle, 0);
        this.f1384b = new s(this);
        int resourceId = typedArrayObtainStyledAttributes2.getResourceId(4, 0);
        if (resourceId != 0) {
            this.f1385c = new h.e(context, resourceId);
        } else {
            this.f1385c = context;
        }
        int i3 = -1;
        TypedArray typedArray = null;
        try {
            typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f1383j, com.snapay.app.R.attr.spinnerStyle, 0);
            try {
                try {
                    if (typedArrayObtainStyledAttributes.hasValue(0)) {
                        i3 = typedArrayObtainStyledAttributes.getInt(0, 0);
                    }
                } catch (Exception e3) {
                    e2 = e3;
                    Log.i("AppCompatSpinner", "Could not read android:spinnerMode", e2);
                    if (typedArrayObtainStyledAttributes != null) {
                    }
                    i2 = 1;
                    if (i3 != 0) {
                        l0 l0Var = new l0(this);
                        this.f1389g = l0Var;
                        l0Var.f1283d = typedArrayObtainStyledAttributes2.getString(2);
                    } else if (i3 == 1) {
                        o0 o0Var = new o0(this, this.f1385c, attributeSet);
                        m0.a aVarU = m0.a.u(this.f1385c, attributeSet, iArr, com.snapay.app.R.attr.spinnerStyle);
                        this.f1390h = ((TypedArray) aVarU.f1643b).getLayoutDimension(3, -2);
                        o0Var.m(aVarU.k(1));
                        o0Var.D = typedArrayObtainStyledAttributes2.getString(2);
                        aVarU.w();
                        this.f1389g = o0Var;
                        this.f1386d = new k(this, this, o0Var, i2);
                    }
                    textArray = typedArrayObtainStyledAttributes2.getTextArray(0);
                    if (textArray != null) {
                        ArrayAdapter arrayAdapter = new ArrayAdapter(context, R.layout.simple_spinner_item, textArray);
                        arrayAdapter.setDropDownViewResource(com.snapay.app.R.layout.support_simple_spinner_dropdown_item);
                        setAdapter((SpinnerAdapter) arrayAdapter);
                    }
                    typedArrayObtainStyledAttributes2.recycle();
                    this.f1388f = true;
                    spinnerAdapter = this.f1387e;
                    if (spinnerAdapter != null) {
                        setAdapter(spinnerAdapter);
                        this.f1387e = null;
                    }
                    this.f1384b.d(attributeSet, com.snapay.app.R.attr.spinnerStyle);
                }
            } catch (Throwable th) {
                th = th;
                typedArray = typedArrayObtainStyledAttributes;
                if (typedArray != null) {
                    typedArray.recycle();
                }
                throw th;
            }
        } catch (Exception e4) {
            e2 = e4;
            typedArrayObtainStyledAttributes = null;
        } catch (Throwable th2) {
            th = th2;
            if (typedArray != null) {
                typedArray.recycle();
            }
            throw th;
        }
        typedArrayObtainStyledAttributes.recycle();
        i2 = 1;
        if (i3 != 0) {
            l0 l0Var2 = new l0(this);
            this.f1389g = l0Var2;
            l0Var2.f1283d = typedArrayObtainStyledAttributes2.getString(2);
        } else if (i3 == 1) {
            o0 o0Var2 = new o0(this, this.f1385c, attributeSet);
            m0.a aVarU2 = m0.a.u(this.f1385c, attributeSet, iArr, com.snapay.app.R.attr.spinnerStyle);
            this.f1390h = ((TypedArray) aVarU2.f1643b).getLayoutDimension(3, -2);
            o0Var2.m(aVarU2.k(1));
            o0Var2.D = typedArrayObtainStyledAttributes2.getString(2);
            aVarU2.w();
            this.f1389g = o0Var2;
            this.f1386d = new k(this, this, o0Var2, i2);
        }
        textArray = typedArrayObtainStyledAttributes2.getTextArray(0);
        if (textArray != null) {
            ArrayAdapter arrayAdapter2 = new ArrayAdapter(context, R.layout.simple_spinner_item, textArray);
            arrayAdapter2.setDropDownViewResource(com.snapay.app.R.layout.support_simple_spinner_dropdown_item);
            setAdapter((SpinnerAdapter) arrayAdapter2);
        }
        typedArrayObtainStyledAttributes2.recycle();
        this.f1388f = true;
        spinnerAdapter = this.f1387e;
        if (spinnerAdapter != null) {
            setAdapter(spinnerAdapter);
            this.f1387e = null;
        }
        this.f1384b.d(attributeSet, com.snapay.app.R.attr.spinnerStyle);
    }

    public final int a(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        int i2 = 0;
        if (spinnerAdapter == null) {
            return 0;
        }
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int iMax = Math.max(0, getSelectedItemPosition());
        int iMin = Math.min(spinnerAdapter.getCount(), iMax + 15);
        View view = null;
        int iMax2 = 0;
        for (int iMax3 = Math.max(0, iMax - (15 - (iMin - iMax))); iMax3 < iMin; iMax3++) {
            int itemViewType = spinnerAdapter.getItemViewType(iMax3);
            if (itemViewType != i2) {
                view = null;
                i2 = itemViewType;
            }
            view = spinnerAdapter.getView(iMax3, view, this);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }
            view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
            iMax2 = Math.max(iMax2, view.getMeasuredWidth());
        }
        if (drawable == null) {
            return iMax2;
        }
        Rect rect = this.f1391i;
        drawable.getPadding(rect);
        return iMax2 + rect.left + rect.right;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        s sVar = this.f1384b;
        if (sVar != null) {
            sVar.a();
        }
    }

    @Override // android.widget.Spinner
    public int getDropDownHorizontalOffset() {
        q0 q0Var = this.f1389g;
        return q0Var != null ? q0Var.c() : super.getDropDownHorizontalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownVerticalOffset() {
        q0 q0Var = this.f1389g;
        return q0Var != null ? q0Var.g() : super.getDropDownVerticalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownWidth() {
        return this.f1389g != null ? this.f1390h : super.getDropDownWidth();
    }

    public final q0 getInternalPopup() {
        return this.f1389g;
    }

    @Override // android.widget.Spinner
    public Drawable getPopupBackground() {
        q0 q0Var = this.f1389g;
        return q0Var != null ? q0Var.h() : super.getPopupBackground();
    }

    @Override // android.widget.Spinner
    public Context getPopupContext() {
        return this.f1385c;
    }

    @Override // android.widget.Spinner
    public CharSequence getPrompt() {
        q0 q0Var = this.f1389g;
        return q0Var != null ? q0Var.i() : super.getPrompt();
    }

    public ColorStateList getSupportBackgroundTintList() {
        s sVar = this.f1384b;
        if (sVar != null) {
            return sVar.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        s sVar = this.f1384b;
        if (sVar != null) {
            return sVar.c();
        }
        return null;
    }

    @Override // android.widget.Spinner, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        q0 q0Var = this.f1389g;
        if (q0Var == null || !q0Var.b()) {
            return;
        }
        q0Var.dismiss();
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public final void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (this.f1389g == null || View.MeasureSpec.getMode(i2) != Integer.MIN_VALUE) {
            return;
        }
        setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), a(getAdapter(), getBackground())), View.MeasureSpec.getSize(i2)), getMeasuredHeight());
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        ViewTreeObserver viewTreeObserver;
        p0 p0Var = (p0) parcelable;
        super.onRestoreInstanceState(p0Var.getSuperState());
        if (!p0Var.f1353a || (viewTreeObserver = getViewTreeObserver()) == null) {
            return;
        }
        viewTreeObserver.addOnGlobalLayoutListener(new i.e(this, 2));
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public final Parcelable onSaveInstanceState() {
        p0 p0Var = new p0(super.onSaveInstanceState());
        q0 q0Var = this.f1389g;
        p0Var.f1353a = q0Var != null && q0Var.b();
        return p0Var;
    }

    @Override // android.widget.Spinner, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        k kVar = this.f1386d;
        if (kVar == null || !kVar.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // android.widget.Spinner, android.view.View
    public final boolean performClick() {
        q0 q0Var = this.f1389g;
        if (q0Var == null) {
            return super.performClick();
        }
        if (q0Var.b()) {
            return true;
        }
        q0Var.d(getTextDirection(), getTextAlignment());
        return true;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        s sVar = this.f1384b;
        if (sVar != null) {
            sVar.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        s sVar = this.f1384b;
        if (sVar != null) {
            sVar.f(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownHorizontalOffset(int i2) {
        q0 q0Var = this.f1389g;
        if (q0Var == null) {
            super.setDropDownHorizontalOffset(i2);
        } else {
            q0Var.p(i2);
            q0Var.a(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownVerticalOffset(int i2) {
        q0 q0Var = this.f1389g;
        if (q0Var != null) {
            q0Var.n(i2);
        } else {
            super.setDropDownVerticalOffset(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownWidth(int i2) {
        if (this.f1389g != null) {
            this.f1390h = i2;
        } else {
            super.setDropDownWidth(i2);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundDrawable(Drawable drawable) {
        q0 q0Var = this.f1389g;
        if (q0Var != null) {
            q0Var.m(drawable);
        } else {
            super.setPopupBackgroundDrawable(drawable);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundResource(int i2) {
        setPopupBackgroundDrawable(e.b.c(getPopupContext(), i2));
    }

    @Override // android.widget.Spinner
    public void setPrompt(CharSequence charSequence) {
        q0 q0Var = this.f1389g;
        if (q0Var != null) {
            q0Var.l(charSequence);
        } else {
            super.setPrompt(charSequence);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        s sVar = this.f1384b;
        if (sVar != null) {
            sVar.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        s sVar = this.f1384b;
        if (sVar != null) {
            sVar.i(mode);
        }
    }

    @Override // android.widget.AdapterView
    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (!this.f1388f) {
            this.f1387e = spinnerAdapter;
            return;
        }
        super.setAdapter(spinnerAdapter);
        q0 q0Var = this.f1389g;
        if (q0Var != null) {
            Context context = this.f1385c;
            if (context == null) {
                context = getContext();
            }
            q0Var.o(new m0(spinnerAdapter, context.getTheme()));
        }
    }
}
