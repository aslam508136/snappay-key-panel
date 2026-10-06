package m;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class g implements Set {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1622a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ i.d f1623b;

    public /* synthetic */ g(i.d dVar, int i2) {
        this.f1622a = i2;
        this.f1623b = dVar;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean add(Object obj) {
        switch (this.f1622a) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean addAll(Collection collection) {
        switch (this.f1622a) {
            case 0:
                i.d dVar = this.f1623b;
                int iF = dVar.f();
                Iterator it = collection.iterator();
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    dVar.i(entry.getKey(), entry.getValue());
                }
                return iF != dVar.f();
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final void clear() {
        int i2 = this.f1622a;
        i.d dVar = this.f1623b;
        switch (i2) {
            case 0:
                dVar.c();
                break;
            default:
                dVar.c();
                break;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean contains(Object obj) {
        int i2 = this.f1622a;
        boolean z2 = true;
        i.d dVar = this.f1623b;
        switch (i2) {
            case 0:
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                int iG = dVar.g(entry.getKey());
                if (iG < 0) {
                    return false;
                }
                Object objD = dVar.d(iG, 1);
                Object value = entry.getValue();
                if (objD != value && (objD == null || !objD.equals(value))) {
                    z2 = false;
                }
                return z2;
            default:
                return dVar.g(obj) >= 0;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean containsAll(Collection collection) {
        switch (this.f1622a) {
            case 0:
                Iterator it = collection.iterator();
                while (it.hasNext()) {
                    if (!contains(it.next())) {
                        return false;
                    }
                }
                return true;
            default:
                b bVarE = this.f1623b.e();
                Iterator it2 = collection.iterator();
                while (it2.hasNext()) {
                    if (!bVarE.containsKey(it2.next())) {
                        return false;
                    }
                }
                return true;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean equals(Object obj) {
        switch (this.f1622a) {
            case 0:
                break;
            default:
                break;
        }
        return i.d.l(this, obj);
    }

    @Override // java.util.Set, java.util.Collection
    public final int hashCode() {
        int i2 = this.f1622a;
        i.d dVar = this.f1623b;
        switch (i2) {
            case 0:
                int iHashCode = 0;
                for (int iF = dVar.f() - 1; iF >= 0; iF--) {
                    Object objD = dVar.d(iF, 0);
                    Object objD2 = dVar.d(iF, 1);
                    iHashCode += (objD == null ? 0 : objD.hashCode()) ^ (objD2 == null ? 0 : objD2.hashCode());
                }
                return iHashCode;
            default:
                int iHashCode2 = 0;
                for (int iF2 = dVar.f() - 1; iF2 >= 0; iF2--) {
                    Object objD3 = dVar.d(iF2, 0);
                    iHashCode2 += objD3 == null ? 0 : objD3.hashCode();
                }
                return iHashCode2;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean isEmpty() {
        int i2 = this.f1622a;
        i.d dVar = this.f1623b;
        switch (i2) {
            case 0:
                return dVar.f() == 0;
            default:
                return dVar.f() == 0;
        }
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        int i2 = this.f1622a;
        i.d dVar = this.f1623b;
        switch (i2) {
            case 0:
                return new h(dVar);
            default:
                return new f(dVar, 0);
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean remove(Object obj) {
        switch (this.f1622a) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                i.d dVar = this.f1623b;
                int iG = dVar.g(obj);
                if (iG < 0) {
                    return false;
                }
                dVar.j(iG);
                return true;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean removeAll(Collection collection) {
        switch (this.f1622a) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                b bVarE = this.f1623b.e();
                int i2 = bVarE.f1635c;
                Iterator it = collection.iterator();
                while (it.hasNext()) {
                    bVarE.remove(it.next());
                }
                return i2 != bVarE.f1635c;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean retainAll(Collection collection) {
        switch (this.f1622a) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                return i.d.n(this.f1623b.e(), collection);
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final int size() {
        int i2 = this.f1622a;
        i.d dVar = this.f1623b;
        switch (i2) {
            case 0:
                break;
            default:
                break;
        }
        return dVar.f();
    }

    @Override // java.util.Set, java.util.Collection
    public final Object[] toArray() {
        switch (this.f1622a) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                i.d dVar = this.f1623b;
                int iF = dVar.f();
                Object[] objArr = new Object[iF];
                for (int i2 = 0; i2 < iF; i2++) {
                    objArr[i2] = dVar.d(i2, 0);
                }
                return objArr;
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        switch (this.f1622a) {
            case 0:
                throw new UnsupportedOperationException();
            default:
                return this.f1623b.o(objArr, 0);
        }
    }
}
