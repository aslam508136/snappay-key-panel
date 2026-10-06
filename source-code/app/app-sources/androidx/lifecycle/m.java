package androidx.lifecycle;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public h f455a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final j f456b;

    public m(k kVar, h hVar) {
        j reflectiveGenericLifecycleObserver;
        HashMap map = o.f457a;
        if (kVar instanceof j) {
            reflectiveGenericLifecycleObserver = (j) kVar;
        } else {
            Class<?> cls = kVar.getClass();
            if (o.c(cls) == 2) {
                List list = (List) o.f458b.get(cls);
                if (list.size() == 1) {
                    o.a((Constructor) list.get(0), kVar);
                    reflectiveGenericLifecycleObserver = new SingleGeneratedAdapterObserver();
                } else {
                    e[] eVarArr = new e[list.size()];
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        o.a((Constructor) list.get(i2), kVar);
                        eVarArr[i2] = null;
                    }
                    reflectiveGenericLifecycleObserver = new CompositeGeneratedAdaptersObserver(eVarArr);
                }
            } else {
                reflectiveGenericLifecycleObserver = new ReflectiveGenericLifecycleObserver(kVar);
            }
        }
        this.f456b = reflectiveGenericLifecycleObserver;
        this.f455a = hVar;
    }

    public final void a(l lVar, g gVar) {
        h hVarA = gVar.a();
        h hVar = this.f455a;
        if (hVarA.compareTo(hVar) < 0) {
            hVar = hVarA;
        }
        this.f455a = hVar;
        this.f456b.a(lVar, gVar);
        this.f455a = hVarA;
    }
}
