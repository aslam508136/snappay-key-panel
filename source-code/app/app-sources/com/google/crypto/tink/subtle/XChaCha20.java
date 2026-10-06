package com.google.crypto.tink.subtle;

import com.google.crypto.tink.aead.internal.InsecureNonceXChaCha20;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
class XChaCha20 implements IndCpaCipher {
    static final int NONCE_LENGTH_IN_BYTES = 24;
    private final InsecureNonceXChaCha20 cipher;

    public XChaCha20(byte[] bArr, int i2) {
        this.cipher = new InsecureNonceXChaCha20(bArr, i2);
    }

    @Override // com.google.crypto.tink.subtle.IndCpaCipher
    public byte[] decrypt(byte[] bArr) throws GeneralSecurityException {
        if (bArr.length < 24) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        return this.cipher.decrypt(Arrays.copyOf(bArr, 24), ByteBuffer.wrap(bArr, 24, bArr.length - 24));
    }

    @Override // com.google.crypto.tink.subtle.IndCpaCipher
    public byte[] encrypt(byte[] bArr) throws GeneralSecurityException {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bArr.length + 24);
        byte[] bArrRandBytes = Random.randBytes(24);
        byteBufferAllocate.put(bArrRandBytes);
        this.cipher.encrypt(byteBufferAllocate, bArrRandBytes, bArr);
        return byteBufferAllocate.array();
    }
}
