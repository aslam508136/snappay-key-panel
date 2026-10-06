package d;

import android.view.View;
import android.widget.AbsListView;

/* JADX INFO: loaded from: classes.dex */
public final class d implements AbsListView.OnScrollListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ View f614a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ View f615b;

    public d(View view, View view2) {
        this.f614a = view;
        this.f615b = view2;
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public final void onScroll(AbsListView absListView, int i2, int i3, int i4) {
        i.a(absListView, this.f614a, this.f615b);
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public final void onScrollStateChanged(AbsListView absListView, int i2) {
    }
}
