package androidx.lifecycle;

import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class ReflectiveGenericLifecycleObserver implements j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f410a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f411b;

    public ReflectiveGenericLifecycleObserver(Object obj) {
        this.f410a = obj;
        this.f411b = c.f417c.b(obj.getClass());
    }

    @Override // androidx.lifecycle.j
    public final void a(l lVar, g gVar) {
        HashMap map = this.f411b.f413a;
        List list = (List) map.get(gVar);
        Object obj = this.f410a;
        a.a(list, lVar, gVar, obj);
        a.a((List) map.get(g.ON_ANY), lVar, gVar, obj);
    }
}
