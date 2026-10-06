package com.google.crypto.tink.aead.internal;

import com.google.crypto.tink.config.internal.TinkFipsUtil;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.GeneralSecurityException;
import javax.crypto.AEADBadTagException;

/* JADX INFO: loaded from: classes.dex */
abstract class InsecureNonceChaCha20Poly1305Base {
    public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS;
    private final InsecureNonceChaCha20Base chacha20;
    private final InsecureNonceChaCha20Base macKeyChaCha20;

    public InsecureNonceChaCha20Poly1305Base(byte[] bArr) throws GeneralSecurityException {
        if (!FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use ChaCha20Poly1305 in FIPS-mode.");
        }
        this.chacha20 = newChaCha20Instance(bArr, 1);
        this.macKeyChaCha20 = newChaCha20Instance(bArr, 0);
    }

    private byte[] getMacKey(byte[] bArr) {
        byte[] bArr2 = new byte[32];
        this.macKeyChaCha20.chacha20Block(bArr, 0).get(bArr2);
        return bArr2;
    }

    private static byte[] macDataRfc8439(byte[] bArr, ByteBuffer byteBuffer) {
        int length = bArr.length % 16 == 0 ? bArr.length : (bArr.length + 16) - (bArr.length % 16);
        int iRemaining = byteBuffer.remaining();
        int i2 = iRemaining % 16;
        int i3 = (i2 == 0 ? iRemaining : (iRemaining + 16) - i2) + length;
        ByteBuffer byteBufferOrder = ByteBuffer.allocate(i3 + 16).order(ByteOrder.LITTLE_ENDIAN);
        byteBufferOrder.put(bArr);
        byteBufferOrder.position(length);
        byteBufferOrder.put(byteBuffer);
        byteBufferOrder.position(i3);
        byteBufferOrder.putLong(bArr.length);
        byteBufferOrder.putLong(iRemaining);
        return byteBufferOrder.array();
    }

    public byte[] decrypt(ByteBuffer byteBuffer, byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (byteBuffer.remaining() < 16) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        int iPosition = byteBuffer.position();
        byte[] bArr3 = new byte[16];
        byteBuffer.position(byteBuffer.limit() - 16);
        byteBuffer.get(bArr3);
        byteBuffer.position(iPosition);
        byteBuffer.limit(byteBuffer.limit() - 16);
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        try {
            Poly1305.verifyMac(getMacKey(bArr), macDataRfc8439(bArr2, byteBuffer), bArr3);
            byteBuffer.position(iPosition);
            return this.chacha20.decrypt(bArr, byteBuffer);
        } catch (GeneralSecurityException e2) {
            throw new AEADBadTagException(e2.toString());
        }
    }

    public void encrypt(ByteBuffer byteBuffer, byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        if (byteBuffer.remaining() < bArr2.length + 16) {
            throw new IllegalArgumentException("Given ByteBuffer output is too small");
        }
        int iPosition = byteBuffer.position();
        this.chacha20.encrypt(byteBuffer, bArr, bArr2);
        byteBuffer.position(iPosition);
        byteBuffer.limit(byteBuffer.limit() - 16);
        if (bArr3 == null) {
            bArr3 = new byte[0];
        }
        byte[] bArrComputeMac = Poly1305.computeMac(getMacKey(bArr), macDataRfc8439(bArr3, byteBuffer));
        byteBuffer.limit(byteBuffer.limit() + 16);
        byteBuffer.put(bArrComputeMac);
    }

    public abstract InsecureNonceChaCha20Base newChaCha20Instance(byte[] bArr, int i2);

    public byte[] decrypt(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return decrypt(ByteBuffer.wrap(bArr2), bArr, bArr3);
    }

    public byte[] encrypt(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        if (bArr2.length > 2147483631) {
            throw new GeneralSecurityException("plaintext too long");
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bArr2.length + 16);
        encrypt(byteBufferAllocate, bArr, bArr2, bArr3);
        return byteBufferAllocate.array();
    }
}
