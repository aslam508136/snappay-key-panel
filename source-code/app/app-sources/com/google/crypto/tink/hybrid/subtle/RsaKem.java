package com.google.crypto.tink.hybrid.subtle;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
class RsaKem {
    static final byte[] EMPTY_AAD = new byte[0];
    static final int MIN_RSA_KEY_LENGTH_BITS = 2048;

    private RsaKem() {
    }

    public static int bigIntSizeInBytes(BigInteger bigInteger) {
        return (bigInteger.bitLength() + 7) / 8;
    }

    public static byte[] bigIntToByteArray(BigInteger bigInteger, int i2) {
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray.length == i2) {
            return byteArray;
        }
        byte[] bArr = new byte[i2];
        if (byteArray.length == i2 + 1) {
            if (byteArray[0] != 0) {
                throw new IllegalArgumentException("Value is one-byte longer than the expected size, but its first byte is not 0");
            }
            System.arraycopy(byteArray, 1, bArr, 0, i2);
        } else {
            if (byteArray.length >= i2) {
                throw new IllegalArgumentException(String.format("Value has invalid length, must be of length at most (%d + 1), but got %d", Integer.valueOf(i2), Integer.valueOf(byteArray.length)));
            }
            System.arraycopy(byteArray, 0, bArr, i2 - byteArray.length, byteArray.length);
        }
        return bArr;
    }

    public static KeyPair generateRsaKeyPair(int i2) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(i2);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e2) {
            throw new IllegalStateException("No support for RSA algorithm.", e2);
        }
    }

    public static byte[] generateSecret(BigInteger bigInteger) {
        int iBigIntSizeInBytes = bigIntSizeInBytes(bigInteger);
        SecureRandom secureRandom = new SecureRandom();
        while (true) {
            BigInteger bigInteger2 = new BigInteger(bigInteger.bitLength(), secureRandom);
            if (bigInteger2.signum() > 0 && bigInteger2.compareTo(bigInteger) < 0) {
                return bigIntToByteArray(bigInteger2, iBigIntSizeInBytes);
            }
        }
    }

    public static void validateRsaModulus(BigInteger bigInteger) throws GeneralSecurityException {
        if (bigInteger.bitLength() < MIN_RSA_KEY_LENGTH_BITS) {
            throw new GeneralSecurityException(String.format("RSA key must be of at least size %d bits, but got %d", Integer.valueOf(MIN_RSA_KEY_LENGTH_BITS), Integer.valueOf(bigInteger.bitLength())));
        }
    }
}
