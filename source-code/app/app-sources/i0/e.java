package i0;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class e extends b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final j0.f f1139a = new j0.f();

    public final boolean equals(Object obj) {
        return obj == this || ((obj instanceof e) && ((e) obj).f1139a.equals(this.f1139a));
    }

    public final int hashCode() {
        return this.f1139a.hashCode();
    }

    public final void j(String str, b bVar) {
        if (bVar == null) {
            bVar = d.f1138a;
        }
        this.f1139a.put(str, bVar);
    }

    public final void k(String str, Long l2) {
        j(str, l2 == null ? d.f1138a : new g(l2));
    }

    public final void l(String str, String str2) {
        j(str, str2 == null ? d.f1138a : new g(str2));
    }

    @Override // i0.b
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public final e a() {
        e eVar = new e();
        for (Map.Entry entry : (j0.c) this.f1139a.entrySet()) {
            eVar.j((String) entry.getKey(), ((b) entry.getValue()).a());
        }
        return eVar;
    }

    public final b n(String str) {
        return (b) this.f1139a.get(str);
    }

    public final boolean o(String str) {
        return this.f1139a.containsKey(str);
    }
}
