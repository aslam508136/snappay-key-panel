package com.google.crypto.tink.subtle;

import com.google.crypto.tink.annotations.Alpha;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
@Alpha
final class Field25519 {
    static final int FIELD_LEN = 32;
    static final int LIMB_CNT = 10;
    private static final long TWO_TO_25 = 33554432;
    private static final long TWO_TO_26 = 67108864;
    private static final int[] EXPAND_START = {0, 3, 6, 9, 12, 16, 19, 22, 25, 28};
    private static final int[] EXPAND_SHIFT = {0, 2, 3, 5, 6, 0, 1, 3, 4, 6};
    private static final int[] MASK = {67108863, 33554431};
    private static final int[] SHIFT = {26, 25};

    private Field25519() {
    }

    public static byte[] contract(long[] jArr) {
        int i2;
        long[] jArrCopyOf = Arrays.copyOf(jArr, 10);
        int i3 = 0;
        while (true) {
            if (i3 >= 2) {
                break;
            }
            int i4 = 0;
            while (i4 < 9) {
                long j2 = jArrCopyOf[i4];
                int i5 = SHIFT[i4 & 1];
                int i6 = -((int) (((j2 >> 31) & j2) >> i5));
                jArrCopyOf[i4] = j2 + ((long) (i6 << i5));
                i4++;
                jArrCopyOf[i4] = jArrCopyOf[i4] - ((long) i6);
            }
            long j3 = jArrCopyOf[9];
            int i7 = -((int) (((j3 >> 31) & j3) >> 25));
            jArrCopyOf[9] = j3 + ((long) (i7 << 25));
            jArrCopyOf[0] = jArrCopyOf[0] - ((long) (i7 * 19));
            i3++;
        }
        long j4 = jArrCopyOf[0];
        int i8 = -((int) (((j4 >> 31) & j4) >> 26));
        jArrCopyOf[0] = j4 + ((long) (i8 << 26));
        jArrCopyOf[1] = jArrCopyOf[1] - ((long) i8);
        for (int i9 = 0; i9 < 2; i9++) {
            int i10 = 0;
            while (i10 < 9) {
                long j5 = jArrCopyOf[i10];
                int i11 = i10 & 1;
                int i12 = (int) (j5 >> SHIFT[i11]);
                jArrCopyOf[i10] = j5 & ((long) MASK[i11]);
                i10++;
                jArrCopyOf[i10] = jArrCopyOf[i10] + ((long) i12);
            }
        }
        long j6 = jArrCopyOf[9];
        jArrCopyOf[9] = j6 & 33554431;
        long j7 = jArrCopyOf[0] + ((long) (((int) (j6 >> 25)) * 19));
        jArrCopyOf[0] = j7;
        int iGte = gte((int) j7, 67108845);
        for (int i13 = 1; i13 < 10; i13++) {
            iGte &= eq((int) jArrCopyOf[i13], MASK[i13 & 1]);
        }
        jArrCopyOf[0] = jArrCopyOf[0] - ((long) (67108845 & iGte));
        long j8 = 33554431 & iGte;
        jArrCopyOf[1] = jArrCopyOf[1] - j8;
        for (i2 = 2; i2 < 10; i2 += 2) {
            jArrCopyOf[i2] = jArrCopyOf[i2] - ((long) (67108863 & iGte));
            int i14 = i2 + 1;
            jArrCopyOf[i14] = jArrCopyOf[i14] - j8;
        }
        for (int i15 = 0; i15 < 10; i15++) {
            jArrCopyOf[i15] = jArrCopyOf[i15] << EXPAND_SHIFT[i15];
        }
        byte[] bArr = new byte[32];
        for (int i16 = 0; i16 < 10; i16++) {
            int i17 = EXPAND_START[i16];
            long j9 = bArr[i17];
            long j10 = jArrCopyOf[i16];
            bArr[i17] = (byte) (j9 | (j10 & 255));
            int i18 = i17 + 1;
            bArr[i18] = (byte) (((long) bArr[i18]) | ((j10 >> 8) & 255));
            int i19 = i17 + 2;
            bArr[i19] = (byte) (((long) bArr[i19]) | ((j10 >> 16) & 255));
            int i20 = i17 + 3;
            bArr[i20] = (byte) (((long) bArr[i20]) | ((j10 >> 24) & 255));
        }
        return bArr;
    }

