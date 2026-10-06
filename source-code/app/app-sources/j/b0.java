package j;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes.dex */
public class b0 extends ImageView {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s f1173b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final h.g f1174c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b0(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        s2.a(context);
        r2.a(this, getContext());
        s sVar = new s(this);
        this.f1173b = sVar;
        sVar.d(attributeSet, i2);
        h.g gVar = new h.g(this);
        this.f1174c = gVar;
        gVar.h(attributeSet, i2);
    }

    @Override // android.widget.ImageView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        s sVar = this.f1173b;
        if (sVar != null) {
            sVar.a();
        }
        h.g gVar = this.f1174c;
        if (gVar != null) {
            gVar.e();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        s sVar = this.f1173b;
        if (sVar != null) {
            return sVar.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        s sVar = this.f1173b;
        if (sVar != null) {
            return sVar.c();
        }
        return null;
    }

    public ColorStateList getSupportImageTintList() {
        t2 t2Var;
        h.g gVar = this.f1174c;
        if (gVar == null || (t2Var = (t2) gVar.f908c) == null) {
            return null;
        }
        return t2Var.f1442a;
    }

    public PorterDuff.Mode getSupportImageTintMode() {
        t2 t2Var;
        h.g gVar = this.f1174c;
        if (gVar == null || (t2Var = (t2) gVar.f908c) == null) {
            return null;
        }
        return t2Var.f1443b;
    }

    @Override // android.widget.ImageView, android.view.View
    public final boolean hasOverlappingRendering() {
        return ((((ImageView) this.f1174c.f906a).getBackground() instanceof RippleDrawable) ^ true) && super.hasOverlappingRendering();
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        s sVar = this.f1173b;
        if (sVar != null) {
            sVar.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        s sVar = this.f1173b;
        if (sVar != null) {
            sVar.f(i2);
        }
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        h.g gVar = this.f1174c;
        if (gVar != null) {
            gVar.e();
        }
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        h.g gVar = this.f1174c;
        if (gVar != null) {
            gVar.e();
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i2) {
        h.g gVar = this.f1174c;
        if (gVar != null) {
            gVar.i(i2);
        }
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        h.g gVar = this.f1174c;
        if (gVar != null) {
            gVar.e();
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        s sVar = this.f1173b;
        if (sVar != null) {
            sVar.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        s sVar = this.f1173b;
        if (sVar != null) {
            sVar.i(mode);
        }
    }

    public void setSupportImageTintList(ColorStateList colorStateList) {
        h.g gVar = this.f1174c;
        if (gVar != null) {
            gVar.j(colorStateList);
        }
    }

    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        h.g gVar = this.f1174c;
        if (gVar != null) {
            gVar.k(mode);
        }
    }
}
