package a0;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f4c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f5d;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f9h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f10i;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f6e = Long.MIN_VALUE;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f8g = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f7f = 0;

    public final float a(long j2) {
        long j3 = this.f6e;
        if (j2 < j3) {
            return 0.0f;
        }
        long j4 = this.f8g;
        if (j4 < 0 || j2 < j4) {
            return d.b((j2 - j3) / this.f2a, 0.0f, 1.0f) * 0.5f;
        }
        float f2 = this.f9h;
        return (d.b((j2 - j4) / this.f10i, 0.0f, 1.0f) * f2) + (1.0f - f2);
    }
}
