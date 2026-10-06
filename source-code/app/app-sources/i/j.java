package i;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.snapay.app.R;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class j extends BaseAdapter {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1045b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ k f1046c;

    public j(k kVar) {
        this.f1046c = kVar;
        a();
    }

    public final void a() {
        o oVar = this.f1046c.f1049d;
        q qVar = oVar.f1081v;
        if (qVar != null) {
            oVar.i();
            ArrayList arrayList = oVar.f1069j;
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (((q) arrayList.get(i2)) == qVar) {
                    this.f1045b = i2;
                    return;
                }
            }
        }
        this.f1045b = -1;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final q getItem(int i2) {
        k kVar = this.f1046c;
        o oVar = kVar.f1049d;
        oVar.i();
        ArrayList arrayList = oVar.f1069j;
        kVar.getClass();
        int i3 = i2 + 0;
        int i4 = this.f1045b;
        if (i4 >= 0 && i3 >= i4) {
            i3++;
        }
        return (q) arrayList.get(i3);
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        k kVar = this.f1046c;
        o oVar = kVar.f1049d;
        oVar.i();
        int size = oVar.f1069j.size();
        kVar.getClass();
        int i2 = size + 0;
        return this.f1045b < 0 ? i2 : i2 - 1;
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public final View getView(int i2, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.f1046c.f1048c.inflate(R.layout.abc_list_menu_item_layout, viewGroup, false);
        }
        ((c0) view).c(getItem(i2));
        return view;
    }

    @Override // android.widget.BaseAdapter
    public final void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }
}
