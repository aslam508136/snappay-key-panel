package com.google.crypto.tink.subtle;

import java.security.interfaces.ECPrivateKey;

/* JADX INFO: loaded from: classes.dex */
public final class EciesHkdfRecipientKem {
    private ECPrivateKey recipientPrivateKey;

    public EciesHkdfRecipientKem(ECPrivateKey eCPrivateKey) {
        this.recipientPrivateKey = eCPrivateKey;
    }

    public byte[] generateKey(byte[] bArr, String str, byte[] bArr2, byte[] bArr3, int i2, EllipticCurves.PointFormatType pointFormatType) {
        return Hkdf.computeEciesHkdfSymmetricKey(bArr, EllipticCurves.computeSharedSecret(this.recipientPrivateKey, EllipticCurves.getEcPublicKey(this.recipientPrivateKey.getParams(), pointFormatType, bArr)), str, bArr2, bArr3, i2);
    }
}
