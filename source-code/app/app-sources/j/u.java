package j;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CheckBox;
import com.snapay.app.R;

/* JADX INFO: loaded from: classes.dex */
public final class u extends CheckBox {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final w f1446b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final s f1447c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final v0 f1448d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.checkboxStyle);
        s2.a(context);
        r2.a(this, getContext());
        w wVar = new w(this);
        this.f1446b = wVar;
        wVar.b(attributeSet, R.attr.checkboxStyle);
        s sVar = new s(this);
        this.f1447c = sVar;
        sVar.d(attributeSet, R.attr.checkboxStyle);
        v0 v0Var = new v0(this);
        this.f1448d = v0Var;
        v0Var.d(attributeSet, R.attr.checkboxStyle);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        s sVar = this.f1447c;
        if (sVar != null) {
            sVar.a();
        }
        v0 v0Var = this.f1448d;
        if (v0Var != null) {
            v0Var.b();
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        w wVar = this.f1446b;
        if (wVar != null) {
            wVar.getClass();
        }
        return compoundPaddingLeft;
    }

    public ColorStateList getSupportBackgroundTintList() {
        s sVar = this.f1447c;
        if (sVar != null) {
            return sVar.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        s sVar = this.f1447c;
        if (sVar != null) {
            return sVar.c();
        }
        return null;
    }

    public ColorStateList getSupportButtonTintList() {
        w wVar = this.f1446b;
        if (wVar != null) {
            return wVar.f1474b;
        }
        return null;
    }

    public PorterDuff.Mode getSupportButtonTintMode() {
        w wVar = this.f1446b;
        if (wVar != null) {
            return wVar.f1475c;
        }
        return null;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        s sVar = this.f1447c;
        if (sVar != null) {
            sVar.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        s sVar = this.f1447c;
        if (sVar != null) {
            sVar.f(i2);
        }
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(int i2) {
        setButtonDrawable(e.b.c(getContext(), i2));
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        s sVar = this.f1447c;
        if (sVar != null) {
            sVar.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        s sVar = this.f1447c;
        if (sVar != null) {
            sVar.i(mode);
        }
    }

    public void setSupportButtonTintList(ColorStateList colorStateList) {
        w wVar = this.f1446b;
        if (wVar != null) {
            wVar.f1474b = colorStateList;
            wVar.f1476d = true;
            wVar.a();
        }
    }

    public void setSupportButtonTintMode(PorterDuff.Mode mode) {
        w wVar = this.f1446b;
        if (wVar != null) {
            wVar.f1475c = mode;
            wVar.f1477e = true;
            wVar.a();
        }
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        w wVar = this.f1446b;
        if (wVar != null) {
            if (wVar.f1478f) {
                wVar.f1478f = false;
            } else {
                wVar.f1478f = true;
                wVar.a();
            }
        }
    }
}
