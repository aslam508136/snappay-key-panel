package j;

/* JADX INFO: loaded from: classes.dex */
public final class b2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1188a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1189b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1190c = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1191d = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1192e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1193f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1194g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1195h = false;

    public final void a(int i2, int i3) {
        this.f1190c = i2;
        this.f1191d = i3;
        this.f1195h = true;
        if (this.f1194g) {
            if (i3 != Integer.MIN_VALUE) {
                this.f1188a = i3;
            }
            if (i2 != Integer.MIN_VALUE) {
                this.f1189b = i2;
                return;
            }
            return;
        }
        if (i2 != Integer.MIN_VALUE) {
            this.f1188a = i2;
        }
        if (i3 != Integer.MIN_VALUE) {
            this.f1189b = i3;
        }
    }
}
