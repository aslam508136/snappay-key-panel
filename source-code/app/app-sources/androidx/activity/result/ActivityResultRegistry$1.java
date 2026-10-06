package androidx.activity.result;

import androidx.lifecycle.g;
import androidx.lifecycle.j;
import androidx.lifecycle.l;

/* JADX INFO: loaded from: classes.dex */
class ActivityResultRegistry$1 implements j {
    @Override // androidx.lifecycle.j
    public final void a(l lVar, g gVar) {
        if (g.ON_START.equals(gVar) || g.ON_STOP.equals(gVar) || g.ON_DESTROY.equals(gVar)) {
            throw null;
        }
    }
}
