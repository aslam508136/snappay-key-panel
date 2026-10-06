package d;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;

/* JADX INFO: loaded from: classes.dex */
public abstract class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object f729a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f730b;

    public x(a0 a0Var) {
        this.f730b = a0Var;
    }

    public final void a() {
        Object obj = this.f729a;
        if (((BroadcastReceiver) obj) != null) {
            try {
                ((a0) this.f730b).f582e.unregisterReceiver((BroadcastReceiver) obj);
            } catch (IllegalArgumentException unused) {
            }
            this.f729a = null;
        }
    }

    public abstract IntentFilter b();

    public abstract int c();

    public abstract void d();

    public final void e() {
        a();
        IntentFilter intentFilterB = b();
        if (intentFilterB == null || intentFilterB.countActions() == 0) {
            return;
        }
        if (((BroadcastReceiver) this.f729a) == null) {
            this.f729a = new w(this, 0);
        }
        ((a0) this.f730b).f582e.registerReceiver((BroadcastReceiver) this.f729a, intentFilterB);
    }
}
