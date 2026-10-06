package com.google.crypto.tink.aead.internal;

import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class Poly1305 {
    public static final int MAC_KEY_SIZE_IN_BYTES = 32;
    public static final int MAC_TAG_SIZE_IN_BYTES = 16;

    private Poly1305() {
    }

    public static byte[] computeMac(byte[] bArr, byte[] bArr2) {
        if (bArr.length != 32) {
            throw new IllegalArgumentException("The key length in bytes must be 32.");
        }
        int i2 = 0;
        long jLoad26 = load26(bArr, 0, 0) & 67108863;
        int i3 = 3;
        long jLoad27 = load26(bArr, 3, 2) & 67108611;
        long jLoad28 = load26(bArr, 6, 4) & 67092735;
        long jLoad29 = load26(bArr, 9, 6) & 66076671;
        long jLoad210 = load26(bArr, 12, 8) & 1048575;
        long j2 = jLoad27 * 5;
        long j3 = jLoad28 * 5;
        long j4 = jLoad29 * 5;
        long j5 = jLoad210 * 5;
        byte[] bArr3 = new byte[17];
        long j6 = 0;
        long j7 = 0;
        long j8 = 0;
        long j9 = 0;
        long j10 = 0;
        int i4 = 0;
        while (i4 < bArr2.length) {
            copyBlockSize(bArr3, bArr2, i4);
            long jLoad211 = j10 + load26(bArr3, i2, i2);
            long jLoad212 = j6 + load26(bArr3, i3, 2);
            long jLoad213 = j7 + load26(bArr3, 6, 4);
            long jLoad214 = j8 + load26(bArr3, 9, 6);
            long jLoad215 = j9 + (load26(bArr3, 12, 8) | ((long) (bArr3[16] << 24)));
            long j11 = (jLoad215 * j2) + (jLoad214 * j3) + (jLoad213 * j4) + (jLoad212 * j5) + (jLoad211 * jLoad26);
            long j12 = (jLoad215 * j3) + (jLoad214 * j4) + (jLoad213 * j5) + (jLoad212 * jLoad26) + (jLoad211 * jLoad27);
            long j13 = (jLoad215 * j4) + (jLoad214 * j5) + (jLoad213 * jLoad26) + (jLoad212 * jLoad27) + (jLoad211 * jLoad28);
            long j14 = (jLoad215 * j5) + (jLoad214 * jLoad26) + (jLoad213 * jLoad27) + (jLoad212 * jLoad28) + (jLoad211 * jLoad29);
            long j15 = jLoad214 * jLoad27;
            long j16 = jLoad215 * jLoad26;
            long j17 = j12 + (j11 >> 26);
            long j18 = j13 + (j17 >> 26);
            long j19 = j14 + (j18 >> 26);
            long j20 = j16 + j15 + (jLoad213 * jLoad28) + (jLoad212 * jLoad29) + (jLoad211 * jLoad210) + (j19 >> 26);
            long j21 = j20 >> 26;
            j9 = j20 & 67108863;
            long j22 = (j21 * 5) + (j11 & 67108863);
            i4 += 16;
            j7 = j18 & 67108863;
            j8 = j19 & 67108863;
            i3 = 3;
            j10 = j22 & 67108863;
            j6 = (j17 & 67108863) + (j22 >> 26);
            i2 = 0;
        }
        long j23 = j7 + (j6 >> 26);
        long j24 = j23 & 67108863;
        long j25 = j8 + (j23 >> 26);
        long j26 = j25 & 67108863;
        long j27 = j9 + (j25 >> 26);
        long j28 = j27 & 67108863;
        long j29 = ((j27 >> 26) * 5) + j10;
        long j30 = j29 >> 26;
        long j31 = j29 & 67108863;
        long j32 = (j6 & 67108863) + j30;
        long j33 = j31 + 5;
        long j34 = j33 & 67108863;
        long j35 = j32 + (j33 >> 26);
        long j36 = j24 + (j35 >> 26);
        long j37 = j26 + (j36 >> 26);
        long j38 = (j28 + (j37 >> 26)) - 67108864;
        long j39 = j38 >> 63;
        long j40 = j31 & j39;
        long j41 = j32 & j39;
        long j42 = j24 & j39;
        long j43 = j26 & j39;
        long j44 = j28 & j39;
        long j45 = ~j39;
        long j46 = j41 | (j35 & 67108863 & j45);
        long j47 = j42 | (j36 & 67108863 & j45);
        long j48 = j43 | (j37 & 67108863 & j45);
        long j49 = (j40 | (j34 & j45) | (j46 << 26)) & 4294967295L;
        long j50 = ((j46 >> 6) | (j47 << 20)) & 4294967295L;
        long j51 = ((j47 >> 12) | (j48 << 14)) & 4294967295L;
        long j52 = ((j48 >> 18) | (((j38 & j45) | j44) << 8)) & 4294967295L;
        long jLoad32 = j49 + load32(bArr, 16);
        long j53 = jLoad32 & 4294967295L;
        long jLoad33 = j50 + load32(bArr, 20) + (jLoad32 >> 32);
        long j54 = jLoad33 & 4294967295L;
        long jLoad34 = j51 + load32(bArr, 24) + (jLoad33 >> 32);
        long j55 = jLoad34 & 4294967295L;
        long jLoad35 = (j52 + load32(bArr, 28) + (jLoad34 >> 32)) & 4294967295L;
        byte[] bArr4 = new byte[16];
        toByteArray(bArr4, j53, 0);
        toByteArray(bArr4, j54, 4);
        toByteArray(bArr4, j55, 8);
        toByteArray(bArr4, jLoad35, 12);
        return bArr4;
    }

    private static void copyBlockSize(byte[] bArr, byte[] bArr2, int i2) {
        int iMin = Math.min(16, bArr2.length - i2);
        System.arraycopy(bArr2, i2, bArr, 0, iMin);
        bArr[iMin] = 1;
        if (iMin != 16) {
            Arrays.fill(bArr, iMin + 1, bArr.length, (byte) 0);
        }
    }

    private static long load26(byte[] bArr, int i2, int i3) {
        return (load32(bArr, i2) >> i3) & 67108863;
    }

    private static long load32(byte[] bArr, int i2) {
        return ((long) (((bArr[i2 + 3] & 255) << 24) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16))) & 4294967295L;
    }

    private static void toByteArray(byte[] bArr, long j2, int i2) {
        int i3 = 0;
        while (i3 < 4) {
            bArr[i2 + i3] = (byte) (255 & j2);
            i3++;
            j2 >>= 8;
        }
    }

    public static void verifyMac(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        if (!Bytes.equal(computeMac(bArr, bArr2), bArr3)) {
            throw new GeneralSecurityException("invalid MAC");
        }
    }
}
