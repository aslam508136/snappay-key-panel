package m0;

import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class c implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1648a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ d f1649b;

    public /* synthetic */ c(d dVar, int i2) {
        this.f1648a = i2;
        this.f1649b = dVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i2 = this.f1648a;
        d dVar = this.f1649b;
        switch (i2) {
            case 0:
                ((Handler) dVar.f1653c).post(new c(dVar, 1));
                break;
            default:
                dVar.a();
                break;
        }
    }
}
