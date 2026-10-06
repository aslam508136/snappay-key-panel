package androidx.lifecycle;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class n extends i {
    public final WeakReference R;
    public final l.a P = new l.a();
    public int S = 0;
    public boolean T = false;
    public boolean U = false;
    public final ArrayList V = new ArrayList();
    public h Q = h.INITIALIZED;
    public final boolean W = true;

    public n(l lVar) {
        this.R = new WeakReference(lVar);
    }

    @Override // androidx.lifecycle.i
    public final void X(k kVar) {
        n0("removeObserver");
        l.a aVar = this.P;
        l.c cVarA = aVar.a(kVar);
        if (cVarA != null) {
            aVar.f1559d--;
            WeakHashMap weakHashMap = aVar.f1558c;
            if (!weakHashMap.isEmpty()) {
                Iterator it = weakHashMap.keySet().iterator();
                while (it.hasNext()) {
                    ((l.f) it.next()).a(cVarA);
                }
            }
            l.c cVar = cVarA.f1550d;
            l.c cVar2 = cVarA.f1549c;
            if (cVar != null) {
                cVar.f1549c = cVar2;
            } else {
                aVar.f1556a = cVar2;
            }
            l.c cVar3 = cVarA.f1549c;
            if (cVar3 != null) {
                cVar3.f1550d = cVar;
            } else {
                aVar.f1557b = cVar;
            }
            cVarA.f1549c = null;
            cVarA.f1550d = null;
        }
        aVar.f1545e.remove(kVar);
    }

    @Override // androidx.lifecycle.i
    public final void b(k kVar) {
        Object obj;
        l lVar;
        g gVar;
        n0("addObserver");
        h hVar = this.Q;
        h hVar2 = h.DESTROYED;
        if (hVar != hVar2) {
            hVar2 = h.INITIALIZED;
        }
        m mVar = new m(kVar, hVar2);
        l.a aVar = this.P;
        l.c cVarA = aVar.a(kVar);
        if (cVarA != null) {
            obj = cVarA.f1548b;
        } else {
            HashMap map = aVar.f1545e;
            l.c cVar = new l.c(kVar, mVar);
            aVar.f1559d++;
            l.c cVar2 = aVar.f1557b;
            if (cVar2 == null) {
                aVar.f1556a = cVar;
            } else {
                cVar2.f1549c = cVar;
                cVar.f1550d = cVar2;
            }
            aVar.f1557b = cVar;
            map.put(kVar, cVar);
            obj = null;
        }
        if (((m) obj) == null && (lVar = (l) this.R.get()) != null) {
            boolean z2 = this.S != 0 || this.T;
            h hVarM0 = m0(kVar);
            this.S++;
            while (mVar.f455a.compareTo(hVarM0) < 0 && aVar.f1545e.containsKey(kVar)) {
                h hVar3 = mVar.f455a;
                ArrayList arrayList = this.V;
                arrayList.add(hVar3);
                int iOrdinal = mVar.f455a.ordinal();
                if (iOrdinal == 1) {
                    gVar = g.ON_CREATE;
                } else if (iOrdinal != 2) {
                    gVar = iOrdinal != 3 ? null : g.ON_RESUME;
                } else {
                    gVar = g.ON_START;
                }
                if (gVar == null) {
                    throw new IllegalStateException("no event up from " + mVar.f455a);
                }
                mVar.a(lVar, gVar);
                arrayList.remove(arrayList.size() - 1);
                hVarM0 = m0(kVar);
            }
            if (!z2) {
                q0();
            }
            this.S--;
        }
    }

    public final h m0(k kVar) {
        l.a aVar = this.P;
        l.c cVar = aVar.f1545e.containsKey(kVar) ? ((l.c) aVar.f1545e.get(kVar)).f1550d : null;
        h hVar = cVar != null ? ((m) cVar.f1548b).f455a : null;
        ArrayList arrayList = this.V;
        h hVar2 = arrayList.isEmpty() ? null : (h) arrayList.get(arrayList.size() - 1);
        h hVar3 = this.Q;
        if (hVar == null || hVar.compareTo(hVar3) >= 0) {
            hVar = hVar3;
        }
        return (hVar2 == null || hVar2.compareTo(hVar) >= 0) ? hVar : hVar2;
    }

    public final void n0(String str) {
        if (this.W) {
            if (k.a.f1528b == null) {
                synchronized (k.a.class) {
                    if (k.a.f1528b == null) {
                        k.a.f1528b = new k.a();
                    }
                }
            }
            if (!k.a.f1528b.i()) {
                throw new IllegalStateException(androidx.activity.c.a("Method ", str, " must be called on the main thread"));
            }
        }
    }

    public final void o0(g gVar) {
        n0("handleLifecycleEvent");
        p0(gVar.a());
    }

    public final void p0(h hVar) {
        if (this.Q == hVar) {
            return;
        }
        this.Q = hVar;
        if (this.T || this.S != 0) {
            this.U = true;
            return;
        }
        this.T = true;
        q0();
        this.T = false;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0029  */
    public final void q0() {
        boolean z2;
        g gVar;
        g gVar2;
        l lVar = (l) this.R.get();
        if (lVar == null) {
            throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is alreadygarbage collected. It is too late to change lifecycle state.");
        }
        while (true) {
            l.a aVar = this.P;
            int i2 = 1;
            if (aVar.f1559d == 0) {
                z2 = true;
            } else {
                h hVar = ((m) aVar.f1556a.f1548b).f455a;
                h hVar2 = ((m) aVar.f1557b.f1548b).f455a;
                if (hVar == hVar2 && this.Q == hVar2) {
                    z2 = true;
                } else {
                    z2 = false;
                }
            }
            this.U = false;
            if (z2) {
                return;
            }
            int iCompareTo = this.Q.compareTo(((m) aVar.f1556a.f1548b).f455a);
            ArrayList arrayList = this.V;
            HashMap map = aVar.f1545e;
            WeakHashMap weakHashMap = aVar.f1558c;
            if (iCompareTo < 0) {
                l.b bVar = new l.b(aVar.f1557b, aVar.f1556a, i2);
                weakHashMap.put(bVar, Boolean.FALSE);
                while (bVar.hasNext() && !this.U) {
                    Map.Entry entry = (Map.Entry) bVar.next();
                    m mVar = (m) entry.getValue();
                    while (mVar.f455a.compareTo(this.Q) > 0 && !this.U && map.containsKey(entry.getKey())) {
                        int iOrdinal = mVar.f455a.ordinal();
                        if (iOrdinal == 2) {
                            gVar2 = g.ON_DESTROY;
                        } else if (iOrdinal != 3) {
                            gVar2 = iOrdinal != 4 ? null : g.ON_PAUSE;
                        } else {
                            gVar2 = g.ON_STOP;
                        }
                        if (gVar2 == null) {
                            throw new IllegalStateException("no event down from " + mVar.f455a);
                        }
                        arrayList.add(gVar2.a());
                        mVar.a(lVar, gVar2);
                        arrayList.remove(arrayList.size() - 1);
                    }
                }
            }
            l.c cVar = aVar.f1557b;
            if (!this.U && cVar != null && this.Q.compareTo(((m) cVar.f1548b).f455a) > 0) {
                l.d dVar = new l.d(aVar);
                weakHashMap.put(dVar, Boolean.FALSE);
                while (dVar.hasNext() && !this.U) {
                    Map.Entry entry2 = (Map.Entry) dVar.next();
                    m mVar2 = (m) entry2.getValue();
                    while (mVar2.f455a.compareTo(this.Q) < 0 && !this.U && map.containsKey(entry2.getKey())) {
                        arrayList.add(mVar2.f455a);
                        int iOrdinal2 = mVar2.f455a.ordinal();
                        if (iOrdinal2 == 1) {
                            gVar = g.ON_CREATE;
                        } else if (iOrdinal2 != 2) {
                            gVar = iOrdinal2 != 3 ? null : g.ON_RESUME;
                        } else {
                            gVar = g.ON_START;
                        }
                        if (gVar == null) {
                            throw new IllegalStateException("no event up from " + mVar2.f455a);
                        }
                        mVar2.a(lVar, gVar);
                        arrayList.remove(arrayList.size() - 1);
                    }
                }
            }
        }
    }
}
