package d;

import android.view.KeyEvent;

/* JADX INFO: loaded from: classes.dex */
public final class b0 implements x.e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ k f605b;

    public b0(k kVar) {
        this.f605b = kVar;
    }

    @Override // x.e
    public final boolean d(KeyEvent keyEvent) {
        return this.f605b.s(keyEvent);
    }
}
