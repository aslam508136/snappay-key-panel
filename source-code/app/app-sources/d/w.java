package d;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import com.snapay.app.SnapPayAccessibilityService;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class w extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f727a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f728b;

    public /* synthetic */ w(Object obj, int i2) {
        this.f727a = i2;
        this.f728b = obj;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        Handler handler;
        m0.g gVar;
        long j2;
        int i2 = this.f727a;
        Object obj = this.f728b;
        switch (i2) {
            case 0:
                ((x) obj).d();
                break;
            case 1:
                String action = intent.getAction();
                if ("com.snapay.app.AUTOPAY_ACTIVATED".equals(action)) {
                    Iterator it = ((SnapPayAccessibilityService) obj).f566c.values().iterator();
                    while (it.hasNext()) {
                        ((n0.a) it.next()).b();
                    }
                } else if ("com.snapay.app.AUTOPAY_DEACTIVATED".equals(action)) {
                    Iterator it2 = ((SnapPayAccessibilityService) obj).f566c.values().iterator();
                    while (it2.hasNext()) {
                        ((n0.a) it2.next()).c();
                    }
                }
                break;
            default:
                String stringExtra = intent.getStringExtra("status");
                if (stringExtra != null) {
                    o0.d dVar = (o0.d) obj;
                    if (stringExtra.startsWith("ERROR:")) {
                        dVar.b(stringExtra.substring(6), "#FF0000");
                        handler = dVar.f1772m;
                        gVar = dVar.f1773n;
                        handler.removeCallbacks(gVar);
                        j2 = 5000;
                    } else {
                        dVar.b(stringExtra, "#FFA726");
                        handler = dVar.f1772m;
                        gVar = dVar.f1773n;
                        handler.removeCallbacks(gVar);
                        j2 = 3000;
                    }
                    handler.postDelayed(gVar, j2);
                }
                break;
        }
    }
}
