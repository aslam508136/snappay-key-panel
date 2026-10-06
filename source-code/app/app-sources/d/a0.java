package d;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.view.menu.ExpandedMenuView;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.Toolbar;
import j.a3;
import j.c1;
import j.e1;
import j.e3;
import j.f1;
import j.g3;
import j.j0;
import j.r0;
import j.w0;
import j.w2;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;
import x.k0;
import x.l0;

/* JADX INFO: loaded from: classes.dex */
public final class a0 extends p implements i.m, LayoutInflater.Factory2 {

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public static final m.j f577a0 = new m.j();

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public static final int[] f578b0 = {R.attr.windowBackground};

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public static final boolean f579c0 = !"robolectric".equals(Build.FINGERPRINT);

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public static final boolean f580d0 = true;
    public boolean A;
    public boolean B;
    public boolean C;
    public boolean D;
    public boolean E;
    public boolean F;
    public z[] G;
    public z H;
    public boolean I;
    public boolean J;
    public boolean K;
    public boolean L;
    public boolean M;
    public final int N;
    public int O;
    public boolean P;
    public boolean Q;
    public v R;
    public v S;
    public boolean T;
    public int U;
    public boolean W;
    public Rect X;
    public Rect Y;
    public d0 Z;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f581d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Context f582e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Window f583f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public u f584g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final o f585h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public i0 f586i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public h.k f587j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public CharSequence f588k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public e1 f589l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public r f590m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public r f591n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public h.c f592o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ActionBarContextView f593p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public PopupWindow f594q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public q f595r;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f598u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public ViewGroup f599v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public TextView f600w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public View f601x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f602y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f603z;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public x.y f596s = null;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final boolean f597t = true;
    public final q V = new q(this, 0);

    public a0(Context context, Window window, o oVar, Object obj) {
        n nVar;
        this.N = -100;
        this.f582e = context;
        this.f585h = oVar;
        this.f581d = obj;
        if (obj instanceof Dialog) {
            while (true) {
                if (context != null) {
                    if (context instanceof n) {
                        nVar = (n) context;
                        break;
                    } else if (context instanceof ContextWrapper) {
                        context = ((ContextWrapper) context).getBaseContext();
                    }
                }
                nVar = null;
                break;
            }
            if (nVar != null) {
                this.N = ((a0) nVar.k()).N;
            }
        }
        if (this.N == -100) {
            m.j jVar = f577a0;
            Integer num = (Integer) jVar.getOrDefault(this.f581d.getClass().getName(), null);
            if (num != null) {
                this.N = num.intValue();
                jVar.remove(this.f581d.getClass().getName());
            }
        }
        if (window != null) {
            k(window);
        }
        j.y.c();
    }

    public static Configuration o(Context context, int i2, Configuration configuration) {
        int i3;
        if (i2 != 1) {
            i3 = i2 != 2 ? context.getApplicationContext().getResources().getConfiguration().uiMode & 48 : 32;
        } else {
            i3 = 16;
        }
        Configuration configuration2 = new Configuration();
        configuration2.fontScale = 0.0f;
        if (configuration != null) {
            configuration2.setTo(configuration);
        }
        configuration2.uiMode = i3 | (configuration2.uiMode & (-49));
        return configuration2;
    }

