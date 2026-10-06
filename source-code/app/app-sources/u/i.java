package u;

import android.os.Process;

/* JADX INFO: loaded from: classes.dex */
public final class i extends Thread {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f1950a;

    public i(Runnable runnable, String str, int i2) {
        super(runnable, str);
        this.f1950a = i2;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        Process.setThreadPriority(this.f1950a);
        super.run();
    }
}
