package j;

import android.view.View;
import android.widget.AdapterView;
import androidx.appcompat.widget.SearchView;

/* JADX INFO: loaded from: classes.dex */
public final class p1 implements AdapterView.OnItemSelectedListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f1354b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f1355c;

    public /* synthetic */ p1(Object obj, int i2) {
        this.f1354b = i2;
        this.f1355c = obj;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onItemSelected(AdapterView adapterView, View view, int i2, long j2) {
        i1 i1Var;
        int i3 = this.f1354b;
        Object obj = this.f1355c;
        switch (i3) {
            case 0:
                if (i2 != -1 && (i1Var = ((t1) obj).f1419d) != null) {
                    i1Var.setListSelectionHidden(false);
                    break;
                }
                break;
            default:
                ((SearchView) obj).p(i2);
                break;
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onNothingSelected(AdapterView adapterView) {
    }
}
