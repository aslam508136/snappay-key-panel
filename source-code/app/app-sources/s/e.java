package s;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public final class e extends Drawable.ConstantState {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1922a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Drawable.ConstantState f1923b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ColorStateList f1924c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public PorterDuff.Mode f1925d;

    public e(e eVar) {
        this.f1924c = null;
        this.f1925d = c.f1914h;
        if (eVar != null) {
            this.f1922a = eVar.f1922a;
            this.f1923b = eVar.f1923b;
            this.f1924c = eVar.f1924c;
            this.f1925d = eVar.f1925d;
        }
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final int getChangingConfigurations() {
        int i2 = this.f1922a;
        Drawable.ConstantState constantState = this.f1923b;
        return i2 | (constantState != null ? constantState.getChangingConfigurations() : 0);
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable() {
        return new d(this, null);
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable(Resources resources) {
        return new d(this, resources);
    }
}
