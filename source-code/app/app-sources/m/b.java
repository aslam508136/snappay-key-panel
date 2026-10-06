package m;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class b extends j implements Map {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public a f1596h;

    @Override // java.util.Map
    public final Set entrySet() {
        int i2 = 0;
        if (this.f1596h == null) {
            this.f1596h = new a(this, i2);
        }
        a aVar = this.f1596h;
        if (((g) aVar.f985a) == null) {
            aVar.f985a = new g(aVar, i2);
        }
        return (g) aVar.f985a;
    }

    @Override // java.util.Map
    public final Set keySet() {
        if (this.f1596h == null) {
            this.f1596h = new a(this, 0);
        }
        a aVar = this.f1596h;
        if (((g) aVar.f986b) == null) {
            aVar.f986b = new g(aVar, 1);
        }
        return (g) aVar.f986b;
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        b(map.size() + this.f1635c);
        for (Map.Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public final Collection values() {
        if (this.f1596h == null) {
            this.f1596h = new a(this, 0);
        }
        a aVar = this.f1596h;
        if (aVar.f987c == null) {
            aVar.f987c = new i(aVar);
        }
        return aVar.f987c;
    }
}