    public final boolean A(z zVar, KeyEvent keyEvent) {
        e1 e1Var;
        e1 e1Var2;
        Resources.Theme themeNewTheme;
        e1 e1Var3;
        e1 e1Var4;
        if (this.M) {
            return false;
        }
        if (zVar.f742k) {
            return true;
        }
        z zVar2 = this.H;
        if (zVar2 != null && zVar2 != zVar) {
            n(zVar2, false);
        }
        Window.Callback callbackV = v();
        int i2 = zVar.f732a;
        if (callbackV != null) {
            zVar.f738g = callbackV.onCreatePanelView(i2);
        }
        boolean z2 = i2 == 0 || i2 == 108;
        if (z2 && (e1Var4 = this.f589l) != null) {
            ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) e1Var4;
            actionBarOverlayLayout.l();
            ((a3) actionBarOverlayLayout.f145f).f1168l = true;
        }
        if (zVar.f738g == null) {
            i.o oVar = zVar.f739h;
            if (oVar == null || zVar.f746o) {
                if (oVar == null) {
                    Context context = this.f582e;
                    if ((i2 == 0 || i2 == 108) && this.f589l != null) {
                        TypedValue typedValue = new TypedValue();
                        Resources.Theme theme = context.getTheme();
                        theme.resolveAttribute(com.snapay.app.R.attr.actionBarTheme, typedValue, true);
                        if (typedValue.resourceId != 0) {
                            themeNewTheme = context.getResources().newTheme();
                            themeNewTheme.setTo(theme);
                            themeNewTheme.applyStyle(typedValue.resourceId, true);
                            themeNewTheme.resolveAttribute(com.snapay.app.R.attr.actionBarWidgetTheme, typedValue, true);
                        } else {
                            theme.resolveAttribute(com.snapay.app.R.attr.actionBarWidgetTheme, typedValue, true);
                            themeNewTheme = null;
                        }
                        if (typedValue.resourceId != 0) {
                            if (themeNewTheme == null) {
                                themeNewTheme = context.getResources().newTheme();
                                themeNewTheme.setTo(theme);
                            }
                            themeNewTheme.applyStyle(typedValue.resourceId, true);
                        }
                        if (themeNewTheme != null) {
                            h.e eVar = new h.e(context, 0);
                            eVar.getTheme().setTo(themeNewTheme);
                            context = eVar;
                        }
                    }
                    i.o oVar2 = new i.o(context);
                    oVar2.f1064e = this;
                    i.o oVar3 = zVar.f739h;
                    if (oVar2 != oVar3) {
                        if (oVar3 != null) {
                            oVar3.r(zVar.f740i);
                        }
                        zVar.f739h = oVar2;
                        i.k kVar = zVar.f740i;
                        if (kVar != null) {
                            oVar2.b(kVar, oVar2.f1060a);
                        }
                    }
                    if (zVar.f739h == null) {
                        return false;
                    }
                }
                if (z2 && (e1Var2 = this.f589l) != null) {
                    if (this.f590m == null) {
                        this.f590m = new r(this, 3);
                    }
                    ((ActionBarOverlayLayout) e1Var2).m(zVar.f739h, this.f590m);
                }
                zVar.f739h.w();
                if (!callbackV.onCreatePanelMenu(i2, zVar.f739h)) {
                    i.o oVar4 = zVar.f739h;
                    if (oVar4 != null) {
                        if (oVar4 != null) {
                            oVar4.r(zVar.f740i);
                        }
                        zVar.f739h = null;
                    }
                    if (z2 && (e1Var = this.f589l) != null) {
                        ((ActionBarOverlayLayout) e1Var).m(null, this.f590m);
                    }
                    return false;
                }
                zVar.f746o = false;
            }
            zVar.f739h.w();
            Bundle bundle = zVar.f747p;
            if (bundle != null) {
                zVar.f739h.s(bundle);
                zVar.f747p = null;
            }
            if (!callbackV.onPreparePanel(0, zVar.f738g, zVar.f739h)) {
                if (z2 && (e1Var3 = this.f589l) != null) {
                    ((ActionBarOverlayLayout) e1Var3).m(null, this.f590m);
                }
                zVar.f739h.v();
                return false;
            }
            zVar.f739h.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
            zVar.f739h.v();
        }
        zVar.f742k = true;
        zVar.f743l = false;
        this.H = zVar;
        return true;
    }

    public final void B() {
        if (this.f598u) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    public final int C(l0 l0Var, Rect rect) {
        int i2;
        boolean z2;
        boolean z3;
        if (l0Var != null) {
            i2 = l0Var.f2000a.g().f1891b;
        } else {
            i2 = rect != null ? rect.top : 0;
        }
        ActionBarContextView actionBarContextView = this.f593p;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            z2 = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.f593p.getLayoutParams();
            boolean z4 = true;
            if (this.f593p.isShown()) {
                if (this.X == null) {
                    this.X = new Rect();
                    this.Y = new Rect();
                }
                Rect rect2 = this.X;
                Rect rect3 = this.Y;
                if (l0Var == null) {
                    rect2.set(rect);
                } else {
                    k0 k0Var = l0Var.f2000a;
                    rect2.set(k0Var.g().f1890a, k0Var.g().f1891b, k0Var.g().f1892c, k0Var.g().f1893d);
                }
                ViewGroup viewGroup = this.f599v;
                Method method = g3.f1244a;
                if (method != null) {
                    try {
                        method.invoke(viewGroup, rect2, rect3);
                    } catch (Exception e2) {
                        Log.d("ViewUtils", "Could not invoke computeFitSystemWindows", e2);
                    }
                }
                int i3 = rect2.top;
                int i4 = rect2.left;
                int i5 = rect2.right;
                ViewGroup viewGroup2 = this.f599v;
                WeakHashMap weakHashMap = x.u.f2012a;
                int i6 = Build.VERSION.SDK_INT;
                l0 l0VarA = i6 >= 23 ? x.r.a(viewGroup2) : x.q.c(viewGroup2);
                int i7 = l0VarA == null ? 0 : l0VarA.f2000a.g().f1890a;
                int i8 = l0VarA == null ? 0 : l0VarA.f2000a.g().f1892c;
                if (marginLayoutParams.topMargin == i3 && marginLayoutParams.leftMargin == i4 && marginLayoutParams.rightMargin == i5) {
                    z3 = false;
                } else {
                    marginLayoutParams.topMargin = i3;
                    marginLayoutParams.leftMargin = i4;
                    marginLayoutParams.rightMargin = i5;
                    z3 = true;
                }
                Context context = this.f582e;
                if (i3 <= 0 || this.f601x != null) {
                    View view = this.f601x;
                    if (view != null) {
                        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                        int i9 = marginLayoutParams2.height;
                        int i10 = marginLayoutParams.topMargin;
                        if (i9 != i10 || marginLayoutParams2.leftMargin != i7 || marginLayoutParams2.rightMargin != i8) {
                            marginLayoutParams2.height = i10;
                            marginLayoutParams2.leftMargin = i7;
                            marginLayoutParams2.rightMargin = i8;
                            this.f601x.setLayoutParams(marginLayoutParams2);
                        }
                    }
                } else {
                    View view2 = new View(context);
                    this.f601x = view2;
                    view2.setVisibility(8);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, marginLayoutParams.topMargin, 51);
                    layoutParams.leftMargin = i7;
                    layoutParams.rightMargin = i8;
                    this.f599v.addView(this.f601x, -1, layoutParams);
                }
                View view3 = this.f601x;
                z2 = view3 != null;
                if (z2 && view3.getVisibility() != 0) {
                    View view4 = this.f601x;
                    int i11 = (view4.getWindowSystemUiVisibility() & 8192) != 0 ? com.snapay.app.R.color.abc_decor_view_status_guard_light : com.snapay.app.R.color.abc_decor_view_status_guard;
                    Object obj = o.a.f1732a;
                    view4.setBackgroundColor(i6 >= 23 ? context.getColor(i11) : context.getResources().getColor(i11));
                }
                if (!this.C && z2) {
                    i2 = 0;
                }
            } else {
                if (marginLayoutParams.topMargin != 0) {
                    marginLayoutParams.topMargin = 0;
                } else {
                    z4 = false;
                }
                z3 = z4;
                z2 = false;
            }
            if (z3) {
                this.f593p.setLayoutParams(marginLayoutParams);
            }
        }
        View view5 = this.f601x;
        if (view5 != null) {
            view5.setVisibility(z2 ? 0 : 8);
        }
        return i2;
    }

    @Override // d.p
    public final void a() {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.f582e);
        if (layoutInflaterFrom.getFactory() == null) {
            layoutInflaterFrom.setFactory2(this);
        } else {
            if (layoutInflaterFrom.getFactory2() instanceof a0) {
                return;
            }
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    /* JADX WARN: Code duplicated, block: B:28:0x0057  */
    /* JADX WARN: Code duplicated, block: B:31:0x005c  */
    /* JADX WARN: Code duplicated, block: B:43:0x0085  */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x005d, code lost:
    
        if (r6 != false) goto L33;
     */
    @Override // i.m
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void b(i.o oVar) {
        boolean z2;
        j.m mVar;
        boolean z3;
        boolean z4;
        ActionMenuView actionMenuView;
        e1 e1Var = this.f589l;
        if (e1Var != null) {
            ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) e1Var;
            actionBarOverlayLayout.l();
            Toolbar toolbar = ((a3) actionBarOverlayLayout.f145f).f1157a;
            if (toolbar.getVisibility() == 0 && (actionMenuView = toolbar.f210b) != null && actionMenuView.f169t) {
                if (ViewConfiguration.get(this.f582e).hasPermanentMenuKey()) {
                    ActionBarOverlayLayout actionBarOverlayLayout2 = (ActionBarOverlayLayout) this.f589l;
                    actionBarOverlayLayout2.l();
                    ActionMenuView actionMenuView2 = ((a3) actionBarOverlayLayout2.f145f).f1157a.f210b;
                    if (actionMenuView2 == null) {
                        z3 = false;
                    } else {
                        j.m mVar2 = actionMenuView2.f170u;
                        if (mVar2 == null) {
                            z4 = false;
                        } else {
                            if (mVar2.f1314v != null || mVar2.j()) {
                                z4 = true;
                            } else {
                                z4 = false;
                            }
                        }
                        if (z4) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                    }
                }
                Window.Callback callbackV = v();
                ActionBarOverlayLayout actionBarOverlayLayout3 = (ActionBarOverlayLayout) this.f589l;
                actionBarOverlayLayout3.l();
                ActionMenuView actionMenuView3 = ((a3) actionBarOverlayLayout3.f145f).f1157a.f210b;
                if (actionMenuView3 == null) {
                    z2 = false;
                } else {
                    j.m mVar3 = actionMenuView3.f170u;
                    if (mVar3 != null && mVar3.j()) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                }
                if (z2) {
                    ((ActionBarOverlayLayout) this.f589l).i();
                    if (this.M) {
                        return;
                    }
                    callbackV.onPanelClosed(108, u(0).f739h);
                    return;
                }
                if (callbackV == null || this.M) {
                    return;
                }
                if (this.T && (1 & this.U) != 0) {
                    View decorView = this.f583f.getDecorView();
                    q qVar = this.V;
                    decorView.removeCallbacks(qVar);
                    qVar.run();
                }
                z zVarU = u(0);
                i.o oVar2 = zVarU.f739h;
                if (oVar2 == null || zVarU.f746o || !callbackV.onPreparePanel(0, zVarU.f738g, oVar2)) {
                    return;
                }
                callbackV.onMenuOpened(108, zVarU.f739h);
                ActionBarOverlayLayout actionBarOverlayLayout4 = (ActionBarOverlayLayout) this.f589l;
                actionBarOverlayLayout4.l();
                ActionMenuView actionMenuView4 = ((a3) actionBarOverlayLayout4.f145f).f1157a.f210b;
                if (actionMenuView4 == null || (mVar = actionMenuView4.f170u) == null) {
                    return;
                }
                mVar.l();
                return;
            }
        }
        z zVarU2 = u(0);
        zVarU2.f745n = true;
        n(zVarU2, false);
        y(zVarU2, null);
    }

    @Override // d.p
    public final void c() {
        String strF;
        this.J = true;
        j(false);
        s();
        Object obj = this.f581d;
        if (obj instanceof Activity) {
            try {
                Activity activity = (Activity) obj;
                try {
                    strF = androidx.lifecycle.i.F(activity, activity.getComponentName());
                } catch (PackageManager.NameNotFoundException e2) {
                    throw new IllegalArgumentException(e2);
                }
            } catch (IllegalArgumentException unused) {
                strF = null;
            }
            if (strF != null) {
                i0 i0Var = this.f586i;
                if (i0Var == null) {
                    this.W = true;
                } else {
                    i0Var.l(true);
                }
            }
            synchronized (p.f713c) {
                p.f(this);
                p.f712b.add(new WeakReference(this));
            }
        }
        this.K = true;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0028  */
    @Override // i.m
    public final boolean d(i.o oVar, MenuItem menuItem) {
        z zVar;
        Window.Callback callbackV = v();
        if (callbackV != null && !this.M) {
            i.o oVarK = oVar.k();
            z[] zVarArr = this.G;
            int length = zVarArr != null ? zVarArr.length : 0;
            for (int i2 = 0; i2 < length; i2++) {
                zVar = zVarArr[i2];
                if (zVar != null && zVar.f739h == oVarK) {
                    if (zVar != null) {
                        return callbackV.onMenuItemSelected(zVar.f732a, menuItem);
                    }
                }
            }
            zVar = null;
            if (zVar != null) {
                return callbackV.onMenuItemSelected(zVar.f732a, menuItem);
            }
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0050  */
    @Override // d.p
    public final void e() {
        if (this.f581d instanceof Activity) {
            synchronized (p.f713c) {
                p.f(this);
            }
        }
        if (this.T) {
            this.f583f.getDecorView().removeCallbacks(this.V);
        }
        this.L = false;
        this.M = true;
        if (this.N != -100) {
            Object obj = this.f581d;
            if ((obj instanceof Activity) && ((Activity) obj).isChangingConfigurations()) {
                f577a0.put(this.f581d.getClass().getName(), Integer.valueOf(this.N));
            } else {
                f577a0.remove(this.f581d.getClass().getName());
            }
        } else {
            f577a0.remove(this.f581d.getClass().getName());
        }
        v vVar = this.R;
        if (vVar != null) {
            vVar.a();
        }
        v vVar2 = this.S;
        if (vVar2 != null) {
            vVar2.a();
        }
    }

    @Override // d.p
    public final boolean g(int i2) {
        if (i2 == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            i2 = 108;
        } else if (i2 == 9) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
            i2 = 109;
        }
        if (this.E && i2 == 108) {
            return false;
        }
        if (this.A && i2 == 1) {
            this.A = false;
        }
        if (i2 == 1) {
            B();
            this.E = true;
            return true;
        }
        if (i2 == 2) {
            B();
            this.f602y = true;
            return true;
        }
        if (i2 == 5) {
            B();
            this.f603z = true;
            return true;
        }
        if (i2 == 10) {
            B();
            this.C = true;
            return true;
        }
        if (i2 == 108) {
            B();
            this.A = true;
            return true;
        }
        if (i2 != 109) {
            return this.f583f.requestFeature(i2);
        }
        B();
        this.B = true;
        return true;
    }

    @Override // d.p
    public final void h(int i2) {
        r();
        ViewGroup viewGroup = (ViewGroup) this.f599v.findViewById(R.id.content);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.f582e).inflate(i2, viewGroup);
        this.f584g.f722b.onContentChanged();
    }

    @Override // d.p
    public final void i(CharSequence charSequence) {
        this.f588k = charSequence;
        e1 e1Var = this.f589l;
        if (e1Var != null) {
            e1Var.setWindowTitle(charSequence);
            return;
        }
        i0 i0Var = this.f586i;
        if (i0Var != null) {
            i0Var.n(charSequence);
            return;
        }
        TextView textView = this.f600w;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    /* JADX WARN: Code duplicated, block: B:147:0x01ea  */
    /* JADX WARN: Code duplicated, block: B:54:0x00bf  */
    /* JADX WARN: Code duplicated, block: B:56:0x00c4  */
    /* JADX WARN: Multi-variable type inference failed */
    public final boolean j(boolean z2) {
        boolean z3;
        boolean z4;
        Object obj;
        int i2;
        if (this.M) {
            return false;
        }
        int i3 = this.N;
        if (i3 == -100) {
            i3 = -100;
        }
        Context context = this.f582e;
        Map map = null;
        obj = null;
        obj = null;
        Object obj2 = null;
        Object obj3 = null;
        Configuration configurationO = o(context, x(context, i3), null);
        boolean z5 = this.Q;
        boolean z6 = true;
        Object obj4 = this.f581d;
        if (z5 || !(obj4 instanceof Activity)) {
            this.Q = true;
            z3 = this.P;
        } else {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                z3 = false;
            } else {
                try {
                    int i4 = Build.VERSION.SDK_INT;
                    if (i4 >= 29) {
                        i2 = 269221888;
                    } else {
                        i2 = i4 >= 24 ? 786432 : 0;
                    }
                    ActivityInfo activityInfo = packageManager.getActivityInfo(new ComponentName(context, obj4.getClass()), i2);
                    this.P = (activityInfo == null || (activityInfo.configChanges & 512) == 0) ? false : true;
                } catch (PackageManager.NameNotFoundException e2) {
                    Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", e2);
                    this.P = false;
                }
                this.Q = true;
                z3 = this.P;
            }
        }
        int i5 = context.getResources().getConfiguration().uiMode & 48;
        int i6 = configurationO.uiMode & 48;
        if (i5 != i6 && z2 && !z3 && this.J && ((f579c0 || this.K) && (obj4 instanceof Activity))) {
            Activity activity = (Activity) obj4;
            if (activity.isChild()) {
                z4 = false;
            } else {
                Object obj5 = o.a.f1732a;
                int i7 = Build.VERSION.SDK_INT;
                if (i7 >= 28) {
                    activity.recreate();
                } else if (i7 <= 23) {
                    new Handler(activity.getMainLooper()).post(new androidx.activity.b(activity, 4));
                } else if (!o.c.a(activity)) {
                    activity.recreate();
                }
                z4 = true;
            }
        } else {
            z4 = false;
        }
        if (z4 || i5 == i6) {
            z6 = z4;
        } else {
            Resources resources = context.getResources();
            Configuration configuration = new Configuration(resources.getConfiguration());
            configuration.uiMode = (resources.getConfiguration().uiMode & (-49)) | i6;
            resources.updateConfiguration(configuration, null);
            int i8 = Build.VERSION.SDK_INT;
            if (i8 < 26 && i8 < 28) {
                if (i8 >= 24) {
                    if (!androidx.lifecycle.i.f436h) {
                        try {
                            Field declaredField = Resources.class.getDeclaredField("mResourcesImpl");
                            androidx.lifecycle.i.f435g = declaredField;
                            declaredField.setAccessible(true);
                        } catch (NoSuchFieldException e3) {
                            Log.e("ResourcesFlusher", "Could not retrieve Resources#mResourcesImpl field", e3);
                        }
                        androidx.lifecycle.i.f436h = true;
                    }
                    Field field = androidx.lifecycle.i.f435g;
                    if (field != null) {
                        try {
                            obj = field.get(resources);
                        } catch (IllegalAccessException e4) {
                            Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mResourcesImpl", e4);
                            obj = null;
                        }
                        if (obj != null) {
                            if (!androidx.lifecycle.i.f430b) {
                                try {
                                    Field declaredField2 = obj.getClass().getDeclaredField("mDrawableCache");
                                    androidx.lifecycle.i.f429a = declaredField2;
                                    declaredField2.setAccessible(true);
                                } catch (NoSuchFieldException e5) {
                                    Log.e("ResourcesFlusher", "Could not retrieve ResourcesImpl#mDrawableCache field", e5);
                                }
                                androidx.lifecycle.i.f430b = true;
                            }
                            Field field2 = androidx.lifecycle.i.f429a;
                            if (field2 != null) {
                                try {
                                    obj2 = field2.get(obj);
                                } catch (IllegalAccessException e6) {
                                    Log.e("ResourcesFlusher", "Could not retrieve value from ResourcesImpl#mDrawableCache", e6);
                                }
                            }
                            if (obj2 != null) {
                                androidx.lifecycle.i.w(obj2);
                            }
                        }
                    }
                } else if (i8 >= 23) {
                    if (!androidx.lifecycle.i.f430b) {
                        try {
                            Field declaredField3 = Resources.class.getDeclaredField("mDrawableCache");
                            androidx.lifecycle.i.f429a = declaredField3;
                            declaredField3.setAccessible(true);
                        } catch (NoSuchFieldException e7) {
                            Log.e("ResourcesFlusher", "Could not retrieve Resources#mDrawableCache field", e7);
                        }
                        androidx.lifecycle.i.f430b = true;
                    }
                    Field field3 = androidx.lifecycle.i.f429a;
                    if (field3 != null) {
                        try {
                            obj3 = field3.get(resources);
                        } catch (IllegalAccessException e8) {
                            Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mDrawableCache", e8);
                        }
                    }
                    if (obj3 != null) {
                        androidx.lifecycle.i.w(obj3);
                    }
                } else {
                    if (!androidx.lifecycle.i.f430b) {
                        try {
                            Field declaredField4 = Resources.class.getDeclaredField("mDrawableCache");
                            androidx.lifecycle.i.f429a = declaredField4;
                            declaredField4.setAccessible(true);
                        } catch (NoSuchFieldException e9) {
                            Log.e("ResourcesFlusher", "Could not retrieve Resources#mDrawableCache field", e9);
                        }
                        androidx.lifecycle.i.f430b = true;
                    }
                    Field field4 = androidx.lifecycle.i.f429a;
                    if (field4 != null) {
                        try {
                            map = (Map) field4.get(resources);
                        } catch (IllegalAccessException e10) {
                            Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mDrawableCache", e10);
                        }
                        if (map != null) {
                            map.clear();
                        }
                    }
                }
            }
            int i9 = this.O;
            if (i9 != 0) {
                context.setTheme(i9);
                if (Build.VERSION.SDK_INT >= 23) {
                    context.getTheme().applyStyle(this.O, true);
                }
            }
            if (z3 && (obj4 instanceof Activity)) {
                Activity activity2 = (Activity) obj4;
                if (activity2 instanceof androidx.lifecycle.l) {
                    if (((androidx.lifecycle.l) activity2).h().Q.compareTo(androidx.lifecycle.h.STARTED) >= 0) {
                        activity2.onConfigurationChanged(configuration);
                    }
                } else if (this.L) {
                    activity2.onConfigurationChanged(configuration);
                }
            }
        }
        if (z6 && (obj4 instanceof n)) {
            ((n) obj4).getClass();
        }
        if (i3 == 0) {
            t(context).e();
        } else {
            v vVar = this.R;
            if (vVar != null) {
                vVar.a();
            }
        }
        if (i3 == 3) {
            if (this.S == null) {
                this.S = new v(this, context);
            }
            this.S.e();
        } else {
            v vVar2 = this.S;
            if (vVar2 != null) {
                vVar2.a();
            }
        }
        return z6;
    }

    public final void k(Window window) {
        int resourceId;
        Drawable drawableG;
        if (this.f583f != null) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        Window.Callback callback = window.getCallback();
        if (callback instanceof u) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        u uVar = new u(this, callback);
        this.f584g = uVar;
        window.setCallback(uVar);
        int[] iArr = f578b0;
        Context context = this.f582e;
        Drawable drawable = null;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes((AttributeSet) null, iArr);
        if (typedArrayObtainStyledAttributes.hasValue(0) && (resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0)) != 0) {
            j.y yVarA = j.y.a();
            synchronized (yVarA) {
                drawableG = yVarA.f1497a.g(context, resourceId, true);
            }
            drawable = drawableG;
        }
        if (drawable != null) {
            window.setBackgroundDrawable(drawable);
        }
        typedArrayObtainStyledAttributes.recycle();
        this.f583f = window;
    }

    public final void l(int i2, z zVar, i.o oVar) {
        if (oVar == null) {
            if (zVar == null && i2 >= 0) {
                z[] zVarArr = this.G;
                if (i2 < zVarArr.length) {
                    zVar = zVarArr[i2];
                }
            }
            if (zVar != null) {
                oVar = zVar.f739h;
            }
        }
        if ((zVar == null || zVar.f744m) && !this.M) {
            this.f584g.f722b.onPanelClosed(i2, oVar);
        }
    }

    public final void m(i.o oVar) {
        j.m mVar;
        if (this.F) {
            return;
        }
        this.F = true;
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) this.f589l;
        actionBarOverlayLayout.l();
        ActionMenuView actionMenuView = ((a3) actionBarOverlayLayout.f145f).f1157a.f210b;
        if (actionMenuView != null && (mVar = actionMenuView.f170u) != null) {
            mVar.f();
            j.h hVar = mVar.f1313u;
            if (hVar != null && hVar.b()) {
                hVar.f1134j.dismiss();
            }
        }
        Window.Callback callbackV = v();
        if (callbackV != null && !this.M) {
            callbackV.onPanelClosed(108, oVar);
        }
        this.F = false;
    }

    /* JADX WARN: Code duplicated, block: B:18:0x002c  */
    public final void n(z zVar, boolean z2) {
        y yVar;
        e1 e1Var;
        boolean z3;
        if (z2 && zVar.f732a == 0 && (e1Var = this.f589l) != null) {
            ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) e1Var;
            actionBarOverlayLayout.l();
            ActionMenuView actionMenuView = ((a3) actionBarOverlayLayout.f145f).f1157a.f210b;
            if (actionMenuView == null) {
                z3 = false;
            } else {
                j.m mVar = actionMenuView.f170u;
                if (mVar != null && mVar.j()) {
                    z3 = true;
                } else {
                    z3 = false;
                }
            }
            if (z3) {
                m(zVar.f739h);
                return;
            }
        }
        WindowManager windowManager = (WindowManager) this.f582e.getSystemService("window");
        if (windowManager != null && zVar.f744m && (yVar = zVar.f736e) != null) {
            windowManager.removeView(yVar);
            if (z2) {
                l(zVar.f732a, zVar, null);
            }
        }
        zVar.f742k = false;
        zVar.f743l = false;
        zVar.f744m = false;
        zVar.f737f = null;
        zVar.f745n = true;
        if (this.H == zVar) {
            this.H = null;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:81:0x013b  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View g0Var;
        d0 d0Var;
        if (this.Z == null) {
            String string = this.f582e.obtainStyledAttributes(c.a.f490j).getString(116);
            if (string == null) {
                d0Var = new d0();
            } else {
                try {
                    this.Z = (d0) Class.forName(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable th) {
                    Log.i("AppCompatDelegate", "Failed to instantiate custom view inflater " + string + ". Falling back to default.", th);
                    d0Var = new d0();
                    this.Z = d0Var;
                }
            }
            this.Z = d0Var;
        }
        d0 d0Var2 = this.Z;
        int i2 = e3.f1234a;
        d0Var2.getClass();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, c.a.f504x, 0, 0);
        byte b2 = 4;
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(4, 0);
        if (resourceId != 0) {
            Log.i("AppCompatViewInflater", "app:theme is now deprecated. Please move to using android:theme instead.");
        }
        typedArrayObtainStyledAttributes.recycle();
        Context eVar = (resourceId == 0 || ((context instanceof h.e) && ((h.e) context).f895a == resourceId)) ? context : new h.e(context, resourceId);
        str.getClass();
        switch (str.hashCode()) {
            case -1946472170:
                if (!str.equals("RatingBar")) {
                    b2 = -1;
                } else {
                    b2 = 0;
                }
                break;
            case -1455429095:
                if (!str.equals("CheckedTextView")) {
                    b2 = -1;
                } else {
                    b2 = 1;
                }
                break;
            case -1346021293:
                if (!str.equals("MultiAutoCompleteTextView")) {
                    b2 = -1;
                } else {
                    b2 = 2;
                }
                break;
            case -938935918:
                if (!str.equals("TextView")) {
                    b2 = -1;
                } else {
                    b2 = 3;
                }
                break;
            case -937446323:
                if (!str.equals("ImageButton")) {
                    b2 = -1;
                }
                break;
            case -658531749:
                if (!str.equals("SeekBar")) {
                    b2 = -1;
                } else {
                    b2 = 5;
                }
                break;
            case -339785223:
                if (!str.equals("Spinner")) {
                    b2 = -1;
                } else {
                    b2 = 6;
                }
                break;
            case 776382189:
                if (!str.equals("RadioButton")) {
                    b2 = -1;
                } else {
                    b2 = 7;
                }
                break;
            case 799298502:
                if (!str.equals("ToggleButton")) {
                    b2 = -1;
                } else {
                    b2 = 8;
                }
                break;
            case 1125864064:
                if (!str.equals("ImageView")) {
                    b2 = -1;
                } else {
                    b2 = 9;
                }
                break;
            case 1413872058:
                if (!str.equals("AutoCompleteTextView")) {
                    b2 = -1;
                } else {
                    b2 = 10;
                }
                break;
            case 1601505219:
                if (!str.equals("CheckBox")) {
                    b2 = -1;
                } else {
                    b2 = 11;
                }
                break;
            case 1666676343:
                if (!str.equals("EditText")) {
                    b2 = -1;
                } else {
                    b2 = 12;
                }
                break;
            case 2001146706:
                if (!str.equals("Button")) {
                    b2 = -1;
                } else {
                    b2 = 13;
                }
                break;
            default:
                b2 = -1;
                break;
        }
        View view2 = null;
        switch (b2) {
            case 0:
                g0Var = new j.g0(eVar, attributeSet);
                break;
            case 1:
                g0Var = new j.v(eVar, attributeSet);
                break;
            case 2:
                g0Var = new j.c0(eVar, attributeSet);
                break;
            case 3:
                g0Var = new w0(eVar, attributeSet);
                break;
            case 4:
                g0Var = new j.a0(eVar, attributeSet, com.snapay.app.R.attr.imageButtonStyle);
                break;
            case 5:
                g0Var = new j0(eVar, attributeSet);
                break;
            case 6:
                g0Var = new r0(eVar, attributeSet);
                break;
            case 7:
                g0Var = new j.f0(eVar, attributeSet);
                break;
            case 8:
                g0Var = new c1(eVar, attributeSet);
                break;
            case 9:
                g0Var = new j.b0(eVar, attributeSet, 0);
                break;
            case 10:
                g0Var = new j.r(eVar, attributeSet);
                break;
            case 11:
                g0Var = new j.u(eVar, attributeSet);
                break;
            case 12:
                g0Var = new j.z(eVar, attributeSet);
                break;
            case TYPE_UINT32_VALUE:
                g0Var = new j.t(eVar, attributeSet);
                break;
            default:
                g0Var = null;
                break;
        }
        if (g0Var == null && context != eVar) {
            Object[] objArr = d0Var2.f620a;
            if (str.equals("view")) {
                str = attributeSet.getAttributeValue(null, "class");
            }
            try {
                objArr[0] = eVar;
                objArr[1] = attributeSet;
                if (-1 == str.indexOf(46)) {
                    int i3 = 0;
                    while (true) {
                        String[] strArr = d0.f618d;
                        if (i3 < 3) {
                            View viewA = d0Var2.a(eVar, str, strArr[i3]);
                            if (viewA != null) {
                                objArr[0] = null;
                                objArr[1] = null;
                                view2 = viewA;
                            } else {
                                i3++;
                            }
                        } else {
                            objArr[0] = null;
                            objArr[1] = null;
                        }
                    }
                } else {
                    View viewA2 = d0Var2.a(eVar, str, null);
                    objArr[0] = null;
                    objArr[1] = null;
                    view2 = viewA2;
                }
            } catch (Exception unused) {
                objArr[0] = null;
                objArr[1] = null;
            } catch (Throwable th2) {
                objArr[0] = null;
                objArr[1] = null;
                throw th2;
            }
            g0Var = view2;
        }
        if (g0Var != null) {
            Context context2 = g0Var.getContext();
            if (context2 instanceof ContextWrapper) {
                WeakHashMap weakHashMap = x.u.f2012a;
                if (g0Var.hasOnClickListeners()) {
                    TypedArray typedArrayObtainStyledAttributes2 = context2.obtainStyledAttributes(attributeSet, d0.f617c);
                    String string2 = typedArrayObtainStyledAttributes2.getString(0);
                    if (string2 != null) {
                        g0Var.setOnClickListener(new c0(g0Var, string2));
                    }
                    typedArrayObtainStyledAttributes2.recycle();
                }
            }
        }
        return g0Var;
    }

    /* JADX WARN: Code duplicated, block: B:126:0x0181  */
    /* JADX WARN: Code duplicated, block: B:143:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:64:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:81:0x00fc  */
    public final boolean p(KeyEvent keyEvent) {
        View decorView;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean zI;
        boolean zA;
        boolean z5;
        ActionMenuView actionMenuView;
        Object obj = this.f581d;
        if (((obj instanceof x.e) || (obj instanceof k)) && (decorView = this.f583f.getDecorView()) != null && androidx.lifecycle.i.u(decorView, keyEvent)) {
            return true;
        }
        if (keyEvent.getKeyCode() == 82 && this.f584g.f722b.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        if (keyEvent.getAction() == 0) {
            if (keyCode == 4) {
                this.I = (keyEvent.getFlags() & 128) != 0;
            } else if (keyCode == 82) {
                if (keyEvent.getRepeatCount() != 0) {
                    return true;
                }
                z zVarU = u(0);
                if (zVarU.f744m) {
                    return true;
                }
                A(zVarU, keyEvent);
                return true;
            }
        } else if (keyCode == 4) {
            boolean z6 = this.I;
            this.I = false;
            z zVarU2 = u(0);
            if (zVarU2.f744m) {
                if (z6) {
                    return true;
                }
                n(zVarU2, true);
                return true;
            }
            h.c cVar = this.f592o;
            if (cVar != null) {
                cVar.a();
            } else {
                w();
                i0 i0Var = this.f586i;
                if (i0Var != null) {
                    f1 f1Var = i0Var.f684e;
                    if (f1Var == null) {
                        z3 = false;
                    } else {
                        w2 w2Var = ((a3) f1Var).f1157a.K;
                        if ((w2Var == null || w2Var.f1485c == null) ? false : true) {
                            i.q qVar = w2Var == null ? null : w2Var.f1485c;
                            if (qVar != null) {
                                qVar.collapseActionView();
                            }
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                    }
                    if (z3) {
                    }
                    if (z2) {
                        return true;
                    }
                }
                z2 = false;
                if (z2) {
                    return true;
                }
            }
            z2 = true;
            if (z2) {
                return true;
            }
        } else if (keyCode == 82) {
            if (this.f592o != null) {
                return true;
            }
            z zVarU3 = u(0);
            e1 e1Var = this.f589l;
            Context context = this.f582e;
            if (e1Var != null) {
                ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) e1Var;
                actionBarOverlayLayout.l();
                Toolbar toolbar = ((a3) actionBarOverlayLayout.f145f).f1157a;
                if (!(toolbar.getVisibility() == 0 && (actionMenuView = toolbar.f210b) != null && actionMenuView.f169t) || ViewConfiguration.get(context).hasPermanentMenuKey()) {
                    z4 = zVarU3.f744m;
                    if (!z4 || zVarU3.f743l) {
                        n(zVarU3, true);
                        zI = z4;
                    } else {
                        if (zVarU3.f742k) {
                            if (zVarU3.f746o) {
                                zVarU3.f742k = false;
                                zA = A(zVarU3, keyEvent);
                            } else {
                                zA = true;
                            }
                            if (zA) {
                                y(zVarU3, keyEvent);
                                zI = true;
                            }
                        }
                        zI = false;
                    }
                } else {
                    ActionBarOverlayLayout actionBarOverlayLayout2 = (ActionBarOverlayLayout) this.f589l;
                    actionBarOverlayLayout2.l();
                    ActionMenuView actionMenuView2 = ((a3) actionBarOverlayLayout2.f145f).f1157a.f210b;
                    if (actionMenuView2 == null) {
                        z5 = false;
                    } else {
                        j.m mVar = actionMenuView2.f170u;
                        if (mVar != null && mVar.j()) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                    }
                    if (z5) {
                        zI = ((ActionBarOverlayLayout) this.f589l).i();
                    } else {
                        if (!this.M && A(zVarU3, keyEvent)) {
                            ActionBarOverlayLayout actionBarOverlayLayout3 = (ActionBarOverlayLayout) this.f589l;
                            actionBarOverlayLayout3.l();
                            ActionMenuView actionMenuView3 = ((a3) actionBarOverlayLayout3.f145f).f1157a.f210b;
                            if (actionMenuView3 != null) {
                                j.m mVar2 = actionMenuView3.f170u;
                                if (mVar2 != null && mVar2.l()) {
                                    zI = true;
                                }
                            }
                        }
                        zI = false;
                    }
                }
            } else {
                z4 = zVarU3.f744m;
                if (z4) {
                }
                n(zVarU3, true);
                zI = z4;
            }
            if (!zI) {
                return true;
            }
            AudioManager audioManager = (AudioManager) context.getApplicationContext().getSystemService("audio");
            if (audioManager != null) {
                audioManager.playSoundEffect(0);
                return true;
            }
            Log.w("AppCompatDelegate", "Couldn't get audio manager");
            return true;
        }
        return false;
    }

    public final void q(int i2) {
        z zVarU = u(i2);
        if (zVarU.f739h != null) {
            Bundle bundle = new Bundle();
            zVarU.f739h.t(bundle);
            if (bundle.size() > 0) {
                zVarU.f747p = bundle;
            }
            zVarU.f739h.w();
            zVarU.f739h.clear();
        }
        zVarU.f746o = true;
        zVarU.f745n = true;
        if ((i2 == 108 || i2 == 0) && this.f589l != null) {
            z zVarU2 = u(0);
            zVarU2.f742k = false;
            A(zVarU2, null);
        }
    }

    public final void r() {
        ViewGroup viewGroup;
        if (this.f598u) {
            return;
        }
        int[] iArr = c.a.f490j;
        Context context = this.f582e;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(iArr);
        if (!typedArrayObtainStyledAttributes.hasValue(117)) {
            typedArrayObtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
        int i2 = 0;
        if (typedArrayObtainStyledAttributes.getBoolean(126, false)) {
            g(1);
        } else if (typedArrayObtainStyledAttributes.getBoolean(117, false)) {
            g(108);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(118, false)) {
            g(109);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(119, false)) {
            g(10);
        }
        this.D = typedArrayObtainStyledAttributes.getBoolean(0, false);
        typedArrayObtainStyledAttributes.recycle();
        s();
        this.f583f.getDecorView();
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        int i3 = 2;
        if (this.E) {
            viewGroup = (ViewGroup) layoutInflaterFrom.inflate(this.C ? com.snapay.app.R.layout.abc_screen_simple_overlay_action_mode : com.snapay.app.R.layout.abc_screen_simple, (ViewGroup) null);
        } else if (this.D) {
            viewGroup = (ViewGroup) layoutInflaterFrom.inflate(com.snapay.app.R.layout.abc_dialog_title_material, (ViewGroup) null);
            this.B = false;
            this.A = false;
        } else if (this.A) {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(com.snapay.app.R.attr.actionBarTheme, typedValue, true);
            viewGroup = (ViewGroup) LayoutInflater.from(typedValue.resourceId != 0 ? new h.e(context, typedValue.resourceId) : context).inflate(com.snapay.app.R.layout.abc_screen_toolbar, (ViewGroup) null);
            e1 e1Var = (e1) viewGroup.findViewById(com.snapay.app.R.id.decor_content_parent);
            this.f589l = e1Var;
            e1Var.setWindowCallback(v());
            if (this.B) {
                ((ActionBarOverlayLayout) this.f589l).k(109);
            }
            if (this.f602y) {
                ((ActionBarOverlayLayout) this.f589l).k(2);
            }
            if (this.f603z) {
                ((ActionBarOverlayLayout) this.f589l).k(5);
            }
        } else {
            viewGroup = null;
        }
        if (viewGroup == null) {
            throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.A + ", windowActionBarOverlay: " + this.B + ", android:windowIsFloating: " + this.D + ", windowActionModeOverlay: " + this.C + ", windowNoTitle: " + this.E + " }");
        }
        r rVar = new r(this, i2);
        WeakHashMap weakHashMap = x.u.f2012a;
        x.q.d(viewGroup, rVar);
        if (this.f589l == null) {
            this.f600w = (TextView) viewGroup.findViewById(com.snapay.app.R.id.title);
        }
        Method method = g3.f1244a;
        try {
            Method method2 = viewGroup.getClass().getMethod("makeOptionalFitsSystemWindows", new Class[0]);
            if (!method2.isAccessible()) {
                method2.setAccessible(true);
            }
            method2.invoke(viewGroup, new Object[0]);
        } catch (IllegalAccessException e2) {
            e = e2;
            Log.d("ViewUtils", "Could not invoke makeOptionalFitsSystemWindows", e);
        } catch (NoSuchMethodException unused) {
            Log.d("ViewUtils", "Could not find method makeOptionalFitsSystemWindows. Oh well...");
        } catch (InvocationTargetException e3) {
            e = e3;
            Log.d("ViewUtils", "Could not invoke makeOptionalFitsSystemWindows", e);
        }
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(com.snapay.app.R.id.action_bar_activity_content);
        ViewGroup viewGroup2 = (ViewGroup) this.f583f.findViewById(R.id.content);
        if (viewGroup2 != null) {
            while (viewGroup2.getChildCount() > 0) {
                View childAt = viewGroup2.getChildAt(0);
                viewGroup2.removeViewAt(0);
                contentFrameLayout.addView(childAt);
            }
            viewGroup2.setId(-1);
            contentFrameLayout.setId(R.id.content);
            if (viewGroup2 instanceof FrameLayout) {
                ((FrameLayout) viewGroup2).setForeground(null);
            }
        }
        this.f583f.setContentView(viewGroup);
        contentFrameLayout.setAttachListener(new r(this, i3));
        this.f599v = viewGroup;
        Object obj = this.f581d;
        CharSequence title = obj instanceof Activity ? ((Activity) obj).getTitle() : this.f588k;
        if (!TextUtils.isEmpty(title)) {
            e1 e1Var2 = this.f589l;
            if (e1Var2 != null) {
                e1Var2.setWindowTitle(title);
            } else {
                i0 i0Var = this.f586i;
                if (i0Var != null) {
                    i0Var.n(title);
                } else {
                    TextView textView = this.f600w;
                    if (textView != null) {
                        textView.setText(title);
                    }
                }
            }
        }
        ContentFrameLayout contentFrameLayout2 = (ContentFrameLayout) this.f599v.findViewById(R.id.content);
        View decorView = this.f583f.getDecorView();
        contentFrameLayout2.f185h.set(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        WeakHashMap weakHashMap2 = x.u.f2012a;
        if (contentFrameLayout2.isLaidOut()) {
            contentFrameLayout2.requestLayout();
        }
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(iArr);
        typedArrayObtainStyledAttributes2.getValue(124, contentFrameLayout2.getMinWidthMajor());
        typedArrayObtainStyledAttributes2.getValue(125, contentFrameLayout2.getMinWidthMinor());
        if (typedArrayObtainStyledAttributes2.hasValue(122)) {
            typedArrayObtainStyledAttributes2.getValue(122, contentFrameLayout2.getFixedWidthMajor());
        }
        if (typedArrayObtainStyledAttributes2.hasValue(123)) {
            typedArrayObtainStyledAttributes2.getValue(123, contentFrameLayout2.getFixedWidthMinor());
        }
        if (typedArrayObtainStyledAttributes2.hasValue(120)) {
            typedArrayObtainStyledAttributes2.getValue(120, contentFrameLayout2.getFixedHeightMajor());
        }
        if (typedArrayObtainStyledAttributes2.hasValue(121)) {
            typedArrayObtainStyledAttributes2.getValue(121, contentFrameLayout2.getFixedHeightMinor());
        }
        typedArrayObtainStyledAttributes2.recycle();
        contentFrameLayout2.requestLayout();
        this.f598u = true;
        z zVarU = u(0);
        if (this.M || zVarU.f739h != null) {
            return;
        }
        this.U |= 4096;
        if (this.T) {
            return;
        }
        this.f583f.getDecorView().postOnAnimation(this.V);
        this.T = true;
    }

    public final void s() {
        if (this.f583f == null) {
            Object obj = this.f581d;
            if (obj instanceof Activity) {
                k(((Activity) obj).getWindow());
            }
        }
        if (this.f583f == null) {
            throw new IllegalStateException("We have not been given a Window");
        }
    }

    public final x t(Context context) {
        if (this.R == null) {
            if (m0.a.f1641e == null) {
                Context applicationContext = context.getApplicationContext();
                m0.a.f1641e = new m0.a(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
            }
            this.R = new v(this, m0.a.f1641e);
        }
        return this.R;
    }

    public final z u(int i2) {
        z[] zVarArr = this.G;
        if (zVarArr == null || zVarArr.length <= i2) {
            z[] zVarArr2 = new z[i2 + 1];
            if (zVarArr != null) {
                System.arraycopy(zVarArr, 0, zVarArr2, 0, zVarArr.length);
            }
            this.G = zVarArr2;
            zVarArr = zVarArr2;
        }
        z zVar = zVarArr[i2];
        if (zVar != null) {
            return zVar;
        }
        z zVar2 = new z(i2);
        zVarArr[i2] = zVar2;
        return zVar2;
    }

    public final Window.Callback v() {
        return this.f583f.getCallback();
    }

    /* JADX WARN: Code duplicated, block: B:16:0x002d  */
    /* JADX WARN: Code duplicated, block: B:19:? A[RETURN, SYNTHETIC] */
    public final void w() {
        i0 i0Var;
        i0 i0Var2;
        r();
        if (this.A && this.f586i == null) {
            Object obj = this.f581d;
            if (!(obj instanceof Activity)) {
                if (obj instanceof Dialog) {
                    i0Var = new i0((Dialog) obj);
                }
                i0Var2 = this.f586i;
                if (i0Var2 != null) {
                    i0Var2.l(this.W);
                }
            }
            i0Var = new i0((Activity) obj, this.B);
            this.f586i = i0Var;
            i0Var2 = this.f586i;
            if (i0Var2 != null) {
                i0Var2.l(this.W);
            }
        }
    }

    public final int x(Context context, int i2) {
        x xVarT;
        if (i2 == -100) {
            return -1;
        }
        if (i2 != -1) {
            if (i2 != 0) {
                if (i2 != 1 && i2 != 2) {
                    if (i2 != 3) {
                        throw new IllegalStateException("Unknown value set for night mode. Please use one of the MODE_NIGHT values from AppCompatDelegate.");
                    }
                    if (this.S == null) {
                        this.S = new v(this, context);
                    }
                    xVarT = this.S;
                }
            } else {
                if (Build.VERSION.SDK_INT >= 23 && ((UiModeManager) context.getApplicationContext().getSystemService("uimode")).getNightMode() == 0) {
                    return -1;
                }
                xVarT = t(context);
            }
            return xVarT.c();
        }
        return i2;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0185  */
    /* JADX WARN: Code duplicated, block: B:103:0x019b  */
    /* JADX WARN: Code duplicated, block: B:106:0x01b1  */
    /* JADX WARN: Code duplicated, block: B:83:0x0158  */
    /* JADX WARN: Code duplicated, block: B:86:0x015d  */
    /* JADX WARN: Code duplicated, block: B:89:0x0162  */
    /* JADX WARN: Code duplicated, block: B:91:0x0168  */
    /* JADX WARN: Code duplicated, block: B:95:0x0179  */
    /* JADX WARN: Code duplicated, block: B:98:0x017d  */
    public final void y(z zVar, KeyEvent keyEvent) {
        boolean z2;
        boolean z3;
        ViewGroup.LayoutParams layoutParams;
        ViewParent parent;
        i.k kVar;
        int i2;
        ViewGroup.LayoutParams layoutParams2;
        if (zVar.f744m || this.M) {
            return;
        }
        Context context = this.f582e;
        int i3 = 4;
        int i4 = zVar.f732a;
        if (i4 == 0) {
            if ((context.getResources().getConfiguration().screenLayout & 15) == 4) {
                return;
            }
        }
        Window.Callback callbackV = v();
        if (callbackV != null && !callbackV.onMenuOpened(i4, zVar.f739h)) {
            n(zVar, true);
            return;
        }
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager != null && A(zVar, keyEvent)) {
            y yVar = zVar.f736e;
            if (yVar != null && !zVar.f745n) {
                View view = zVar.f738g;
                if (view != null && (layoutParams2 = view.getLayoutParams()) != null && layoutParams2.width == -1) {
                    i2 = -1;
                }
                zVar.f743l = false;
                WindowManager.LayoutParams layoutParams3 = new WindowManager.LayoutParams(i2, -2, 0, 0, 1002, 8519680, -3);
                layoutParams3.gravity = zVar.f734c;
                layoutParams3.windowAnimations = zVar.f735d;
                windowManager.addView(zVar.f736e, layoutParams3);
                zVar.f744m = true;
            }
            if (yVar == null) {
                w();
                i0 i0Var = this.f586i;
                Context contextJ = i0Var != null ? i0Var.j() : null;
                if (contextJ != null) {
                    context = contextJ;
                }
                TypedValue typedValue = new TypedValue();
                Resources.Theme themeNewTheme = context.getResources().newTheme();
                themeNewTheme.setTo(context.getTheme());
                themeNewTheme.resolveAttribute(com.snapay.app.R.attr.actionBarPopupTheme, typedValue, true);
                int i5 = typedValue.resourceId;
                if (i5 != 0) {
                    themeNewTheme.applyStyle(i5, true);
                }
                themeNewTheme.resolveAttribute(com.snapay.app.R.attr.panelMenuListTheme, typedValue, true);
                int i6 = typedValue.resourceId;
                if (i6 == 0) {
                    i6 = com.snapay.app.R.style.Theme_AppCompat_CompactMenu;
                }
                themeNewTheme.applyStyle(i6, true);
                h.e eVar = new h.e(context, 0);
                eVar.getTheme().setTo(themeNewTheme);
                zVar.f741j = eVar;
                TypedArray typedArrayObtainStyledAttributes = eVar.obtainStyledAttributes(c.a.f490j);
                zVar.f733b = typedArrayObtainStyledAttributes.getResourceId(86, 0);
                zVar.f735d = typedArrayObtainStyledAttributes.getResourceId(1, 0);
                typedArrayObtainStyledAttributes.recycle();
                zVar.f736e = new y(this, zVar.f741j);
                zVar.f734c = 81;
            } else if (zVar.f745n && yVar.getChildCount() > 0) {
                zVar.f736e.removeAllViews();
            }
            View view2 = zVar.f738g;
            if (view2 == null) {
                if (zVar.f739h != null) {
                    if (this.f591n == null) {
                        this.f591n = new r(this, i3);
                    }
                    r rVar = this.f591n;
                    if (zVar.f740i == null) {
                        i.k kVar2 = new i.k(zVar.f741j);
                        zVar.f740i = kVar2;
                        kVar2.f1051f = rVar;
                        i.o oVar = zVar.f739h;
                        oVar.b(kVar2, oVar.f1060a);
                    }
                    i.k kVar3 = zVar.f740i;
                    y yVar2 = zVar.f736e;
                    if (kVar3.f1050e == null) {
                        kVar3.f1050e = (ExpandedMenuView) kVar3.f1048c.inflate(com.snapay.app.R.layout.abc_expanded_menu_layout, (ViewGroup) yVar2, false);
                        if (kVar3.f1052g == null) {
                            kVar3.f1052g = new i.j(kVar3);
                        }
                        kVar3.f1050e.setAdapter((ListAdapter) kVar3.f1052g);
                        kVar3.f1050e.setOnItemClickListener(kVar3);
                    }
                    ExpandedMenuView expandedMenuView = kVar3.f1050e;
                    zVar.f737f = expandedMenuView;
                    if (expandedMenuView != null) {
                    }
                    if (z2) {
                        if (zVar.f737f != null) {
                            if (zVar.f738g == null) {
                                kVar = zVar.f740i;
                                if (kVar.f1052g == null) {
                                    kVar.f1052g = new i.j(kVar);
                                }
                                if (kVar.f1052g.getCount() <= 0) {
                                    z3 = false;
                                }
                            }
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        if (z3) {
                            layoutParams = zVar.f737f.getLayoutParams();
                            if (layoutParams == null) {
                                layoutParams = new ViewGroup.LayoutParams(-2, -2);
                            }
                            zVar.f736e.setBackgroundResource(zVar.f733b);
                            parent = zVar.f737f.getParent();
                            if (parent instanceof ViewGroup) {
                                ((ViewGroup) parent).removeView(zVar.f737f);
                            }
                            zVar.f736e.addView(zVar.f737f, layoutParams);
                            if (!zVar.f737f.hasFocus()) {
                                zVar.f737f.requestFocus();
                            }
                        }
                    }
                    zVar.f745n = true;
                    return;
                }
                z2 = false;
                if (z2) {
                    if (zVar.f737f != null) {
                        if (zVar.f738g == null) {
                            kVar = zVar.f740i;
                            if (kVar.f1052g == null) {
                                kVar.f1052g = new i.j(kVar);
                            }
                            if (kVar.f1052g.getCount() <= 0) {
                                z3 = false;
                            }
                        }
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (z3) {
                        layoutParams = zVar.f737f.getLayoutParams();
                        if (layoutParams == null) {
                            layoutParams = new ViewGroup.LayoutParams(-2, -2);
                        }
                        zVar.f736e.setBackgroundResource(zVar.f733b);
                        parent = zVar.f737f.getParent();
                        if (parent instanceof ViewGroup) {
                            ((ViewGroup) parent).removeView(zVar.f737f);
                        }
                        zVar.f736e.addView(zVar.f737f, layoutParams);
                        if (!zVar.f737f.hasFocus()) {
                            zVar.f737f.requestFocus();
                        }
                    }
                }
                zVar.f745n = true;
                return;
            }
            zVar.f737f = view2;
            z2 = true;
            if (z2) {
                if (zVar.f737f != null) {
                    if (zVar.f738g == null) {
                        kVar = zVar.f740i;
                        if (kVar.f1052g == null) {
                            kVar.f1052g = new i.j(kVar);
                        }
                        if (kVar.f1052g.getCount() <= 0) {
                            z3 = false;
                        }
                    }
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (z3) {
                    layoutParams = zVar.f737f.getLayoutParams();
                    if (layoutParams == null) {
                        layoutParams = new ViewGroup.LayoutParams(-2, -2);
                    }
                    zVar.f736e.setBackgroundResource(zVar.f733b);
                    parent = zVar.f737f.getParent();
                    if (parent instanceof ViewGroup) {
                        ((ViewGroup) parent).removeView(zVar.f737f);
                    }
                    zVar.f736e.addView(zVar.f737f, layoutParams);
                    if (!zVar.f737f.hasFocus()) {
                        zVar.f737f.requestFocus();
                    }
                }
            }
            zVar.f745n = true;
            return;
            i2 = -2;
            zVar.f743l = false;
            WindowManager.LayoutParams layoutParams4 = new WindowManager.LayoutParams(i2, -2, 0, 0, 1002, 8519680, -3);
            layoutParams4.gravity = zVar.f734c;
            layoutParams4.windowAnimations = zVar.f735d;
            windowManager.addView(zVar.f736e, layoutParams4);
            zVar.f744m = true;
        }
    }

    public final boolean z(z zVar, int i2, KeyEvent keyEvent) {
        i.o oVar;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((zVar.f742k || A(zVar, keyEvent)) && (oVar = zVar.f739h) != null) {
            return oVar.performShortcut(i2, keyEvent, 1);
        }
        return false;
    }

    @Override // android.view.LayoutInflater.Factory
    public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }
}
