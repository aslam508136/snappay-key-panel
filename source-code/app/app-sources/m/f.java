package m;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class f implements Iterator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f1617a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1618b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1619c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1620d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ i.d f1621e;

    public f(i.d dVar, int i2) {
        this.f1621e = dVar;
        this.f1617a = i2;
        this.f1618b = dVar.f();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f1619c < this.f1618b;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Object objD = this.f1621e.d(this.f1619c, this.f1617a);
        this.f1619c++;
        this.f1620d = true;
        return objD;
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.f1620d) {
            throw new IllegalStateException();
        }
        int i2 = this.f1619c - 1;
        this.f1619c = i2;
        this.f1618b--;
        this.f1620d = false;
        this.f1621e.j(i2);
    }
}
