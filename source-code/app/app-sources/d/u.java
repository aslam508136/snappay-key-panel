package d;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.PopupWindow;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ViewStubCompat;
import com.snapay.app.R;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class u implements Window.Callback {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Window.Callback f722b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ a0 f723c;

    public u(a0 a0Var, Window.Callback callback) {
        this.f723c = a0Var;
        if (callback == null) {
            throw new IllegalArgumentException("Window callback may not be null");
        }
        this.f722b = callback;
    }

    @Override // android.view.Window.Callback
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        return this.f722b.dispatchGenericMotionEvent(motionEvent);
    }

    public final boolean b(KeyEvent keyEvent) {
        return this.f722b.dispatchKeyEvent(keyEvent);
    }

    public final boolean c(KeyEvent keyEvent) {
        return this.f722b.dispatchKeyShortcutEvent(keyEvent);
    }

    @Override // android.view.Window.Callback
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return this.f722b.dispatchPopulateAccessibilityEvent(accessibilityEvent);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return this.f723c.p(keyEvent) || b(keyEvent);
    }

    /* JADX WARN: Code duplicated, block: B:19:0x003b  */
    /* JADX WARN: Code duplicated, block: B:26:0x0050  */
    /* JADX WARN: Code duplicated, block: B:28:0x0054  */
    /* JADX WARN: Code duplicated, block: B:30:0x0067  */
    @Override // android.view.Window.Callback
    public final boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        z zVar;
        boolean z2;
        boolean zPerformShortcut;
        i.o oVar;
        if (c(keyEvent)) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        a0 a0Var = this.f723c;
        a0Var.w();
        i0 i0Var = a0Var.f586i;
        if (i0Var == null) {
            zVar = a0Var.H;
            if (zVar == null && a0Var.z(zVar, keyEvent.getKeyCode(), keyEvent)) {
                z zVar2 = a0Var.H;
                if (zVar2 != null) {
                    zVar2.f743l = true;
                }
            } else {
                if (a0Var.H == null) {
                    z zVarU = a0Var.u(0);
                    a0Var.A(zVarU, keyEvent);
                    boolean z3 = a0Var.z(zVarU, keyEvent.getKeyCode(), keyEvent);
                    zVarU.f742k = false;
                    if (z3) {
                    }
                }
            }
        } else {
            h0 h0Var = i0Var.f688i;
            if (h0Var == null || (oVar = h0Var.f648e) == null) {
                zPerformShortcut = false;
            } else {
                oVar.setQwertyMode(KeyCharacterMap.load(keyEvent.getDeviceId()).getKeyboardType() != 1);
                zPerformShortcut = oVar.performShortcut(keyCode, keyEvent, 0);
            }
            if (!zPerformShortcut) {
                zVar = a0Var.H;
                if (zVar == null) {
                    if (a0Var.H == null) {
                        z zVarU2 = a0Var.u(0);
                        a0Var.A(zVarU2, keyEvent);
                        boolean z4 = a0Var.z(zVarU2, keyEvent.getKeyCode(), keyEvent);
                        zVarU2.f742k = false;
                        z2 = z4;
                    }
                } else {
                    if (a0Var.H == null) {
                        z zVarU3 = a0Var.u(0);
                        a0Var.A(zVarU3, keyEvent);
                        boolean z5 = a0Var.z(zVarU3, keyEvent.getKeyCode(), keyEvent);
                        zVarU3.f742k = false;
                        if (z5) {
                        }
                    }
                }
            }
        }
        return z2;
    }

    @Override // android.view.Window.Callback
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return this.f722b.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public final boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        return this.f722b.dispatchTrackballEvent(motionEvent);
    }

    @Override // android.view.Window.Callback
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public final void onActionModeFinished(ActionMode actionMode) {
        this.f722b.onActionModeFinished(actionMode);
    }

    @Override // android.view.Window.Callback
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public final void onActionModeStarted(ActionMode actionMode) {
        this.f722b.onActionModeStarted(actionMode);
    }

    @Override // android.view.Window.Callback
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public final void onAttachedToWindow() {
        this.f722b.onAttachedToWindow();
    }

    public final boolean j(int i2, Menu menu) {
        return this.f722b.onCreatePanelMenu(i2, menu);
    }

    @Override // android.view.Window.Callback
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public final View onCreatePanelView(int i2) {
        return this.f722b.onCreatePanelView(i2);
    }

    @Override // android.view.Window.Callback
    /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
    public final void onDetachedFromWindow() {
        this.f722b.onDetachedFromWindow();
    }

    @Override // android.view.Window.Callback
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public final boolean onMenuItemSelected(int i2, MenuItem menuItem) {
        return this.f722b.onMenuItemSelected(i2, menuItem);
    }

    public final boolean n(int i2, Menu menu) {
        return this.f722b.onMenuOpened(i2, menu);
    }

    public final void o(int i2, Menu menu) {
        this.f722b.onPanelClosed(i2, menu);
    }

    @Override // android.view.Window.Callback
    public final void onContentChanged() {
    }

    @Override // android.view.Window.Callback
    public final boolean onCreatePanelMenu(int i2, Menu menu) {
        if (i2 != 0 || (menu instanceof i.o)) {
            return j(i2, menu);
        }
        return false;
    }

    @Override // android.view.Window.Callback
    public final boolean onMenuOpened(int i2, Menu menu) {
        n(i2, menu);
        a0 a0Var = this.f723c;
        if (i2 == 108) {
            a0Var.w();
            i0 i0Var = a0Var.f586i;
            if (i0Var != null && true != i0Var.f691l) {
                i0Var.f691l = true;
                ArrayList arrayList = i0Var.f692m;
                if (arrayList.size() > 0) {
                    androidx.activity.c.b(arrayList.get(0));
                    throw null;
                }
            }
        } else {
            a0Var.getClass();
        }
        return true;
    }

    @Override // android.view.Window.Callback
    public final void onPanelClosed(int i2, Menu menu) {
        o(i2, menu);
        a0 a0Var = this.f723c;
        if (i2 != 108) {
            if (i2 != 0) {
                a0Var.getClass();
                return;
            }
            z zVarU = a0Var.u(i2);
            if (zVarU.f744m) {
                a0Var.n(zVarU, false);
                return;
            }
            return;
        }
        a0Var.w();
        i0 i0Var = a0Var.f586i;
        if (i0Var == null || !i0Var.f691l) {
            return;
        }
        i0Var.f691l = false;
        ArrayList arrayList = i0Var.f692m;
        if (arrayList.size() <= 0) {
            return;
        }
        androidx.activity.c.b(arrayList.get(0));
        throw null;
    }

    @Override // android.view.Window.Callback
    public final boolean onPreparePanel(int i2, View view, Menu menu) {
        i.o oVar = menu instanceof i.o ? (i.o) menu : null;
        if (i2 == 0 && oVar == null) {
            return false;
        }
        if (oVar != null) {
            oVar.f1083x = true;
        }
        boolean zQ = q(i2, view, menu);
        if (oVar != null) {
            oVar.f1083x = false;
        }
        return zQ;
    }

    @Override // android.view.Window.Callback
    public final void onProvideKeyboardShortcuts(List list, Menu menu, int i2) {
        i.o oVar = this.f723c.u(0).f739h;
        if (oVar != null) {
            r(list, oVar, i2);
        } else {
            r(list, menu, i2);
        }
    }

    @Override // android.view.Window.Callback
    public final ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        if (Build.VERSION.SDK_INT >= 23) {
            return null;
        }
        return this.f723c.f597t ? y(callback) : w(callback);
    }

    @Override // android.view.Window.Callback
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public final void onPointerCaptureChanged(boolean z2) {
        this.f722b.onPointerCaptureChanged(z2);
    }

    public final boolean q(int i2, View view, Menu menu) {
        return this.f722b.onPreparePanel(i2, view, menu);
    }

    public final void r(List list, Menu menu, int i2) {
        this.f722b.onProvideKeyboardShortcuts(list, menu, i2);
    }

    @Override // android.view.Window.Callback
    /* JADX INFO: renamed from: s, reason: merged with bridge method [inline-methods] */
    public final boolean onSearchRequested() {
        return this.f722b.onSearchRequested();
    }

    @Override // android.view.Window.Callback
    /* JADX INFO: renamed from: t, reason: merged with bridge method [inline-methods] */
    public final boolean onSearchRequested(SearchEvent searchEvent) {
        return this.f722b.onSearchRequested(searchEvent);
    }

    @Override // android.view.Window.Callback
    /* JADX INFO: renamed from: u, reason: merged with bridge method [inline-methods] */
    public final void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
        this.f722b.onWindowAttributesChanged(layoutParams);
    }

    @Override // android.view.Window.Callback
    /* JADX INFO: renamed from: v, reason: merged with bridge method [inline-methods] */
    public final void onWindowFocusChanged(boolean z2) {
        this.f722b.onWindowFocusChanged(z2);
    }

    public final ActionMode w(ActionMode.Callback callback) {
        return this.f722b.onWindowStartingActionMode(callback);
    }

    public final ActionMode x(ActionMode.Callback callback, int i2) {
        return this.f722b.onWindowStartingActionMode(callback, i2);
    }

    /* JADX WARN: Code duplicated, block: B:67:0x0182  */
    public final h.h y(ActionMode.Callback callback) {
        boolean z2;
        ViewGroup viewGroup;
        a0 a0Var = this.f723c;
        h.g gVar = new h.g(a0Var.f582e, callback);
        h.c cVar = a0Var.f592o;
        if (cVar != null) {
            cVar.a();
        }
        t tVar = new t(a0Var, gVar);
        a0Var.w();
        i0 i0Var = a0Var.f586i;
        int i2 = 1;
        o oVar = a0Var.f585h;
        if (i0Var != null) {
            h0 h0Var = i0Var.f688i;
            if (h0Var != null) {
                h0Var.a();
            }
            i0Var.f682c.setHideOnContentScrollEnabled(false);
            i0Var.f685f.e();
            h0 h0Var2 = new h0(i0Var, i0Var.f685f.getContext(), tVar);
            i.o oVar2 = h0Var2.f648e;
            oVar2.w();
            try {
                boolean zD = h0Var2.f649f.d(h0Var2, oVar2);
                oVar2.v();
                if (zD) {
                    i0Var.f688i = h0Var2;
                    h0Var2.i();
                    i0Var.f685f.c(h0Var2);
                    i0Var.i(true);
                    i0Var.f685f.sendAccessibilityEvent(32);
                } else {
                    h0Var2 = null;
                }
                a0Var.f592o = h0Var2;
                if (h0Var2 != null && oVar != null) {
                    oVar.b();
                }
            } catch (Throwable th) {
                oVar2.v();
                throw th;
            }
        }
        if (a0Var.f592o == null) {
            x.y yVar = a0Var.f596s;
            if (yVar != null) {
                yVar.b();
            }
            h.c cVar2 = a0Var.f592o;
            if (cVar2 != null) {
                cVar2.a();
            }
            if (oVar != null && !a0Var.M) {
                try {
                    oVar.f();
                } catch (AbstractMethodError unused) {
                }
            }
            if (a0Var.f593p == null) {
                boolean z3 = a0Var.D;
                Context context = a0Var.f582e;
                if (z3) {
                    TypedValue typedValue = new TypedValue();
                    Resources.Theme theme = context.getTheme();
                    theme.resolveAttribute(R.attr.actionBarTheme, typedValue, true);
                    if (typedValue.resourceId != 0) {
                        Resources.Theme themeNewTheme = context.getResources().newTheme();
                        themeNewTheme.setTo(theme);
                        themeNewTheme.applyStyle(typedValue.resourceId, true);
                        h.e eVar = new h.e(context, 0);
                        eVar.getTheme().setTo(themeNewTheme);
                        context = eVar;
                    }
                    a0Var.f593p = new ActionBarContextView(context, null);
                    PopupWindow popupWindow = new PopupWindow(context, (AttributeSet) null, R.attr.actionModePopupWindowStyle);
                    a0Var.f594q = popupWindow;
                    androidx.lifecycle.i.h0(popupWindow, 2);
                    a0Var.f594q.setContentView(a0Var.f593p);
                    a0Var.f594q.setWidth(-1);
                    context.getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true);
                    a0Var.f593p.setContentHeight(TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics()));
                    a0Var.f594q.setHeight(-2);
                    a0Var.f595r = new q(a0Var, i2);
                } else {
                    ViewStubCompat viewStubCompat = (ViewStubCompat) a0Var.f599v.findViewById(R.id.action_mode_bar_stub);
                    if (viewStubCompat != null) {
                        a0Var.w();
                        i0 i0Var2 = a0Var.f586i;
                        Context contextJ = i0Var2 != null ? i0Var2.j() : null;
                        if (contextJ != null) {
                            context = contextJ;
                        }
                        viewStubCompat.setLayoutInflater(LayoutInflater.from(context));
                        a0Var.f593p = (ActionBarContextView) viewStubCompat.a();
                    }
                }
            }
            if (a0Var.f593p != null) {
                x.y yVar2 = a0Var.f596s;
                if (yVar2 != null) {
                    yVar2.b();
                }
                a0Var.f593p.e();
                h.f fVar = new h.f(a0Var.f593p.getContext(), a0Var.f593p, tVar);
                if (tVar.d(fVar, fVar.f905i)) {
                    fVar.i();
                    a0Var.f593p.c(fVar);
                    a0Var.f592o = fVar;
                    if (!a0Var.f598u || (viewGroup = a0Var.f599v) == null) {
                        z2 = false;
                    } else {
                        WeakHashMap weakHashMap = x.u.f2012a;
                        if (viewGroup.isLaidOut()) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                    }
                    if (z2) {
                        a0Var.f593p.setAlpha(0.0f);
                        x.y yVarA = x.u.a(a0Var.f593p);
                        yVarA.a(1.0f);
                        a0Var.f596s = yVarA;
                        yVarA.d(new s(a0Var, i2));
                    } else {
                        a0Var.f593p.setAlpha(1.0f);
                        a0Var.f593p.setVisibility(0);
                        a0Var.f593p.sendAccessibilityEvent(32);
                        if (a0Var.f593p.getParent() instanceof View) {
                            View view = (View) a0Var.f593p.getParent();
                            WeakHashMap weakHashMap2 = x.u.f2012a;
                            view.requestApplyInsets();
                        }
                    }
                    if (a0Var.f594q != null) {
                        a0Var.f583f.getDecorView().post(a0Var.f595r);
                    }
                } else {
                    a0Var.f592o = null;
                }
            }
            if (a0Var.f592o != null && oVar != null) {
                oVar.b();
            }
            a0Var.f592o = a0Var.f592o;
        }
        h.c cVar3 = a0Var.f592o;
        if (cVar3 != null) {
            return gVar.f(cVar3);
        }
        return null;
    }

    @Override // android.view.Window.Callback
    public final ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i2) {
        return (this.f723c.f597t && i2 == 0) ? y(callback) : x(callback, i2);
    }
}
