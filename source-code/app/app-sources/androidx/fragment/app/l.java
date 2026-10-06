package androidx.fragment.app;

import android.content.Context;
import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
public abstract class l {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f334b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Handler f335c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final r f336d;

    public l(d.n nVar) {
        Handler handler = new Handler();
        this.f336d = new r();
        this.f334b = nVar;
        this.f335c = handler;
    }
}
