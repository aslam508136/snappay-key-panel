package com.google.crypto.tink.aead.internal;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

/* JADX INFO: loaded from: classes.dex */
final class ChaCha20Util {
    static final int BLOCK_SIZE_IN_BYTES = 64;
    static final int BLOCK_SIZE_IN_INTS = 16;
    static final int KEY_SIZE_IN_BYTES = 32;
    static final int KEY_SIZE_IN_INTS = 8;
    private static final int[] SIGMA = toIntArray(new byte[]{101, 120, 112, 97, 110, 100, 32, 51, 50, 45, 98, 121, 116, 101, 32, 107});

    private ChaCha20Util() {
    }

    public static void quarterRound(int[] iArr, int i2, int i3, int i4, int i5) {
        int i6 = iArr[i2] + iArr[i3];
        iArr[i2] = i6;
        int iRotateLeft = rotateLeft(i6 ^ iArr[i5], 16);
        iArr[i5] = iRotateLeft;
        int i7 = iArr[i4] + iRotateLeft;
        iArr[i4] = i7;
        int iRotateLeft2 = rotateLeft(iArr[i3] ^ i7, 12);
        iArr[i3] = iRotateLeft2;
        int i8 = iArr[i2] + iRotateLeft2;
        iArr[i2] = i8;
        int iRotateLeft3 = rotateLeft(iArr[i5] ^ i8, 8);
        iArr[i5] = iRotateLeft3;
        int i9 = iArr[i4] + iRotateLeft3;
        iArr[i4] = i9;
        iArr[i3] = rotateLeft(iArr[i3] ^ i9, 7);
    }

    private static int rotateLeft(int i2, int i3) {
        return (i2 >>> (-i3)) | (i2 << i3);
    }

    public static void setSigmaAndKey(int[] iArr, int[] iArr2) {
        int[] iArr3 = SIGMA;
        System.arraycopy(iArr3, 0, iArr, 0, iArr3.length);
        System.arraycopy(iArr2, 0, iArr, iArr3.length, 8);
    }

    public static void shuffleState(int[] iArr) {
        for (int i2 = 0; i2 < 10; i2++) {
            quarterRound(iArr, 0, 4, 8, 12);
            quarterRound(iArr, 1, 5, 9, 13);
            quarterRound(iArr, 2, 6, 10, 14);
            quarterRound(iArr, 3, 7, 11, 15);
            quarterRound(iArr, 0, 5, 10, 15);
            quarterRound(iArr, 1, 6, 11, 12);
            quarterRound(iArr, 2, 7, 8, 13);
            quarterRound(iArr, 3, 4, 9, 14);
        }
    }

    public static int[] toIntArray(byte[] bArr) {
        IntBuffer intBufferAsIntBuffer = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).asIntBuffer();
        int[] iArr = new int[intBufferAsIntBuffer.remaining()];
        intBufferAsIntBuffer.get(iArr);
        return iArr;
    }
}
