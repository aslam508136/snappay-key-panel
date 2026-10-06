package androidx.activity;

import androidx.fragment.app.o;
import androidx.lifecycle.l;
import java.util.ArrayDeque;

/* JADX INFO: loaded from: classes.dex */
class OnBackPressedDispatcher$LifecycleOnBackPressedCancellable implements androidx.lifecycle.j, a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final androidx.lifecycle.i f45a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final o f46b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public i f47c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ j f48d;

    public OnBackPressedDispatcher$LifecycleOnBackPressedCancellable(j jVar, androidx.lifecycle.i iVar, o oVar) {
        this.f48d = jVar;
        this.f45a = iVar;
        this.f46b = oVar;
        iVar.b(this);
    }

    @Override // androidx.lifecycle.j
    public final void a(l lVar, androidx.lifecycle.g gVar) {
        if (gVar == androidx.lifecycle.g.ON_START) {
            j jVar = this.f48d;
            ArrayDeque arrayDeque = jVar.f72b;
            o oVar = this.f46b;
            arrayDeque.add(oVar);
            i iVar = new i(jVar, oVar);
            oVar.f341b.add(iVar);
            this.f47c = iVar;
            return;
        }
        if (gVar != androidx.lifecycle.g.ON_STOP) {
            if (gVar == androidx.lifecycle.g.ON_DESTROY) {
                cancel();
            }
        } else {
            i iVar2 = this.f47c;
            if (iVar2 != null) {
                iVar2.cancel();
            }
        }
    }

    @Override // androidx.activity.a
    public final void cancel() {
        this.f45a.X(this);
        this.f46b.f341b.remove(this);
        i iVar = this.f47c;
        if (iVar != null) {
            iVar.cancel();
            this.f47c = null;
        }
    }
}
