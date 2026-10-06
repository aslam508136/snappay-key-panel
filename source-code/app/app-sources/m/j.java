package m;

import java.util.ConcurrentModificationException;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Object[] f1629d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static int f1630e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static Object[] f1631f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static int f1632g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int[] f1633a = androidx.lifecycle.i.f452x;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object[] f1634b = androidx.lifecycle.i.f453y;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1635c = 0;

    private void a(int i2) {
        if (i2 == 8) {
            synchronized (j.class) {
                Object[] objArr = f1631f;
                if (objArr != null) {
                    this.f1634b = objArr;
                    f1631f = (Object[]) objArr[0];
                    this.f1633a = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    f1632g--;
                    return;
                }
            }
        } else if (i2 == 4) {
            synchronized (j.class) {
                Object[] objArr2 = f1629d;
                if (objArr2 != null) {
                    this.f1634b = objArr2;
                    f1629d = (Object[]) objArr2[0];
                    this.f1633a = (int[]) objArr2[1];
                    objArr2[1] = null;
                    objArr2[0] = null;
                    f1630e--;
                    return;
                }
            }
        }
        this.f1633a = new int[i2];
        this.f1634b = new Object[i2 << 1];
    }

    public static void c(int[] iArr, Object[] objArr, int i2) {
        if (iArr.length == 8) {
            synchronized (j.class) {
                if (f1632g < 10) {
                    objArr[0] = f1631f;
                    objArr[1] = iArr;
                    for (int i3 = (i2 << 1) - 1; i3 >= 2; i3--) {
                        objArr[i3] = null;
                    }
                    f1631f = objArr;
                    f1632g++;
                }
            }
            return;
        }
        if (iArr.length == 4) {
            synchronized (j.class) {
                if (f1630e < 10) {
                    objArr[0] = f1629d;
                    objArr[1] = iArr;
                    for (int i4 = (i2 << 1) - 1; i4 >= 2; i4--) {
                        objArr[i4] = null;
                    }
                    f1629d = objArr;
                    f1630e++;
                }
            }
        }
    }

    public final void b(int i2) {
        int i3 = this.f1635c;
        int[] iArr = this.f1633a;
        if (iArr.length < i2) {
            Object[] objArr = this.f1634b;
            a(i2);
            if (this.f1635c > 0) {
                System.arraycopy(iArr, 0, this.f1633a, 0, i3);
                System.arraycopy(objArr, 0, this.f1634b, 0, i3 << 1);
            }
            c(iArr, objArr, i3);
        }
        if (this.f1635c != i3) {
            throw new ConcurrentModificationException();
        }
    }

    public final void clear() {
        int i2 = this.f1635c;
        if (i2 > 0) {
            int[] iArr = this.f1633a;
            Object[] objArr = this.f1634b;
            this.f1633a = androidx.lifecycle.i.f452x;
            this.f1634b = androidx.lifecycle.i.f453y;
            this.f1635c = 0;
            c(iArr, objArr, i2);
        }
        if (this.f1635c > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public final boolean containsKey(Object obj) {
        return e(obj) >= 0;
    }

    public final boolean containsValue(Object obj) {
        return g(obj) >= 0;
    }

    public final int d(int i2, Object obj) {
        int i3 = this.f1635c;
        if (i3 == 0) {
            return -1;
        }
        try {
            int iD = androidx.lifecycle.i.d(i3, i2, this.f1633a);
            if (iD < 0 || obj.equals(this.f1634b[iD << 1])) {
                return iD;
            }
            int i4 = iD + 1;
            while (i4 < i3 && this.f1633a[i4] == i2) {
                if (obj.equals(this.f1634b[i4 << 1])) {
                    return i4;
                }
                i4++;
            }
            for (int i5 = iD - 1; i5 >= 0 && this.f1633a[i5] == i2; i5--) {
                if (obj.equals(this.f1634b[i5 << 1])) {
                    return i5;
                }
            }
            return ~i4;
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    public final int e(Object obj) {
        return obj == null ? f() : d(obj.hashCode(), obj);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof j) {
            j jVar = (j) obj;
            if (this.f1635c != jVar.f1635c) {
                return false;
            }
            for (int i2 = 0; i2 < this.f1635c; i2++) {
                try {
                    Object objH = h(i2);
                    Object objJ = j(i2);
                    Object orDefault = jVar.getOrDefault(objH, null);
                    if (objJ == null) {
                        if (orDefault != null || !jVar.containsKey(objH)) {
                            return false;
                        }
                    } else if (!objJ.equals(orDefault)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                    return false;
                }
            }
            return true;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (this.f1635c != map.size()) {
                return false;
            }
            for (int i3 = 0; i3 < this.f1635c; i3++) {
                try {
                    Object objH2 = h(i3);
                    Object objJ2 = j(i3);
                    Object obj2 = map.get(objH2);
                    if (objJ2 == null) {
                        if (obj2 != null || !map.containsKey(objH2)) {
                            return false;
                        }
                    } else if (!objJ2.equals(obj2)) {
                        return false;
                    }
                } catch (ClassCastException | NullPointerException unused2) {
                }
            }
            return true;
        }
        return false;
    }

    public final int f() {
        int i2 = this.f1635c;
        if (i2 == 0) {
            return -1;
        }
        try {
            int iD = androidx.lifecycle.i.d(i2, 0, this.f1633a);
            if (iD < 0 || this.f1634b[iD << 1] == null) {
                return iD;
            }
            int i3 = iD + 1;
            while (i3 < i2 && this.f1633a[i3] == 0) {
                if (this.f1634b[i3 << 1] == null) {
                    return i3;
                }
                i3++;
            }
            for (int i4 = iD - 1; i4 >= 0 && this.f1633a[i4] == 0; i4--) {
                if (this.f1634b[i4 << 1] == null) {
                    return i4;
                }
            }
            return ~i3;
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    public final int g(Object obj) {
        int i2 = this.f1635c * 2;
        Object[] objArr = this.f1634b;
        if (obj == null) {
            for (int i3 = 1; i3 < i2; i3 += 2) {
                if (objArr[i3] == null) {
                    return i3 >> 1;
                }
            }
            return -1;
        }
        for (int i4 = 1; i4 < i2; i4 += 2) {
            if (obj.equals(objArr[i4])) {
                return i4 >> 1;
            }
        }
        return -1;
    }

    public final Object get(Object obj) {
        return getOrDefault(obj, null);
    }

    public final Object getOrDefault(Object obj, Object obj2) {
        int iE = e(obj);
        return iE >= 0 ? this.f1634b[(iE << 1) + 1] : obj2;
    }

    public final Object h(int i2) {
        return this.f1634b[i2 << 1];
    }

    public final int hashCode() {
        int[] iArr = this.f1633a;
        Object[] objArr = this.f1634b;
        int i2 = this.f1635c;
        int i3 = 1;
        int i4 = 0;
        int iHashCode = 0;
        while (i4 < i2) {
            Object obj = objArr[i3];
            iHashCode += (obj == null ? 0 : obj.hashCode()) ^ iArr[i4];
            i4++;
            i3 += 2;
        }
        return iHashCode;
    }

    public final Object i(int i2) {
        Object[] objArr = this.f1634b;
        int i3 = i2 << 1;
        Object obj = objArr[i3 + 1];
        int i4 = this.f1635c;
        int i5 = 0;
        if (i4 <= 1) {
            c(this.f1633a, objArr, i4);
            this.f1633a = androidx.lifecycle.i.f452x;
            this.f1634b = androidx.lifecycle.i.f453y;
        } else {
            int i6 = i4 - 1;
            int[] iArr = this.f1633a;
            if (iArr.length <= 8 || i4 >= iArr.length / 3) {
                if (i2 < i6) {
                    int i7 = i2 + 1;
                    int i8 = i6 - i2;
                    System.arraycopy(iArr, i7, iArr, i2, i8);
                    Object[] objArr2 = this.f1634b;
                    System.arraycopy(objArr2, i7 << 1, objArr2, i3, i8 << 1);
                }
                Object[] objArr3 = this.f1634b;
                int i9 = i6 << 1;
                objArr3[i9] = null;
                objArr3[i9 + 1] = null;
            } else {
                a(i4 > 8 ? i4 + (i4 >> 1) : 8);
                if (i4 != this.f1635c) {
                    throw new ConcurrentModificationException();
                }
                if (i2 > 0) {
                    System.arraycopy(iArr, 0, this.f1633a, 0, i2);
                    System.arraycopy(objArr, 0, this.f1634b, 0, i3);
                }
                if (i2 < i6) {
                    int i10 = i2 + 1;
                    int i11 = i6 - i2;
                    System.arraycopy(iArr, i10, this.f1633a, i2, i11);
                    System.arraycopy(objArr, i10 << 1, this.f1634b, i3, i11 << 1);
                }
            }
            i5 = i6;
        }
        if (i4 != this.f1635c) {
            throw new ConcurrentModificationException();
        }
        this.f1635c = i5;
        return obj;
    }

    public final boolean isEmpty() {
        return this.f1635c <= 0;
    }

    public final Object j(int i2) {
        return this.f1634b[(i2 << 1) + 1];
    }

    public final Object put(Object obj, Object obj2) {
        int i2;
        int iD;
        int i3 = this.f1635c;
        if (obj == null) {
            iD = f();
            i2 = 0;
        } else {
            int iHashCode = obj.hashCode();
            i2 = iHashCode;
            iD = d(iHashCode, obj);
        }
        if (iD >= 0) {
            int i4 = (iD << 1) + 1;
            Object[] objArr = this.f1634b;
            Object obj3 = objArr[i4];
            objArr[i4] = obj2;
            return obj3;
        }
        int i5 = ~iD;
        int[] iArr = this.f1633a;
        if (i3 >= iArr.length) {
            int i6 = 8;
            if (i3 >= 8) {
                i6 = (i3 >> 1) + i3;
            } else if (i3 < 4) {
                i6 = 4;
            }
            Object[] objArr2 = this.f1634b;
            a(i6);
            if (i3 != this.f1635c) {
                throw new ConcurrentModificationException();
            }
            int[] iArr2 = this.f1633a;
            if (iArr2.length > 0) {
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                System.arraycopy(objArr2, 0, this.f1634b, 0, objArr2.length);
            }
            c(iArr, objArr2, i3);
        }
        if (i5 < i3) {
            int[] iArr3 = this.f1633a;
            int i7 = i5 + 1;
            System.arraycopy(iArr3, i5, iArr3, i7, i3 - i5);
            Object[] objArr3 = this.f1634b;
            System.arraycopy(objArr3, i5 << 1, objArr3, i7 << 1, (this.f1635c - i5) << 1);
        }
        int i8 = this.f1635c;
        if (i3 == i8) {
            int[] iArr4 = this.f1633a;
            if (i5 < iArr4.length) {
                iArr4[i5] = i2;
                Object[] objArr4 = this.f1634b;
                int i9 = i5 << 1;
                objArr4[i9] = obj;
                objArr4[i9 + 1] = obj2;
                this.f1635c = i8 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public final Object putIfAbsent(Object obj, Object obj2) {
        Object orDefault = getOrDefault(obj, null);
        return orDefault == null ? put(obj, obj2) : orDefault;
    }

    public final Object remove(Object obj) {
        int iE = e(obj);
        if (iE >= 0) {
            return i(iE);
        }
        return null;
    }

    public final Object replace(Object obj, Object obj2) {
        int iE = e(obj);
        if (iE < 0) {
            return null;
        }
        int i2 = (iE << 1) + 1;
        Object[] objArr = this.f1634b;
        Object obj3 = objArr[i2];
        objArr[i2] = obj2;
        return obj3;
    }

    public final int size() {
        return this.f1635c;
    }

    public final String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.f1635c * 28);
        sb.append('{');
        for (int i2 = 0; i2 < this.f1635c; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            Object objH = h(i2);
            if (objH != this) {
                sb.append(objH);
            } else {
                sb.append("(this Map)");
            }
            sb.append('=');
            Object objJ = j(i2);
            if (objJ != this) {
                sb.append(objJ);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public final boolean remove(Object obj, Object obj2) {
        int iE = e(obj);
        if (iE < 0) {
            return false;
        }
        Object objJ = j(iE);
        if (obj2 != objJ && (obj2 == null || !obj2.equals(objJ))) {
            return false;
        }
        i(iE);
        return true;
    }

    public final boolean replace(Object obj, Object obj2, Object obj3) {
        int iE = e(obj);
        if (iE < 0) {
            return false;
        }
        Object objJ = j(iE);
        if (objJ != obj2 && (obj2 == null || !obj2.equals(objJ))) {
            return false;
        }
        int i2 = (iE << 1) + 1;
        Object[] objArr = this.f1634b;
        Object obj4 = objArr[i2];
        objArr[i2] = obj3;
        return true;
    }
}
