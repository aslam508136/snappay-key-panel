package m0;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import com.snapay.app.MainActivity;
import com.snapay.app.R;

/* JADX INFO: loaded from: classes.dex */
public final class k implements View.OnClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f1674b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ MainActivity f1675c;

    public /* synthetic */ k(MainActivity mainActivity, int i2) {
        this.f1674b = i2;
        this.f1675c = mainActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i2 = this.f1674b;
        MainActivity mainActivity = this.f1675c;
        switch (i2) {
            case 0:
                mainActivity.startActivity(new Intent("android.settings.ACCESSIBILITY_SETTINGS"));
                break;
            case 1:
                mainActivity.startActivity(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + mainActivity.getPackageName())));
                break;
            default:
                int i3 = MainActivity.s0;
                mainActivity.getClass();
                mainActivity.f552p.setText("PHONEPE");
                mainActivity.f550o.setBackgroundResource(R.drawable.btn_phonepe_selected);
                mainActivity.f550o.setTextColor(Color.parseColor("#FF0000"));
                mainActivity.m();
                break;
        }
    }
}
