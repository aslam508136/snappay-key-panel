package m0;

import android.animation.ValueAnimator;
import android.view.KeyEvent;
import android.widget.TextView;
import com.snapay.app.SplashActivity;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class n implements ValueAnimator.AnimatorUpdateListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1679a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ KeyEvent.Callback f1680b;

    public /* synthetic */ n(KeyEvent.Callback callback, int i2) {
        this.f1679a = i2;
        this.f1680b = callback;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i2 = this.f1679a;
        KeyEvent.Callback callback = this.f1680b;
        switch (i2) {
            case 0:
                SplashActivity splashActivity = (SplashActivity) callback;
                int i3 = SplashActivity.f568v;
                splashActivity.getClass();
                splashActivity.f573s.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                break;
            default:
                TextView textView = (TextView) callback;
                int i4 = SplashActivity.f568v;
                textView.setShadowLayer(((Float) valueAnimator.getAnimatedValue()).floatValue(), 0.0f, 0.0f, textView.getCurrentTextColor());
                break;
        }
    }
}
