package com.google.crypto.tink.internal;

import com.google.crypto.tink.subtle.Random;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.spec.ECField;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;

/* JADX INFO: loaded from: classes.dex */
public final class EllipticCurvesUtil {
    public static final ECParameterSpec NIST_P256_PARAMS = getNistP256Params();
    public static final ECParameterSpec NIST_P384_PARAMS = getNistP384Params();
    public static final ECParameterSpec NIST_P521_PARAMS = getNistP521Params();
    private static final BigInteger TWO = BigInteger.valueOf(2);
    private static final BigInteger THREE = BigInteger.valueOf(3);
    private static final BigInteger FOUR = BigInteger.valueOf(4);
    private static final BigInteger EIGHT = BigInteger.valueOf(8);

    public static class JacobianEcPoint {
        static final JacobianEcPoint INFINITY;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        BigInteger f510x;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        BigInteger f511y;

        /* JADX INFO: renamed from: z, reason: collision with root package name */
        BigInteger f512z;

        static {
            BigInteger bigInteger = BigInteger.ONE;
            INFINITY = new JacobianEcPoint(bigInteger, bigInteger, BigInteger.ZERO);
        }

        public JacobianEcPoint(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
            this.f510x = bigInteger;
            this.f511y = bigInteger2;
            this.f512z = bigInteger3;
        }

        public boolean isInfinity() {
            return this.f512z.equals(BigInteger.ZERO);
        }

        public ECPoint toECPoint(BigInteger bigInteger) {
            if (isInfinity()) {
                return ECPoint.POINT_INFINITY;
            }
            BigInteger bigIntegerModInverse = this.f512z.modInverse(bigInteger);
            BigInteger bigIntegerMod = bigIntegerModInverse.multiply(bigIntegerModInverse).mod(bigInteger);
            return new ECPoint(this.f510x.multiply(bigIntegerMod).mod(bigInteger), this.f511y.multiply(bigIntegerMod).mod(bigInteger).multiply(bigIntegerModInverse).mod(bigInteger));
        }
    }

    private EllipticCurvesUtil() {
    }

    public static JacobianEcPoint addJacobianPoints(JacobianEcPoint jacobianEcPoint, JacobianEcPoint jacobianEcPoint2, BigInteger bigInteger, BigInteger bigInteger2) {
        if (jacobianEcPoint.isInfinity()) {
            return jacobianEcPoint2;
        }
        if (jacobianEcPoint2.isInfinity()) {
            return jacobianEcPoint;
        }
        BigInteger bigInteger3 = jacobianEcPoint.f512z;
        BigInteger bigIntegerMod = bigInteger3.multiply(bigInteger3).mod(bigInteger2);
        BigInteger bigInteger4 = jacobianEcPoint2.f512z;
        BigInteger bigIntegerMod2 = bigInteger4.multiply(bigInteger4).mod(bigInteger2);
        BigInteger bigIntegerMod3 = jacobianEcPoint.f510x.multiply(bigIntegerMod2).mod(bigInteger2);
        BigInteger bigIntegerMod4 = jacobianEcPoint2.f510x.multiply(bigIntegerMod).mod(bigInteger2);
        BigInteger bigIntegerMod5 = jacobianEcPoint.f511y.multiply(jacobianEcPoint2.f512z).mod(bigInteger2).multiply(bigIntegerMod2).mod(bigInteger2);
        BigInteger bigIntegerMod6 = jacobianEcPoint2.f511y.multiply(jacobianEcPoint.f512z).mod(bigInteger2).multiply(bigIntegerMod).mod(bigInteger2);
        if (bigIntegerMod3.equals(bigIntegerMod4)) {
            return !bigIntegerMod5.equals(bigIntegerMod6) ? JacobianEcPoint.INFINITY : doubleJacobianPoint(jacobianEcPoint, bigInteger, bigInteger2);
        }
        BigInteger bigIntegerMod7 = bigIntegerMod4.subtract(bigIntegerMod3).mod(bigInteger2);
        BigInteger bigIntegerMod8 = bigIntegerMod7.multiply(FOUR).multiply(bigIntegerMod7).mod(bigInteger2);
        BigInteger bigIntegerMod9 = bigIntegerMod7.multiply(bigIntegerMod8).mod(bigInteger2);
        BigInteger bigIntegerSubtract = bigIntegerMod6.subtract(bigIntegerMod5);
        BigInteger bigInteger5 = TWO;
        BigInteger bigIntegerMod10 = bigIntegerSubtract.multiply(bigInteger5).mod(bigInteger2);
        BigInteger bigIntegerMod11 = bigIntegerMod3.multiply(bigIntegerMod8).mod(bigInteger2);
        BigInteger bigIntegerMod12 = bigIntegerMod10.multiply(bigIntegerMod10).mod(bigInteger2).subtract(bigIntegerMod9).subtract(bigIntegerMod11.multiply(bigInteger5)).mod(bigInteger2);
        BigInteger bigIntegerMod13 = bigIntegerMod10.multiply(bigIntegerMod11.subtract(bigIntegerMod12)).subtract(bigIntegerMod5.multiply(bigInteger5).multiply(bigIntegerMod9)).mod(bigInteger2);
        BigInteger bigIntegerAdd = jacobianEcPoint.f512z.add(jacobianEcPoint2.f512z);
        return new JacobianEcPoint(bigIntegerMod12, bigIntegerMod13, bigIntegerAdd.multiply(bigIntegerAdd).mod(bigInteger2).subtract(bigIntegerMod).subtract(bigIntegerMod2).multiply(bigIntegerMod7).mod(bigInteger2));
    }

