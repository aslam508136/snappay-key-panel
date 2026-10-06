package androidx.fragment.app;

import android.util.Log;
import java.io.PrintWriter;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f288a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f289b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f290c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f291d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f292e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f293f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f294g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f295h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f296i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public CharSequence f297j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f298k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public CharSequence f299l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ArrayList f300m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ArrayList f301n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f302o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final r f303p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f304q;

    public a(r rVar) {
        l lVar = rVar.f358l;
        if (lVar != null) {
            lVar.f334b.getClassLoader();
        }
        this.f288a = new ArrayList();
        this.f302o = false;
        this.f304q = -1;
        this.f303p = rVar;
    }

    public final void a(int i2) {
        if (this.f294g) {
            if (r.h(2)) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + i2);
            }
            ArrayList arrayList = this.f288a;
            int size = arrayList.size();
            for (int i3 = 0; i3 < size; i3++) {
                ((x) arrayList.get(i3)).getClass();
            }
        }
    }

    public final void b(String str, PrintWriter printWriter, boolean z2) {
        String str2;
        if (z2) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.f295h);
            printWriter.print(" mIndex=");
            printWriter.print(this.f304q);
            printWriter.print(" mCommitted=");
            printWriter.println(false);
            if (this.f293f != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.f293f));
            }
            if (this.f289b != 0 || this.f290c != 0) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f289b));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.f290c));
            }
            if (this.f291d != 0 || this.f292e != 0) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f291d));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.f292e));
            }
            if (this.f296i != 0 || this.f297j != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.f296i));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.f297j);
            }
            if (this.f298k != 0 || this.f299l != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.f298k));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.f299l);
            }
        }
        ArrayList arrayList = this.f288a;
        if (arrayList.isEmpty()) {
            return;
        }
        printWriter.print(str);
        printWriter.println("Operations:");
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            x xVar = (x) arrayList.get(i2);
            switch (xVar.f400a) {
                case 0:
                    str2 = "NULL";
                    break;
                case 1:
                    str2 = "ADD";
                    break;
                case 2:
                    str2 = "REPLACE";
                    break;
                case 3:
                    str2 = "REMOVE";
                    break;
                case 4:
                    str2 = "HIDE";
                    break;
                case 5:
                    str2 = "SHOW";
                    break;
                case 6:
                    str2 = "DETACH";
                    break;
                case 7:
                    str2 = "ATTACH";
                    break;
                case 8:
                    str2 = "SET_PRIMARY_NAV";
                    break;
                case 9:
                    str2 = "UNSET_PRIMARY_NAV";
                    break;
                case 10:
                    str2 = "OP_SET_MAX_LIFECYCLE";
                    break;
                default:
                    str2 = "cmd=" + xVar.f400a;
                    break;
            }
            printWriter.print(str);
            printWriter.print("  Op #");
            printWriter.print(i2);
            printWriter.print(": ");
            printWriter.print(str2);
            printWriter.print(" ");
            printWriter.println((Object) null);
            if (z2) {
                if (xVar.f401b != 0 || xVar.f402c != 0) {
                    printWriter.print(str);
                    printWriter.print("enterAnim=#");
                    printWriter.print(Integer.toHexString(xVar.f401b));
                    printWriter.print(" exitAnim=#");
                    printWriter.println(Integer.toHexString(xVar.f402c));
                }
                if (xVar.f403d != 0 || xVar.f404e != 0) {
                    printWriter.print(str);
                    printWriter.print("popEnterAnim=#");
                    printWriter.print(Integer.toHexString(xVar.f403d));
                    printWriter.print(" popExitAnim=#");
                    printWriter.println(Integer.toHexString(xVar.f404e));
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0034  */
    /* JADX WARN: Code duplicated, block: B:12:0x003b  */
    /* JADX WARN: Code duplicated, block: B:22:0x001a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:23:0x0030 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:24:0x0041 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:25:0x0042 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0043 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:27:0x0044 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:28:0x0045 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:29:0x0046 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:31:0x003e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:4:0x0009  */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:26:0x0043
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final void c() {
        /*
            r7 = this;
            java.util.ArrayList r0 = r7.f288a
            int r1 = r0.size()
            r2 = 0
        L7:
            if (r2 >= r1) goto L47
            java.lang.Object r3 = r0.get(r2)
            androidx.fragment.app.x r3 = (androidx.fragment.app.x) r3
            r3.getClass()
            int r4 = r3.f400a
            androidx.fragment.app.r r5 = r7.f303p
            r6 = 0
            switch(r4) {
                case 1: goto L46;
                case 2: goto L1a;
                case 3: goto L45;
                case 4: goto L44;
                case 5: goto L43;
                case 6: goto L42;
                case 7: goto L41;
                case 8: goto L34;
                case 9: goto L34;
                case 10: goto L30;
                default: goto L1a;
            }
        L1a:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Unknown cmd: "
            r1.<init>(r2)
            int r2 = r3.f400a
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L30:
            r5.getClass()
            throw r6
        L34:
            r5.getClass()
            boolean r4 = r7.f302o
            if (r4 != 0) goto L3e
            int r3 = r3.f400a
            r4 = 1
        L3e:
            int r2 = r2 + 1
            goto L7
        L41:
            throw r6
        L42:
            throw r6
        L43:
            throw r6
        L44:
            throw r6
        L45:
            throw r6
        L46:
            throw r6
        L47:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.a.c():void");
    }

    public final void d() {
        ArrayList arrayList = this.f288a;
        int size = arrayList.size();
        while (true) {
            size--;
            if (size >= 0) {
                x xVar = (x) arrayList.get(size);
                xVar.getClass();
                int i2 = xVar.f400a;
                r rVar = this.f303p;
                switch (i2) {
                    case 1:
                        throw null;
                    case 2:
                    default:
                        throw new IllegalArgumentException("Unknown cmd: " + xVar.f400a);
                    case 3:
                        throw null;
                    case 4:
                        throw null;
                    case 5:
                        throw null;
                    case 6:
                        throw null;
                    case 7:
                        throw null;
                    case 8:
                    case 9:
                        rVar.getClass();
                        break;
                    case 10:
                        rVar.getClass();
                        throw null;
                }
            } else {
                return;
            }
        }
    }

    public final void e(ArrayList arrayList, ArrayList arrayList2) {
        if (r.h(2)) {
            Log.v("FragmentManager", "Run: " + this);
        }
        arrayList.add(this);
        arrayList2.add(Boolean.FALSE);
        if (this.f294g) {
            r rVar = this.f303p;
            if (rVar.f350d == null) {
                rVar.f350d = new ArrayList();
            }
            rVar.f350d.add(this);
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.f304q >= 0) {
            sb.append(" #");
            sb.append(this.f304q);
        }
        if (this.f295h != null) {
            sb.append(" ");
            sb.append(this.f295h);
        }
        sb.append("}");
        return sb.toString();
    }
}
