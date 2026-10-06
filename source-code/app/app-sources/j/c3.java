package j;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.widget.TextView;
import com.google.crypto.tink.shaded.protobuf.Reader;
import com.snapay.app.R;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class c3 implements View.OnLongClickListener, View.OnHoverListener, View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static c3 f1206j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static c3 f1207k;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f1208a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final CharSequence f1209b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1210c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final b3 f1211d = new b3(this, 0);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final b3 f1212e = new b3(this, 1);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1213f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1214g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public d3 f1215h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1216i;

    public c3(View view, CharSequence charSequence) {
        this.f1208a = view;
        this.f1209b = charSequence;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(view.getContext());
        int i2 = x.v.f2016a;
        this.f1210c = Build.VERSION.SDK_INT >= 28 ? viewConfiguration.getScaledHoverSlop() : viewConfiguration.getScaledTouchSlop() / 2;
        this.f1213f = Reader.READ_DONE;
        this.f1214g = Reader.READ_DONE;
        view.setOnLongClickListener(this);
        view.setOnHoverListener(this);
    }

    public static void b(c3 c3Var) {
        c3 c3Var2 = f1206j;
        if (c3Var2 != null) {
            c3Var2.f1208a.removeCallbacks(c3Var2.f1211d);
        }
        f1206j = c3Var;
        if (c3Var != null) {
            c3Var.f1208a.postDelayed(c3Var.f1211d, ViewConfiguration.getLongPressTimeout());
        }
    }

    public final void a() {
        c3 c3Var = f1207k;
        View view = this.f1208a;
        if (c3Var == this) {
            f1207k = null;
            d3 d3Var = this.f1215h;
            if (d3Var != null) {
                View view2 = (View) d3Var.f1221b;
                if (view2.getParent() != null) {
                    ((WindowManager) d3Var.f1220a.getSystemService("window")).removeView(view2);
                }
                this.f1215h = null;
                this.f1213f = Reader.READ_DONE;
                this.f1214g = Reader.READ_DONE;
                view.removeOnAttachStateChangeListener(this);
            } else {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }
        if (f1206j == this) {
            b(null);
        }
        view.removeCallbacks(this.f1212e);
    }

    public final void c(boolean z2) {
        int height;
        int i2;
        long longPressTimeout;
        long j2;
        long j3;
        WeakHashMap weakHashMap = x.u.f2012a;
        View view = this.f1208a;
        if (view.isAttachedToWindow()) {
            b(null);
            c3 c3Var = f1207k;
            if (c3Var != null) {
                c3Var.a();
            }
            f1207k = this;
            this.f1216i = z2;
            d3 d3Var = new d3(view.getContext(), 0);
            this.f1215h = d3Var;
            int width = this.f1213f;
            int i3 = this.f1214g;
            boolean z3 = this.f1216i;
            Object obj = d3Var.f1221b;
            boolean z4 = ((View) obj).getParent() != null;
            Context context = d3Var.f1220a;
            if (z4) {
                View view2 = (View) obj;
                if (view2.getParent() != null) {
                    ((WindowManager) context.getSystemService("window")).removeView(view2);
                }
            }
            ((TextView) d3Var.f1222c).setText(this.f1209b);
            WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) d3Var.f1223d;
            layoutParams.token = view.getApplicationWindowToken();
            int dimensionPixelOffset = context.getResources().getDimensionPixelOffset(R.dimen.tooltip_precise_anchor_threshold);
            if (view.getWidth() < dimensionPixelOffset) {
                width = view.getWidth() / 2;
            }
            if (view.getHeight() >= dimensionPixelOffset) {
                int dimensionPixelOffset2 = context.getResources().getDimensionPixelOffset(R.dimen.tooltip_precise_anchor_extra_offset);
                height = i3 + dimensionPixelOffset2;
                i2 = i3 - dimensionPixelOffset2;
            } else {
                height = view.getHeight();
                i2 = 0;
            }
            layoutParams.gravity = 49;
            int dimensionPixelOffset3 = context.getResources().getDimensionPixelOffset(z3 ? R.dimen.tooltip_y_offset_touch : R.dimen.tooltip_y_offset_non_touch);
            View rootView = view.getRootView();
            ViewGroup.LayoutParams layoutParams2 = rootView.getLayoutParams();
            if (!(layoutParams2 instanceof WindowManager.LayoutParams) || ((WindowManager.LayoutParams) layoutParams2).type != 2) {
                for (Context context2 = view.getContext(); context2 instanceof ContextWrapper; context2 = ((ContextWrapper) context2).getBaseContext()) {
                    if (context2 instanceof Activity) {
                        rootView = ((Activity) context2).getWindow().getDecorView();
                        break;
                    }
                }
            }
            if (rootView == null) {
                Log.e("TooltipPopup", "Cannot find app view");
            } else {
                rootView.getWindowVisibleDisplayFrame((Rect) d3Var.f1224e);
                Rect rect = (Rect) d3Var.f1224e;
                if (rect.left < 0 && rect.top < 0) {
                    Resources resources = context.getResources();
                    int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
                    int dimensionPixelSize = identifier != 0 ? resources.getDimensionPixelSize(identifier) : 0;
                    DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                    ((Rect) d3Var.f1224e).set(0, dimensionPixelSize, displayMetrics.widthPixels, displayMetrics.heightPixels);
                }
                rootView.getLocationOnScreen((int[]) d3Var.f1226g);
                view.getLocationOnScreen((int[]) d3Var.f1225f);
                int[] iArr = (int[]) d3Var.f1225f;
                int i4 = iArr[0];
                int[] iArr2 = (int[]) d3Var.f1226g;
                int i5 = i4 - iArr2[0];
                iArr[0] = i5;
                iArr[1] = iArr[1] - iArr2[1];
                layoutParams.x = (i5 + width) - (rootView.getWidth() / 2);
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                View view3 = (View) obj;
                view3.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                int measuredHeight = view3.getMeasuredHeight();
                int i6 = ((int[]) d3Var.f1225f)[1];
                int i7 = ((i2 + i6) - dimensionPixelOffset3) - measuredHeight;
                int i8 = i6 + height + dimensionPixelOffset3;
                if (!z3 ? measuredHeight + i8 > ((Rect) d3Var.f1224e).height() : i7 >= 0) {
                    layoutParams.y = i7;
                } else {
                    layoutParams.y = i8;
                }
            }
            ((WindowManager) context.getSystemService("window")).addView((View) obj, (WindowManager.LayoutParams) d3Var.f1223d);
            view.addOnAttachStateChangeListener(this);
            if (this.f1216i) {
                j3 = 2500;
            } else {
                if ((view.getWindowSystemUiVisibility() & 1) == 1) {
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                    j2 = 3000;
                } else {
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                    j2 = 15000;
                }
                j3 = j2 - longPressTimeout;
            }
            b3 b3Var = this.f1212e;
            view.removeCallbacks(b3Var);
            view.postDelayed(b3Var, j3);
        }
    }

    @Override // android.view.View.OnHoverListener
    public final boolean onHover(View view, MotionEvent motionEvent) {
        boolean z2;
        if (this.f1215h != null && this.f1216i) {
            return false;
        }
        View view2 = this.f1208a;
        AccessibilityManager accessibilityManager = (AccessibilityManager) view2.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 7) {
            if (action == 10) {
                this.f1213f = Reader.READ_DONE;
                this.f1214g = Reader.READ_DONE;
                a();
            }
        } else if (view2.isEnabled() && this.f1215h == null) {
            int x2 = (int) motionEvent.getX();
            int y2 = (int) motionEvent.getY();
            int iAbs = Math.abs(x2 - this.f1213f);
            int i2 = this.f1210c;
            if (iAbs > i2 || Math.abs(y2 - this.f1214g) > i2) {
                this.f1213f = x2;
                this.f1214g = y2;
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                b(this);
            }
        }
        return false;
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        this.f1213f = view.getWidth() / 2;
        this.f1214g = view.getHeight() / 2;
        c(true);
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        a();
    }
}
