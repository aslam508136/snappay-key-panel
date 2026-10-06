package j;

import androidx.appcompat.widget.SearchView;

/* JADX INFO: loaded from: classes.dex */
public final class e2 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1232a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ SearchView f1233b;

    public /* synthetic */ e2(SearchView searchView, int i2) {
        this.f1232a = i2;
        this.f1233b = searchView;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i2 = this.f1232a;
        SearchView searchView = this.f1233b;
        switch (i2) {
            case 0:
                searchView.t();
                break;
            default:
                b0.b bVar = searchView.P;
                if (bVar instanceof q2) {
                    bVar.b(null);
                }
                break;
        }
    }
}
