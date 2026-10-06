package u;

import java.util.concurrent.ThreadFactory;

/* JADX INFO: loaded from: classes.dex */
public final class j implements ThreadFactory {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1951a = "fonts-androidx";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1952b = 10;

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        return new i(runnable, this.f1951a, this.f1952b);
    }
}
