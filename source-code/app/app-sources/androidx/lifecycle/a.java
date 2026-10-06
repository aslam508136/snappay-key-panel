package androidx.lifecycle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HashMap f413a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Map f414b;

    public a(HashMap map) {
        this.f414b = map;
        for (Map.Entry entry : map.entrySet()) {
            g gVar = (g) entry.getValue();
            List arrayList = (List) this.f413a.get(gVar);
            if (arrayList == null) {
                arrayList = new ArrayList();
                this.f413a.put(gVar, arrayList);
            }
            arrayList.add(entry.getKey());
        }
    }

    public static void a(List list, l lVar, g gVar, Object obj) {
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                b bVar = (b) list.get(size);
                bVar.getClass();
                try {
                    int i2 = bVar.f415a;
                    Method method = bVar.f416b;
                    if (i2 == 0) {
                        method.invoke(obj, new Object[0]);
                    } else if (i2 == 1) {
                        method.invoke(obj, lVar);
                    } else if (i2 == 2) {
                        method.invoke(obj, lVar, gVar);
                    }
                } catch (IllegalAccessException e2) {
                    throw new RuntimeException(e2);
                } catch (InvocationTargetException e3) {
                    throw new RuntimeException("Failed to call observer method", e3.getCause());
                }
            }
        }
    }
}
