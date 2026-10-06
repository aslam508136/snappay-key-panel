package i;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.snapay.app.R;
import j.i1;
import j.w1;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class g0 extends x implements PopupWindow.OnDismissListener, View.OnKeyListener {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Context f997c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final o f998d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final l f999e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f1000f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f1001g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f1002h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f1003i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final w1 f1004j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final e f1005k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final f f1006l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public PopupWindow.OnDismissListener f1007m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public View f1008n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public View f1009o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public a0 f1010p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public ViewTreeObserver f1011q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f1012r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f1013s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f1014t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f1015u = 0;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f1016v;

    public g0(int i2, int i3, Context context, View view, o oVar, boolean z2) {
        int i4 = 1;
        this.f1005k = new e(this, i4);
        this.f1006l = new f(this, i4);
        this.f997c = context;
        this.f998d = oVar;
        this.f1000f = z2;
        this.f999e = new l(oVar, LayoutInflater.from(context), z2, R.layout.abc_popup_menu_item_layout);
        this.f1002h = i2;
        this.f1003i = i3;
        Resources resources = context.getResources();
        this.f1001g = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.f1008n = view;
        this.f1004j = new w1(context, i2, i3);
        oVar.b(this, context);
    }

    @Override // i.b0
    public final void a(o oVar, boolean z2) {
        if (oVar != this.f998d) {
            return;
        }
        dismiss();
        a0 a0Var = this.f1010p;
        if (a0Var != null) {
            a0Var.a(oVar, z2);
        }
    }

    @Override // i.f0
    public final boolean b() {
        return !this.f1012r && this.f1004j.b();
    }

    @Override // i.b0
    public final boolean d() {
        return false;
    }

    @Override // i.f0
    public final void dismiss() {
        if (b()) {
            this.f1004j.dismiss();
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // i.f0
    public final void f() {
        View view;
        boolean z2 = true;
        if (!b()) {
            if (this.f1012r || (view = this.f1008n) == null) {
                z2 = false;
            } else {
                this.f1009o = view;
                w1 w1Var = this.f1004j;
                w1Var.f1441z.setOnDismissListener(this);
                w1Var.f1432q = this;
                w1Var.f1440y = true;
                j.d0 d0Var = w1Var.f1441z;
                d0Var.setFocusable(true);
                View view2 = this.f1009o;
                boolean z3 = this.f1011q == null;
                ViewTreeObserver viewTreeObserver = view2.getViewTreeObserver();
                this.f1011q = viewTreeObserver;
                if (z3) {
                    viewTreeObserver.addOnGlobalLayoutListener(this.f1005k);
                }
                view2.addOnAttachStateChangeListener(this.f1006l);
                w1Var.f1431p = view2;
                w1Var.f1428m = this.f1015u;
                boolean z4 = this.f1013s;
                Context context = this.f997c;
                l lVar = this.f999e;
                if (!z4) {
                    this.f1014t = x.m(lVar, context, this.f1001g);
                    this.f1013s = true;
                }
                w1Var.r(this.f1014t);
                d0Var.setInputMethodMode(2);
                Rect rect = this.f1123b;
                w1Var.f1439x = rect != null ? new Rect(rect) : null;
                w1Var.f();
                i1 i1Var = w1Var.f1419d;
                i1Var.setOnKeyListener(this);
                if (this.f1016v) {
                    o oVar = this.f998d;
                    if (oVar.f1072m != null) {
                        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(context).inflate(R.layout.abc_popup_menu_header_item_layout, (ViewGroup) i1Var, false);
                        TextView textView = (TextView) frameLayout.findViewById(android.R.id.title);
                        if (textView != null) {
                            textView.setText(oVar.f1072m);
                        }
                        frameLayout.setEnabled(false);
                        i1Var.addHeaderView(frameLayout, null, false);
                    }
                }
                w1Var.o(lVar);
                w1Var.f();
            }
        }
        if (!z2) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
    }

    @Override // i.b0
    public final void g(a0 a0Var) {
        this.f1010p = a0Var;
    }

    @Override // i.b0
    public final void i() {
        this.f1013s = false;
        l lVar = this.f999e;
        if (lVar != null) {
            lVar.notifyDataSetChanged();
        }
    }

    @Override // i.f0
    public final i1 j() {
        return this.f1004j.f1419d;
    }

    @Override // i.b0
    public final boolean k(h0 h0Var) {
        boolean z2;
        if (h0Var.hasVisibleItems()) {
            z zVar = new z(this.f1002h, this.f1003i, this.f997c, this.f1009o, h0Var, this.f1000f);
            a0 a0Var = this.f1010p;
            zVar.f1133i = a0Var;
            x xVar = zVar.f1134j;
            if (xVar != null) {
                xVar.g(a0Var);
            }
            boolean zU = x.u(h0Var);
            zVar.f1132h = zU;
            x xVar2 = zVar.f1134j;
            if (xVar2 != null) {
                xVar2.o(zU);
            }
            zVar.f1135k = this.f1007m;
            this.f1007m = null;
            this.f998d.c(false);
            w1 w1Var = this.f1004j;
            int width = w1Var.f1422g;
            int iG = w1Var.g();
            int i2 = this.f1015u;
            View view = this.f1008n;
            WeakHashMap weakHashMap = x.u.f2012a;
            if ((Gravity.getAbsoluteGravity(i2, view.getLayoutDirection()) & 7) == 5) {
                width += this.f1008n.getWidth();
            }
            if (zVar.b()) {
                z2 = true;
            } else if (zVar.f1130f == null) {
                z2 = false;
            } else {
                zVar.d(width, iG, true, true);
                z2 = true;
            }
            if (z2) {
                a0 a0Var2 = this.f1010p;
                if (a0Var2 != null) {
                    a0Var2.c(h0Var);
                }
                return true;
            }
        }
        return false;
    }

    @Override // i.x
    public final void l(o oVar) {
    }

    @Override // i.x
    public final void n(View view) {
        this.f1008n = view;
    }

    @Override // i.x
    public final void o(boolean z2) {
        this.f999e.f1055d = z2;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        this.f1012r = true;
        this.f998d.c(true);
        ViewTreeObserver viewTreeObserver = this.f1011q;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.f1011q = this.f1009o.getViewTreeObserver();
            }
            this.f1011q.removeGlobalOnLayoutListener(this.f1005k);
            this.f1011q = null;
        }
        this.f1009o.removeOnAttachStateChangeListener(this.f1006l);
        PopupWindow.OnDismissListener onDismissListener = this.f1007m;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    @Override // android.view.View.OnKeyListener
    public final boolean onKey(View view, int i2, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i2 != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    @Override // i.x
    public final void p(int i2) {
        this.f1015u = i2;
    }

    @Override // i.x
    public final void q(int i2) {
        this.f1004j.f1422g = i2;
    }

    @Override // i.x
    public final void r(PopupWindow.OnDismissListener onDismissListener) {
        this.f1007m = onDismissListener;
    }

    @Override // i.x
    public final void s(boolean z2) {
        this.f1016v = z2;
    }

    @Override // i.x
    public final void t(int i2) {
        this.f1004j.n(i2);
    }
}
