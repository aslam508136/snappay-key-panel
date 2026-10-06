package com.google.crypto.tink.subtle;

import androidx.security.crypto.MasterKey;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
final class Ed25519 {
    public static final int PUBLIC_KEY_LEN = 32;
    public static final int SECRET_KEY_LEN = 32;
    public static final int SIGNATURE_LEN = 64;
    private static final CachedXYT CACHED_NEUTRAL = new CachedXYT(new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new long[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    private static final PartialXYZT NEUTRAL = new PartialXYZT(new XYZ(new long[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}), new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    static final byte[] GROUP_ORDER = {-19, -45, -11, 92, 26, 99, 18, 88, -42, -100, -9, -94, -34, -7, -34, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16};

    public static class CachedXYT {
        final long[] t2d;
        final long[] yMinusX;
        final long[] yPlusX;

        public CachedXYT() {
            this(new long[10], new long[10], new long[10]);
        }

        public void copyConditional(CachedXYT cachedXYT, int i2) {
            Curve25519.copyConditional(this.yPlusX, cachedXYT.yPlusX, i2);
            Curve25519.copyConditional(this.yMinusX, cachedXYT.yMinusX, i2);
            Curve25519.copyConditional(this.t2d, cachedXYT.t2d, i2);
        }

        public void multByZ(long[] jArr, long[] jArr2) {
            System.arraycopy(jArr2, 0, jArr, 0, 10);
        }

        public CachedXYT(CachedXYT cachedXYT) {
            this.yPlusX = Arrays.copyOf(cachedXYT.yPlusX, 10);
            this.yMinusX = Arrays.copyOf(cachedXYT.yMinusX, 10);
            this.t2d = Arrays.copyOf(cachedXYT.t2d, 10);
        }

        public CachedXYT(long[] jArr, long[] jArr2, long[] jArr3) {
            this.yPlusX = jArr;
            this.yMinusX = jArr2;
            this.t2d = jArr3;
        }
    }

    public static class CachedXYZT extends CachedXYT {

        /* JADX INFO: renamed from: z, reason: collision with root package name */
        private final long[] f519z;

        public CachedXYZT() {
            this(new long[10], new long[10], new long[10], new long[10]);
        }

        @Override // com.google.crypto.tink.subtle.Ed25519.CachedXYT
        public void multByZ(long[] jArr, long[] jArr2) {
            Field25519.mult(jArr, jArr2, this.f519z);
        }

        public CachedXYZT(XYZT xyzt) {
            this();
            long[] jArr = this.yPlusX;
            XYZ xyz = xyzt.xyz;
            Field25519.sum(jArr, xyz.f522y, xyz.f521x);
            long[] jArr2 = this.yMinusX;
            XYZ xyz2 = xyzt.xyz;
            Field25519.sub(jArr2, xyz2.f522y, xyz2.f521x);
            System.arraycopy(xyzt.xyz.f523z, 0, this.f519z, 0, 10);
            Field25519.mult(this.t2d, xyzt.f524t, Ed25519Constants.D2);
        }

        public CachedXYZT(long[] jArr, long[] jArr2, long[] jArr3, long[] jArr4) {
            super(jArr, jArr2, jArr4);
            this.f519z = jArr3;
        }
    }

    public static class PartialXYZT {

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        final long[] f520t;
        final XYZ xyz;

        public PartialXYZT() {
            this(new XYZ(), new long[10]);
        }

        public PartialXYZT(PartialXYZT partialXYZT) {
            this.xyz = new XYZ(partialXYZT.xyz);
            this.f520t = Arrays.copyOf(partialXYZT.f520t, 10);
        }

        public PartialXYZT(XYZ xyz, long[] jArr) {
            this.xyz = xyz;
            this.f520t = jArr;
        }
    }

    public static class XYZ {

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        final long[] f521x;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        final long[] f522y;

        /* JADX INFO: renamed from: z, reason: collision with root package name */
        final long[] f523z;

        public XYZ() {
            this(new long[10], new long[10], new long[10]);
        }

        @CanIgnoreReturnValue
        public static XYZ fromPartialXYZT(XYZ xyz, PartialXYZT partialXYZT) {
            Field25519.mult(xyz.f521x, partialXYZT.xyz.f521x, partialXYZT.f520t);
            long[] jArr = xyz.f522y;
            XYZ xyz2 = partialXYZT.xyz;
            Field25519.mult(jArr, xyz2.f522y, xyz2.f523z);
            Field25519.mult(xyz.f523z, partialXYZT.xyz.f523z, partialXYZT.f520t);
            return xyz;
        }

        public boolean isOnCurve() {
            long[] jArr = new long[10];
            Field25519.square(jArr, this.f521x);
            long[] jArr2 = new long[10];
            Field25519.square(jArr2, this.f522y);
            long[] jArr3 = new long[10];
            Field25519.square(jArr3, this.f523z);
            long[] jArr4 = new long[10];
            Field25519.square(jArr4, jArr3);
            long[] jArr5 = new long[10];
            Field25519.sub(jArr5, jArr2, jArr);
            Field25519.mult(jArr5, jArr5, jArr3);
            long[] jArr6 = new long[10];
            Field25519.mult(jArr6, jArr, jArr2);
            Field25519.mult(jArr6, jArr6, Ed25519Constants.D);
            Field25519.sum(jArr6, jArr4);
            Field25519.reduce(jArr6, jArr6);
            return Bytes.equal(Field25519.contract(jArr5), Field25519.contract(jArr6));
        }

        public byte[] toBytes() {
            long[] jArr = new long[10];
            long[] jArr2 = new long[10];
            long[] jArr3 = new long[10];
            Field25519.inverse(jArr, this.f523z);
            Field25519.mult(jArr2, this.f521x, jArr);
            Field25519.mult(jArr3, this.f522y, jArr);
            byte[] bArrContract = Field25519.contract(jArr3);
            bArrContract[31] = (byte) ((Ed25519.getLsb(jArr2) << 7) ^ bArrContract[31]);
            return bArrContract;
        }

        public XYZ(PartialXYZT partialXYZT) {
            this();
            fromPartialXYZT(this, partialXYZT);
        }

        public XYZ(XYZ xyz) {
            this.f521x = Arrays.copyOf(xyz.f521x, 10);
            this.f522y = Arrays.copyOf(xyz.f522y, 10);
            this.f523z = Arrays.copyOf(xyz.f523z, 10);
        }

        public XYZ(long[] jArr, long[] jArr2, long[] jArr3) {
            this.f521x = jArr;
            this.f522y = jArr2;
            this.f523z = jArr3;
        }
    }

    public static class XYZT {

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        final long[] f524t;
        final XYZ xyz;

        public XYZT() {
            this(new XYZ(), new long[10]);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static XYZT fromBytesNegateVarTime(byte[] bArr) throws GeneralSecurityException {
            long[] jArr = new long[10];
            long[] jArrExpand = Field25519.expand(bArr);
            long[] jArr2 = new long[10];
            jArr2[0] = 1;
            long[] jArr3 = new long[10];
            long[] jArr4 = new long[10];
            long[] jArr5 = new long[10];
            long[] jArr6 = new long[10];
            long[] jArr7 = new long[10];
            Field25519.square(jArr4, jArrExpand);
            Field25519.mult(jArr5, jArr4, Ed25519Constants.D);
            Field25519.sub(jArr4, jArr4, jArr2);
            Field25519.sum(jArr5, jArr5, jArr2);
            long[] jArr8 = new long[10];
            Field25519.square(jArr8, jArr5);
            Field25519.mult(jArr8, jArr8, jArr5);
            Field25519.square(jArr, jArr8);
            Field25519.mult(jArr, jArr, jArr5);
            Field25519.mult(jArr, jArr, jArr4);
            Ed25519.pow2252m3(jArr, jArr);
            Field25519.mult(jArr, jArr, jArr8);
            Field25519.mult(jArr, jArr, jArr4);
            Field25519.square(jArr6, jArr);
            Field25519.mult(jArr6, jArr6, jArr5);
            Field25519.sub(jArr7, jArr6, jArr4);
            if (Ed25519.isNonZeroVarTime(jArr7)) {
                Field25519.sum(jArr7, jArr6, jArr4);
                if (Ed25519.isNonZeroVarTime(jArr7)) {
                    throw new GeneralSecurityException("Cannot convert given bytes to extended projective coordinates. No square root exists for modulo 2^255-19");
                }
                Field25519.mult(jArr, jArr, Ed25519Constants.SQRTM1);
            }
            if (!Ed25519.isNonZeroVarTime(jArr) && ((bArr[31] & 255) >> 7) != 0) {
                throw new GeneralSecurityException("Cannot convert given bytes to extended projective coordinates. Computed x is zero and encoded x's least significant bit is not zero");
            }
            if (Ed25519.getLsb(jArr) == ((bArr[31] & 255) >> 7)) {
                Ed25519.neg(jArr, jArr);
            }
            Field25519.mult(jArr3, jArr, jArrExpand);
            return new XYZT(new XYZ(jArr, jArrExpand, jArr2), jArr3);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @CanIgnoreReturnValue
        public static XYZT fromPartialXYZT(XYZT xyzt, PartialXYZT partialXYZT) {
            Field25519.mult(xyzt.xyz.f521x, partialXYZT.xyz.f521x, partialXYZT.f520t);
            long[] jArr = xyzt.xyz.f522y;
            XYZ xyz = partialXYZT.xyz;
            Field25519.mult(jArr, xyz.f522y, xyz.f523z);
            Field25519.mult(xyzt.xyz.f523z, partialXYZT.xyz.f523z, partialXYZT.f520t);
            long[] jArr2 = xyzt.f524t;
            XYZ xyz2 = partialXYZT.xyz;
            Field25519.mult(jArr2, xyz2.f521x, xyz2.f522y);
            return xyzt;
        }

        public XYZT(PartialXYZT partialXYZT) {
            this();
            fromPartialXYZT(this, partialXYZT);
        }

        public XYZT(XYZ xyz, long[] jArr) {
            this.xyz = xyz;
            this.f524t = jArr;
        }
    }

    private Ed25519() {
    }

    private static void add(PartialXYZT partialXYZT, XYZT xyzt, CachedXYT cachedXYT) {
        long[] jArr = new long[10];
        long[] jArr2 = partialXYZT.xyz.f521x;
        XYZ xyz = xyzt.xyz;
        Field25519.sum(jArr2, xyz.f522y, xyz.f521x);
        long[] jArr3 = partialXYZT.xyz.f522y;
        XYZ xyz2 = xyzt.xyz;
        Field25519.sub(jArr3, xyz2.f522y, xyz2.f521x);
        long[] jArr4 = partialXYZT.xyz.f522y;
        Field25519.mult(jArr4, jArr4, cachedXYT.yMinusX);
        XYZ xyz3 = partialXYZT.xyz;
        Field25519.mult(xyz3.f523z, xyz3.f521x, cachedXYT.yPlusX);
        Field25519.mult(partialXYZT.f520t, xyzt.f524t, cachedXYT.t2d);
        cachedXYT.multByZ(partialXYZT.xyz.f521x, xyzt.xyz.f523z);
        long[] jArr5 = partialXYZT.xyz.f521x;
        Field25519.sum(jArr, jArr5, jArr5);
        XYZ xyz4 = partialXYZT.xyz;
        Field25519.sub(xyz4.f521x, xyz4.f523z, xyz4.f522y);
        XYZ xyz5 = partialXYZT.xyz;
        long[] jArr6 = xyz5.f522y;
        Field25519.sum(jArr6, xyz5.f523z, jArr6);
        Field25519.sum(partialXYZT.xyz.f523z, jArr, partialXYZT.f520t);
        long[] jArr7 = partialXYZT.f520t;
        Field25519.sub(jArr7, jArr, jArr7);
    }

    private static XYZ doubleScalarMultVarTime(byte[] bArr, XYZT xyzt, byte[] bArr2) {
        CachedXYZT[] cachedXYZTArr = new CachedXYZT[8];
        cachedXYZTArr[0] = new CachedXYZT(xyzt);
        PartialXYZT partialXYZT = new PartialXYZT();
        doubleXYZT(partialXYZT, xyzt);
        XYZT xyzt2 = new XYZT(partialXYZT);
        for (int i2 = 1; i2 < 8; i2++) {
            add(partialXYZT, xyzt2, cachedXYZTArr[i2 - 1]);
            cachedXYZTArr[i2] = new CachedXYZT(new XYZT(partialXYZT));
        }
        byte[] bArrSlide = slide(bArr);
        byte[] bArrSlide2 = slide(bArr2);
        PartialXYZT partialXYZT2 = new PartialXYZT(NEUTRAL);
        XYZT xyzt3 = new XYZT();
        int i3 = 255;
        while (i3 >= 0 && bArrSlide[i3] == 0 && bArrSlide2[i3] == 0) {
            i3--;
        }
        while (i3 >= 0) {
            doubleXYZ(partialXYZT2, new XYZ(partialXYZT2));
            byte b2 = bArrSlide[i3];
            if (b2 > 0) {
                add(partialXYZT2, XYZT.fromPartialXYZT(xyzt3, partialXYZT2), cachedXYZTArr[bArrSlide[i3] / 2]);
            } else if (b2 < 0) {
                sub(partialXYZT2, XYZT.fromPartialXYZT(xyzt3, partialXYZT2), cachedXYZTArr[(-bArrSlide[i3]) / 2]);
            }
            byte b3 = bArrSlide2[i3];
            if (b3 > 0) {
                add(partialXYZT2, XYZT.fromPartialXYZT(xyzt3, partialXYZT2), Ed25519Constants.B2[bArrSlide2[i3] / 2]);
            } else if (b3 < 0) {
                sub(partialXYZT2, XYZT.fromPartialXYZT(xyzt3, partialXYZT2), Ed25519Constants.B2[(-bArrSlide2[i3]) / 2]);
            }
            i3--;
        }
        return new XYZ(partialXYZT2);
    }

    private static void doubleXYZ(PartialXYZT partialXYZT, XYZ xyz) {
        long[] jArr = new long[10];
        Field25519.square(partialXYZT.xyz.f521x, xyz.f521x);
        Field25519.square(partialXYZT.xyz.f523z, xyz.f522y);
        Field25519.square(partialXYZT.f520t, xyz.f523z);
        long[] jArr2 = partialXYZT.f520t;
        Field25519.sum(jArr2, jArr2, jArr2);
        Field25519.sum(partialXYZT.xyz.f522y, xyz.f521x, xyz.f522y);
        Field25519.square(jArr, partialXYZT.xyz.f522y);
        XYZ xyz2 = partialXYZT.xyz;
        Field25519.sum(xyz2.f522y, xyz2.f523z, xyz2.f521x);
        XYZ xyz3 = partialXYZT.xyz;
        long[] jArr3 = xyz3.f523z;
        Field25519.sub(jArr3, jArr3, xyz3.f521x);
        XYZ xyz4 = partialXYZT.xyz;
        Field25519.sub(xyz4.f521x, jArr, xyz4.f522y);
        long[] jArr4 = partialXYZT.f520t;
        Field25519.sub(jArr4, jArr4, partialXYZT.xyz.f523z);
    }

    private static void doubleXYZT(PartialXYZT partialXYZT, XYZT xyzt) {
        doubleXYZ(partialXYZT, xyzt.xyz);
    }

    private static int eq(int i2, int i3) {
        int i4 = (~(i2 ^ i3)) & 255;
        int i5 = i4 & (i4 << 4);
        int i6 = i5 & (i5 << 2);
        return ((i6 & (i6 << 1)) >> 7) & 1;
    }

    public static byte[] getHashedScalar(byte[] bArr) {
        MessageDigest engineFactory = EngineFactory.MESSAGE_DIGEST.getInstance("SHA-512");
        engineFactory.update(bArr, 0, 32);
        byte[] bArrDigest = engineFactory.digest();
        bArrDigest[0] = (byte) (bArrDigest[0] & 248);
        byte b2 = (byte) (bArrDigest[31] & 127);
        bArrDigest[31] = b2;
        bArrDigest[31] = (byte) (b2 | 64);
        return bArrDigest;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getLsb(long[] jArr) {
        return Field25519.contract(jArr)[0] & 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isNonZeroVarTime(long[] jArr) {
        long[] jArr2 = new long[jArr.length + 1];
        System.arraycopy(jArr, 0, jArr2, 0, jArr.length);
        Field25519.reduceCoefficients(jArr2);
        for (byte b2 : Field25519.contract(jArr2)) {
            if (b2 != 0) {
                return true;
            }
        }
        return false;
    }

    private static boolean isSmallerThanGroupOrder(byte[] bArr) {
        for (int i2 = 31; i2 >= 0; i2--) {
            int i3 = bArr[i2] & 255;
            int i4 = GROUP_ORDER[i2] & 255;
            if (i3 != i4) {
                return i3 < i4;
            }
        }
        return false;
    }

    private static long load3(byte[] bArr, int i2) {
        return (((long) (bArr[i2 + 2] & 255)) << 16) | (((long) bArr[i2]) & 255) | (((long) (bArr[i2 + 1] & 255)) << 8);
    }

    private static long load4(byte[] bArr, int i2) {
        return (((long) (bArr[i2 + 3] & 255)) << 24) | load3(bArr, i2);
    }

    private static void mulAdd(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        long jLoad3 = load3(bArr2, 0) & 2097151;
        long jLoad4 = (load4(bArr2, 2) >> 5) & 2097151;
        long jLoad5 = (load3(bArr2, 5) >> 2) & 2097151;
        long jLoad6 = (load4(bArr2, 7) >> 7) & 2097151;
        long jLoad7 = (load4(bArr2, 10) >> 4) & 2097151;
        long jLoad8 = (load3(bArr2, 13) >> 1) & 2097151;
        long jLoad9 = (load4(bArr2, 15) >> 6) & 2097151;
        long jLoad10 = (load3(bArr2, 18) >> 3) & 2097151;
        long jLoad11 = load3(bArr2, 21) & 2097151;
        long jLoad12 = (load4(bArr2, 23) >> 5) & 2097151;
        long jLoad13 = (load3(bArr2, 26) >> 2) & 2097151;
        long jLoad14 = load4(bArr2, 28) >> 7;
        long jLoad15 = load3(bArr3, 0) & 2097151;
        long jLoad16 = (load4(bArr3, 2) >> 5) & 2097151;
        long jLoad17 = (load3(bArr3, 5) >> 2) & 2097151;
        long jLoad18 = (load4(bArr3, 7) >> 7) & 2097151;
        long jLoad19 = (load4(bArr3, 10) >> 4) & 2097151;
        long jLoad20 = (load3(bArr3, 13) >> 1) & 2097151;
        long jLoad21 = (load4(bArr3, 15) >> 6) & 2097151;
        long jLoad22 = (load3(bArr3, 18) >> 3) & 2097151;
        long jLoad23 = load3(bArr3, 21) & 2097151;
        long jLoad24 = (load4(bArr3, 23) >> 5) & 2097151;
        long jLoad25 = (load3(bArr3, 26) >> 2) & 2097151;
        long jLoad26 = load4(bArr3, 28) >> 7;
        long jLoad27 = load3(bArr4, 0) & 2097151;
        long jLoad28 = (load4(bArr4, 2) >> 5) & 2097151;
        long jLoad29 = (load3(bArr4, 5) >> 2) & 2097151;
        long jLoad30 = (load4(bArr4, 7) >> 7) & 2097151;
        long jLoad31 = (load4(bArr4, 10) >> 4) & 2097151;
        long jLoad32 = (load3(bArr4, 13) >> 1) & 2097151;
        long jLoad33 = (load4(bArr4, 15) >> 6) & 2097151;
        long jLoad34 = (load3(bArr4, 18) >> 3) & 2097151;
        long jLoad35 = load3(bArr4, 21) & 2097151;
        long j2 = (jLoad3 * jLoad15) + jLoad27;
        long j3 = (jLoad4 * jLoad15) + (jLoad3 * jLoad16) + jLoad28;
        long j4 = (jLoad5 * jLoad15) + (jLoad4 * jLoad16) + (jLoad3 * jLoad17) + jLoad29;
        long j5 = (jLoad6 * jLoad15) + (jLoad5 * jLoad16) + (jLoad4 * jLoad17) + (jLoad3 * jLoad18) + jLoad30;
        long j6 = (jLoad7 * jLoad15) + (jLoad6 * jLoad16) + (jLoad5 * jLoad17) + (jLoad4 * jLoad18) + (jLoad3 * jLoad19) + jLoad31;
        long j7 = (jLoad8 * jLoad15) + (jLoad7 * jLoad16) + (jLoad6 * jLoad17) + (jLoad5 * jLoad18) + (jLoad4 * jLoad19) + (jLoad3 * jLoad20) + jLoad32;
        long j8 = (jLoad9 * jLoad15) + (jLoad8 * jLoad16) + (jLoad7 * jLoad17) + (jLoad6 * jLoad18) + (jLoad5 * jLoad19) + (jLoad4 * jLoad20) + (jLoad3 * jLoad21) + jLoad33;
        long j9 = (jLoad10 * jLoad15) + (jLoad9 * jLoad16) + (jLoad8 * jLoad17) + (jLoad7 * jLoad18) + (jLoad6 * jLoad19) + (jLoad5 * jLoad20) + (jLoad4 * jLoad21) + (jLoad3 * jLoad22) + jLoad34;
        long j10 = (jLoad11 * jLoad15) + (jLoad10 * jLoad16) + (jLoad9 * jLoad17) + (jLoad8 * jLoad18) + (jLoad7 * jLoad19) + (jLoad6 * jLoad20) + (jLoad5 * jLoad21) + (jLoad4 * jLoad22) + (jLoad3 * jLoad23) + jLoad35;
        long jLoad36 = (jLoad12 * jLoad15) + (jLoad11 * jLoad16) + (jLoad10 * jLoad17) + (jLoad9 * jLoad18) + (jLoad8 * jLoad19) + (jLoad7 * jLoad20) + (jLoad6 * jLoad21) + (jLoad5 * jLoad22) + (jLoad4 * jLoad23) + (jLoad3 * jLoad24) + ((load4(bArr4, 23) >> 5) & 2097151);
        long jLoad37 = (jLoad13 * jLoad15) + (jLoad12 * jLoad16) + (jLoad11 * jLoad17) + (jLoad10 * jLoad18) + (jLoad9 * jLoad19) + (jLoad8 * jLoad20) + (jLoad7 * jLoad21) + (jLoad6 * jLoad22) + (jLoad5 * jLoad23) + (jLoad4 * jLoad24) + (jLoad3 * jLoad25) + ((load3(bArr4, 26) >> 2) & 2097151);
        long jLoad38 = (jLoad15 * jLoad14) + (jLoad13 * jLoad16) + (jLoad12 * jLoad17) + (jLoad11 * jLoad18) + (jLoad10 * jLoad19) + (jLoad9 * jLoad20) + (jLoad8 * jLoad21) + (jLoad7 * jLoad22) + (jLoad6 * jLoad23) + (jLoad5 * jLoad24) + (jLoad4 * jLoad25) + (jLoad3 * jLoad26) + (load4(bArr4, 28) >> 7);
        long j11 = jLoad16 * jLoad14;
        long j12 = j11 + (jLoad13 * jLoad17) + (jLoad12 * jLoad18) + (jLoad11 * jLoad19) + (jLoad10 * jLoad20) + (jLoad9 * jLoad21) + (jLoad8 * jLoad22) + (jLoad7 * jLoad23) + (jLoad6 * jLoad24) + (jLoad5 * jLoad25) + (jLoad4 * jLoad26);
        long j13 = jLoad17 * jLoad14;
        long j14 = j13 + (jLoad13 * jLoad18) + (jLoad12 * jLoad19) + (jLoad11 * jLoad20) + (jLoad10 * jLoad21) + (jLoad9 * jLoad22) + (jLoad8 * jLoad23) + (jLoad7 * jLoad24) + (jLoad6 * jLoad25) + (jLoad5 * jLoad26);
        long j15 = jLoad18 * jLoad14;
        long j16 = j15 + (jLoad13 * jLoad19) + (jLoad12 * jLoad20) + (jLoad11 * jLoad21) + (jLoad10 * jLoad22) + (jLoad9 * jLoad23) + (jLoad8 * jLoad24) + (jLoad7 * jLoad25) + (jLoad6 * jLoad26);
        long j17 = jLoad19 * jLoad14;
        long j18 = j17 + (jLoad13 * jLoad20) + (jLoad12 * jLoad21) + (jLoad11 * jLoad22) + (jLoad10 * jLoad23) + (jLoad9 * jLoad24) + (jLoad8 * jLoad25) + (jLoad7 * jLoad26);
        long j19 = jLoad20 * jLoad14;
        long j20 = j19 + (jLoad13 * jLoad21) + (jLoad12 * jLoad22) + (jLoad11 * jLoad23) + (jLoad10 * jLoad24) + (jLoad9 * jLoad25) + (jLoad8 * jLoad26);
        long j21 = jLoad21 * jLoad14;
        long j22 = j21 + (jLoad13 * jLoad22) + (jLoad12 * jLoad23) + (jLoad11 * jLoad24) + (jLoad10 * jLoad25) + (jLoad9 * jLoad26);
        long j23 = jLoad22 * jLoad14;
        long j24 = j23 + (jLoad13 * jLoad23) + (jLoad12 * jLoad24) + (jLoad11 * jLoad25) + (jLoad10 * jLoad26);
        long j25 = jLoad23 * jLoad14;
        long j26 = j25 + (jLoad13 * jLoad24) + (jLoad12 * jLoad25) + (jLoad11 * jLoad26);
        long j27 = (jLoad24 * jLoad14) + (jLoad13 * jLoad25) + (jLoad12 * jLoad26);
        long j28 = jLoad25 * jLoad14;
        long j29 = jLoad14 * jLoad26;
        long j30 = (j2 + 1048576) >> 21;
        long j31 = j3 + j30;
        long j32 = j2 - (j30 << 21);
        long j33 = (j4 + 1048576) >> 21;
        long j34 = j5 + j33;
        long j35 = j4 - (j33 << 21);
        long j36 = (j6 + 1048576) >> 21;
        long j37 = j7 + j36;
        long j38 = j6 - (j36 << 21);
        long j39 = (j8 + 1048576) >> 21;
        long j40 = j9 + j39;
        long j41 = j8 - (j39 << 21);
        long j42 = (j10 + 1048576) >> 21;
        long j43 = jLoad36 + j42;
        long j44 = j10 - (j42 << 21);
        long j45 = (jLoad37 + 1048576) >> 21;
        long j46 = jLoad38 + j45;
        long j47 = jLoad37 - (j45 << 21);
        long j48 = (j12 + 1048576) >> 21;
        long j49 = j14 + j48;
        long j50 = j12 - (j48 << 21);
        long j51 = (j16 + 1048576) >> 21;
        long j52 = j18 + j51;
        long j53 = j16 - (j51 << 21);
        long j54 = (j20 + 1048576) >> 21;
        long j55 = j22 + j54;
        long j56 = j20 - (j54 << 21);
        long j57 = (j24 + 1048576) >> 21;
        long j58 = j26 + j57;
        long j59 = j24 - (j57 << 21);
        long j60 = (j27 + 1048576) >> 21;
        long j61 = j28 + (jLoad13 * jLoad26) + j60;
        long j62 = j27 - (j60 << 21);
        long j63 = (j29 + 1048576) >> 21;
        long j64 = 0 + j63;
        long j65 = j29 - (j63 << 21);
        long j66 = (j31 + 1048576) >> 21;
        long j67 = j35 + j66;
        long j68 = j31 - (j66 << 21);
        long j69 = (j34 + 1048576) >> 21;
        long j70 = j38 + j69;
        long j71 = j34 - (j69 << 21);
        long j72 = (j37 + 1048576) >> 21;
        long j73 = j41 + j72;
        long j74 = j37 - (j72 << 21);
        long j75 = (j40 + 1048576) >> 21;
        long j76 = j44 + j75;
        long j77 = j40 - (j75 << 21);
        long j78 = (j43 + 1048576) >> 21;
        long j79 = j47 + j78;
        long j80 = j43 - (j78 << 21);
        long j81 = (j46 + 1048576) >> 21;
        long j82 = j50 + j81;
        long j83 = j46 - (j81 << 21);
        long j84 = (j49 + 1048576) >> 21;
        long j85 = j53 + j84;
        long j86 = j49 - (j84 << 21);
        long j87 = (j52 + 1048576) >> 21;
        long j88 = j56 + j87;
        long j89 = j52 - (j87 << 21);
        long j90 = (j55 + 1048576) >> 21;
        long j91 = j59 + j90;
        long j92 = j55 - (j90 << 21);
        long j93 = (j58 + 1048576) >> 21;
        long j94 = j62 + j93;
        long j95 = j58 - (j93 << 21);
        long j96 = (j61 + 1048576) >> 21;
        long j97 = j65 + j96;
        long j98 = j61 - (j96 << 21);
        long j99 = j88 - (j64 * 683901);
        long j100 = (j97 * 470296) + (j64 * 666643) + j83;
        long j101 = (j97 * 654183) + (j64 * 470296) + j82;
        long j102 = ((j64 * 654183) + j86) - (j97 * 997805);
        long j103 = (j97 * 136657) + (j85 - (j64 * 997805));
        long j104 = ((j64 * 136657) + j89) - (j97 * 683901);
        long j105 = (j98 * 654183) + j100;
        long j106 = (j98 * 136657) + j102;
        long j107 = j103 - (j98 * 683901);
        long j108 = (j94 * 654183) + (j98 * 470296) + (j97 * 666643) + j79;
        long j109 = (j95 * 654183) + (j94 * 470296) + (j98 * 666643) + j80;
        long j110 = (j95 * 136657) + (j105 - (j94 * 997805));
        long j111 = ((j94 * 136657) + (j101 - (j98 * 997805))) - (j95 * 683901);
        long j112 = (j91 * 666643) + j73;
        long j113 = (j91 * 654183) + (j95 * 470296) + (j94 * 666643) + j76;
        long j114 = (j91 * 136657) + (j108 - (j95 * 997805));
        long j115 = (j112 + 1048576) >> 21;
        long j116 = (j91 * 470296) + (j95 * 666643) + j77 + j115;
        long j117 = j112 - (j115 << 21);
        long j118 = (j113 + 1048576) >> 21;
        long j119 = (j109 - (j91 * 997805)) + j118;
        long j120 = j113 - (j118 << 21);
        long j121 = (j114 + 1048576) >> 21;
        long j122 = (j110 - (j91 * 683901)) + j121;
        long j123 = j114 - (j121 << 21);
        long j124 = (j111 + 1048576) >> 21;
        long j125 = (j106 - (j94 * 683901)) + j124;
        long j126 = j111 - (j124 << 21);
        long j127 = (j107 + 1048576) >> 21;
        long j128 = j104 + j127;
        long j129 = j107 - (j127 << 21);
        long j130 = (j99 + 1048576) >> 21;
        long j131 = j92 + j130;
        long j132 = j99 - (j130 << 21);
        long j133 = (j116 + 1048576) >> 21;
        long j134 = j120 + j133;
        long j135 = j116 - (j133 << 21);
        long j136 = (j119 + 1048576) >> 21;
        long j137 = j123 + j136;
        long j138 = j119 - (j136 << 21);
        long j139 = (j122 + 1048576) >> 21;
        long j140 = j126 + j139;
        long j141 = j122 - (j139 << 21);
        long j142 = (j125 + 1048576) >> 21;
        long j143 = j129 + j142;
        long j144 = j125 - (j142 << 21);
        long j145 = (j128 + 1048576) >> 21;
        long j146 = j132 + j145;
        long j147 = j128 - (j145 << 21);
        long j148 = (j131 * 470296) + j117;
        long j149 = (j131 * 654183) + j135;
        long j150 = j137 - (j131 * 683901);
        long j151 = (j146 * 470296) + (j131 * 666643) + j74;
        long j152 = (j146 * 654183) + j148;
        long j153 = j149 - (j146 * 997805);
        long j154 = (j146 * 136657) + (j134 - (j131 * 997805));
        long j155 = ((j131 * 136657) + j138) - (j146 * 683901);
        long j156 = (j147 * 470296) + (j146 * 666643) + j70;
        long j157 = (j147 * 654183) + j151;
        long j158 = j152 - (j147 * 997805);
        long j159 = (j147 * 136657) + j153;
        long j160 = j154 - (j147 * 683901);
        long j161 = (j143 * 666643) + j67;
        long j162 = (j143 * 470296) + (j147 * 666643) + j71;
        long j163 = (j143 * 654183) + j156;
        long j164 = j157 - (j143 * 997805);
        long j165 = (j143 * 136657) + j158;
        long j166 = j159 - (j143 * 683901);
        long j167 = (j144 * 666643) + j68;
        long j168 = (j144 * 470296) + j161;
        long j169 = (j144 * 654183) + j162;
        long j170 = j163 - (j144 * 997805);
        long j171 = (j144 * 136657) + j164;
        long j172 = j165 - (j144 * 683901);
        long j173 = (j140 * 666643) + j32;
        long j174 = (j140 * 470296) + j167;
        long j175 = (j140 * 654183) + j168;
        long j176 = (j140 * 136657) + j170;
        long j177 = (j173 + 1048576) >> 21;
        long j178 = j174 + j177;
        long j179 = j173 - (j177 << 21);
        long j180 = (j175 + 1048576) >> 21;
        long j181 = (j169 - (j140 * 997805)) + j180;
        long j182 = j175 - (j180 << 21);
        long j183 = (j176 + 1048576) >> 21;
        long j184 = (j171 - (j140 * 683901)) + j183;
        long j185 = j176 - (j183 << 21);
        long j186 = (j172 + 1048576) >> 21;
        long j187 = j166 + j186;
        long j188 = j172 - (j186 << 21);
        long j189 = (j160 + 1048576) >> 21;
        long j190 = j155 + j189;
        long j191 = j160 - (j189 << 21);
        long j192 = (j150 + 1048576) >> 21;
        long j193 = j141 + j192;
        long j194 = j150 - (j192 << 21);
        long j195 = (j178 + 1048576) >> 21;
        long j196 = j182 + j195;
        long j197 = j178 - (j195 << 21);
        long j198 = (j181 + 1048576) >> 21;
        long j199 = j185 + j198;
        long j200 = j181 - (j198 << 21);
        long j201 = (j184 + 1048576) >> 21;
        long j202 = j188 + j201;
        long j203 = j184 - (j201 << 21);
        long j204 = (j187 + 1048576) >> 21;
        long j205 = j191 + j204;
        long j206 = j187 - (j204 << 21);
        long j207 = (j190 + 1048576) >> 21;
        long j208 = j194 + j207;
        long j209 = j190 - (j207 << 21);
        long j210 = (j193 + 1048576) >> 21;
        long j211 = 0 + j210;
        long j212 = j193 - (j210 << 21);
        long j213 = (j211 * 666643) + j179;
        long j214 = (j211 * 470296) + j197;
        long j215 = (j211 * 654183) + j196;
        long j216 = (j211 * 136657) + j199;
        long j217 = j213 >> 21;
        long j218 = j214 + j217;
        long j219 = j213 - (j217 << 21);
        long j220 = j218 >> 21;
        long j221 = j215 + j220;
        long j222 = j218 - (j220 << 21);
        long j223 = j221 >> 21;
        long j224 = (j200 - (j211 * 997805)) + j223;
        long j225 = j221 - (j223 << 21);
        long j226 = j224 >> 21;
        long j227 = j216 + j226;
        long j228 = j224 - (j226 << 21);
        long j229 = j227 >> 21;
        long j230 = (j203 - (j211 * 683901)) + j229;
        long j231 = j227 - (j229 << 21);
        long j232 = j230 >> 21;
        long j233 = j202 + j232;
        long j234 = j230 - (j232 << 21);
        long j235 = j233 >> 21;
        long j236 = j206 + j235;
        long j237 = j233 - (j235 << 21);
        long j238 = j236 >> 21;
        long j239 = j205 + j238;
        long j240 = j236 - (j238 << 21);
        long j241 = j239 >> 21;
        long j242 = j209 + j241;
        long j243 = j239 - (j241 << 21);
        long j244 = j242 >> 21;
        long j245 = j208 + j244;
        long j246 = j242 - (j244 << 21);
        long j247 = j245 >> 21;
        long j248 = j212 + j247;
        long j249 = j245 - (j247 << 21);
        long j250 = j248 >> 21;
        long j251 = j250 + 0;
        long j252 = (666643 * j251) + j219;
        long j253 = (470296 * j251) + j222;
        long j254 = j252 >> 21;
        long j255 = j253 + j254;
        long j256 = j252 - (j254 << 21);
        long j257 = j255 >> 21;
        long j258 = (654183 * j251) + j225 + j257;
        long j259 = j255 - (j257 << 21);
        long j260 = j258 >> 21;
        long j261 = (j228 - (997805 * j251)) + j260;
        long j262 = j258 - (j260 << 21);
        long j263 = j261 >> 21;
        long j264 = (136657 * j251) + j231 + j263;
        long j265 = j261 - (j263 << 21);
        long j266 = j264 >> 21;
        long j267 = (j234 - (j251 * 683901)) + j266;
        long j268 = j264 - (j266 << 21);
        long j269 = j267 >> 21;
        long j270 = j237 + j269;
        long j271 = j267 - (j269 << 21);
        long j272 = j270 >> 21;
        long j273 = j240 + j272;
        long j274 = j270 - (j272 << 21);
        long j275 = j273 >> 21;
        long j276 = j243 + j275;
        long j277 = j273 - (j275 << 21);
        long j278 = j276 >> 21;
        long j279 = j246 + j278;
        long j280 = j276 - (j278 << 21);
        long j281 = j279 >> 21;
        long j282 = j249 + j281;
        long j283 = j279 - (j281 << 21);
        long j284 = j282 >> 21;
        long j285 = (j248 - (j250 << 21)) + j284;
        long j286 = j282 - (j284 << 21);
        bArr[0] = (byte) j256;
        bArr[1] = (byte) (j256 >> 8);
        bArr[2] = (byte) ((j256 >> 16) | (j259 << 5));
        bArr[3] = (byte) (j259 >> 3);
        bArr[4] = (byte) (j259 >> 11);
        bArr[5] = (byte) ((j259 >> 19) | (j262 << 2));
        bArr[6] = (byte) (j262 >> 6);
        bArr[7] = (byte) ((j262 >> 14) | (j265 << 7));
        bArr[8] = (byte) (j265 >> 1);
        bArr[9] = (byte) (j265 >> 9);
        bArr[10] = (byte) ((j265 >> 17) | (j268 << 4));
        bArr[11] = (byte) (j268 >> 4);
        bArr[12] = (byte) (j268 >> 12);
        bArr[13] = (byte) ((j268 >> 20) | (j271 << 1));
        bArr[14] = (byte) (j271 >> 7);
        bArr[15] = (byte) ((j271 >> 15) | (j274 << 6));
        bArr[16] = (byte) (j274 >> 2);
        bArr[17] = (byte) (j274 >> 10);
        bArr[18] = (byte) ((j274 >> 18) | (j277 << 3));
        bArr[19] = (byte) (j277 >> 5);
        bArr[20] = (byte) (j277 >> 13);
        bArr[21] = (byte) j280;
        bArr[22] = (byte) (j280 >> 8);
        bArr[23] = (byte) ((j280 >> 16) | (j283 << 5));
        bArr[24] = (byte) (j283 >> 3);
        bArr[25] = (byte) (j283 >> 11);
        bArr[26] = (byte) ((j283 >> 19) | (j286 << 2));
        bArr[27] = (byte) (j286 >> 6);
        bArr[28] = (byte) ((j286 >> 14) | (j285 << 7));
        bArr[29] = (byte) (j285 >> 1);
        bArr[30] = (byte) (j285 >> 9);
        bArr[31] = (byte) (j285 >> 17);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void neg(long[] jArr, long[] jArr2) {
        for (int i2 = 0; i2 < jArr2.length; i2++) {
            jArr[i2] = -jArr2[i2];
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void pow2252m3(long[] jArr, long[] jArr2) {
        long[] jArr3 = new long[10];
        long[] jArr4 = new long[10];
        long[] jArr5 = new long[10];
        Field25519.square(jArr3, jArr2);
        Field25519.square(jArr4, jArr3);
        Field25519.square(jArr4, jArr4);
        Field25519.mult(jArr4, jArr2, jArr4);
        Field25519.mult(jArr3, jArr3, jArr4);
        Field25519.square(jArr3, jArr3);
        Field25519.mult(jArr3, jArr4, jArr3);
        Field25519.square(jArr4, jArr3);
        for (int i2 = 1; i2 < 5; i2++) {
            Field25519.square(jArr4, jArr4);
        }
        Field25519.mult(jArr3, jArr4, jArr3);
        Field25519.square(jArr4, jArr3);
        for (int i3 = 1; i3 < 10; i3++) {
            Field25519.square(jArr4, jArr4);
        }
        Field25519.mult(jArr4, jArr4, jArr3);
        Field25519.square(jArr5, jArr4);
        for (int i4 = 1; i4 < 20; i4++) {
            Field25519.square(jArr5, jArr5);
        }
        Field25519.mult(jArr4, jArr5, jArr4);
        Field25519.square(jArr4, jArr4);
        for (int i5 = 1; i5 < 10; i5++) {
            Field25519.square(jArr4, jArr4);
        }
        Field25519.mult(jArr3, jArr4, jArr3);
        Field25519.square(jArr4, jArr3);
        for (int i6 = 1; i6 < 50; i6++) {
            Field25519.square(jArr4, jArr4);
        }
        Field25519.mult(jArr4, jArr4, jArr3);
        Field25519.square(jArr5, jArr4);
        for (int i7 = 1; i7 < 100; i7++) {
            Field25519.square(jArr5, jArr5);
        }
        Field25519.mult(jArr4, jArr5, jArr4);
        Field25519.square(jArr4, jArr4);
        for (int i8 = 1; i8 < 50; i8++) {
            Field25519.square(jArr4, jArr4);
        }
        Field25519.mult(jArr3, jArr4, jArr3);
        Field25519.square(jArr3, jArr3);
        Field25519.square(jArr3, jArr3);
        Field25519.mult(jArr, jArr3, jArr2);
    }

    private static void reduce(byte[] bArr) {
        long jLoad3 = load3(bArr, 0) & 2097151;
        long jLoad4 = (load4(bArr, 2) >> 5) & 2097151;
        long jLoad5 = (load3(bArr, 5) >> 2) & 2097151;
        long jLoad6 = (load4(bArr, 7) >> 7) & 2097151;
        long jLoad7 = (load4(bArr, 10) >> 4) & 2097151;
        long jLoad8 = (load3(bArr, 13) >> 1) & 2097151;
        long jLoad9 = (load4(bArr, 15) >> 6) & 2097151;
        long jLoad10 = (load3(bArr, 18) >> 3) & 2097151;
        long jLoad11 = load3(bArr, 21) & 2097151;
        long jLoad12 = (load4(bArr, 23) >> 5) & 2097151;
        long jLoad13 = (load3(bArr, 26) >> 2) & 2097151;
        long jLoad14 = (load4(bArr, 28) >> 7) & 2097151;
        long jLoad15 = (load4(bArr, 31) >> 4) & 2097151;
        long jLoad16 = (load3(bArr, 34) >> 1) & 2097151;
        long jLoad17 = (load4(bArr, 36) >> 6) & 2097151;
        long jLoad18 = (load3(bArr, 39) >> 3) & 2097151;
        long jLoad19 = load3(bArr, 42) & 2097151;
        long jLoad20 = (load4(bArr, 44) >> 5) & 2097151;
        long jLoad21 = (load3(bArr, 47) >> 2) & 2097151;
        long jLoad22 = (load4(bArr, 49) >> 7) & 2097151;
        long jLoad23 = (load4(bArr, 52) >> 4) & 2097151;
        long jLoad24 = (load3(bArr, 55) >> 1) & 2097151;
        long jLoad25 = (load4(bArr, 57) >> 6) & 2097151;
        long jLoad26 = load4(bArr, 60) >> 3;
        long j2 = (jLoad26 * 666643) + jLoad14;
        long j3 = (jLoad26 * 470296) + jLoad15;
        long j4 = (jLoad26 * 654183) + jLoad16;
        long j5 = jLoad17 - (jLoad26 * 997805);
        long j6 = (jLoad26 * 136657) + jLoad18;
        long j7 = jLoad19 - (jLoad26 * 683901);
        long j8 = (jLoad25 * 666643) + jLoad13;
        long j9 = (jLoad25 * 470296) + j2;
        long j10 = (jLoad25 * 654183) + j3;
        long j11 = j4 - (jLoad25 * 997805);
        long j12 = (jLoad25 * 136657) + j5;
        long j13 = j6 - (jLoad25 * 683901);
        long j14 = (jLoad24 * 666643) + jLoad12;
        long j15 = (jLoad24 * 470296) + j8;
        long j16 = (jLoad24 * 654183) + j9;
        long j17 = j10 - (jLoad24 * 997805);
        long j18 = (jLoad24 * 136657) + j11;
        long j19 = j12 - (jLoad24 * 683901);
        long j20 = (jLoad23 * 666643) + jLoad11;
        long j21 = (jLoad23 * 470296) + j14;
        long j22 = (jLoad23 * 654183) + j15;
        long j23 = j16 - (jLoad23 * 997805);
        long j24 = (jLoad23 * 136657) + j17;
        long j25 = j18 - (jLoad23 * 683901);
        long j26 = (jLoad22 * 666643) + jLoad10;
        long j27 = (jLoad22 * 470296) + j20;
        long j28 = (jLoad22 * 654183) + j21;
        long j29 = j22 - (jLoad22 * 997805);
        long j30 = (jLoad22 * 136657) + j23;
        long j31 = j24 - (jLoad22 * 683901);
        long j32 = (jLoad21 * 666643) + jLoad9;
        long j33 = (jLoad21 * 470296) + j26;
        long j34 = (jLoad21 * 654183) + j27;
        long j35 = j28 - (jLoad21 * 997805);
        long j36 = (jLoad21 * 136657) + j29;
        long j37 = j30 - (jLoad21 * 683901);
        long j38 = (j32 + 1048576) >> 21;
        long j39 = j33 + j38;
        long j40 = j32 - (j38 << 21);
        long j41 = (j34 + 1048576) >> 21;
        long j42 = j35 + j41;
        long j43 = j34 - (j41 << 21);
        long j44 = (j36 + 1048576) >> 21;
        long j45 = j37 + j44;
        long j46 = j36 - (j44 << 21);
        long j47 = (j31 + 1048576) >> 21;
        long j48 = j25 + j47;
        long j49 = j31 - (j47 << 21);
        long j50 = (j19 + 1048576) >> 21;
        long j51 = j13 + j50;
        long j52 = j19 - (j50 << 21);
        long j53 = (j7 + 1048576) >> 21;
        long j54 = jLoad20 + j53;
        long j55 = j7 - (j53 << 21);
        long j56 = (j39 + 1048576) >> 21;
        long j57 = j43 + j56;
        long j58 = j39 - (j56 << 21);
        long j59 = (j42 + 1048576) >> 21;
        long j60 = j46 + j59;
        long j61 = j42 - (j59 << 21);
        long j62 = (j45 + 1048576) >> 21;
        long j63 = j49 + j62;
        long j64 = j45 - (j62 << 21);
        long j65 = (j48 + 1048576) >> 21;
        long j66 = j52 + j65;
        long j67 = j48 - (j65 << 21);
        long j68 = (j51 + 1048576) >> 21;
        long j69 = j55 + j68;
        long j70 = j51 - (j68 << 21);
        long j71 = (j54 * 666643) + jLoad8;
        long j72 = (j54 * 470296) + j40;
        long j73 = (j54 * 654183) + j58;
        long j74 = j57 - (j54 * 997805);
        long j75 = (j54 * 136657) + j61;
        long j76 = j60 - (j54 * 683901);
        long j77 = (j69 * 666643) + jLoad7;
        long j78 = (j69 * 470296) + j71;
        long j79 = (j69 * 654183) + j72;
        long j80 = j73 - (j69 * 997805);
        long j81 = (j69 * 136657) + j74;
        long j82 = j75 - (j69 * 683901);
        long j83 = (j70 * 666643) + jLoad6;
        long j84 = (j70 * 470296) + j77;
        long j85 = (j70 * 654183) + j78;
        long j86 = (j70 * 136657) + j80;
        long j87 = j81 - (j70 * 683901);
        long j88 = (j66 * 666643) + jLoad5;
        long j89 = (j66 * 470296) + j83;
        long j90 = (j66 * 654183) + j84;
        long j91 = (j66 * 136657) + (j79 - (j70 * 997805));
        long j92 = (j67 * 666643) + jLoad4;
        long j93 = (j67 * 470296) + j88;
        long j94 = (j67 * 654183) + j89;
        long j95 = j90 - (j67 * 997805);
        long j96 = (j67 * 136657) + (j85 - (j66 * 997805));
        long j97 = j91 - (j67 * 683901);
        long j98 = (j63 * 666643) + jLoad3;
        long j99 = (j63 * 654183) + j93;
        long j100 = (j63 * 136657) + j95;
        long j101 = (j98 + 1048576) >> 21;
        long j102 = (j63 * 470296) + j92 + j101;
        long j103 = j98 - (j101 << 21);
        long j104 = (j99 + 1048576) >> 21;
        long j105 = (j94 - (j63 * 997805)) + j104;
        long j106 = j99 - (j104 << 21);
        long j107 = (j100 + 1048576) >> 21;
        long j108 = (j96 - (j63 * 683901)) + j107;
        long j109 = j100 - (j107 << 21);
        long j110 = (j97 + 1048576) >> 21;
        long j111 = (j86 - (j66 * 683901)) + j110;
        long j112 = j97 - (j110 << 21);
        long j113 = (j87 + 1048576) >> 21;
        long j114 = j82 + j113;
        long j115 = j87 - (j113 << 21);
        long j116 = (j76 + 1048576) >> 21;
        long j117 = j64 + j116;
        long j118 = j76 - (j116 << 21);
        long j119 = (j102 + 1048576) >> 21;
        long j120 = j106 + j119;
        long j121 = j102 - (j119 << 21);
        long j122 = (j105 + 1048576) >> 21;
        long j123 = j109 + j122;
        long j124 = j105 - (j122 << 21);
        long j125 = (j108 + 1048576) >> 21;
        long j126 = j112 + j125;
        long j127 = j108 - (j125 << 21);
        long j128 = (j111 + 1048576) >> 21;
        long j129 = j115 + j128;
        long j130 = j111 - (j128 << 21);
        long j131 = (j114 + 1048576) >> 21;
        long j132 = j118 + j131;
        long j133 = j114 - (j131 << 21);
        long j134 = (j117 + 1048576) >> 21;
        long j135 = j134 + 0;
        long j136 = j117 - (j134 << 21);
        long j137 = (j135 * 666643) + j103;
        long j138 = j137 >> 21;
        long j139 = (j135 * 470296) + j121 + j138;
        long j140 = j137 - (j138 << 21);
        long j141 = j139 >> 21;
        long j142 = (j135 * 654183) + j120 + j141;
        long j143 = j139 - (j141 << 21);
        long j144 = j142 >> 21;
        long j145 = (j124 - (j135 * 997805)) + j144;
        long j146 = j142 - (j144 << 21);
        long j147 = j145 >> 21;
        long j148 = (j135 * 136657) + j123 + j147;
        long j149 = j145 - (j147 << 21);
        long j150 = j148 >> 21;
        long j151 = (j127 - (j135 * 683901)) + j150;
        long j152 = j148 - (j150 << 21);
        long j153 = j151 >> 21;
        long j154 = j126 + j153;
        long j155 = j151 - (j153 << 21);
        long j156 = j154 >> 21;
        long j157 = j130 + j156;
        long j158 = j154 - (j156 << 21);
        long j159 = j157 >> 21;
        long j160 = j129 + j159;
        long j161 = j157 - (j159 << 21);
        long j162 = j160 >> 21;
        long j163 = j133 + j162;
        long j164 = j160 - (j162 << 21);
        long j165 = j163 >> 21;
        long j166 = j132 + j165;
        long j167 = j163 - (j165 << 21);
        long j168 = j166 >> 21;
        long j169 = j136 + j168;
        long j170 = j166 - (j168 << 21);
        long j171 = j169 >> 21;
        long j172 = j171 + 0;
        long j173 = (666643 * j172) + j140;
        long j174 = j173 >> 21;
        long j175 = (470296 * j172) + j143 + j174;
        long j176 = j173 - (j174 << 21);
        long j177 = j175 >> 21;
        long j178 = (654183 * j172) + j146 + j177;
        long j179 = j175 - (j177 << 21);
        long j180 = j178 >> 21;
        long j181 = (j149 - (997805 * j172)) + j180;
        long j182 = j178 - (j180 << 21);
        long j183 = j181 >> 21;
        long j184 = (136657 * j172) + j152 + j183;
        long j185 = j181 - (j183 << 21);
        long j186 = j184 >> 21;
        long j187 = (j155 - (j172 * 683901)) + j186;
        long j188 = j184 - (j186 << 21);
        long j189 = j187 >> 21;
        long j190 = j158 + j189;
        long j191 = j187 - (j189 << 21);
        long j192 = j190 >> 21;
        long j193 = j161 + j192;
        long j194 = j190 - (j192 << 21);
        long j195 = j193 >> 21;
        long j196 = j164 + j195;
        long j197 = j193 - (j195 << 21);
        long j198 = j196 >> 21;
        long j199 = j167 + j198;
        long j200 = j196 - (j198 << 21);
        long j201 = j199 >> 21;
        long j202 = j170 + j201;
        long j203 = j199 - (j201 << 21);
        long j204 = j202 >> 21;
        long j205 = (j169 - (j171 << 21)) + j204;
        long j206 = j202 - (j204 << 21);
        bArr[0] = (byte) j176;
        bArr[1] = (byte) (j176 >> 8);
        bArr[2] = (byte) ((j176 >> 16) | (j179 << 5));
        bArr[3] = (byte) (j179 >> 3);
        bArr[4] = (byte) (j179 >> 11);
        bArr[5] = (byte) ((j179 >> 19) | (j182 << 2));
        bArr[6] = (byte) (j182 >> 6);
        bArr[7] = (byte) ((j182 >> 14) | (j185 << 7));
        bArr[8] = (byte) (j185 >> 1);
        bArr[9] = (byte) (j185 >> 9);
        bArr[10] = (byte) ((j185 >> 17) | (j188 << 4));
        bArr[11] = (byte) (j188 >> 4);
        bArr[12] = (byte) (j188 >> 12);
        bArr[13] = (byte) ((j188 >> 20) | (j191 << 1));
        bArr[14] = (byte) (j191 >> 7);
        bArr[15] = (byte) ((j191 >> 15) | (j194 << 6));
        bArr[16] = (byte) (j194 >> 2);
        bArr[17] = (byte) (j194 >> 10);
        bArr[18] = (byte) ((j194 >> 18) | (j197 << 3));
        bArr[19] = (byte) (j197 >> 5);
        bArr[20] = (byte) (j197 >> 13);
        bArr[21] = (byte) j200;
        bArr[22] = (byte) (j200 >> 8);
        bArr[23] = (byte) ((j200 >> 16) | (j203 << 5));
        bArr[24] = (byte) (j203 >> 3);
        bArr[25] = (byte) (j203 >> 11);
        bArr[26] = (byte) ((j203 >> 19) | (j206 << 2));
        bArr[27] = (byte) (j206 >> 6);
        bArr[28] = (byte) ((j206 >> 14) | (j205 << 7));
        bArr[29] = (byte) (j205 >> 1);
        bArr[30] = (byte) (j205 >> 9);
        bArr[31] = (byte) (j205 >> 17);
    }

    private static XYZ scalarMultWithBase(byte[] bArr) {
        int i2;
        byte[] bArr2 = new byte[64];
        int i3 = 0;
        while (true) {
            if (i3 >= 32) {
                break;
            }
            int i4 = i3 * 2;
            bArr2[i4 + 0] = (byte) (((bArr[i3] & 255) >> 0) & 15);
            bArr2[i4 + 1] = (byte) (((bArr[i3] & 255) >> 4) & 15);
            i3++;
        }
        int i5 = 0;
        int i6 = 0;
        while (i5 < 63) {
            byte b2 = (byte) (bArr2[i5] + i6);
            bArr2[i5] = b2;
            int i7 = (b2 + 8) >> 4;
            bArr2[i5] = (byte) (b2 - (i7 << 4));
            i5++;
            i6 = i7;
        }
        bArr2[63] = (byte) (bArr2[63] + i6);
        PartialXYZT partialXYZT = new PartialXYZT(NEUTRAL);
        XYZT xyzt = new XYZT();
        for (i2 = 1; i2 < 64; i2 += 2) {
            CachedXYT cachedXYT = new CachedXYT(CACHED_NEUTRAL);
            select(cachedXYT, i2 / 2, bArr2[i2]);
            add(partialXYZT, XYZT.fromPartialXYZT(xyzt, partialXYZT), cachedXYT);
        }
        XYZ xyz = new XYZ();
        doubleXYZ(partialXYZT, XYZ.fromPartialXYZT(xyz, partialXYZT));
        doubleXYZ(partialXYZT, XYZ.fromPartialXYZT(xyz, partialXYZT));
        doubleXYZ(partialXYZT, XYZ.fromPartialXYZT(xyz, partialXYZT));
        doubleXYZ(partialXYZT, XYZ.fromPartialXYZT(xyz, partialXYZT));
        for (int i8 = 0; i8 < 64; i8 += 2) {
            CachedXYT cachedXYT2 = new CachedXYT(CACHED_NEUTRAL);
            select(cachedXYT2, i8 / 2, bArr2[i8]);
            add(partialXYZT, XYZT.fromPartialXYZT(xyzt, partialXYZT), cachedXYT2);
        }
        XYZ xyz2 = new XYZ(partialXYZT);
        if (xyz2.isOnCurve()) {
            return xyz2;
        }
        throw new IllegalStateException("arithmetic error in scalar multiplication");
    }

    public static byte[] scalarMultWithBaseToBytes(byte[] bArr) {
        return scalarMultWithBase(bArr).toBytes();
    }

    private static void select(CachedXYT cachedXYT, int i2, byte b2) {
        int i3 = (b2 & 255) >> 7;
        int i4 = b2 - (((-i3) & b2) << 1);
        CachedXYT[][] cachedXYTArr = Ed25519Constants.B_TABLE;
        cachedXYT.copyConditional(cachedXYTArr[i2][0], eq(i4, 1));
        cachedXYT.copyConditional(cachedXYTArr[i2][1], eq(i4, 2));
        cachedXYT.copyConditional(cachedXYTArr[i2][2], eq(i4, 3));
        cachedXYT.copyConditional(cachedXYTArr[i2][3], eq(i4, 4));
        cachedXYT.copyConditional(cachedXYTArr[i2][4], eq(i4, 5));
        cachedXYT.copyConditional(cachedXYTArr[i2][5], eq(i4, 6));
        cachedXYT.copyConditional(cachedXYTArr[i2][6], eq(i4, 7));
        cachedXYT.copyConditional(cachedXYTArr[i2][7], eq(i4, 8));
        long[] jArrCopyOf = Arrays.copyOf(cachedXYT.yMinusX, 10);
        long[] jArrCopyOf2 = Arrays.copyOf(cachedXYT.yPlusX, 10);
        long[] jArrCopyOf3 = Arrays.copyOf(cachedXYT.t2d, 10);
        neg(jArrCopyOf3, jArrCopyOf3);
        cachedXYT.copyConditional(new CachedXYT(jArrCopyOf, jArrCopyOf2, jArrCopyOf3), i3);
    }

    public static byte[] sign(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, 0, bArr.length);
        MessageDigest engineFactory = EngineFactory.MESSAGE_DIGEST.getInstance("SHA-512");
        engineFactory.update(bArr3, 32, 32);
        engineFactory.update(bArrCopyOfRange);
        byte[] bArrDigest = engineFactory.digest();
        reduce(bArrDigest);
        byte[] bArrCopyOfRange2 = Arrays.copyOfRange(scalarMultWithBase(bArrDigest).toBytes(), 0, 32);
        engineFactory.reset();
        engineFactory.update(bArrCopyOfRange2);
        engineFactory.update(bArr2);
        engineFactory.update(bArrCopyOfRange);
        byte[] bArrDigest2 = engineFactory.digest();
        reduce(bArrDigest2);
        byte[] bArr4 = new byte[32];
        mulAdd(bArr4, bArrDigest2, bArr3, bArrDigest);
        return Bytes.concat(bArrCopyOfRange2, bArr4);
    }

    private static byte[] slide(byte[] bArr) {
        int i2;
        byte[] bArr2 = new byte[MasterKey.DEFAULT_AES_GCM_MASTER_KEY_SIZE];
        for (int i3 = 0; i3 < 256; i3++) {
            bArr2[i3] = (byte) (1 & ((bArr[i3 >> 3] & 255) >> (i3 & 7)));
        }
        for (int i4 = 0; i4 < 256; i4++) {
            if (bArr2[i4] != 0) {
                for (int i5 = 1; i5 <= 6 && (i2 = i4 + i5) < 256; i5++) {
                    byte b2 = bArr2[i2];
                    if (b2 != 0) {
                        byte b3 = bArr2[i4];
                        if ((b2 << i5) + b3 > 15) {
                            if (b3 - (b2 << i5) < -15) {
                                break;
                            }
                            bArr2[i4] = (byte) (b3 - (b2 << i5));
                            while (i2 < 256) {
                                if (bArr2[i2] == 0) {
                                    bArr2[i2] = 1;
                                    break;
                                }
                                bArr2[i2] = 0;
                                i2++;
                            }
                        } else {
                            bArr2[i4] = (byte) (b3 + (b2 << i5));
                            bArr2[i2] = 0;
                        }
                    }
                }
            }
        }
        return bArr2;
    }

    private static void sub(PartialXYZT partialXYZT, XYZT xyzt, CachedXYT cachedXYT) {
        long[] jArr = new long[10];
        long[] jArr2 = partialXYZT.xyz.f521x;
        XYZ xyz = xyzt.xyz;
        Field25519.sum(jArr2, xyz.f522y, xyz.f521x);
        long[] jArr3 = partialXYZT.xyz.f522y;
        XYZ xyz2 = xyzt.xyz;
        Field25519.sub(jArr3, xyz2.f522y, xyz2.f521x);
        long[] jArr4 = partialXYZT.xyz.f522y;
        Field25519.mult(jArr4, jArr4, cachedXYT.yPlusX);
        XYZ xyz3 = partialXYZT.xyz;
        Field25519.mult(xyz3.f523z, xyz3.f521x, cachedXYT.yMinusX);
        Field25519.mult(partialXYZT.f520t, xyzt.f524t, cachedXYT.t2d);
        cachedXYT.multByZ(partialXYZT.xyz.f521x, xyzt.xyz.f523z);
        long[] jArr5 = partialXYZT.xyz.f521x;
        Field25519.sum(jArr, jArr5, jArr5);
        XYZ xyz4 = partialXYZT.xyz;
        Field25519.sub(xyz4.f521x, xyz4.f523z, xyz4.f522y);
        XYZ xyz5 = partialXYZT.xyz;
        long[] jArr6 = xyz5.f522y;
        Field25519.sum(jArr6, xyz5.f523z, jArr6);
        Field25519.sub(partialXYZT.xyz.f523z, jArr, partialXYZT.f520t);
        long[] jArr7 = partialXYZT.f520t;
        Field25519.sum(jArr7, jArr, jArr7);
    }

    public static boolean verify(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bArr2.length != 64) {
            return false;
        }
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr2, 32, 64);
        if (!isSmallerThanGroupOrder(bArrCopyOfRange)) {
            return false;
        }
        MessageDigest engineFactory = EngineFactory.MESSAGE_DIGEST.getInstance("SHA-512");
        engineFactory.update(bArr2, 0, 32);
        engineFactory.update(bArr3);
        engineFactory.update(bArr);
        byte[] bArrDigest = engineFactory.digest();
        reduce(bArrDigest);
        byte[] bytes = doubleScalarMultVarTime(bArrDigest, XYZT.fromBytesNegateVarTime(bArr3), bArrCopyOfRange).toBytes();
        for (int i2 = 0; i2 < 32; i2++) {
            if (bytes[i2] != bArr2[i2]) {
                return false;
            }
        }
        return true;
    }
}
