package com.google.crypto.tink.hybrid.subtle;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.HybridDecrypt;
import com.google.crypto.tink.aead.subtle.AeadFactory;
import com.google.crypto.tink.subtle.Hkdf;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.interfaces.RSAPrivateKey;
import javax.crypto.Cipher;

/* JADX INFO: loaded from: classes.dex */
public final class RsaKemHybridDecrypt implements HybridDecrypt {
    private final AeadFactory aeadFactory;
    private final String hkdfHmacAlgo;
    private final byte[] hkdfSalt;
    private final RSAPrivateKey recipientPrivateKey;

    public RsaKemHybridDecrypt(RSAPrivateKey rSAPrivateKey, String str, byte[] bArr, AeadFactory aeadFactory) throws GeneralSecurityException {
        RsaKem.validateRsaModulus(rSAPrivateKey.getModulus());
        this.recipientPrivateKey = rSAPrivateKey;
        this.hkdfSalt = bArr;
        this.hkdfHmacAlgo = str;
        this.aeadFactory = aeadFactory;
    }

    @Override // com.google.crypto.tink.HybridDecrypt
    public byte[] decrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int iBigIntSizeInBytes = RsaKem.bigIntSizeInBytes(this.recipientPrivateKey.getModulus());
        if (bArr.length < iBigIntSizeInBytes) {
            throw new GeneralSecurityException(String.format("Ciphertext must be of at least size %d bytes, but got %d", Integer.valueOf(iBigIntSizeInBytes), Integer.valueOf(bArr.length)));
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byte[] bArr3 = new byte[iBigIntSizeInBytes];
        byteBufferWrap.get(bArr3);
        Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
        cipher.init(2, this.recipientPrivateKey);
        Aead aeadCreateAead = this.aeadFactory.createAead(Hkdf.computeHkdf(this.hkdfHmacAlgo, cipher.doFinal(bArr3), this.hkdfSalt, bArr2, this.aeadFactory.getKeySizeInBytes()));
        byte[] bArr4 = new byte[byteBufferWrap.remaining()];
        byteBufferWrap.get(bArr4);
        return aeadCreateAead.decrypt(bArr4, RsaKem.EMPTY_AAD);
    }
}
