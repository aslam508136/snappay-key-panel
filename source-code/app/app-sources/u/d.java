package u;

import android.content.Context;
import j.s;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class d implements Callable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1932a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ String f1933b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Context f1934c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ s f1935d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f1936e;

    public /* synthetic */ d(String str, Context context, s sVar, int i2, int i3) {
        this.f1932a = i3;
        this.f1933b = str;
        this.f1934c = context;
        this.f1935d = sVar;
        this.f1936e = i2;
    }

    public final f a() {
        int i2 = this.f1932a;
        Context context = this.f1934c;
        String str = this.f1933b;
        int i3 = this.f1936e;
        s sVar = this.f1935d;
        switch (i2) {
            case 0:
                break;
            default:
                break;
        }
        return g.a(str, context, sVar, i3);
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ Object call() {
        switch (this.f1932a) {
            case 0:
                break;
            default:
                break;
        }
        return a();
    }
}
