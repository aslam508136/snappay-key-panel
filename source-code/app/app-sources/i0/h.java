package i0;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class h extends i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ i f1141a;

    public h(i iVar) {
        this.f1141a = iVar;
    }

    @Override // i0.i
    public final Object read(l0.a aVar) throws IOException {
        if (aVar.t() != l0.b.NULL) {
            return this.f1141a.read(aVar);
        }
        aVar.p();
        return null;
    }

    @Override // i0.i
    public final void write(l0.c cVar, Object obj) throws IOException {
        if (obj == null) {
            cVar.h();
        } else {
            this.f1141a.write(cVar, obj);
        }
    }
}
