package i;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import com.snapay.app.R;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f1125a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final o f1126b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f1127c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f1128d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f1129e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public View f1130f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1131g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1132h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public a0 f1133i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public x f1134j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public PopupWindow.OnDismissListener f1135k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final y f1136l;

    public z(int i2, int i3, Context context, View view, o oVar, boolean z2) {
        this.f1131g = 8388611;
        this.f1136l = new y(this);
        this.f1125a = context;
        this.f1126b = oVar;
        this.f1130f = view;
        this.f1127c = z2;
        this.f1128d = i2;
        this.f1129e = i3;
    }

    public final x a() {
        x g0Var;
        if (this.f1134j == null) {
            Context context = this.f1125a;
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getRealSize(point);
            if (Math.min(point.x, point.y) >= context.getResources().getDimensionPixelSize(R.dimen.abc_cascading_menus_min_smallest_width)) {
                g0Var = new i(this.f1125a, this.f1130f, this.f1128d, this.f1129e, this.f1127c);
            } else {
                g0Var = new g0(this.f1128d, this.f1129e, this.f1125a, this.f1130f, this.f1126b, this.f1127c);
            }
            g0Var.l(this.f1126b);
            g0Var.r(this.f1136l);
            g0Var.n(this.f1130f);
            g0Var.g(this.f1133i);
            g0Var.o(this.f1132h);
            g0Var.p(this.f1131g);
            this.f1134j = g0Var;
        }
        return this.f1134j;
    }

    public final boolean b() {
        x xVar = this.f1134j;
        return xVar != null && xVar.b();
    }

    public void c() {
        this.f1134j = null;
        PopupWindow.OnDismissListener onDismissListener = this.f1135k;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    public final void d(int i2, int i3, boolean z2, boolean z3) {
        x xVarA = a();
        xVarA.s(z3);
        if (z2) {
            int i4 = this.f1131g;
            View view = this.f1130f;
            WeakHashMap weakHashMap = x.u.f2012a;
            if ((Gravity.getAbsoluteGravity(i4, view.getLayoutDirection()) & 7) == 5) {
                i2 -= this.f1130f.getWidth();
            }
            xVarA.q(i2);
            xVarA.t(i3);
            int i5 = (int) ((this.f1125a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            xVarA.f1123b = new Rect(i2 - i5, i3 - i5, i2 + i5, i3 + i5);
        }
        xVarA.f();
    }

    public z(Context context, o oVar, View view, boolean z2) {
        this(R.attr.actionOverflowMenuStyle, 0, context, view, oVar, z2);
    }
}
