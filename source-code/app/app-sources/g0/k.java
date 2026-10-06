package g0;

/* JADX INFO: loaded from: classes.dex */
public abstract class k extends j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public r.c[] f849a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f850b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f851c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f852d;

    public k() {
        this.f849a = null;
        this.f851c = 0;
    }

    public r.c[] getPathData() {
        return this.f849a;
    }

    public String getPathName() {
        return this.f850b;
    }

    public void setPathData(r.c[] cVarArr) {
        if (!androidx.lifecycle.i.f(this.f849a, cVarArr)) {
            this.f849a = androidx.lifecycle.i.t(cVarArr);
            return;
        }
        r.c[] cVarArr2 = this.f849a;
        for (int i2 = 0; i2 < cVarArr.length; i2++) {
            cVarArr2[i2].f1894a = cVarArr[i2].f1894a;
            int i3 = 0;
            while (true) {
                float[] fArr = cVarArr[i2].f1895b;
                if (i3 < fArr.length) {
                    cVarArr2[i2].f1895b[i3] = fArr[i3];
                    i3++;
                }
            }
        }
    }

    public k(k kVar) {
        this.f849a = null;
        this.f851c = 0;
        this.f850b = kVar.f850b;
        this.f852d = kVar.f852d;
        this.f849a = androidx.lifecycle.i.t(kVar.f849a);
    }
}