    public static void checkPointOnCurve(ECPoint eCPoint, EllipticCurve ellipticCurve) throws GeneralSecurityException {
        BigInteger modulus = getModulus(ellipticCurve);
        BigInteger affineX = eCPoint.getAffineX();
        BigInteger affineY = eCPoint.getAffineY();
        if (affineX == null || affineY == null) {
            throw new GeneralSecurityException("point is at infinity");
        }
        if (affineX.signum() == -1 || affineX.compareTo(modulus) >= 0) {
            throw new GeneralSecurityException("x is out of range");
        }
        if (affineY.signum() == -1 || affineY.compareTo(modulus) >= 0) {
            throw new GeneralSecurityException("y is out of range");
        }
        if (!affineY.multiply(affineY).mod(modulus).equals(affineX.multiply(affineX).add(ellipticCurve.getA()).multiply(affineX).add(ellipticCurve.getB()).mod(modulus))) {
            throw new GeneralSecurityException("Point is not on curve");
        }
    }

    public static JacobianEcPoint doubleJacobianPoint(JacobianEcPoint jacobianEcPoint, BigInteger bigInteger, BigInteger bigInteger2) {
        if (jacobianEcPoint.f511y.equals(BigInteger.ZERO)) {
            return JacobianEcPoint.INFINITY;
        }
        BigInteger bigInteger3 = jacobianEcPoint.f510x;
        BigInteger bigIntegerMod = bigInteger3.multiply(bigInteger3).mod(bigInteger2);
        BigInteger bigInteger4 = jacobianEcPoint.f511y;
        BigInteger bigIntegerMod2 = bigInteger4.multiply(bigInteger4).mod(bigInteger2);
        BigInteger bigIntegerMod3 = bigIntegerMod2.multiply(bigIntegerMod2).mod(bigInteger2);
        BigInteger bigInteger5 = jacobianEcPoint.f512z;
        BigInteger bigIntegerMod4 = bigInteger5.multiply(bigInteger5).mod(bigInteger2);
        BigInteger bigIntegerAdd = jacobianEcPoint.f510x.add(bigIntegerMod2);
        BigInteger bigIntegerSubtract = bigIntegerAdd.multiply(bigIntegerAdd).mod(bigInteger2).subtract(bigIntegerMod).subtract(bigIntegerMod3);
        BigInteger bigInteger6 = TWO;
        BigInteger bigIntegerMultiply = bigIntegerSubtract.multiply(bigInteger6);
        BigInteger bigIntegerAdd2 = bigIntegerMod.multiply(THREE).add(bigInteger.multiply(bigIntegerMod4).multiply(bigIntegerMod4).mod(bigInteger2));
        BigInteger bigIntegerMod5 = bigIntegerAdd2.multiply(bigIntegerAdd2).mod(bigInteger2).subtract(bigIntegerMultiply.multiply(bigInteger6)).mod(bigInteger2);
        BigInteger bigIntegerMod6 = bigIntegerAdd2.multiply(bigIntegerMultiply.subtract(bigIntegerMod5)).mod(bigInteger2).subtract(bigIntegerMod3.multiply(EIGHT)).mod(bigInteger2);
        BigInteger bigIntegerAdd3 = jacobianEcPoint.f511y.add(jacobianEcPoint.f512z);
        return new JacobianEcPoint(bigIntegerMod5, bigIntegerMod6, bigIntegerAdd3.multiply(bigIntegerAdd3).mod(bigInteger2).subtract(bigIntegerMod2).subtract(bigIntegerMod4).mod(bigInteger2));
    }

