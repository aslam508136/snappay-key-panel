package androidx.fragment.app;

/* JADX INFO: loaded from: classes.dex */
public final class j implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f328a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f329b;

    public /* synthetic */ j(Object obj, int i2) {
        this.f328a = i2;
        this.f329b = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i2 = this.f328a;
        Object obj = this.f329b;
        switch (i2) {
            case 0:
                androidx.activity.c.b(obj);
                throw null;
            case 1:
                androidx.activity.c.b(obj);
                throw null;
            default:
                ((r) obj).e(true);
                return;
        }
    }
}
