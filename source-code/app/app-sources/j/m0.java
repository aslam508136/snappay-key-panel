package j;

import android.content.res.Resources;
import android.database.DataSetObserver;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;

/* JADX INFO: loaded from: classes.dex */
public final class m0 implements ListAdapter, SpinnerAdapter {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final SpinnerAdapter f1317b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ListAdapter f1318c;

    public m0(SpinnerAdapter spinnerAdapter, Resources.Theme theme) {
        this.f1317b = spinnerAdapter;
        if (spinnerAdapter instanceof ListAdapter) {
            this.f1318c = (ListAdapter) spinnerAdapter;
        }
        if (theme == null || Build.VERSION.SDK_INT < 23 || !a0.c.x(spinnerAdapter)) {
            return;
        }
        ThemedSpinnerAdapter themedSpinnerAdapterM = a0.c.m(spinnerAdapter);
        if (themedSpinnerAdapterM.getDropDownViewTheme() != theme) {
            themedSpinnerAdapterM.setDropDownViewTheme(theme);
        }
    }

    @Override // android.widget.ListAdapter
    public final boolean areAllItemsEnabled() {
        ListAdapter listAdapter = this.f1318c;
        if (listAdapter != null) {
            return listAdapter.areAllItemsEnabled();
        }
        return true;
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        SpinnerAdapter spinnerAdapter = this.f1317b;
        if (spinnerAdapter == null) {
            return 0;
        }
        return spinnerAdapter.getCount();
    }

    @Override // android.widget.SpinnerAdapter
    public final View getDropDownView(int i2, View view, ViewGroup viewGroup) {
        SpinnerAdapter spinnerAdapter = this.f1317b;
        if (spinnerAdapter == null) {
            return null;
        }
        return spinnerAdapter.getDropDownView(i2, view, viewGroup);
    }

    @Override // android.widget.Adapter
    public final Object getItem(int i2) {
        SpinnerAdapter spinnerAdapter = this.f1317b;
        if (spinnerAdapter == null) {
            return null;
        }
        return spinnerAdapter.getItem(i2);
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i2) {
        SpinnerAdapter spinnerAdapter = this.f1317b;
        if (spinnerAdapter == null) {
            return -1L;
        }
        return spinnerAdapter.getItemId(i2);
    }

    @Override // android.widget.Adapter
    public final int getItemViewType(int i2) {
        return 0;
    }

    @Override // android.widget.Adapter
    public final View getView(int i2, View view, ViewGroup viewGroup) {
        return getDropDownView(i2, view, viewGroup);
    }

    @Override // android.widget.Adapter
    public final int getViewTypeCount() {
        return 1;
    }

    @Override // android.widget.Adapter
    public final boolean hasStableIds() {
        SpinnerAdapter spinnerAdapter = this.f1317b;
        return spinnerAdapter != null && spinnerAdapter.hasStableIds();
    }

    @Override // android.widget.Adapter
    public final boolean isEmpty() {
        return getCount() == 0;
    }

    @Override // android.widget.ListAdapter
    public final boolean isEnabled(int i2) {
        ListAdapter listAdapter = this.f1318c;
        if (listAdapter != null) {
            return listAdapter.isEnabled(i2);
        }
        return true;
    }

    @Override // android.widget.Adapter
    public final void registerDataSetObserver(DataSetObserver dataSetObserver) {
        SpinnerAdapter spinnerAdapter = this.f1317b;
        if (spinnerAdapter != null) {
            spinnerAdapter.registerDataSetObserver(dataSetObserver);
        }
    }

    @Override // android.widget.Adapter
    public final void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        SpinnerAdapter spinnerAdapter = this.f1317b;
        if (spinnerAdapter != null) {
            spinnerAdapter.unregisterDataSetObserver(dataSetObserver);
        }
    }
}
