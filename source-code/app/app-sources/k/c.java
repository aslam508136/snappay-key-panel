package k;

import android.os.Looper;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public final class c extends b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f1531a = new Object();

    public c() {
        Executors.newFixedThreadPool(4, new b());
    }

    public final boolean i() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }
}
