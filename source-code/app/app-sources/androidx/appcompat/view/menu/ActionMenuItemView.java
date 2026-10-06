package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.lifecycle.i;
import c.a;
import i.b;
import i.c;
import i.c0;
import i.o;
import i.q;
import j.n;
import j.w0;

/* JADX INFO: loaded from: classes.dex */
public class ActionMenuItemView extends w0 implements c0, View.OnClickListener, n {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public q f82g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public CharSequence f83h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Drawable f84i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public i.n f85j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public b f86k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public c f87l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f88m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f89n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final int f90o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f91p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f92q;

    public ActionMenuItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        Resources resources = context.getResources();
        this.f88m = e();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.f483c, 0, 0);
        this.f90o = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        this.f92q = (int) ((resources.getDisplayMetrics().density * 32.0f) + 0.5f);
        setOnClickListener(this);
        this.f91p = -1;
        setSaveEnabled(false);
    }

    @Override // j.n
    public final boolean a() {
        return d();
    }

    @Override // j.n
    public final boolean b() {
        return d() && this.f82g.getIcon() == null;
    }

    @Override // i.c0
    public final void c(q qVar) {
        this.f82g = qVar;
        setIcon(qVar.getIcon());
        setTitle(qVar.getTitleCondensed());
        setId(qVar.f1087a);
        setVisibility(qVar.isVisible() ? 0 : 8);
        setEnabled(qVar.isEnabled());
        if (qVar.hasSubMenu() && this.f86k == null) {
            this.f86k = new b(this);
        }
    }

    public final boolean d() {
        return !TextUtils.isEmpty(getText());
    }

    public final boolean e() {
        Configuration configuration = getContext().getResources().getConfiguration();
        int i2 = configuration.screenWidthDp;
        return i2 >= 480 || (i2 >= 640 && configuration.screenHeightDp >= 480) || configuration.orientation == 2;
    }

    public final void f() {
        boolean z2 = true;
        boolean z3 = !TextUtils.isEmpty(this.f83h);
        if (this.f84i != null) {
            if (!((this.f82g.f1111y & 4) == 4) || (!this.f88m && !this.f89n)) {
                z2 = false;
            }
        }
        boolean z4 = z3 & z2;
        setText(z4 ? this.f83h : null);
        CharSequence charSequence = this.f82g.f1103q;
        if (TextUtils.isEmpty(charSequence)) {
            charSequence = z4 ? null : this.f82g.f1091e;
        }
        setContentDescription(charSequence);
        CharSequence charSequence2 = this.f82g.f1104r;
        if (TextUtils.isEmpty(charSequence2)) {
            i.g0(this, z4 ? null : this.f82g.f1091e);
        } else {
            i.g0(this, charSequence2);
        }
    }

    @Override // i.c0
    public q getItemData() {
        return this.f82g;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        i.n nVar = this.f85j;
        if (nVar != null) {
            nVar.a(this.f82g);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f88m = e();
        f();
    }

    @Override // j.w0, android.widget.TextView, android.view.View
    public final void onMeasure(int i2, int i3) {
        int i4;
        boolean zD = d();
        if (zD && (i4 = this.f91p) >= 0) {
            super.setPadding(i4, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        super.onMeasure(i2, i3);
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        int measuredWidth = getMeasuredWidth();
        int i5 = this.f90o;
        int iMin = mode == Integer.MIN_VALUE ? Math.min(size, i5) : i5;
        if (mode != 1073741824 && i5 > 0 && measuredWidth < iMin) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(iMin, 1073741824), i3);
        }
        if (zD || this.f84i == null) {
            return;
        }
        super.setPadding((getMeasuredWidth() - this.f84i.getBounds().width()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
    }

    @Override // android.widget.TextView, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(null);
    }

    @Override // android.widget.TextView, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        b bVar;
        if (this.f82g.hasSubMenu() && (bVar = this.f86k) != null && bVar.onTouch(this, motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setCheckable(boolean z2) {
    }

    public void setChecked(boolean z2) {
    }

    public void setExpandedFormat(boolean z2) {
        if (this.f89n != z2) {
            this.f89n = z2;
            q qVar = this.f82g;
            if (qVar != null) {
                o oVar = qVar.f1100n;
                oVar.f1070k = true;
                oVar.p(true);
            }
        }
    }

    public void setIcon(Drawable drawable) {
        this.f84i = drawable;
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int i2 = this.f92q;
            if (intrinsicWidth > i2) {
                intrinsicHeight = (int) (intrinsicHeight * (i2 / intrinsicWidth));
                intrinsicWidth = i2;
            }
            if (intrinsicHeight > i2) {
                intrinsicWidth = (int) (intrinsicWidth * (i2 / intrinsicHeight));
            } else {
                i2 = intrinsicHeight;
            }
            drawable.setBounds(0, 0, intrinsicWidth, i2);
        }
        setCompoundDrawables(drawable, null, null, null);
        f();
    }

    public void setItemInvoker(i.n nVar) {
        this.f85j = nVar;
    }

    @Override // android.widget.TextView, android.view.View
    public final void setPadding(int i2, int i3, int i4, int i5) {
        this.f91p = i2;
        super.setPadding(i2, i3, i4, i5);
    }

    public void setPopupCallback(c cVar) {
        this.f87l = cVar;
    }

    public void setTitle(CharSequence charSequence) {
        this.f83h = charSequence;
        f();
    }
}