    public static BigInteger getModulus(EllipticCurve ellipticCurve) throws GeneralSecurityException {
        ECField field = ellipticCurve.getField();
        if (field instanceof ECFieldFp) {
            return ((ECFieldFp) field).getP();
        }
        throw new GeneralSecurityException("Only curves over prime order fields are supported");
    }

    private static ECParameterSpec getNistCurveSpec(String str, String str2, String str3, String str4, String str5) {
        BigInteger bigInteger = new BigInteger(str);
        return new ECParameterSpec(new EllipticCurve(new ECFieldFp(bigInteger), bigInteger.subtract(new BigInteger("3")), new BigInteger(str3, 16)), new ECPoint(new BigInteger(str4, 16), new BigInteger(str5, 16)), new BigInteger(str2), 1);
    }

    private static ECParameterSpec getNistP256Params() {
        return getNistCurveSpec("115792089210356248762697446949407573530086143415290314195533631308867097853951", "115792089210356248762697446949407573529996955224135760342422259061068512044369", "5ac635d8aa3a93e7b3ebbd55769886bc651d06b0cc53b0f63bce3c3e27d2604b", "6b17d1f2e12c4247f8bce6e563a440f277037d812deb33a0f4a13945d898c296", "4fe342e2fe1a7f9b8ee7eb4a7c0f9e162bce33576b315ececbb6406837bf51f5");
    }

    private static ECParameterSpec getNistP384Params() {
        return getNistCurveSpec("39402006196394479212279040100143613805079739270465446667948293404245721771496870329047266088258938001861606973112319", "39402006196394479212279040100143613805079739270465446667946905279627659399113263569398956308152294913554433653942643", "b3312fa7e23ee7e4988e056be3f82d19181d9c6efe8141120314088f5013875ac656398d8a2ed19d2a85c8edd3ec2aef", "aa87ca22be8b05378eb1c71ef320ad746e1d3b628ba79b9859f741e082542a385502f25dbf55296c3a545e3872760ab7", "3617de4a96262c6f5d9e98bf9292dc29f8f41dbd289a147ce9da3113b5f0b8c00a60b1ce1d7e819d7a431d7c90ea0e5f");
    }

    private static ECParameterSpec getNistP521Params() {
        return getNistCurveSpec("6864797660130609714981900799081393217269435300143305409394463459185543183397656052122559640661454554977296311391480858037121987999716643812574028291115057151", "6864797660130609714981900799081393217269435300143305409394463459185543183397655394245057746333217197532963996371363321113864768612440380340372808892707005449", "051953eb9618e1c9a1f929a21a0b68540eea2da725b99b315f3b8b489918ef109e156193951ec7e937b1652c0bd3bb1bf073573df883d2c34f1ef451fd46b503f00", "c6858e06b70404e9cd9e3ecb662395b4429c648139053fb521f828af606b4d3dbaa14b5e77efe75928fe1dc127a2ffa8de3348b3c1856a429bf97e7e31c2e5bd66", "11839296a789a3bc0045c8a5fb42c7d1bd998f54449579b446817afbd17273e662c97ee72995ef42640c550b9013fad0761353c7086a272c24088be94769fd16650");
    }