    private static int eq(int i2, int i3) {
        int i4 = ~(i2 ^ i3);
        int i5 = i4 & (i4 << 16);
        int i6 = i5 & (i5 << 8);
        int i7 = i6 & (i6 << 4);
        int i8 = i7 & (i7 << 2);
        return (i8 & (i8 << 1)) >> 31;
    }

    public static long[] expand(byte[] bArr) {
        long[] jArr = new long[10];
        for (int i2 = 0; i2 < 10; i2++) {
            int i3 = EXPAND_START[i2];
            jArr[i2] = ((((((long) (bArr[i3] & 255)) | (((long) (bArr[i3 + 1] & 255)) << 8)) | (((long) (bArr[i3 + 2] & 255)) << 16)) | (((long) (bArr[i3 + 3] & 255)) << 24)) >> EXPAND_SHIFT[i2]) & ((long) MASK[i2 & 1]);
        }
        return jArr;
    }

    private static int gte(int i2, int i3) {
        return ~((i2 - i3) >> 31);
    }

    public static void inverse(long[] jArr, long[] jArr2) {
        long[] jArr3 = new long[10];
        long[] jArr4 = new long[10];
        long[] jArr5 = new long[10];
        long[] jArr6 = new long[10];
        long[] jArr7 = new long[10];
        long[] jArr8 = new long[10];
        long[] jArr9 = new long[10];
        long[] jArr10 = new long[10];
        long[] jArr11 = new long[10];
        long[] jArr12 = new long[10];
        square(jArr3, jArr2);
        square(jArr12, jArr3);
        square(jArr11, jArr12);
        mult(jArr4, jArr11, jArr2);
        mult(jArr5, jArr4, jArr3);
        square(jArr11, jArr5);
        mult(jArr6, jArr11, jArr4);
        square(jArr11, jArr6);
        square(jArr12, jArr11);
        square(jArr11, jArr12);
        square(jArr12, jArr11);
        square(jArr11, jArr12);
        mult(jArr7, jArr11, jArr6);
        square(jArr11, jArr7);
        square(jArr12, jArr11);
        for (int i2 = 2; i2 < 10; i2 += 2) {
            square(jArr11, jArr12);
            square(jArr12, jArr11);
        }
        mult(jArr8, jArr12, jArr7);
        square(jArr11, jArr8);
        square(jArr12, jArr11);
        for (int i3 = 2; i3 < 20; i3 += 2) {
            square(jArr11, jArr12);
            square(jArr12, jArr11);
        }
        mult(jArr11, jArr12, jArr8);
        square(jArr12, jArr11);
        square(jArr11, jArr12);
        for (int i4 = 2; i4 < 10; i4 += 2) {
            square(jArr12, jArr11);
            square(jArr11, jArr12);
        }
        mult(jArr9, jArr11, jArr7);
        square(jArr11, jArr9);
        square(jArr12, jArr11);
        for (int i5 = 2; i5 < 50; i5 += 2) {
            square(jArr11, jArr12);
            square(jArr12, jArr11);
        }
        mult(jArr10, jArr12, jArr9);
        square(jArr12, jArr10);
        square(jArr11, jArr12);
        for (int i6 = 2; i6 < 100; i6 += 2) {
            square(jArr12, jArr11);
            square(jArr11, jArr12);
        }
        mult(jArr12, jArr11, jArr10);
        square(jArr11, jArr12);
        square(jArr12, jArr11);
        for (int i7 = 2; i7 < 50; i7 += 2) {
            square(jArr11, jArr12);
            square(jArr12, jArr11);
        }
        mult(jArr11, jArr12, jArr9);
        square(jArr12, jArr11);
        square(jArr11, jArr12);
        square(jArr12, jArr11);
        square(jArr11, jArr12);
        square(jArr12, jArr11);
        mult(jArr, jArr12, jArr5);
    }

