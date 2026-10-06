package s;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import androidx.lifecycle.i;

/* JADX INFO: loaded from: classes.dex */
public abstract class c extends Drawable implements Drawable.Callback, b, a {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final PorterDuff.Mode f1914h = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1915b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public PorterDuff.Mode f1916c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1917d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public e f1918e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1919f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Drawable f1920g;

    public c(Drawable drawable) {
        this.f1918e = new e(this.f1918e);
        b(drawable);
    }

    public abstract boolean a();

    public final void b(Drawable drawable) {
        Drawable drawable2 = this.f1920g;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f1920g = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            setVisible(drawable.isVisible(), true);
            setState(drawable.getState());
            setLevel(drawable.getLevel());
            setBounds(drawable.getBounds());
            e eVar = this.f1918e;
            if (eVar != null) {
                eVar.f1923b = drawable.getConstantState();
            }
        }
        invalidateSelf();
    }

    public final boolean c(int[] iArr) {
        if (!a()) {
            return false;
        }
        e eVar = this.f1918e;
        ColorStateList colorStateList = eVar.f1924c;
        PorterDuff.Mode mode = eVar.f1925d;
        if (colorStateList == null || mode == null) {
            this.f1917d = false;
            clearColorFilter();
        } else {
            int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
            if (!this.f1917d || colorForState != this.f1915b || mode != this.f1916c) {
                setColorFilter(colorForState, mode);
                this.f1915b = colorForState;
                this.f1916c = mode;
                this.f1917d = true;
                return true;
            }
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        this.f1920g.draw(canvas);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getChangingConfigurations() {
        int changingConfigurations = super.getChangingConfigurations();
        e eVar = this.f1918e;
        return changingConfigurations | (eVar != null ? eVar.getChangingConfigurations() : 0) | this.f1920g.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        e eVar = this.f1918e;
        if (eVar == null) {
            return null;
        }
        if (!(eVar.f1923b != null)) {
            return null;
        }
        eVar.f1922a = getChangingConfigurations();
        return this.f1918e;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable getCurrent() {
        return this.f1920g.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return this.f1920g.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return this.f1920g.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getLayoutDirection() {
        return i.y(this.f1920g);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getMinimumHeight() {
        return this.f1920g.getMinimumHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getMinimumWidth() {
        return this.f1920g.getMinimumWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return this.f1920g.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean getPadding(Rect rect) {
        return this.f1920g.getPadding(rect);
    }

    @Override // android.graphics.drawable.Drawable
    public final int[] getState() {
        return this.f1920g.getState();
    }

    @Override // android.graphics.drawable.Drawable
    public final Region getTransparentRegion() {
        return this.f1920g.getTransparentRegion();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isAutoMirrored() {
        return this.f1920g.isAutoMirrored();
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isStateful() {
        e eVar;
        ColorStateList colorStateList = (!a() || (eVar = this.f1918e) == null) ? null : eVar.f1924c;
        return (colorStateList != null && colorStateList.isStateful()) || this.f1920g.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public final void jumpToCurrentState() {
        this.f1920g.jumpToCurrentState();
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable mutate() {
        if (!this.f1919f && super.mutate() == this) {
            this.f1918e = new e(this.f1918e);
            Drawable drawable = this.f1920g;
            if (drawable != null) {
                drawable.mutate();
            }
            e eVar = this.f1918e;
            if (eVar != null) {
                Drawable drawable2 = this.f1920g;
                eVar.f1923b = drawable2 != null ? drawable2.getConstantState() : null;
            }
            this.f1919f = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        Drawable drawable = this.f1920g;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onLayoutDirectionChanged(int i2) {
        return i.a0(this.f1920g, i2);
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onLevelChange(int i2) {
        return this.f1920g.setLevel(i2);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j2) {
        scheduleSelf(runnable, j2);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i2) {
        this.f1920g.setAlpha(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAutoMirrored(boolean z2) {
        this.f1920g.setAutoMirrored(z2);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setChangingConfigurations(int i2) {
        this.f1920g.setChangingConfigurations(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.f1920g.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setDither(boolean z2) {
        this.f1920g.setDither(z2);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setFilterBitmap(boolean z2) {
        this.f1920g.setFilterBitmap(z2);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setState(int[] iArr) {
        return c(iArr) || this.f1920g.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTint(int i2) {
        setTintList(ColorStateList.valueOf(i2));
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        this.f1918e.f1924c = colorStateList;
        c(getState());
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        this.f1918e.f1925d = mode;
        c(getState());
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z2, boolean z3) {
        return super.setVisible(z2, z3) || this.f1920g.setVisible(z2, z3);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }

    public c(e eVar, Resources resources) {
        Drawable.ConstantState constantState;
        this.f1918e = eVar;
        if (eVar == null || (constantState = eVar.f1923b) == null) {
            return;
        }
        b(constantState.newDrawable(resources));
    }
}
