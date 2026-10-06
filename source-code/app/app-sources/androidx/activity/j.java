package androidx.activity;

import androidx.fragment.app.o;
import androidx.fragment.app.r;
import androidx.lifecycle.n;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Runnable f71a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayDeque f72b = new ArrayDeque();

    public j(b bVar) {
        this.f71a = bVar;
    }

    public final void a(k kVar, o oVar) {
        n nVarH = kVar.h();
        if (nVarH.Q == androidx.lifecycle.h.DESTROYED) {
            return;
        }
        oVar.f341b.add(new OnBackPressedDispatcher$LifecycleOnBackPressedCancellable(this, nVarH, oVar));
    }

    public final void b() {
        int size;
        Iterator itDescendingIterator = this.f72b.descendingIterator();
        while (itDescendingIterator.hasNext()) {
            o oVar = (o) itDescendingIterator.next();
            if (oVar.f340a) {
                r rVar = oVar.f342c;
                rVar.e(true);
                if (!rVar.f353g.f340a) {
                    rVar.f352f.b();
                    return;
                }
                boolean z2 = false;
                rVar.e(false);
                rVar.d(true);
                ArrayList arrayList = rVar.f368v;
                ArrayList arrayList2 = rVar.f369w;
                ArrayList arrayList3 = rVar.f350d;
                if (arrayList3 != null && (size = arrayList3.size() - 1) >= 0) {
                    arrayList.add(rVar.f350d.remove(size));
                    arrayList2.add(Boolean.TRUE);
                    z2 = true;
                }
                if (z2) {
                    rVar.f348b = true;
                    try {
                        rVar.j(rVar.f368v, rVar.f369w);
                        rVar.a();
                    } catch (Throwable th) {
                        rVar.a();
                        throw th;
                    }
                }
                rVar.l();
                rVar.f349c.f399b.values().removeAll(Collections.singleton(null));
                return;
            }
        }
        Runnable runnable = this.f71a;
        if (runnable != null) {
            runnable.run();
        }
    }
}
