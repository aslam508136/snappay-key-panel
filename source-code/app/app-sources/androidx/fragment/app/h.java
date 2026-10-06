package androidx.fragment.app;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import j.s0;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class h extends androidx.activity.h {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final i f322i;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f324k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f325l;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final androidx.lifecycle.n f323j = new androidx.lifecycle.n(this);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f326m = true;

    public h() {
        d.n nVar = (d.n) this;
        this.f322i = new i(new g(nVar), 1);
        this.f65e.f469b.b("android:support:fragments", new e(nVar));
        j(new f(nVar));
    }

    @Override // android.app.Activity
    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        super.dump(str, fileDescriptor, printWriter, strArr);
        printWriter.print(str);
        printWriter.print("Local FragmentActivity ");
        printWriter.print(Integer.toHexString(System.identityHashCode(this)));
        printWriter.println(" State:");
        String str2 = str + "  ";
        printWriter.print(str2);
        printWriter.print("mCreated=");
        printWriter.print(this.f324k);
        printWriter.print(" mResumed=");
        printWriter.print(this.f325l);
        printWriter.print(" mStopped=");
        printWriter.print(this.f326m);
        if (getApplication() != null) {
            m.k kVar = ((e0.a) new s0(e(), e0.a.f755c).a(e0.a.class)).f756b;
            if (kVar.f1639c > 0) {
                printWriter.print(str2);
                printWriter.println("Loaders:");
                if (kVar.f1639c > 0) {
                    androidx.activity.c.b(kVar.f1638b[0]);
                    printWriter.print(str2);
                    printWriter.print("  #");
                    printWriter.print(kVar.f1637a[0]);
                    printWriter.print(": ");
                    throw null;
                }
            }
        }
        r rVar = ((l) this.f322i.f327a).f336d;
        rVar.getClass();
        String str3 = str + "    ";
        w wVar = rVar.f349c;
        wVar.getClass();
        HashMap map = wVar.f399b;
        if (!map.isEmpty()) {
            printWriter.print(str);
            printWriter.println("Active Fragments:");
            Iterator it = map.values().iterator();
            while (it.hasNext()) {
                androidx.activity.c.b(it.next());
                printWriter.print(str);
                printWriter.println("null");
            }
        }
        ArrayList arrayList = wVar.f398a;
        int size2 = arrayList.size();
        if (size2 > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            if (size2 > 0) {
                androidx.activity.c.b(arrayList.get(0));
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(0);
                printWriter.print(": ");
                throw null;
            }
        }
        ArrayList arrayList2 = rVar.f350d;
        if (arrayList2 != null && (size = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i2 = 0; i2 < size; i2++) {
                a aVar = (a) rVar.f350d.get(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(aVar.toString());
                aVar.b(str3, printWriter, true);
            }
        }
        printWriter.print(str);
        printWriter.println("Back Stack Index: " + rVar.f354h.get());
        synchronized (rVar.f347a) {
            int size3 = rVar.f347a.size();
            if (size3 > 0) {
                printWriter.print(str);
                printWriter.println("Pending Actions:");
                for (int i3 = 0; i3 < size3; i3++) {
                    a aVar2 = (a) rVar.f347a.get(i3);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i3);
                    printWriter.print(": ");
                    printWriter.println(aVar2);
                }
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(rVar.f358l);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(rVar.f359m);
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(rVar.f357k);
        printWriter.print(" mStateSaved=");
        printWriter.print(rVar.f365s);
        printWriter.print(" mStopped=");
        printWriter.print(rVar.f366t);
        printWriter.print(" mDestroyed=");
        printWriter.println(rVar.f367u);
    }

    @Override // androidx.activity.h, android.app.Activity
    public final void onActivityResult(int i2, int i3, Intent intent) {
        this.f322i.a();
        super.onActivityResult(i2, i3, intent);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        i iVar = this.f322i;
        iVar.a();
        super.onConfigurationChanged(configuration);
        Iterator it = ((l) iVar.f327a).f336d.f349c.c().iterator();
        while (it.hasNext()) {
            androidx.activity.c.b(it.next());
        }
    }

    @Override // androidx.activity.h, o.d, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f323j.o0(androidx.lifecycle.g.ON_CREATE);
        r rVar = ((l) this.f322i.f327a).f336d;
        rVar.f365s = false;
        rVar.f366t = false;
        rVar.f371y.getClass();
        rVar.c(1);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean onCreatePanelMenu(int i2, Menu menu) {
        if (i2 != 0) {
            return super.onCreatePanelMenu(i2, menu);
        }
        boolean zOnCreatePanelMenu = super.onCreatePanelMenu(i2, menu);
        getMenuInflater();
        r rVar = ((l) this.f322i.f327a).f336d;
        if (rVar.f357k >= 1) {
            Iterator it = rVar.f349c.c().iterator();
            while (it.hasNext()) {
                androidx.activity.c.b(it.next());
            }
        }
        return zOnCreatePanelMenu | false;
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View viewOnCreateView = ((l) this.f322i.f327a).f336d.f351e.onCreateView(view, str, context, attributeSet);
        return viewOnCreateView == null ? super.onCreateView(view, str, context, attributeSet) : viewOnCreateView;
    }

    @Override // android.app.Activity
    public void onDestroy() {
        Integer num;
        Integer num2;
        Integer num3;
        super.onDestroy();
        r rVar = ((l) this.f322i.f327a).f336d;
        rVar.f367u = true;
        rVar.e(true);
        Iterator it = rVar.b().iterator();
        if (it.hasNext()) {
            ((z) it.next()).getClass();
            WeakHashMap weakHashMap = x.u.f2012a;
            throw null;
        }
        rVar.c(-1);
        rVar.f358l = null;
        rVar.f359m = null;
        if (rVar.f352f != null) {
            Iterator it2 = rVar.f353g.f341b.iterator();
            while (it2.hasNext()) {
                ((androidx.activity.a) it2.next()).cancel();
            }
            rVar.f352f = null;
        }
        androidx.activity.result.d dVar = rVar.f361o;
        if (dVar != null) {
            androidx.activity.d dVar2 = dVar.f77b;
            ArrayList arrayList = dVar2.f55e;
            String str = dVar.f76a;
            if (!arrayList.contains(str) && (num3 = (Integer) dVar2.f53c.remove(str)) != null) {
                dVar2.f52b.remove(num3);
            }
            dVar2.f56f.remove(str);
            HashMap map = dVar2.f57g;
            if (map.containsKey(str)) {
                Log.w("ActivityResultRegistry", "Dropping pending result for request " + str + ": " + map.get(str));
                map.remove(str);
            }
            Bundle bundle = dVar2.f58h;
            if (bundle.containsKey(str)) {
                Log.w("ActivityResultRegistry", "Dropping pending result for request " + str + ": " + bundle.getParcelable(str));
                bundle.remove(str);
            }
            androidx.activity.c.b(dVar2.f54d.get(str));
            androidx.activity.result.d dVar3 = rVar.f362p;
            androidx.activity.d dVar4 = dVar3.f77b;
            ArrayList arrayList2 = dVar4.f55e;
            String str2 = dVar3.f76a;
            if (!arrayList2.contains(str2) && (num2 = (Integer) dVar4.f53c.remove(str2)) != null) {
                dVar4.f52b.remove(num2);
            }
            dVar4.f56f.remove(str2);
            HashMap map2 = dVar4.f57g;
            if (map2.containsKey(str2)) {
                Log.w("ActivityResultRegistry", "Dropping pending result for request " + str2 + ": " + map2.get(str2));
                map2.remove(str2);
            }
            Bundle bundle2 = dVar4.f58h;
            if (bundle2.containsKey(str2)) {
                Log.w("ActivityResultRegistry", "Dropping pending result for request " + str2 + ": " + bundle2.getParcelable(str2));
                bundle2.remove(str2);
            }
            androidx.activity.c.b(dVar4.f54d.get(str2));
            androidx.activity.result.d dVar5 = rVar.f363q;
            androidx.activity.d dVar6 = dVar5.f77b;
            ArrayList arrayList3 = dVar6.f55e;
            String str3 = dVar5.f76a;
            if (!arrayList3.contains(str3) && (num = (Integer) dVar6.f53c.remove(str3)) != null) {
                dVar6.f52b.remove(num);
            }
            dVar6.f56f.remove(str3);
            HashMap map3 = dVar6.f57g;
            if (map3.containsKey(str3)) {
                Log.w("ActivityResultRegistry", "Dropping pending result for request " + str3 + ": " + map3.get(str3));
                map3.remove(str3);
            }
            Bundle bundle3 = dVar6.f58h;
            if (bundle3.containsKey(str3)) {
                Log.w("ActivityResultRegistry", "Dropping pending result for request " + str3 + ": " + bundle3.getParcelable(str3));
                bundle3.remove(str3);
            }
            androidx.activity.c.b(dVar6.f54d.get(str3));
        }
        this.f323j.o0(androidx.lifecycle.g.ON_DESTROY);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public final void onLowMemory() {
        super.onLowMemory();
        Iterator it = ((l) this.f322i.f327a).f336d.f349c.c().iterator();
        while (it.hasNext()) {
            androidx.activity.c.b(it.next());
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i2, MenuItem menuItem) {
        if (super.onMenuItemSelected(i2, menuItem)) {
            return true;
        }
        i iVar = this.f322i;
        if (i2 == 0) {
            r rVar = ((l) iVar.f327a).f336d;
            if (rVar.f357k >= 1) {
                Iterator it = rVar.f349c.c().iterator();
                while (it.hasNext()) {
                    androidx.activity.c.b(it.next());
                }
            }
            return false;
        }
        if (i2 != 6) {
            return false;
        }
        r rVar2 = ((l) iVar.f327a).f336d;
        if (rVar2.f357k >= 1) {
            Iterator it2 = rVar2.f349c.c().iterator();
            while (it2.hasNext()) {
                androidx.activity.c.b(it2.next());
            }
        }
        return false;
    }

    @Override // android.app.Activity
    public final void onMultiWindowModeChanged(boolean z2) {
        Iterator it = ((l) this.f322i.f327a).f336d.f349c.c().iterator();
        while (it.hasNext()) {
            androidx.activity.c.b(it.next());
        }
    }

    @Override // android.app.Activity
    public final void onNewIntent(Intent intent) {
        this.f322i.a();
        super.onNewIntent(intent);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i2, Menu menu) {
        if (i2 == 0) {
            r rVar = ((l) this.f322i.f327a).f336d;
            if (rVar.f357k >= 1) {
                Iterator it = rVar.f349c.c().iterator();
                while (it.hasNext()) {
                    androidx.activity.c.b(it.next());
                }
            }
        }
        super.onPanelClosed(i2, menu);
    }

    @Override // android.app.Activity
    public final void onPause() {
        super.onPause();
        this.f325l = false;
        ((l) this.f322i.f327a).f336d.c(5);
        this.f323j.o0(androidx.lifecycle.g.ON_PAUSE);
    }

    @Override // android.app.Activity
    public final void onPictureInPictureModeChanged(boolean z2) {
        Iterator it = ((l) this.f322i.f327a).f336d.f349c.c().iterator();
        while (it.hasNext()) {
            androidx.activity.c.b(it.next());
        }
    }

    @Override // android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        this.f323j.o0(androidx.lifecycle.g.ON_RESUME);
        r rVar = ((l) this.f322i.f327a).f336d;
        rVar.f365s = false;
        rVar.f366t = false;
        rVar.f371y.getClass();
        rVar.c(7);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean onPreparePanel(int i2, View view, Menu menu) {
        if (i2 != 0) {
            return super.onPreparePanel(i2, view, menu);
        }
        boolean zOnPreparePanel = super.onPreparePanel(0, view, menu);
        r rVar = ((l) this.f322i.f327a).f336d;
        if (rVar.f357k >= 1) {
            Iterator it = rVar.f349c.c().iterator();
            while (it.hasNext()) {
                androidx.activity.c.b(it.next());
            }
        }
        return false | zOnPreparePanel;
    }

    @Override // androidx.activity.h, android.app.Activity
    public final void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        this.f322i.a();
        super.onRequestPermissionsResult(i2, strArr, iArr);
    }

    @Override // android.app.Activity
    public void onResume() {
        i iVar = this.f322i;
        iVar.a();
        super.onResume();
        this.f325l = true;
        ((l) iVar.f327a).f336d.e(true);
    }

    @Override // android.app.Activity
    public void onStart() {
        i iVar = this.f322i;
        iVar.a();
        super.onStart();
        this.f326m = false;
        boolean z2 = this.f324k;
        Object obj = iVar.f327a;
        if (!z2) {
            this.f324k = true;
            r rVar = ((l) obj).f336d;
            rVar.f365s = false;
            rVar.f366t = false;
            rVar.f371y.getClass();
            rVar.c(4);
        }
        ((l) obj).f336d.e(true);
        this.f323j.o0(androidx.lifecycle.g.ON_START);
        r rVar2 = ((l) obj).f336d;
        rVar2.f365s = false;
        rVar2.f366t = false;
        rVar2.f371y.getClass();
        rVar2.c(5);
    }

    @Override // android.app.Activity
    public final void onStateNotSaved() {
        this.f322i.a();
    }

    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
        this.f326m = true;
        i iVar = this.f322i;
        Iterator it = ((l) iVar.f327a).f336d.f349c.c().iterator();
        while (it.hasNext()) {
            androidx.activity.c.b(it.next());
        }
        r rVar = ((l) iVar.f327a).f336d;
        rVar.f366t = true;
        rVar.f371y.getClass();
        rVar.c(4);
        this.f323j.o0(androidx.lifecycle.g.ON_STOP);
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory
    public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View viewOnCreateView = ((l) this.f322i.f327a).f336d.f351e.onCreateView(null, str, context, attributeSet);
        return viewOnCreateView == null ? super.onCreateView(str, context, attributeSet) : viewOnCreateView;
    }
}
