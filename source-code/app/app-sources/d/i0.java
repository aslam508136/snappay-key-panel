package d;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import androidx.appcompat.widget.ActionBarContainer;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.Toolbar;
import j.a3;
import j.f1;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class i0 extends b.a implements j.f {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final AccelerateInterpolator f678y = new AccelerateInterpolator();

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static final DecelerateInterpolator f679z = new DecelerateInterpolator();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f680a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f681b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ActionBarOverlayLayout f682c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ActionBarContainer f683d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public f1 f684e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ActionBarContextView f685f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final View f686g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f687h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public h0 f688i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public h0 f689j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public h.b f690k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f691l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ArrayList f692m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f693n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f694o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f695p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f696q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f697r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public h.m f698s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f699t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f700u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final g0 f701v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final g0 f702w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final androidx.fragment.app.i f703x;

    public i0(Activity activity, boolean z2) {
        new ArrayList();
        this.f692m = new ArrayList();
        this.f693n = 0;
        this.f694o = true;
        this.f697r = true;
        this.f701v = new g0(this, 0);
        this.f702w = new g0(this, 1);
        this.f703x = new androidx.fragment.app.i(this);
        View decorView = activity.getWindow().getDecorView();
        k(decorView);
        if (z2) {
            return;
        }
        this.f686g = decorView.findViewById(R.id.content);
    }

    public final void i(boolean z2) {
        x.y yVarL;
        x.y yVarL2;
        if (z2) {
            if (!this.f696q) {
                this.f696q = true;
                ActionBarOverlayLayout actionBarOverlayLayout = this.f682c;
                if (actionBarOverlayLayout != null) {
                    actionBarOverlayLayout.setShowingForActionMode(true);
                }
                o(false);
            }
        } else if (this.f696q) {
            this.f696q = false;
            ActionBarOverlayLayout actionBarOverlayLayout2 = this.f682c;
            if (actionBarOverlayLayout2 != null) {
                actionBarOverlayLayout2.setShowingForActionMode(false);
            }
            o(false);
        }
        ActionBarContainer actionBarContainer = this.f683d;
        WeakHashMap weakHashMap = x.u.f2012a;
        if (!actionBarContainer.isLaidOut()) {
            if (z2) {
                ((a3) this.f684e).f1157a.setVisibility(4);
                this.f685f.setVisibility(0);
                return;
            } else {
                ((a3) this.f684e).f1157a.setVisibility(0);
                this.f685f.setVisibility(8);
                return;
            }
        }
        if (z2) {
            a3 a3Var = (a3) this.f684e;
            yVarL = x.u.a(a3Var.f1157a);
            yVarL.a(0.0f);
            yVarL.c(100L);
            yVarL.d(new h.l(a3Var, 4));
            yVarL2 = this.f685f.l(0, 200L);
        } else {
            a3 a3Var2 = (a3) this.f684e;
            x.y yVarA = x.u.a(a3Var2.f1157a);
            yVarA.a(1.0f);
            yVarA.c(200L);
            yVarA.d(new h.l(a3Var2, 0));
            yVarL = this.f685f.l(8, 100L);
            yVarL2 = yVarA;
        }
        h.m mVar = new h.m();
        ArrayList arrayList = mVar.f951a;
        arrayList.add(yVarL);
        View view = (View) yVarL.f2020a.get();
        long duration = view != null ? view.animate().getDuration() : 0L;
        View view2 = (View) yVarL2.f2020a.get();
        if (view2 != null) {
            view2.animate().setStartDelay(duration);
        }
        arrayList.add(yVarL2);
        mVar.b();
    }

    public final Context j() {
        if (this.f681b == null) {
            TypedValue typedValue = new TypedValue();
            this.f680a.getTheme().resolveAttribute(com.snapay.app.R.attr.actionBarWidgetTheme, typedValue, true);
            int i2 = typedValue.resourceId;
            if (i2 != 0) {
                this.f681b = new ContextThemeWrapper(this.f680a, i2);
            } else {
                this.f681b = this.f680a;
            }
        }
        return this.f681b;
    }

    public final void k(View view) {
        f1 wrapper;
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) view.findViewById(com.snapay.app.R.id.decor_content_parent);
        this.f682c = actionBarOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        KeyEvent.Callback callbackFindViewById = view.findViewById(com.snapay.app.R.id.action_bar);
        if (callbackFindViewById instanceof f1) {
            wrapper = (f1) callbackFindViewById;
        } else {
            if (!(callbackFindViewById instanceof Toolbar)) {
                throw new IllegalStateException("Can't make a decor toolbar out of ".concat(callbackFindViewById != null ? callbackFindViewById.getClass().getSimpleName() : "null"));
            }
            wrapper = ((Toolbar) callbackFindViewById).getWrapper();
        }
        this.f684e = wrapper;
        this.f685f = (ActionBarContextView) view.findViewById(com.snapay.app.R.id.action_context_bar);
        ActionBarContainer actionBarContainer = (ActionBarContainer) view.findViewById(com.snapay.app.R.id.action_bar_container);
        this.f683d = actionBarContainer;
        f1 f1Var = this.f684e;
        if (f1Var == null || this.f685f == null || actionBarContainer == null) {
            throw new IllegalStateException(i0.class.getSimpleName().concat(" can only be used with a compatible window decor layout"));
        }
        Context context = ((a3) f1Var).f1157a.getContext();
        this.f680a = context;
        if ((((a3) this.f684e).f1158b & 4) != 0) {
            this.f687h = true;
        }
        int i2 = context.getApplicationInfo().targetSdkVersion;
        this.f684e.getClass();
        m(context.getResources().getBoolean(com.snapay.app.R.bool.abc_action_bar_embed_tabs));
        TypedArray typedArrayObtainStyledAttributes = this.f680a.obtainStyledAttributes(null, c.a.f481a, com.snapay.app.R.attr.actionBarStyle, 0);
        if (typedArrayObtainStyledAttributes.getBoolean(14, false)) {
            ActionBarOverlayLayout actionBarOverlayLayout2 = this.f682c;
            if (!actionBarOverlayLayout2.f148i) {
                throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
            }
            this.f700u = true;
            actionBarOverlayLayout2.setHideOnContentScrollEnabled(true);
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(12, 0);
        if (dimensionPixelSize != 0) {
            ActionBarContainer actionBarContainer2 = this.f683d;
            WeakHashMap weakHashMap = x.u.f2012a;
            actionBarContainer2.setElevation(dimensionPixelSize);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void l(boolean z2) {
        if (this.f687h) {
            return;
        }
        int i2 = z2 ? 4 : 0;
        a3 a3Var = (a3) this.f684e;
        int i3 = a3Var.f1158b;
        this.f687h = true;
        a3Var.a((i2 & 4) | (i3 & (-5)));
    }

    public final void m(boolean z2) {
        if (z2) {
            this.f683d.setTabContainer(null);
            ((a3) this.f684e).getClass();
        } else {
            ((a3) this.f684e).getClass();
            this.f683d.setTabContainer(null);
        }
        this.f684e.getClass();
        ((a3) this.f684e).f1157a.setCollapsible(false);
        this.f682c.setHasNonEmbeddedTabs(false);
    }

    public final void n(CharSequence charSequence) {
        a3 a3Var = (a3) this.f684e;
        if (a3Var.f1163g) {
            return;
        }
        a3Var.f1164h = charSequence;
        if ((a3Var.f1158b & 8) != 0) {
            a3Var.f1157a.setTitle(charSequence);
        }
    }

    public final void o(boolean z2) {
        boolean z3 = this.f696q || !this.f695p;
        View view = this.f686g;
        androidx.fragment.app.i iVar = this.f703x;
        if (!z3) {
            if (this.f697r) {
                this.f697r = false;
                h.m mVar = this.f698s;
                if (mVar != null) {
                    mVar.a();
                }
                int i2 = this.f693n;
                g0 g0Var = this.f701v;
                if (i2 != 0 || (!this.f699t && !z2)) {
                    g0Var.a();
                    return;
                }
                this.f683d.setAlpha(1.0f);
                this.f683d.setTransitioning(true);
                h.m mVar2 = new h.m();
                float f2 = -this.f683d.getHeight();
                if (z2) {
                    int[] iArr = {0, 0};
                    this.f683d.getLocationInWindow(iArr);
                    f2 -= iArr[1];
                }
                x.y yVarA = x.u.a(this.f683d);
                yVarA.e(f2);
                View view2 = (View) yVarA.f2020a.get();
                if (view2 != null) {
                    view2.animate().setUpdateListener(iVar != null ? new x.x(iVar, view2) : null);
                }
                boolean z4 = mVar2.f955e;
                ArrayList arrayList = mVar2.f951a;
                if (!z4) {
                    arrayList.add(yVarA);
                }
                if (this.f694o && view != null) {
                    x.y yVarA2 = x.u.a(view);
                    yVarA2.e(f2);
                    if (!mVar2.f955e) {
                        arrayList.add(yVarA2);
                    }
                }
                AccelerateInterpolator accelerateInterpolator = f678y;
                boolean z5 = mVar2.f955e;
                if (!z5) {
                    mVar2.f953c = accelerateInterpolator;
                }
                if (!z5) {
                    mVar2.f952b = 250L;
                }
                if (!z5) {
                    mVar2.f954d = g0Var;
                }
                this.f698s = mVar2;
                mVar2.b();
                return;
            }
            return;
        }
        if (this.f697r) {
            return;
        }
        this.f697r = true;
        h.m mVar3 = this.f698s;
        if (mVar3 != null) {
            mVar3.a();
        }
        this.f683d.setVisibility(0);
        int i3 = this.f693n;
        g0 g0Var2 = this.f702w;
        if (i3 == 0 && (this.f699t || z2)) {
            this.f683d.setTranslationY(0.0f);
            float f3 = -this.f683d.getHeight();
            if (z2) {
                int[] iArr2 = {0, 0};
                this.f683d.getLocationInWindow(iArr2);
                f3 -= iArr2[1];
            }
            this.f683d.setTranslationY(f3);
            h.m mVar4 = new h.m();
            x.y yVarA3 = x.u.a(this.f683d);
            yVarA3.e(0.0f);
            View view3 = (View) yVarA3.f2020a.get();
            if (view3 != null) {
                view3.animate().setUpdateListener(iVar != null ? new x.x(iVar, view3) : null);
            }
            boolean z6 = mVar4.f955e;
            ArrayList arrayList2 = mVar4.f951a;
            if (!z6) {
                arrayList2.add(yVarA3);
            }
            if (this.f694o && view != null) {
                view.setTranslationY(f3);
                x.y yVarA4 = x.u.a(view);
                yVarA4.e(0.0f);
                if (!mVar4.f955e) {
                    arrayList2.add(yVarA4);
                }
            }
            DecelerateInterpolator decelerateInterpolator = f679z;
            boolean z7 = mVar4.f955e;
            if (!z7) {
                mVar4.f953c = decelerateInterpolator;
            }
            if (!z7) {
                mVar4.f952b = 250L;
            }
            if (!z7) {
                mVar4.f954d = g0Var2;
            }
            this.f698s = mVar4;
            mVar4.b();
        } else {
            this.f683d.setAlpha(1.0f);
            this.f683d.setTranslationY(0.0f);
            if (this.f694o && view != null) {
                view.setTranslationY(0.0f);
            }
            g0Var2.a();
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.f682c;
        if (actionBarOverlayLayout != null) {
            WeakHashMap weakHashMap = x.u.f2012a;
            actionBarOverlayLayout.requestApplyInsets();
        }
    }

    public i0(Dialog dialog) {
        new ArrayList();
        this.f692m = new ArrayList();
        this.f693n = 0;
        this.f694o = true;
        this.f697r = true;
        this.f701v = new g0(this, 0);
        this.f702w = new g0(this, 1);
        this.f703x = new androidx.fragment.app.i(this);
        k(dialog.getWindow().getDecorView());
    }
}
