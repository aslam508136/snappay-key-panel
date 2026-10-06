package com.google.crypto.tink.subtle;

import java.lang.reflect.Array;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
final class Ed25519Constants {
    static final Ed25519.CachedXYT[] B2;
    static final Ed25519.CachedXYT[][] B_TABLE;
    static final long[] D;
    static final long[] D2;
    private static final BigInteger D2_BI;
    private static final BigInteger D_BI;
    private static final BigInteger P_BI;
    static final long[] SQRTM1;
    private static final BigInteger SQRTM1_BI;

    public static class Point {

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        private BigInteger f525x;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        private BigInteger f526y;

        private Point() {
        }
    }

    static {
        BigInteger bigIntegerSubtract = BigInteger.valueOf(2L).pow(255).subtract(BigInteger.valueOf(19L));
        P_BI = bigIntegerSubtract;
        BigInteger bigIntegerMod = BigInteger.valueOf(-121665L).multiply(BigInteger.valueOf(121666L).modInverse(bigIntegerSubtract)).mod(bigIntegerSubtract);
        D_BI = bigIntegerMod;
        BigInteger bigIntegerMod2 = BigInteger.valueOf(2L).multiply(bigIntegerMod).mod(bigIntegerSubtract);
        D2_BI = bigIntegerMod2;
        BigInteger bigIntegerModPow = BigInteger.valueOf(2L).modPow(bigIntegerSubtract.subtract(BigInteger.ONE).divide(BigInteger.valueOf(4L)), bigIntegerSubtract);
        SQRTM1_BI = bigIntegerModPow;
        Point point = new Point();
        point.f526y = BigInteger.valueOf(4L).multiply(BigInteger.valueOf(5L).modInverse(bigIntegerSubtract)).mod(bigIntegerSubtract);
        point.f525x = recoverX(point.f526y);
        D = Field25519.expand(toLittleEndian(bigIntegerMod));
        D2 = Field25519.expand(toLittleEndian(bigIntegerMod2));
        SQRTM1 = Field25519.expand(toLittleEndian(bigIntegerModPow));
        B_TABLE = (Ed25519.CachedXYT[][]) Array.newInstance((Class<?>) Ed25519.CachedXYT.class, 32, 8);
        Point pointEdwards = point;
        for (int i2 = 0; i2 < 32; i2++) {
            Point pointEdwards2 = pointEdwards;
            for (int i3 = 0; i3 < 8; i3++) {
                B_TABLE[i2][i3] = getCachedXYT(pointEdwards2);
                pointEdwards2 = edwards(pointEdwards2, pointEdwards);
            }
            for (int i4 = 0; i4 < 8; i4++) {
                pointEdwards = edwards(pointEdwards, pointEdwards);
            }
        }
        Point pointEdwards3 = edwards(point, point);
        B2 = new Ed25519.CachedXYT[8];
        for (int i5 = 0; i5 < 8; i5++) {
            B2[i5] = getCachedXYT(point);
            point = edwards(point, pointEdwards3);
        }
    }

    private Ed25519Constants() {
    }

    private static Point edwards(Point point, Point point2) {
        Point point3 = new Point();
        BigInteger bigIntegerMultiply = D_BI.multiply(point.f525x.multiply(point2.f525x).multiply(point.f526y).multiply(point2.f526y));
        BigInteger bigInteger = P_BI;
        BigInteger bigIntegerMod = bigIntegerMultiply.mod(bigInteger);
        BigInteger bigIntegerAdd = point.f525x.multiply(point2.f526y).add(point2.f525x.multiply(point.f526y));
        BigInteger bigInteger2 = BigInteger.ONE;
        point3.f525x = bigIntegerAdd.multiply(bigInteger2.add(bigIntegerMod).modInverse(bigInteger)).mod(bigInteger);
        point3.f526y = point.f526y.multiply(point2.f526y).add(point.f525x.multiply(point2.f525x)).multiply(bigInteger2.subtract(bigIntegerMod).modInverse(bigInteger)).mod(bigInteger);
        return point3;
    }

    private static Ed25519.CachedXYT getCachedXYT(Point point) {
        BigInteger bigIntegerAdd = point.f526y.add(point.f525x);
        BigInteger bigInteger = P_BI;
        return new Ed25519.CachedXYT(Field25519.expand(toLittleEndian(bigIntegerAdd.mod(bigInteger))), Field25519.expand(toLittleEndian(point.f526y.subtract(point.f525x).mod(bigInteger))), Field25519.expand(toLittleEndian(D2_BI.multiply(point.f525x).multiply(point.f526y).mod(bigInteger))));
    }

    private static BigInteger recoverX(BigInteger bigInteger) {
        BigInteger bigIntegerPow = bigInteger.pow(2);
        BigInteger bigInteger2 = BigInteger.ONE;
        BigInteger bigIntegerSubtract = bigIntegerPow.subtract(bigInteger2);
        BigInteger bigIntegerAdd = D_BI.multiply(bigInteger.pow(2)).add(bigInteger2);
        BigInteger bigInteger3 = P_BI;
        BigInteger bigIntegerMultiply = bigIntegerSubtract.multiply(bigIntegerAdd.modInverse(bigInteger3));
        BigInteger bigIntegerModPow = bigIntegerMultiply.modPow(bigInteger3.add(BigInteger.valueOf(3L)).divide(BigInteger.valueOf(8L)), bigInteger3);
        if (!bigIntegerModPow.pow(2).subtract(bigIntegerMultiply).mod(bigInteger3).equals(BigInteger.ZERO)) {
            bigIntegerModPow = bigIntegerModPow.multiply(SQRTM1_BI).mod(bigInteger3);
        }
        return bigIntegerModPow.testBit(0) ? bigInteger3.subtract(bigIntegerModPow) : bigIntegerModPow;
    }

    private static byte[] toLittleEndian(BigInteger bigInteger) {
        byte[] bArr = new byte[32];
        byte[] byteArray = bigInteger.toByteArray();
        System.arraycopy(byteArray, 0, bArr, 32 - byteArray.length, byteArray.length);
        for (int i2 = 0; i2 < 16; i2++) {
            byte b2 = bArr[i2];
            int i3 = (32 - i2) - 1;
            bArr[i2] = bArr[i3];
            bArr[i3] = b2;
        }
        return bArr;
    }
}
