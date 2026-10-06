package x;

import android.animation.ValueAnimator;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class x implements ValueAnimator.AnimatorUpdateListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ androidx.fragment.app.i f2019a;

    public x(androidx.fragment.app.i iVar, View view) {
        this.f2019a = iVar;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        ((View) ((d.i0) this.f2019a.f327a).f683d.getParent()).invalidate();
    }
}
