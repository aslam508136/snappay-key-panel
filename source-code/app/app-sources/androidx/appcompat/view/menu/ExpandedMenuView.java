package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import i.d0;
import i.n;
import i.o;
import i.q;
import m0.a;

/* JADX INFO: loaded from: classes.dex */
public final class ExpandedMenuView extends ListView implements n, d0, AdapterView.OnItemClickListener {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f93c = {R.attr.background, R.attr.divider};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public o f94b;

    public ExpandedMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnItemClickListener(this);
        a aVar = new a(context, context.obtainStyledAttributes(attributeSet, f93c, R.attr.listViewStyle, 0));
        if (aVar.s(0)) {
            setBackgroundDrawable(aVar.k(0));
        }
        if (aVar.s(1)) {
            setDivider(aVar.k(1));
        }
        aVar.w();
    }

    @Override // i.n
    public final boolean a(q qVar) {
        return this.f94b.q(qVar, null, 0);
    }

    @Override // i.d0
    public final void c(o oVar) {
        this.f94b = oVar;
    }

    public int getWindowAnimations() {
        return 0;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChildrenDrawingCacheEnabled(false);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
        a((q) getAdapter().getItem(i2));
    }
}
