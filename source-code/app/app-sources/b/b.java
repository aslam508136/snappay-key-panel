package b;

import android.content.Intent;
import java.util.Collections;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class b extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f471a;

    @Override // b.a
    public final Object e(Intent intent, int i2) {
        int i3 = this.f471a;
        switch (i3) {
            case 0:
                if (i2 == -1 && intent != null) {
                    String[] stringArrayExtra = intent.getStringArrayExtra("androidx.activity.result.contract.extra.PERMISSIONS");
                    int[] intArrayExtra = intent.getIntArrayExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS");
                    if (intArrayExtra != null && stringArrayExtra != null) {
                        HashMap map = new HashMap();
                        int length = stringArrayExtra.length;
                        for (int i4 = 0; i4 < length; i4++) {
                            map.put(stringArrayExtra[i4], Boolean.valueOf(intArrayExtra[i4] == 0));
                        }
                        return map;
                    }
                }
                return Collections.emptyMap();
            case 1:
                switch (i3) {
                    case 1:
                        return new androidx.activity.result.b(intent, i2);
                    default:
                        return new androidx.activity.result.b(intent, i2);
                }
            default:
                switch (i3) {
                    case 1:
                        return new androidx.activity.result.b(intent, i2);
                    default:
                        return new androidx.activity.result.b(intent, i2);
                }
        }
    }
}
