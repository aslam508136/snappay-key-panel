package j0;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class f extends AbstractMap implements Serializable {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final u.c f1520h = new u.c(1);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Comparator f1521a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public e f1522b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1523c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1524d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final e f1525e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c f1526f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c f1527g;

    public f() {
        u.c cVar = f1520h;
        this.f1523c = 0;
        this.f1524d = 0;
        this.f1525e = new e();
        this.f1521a = cVar;
    }

    public final e a(Object obj, boolean z2) {
        int iCompareTo;
        e eVar;
        e eVar2 = this.f1522b;
        u.c cVar = f1520h;
        Comparator comparator = this.f1521a;
        if (eVar2 != null) {
            Comparable comparable = comparator == cVar ? (Comparable) obj : null;
            while (true) {
                Object obj2 = eVar2.f1517f;
                iCompareTo = comparable != null ? comparable.compareTo(obj2) : comparator.compare(obj, obj2);
                if (iCompareTo == 0) {
                    return eVar2;
                }
                e eVar3 = iCompareTo < 0 ? eVar2.f1513b : eVar2.f1514c;
                if (eVar3 == null) {
                    break;
                }
                eVar2 = eVar3;
            }
        } else {
            iCompareTo = 0;
        }
        if (!z2) {
            return null;
        }
        e eVar4 = this.f1525e;
        if (eVar2 != null) {
            eVar = new e(eVar2, obj, eVar4, eVar4.f1516e);
            if (iCompareTo < 0) {
                eVar2.f1513b = eVar;
            } else {
                eVar2.f1514c = eVar;
            }
            c(eVar2, true);
        } else {
            if (comparator == cVar && !(obj instanceof Comparable)) {
                throw new ClassCastException(obj.getClass().getName().concat(" is not Comparable"));
            }
            eVar = new e(eVar2, obj, eVar4, eVar4.f1516e);
            this.f1522b = eVar;
        }
        this.f1523c++;
        this.f1524d++;
        return eVar;
    }

    public final e b(Map.Entry entry) {
        e eVarA;
        Object key = entry.getKey();
        boolean z2 = false;
        if (key != null) {
            try {
                eVarA = a(key, false);
            } catch (ClassCastException unused) {
                eVarA = null;
            }
        } else {
            eVarA = null;
        }
        if (eVarA != null) {
            Object obj = eVarA.f1518g;
            Object value = entry.getValue();
            if (obj == value || (obj != null && obj.equals(value))) {
                z2 = true;
            }
        }
        if (z2) {
            return eVarA;
        }
        return null;
    }

    public final void c(e eVar, boolean z2) {
        while (eVar != null) {
            e eVar2 = eVar.f1513b;
            e eVar3 = eVar.f1514c;
            int i2 = eVar2 != null ? eVar2.f1519h : 0;
            int i3 = eVar3 != null ? eVar3.f1519h : 0;
            int i4 = i2 - i3;
            if (i4 == -2) {
                e eVar4 = eVar3.f1513b;
                e eVar5 = eVar3.f1514c;
                int i5 = (eVar4 != null ? eVar4.f1519h : 0) - (eVar5 != null ? eVar5.f1519h : 0);
                if (i5 != -1 && (i5 != 0 || z2)) {
                    g(eVar3);
                }
                f(eVar);
                if (z2) {
                    return;
                }
            } else if (i4 == 2) {
                e eVar6 = eVar2.f1513b;
                e eVar7 = eVar2.f1514c;
                int i6 = (eVar6 != null ? eVar6.f1519h : 0) - (eVar7 != null ? eVar7.f1519h : 0);
                if (i6 != 1 && (i6 != 0 || z2)) {
                    f(eVar2);
                }
                g(eVar);
                if (z2) {
                    return;
                }
            } else if (i4 == 0) {
                eVar.f1519h = i2 + 1;
                if (z2) {
                    return;
                }
            } else {
                eVar.f1519h = Math.max(i2, i3) + 1;
                if (!z2) {
                    return;
                }
            }
            eVar = eVar.f1512a;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        this.f1522b = null;
        this.f1523c = 0;
        this.f1524d++;
        e eVar = this.f1525e;
        eVar.f1516e = eVar;
        eVar.f1515d = eVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        e eVarA;
        if (obj != null) {
            try {
                eVarA = a(obj, false);
            } catch (ClassCastException unused) {
                eVarA = null;
            }
        } else {
            eVarA = null;
        }
        return eVarA != null;
    }

    public final void d(e eVar, boolean z2) {
        e eVar2;
        e eVar3;
        int i2;
        if (z2) {
            e eVar4 = eVar.f1516e;
            eVar4.f1515d = eVar.f1515d;
            eVar.f1515d.f1516e = eVar4;
        }
        e eVar5 = eVar.f1513b;
        e eVar6 = eVar.f1514c;
        e eVar7 = eVar.f1512a;
        int i3 = 0;
        if (eVar5 == null || eVar6 == null) {
            if (eVar5 != null) {
                e(eVar, eVar5);
                eVar.f1513b = null;
            } else if (eVar6 != null) {
                e(eVar, eVar6);
                eVar.f1514c = null;
            } else {
                e(eVar, null);
            }
            c(eVar7, false);
            this.f1523c--;
            this.f1524d++;
            return;
        }
        if (eVar5.f1519h > eVar6.f1519h) {
            do {
                eVar3 = eVar5;
                eVar5 = eVar5.f1514c;
            } while (eVar5 != null);
        } else {
            do {
                eVar2 = eVar6;
                eVar6 = eVar6.f1513b;
            } while (eVar6 != null);
            eVar3 = eVar2;
        }
        d(eVar3, false);
        e eVar8 = eVar.f1513b;
        if (eVar8 != null) {
            i2 = eVar8.f1519h;
            eVar3.f1513b = eVar8;
            eVar8.f1512a = eVar3;
            eVar.f1513b = null;
        } else {
            i2 = 0;
        }
        e eVar9 = eVar.f1514c;
        if (eVar9 != null) {
            i3 = eVar9.f1519h;
            eVar3.f1514c = eVar9;
            eVar9.f1512a = eVar3;
            eVar.f1514c = null;
        }
        eVar3.f1519h = Math.max(i2, i3) + 1;
        e(eVar, eVar3);
    }

    public final void e(e eVar, e eVar2) {
        e eVar3 = eVar.f1512a;
        eVar.f1512a = null;
        if (eVar2 != null) {
            eVar2.f1512a = eVar3;
        }
        if (eVar3 == null) {
            this.f1522b = eVar2;
        } else if (eVar3.f1513b == eVar) {
            eVar3.f1513b = eVar2;
        } else {
            eVar3.f1514c = eVar2;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        c cVar = this.f1526f;
        if (cVar != null) {
            return cVar;
        }
        c cVar2 = new c(this, 0);
        this.f1526f = cVar2;
        return cVar2;
    }

    public final void f(e eVar) {
        e eVar2 = eVar.f1513b;
        e eVar3 = eVar.f1514c;
        e eVar4 = eVar3.f1513b;
        e eVar5 = eVar3.f1514c;
        eVar.f1514c = eVar4;
        if (eVar4 != null) {
            eVar4.f1512a = eVar;
        }
        e(eVar, eVar3);
        eVar3.f1513b = eVar;
        eVar.f1512a = eVar3;
        int iMax = Math.max(eVar2 != null ? eVar2.f1519h : 0, eVar4 != null ? eVar4.f1519h : 0) + 1;
        eVar.f1519h = iMax;
        eVar3.f1519h = Math.max(iMax, eVar5 != null ? eVar5.f1519h : 0) + 1;
    }

    public final void g(e eVar) {
        e eVar2 = eVar.f1513b;
        e eVar3 = eVar.f1514c;
        e eVar4 = eVar2.f1513b;
        e eVar5 = eVar2.f1514c;
        eVar.f1513b = eVar5;
        if (eVar5 != null) {
            eVar5.f1512a = eVar;
        }
        e(eVar, eVar2);
        eVar2.f1514c = eVar;
        eVar.f1512a = eVar2;
        int iMax = Math.max(eVar3 != null ? eVar3.f1519h : 0, eVar5 != null ? eVar5.f1519h : 0) + 1;
        eVar.f1519h = iMax;
        eVar2.f1519h = Math.max(iMax, eVar4 != null ? eVar4.f1519h : 0) + 1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        e eVarA;
        if (obj != null) {
            try {
                eVarA = a(obj, false);
            } catch (ClassCastException unused) {
                eVarA = null;
            }
        } else {
            eVarA = null;
        }
        if (eVarA != null) {
            return eVarA.f1518g;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        c cVar = this.f1527g;
        if (cVar != null) {
            return cVar;
        }
        c cVar2 = new c(this, 1);
        this.f1527g = cVar2;
        return cVar2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object put(Object obj, Object obj2) {
        if (obj == null) {
            throw new NullPointerException("key == null");
        }
        e eVarA = a(obj, true);
        Object obj3 = eVarA.f1518g;
        eVarA.f1518g = obj2;
        return obj3;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        e eVarA;
        if (obj != null) {
            try {
                eVarA = a(obj, false);
            } catch (ClassCastException unused) {
                eVarA = null;
            }
        } else {
            eVarA = null;
        }
        if (eVarA != null) {
            d(eVarA, true);
        }
        if (eVarA != null) {
            return eVarA.f1518g;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.f1523c;
    }
}
