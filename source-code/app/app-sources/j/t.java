package j;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import com.snapay.app.R;

/* JADX INFO: loaded from: classes.dex */
public final class t extends Button implements a0.b, a0.l {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s f1411b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final v0 f1412c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.buttonStyle);
        s2.a(context);
        r2.a(this, getContext());
        s sVar = new s(this);
        this.f1411b = sVar;
        sVar.d(attributeSet, R.attr.buttonStyle);
        v0 v0Var = new v0(this);
        this.f1412c = v0Var;
        v0Var.d(attributeSet, R.attr.buttonStyle);
        v0Var.b();
    }

    @Override // android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        s sVar = this.f1411b;
        if (sVar != null) {
            sVar.a();
        }
        v0 v0Var = this.f1412c;
        if (v0Var != null) {
            v0Var.b();
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (a0.b.f11a) {
            return super.getAutoSizeMaxTextSize();
        }
        v0 v0Var = this.f1412c;
        if (v0Var != null) {
            return Math.round(v0Var.f1462i.f1181e);
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        if (a0.b.f11a) {
            return super.getAutoSizeMinTextSize();
        }
        v0 v0Var = this.f1412c;
        if (v0Var != null) {
            return Math.round(v0Var.f1462i.f1180d);
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        if (a0.b.f11a) {
            return super.getAutoSizeStepGranularity();
        }
        v0 v0Var = this.f1412c;
        if (v0Var != null) {
            return Math.round(v0Var.f1462i.f1179c);
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        if (a0.b.f11a) {
            return super.getAutoSizeTextAvailableSizes();
        }
        v0 v0Var = this.f1412c;
        return v0Var != null ? v0Var.f1462i.f1182f : new int[0];
    }

    @Override // android.widget.TextView
    @SuppressLint({"WrongConstant"})
    public int getAutoSizeTextType() {
        if (a0.b.f11a) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        v0 v0Var = this.f1412c;
        if (v0Var != null) {
            return v0Var.f1462i.f1177a;
        }
        return 0;
    }

    public ColorStateList getSupportBackgroundTintList() {
        s sVar = this.f1411b;
        if (sVar != null) {
            return sVar.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        s sVar = this.f1411b;
        if (sVar != null) {
            return sVar.c();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        t2 t2Var = this.f1412c.f1461h;
        if (t2Var != null) {
            return t2Var.f1442a;
        }
        return null;
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        t2 t2Var = this.f1412c.f1461h;
        if (t2Var != null) {
            return t2Var.f1443b;
        }
        return null;
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(Button.class.getName());
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(Button.class.getName());
    }

    @Override // android.widget.TextView, android.view.View
    public final void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        v0 v0Var = this.f1412c;
        if (v0Var == null || a0.b.f11a) {
            return;
        }
        v0Var.f1462i.a();
    }

    @Override // android.widget.TextView
    public final void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        super.onTextChanged(charSequence, i2, i3, i4);
        v0 v0Var = this.f1412c;
        if (v0Var == null || a0.b.f11a) {
            return;
        }
        b1 b1Var = v0Var.f1462i;
        if (b1Var.i() && b1Var.f1177a != 0) {
            b1Var.a();
        }
    }

    @Override // android.widget.TextView
    public final void setAutoSizeTextTypeUniformWithConfiguration(int i2, int i3, int i4, int i5) {
        if (a0.b.f11a) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i2, i3, i4, i5);
            return;
        }
        v0 v0Var = this.f1412c;
        if (v0Var != null) {
            v0Var.g(i2, i3, i4, i5);
        }
    }

    @Override // android.widget.TextView
    public final void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i2) {
        if (a0.b.f11a) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i2);
            return;
        }
        v0 v0Var = this.f1412c;
        if (v0Var != null) {
            v0Var.h(iArr, i2);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int i2) {
        if (a0.b.f11a) {
            super.setAutoSizeTextTypeWithDefaults(i2);
            return;
        }
        v0 v0Var = this.f1412c;
        if (v0Var != null) {
            v0Var.i(i2);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        s sVar = this.f1411b;
        if (sVar != null) {
            sVar.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        s sVar = this.f1411b;
        if (sVar != null) {
            sVar.f(i2);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(androidx.lifecycle.i.l0(callback, this));
    }

    public void setSupportAllCaps(boolean z2) {
        v0 v0Var = this.f1412c;
        if (v0Var != null) {
            v0Var.f1454a.setAllCaps(z2);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        s sVar = this.f1411b;
        if (sVar != null) {
            sVar.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        s sVar = this.f1411b;
        if (sVar != null) {
            sVar.i(mode);
        }
    }

    @Override // a0.l
    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        v0 v0Var = this.f1412c;
        if (v0Var.f1461h == null) {
            v0Var.f1461h = new t2();
        }
        t2 t2Var = v0Var.f1461h;
        t2Var.f1442a = colorStateList;
        t2Var.f1445d = colorStateList != null;
        v0Var.f1455b = t2Var;
        v0Var.f1456c = t2Var;
        v0Var.f1457d = t2Var;
        v0Var.f1458e = t2Var;
        v0Var.f1459f = t2Var;
        v0Var.f1460g = t2Var;
        v0Var.b();
    }

    @Override // a0.l
    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        v0 v0Var = this.f1412c;
        if (v0Var.f1461h == null) {
            v0Var.f1461h = new t2();
        }
        t2 t2Var = v0Var.f1461h;
        t2Var.f1443b = mode;
        t2Var.f1444c = mode != null;
        v0Var.f1455b = t2Var;
        v0Var.f1456c = t2Var;
        v0Var.f1457d = t2Var;
        v0Var.f1458e = t2Var;
        v0Var.f1459f = t2Var;
        v0Var.f1460g = t2Var;
        v0Var.b();
    }

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i2) {
        super.setTextAppearance(context, i2);
        v0 v0Var = this.f1412c;
        if (v0Var != null) {
            v0Var.e(context, i2);
        }
    }

    @Override // android.widget.TextView
    public final void setTextSize(int i2, float f2) {
        boolean z2 = a0.b.f11a;
        if (z2) {
            super.setTextSize(i2, f2);
            return;
        }
        v0 v0Var = this.f1412c;
        if (v0Var == null || z2) {
            return;
        }
        b1 b1Var = v0Var.f1462i;
        if (b1Var.i() && b1Var.f1177a != 0) {
            return;
        }
        b1Var.f(i2, f2);
    }
}
