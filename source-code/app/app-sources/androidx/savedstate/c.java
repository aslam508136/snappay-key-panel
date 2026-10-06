package androidx.savedstate;

import android.os.Bundle;
import androidx.lifecycle.t;
import java.util.Set;
import l.g;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Bundle f464b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f465c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public androidx.activity.e f466d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g f463a = new g();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f467e = true;

    public final Bundle a(String str) {
        if (!this.f465c) {
            throw new IllegalStateException("You can consumeRestoredStateForKey only after super.onCreate of corresponding component");
        }
        Bundle bundle = this.f464b;
        if (bundle == null) {
            return null;
        }
        Bundle bundle2 = bundle.getBundle(str);
        this.f464b.remove(str);
        if (this.f464b.isEmpty()) {
            this.f464b = null;
        }
        return bundle2;
    }

    public final void b(String str, b bVar) {
        Object obj;
        g gVar = this.f463a;
        l.c cVar = gVar.f1556a;
        while (cVar != null && !cVar.f1547a.equals(str)) {
            cVar = cVar.f1549c;
        }
        if (cVar != null) {
            obj = cVar.f1548b;
        } else {
            l.c cVar2 = new l.c(str, bVar);
            gVar.f1559d++;
            l.c cVar3 = gVar.f1557b;
            if (cVar3 == null) {
                gVar.f1556a = cVar2;
            } else {
                cVar3.f1549c = cVar2;
                cVar2.f1550d = cVar3;
            }
            gVar.f1557b = cVar2;
            obj = null;
        }
        if (((b) obj) != null) {
            throw new IllegalArgumentException("SavedStateProvider with the given key is already registered");
        }
    }

    public final void c() {
        if (!this.f467e) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
        if (this.f466d == null) {
            this.f466d = new androidx.activity.e(this);
        }
        try {
            t.class.getDeclaredConstructor(new Class[0]);
            ((Set) this.f466d.f60b).add(t.class.getName());
        } catch (NoSuchMethodException e2) {
            throw new IllegalArgumentException("Class" + t.class.getSimpleName() + " must have default constructor in order to be automatically recreated", e2);
        }
    }
}
