package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.aead.internal.InsecureNonceChaCha20Poly1305;
import com.google.errorprone.annotations.Immutable;
import java.security.InvalidAlgorithmParameterException;

/* JADX INFO: loaded from: classes.dex */
@Immutable
final class ChaCha20Poly1305HpkeAead implements HpkeAead {
    @Override // com.google.crypto.tink.hybrid.internal.HpkeAead
    public byte[] getAeadId() {
        return HpkeUtil.CHACHA20_POLY1305_AEAD_ID;
    }

    @Override // com.google.crypto.tink.hybrid.internal.HpkeAead
    public int getKeyLength() {
        return 32;
    }

    @Override // com.google.crypto.tink.hybrid.internal.HpkeAead
    public int getNonceLength() {
        return 12;
    }

    @Override // com.google.crypto.tink.hybrid.internal.HpkeAead
    public byte[] open(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) throws InvalidAlgorithmParameterException {
        if (bArr.length == getKeyLength()) {
            return new InsecureNonceChaCha20Poly1305(bArr).decrypt(bArr2, bArr3, bArr4);
        }
        throw new InvalidAlgorithmParameterException("Unexpected key length: " + getKeyLength());
    }

    @Override // com.google.crypto.tink.hybrid.internal.HpkeAead
    public byte[] seal(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) throws InvalidAlgorithmParameterException {
        if (bArr.length == getKeyLength()) {
            return new InsecureNonceChaCha20Poly1305(bArr).encrypt(bArr2, bArr3, bArr4);
        }
        throw new InvalidAlgorithmParameterException("Unexpected key length: " + getKeyLength());
    }
}
