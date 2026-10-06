package g0;

import android.animation.TypeEvaluator;

/* JADX INFO: loaded from: classes.dex */
public final class d implements TypeEvaluator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public r.c[] f823a;

    @Override // android.animation.TypeEvaluator
    public final Object evaluate(float f2, Object obj, Object obj2) {
        r.c[] cVarArr = (r.c[]) obj;
        r.c[] cVarArr2 = (r.c[]) obj2;
        if (!androidx.lifecycle.i.f(cVarArr, cVarArr2)) {
            throw new IllegalArgumentException("Can't interpolate between two incompatible pathData");
        }
        if (!androidx.lifecycle.i.f(this.f823a, cVarArr)) {
            this.f823a = androidx.lifecycle.i.t(cVarArr);
        }
        for (int i2 = 0; i2 < cVarArr.length; i2++) {
            r.c cVar = this.f823a[i2];
            r.c cVar2 = cVarArr[i2];
            r.c cVar3 = cVarArr2[i2];
            cVar.getClass();
            cVar.f1894a = cVar2.f1894a;
            int i3 = 0;
            while (true) {
                float[] fArr = cVar2.f1895b;
                if (i3 < fArr.length) {
                    cVar.f1895b[i3] = (cVar3.f1895b[i3] * f2) + ((1.0f - f2) * fArr[i3]);
                    i3++;
                }
            }
        }
        return this.f823a;
    }
}