    public static void mult(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[19];
        product(jArr4, jArr2, jArr3);
        reduce(jArr4, jArr);
    }

    public static void product(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr[0] = jArr2[0] * jArr3[0];
        long j2 = jArr2[0];
        long j3 = jArr3[1] * j2;
        long j4 = jArr2[1];
        long j5 = jArr3[0];
        jArr[1] = (j4 * j5) + j3;
        long j6 = jArr2[1];
        long j7 = jArr3[1];
        jArr[2] = (jArr2[2] * j5) + (jArr3[2] * j2) + (j6 * 2 * j7);
        long j8 = jArr3[2];
        long j9 = jArr2[2];
        jArr[3] = (jArr2[3] * j5) + (jArr3[3] * j2) + (j9 * j7) + (j6 * j8);
        long j10 = jArr3[3];
        long j11 = jArr2[3];
        jArr[4] = (jArr2[4] * j5) + (jArr3[4] * j2) + (((j11 * j7) + (j6 * j10)) * 2) + (j9 * j8);
        long j12 = jArr3[4];
        long j13 = (j6 * j12) + (j11 * j8) + (j9 * j10);
        long j14 = jArr2[4];
        jArr[5] = (jArr2[5] * j5) + (jArr3[5] * j2) + (j14 * j7) + j13;
        long j15 = jArr3[5];
        long j16 = jArr2[5];
        jArr[6] = (jArr2[6] * j5) + (jArr3[6] * j2) + (j14 * j8) + (j9 * j12) + (((j16 * j7) + (j6 * j15) + (j11 * j10)) * 2);
        long j17 = (j16 * j8) + (j9 * j15) + (j14 * j10) + (j11 * j12);
        long j18 = jArr3[6];
        long j19 = (j6 * j18) + j17;
        long j20 = jArr2[6];
        jArr[7] = (jArr2[7] * j5) + (jArr3[7] * j2) + (j20 * j7) + j19;
        long j21 = jArr3[7];
        long j22 = (j6 * j21) + (j16 * j10) + (j11 * j15);
        long j23 = jArr2[7];
        long j24 = (((j23 * j7) + j22) * 2) + (j14 * j12);
        jArr[8] = (jArr2[8] * j5) + (jArr3[8] * j2) + (j20 * j8) + (j9 * j18) + j24;
        long j25 = (j23 * j8) + (j9 * j21) + (j20 * j10) + (j11 * j18) + (j16 * j12) + (j14 * j15);
        long j26 = jArr3[8];
        long j27 = (j6 * j26) + j25;
        long j28 = jArr2[8];
        jArr[9] = (jArr2[9] * j5) + (j2 * jArr3[9]) + (j28 * j7) + j27;
        long j29 = (j23 * j10) + (j11 * j21) + (j16 * j15);
        long j30 = jArr3[9];
        long j31 = jArr2[9];
        long j32 = j14 * j18;
        jArr[10] = (j28 * j8) + (j9 * j26) + (j20 * j12) + j32 + (((j7 * j31) + (j6 * j30) + j29) * 2);
        long j33 = j9 * j30;
        long j34 = j8 * j31;
        jArr[11] = j34 + j33 + (j28 * j10) + (j11 * j26) + (j23 * j12) + (j14 * j21) + (j20 * j15) + (j16 * j18);
        long j35 = j11 * j30;
        long j36 = j10 * j31;
        long j37 = j28 * j12;
        jArr[12] = j37 + (j14 * j26) + ((j36 + j35 + (j23 * j15) + (j16 * j21)) * 2) + (j20 * j18);
        long j38 = j14 * j30;
        long j39 = j12 * j31;
        jArr[13] = j39 + j38 + (j28 * j15) + (j16 * j26) + (j23 * j18) + (j20 * j21);
        long j40 = j15 * j31;
        long j41 = j28 * j18;
        jArr[14] = j41 + (j20 * j26) + ((j40 + (j16 * j30) + (j23 * j21)) * 2);
        long j42 = j20 * j30;
        long j43 = j18 * j31;
        jArr[15] = j43 + j42 + (j28 * j21) + (j23 * j26);
        jArr[16] = (((j21 * j31) + (j23 * j30)) * 2) + (j28 * j26);
        jArr[17] = (j26 * j31) + (j28 * j30);
        jArr[18] = j31 * 2 * j30;
    }

