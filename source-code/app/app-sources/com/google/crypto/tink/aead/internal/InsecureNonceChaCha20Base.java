package com.google.crypto.tink.aead.internal;

import com.google.crypto.tink.subtle.Bytes;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

/* JADX INFO: loaded from: classes.dex */
abstract class InsecureNonceChaCha20Base {
    private final int initialCounter;
    int[] key;

    public InsecureNonceChaCha20Base(byte[] bArr, int i2) throws InvalidKeyException {
        if (bArr.length != 32) {
            throw new InvalidKeyException("The key length in bytes must be 32.");
        }
        this.key = ChaCha20Util.toIntArray(bArr);
        this.initialCounter = i2;
    }

    private void process(byte[] bArr, ByteBuffer byteBuffer, ByteBuffer byteBuffer2) throws GeneralSecurityException {
        if (bArr.length != nonceSizeInBytes()) {
            throw new GeneralSecurityException("The nonce length (in bytes) must be " + nonceSizeInBytes());
        }
        int iRemaining = byteBuffer2.remaining();
        int i2 = (iRemaining / 64) + 1;
        for (int i3 = 0; i3 < i2; i3++) {
            ByteBuffer byteBufferChacha20Block = chacha20Block(bArr, this.initialCounter + i3);
            if (i3 == i2 - 1) {
                Bytes.xor(byteBuffer, byteBuffer2, byteBufferChacha20Block, iRemaining % 64);
            } else {
                Bytes.xor(byteBuffer, byteBuffer2, byteBufferChacha20Block, 64);
            }
        }
    }

    public ByteBuffer chacha20Block(byte[] bArr, int i2) {
        int[] iArrCreateInitialState = createInitialState(ChaCha20Util.toIntArray(bArr), i2);
        int[] iArr = (int[]) iArrCreateInitialState.clone();
        ChaCha20Util.shuffleState(iArr);
        for (int i3 = 0; i3 < iArrCreateInitialState.length; i3++) {
            iArrCreateInitialState[i3] = iArrCreateInitialState[i3] + iArr[i3];
        }
        ByteBuffer byteBufferOrder = ByteBuffer.allocate(64).order(ByteOrder.LITTLE_ENDIAN);
        byteBufferOrder.asIntBuffer().put(iArrCreateInitialState, 0, 16);
        return byteBufferOrder;
    }

    public abstract int[] createInitialState(int[] iArr, int i2);

    public byte[] decrypt(byte[] bArr, ByteBuffer byteBuffer) throws GeneralSecurityException {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(byteBuffer.remaining());
        process(bArr, byteBufferAllocate, byteBuffer);
        return byteBufferAllocate.array();
    }

    public void encrypt(ByteBuffer byteBuffer, byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (byteBuffer.remaining() < bArr2.length) {
            throw new IllegalArgumentException("Given ByteBuffer output is too small");
        }
        process(bArr, byteBuffer, ByteBuffer.wrap(bArr2));
    }

    public abstract int nonceSizeInBytes();

    public byte[] decrypt(byte[] bArr, byte[] bArr2) {
        return decrypt(bArr, ByteBuffer.wrap(bArr2));
    }

    public byte[] encrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bArr2.length);
        encrypt(byteBufferAllocate, bArr, bArr2);
        return byteBufferAllocate.array();
    }
}
