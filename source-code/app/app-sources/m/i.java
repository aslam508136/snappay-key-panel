package m;

import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class i implements Collection {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ i.d f1628a;

    public i(i.d dVar) {
        this.f1628a = dVar;
    }

    @Override // java.util.Collection
    public final boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Collection
    public final void clear() {
        this.f1628a.c();
    }

    @Override // java.util.Collection
    public final boolean contains(Object obj) {
        return this.f1628a.h(obj) >= 0;
    }

    @Override // java.util.Collection
    public final boolean containsAll(Collection collection) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection
    public final boolean isEmpty() {
        return this.f1628a.f() == 0;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new f(this.f1628a, 1);
    }

    @Override // java.util.Collection
    public final boolean remove(Object obj) {
        i.d dVar = this.f1628a;
        int iH = dVar.h(obj);
        if (iH < 0) {
            return false;
        }
        dVar.j(iH);
        return true;
    }

    @Override // java.util.Collection
    public final boolean removeAll(Collection collection) {
        i.d dVar = this.f1628a;
        int iF = dVar.f();
        int i2 = 0;
        boolean z2 = false;
        while (i2 < iF) {
            if (collection.contains(dVar.d(i2, 1))) {
                dVar.j(i2);
                i2--;
                iF--;
                z2 = true;
            }
            i2++;
        }
        return z2;
    }

    @Override // java.util.Collection
    public final boolean retainAll(Collection collection) {
        i.d dVar = this.f1628a;
        int iF = dVar.f();
        int i2 = 0;
        boolean z2 = false;
        while (i2 < iF) {
            if (!collection.contains(dVar.d(i2, 1))) {
                dVar.j(i2);
                i2--;
                iF--;
                z2 = true;
            }
            i2++;
        }
        return z2;
    }

    @Override // java.util.Collection
    public final int size() {
        return this.f1628a.f();
    }

    @Override // java.util.Collection
    public final Object[] toArray() {
        i.d dVar = this.f1628a;
        int iF = dVar.f();
        Object[] objArr = new Object[iF];
        for (int i2 = 0; i2 < iF; i2++) {
            objArr[i2] = dVar.d(i2, 1);
        }
        return objArr;
    }

    @Override // java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        return this.f1628a.o(objArr, 1);
    }
}
