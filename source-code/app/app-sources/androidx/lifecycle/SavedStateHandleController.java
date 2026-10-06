package androidx.lifecycle;

/* JADX INFO: loaded from: classes.dex */
final class SavedStateHandleController implements j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f412a;

    /* JADX INFO: renamed from: androidx.lifecycle.SavedStateHandleController$1, reason: invalid class name */
    class AnonymousClass1 implements j {
        @Override // androidx.lifecycle.j
        public final void a(l lVar, g gVar) {
            if (gVar == g.ON_START) {
                throw null;
            }
        }
    }

    @Override // androidx.lifecycle.j
    public final void a(l lVar, g gVar) {
        if (gVar == g.ON_DESTROY) {
            this.f412a = false;
            lVar.h().X(this);
        }
    }
}
