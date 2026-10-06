package m0;

import android.accessibilityservice.AccessibilityService;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.snapay.app.MainActivity;
import com.snapay.app.SnapPayAccessibilityService;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class j implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1670a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f1671b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f1672c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f1673d;

    public /* synthetic */ j(Object obj, Object obj2, Object obj3, int i2) {
        this.f1670a = i2;
        this.f1671b = obj;
        this.f1672c = obj2;
        this.f1673d = obj3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public final void run() {
        AccessibilityService accessibilityService;
        h hVar;
        AccessibilityService accessibilityService2;
        AccessibilityService accessibilityService3;
        AccessibilityService accessibilityService4;
        switch (this.f1670a) {
            case 0:
                MainActivity mainActivity = (MainActivity) this.f1671b;
                String str = (String) this.f1672c;
                SharedPreferences sharedPreferences = (SharedPreferences) this.f1673d;
                int i2 = MainActivity.s0;
                mainActivity.getClass();
                try {
                    JSONObject jSONObjectT = MainActivity.t(str);
                    if (jSONObjectT.getBoolean("valid")) {
                        sharedPreferences.edit().putString("expires_at", jSONObjectT.optString("expiresAt", "")).apply();
                        hVar = new h(mainActivity, 2);
                    } else {
                        hVar = new h(mainActivity, 1);
                    }
                    mainActivity.runOnUiThread(hVar);
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            case 1:
                TextView textView = (TextView) this.f1671b;
                Button button = (Button) this.f1672c;
                ProgressBar progressBar = (ProgressBar) this.f1673d;
                textView.setText("Download failed. Tap Retry.");
                button.setEnabled(true);
                button.setText("↺ Retry");
                progressBar.setVisibility(8);
                return;
            case 2:
                p0.b bVar = (p0.b) this.f1671b;
                String str2 = (String) this.f1672c;
                Runnable runnable = (Runnable) this.f1673d;
                bVar.getClass();
                try {
                    bVar.n(str2, runnable);
                    AccessibilityService accessibilityService5 = bVar.f1806b;
                    boolean z2 = accessibilityService5 instanceof SnapPayAccessibilityService;
                    accessibilityService2 = accessibilityService5;
                    bVar = accessibilityService5;
                    if (z2) {
                        bVar = (SnapPayAccessibilityService) accessibilityService2;
                    }
                } catch (Exception e3) {
                    bVar.h("fillPin thread error: " + e3.getMessage());
                    boolean z3 = bVar.f1806b instanceof SnapPayAccessibilityService;
                    bVar = bVar;
                    if (z3) {
                        accessibilityService2 = bVar.f1806b;
                    }
                } finally {
                    AccessibilityService accessibilityService6 = bVar.f1806b;
                    if (accessibilityService6 instanceof SnapPayAccessibilityService) {
                        ((SnapPayAccessibilityService) accessibilityService6).f565b = false;
                    }
                }
                return;
            case 3:
                p0.d dVar = (p0.d) this.f1671b;
                String str3 = (String) this.f1672c;
                Runnable runnable2 = (Runnable) this.f1673d;
                dVar.getClass();
                try {
                    dVar.n(str3, runnable2);
                    accessibilityService3 = dVar.f1823b;
                    if (!(accessibilityService3 instanceof SnapPayAccessibilityService)) {
                        return;
                    }
                } catch (Exception e4) {
                    dVar.i("fillPin thread error: " + e4.getMessage());
                    if (!(dVar.f1823b instanceof SnapPayAccessibilityService)) {
                        return;
                    } else {
                        accessibilityService3 = dVar.f1823b;
                    }
                } finally {
                    AccessibilityService accessibilityService7 = dVar.f1823b;
                    if (accessibilityService7 instanceof SnapPayAccessibilityService) {
                        ((SnapPayAccessibilityService) accessibilityService7).f565b = false;
                    }
                }
                SnapPayAccessibilityService snapPayAccessibilityService = (SnapPayAccessibilityService) accessibilityService3;
                return;
            case 4:
                q0.b bVar2 = (q0.b) this.f1671b;
                String str4 = (String) this.f1672c;
                Runnable runnable3 = (Runnable) this.f1673d;
                bVar2.getClass();
                try {
                    bVar2.j(str4, runnable3);
                    AccessibilityService accessibilityService8 = bVar2.f1859b;
                    boolean z4 = accessibilityService8 instanceof SnapPayAccessibilityService;
                    accessibilityService4 = accessibilityService8;
                    bVar2 = accessibilityService8;
                    if (z4) {
                        bVar2 = (SnapPayAccessibilityService) accessibilityService4;
                    }
                } catch (Exception e5) {
                    bVar2.f("fillPin thread error: " + e5.getMessage());
                    boolean z5 = bVar2.f1859b instanceof SnapPayAccessibilityService;
                    bVar2 = bVar2;
                    if (z5) {
                        accessibilityService4 = bVar2.f1859b;
                    }
                } finally {
                    AccessibilityService accessibilityService9 = bVar2.f1859b;
                    if (accessibilityService9 instanceof SnapPayAccessibilityService) {
                        ((SnapPayAccessibilityService) accessibilityService9).f565b = false;
                    }
                }
                return;
            default:
                q0.d dVar2 = (q0.d) this.f1671b;
                String str5 = (String) this.f1672c;
                Runnable runnable4 = (Runnable) this.f1673d;
                dVar2.getClass();
                try {
                    dVar2.h(str5, runnable4);
                    accessibilityService = dVar2.f1875b;
                    if (!(accessibilityService instanceof SnapPayAccessibilityService)) {
                        return;
                    }
                } catch (Exception e6) {
                    dVar2.e("fillPin thread error: " + e6.getMessage());
                    if (!(dVar2.f1875b instanceof SnapPayAccessibilityService)) {
                        return;
                    } else {
                        accessibilityService = dVar2.f1875b;
                    }
                } finally {
                    AccessibilityService accessibilityService10 = dVar2.f1875b;
                    if (accessibilityService10 instanceof SnapPayAccessibilityService) {
                        ((SnapPayAccessibilityService) accessibilityService10).f565b = false;
                    }
                }
                SnapPayAccessibilityService snapPayAccessibilityService2 = (SnapPayAccessibilityService) accessibilityService;
                return;
        }
    }
}
