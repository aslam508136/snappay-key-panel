package m0;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.widget.TextView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.snapay.app.LoginActivity;
import com.snapay.app.SplashActivity;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class g implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1664a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f1665b;

    public /* synthetic */ g(Object obj, int i2) {
        this.f1664a = i2;
        this.f1665b = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f1664a) {
            case 0:
                LoginActivity loginActivity = (LoginActivity) this.f1665b;
                loginActivity.f529p.setEnabled(true);
                loginActivity.f529p.setText("Login");
                loginActivity.f531r.setText("Network error. Please check your connection");
                loginActivity.f531r.setVisibility(0);
                break;
            case 1:
                TextView textView = (TextView) this.f1665b;
                int i2 = SplashActivity.f568v;
                ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(10.0f, 30.0f);
                valueAnimatorOfFloat.setDuration(1000L);
                valueAnimatorOfFloat.setRepeatCount(-1);
                valueAnimatorOfFloat.setRepeatMode(2);
                valueAnimatorOfFloat.addUpdateListener(new n(textView, 1));
                valueAnimatorOfFloat.start();
                break;
            case 2:
                o0.d dVar = (o0.d) this.f1665b;
                if (dVar.f1761b.f1780b) {
                    dVar.b("Watching", "#4CAF50");
                }
                break;
            case 3:
                p0.b bVar = (p0.b) this.f1665b;
                if (!bVar.f1812h) {
                    bVar.f1812h = true;
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    long j2 = jCurrentTimeMillis - bVar.f1816l;
                    long j3 = jCurrentTimeMillis - bVar.f1817m;
                    long j4 = jCurrentTimeMillis - bVar.f1818n;
                    o0.g gVar = bVar.f1807c;
                    gVar.e(j2);
                    gVar.d(j3);
                    bVar.h("Payment submitted — total: " + j2 + "ms | click-to-pay: " + j3 + "ms | pin-to-pay: " + j4 + "ms");
                    bVar.a("Payment submitted");
                    LocalBroadcastManager.getInstance(bVar.f1805a).sendBroadcast(new Intent("com.snapay.app.PAYMENT_STATS_UPDATED"));
                    break;
                }
                break;
            case 4:
                p0.d dVar2 = (p0.d) this.f1665b;
                if (!dVar2.f1829h) {
                    dVar2.f1829h = true;
                    long jCurrentTimeMillis2 = System.currentTimeMillis();
                    long j5 = jCurrentTimeMillis2 - dVar2.f1833l;
                    long j6 = jCurrentTimeMillis2 - dVar2.f1834m;
                    long j7 = jCurrentTimeMillis2 - dVar2.f1835n;
                    o0.g gVar2 = dVar2.f1824c;
                    gVar2.e(j5);
                    gVar2.d(j6);
                    dVar2.i("Payment submitted — total: " + j5 + "ms | click-to-pay: " + j6 + "ms | pin-to-pay: " + j7 + "ms");
                    dVar2.a("Payment submitted");
                    LocalBroadcastManager.getInstance(dVar2.f1822a).sendBroadcast(new Intent("com.snapay.app.PAYMENT_STATS_UPDATED"));
                    break;
                }
                break;
            case 5:
                q0.b bVar2 = (q0.b) this.f1665b;
                if (!bVar2.f1864g) {
                    bVar2.f1864g = true;
                    long jCurrentTimeMillis3 = System.currentTimeMillis();
                    long j8 = jCurrentTimeMillis3 - bVar2.f1868k;
                    long j9 = jCurrentTimeMillis3 - bVar2.f1869l;
                    long j10 = jCurrentTimeMillis3 - bVar2.f1870m;
                    o0.g gVar3 = bVar2.f1860c;
                    gVar3.e(j8);
                    gVar3.d(j9);
                    bVar2.f("Payment submitted — total: " + j8 + "ms | click-to-pay: " + j9 + "ms | pin-to-pay: " + j10 + "ms");
                    bVar2.a("Payment submitted");
                    LocalBroadcastManager.getInstance(bVar2.f1858a).sendBroadcast(new Intent("com.snapay.app.PAYMENT_STATS_UPDATED"));
                    break;
                }
                break;
            default:
                q0.d dVar3 = (q0.d) this.f1665b;
                if (!dVar3.f1880g) {
                    dVar3.f1880g = true;
                    long jCurrentTimeMillis4 = System.currentTimeMillis();
                    long j11 = jCurrentTimeMillis4 - dVar3.f1884k;
                    long j12 = jCurrentTimeMillis4 - dVar3.f1885l;
                    long j13 = jCurrentTimeMillis4 - dVar3.f1886m;
                    o0.g gVar4 = dVar3.f1876c;
                    gVar4.e(j11);
                    gVar4.d(j12);
                    dVar3.e("Payment submitted — total: " + j11 + "ms | click-to-pay: " + j12 + "ms | pin-to-pay: " + j13 + "ms (cycle #" + dVar3.f1887n + ")");
                    dVar3.a("Payment submitted");
                    LocalBroadcastManager.getInstance(dVar3.f1874a).sendBroadcast(new Intent("com.snapay.app.PAYMENT_STATS_UPDATED"));
                    break;
                }
                break;
        }
    }
}
