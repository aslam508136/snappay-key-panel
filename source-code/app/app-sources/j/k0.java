package j;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;
import com.snapay.app.R;

/* JADX INFO: loaded from: classes.dex */
public final class k0 extends e0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final SeekBar f1272d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Drawable f1273e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ColorStateList f1274f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public PorterDuff.Mode f1275g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1276h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1277i;

    public k0(SeekBar seekBar) {
        super(seekBar);
        this.f1274f = null;
        this.f1275g = null;
        this.f1276h = false;
        this.f1277i = false;
        this.f1272d = seekBar;
    }

    @Override // j.e0
    public final void a(AttributeSet attributeSet, int i2) {
        super.a(attributeSet, R.attr.seekBarStyle);
        SeekBar seekBar = this.f1272d;
        Context context = seekBar.getContext();
        int[] iArr = c.a.f487g;
        m0.a aVarU = m0.a.u(context, attributeSet, iArr, R.attr.seekBarStyle);
        x.u.d(seekBar, seekBar.getContext(), iArr, attributeSet, (TypedArray) aVarU.f1643b, R.attr.seekBarStyle);
        Drawable drawableL = aVarU.l(0);
        if (drawableL != null) {
            seekBar.setThumb(drawableL);
        }
        Drawable drawableK = aVarU.k(1);
        Drawable drawable = this.f1273e;
        if (drawable != null) {
            drawable.setCallback(null);
        }
        this.f1273e = drawableK;
        if (drawableK != null) {
            drawableK.setCallback(seekBar);
            androidx.lifecycle.i.a0(drawableK, seekBar.getLayoutDirection());
            if (drawableK.isStateful()) {
                drawableK.setState(seekBar.getDrawableState());
            }
            c();
        }
        seekBar.invalidate();
        if (aVarU.s(3)) {
            this.f1275g = g1.c(aVarU.n(3, -1), this.f1275g);
            this.f1277i = true;
        }
        if (aVarU.s(2)) {
            this.f1274f = aVarU.h(2);
            this.f1276h = true;
        }
        aVarU.w();
        c();
    }

    public final void c() {
        Drawable drawable = this.f1273e;
        if (drawable != null) {
            if (this.f1276h || this.f1277i) {
                Drawable drawableK0 = androidx.lifecycle.i.k0(drawable.mutate());
                this.f1273e = drawableK0;
                if (this.f1276h) {
                    drawableK0.setTintList(this.f1274f);
                }
                if (this.f1277i) {
                    this.f1273e.setTintMode(this.f1275g);
                }
                if (this.f1273e.isStateful()) {
                    this.f1273e.setState(this.f1272d.getDrawableState());
                }
            }
        }
    }

    public final void d(Canvas canvas) {
        if (this.f1273e != null) {
            SeekBar seekBar = this.f1272d;
            int max = seekBar.getMax();
            if (max > 1) {
                int intrinsicWidth = this.f1273e.getIntrinsicWidth();
                int intrinsicHeight = this.f1273e.getIntrinsicHeight();
                int i2 = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                int i3 = intrinsicHeight >= 0 ? intrinsicHeight / 2 : 1;
                this.f1273e.setBounds(-i2, -i3, i2, i3);
                float width = ((seekBar.getWidth() - seekBar.getPaddingLeft()) - seekBar.getPaddingRight()) / max;
                int iSave = canvas.save();
                canvas.translate(seekBar.getPaddingLeft(), seekBar.getHeight() / 2);
                for (int i4 = 0; i4 <= max; i4++) {
                    this.f1273e.draw(canvas);
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(iSave);
            }
        }
    }
}
