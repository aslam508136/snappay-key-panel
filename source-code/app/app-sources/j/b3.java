package j;

/* JADX INFO: loaded from: classes.dex */
public final class b3 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1196a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ c3 f1197b;

    public /* synthetic */ b3(c3 c3Var, int i2) {
        this.f1196a = i2;
        this.f1197b = c3Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i2 = this.f1196a;
        c3 c3Var = this.f1197b;
        switch (i2) {
            case 0:
                c3Var.c(false);
                break;
            default:
                c3Var.a();
                break;
        }
    }
}
