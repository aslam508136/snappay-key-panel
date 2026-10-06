package j;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.snapay.app.R;

/* JADX INFO: loaded from: classes.dex */
public final class l extends b0 implements n {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ m f1280d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(m mVar, Context context) {
        super(context, null, R.attr.actionOverflowButtonStyle);
        this.f1280d = mVar;
        setClickable(true);
        setFocusable(true);
        setVisibility(0);
        setEnabled(true);
        androidx.lifecycle.i.g0(this, getContentDescription());
        setOnTouchListener(new k(this, this, mVar, 0));
    }

    @Override // j.n
    public final boolean a() {
        return false;
    }

    @Override // j.n
    public final boolean b() {
        return false;
    }

    @Override // android.view.View
    public final boolean performClick() {
        if (super.performClick()) {
            return true;
        }
        playSoundEffect(0);
        this.f1280d.l();
        return true;
    }

    @Override // android.widget.ImageView
    public final boolean setFrame(int i2, int i3, int i4, int i5) {
        boolean frame = super.setFrame(i2, i3, i4, i5);
        Drawable drawable = getDrawable();
        Drawable background = getBackground();
        if (drawable != null && background != null) {
            int width = getWidth();
            int height = getHeight();
            int iMax = Math.max(width, height) / 2;
            int paddingLeft = (width + (getPaddingLeft() - getPaddingRight())) / 2;
            int paddingTop = (height + (getPaddingTop() - getPaddingBottom())) / 2;
            background.setHotspotBounds(paddingLeft - iMax, paddingTop - iMax, paddingLeft + iMax, paddingTop + iMax);
        }
        return frame;
    }
}