    public static void reduce(long[] jArr, long[] jArr2) {
        if (jArr.length != 19) {
            long[] jArr3 = new long[19];
            System.arraycopy(jArr, 0, jArr3, 0, jArr.length);
            jArr = jArr3;
        }
        reduceSizeByModularReduction(jArr);
        reduceCoefficients(jArr);
        System.arraycopy(jArr, 0, jArr2, 0, 10);
    }

    public static void reduceCoefficients(long[] jArr) {
        jArr[10] = 0;
        int i2 = 0;
        while (i2 < 10) {
            long j2 = jArr[i2];
            long j3 = j2 / TWO_TO_26;
            jArr[i2] = j2 - (j3 << 26);
            int i3 = i2 + 1;
            long j4 = jArr[i3] + j3;
            jArr[i3] = j4;
            long j5 = j4 / TWO_TO_25;
            jArr[i3] = j4 - (j5 << 25);
            i2 += 2;
            jArr[i2] = jArr[i2] + j5;
        }
        long j6 = jArr[0];
        long j7 = jArr[10];
        long j8 = j6 + (j7 << 4);
        jArr[0] = j8;
        long j9 = j8 + (j7 << 1);
        jArr[0] = j9;
        long j10 = j9 + j7;
        jArr[0] = j10;
        jArr[10] = 0;
        long j11 = j10 / TWO_TO_26;
        jArr[0] = j10 - (j11 << 26);
        jArr[1] = jArr[1] + j11;
    }

    public static void reduceSizeByModularReduction(long[] jArr) {
        long j2 = jArr[8];
        long j3 = jArr[18];
        long j4 = j2 + (j3 << 4);
        jArr[8] = j4;
        long j5 = j4 + (j3 << 1);
        jArr[8] = j5;
        jArr[8] = j5 + j3;
        long j6 = jArr[7];
        long j7 = jArr[17];
        long j8 = j6 + (j7 << 4);
        jArr[7] = j8;
        long j9 = j8 + (j7 << 1);
        jArr[7] = j9;
        jArr[7] = j9 + j7;
        long j10 = jArr[6];
        long j11 = jArr[16];
        long j12 = j10 + (j11 << 4);
        jArr[6] = j12;
        long j13 = j12 + (j11 << 1);
        jArr[6] = j13;
        jArr[6] = j13 + j11;
        long j14 = jArr[5];
        long j15 = jArr[15];
        long j16 = j14 + (j15 << 4);
        jArr[5] = j16;
        long j17 = j16 + (j15 << 1);
        jArr[5] = j17;
        jArr[5] = j17 + j15;
        long j18 = jArr[4];
        long j19 = jArr[14];
        long j20 = j18 + (j19 << 4);
        jArr[4] = j20;
        long j21 = j20 + (j19 << 1);
        jArr[4] = j21;
        jArr[4] = j21 + j19;
        long j22 = jArr[3];
        long j23 = jArr[13];
        long j24 = j22 + (j23 << 4);
        jArr[3] = j24;
        long j25 = j24 + (j23 << 1);
        jArr[3] = j25;
        jArr[3] = j25 + j23;
        long j26 = jArr[2];
        long j27 = jArr[12];
        long j28 = j26 + (j27 << 4);
        jArr[2] = j28;
        long j29 = j28 + (j27 << 1);
        jArr[2] = j29;
        jArr[2] = j29 + j27;
        long j30 = jArr[1];
        long j31 = jArr[11];
        long j32 = j30 + (j31 << 4);
        jArr[1] = j32;
        long j33 = j32 + (j31 << 1);
        jArr[1] = j33;
        jArr[1] = j33 + j31;
        long j34 = jArr[0];
        long j35 = jArr[10];
        long j36 = j34 + (j35 << 4);
        jArr[0] = j36;
        long j37 = j36 + (j35 << 1);
        jArr[0] = j37;
        jArr[0] = j37 + j35;
    }

