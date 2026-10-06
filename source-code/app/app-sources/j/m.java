package j;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.widget.ActionMenuView;
import com.snapay.app.R;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class m implements i.b0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f1294b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f1295c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public i.o f1296d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final LayoutInflater f1297e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public i.a0 f1298f;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public i.d0 f1301i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public l f1302j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Drawable f1303k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f1304l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f1305m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f1306n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f1307o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f1308p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f1309q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f1310r;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public h f1312t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public h f1313u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public j f1314v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public i f1315w;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f1299g = R.layout.abc_action_menu_layout;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f1300h = R.layout.abc_action_menu_item_layout;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final SparseBooleanArray f1311s = new SparseBooleanArray();

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final h.a f1316x = new h.a(this, 3);

    public m(Context context) {
        this.f1294b = context;
        this.f1297e = LayoutInflater.from(context);
    }

    @Override // i.b0
    public final void a(i.o oVar, boolean z2) {
        f();
        h hVar = this.f1313u;
        if (hVar != null && hVar.b()) {
            hVar.f1134j.dismiss();
        }
        i.a0 a0Var = this.f1298f;
        if (a0Var != null) {
            a0Var.a(oVar, z2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final View b(i.q qVar, View view, ViewGroup viewGroup) {
        View actionView = qVar.getActionView();
        if (actionView == null || qVar.e()) {
            i.c0 c0Var = view instanceof i.c0 ? (i.c0) view : (i.c0) this.f1297e.inflate(this.f1300h, viewGroup, false);
            c0Var.c(qVar);
            ActionMenuItemView actionMenuItemView = (ActionMenuItemView) c0Var;
            actionMenuItemView.setItemInvoker((ActionMenuView) this.f1301i);
            if (this.f1315w == null) {
                this.f1315w = new i(this);
            }
            actionMenuItemView.setPopupCallback(this.f1315w);
            actionView = (View) c0Var;
        }
        actionView.setVisibility(qVar.C ? 8 : 0);
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        if (!((ActionMenuView) viewGroup).checkLayoutParams(layoutParams)) {
            actionView.setLayoutParams(ActionMenuView.k(layoutParams));
        }
        return actionView;
    }

    @Override // i.b0
    public final /* bridge */ /* synthetic */ boolean c(i.q qVar) {
        return false;
    }

    @Override // i.b0
    public final boolean d() {
        ArrayList arrayListL;
        int size;
        int i2;
        boolean z2;
        i.o oVar = this.f1296d;
        if (oVar != null) {
            arrayListL = oVar.l();
            size = arrayListL.size();
        } else {
            arrayListL = null;
            size = 0;
        }
        int i3 = this.f1309q;
        int i4 = this.f1308p;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) this.f1301i;
        int i5 = 0;
        boolean z3 = false;
        int i6 = 0;
        int i7 = 0;
        while (true) {
            i2 = 2;
            z2 = true;
            if (i5 >= size) {
                break;
            }
            i.q qVar = (i.q) arrayListL.get(i5);
            int i8 = qVar.f1111y;
            if ((i8 & 2) == 2) {
                i6++;
            } else if ((i8 & 1) == 1) {
                i7++;
            } else {
                z3 = true;
            }
            if (this.f1310r && qVar.C) {
                i3 = 0;
            }
            i5++;
        }
        if (this.f1305m && (z3 || i7 + i6 > i3)) {
            i3--;
        }
        int i9 = i3 - i6;
        SparseBooleanArray sparseBooleanArray = this.f1311s;
        sparseBooleanArray.clear();
        int i10 = 0;
        int i11 = 0;
        while (i10 < size) {
            i.q qVar2 = (i.q) arrayListL.get(i10);
            int i12 = qVar2.f1111y;
            boolean z4 = (i12 & 2) == i2;
            int i13 = qVar2.f1088b;
            if (z4) {
                View viewB = b(qVar2, null, viewGroup);
                viewB.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                int measuredWidth = viewB.getMeasuredWidth();
                i4 -= measuredWidth;
                if (i11 == 0) {
                    i11 = measuredWidth;
                }
                if (i13 != 0) {
                    sparseBooleanArray.put(i13, z2);
                }
                qVar2.g(z2);
            } else {
                if ((i12 & 1) == z2) {
                    boolean z5 = sparseBooleanArray.get(i13);
                    boolean z6 = (i9 > 0 || z5) && i4 > 0;
                    if (z6) {
                        View viewB2 = b(qVar2, null, viewGroup);
                        viewB2.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                        int measuredWidth2 = viewB2.getMeasuredWidth();
                        i4 -= measuredWidth2;
                        if (i11 == 0) {
                            i11 = measuredWidth2;
                        }
                        z6 &= i4 + i11 > 0;
                    }
                    if (z6 && i13 != 0) {
                        sparseBooleanArray.put(i13, true);
                    } else if (z5) {
                        sparseBooleanArray.put(i13, false);
                        for (int i14 = 0; i14 < i10; i14++) {
                            i.q qVar3 = (i.q) arrayListL.get(i14);
                            if (qVar3.f1088b == i13) {
                                if (qVar3.f()) {
                                    i9++;
                                }
                                qVar3.g(false);
                            }
                        }
                    }
                    if (z6) {
                        i9--;
                    }
                    qVar2.g(z6);
                } else {
                    qVar2.g(false);
                }
                i10++;
                i2 = 2;
                z2 = true;
            }
            i10++;
            i2 = 2;
            z2 = true;
        }
        return true;
    }

    @Override // i.b0
    public final void e(Context context, i.o oVar) {
        this.f1295c = context;
        LayoutInflater.from(context);
        this.f1296d = oVar;
        Resources resources = context.getResources();
        if (!this.f1306n) {
            this.f1305m = true;
        }
        int i2 = 2;
        this.f1307o = context.getResources().getDisplayMetrics().widthPixels / 2;
        Configuration configuration = context.getResources().getConfiguration();
        int i3 = configuration.screenWidthDp;
        int i4 = configuration.screenHeightDp;
        if (configuration.smallestScreenWidthDp > 600 || i3 > 600 || ((i3 > 960 && i4 > 720) || (i3 > 720 && i4 > 960))) {
            i2 = 5;
        } else if (i3 >= 500 || ((i3 > 640 && i4 > 480) || (i3 > 480 && i4 > 640))) {
            i2 = 4;
        } else if (i3 >= 360) {
            i2 = 3;
        }
        this.f1309q = i2;
        int measuredWidth = this.f1307o;
        if (this.f1305m) {
            if (this.f1302j == null) {
                l lVar = new l(this, this.f1294b);
                this.f1302j = lVar;
                if (this.f1304l) {
                    lVar.setImageDrawable(this.f1303k);
                    this.f1303k = null;
                    this.f1304l = false;
                }
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.f1302j.measure(iMakeMeasureSpec, iMakeMeasureSpec);
            }
            measuredWidth -= this.f1302j.getMeasuredWidth();
        } else {
            this.f1302j = null;
        }
        this.f1308p = measuredWidth;
        float f2 = resources.getDisplayMetrics().density;
    }

    public final boolean f() {
        Object obj;
        j jVar = this.f1314v;
        if (jVar != null && (obj = this.f1301i) != null) {
            ((View) obj).removeCallbacks(jVar);
            this.f1314v = null;
            return true;
        }
        h hVar = this.f1312t;
        if (hVar == null) {
            return false;
        }
        if (hVar.b()) {
            hVar.f1134j.dismiss();
        }
        return true;
    }

    @Override // i.b0
    public final void g(i.a0 a0Var) {
        this.f1298f = a0Var;
    }

    @Override // i.b0
    public final /* bridge */ /* synthetic */ boolean h(i.q qVar) {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // i.b0
    public final void i() {
        int i2;
        boolean z2;
        ViewGroup viewGroup = (ViewGroup) this.f1301i;
        ArrayList arrayList = null;
        boolean z3 = false;
        if (viewGroup != null) {
            i.o oVar = this.f1296d;
            if (oVar != null) {
                oVar.i();
                ArrayList arrayListL = this.f1296d.l();
                int size = arrayListL.size();
                i2 = 0;
                for (int i3 = 0; i3 < size; i3++) {
                    i.q qVar = (i.q) arrayListL.get(i3);
                    if (qVar.f()) {
                        View childAt = viewGroup.getChildAt(i2);
                        i.q itemData = childAt instanceof i.c0 ? ((i.c0) childAt).getItemData() : null;
                        View viewB = b(qVar, childAt, viewGroup);
                        if (qVar != itemData) {
                            viewB.setPressed(false);
                            viewB.jumpDrawablesToCurrentState();
                        }
                        if (viewB != childAt) {
                            ViewGroup viewGroup2 = (ViewGroup) viewB.getParent();
                            if (viewGroup2 != null) {
                                viewGroup2.removeView(viewB);
                            }
                            ((ViewGroup) this.f1301i).addView(viewB, i2);
                        }
                        i2++;
                    }
                }
            } else {
                i2 = 0;
            }
            while (i2 < viewGroup.getChildCount()) {
                if (viewGroup.getChildAt(i2) == this.f1302j) {
                    z2 = false;
                } else {
                    viewGroup.removeViewAt(i2);
                    z2 = true;
                }
                if (!z2) {
                    i2++;
                }
            }
        }
        ((View) this.f1301i).requestLayout();
        i.o oVar2 = this.f1296d;
        if (oVar2 != null) {
            oVar2.i();
            ArrayList arrayList2 = oVar2.f1068i;
            int size2 = arrayList2.size();
            for (int i4 = 0; i4 < size2; i4++) {
                i.r rVar = ((i.q) arrayList2.get(i4)).A;
            }
        }
        i.o oVar3 = this.f1296d;
        if (oVar3 != null) {
            oVar3.i();
            arrayList = oVar3.f1069j;
        }
        if (this.f1305m && arrayList != null) {
            int size3 = arrayList.size();
            if (size3 == 1) {
                z3 = !((i.q) arrayList.get(0)).C;
            } else if (size3 > 0) {
                z3 = true;
            }
        }
        l lVar = this.f1302j;
        if (z3) {
            if (lVar == null) {
                this.f1302j = new l(this, this.f1294b);
            }
            ViewGroup viewGroup3 = (ViewGroup) this.f1302j.getParent();
            if (viewGroup3 != this.f1301i) {
                if (viewGroup3 != null) {
                    viewGroup3.removeView(this.f1302j);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.f1301i;
                l lVar2 = this.f1302j;
                actionMenuView.getClass();
                p pVar = new p();
                ((LinearLayout.LayoutParams) pVar).gravity = 16;
                pVar.f1347a = true;
                actionMenuView.addView(lVar2, pVar);
            }
        } else if (lVar != null) {
            Object parent = lVar.getParent();
            Object obj = this.f1301i;
            if (parent == obj) {
                ((ViewGroup) obj).removeView(this.f1302j);
            }
        }
        ((ActionMenuView) this.f1301i).setOverflowReserved(this.f1305m);
    }

    public final boolean j() {
        h hVar = this.f1312t;
        return hVar != null && hVar.b();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // i.b0
    public final boolean k(i.h0 h0Var) {
        View childAt;
        boolean z2;
        boolean z3 = false;
        if (!h0Var.hasVisibleItems()) {
            return false;
        }
        i.h0 h0Var2 = h0Var;
        while (true) {
            i.o oVar = h0Var2.f1020z;
            if (oVar == this.f1296d) {
                break;
            }
            h0Var2 = (i.h0) oVar;
        }
        ViewGroup viewGroup = (ViewGroup) this.f1301i;
        if (viewGroup != null) {
            int childCount = viewGroup.getChildCount();
            int i2 = 0;
            while (true) {
                if (i2 >= childCount) {
                    childAt = 0;
                    break;
                }
                childAt = viewGroup.getChildAt(i2);
                if ((childAt instanceof i.c0) && ((i.c0) childAt).getItemData() == h0Var2.A) {
                    break;
                }
                i2++;
            }
        } else {
            childAt = 0;
            break;
        }
        if (childAt == 0) {
            return false;
        }
        h0Var.A.getClass();
        int size = h0Var.size();
        int i3 = 0;
        while (true) {
            if (i3 >= size) {
                z2 = false;
                break;
            }
            MenuItem item = h0Var.getItem(i3);
            if (item.isVisible() && item.getIcon() != null) {
                z2 = true;
                break;
            }
            i3++;
        }
        h hVar = new h(this, this.f1295c, h0Var, childAt);
        this.f1313u = hVar;
        hVar.f1132h = z2;
        i.x xVar = hVar.f1134j;
        if (xVar != null) {
            xVar.o(z2);
        }
        h hVar2 = this.f1313u;
        if (hVar2.b()) {
            z3 = true;
        } else if (hVar2.f1130f != null) {
            hVar2.d(0, 0, false, false);
            z3 = true;
        }
        if (!z3) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
        i.a0 a0Var = this.f1298f;
        if (a0Var != null) {
            a0Var.c(h0Var);
        }
        return true;
    }

    public final boolean l() {
        i.o oVar;
        if (this.f1305m && !j() && (oVar = this.f1296d) != null && this.f1301i != null && this.f1314v == null) {
            oVar.i();
            if (!oVar.f1069j.isEmpty()) {
                j jVar = new j(this, new h(this, this.f1295c, this.f1296d, this.f1302j), 0);
                this.f1314v = jVar;
                ((View) this.f1301i).post(jVar);
                return true;
            }
        }
        return false;
    }
}
