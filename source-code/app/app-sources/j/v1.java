package j;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import androidx.appcompat.view.menu.ListMenuItemView;

/* JADX INFO: loaded from: classes.dex */
public final class v1 extends i1 {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final int f1467o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f1468p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public u1 f1469q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public i.q f1470r;

    public v1(Context context, boolean z2) {
        super(context, z2);
        if (1 == context.getResources().getConfiguration().getLayoutDirection()) {
            this.f1467o = 21;
            this.f1468p = 22;
        } else {
            this.f1467o = 22;
            this.f1468p = 21;
        }
    }

    @Override // j.i1, android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        i.l lVar;
        int headersCount;
        int iPointToPosition;
        int i2;
        if (this.f1469q != null) {
            ListAdapter adapter = getAdapter();
            if (adapter instanceof HeaderViewListAdapter) {
                HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
                headersCount = headerViewListAdapter.getHeadersCount();
                lVar = (i.l) headerViewListAdapter.getWrappedAdapter();
            } else {
                lVar = (i.l) adapter;
                headersCount = 0;
            }
            i.q item = (motionEvent.getAction() == 10 || (iPointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY())) == -1 || (i2 = iPointToPosition - headersCount) < 0 || i2 >= lVar.getCount()) ? null : lVar.getItem(i2);
            i.q qVar = this.f1470r;
            if (qVar != item) {
                i.o oVar = lVar.f1053b;
                if (qVar != null) {
                    this.f1469q.k(oVar, qVar);
                }
                this.f1470r = item;
                if (item != null) {
                    this.f1469q.e(oVar, item);
                }
            }
        }
        return super.onHoverEvent(motionEvent);
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i2, KeyEvent keyEvent) {
        ListMenuItemView listMenuItemView = (ListMenuItemView) getSelectedView();
        if (listMenuItemView != null && i2 == this.f1467o) {
            if (listMenuItemView.isEnabled() && listMenuItemView.getItemData().hasSubMenu()) {
                performItemClick(listMenuItemView, getSelectedItemPosition(), getSelectedItemId());
            }
            return true;
        }
        if (listMenuItemView == null || i2 != this.f1468p) {
            return super.onKeyDown(i2, keyEvent);
        }
        setSelection(-1);
        ListAdapter adapter = getAdapter();
        if (adapter instanceof HeaderViewListAdapter) {
            adapter = ((HeaderViewListAdapter) adapter).getWrappedAdapter();
        }
        ((i.l) adapter).f1053b.c(false);
        return true;
    }

    public void setHoverListener(u1 u1Var) {
        this.f1469q = u1Var;
    }

    @Override // j.i1, android.widget.AbsListView
    public /* bridge */ /* synthetic */ void setSelector(Drawable drawable) {
        super.setSelector(drawable);
    }
}
