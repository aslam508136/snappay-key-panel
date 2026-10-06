package j;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;
import com.snapay.app.R;

/* JADX INFO: loaded from: classes.dex */
public final class j0 extends SeekBar {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final k0 f1268b;

    public j0(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.seekBarStyle);
        r2.a(this, getContext());
        k0 k0Var = new k0(this);
        this.f1268b = k0Var;
        k0Var.a(attributeSet, R.attr.seekBarStyle);
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        k0 k0Var = this.f1268b;
        Drawable drawable = k0Var.f1273e;
        if (drawable == null || !drawable.isStateful()) {
            return;
        }
        SeekBar seekBar = k0Var.f1272d;
        if (drawable.setState(seekBar.getDrawableState())) {
            seekBar.invalidateDrawable(drawable);
        }
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public final void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f1268b.f1273e;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public final synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f1268b.d(canvas);
    }
}
