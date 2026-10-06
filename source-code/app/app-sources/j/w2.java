package j;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class w2 implements i.b0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public i.o f1484b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public i.q f1485c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Toolbar f1486d;

    public w2(Toolbar toolbar) {
        this.f1486d = toolbar;
    }

    @Override // i.b0
    public final void a(i.o oVar, boolean z2) {
    }

    @Override // i.b0
    public final boolean c(i.q qVar) {
        Toolbar toolbar = this.f1486d;
        KeyEvent.Callback callback = toolbar.f218j;
        if (callback instanceof h.d) {
            ((h.d) callback).d();
        }
        toolbar.removeView(toolbar.f218j);
        toolbar.removeView(toolbar.f217i);
        toolbar.f218j = null;
        ArrayList arrayList = toolbar.F;
        int size = arrayList.size();
        while (true) {
            size--;
            if (size < 0) {
                arrayList.clear();
                this.f1485c = null;
                toolbar.requestLayout();
                qVar.C = false;
                qVar.f1100n.p(false);
                return true;
            }
            toolbar.addView((View) arrayList.get(size));
        }
    }

    @Override // i.b0
    public final boolean d() {
        return false;
    }

    @Override // i.b0
    public final void e(Context context, i.o oVar) {
        i.q qVar;
        i.o oVar2 = this.f1484b;
        if (oVar2 != null && (qVar = this.f1485c) != null) {
            oVar2.d(qVar);
        }
        this.f1484b = oVar;
    }

    @Override // i.b0
    public final boolean h(i.q qVar) {
        Toolbar toolbar = this.f1486d;
        toolbar.c();
        ViewParent parent = toolbar.f217i.getParent();
        if (parent != toolbar) {
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(toolbar.f217i);
            }
            toolbar.addView(toolbar.f217i);
        }
        View actionView = qVar.getActionView();
        toolbar.f218j = actionView;
        this.f1485c = qVar;
        ViewParent parent2 = actionView.getParent();
        if (parent2 != toolbar) {
            if (parent2 instanceof ViewGroup) {
                ((ViewGroup) parent2).removeView(toolbar.f218j);
            }
            x2 x2Var = new x2();
            x2Var.f576a = (toolbar.f223o & 112) | 8388611;
            x2Var.f1494b = 2;
            toolbar.f218j.setLayoutParams(x2Var);
            toolbar.addView(toolbar.f218j);
        }
        int childCount = toolbar.getChildCount();
        while (true) {
            childCount--;
            if (childCount < 0) {
                break;
            }
            View childAt = toolbar.getChildAt(childCount);
            if (((x2) childAt.getLayoutParams()).f1494b != 2 && childAt != toolbar.f210b) {
                toolbar.removeViewAt(childCount);
                toolbar.F.add(childAt);
            }
        }
        toolbar.requestLayout();
        qVar.C = true;
        qVar.f1100n.p(false);
        KeyEvent.Callback callback = toolbar.f218j;
        if (callback instanceof h.d) {
            ((h.d) callback).b();
        }
        return true;
    }

    @Override // i.b0
    public final void i() {
        if (this.f1485c != null) {
            i.o oVar = this.f1484b;
            boolean z2 = false;
            if (oVar != null) {
                int size = oVar.size();
                for (int i2 = 0; i2 < size; i2++) {
                    if (this.f1484b.getItem(i2) == this.f1485c) {
                        z2 = true;
                        break;
                    }
                }
            }
            if (z2) {
                return;
            }
            c(this.f1485c);
        }
    }

    @Override // i.b0
    public final boolean k(i.h0 h0Var) {
        return false;
    }
}
