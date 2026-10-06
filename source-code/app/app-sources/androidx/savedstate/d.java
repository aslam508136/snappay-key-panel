package androidx.savedstate;

import android.os.Bundle;
import androidx.fragment.app.h;
import androidx.lifecycle.g;
import androidx.lifecycle.j;
import androidx.lifecycle.l;
import androidx.lifecycle.n;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e f468a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c f469b = new c();

    public d(h hVar) {
        this.f468a = hVar;
    }

    public final void a(Bundle bundle) {
        e eVar = this.f468a;
        n nVar = ((androidx.activity.h) eVar).f64d;
        if (nVar.Q != androidx.lifecycle.h.INITIALIZED) {
            throw new IllegalStateException("Restarter must be created only during owner's initialization stage");
        }
        nVar.b(new Recreator(eVar));
        final c cVar = this.f469b;
        if (cVar.f465c) {
            throw new IllegalStateException("SavedStateRegistry was already restored.");
        }
        if (bundle != null) {
            cVar.f464b = bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key");
        }
        nVar.b(new j() { // from class: androidx.savedstate.SavedStateRegistry$1
            @Override // androidx.lifecycle.j
            public final void a(l lVar, g gVar) {
                boolean z2;
                if (gVar == g.ON_START) {
                    z2 = true;
                } else if (gVar != g.ON_STOP) {
                    return;
                } else {
                    z2 = false;
                }
                cVar.f467e = z2;
            }
        });
        cVar.f465c = true;
    }
}
