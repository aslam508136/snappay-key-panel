package m0;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.snapay.app.LoginActivity;
import com.snapay.app.MainActivity;
import com.snapay.app.R;
import com.snapay.app.SplashActivity;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1645a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f1646b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f1647c;

    public /* synthetic */ b(Object obj, Object obj2, int i2) {
        this.f1645a = i2;
        this.f1647c = obj;
        this.f1646b = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i2;
        Runnable runnable;
        int i3 = this.f1645a;
        final int i4 = 1;
        Object obj = this.f1646b;
        Object obj2 = this.f1647c;
        final int i5 = 0;
        switch (i3) {
            case 0:
                d dVar = (d) obj2;
                String str = (String) obj;
                int i6 = Build.VERSION.SDK_INT;
                Context context = dVar.f1651a;
                if (i6 >= 23 && !Settings.canDrawOverlays(context)) {
                    i4 = 0;
                }
                if (i4 != 0) {
                    dVar.a();
                    View viewInflate = LayoutInflater.from(context).inflate(R.layout.overlay_danger, (ViewGroup) null);
                    dVar.f1654d = viewInflate;
                    ((TextView) viewInflate.findViewById(R.id.txtDangerMessage)).setText(str);
                    ((Button) dVar.f1654d.findViewById(R.id.btnDangerDismiss)).setOnClickListener(new i(dVar, 2));
                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -2, i6 >= 26 ? 2038 : 2002, 262176, -3);
                    layoutParams.gravity = 8388659;
                    layoutParams.x = 0;
                    layoutParams.y = 0;
                    try {
                        ((WindowManager) dVar.f1652b).addView(dVar.f1654d, layoutParams);
                        c cVar = new c(dVar, i5);
                        dVar.f1655e = cVar;
                        ((Handler) dVar.f1653c).postDelayed(cVar, 6000L);
                    } catch (Exception unused) {
                        dVar.f1654d = null;
                        return;
                    }
                    break;
                }
                break;
            case 1:
                final LoginActivity loginActivity = (LoginActivity) obj2;
                final String str2 = (String) obj;
                int i7 = LoginActivity.f527x;
                loginActivity.getClass();
                try {
                    final JSONObject jSONObjectN = loginActivity.n(str2, loginActivity.m());
                    final boolean z2 = jSONObjectN.getBoolean("valid");
                    final String strOptString = jSONObjectN.optString("status", "");
                    final String strOptString2 = jSONObjectN.optString("message", "");
                    loginActivity.runOnUiThread(new Runnable() { // from class: m0.f
                        @Override // java.lang.Runnable
                        public final void run() {
                            LoginActivity loginActivity2 = loginActivity;
                            loginActivity2.f529p.setEnabled(true);
                            loginActivity2.f529p.setText("Login");
                            if (z2) {
                                String strOptString3 = jSONObjectN.optString("expiresAt", "");
                                SharedPreferences.Editor editorEdit = m.a(loginActivity2).edit();
                                editorEdit.putString("access_key", str2);
                                editorEdit.putBoolean("is_logged_in", true);
                                editorEdit.putString("expires_at", strOptString3);
                                editorEdit.apply();
                                loginActivity2.startActivity(new Intent(loginActivity2, (Class<?>) MainActivity.class));
                                loginActivity2.finish();
                                return;
                            }
                            String str3 = strOptString2;
                            if (str3.isEmpty()) {
                                String str4 = strOptString;
                                if ("revoked".equals(str4)) {
                                    str3 = "This key has been revoked by admin";
                                } else if ("expired".equals(str4)) {
                                    str3 = "This key has expired";
                                } else if ("not_found".equals(str4)) {
                                    str3 = "Key not found. Please check and try again";
                                } else {
                                    str3 = "device_mismatch".equals(str4) ? "This key is registered to another device" : "Invalid access key";
                                }
                            }
                            loginActivity2.f531r.setText(str3);
                            loginActivity2.f531r.setVisibility(0);
                        }
                    });
                } catch (Exception e2) {
                    e2.printStackTrace();
                    loginActivity.runOnUiThread(new g(loginActivity, i5));
                }
                break;
            default:
                final Activity activity = (Activity) obj2;
                final x xVar = (x) obj;
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("https://snappay-web.vercel.app/api/app/version").openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(8000);
                    httpURLConnection.setReadTimeout(8000);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            bufferedReader.close();
                            JSONObject jSONObject = new JSONObject(sb.toString());
                            int iOptInt = jSONObject.optInt("versionCode", 0);
                            final String strOptString3 = jSONObject.optString("version", "");
                            String strOptString4 = jSONObject.optString("downloadUrl", "");
                            String strOptString5 = jSONObject.optString("releaseNotes", "");
                            try {
                                i2 = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0).versionCode;
                            } catch (Exception unused2) {
                                i2 = 0;
                            }
                            if (iOptInt > i2) {
                                if (strOptString4.isEmpty()) {
                                    strOptString4 = "/api/app/download?v=" + strOptString3;
                                }
                                final String str3 = strOptString4.startsWith("http") ? strOptString4 : "https://snappay-web.vercel.app" + strOptString4;
                                if (strOptString5.isEmpty()) {
                                    strOptString5 = "Bug fixes and improvements.";
                                }
                                final String str4 = strOptString5;
                                runnable = new Runnable() { // from class: m0.q
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        x xVar2;
                                        androidx.activity.b bVar;
                                        if (activity.isFinishing() || (xVar2 = xVar) == null) {
                                            return;
                                        }
                                        h.a aVar = (h.a) xVar2;
                                        String str5 = strOptString3;
                                        String str6 = str4;
                                        String str7 = str3;
                                        int i8 = aVar.f891b;
                                        Object obj3 = aVar.f892c;
                                        switch (i8) {
                                            case TYPE_UINT32_VALUE:
                                                androidx.lifecycle.i.i0((LoginActivity) obj3, str5, str6, str7);
                                                break;
                                            case TYPE_ENUM_VALUE:
                                                MainActivity mainActivity = (MainActivity) obj3;
                                                Handler handler = mainActivity.f548m0;
                                                if (handler != null && (bVar = mainActivity.f549n0) != null) {
                                                    handler.removeCallbacks(bVar);
                                                }
                                                androidx.lifecycle.i.i0(mainActivity, str5, str6, str7);
                                                break;
                                            default:
                                                androidx.lifecycle.i.i0((SplashActivity) obj3, str5, str6, str7);
                                                break;
                                        }
                                    }
                                };
                            } else {
                                runnable = new Runnable() { // from class: m0.r
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        int i8 = i5;
                                        x xVar2 = xVar;
                                        switch (i8) {
                                            case 0:
                                                if (xVar2 != null) {
                                                    ((h.a) xVar2).n();
                                                }
                                                break;
                                            default:
                                                if (xVar2 != null) {
                                                    ((h.a) xVar2).n();
                                                }
                                                break;
                                        }
                                    }
                                };
                            }
                            activity.runOnUiThread(runnable);
                        } else {
                            sb.append(line);
                        }
                    }
                } catch (Exception unused3) {
                    activity.runOnUiThread(new Runnable() { // from class: m0.r
                        @Override // java.lang.Runnable
                        public final void run() {
                            int i8 = i4;
                            x xVar2 = xVar;
                            switch (i8) {
                                case 0:
                                    if (xVar2 != null) {
                                        ((h.a) xVar2).n();
                                    }
                                    break;
                                default:
                                    if (xVar2 != null) {
                                        ((h.a) xVar2).n();
                                    }
                                    break;
                            }
                        }
                    });
                    return;
                }
                break;
        }
    }
}
