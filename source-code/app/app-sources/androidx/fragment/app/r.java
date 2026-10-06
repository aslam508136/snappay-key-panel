package androidx.fragment.app;

import android.os.Looper;
import android.util.Log;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class r {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f348b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ArrayList f350d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public androidx.activity.j f352f;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final CopyOnWriteArrayList f356j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f357k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public l f358l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public l f359m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final p f360n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public androidx.activity.result.d f361o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public androidx.activity.result.d f362p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public androidx.activity.result.d f363q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public ArrayDeque f364r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f365s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f366t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f367u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public ArrayList f368v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ArrayList f369w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public ArrayList f370x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public t f371y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final j f372z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f347a = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final w f349c = new w();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final m f351e = new m(this);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final o f353g = new o(this);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final AtomicInteger f354h = new AtomicInteger();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Map f355i = Collections.synchronizedMap(new HashMap());

    public r() {
        Collections.synchronizedMap(new HashMap());
        Collections.synchronizedMap(new HashMap());
        new CopyOnWriteArrayList();
        this.f356j = new CopyOnWriteArrayList();
        this.f357k = -1;
        this.f360n = new p(this);
        this.f364r = new ArrayDeque();
        this.f372z = new j(this, 2);
    }

    public static boolean h(int i2) {
        return Log.isLoggable("FragmentManager", i2);
    }

    public final void a() {
        this.f348b = false;
        this.f369w.clear();
        this.f368v.clear();
    }

    public final HashSet b() {
        HashSet hashSet = new HashSet();
        Iterator it = this.f349c.b().iterator();
        if (!it.hasNext()) {
            return hashSet;
        }
        androidx.activity.c.b(it.next());
        throw null;
    }

    public final void c(int i2) {
        try {
            this.f348b = true;
            Iterator it = this.f349c.f399b.values().iterator();
            while (it.hasNext()) {
                androidx.activity.c.b(it.next());
            }
            i(i2, false);
            Iterator it2 = b().iterator();
            if (it2.hasNext()) {
                ((z) it2.next()).getClass();
                WeakHashMap weakHashMap = x.u.f2012a;
                throw null;
            }
            this.f348b = false;
            e(true);
        } catch (Throwable th) {
            this.f348b = false;
            throw th;
        }
    }

    public final void d(boolean z2) {
        if (this.f348b) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }
        if (this.f358l == null) {
            if (!this.f367u) {
                throw new IllegalStateException("FragmentManager has not been attached to a host.");
            }
            throw new IllegalStateException("FragmentManager has been destroyed");
        }
        if (Looper.myLooper() != this.f358l.f335c.getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
        if (!z2) {
            if (this.f365s || this.f366t) {
                throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
            }
        }
        if (this.f368v == null) {
            this.f368v = new ArrayList();
            this.f369w = new ArrayList();
        }
        this.f348b = false;
    }

    public final boolean e(boolean z2) {
        boolean z3;
        d(z2);
        boolean z4 = false;
        while (true) {
            ArrayList arrayList = this.f368v;
            ArrayList arrayList2 = this.f369w;
            synchronized (this.f347a) {
                if (this.f347a.isEmpty()) {
                    z3 = false;
                } else {
                    int size = this.f347a.size();
                    z3 = false;
                    for (int i2 = 0; i2 < size; i2++) {
                        ((a) this.f347a.get(i2)).e(arrayList, arrayList2);
                        z3 |= true;
                    }
                    this.f347a.clear();
                    this.f358l.f335c.removeCallbacks(this.f372z);
                }
            }
            if (!z3) {
                l();
                this.f349c.f399b.values().removeAll(Collections.singleton(null));
                return z4;
            }
            z4 = true;
            this.f348b = true;
            try {
                j(this.f368v, this.f369w);
                a();
            } catch (Throwable th) {
                a();
                throw th;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0079  */
    /* JADX WARN: Code duplicated, block: B:46:0x00af  */
    public final void f(ArrayList arrayList, ArrayList arrayList2, int i2, int i3) {
        x xVar;
        boolean z2 = ((a) arrayList.get(i2)).f302o;
        ArrayList arrayList3 = this.f370x;
        if (arrayList3 == null) {
            this.f370x = new ArrayList();
        } else {
            arrayList3.clear();
        }
        this.f370x.addAll(this.f349c.c());
        boolean z3 = false;
        int i4 = i2;
        while (true) {
            int i5 = 1;
            if (i4 >= i3) {
                this.f370x.clear();
                if (!z2 && this.f357k >= 1) {
                    for (int i6 = i2; i6 < i3; i6++) {
                        Iterator it = ((a) arrayList.get(i6)).f288a.iterator();
                        while (it.hasNext()) {
                            ((x) it.next()).getClass();
                        }
                    }
                }
                for (int i7 = i2; i7 < i3; i7++) {
                    a aVar = (a) arrayList.get(i7);
                    if (((Boolean) arrayList2.get(i7)).booleanValue()) {
                        aVar.a(-1);
                        aVar.d();
                    } else {
                        aVar.a(1);
                        aVar.c();
                    }
                }
                boolean zBooleanValue = ((Boolean) arrayList2.get(i3 - 1)).booleanValue();
                for (int i8 = i2; i8 < i3; i8++) {
                    a aVar2 = (a) arrayList.get(i8);
                    if (zBooleanValue) {
                        for (int size = aVar2.f288a.size() - 1; size >= 0; size--) {
                            ((x) aVar2.f288a.get(size)).getClass();
                        }
                    } else {
                        Iterator it2 = aVar2.f288a.iterator();
                        while (it2.hasNext()) {
                            ((x) it2.next()).getClass();
                        }
                    }
                }
                i(this.f357k, true);
                HashSet hashSet = new HashSet();
                for (int i9 = i2; i9 < i3; i9++) {
                    Iterator it3 = ((a) arrayList.get(i9)).f288a.iterator();
                    while (it3.hasNext()) {
                        ((x) it3.next()).getClass();
                    }
                }
                Iterator it4 = hashSet.iterator();
                if (it4.hasNext()) {
                    ((z) it4.next()).getClass();
                    throw null;
                }
                while (i2 < i3) {
                    a aVar3 = (a) arrayList.get(i2);
                    if (((Boolean) arrayList2.get(i2)).booleanValue() && aVar3.f304q >= 0) {
                        aVar3.f304q = -1;
                    }
                    aVar3.getClass();
                    i2++;
                }
                return;
            }
            a aVar4 = (a) arrayList.get(i4);
            int i10 = 6;
            if (!((Boolean) arrayList2.get(i4)).booleanValue()) {
                ArrayList arrayList4 = this.f370x;
                int i11 = 0;
                while (true) {
                    ArrayList arrayList5 = aVar4.f288a;
                    if (i11 >= arrayList5.size()) {
                        break;
                    }
                    int i12 = ((x) arrayList5.get(i11)).f400a;
                    if (i12 == i5) {
                        arrayList4.add(null);
                    } else {
                        if (i12 == 2) {
                            throw null;
                        }
                        if (i12 == 3 || i12 == i10) {
                            arrayList4.remove((Object) null);
                            xVar = new x(0);
                        } else if (i12 == 7) {
                            arrayList4.add(null);
                        } else if (i12 == 8) {
                            xVar = new x(0);
                        }
                        arrayList5.add(i11, xVar);
                        i11++;
                    }
                    i11++;
                    i5 = 1;
                    i10 = 6;
                }
            } else {
                int i13 = 1;
                ArrayList arrayList6 = this.f370x;
                ArrayList arrayList7 = aVar4.f288a;
                int size2 = arrayList7.size() - 1;
                while (size2 >= 0) {
                    x xVar2 = (x) arrayList7.get(size2);
                    int i14 = xVar2.f400a;
                    if (i14 == i13) {
                        arrayList6.remove((Object) null);
                    } else if (i14 == 3 || i14 == 6) {
                        arrayList6.add(null);
                    } else if (i14 == 7) {
                        arrayList6.remove((Object) null);
                    } else if (i14 == 10) {
                        xVar2.f406g = xVar2.f405f;
                    }
                    size2--;
                    i13 = 1;
                }
            }
            z3 = z3 || aVar4.f294g;
            i4++;
        }
    }

    public final void g() {
        w wVar = this.f349c;
        ArrayList arrayList = wVar.f398a;
        int size = arrayList.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            } else {
                androidx.activity.c.b(arrayList.get(size));
            }
        }
        Iterator it = wVar.f399b.values().iterator();
        while (it.hasNext()) {
            androidx.activity.c.b(it.next());
        }
    }

    public final void i(int i2, boolean z2) {
        if (this.f358l == null && i2 != -1) {
            throw new IllegalStateException("No activity");
        }
        if (z2 || i2 != this.f357k) {
            this.f357k = i2;
            w wVar = this.f349c;
            Iterator it = wVar.f398a.iterator();
            if (it.hasNext()) {
                androidx.activity.c.b(it.next());
                throw null;
            }
            Iterator it2 = wVar.f399b.values().iterator();
            while (it2.hasNext()) {
                androidx.activity.c.b(it2.next());
            }
            Iterator it3 = wVar.b().iterator();
            if (it3.hasNext()) {
                androidx.activity.c.b(it3.next());
                throw null;
            }
        }
    }

    public final void j(ArrayList arrayList, ArrayList arrayList2) {
        if (arrayList.isEmpty()) {
            return;
        }
        if (arrayList.size() != arrayList2.size()) {
            throw new IllegalStateException("Internal error with the back stack records");
        }
        int size = arrayList.size();
        int i2 = 0;
        int i3 = 0;
        while (i2 < size) {
            if (!((a) arrayList.get(i2)).f302o) {
                if (i3 != i2) {
                    f(arrayList, arrayList2, i3, i2);
                }
                i3 = i2 + 1;
                if (((Boolean) arrayList2.get(i2)).booleanValue()) {
                    while (i3 < size && ((Boolean) arrayList2.get(i3)).booleanValue() && !((a) arrayList.get(i3)).f302o) {
                        i3++;
                    }
                }
                f(arrayList, arrayList2, i2, i3);
                i2 = i3 - 1;
            }
            i2++;
        }
        if (i3 != size) {
            f(arrayList, arrayList2, i3, size);
        }
    }

    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        l lVar = this.f358l;
        if (lVar != null) {
            sb.append(lVar.getClass().getSimpleName());
            sb.append("{");
            sb.append(Integer.toHexString(System.identityHashCode(this.f358l)));
            str = "}";
        } else {
            str = "null";
        }
        sb.append(str);
        sb.append("}}");
        return sb.toString();
    }

    public final void l() {
        synchronized (this.f347a) {
            if (!this.f347a.isEmpty()) {
                this.f353g.f340a = true;
                return;
            }
            o oVar = this.f353g;
            ArrayList arrayList = this.f350d;
            oVar.f340a = (arrayList != null ? arrayList.size() : 0) > 0;
        }
    }
}
