package androidx.fragment.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f398a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashMap f399b = new HashMap();

    public final void a() {
        Iterator it = this.f399b.values().iterator();
        while (it.hasNext()) {
            androidx.activity.c.b(it.next());
        }
    }

    public final ArrayList b() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f399b.values().iterator();
        while (it.hasNext()) {
            androidx.activity.c.b(it.next());
        }
        return arrayList;
    }

    public final List c() {
        ArrayList arrayList;
        if (this.f398a.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (this.f398a) {
            arrayList = new ArrayList(this.f398a);
        }
        return arrayList;
    }
}
