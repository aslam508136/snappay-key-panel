package m;

/* JADX INFO: loaded from: classes.dex */
public final class d implements Cloneable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Object f1607e = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f1608a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long[] f1609b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object[] f1610c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1611d;

    public d() {
        int i2;
        int i3 = 4;
        while (true) {
            i2 = 80;
            if (i3 >= 32) {
                break;
            }
            int i4 = (1 << i3) - 12;
            if (80 <= i4) {
                i2 = i4;
                break;
            }
            i3++;
        }
        int i5 = i2 / 8;
        this.f1609b = new long[i5];
        this.f1610c = new Object[i5];
    }

    public final void a(Long l2, long j2) {
        int i2 = this.f1611d;
        if (i2 != 0 && j2 <= this.f1609b[i2 - 1]) {
            e(l2, j2);
            return;
        }
        if (this.f1608a && i2 >= this.f1609b.length) {
            c();
        }
        int i3 = this.f1611d;
        if (i3 >= this.f1609b.length) {
            int i4 = (i3 + 1) * 8;
            for (int i5 = 4; i5 < 32; i5++) {
                int i6 = (1 << i5) - 12;
                if (i4 <= i6) {
                    i4 = i6;
                    break;
                }
            }
            int i7 = i4 / 8;
            long[] jArr = new long[i7];
            Object[] objArr = new Object[i7];
            long[] jArr2 = this.f1609b;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr2 = this.f1610c;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.f1609b = jArr;
            this.f1610c = objArr;
        }
        this.f1609b[i3] = j2;
        this.f1610c[i3] = l2;
        this.f1611d = i3 + 1;
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final d clone() {
        try {
            d dVar = (d) super.clone();
            dVar.f1609b = (long[]) this.f1609b.clone();
            dVar.f1610c = (Object[]) this.f1610c.clone();
            return dVar;
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public final void c() {
        int i2 = this.f1611d;
        long[] jArr = this.f1609b;
        Object[] objArr = this.f1610c;
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            Object obj = objArr[i4];
            if (obj != f1607e) {
                if (i4 != i3) {
                    jArr[i3] = jArr[i4];
                    objArr[i3] = obj;
                    objArr[i4] = null;
                }
                i3++;
            }
        }
        this.f1608a = false;
        this.f1611d = i3;
    }

    public final Object d(Long l2, long j2) {
        Object obj;
        int iE = androidx.lifecycle.i.e(this.f1609b, this.f1611d, j2);
        return (iE < 0 || (obj = this.f1610c[iE]) == f1607e) ? l2 : obj;
    }

    public final void e(Object obj, long j2) {
        int iE = androidx.lifecycle.i.e(this.f1609b, this.f1611d, j2);
        if (iE >= 0) {
            this.f1610c[iE] = obj;
            return;
        }
        int i2 = ~iE;
        int i3 = this.f1611d;
        if (i2 < i3) {
            Object[] objArr = this.f1610c;
            if (objArr[i2] == f1607e) {
                this.f1609b[i2] = j2;
                objArr[i2] = obj;
                return;
            }
        }
        if (this.f1608a && i3 >= this.f1609b.length) {
            c();
            i2 = ~androidx.lifecycle.i.e(this.f1609b, this.f1611d, j2);
        }
        int i4 = this.f1611d;
        if (i4 >= this.f1609b.length) {
            int i5 = (i4 + 1) * 8;
            for (int i6 = 4; i6 < 32; i6++) {
                int i7 = (1 << i6) - 12;
                if (i5 <= i7) {
                    i5 = i7;
                    break;
                }
            }
            int i8 = i5 / 8;
            long[] jArr = new long[i8];
            Object[] objArr2 = new Object[i8];
            long[] jArr2 = this.f1609b;
            System.arraycopy(jArr2, 0, jArr, 0, jArr2.length);
            Object[] objArr3 = this.f1610c;
            System.arraycopy(objArr3, 0, objArr2, 0, objArr3.length);
            this.f1609b = jArr;
            this.f1610c = objArr2;
        }
        int i9 = this.f1611d - i2;
        if (i9 != 0) {
            long[] jArr3 = this.f1609b;
            int i10 = i2 + 1;
            System.arraycopy(jArr3, i2, jArr3, i10, i9);
            Object[] objArr4 = this.f1610c;
            System.arraycopy(objArr4, i2, objArr4, i10, this.f1611d - i2);
        }
        this.f1609b[i2] = j2;
        this.f1610c[i2] = obj;
        this.f1611d++;
    }

    public final String toString() {
        if (this.f1608a) {
            c();
        }
        int i2 = this.f1611d;
        if (i2 <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(i2 * 28);
        sb.append('{');
        for (int i3 = 0; i3 < this.f1611d; i3++) {
            if (i3 > 0) {
                sb.append(", ");
            }
            if (this.f1608a) {
                c();
            }
            sb.append(this.f1609b[i3]);
            sb.append('=');
            if (this.f1608a) {
                c();
            }
            Object obj = this.f1610c[i3];
            if (obj != this) {
                sb.append(obj);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
