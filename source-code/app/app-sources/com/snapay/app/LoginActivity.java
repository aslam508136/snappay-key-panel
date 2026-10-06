package com.snapay.app;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.lifecycle.i;
import com.snapay.app.LoginActivity;
import d.n;
import h.a;
import j.d2;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import m0.m;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class LoginActivity extends n {

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final /* synthetic */ int f527x = 0;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public EditText f528o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Button f529p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public TextView f530q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public TextView f531r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public TextView f532s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public ImageButton f533t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public ImageButton f534u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public ImageButton f535v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f536w = false;

    public final String m() {
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

    public final JSONObject n(String str, String str2) {
        BufferedReader bufferedReader;
        StringBuilder sb;
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("https://snappay-web.vercel.app/api/keys/validate").openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setDoOutput(true);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("deviceId", str2);
        jSONObject.put("manufacturer", Build.MANUFACTURER);
        jSONObject.put("model", Build.MODEL);
        jSONObject.put("androidVersion", Build.VERSION.RELEASE);
        jSONObject.put("appVersionCode", 0);
        try {
            jSONObject.put("appVersionCode", getPackageManager().getPackageInfo(getPackageName(), 0).versionCode);
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    bufferedReader.close();
                    return new JSONObject(sb.toString());
                }
                sb.append(line.trim());
            }
        } catch (Exception unused) {
        }
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("key", str);
        jSONObject2.put("deviceInfo", jSONObject.toString());
        String string = jSONObject2.toString();
        OutputStream outputStream = httpURLConnection.getOutputStream();
        outputStream.write(string.getBytes("utf-8"));
        outputStream.close();
        bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getResponseCode() >= 400 ? httpURLConnection.getErrorStream() : httpURLConnection.getInputStream(), "utf-8"));
        sb = new StringBuilder();
    }

    @Override // androidx.fragment.app.h, androidx.activity.h, o.d, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setStatusBarColor(Color.parseColor("#F0EFFF"));
        setContentView(R.layout.activity_login);
        this.f528o = (EditText) findViewById(R.id.edtAccessKey);
        this.f529p = (Button) findViewById(R.id.btnLogin);
        this.f530q = (TextView) findViewById(R.id.txtGetSubscription);
        this.f531r = (TextView) findViewById(R.id.txtError);
        this.f532s = (TextView) findViewById(R.id.txtDeviceId);
        this.f533t = (ImageButton) findViewById(R.id.btnToggleKeyVisibility);
        this.f534u = (ImageButton) findViewById(R.id.btnPasteKey);
        this.f535v = (ImageButton) findViewById(R.id.btnCopyDeviceId);
        this.f528o.setTransformationMethod(PasswordTransformationMethod.getInstance());
        this.f532s.setText(m());
        final int i2 = 0;
        i.f454z = false;
        i.A = false;
        i.g(this, new a(this, 13));
        this.f529p.setOnClickListener(new View.OnClickListener(this) { // from class: m0.e

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ LoginActivity f1657c;

            {
                this.f1657c = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditText editText;
                TransformationMethod hideReturnsTransformationMethod;
                int i3 = i2;
                int i4 = 1;
                LoginActivity loginActivity = this.f1657c;
                switch (i3) {
                    case 0:
                        String strTrim = loginActivity.f528o.getText().toString().trim();
                        if (!strTrim.isEmpty()) {
                            loginActivity.f531r.setVisibility(8);
                            loginActivity.f529p.setEnabled(false);
                            loginActivity.f529p.setText("Validating...");
                            new Thread(new b(loginActivity, strTrim, i4)).start();
                        } else {
                            loginActivity.f531r.setText("Please enter your access key");
                            loginActivity.f531r.setVisibility(0);
                        }
                        break;
                    case 1:
                        int i5 = LoginActivity.f527x;
                        loginActivity.getClass();
                        try {
                            loginActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://t.me/snappayybot")));
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            return;
                        }
                        break;
                    case 2:
                        if (loginActivity.f536w) {
                            editText = loginActivity.f528o;
                            hideReturnsTransformationMethod = PasswordTransformationMethod.getInstance();
                        } else {
                            editText = loginActivity.f528o;
                            hideReturnsTransformationMethod = HideReturnsTransformationMethod.getInstance();
                        }
                        editText.setTransformationMethod(hideReturnsTransformationMethod);
                        EditText editText2 = loginActivity.f528o;
                        editText2.setSelection(editText2.getText().length());
                        loginActivity.f536w = !loginActivity.f536w;
                        break;
                    case 3:
                        int i6 = LoginActivity.f527x;
                        ClipboardManager clipboardManager = (ClipboardManager) loginActivity.getSystemService("clipboard");
                        if (clipboardManager != null && clipboardManager.hasPrimaryClip()) {
                            loginActivity.f528o.setText(clipboardManager.getPrimaryClip().getItemAt(0).getText().toString().trim());
                        } else {
                            Toast.makeText(loginActivity, "Nothing to paste", 0).show();
                        }
                        break;
                    default:
                        int i7 = LoginActivity.f527x;
                        ((ClipboardManager) loginActivity.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Device ID", loginActivity.m()));
                        Toast.makeText(loginActivity, "Device ID copied to clipboard", 0).show();
                        break;
                }
            }
        });
        final int i3 = 1;
        this.f530q.setOnClickListener(new View.OnClickListener(this) { // from class: m0.e

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ LoginActivity f1657c;

            {
                this.f1657c = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditText editText;
                TransformationMethod hideReturnsTransformationMethod;
                int i4 = i3;
                int i5 = 1;
                LoginActivity loginActivity = this.f1657c;
                switch (i4) {
                    case 0:
                        String strTrim = loginActivity.f528o.getText().toString().trim();
                        if (!strTrim.isEmpty()) {
                            loginActivity.f531r.setVisibility(8);
                            loginActivity.f529p.setEnabled(false);
                            loginActivity.f529p.setText("Validating...");
                            new Thread(new b(loginActivity, strTrim, i5)).start();
                        } else {
                            loginActivity.f531r.setText("Please enter your access key");
                            loginActivity.f531r.setVisibility(0);
                        }
                        break;
                    case 1:
                        int i6 = LoginActivity.f527x;
                        loginActivity.getClass();
                        try {
                            loginActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://t.me/snappayybot")));
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            return;
                        }
                        break;
                    case 2:
                        if (loginActivity.f536w) {
                            editText = loginActivity.f528o;
                            hideReturnsTransformationMethod = PasswordTransformationMethod.getInstance();
                        } else {
                            editText = loginActivity.f528o;
                            hideReturnsTransformationMethod = HideReturnsTransformationMethod.getInstance();
                        }
                        editText.setTransformationMethod(hideReturnsTransformationMethod);
                        EditText editText2 = loginActivity.f528o;
                        editText2.setSelection(editText2.getText().length());
                        loginActivity.f536w = !loginActivity.f536w;
                        break;
                    case 3:
                        int i7 = LoginActivity.f527x;
                        ClipboardManager clipboardManager = (ClipboardManager) loginActivity.getSystemService("clipboard");
                        if (clipboardManager != null && clipboardManager.hasPrimaryClip()) {
                            loginActivity.f528o.setText(clipboardManager.getPrimaryClip().getItemAt(0).getText().toString().trim());
                        } else {
                            Toast.makeText(loginActivity, "Nothing to paste", 0).show();
                        }
                        break;
                    default:
                        int i8 = LoginActivity.f527x;
                        ((ClipboardManager) loginActivity.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Device ID", loginActivity.m()));
                        Toast.makeText(loginActivity, "Device ID copied to clipboard", 0).show();
                        break;
                }
            }
        });
        final int i4 = 2;
        this.f533t.setOnClickListener(new View.OnClickListener(this) { // from class: m0.e

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ LoginActivity f1657c;

            {
                this.f1657c = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditText editText;
                TransformationMethod hideReturnsTransformationMethod;
                int i5 = i4;
                int i6 = 1;
                LoginActivity loginActivity = this.f1657c;
                switch (i5) {
                    case 0:
                        String strTrim = loginActivity.f528o.getText().toString().trim();
                        if (!strTrim.isEmpty()) {
                            loginActivity.f531r.setVisibility(8);
                            loginActivity.f529p.setEnabled(false);
                            loginActivity.f529p.setText("Validating...");
                            new Thread(new b(loginActivity, strTrim, i6)).start();
                        } else {
                            loginActivity.f531r.setText("Please enter your access key");
                            loginActivity.f531r.setVisibility(0);
                        }
                        break;
                    case 1:
                        int i7 = LoginActivity.f527x;
                        loginActivity.getClass();
                        try {
                            loginActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://t.me/snappayybot")));
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            return;
                        }
                        break;
                    case 2:
                        if (loginActivity.f536w) {
                            editText = loginActivity.f528o;
                            hideReturnsTransformationMethod = PasswordTransformationMethod.getInstance();
                        } else {
                            editText = loginActivity.f528o;
                            hideReturnsTransformationMethod = HideReturnsTransformationMethod.getInstance();
                        }
                        editText.setTransformationMethod(hideReturnsTransformationMethod);
                        EditText editText2 = loginActivity.f528o;
                        editText2.setSelection(editText2.getText().length());
                        loginActivity.f536w = !loginActivity.f536w;
                        break;
                    case 3:
                        int i8 = LoginActivity.f527x;
                        ClipboardManager clipboardManager = (ClipboardManager) loginActivity.getSystemService("clipboard");
                        if (clipboardManager != null && clipboardManager.hasPrimaryClip()) {
                            loginActivity.f528o.setText(clipboardManager.getPrimaryClip().getItemAt(0).getText().toString().trim());
                        } else {
                            Toast.makeText(loginActivity, "Nothing to paste", 0).show();
                        }
                        break;
                    default:
                        int i9 = LoginActivity.f527x;
                        ((ClipboardManager) loginActivity.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Device ID", loginActivity.m()));
                        Toast.makeText(loginActivity, "Device ID copied to clipboard", 0).show();
                        break;
                }
            }
        });
        final int i5 = 3;
        this.f534u.setOnClickListener(new View.OnClickListener(this) { // from class: m0.e

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ LoginActivity f1657c;

            {
                this.f1657c = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditText editText;
                TransformationMethod hideReturnsTransformationMethod;
                int i6 = i5;
                int i7 = 1;
                LoginActivity loginActivity = this.f1657c;
                switch (i6) {
                    case 0:
                        String strTrim = loginActivity.f528o.getText().toString().trim();
                        if (!strTrim.isEmpty()) {
                            loginActivity.f531r.setVisibility(8);
                            loginActivity.f529p.setEnabled(false);
                            loginActivity.f529p.setText("Validating...");
                            new Thread(new b(loginActivity, strTrim, i7)).start();
                        } else {
                            loginActivity.f531r.setText("Please enter your access key");
                            loginActivity.f531r.setVisibility(0);
                        }
                        break;
                    case 1:
                        int i8 = LoginActivity.f527x;
                        loginActivity.getClass();
                        try {
                            loginActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://t.me/snappayybot")));
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            return;
                        }
                        break;
                    case 2:
                        if (loginActivity.f536w) {
                            editText = loginActivity.f528o;
                            hideReturnsTransformationMethod = PasswordTransformationMethod.getInstance();
                        } else {
                            editText = loginActivity.f528o;
                            hideReturnsTransformationMethod = HideReturnsTransformationMethod.getInstance();
                        }
                        editText.setTransformationMethod(hideReturnsTransformationMethod);
                        EditText editText2 = loginActivity.f528o;
                        editText2.setSelection(editText2.getText().length());
                        loginActivity.f536w = !loginActivity.f536w;
                        break;
                    case 3:
                        int i9 = LoginActivity.f527x;
                        ClipboardManager clipboardManager = (ClipboardManager) loginActivity.getSystemService("clipboard");
                        if (clipboardManager != null && clipboardManager.hasPrimaryClip()) {
                            loginActivity.f528o.setText(clipboardManager.getPrimaryClip().getItemAt(0).getText().toString().trim());
                        } else {
                            Toast.makeText(loginActivity, "Nothing to paste", 0).show();
                        }
                        break;
                    default:
                        int i10 = LoginActivity.f527x;
                        ((ClipboardManager) loginActivity.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Device ID", loginActivity.m()));
                        Toast.makeText(loginActivity, "Device ID copied to clipboard", 0).show();
                        break;
                }
            }
        });
        final int i6 = 4;
        this.f535v.setOnClickListener(new View.OnClickListener(this) { // from class: m0.e

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ LoginActivity f1657c;

            {
                this.f1657c = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditText editText;
                TransformationMethod hideReturnsTransformationMethod;
                int i7 = i6;
                int i8 = 1;
                LoginActivity loginActivity = this.f1657c;
                switch (i7) {
                    case 0:
                        String strTrim = loginActivity.f528o.getText().toString().trim();
                        if (!strTrim.isEmpty()) {
                            loginActivity.f531r.setVisibility(8);
                            loginActivity.f529p.setEnabled(false);
                            loginActivity.f529p.setText("Validating...");
                            new Thread(new b(loginActivity, strTrim, i8)).start();
                        } else {
                            loginActivity.f531r.setText("Please enter your access key");
                            loginActivity.f531r.setVisibility(0);
                        }
                        break;
                    case 1:
                        int i9 = LoginActivity.f527x;
                        loginActivity.getClass();
                        try {
                            loginActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://t.me/snappayybot")));
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            return;
                        }
                        break;
                    case 2:
                        if (loginActivity.f536w) {
                            editText = loginActivity.f528o;
                            hideReturnsTransformationMethod = PasswordTransformationMethod.getInstance();
                        } else {
                            editText = loginActivity.f528o;
                            hideReturnsTransformationMethod = HideReturnsTransformationMethod.getInstance();
                        }
                        editText.setTransformationMethod(hideReturnsTransformationMethod);
                        EditText editText2 = loginActivity.f528o;
                        editText2.setSelection(editText2.getText().length());
                        loginActivity.f536w = !loginActivity.f536w;
                        break;
                    case 3:
                        int i10 = LoginActivity.f527x;
                        ClipboardManager clipboardManager = (ClipboardManager) loginActivity.getSystemService("clipboard");
                        if (clipboardManager != null && clipboardManager.hasPrimaryClip()) {
                            loginActivity.f528o.setText(clipboardManager.getPrimaryClip().getItemAt(0).getText().toString().trim());
                        } else {
                            Toast.makeText(loginActivity, "Nothing to paste", 0).show();
                        }
                        break;
                    default:
                        int i11 = LoginActivity.f527x;
                        ((ClipboardManager) loginActivity.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Device ID", loginActivity.m()));
                        Toast.makeText(loginActivity, "Device ID copied to clipboard", 0).show();
                        break;
                }
            }
        });
        this.f528o.addTextChangedListener(new d2(this, 1));
    }
}
