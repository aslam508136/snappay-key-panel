package j;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.appcompat.widget.Toolbar;
import com.snapay.app.R;

/* JADX INFO: loaded from: classes.dex */
public final class a3 implements f1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Toolbar f1157a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1158b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public View f1159c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Drawable f1160d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Drawable f1161e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Drawable f1162f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1163g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public CharSequence f1164h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public CharSequence f1165i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public CharSequence f1166j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Window.Callback f1167k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f1168l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public m f1169m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f1170n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Drawable f1171o;

    public a3(Toolbar toolbar) {
        Drawable drawable;
        this.f1170n = 0;
        this.f1157a = toolbar;
        this.f1164h = toolbar.getTitle();
        this.f1165i = toolbar.getSubtitle();
        this.f1163g = this.f1164h != null;
        this.f1162f = toolbar.getNavigationIcon();
        m0.a aVarU = m0.a.u(toolbar.getContext(), null, c.a.f481a, R.attr.actionBarStyle);
        this.f1171o = aVarU.k(15);
        CharSequence charSequenceR = aVarU.r(27);
        if (!TextUtils.isEmpty(charSequenceR)) {
            this.f1163g = true;
            this.f1164h = charSequenceR;
            if ((this.f1158b & 8) != 0) {
                toolbar.setTitle(charSequenceR);
            }
        }
        CharSequence charSequenceR2 = aVarU.r(25);
        if (!TextUtils.isEmpty(charSequenceR2)) {
            this.f1165i = charSequenceR2;
            if ((this.f1158b & 8) != 0) {
                toolbar.setSubtitle(charSequenceR2);
            }
        }
        Drawable drawableK = aVarU.k(20);
        if (drawableK != null) {
            this.f1161e = drawableK;
            b();
        }
        Drawable drawableK2 = aVarU.k(17);
        if (drawableK2 != null) {
            this.f1160d = drawableK2;
            b();
        }
        if (this.f1162f == null && (drawable = this.f1171o) != null) {
            this.f1162f = drawable;
            toolbar.setNavigationIcon((this.f1158b & 4) == 0 ? null : drawable);
        }
        a(aVarU.n(10, 0));
        int iP = aVarU.p(9, 0);
        if (iP != 0) {
            View viewInflate = LayoutInflater.from(toolbar.getContext()).inflate(iP, (ViewGroup) toolbar, false);
            View view = this.f1159c;
            if (view != null && (this.f1158b & 16) != 0) {
                toolbar.removeView(view);
            }
            this.f1159c = viewInflate;
            if (viewInflate != null && (this.f1158b & 16) != 0) {
                toolbar.addView(viewInflate);
            }
            a(this.f1158b | 16);
        }
        int layoutDimension = ((TypedArray) aVarU.f1643b).getLayoutDimension(13, 0);
        if (layoutDimension > 0) {
            ViewGroup.LayoutParams layoutParams = toolbar.getLayoutParams();
            layoutParams.height = layoutDimension;
            toolbar.setLayoutParams(layoutParams);
        }
        int i2 = aVarU.i(7, -1);
        int i3 = aVarU.i(3, -1);
        if (i2 >= 0 || i3 >= 0) {
            int iMax = Math.max(i2, 0);
            int iMax2 = Math.max(i3, 0);
            if (toolbar.f229u == null) {
                toolbar.f229u = new b2();
            }
            toolbar.f229u.a(iMax, iMax2);
        }
        int iP2 = aVarU.p(28, 0);
        if (iP2 != 0) {
            Context context = toolbar.getContext();
            toolbar.f221m = iP2;
            w0 w0Var = toolbar.f211c;
            if (w0Var != null) {
                w0Var.setTextAppearance(context, iP2);
            }
        }
        int iP3 = aVarU.p(26, 0);
        if (iP3 != 0) {
            Context context2 = toolbar.getContext();
            toolbar.f222n = iP3;
            w0 w0Var2 = toolbar.f212d;
            if (w0Var2 != null) {
                w0Var2.setTextAppearance(context2, iP3);
            }
        }
        int iP4 = aVarU.p(22, 0);
        if (iP4 != 0) {
            toolbar.setPopupTheme(iP4);
        }
        aVarU.w();
        if (R.string.abc_action_bar_up_description != this.f1170n) {
            this.f1170n = R.string.abc_action_bar_up_description;
            if (TextUtils.isEmpty(toolbar.getNavigationContentDescription())) {
                int i4 = this.f1170n;
                String string = i4 != 0 ? toolbar.getContext().getString(i4) : null;
                this.f1166j = string;
                if ((this.f1158b & 4) != 0) {
                    if (TextUtils.isEmpty(string)) {
                        toolbar.setNavigationContentDescription(this.f1170n);
                    } else {
                        toolbar.setNavigationContentDescription(this.f1166j);
                    }
                }
            }
        }
        this.f1166j = toolbar.getNavigationContentDescription();
        toolbar.setNavigationOnClickListener(new c(this));
    }

    public final void a(int i2) {
        View view;
        Drawable drawable;
        int i3 = this.f1158b ^ i2;
        this.f1158b = i2;
        if (i3 != 0) {
            int i4 = i3 & 4;
            CharSequence charSequence = null;
            Toolbar toolbar = this.f1157a;
            if (i4 != 0) {
                if ((i2 & 4) != 0 && (i2 & 4) != 0) {
                    if (TextUtils.isEmpty(this.f1166j)) {
                        toolbar.setNavigationContentDescription(this.f1170n);
                    } else {
                        toolbar.setNavigationContentDescription(this.f1166j);
                    }
                }
                if ((this.f1158b & 4) != 0) {
                    drawable = this.f1162f;
                    if (drawable == null) {
                        drawable = this.f1171o;
                    }
                } else {
                    drawable = null;
                }
                toolbar.setNavigationIcon(drawable);
            }
            if ((i3 & 3) != 0) {
                b();
            }
            if ((i3 & 8) != 0) {
                if ((i2 & 8) != 0) {
                    toolbar.setTitle(this.f1164h);
                    charSequence = this.f1165i;
                } else {
                    toolbar.setTitle((CharSequence) null);
                }
                toolbar.setSubtitle(charSequence);
            }
            if ((i3 & 16) == 0 || (view = this.f1159c) == null) {
                return;
            }
            if ((i2 & 16) != 0) {
                toolbar.addView(view);
            } else {
                toolbar.removeView(view);
            }
        }
    }

    public final void b() {
        Drawable drawable;
        int i2 = this.f1158b;
        if ((i2 & 2) == 0) {
            drawable = null;
        } else if ((i2 & 1) == 0 || (drawable = this.f1161e) == null) {
            drawable = this.f1160d;
        }
        this.f1157a.setLogo(drawable);
    }
}
