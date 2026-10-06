package o0;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

/* JADX INFO: loaded from: classes.dex */
public final class c implements View.OnClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f1758b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ d f1759c;

    public /* synthetic */ c(d dVar, int i2) {
        this.f1758b = i2;
        this.f1759c = dVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        Intent intent;
        switch (this.f1758b) {
            case 0:
                boolean z2 = this.f1759c.f1761b.f1780b;
                if (!z2) {
                    if (!this.f1759c.f1761b.b()) {
                        Toast.makeText(this.f1759c.f1760a, "Please save your UPI PIN first", 0).show();
                    } else {
                        if (Build.VERSION.SDK_INT >= 23 && !Settings.canDrawOverlays(this.f1759c.f1760a)) {
                            Toast.makeText(this.f1759c.f1760a, "Please enable Display Over Apps permission first", 1).show();
                            intent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + this.f1759c.f1760a.getPackageName()));
                            intent.addFlags(268435456);
                        } else if (!androidx.lifecycle.i.J(this.f1759c.f1760a)) {
                            intent = new Intent("android.settings.ACCESSIBILITY_SETTINGS");
                        }
                        this.f1759c.f1760a.startActivity(intent);
                    }
                }
                this.f1759c.c(!z2);
                break;
            case 1:
                Intent launchIntentForPackage = this.f1759c.f1760a.getPackageManager().getLaunchIntentForPackage("com.phonepe.app");
                try {
                    launchIntentForPackage.addFlags(268435456);
                    this.f1759c.f1760a.startActivity(launchIntentForPackage);
                    if ("QR_SCANNER".equals(this.f1759c.f1761b.f1782d)) {
                        LocalBroadcastManager.getInstance(this.f1759c.f1760a).sendBroadcast(new Intent("com.snapay.app.OPEN_SCANNER_REQUESTED"));
                    }
                } catch (Exception unused) {
                    Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.phonepe.app"));
                    intent2.addFlags(268435456);
                    this.f1759c.f1760a.startActivity(intent2);
                }
                break;
            default:
                d dVar = this.f1759c;
                dVar.f1761b.c();
                dVar.e();
                break;
        }
    }
}
