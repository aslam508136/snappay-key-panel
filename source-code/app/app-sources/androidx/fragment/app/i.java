package androidx.fragment.app;

import d.i0;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f327a;

    public i(i0 i0Var) {
        this.f327a = i0Var;
    }

    public void a() {
        r rVar = ((l) this.f327a).f336d;
        if (rVar.f358l == null) {
            return;
        }
        rVar.f365s = false;
        rVar.f366t = false;
        rVar.f371y.getClass();
        Iterator it = rVar.f349c.c().iterator();
        while (it.hasNext()) {
            androidx.activity.c.b(it.next());
        }
    }

    public /* synthetic */ i(Object obj, int i2) {
        this.f327a = obj;
    }
}
