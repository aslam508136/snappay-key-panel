package m0;

import android.content.Intent;
import android.os.Handler;
import com.snapay.app.LoginActivity;
import com.snapay.app.MainActivity;
import com.snapay.app.SplashActivity;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class o implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1681a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ SplashActivity f1682b;

    public /* synthetic */ o(SplashActivity splashActivity, int i2) {
        this.f1681a = i2;
        this.f1682b = splashActivity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i2 = this.f1681a;
        SplashActivity splashActivity = this.f1682b;
        switch (i2) {
            case 0:
                int i3 = SplashActivity.f568v;
                splashActivity.getClass();
                splashActivity.startActivity(new Intent(splashActivity, (Class<?>) LoginActivity.class));
                splashActivity.finish();
                break;
            case 1:
                int i4 = SplashActivity.f568v;
                if (!splashActivity.isFinishing()) {
                    splashActivity.f569o.setText("✓ Access Granted!");
                    new Handler().postDelayed(new o(splashActivity, 3), 500L);
                }
                break;
            case 2:
                int i5 = SplashActivity.f568v;
                if (!splashActivity.isFinishing()) {
                    splashActivity.startActivity(new Intent(splashActivity, (Class<?>) LoginActivity.class));
                    splashActivity.finish();
                }
                break;
            default:
                int i6 = SplashActivity.f568v;
                if (!splashActivity.isFinishing()) {
                    splashActivity.startActivity(new Intent(splashActivity, (Class<?>) MainActivity.class));
                    splashActivity.finish();
                }
                break;
        }
    }
}
