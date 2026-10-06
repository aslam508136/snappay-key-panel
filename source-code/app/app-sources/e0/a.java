package e0;

import androidx.activity.c;
import androidx.lifecycle.u;
import j.o;
import m.k;

/* JADX INFO: loaded from: classes.dex */
public final class a extends u {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final o f755c = new o(2);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final k f756b = new k();

    @Override // androidx.lifecycle.u
    public final void a() {
        k kVar = this.f756b;
        int i2 = kVar.f1639c;
        if (i2 > 0) {
            c.b(kVar.f1638b[0]);
            throw null;
        }
        Object[] objArr = kVar.f1638b;
        for (int i3 = 0; i3 < i2; i3++) {
            objArr[i3] = null;
        }
        kVar.f1639c = 0;
    }
}
