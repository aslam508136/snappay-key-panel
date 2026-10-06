package l;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class d implements Iterator, f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public c f1551a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f1552b = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ g f1553c;

    public d(g gVar) {
        this.f1553c = gVar;
    }

    @Override // l.f
    public final void a(c cVar) {
        c cVar2 = this.f1551a;
        if (cVar == cVar2) {
            c cVar3 = cVar2.f1550d;
            this.f1551a = cVar3;
            this.f1552b = cVar3 == null;
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.f1552b) {
            return this.f1553c.f1556a != null;
        }
        c cVar = this.f1551a;
        return (cVar == null || cVar.f1549c == null) ? false : true;
    }

    @Override // java.util.Iterator
    public final Object next() {
        c cVar;
        if (this.f1552b) {
            this.f1552b = false;
            cVar = this.f1553c.f1556a;
        } else {
            c cVar2 = this.f1551a;
            cVar = cVar2 != null ? cVar2.f1549c : null;
        }
        this.f1551a = cVar;
        return cVar;
    }
}
