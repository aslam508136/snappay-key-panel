package l;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class e implements Iterator, f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public c f1554a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c f1555b;

    public e(c cVar, c cVar2) {
        this.f1554a = cVar2;
        this.f1555b = cVar;
    }

    @Override // l.f
    public final void a(c cVar) {
        c cVar2;
        if (this.f1554a == cVar && cVar == this.f1555b) {
            this.f1555b = null;
            this.f1554a = null;
        }
        c cVar3 = this.f1554a;
        if (cVar3 == cVar) {
            switch (((b) this).f1546c) {
                case 0:
                    cVar2 = cVar3.f1550d;
                    break;
                default:
                    cVar2 = cVar3.f1549c;
                    break;
            }
            this.f1554a = cVar2;
        }
        if (this.f1555b == cVar) {
            this.f1555b = b();
        }
    }

    public final c b() {
        c cVar = this.f1555b;
        c cVar2 = this.f1554a;
        if (cVar == cVar2 || cVar2 == null) {
            return null;
        }
        switch (((b) this).f1546c) {
            case 0:
                return cVar.f1549c;
            default:
                return cVar.f1550d;
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f1555b != null;
    }

    @Override // java.util.Iterator
    public final Object next() {
        c cVar = this.f1555b;
        this.f1555b = b();
        return cVar;
    }
}
