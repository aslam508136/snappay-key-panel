package com.google.crypto.tink.subtle;

import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.shaded.protobuf.Reader;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
public final class AesCtrJceCipher implements IndCpaCipher {
    private static final String CIPHER_ALGORITHM = "AES/CTR/NoPadding";
    private static final String KEY_ALGORITHM = "AES";
    private static final int MIN_IV_SIZE_IN_BYTES = 12;
    private final int blockSize;
    private final int ivSize;
    private final SecretKeySpec keySpec;
    public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    private static final ThreadLocal<Cipher> localCipher = new ThreadLocal<Cipher>() { // from class: com.google.crypto.tink.subtle.AesCtrJceCipher.1
        @Override // java.lang.ThreadLocal
        public Cipher initialValue() {
            try {
                return EngineFactory.CIPHER.getInstance(AesCtrJceCipher.CIPHER_ALGORITHM);
            } catch (GeneralSecurityException e2) {
                throw new IllegalStateException(e2);
            }
        }
    };

    public AesCtrJceCipher(byte[] bArr, int i2) throws GeneralSecurityException {
        if (!FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use AES-CTR in FIPS-mode, as BoringCrypto module is not available.");
        }
        Validators.validateAesKeySize(bArr.length);
        this.keySpec = new SecretKeySpec(bArr, KEY_ALGORITHM);
        int blockSize = localCipher.get().getBlockSize();
        this.blockSize = blockSize;
        if (i2 < 12 || i2 > blockSize) {
            throw new GeneralSecurityException("invalid IV size");
        }
        this.ivSize = i2;
    }

    private void doCtr(byte[] bArr, int i2, int i3, byte[] bArr2, int i4, byte[] bArr3, boolean z2) throws GeneralSecurityException {
        Cipher cipher = localCipher.get();
        byte[] bArr4 = new byte[this.blockSize];
        System.arraycopy(bArr3, 0, bArr4, 0, this.ivSize);
        cipher.init(z2 ? 1 : 2, this.keySpec, new IvParameterSpec(bArr4));
        if (cipher.doFinal(bArr, i2, i3, bArr2, i4) != i3) {
            throw new GeneralSecurityException("stored output's length does not match input's length");
        }
    }

    @Override // com.google.crypto.tink.subtle.IndCpaCipher
    public byte[] decrypt(byte[] bArr) throws GeneralSecurityException {
        int length = bArr.length;
        int i2 = this.ivSize;
        if (length < i2) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, 0, bArr2, 0, i2);
        int length2 = bArr.length;
        int i3 = this.ivSize;
        byte[] bArr3 = new byte[length2 - i3];
        doCtr(bArr, i3, bArr.length - i3, bArr3, 0, bArr2, false);
        return bArr3;
    }

    @Override // com.google.crypto.tink.subtle.IndCpaCipher
    public byte[] encrypt(byte[] bArr) throws GeneralSecurityException {
        int length = bArr.length;
        int i2 = this.ivSize;
        if (length > Reader.READ_DONE - i2) {
            throw new GeneralSecurityException("plaintext length can not exceed " + (Reader.READ_DONE - this.ivSize));
        }
        byte[] bArr2 = new byte[bArr.length + i2];
        byte[] bArrRandBytes = Random.randBytes(i2);
        System.arraycopy(bArrRandBytes, 0, bArr2, 0, this.ivSize);
        doCtr(bArr, 0, bArr.length, bArr2, this.ivSize, bArrRandBytes, true);
        return bArr2;
    }
}
