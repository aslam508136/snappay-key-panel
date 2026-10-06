package com.google.crypto.tink.subtle;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

/* JADX INFO: loaded from: classes.dex */
public final class EciesHkdfSenderKem {
    private final ECPublicKey recipientPublicKey;

    public static final class KemKey {
        private final com.google.crypto.tink.util.Bytes kemBytes;
        private final com.google.crypto.tink.util.Bytes symmetricKey;

        public KemKey(byte[] bArr, byte[] bArr2) {
            if (bArr == null) {
                throw new NullPointerException("KemBytes must be non-null");
            }
            if (bArr2 == null) {
                throw new NullPointerException("symmetricKey must be non-null");
            }
            this.kemBytes = com.google.crypto.tink.util.Bytes.copyFrom(bArr);
            this.symmetricKey = com.google.crypto.tink.util.Bytes.copyFrom(bArr2);
        }

        public byte[] getKemBytes() {
            return this.kemBytes.toByteArray();
        }

        public byte[] getSymmetricKey() {
            return this.symmetricKey.toByteArray();
        }
    }

    public EciesHkdfSenderKem(ECPublicKey eCPublicKey) {
        this.recipientPublicKey = eCPublicKey;
    }

    public KemKey generateKey(String str, byte[] bArr, byte[] bArr2, int i2, EllipticCurves.PointFormatType pointFormatType) throws GeneralSecurityException {
        KeyPair keyPairGenerateKeyPair = EllipticCurves.generateKeyPair(this.recipientPublicKey.getParams());
        ECPublicKey eCPublicKey = (ECPublicKey) keyPairGenerateKeyPair.getPublic();
        byte[] bArrComputeSharedSecret = EllipticCurves.computeSharedSecret((ECPrivateKey) keyPairGenerateKeyPair.getPrivate(), this.recipientPublicKey);
        byte[] bArrPointEncode = EllipticCurves.pointEncode(eCPublicKey.getParams().getCurve(), pointFormatType, eCPublicKey.getW());
        return new KemKey(bArrPointEncode, Hkdf.computeEciesHkdfSymmetricKey(bArrPointEncode, bArrComputeSharedSecret, str, bArr, bArr2, i2));
    }
}
