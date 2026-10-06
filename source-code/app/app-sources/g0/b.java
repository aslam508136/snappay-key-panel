package g0;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public final class b extends Drawable.ConstantState {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Drawable.ConstantState f819a;

    public b(Drawable.ConstantState constantState) {
        this.f819a = constantState;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final boolean canApplyTheme() {
        return this.f819a.canApplyTheme();
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final int getChangingConfigurations() {
        return this.f819a.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable() {
        c cVar = new c(null);
        Drawable drawableNewDrawable = this.f819a.newDrawable();
        cVar.f825b = drawableNewDrawable;
        drawableNewDrawable.setCallback(cVar.f822e);
        return cVar;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable(Resources resources) {
        c cVar = new c(null);
        Drawable drawableNewDrawable = this.f819a.newDrawable(resources);
        cVar.f825b = drawableNewDrawable;
        drawableNewDrawable.setCallback(cVar.f822e);
        return cVar;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable(Resources resources, Resources.Theme theme) {
        c cVar = new c(null);
        Drawable drawableNewDrawable = this.f819a.newDrawable(resources, theme);
        cVar.f825b = drawableNewDrawable;
        drawableNewDrawable.setCallback(cVar.f822e);
        return cVar;
    }
}
