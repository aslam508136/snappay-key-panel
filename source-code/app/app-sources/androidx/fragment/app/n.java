package androidx.fragment.app;

import android.util.Log;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class n implements androidx.activity.result.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f338a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ r f339b;

    public /* synthetic */ n(r rVar, int i2) {
        this.f338a = i2;
        this.f339b = rVar;
    }

    public final void a() {
        StringBuilder sb;
        StringBuilder sb2;
        int i2 = this.f338a;
        r rVar = this.f339b;
        switch (i2) {
            case 0:
                q qVar = (q) rVar.f364r.pollFirst();
                if (qVar == null) {
                    sb2 = new StringBuilder("No IntentSenders were started for ");
                    sb2.append(this);
                } else {
                    rVar.f349c.a();
                    StringBuilder sb3 = new StringBuilder("Intent Sender result delivered for unknown Fragment ");
                    sb3.append(qVar.f345a);
                    sb2 = sb3;
                }
                Log.w("FragmentManager", sb2.toString());
                break;
            default:
                q qVar2 = (q) rVar.f364r.pollFirst();
                if (qVar2 == null) {
                    sb = new StringBuilder("No Activities were started for result for ");
                    sb.append(this);
                } else {
                    rVar.f349c.a();
                    StringBuilder sb4 = new StringBuilder("Activity result delivered for unknown Fragment ");
                    sb4.append(qVar2.f345a);
                    sb = sb4;
                }
                Log.w("FragmentManager", sb.toString());
                break;
        }
    }

    public final void b(Object obj) {
        StringBuilder sb;
        switch (this.f338a) {
            case 0:
                a();
                break;
            case 1:
                Map map = (Map) obj;
                ArrayList arrayList = new ArrayList(map.values());
                int[] iArr = new int[arrayList.size()];
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    iArr[i2] = ((Boolean) arrayList.get(i2)).booleanValue() ? 0 : -1;
                }
                r rVar = this.f339b;
                q qVar = (q) rVar.f364r.pollFirst();
                if (qVar == null) {
                    sb = new StringBuilder("No permissions were requested for ");
                    sb.append(this);
                } else {
                    rVar.f349c.a();
                    sb = new StringBuilder("Permission request result delivered for unknown Fragment ");
                    sb.append(qVar.f345a);
                }
                Log.w("FragmentManager", sb.toString());
                break;
            default:
                a();
                break;
        }
    }
}
