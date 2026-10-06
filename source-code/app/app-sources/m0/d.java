package m0;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.snapay.app.R;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static d f1650f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f1651a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f1652b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f1653c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public View f1654d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f1655e;

    public d(Context context) {
        this.f1653c = new Handler(Looper.getMainLooper());
        Context applicationContext = context.getApplicationContext();
        this.f1651a = applicationContext;
        this.f1652b = (WindowManager) applicationContext.getSystemService("window");
    }

    public static d b(Context context) {
        if (f1650f == null) {
            f1650f = new d(context);
        }
        return f1650f;
    }

    public static void d(LinearLayout linearLayout, int i2) {
        for (int i3 = 0; i3 < linearLayout.getChildCount(); i3++) {
            View childAt = linearLayout.getChildAt(i3);
            if (childAt instanceof TextView) {
                ((TextView) childAt).setTextColor(i2);
            }
        }
    }

    public final void a() {
        Object obj = this.f1655e;
        if (((Runnable) obj) != null) {
            ((Handler) this.f1653c).removeCallbacks((Runnable) obj);
            this.f1655e = null;
        }
        View view = this.f1654d;
        if (view != null) {
            try {
                ((WindowManager) this.f1652b).removeView(view);
            } catch (Exception unused) {
            }
            this.f1654d = null;
        }
    }

    public final void c(String str) {
        o0.g gVar = (o0.g) this.f1652b;
        gVar.f1779a.edit().putString("payment_mode", str).apply();
        gVar.f1781c = str;
        f(str);
        o0.d dVar = (o0.d) this.f1655e;
        if (dVar != null) {
            dVar.a();
        }
    }

    public final void e() {
        ((Handler) this.f1653c).post(new b(this, "Wrong screen! Restart PhonePe & AutoPay Dubara Activate Kiiye", 0));
    }

    public final void f(String str) {
        boolean zEquals = str.equals("PAY_ALL");
        Object obj = this.f1653c;
        if (zEquals) {
            LinearLayout linearLayout = (LinearLayout) obj;
            linearLayout.setBackgroundResource(R.drawable.btn_pay_all_selected);
            d(linearLayout, Color.parseColor("#1A1446"));
            ((LinearLayout) this.f1654d).setBackgroundResource(R.drawable.btn_one_time);
            d((LinearLayout) this.f1654d, Color.parseColor("#4C3FC1"));
            return;
        }
        ((LinearLayout) this.f1654d).setBackgroundResource(R.drawable.btn_one_time_selected);
        d((LinearLayout) this.f1654d, Color.parseColor("#4C3FC1"));
        LinearLayout linearLayout2 = (LinearLayout) obj;
        linearLayout2.setBackgroundResource(R.drawable.btn_pay_all);
        d(linearLayout2, Color.parseColor("#1A1446"));
    }

    public d(Context context, LinearLayout linearLayout, LinearLayout linearLayout2) {
        this.f1651a = context;
        o0.g gVarA = o0.g.a(context);
        this.f1652b = gVarA;
        this.f1653c = linearLayout;
        this.f1654d = linearLayout2;
        f(gVarA.f1781c);
        final int i2 = 0;
        linearLayout.setOnClickListener(new View.OnClickListener(this) { // from class: o0.f

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ m0.d f1777c;

            {
                this.f1777c = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i3 = i2;
                m0.d dVar = this.f1777c;
                switch (i3) {
                    case 0:
                        dVar.c("PAY_ALL");
                        break;
                    default:
                        dVar.c("ONE_TIME");
                        break;
                }
            }
        });
        final int i3 = 1;
        ((LinearLayout) this.f1654d).setOnClickListener(new View.OnClickListener(this) { // from class: o0.f

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ m0.d f1777c;

            {
                this.f1777c = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i4 = i3;
                m0.d dVar = this.f1777c;
                switch (i4) {
                    case 0:
                        dVar.c("PAY_ALL");
                        break;
                    default:
                        dVar.c("ONE_TIME");
                        break;
                }
            }
        });
    }
}
