package androidx.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.n;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Random f51a = new Random();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashMap f52b = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final HashMap f53c = new HashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final HashMap f54d = new HashMap();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ArrayList f55e = new ArrayList();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final transient HashMap f56f = new HashMap();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final HashMap f57g = new HashMap();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Bundle f58h = new Bundle();

    public final boolean a(int i2, int i3, Intent intent) {
        androidx.activity.result.c cVar;
        String str = (String) this.f52b.get(Integer.valueOf(i2));
        if (str == null) {
            return false;
        }
        this.f55e.remove(str);
        androidx.activity.result.e eVar = (androidx.activity.result.e) this.f56f.get(str);
        if (eVar == null || (cVar = eVar.f78a) == null) {
            this.f57g.remove(str);
            this.f58h.putParcelable(str, new androidx.activity.result.b(intent, i3));
        } else {
            ((n) cVar).b(eVar.f79b.e(intent, i3));
        }
        return true;
    }

    public final androidx.activity.result.d b(String str, b.b bVar, n nVar) {
        int iNextInt;
        HashMap map;
        HashMap map2 = this.f53c;
        Integer num = (Integer) map2.get(str);
        if (num != null) {
            num.intValue();
        } else {
            do {
                iNextInt = this.f51a.nextInt(2147418112) + 65536;
                map = this.f52b;
            } while (map.containsKey(Integer.valueOf(iNextInt)));
            map.put(Integer.valueOf(iNextInt), str);
            map2.put(str, Integer.valueOf(iNextInt));
        }
        this.f56f.put(str, new androidx.activity.result.e(nVar, bVar));
        HashMap map3 = this.f57g;
        if (map3.containsKey(str)) {
            Object obj = map3.get(str);
            map3.remove(str);
            nVar.b(obj);
        }
        Bundle bundle = this.f58h;
        androidx.activity.result.b bVar2 = (androidx.activity.result.b) bundle.getParcelable(str);
        if (bVar2 != null) {
            bundle.remove(str);
            nVar.b(bVar.e(bVar2.f75b, bVar2.f74a));
        }
        return new androidx.activity.result.d(this, str);
    }
}
