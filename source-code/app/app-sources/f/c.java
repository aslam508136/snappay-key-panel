package f;

import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;

/* JADX INFO: loaded from: classes.dex */
public final class c extends b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ObjectAnimator f759a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f760b;

    public c(AnimationDrawable animationDrawable, boolean z2, boolean z3) {
        super(0);
        int numberOfFrames = animationDrawable.getNumberOfFrames();
        int i2 = z2 ? numberOfFrames - 1 : 0;
        int i3 = z2 ? 0 : numberOfFrames - 1;
        d dVar = new d(animationDrawable, z2);
        ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(animationDrawable, "currentIndex", i2, i3);
        objectAnimatorOfInt.setAutoCancel(true);
        objectAnimatorOfInt.setDuration(dVar.f763c);
        objectAnimatorOfInt.setInterpolator(dVar);
        this.f760b = z3;
        this.f759a = objectAnimatorOfInt;
    }

    @Override // b.a
    public final boolean d() {
        return this.f760b;
    }

    @Override // b.a
    public final void f() {
        this.f759a.reverse();
    }

    @Override // b.a
    public final void g() {
        this.f759a.start();
    }

    @Override // b.a
    public final void h() {
        this.f759a.cancel();
    }
}
