package m0;

import android.os.Handler;
import com.snapay.app.MainActivity;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class h implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1666a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ MainActivity f1667b;

    public /* synthetic */ h(MainActivity mainActivity, int i2) {
        this.f1666a = i2;
        this.f1667b = mainActivity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i2 = this.f1666a;
        MainActivity mainActivity = this.f1667b;
        switch (i2) {
            case 0:
                mainActivity.f555q0.scrollTo(0, 0);
                break;
            case 1:
                int i3 = MainActivity.s0;
                mainActivity.o();
                break;
            case 2:
                int i4 = MainActivity.s0;
                mainActivity.getClass();
                Handler handler = new Handler();
                mainActivity.f548m0 = handler;
                androidx.activity.b bVar = new androidx.activity.b(mainActivity, 6);
                mainActivity.f549n0 = bVar;
                handler.post(bVar);
                break;
            default:
                int i5 = MainActivity.s0;
                mainActivity.o();
                break;
        }
    }
}
