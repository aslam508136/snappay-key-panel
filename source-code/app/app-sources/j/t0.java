package j;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1413a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1414b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f1415c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object f1416d;

    public t0(v0 v0Var, int i2, int i3, WeakReference weakReference) {
        this.f1416d = v0Var;
        this.f1413a = i2;
        this.f1414b = i3;
        this.f1415c = weakReference;
    }

    public final void a() {
        new Handler(Looper.getMainLooper()).post(new q.f(this));
    }

    public final void b(Typeface typeface) {
        new Handler(Looper.getMainLooper()).post(new j(this, typeface, 4));
    }

    public final void c(Typeface typeface) {
        int i2;
        if (Build.VERSION.SDK_INT >= 28 && (i2 = this.f1413a) != -1) {
            typeface = Typeface.create(typeface, i2, (this.f1414b & 2) != 0);
        }
        v0 v0Var = (v0) this.f1416d;
        WeakReference weakReference = (WeakReference) this.f1415c;
        if (v0Var.f1466m) {
            v0Var.f1465l = typeface;
            TextView textView = (TextView) weakReference.get();
            if (textView != null) {
                WeakHashMap weakHashMap = x.u.f2012a;
                if (textView.isAttachedToWindow()) {
                    textView.post(new u0(textView, typeface, v0Var.f1463j));
                } else {
                    textView.setTypeface(typeface, v0Var.f1463j);
                }
            }
        }
    }
}
