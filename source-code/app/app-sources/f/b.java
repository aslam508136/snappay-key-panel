package f;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import m.k;

/* JADX INFO: loaded from: classes.dex */
public final class b extends i {
    public static final /* synthetic */ int K = 0;
    public m.d I;
    public k J;

    public b(b bVar, e eVar, Resources resources) {
        k kVar;
        super(bVar, eVar, resources);
        if (bVar != null) {
            this.I = bVar.I;
            kVar = bVar.J;
        } else {
            this.I = new m.d();
            kVar = new k();
        }
        this.J = kVar;
    }

    @Override // f.g
    public final void e() {
        this.I = this.I.clone();
        this.J = this.J.clone();
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable() {
        return new e(this, null);
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable(Resources resources) {
        return new e(this, resources);
    }
}
