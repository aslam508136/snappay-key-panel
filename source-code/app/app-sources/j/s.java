package j;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Base64;
import android.view.View;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1400a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1401b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f1402c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f1403d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f1404e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f1405f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Object f1406g;

    public s(View view) {
        this.f1400a = 0;
        this.f1401b = -1;
        this.f1402c = view;
        this.f1403d = y.a();
    }

    public final void a() {
        View view = (View) this.f1402c;
        Drawable background = view.getBackground();
        if (background != null) {
            int i2 = Build.VERSION.SDK_INT;
            boolean z2 = true;
            if (i2 <= 21 ? i2 == 21 : ((t2) this.f1404e) != null) {
                if (((t2) this.f1406g) == null) {
                    this.f1406g = new t2();
                }
                t2 t2Var = (t2) this.f1406g;
                t2Var.f1442a = null;
                t2Var.f1445d = false;
                t2Var.f1443b = null;
                t2Var.f1444c = false;
                WeakHashMap weakHashMap = x.u.f2012a;
                ColorStateList backgroundTintList = view.getBackgroundTintList();
                if (backgroundTintList != null) {
                    t2Var.f1445d = true;
                    t2Var.f1442a = backgroundTintList;
                }
                PorterDuff.Mode backgroundTintMode = view.getBackgroundTintMode();
                if (backgroundTintMode != null) {
                    t2Var.f1444c = true;
                    t2Var.f1443b = backgroundTintMode;
                }
                if (t2Var.f1445d || t2Var.f1444c) {
                    y.d(background, t2Var, view.getDrawableState());
                } else {
                    z2 = false;
                }
                if (z2) {
                    return;
                }
            }
            t2 t2Var2 = (t2) this.f1405f;
            if (t2Var2 != null) {
                y.d(background, t2Var2, view.getDrawableState());
                return;
            }
            t2 t2Var3 = (t2) this.f1404e;
            if (t2Var3 != null) {
                y.d(background, t2Var3, view.getDrawableState());
            }
        }
    }

    public final ColorStateList b() {
        Object obj = this.f1405f;
        if (((t2) obj) != null) {
            return ((t2) obj).f1442a;
        }
        return null;
    }

    public final PorterDuff.Mode c() {
        Object obj = this.f1405f;
        if (((t2) obj) != null) {
            return ((t2) obj).f1443b;
        }
        return null;
    }

    public final void d(AttributeSet attributeSet, int i2) {
        ColorStateList colorStateListI;
        Object obj = this.f1402c;
        View view = (View) obj;
        Context context = view.getContext();
        int[] iArr = c.a.f505y;
        m0.a aVarU = m0.a.u(context, attributeSet, iArr, i2);
        x.u.d(view, view.getContext(), iArr, attributeSet, (TypedArray) aVarU.f1643b, i2);
        try {
            if (aVarU.s(0)) {
                this.f1401b = aVarU.p(0, -1);
                y yVar = (y) this.f1403d;
                Context context2 = ((View) obj).getContext();
                int i3 = this.f1401b;
                synchronized (yVar) {
                    colorStateListI = yVar.f1497a.i(context2, i3);
                }
                if (colorStateListI != null) {
                    g(colorStateListI);
                }
            }
            if (aVarU.s(1)) {
                View view2 = (View) obj;
                ColorStateList colorStateListH = aVarU.h(1);
                int i4 = Build.VERSION.SDK_INT;
                view2.setBackgroundTintList(colorStateListH);
                if (i4 == 21) {
                    Drawable background = view2.getBackground();
                    boolean z2 = (view2.getBackgroundTintList() == null && view2.getBackgroundTintMode() == null) ? false : true;
                    if (background != null && z2) {
                        if (background.isStateful()) {
                            background.setState(view2.getDrawableState());
                        }
                        view2.setBackground(background);
                    }
                }
            }
            if (aVarU.s(2)) {
                View view3 = (View) obj;
                PorterDuff.Mode modeC = g1.c(aVarU.n(2, -1), null);
                int i5 = Build.VERSION.SDK_INT;
                view3.setBackgroundTintMode(modeC);
                if (i5 == 21) {
                    Drawable background2 = view3.getBackground();
                    boolean z3 = (view3.getBackgroundTintList() == null && view3.getBackgroundTintMode() == null) ? false : true;
                    if (background2 != null && z3) {
                        if (background2.isStateful()) {
                            background2.setState(view3.getDrawableState());
                        }
                        view3.setBackground(background2);
                    }
                }
            }
            aVarU.w();
        } catch (Throwable th) {
            aVarU.w();
            throw th;
        }
    }

    public final void e() {
        this.f1401b = -1;
        g(null);
        a();
    }

    public final void f(int i2) {
        ColorStateList colorStateListI;
        this.f1401b = i2;
        y yVar = (y) this.f1403d;
        if (yVar != null) {
            Context context = ((View) this.f1402c).getContext();
            synchronized (yVar) {
                colorStateListI = yVar.f1497a.i(context, i2);
            }
        } else {
            colorStateListI = null;
        }
        g(colorStateListI);
        a();
    }

    public final void g(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (((t2) this.f1404e) == null) {
                this.f1404e = new t2();
            }
            Object obj = this.f1404e;
            ((t2) obj).f1442a = colorStateList;
            ((t2) obj).f1445d = true;
        } else {
            this.f1404e = null;
        }
        a();
    }

    public final void h(ColorStateList colorStateList) {
        if (((t2) this.f1405f) == null) {
            this.f1405f = new t2();
        }
        t2 t2Var = (t2) this.f1405f;
        t2Var.f1442a = colorStateList;
        t2Var.f1445d = true;
        a();
    }

    public final void i(PorterDuff.Mode mode) {
        if (((t2) this.f1405f) == null) {
            this.f1405f = new t2();
        }
        t2 t2Var = (t2) this.f1405f;
        t2Var.f1443b = mode;
        t2Var.f1444c = true;
        a();
    }

    public final String toString() {
        switch (this.f1400a) {
            case 1:
                StringBuilder sb = new StringBuilder();
                sb.append("FontRequest {mProviderAuthority: " + ((String) this.f1402c) + ", mProviderPackage: " + ((String) this.f1403d) + ", mQuery: " + ((String) this.f1404e) + ", mCertificates:");
                for (int i2 = 0; i2 < ((List) this.f1405f).size(); i2++) {
                    sb.append(" [");
                    List list = (List) ((List) this.f1405f).get(i2);
                    for (int i3 = 0; i3 < list.size(); i3++) {
                        sb.append(" \"");
                        sb.append(Base64.encodeToString((byte[]) list.get(i3), 0));
                        sb.append("\"");
                    }
                    sb.append(" ]");
                }
                sb.append("}");
                sb.append("mCertificatesArray: " + this.f1401b);
                return sb.toString();
            default:
                return super.toString();
        }
    }

    public s(String str, String str2, String str3, List list) {
        this.f1400a = 1;
        this.f1402c = str;
        this.f1403d = str2;
        this.f1404e = str3;
        list.getClass();
        this.f1405f = list;
        this.f1401b = 0;
        this.f1406g = str + "-" + str2 + "-" + str3;
    }
}
