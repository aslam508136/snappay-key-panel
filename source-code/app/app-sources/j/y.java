package j;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class y {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final PorterDuff.Mode f1495b = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static y f1496c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a2 f1497a;

    public static synchronized y a() {
        if (f1496c == null) {
            c();
        }
        return f1496c;
    }

    public static synchronized void c() {
        if (f1496c == null) {
            y yVar = new y();
            f1496c = yVar;
            yVar.f1497a = a2.d();
            f1496c.f1497a.l(new x());
        }
    }

    public static void d(Drawable drawable, t2 t2Var, int[] iArr) {
        PorterDuff.Mode mode = a2.f1147h;
        if (g1.a(drawable) && drawable.mutate() != drawable) {
            Log.d("ResourceManagerInternal", "Mutated drawable is not the same instance as the input.");
            return;
        }
        boolean z2 = t2Var.f1445d;
        if (z2 || t2Var.f1444c) {
            PorterDuffColorFilter porterDuffColorFilterH = null;
            ColorStateList colorStateList = z2 ? t2Var.f1442a : null;
            PorterDuff.Mode mode2 = t2Var.f1444c ? t2Var.f1443b : a2.f1147h;
            if (colorStateList != null && mode2 != null) {
                porterDuffColorFilterH = a2.h(colorStateList.getColorForState(iArr, 0), mode2);
            }
            drawable.setColorFilter(porterDuffColorFilterH);
        } else {
            drawable.clearColorFilter();
        }
        if (Build.VERSION.SDK_INT <= 23) {
            drawable.invalidateSelf();
        }
    }

    public final synchronized Drawable b(Context context, int i2) {
        return this.f1497a.f(context, i2);
    }
}
