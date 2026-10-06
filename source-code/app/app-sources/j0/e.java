package j0;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class e implements Map.Entry {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public e f1512a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public e f1513b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public e f1514c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public e f1515d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public e f1516e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f1517f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Object f1518g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1519h;

    public e() {
        this.f1517f = null;
        this.f1516e = this;
        this.f1515d = this;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object obj2 = this.f1517f;
        if (obj2 == null) {
            if (entry.getKey() != null) {
                return false;
            }
        } else if (!obj2.equals(entry.getKey())) {
            return false;
        }
        Object obj3 = this.f1518g;
        Object value = entry.getValue();
        if (obj3 == null) {
            if (value != null) {
                return false;
            }
        } else if (!obj3.equals(value)) {
            return false;
        }
        return true;
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.f1517f;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.f1518g;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Object obj = this.f1517f;
        int iHashCode = obj == null ? 0 : obj.hashCode();
        Object obj2 = this.f1518g;
        return (obj2 != null ? obj2.hashCode() : 0) ^ iHashCode;
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        Object obj2 = this.f1518g;
        this.f1518g = obj;
        return obj2;
    }

    public final String toString() {
        return this.f1517f + "=" + this.f1518g;
    }

    public e(e eVar, Object obj, e eVar2, e eVar3) {
        this.f1512a = eVar;
        this.f1517f = obj;
        this.f1519h = 1;
        this.f1515d = eVar2;
        this.f1516e = eVar3;
        eVar3.f1515d = this;
        eVar2.f1516e = this;
    }
}
