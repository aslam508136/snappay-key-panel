package j;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public final class x2 extends d.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1494b;

    public x2() {
        this.f1494b = 0;
        this.f576a = 8388627;
    }

    public x2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1494b = 0;
    }

    public x2(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
        this.f1494b = 0;
    }

    public x2(ViewGroup.MarginLayoutParams marginLayoutParams) {
        super(marginLayoutParams);
        this.f1494b = 0;
        ((ViewGroup.MarginLayoutParams) this).leftMargin = marginLayoutParams.leftMargin;
        ((ViewGroup.MarginLayoutParams) this).topMargin = marginLayoutParams.topMargin;
        ((ViewGroup.MarginLayoutParams) this).rightMargin = marginLayoutParams.rightMargin;
        ((ViewGroup.MarginLayoutParams) this).bottomMargin = marginLayoutParams.bottomMargin;
    }

    public x2(d.a aVar) {
        super(aVar);
        this.f1494b = 0;
    }

    public x2(x2 x2Var) {
        super((d.a) x2Var);
        this.f1494b = 0;
        this.f1494b = x2Var.f1494b;
    }
}
