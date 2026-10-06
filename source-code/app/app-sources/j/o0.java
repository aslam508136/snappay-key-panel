package j;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.ListAdapter;
import android.widget.SpinnerAdapter;
import com.snapay.app.R;

/* JADX INFO: loaded from: classes.dex */
public final class o0 extends t1 implements q0 {
    public CharSequence D;
    public ListAdapter E;
    public final Rect F;
    public int G;
    public final /* synthetic */ r0 H;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o0(r0 r0Var, Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.spinnerStyle, 0);
        this.H = r0Var;
        this.F = new Rect();
        this.f1431p = r0Var;
        this.f1440y = true;
        this.f1441z.setFocusable(true);
        this.f1432q = new d.e(this, r0Var, 1);
    }

    @Override // j.q0
    public final void d(int i2, int i3) {
        ViewTreeObserver viewTreeObserver;
        boolean zB = b();
        s();
        d0 d0Var = this.f1441z;
        d0Var.setInputMethodMode(2);
        f();
        i1 i1Var = this.f1419d;
        i1Var.setChoiceMode(1);
        i1Var.setTextDirection(i2);
        i1Var.setTextAlignment(i3);
        r0 r0Var = this.H;
        int selectedItemPosition = r0Var.getSelectedItemPosition();
        i1 i1Var2 = this.f1419d;
        if (b() && i1Var2 != null) {
            i1Var2.setListSelectionHidden(false);
            i1Var2.setSelection(selectedItemPosition);
            if (i1Var2.getChoiceMode() != 0) {
                i1Var2.setItemChecked(selectedItemPosition, true);
            }
        }
        if (zB || (viewTreeObserver = r0Var.getViewTreeObserver()) == null) {
            return;
        }
        i.e eVar = new i.e(this, 3);
        viewTreeObserver.addOnGlobalLayoutListener(eVar);
        d0Var.setOnDismissListener(new n0(this, eVar));
    }

    @Override // j.q0
    public final CharSequence i() {
        return this.D;
    }

    @Override // j.q0
    public final void l(CharSequence charSequence) {
        this.D = charSequence;
    }

    @Override // j.t1, j.q0
    public final void o(ListAdapter listAdapter) {
        super.o(listAdapter);
        this.E = listAdapter;
    }

    @Override // j.q0
    public final void p(int i2) {
        this.G = i2;
    }

    public final void s() {
        int i2;
        Drawable drawableH = h();
        r0 r0Var = this.H;
        if (drawableH != null) {
            drawableH.getPadding(r0Var.f1391i);
            i2 = g3.a(r0Var) ? r0Var.f1391i.right : -r0Var.f1391i.left;
        } else {
            Rect rect = r0Var.f1391i;
            rect.right = 0;
            rect.left = 0;
            i2 = 0;
        }
        int paddingLeft = r0Var.getPaddingLeft();
        int paddingRight = r0Var.getPaddingRight();
        int width = r0Var.getWidth();
        int iMax = r0Var.f1390h;
        if (iMax == -2) {
            int iA = r0Var.a((SpinnerAdapter) this.E, h());
            int i3 = r0Var.getContext().getResources().getDisplayMetrics().widthPixels;
            Rect rect2 = r0Var.f1391i;
            int i4 = (i3 - rect2.left) - rect2.right;
            if (iA > i4) {
                iA = i4;
            }
            iMax = Math.max(iA, (width - paddingLeft) - paddingRight);
        } else if (iMax == -1) {
            iMax = (width - paddingLeft) - paddingRight;
        }
        r(iMax);
        this.f1422g = g3.a(r0Var) ? (((width - paddingRight) - this.f1421f) - this.G) + i2 : paddingLeft + this.G + i2;
    }
}
