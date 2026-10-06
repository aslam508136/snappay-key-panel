package m0;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.snapay.app.MainActivity;
import j.d3;

/* JADX INFO: loaded from: classes.dex */
public final class l extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1676a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ MainActivity f1677b;

    public /* synthetic */ l(MainActivity mainActivity, int i2) {
        this.f1676a = i2;
        this.f1677b = mainActivity;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        int i2 = this.f1676a;
        MainActivity mainActivity = this.f1677b;
        switch (i2) {
            case 0:
                String action = intent.getAction();
                if ("com.snapay.app.AUTOPAY_DEACTIVATED".equals(action) || "com.snapay.app.AUTOPAY_ACTIVATED".equals(action) || "com.snapay.app.PAYMENT_STATS_UPDATED".equals(action)) {
                    d3 d3Var = mainActivity.f554q;
                    if (d3Var != null) {
                        d3Var.a(mainActivity.T, mainActivity.U, mainActivity.W, mainActivity.X, mainActivity.Y, mainActivity.Z, mainActivity.f537a0, mainActivity.f538b0, mainActivity.f539c0, mainActivity.V);
                    }
                } else if ("com.snapay.app.CLOSE_APP".equals(action)) {
                    mainActivity.finishAndRemoveTask();
                }
                break;
            default:
                int i3 = MainActivity.s0;
                mainActivity.q();
                break;
        }
    }
}
