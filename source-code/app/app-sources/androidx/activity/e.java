package androidx.activity;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class e implements androidx.savedstate.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f59a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f60b;

    public e(androidx.fragment.app.h hVar) {
        this.f59a = 0;
        this.f60b = hVar;
    }

    @Override // androidx.savedstate.b
    public final Bundle a() {
        int i2 = this.f59a;
        Object obj = this.f60b;
        switch (i2) {
            case 0:
                Bundle bundle = new Bundle();
                d dVar = ((h) obj).f68h;
                dVar.getClass();
                HashMap map = dVar.f53c;
                bundle.putIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS", new ArrayList<>(map.values()));
                bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS", new ArrayList<>(map.keySet()));
                bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS", new ArrayList<>(dVar.f55e));
                bundle.putBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT", (Bundle) dVar.f58h.clone());
                bundle.putSerializable("KEY_COMPONENT_ACTIVITY_RANDOM_OBJECT", dVar.f51a);
                return bundle;
            default:
                Bundle bundle2 = new Bundle();
                bundle2.putStringArrayList("classes_to_restore", new ArrayList<>((Set) obj));
                return bundle2;
        }
    }

    public e(androidx.savedstate.c cVar) {
        this.f59a = 1;
        this.f60b = new HashSet();
        cVar.b("androidx.savedstate.Restarter", this);
    }
}
