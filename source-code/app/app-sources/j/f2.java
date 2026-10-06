package j;

import android.view.View;
import androidx.appcompat.widget.SearchView;

/* JADX INFO: loaded from: classes.dex */
public final class f2 implements View.OnFocusChangeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1238a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f1239b;

    public /* synthetic */ f2(Object obj, int i2) {
        this.f1238a = i2;
        this.f1239b = obj;
    }

    @Override // android.view.View.OnFocusChangeListener
    public final void onFocusChange(View view, boolean z2) {
        int i2 = this.f1238a;
        Object obj = this.f1239b;
        switch (i2) {
            case 0:
                SearchView searchView = (SearchView) obj;
                View.OnFocusChangeListener onFocusChangeListener = searchView.L;
                if (onFocusChangeListener != null) {
                    onFocusChangeListener.onFocusChange(searchView, z2);
                }
                break;
            default:
                if (z2) {
                    ((o0.i) obj).a();
                }
                break;
        }
    }
}
