package com.snapay.app;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.b;
import androidx.core.widget.NestedScrollView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import d.n;
import h.a;
import j.d3;
import j.v2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import m0.h;
import m0.j;
import m0.k;
import m0.l;
import m0.m;
import o0.d;
import o0.i;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends n {
    public static final /* synthetic */ int s0 = 0;
    public LinearLayout A;
    public LinearLayout B;
    public Button C;
    public Button D;
    public Button E;
    public Button F;
    public Button G;
    public Button H;
    public Button I;
    public Button J;
    public Button K;
    public Button L;
    public Button M;
    public Button N;
    public Button O;
    public Button P;
    public Button Q;
    public Button R;
    public TextView S;
    public Button T;
    public Button U;
    public Button V;
    public TextView W;
    public TextView X;
    public TextView Y;
    public LinearLayout Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public TextView f537a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public TextView f538b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public TextView f539c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public LinearLayout f540d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public LinearLayout f541e0;
    public LinearLayout f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public LinearLayout f542g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public TextView f543h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public Button f544i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public TextView f545j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public Button f546k0;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public TextView f547l0;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public Handler f548m0;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public b f549n0;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Button f550o;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public boolean f551o0 = false;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public TextView f552p;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public LinearLayout f553p0;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public d3 f554q;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public NestedScrollView f555q0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public l f556r;
    public l r0;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public LinearLayout f557s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public LinearLayout f558t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public EditText f559u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public Button f560v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public TextView f561w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public TextView f562x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public TextView f563y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public ImageButton f564z;

    public static JSONObject t(String str) throws JSONException, IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("https://snappay-web.vercel.app/api/keys/validate").openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setDoOutput(true);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("key", str);
        byte[] bytes = jSONObject.toString().getBytes("utf-8");
        OutputStream outputStream = httpURLConnection.getOutputStream();
        outputStream.write(bytes);
        outputStream.close();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getResponseCode() >= 400 ? httpURLConnection.getErrorStream() : httpURLConnection.getInputStream(), "utf-8"));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                bufferedReader.close();
                return new JSONObject(sb.toString());
            }
            sb.append(line.trim());
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            View currentFocus = getCurrentFocus();
            if (currentFocus instanceof EditText) {
                Rect rect = new Rect();
                currentFocus.getGlobalVisibleRect(rect);
                if (!rect.contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY())) {
                    currentFocus.clearFocus();
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
                    if (inputMethodManager != null) {
                        inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
                    }
                }
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public final void m() {
        this.f554q.a(this.T, this.U, this.W, this.X, this.Y, this.Z, this.f537a0, this.f538b0, this.f539c0, this.V);
        d3 d3Var = this.f554q;
        i iVar = new i(d3Var.f1220a, this.f559u, this.f560v, this.f561w, this.f562x, this.f564z);
        d3Var.f1222c = iVar;
        d dVar = (d) d3Var.f1226g;
        if (dVar != null) {
            iVar.f1799k = dVar;
        }
        d3 d3Var2 = this.f554q;
        TextView textView = this.S;
        int i2 = 1;
        Button[] buttonArr = {this.C, this.D, this.E, this.F, this.G, this.H, this.I, this.J, this.K, this.L, this.M, this.N, this.O, this.P, this.Q, this.R};
        d3Var2.f1223d = new o0.b(d3Var2.f1220a, textView);
        for (int i3 = 0; i3 < 16; i3++) {
            Button button = buttonArr[i3];
            o0.b bVar = (o0.b) d3Var2.f1223d;
            ((List) bVar.f1755g).add(button);
            button.setOnClickListener(new v2(bVar, i2));
        }
        ((o0.b) d3Var2.f1223d).c();
        i iVar2 = (i) d3Var2.f1222c;
        if (iVar2 != null) {
            o0.b bVar2 = (o0.b) d3Var2.f1223d;
            switch (bVar2.f1749a) {
                case 0:
                    bVar2.f1753e = iVar2;
                    break;
                default:
                    bVar2.f1753e = iVar2;
                    break;
            }
        }
        d dVar2 = (d) d3Var2.f1226g;
        if (dVar2 != null) {
            o0.b bVar3 = (o0.b) d3Var2.f1223d;
            switch (bVar3.f1749a) {
                case 0:
                    bVar3.f1754f = dVar2;
                    break;
                default:
                    bVar3.f1754f = dVar2;
                    break;
            }
        }
        d3 d3Var3 = this.f554q;
        o0.b bVar4 = new o0.b(d3Var3.f1220a, this.f557s, this.f558t, this.f563y);
        d3Var3.f1225f = bVar4;
        o0.b bVar5 = (o0.b) d3Var3.f1223d;
        if (bVar5 != null) {
            bVar4.f1757i = bVar5;
            bVar5.d(bVar4.f1751c.f1782d);
        }
        Object obj = d3Var3.f1222c;
        i iVar3 = (i) obj;
        if (iVar3 != null) {
            Object obj2 = d3Var3.f1225f;
            o0.b bVar6 = (o0.b) obj2;
            switch (bVar6.f1749a) {
                case 0:
                    bVar6.f1753e = iVar3;
                    break;
                default:
                    bVar6.f1753e = iVar3;
                    break;
            }
            ((i) obj).b(((o0.b) obj2).f1751c.f1782d);
        }
        d dVar3 = (d) d3Var3.f1226g;
        if (dVar3 != null) {
            o0.b bVar7 = (o0.b) d3Var3.f1225f;
            switch (bVar7.f1749a) {
                case 0:
                    bVar7.f1754f = dVar3;
                    break;
                default:
                    bVar7.f1754f = dVar3;
                    break;
            }
        }
        d3 d3Var4 = this.f554q;
        m0.d dVar4 = new m0.d(d3Var4.f1220a, this.A, this.B);
        d3Var4.f1224e = dVar4;
        d dVar5 = (d) d3Var4.f1226g;
        if (dVar5 != null) {
            dVar4.f1655e = dVar5;
        }
    }

    public final void n() {
        this.f550o = (Button) findViewById(R.id.btnPhonePe);
        this.f552p = (TextView) findViewById(R.id.txtSelectedApp);
        this.f557s = (LinearLayout) findViewById(R.id.btnUpiAutopay);
        this.f558t = (LinearLayout) findViewById(R.id.btnQrScanner);
        this.f559u = (EditText) findViewById(R.id.edtUpiPin);
        this.f560v = (Button) findViewById(R.id.btnSavePin);
        this.f561w = (TextView) findViewById(R.id.txtPinSaved);
        this.f562x = (TextView) findViewById(R.id.txtBankWarning);
        this.f563y = (TextView) findViewById(R.id.txtBankNote);
        this.f564z = (ImageButton) findViewById(R.id.btnTogglePinVisibility);
        this.A = (LinearLayout) findViewById(R.id.btnPayAll);
        this.B = (LinearLayout) findViewById(R.id.btnOneTime);
        this.S = (TextView) findViewById(R.id.txtSelectedBank);
        this.C = (Button) findViewById(R.id.btnSbi);
        this.D = (Button) findViewById(R.id.btnPnb);
        this.E = (Button) findViewById(R.id.btnBob);
        this.F = (Button) findViewById(R.id.btnCb);
        this.G = (Button) findViewById(R.id.btnUbi);
        this.H = (Button) findViewById(R.id.btnIb);
        this.I = (Button) findViewById(R.id.btnBoi);
        this.J = (Button) findViewById(R.id.btnCbi);
        this.K = (Button) findViewById(R.id.btnIob);
        this.L = (Button) findViewById(R.id.btnUco);
        this.M = (Button) findViewById(R.id.btnBom);
        this.N = (Button) findViewById(R.id.btnPsb);
        this.O = (Button) findViewById(R.id.btnHdfc);
        this.P = (Button) findViewById(R.id.btnIcici);
        this.Q = (Button) findViewById(R.id.btnAxis);
        this.R = (Button) findViewById(R.id.btnOther);
        this.T = (Button) findViewById(R.id.btnActivateAutopay);
        this.V = (Button) findViewById(R.id.btnOpenPhonePe);
        this.W = (TextView) findViewById(R.id.txtAutopayStatus);
        this.X = (TextView) findViewById(R.id.txtStatus);
        this.Y = (TextView) findViewById(R.id.txtWatching);
        this.Z = (LinearLayout) findViewById(R.id.statusContainer);
        this.f537a0 = (TextView) findViewById(R.id.txtLast);
        this.f538b0 = (TextView) findViewById(R.id.txtTotal);
        this.f539c0 = (TextView) findViewById(R.id.txtAvg);
        this.U = (Button) findViewById(R.id.btnResetStats);
        this.f540d0 = (LinearLayout) findViewById(R.id.containerAppMode);
        this.f541e0 = (LinearLayout) findViewById(R.id.containerUpiPin);
        this.f0 = (LinearLayout) findViewById(R.id.containerBankSelection);
        this.f542g0 = (LinearLayout) findViewById(R.id.containerPaymentMode);
        this.f543h0 = (TextView) findViewById(R.id.txtAccessibilityStatus);
        this.f544i0 = (Button) findViewById(R.id.btnSettings);
        this.f547l0 = (TextView) findViewById(R.id.txtSubscriptionCountdown);
        TextView textView = (TextView) findViewById(R.id.txtAppVersion);
        int i2 = 0;
        try {
            textView.setText("v" + getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
        } catch (Exception unused) {
        }
        this.f545j0 = (TextView) findViewById(R.id.txtOverlayStatus);
        this.f546k0 = (Button) findViewById(R.id.btnOverlaySettings);
        this.f553p0 = (LinearLayout) findViewById(R.id.logContainer);
        this.f555q0 = (NestedScrollView) findViewById(R.id.logScrollView);
        ((Button) findViewById(R.id.btnClearLogs)).setOnClickListener(new m0.i(this, i2));
        ((Button) findViewById(R.id.btnCopyLogs)).setOnClickListener(new m0.i(this, 1));
        this.f544i0.setOnClickListener(new k(this, 0));
        this.f546k0.setOnClickListener(new k(this, 1));
    }

    public final void o() {
        b bVar;
        Handler handler = this.f548m0;
        if (handler != null && (bVar = this.f549n0) != null) {
            handler.removeCallbacks(bVar);
        }
        m.a(this).edit().clear().apply();
        Intent intent = new Intent(this, (Class<?>) LoginActivity.class);
        intent.setFlags(268468224);
        startActivity(intent);
        finish();
    }

    @Override // androidx.fragment.app.h, androidx.activity.h, o.d, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int i2 = Build.VERSION.SDK_INT;
        getWindow().setStatusBarColor(Color.parseColor("#F0EFFF"));
        if (i2 >= 23) {
            getWindow().getDecorView().setSystemUiVisibility(8192);
        }
        getWindow().setSoftInputMode(3);
        try {
            setContentView(R.layout.activity_main);
            int i3 = 0;
            androidx.lifecycle.i.f454z = false;
            androidx.lifecycle.i.A = false;
            this.f554q = new d3(this, 1);
            n();
            this.f550o.setOnClickListener(new k(this, 2));
            m();
            r();
            s();
            p();
            SharedPreferences sharedPreferencesA = m.a(this);
            String string = sharedPreferencesA.getString("access_key", "");
            if (string.isEmpty()) {
                o();
            } else {
                new Thread(new j(this, string, sharedPreferencesA, i3)).start();
            }
            q();
        } catch (Exception e2) {
            Log.e("MainActivity", "Error in onCreate", e2);
            e2.printStackTrace();
        }
    }

    @Override // d.n, androidx.fragment.app.h, android.app.Activity
    public final void onDestroy() {
        d dVar;
        super.onDestroy();
        d3 d3Var = this.f554q;
        if (d3Var != null && (dVar = (d) d3Var.f1226g) != null) {
            dVar.a();
        }
        if (this.f556r != null) {
            try {
                LocalBroadcastManager.getInstance(this).unregisterReceiver(this.f556r);
            } catch (IllegalArgumentException unused) {
            }
        }
        if (this.r0 != null) {
            try {
                LocalBroadcastManager.getInstance(this).unregisterReceiver(this.r0);
            } catch (IllegalArgumentException unused2) {
            }
        }
    }

    @Override // androidx.fragment.app.h, android.app.Activity
    public final void onResume() {
        super.onResume();
        s();
        q();
        if (m.a(this).getBoolean("is_logged_in", false)) {
            androidx.lifecycle.i.g(this, new a(this, 14));
        } else {
            o();
        }
    }

    public final void p() {
        this.f556r = new l(this, 0);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.snapay.app.AUTOPAY_DEACTIVATED");
        intentFilter.addAction("com.snapay.app.AUTOPAY_ACTIVATED");
        intentFilter.addAction("com.snapay.app.PAYMENT_STATS_UPDATED");
        intentFilter.addAction("com.snapay.app.CLOSE_APP");
        LocalBroadcastManager.getInstance(this).registerReceiver(this.f556r, intentFilter);
        this.r0 = new l(this, 1);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.r0, new IntentFilter("com.snapay.app.LOG_UPDATED"));
    }

    public final void q() {
        LinearLayout linearLayout = this.f553p0;
        if (linearLayout == null) {
            return;
        }
        linearLayout.removeAllViews();
        ArrayList<String> arrayListO = m0.a.f(this).o();
        int i2 = 0;
        if (arrayListO.isEmpty()) {
            TextView textView = new TextView(this);
            textView.setText("~ no logs yet");
            textView.setTextSize(12.0f);
            textView.setTextColor(Color.parseColor("#555555"));
            textView.setTypeface(Typeface.MONOSPACE);
            textView.setPadding(0, 2, 0, 2);
            this.f553p0.addView(textView);
            return;
        }
        for (String str : arrayListO) {
            int iIndexOf = str.indexOf("  ");
            TextView textView2 = new TextView(this);
            if (iIndexOf > 0) {
                SpannableString spannableString = new SpannableString(str);
                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#00C853")), 0, iIndexOf, 33);
                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#E0E0E0")), iIndexOf + 2, str.length(), 33);
                textView2.setText(spannableString);
            } else {
                textView2.setText(str);
                textView2.setTextColor(Color.parseColor("#E0E0E0"));
            }
            textView2.setTextSize(11.5f);
            textView2.setTypeface(Typeface.MONOSPACE);
            textView2.setPadding(0, 2, 0, 2);
            this.f553p0.addView(textView2);
        }
        NestedScrollView nestedScrollView = this.f555q0;
        if (nestedScrollView != null) {
            nestedScrollView.post(new h(this, i2));
        }
    }

    public final void r() {
        this.f552p.setText("PHONEPE");
        this.f550o.setBackgroundResource(R.drawable.btn_phonepe_selected);
        this.f550o.setTextColor(Color.parseColor("#FF0000"));
        this.f540d0.setVisibility(0);
        this.Z.setVisibility(0);
        this.f541e0.setVisibility(0);
        this.f0.setVisibility(0);
        this.f542g0.setVisibility(0);
    }

    public final void s() {
        TextView textView;
        int color;
        Button button;
        String str;
        if (this.f543h0 == null) {
            return;
        }
        if (androidx.lifecycle.i.J(this)) {
            this.f543h0.setText("● Active");
            textView = this.f543h0;
            color = Color.parseColor("#22C55E");
        } else {
            this.f543h0.setText("● Inactive");
            textView = this.f543h0;
            color = Color.parseColor("#F59E0B");
        }
        textView.setTextColor(color);
        if (this.f545j0 == null || this.f546k0 == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 23 || Settings.canDrawOverlays(this)) {
            this.f545j0.setText("● Active");
            this.f545j0.setTextColor(Color.parseColor("#22C55E"));
            button = this.f546k0;
            str = "Settings";
        } else {
            this.f545j0.setText("● Inactive");
            this.f545j0.setTextColor(Color.parseColor("#F59E0B"));
            button = this.f546k0;
            str = "Enable";
        }
        button.setText(str);
    }
}
