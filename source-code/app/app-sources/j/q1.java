package j;

import android.database.DataSetObserver;

/* JADX INFO: loaded from: classes.dex */
public final class q1 extends DataSetObserver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1361a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f1362b;

    public /* synthetic */ q1(Object obj, int i2) {
        this.f1361a = i2;
        this.f1362b = obj;
    }

    @Override // android.database.DataSetObserver
    public final void onChanged() {
        int i2 = this.f1361a;
        Object obj = this.f1362b;
        switch (i2) {
            case 0:
                t1 t1Var = (t1) obj;
                if (t1Var.b()) {
                    t1Var.f();
                }
                break;
            default:
                b0.b bVar = (b0.b) obj;
                bVar.f473b = true;
                bVar.notifyDataSetChanged();
                break;
        }
    }

    @Override // android.database.DataSetObserver
    public final void onInvalidated() {
        int i2 = this.f1361a;
        Object obj = this.f1362b;
        switch (i2) {
            case 0:
                ((t1) obj).dismiss();
                break;
            default:
                b0.b bVar = (b0.b) obj;
                bVar.f473b = false;
                bVar.notifyDataSetInvalidated();
                break;
        }
    }
}
