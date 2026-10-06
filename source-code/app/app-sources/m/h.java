package m;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class h implements Iterator, Map.Entry {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1624a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ i.d f1627d;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1626c = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1625b = -1;

    public h(i.d dVar) {
        this.f1627d = dVar;
        this.f1624a = dVar.f() - 1;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!this.f1626c) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        int i2 = this.f1625b;
        i.d dVar = this.f1627d;
        Object objD = dVar.d(i2, 0);
        if (!(key == objD || (key != null && key.equals(objD)))) {
            return false;
        }
        Object value = entry.getValue();
        Object objD2 = dVar.d(this.f1625b, 1);
        return value == objD2 || (value != null && value.equals(objD2));
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        if (!this.f1626c) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        return this.f1627d.d(this.f1625b, 0);
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        if (!this.f1626c) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        return this.f1627d.d(this.f1625b, 1);
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f1625b < this.f1624a;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        if (!this.f1626c) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }
        int i2 = this.f1625b;
        i.d dVar = this.f1627d;
        Object objD = dVar.d(i2, 0);
        Object objD2 = dVar.d(this.f1625b, 1);
        return (objD == null ? 0 : objD.hashCode()) ^ (objD2 != null ? objD2.hashCode() : 0);
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.f1625b++;
        this.f1626c = true;
        return this;
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.f1626c) {
            throw new IllegalStateException();
        }
        this.f1627d.j(this.f1625b);
        this.f1625b--;
        this.f1624a--;
        this.f1626c = false;
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        if (this.f1626c) {
            return this.f1627d.k(this.f1625b, obj);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    public final String toString() {
        return getKey() + "=" + getValue();
    }
}
