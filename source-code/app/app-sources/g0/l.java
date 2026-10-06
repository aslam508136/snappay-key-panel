package g0;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final Matrix f853p = new Matrix();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Path f854a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Path f855b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Matrix f856c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Paint f857d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Paint f858e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public PathMeasure f859f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final i f860g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f861h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f862i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f863j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f864k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f865l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f866m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Boolean f867n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final m.b f868o;

    public l() {
        this.f856c = new Matrix();
        this.f861h = 0.0f;
        this.f862i = 0.0f;
        this.f863j = 0.0f;
        this.f864k = 0.0f;
        this.f865l = 255;
        this.f866m = null;
        this.f867n = null;
        this.f868o = new m.b();
        this.f860g = new i();
        this.f854a = new Path();
        this.f855b = new Path();
    }

    public final void a(i iVar, Matrix matrix, Canvas canvas, int i2, int i3) {
        int i4;
        float f2;
        boolean z2;
        iVar.f837a.set(matrix);
        Matrix matrix2 = iVar.f837a;
        matrix2.preConcat(iVar.f846j);
        canvas.save();
        char c2 = 0;
        int i5 = 0;
        while (true) {
            ArrayList arrayList = iVar.f838b;
            if (i5 >= arrayList.size()) {
                canvas.restore();
                return;
            }
            j jVar = (j) arrayList.get(i5);
            if (jVar instanceof i) {
                a((i) jVar, matrix2, canvas, i2, i3);
            } else {
                if (jVar instanceof k) {
                    k kVar = (k) jVar;
                    float f3 = i2 / this.f863j;
                    float f4 = i3 / this.f864k;
                    float fMin = Math.min(f3, f4);
                    Matrix matrix3 = this.f856c;
                    matrix3.set(matrix2);
                    matrix3.postScale(f3, f4);
                    float[] fArr = {0.0f, 1.0f, 1.0f, 0.0f};
                    matrix2.mapVectors(fArr);
                    float fHypot = (float) Math.hypot(fArr[c2], fArr[1]);
                    i4 = i5;
                    float fHypot2 = (float) Math.hypot(fArr[2], fArr[3]);
                    float f5 = (fArr[0] * fArr[3]) - (fArr[1] * fArr[2]);
                    float fMax = Math.max(fHypot, fHypot2);
                    float fAbs = fMax > 0.0f ? Math.abs(f5) / fMax : 0.0f;
                    if (fAbs != 0.0f) {
                        kVar.getClass();
                        Path path = this.f854a;
                        path.reset();
                        r.c[] cVarArr = kVar.f849a;
                        if (cVarArr != null) {
                            r.c.b(cVarArr, path);
                        }
                        Path path2 = this.f855b;
                        path2.reset();
                        if (kVar instanceof g) {
                            path2.setFillType(kVar.f851c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                            path2.addPath(path, matrix3);
                            canvas.clipPath(path2);
                        } else {
                            h hVar = (h) kVar;
                            float f6 = hVar.f831j;
                            if (f6 != 0.0f || hVar.f832k != 1.0f) {
                                float f7 = hVar.f833l;
                                float f8 = (f6 + f7) % 1.0f;
                                float f9 = (hVar.f832k + f7) % 1.0f;
                                if (this.f859f == null) {
                                    this.f859f = new PathMeasure();
                                }
                                this.f859f.setPath(path, false);
                                float length = this.f859f.getLength();
                                float f10 = f8 * length;
                                float f11 = f9 * length;
                                path.reset();
                                if (f10 > f11) {
                                    this.f859f.getSegment(f10, length, path, true);
                                    f2 = 0.0f;
                                    this.f859f.getSegment(0.0f, f11, path, true);
                                } else {
                                    f2 = 0.0f;
                                    this.f859f.getSegment(f10, f11, path, true);
                                }
                                path.rLineTo(f2, f2);
                            }
                            path2.addPath(path, matrix3);
                            q.a aVar = hVar.f828g;
                            if ((aVar.f1837a != null) || aVar.f1839c != 0) {
                                if (this.f858e == null) {
                                    Paint paint = new Paint(1);
                                    this.f858e = paint;
                                    paint.setStyle(Paint.Style.FILL);
                                }
                                Paint paint2 = this.f858e;
                                Shader shader = aVar.f1837a;
                                if (shader != null) {
                                    shader.setLocalMatrix(matrix3);
                                    paint2.setShader(shader);
                                    paint2.setAlpha(Math.round(hVar.f830i * 255.0f));
                                } else {
                                    paint2.setShader(null);
                                    paint2.setAlpha(255);
                                    int i6 = aVar.f1839c;
                                    float f12 = hVar.f830i;
                                    PorterDuff.Mode mode = o.f882k;
                                    paint2.setColor((i6 & 16777215) | (((int) (Color.alpha(i6) * f12)) << 24));
                                }
                                paint2.setColorFilter(null);
                                path2.setFillType(hVar.f851c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                                canvas.drawPath(path2, paint2);
                            }
                            q.a aVar2 = hVar.f826e;
                            if ((aVar2.f1837a != null) || aVar2.f1839c != 0) {
                                if (this.f857d == null) {
                                    z2 = true;
                                    Paint paint3 = new Paint(1);
                                    this.f857d = paint3;
                                    paint3.setStyle(Paint.Style.STROKE);
                                } else {
                                    z2 = true;
                                }
                                Paint paint4 = this.f857d;
                                Paint.Join join = hVar.f835n;
                                if (join != null) {
                                    paint4.setStrokeJoin(join);
                                }
                                Paint.Cap cap = hVar.f834m;
                                if (cap != null) {
                                    paint4.setStrokeCap(cap);
                                }
                                paint4.setStrokeMiter(hVar.f836o);
                                Shader shader2 = aVar2.f1837a;
                                if (shader2 == null) {
                                    z2 = false;
                                }
                                if (z2) {
                                    shader2.setLocalMatrix(matrix3);
                                    paint4.setShader(shader2);
                                    paint4.setAlpha(Math.round(hVar.f829h * 255.0f));
                                } else {
                                    paint4.setShader(null);
                                    paint4.setAlpha(255);
                                    int i7 = aVar2.f1839c;
                                    float f13 = hVar.f829h;
                                    PorterDuff.Mode mode2 = o.f882k;
                                    paint4.setColor((i7 & 16777215) | (((int) (Color.alpha(i7) * f13)) << 24));
                                }
                                paint4.setColorFilter(null);
                                paint4.setStrokeWidth(hVar.f827f * fAbs * fMin);
                                canvas.drawPath(path2, paint4);
                            }
                        }
                    }
                }
                i5 = i4 + 1;
                c2 = 0;
            }
            i4 = i5;
            i5 = i4 + 1;
            c2 = 0;
        }
    }

    public float getAlpha() {
        return getRootAlpha() / 255.0f;
    }

    public int getRootAlpha() {
        return this.f865l;
    }

    public void setAlpha(float f2) {
        setRootAlpha((int) (f2 * 255.0f));
    }

    public void setRootAlpha(int i2) {
        this.f865l = i2;
    }

    public l(l lVar) {
        this.f856c = new Matrix();
        this.f861h = 0.0f;
        this.f862i = 0.0f;
        this.f863j = 0.0f;
        this.f864k = 0.0f;
        this.f865l = 255;
        this.f866m = null;
        this.f867n = null;
        m.b bVar = new m.b();
        this.f868o = bVar;
        this.f860g = new i(lVar.f860g, bVar);
        this.f854a = new Path(lVar.f854a);
        this.f855b = new Path(lVar.f855b);
        this.f861h = lVar.f861h;
        this.f862i = lVar.f862i;
        this.f863j = lVar.f863j;
        this.f864k = lVar.f864k;
        this.f865l = lVar.f865l;
        this.f866m = lVar.f866m;
        String str = lVar.f866m;
        if (str != null) {
            bVar.put(str, this);
        }
        this.f867n = lVar.f867n;
    }
}
