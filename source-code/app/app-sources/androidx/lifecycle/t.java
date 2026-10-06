package androidx.lifecycle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class t implements androidx.savedstate.a {
    public final void a(androidx.savedstate.e eVar) {
        Object obj;
        boolean z2;
        if (!(eVar instanceof x)) {
            throw new IllegalStateException("Internal error: OnRecreation should be registered only on componentsthat implement ViewModelStoreOwner");
        }
        w wVarE = ((x) eVar).e();
        androidx.activity.h hVar = (androidx.activity.h) eVar;
        androidx.savedstate.c cVar = hVar.f65e.f469b;
        wVarE.getClass();
        Iterator it = new HashSet(wVarE.f460a.keySet()).iterator();
        while (it.hasNext()) {
            u uVar = (u) wVarE.f460a.get((String) it.next());
            n nVar = hVar.f64d;
            HashMap map = uVar.f459a;
            if (map == null) {
                obj = null;
            } else {
                synchronized (map) {
                    obj = uVar.f459a.get("androidx.lifecycle.savedstate.vm.tag");
                }
            }
            SavedStateHandleController savedStateHandleController = (SavedStateHandleController) obj;
            if (savedStateHandleController != null && !(z2 = savedStateHandleController.f412a)) {
                if (z2) {
                    throw new IllegalStateException("Already attached to lifecycleOwner");
                }
                savedStateHandleController.f412a = true;
                nVar.b(savedStateHandleController);
                throw null;
            }
        }
        if (new HashSet(wVarE.f460a.keySet()).isEmpty()) {
            return;
        }
        cVar.c();
    }
}
