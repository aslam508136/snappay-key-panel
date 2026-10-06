package androidx.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import com.snapay.app.MainActivity;
import j.i1;
import j.m;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import java.util.WeakHashMap;
import x.u;

/* JADX INFO: loaded from: classes.dex */
public final class b implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f49a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f50b;

    public /* synthetic */ b(Object obj, int i2) {
        this.f49a = i2;
        this.f50b = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        TextView textView;
        String str;
        String str2;
        TextView textView2;
        String str3;
        b bVar;
        m mVar;
        int i2 = this.f49a;
        Object obj = this.f50b;
        switch (i2) {
            case 0:
                try {
                    super/*android.app.Activity*/.onBackPressed();
                    return;
                } catch (IllegalStateException e2) {
                    if (!TextUtils.equals(e2.getMessage(), "Can not perform this action after onSaveInstanceState")) {
                        throw e2;
                    }
                    return;
                }
            case 1:
                f.h hVar = (f.h) obj;
                hVar.a(true);
                hVar.invalidateSelf();
                return;
            case 2:
                i1 i1Var = (i1) obj;
                i1Var.f1263n = null;
                i1Var.drawableStateChanged();
                return;
            case 3:
                ActionMenuView actionMenuView = ((Toolbar) obj).f210b;
                if (actionMenuView == null || (mVar = actionMenuView.f170u) == null) {
                    return;
                }
                mVar.l();
                return;
            case 4:
                Activity activity = (Activity) obj;
                if (activity.isFinishing() || o.c.a(activity)) {
                    return;
                }
                activity.recreate();
                return;
            case 5:
                a0.d dVar = (a0.d) obj;
                if (dVar.f27o) {
                    boolean z2 = dVar.f25m;
                    a0.a aVar = dVar.f13a;
                    if (z2) {
                        dVar.f25m = false;
                        aVar.getClass();
                        long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
                        aVar.f6e = jCurrentAnimationTimeMillis;
                        aVar.f8g = -1L;
                        aVar.f7f = jCurrentAnimationTimeMillis;
                        aVar.f9h = 0.5f;
                    }
                    if ((aVar.f8g > 0 && AnimationUtils.currentAnimationTimeMillis() > aVar.f8g + ((long) aVar.f10i)) || !dVar.f()) {
                        dVar.f27o = false;
                        return;
                    }
                    boolean z3 = dVar.f26n;
                    View view = dVar.f15c;
                    if (z3) {
                        dVar.f26n = false;
                        long jUptimeMillis = SystemClock.uptimeMillis();
                        MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                        view.onTouchEvent(motionEventObtain);
                        motionEventObtain.recycle();
                    }
                    if (aVar.f7f == 0) {
                        throw new RuntimeException("Cannot compute scroll delta before calling start()");
                    }
                    long jCurrentAnimationTimeMillis2 = AnimationUtils.currentAnimationTimeMillis();
                    float fA = aVar.a(jCurrentAnimationTimeMillis2);
                    long j2 = jCurrentAnimationTimeMillis2 - aVar.f7f;
                    aVar.f7f = jCurrentAnimationTimeMillis2;
                    dVar.f29q.scrollListBy((int) (j2 * ((fA * 4.0f) + ((-4.0f) * fA * fA)) * aVar.f5d));
                    WeakHashMap weakHashMap = u.f2012a;
                    view.postOnAnimation(this);
                    return;
                }
                return;
            default:
                MainActivity mainActivity = (MainActivity) obj;
                int i3 = MainActivity.s0;
                mainActivity.getClass();
                String string = m0.m.a(mainActivity).getString("expires_at", "");
                if (!string.isEmpty()) {
                    try {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
                        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                        long time = simpleDateFormat.parse(string).getTime() - System.currentTimeMillis();
                        int i4 = 3;
                        if (time > 0) {
                            long j3 = time / 1000;
                            long j4 = j3 / 60;
                            long j5 = j4 / 60;
                            long j6 = j5 / 24;
                            long j7 = j6 / 7;
                            long j8 = j6 / 30;
                            if (j8 > 0) {
                                str2 = String.format("%dm %dw %dd %dh %dm %ds", Long.valueOf(j8), Long.valueOf(j7 % 4), Long.valueOf(j6 % 7), Long.valueOf(j5 % 24), Long.valueOf(j4 % 60), Long.valueOf(j3 % 60));
                            } else if (j7 > 0) {
                                str2 = String.format("%dw %dd %dh %dm %ds", Long.valueOf(j7), Long.valueOf(j6 % 7), Long.valueOf(j5 % 24), Long.valueOf(j4 % 60), Long.valueOf(j3 % 60));
                            } else if (j6 > 0) {
                                str2 = String.format("%dd %dh %dm %ds", Long.valueOf(j6), Long.valueOf(j5 % 24), Long.valueOf(j4 % 60), Long.valueOf(j3 % 60));
                            } else if (j5 > 0) {
                                str2 = String.format("%dh %dm %ds", Long.valueOf(j5), Long.valueOf(j4 % 60), Long.valueOf(j3 % 60));
                            } else {
                                str2 = j4 > 0 ? String.format("%dm %ds", Long.valueOf(j4), Long.valueOf(j3 % 60)) : String.format("%ds", Long.valueOf(j3));
                            }
                            mainActivity.f547l0.setText(str2);
                            if (time < 86400000) {
                                textView2 = mainActivity.f547l0;
                                str3 = "#EF4444";
                            } else if (time < 604800000) {
                                textView2 = mainActivity.f547l0;
                                str3 = "#F59E0B";
                            } else {
                                textView2 = mainActivity.f547l0;
                                str3 = "#4C3FC1";
                            }
                            textView2.setTextColor(Color.parseColor(str3));
                        } else if (!mainActivity.f551o0) {
                            mainActivity.f551o0 = true;
                            mainActivity.f547l0.setText("Expired");
                            mainActivity.f547l0.setTextColor(Color.parseColor("#FF0000"));
                            Handler handler = mainActivity.f548m0;
                            if (handler != null && (bVar = mainActivity.f549n0) != null) {
                                handler.removeCallbacks(bVar);
                            }
                            Toast.makeText(mainActivity, "Your subscription has expired. Please login again.", 1).show();
                            new Handler().postDelayed(new m0.h(mainActivity, i4), 2000L);
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        textView = mainActivity.f547l0;
                        str = "Error";
                        textView.setText(str);
                    }
                    mainActivity.f548m0.postDelayed(this, 1000L);
                    return;
                }
                textView = mainActivity.f547l0;
                str = "N/A";
                textView.setText(str);
                mainActivity.f548m0.postDelayed(this, 1000L);
                return;
        }
    }
}
