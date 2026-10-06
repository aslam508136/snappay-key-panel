package com.google.crypto.tink.subtle;

import com.google.crypto.tink.annotations.Alpha;
import java.security.InvalidKeyException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
@Alpha
public final class X25519 {
    private X25519() {
    }

    public static byte[] computeSharedSecret(byte[] bArr, byte[] bArr2) throws InvalidKeyException {
        if (bArr.length != 32) {
            throw new InvalidKeyException("Private key must have 32 bytes.");
        }
        long[] jArr = new long[11];
        byte[] bArrCopyOf = Arrays.copyOf(bArr, 32);
        bArrCopyOf[0] = (byte) (bArrCopyOf[0] & 248);
        byte b2 = (byte) (bArrCopyOf[31] & 127);
        bArrCopyOf[31] = b2;
        bArrCopyOf[31] = (byte) (b2 | 64);
        Curve25519.curveMult(jArr, bArrCopyOf, bArr2);
        return Field25519.contract(jArr);
    }

    public static byte[] generatePrivateKey() {
        byte[] bArrRandBytes = Random.randBytes(32);
        bArrRandBytes[0] = (byte) (bArrRandBytes[0] | 7);
        byte b2 = (byte) (bArrRandBytes[31] & 63);
        bArrRandBytes[31] = b2;
        bArrRandBytes[31] = (byte) (b2 | 128);
        return bArrRandBytes;
    }

    public static byte[] publicFromPrivate(byte[] bArr) throws InvalidKeyException {
        if (bArr.length != 32) {
            throw new InvalidKeyException("Private key must have 32 bytes.");
        }
        byte[] bArr2 = new byte[32];
        bArr2[0] = 9;
        return computeSharedSecret(bArr, bArr2);
    }
}
