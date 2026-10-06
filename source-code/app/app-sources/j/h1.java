package j;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public final class h1 extends Drawable implements Drawable.Callback {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Drawable f1247b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1248c;

    public h1(Drawable drawable) {
        Drawable drawable2 = this.f1247b;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f1247b = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        this.f1248c = true;
    }

    @Override // android.graphics.drawable.Drawable
    /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
    public final void setTintList(ColorStateList colorStateList) {
        this.f1247b.setTintList(colorStateList);
    }

    @Override // android.graphics.drawable.Drawable
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public final void setTintMode(PorterDuff.Mode mode) {
        this.f1247b.setTintMode(mode);
    }

    public final boolean C(boolean z2, boolean z3) {
        return super.setVisible(z2, z3) || this.f1247b.setVisible(z2, z3);
    }

    public final void a(Canvas canvas) {
        this.f1247b.draw(canvas);
    }

    @Override // android.graphics.drawable.Drawable
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final int getChangingConfigurations() {
        return this.f1247b.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final Drawable getCurrent() {
        return this.f1247b.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public final int getIntrinsicHeight() {
        return this.f1247b.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        if (this.f1248c) {
            a(canvas);
        }
    }

    @Override // android.graphics.drawable.Drawable
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public final int getIntrinsicWidth() {
        return this.f1247b.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public final int getMinimumHeight() {
        return this.f1247b.getMinimumHeight();
    }

    @Override // android.graphics.drawable.Drawable
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public final int getMinimumWidth() {
        return this.f1247b.getMinimumWidth();
    }

    @Override // android.graphics.drawable.Drawable
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public final int getOpacity() {
        return this.f1247b.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public final boolean getPadding(Rect rect) {
        return this.f1247b.getPadding(rect);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
    public final int[] getState() {
        return this.f1247b.getState();
    }

    @Override // android.graphics.drawable.Drawable
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public final Region getTransparentRegion() {
        return this.f1247b.getTransparentRegion();
    }

    @Override // android.graphics.drawable.Drawable
    /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
    public final boolean isAutoMirrored() {
        return this.f1247b.isAutoMirrored();
    }

    @Override // android.graphics.drawable.Drawable
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public final boolean isStateful() {
        return this.f1247b.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public final void jumpToCurrentState() {
        this.f1247b.jumpToCurrentState();
    }

    @Override // android.graphics.drawable.Drawable
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public final void onBoundsChange(Rect rect) {
        this.f1247b.setBounds(rect);
    }

    @Override // android.graphics.drawable.Drawable
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public final boolean onLevelChange(int i2) {
        return this.f1247b.setLevel(i2);
    }

    @Override // android.graphics.drawable.Drawable
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public final void setAlpha(int i2) {
        this.f1247b.setAlpha(i2);
    }

    @Override // android.graphics.drawable.Drawable
    /* JADX INFO: renamed from: r, reason: merged with bridge method [inline-methods] */
    public final void setAutoMirrored(boolean z2) {
        this.f1247b.setAutoMirrored(z2);
    }

    @Override // android.graphics.drawable.Drawable
    /* JADX INFO: renamed from: s, reason: merged with bridge method [inline-methods] */
    public final void setChangingConfigurations(int i2) {
        this.f1247b.setChangingConfigurations(i2);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j2) {
        scheduleSelf(runnable, j2);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setHotspot(float f2, float f3) {
        if (this.f1248c) {
            w(f2, f3);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setHotspotBounds(int i2, int i3, int i4, int i5) {
        if (this.f1248c) {
            x(i2, i3, i4, i5);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setState(int[] iArr) {
        if (this.f1248c) {
            return y(iArr);
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z2, boolean z3) {
        if (this.f1248c) {
            return C(z2, z3);
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    /* JADX INFO: renamed from: t, reason: merged with bridge method [inline-methods] */
    public final void setColorFilter(ColorFilter colorFilter) {
        this.f1247b.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    /* JADX INFO: renamed from: u, reason: merged with bridge method [inline-methods] */
    public final void setDither(boolean z2) {
        this.f1247b.setDither(z2);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }

    @Override // android.graphics.drawable.Drawable
    /* JADX INFO: renamed from: v, reason: merged with bridge method [inline-methods] */
    public final void setFilterBitmap(boolean z2) {
        this.f1247b.setFilterBitmap(z2);
    }

    public final void w(float f2, float f3) {
        this.f1247b.setHotspot(f2, f3);
    }

    public final void x(int i2, int i3, int i4, int i5) {
        this.f1247b.setHotspotBounds(i2, i3, i4, i5);
    }

    public final boolean y(int[] iArr) {
        return this.f1247b.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    /* JADX INFO: renamed from: z, reason: merged with bridge method [inline-methods] */
    public final void setTint(int i2) {
        this.f1247b.setTint(i2);
    }
}
