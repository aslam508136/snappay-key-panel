package j;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import com.google.crypto.tink.shaded.protobuf.Reader;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class t1 implements i.f0 {
    public static final Method A;
    public static final Method B;
    public static final Method C;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f1417b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ListAdapter f1418c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public i1 f1419d;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1422g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1423h;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f1425j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f1426k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f1427l;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public q1 f1430o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public View f1431p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public AdapterView.OnItemClickListener f1432q;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final Handler f1437v;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public Rect f1439x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f1440y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final d0 f1441z;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f1420e = -2;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1421f = -2;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f1424i = 1002;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f1428m = 0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f1429n = Reader.READ_DONE;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final o1 f1433r = new o1(this, 2);

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final s1 f1434s = new s1(this);

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final r1 f1435t = new r1(this);

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final o1 f1436u = new o1(this, 1);

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final Rect f1438w = new Rect();

    static {
        if (Build.VERSION.SDK_INT <= 28) {
            try {
                A = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
            } catch (NoSuchMethodException unused) {
                Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
            try {
                C = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
            } catch (NoSuchMethodException unused2) {
                Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
            }
        }
        if (Build.VERSION.SDK_INT <= 23) {
            try {
                B = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", View.class, Integer.TYPE, Boolean.TYPE);
            } catch (NoSuchMethodException unused3) {
                Log.i("ListPopupWindow", "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.");
            }
        }
    }

    public t1(Context context, AttributeSet attributeSet, int i2, int i3) {
        this.f1417b = context;
        this.f1437v = new Handler(context.getMainLooper());
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, c.a.f494n, i2, i3);
        this.f1422g = typedArrayObtainStyledAttributes.getDimensionPixelOffset(0, 0);
        int dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(1, 0);
        this.f1423h = dimensionPixelOffset;
        if (dimensionPixelOffset != 0) {
            this.f1425j = true;
        }
        typedArrayObtainStyledAttributes.recycle();
        d0 d0Var = new d0(context, attributeSet, i2, i3);
        this.f1441z = d0Var;
        d0Var.setInputMethodMode(1);
    }

    public final void a(int i2) {
        this.f1422g = i2;
    }

    @Override // i.f0
    public final boolean b() {
        return this.f1441z.isShowing();
    }

    public final int c() {
        return this.f1422g;
    }

    @Override // i.f0
    public final void dismiss() {
        d0 d0Var = this.f1441z;
        d0Var.dismiss();
        d0Var.setContentView(null);
        this.f1419d = null;
        this.f1437v.removeCallbacks(this.f1433r);
    }

    @Override // i.f0
    public final void f() {
        int i2;
        int maxAvailableHeight;
        int iMakeMeasureSpec;
        int paddingBottom;
        i1 i1Var;
        i1 i1Var2 = this.f1419d;
        d0 d0Var = this.f1441z;
        Context context = this.f1417b;
        if (i1Var2 == null) {
            i1 i1VarQ = q(context, !this.f1440y);
            this.f1419d = i1VarQ;
            i1VarQ.setAdapter(this.f1418c);
            this.f1419d.setOnItemClickListener(this.f1432q);
            this.f1419d.setFocusable(true);
            this.f1419d.setFocusableInTouchMode(true);
            this.f1419d.setOnItemSelectedListener(new p1(this, 0));
            this.f1419d.setOnScrollListener(this.f1435t);
            d0Var.setContentView(this.f1419d);
        }
        Drawable background = d0Var.getBackground();
        Rect rect = this.f1438w;
        if (background != null) {
            background.getPadding(rect);
            int i3 = rect.top;
            i2 = rect.bottom + i3;
            if (!this.f1425j) {
                this.f1423h = -i3;
            }
        } else {
            rect.setEmpty();
            i2 = 0;
        }
        boolean z2 = d0Var.getInputMethodMode() == 2;
        View view = this.f1431p;
        int i4 = this.f1423h;
        if (Build.VERSION.SDK_INT <= 23) {
            Method method = B;
            if (method != null) {
                try {
                    maxAvailableHeight = ((Integer) method.invoke(d0Var, view, Integer.valueOf(i4), Boolean.valueOf(z2))).intValue();
                } catch (Exception unused) {
                    Log.i("ListPopupWindow", "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
                    maxAvailableHeight = d0Var.getMaxAvailableHeight(view, i4);
                }
            } else {
                maxAvailableHeight = d0Var.getMaxAvailableHeight(view, i4);
            }
        } else {
            maxAvailableHeight = d0Var.getMaxAvailableHeight(view, i4, z2);
        }
        int i5 = this.f1420e;
        if (i5 == -1) {
            paddingBottom = maxAvailableHeight + i2;
        } else {
            int i6 = this.f1421f;
            if (i6 != -2) {
                iMakeMeasureSpec = i6 != -1 ? View.MeasureSpec.makeMeasureSpec(i6, 1073741824) : View.MeasureSpec.makeMeasureSpec(context.getResources().getDisplayMetrics().widthPixels - (rect.left + rect.right), 1073741824);
            } else {
                iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(context.getResources().getDisplayMetrics().widthPixels - (rect.left + rect.right), Integer.MIN_VALUE);
            }
            int iA = this.f1419d.a(iMakeMeasureSpec, maxAvailableHeight + 0);
            paddingBottom = iA + (iA > 0 ? this.f1419d.getPaddingBottom() + this.f1419d.getPaddingTop() + i2 + 0 : 0);
        }
        boolean z3 = d0Var.getInputMethodMode() == 2;
        androidx.lifecycle.i.h0(d0Var, this.f1424i);
        if (d0Var.isShowing()) {
            View view2 = this.f1431p;
            WeakHashMap weakHashMap = x.u.f2012a;
            if (view2.isAttachedToWindow()) {
                int width = this.f1421f;
                if (width == -1) {
                    width = -1;
                } else if (width == -2) {
                    width = this.f1431p.getWidth();
                }
                if (i5 == -1) {
                    i5 = z3 ? paddingBottom : -1;
                    int i7 = this.f1421f;
                    if (z3) {
                        d0Var.setWidth(i7 == -1 ? -1 : 0);
                        d0Var.setHeight(0);
                    } else {
                        d0Var.setWidth(i7 == -1 ? -1 : 0);
                        d0Var.setHeight(-1);
                    }
                } else if (i5 == -2) {
                    i5 = paddingBottom;
                }
                d0Var.setOutsideTouchable(true);
                View view3 = this.f1431p;
                int i8 = this.f1422g;
                int i9 = this.f1423h;
                if (width < 0) {
                    width = -1;
                }
                d0Var.update(view3, i8, i9, width, i5 < 0 ? -1 : i5);
                return;
            }
            return;
        }
        int width2 = this.f1421f;
        if (width2 == -1) {
            width2 = -1;
        } else if (width2 == -2) {
            width2 = this.f1431p.getWidth();
        }
        if (i5 == -1) {
            i5 = -1;
        } else if (i5 == -2) {
            i5 = paddingBottom;
        }
        d0Var.setWidth(width2);
        d0Var.setHeight(i5);
        if (Build.VERSION.SDK_INT <= 28) {
            Method method2 = A;
            if (method2 != null) {
                try {
                    method2.invoke(d0Var, Boolean.TRUE);
                } catch (Exception unused2) {
                    Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
                }
            }
        } else {
            d0Var.setIsClippedToScreen(true);
        }
        d0Var.setOutsideTouchable(true);
        d0Var.setTouchInterceptor(this.f1434s);
        if (this.f1427l) {
            androidx.lifecycle.i.b0(d0Var, this.f1426k);
        }
        if (Build.VERSION.SDK_INT <= 28) {
            Method method3 = C;
            if (method3 != null) {
                try {
                    method3.invoke(d0Var, this.f1439x);
                } catch (Exception e2) {
                    Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", e2);
                }
            }
        } else {
            d0Var.setEpicenterBounds(this.f1439x);
        }
        d0Var.showAsDropDown(this.f1431p, this.f1422g, this.f1423h, this.f1428m);
        this.f1419d.setSelection(-1);
        if ((!this.f1440y || this.f1419d.isInTouchMode()) && (i1Var = this.f1419d) != null) {
            i1Var.setListSelectionHidden(true);
            i1Var.requestLayout();
        }
        if (this.f1440y) {
            return;
        }
        this.f1437v.post(this.f1436u);
    }

    public final int g() {
        if (this.f1425j) {
            return this.f1423h;
        }
        return 0;
    }

    public final Drawable h() {
        return this.f1441z.getBackground();
    }

    @Override // i.f0
    public final i1 j() {
        return this.f1419d;
    }

    public final void m(Drawable drawable) {
        this.f1441z.setBackgroundDrawable(drawable);
    }

    public final void n(int i2) {
        this.f1423h = i2;
        this.f1425j = true;
    }

    public void o(ListAdapter listAdapter) {
        q1 q1Var = this.f1430o;
        if (q1Var == null) {
            this.f1430o = new q1(this, 0);
        } else {
            ListAdapter listAdapter2 = this.f1418c;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(q1Var);
            }
        }
        this.f1418c = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.f1430o);
        }
        i1 i1Var = this.f1419d;
        if (i1Var != null) {
            i1Var.setAdapter(this.f1418c);
        }
    }

    public i1 q(Context context, boolean z2) {
        return new i1(context, z2);
    }

    public final void r(int i2) {
        Drawable background = this.f1441z.getBackground();
        if (background == null) {
            this.f1421f = i2;
            return;
        }
        Rect rect = this.f1438w;
        background.getPadding(rect);
        this.f1421f = rect.left + rect.right + i2;
    }
}
