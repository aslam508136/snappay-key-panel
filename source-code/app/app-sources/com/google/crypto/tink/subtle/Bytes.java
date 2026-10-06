package com.google.crypto.tink.subtle;

import com.google.crypto.tink.shaded.protobuf.Reader;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class Bytes {
    private Bytes() {
    }

    public static int byteArrayToInt(byte[] bArr) {
        return byteArrayToInt(bArr, bArr.length);
    }

    public static byte[] concat(byte[]... bArr) throws GeneralSecurityException {
        int length = 0;
        for (byte[] bArr2 : bArr) {
            if (length > Reader.READ_DONE - bArr2.length) {
                throw new GeneralSecurityException("exceeded size limit");
            }
            length += bArr2.length;
        }
        byte[] bArr3 = new byte[length];
        int length2 = 0;
        for (byte[] bArr4 : bArr) {
            System.arraycopy(bArr4, 0, bArr3, length2, bArr4.length);
            length2 += bArr4.length;
        }
        return bArr3;
    }

    public static final boolean equal(byte[] bArr, byte[] bArr2) {
        return MessageDigest.isEqual(bArr, bArr2);
    }

    public static byte[] intToByteArray(int i2, int i3) {
        byte[] bArr = new byte[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            bArr[i4] = (byte) ((i3 >> (i4 * 8)) & 255);
        }
        return bArr;
    }

    public static final void xor(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, ByteBuffer byteBuffer3, int i2) {
        if (i2 < 0 || byteBuffer2.remaining() < i2 || byteBuffer3.remaining() < i2 || byteBuffer.remaining() < i2) {
            throw new IllegalArgumentException("That combination of buffers, offsets and length to xor result in out-of-bond accesses.");
        }
        for (int i3 = 0; i3 < i2; i3++) {
            byteBuffer.put((byte) (byteBuffer2.get() ^ byteBuffer3.get()));
        }
    }

    public static final byte[] xorEnd(byte[] bArr, byte[] bArr2) {
        if (bArr.length < bArr2.length) {
            throw new IllegalArgumentException("xorEnd requires a.length >= b.length");
        }
        int length = bArr.length - bArr2.length;
        byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
        for (int i2 = 0; i2 < bArr2.length; i2++) {
            int i3 = length + i2;
            bArrCopyOf[i3] = (byte) (bArrCopyOf[i3] ^ bArr2[i2]);
        }
        return bArrCopyOf;
    }

    public static int byteArrayToInt(byte[] bArr, int i2) {
        return byteArrayToInt(bArr, 0, i2);
    }

    public static final byte[] xor(byte[] bArr, int i2, byte[] bArr2, int i3, int i4) {
        if (i4 < 0 || bArr.length - i4 < i2 || bArr2.length - i4 < i3) {
            throw new IllegalArgumentException("That combination of buffers, offsets and length to xor result in out-of-bond accesses.");
        }
        byte[] bArr3 = new byte[i4];
        for (int i5 = 0; i5 < i4; i5++) {
            bArr3[i5] = (byte) (bArr[i5 + i2] ^ bArr2[i5 + i3]);
        }
        return bArr3;
    }

    public static int byteArrayToInt(byte[] bArr, int i2, int i3) {
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            i4 += (bArr[i5 + i2] & 255) << (i5 * 8);
        }
        return i4;
    }

    public static final byte[] xor(byte[] bArr, byte[] bArr2) {
        if (bArr.length == bArr2.length) {
            return xor(bArr, 0, bArr2, 0, bArr.length);
        }
        throw new IllegalArgumentException("The lengths of x and y should match.");
    }
}
