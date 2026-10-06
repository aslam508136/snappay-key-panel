package m0;

import android.content.SharedPreferences;
import android.os.Handler;
import com.snapay.app.SplashActivity;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class p implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1683a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ SplashActivity f1684b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ String f1685c;

    public /* synthetic */ p(SplashActivity splashActivity, String str, int i2) {
        this.f1683a = i2;
        this.f1684b = splashActivity;
        this.f1685c = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        p pVar;
        Runnable oVar;
        int i2 = this.f1683a;
        int i3 = 2;
        SplashActivity splashActivity = this.f1684b;
        String str = this.f1685c;
        switch (i2) {
            case 0:
                int i4 = SplashActivity.f568v;
                splashActivity.getClass();
                try {
                    int i5 = 1;
                    if (splashActivity.o()) {
                        JSONObject jSONObjectP = splashActivity.p(str, splashActivity.n());
                        boolean z2 = jSONObjectP.getBoolean("valid");
                        String strOptString = jSONObjectP.optString("status", "");
                        if (z2 || "active".equals(strOptString) || "activated".equals(strOptString)) {
                            String strOptString2 = jSONObjectP.optString("expiresAt", "");
                            SharedPreferences.Editor editorEdit = m.a(splashActivity).edit();
                            editorEdit.putString("expires_at", strOptString2);
                            editorEdit.apply();
                            oVar = new o(splashActivity, 1);
                        } else {
                            oVar = new p(splashActivity, strOptString, i3);
                        }
                    } else {
                        oVar = new p(splashActivity, str, i5);
                    }
                    splashActivity.runOnUiThread(oVar);
                } catch (SocketTimeoutException | UnknownHostException unused) {
                    pVar = new p(splashActivity, str, 3);
                    splashActivity.runOnUiThread(pVar);
                    return;
                } catch (Exception unused2) {
                    pVar = new p(splashActivity, str, 4);
                    splashActivity.runOnUiThread(pVar);
                    return;
                }
                break;
            case 1:
                int i6 = SplashActivity.f568v;
                if (!splashActivity.isFinishing()) {
                    splashActivity.f569o.setText("Waiting for internet...");
                    Handler handler = splashActivity.f574t;
                    if (handler != null) {
                        handler.postDelayed(new p(splashActivity, str, 6), 3000L);
                    }
                }
                break;
            case 2:
                int i7 = SplashActivity.f568v;
                if (!splashActivity.isFinishing()) {
                    m.a(splashActivity).edit().clear().apply();
                    splashActivity.f569o.setText(("expired".equals(str) || "revoked".equals(str)) ? "✗ Subscription Expired" : "✗ Invalid Key");
                    new Handler().postDelayed(new o(splashActivity, 2), 800L);
                }
                break;
            case 3:
                int i8 = SplashActivity.f568v;
                if (!splashActivity.isFinishing()) {
                    splashActivity.f569o.setText("Connecting...");
                    Handler handler2 = splashActivity.f574t;
                    if (handler2 != null) {
                        handler2.postDelayed(new p(splashActivity, str, 5), 3000L);
                    }
                }
                break;
            case 4:
                int i9 = SplashActivity.f568v;
                if (!splashActivity.isFinishing()) {
                    splashActivity.f569o.setText("Connecting...");
                    Handler handler3 = splashActivity.f574t;
                    if (handler3 != null) {
                        handler3.postDelayed(new p(splashActivity, str, 7), 3000L);
                    }
                }
                break;
            case 5:
                int i10 = SplashActivity.f568v;
                if (!splashActivity.isFinishing()) {
                    splashActivity.m(str);
                }
                break;
            case 6:
                int i11 = SplashActivity.f568v;
                if (!splashActivity.isFinishing()) {
                    splashActivity.m(str);
                }
                break;
            default:
                int i12 = SplashActivity.f568v;
                if (!splashActivity.isFinishing()) {
                    splashActivity.m(str);
                }
                break;
        }
    }
}
