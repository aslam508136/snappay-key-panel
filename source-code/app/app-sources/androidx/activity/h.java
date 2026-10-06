package androidx.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Build;
import android.os.Bundle;
import android.os.Trace;
import android.view.View;
import android.view.Window;
import androidx.lifecycle.l;
import androidx.lifecycle.n;
import androidx.lifecycle.s;
import androidx.lifecycle.u;
import androidx.lifecycle.w;
import androidx.lifecycle.x;
import com.snapay.app.R;
import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public abstract class h extends o.d implements x, androidx.savedstate.e, k, androidx.activity.result.f {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final a.a f63c = new a.a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final n f64d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final androidx.savedstate.d f65e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public w f66f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final j f67g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final d f68h;

    public h() {
        n nVar = new n(this);
        this.f64d = nVar;
        final androidx.fragment.app.h hVar = (androidx.fragment.app.h) this;
        androidx.savedstate.d dVar = new androidx.savedstate.d(hVar);
        this.f65e = dVar;
        this.f67g = new j(new b(this, 0));
        new AtomicInteger();
        this.f68h = new d();
        int i2 = Build.VERSION.SDK_INT;
        nVar.b(new androidx.lifecycle.j() { // from class: androidx.activity.ComponentActivity$3
            @Override // androidx.lifecycle.j
            public final void a(l lVar, androidx.lifecycle.g gVar) {
                if (gVar == androidx.lifecycle.g.ON_STOP) {
                    Window window = hVar.getWindow();
                    View viewPeekDecorView = window != null ? window.peekDecorView() : null;
                    if (viewPeekDecorView != null) {
                        viewPeekDecorView.cancelPendingInputEvents();
                    }
                }
            }
        });
        nVar.b(new androidx.lifecycle.j() { // from class: androidx.activity.ComponentActivity$4
            /* JADX WARN: Bottom block not found for handler: all -> 0x005b */
            @Override // androidx.lifecycle.j
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final void a(l lVar, androidx.lifecycle.g gVar) {
                if (gVar == androidx.lifecycle.g.ON_DESTROY) {
                    hVar.f63c.f1b = null;
                    if (hVar.isChangingConfigurations()) {
                        return;
                    }
                    w wVarE = hVar.e();
                    for (u uVar : wVarE.f460a.values()) {
                        HashMap map = uVar.f459a;
                        if (map != null) {
                            synchronized (map) {
                                for (Object obj : uVar.f459a.values()) {
                                    if (obj instanceof Closeable) {
                                        try {
                                            ((Closeable) obj).close();
                                        } catch (IOException e2) {
                                            throw new RuntimeException(e2);
                                        }
                                    }
                                }
                            }
                        }
                        uVar.a();
                    }
                    wVarE.f460a.clear();
                }
            }
        });
        nVar.b(new androidx.lifecycle.j() { // from class: androidx.activity.ComponentActivity$5
            @Override // androidx.lifecycle.j
            public final void a(l lVar, androidx.lifecycle.g gVar) {
                h hVar2 = hVar;
                if (hVar2.f66f == null) {
                    g gVar2 = (g) hVar2.getLastNonConfigurationInstance();
                    if (gVar2 != null) {
                        hVar2.f66f = gVar2.f62a;
                    }
                    if (hVar2.f66f == null) {
                        hVar2.f66f = new w();
                    }
                }
                hVar2.f64d.X(this);
            }
        });
        if (i2 <= 23) {
            nVar.b(new ImmLeaksCleaner(hVar));
        }
        dVar.f469b.b("android:support:activity-result", new e(hVar));
        j(new f(hVar));
    }

    @Override // androidx.activity.k
    public final j a() {
        return this.f67g;
    }

    @Override // androidx.activity.result.f
    public final d c() {
        return this.f68h;
    }

    @Override // androidx.lifecycle.x
    public final w e() {
        if (getApplication() == null) {
            throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
        }
        if (this.f66f == null) {
            g gVar = (g) getLastNonConfigurationInstance();
            if (gVar != null) {
                this.f66f = gVar.f62a;
            }
            if (this.f66f == null) {
                this.f66f = new w();
            }
        }
        return this.f66f;
    }

    @Override // androidx.lifecycle.l
    public final n h() {
        return this.f64d;
    }

    public final void j(a.b bVar) {
        a.a aVar = this.f63c;
        if (aVar.f1b != null) {
            bVar.a();
        }
        aVar.f0a.add(bVar);
    }

    @Override // android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        if (this.f68h.a(i2, i3, intent)) {
            return;
        }
        super.onActivityResult(i2, i3, intent);
    }

    @Override // android.app.Activity
    public final void onBackPressed() {
        this.f67g.b();
    }

    @Override // o.d, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.f65e.a(bundle);
        a.a aVar = this.f63c;
        aVar.f1b = this;
        Iterator it = aVar.f0a.iterator();
        while (it.hasNext()) {
            ((a.b) it.next()).a();
        }
        super.onCreate(bundle);
        s.c(this);
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        if (this.f68h.a(i2, -1, new Intent().putExtra("androidx.activity.result.contract.extra.PERMISSIONS", strArr).putExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS", iArr)) || Build.VERSION.SDK_INT < 23) {
            return;
        }
        super.onRequestPermissionsResult(i2, strArr, iArr);
    }

    @Override // android.app.Activity
    public final Object onRetainNonConfigurationInstance() {
        g gVar;
        w wVar = this.f66f;
        if (wVar == null && (gVar = (g) getLastNonConfigurationInstance()) != null) {
            wVar = gVar.f62a;
        }
        if (wVar == null) {
            return null;
        }
        g gVar2 = new g();
        gVar2.f62a = wVar;
        return gVar2;
    }

    @Override // o.d, android.app.Activity
    public final void onSaveInstanceState(Bundle bundle) {
        n nVar = this.f64d;
        if (nVar instanceof n) {
            androidx.lifecycle.h hVar = androidx.lifecycle.h.CREATED;
            nVar.n0("setCurrentState");
            nVar.p0(hVar);
        }
        super.onSaveInstanceState(bundle);
        androidx.savedstate.c cVar = this.f65e.f469b;
        cVar.getClass();
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = cVar.f464b;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        l.g gVar = cVar.f463a;
        gVar.getClass();
        l.d dVar = new l.d(gVar);
        gVar.f1558c.put(dVar, Boolean.FALSE);
        while (dVar.hasNext()) {
            Map.Entry entry = (Map.Entry) dVar.next();
            bundle2.putBundle((String) entry.getKey(), ((androidx.savedstate.b) entry.getValue()).a());
        }
        bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle2);
    }

    @Override // android.app.Activity
    public final void reportFullyDrawn() {
        try {
            if (androidx.lifecycle.i.L()) {
                Trace.beginSection("reportFullyDrawn() for " + getComponentName());
            }
            super.reportFullyDrawn();
        } finally {
            Trace.endSection();
        }
    }

    @Override // android.app.Activity
    public void setContentView(@SuppressLint({"UnknownNullness", "MissingNullability"}) View view) {
        getWindow().getDecorView().setTag(R.id.view_tree_lifecycle_owner, this);
        getWindow().getDecorView().setTag(R.id.view_tree_view_model_store_owner, this);
        getWindow().getDecorView().setTag(R.id.view_tree_saved_state_registry_owner, this);
        super.setContentView(view);
    }

    @Override // android.app.Activity
    public final void startActivityForResult(Intent intent, int i2) {
        super.startActivityForResult(intent, i2);
    }

    @Override // android.app.Activity
    public final void startIntentSenderForResult(IntentSender intentSender, int i2, Intent intent, int i3, int i4, int i5) throws IntentSender.SendIntentException {
        super.startIntentSenderForResult(intentSender, i2, intent, i3, i4, i5);
    }

    @Override // android.app.Activity
    public final void startActivityForResult(Intent intent, int i2, Bundle bundle) {
        super.startActivityForResult(intent, i2, bundle);
    }

    @Override // android.app.Activity
    public final void startIntentSenderForResult(IntentSender intentSender, int i2, Intent intent, int i3, int i4, int i5, Bundle bundle) throws IntentSender.SendIntentException {
        super.startIntentSenderForResult(intentSender, i2, intent, i3, i4, i5, bundle);
    }
}