    public static void scalarProduct(long[] jArr, long[] jArr2, long j2) {
        for (int i2 = 0; i2 < 10; i2++) {
            jArr[i2] = jArr2[i2] * j2;
        }
    }

    public static void square(long[] jArr, long[] jArr2) {
        long[] jArr3 = new long[19];
        squareInner(jArr3, jArr2);
        reduce(jArr3, jArr);
    }

    private static void squareInner(long[] jArr, long[] jArr2) {
        long j2 = jArr2[0];
        jArr[0] = j2 * j2;
        long j3 = jArr2[0];
        jArr[1] = j3 * 2 * jArr2[1];
        long j4 = jArr2[1];
        jArr[2] = ((jArr2[2] * j3) + (j4 * j4)) * 2;
        long j5 = jArr2[2];
        jArr[3] = ((jArr2[3] * j3) + (j4 * j5)) * 2;
        long j6 = jArr2[3];
        jArr[4] = (j3 * 2 * jArr2[4]) + (j4 * 4 * j6) + (j5 * j5);
        long j7 = jArr2[4];
        long j8 = jArr2[5] * j3;
        jArr[5] = (j8 + (j4 * j7) + (j5 * j6)) * 2;
        long j9 = jArr2[6] * j3;
        long j10 = jArr2[5];
        jArr[6] = ((j4 * 2 * j10) + j9 + (j5 * j7) + (j6 * j6)) * 2;
        long j11 = jArr2[6];
        jArr[7] = ((jArr2[7] * j3) + (j4 * j11) + (j5 * j10) + (j6 * j7)) * 2;
        long j12 = (jArr2[8] * j3) + (j5 * j11);
        long j13 = jArr2[7];
        jArr[8] = (((((j6 * j10) + (j4 * j13)) * 2) + j12) * 2) + (j7 * j7);
        long j14 = jArr2[8];
        long j15 = j4 * j14;
        jArr[9] = ((j3 * jArr2[9]) + j15 + (j5 * j13) + (j6 * j11) + (j7 * j10)) * 2;
        long j16 = jArr2[9];
        jArr[10] = ((((j4 * j16) + (j6 * j13)) * 2) + (j5 * j14) + (j7 * j11) + (j10 * j10)) * 2;
        long j17 = j5 * j16;
        jArr[11] = (j17 + (j6 * j14) + (j7 * j13) + (j10 * j11)) * 2;
        jArr[12] = (((((j6 * j16) + (j10 * j13)) * 2) + (j7 * j14)) * 2) + (j11 * j11);
        long j18 = j7 * j16;
        jArr[13] = (j18 + (j10 * j14) + (j11 * j13)) * 2;
        long j19 = j10 * 2 * j16;
        jArr[14] = (j19 + (j11 * j14) + (j13 * j13)) * 2;
        jArr[15] = ((j11 * j16) + (j13 * j14)) * 2;
        jArr[16] = (j13 * 4 * j16) + (j14 * j14);
        jArr[17] = j14 * 2 * j16;
        jArr[18] = 2 * j16 * j16;
    }

    public static void sub(long[] jArr, long[] jArr2) {
        sub(jArr, jArr2, jArr);
    }

    public static void sum(long[] jArr, long[] jArr2) {
        sum(jArr, jArr, jArr2);
    }

    public static void sub(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i2 = 0; i2 < 10; i2++) {
            jArr[i2] = jArr2[i2] - jArr3[i2];
        }
    }

    public static void sum(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i2 = 0; i2 < 10; i2++) {
            jArr[i2] = jArr2[i2] + jArr3[i2];
        }
    }
}
