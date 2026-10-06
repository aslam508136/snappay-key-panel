package j;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.snapay.app.R;

/* JADX INFO: loaded from: classes.dex */
public final class d3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f1220a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f1221b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object f1222c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object f1223d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f1224e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f1225f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Object f1226g;

    public d3(Context context, int i2) {
        if (i2 == 1) {
            this.f1220a = context;
            this.f1221b = o0.g.a(context);
            return;
        }
        this.f1223d = new WindowManager.LayoutParams();
        this.f1224e = new Rect();
        this.f1225f = new int[2];
        this.f1226g = new int[2];
        this.f1220a = context;
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.abc_tooltip, (ViewGroup) null);
        this.f1221b = viewInflate;
        this.f1222c = (TextView) viewInflate.findViewById(R.id.message);
        ((WindowManager.LayoutParams) this.f1223d).setTitle(d3.class.getSimpleName());
        ((WindowManager.LayoutParams) this.f1223d).packageName = context.getPackageName();
        WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) this.f1223d;
        layoutParams.type = 1002;
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.format = -3;
        layoutParams.windowAnimations = R.style.Animation_AppCompat_Tooltip;
        layoutParams.flags = 24;
    }

    public final void a(Button button, Button button2, TextView textView, TextView textView2, TextView textView3, LinearLayout linearLayout, TextView textView4, TextView textView5, TextView textView6, Button button3) {
        this.f1226g = new o0.d(this.f1220a, button, button2, textView, textView2, textView3, linearLayout, textView4, textView5, textView6, button3);
    }
}
