package androidx.fragment.app;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class e implements androidx.savedstate.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ h f319a;

    public e(d.n nVar) {
        this.f319a = nVar;
    }

    @Override // androidx.savedstate.b
    public final Bundle a() {
        ArrayList arrayList;
        int size;
        Bundle bundle = new Bundle();
        Iterator it = ((l) this.f319a.f322i.f327a).f336d.f349c.c().iterator();
        while (it.hasNext()) {
            androidx.activity.c.b(it.next());
        }
        this.f319a.f323j.o0(androidx.lifecycle.g.ON_STOP);
        r rVar = ((l) this.f319a.f322i.f327a).f336d;
        Iterator it2 = rVar.b().iterator();
        while (it2.hasNext()) {
            ((z) it2.next()).getClass();
        }
        Iterator it3 = rVar.b().iterator();
        b[] bVarArr = null;
        parcelable = null;
        Parcelable parcelable = null;
        bVarArr = null;
        if (it3.hasNext()) {
            ((z) it3.next()).getClass();
            WeakHashMap weakHashMap = x.u.f2012a;
            throw null;
        }
        rVar.e(true);
        rVar.f365s = true;
        rVar.f371y.getClass();
        w wVar = rVar.f349c;
        wVar.getClass();
        HashMap map = wVar.f399b;
        ArrayList arrayList2 = new ArrayList(map.size());
        Iterator it4 = map.values().iterator();
        while (it4.hasNext()) {
            androidx.activity.c.b(it4.next());
        }
        if (!arrayList2.isEmpty()) {
            w wVar2 = rVar.f349c;
            synchronized (wVar2.f398a) {
                if (wVar2.f398a.isEmpty()) {
                    arrayList = null;
                } else {
                    arrayList = new ArrayList(wVar2.f398a.size());
                    Iterator it5 = wVar2.f398a.iterator();
                    if (it5.hasNext()) {
                        androidx.activity.c.b(it5.next());
                        throw null;
                    }
                }
            }
            ArrayList arrayList3 = rVar.f350d;
            if (arrayList3 != null && (size = arrayList3.size()) > 0) {
                bVarArr = new b[size];
                for (int i2 = 0; i2 < size; i2++) {
                    bVarArr[i2] = new b((a) rVar.f350d.get(i2));
                    if (r.h(2)) {
                        Log.v("FragmentManager", "saveAllState: adding back stack #" + i2 + ": " + rVar.f350d.get(i2));
                    }
                }
            }
            s sVar = new s();
            sVar.f373a = arrayList2;
            sVar.f374b = arrayList;
            sVar.f375c = bVarArr;
            sVar.f376d = rVar.f354h.get();
            sVar.f378f.addAll(rVar.f355i.keySet());
            sVar.f379g.addAll(rVar.f355i.values());
            sVar.f380h = new ArrayList(rVar.f364r);
            parcelable = sVar;
        } else if (r.h(2)) {
            Log.v("FragmentManager", "saveAllState: no fragments!");
        }
        if (parcelable != null) {
            bundle.putParcelable("android:support:fragments", parcelable);
        }
        return bundle;
    }
}
