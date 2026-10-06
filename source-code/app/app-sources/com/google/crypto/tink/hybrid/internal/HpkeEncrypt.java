package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.HybridEncrypt;
import com.google.crypto.tink.proto.HpkeParams;
import com.google.crypto.tink.proto.HpkePublicKey;
import com.google.crypto.tink.subtle.Bytes;
import com.google.errorprone.annotations.Immutable;

/* JADX INFO: loaded from: classes.dex */
@Immutable
final class HpkeEncrypt implements HybridEncrypt {
    private static final byte[] EMPTY_ASSOCIATED_DATA = new byte[0];
    private final HpkeAead aead;
    private final HpkeKdf kdf;
    private final HpkeKem kem;
    private final HpkePublicKey recipientPublicKey;

    private HpkeEncrypt(HpkePublicKey hpkePublicKey, HpkeKem hpkeKem, HpkeKdf hpkeKdf, HpkeAead hpkeAead) {
        this.recipientPublicKey = hpkePublicKey;
        this.kem = hpkeKem;
        this.kdf = hpkeKdf;
        this.aead = hpkeAead;
    }

    public static HpkeEncrypt createHpkeEncrypt(HpkePublicKey hpkePublicKey) {
        if (hpkePublicKey.getPublicKey().isEmpty()) {
            throw new IllegalArgumentException("HpkePublicKey.public_key is empty.");
        }
        HpkeParams params = hpkePublicKey.getParams();
        return new HpkeEncrypt(hpkePublicKey, HpkePrimitiveFactory.createKem(params), HpkePrimitiveFactory.createKdf(params), HpkePrimitiveFactory.createAead(params));
    }

    @Override // com.google.crypto.tink.HybridEncrypt
    public byte[] encrypt(byte[] bArr, byte[] bArr2) {
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        HpkeContext hpkeContextCreateSenderContext = HpkeContext.createSenderContext(this.recipientPublicKey, this.kem, this.kdf, this.aead, bArr2);
        return Bytes.concat(hpkeContextCreateSenderContext.getEncapsulatedKey(), hpkeContextCreateSenderContext.seal(bArr, EMPTY_ASSOCIATED_DATA));
    }
}
