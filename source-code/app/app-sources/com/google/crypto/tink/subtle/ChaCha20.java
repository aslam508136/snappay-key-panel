package com.google.crypto.tink.subtle;

import com.google.crypto.tink.aead.internal.InsecureNonceChaCha20;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
class ChaCha20 implements IndCpaCipher {
    static final int NONCE_LENGTH_IN_BYTES = 12;
    private final InsecureNonceChaCha20 cipher;

    public ChaCha20(byte[] bArr, int i2) {
        this.cipher = new InsecureNonceChaCha20(bArr, i2);
    }

    @Override // com.google.crypto.tink.subtle.IndCpaCipher
    public byte[] decrypt(byte[] bArr) throws GeneralSecurityException {
        if (bArr.length < 12) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        return this.cipher.decrypt(Arrays.copyOf(bArr, 12), ByteBuffer.wrap(bArr, 12, bArr.length - 12));
    }

    @Override // com.google.crypto.tink.subtle.IndCpaCipher
    public byte[] encrypt(byte[] bArr) throws GeneralSecurityException {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bArr.length + 12);
        byte[] bArrRandBytes = Random.randBytes(12);
        byteBufferAllocate.put(bArrRandBytes);
        this.cipher.encrypt(byteBufferAllocate, bArrRandBytes, bArr);
        return byteBufferAllocate.array();
    }
}
