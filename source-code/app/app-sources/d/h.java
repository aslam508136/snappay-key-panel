package d;

import android.R;
import android.content.Context;
import android.widget.ArrayAdapter;

/* JADX INFO: loaded from: classes.dex */
public final class h extends ArrayAdapter {
    public h(Context context, int i2) {
        super(context, i2, R.id.text1, (Object[]) null);
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public final long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public final boolean hasStableIds() {
        return true;
    }
}
