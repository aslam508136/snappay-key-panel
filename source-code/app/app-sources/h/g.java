package h;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import i.e0;
import i.o;
import i.w;
import j.g1;
import j.t2;
import j.y;
import java.util.ArrayList;
import x.u;

/* JADX INFO: loaded from: classes.dex */
public final class g implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f906a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f907b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object f908c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object f909d;

    public g(Context context, ActionMode.Callback callback) {
        this.f907b = context;
        this.f906a = callback;
        this.f908c = new ArrayList();
        this.f909d = new m.j();
    }

    @Override // h.b
    public final boolean a(c cVar, MenuItem menuItem) {
        return ((ActionMode.Callback) this.f906a).onActionItemClicked(f(cVar), new w((Context) this.f907b, (t.b) menuItem));
    }

    @Override // h.b
    public final boolean b(c cVar, o oVar) {
        return ((ActionMode.Callback) this.f906a).onPrepareActionMode(f(cVar), g(oVar));
    }

    @Override // h.b
    public final void c(c cVar) {
        ((ActionMode.Callback) this.f906a).onDestroyActionMode(f(cVar));
    }

    @Override // h.b
    public final boolean d(c cVar, o oVar) {
        return ((ActionMode.Callback) this.f906a).onCreateActionMode(f(cVar), g(oVar));
    }

    public final void e() {
        ImageView imageView = (ImageView) this.f906a;
        Drawable drawable = imageView.getDrawable();
        if (drawable != null) {
            g1.b(drawable);
        }
        if (drawable != null) {
            int i2 = Build.VERSION.SDK_INT;
            boolean z2 = true;
            Object obj = this.f907b;
            if (i2 <= 21 ? i2 == 21 : ((t2) obj) != null) {
                if (((t2) this.f909d) == null) {
                    this.f909d = new t2();
                }
                t2 t2Var = (t2) this.f909d;
                t2Var.f1442a = null;
                t2Var.f1445d = false;
                t2Var.f1443b = null;
                t2Var.f1444c = false;
                ColorStateList imageTintList = imageView.getImageTintList();
                if (imageTintList != null) {
                    t2Var.f1445d = true;
                    t2Var.f1442a = imageTintList;
                }
                PorterDuff.Mode imageTintMode = imageView.getImageTintMode();
                if (imageTintMode != null) {
                    t2Var.f1444c = true;
                    t2Var.f1443b = imageTintMode;
                }
                if (t2Var.f1445d || t2Var.f1444c) {
                    y.d(drawable, t2Var, imageView.getDrawableState());
                } else {
                    z2 = false;
                }
                if (z2) {
                    return;
                }
            }
            t2 t2Var2 = (t2) this.f908c;
            if (t2Var2 != null) {
                y.d(drawable, t2Var2, imageView.getDrawableState());
                return;
            }
            t2 t2Var3 = (t2) obj;
            if (t2Var3 != null) {
                y.d(drawable, t2Var3, imageView.getDrawableState());
            }
        }
    }

    public final h f(c cVar) {
        int size = ((ArrayList) this.f908c).size();
        for (int i2 = 0; i2 < size; i2++) {
            h hVar = (h) ((ArrayList) this.f908c).get(i2);
            if (hVar != null && hVar.f911b == cVar) {
                return hVar;
            }
        }
        h hVar2 = new h((Context) this.f907b, cVar);
        ((ArrayList) this.f908c).add(hVar2);
        return hVar2;
    }

    public final Menu g(o oVar) {
        Menu menu = (Menu) ((m.j) this.f909d).getOrDefault(oVar, null);
        if (menu != null) {
            return menu;
        }
        e0 e0Var = new e0((Context) this.f907b, oVar);
        ((m.j) this.f909d).put(oVar, e0Var);
        return e0Var;
    }

    public final void h(AttributeSet attributeSet, int i2) {
        Drawable drawable;
        Drawable drawable2;
        int iP;
        Object obj = this.f906a;
        ImageView imageView = (ImageView) obj;
        Context context = imageView.getContext();
        int[] iArr = c.a.f486f;
        m0.a aVarU = m0.a.u(context, attributeSet, iArr, i2);
        u.d(imageView, imageView.getContext(), iArr, attributeSet, (TypedArray) aVarU.f1643b, i2);
        try {
            Drawable drawable3 = ((ImageView) obj).getDrawable();
            if (drawable3 == null && (iP = aVarU.p(1, -1)) != -1 && (drawable3 = e.b.c(((ImageView) obj).getContext(), iP)) != null) {
                ((ImageView) obj).setImageDrawable(drawable3);
            }
            if (drawable3 != null) {
                g1.b(drawable3);
            }
            if (aVarU.s(2)) {
                ImageView imageView2 = (ImageView) obj;
                ColorStateList colorStateListH = aVarU.h(2);
                int i3 = Build.VERSION.SDK_INT;
                imageView2.setImageTintList(colorStateListH);
                if (i3 == 21 && (drawable2 = imageView2.getDrawable()) != null && imageView2.getImageTintList() != null) {
                    if (drawable2.isStateful()) {
                        drawable2.setState(imageView2.getDrawableState());
                    }
                    imageView2.setImageDrawable(drawable2);
                }
            }
            if (aVarU.s(3)) {
                ImageView imageView3 = (ImageView) obj;
                PorterDuff.Mode modeC = g1.c(aVarU.n(3, -1), null);
                int i4 = Build.VERSION.SDK_INT;
                imageView3.setImageTintMode(modeC);
                if (i4 == 21 && (drawable = imageView3.getDrawable()) != null && imageView3.getImageTintList() != null) {
                    if (drawable.isStateful()) {
                        drawable.setState(imageView3.getDrawableState());
                    }
                    imageView3.setImageDrawable(drawable);
                }
            }
        } finally {
            aVarU.w();
        }
    }

    public final void i(int i2) {
        Drawable drawableC;
        ImageView imageView = (ImageView) this.f906a;
        if (i2 != 0) {
            drawableC = e.b.c(imageView.getContext(), i2);
            if (drawableC != null) {
                g1.b(drawableC);
            }
        } else {
            drawableC = null;
        }
        imageView.setImageDrawable(drawableC);
        e();
    }

    public final void j(ColorStateList colorStateList) {
        if (((t2) this.f908c) == null) {
            this.f908c = new t2();
        }
        t2 t2Var = (t2) this.f908c;
        t2Var.f1442a = colorStateList;
        t2Var.f1445d = true;
        e();
    }

    public final void k(PorterDuff.Mode mode) {
        if (((t2) this.f908c) == null) {
            this.f908c = new t2();
        }
        t2 t2Var = (t2) this.f908c;
        t2Var.f1443b = mode;
        t2Var.f1444c = true;
        e();
    }

    public g(ImageView imageView) {
        this.f906a = imageView;
    }
}
