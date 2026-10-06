package i;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.snapay.app.R;
import j.i1;
import j.w1;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class i extends x implements View.OnKeyListener, PopupWindow.OnDismissListener {
    public PopupWindow.OnDismissListener A;
    public boolean B;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Context f1021c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f1022d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f1023e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f1024f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f1025g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Handler f1026h;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final e f1029k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final f f1030l;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public View f1034p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public View f1035q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f1036r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f1037s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f1038t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f1039u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f1040v;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f1042x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public a0 f1043y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public ViewTreeObserver f1044z;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ArrayList f1027i = new ArrayList();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final ArrayList f1028j = new ArrayList();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final h.a f1031m = new h.a(this, 1);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f1032n = 0;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f1033o = 0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f1041w = false;

    public i(Context context, View view, int i2, int i3, boolean z2) {
        int i4 = 0;
        this.f1029k = new e(this, i4);
        this.f1030l = new f(this, i4);
        this.f1021c = context;
        this.f1034p = view;
        this.f1023e = i2;
        this.f1024f = i3;
        this.f1025g = z2;
        WeakHashMap weakHashMap = x.u.f2012a;
        this.f1036r = view.getLayoutDirection() != 1 ? 1 : 0;
        Resources resources = context.getResources();
        this.f1022d = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.f1026h = new Handler();
    }

    @Override // i.b0
    public final void a(o oVar, boolean z2) {
        int i2;
        ArrayList arrayList = this.f1028j;
        int size = arrayList.size();
        int i3 = 0;
        while (true) {
            if (i3 >= size) {
                i3 = -1;
                break;
            } else if (oVar == ((h) arrayList.get(i3)).f1018b) {
                break;
            } else {
                i3++;
            }
        }
        if (i3 < 0) {
            return;
        }
        int i4 = i3 + 1;
        if (i4 < arrayList.size()) {
            ((h) arrayList.get(i4)).f1018b.c(false);
        }
        h hVar = (h) arrayList.remove(i3);
        hVar.f1018b.r(this);
        boolean z3 = this.B;
        w1 w1Var = hVar.f1017a;
        if (z3) {
            if (Build.VERSION.SDK_INT >= 23) {
                w1Var.f1441z.setExitTransition(null);
            } else {
                w1Var.getClass();
            }
            w1Var.f1441z.setAnimationStyle(0);
        }
        w1Var.dismiss();
        int size2 = arrayList.size();
        if (size2 > 0) {
            i2 = ((h) arrayList.get(size2 - 1)).f1019c;
        } else {
            View view = this.f1034p;
            WeakHashMap weakHashMap = x.u.f2012a;
            i2 = view.getLayoutDirection() == 1 ? 0 : 1;
        }
        this.f1036r = i2;
        if (size2 != 0) {
            if (z2) {
                ((h) arrayList.get(0)).f1018b.c(false);
                return;
            }
            return;
        }
        dismiss();
        a0 a0Var = this.f1043y;
        if (a0Var != null) {
            a0Var.a(oVar, true);
        }
        ViewTreeObserver viewTreeObserver = this.f1044z;
        if (viewTreeObserver != null) {
            if (viewTreeObserver.isAlive()) {
                this.f1044z.removeGlobalOnLayoutListener(this.f1029k);
            }
            this.f1044z = null;
        }
        this.f1035q.removeOnAttachStateChangeListener(this.f1030l);
        this.A.onDismiss();
    }

    @Override // i.f0
    public final boolean b() {
        ArrayList arrayList = this.f1028j;
        return arrayList.size() > 0 && ((h) arrayList.get(0)).f1017a.b();
    }

    @Override // i.b0
    public final boolean d() {
        return false;
    }

    @Override // i.f0
    public final void dismiss() {
        ArrayList arrayList = this.f1028j;
        int size = arrayList.size();
        if (size <= 0) {
            return;
        }
        h[] hVarArr = (h[]) arrayList.toArray(new h[size]);
        while (true) {
            size--;
            if (size < 0) {
                return;
            }
            h hVar = hVarArr[size];
            if (hVar.f1017a.b()) {
                hVar.f1017a.dismiss();
            }
        }
    }

    @Override // i.f0
    public final void f() {
        if (b()) {
            return;
        }
        ArrayList arrayList = this.f1027i;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            v((o) it.next());
        }
        arrayList.clear();
        View view = this.f1034p;
        this.f1035q = view;
        if (view != null) {
            boolean z2 = this.f1044z == null;
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            this.f1044z = viewTreeObserver;
            if (z2) {
                viewTreeObserver.addOnGlobalLayoutListener(this.f1029k);
            }
            this.f1035q.addOnAttachStateChangeListener(this.f1030l);
        }
    }

    @Override // i.b0
    public final void g(a0 a0Var) {
        this.f1043y = a0Var;
    }

    @Override // i.b0
    public final void i() {
        Iterator it = this.f1028j.iterator();
        while (it.hasNext()) {
            ListAdapter adapter = ((h) it.next()).f1017a.f1419d.getAdapter();
            if (adapter instanceof HeaderViewListAdapter) {
                adapter = ((HeaderViewListAdapter) adapter).getWrappedAdapter();
            }
            ((l) adapter).notifyDataSetChanged();
        }
    }

    @Override // i.f0
    public final i1 j() {
        ArrayList arrayList = this.f1028j;
        if (arrayList.isEmpty()) {
            return null;
        }
        return ((h) arrayList.get(arrayList.size() - 1)).f1017a.f1419d;
    }

    @Override // i.b0
    public final boolean k(h0 h0Var) {
        for (h hVar : this.f1028j) {
            if (h0Var == hVar.f1018b) {
                hVar.f1017a.f1419d.requestFocus();
                return true;
            }
        }
        if (!h0Var.hasVisibleItems()) {
            return false;
        }
        l(h0Var);
        a0 a0Var = this.f1043y;
        if (a0Var != null) {
            a0Var.c(h0Var);
        }
        return true;
    }

    @Override // i.x
    public final void l(o oVar) {
        oVar.b(this, this.f1021c);
        if (b()) {
            v(oVar);
        } else {
            this.f1027i.add(oVar);
        }
    }

    @Override // i.x
    public final void n(View view) {
        if (this.f1034p != view) {
            this.f1034p = view;
            int i2 = this.f1032n;
            WeakHashMap weakHashMap = x.u.f2012a;
            this.f1033o = Gravity.getAbsoluteGravity(i2, view.getLayoutDirection());
        }
    }

    @Override // i.x
    public final void o(boolean z2) {
        this.f1041w = z2;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        h hVar;
        ArrayList arrayList = this.f1028j;
        int size = arrayList.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                hVar = null;
                break;
            }
            hVar = (h) arrayList.get(i2);
            if (!hVar.f1017a.b()) {
                break;
            } else {
                i2++;
            }
        }
        if (hVar != null) {
            hVar.f1018b.c(false);
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
        if (this.f1032n != i2) {
            this.f1032n = i2;
            View view = this.f1034p;
            WeakHashMap weakHashMap = x.u.f2012a;
            this.f1033o = Gravity.getAbsoluteGravity(i2, view.getLayoutDirection());
        }
    }

    @Override // i.x
    public final void q(int i2) {
        this.f1037s = true;
        this.f1039u = i2;
    }

    @Override // i.x
    public final void r(PopupWindow.OnDismissListener onDismissListener) {
        this.A = onDismissListener;
    }

    @Override // i.x
    public final void s(boolean z2) {
        this.f1042x = z2;
    }

    @Override // i.x
    public final void t(int i2) {
        this.f1038t = true;
        this.f1040v = i2;
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0096  */
    public final void v(o oVar) {
        View childAt;
        h hVar;
        char c2;
        int i2;
        int i3;
        int width;
        MenuItem item;
        l lVar;
        int headersCount;
        int firstVisiblePosition;
        Context context = this.f1021c;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        l lVar2 = new l(oVar, layoutInflaterFrom, this.f1025g, R.layout.abc_cascading_menu_item_layout);
        if (!b() && this.f1041w) {
            lVar2.f1055d = true;
        } else if (b()) {
            lVar2.f1055d = x.u(oVar);
        }
        int iM = x.m(lVar2, context, this.f1022d);
        w1 w1Var = new w1(context, this.f1023e, this.f1024f);
        w1Var.D = this.f1031m;
        w1Var.f1432q = this;
        j.d0 d0Var = w1Var.f1441z;
        d0Var.setOnDismissListener(this);
        w1Var.f1431p = this.f1034p;
        w1Var.f1428m = this.f1033o;
        w1Var.f1440y = true;
        d0Var.setFocusable(true);
        d0Var.setInputMethodMode(2);
        w1Var.o(lVar2);
        w1Var.r(iM);
        w1Var.f1428m = this.f1033o;
        ArrayList arrayList = this.f1028j;
        if (arrayList.size() > 0) {
            hVar = (h) arrayList.get(arrayList.size() - 1);
            o oVar2 = hVar.f1018b;
            int size = oVar2.size();
            int i4 = 0;
            while (true) {
                if (i4 >= size) {
                    item = null;
                    break;
                }
                item = oVar2.getItem(i4);
                if (item.hasSubMenu() && oVar == item.getSubMenu()) {
                    break;
                } else {
                    i4++;
                }
            }
            if (item == null) {
                childAt = null;
            } else {
                i1 i1Var = hVar.f1017a.f1419d;
                ListAdapter adapter = i1Var.getAdapter();
                if (adapter instanceof HeaderViewListAdapter) {
                    HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
                    headersCount = headerViewListAdapter.getHeadersCount();
                    lVar = (l) headerViewListAdapter.getWrappedAdapter();
                } else {
                    lVar = (l) adapter;
                    headersCount = 0;
                }
                int count = lVar.getCount();
                int i5 = 0;
                while (true) {
                    if (i5 >= count) {
                        i5 = -1;
                        break;
                    } else if (item == lVar.getItem(i5)) {
                        break;
                    } else {
                        i5++;
                    }
                }
                if (i5 != -1 && (firstVisiblePosition = (i5 + headersCount) - i1Var.getFirstVisiblePosition()) >= 0 && firstVisiblePosition < i1Var.getChildCount()) {
                    childAt = i1Var.getChildAt(firstVisiblePosition);
                } else {
                    childAt = null;
                }
            }
        } else {
            childAt = null;
            hVar = null;
        }
        if (childAt != null) {
            if (Build.VERSION.SDK_INT <= 28) {
                Method method = w1.E;
                if (method != null) {
                    try {
                        method.invoke(d0Var, Boolean.FALSE);
                    } catch (Exception unused) {
                        Log.i("MenuPopupWindow", "Could not invoke setTouchModal() on PopupWindow. Oh well.");
                    }
                }
            } else {
                d0Var.setTouchModal(false);
            }
            int i6 = Build.VERSION.SDK_INT;
            if (i6 >= 23) {
                d0Var.setEnterTransition(null);
            }
            i1 i1Var2 = ((h) arrayList.get(arrayList.size() - 1)).f1017a.f1419d;
            int[] iArr = new int[2];
            i1Var2.getLocationOnScreen(iArr);
            Rect rect = new Rect();
            this.f1035q.getWindowVisibleDisplayFrame(rect);
            int i7 = (this.f1036r != 1 ? iArr[0] - iM >= 0 : (i1Var2.getWidth() + iArr[0]) + iM > rect.right) ? 0 : 1;
            boolean z2 = i7 == 1;
            this.f1036r = i7;
            if (i6 >= 26) {
                w1Var.f1431p = childAt;
                i3 = 0;
                i2 = 0;
            } else {
                int[] iArr2 = new int[2];
                this.f1034p.getLocationOnScreen(iArr2);
                int[] iArr3 = new int[2];
                childAt.getLocationOnScreen(iArr3);
                if ((this.f1033o & 7) == 5) {
                    c2 = 0;
                    iArr2[0] = this.f1034p.getWidth() + iArr2[0];
                    iArr3[0] = childAt.getWidth() + iArr3[0];
                } else {
                    c2 = 0;
                }
                i2 = iArr3[c2] - iArr2[c2];
                i3 = iArr3[1] - iArr2[1];
            }
            if ((this.f1033o & 5) != 5) {
                width = z2 ? i2 + childAt.getWidth() : i2 - iM;
            } else if (z2) {
                width = i2 + iM;
            } else {
                iM = childAt.getWidth();
            }
            w1Var.f1422g = width;
            w1Var.f1427l = true;
            w1Var.f1426k = true;
            w1Var.n(i3);
        } else {
            if (this.f1037s) {
                w1Var.f1422g = this.f1039u;
            }
            if (this.f1038t) {
                w1Var.n(this.f1040v);
            }
            Rect rect2 = this.f1123b;
            w1Var.f1439x = rect2 != null ? new Rect(rect2) : null;
        }
        arrayList.add(new h(w1Var, oVar, this.f1036r));
        w1Var.f();
        i1 i1Var3 = w1Var.f1419d;
        i1Var3.setOnKeyListener(this);
        if (hVar == null && this.f1042x && oVar.f1072m != null) {
            FrameLayout frameLayout = (FrameLayout) layoutInflaterFrom.inflate(R.layout.abc_popup_menu_header_item_layout, (ViewGroup) i1Var3, false);
            TextView textView = (TextView) frameLayout.findViewById(android.R.id.title);
            frameLayout.setEnabled(false);
            textView.setText(oVar.f1072m);
            i1Var3.addHeaderView(frameLayout, null, false);
            w1Var.f();
        }
    }
}
