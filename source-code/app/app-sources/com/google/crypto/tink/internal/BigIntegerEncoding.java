package com.google.crypto.tink.internal;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class BigIntegerEncoding {
    private BigIntegerEncoding() {
    }

    public static BigInteger fromUnsignedBigEndianBytes(byte[] bArr) {
        return new BigInteger(1, bArr);
    }

    public static byte[] toBigEndianBytes(BigInteger bigInteger) {
        if (bigInteger.signum() != -1) {
            return bigInteger.toByteArray();
        }
        throw new IllegalArgumentException("n must not be negative");
    }

    public static byte[] toBigEndianBytesOfFixedLength(BigInteger bigInteger, int i2) throws GeneralSecurityException {
        if (bigInteger.signum() == -1) {
            throw new IllegalArgumentException("integer must be nonnegative");
        }
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray.length == i2) {
            return byteArray;
        }
        int i3 = i2 + 1;
        if (byteArray.length > i3) {
            throw new GeneralSecurityException("integer too large");
        }
        if (byteArray.length == i3) {
            if (byteArray[0] == 0) {
                return Arrays.copyOfRange(byteArray, 1, byteArray.length);
            }
            throw new GeneralSecurityException("integer too large");
        }
        byte[] bArr = new byte[i2];
        System.arraycopy(byteArray, 0, bArr, i2 - byteArray.length, byteArray.length);
        return bArr;
    }
}
