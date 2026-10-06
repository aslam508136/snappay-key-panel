package k0;

import i0.g;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class b extends l0.a {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final a f1532t = new a();

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final Object f1533u = new Object();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Object[] f1534p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f1535q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public String[] f1536r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int[] f1537s;

    public b(i0.b bVar) {
        super(f1532t);
        this.f1534p = new Object[32];
        this.f1535q = 0;
        this.f1536r = new String[32];
        this.f1537s = new int[32];
        A(bVar);
    }

    private String k() {
        return " at path " + h();
    }

    public final void A(Object obj) {
        int i2 = this.f1535q;
        Object[] objArr = this.f1534p;
        if (i2 == objArr.length) {
            int i3 = i2 * 2;
            this.f1534p = Arrays.copyOf(objArr, i3);
            this.f1537s = Arrays.copyOf(this.f1537s, i3);
            this.f1536r = (String[]) Arrays.copyOf(this.f1536r, i3);
        }
        Object[] objArr2 = this.f1534p;
        int i4 = this.f1535q;
        this.f1535q = i4 + 1;
        objArr2[i4] = obj;
    }

    @Override // l0.a
    public final void a() {
        x(l0.b.BEGIN_ARRAY);
        A(((i0.a) y()).iterator());
        this.f1537s[this.f1535q - 1] = 0;
    }

    @Override // l0.a
    public final void b() {
        x(l0.b.BEGIN_OBJECT);
        A(((j0.c) ((i0.e) y()).f1139a.entrySet()).iterator());
    }

    @Override // l0.a, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f1534p = new Object[]{f1533u};
        this.f1535q = 1;
    }

    @Override // l0.a
    public final void e() {
        x(l0.b.END_ARRAY);
        z();
        z();
        int i2 = this.f1535q;
        if (i2 > 0) {
            int[] iArr = this.f1537s;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
        }
    }

    @Override // l0.a
    public final void f() {
        x(l0.b.END_OBJECT);
        z();
        z();
        int i2 = this.f1535q;
        if (i2 > 0) {
            int[] iArr = this.f1537s;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
        }
    }

    @Override // l0.a
    public final String h() {
        StringBuilder sb = new StringBuilder("$");
        int i2 = 0;
        while (true) {
            int i3 = this.f1535q;
            if (i2 >= i3) {
                return sb.toString();
            }
            Object[] objArr = this.f1534p;
            Object obj = objArr[i2];
            if (obj instanceof i0.a) {
                i2++;
                if (i2 < i3 && (objArr[i2] instanceof Iterator)) {
                    sb.append('[');
                    sb.append(this.f1537s[i2]);
                    sb.append(']');
                }
            } else if ((obj instanceof i0.e) && (i2 = i2 + 1) < i3 && (objArr[i2] instanceof Iterator)) {
                sb.append('.');
                String str = this.f1536r[i2];
                if (str != null) {
                    sb.append(str);
                }
            }
            i2++;
        }
    }

    @Override // l0.a
    public final boolean i() {
        l0.b bVarT = t();
        return (bVarT == l0.b.END_OBJECT || bVarT == l0.b.END_ARRAY) ? false : true;
    }

    @Override // l0.a
    public final boolean l() {
        x(l0.b.BOOLEAN);
        boolean zB = ((g) z()).b();
        int i2 = this.f1535q;
        if (i2 > 0) {
            int[] iArr = this.f1537s;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
        }
        return zB;
    }

    @Override // l0.a
    public final int m() {
        l0.b bVarT = t();
        l0.b bVar = l0.b.NUMBER;
        if (bVarT != bVar && bVarT != l0.b.STRING) {
            throw new IllegalStateException("Expected " + bVar + " but was " + bVarT + k());
        }
        g gVar = (g) y();
        int iIntValue = gVar.f1140a instanceof Number ? gVar.h().intValue() : Integer.parseInt(gVar.i());
        z();
        int i2 = this.f1535q;
        if (i2 > 0) {
            int[] iArr = this.f1537s;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
        }
        return iIntValue;
    }

    @Override // l0.a
    public final String n() {
        x(l0.b.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) y()).next();
        String str = (String) entry.getKey();
        this.f1536r[this.f1535q - 1] = str;
        A(entry.getValue());
        return str;
    }

    @Override // l0.a
    public final void p() {
        x(l0.b.NULL);
        z();
        int i2 = this.f1535q;
        if (i2 > 0) {
            int[] iArr = this.f1537s;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
        }
    }

    @Override // l0.a
    public final String r() {
        l0.b bVarT = t();
        l0.b bVar = l0.b.STRING;
        if (bVarT != bVar && bVarT != l0.b.NUMBER) {
            throw new IllegalStateException("Expected " + bVar + " but was " + bVarT + k());
        }
        String strI = ((g) z()).i();
        int i2 = this.f1535q;
        if (i2 > 0) {
            int[] iArr = this.f1537s;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
        }
        return strI;
    }

    @Override // l0.a
    public final l0.b t() {
        if (this.f1535q == 0) {
            return l0.b.END_DOCUMENT;
        }
        Object objY = y();
        if (objY instanceof Iterator) {
            boolean z2 = this.f1534p[this.f1535q - 2] instanceof i0.e;
            Iterator it = (Iterator) objY;
            if (!it.hasNext()) {
                return z2 ? l0.b.END_OBJECT : l0.b.END_ARRAY;
            }
            if (z2) {
                return l0.b.NAME;
            }
            A(it.next());
            return t();
        }
        if (objY instanceof i0.e) {
            return l0.b.BEGIN_OBJECT;
        }
        if (objY instanceof i0.a) {
            return l0.b.BEGIN_ARRAY;
        }
        if (!(objY instanceof g)) {
            if (objY instanceof i0.d) {
                return l0.b.NULL;
            }
            if (objY == f1533u) {
                throw new IllegalStateException("JsonReader is closed");
            }
            throw new AssertionError();
        }
        Serializable serializable = ((g) objY).f1140a;
        if (serializable instanceof String) {
            return l0.b.STRING;
        }
        if (serializable instanceof Boolean) {
            return l0.b.BOOLEAN;
        }
        if (serializable instanceof Number) {
            return l0.b.NUMBER;
        }
        throw new AssertionError();
    }

    @Override // l0.a
    public final String toString() {
        return b.class.getSimpleName() + k();
    }

    public final void x(l0.b bVar) {
        if (t() == bVar) {
            return;
        }
        throw new IllegalStateException("Expected " + bVar + " but was " + t() + k());
    }

    public final Object y() {
        return this.f1534p[this.f1535q - 1];
    }

    public final Object z() {
        Object[] objArr = this.f1534p;
        int i2 = this.f1535q - 1;
        this.f1535q = i2;
        Object obj = objArr[i2];
        objArr[i2] = null;
        return obj;
    }
}
