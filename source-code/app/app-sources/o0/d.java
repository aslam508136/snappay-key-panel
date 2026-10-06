package o0;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.snapay.app.R;
import d.w;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f1760a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g f1761b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Button f1762c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Button f1763d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final TextView f1764e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final TextView f1765f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final TextView f1766g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final LinearLayout f1767h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final TextView f1768i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final TextView f1769j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final TextView f1770k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public w f1771l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Handler f1772m = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final m0.g f1773n;

    public d(Context context, Button button, Button button2, TextView textView, TextView textView2, TextView textView3, LinearLayout linearLayout, TextView textView4, TextView textView5, TextView textView6, Button button3) {
        int i2 = 2;
        this.f1773n = new m0.g(this, i2);
        this.f1760a = context;
        g gVarA = g.a(context);
        this.f1761b = gVarA;
        this.f1762c = button;
        this.f1763d = button3;
        this.f1764e = textView;
        this.f1765f = textView2;
        this.f1766g = textView3;
        this.f1767h = linearLayout;
        this.f1768i = textView4;
        this.f1769j = textView5;
        this.f1770k = textView6;
        d(gVarA.f1780b);
        e();
        button.setOnClickListener(new c(this, 0));
        if (button3 != null) {
            button3.setOnClickListener(new c(this, 1));
        }
        button2.setOnClickListener(new c(this, i2));
        this.f1771l = new w(this, i2);
        LocalBroadcastManager.getInstance(context).registerReceiver(this.f1771l, new IntentFilter("com.snapay.app.BOT_STATUS"));
    }

    public final void a() {
        if (this.f1761b.f1780b) {
            c(false);
        }
        if (this.f1771l != null) {
            try {
                LocalBroadcastManager.getInstance(this.f1760a).unregisterReceiver(this.f1771l);
            } catch (Exception unused) {
            }
            this.f1771l = null;
        }
        this.f1772m.removeCallbacks(this.f1773n);
    }

    public final void b(String str, String str2) {
        TextView textView = this.f1766g;
        if (textView != null) {
            textView.setText(str);
            textView.setTextColor(Color.parseColor(str2));
        }
    }

    public final void c(boolean z2) {
        this.f1761b.f(z2);
        d(z2);
        Context context = this.f1760a;
        m0.a.f(context).t(z2 ? "Autopay activated" : "Autopay deactivated");
        if (!z2) {
            this.f1772m.removeCallbacks(this.f1773n);
        }
        Intent intent = new Intent();
        intent.setAction(z2 ? "com.snapay.app.AUTOPAY_ACTIVATED" : "com.snapay.app.AUTOPAY_DEACTIVATED");
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    public final void d(boolean z2) {
        int i2;
        Button button = this.f1763d;
        LinearLayout linearLayout = this.f1767h;
        TextView textView = this.f1766g;
        TextView textView2 = this.f1765f;
        TextView textView3 = this.f1764e;
        Button button2 = this.f1762c;
        if (z2) {
            button2.setText("DEACTIVATE AUTOPAY");
            button2.setTextColor(Color.parseColor("#FF0000"));
            button2.setBackgroundResource(R.drawable.btn_deactivate);
            textView3.setText("⚡ AUTOPAY ACTIVE");
            textView3.setTextColor(Color.parseColor("#4CAF50"));
            textView2.setText("● ACTIVE");
            textView2.setTextColor(Color.parseColor("#4CAF50"));
            textView.setText("Watching");
            textView.setTextColor(Color.parseColor("#4CAF50"));
            linearLayout.setBackgroundResource(R.drawable.status_active_bg);
            if (button == null) {
                return;
            } else {
                i2 = 0;
            }
        } else {
            button2.setText("⚡ ACTIVATE AUTOPAY");
            button2.setTextColor(Color.parseColor("#FFFFFF"));
            button2.setBackgroundResource(R.drawable.btn_activate);
            textView3.setText("⚡ AUTOPAY INACTIVE");
            textView3.setTextColor(Color.parseColor("#FF6600"));
            textView2.setText("● STANDBY");
            textView2.setTextColor(Color.parseColor("#FF6600"));
            textView.setText("Not-Watching");
            textView.setTextColor(Color.parseColor("#999999"));
            linearLayout.setBackgroundResource(R.drawable.status_inactive_bg);
            if (button == null) {
                return;
            } else {
                i2 = 8;
            }
        }
        button.setVisibility(i2);
    }

    public final void e() {
        TextView textView;
        TextView textView2;
        TextView textView3 = this.f1768i;
        if (textView3 == null || (textView = this.f1769j) == null || (textView2 = this.f1770k) == null) {
            return;
        }
        g gVar = this.f1761b;
        long j2 = gVar.f1779a.getLong("last_payment_time", 0L);
        SharedPreferences sharedPreferences = gVar.f1779a;
        int i2 = sharedPreferences.getInt("total_payments", 0);
        int i3 = sharedPreferences.getInt("total_payments", 0);
        long j3 = i3 != 0 ? sharedPreferences.getLong("total_time", 0L) / ((long) i3) : 0L;
        textView3.setText(j2 + "ms");
        textView.setText(String.valueOf(i2));
        textView2.setText(j3 + "ms");
    }
}
