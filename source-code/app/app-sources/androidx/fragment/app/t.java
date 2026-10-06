package androidx.fragment.app;

import android.util.Log;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class t extends androidx.lifecycle.u {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final j.o f381e = new j.o(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashMap f382b = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final HashMap f383c = new HashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final HashMap f384d = new HashMap();

    public t(boolean z2) {
    }

    @Override // androidx.lifecycle.u
    public final void a() {
        if (r.h(3)) {
            Log.d("FragmentManager", "onCleared called for " + this);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || t.class != obj.getClass()) {
            return false;
        }
        t tVar = (t) obj;
        return this.f382b.equals(tVar.f382b) && this.f383c.equals(tVar.f383c) && this.f384d.equals(tVar.f384d);
    }

    public final int hashCode() {
        return this.f384d.hashCode() + ((this.f383c.hashCode() + (this.f382b.hashCode() * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("FragmentManagerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} Fragments (");
        Iterator it = this.f382b.values().iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") Child Non Config (");
        Iterator it2 = this.f383c.keySet().iterator();
        while (it2.hasNext()) {
            sb.append((String) it2.next());
            if (it2.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") ViewModelStores (");
        Iterator it3 = this.f384d.keySet().iterator();
        while (it3.hasNext()) {
            sb.append((String) it3.next());
            if (it3.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(')');
        return sb.toString();
    }
}