    public static boolean isNistEcParameterSpec(ECParameterSpec eCParameterSpec) {
        return isSameEcParameterSpec(eCParameterSpec, NIST_P256_PARAMS) || isSameEcParameterSpec(eCParameterSpec, NIST_P384_PARAMS) || isSameEcParameterSpec(eCParameterSpec, NIST_P521_PARAMS);
    }

    public static boolean isSameEcParameterSpec(ECParameterSpec eCParameterSpec, ECParameterSpec eCParameterSpec2) {
        return eCParameterSpec.getCurve().equals(eCParameterSpec2.getCurve()) && eCParameterSpec.getGenerator().equals(eCParameterSpec2.getGenerator()) && eCParameterSpec.getOrder().equals(eCParameterSpec2.getOrder()) && eCParameterSpec.getCofactor() == eCParameterSpec2.getCofactor();
    }

    public static ECPoint multiplyByGenerator(BigInteger bigInteger, ECParameterSpec eCParameterSpec) throws GeneralSecurityException {
        if (!isNistEcParameterSpec(eCParameterSpec)) {
            throw new GeneralSecurityException("spec must be NIST P256, P384 or P521");
        }
        if (bigInteger.signum() != 1) {
            throw new GeneralSecurityException("k must be positive");
        }
        if (bigInteger.compareTo(eCParameterSpec.getOrder()) >= 0) {
            throw new GeneralSecurityException("k must be smaller than the order of the generator");
        }
        EllipticCurve curve = eCParameterSpec.getCurve();
        ECPoint generator = eCParameterSpec.getGenerator();
        checkPointOnCurve(generator, curve);
        BigInteger a2 = eCParameterSpec.getCurve().getA();
        BigInteger modulus = getModulus(curve);
        JacobianEcPoint jacobianEcPoint = toJacobianEcPoint(ECPoint.POINT_INFINITY, modulus);
        JacobianEcPoint jacobianEcPoint2 = toJacobianEcPoint(generator, modulus);
        for (int iBitLength = bigInteger.bitLength(); iBitLength >= 0; iBitLength--) {
            if (bigInteger.testBit(iBitLength)) {
                jacobianEcPoint = addJacobianPoints(jacobianEcPoint, jacobianEcPoint2, a2, modulus);
                jacobianEcPoint2 = doubleJacobianPoint(jacobianEcPoint2, a2, modulus);
            } else {
                jacobianEcPoint2 = addJacobianPoints(jacobianEcPoint, jacobianEcPoint2, a2, modulus);
                jacobianEcPoint = doubleJacobianPoint(jacobianEcPoint, a2, modulus);
            }
        }
        ECPoint eCPoint = jacobianEcPoint.toECPoint(modulus);
        checkPointOnCurve(eCPoint, curve);
        return eCPoint;
    }

    public static JacobianEcPoint toJacobianEcPoint(ECPoint eCPoint, BigInteger bigInteger) {
        if (eCPoint.equals(ECPoint.POINT_INFINITY)) {
            return JacobianEcPoint.INFINITY;
        }
        BigInteger bigIntegerMod = new BigInteger(1, Random.randBytes((bigInteger.bitLength() + 8) / 8)).mod(bigInteger);
        BigInteger bigIntegerMod2 = bigIntegerMod.multiply(bigIntegerMod).mod(bigInteger);
        return new JacobianEcPoint(eCPoint.getAffineX().multiply(bigIntegerMod2).mod(bigInteger), eCPoint.getAffineY().multiply(bigIntegerMod2.multiply(bigIntegerMod).mod(bigInteger)).mod(bigInteger), bigIntegerMod);
    }
}
