package j;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RatingBar;
import com.snapay.app.R;

/* JADX INFO: loaded from: classes.dex */
public final class g0 extends RatingBar {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e0 f1240b;

    public g0(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.ratingBarStyle);
        r2.a(this, getContext());
        e0 e0Var = new e0(this);
        this.f1240b = e0Var;
        e0Var.a(attributeSet, R.attr.ratingBarStyle);
    }

    @Override // android.widget.RatingBar, android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public final synchronized void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        Bitmap bitmap = this.f1240b.f1231b;
        if (bitmap != null) {
            setMeasuredDimension(View.resolveSizeAndState(bitmap.getWidth() * getNumStars(), i2, 0), getMeasuredHeight());
        }
    }
}
