package r;

import android.graphics.Path;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public char f1894a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float[] f1895b;

    public c(char c2, float[] fArr) {
        this.f1894a = c2;
        this.f1895b = fArr;
    }

    public static void a(Path path, float f2, float f3, float f4, float f5, float f6, float f7, float f8, boolean z2, boolean z3) {
        double d2;
        double d3;
        double radians = Math.toRadians(f8);
        double dCos = Math.cos(radians);
        double dSin = Math.sin(radians);
        double d4 = f2;
        double d5 = f3;
        double d6 = (d5 * dSin) + (d4 * dCos);
        double d7 = d4;
        double d8 = f6;
        double d9 = d6 / d8;
        double d10 = f7;
        double d11 = ((d5 * dCos) + (((double) (-f2)) * dSin)) / d10;
        double d12 = d5;
        double d13 = f5;
        double d14 = ((d13 * dSin) + (((double) f4) * dCos)) / d8;
        double d15 = ((d13 * dCos) + (((double) (-f4)) * dSin)) / d10;
        double d16 = d9 - d14;
        double d17 = d11 - d15;
        double d18 = (d9 + d14) / 2.0d;
        double d19 = (d11 + d15) / 2.0d;
        double d20 = (d17 * d17) + (d16 * d16);
        if (d20 == 0.0d) {
            Log.w("PathParser", " Points are coincident");
            return;
        }
        double d21 = (1.0d / d20) - 0.25d;
        if (d21 < 0.0d) {
            Log.w("PathParser", "Points are too far apart " + d20);
            float fSqrt = (float) (Math.sqrt(d20) / 1.99999d);
            a(path, f2, f3, f4, f5, f6 * fSqrt, f7 * fSqrt, f8, z2, z3);
            return;
        }
        double dSqrt = Math.sqrt(d21);
        double d22 = d16 * dSqrt;
        double d23 = dSqrt * d17;
        if (z2 == z3) {
            d2 = d18 - d23;
            d3 = d19 + d22;
        } else {
            d2 = d18 + d23;
            d3 = d19 - d22;
        }
        double dAtan2 = Math.atan2(d11 - d3, d9 - d2);
        double dAtan3 = Math.atan2(d15 - d3, d14 - d2) - dAtan2;
        int i2 = 0;
        if (z3 != (dAtan3 >= 0.0d)) {
            dAtan3 = dAtan3 > 0.0d ? dAtan3 - 6.283185307179586d : dAtan3 + 6.283185307179586d;
        }
        double d24 = d2 * d8;
        double d25 = d3 * d10;
        double d26 = (d24 * dCos) - (d25 * dSin);
        double d27 = (d25 * dCos) + (d24 * dSin);
        int iCeil = (int) Math.ceil(Math.abs((dAtan3 * 4.0d) / 3.141592653589793d));
        double dCos2 = Math.cos(radians);
        double dSin2 = Math.sin(radians);
        double dCos3 = Math.cos(dAtan2);
        double dSin3 = Math.sin(dAtan2);
        double d28 = -d8;
        double d29 = d28 * dCos2;
        double d30 = d10 * dSin2;
        double d31 = (d29 * dSin3) - (d30 * dCos3);
        double d32 = d28 * dSin2;
        double d33 = d10 * dCos2;
        double d34 = (dCos3 * d33) + (dSin3 * d32);
        double d35 = dAtan3 / ((double) iCeil);
        double d36 = dAtan2;
        while (i2 < iCeil) {
            double d37 = d36 + d35;
            double dSin4 = Math.sin(d37);
            double dCos4 = Math.cos(d37);
            double d38 = d35;
            double d39 = (((d8 * dCos2) * dCos4) + d26) - (d30 * dSin4);
            double d40 = d26;
            double d41 = (d33 * dSin4) + (d8 * dSin2 * dCos4) + d27;
            double d42 = (d29 * dSin4) - (d30 * dCos4);
            double d43 = (dCos4 * d33) + (dSin4 * d32);
            double d44 = d37 - d36;
            double dTan = Math.tan(d44 / 2.0d);
            double dSqrt2 = ((Math.sqrt(((dTan * 3.0d) * dTan) + 4.0d) - 1.0d) * Math.sin(d44)) / 3.0d;
            path.rLineTo(0.0f, 0.0f);
            path.cubicTo((float) ((d31 * dSqrt2) + d7), (float) ((d34 * dSqrt2) + d12), (float) (d39 - (dSqrt2 * d42)), (float) (d41 - (dSqrt2 * d43)), (float) d39, (float) d41);
            i2++;
            d33 = d33;
            d32 = d32;
            iCeil = iCeil;
            dCos2 = dCos2;
            d36 = d37;
            d8 = d8;
            d34 = d43;
            d31 = d42;
            d7 = d39;
            d12 = d41;
            d35 = d38;
            d26 = d40;
        }
    }

    public static void b(c[] cVarArr, Path path) {
        int i2;
        int i3;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        float f8;
        float f9;
        float f10;
        float f11;
        float f12;
        float f13;
        float f14;
        float f15;
        float f16;
        float f17;
        float f18;
        float f19;
        float f20;
        float f21;
        float[] fArr = new float[6];
        char c2 = 'm';
        char c3 = 0;
        char c4 = 'm';
        int i4 = 0;
        while (i4 < cVarArr.length) {
            c cVar = cVarArr[i4];
            char c5 = cVar.f1894a;
            float f22 = fArr[c3];
            float f23 = fArr[1];
            float f24 = fArr[2];
            float f25 = fArr[3];
            float f26 = fArr[4];
            float f27 = fArr[5];
            switch (c5) {
                case 'A':
                case 'a':
                    i2 = 7;
                    break;
                case 'C':
                case 'c':
                    i2 = 6;
                    break;
                case 'H':
                case 'V':
                case 'h':
                case 'v':
                    i2 = 1;
                    break;
                case 'Q':
                case 'S':
                case 'q':
                case 's':
                    i2 = 4;
                    break;
                case 'Z':
                case 'z':
                    path.close();
                    path.moveTo(f26, f27);
                    f22 = f26;
                    f24 = f22;
                    f23 = f27;
                    f25 = f23;
                default:
                    i2 = 2;
                    break;
            }
            float f28 = f26;
            float f29 = f27;
            float f30 = f22;
            float f31 = f23;
            int i5 = 0;
            while (true) {
                float[] fArr2 = cVar.f1895b;
                if (i5 < fArr2.length) {
                    if (c5 != 'A') {
                        if (c5 != 'C') {
                            if (c5 == 'H') {
                                i3 = i5;
                                c5 = c5;
                                cVar = cVar;
                                i4 = i4;
                                int i6 = i3 + 0;
                                path.lineTo(fArr2[i6], f31);
                                f30 = fArr2[i6];
                            } else if (c5 == 'Q') {
                                i3 = i5;
                                int i7 = i3 + 0;
                                int i8 = i3 + 1;
                                int i9 = i3 + 2;
                                int i10 = i3 + 3;
                                path.quadTo(fArr2[i7], fArr2[i8], fArr2[i9], fArr2[i10]);
                                f2 = fArr2[i7];
                                f3 = fArr2[i8];
                                f30 = fArr2[i9];
                                f31 = fArr2[i10];
                            } else if (c5 == 'V') {
                                i3 = i5;
                                c5 = c5;
                                cVar = cVar;
                                i4 = i4;
                                int i11 = i3 + 0;
                                path.lineTo(f30, fArr2[i11]);
                                f31 = fArr2[i11];
                            } else if (c5 != 'a') {
                                if (c5 != 'c') {
                                    if (c5 == 'h') {
                                        i3 = i5;
                                        int i12 = i3 + 0;
                                        path.rLineTo(fArr2[i12], 0.0f);
                                        f30 += fArr2[i12];
                                    } else if (c5 != 'q') {
                                        if (c5 != 'v') {
                                            if (c5 != 'L') {
                                                if (c5 == 'M') {
                                                    i3 = i5;
                                                    f12 = fArr2[i3 + 0];
                                                    f13 = fArr2[i3 + 1];
                                                    if (i3 > 0) {
                                                        path.lineTo(f12, f13);
                                                    } else {
                                                        path.moveTo(f12, f13);
                                                        f28 = f12;
                                                        f29 = f13;
                                                    }
                                                } else if (c5 == 'S') {
                                                    i3 = i5;
                                                    float f32 = f31;
                                                    float f33 = f30;
                                                    if (c4 == 'c' || c4 == 's' || c4 == 'C' || c4 == 'S') {
                                                        f14 = (f32 * 2.0f) - f25;
                                                        f15 = (f33 * 2.0f) - f24;
                                                    } else {
                                                        f15 = f33;
                                                        f14 = f32;
                                                    }
                                                    int i13 = i3 + 0;
                                                    int i14 = i3 + 1;
                                                    int i15 = i3 + 2;
                                                    int i16 = i3 + 3;
                                                    path.cubicTo(f15, f14, fArr2[i13], fArr2[i14], fArr2[i15], fArr2[i16]);
                                                    float f34 = fArr2[i13];
                                                    float f35 = fArr2[i14];
                                                    f10 = fArr2[i15];
                                                    f9 = fArr2[i16];
                                                    f24 = f34;
                                                    f25 = f35;
                                                    f30 = f10;
                                                    f31 = f9;
                                                } else if (c5 == 'T') {
                                                    i3 = i5;
                                                    float f36 = f31;
                                                    float f37 = f30;
                                                    if (c4 == 'q' || c4 == 't' || c4 == 'Q' || c4 == 'T') {
                                                        f16 = (f37 * 2.0f) - f24;
                                                        f17 = (f36 * 2.0f) - f25;
                                                    } else {
                                                        f16 = f37;
                                                        f17 = f36;
                                                    }
                                                    int i17 = i3 + 0;
                                                    int i18 = i3 + 1;
                                                    path.quadTo(f16, f17, fArr2[i17], fArr2[i18]);
                                                    f25 = f17;
                                                    f24 = f16;
                                                    c5 = c5;
                                                    cVar = cVar;
                                                    i4 = i4;
                                                    f30 = fArr2[i17];
                                                    f31 = fArr2[i18];
                                                } else if (c5 == 'l') {
                                                    i3 = i5;
                                                    int i19 = i3 + 0;
                                                    float f38 = fArr2[i19];
                                                    int i20 = i3 + 1;
                                                    path.rLineTo(f38, fArr2[i20]);
                                                    f30 += fArr2[i19];
                                                    f11 = fArr2[i20];
                                                } else if (c5 == c2) {
                                                    i3 = i5;
                                                    float f39 = fArr2[i3 + 0];
                                                    f30 += f39;
                                                    float f40 = fArr2[i3 + 1];
                                                    f31 += f40;
                                                    if (i3 > 0) {
                                                        path.rLineTo(f39, f40);
                                                    } else {
                                                        path.rMoveTo(f39, f40);
                                                        f29 = f31;
                                                        f28 = f30;
                                                    }
                                                } else if (c5 != 's') {
                                                    if (c5 == 't') {
                                                        if (c4 == 'q' || c4 == 't' || c4 == 'Q' || c4 == 'T') {
                                                            f20 = f30 - f24;
                                                            f21 = f31 - f25;
                                                        } else {
                                                            f21 = 0.0f;
                                                            f20 = 0.0f;
                                                        }
                                                        int i21 = i5 + 0;
                                                        int i22 = i5 + 1;
                                                        path.rQuadTo(f20, f21, fArr2[i21], fArr2[i22]);
                                                        float f41 = f20 + f30;
                                                        float f42 = f21 + f31;
                                                        f30 += fArr2[i21];
                                                        f31 += fArr2[i22];
                                                        f25 = f42;
                                                        f24 = f41;
                                                    }
                                                    i3 = i5;
                                                } else {
                                                    if (c4 == 'c' || c4 == 's' || c4 == 'C' || c4 == 'S') {
                                                        float f43 = f30 - f24;
                                                        f18 = f31 - f25;
                                                        f19 = f43;
                                                    } else {
                                                        f18 = 0.0f;
                                                        f19 = 0.0f;
                                                    }
                                                    int i23 = i5 + 0;
                                                    int i24 = i5 + 1;
                                                    int i25 = i5 + 2;
                                                    int i26 = i5 + 3;
                                                    i3 = i5;
                                                    f4 = f31;
                                                    float f44 = f30;
                                                    path.rCubicTo(f19, f18, fArr2[i23], fArr2[i24], fArr2[i25], fArr2[i26]);
                                                    f5 = fArr2[i23] + f44;
                                                    f6 = fArr2[i24] + f4;
                                                    f7 = f44 + fArr2[i25];
                                                    f8 = fArr2[i26];
                                                }
                                                f30 = f28;
                                                f31 = f29;
                                            } else {
                                                i3 = i5;
                                                int i27 = i3 + 0;
                                                int i28 = i3 + 1;
                                                path.lineTo(fArr2[i27], fArr2[i28]);
                                                f12 = fArr2[i27];
                                                f13 = fArr2[i28];
                                            }
                                            f30 = f12;
                                            f31 = f13;
                                        } else {
                                            i3 = i5;
                                            int i29 = i3 + 0;
                                            path.rLineTo(0.0f, fArr2[i29]);
                                            f11 = fArr2[i29];
                                        }
                                        f31 += f11;
                                    } else {
                                        i3 = i5;
                                        f4 = f31;
                                        float f45 = f30;
                                        int i30 = i3 + 0;
                                        float f46 = fArr2[i30];
                                        int i31 = i3 + 1;
                                        int i32 = i3 + 2;
                                        int i33 = i3 + 3;
                                        path.rQuadTo(f46, fArr2[i31], fArr2[i32], fArr2[i33]);
                                        f5 = fArr2[i30] + f45;
                                        f6 = fArr2[i31] + f4;
                                        float f47 = f45 + fArr2[i32];
                                        float f48 = fArr2[i33];
                                        f7 = f47;
                                        f8 = f48;
                                    }
                                    c5 = c5;
                                    cVar = cVar;
                                    i4 = i4;
                                } else {
                                    i3 = i5;
                                    f4 = f31;
                                    float f49 = f30;
                                    int i34 = i3 + 2;
                                    int i35 = i3 + 3;
                                    int i36 = i3 + 4;
                                    int i37 = i3 + 5;
                                    path.rCubicTo(fArr2[i3 + 0], fArr2[i3 + 1], fArr2[i34], fArr2[i35], fArr2[i36], fArr2[i37]);
                                    f5 = fArr2[i34] + f49;
                                    f6 = fArr2[i35] + f4;
                                    f7 = f49 + fArr2[i36];
                                    f8 = fArr2[i37];
                                }
                                f9 = f4 + f8;
                                f24 = f5;
                                f25 = f6;
                                f10 = f7;
                                f30 = f10;
                                f31 = f9;
                                c5 = c5;
                                cVar = cVar;
                                i4 = i4;
                            } else {
                                i3 = i5;
                                float f50 = f31;
                                float f51 = f30;
                                int i38 = i3 + 5;
                                int i39 = i3 + 6;
                                a(path, f51, f50, fArr2[i38] + f51, fArr2[i39] + f50, fArr2[i3 + 0], fArr2[i3 + 1], fArr2[i3 + 2], fArr2[i3 + 3] != 0.0f, fArr2[i3 + 4] != 0.0f);
                                f30 = f51 + fArr2[i38];
                                f31 = f50 + fArr2[i39];
                            }
                            i5 = i3 + i2;
                            cVar = cVar;
                            c4 = c5;
                            c5 = c4;
                            i4 = i4;
                            c2 = 'm';
                        } else {
                            i3 = i5;
                            int i40 = i3 + 2;
                            int i41 = i3 + 3;
                            int i42 = i3 + 4;
                            int i43 = i3 + 5;
                            path.cubicTo(fArr2[i3 + 0], fArr2[i3 + 1], fArr2[i40], fArr2[i41], fArr2[i42], fArr2[i43]);
                            float f52 = fArr2[i42];
                            float f53 = fArr2[i43];
                            f2 = fArr2[i40];
                            f30 = f52;
                            f31 = f53;
                            f3 = fArr2[i41];
                        }
                        f24 = f2;
                        f25 = f3;
                        i5 = i3 + i2;
                        cVar = cVar;
                        c4 = c5;
                        c5 = c4;
                        i4 = i4;
                        c2 = 'm';
                    } else {
                        i3 = i5;
                        int i44 = i3 + 5;
                        int i45 = i3 + 6;
                        a(path, f30, f31, fArr2[i44], fArr2[i45], fArr2[i3 + 0], fArr2[i3 + 1], fArr2[i3 + 2], fArr2[i3 + 3] != 0.0f, fArr2[i3 + 4] != 0.0f);
                        f30 = fArr2[i44];
                        f31 = fArr2[i45];
                    }
                    f25 = f31;
                    f24 = f30;
                    i5 = i3 + i2;
                    cVar = cVar;
                    c4 = c5;
                    c5 = c4;
                    i4 = i4;
                    c2 = 'm';
                }
            }
            int i46 = i4;
            fArr[0] = f30;
            fArr[1] = f31;
            fArr[2] = f24;
            fArr[3] = f25;
            fArr[4] = f28;
            fArr[5] = f29;
            i4 = i46 + 1;
            c4 = cVarArr[i46].f1894a;
            c2 = 'm';
            c3 = 0;
        }
    }

    public c(c cVar) {
        this.f1894a = cVar.f1894a;
        float[] fArr = cVar.f1895b;
        this.f1895b = androidx.lifecycle.i.k(fArr, fArr.length);
    }
}
