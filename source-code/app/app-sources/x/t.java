package x;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import com.snapay.app.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class t {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final ArrayList f2008d = new ArrayList();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public WeakHashMap f2009a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public SparseArray f2010b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public WeakReference f2011c = null;

    public static void b(View view) {
        int size;
        ArrayList arrayList = (ArrayList) view.getTag(R.id.tag_unhandled_key_listeners);
        if (arrayList == null || (size = arrayList.size() - 1) < 0) {
            return;
        }
        androidx.activity.c.b(arrayList.get(size));
        throw null;
    }

    public final View a(View view) {
        View viewA;
        WeakHashMap weakHashMap = this.f2009a;
        if (weakHashMap == null || !weakHashMap.containsKey(view)) {
            return null;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            do {
                childCount--;
                if (childCount >= 0) {
                    viewA = a(viewGroup.getChildAt(childCount));
                }
            } while (viewA == null);
            return viewA;
        }
        b(view);
        return null;
    }
}
