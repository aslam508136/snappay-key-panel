package b0;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import j.q1;
import j.q2;

/* JADX INFO: loaded from: classes.dex */
public abstract class b extends BaseAdapter implements Filterable, c {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public d f479h;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f474c = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Cursor f475d = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f473b = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f476e = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public a f477f = new a(this);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public q1 f478g = new q1(this, 1);

    public b(Context context) {
    }

    public abstract void a(View view, Cursor cursor);

    public void b(Cursor cursor) {
        Cursor cursor2 = this.f475d;
        if (cursor == cursor2) {
            cursor2 = null;
        } else {
            if (cursor2 != null) {
                a aVar = this.f477f;
                if (aVar != null) {
                    cursor2.unregisterContentObserver(aVar);
                }
                q1 q1Var = this.f478g;
                if (q1Var != null) {
                    cursor2.unregisterDataSetObserver(q1Var);
                }
            }
            this.f475d = cursor;
            if (cursor != null) {
                a aVar2 = this.f477f;
                if (aVar2 != null) {
                    cursor.registerContentObserver(aVar2);
                }
                q1 q1Var2 = this.f478g;
                if (q1Var2 != null) {
                    cursor.registerDataSetObserver(q1Var2);
                }
                this.f476e = cursor.getColumnIndexOrThrow("_id");
                this.f473b = true;
                notifyDataSetChanged();
            } else {
                this.f476e = -1;
                this.f473b = false;
                notifyDataSetInvalidated();
            }
        }
        if (cursor2 != null) {
            cursor2.close();
        }
    }

    public abstract String c(Cursor cursor);

    public abstract View d(ViewGroup viewGroup);

    @Override // android.widget.Adapter
    public final int getCount() {
        Cursor cursor;
        if (!this.f473b || (cursor = this.f475d) == null) {
            return 0;
        }
        return cursor.getCount();
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i2, View view, ViewGroup viewGroup) {
        if (!this.f473b) {
            return null;
        }
        this.f475d.moveToPosition(i2);
        if (view == null) {
            q2 q2Var = (q2) this;
            view = q2Var.f1366k.inflate(q2Var.f1365j, viewGroup, false);
        }
        a(view, this.f475d);
        return view;
    }

    @Override // android.widget.Filterable
    public final Filter getFilter() {
        if (this.f479h == null) {
            this.f479h = new d(this);
        }
        return this.f479h;
    }

    @Override // android.widget.Adapter
    public final Object getItem(int i2) {
        Cursor cursor;
        if (!this.f473b || (cursor = this.f475d) == null) {
            return null;
        }
        cursor.moveToPosition(i2);
        return this.f475d;
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i2) {
        Cursor cursor;
        if (this.f473b && (cursor = this.f475d) != null && cursor.moveToPosition(i2)) {
            return this.f475d.getLong(this.f476e);
        }
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        if (!this.f473b) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        if (!this.f475d.moveToPosition(i2)) {
            throw new IllegalStateException("couldn't move cursor to position " + i2);
        }
        if (view == null) {
            view = d(viewGroup);
        }
        a(view, this.f475d);
        return view;
    }
}
