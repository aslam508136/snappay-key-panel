package f;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.SparseArray;

/* JADX INFO: loaded from: classes.dex */
public abstract class g extends Drawable.ConstantState {
    public boolean A;
    public ColorFilter B;
    public boolean C;
    public ColorStateList D;
    public PorterDuff.Mode E;
    public boolean F;
    public boolean G;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final h f771a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Resources f772b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f773c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f774d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f775e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public SparseArray f776f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Drawable[] f777g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f778h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f779i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f780j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Rect f781k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f782l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f783m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f784n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f785o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f786p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f787q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f788r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f789s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f790t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f791u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f792v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f793w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f794x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f795y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f796z;

    public g(g gVar, h hVar, Resources resources) {
        this.f779i = false;
        this.f782l = false;
        this.f793w = true;
        this.f795y = 0;
        this.f796z = 0;
        this.f771a = hVar;
        this.f772b = resources != null ? resources : gVar != null ? gVar.f772b : null;
        int i2 = gVar != null ? gVar.f773c : 0;
        int i3 = h.f797n;
        i2 = resources != null ? resources.getDisplayMetrics().densityDpi : i2;
        i2 = i2 == 0 ? 160 : i2;
        this.f773c = i2;
        if (gVar == null) {
            this.f777g = new Drawable[10];
            this.f778h = 0;
            return;
        }
        this.f774d = gVar.f774d;
        this.f775e = gVar.f775e;
        this.f791u = true;
        this.f792v = true;
        this.f779i = gVar.f779i;
        this.f782l = gVar.f782l;
        this.f793w = gVar.f793w;
        this.f794x = gVar.f794x;
        this.f795y = gVar.f795y;
        this.f796z = gVar.f796z;
        this.A = gVar.A;
        this.B = gVar.B;
        this.C = gVar.C;
        this.D = gVar.D;
        this.E = gVar.E;
        this.F = gVar.F;
        this.G = gVar.G;
        if (gVar.f773c == i2) {
            if (gVar.f780j) {
                this.f781k = gVar.f781k != null ? new Rect(gVar.f781k) : null;
                this.f780j = true;
            }
            if (gVar.f783m) {
                this.f784n = gVar.f784n;
                this.f785o = gVar.f785o;
                this.f786p = gVar.f786p;
                this.f787q = gVar.f787q;
                this.f783m = true;
            }
        }
        if (gVar.f788r) {
            this.f789s = gVar.f789s;
            this.f788r = true;
        }
        if (gVar.f790t) {
            this.f790t = true;
        }
        Drawable[] drawableArr = gVar.f777g;
        this.f777g = new Drawable[drawableArr.length];
        this.f778h = gVar.f778h;
        SparseArray sparseArray = gVar.f776f;
        this.f776f = sparseArray != null ? sparseArray.clone() : new SparseArray(this.f778h);
        int i4 = this.f778h;
        for (int i5 = 0; i5 < i4; i5++) {
            Drawable drawable = drawableArr[i5];
            if (drawable != null) {
                Drawable.ConstantState constantState = drawable.getConstantState();
                if (constantState != null) {
                    this.f776f.put(i5, constantState);
                } else {
                    this.f777g[i5] = drawableArr[i5];
                }
            }
        }
    }

    public final int a(Drawable drawable) {
        int i2 = this.f778h;
        if (i2 >= this.f777g.length) {
            int i3 = i2 + 10;
            i iVar = (i) this;
            Drawable[] drawableArr = new Drawable[i3];
            Drawable[] drawableArr2 = iVar.f777g;
            if (drawableArr2 != null) {
                System.arraycopy(drawableArr2, 0, drawableArr, 0, i2);
            }
            iVar.f777g = drawableArr;
            int[][] iArr = new int[i3][];
            System.arraycopy(iVar.H, 0, iArr, 0, i2);
            iVar.H = iArr;
        }
        drawable.mutate();
        drawable.setVisible(false, true);
        drawable.setCallback(this.f771a);
        this.f777g[i2] = drawable;
        this.f778h++;
        this.f775e = drawable.getChangingConfigurations() | this.f775e;
        this.f788r = false;
        this.f790t = false;
        this.f781k = null;
        this.f780j = false;
        this.f783m = false;
        this.f791u = false;
        return i2;
    }

    public final void b() {
        this.f783m = true;
        c();
        int i2 = this.f778h;
        Drawable[] drawableArr = this.f777g;
        this.f785o = -1;
        this.f784n = -1;
        this.f787q = 0;
        this.f786p = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            Drawable drawable = drawableArr[i3];
            int intrinsicWidth = drawable.getIntrinsicWidth();
            if (intrinsicWidth > this.f784n) {
                this.f784n = intrinsicWidth;
            }
            int intrinsicHeight = drawable.getIntrinsicHeight();
            if (intrinsicHeight > this.f785o) {
                this.f785o = intrinsicHeight;
            }
            int minimumWidth = drawable.getMinimumWidth();
            if (minimumWidth > this.f786p) {
                this.f786p = minimumWidth;
            }
            int minimumHeight = drawable.getMinimumHeight();
            if (minimumHeight > this.f787q) {
                this.f787q = minimumHeight;
            }
        }
    }

    public final void c() {
        SparseArray sparseArray = this.f776f;
        if (sparseArray != null) {
            int size = sparseArray.size();
            for (int i2 = 0; i2 < size; i2++) {
                int iKeyAt = this.f776f.keyAt(i2);
                Drawable.ConstantState constantState = (Drawable.ConstantState) this.f776f.valueAt(i2);
                Drawable[] drawableArr = this.f777g;
                Drawable drawableNewDrawable = constantState.newDrawable(this.f772b);
                if (Build.VERSION.SDK_INT >= 23) {
                    androidx.lifecycle.i.a0(drawableNewDrawable, this.f794x);
                }
                Drawable drawableMutate = drawableNewDrawable.mutate();
                drawableMutate.setCallback(this.f771a);
                drawableArr[iKeyAt] = drawableMutate;
            }
            this.f776f = null;
        }
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final boolean canApplyTheme() {
        int i2 = this.f778h;
        Drawable[] drawableArr = this.f777g;
        for (int i3 = 0; i3 < i2; i3++) {
            Drawable drawable = drawableArr[i3];
            if (drawable == null) {
                Drawable.ConstantState constantState = (Drawable.ConstantState) this.f776f.get(i3);
                if (constantState != null && constantState.canApplyTheme()) {
                    return true;
                }
            } else if (drawable.canApplyTheme()) {
                return true;
            }
        }
        return false;
    }

    public final Drawable d(int i2) {
        int iIndexOfKey;
        Drawable drawable = this.f777g[i2];
        if (drawable != null) {
            return drawable;
        }
        SparseArray sparseArray = this.f776f;
        if (sparseArray == null || (iIndexOfKey = sparseArray.indexOfKey(i2)) < 0) {
            return null;
        }
        Drawable drawableNewDrawable = ((Drawable.ConstantState) this.f776f.valueAt(iIndexOfKey)).newDrawable(this.f772b);
        if (Build.VERSION.SDK_INT >= 23) {
            androidx.lifecycle.i.a0(drawableNewDrawable, this.f794x);
        }
        Drawable drawableMutate = drawableNewDrawable.mutate();
        drawableMutate.setCallback(this.f771a);
        this.f777g[i2] = drawableMutate;
        this.f776f.removeAt(iIndexOfKey);
        if (this.f776f.size() == 0) {
            this.f776f = null;
        }
        return drawableMutate;
    }

    public abstract void e();

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final int getChangingConfigurations() {
        return this.f774d | this.f775e;
    }
}
