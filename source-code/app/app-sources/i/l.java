package i;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.appcompat.view.menu.ListMenuItemView;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class l extends BaseAdapter {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final o f1053b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1054c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1055d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f1056e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final LayoutInflater f1057f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f1058g;

    public l(o oVar, LayoutInflater layoutInflater, boolean z2, int i2) {
        this.f1056e = z2;
        this.f1057f = layoutInflater;
        this.f1053b = oVar;
        this.f1058g = i2;
        a();
    }

    public final void a() {
        o oVar = this.f1053b;
        q qVar = oVar.f1081v;
        if (qVar != null) {
            oVar.i();
            ArrayList arrayList = oVar.f1069j;
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (((q) arrayList.get(i2)) == qVar) {
                    this.f1054c = i2;
                    return;
                }
            }
        }
        this.f1054c = -1;
    }

    @Override // android.widget.Adapter
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final q getItem(int i2) {
        ArrayList arrayListL;
        boolean z2 = this.f1056e;
        o oVar = this.f1053b;
        if (z2) {
            oVar.i();
            arrayListL = oVar.f1069j;
        } else {
            arrayListL = oVar.l();
        }
        int i3 = this.f1054c;
        if (i3 >= 0 && i2 >= i3) {
            i2++;
        }
        return (q) arrayListL.get(i2);
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        ArrayList arrayListL;
        boolean z2 = this.f1056e;
        o oVar = this.f1053b;
        if (z2) {
            oVar.i();
            arrayListL = oVar.f1069j;
        } else {
            arrayListL = oVar.l();
        }
        int i2 = this.f1054c;
        int size = arrayListL.size();
        return i2 < 0 ? size : size - 1;
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.Adapter
    public final View getView(int i2, View view, ViewGroup viewGroup) {
        boolean z2 = false;
        if (view == null) {
            view = this.f1057f.inflate(this.f1058g, viewGroup, false);
        }
        int i3 = getItem(i2).f1088b;
        int i4 = i2 - 1;
        int i5 = i4 >= 0 ? getItem(i4).f1088b : i3;
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        if (this.f1053b.m() && i3 != i5) {
            z2 = true;
        }
        listMenuItemView.setGroupDividerEnabled(z2);
        c0 c0Var = (c0) view;
        if (this.f1055d) {
            listMenuItemView.setForceShowIcon(true);
        }
        c0Var.c(getItem(i2));
        return view;
    }

    @Override // android.widget.BaseAdapter
    public final void notifyDataSetChanged() {
        a();
        super.notifyDataSetChanged();
    }
}
