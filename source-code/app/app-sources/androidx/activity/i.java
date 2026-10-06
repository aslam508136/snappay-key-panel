package androidx.activity;

import androidx.fragment.app.o;
import java.util.ArrayDeque;

/* JADX INFO: loaded from: classes.dex */
public final class i implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final o f69a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ j f70b;

    public i(j jVar, o oVar) {
        this.f70b = jVar;
        this.f69a = oVar;
    }

    @Override // androidx.activity.a
    public final void cancel() {
        ArrayDeque arrayDeque = this.f70b.f72b;
        o oVar = this.f69a;
        arrayDeque.remove(oVar);
        oVar.f341b.remove(this);
    }
}
