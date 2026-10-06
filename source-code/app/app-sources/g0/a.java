package g0;

import android.animation.AnimatorSet;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class a extends Drawable.ConstantState {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public o f815a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public AnimatorSet f816b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ArrayList f817c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public m.b f818d;

    public a(f.f fVar) {
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final int getChangingConfigurations() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable() {
        throw new IllegalStateException("No constant state support for SDK < 24.");
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable(Resources resources) {
        throw new IllegalStateException("No constant state support for SDK < 24.");
    }
}
