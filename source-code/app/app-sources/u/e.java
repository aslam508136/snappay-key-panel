package u;

import j.s0;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class e implements w.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1937a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f1938b;

    public /* synthetic */ e(Object obj, int i2) {
        this.f1937a = i2;
        this.f1938b = obj;
    }

    public final /* bridge */ /* synthetic */ void a(Object obj) {
        switch (this.f1937a) {
            case 0:
                b((f) obj);
                break;
            default:
                b((f) obj);
                break;
        }
    }

    public final void b(f fVar) {
        switch (this.f1937a) {
            case 0:
                ((s0) this.f1938b).c(fVar);
                return;
            default:
                synchronized (g.f1943c) {
                    m.j jVar = g.f1944d;
                    ArrayList arrayList = (ArrayList) jVar.getOrDefault((String) this.f1938b, null);
                    if (arrayList == null) {
                        return;
                    }
                    jVar.remove((String) this.f1938b);
                    for (int i2 = 0; i2 < arrayList.size(); i2++) {
                        ((e) ((w.a) arrayList.get(i2))).a(fVar);
                    }
                    return;
                }
        }
    }
}
