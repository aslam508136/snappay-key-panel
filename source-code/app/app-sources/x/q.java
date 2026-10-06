package x;

import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import com.snapay.app.R;

/* JADX INFO: loaded from: classes.dex */
public abstract class q {
    public static void a(WindowInsets windowInsets, View view) {
        View.OnApplyWindowInsetsListener onApplyWindowInsetsListener = (View.OnApplyWindowInsetsListener) view.getTag(R.id.tag_window_insets_animation_callback);
        if (onApplyWindowInsetsListener != null) {
            onApplyWindowInsetsListener.onApplyWindowInsets(view, windowInsets);
        }
    }

    public static l0 b(View view, l0 l0Var, Rect rect) {
        WindowInsets windowInsetsB = l0Var.b();
        if (windowInsetsB != null) {
            return l0.c(view.computeSystemWindowInsets(windowInsetsB, rect), view);
        }
        rect.setEmpty();
        return l0Var;
    }

    public static l0 c(View view) {
        e0 c0Var;
        if (a0.f1961d && view.isAttachedToWindow()) {
            try {
                Object obj = a0.f1958a.get(view.getRootView());
                if (obj != null) {
                    Rect rect = (Rect) a0.f1959b.get(obj);
                    Rect rect2 = (Rect) a0.f1960c.get(obj);
                    if (rect != null && rect2 != null) {
                        int i2 = Build.VERSION.SDK_INT;
                        if (i2 >= 30) {
                            c0Var = new d0();
                        } else {
                            c0Var = i2 >= 29 ? new c0() : new b0();
                        }
                        c0Var.c(r.b.a(rect.left, rect.top, rect.right, rect.bottom));
                        c0Var.d(r.b.a(rect2.left, rect2.top, rect2.right, rect2.bottom));
                        l0 l0VarB = c0Var.b();
                        l0VarB.f2000a.l(l0VarB);
                        l0VarB.f2000a.d(view.getRootView());
                        return l0VarB;
                    }
                }
            } catch (IllegalAccessException e2) {
                Log.w("WindowInsetsCompat", "Failed to get insets from AttachInfo. " + e2.getMessage(), e2);
            }
        }
        return null;
    }

    public static void d(View view, k kVar) {
        if (Build.VERSION.SDK_INT < 30) {
            view.setTag(R.id.tag_on_apply_window_listener, kVar);
        }
        if (kVar == null) {
            view.setOnApplyWindowInsetsListener((View.OnApplyWindowInsetsListener) view.getTag(R.id.tag_window_insets_animation_callback));
        } else {
            view.setOnApplyWindowInsetsListener(new p(view, kVar));
        }
    }
}
