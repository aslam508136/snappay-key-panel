package d;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class c implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f606a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ View f607b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ View f608c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ i f609d;

    public /* synthetic */ c(i iVar, View view, View view2, int i2) {
        this.f606a = i2;
        this.f609d = iVar;
        this.f607b = view;
        this.f608c = view2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i2 = this.f606a;
        View view = this.f608c;
        View view2 = this.f607b;
        i iVar = this.f609d;
        switch (i2) {
            case 0:
                i.a(iVar.f665n, view2, view);
                break;
            default:
                i.a(iVar.f658g, view2, view);
                break;
        }
    }
}
