package i0;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class a extends b implements Iterable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f1137a;

    public a() {
        this.f1137a = new ArrayList();
    }

    @Override // i0.b
    public final b a() {
        ArrayList arrayList = this.f1137a;
        if (arrayList.isEmpty()) {
            return new a();
        }
        a aVar = new a(arrayList.size());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            aVar.j(((b) it.next()).a());
        }
        return aVar;
    }

    @Override // i0.b
    public final boolean b() {
        ArrayList arrayList = this.f1137a;
        if (arrayList.size() == 1) {
            return ((b) arrayList.get(0)).b();
        }
        throw new IllegalStateException();
    }

    @Override // i0.b
    public final double c() {
        ArrayList arrayList = this.f1137a;
        if (arrayList.size() == 1) {
            return ((b) arrayList.get(0)).c();
        }
        throw new IllegalStateException();
    }

    public final boolean equals(Object obj) {
        return obj == this || ((obj instanceof a) && ((a) obj).f1137a.equals(this.f1137a));
    }

    @Override // i0.b
    public final long g() {
        ArrayList arrayList = this.f1137a;
        if (arrayList.size() == 1) {
            return ((b) arrayList.get(0)).g();
        }
        throw new IllegalStateException();
    }

    @Override // i0.b
    public final Number h() {
        ArrayList arrayList = this.f1137a;
        if (arrayList.size() == 1) {
            return ((b) arrayList.get(0)).h();
        }
        throw new IllegalStateException();
    }

    public final int hashCode() {
        return this.f1137a.hashCode();
    }

    @Override // i0.b
    public final String i() {
        ArrayList arrayList = this.f1137a;
        if (arrayList.size() == 1) {
            return ((b) arrayList.get(0)).i();
        }
        throw new IllegalStateException();
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return this.f1137a.iterator();
    }

    public final void j(b bVar) {
        if (bVar == null) {
            bVar = d.f1138a;
        }
        this.f1137a.add(bVar);
    }

    public final void k(String str) {
        this.f1137a.add(str == null ? d.f1138a : new g(str));
    }

    public final b l(int i2) {
        return (b) this.f1137a.get(i2);
    }

    public final int size() {
        return this.f1137a.size();
    }

    public a(int i2) {
        this.f1137a = new ArrayList(i2);
    }
}
