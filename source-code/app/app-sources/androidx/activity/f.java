package androidx.activity;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public final class f implements a.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ h f61a;

    public f(androidx.fragment.app.h hVar) {
        this.f61a = hVar;
    }

    @Override // a.b
    public final void a() {
        h hVar = this.f61a;
        Bundle bundleA = hVar.f65e.f469b.a("android:support:activity-result");
        if (bundleA != null) {
            d dVar = hVar.f68h;
            dVar.getClass();
            ArrayList<Integer> integerArrayList = bundleA.getIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS");
            ArrayList<String> stringArrayList = bundleA.getStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS");
            if (stringArrayList == null || integerArrayList == null) {
                return;
            }
            dVar.f55e = bundleA.getStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS");
            dVar.f51a = (Random) bundleA.getSerializable("KEY_COMPONENT_ACTIVITY_RANDOM_OBJECT");
            Bundle bundle = bundleA.getBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT");
            Bundle bundle2 = dVar.f58h;
            bundle2.putAll(bundle);
            for (int i2 = 0; i2 < stringArrayList.size(); i2++) {
                String str = stringArrayList.get(i2);
                HashMap map = dVar.f53c;
                boolean zContainsKey = map.containsKey(str);
                HashMap map2 = dVar.f52b;
                if (zContainsKey) {
                    Integer num = (Integer) map.remove(str);
                    if (!bundle2.containsKey(str)) {
                        map2.remove(num);
                    }
                }
                int iIntValue = integerArrayList.get(i2).intValue();
                String str2 = stringArrayList.get(i2);
                map2.put(Integer.valueOf(iIntValue), str2);
                map.put(str2, Integer.valueOf(iIntValue));
            }
        }
    }
}
