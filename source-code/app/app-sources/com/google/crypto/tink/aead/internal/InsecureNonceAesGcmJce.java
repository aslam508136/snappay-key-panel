package com.google.crypto.tink.aead.internal;

import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.SubtleUtil;
import com.google.crypto.tink.subtle.Validators;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
public final class InsecureNonceAesGcmJce {
    public static final int IV_SIZE_IN_BYTES = 12;
    public static final int TAG_SIZE_IN_BYTES = 16;
    private final SecretKey keySpec;
    private final boolean prependIv;
    public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    private static final ThreadLocal<Cipher> localCipher = new ThreadLocal<Cipher>() { // from class: com.google.crypto.tink.aead.internal.InsecureNonceAesGcmJce.1
        @Override // java.lang.ThreadLocal
        public Cipher initialValue() {
            try {
                return EngineFactory.CIPHER.getInstance("AES/GCM/NoPadding");
            } catch (GeneralSecurityException e2) {
                throw new IllegalStateException(e2);
            }
        }
    };

    public InsecureNonceAesGcmJce(byte[] bArr, boolean z2) throws GeneralSecurityException {
        if (!FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use AES-GCM in FIPS-mode, as BoringCrypto module is not available.");
        }
        Validators.validateAesKeySize(bArr.length);
        this.keySpec = new SecretKeySpec(bArr, "AES");
        this.prependIv = z2;
    }

    private static AlgorithmParameterSpec getParams(byte[] bArr) {
        return getParams(bArr, 0, bArr.length);
    }

    public byte[] decrypt(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        if (bArr.length != 12) {
            throw new GeneralSecurityException("iv is wrong size");
        }
        boolean z2 = this.prependIv;
        if (bArr2.length < (z2 ? 28 : 16)) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        if (z2 && !ByteBuffer.wrap(bArr).equals(ByteBuffer.wrap(bArr2, 0, 12))) {
            throw new GeneralSecurityException("iv does not match prepended iv");
        }
        AlgorithmParameterSpec params = getParams(bArr);
        ThreadLocal<Cipher> threadLocal = localCipher;
        threadLocal.get().init(2, this.keySpec, params);
        if (bArr3 != null && bArr3.length != 0) {
            threadLocal.get().updateAAD(bArr3);
        }
        boolean z3 = this.prependIv;
        return threadLocal.get().doFinal(bArr2, z3 ? 12 : 0, z3 ? bArr2.length - 12 : bArr2.length);
    }

    public byte[] encrypt(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        if (bArr.length != 12) {
            throw new GeneralSecurityException("iv is wrong size");
        }
        if (bArr2.length > 2147483619) {
            throw new GeneralSecurityException("plaintext too long");
        }
        boolean z2 = this.prependIv;
        int length = bArr2.length;
        if (z2) {
            length += 12;
        }
        byte[] bArr4 = new byte[length + 16];
        if (z2) {
            System.arraycopy(bArr, 0, bArr4, 0, 12);
        }
        AlgorithmParameterSpec params = getParams(bArr);
        ThreadLocal<Cipher> threadLocal = localCipher;
        threadLocal.get().init(1, this.keySpec, params);
        if (bArr3 != null && bArr3.length != 0) {
            threadLocal.get().updateAAD(bArr3);
        }
        int iDoFinal = threadLocal.get().doFinal(bArr2, 0, bArr2.length, bArr4, this.prependIv ? 12 : 0);
        if (iDoFinal == bArr2.length + 16) {
            return bArr4;
        }
        throw new GeneralSecurityException(String.format("encryption failed; GCM tag must be %s bytes, but got only %s bytes", 16, Integer.valueOf(iDoFinal - bArr2.length)));
    }

    private static AlgorithmParameterSpec getParams(byte[] bArr, int i2, int i3) {
        return (!SubtleUtil.isAndroid() || SubtleUtil.androidApiLevel() > 19) ? new GCMParameterSpec(128, bArr, i2, i3) : new IvParameterSpec(bArr, i2, i3);
    }
}
