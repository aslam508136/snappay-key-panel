package f;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.SystemClock;

/* JADX INFO: loaded from: classes.dex */
public abstract class h extends Drawable implements Drawable.Callback {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ int f797n = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g f798b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Rect f799c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Drawable f800d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Drawable f801e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f803g;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f805i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public androidx.activity.b f806j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public long f807k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public long f808l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public f f809m;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f802f = 255;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f804h = -1;

    public final void a(boolean z2) {
        boolean z3;
        boolean z4 = true;
        this.f803g = true;
        long jUptimeMillis = SystemClock.uptimeMillis();
        Drawable drawable = this.f800d;
        if (drawable != null) {
            long j2 = this.f807k;
            if (j2 == 0) {
                z3 = false;
            } else if (j2 <= jUptimeMillis) {
                drawable.setAlpha(this.f802f);
                this.f807k = 0L;
                z3 = false;
            } else {
                drawable.setAlpha(((255 - (((int) ((j2 - jUptimeMillis) * 255)) / this.f798b.f795y)) * this.f802f) / 255);
                z3 = true;
            }
        } else {
            this.f807k = 0L;
            z3 = false;
        }
        Drawable drawable2 = this.f801e;
        if (drawable2 != null) {
            long j3 = this.f808l;
            if (j3 == 0) {
                z4 = z3;
            } else if (j3 <= jUptimeMillis) {
                drawable2.setVisible(false, false);
                this.f801e = null;
                this.f808l = 0L;
                z4 = z3;
            } else {
                drawable2.setAlpha(((((int) ((j3 - jUptimeMillis) * 255)) / this.f798b.f796z) * this.f802f) / 255);
            }
        } else {
            this.f808l = 0L;
            z4 = z3;
        }
        if (z2 && z4) {
            scheduleSelf(this.f806j, jUptimeMillis + 16);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        g gVar = this.f798b;
        if (theme == null) {
            gVar.getClass();
            return;
        }
        gVar.c();
        int i2 = gVar.f778h;
        Drawable[] drawableArr = gVar.f777g;
        for (int i3 = 0; i3 < i2; i3++) {
            Drawable drawable = drawableArr[i3];
            if (drawable != null && drawable.canApplyTheme()) {
                drawableArr[i3].applyTheme(theme);
                gVar.f775e |= drawableArr[i3].getChangingConfigurations();
            }
        }
        Resources resources = theme.getResources();
        if (resources != null) {
            gVar.f772b = resources;
            int i4 = resources.getDisplayMetrics().densityDpi;
            if (i4 == 0) {
                i4 = 160;
            }
            int i5 = gVar.f773c;
            gVar.f773c = i4;
            if (i5 != i4) {
                gVar.f783m = false;
                gVar.f780j = false;
            }
        }
    }

    public final void b(Drawable drawable) {
        if (this.f809m == null) {
            this.f809m = new f();
        }
        f fVar = this.f809m;
        fVar.f770c = drawable.getCallback();
        drawable.setCallback(fVar);
        try {
            if (this.f798b.f795y <= 0 && this.f803g) {
                drawable.setAlpha(this.f802f);
            }
            g gVar = this.f798b;
            if (gVar.C) {
                drawable.setColorFilter(gVar.B);
            } else {
                if (gVar.F) {
                    drawable.setTintList(gVar.D);
                }
                g gVar2 = this.f798b;
                if (gVar2.G) {
                    drawable.setTintMode(gVar2.E);
                }
            }
            drawable.setVisible(isVisible(), true);
            drawable.setDither(this.f798b.f793w);
            drawable.setState(getState());
            drawable.setLevel(getLevel());
            drawable.setBounds(getBounds());
            if (Build.VERSION.SDK_INT >= 23) {
                androidx.lifecycle.i.a0(drawable, androidx.lifecycle.i.y(this));
            }
            drawable.setAutoMirrored(this.f798b.A);
            Rect rect = this.f799c;
            if (rect != null) {
                drawable.setHotspotBounds(rect.left, rect.top, rect.right, rect.bottom);
            }
        } finally {
            f fVar2 = this.f809m;
            Drawable.Callback callback = (Drawable.Callback) fVar2.f770c;
            fVar2.f770c = null;
            drawable.setCallback(callback);
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0055  */
    public final boolean c(int i2) {
        if (i2 == this.f804h) {
            return false;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (this.f798b.f796z > 0) {
            Drawable drawable = this.f801e;
            if (drawable != null) {
                drawable.setVisible(false, false);
            }
            Drawable drawable2 = this.f800d;
            if (drawable2 != null) {
                this.f801e = drawable2;
                this.f808l = ((long) this.f798b.f796z) + jUptimeMillis;
            } else {
                this.f801e = null;
                this.f808l = 0L;
            }
        } else {
            Drawable drawable3 = this.f800d;
            if (drawable3 != null) {
                drawable3.setVisible(false, false);
            }
        }
        if (i2 >= 0) {
            g gVar = this.f798b;
            if (i2 < gVar.f778h) {
                Drawable drawableD = gVar.d(i2);
                this.f800d = drawableD;
                this.f804h = i2;
                if (drawableD != null) {
                    int i3 = this.f798b.f795y;
                    if (i3 > 0) {
                        this.f807k = jUptimeMillis + ((long) i3);
                    }
                    b(drawableD);
                }
            } else {
                this.f800d = null;
                this.f804h = -1;
            }
        } else {
            this.f800d = null;
            this.f804h = -1;
        }
        int i4 = 1;
        if (this.f807k != 0 || this.f808l != 0) {
            androidx.activity.b bVar = this.f806j;
            if (bVar == null) {
                this.f806j = new androidx.activity.b(this, i4);
            } else {
                unscheduleSelf(bVar);
            }
            a(true);
        }
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean canApplyTheme() {
        return this.f798b.canApplyTheme();
    }

    public abstract void d(b bVar);

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Drawable drawable = this.f800d;
        if (drawable != null) {
            drawable.draw(canvas);
        }
        Drawable drawable2 = this.f801e;
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        return this.f802f;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getChangingConfigurations() {
        int changingConfigurations = super.getChangingConfigurations();
        g gVar = this.f798b;
        return changingConfigurations | gVar.f775e | gVar.f774d;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        boolean z2;
        g gVar = this.f798b;
        if (!gVar.f791u) {
            gVar.c();
            gVar.f791u = true;
            int i2 = gVar.f778h;
            Drawable[] drawableArr = gVar.f777g;
            int i3 = 0;
            while (true) {
                if (i3 >= i2) {
                    gVar.f792v = true;
                    z2 = true;
                    break;
                }
                if (drawableArr[i3].getConstantState() == null) {
                    gVar.f792v = false;
                    z2 = false;
                    break;
                }
                i3++;
            }
        } else {
            z2 = gVar.f792v;
        }
        if (!z2) {
            return null;
        }
        this.f798b.f774d = getChangingConfigurations();
        return this.f798b;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable getCurrent() {
        return this.f800d;
    }

    @Override // android.graphics.drawable.Drawable
    public final void getHotspotBounds(Rect rect) {
        Rect rect2 = this.f799c;
        if (rect2 != null) {
            rect.set(rect2);
        } else {
            super.getHotspotBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        g gVar = this.f798b;
        if (gVar.f782l) {
            if (!gVar.f783m) {
                gVar.b();
            }
            return gVar.f785o;
        }
        Drawable drawable = this.f800d;
        if (drawable != null) {
            return drawable.getIntrinsicHeight();
        }
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        g gVar = this.f798b;
        if (gVar.f782l) {
            if (!gVar.f783m) {
                gVar.b();
            }
            return gVar.f784n;
        }
        Drawable drawable = this.f800d;
        if (drawable != null) {
            return drawable.getIntrinsicWidth();
        }
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getMinimumHeight() {
        g gVar = this.f798b;
        if (gVar.f782l) {
            if (!gVar.f783m) {
                gVar.b();
            }
            return gVar.f787q;
        }
        Drawable drawable = this.f800d;
        if (drawable != null) {
            return drawable.getMinimumHeight();
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getMinimumWidth() {
        g gVar = this.f798b;
        if (gVar.f782l) {
            if (!gVar.f783m) {
                gVar.b();
            }
            return gVar.f786p;
        }
        Drawable drawable = this.f800d;
        if (drawable != null) {
            return drawable.getMinimumWidth();
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        Drawable drawable = this.f800d;
        if (drawable == null || !drawable.isVisible()) {
            return -2;
        }
        g gVar = this.f798b;
        if (gVar.f788r) {
            return gVar.f789s;
        }
        gVar.c();
        int i2 = gVar.f778h;
        Drawable[] drawableArr = gVar.f777g;
        int opacity = i2 > 0 ? drawableArr[0].getOpacity() : -2;
        for (int i3 = 1; i3 < i2; i3++) {
            opacity = Drawable.resolveOpacity(opacity, drawableArr[i3].getOpacity());
        }
        gVar.f789s = opacity;
        gVar.f788r = true;
        return opacity;
    }

    @Override // android.graphics.drawable.Drawable
    public final void getOutline(Outline outline) {
        Drawable drawable = this.f800d;
        if (drawable != null) {
            drawable.getOutline(outline);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean getPadding(Rect rect) {
        boolean padding;
        g gVar = this.f798b;
        Rect rect2 = null;
        if (!gVar.f779i) {
            Rect rect3 = gVar.f781k;
            if (rect3 != null || gVar.f780j) {
                rect2 = rect3;
            } else {
                gVar.c();
                Rect rect4 = new Rect();
                int i2 = gVar.f778h;
                Drawable[] drawableArr = gVar.f777g;
                for (int i3 = 0; i3 < i2; i3++) {
                    if (drawableArr[i3].getPadding(rect4)) {
                        if (rect2 == null) {
                            rect2 = new Rect(0, 0, 0, 0);
                        }
                        int i4 = rect4.left;
                        if (i4 > rect2.left) {
                            rect2.left = i4;
                        }
                        int i5 = rect4.top;
                        if (i5 > rect2.top) {
                            rect2.top = i5;
                        }
                        int i6 = rect4.right;
                        if (i6 > rect2.right) {
                            rect2.right = i6;
                        }
                        int i7 = rect4.bottom;
                        if (i7 > rect2.bottom) {
                            rect2.bottom = i7;
                        }
                    }
                }
                gVar.f780j = true;
                gVar.f781k = rect2;
            }
        }
        if (rect2 != null) {
            rect.set(rect2);
            padding = (((rect2.left | rect2.top) | rect2.bottom) | rect2.right) != 0;
        } else {
            Drawable drawable = this.f800d;
            padding = drawable != null ? drawable.getPadding(rect) : super.getPadding(rect);
        }
        if (this.f798b.A && androidx.lifecycle.i.y(this) == 1) {
            int i8 = rect.left;
            rect.left = rect.right;
            rect.right = i8;
        }
        return padding;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void invalidateDrawable(Drawable drawable) {
        g gVar = this.f798b;
        if (gVar != null) {
            gVar.f788r = false;
            gVar.f790t = false;
        }
        if (drawable != this.f800d || getCallback() == null) {
            return;
        }
        getCallback().invalidateDrawable(this);
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isAutoMirrored() {
        return this.f798b.A;
    }

    @Override // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        boolean z2;
        Drawable drawable = this.f801e;
        boolean z3 = true;
        if (drawable != null) {
            drawable.jumpToCurrentState();
            this.f801e = null;
            z2 = true;
        } else {
            z2 = false;
        }
        Drawable drawable2 = this.f800d;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
            if (this.f803g) {
                this.f800d.setAlpha(this.f802f);
            }
        }
        if (this.f808l != 0) {
            this.f808l = 0L;
            z2 = true;
        }
        if (this.f807k != 0) {
            this.f807k = 0L;
        } else {
            z3 = z2;
        }
        if (z3) {
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.f805i && super.mutate() == this) {
            e eVar = (e) this;
            b bVar = new b(eVar.f764q, eVar, null);
            bVar.e();
            d(bVar);
            this.f805i = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        Drawable drawable = this.f801e;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
        Drawable drawable2 = this.f800d;
        if (drawable2 != null) {
            drawable2.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onLayoutDirectionChanged(int i2) {
        g gVar = this.f798b;
        int i3 = this.f804h;
        int i4 = gVar.f778h;
        Drawable[] drawableArr = gVar.f777g;
        boolean z2 = false;
        for (int i5 = 0; i5 < i4; i5++) {
            Drawable drawable = drawableArr[i5];
            if (drawable != null) {
                boolean zA0 = Build.VERSION.SDK_INT >= 23 ? androidx.lifecycle.i.a0(drawable, i2) : false;
                if (i5 == i3) {
                    z2 = zA0;
                }
            }
        }
        gVar.f794x = i2;
        return z2;
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onLevelChange(int i2) {
        Drawable drawable = this.f801e;
        if (drawable != null) {
            return drawable.setLevel(i2);
        }
        Drawable drawable2 = this.f800d;
        if (drawable2 != null) {
            return drawable2.setLevel(i2);
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j2) {
        if (drawable != this.f800d || getCallback() == null) {
            return;
        }
        getCallback().scheduleDrawable(this, runnable, j2);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i2) {
        if (this.f803g && this.f802f == i2) {
            return;
        }
        this.f803g = true;
        this.f802f = i2;
        Drawable drawable = this.f800d;
        if (drawable != null) {
            if (this.f807k == 0) {
                drawable.setAlpha(i2);
            } else {
                a(false);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAutoMirrored(boolean z2) {
        g gVar = this.f798b;
        if (gVar.A != z2) {
            gVar.A = z2;
            Drawable drawable = this.f800d;
            if (drawable != null) {
                drawable.setAutoMirrored(z2);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        g gVar = this.f798b;
        gVar.C = true;
        if (gVar.B != colorFilter) {
            gVar.B = colorFilter;
            Drawable drawable = this.f800d;
            if (drawable != null) {
                drawable.setColorFilter(colorFilter);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setDither(boolean z2) {
        g gVar = this.f798b;
        if (gVar.f793w != z2) {
            gVar.f793w = z2;
            Drawable drawable = this.f800d;
            if (drawable != null) {
                drawable.setDither(z2);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setHotspot(float f2, float f3) {
        Drawable drawable = this.f800d;
        if (drawable != null) {
            drawable.setHotspot(f2, f3);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setHotspotBounds(int i2, int i3, int i4, int i5) {
        Rect rect = this.f799c;
        if (rect == null) {
            this.f799c = new Rect(i2, i3, i4, i5);
        } else {
            rect.set(i2, i3, i4, i5);
        }
        Drawable drawable = this.f800d;
        if (drawable != null) {
            drawable.setHotspotBounds(i2, i3, i4, i5);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintList(ColorStateList colorStateList) {
        g gVar = this.f798b;
        gVar.F = true;
        if (gVar.D != colorStateList) {
            gVar.D = colorStateList;
            androidx.lifecycle.i.e0(this.f800d, colorStateList);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintMode(PorterDuff.Mode mode) {
        g gVar = this.f798b;
        gVar.G = true;
        if (gVar.E != mode) {
            gVar.E = mode;
            androidx.lifecycle.i.f0(this.f800d, mode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z2, boolean z3) {
        boolean visible = super.setVisible(z2, z3);
        Drawable drawable = this.f801e;
        if (drawable != null) {
            drawable.setVisible(z2, z3);
        }
        Drawable drawable2 = this.f800d;
        if (drawable2 != null) {
            drawable2.setVisible(z2, z3);
        }
        return visible;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        if (drawable != this.f800d || getCallback() == null) {
            return;
        }
        getCallback().unscheduleDrawable(this, runnable);
    }
}
