package com.google.crypto.tink.subtle;

import com.google.crypto.tink.KeyWrap;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
public class Kwp implements KeyWrap {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int MAX_WRAP_KEY_SIZE = 4096;
    static final int MIN_WRAP_KEY_SIZE = 16;
    static final byte[] PREFIX = {-90, 89, 89, -90};
    static final int ROUNDS = 6;
    private final SecretKey aesKey;

    public Kwp(byte[] bArr) throws GeneralSecurityException {
        if (bArr.length != 16 && bArr.length != 32) {
            throw new GeneralSecurityException("Unsupported key length");
        }
        this.aesKey = new SecretKeySpec(bArr, "AES");
    }

    private byte[] computeW(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr2.length <= 8 || bArr2.length > 2147483631 || bArr.length != 8) {
            throw new GeneralSecurityException("computeW called with invalid parameters");
        }
        int iWrappingSize = wrappingSize(bArr2.length);
        byte[] bArr3 = new byte[iWrappingSize];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, 8, bArr2.length);
        int i2 = 1;
        int i3 = (iWrappingSize / 8) - 1;
        Cipher engineFactory = EngineFactory.CIPHER.getInstance("AES/ECB/NoPadding");
        engineFactory.init(1, this.aesKey);
        byte[] bArr4 = new byte[16];
        System.arraycopy(bArr3, 0, bArr4, 0, 8);
        int i4 = 0;
        while (i4 < 6) {
            int i5 = 0;
            while (i5 < i3) {
                int i6 = i5 + 1;
                int i7 = i6 * 8;
                System.arraycopy(bArr3, i7, bArr4, 8, 8);
                engineFactory.doFinal(bArr4, 0, 16, bArr4);
                int i8 = (i4 * i3) + i5 + i2;
                for (int i9 = 0; i9 < 4; i9++) {
                    int i10 = 7 - i9;
                    bArr4[i10] = (byte) (((byte) (i8 & 255)) ^ bArr4[i10]);
                    i8 >>>= 8;
                }
                System.arraycopy(bArr4, 8, bArr3, i7, 8);
                i5 = i6;
                i2 = 1;
            }
            i4++;
            i2 = 1;
        }
        System.arraycopy(bArr4, 0, bArr3, 0, 8);
        return bArr3;
    }

    private byte[] invertW(byte[] bArr) throws GeneralSecurityException {
        if (bArr.length < 24 || bArr.length % 8 != 0) {
            throw new GeneralSecurityException("Incorrect data size");
        }
        byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
        int length = (bArrCopyOf.length / 8) - 1;
        Cipher engineFactory = EngineFactory.CIPHER.getInstance("AES/ECB/NoPadding");
        engineFactory.init(2, this.aesKey);
        byte[] bArr2 = new byte[16];
        System.arraycopy(bArrCopyOf, 0, bArr2, 0, 8);
        for (int i2 = 5; i2 >= 0; i2--) {
            for (int i3 = length - 1; i3 >= 0; i3--) {
                int i4 = (i3 + 1) * 8;
                System.arraycopy(bArrCopyOf, i4, bArr2, 8, 8);
                int i5 = (i2 * length) + i3 + 1;
                for (int i6 = 0; i6 < 4; i6++) {
                    int i7 = 7 - i6;
                    bArr2[i7] = (byte) (bArr2[i7] ^ ((byte) (i5 & 255)));
                    i5 >>>= 8;
                }
                engineFactory.doFinal(bArr2, 0, 16, bArr2);
                System.arraycopy(bArr2, 8, bArrCopyOf, i4, 8);
            }
        }
        System.arraycopy(bArr2, 0, bArrCopyOf, 0, 8);
        return bArrCopyOf;
    }

    private int wrappingSize(int i2) {
        return (7 - ((i2 + 7) % 8)) + i2 + 8;
    }

    @Override // com.google.crypto.tink.KeyWrap
    public byte[] unwrap(byte[] bArr) throws GeneralSecurityException {
        int i2;
        if (bArr.length < wrappingSize(16)) {
            throw new GeneralSecurityException("Wrapped key size is too small");
        }
        if (bArr.length > wrappingSize(4096)) {
            throw new GeneralSecurityException("Wrapped key size is too large");
        }
        if (bArr.length % 8 != 0) {
            throw new GeneralSecurityException("Wrapped key size must be a multiple of 8 bytes");
        }
        byte[] bArrInvertW = invertW(bArr);
        boolean z2 = true;
        boolean z3 = $assertionsDisabled;
        int i3 = 0;
        while (true) {
            if (i3 >= 4) {
                break;
            }
            if (PREFIX[i3] != bArrInvertW[i3]) {
                z2 = $assertionsDisabled;
            }
            i3++;
        }
        int i4 = 0;
        for (i2 = 4; i2 < 8; i2++) {
            i4 = (i4 << 8) + (bArrInvertW[i2] & 255);
        }
        if (wrappingSize(i4) == bArrInvertW.length) {
            for (int i5 = i4 + 8; i5 < bArrInvertW.length; i5++) {
                if (bArrInvertW[i5] != 0) {
                    z2 = $assertionsDisabled;
                }
            }
            z3 = z2;
        }
        if (z3) {
            return Arrays.copyOfRange(bArrInvertW, 8, i4 + 8);
        }
        throw new BadPaddingException("Invalid padding");
    }

    @Override // com.google.crypto.tink.KeyWrap
    public byte[] wrap(byte[] bArr) throws GeneralSecurityException {
        if (bArr.length < 16) {
            throw new GeneralSecurityException("Key size of key to wrap too small");
        }
        if (bArr.length > 4096) {
            throw new GeneralSecurityException("Key size of key to wrap too large");
        }
        byte[] bArr2 = new byte[8];
        byte[] bArr3 = PREFIX;
        System.arraycopy(bArr3, 0, bArr2, 0, bArr3.length);
        for (int i2 = 0; i2 < 4; i2++) {
            bArr2[i2 + 4] = (byte) ((bArr.length >> ((3 - i2) * 8)) & 255);
        }
        return computeW(bArr2, bArr);
    }
}
