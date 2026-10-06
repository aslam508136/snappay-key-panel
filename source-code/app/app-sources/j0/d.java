package j0;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public abstract class d implements Iterator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public e f1508a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public e f1509b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1510c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ f f1511d;

    public d(f fVar) {
        this.f1511d = fVar;
        this.f1508a = fVar.f1525e.f1515d;
        this.f1510c = fVar.f1524d;
    }

    public final e a() {
        e eVar = this.f1508a;
        f fVar = this.f1511d;
        if (eVar == fVar.f1525e) {
            throw new NoSuchElementException();
        }
        if (fVar.f1524d != this.f1510c) {
            throw new ConcurrentModificationException();
        }
        this.f1508a = eVar.f1515d;
        this.f1509b = eVar;
        return eVar;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f1508a != this.f1511d.f1525e;
    }

    @Override // java.util.Iterator
    public final void remove() {
        e eVar = this.f1509b;
        if (eVar == null) {
            throw new IllegalStateException();
        }
        f fVar = this.f1511d;
        fVar.d(eVar, true);
        this.f1509b = null;
        this.f1510c = fVar.f1524d;
    }
}
