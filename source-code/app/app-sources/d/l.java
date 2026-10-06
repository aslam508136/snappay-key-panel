package d;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public final class l implements androidx.savedstate.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ n f709a;

    public l(n nVar) {
        this.f709a = nVar;
    }

    @Override // androidx.savedstate.b
    public final Bundle a() {
        Bundle bundle = new Bundle();
        this.f709a.k().getClass();
        return bundle;
    }
}
