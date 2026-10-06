package g0;

import android.graphics.Matrix;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class i extends j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Matrix f837a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f838b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f839c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f840d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f841e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f842f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f843g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f844h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f845i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Matrix f846j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final int f847k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f848l;

    public i() {
        this.f837a = new Matrix();
        this.f838b = new ArrayList();
        this.f839c = 0.0f;
        this.f840d = 0.0f;
        this.f841e = 0.0f;
        this.f842f = 1.0f;
        this.f843g = 1.0f;
        this.f844h = 0.0f;
        this.f845i = 0.0f;
        this.f846j = new Matrix();
        this.f848l = null;
    }

    @Override // g0.j
    public final boolean a() {
        int i2 = 0;
        while (true) {
            ArrayList arrayList = this.f838b;
            if (i2 >= arrayList.size()) {
                return false;
            }
            if (((j) arrayList.get(i2)).a()) {
                return true;
            }
            i2++;
        }
    }

    @Override // g0.j
    public final boolean b(int[] iArr) {
        int i2 = 0;
        boolean zB = false;
        while (true) {
            ArrayList arrayList = this.f838b;
            if (i2 >= arrayList.size()) {
                return zB;
            }
            zB |= ((j) arrayList.get(i2)).b(iArr);
            i2++;
        }
    }

    public final void c() {
        Matrix matrix = this.f846j;
        matrix.reset();
        matrix.postTranslate(-this.f840d, -this.f841e);
        matrix.postScale(this.f842f, this.f843g);
        matrix.postRotate(this.f839c, 0.0f, 0.0f);
        matrix.postTranslate(this.f844h + this.f840d, this.f845i + this.f841e);
    }

    public String getGroupName() {
        return this.f848l;
    }

    public Matrix getLocalMatrix() {
        return this.f846j;
    }

    public float getPivotX() {
        return this.f840d;
    }

    public float getPivotY() {
        return this.f841e;
    }

    public float getRotation() {
        return this.f839c;
    }

    public float getScaleX() {
        return this.f842f;
    }

    public float getScaleY() {
        return this.f843g;
    }

    public float getTranslateX() {
        return this.f844h;
    }

    public float getTranslateY() {
        return this.f845i;
    }

    public void setPivotX(float f2) {
        if (f2 != this.f840d) {
            this.f840d = f2;
            c();
        }
    }

    public void setPivotY(float f2) {
        if (f2 != this.f841e) {
            this.f841e = f2;
            c();
        }
    }

    public void setRotation(float f2) {
        if (f2 != this.f839c) {
            this.f839c = f2;
            c();
        }
    }

    public void setScaleX(float f2) {
        if (f2 != this.f842f) {
            this.f842f = f2;
            c();
        }
    }

    public void setScaleY(float f2) {
        if (f2 != this.f843g) {
            this.f843g = f2;
            c();
        }
    }

    public void setTranslateX(float f2) {
        if (f2 != this.f844h) {
            this.f844h = f2;
            c();
        }
    }

    public void setTranslateY(float f2) {
        if (f2 != this.f845i) {
            this.f845i = f2;
            c();
        }
    }

    public i(i iVar, m.b bVar) {
        k gVar;
        this.f837a = new Matrix();
        this.f838b = new ArrayList();
        this.f839c = 0.0f;
        this.f840d = 0.0f;
        this.f841e = 0.0f;
        this.f842f = 1.0f;
        this.f843g = 1.0f;
        this.f844h = 0.0f;
        this.f845i = 0.0f;
        Matrix matrix = new Matrix();
        this.f846j = matrix;
        this.f848l = null;
        this.f839c = iVar.f839c;
        this.f840d = iVar.f840d;
        this.f841e = iVar.f841e;
        this.f842f = iVar.f842f;
        this.f843g = iVar.f843g;
        this.f844h = iVar.f844h;
        this.f845i = iVar.f845i;
        String str = iVar.f848l;
        this.f848l = str;
        this.f847k = iVar.f847k;
        if (str != null) {
            bVar.put(str, this);
        }
        matrix.set(iVar.f846j);
        ArrayList arrayList = iVar.f838b;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            Object obj = arrayList.get(i2);
            if (obj instanceof i) {
                this.f838b.add(new i((i) obj, bVar));
            } else {
                if (obj instanceof h) {
                    gVar = new h((h) obj);
                } else {
                    if (!(obj instanceof g)) {
                        throw new IllegalStateException("Unknown object in the tree!");
                    }
                    gVar = new g((g) obj);
                }
                this.f838b.add(gVar);
                Object obj2 = gVar.f850b;
                if (obj2 != null) {
                    bVar.put(obj2, gVar);
                }
            }
        }
    }
}
