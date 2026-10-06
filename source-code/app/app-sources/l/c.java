package l;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class c implements Map.Entry {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f1547a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f1548b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c f1549c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c f1550d;

    public c(Object obj, Object obj2) {
        this.f1547a = obj;
        this.f1548b = obj2;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return this.f1547a.equals(cVar.f1547a) && this.f1548b.equals(cVar.f1548b);
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.f1547a;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.f1548b;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        return this.f1547a.hashCode() ^ this.f1548b.hashCode();
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        throw new UnsupportedOperationException("An entry modification is not supported");
    }

    public final String toString() {
        return this.f1547a + "=" + this.f1548b;
    }
}
