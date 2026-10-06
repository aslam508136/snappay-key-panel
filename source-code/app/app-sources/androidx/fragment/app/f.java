package androidx.fragment.app;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import j.s0;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class f implements a.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ h f320a;

    public f(d.n nVar) {
        this.f320a = nVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // a.b
    public final void a() {
        h hVar = this.f320a;
        l lVar = (l) hVar.f322i.f327a;
        r rVar = lVar.f336d;
        if (rVar.f358l != null) {
            throw new IllegalStateException("Already attached");
        }
        rVar.f358l = lVar;
        rVar.f359m = lVar;
        if (lVar instanceof u) {
            rVar.f356j.add((u) lVar);
        }
        if (lVar instanceof androidx.activity.k) {
            androidx.activity.k kVar = (androidx.activity.k) lVar;
            androidx.activity.j jVarA = kVar.a();
            rVar.f352f = jVarA;
            jVarA.a(kVar, rVar.f353g);
        }
        t tVar = lVar instanceof androidx.lifecycle.x ? (t) new s0(((androidx.lifecycle.x) lVar).e(), t.f381e).a(t.class) : new t(false);
        rVar.f371y = tVar;
        tVar.getClass();
        rVar.f349c.getClass();
        Object obj = rVar.f358l;
        if (obj instanceof androidx.activity.result.f) {
            androidx.activity.d dVarC = ((androidx.activity.result.f) obj).c();
            rVar.f361o = dVarC.b("FragmentManager:StartActivityForResult", new b.b(1), new n(rVar, 4));
            rVar.f362p = dVarC.b("FragmentManager:StartIntentSenderForResult", new b.b(2), new n(rVar, 0));
            rVar.f363q = dVarC.b("FragmentManager:RequestPermissions", new b.b(0), new n(rVar, 1));
        }
        Bundle bundleA = hVar.f65e.f469b.a("android:support:fragments");
        if (bundleA != null) {
            Parcelable parcelable = bundleA.getParcelable("android:support:fragments");
            l lVar2 = (l) hVar.f322i.f327a;
            if (!(lVar2 instanceof androidx.lifecycle.x)) {
                throw new IllegalStateException("Your FragmentHostCallback must implement ViewModelStoreOwner to call restoreSaveState(). Call restoreAllState()  if you're still using retainNestedNonConfig().");
            }
            r rVar2 = lVar2.f336d;
            rVar2.getClass();
            if (parcelable == null) {
                return;
            }
            s sVar = (s) parcelable;
            if (sVar.f373a == null) {
                return;
            }
            w wVar = rVar2.f349c;
            wVar.f399b.clear();
            for (v vVar : sVar.f373a) {
                if (vVar != null) {
                    androidx.activity.c.b(rVar2.f371y.f382b.get(vVar.f386b));
                    ClassLoader classLoader = rVar2.f358l.f334b.getClassLoader();
                    rVar2.f360n.a(vVar.f385a);
                    Bundle bundle = vVar.f394j;
                    if (bundle == null) {
                        throw null;
                    }
                    bundle.setClassLoader(classLoader);
                    throw null;
                }
            }
            t tVar2 = rVar2.f371y;
            tVar2.getClass();
            Iterator it = new ArrayList(tVar2.f382b.values()).iterator();
            if (it.hasNext()) {
                androidx.activity.c.b(it.next());
                throw null;
            }
            ArrayList arrayList = sVar.f374b;
            wVar.f398a.clear();
            if (arrayList != null) {
                Iterator it2 = arrayList.iterator();
                if (it2.hasNext()) {
                    String str = (String) it2.next();
                    androidx.activity.c.b(wVar.f399b.get(str));
                    throw new IllegalStateException(androidx.activity.c.a("No instantiated fragment for (", str, ")"));
                }
            }
            if (sVar.f375c != null) {
                rVar2.f350d = new ArrayList(sVar.f375c.length);
                int i2 = 0;
                while (true) {
                    b[] bVarArr = sVar.f375c;
                    if (i2 >= bVarArr.length) {
                        break;
                    }
                    b bVar = bVarArr[i2];
                    bVar.getClass();
                    a aVar = new a(rVar2);
                    int i3 = 0;
                    int i4 = 0;
                    while (true) {
                        int[] iArr = bVar.f305a;
                        if (i3 >= iArr.length) {
                            break;
                        }
                        x xVar = new x();
                        int i5 = i3 + 1;
                        xVar.f400a = iArr[i3];
                        if (r.h(2)) {
                            Log.v("FragmentManager", "Instantiate " + aVar + " op #" + i4 + " base fragment #" + iArr[i5]);
                        }
                        String str2 = (String) bVar.f306b.get(i4);
                        if (str2 != null) {
                            androidx.activity.c.b(wVar.f399b.get(str2));
                        }
                        xVar.f405f = androidx.lifecycle.h.values()[bVar.f307c[i4]];
                        xVar.f406g = androidx.lifecycle.h.values()[bVar.f308d[i4]];
                        int i6 = i5 + 1;
                        int i7 = iArr[i5];
                        xVar.f401b = i7;
                        int i8 = i6 + 1;
                        int i9 = iArr[i6];
                        xVar.f402c = i9;
                        int i10 = i8 + 1;
                        int i11 = iArr[i8];
                        xVar.f403d = i11;
                        int i12 = iArr[i10];
                        xVar.f404e = i12;
                        aVar.f289b = i7;
                        aVar.f290c = i9;
                        aVar.f291d = i11;
                        aVar.f292e = i12;
                        aVar.f288a.add(xVar);
                        xVar.f401b = aVar.f289b;
                        xVar.f402c = aVar.f290c;
                        xVar.f403d = aVar.f291d;
                        xVar.f404e = aVar.f292e;
                        i4++;
                        i3 = i10 + 1;
                    }
                    aVar.f293f = bVar.f309e;
                    aVar.f295h = bVar.f310f;
                    aVar.f304q = bVar.f311g;
                    aVar.f294g = true;
                    aVar.f296i = bVar.f312h;
                    aVar.f297j = bVar.f313i;
                    aVar.f298k = bVar.f314j;
                    aVar.f299l = bVar.f315k;
                    aVar.f300m = bVar.f316l;
                    aVar.f301n = bVar.f317m;
                    aVar.f302o = bVar.f318n;
                    aVar.a(1);
                    if (r.h(2)) {
                        Log.v("FragmentManager", "restoreAllState: back stack #" + i2 + " (index " + aVar.f304q + "): " + aVar);
                        PrintWriter printWriter = new PrintWriter(new y());
                        aVar.b("  ", printWriter, false);
                        printWriter.close();
                    }
                    rVar2.f350d.add(aVar);
                    i2++;
                }
            } else {
                rVar2.f350d = null;
            }
            rVar2.f354h.set(sVar.f376d);
            String str3 = sVar.f377e;
            if (str3 != null) {
                androidx.activity.c.b(wVar.f399b.get(str3));
            }
            ArrayList arrayList2 = sVar.f378f;
            if (arrayList2 != null) {
                for (int i13 = 0; i13 < arrayList2.size(); i13++) {
                    Bundle bundle2 = (Bundle) sVar.f379g.get(i13);
                    bundle2.setClassLoader(rVar2.f358l.f334b.getClassLoader());
                    rVar2.f355i.put(arrayList2.get(i13), bundle2);
                }
            }
            rVar2.f364r = new ArrayDeque(sVar.f380h);
        }
    }
}
