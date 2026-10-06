package k0;

import i0.g;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class d extends l0.c {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final c f1538l = new c();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final g f1539m = new g("closed");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ArrayList f1540i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public String f1541j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public i0.b f1542k;

    public d() {
        super(f1538l);
        this.f1540i = new ArrayList();
        this.f1542k = i0.d.f1138a;
    }

    @Override // l0.c
    public final void b() {
        i0.a aVar = new i0.a();
        s(aVar);
        this.f1540i.add(aVar);
    }

    @Override // l0.c
    public final void c() {
        i0.e eVar = new i0.e();
        s(eVar);
        this.f1540i.add(eVar);
    }

    @Override // l0.c, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        ArrayList arrayList = this.f1540i;
        if (!arrayList.isEmpty()) {
            throw new IOException("Incomplete document");
        }
        arrayList.add(f1539m);
    }

    @Override // l0.c
    public final void e() {
        ArrayList arrayList = this.f1540i;
        if (arrayList.isEmpty() || this.f1541j != null) {
            throw new IllegalStateException();
        }
        if (!(r() instanceof i0.a)) {
            throw new IllegalStateException();
        }
        arrayList.remove(arrayList.size() - 1);
    }

    @Override // l0.c
    public final void f() {
        ArrayList arrayList = this.f1540i;
        if (arrayList.isEmpty() || this.f1541j != null) {
            throw new IllegalStateException();
        }
        if (!(r() instanceof i0.e)) {
            throw new IllegalStateException();
        }
        arrayList.remove(arrayList.size() - 1);
    }

    @Override // l0.c, java.io.Flushable
    public final void flush() {
    }

    @Override // l0.c
    public final void g(String str) {
        if (str == null) {
            throw new NullPointerException("name == null");
        }
        if (this.f1540i.isEmpty() || this.f1541j != null) {
            throw new IllegalStateException();
        }
        if (!(r() instanceof i0.e)) {
            throw new IllegalStateException();
        }
        this.f1541j = str;
    }

    @Override // l0.c
    public final l0.c h() {
        s(i0.d.f1138a);
        return this;
    }

    @Override // l0.c
    public final void l(long j2) {
        s(new g(Long.valueOf(j2)));
    }

    @Override // l0.c
    public final void m(Boolean bool) {
        if (bool == null) {
            s(i0.d.f1138a);
        } else {
            s(new g(bool));
        }
    }

    @Override // l0.c
    public final void n(Number number) {
        if (number == null) {
            s(i0.d.f1138a);
            return;
        }
        if (!this.f1591e) {
            double dDoubleValue = number.doubleValue();
            if (Double.isNaN(dDoubleValue) || Double.isInfinite(dDoubleValue)) {
                throw new IllegalArgumentException("JSON forbids NaN and infinities: " + number);
            }
        }
        s(new g(number));
    }

    @Override // l0.c
    public final void o(String str) {
        if (str == null) {
            s(i0.d.f1138a);
        } else {
            s(new g(str));
        }
    }

    @Override // l0.c
    public final void p(boolean z2) {
        s(new g(Boolean.valueOf(z2)));
    }

    public final i0.b r() {
        ArrayList arrayList = this.f1540i;
        return (i0.b) arrayList.get(arrayList.size() - 1);
    }

    public final void s(i0.b bVar) {
        if (this.f1541j != null) {
            if (!(bVar instanceof i0.d) || this.f1593g) {
                ((i0.e) r()).j(this.f1541j, bVar);
            }
            this.f1541j = null;
            return;
        }
        if (this.f1540i.isEmpty()) {
            this.f1542k = bVar;
            return;
        }
        i0.b bVarR = r();
        if (!(bVarR instanceof i0.a)) {
            throw new IllegalStateException();
        }
        ((i0.a) bVarR).j(bVar);
    }
}
