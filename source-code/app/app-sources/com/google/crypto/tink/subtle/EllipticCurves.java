package com.google.crypto.tink.subtle;

import com.google.crypto.tink.internal.BigIntegerEncoding;
import com.google.crypto.tink.internal.EllipticCurvesUtil;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.EllipticCurve;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import javax.crypto.KeyAgreement;

/* JADX INFO: loaded from: classes.dex */
public final class EllipticCurves {

    /* JADX INFO: renamed from: com.google.crypto.tink.subtle.EllipticCurves$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType;
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$PointFormatType;

        static {
            int[] iArr = new int[CurveType.values().length];
            $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType = iArr;
            try {
                iArr[CurveType.NIST_P256.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType[CurveType.NIST_P384.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType[CurveType.NIST_P521.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[PointFormatType.values().length];
            $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$PointFormatType = iArr2;
            try {
                iArr2[PointFormatType.UNCOMPRESSED.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$PointFormatType[PointFormatType.DO_NOT_USE_CRUNCHY_UNCOMPRESSED.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$PointFormatType[PointFormatType.COMPRESSED.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public enum CurveType {
        NIST_P256,
        NIST_P384,
        NIST_P521
    }

    public enum EcdsaEncoding {
        IEEE_P1363,
        DER
    }

    public enum PointFormatType {
        UNCOMPRESSED,
        COMPRESSED,
        DO_NOT_USE_CRUNCHY_UNCOMPRESSED
    }

    private EllipticCurves() {
    }

    public static void checkPublicKey(ECPublicKey eCPublicKey) throws GeneralSecurityException {
        EllipticCurvesUtil.checkPointOnCurve(eCPublicKey.getW(), eCPublicKey.getParams().getCurve());
    }

    public static byte[] computeSharedSecret(ECPrivateKey eCPrivateKey, ECPublicKey eCPublicKey) throws GeneralSecurityException {
        validatePublicKeySpec(eCPublicKey, eCPrivateKey);
        return computeSharedSecret(eCPrivateKey, eCPublicKey.getW());
    }

    public static ECPoint ecPointDecode(EllipticCurve ellipticCurve, PointFormatType pointFormatType, byte[] bArr) {
        return pointDecode(ellipticCurve, pointFormatType, bArr);
    }

    public static byte[] ecdsaDer2Ieee(byte[] bArr, int i2) throws GeneralSecurityException {
        if (!isValidDerEncoding(bArr)) {
            throw new GeneralSecurityException("Invalid DER encoding");
        }
        byte[] bArr2 = new byte[i2];
        int i3 = ((bArr[1] & 255) >= 128 ? 3 : 2) + 1;
        int i4 = i3 + 1;
        int i5 = bArr[i3];
        int i6 = bArr[i4] == 0 ? 1 : 0;
        System.arraycopy(bArr, i4 + i6, bArr2, ((i2 / 2) - i5) + i6, i5 - i6);
        int i7 = i5 + 1 + i4;
        int i8 = i7 + 1;
        int i9 = bArr[i7];
        int i10 = bArr[i8] != 0 ? 0 : 1;
        System.arraycopy(bArr, i8 + i10, bArr2, (i2 - i9) + i10, i9 - i10);
        return bArr2;
    }

    public static byte[] ecdsaIeee2Der(byte[] bArr) throws GeneralSecurityException {
        byte[] bArr2;
        int i2;
        if (bArr.length % 2 != 0 || bArr.length == 0 || bArr.length > 132) {
            throw new GeneralSecurityException("Invalid IEEE_P1363 encoding");
        }
        byte[] minimalSignedNumber = toMinimalSignedNumber(Arrays.copyOf(bArr, bArr.length / 2));
        byte[] minimalSignedNumber2 = toMinimalSignedNumber(Arrays.copyOfRange(bArr, bArr.length / 2, bArr.length));
        int length = minimalSignedNumber.length + 2 + 1 + 1 + minimalSignedNumber2.length;
        if (length >= 128) {
            bArr2 = new byte[length + 3];
            bArr2[0] = 48;
            bArr2[1] = -127;
            bArr2[2] = (byte) length;
            i2 = 3;
        } else {
            bArr2 = new byte[length + 2];
            bArr2[0] = 48;
            bArr2[1] = (byte) length;
            i2 = 2;
        }
        int i3 = i2 + 1;
        bArr2[i2] = 2;
        int i4 = i3 + 1;
        bArr2[i3] = (byte) minimalSignedNumber.length;
        System.arraycopy(minimalSignedNumber, 0, bArr2, i4, minimalSignedNumber.length);
        int length2 = i4 + minimalSignedNumber.length;
        int i5 = length2 + 1;
        bArr2[length2] = 2;
        bArr2[i5] = (byte) minimalSignedNumber2.length;
        System.arraycopy(minimalSignedNumber2, 0, bArr2, i5 + 1, minimalSignedNumber2.length);
        return bArr2;
    }

    public static int encodingSizeInBytes(EllipticCurve ellipticCurve, PointFormatType pointFormatType) throws GeneralSecurityException {
        int iFieldSizeInBytes = fieldSizeInBytes(ellipticCurve);
        int i2 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$PointFormatType[pointFormatType.ordinal()];
        if (i2 == 1) {
            return (iFieldSizeInBytes * 2) + 1;
        }
        if (i2 == 2) {
            return iFieldSizeInBytes * 2;
        }
        if (i2 == 3) {
            return iFieldSizeInBytes + 1;
        }
        throw new GeneralSecurityException("unknown EC point format");
    }

    public static int fieldSizeInBits(EllipticCurve ellipticCurve) {
        return getModulus(ellipticCurve).subtract(BigInteger.ONE).bitLength();
    }

    public static int fieldSizeInBytes(EllipticCurve ellipticCurve) {
        return (fieldSizeInBits(ellipticCurve) + 7) / 8;
    }

    public static KeyPair generateKeyPair(CurveType curveType) {
        return generateKeyPair(getCurveSpec(curveType));
    }

    public static ECParameterSpec getCurveSpec(CurveType curveType) throws NoSuchAlgorithmException {
        int i2 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType[curveType.ordinal()];
        if (i2 == 1) {
            return getNistP256Params();
        }
        if (i2 == 2) {
            return getNistP384Params();
        }
        if (i2 == 3) {
            return getNistP521Params();
        }
        throw new NoSuchAlgorithmException("curve not implemented:" + curveType);
    }

    public static ECPrivateKey getEcPrivateKey(CurveType curveType, byte[] bArr) throws NoSuchAlgorithmException {
        return (ECPrivateKey) EngineFactory.KEY_FACTORY.getInstance("EC").generatePrivate(new ECPrivateKeySpec(BigIntegerEncoding.fromUnsignedBigEndianBytes(bArr), getCurveSpec(curveType)));
    }

    public static ECPublicKey getEcPublicKey(CurveType curveType, PointFormatType pointFormatType, byte[] bArr) {
        return getEcPublicKey(getCurveSpec(curveType), pointFormatType, bArr);
    }

    public static BigInteger getModulus(EllipticCurve ellipticCurve) {
        return EllipticCurvesUtil.getModulus(ellipticCurve);
    }

    public static ECParameterSpec getNistP256Params() {
        return EllipticCurvesUtil.NIST_P256_PARAMS;
    }

    public static ECParameterSpec getNistP384Params() {
        return EllipticCurvesUtil.NIST_P384_PARAMS;
    }

    public static ECParameterSpec getNistP521Params() {
        return EllipticCurvesUtil.NIST_P521_PARAMS;
    }

    public static BigInteger getY(BigInteger bigInteger, boolean z2, EllipticCurve ellipticCurve) throws GeneralSecurityException {
        BigInteger modulus = getModulus(ellipticCurve);
        BigInteger bigIntegerModSqrt = modSqrt(bigInteger.multiply(bigInteger).add(ellipticCurve.getA()).multiply(bigInteger).add(ellipticCurve.getB()).mod(modulus), modulus);
        return z2 != bigIntegerModSqrt.testBit(0) ? modulus.subtract(bigIntegerModSqrt).mod(modulus) : bigIntegerModSqrt;
    }

    public static boolean isNistEcParameterSpec(ECParameterSpec eCParameterSpec) {
        return EllipticCurvesUtil.isNistEcParameterSpec(eCParameterSpec);
    }

    public static boolean isSameEcParameterSpec(ECParameterSpec eCParameterSpec, ECParameterSpec eCParameterSpec2) {
        return EllipticCurvesUtil.isSameEcParameterSpec(eCParameterSpec, eCParameterSpec2);
    }

    public static boolean isValidDerEncoding(byte[] bArr) {
        int i2;
        if (bArr.length < 8 || bArr[0] != 48) {
            return false;
        }
        int i3 = bArr[1] & 255;
        if (i3 == 129) {
            i3 = bArr[2] & 255;
            if (i3 < 128) {
                return false;
            }
            i2 = 2;
        } else {
            if (i3 == 128 || i3 > 129) {
                return false;
            }
            i2 = 1;
        }
        if (i3 != (bArr.length - 1) - i2) {
            return false;
        }
        int i4 = i2 + 1;
        if (bArr[i4] != 2) {
            return false;
        }
        int i5 = i4 + 1;
        int i6 = bArr[i5] & 255;
        int i7 = i5 + 1 + i6 + 1;
        if (i7 >= bArr.length || i6 == 0) {
            return false;
        }
        int i8 = i2 + 3;
        byte b2 = bArr[i8];
        if ((b2 & 255) >= 128) {
            return false;
        }
        if ((i6 > 1 && b2 == 0 && (bArr[i2 + 4] & 255) < 128) || bArr[i8 + i6] != 2) {
            return false;
        }
        int i9 = bArr[i7] & 255;
        if (i7 + 1 + i9 != bArr.length || i9 == 0) {
            return false;
        }
        byte b3 = bArr[i2 + 5 + i6];
        if ((b3 & 255) >= 128) {
            return false;
        }
        return i9 <= 1 || b3 != 0 || (bArr[(i2 + 6) + i6] & 255) >= 128;
    }

    public static BigInteger modSqrt(BigInteger bigInteger, BigInteger bigInteger2) throws GeneralSecurityException {
        BigInteger bigIntegerModPow;
        if (bigInteger2.signum() != 1) {
            throw new InvalidAlgorithmParameterException("p must be positive");
        }
        BigInteger bigIntegerMod = bigInteger.mod(bigInteger2);
        BigInteger bigInteger3 = BigInteger.ZERO;
        if (bigIntegerMod.equals(bigInteger3)) {
            return bigInteger3;
        }
        int i2 = 0;
        if (bigInteger2.testBit(0) && bigInteger2.testBit(1)) {
            bigIntegerModPow = bigIntegerMod.modPow(bigInteger2.add(BigInteger.ONE).shiftRight(2), bigInteger2);
        } else if (!bigInteger2.testBit(0) || bigInteger2.testBit(1)) {
            bigIntegerModPow = null;
        } else {
            BigInteger bigIntegerAdd = BigInteger.ONE;
            BigInteger bigIntegerShiftRight = bigInteger2.subtract(bigIntegerAdd).shiftRight(1);
            while (true) {
                BigInteger bigIntegerMod2 = bigIntegerAdd.multiply(bigIntegerAdd).subtract(bigIntegerMod).mod(bigInteger2);
                if (bigIntegerMod2.equals(BigInteger.ZERO)) {
                    return bigIntegerAdd;
                }
                BigInteger bigIntegerModPow2 = bigIntegerMod2.modPow(bigIntegerShiftRight, bigInteger2);
                BigInteger bigIntegerMod3 = BigInteger.ONE;
                if (bigIntegerModPow2.add(bigIntegerMod3).equals(bigInteger2)) {
                    BigInteger bigIntegerShiftRight2 = bigInteger2.add(bigIntegerMod3).shiftRight(1);
                    BigInteger bigIntegerMod4 = bigIntegerAdd;
                    for (int iBitLength = bigIntegerShiftRight2.bitLength() - 2; iBitLength >= 0; iBitLength--) {
                        BigInteger bigIntegerMultiply = bigIntegerMod4.multiply(bigIntegerMod3);
                        bigIntegerMod4 = bigIntegerMod4.multiply(bigIntegerMod4).add(bigIntegerMod3.multiply(bigIntegerMod3).mod(bigInteger2).multiply(bigIntegerMod2)).mod(bigInteger2);
                        BigInteger bigIntegerMod5 = bigIntegerMultiply.add(bigIntegerMultiply).mod(bigInteger2);
                        if (bigIntegerShiftRight2.testBit(iBitLength)) {
                            BigInteger bigIntegerMod6 = bigIntegerMod4.multiply(bigIntegerAdd).add(bigIntegerMod5.multiply(bigIntegerMod2)).mod(bigInteger2);
                            bigIntegerMod3 = bigIntegerAdd.multiply(bigIntegerMod5).add(bigIntegerMod4).mod(bigInteger2);
                            bigIntegerMod4 = bigIntegerMod6;
                        } else {
                            bigIntegerMod3 = bigIntegerMod5;
                        }
                    }
                    bigIntegerModPow = bigIntegerMod4;
                    break;
                }
                if (!bigIntegerModPow2.equals(bigIntegerMod3)) {
                    throw new InvalidAlgorithmParameterException("p is not prime");
                }
                bigIntegerAdd = bigIntegerAdd.add(bigIntegerMod3);
                i2++;
                if (i2 == 128 && !bigInteger2.isProbablePrime(80)) {
                    throw new InvalidAlgorithmParameterException("p is not prime");
                }
            }
        }
        if (bigIntegerModPow == null || bigIntegerModPow.multiply(bigIntegerModPow).mod(bigInteger2).compareTo(bigIntegerMod) == 0) {
            return bigIntegerModPow;
        }
        throw new GeneralSecurityException("Could not find a modular square root");
    }

    public static ECPoint pointDecode(CurveType curveType, PointFormatType pointFormatType, byte[] bArr) {
        return pointDecode(getCurveSpec(curveType).getCurve(), pointFormatType, bArr);
    }

    public static byte[] pointEncode(CurveType curveType, PointFormatType pointFormatType, ECPoint eCPoint) {
        return pointEncode(getCurveSpec(curveType).getCurve(), pointFormatType, eCPoint);
    }

    private static byte[] toMinimalSignedNumber(byte[] bArr) {
        int length = 0;
        while (length < bArr.length && bArr[length] == 0) {
            length++;
        }
        if (length == bArr.length) {
            length = bArr.length - 1;
        }
        int i2 = (bArr[length] & 128) == 128 ? 1 : 0;
        byte[] bArr2 = new byte[(bArr.length - length) + i2];
        System.arraycopy(bArr, length, bArr2, i2, bArr.length - length);
        return bArr2;
    }

    public static void validatePublicKey(ECPublicKey eCPublicKey, ECPrivateKey eCPrivateKey) throws GeneralSecurityException {
        validatePublicKeySpec(eCPublicKey, eCPrivateKey);
        EllipticCurvesUtil.checkPointOnCurve(eCPublicKey.getW(), eCPrivateKey.getParams().getCurve());
    }

    public static void validatePublicKeySpec(ECPublicKey eCPublicKey, ECPrivateKey eCPrivateKey) throws GeneralSecurityException {
        try {
            if (isSameEcParameterSpec(eCPublicKey.getParams(), eCPrivateKey.getParams())) {
            } else {
                throw new GeneralSecurityException("invalid public key spec");
            }
        } catch (IllegalArgumentException | NullPointerException e2) {
            throw new GeneralSecurityException(e2);
        }
    }

    private static void validateSharedSecret(byte[] bArr, ECPrivateKey eCPrivateKey) throws GeneralSecurityException {
        EllipticCurve curve = eCPrivateKey.getParams().getCurve();
        BigInteger bigInteger = new BigInteger(1, bArr);
        if (bigInteger.signum() == -1 || bigInteger.compareTo(getModulus(curve)) >= 0) {
            throw new GeneralSecurityException("shared secret is out of range");
        }
        getY(bigInteger, true, curve);
    }

    public static byte[] computeSharedSecret(ECPrivateKey eCPrivateKey, ECPoint eCPoint) throws GeneralSecurityException {
        EllipticCurvesUtil.checkPointOnCurve(eCPoint, eCPrivateKey.getParams().getCurve());
        PublicKey publicKeyGeneratePublic = EngineFactory.KEY_FACTORY.getInstance("EC").generatePublic(new ECPublicKeySpec(eCPoint, eCPrivateKey.getParams()));
        KeyAgreement engineFactory = EngineFactory.KEY_AGREEMENT.getInstance("ECDH");
        engineFactory.init(eCPrivateKey);
        try {
            engineFactory.doPhase(publicKeyGeneratePublic, true);
            byte[] bArrGenerateSecret = engineFactory.generateSecret();
            validateSharedSecret(bArrGenerateSecret, eCPrivateKey);
            return bArrGenerateSecret;
        } catch (IllegalStateException e2) {
            throw new GeneralSecurityException(e2);
        }
    }

    public static KeyPair generateKeyPair(ECParameterSpec eCParameterSpec) throws InvalidAlgorithmParameterException {
        KeyPairGenerator engineFactory = EngineFactory.KEY_PAIR_GENERATOR.getInstance("EC");
        engineFactory.initialize(eCParameterSpec);
        return engineFactory.generateKeyPair();
    }

    public static ECPrivateKey getEcPrivateKey(byte[] bArr) {
        return (ECPrivateKey) EngineFactory.KEY_FACTORY.getInstance("EC").generatePrivate(new PKCS8EncodedKeySpec(bArr));
    }

    public static ECPublicKey getEcPublicKey(CurveType curveType, byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        ECParameterSpec curveSpec = getCurveSpec(curveType);
        ECPoint eCPoint = new ECPoint(new BigInteger(1, bArr), new BigInteger(1, bArr2));
        EllipticCurvesUtil.checkPointOnCurve(eCPoint, curveSpec.getCurve());
        return (ECPublicKey) EngineFactory.KEY_FACTORY.getInstance("EC").generatePublic(new ECPublicKeySpec(eCPoint, curveSpec));
    }

    public static ECPoint pointDecode(EllipticCurve ellipticCurve, PointFormatType pointFormatType, byte[] bArr) throws GeneralSecurityException {
        ECPoint eCPoint;
        int iFieldSizeInBytes = fieldSizeInBytes(ellipticCurve);
        int i2 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$PointFormatType[pointFormatType.ordinal()];
        boolean z2 = false;
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    throw new GeneralSecurityException("invalid format:" + pointFormatType);
                }
                BigInteger modulus = getModulus(ellipticCurve);
                if (bArr.length != iFieldSizeInBytes + 1) {
                    throw new GeneralSecurityException("compressed point has wrong length");
                }
                byte b2 = bArr[0];
                if (b2 != 2) {
                    if (b2 != 3) {
                        throw new GeneralSecurityException("invalid format");
                    }
                    z2 = true;
                }
                BigInteger bigInteger = new BigInteger(1, Arrays.copyOfRange(bArr, 1, bArr.length));
                if (bigInteger.signum() == -1 || bigInteger.compareTo(modulus) >= 0) {
                    throw new GeneralSecurityException("x is out of range");
                }
                return new ECPoint(bigInteger, getY(bigInteger, z2, ellipticCurve));
            }
            if (bArr.length != iFieldSizeInBytes * 2) {
                throw new GeneralSecurityException("invalid point size");
            }
            eCPoint = new ECPoint(new BigInteger(1, Arrays.copyOfRange(bArr, 0, iFieldSizeInBytes)), new BigInteger(1, Arrays.copyOfRange(bArr, iFieldSizeInBytes, bArr.length)));
        } else {
            if (bArr.length != (iFieldSizeInBytes * 2) + 1) {
                throw new GeneralSecurityException("invalid point size");
            }
            if (bArr[0] != 4) {
                throw new GeneralSecurityException("invalid point format");
            }
            int i3 = iFieldSizeInBytes + 1;
            eCPoint = new ECPoint(new BigInteger(1, Arrays.copyOfRange(bArr, 1, i3)), new BigInteger(1, Arrays.copyOfRange(bArr, i3, bArr.length)));
        }
        EllipticCurvesUtil.checkPointOnCurve(eCPoint, ellipticCurve);
        return eCPoint;
    }

    public static byte[] pointEncode(EllipticCurve ellipticCurve, PointFormatType pointFormatType, ECPoint eCPoint) throws GeneralSecurityException {
        EllipticCurvesUtil.checkPointOnCurve(eCPoint, ellipticCurve);
        int iFieldSizeInBytes = fieldSizeInBytes(ellipticCurve);
        int i2 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$PointFormatType[pointFormatType.ordinal()];
        if (i2 == 1) {
            int i3 = (iFieldSizeInBytes * 2) + 1;
            byte[] bArr = new byte[i3];
            byte[] bigEndianBytes = BigIntegerEncoding.toBigEndianBytes(eCPoint.getAffineX());
            byte[] bigEndianBytes2 = BigIntegerEncoding.toBigEndianBytes(eCPoint.getAffineY());
            System.arraycopy(bigEndianBytes2, 0, bArr, i3 - bigEndianBytes2.length, bigEndianBytes2.length);
            System.arraycopy(bigEndianBytes, 0, bArr, (iFieldSizeInBytes + 1) - bigEndianBytes.length, bigEndianBytes.length);
            bArr[0] = 4;
            return bArr;
        }
        if (i2 != 2) {
            if (i2 != 3) {
                throw new GeneralSecurityException("invalid format:" + pointFormatType);
            }
            int i4 = iFieldSizeInBytes + 1;
            byte[] bArr2 = new byte[i4];
            byte[] bigEndianBytes3 = BigIntegerEncoding.toBigEndianBytes(eCPoint.getAffineX());
            System.arraycopy(bigEndianBytes3, 0, bArr2, i4 - bigEndianBytes3.length, bigEndianBytes3.length);
            bArr2[0] = (byte) (eCPoint.getAffineY().testBit(0) ? 3 : 2);
            return bArr2;
        }
        int i5 = iFieldSizeInBytes * 2;
        byte[] bArr3 = new byte[i5];
        byte[] bigEndianBytes4 = BigIntegerEncoding.toBigEndianBytes(eCPoint.getAffineX());
        if (bigEndianBytes4.length > iFieldSizeInBytes) {
            bigEndianBytes4 = Arrays.copyOfRange(bigEndianBytes4, bigEndianBytes4.length - iFieldSizeInBytes, bigEndianBytes4.length);
        }
        byte[] bigEndianBytes5 = BigIntegerEncoding.toBigEndianBytes(eCPoint.getAffineY());
        if (bigEndianBytes5.length > iFieldSizeInBytes) {
            bigEndianBytes5 = Arrays.copyOfRange(bigEndianBytes5, bigEndianBytes5.length - iFieldSizeInBytes, bigEndianBytes5.length);
        }
        System.arraycopy(bigEndianBytes5, 0, bArr3, i5 - bigEndianBytes5.length, bigEndianBytes5.length);
        System.arraycopy(bigEndianBytes4, 0, bArr3, iFieldSizeInBytes - bigEndianBytes4.length, bigEndianBytes4.length);
        return bArr3;
    }

    public static ECPublicKey getEcPublicKey(ECParameterSpec eCParameterSpec, PointFormatType pointFormatType, byte[] bArr) {
        return (ECPublicKey) EngineFactory.KEY_FACTORY.getInstance("EC").generatePublic(new ECPublicKeySpec(pointDecode(eCParameterSpec.getCurve(), pointFormatType, bArr), eCParameterSpec));
    }

    public static ECPublicKey getEcPublicKey(byte[] bArr) {
        return (ECPublicKey) EngineFactory.KEY_FACTORY.getInstance("EC").generatePublic(new X509EncodedKeySpec(bArr));
    }
}
