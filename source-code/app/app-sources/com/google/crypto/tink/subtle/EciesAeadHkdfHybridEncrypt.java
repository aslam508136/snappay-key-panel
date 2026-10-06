package com.google.crypto.tink.subtle;

import com.google.crypto.tink.HybridEncrypt;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.interfaces.ECPublicKey;

/* JADX INFO: loaded from: classes.dex */
public final class EciesAeadHkdfHybridEncrypt implements HybridEncrypt {
    private static final byte[] EMPTY_AAD = new byte[0];
    private final EciesAeadHkdfDemHelper demHelper;
    private final EllipticCurves.PointFormatType ecPointFormat;
    private final String hkdfHmacAlgo;
    private final byte[] hkdfSalt;
    private final EciesHkdfSenderKem senderKem;

    public EciesAeadHkdfHybridEncrypt(ECPublicKey eCPublicKey, byte[] bArr, String str, EllipticCurves.PointFormatType pointFormatType, EciesAeadHkdfDemHelper eciesAeadHkdfDemHelper) throws GeneralSecurityException {
        EllipticCurves.checkPublicKey(eCPublicKey);
        this.senderKem = new EciesHkdfSenderKem(eCPublicKey);
        this.hkdfSalt = bArr;
        this.hkdfHmacAlgo = str;
        this.ecPointFormat = pointFormatType;
        this.demHelper = eciesAeadHkdfDemHelper;
    }

    @Override // com.google.crypto.tink.HybridEncrypt
    public byte[] encrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        EciesHkdfSenderKem.KemKey kemKeyGenerateKey = this.senderKem.generateKey(this.hkdfHmacAlgo, this.hkdfSalt, bArr2, this.demHelper.getSymmetricKeySizeInBytes(), this.ecPointFormat);
        byte[] bArrEncrypt = this.demHelper.getAeadOrDaead(kemKeyGenerateKey.getSymmetricKey()).encrypt(bArr, EMPTY_AAD);
        byte[] kemBytes = kemKeyGenerateKey.getKemBytes();
        return ByteBuffer.allocate(kemBytes.length + bArrEncrypt.length).put(kemBytes).put(bArrEncrypt).array();
    }
}
