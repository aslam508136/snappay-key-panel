package f;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public abstract class j extends h {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public i f810o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f811p;

    @Override // f.h, android.graphics.drawable.Drawable
    public final void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
        onStateChange(getState());
    }

    @Override // f.h, android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.f811p) {
            super.mutate();
            this.f810o.e();
            this.f811p = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public abstract boolean onStateChange(int[] iArr);
}
