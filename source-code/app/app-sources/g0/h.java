package g0;

import android.content.res.ColorStateList;
import android.graphics.Paint;

/* JADX INFO: loaded from: classes.dex */
public final class h extends k {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public q.a f826e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f827f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public q.a f828g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f829h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f830i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f831j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f832k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f833l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Paint.Cap f834m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Paint.Join f835n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f836o;

    public h() {
        this.f827f = 0.0f;
        this.f829h = 1.0f;
        this.f830i = 1.0f;
        this.f831j = 0.0f;
        this.f832k = 1.0f;
        this.f833l = 0.0f;
        this.f834m = Paint.Cap.BUTT;
        this.f835n = Paint.Join.MITER;
        this.f836o = 4.0f;
    }

    @Override // g0.j
    public final boolean a() {
        return this.f828g.b() || this.f826e.b();
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0036  */
    /* JADX WARN: Code duplicated, block: B:7:0x001c  */
    @Override // g0.j
    public final boolean b(int[] iArr) {
        boolean z2;
        q.a aVar = this.f828g;
        boolean z3 = true;
        if (aVar.b()) {
            ColorStateList colorStateList = aVar.f1838b;
            int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
            if (colorForState != aVar.f1839c) {
                aVar.f1839c = colorForState;
                z2 = true;
            } else {
                z2 = false;
            }
        } else {
            z2 = false;
        }
        q.a aVar2 = this.f826e;
        if (aVar2.b()) {
            ColorStateList colorStateList2 = aVar2.f1838b;
            int colorForState2 = colorStateList2.getColorForState(iArr, colorStateList2.getDefaultColor());
            if (colorForState2 != aVar2.f1839c) {
                aVar2.f1839c = colorForState2;
            } else {
                z3 = false;
            }
        } else {
            z3 = false;
        }
        return z3 | z2;
    }

    public float getFillAlpha() {
        return this.f830i;
    }

    public int getFillColor() {
        return this.f828g.f1839c;
    }

    public float getStrokeAlpha() {
        return this.f829h;
    }

    public int getStrokeColor() {
        return this.f826e.f1839c;
    }

    public float getStrokeWidth() {
        return this.f827f;
    }

    public float getTrimPathEnd() {
        return this.f832k;
    }

    public float getTrimPathOffset() {
        return this.f833l;
    }

    public float getTrimPathStart() {
        return this.f831j;
    }

    public void setFillAlpha(float f2) {
        this.f830i = f2;
    }

    public void setFillColor(int i2) {
        this.f828g.f1839c = i2;
    }

    public void setStrokeAlpha(float f2) {
        this.f829h = f2;
    }

    public void setStrokeColor(int i2) {
        this.f826e.f1839c = i2;
    }

    public void setStrokeWidth(float f2) {
        this.f827f = f2;
    }

    public void setTrimPathEnd(float f2) {
        this.f832k = f2;
    }

    public void setTrimPathOffset(float f2) {
        this.f833l = f2;
    }

    public void setTrimPathStart(float f2) {
        this.f831j = f2;
    }

    public h(h hVar) {
        super(hVar);
        this.f827f = 0.0f;
        this.f829h = 1.0f;
        this.f830i = 1.0f;
        this.f831j = 0.0f;
        this.f832k = 1.0f;
        this.f833l = 0.0f;
        this.f834m = Paint.Cap.BUTT;
        this.f835n = Paint.Join.MITER;
        this.f836o = 4.0f;
        this.f826e = hVar.f826e;
        this.f827f = hVar.f827f;
        this.f829h = hVar.f829h;
        this.f828g = hVar.f828g;
        this.f851c = hVar.f851c;
        this.f830i = hVar.f830i;
        this.f831j = hVar.f831j;
        this.f832k = hVar.f832k;
        this.f833l = hVar.f833l;
        this.f834m = hVar.f834m;
        this.f835n = hVar.f835n;
        this.f836o = hVar.f836o;
    }
}
