package j;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ToggleButton;

/* JADX INFO: loaded from: classes.dex */
public final class c1 extends ToggleButton {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s f1204b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final v0 f1205c;

    public c1(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.buttonStyleToggle);
        r2.a(this, getContext());
        s sVar = new s(this);
        this.f1204b = sVar;
        sVar.d(attributeSet, R.attr.buttonStyleToggle);
        v0 v0Var = new v0(this);
        this.f1205c = v0Var;
        v0Var.d(attributeSet, R.attr.buttonStyleToggle);
    }

    @Override // android.widget.ToggleButton, android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        s sVar = this.f1204b;
        if (sVar != null) {
            sVar.a();
        }
        v0 v0Var = this.f1205c;
        if (v0Var != null) {
            v0Var.b();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        s sVar = this.f1204b;
        if (sVar != null) {
            return sVar.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        s sVar = this.f1204b;
        if (sVar != null) {
            return sVar.c();
        }
        return null;
    }

    @Override // android.widget.ToggleButton, android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        s sVar = this.f1204b;
        if (sVar != null) {
            sVar.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        s sVar = this.f1204b;
        if (sVar != null) {
            sVar.f(i2);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        s sVar = this.f1204b;
        if (sVar != null) {
            sVar.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        s sVar = this.f1204b;
        if (sVar != null) {
            sVar.i(mode);
        }
    }
}
