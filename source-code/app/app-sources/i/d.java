package i;

import android.content.Context;
import android.view.MenuItem;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object f985a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f986b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public m.i f987c;

    public d(Context context) {
        this.f985a = context;
    }

    public static boolean l(Set set, Object obj) {
        if (set == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set2 = (Set) obj;
            try {
                return set.size() == set2.size() && set.containsAll(set2);
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    public static boolean n(Map map, Collection collection) {
        int size = map.size();
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                it.remove();
            }
        }
        return size != map.size();
    }

    public abstract void c();

    public abstract Object d(int i2, int i3);

    public abstract m.b e();

    public abstract int f();

    public abstract int g(Object obj);

    public abstract int h(Object obj);

    public abstract void i(Object obj, Object obj2);

    public abstract void j(int i2);

    public abstract Object k(int i2, Object obj);

    public final MenuItem m(MenuItem menuItem) {
        if (!(menuItem instanceof t.b)) {
            return menuItem;
        }
        t.b bVar = (t.b) menuItem;
        if (((m.j) this.f986b) == null) {
            this.f986b = new m.j();
        }
        MenuItem menuItem2 = (MenuItem) ((m.j) this.f986b).getOrDefault(menuItem, null);
        if (menuItem2 != null) {
            return menuItem2;
        }
        w wVar = new w((Context) this.f985a, bVar);
        ((m.j) this.f986b).put(bVar, wVar);
        return wVar;
    }

    public final Object[] o(Object[] objArr, int i2) {
        int iF = f();
        if (objArr.length < iF) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), iF);
        }
        for (int i3 = 0; i3 < iF; i3++) {
            objArr[i3] = d(i3, i2);
        }
        if (objArr.length > iF) {
            objArr[iF] = null;
        }
        return objArr;
    }
}
