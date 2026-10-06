package com.snapay.app;

import android.animation.ValueAnimator;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.lifecycle.i;
import d.n;
import h.a;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import m0.g;
import m0.m;
import m0.p;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SplashActivity extends n {

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final /* synthetic */ int f568v = 0;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public TextView f569o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public TextView f570p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public TextView f571q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public TextView f572r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public LinearLayout f573s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public Handler f574t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f575u = false;

    public final void m(String str) {
        new Thread(new p(this, str, 0)).start();
    }

    public final String n() {
        try {
            File file = new File(getFilesDir(), ".device_id");
            if (file.exists()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String line = bufferedReader.readLine();
                bufferedReader.close();
                if (line != null && !line.isEmpty()) {
                    m.a(this).edit().putString("device_id", line).commit();
                    return line;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        SharedPreferences sharedPreferencesA = m.a(this);
        String string = sharedPreferencesA.getString("device_id", null);
        if (string == null || string.isEmpty()) {
            string = String.valueOf(System.currentTimeMillis()) + String.valueOf(new SecureRandom().nextInt(900000) + 100000);
            try {
                FileWriter fileWriter = new FileWriter(new File(getFilesDir(), ".device_id"));
                fileWriter.write(string);
                fileWriter.close();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            sharedPreferencesA.edit().putString("device_id", string).commit();
        } else {
            try {
                File file2 = new File(getFilesDir(), ".device_id");
                if (!file2.exists()) {
                    FileWriter fileWriter2 = new FileWriter(file2);
                    fileWriter2.write(string);
                    fileWriter2.close();
                }
            } catch (Exception e4) {
                e4.printStackTrace();
            }
        }
        return string;
    }

    public final boolean o() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService("connectivity");
            if (connectivityManager == null) {
                return true;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
        } catch (Exception unused) {
            return true;
        }
    }

    @Override // androidx.fragment.app.h, androidx.activity.h, o.d, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        i.f454z = false;
        i.A = false;
        int i2 = Build.VERSION.SDK_INT;
        getWindow().setStatusBarColor(Color.parseColor("#2d1b69"));
        if (i2 >= 23) {
            getWindow().getDecorView().setSystemUiVisibility(0);
        }
        setContentView(R.layout.activity_splash);
        this.f569o = (TextView) findViewById(R.id.txtStatus);
        this.f570p = (TextView) findViewById(R.id.txtWelcome);
        this.f571q = (TextView) findViewById(R.id.txtTo);
        this.f572r = (TextView) findViewById(R.id.txtAppName);
        this.f573s = (LinearLayout) findViewById(R.id.neonContainer);
        int i3 = 1;
        new Handler().postDelayed(new g(this.f570p, i3), 0L);
        new Handler().postDelayed(new g(this.f571q, i3), 200L);
        new Handler().postDelayed(new g(this.f572r, i3), 400L);
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.6f, 1.0f);
        valueAnimatorOfFloat.setDuration(1500L);
        valueAnimatorOfFloat.setRepeatCount(-1);
        valueAnimatorOfFloat.setRepeatMode(2);
        valueAnimatorOfFloat.addUpdateListener(new m0.n(this, 0));
        valueAnimatorOfFloat.setStartDelay(1200L);
        valueAnimatorOfFloat.start();
        this.f569o.setText("Checking for updates...");
        i.g(this, new a(this, 15));
    }

    @Override // d.n, androidx.fragment.app.h, android.app.Activity
    public final void onDestroy() {
        super.onDestroy();
        Handler handler = this.f574t;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    public final JSONObject p(String str, String str2) throws JSONException, IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("https://snappay-web.vercel.app/api/keys/validate").openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setConnectTimeout(15000);
        httpURLConnection.setReadTimeout(15000);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("deviceId", str2);
        jSONObject.put("manufacturer", Build.MANUFACTURER);
        jSONObject.put("model", Build.MODEL);
        jSONObject.put("brand", Build.BRAND);
        jSONObject.put("device", Build.DEVICE);
        jSONObject.put("androidVersion", Build.VERSION.RELEASE);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("key", str);
        jSONObject2.put("deviceInfo", jSONObject.toString());
        String string = jSONObject2.toString();
        OutputStream outputStream = httpURLConnection.getOutputStream();
        outputStream.write(string.getBytes("utf-8"));
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
}
