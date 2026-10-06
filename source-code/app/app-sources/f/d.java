package f;

import android.animation.TimeInterpolator;
import android.graphics.drawable.AnimationDrawable;

/* JADX INFO: loaded from: classes.dex */
public final class d implements TimeInterpolator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int[] f761a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f762b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f763c;

    public d(AnimationDrawable animationDrawable, boolean z2) {
        int numberOfFrames = animationDrawable.getNumberOfFrames();
        this.f762b = numberOfFrames;
        int[] iArr = this.f761a;
        if (iArr == null || iArr.length < numberOfFrames) {
            this.f761a = new int[numberOfFrames];
        }
        int[] iArr2 = this.f761a;
        int i2 = 0;
        for (int i3 = 0; i3 < numberOfFrames; i3++) {
            int duration = animationDrawable.getDuration(z2 ? (numberOfFrames - i3) - 1 : i3);
            iArr2[i3] = duration;
            i2 += duration;
        }
        this.f763c = i2;
    }

    @Override // android.animation.TimeInterpolator
    public final float getInterpolation(float f2) {
        int i2 = (int) ((f2 * this.f763c) + 0.5f);
        int i3 = this.f762b;
        int[] iArr = this.f761a;
        int i4 = 0;
        while (i4 < i3) {
            int i5 = iArr[i4];
            if (i2 < i5) {
                break;
            }
            i2 -= i5;
            i4++;
        }
        return (i4 / i3) + (i4 < i3 ? i2 / this.f763c : 0.0f);
    }
}
