package f;

import android.graphics.drawable.Animatable;

/* JADX INFO: loaded from: classes.dex */
public final class a extends b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f757a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Animatable f758b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a(Animatable animatable, int i2) {
        super(0);
        this.f757a = i2;
        this.f758b = animatable;
    }

    @Override // b.a
    public final void g() {
        int i2 = this.f757a;
        Animatable animatable = this.f758b;
        switch (i2) {
            case 0:
                animatable.start();
                break;
            default:
                ((g0.c) animatable).start();
                break;
        }
    }

    @Override // b.a
    public final void h() {
        int i2 = this.f757a;
        Animatable animatable = this.f758b;
        switch (i2) {
            case 0:
                animatable.stop();
                break;
            default:
                ((g0.c) animatable).stop();
                break;
        }
    }
}
