package b0;

import android.database.Cursor;
import android.util.Log;
import android.widget.Filter;
import androidx.appcompat.widget.SearchView;
import j.q2;

/* JADX INFO: loaded from: classes.dex */
public final class d extends Filter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f480a;

    public d(c cVar) {
        this.f480a = cVar;
    }

    @Override // android.widget.Filter
    public final CharSequence convertResultToString(Object obj) {
        return ((q2) this.f480a).c((Cursor) obj);
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0034  */
    @Override // android.widget.Filter
    public final Filter.FilterResults performFiltering(CharSequence charSequence) {
        String string;
        Cursor cursorG;
        q2 q2Var = (q2) this.f480a;
        if (charSequence == null) {
            string = "";
        } else {
            q2Var.getClass();
            string = charSequence.toString();
        }
        SearchView searchView = q2Var.f1367l;
        if (searchView.getVisibility() == 0 && searchView.getWindowVisibility() == 0) {
            try {
                cursorG = q2Var.g(q2Var.f1368m, string);
                if (cursorG != null) {
                    cursorG.getCount();
                } else {
                    cursorG = null;
                }
            } catch (RuntimeException e2) {
                Log.w("SuggestionsAdapter", "Search suggestions query threw an exception.", e2);
            }
        } else {
            cursorG = null;
        }
        Filter.FilterResults filterResults = new Filter.FilterResults();
        if (cursorG != null) {
            filterResults.count = cursorG.getCount();
            filterResults.values = cursorG;
        } else {
            filterResults.count = 0;
            filterResults.values = null;
        }
        return filterResults;
    }

    @Override // android.widget.Filter
    public final void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
        c cVar = this.f480a;
        Cursor cursor = ((b) cVar).f475d;
        Object obj = filterResults.values;
        if (obj == null || obj == cursor) {
            return;
        }
        ((q2) cVar).b((Cursor) obj);
    }
}
