package com.google.crypto.tink.subtle;

import com.google.crypto.tink.annotations.Alpha;
import java.security.InvalidKeyException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
@Alpha
final class Curve25519 {
    static final byte[][] BANNED_PUBLIC_KEYS = {new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new byte[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new byte[]{-32, -21, 122, 124, 59, 65, -72, -82, 22, 86, -29, -6, -15, -97, -60, 106, -38, 9, -115, -21, -100, 50, -79, -3, -122, 98, 5, 22, 95, 73, -72, 0}, new byte[]{95, -100, -107, -68, -93, 80, -116, 36, -79, -48, -79, 85, -100, -125, -17, 91, 4, 68, 92, -60, 88, 28, -114, -122, -40, 34, 78, -35, -48, -97, 17, 87}, new byte[]{-20, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 127}, new byte[]{-19, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 127}, new byte[]{-18, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 127}};

    private Curve25519() {
    }

    public static void copyConditional(long[] jArr, long[] jArr2, int i2) {
        int i3 = -i2;
        for (int i4 = 0; i4 < 10; i4++) {
            long j2 = jArr[i4];
            jArr[i4] = ((int) j2) ^ ((((int) j2) ^ ((int) jArr2[i4])) & i3);
        }
    }

    public static void curveMult(long[] jArr, byte[] bArr, byte[] bArr2) {
        long[] jArrExpand = Field25519.expand(validatePubKeyAndClearMsb(bArr2));
        long[] jArr2 = new long[19];
        long[] jArr3 = new long[19];
        jArr3[0] = 1;
        long[] jArr4 = new long[19];
        jArr4[0] = 1;
        long[] jArr5 = new long[19];
        long[] jArr6 = new long[19];
        long[] jArr7 = new long[19];
        jArr7[0] = 1;
        long[] jArr8 = new long[19];
        long[] jArr9 = new long[19];
        jArr9[0] = 1;
        System.arraycopy(jArrExpand, 0, jArr2, 0, 10);
        int i2 = 0;
        while (i2 < 32) {
            int i3 = bArr[(32 - i2) - 1] & 255;
            long[] jArr10 = jArr6;
            long[] jArr11 = jArr8;
            long[] jArr12 = jArr9;
            long[] jArr13 = jArr3;
            long[] jArr14 = jArr7;
            long[] jArr15 = jArr2;
            int i4 = 0;
            long[] jArr16 = jArr5;
            long[] jArr17 = jArr4;
            long[] jArr18 = jArr16;
            while (i4 < 8) {
                int i5 = (i3 >> (7 - i4)) & 1;
                swapConditional(jArr17, jArr15, i5);
                swapConditional(jArr18, jArr13, i5);
                long[] jArr19 = jArr14;
                long[] jArr20 = jArr11;
                int i6 = i3;
                long[] jArr21 = jArr10;
                long[] jArr22 = jArr18;
                long[] jArr23 = jArr17;
                long[] jArr24 = jArr13;
                long[] jArr25 = jArr15;
                monty(jArr11, jArr12, jArr10, jArr19, jArr17, jArr18, jArr15, jArr13, jArrExpand);
                swapConditional(jArr20, jArr21, i5);
                swapConditional(jArr12, jArr19, i5);
                i4++;
                jArr13 = jArr19;
                jArr18 = jArr12;
                jArr17 = jArr20;
                jArr15 = jArr21;
                i3 = i6;
                jArr12 = jArr22;
                jArr11 = jArr23;
                jArr14 = jArr24;
                jArr10 = jArr25;
            }
            long[] jArr26 = jArr17;
            long[] jArr27 = jArr13;
            long[] jArr28 = jArr15;
            jArr7 = jArr14;
            i2++;
            jArr9 = jArr12;
            jArr8 = jArr11;
            jArr6 = jArr10;
            jArr5 = jArr18;
            jArr4 = jArr26;
            jArr3 = jArr27;
            jArr2 = jArr28;
        }
        long[] jArr29 = new long[10];
        Field25519.inverse(jArr29, jArr5);
        Field25519.mult(jArr, jArr4, jArr29);
        if (isCollinear(jArrExpand, jArr, jArr2, jArr3)) {
            return;
        }
        throw new IllegalStateException("Arithmetic error in curve multiplication with the public key: " + Hex.encode(bArr2));
    }

    private static boolean isCollinear(long[] jArr, long[] jArr2, long[] jArr3, long[] jArr4) {
        long[] jArr5 = new long[10];
        long[] jArr6 = new long[10];
        long[] jArr7 = new long[11];
        long[] jArr8 = new long[11];
        long[] jArr9 = new long[11];
        Field25519.mult(jArr5, jArr, jArr2);
        Field25519.sum(jArr6, jArr, jArr2);
        long[] jArr10 = new long[10];
        jArr10[0] = 486662;
        Field25519.sum(jArr8, jArr6, jArr10);
        Field25519.mult(jArr8, jArr8, jArr4);
        Field25519.sum(jArr8, jArr3);
        Field25519.mult(jArr8, jArr8, jArr5);
        Field25519.mult(jArr8, jArr8, jArr3);
        Field25519.scalarProduct(jArr7, jArr8, 4L);
        Field25519.reduceCoefficients(jArr7);
        Field25519.mult(jArr8, jArr5, jArr4);
        Field25519.sub(jArr8, jArr8, jArr4);
        Field25519.mult(jArr9, jArr6, jArr3);
        Field25519.sum(jArr8, jArr8, jArr9);
        Field25519.square(jArr8, jArr8);
        return Bytes.equal(Field25519.contract(jArr7), Field25519.contract(jArr8));
    }

    private static void monty(long[] jArr, long[] jArr2, long[] jArr3, long[] jArr4, long[] jArr5, long[] jArr6, long[] jArr7, long[] jArr8, long[] jArr9) {
        long[] jArrCopyOf = Arrays.copyOf(jArr5, 10);
        long[] jArr10 = new long[19];
        long[] jArr11 = new long[19];
        long[] jArr12 = new long[19];
        long[] jArr13 = new long[19];
        long[] jArr14 = new long[19];
        long[] jArr15 = new long[19];
        long[] jArr16 = new long[19];
        Field25519.sum(jArr5, jArr6);
        Field25519.sub(jArr6, jArrCopyOf);
        long[] jArrCopyOf2 = Arrays.copyOf(jArr7, 10);
        Field25519.sum(jArr7, jArr8);
        Field25519.sub(jArr8, jArrCopyOf2);
        Field25519.product(jArr13, jArr7, jArr6);
        Field25519.product(jArr14, jArr5, jArr8);
        Field25519.reduceSizeByModularReduction(jArr13);
        Field25519.reduceCoefficients(jArr13);
        Field25519.reduceSizeByModularReduction(jArr14);
        Field25519.reduceCoefficients(jArr14);
        System.arraycopy(jArr13, 0, jArrCopyOf2, 0, 10);
        Field25519.sum(jArr13, jArr14);
        Field25519.sub(jArr14, jArrCopyOf2);
        Field25519.square(jArr16, jArr13);
        Field25519.square(jArr15, jArr14);
        Field25519.product(jArr14, jArr15, jArr9);
        Field25519.reduceSizeByModularReduction(jArr14);
        Field25519.reduceCoefficients(jArr14);
        System.arraycopy(jArr16, 0, jArr3, 0, 10);
        System.arraycopy(jArr14, 0, jArr4, 0, 10);
        Field25519.square(jArr11, jArr5);
        Field25519.square(jArr12, jArr6);
        Field25519.product(jArr, jArr11, jArr12);
        Field25519.reduceSizeByModularReduction(jArr);
        Field25519.reduceCoefficients(jArr);
        Field25519.sub(jArr12, jArr11);
        Arrays.fill(jArr10, 10, 18, 0L);
        Field25519.scalarProduct(jArr10, jArr12, 121665L);
        Field25519.reduceCoefficients(jArr10);
        Field25519.sum(jArr10, jArr11);
        Field25519.product(jArr2, jArr12, jArr10);
        Field25519.reduceSizeByModularReduction(jArr2);
        Field25519.reduceCoefficients(jArr2);
    }

    public static void swapConditional(long[] jArr, long[] jArr2, int i2) {
        int i3 = -i2;
        for (int i4 = 0; i4 < 10; i4++) {
            long j2 = jArr[i4];
            int i5 = (((int) j2) ^ ((int) jArr2[i4])) & i3;
            jArr[i4] = ((int) j2) ^ i5;
            jArr2[i4] = ((int) jArr2[i4]) ^ i5;
        }
    }

    private static byte[] validatePubKeyAndClearMsb(byte[] bArr) throws InvalidKeyException {
        if (bArr.length != 32) {
            throw new InvalidKeyException("Public key length is not 32-byte");
        }
        byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
        bArrCopyOf[31] = (byte) (bArrCopyOf[31] & 127);
        int i2 = 0;
        while (true) {
            byte[][] bArr2 = BANNED_PUBLIC_KEYS;
            if (i2 >= bArr2.length) {
                return bArrCopyOf;
            }
            if (Bytes.equal(bArr2[i2], bArrCopyOf)) {
                throw new InvalidKeyException("Banned public key: " + Hex.encode(bArr2[i2]));
            }
            i2++;
        }
    }
}
