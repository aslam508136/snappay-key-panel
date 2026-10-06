package u;

import android.graphics.Typeface;
import android.os.Handler;
import j.t0;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class a implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1926a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f1927b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f1928c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f1929d;

    public /* synthetic */ a(Object obj, Object obj2, Object obj3, int i2) {
        this.f1926a = i2;
        this.f1929d = obj;
        this.f1927b = obj2;
        this.f1928c = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object objCall;
        int i2 = this.f1926a;
        Object obj = this.f1928c;
        Object obj2 = this.f1927b;
        switch (i2) {
            case 0:
                Typeface typeface = (Typeface) obj;
                t0 t0Var = (t0) ((h.a) obj2).f892c;
                if (t0Var != null) {
                    t0Var.c(typeface);
                }
                break;
            case 1:
                ((e) ((w.a) obj2)).a(obj);
                break;
            default:
                try {
                    objCall = ((Callable) obj2).call();
                } catch (Exception unused) {
                    objCall = null;
                }
                ((Handler) this.f1929d).post(new a(this, (w.a) obj, objCall, 1));
                break;
        }
    }
}
