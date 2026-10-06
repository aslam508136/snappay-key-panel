package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.subtle.EngineFactory;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
@Immutable
final class HkdfHpkeKdf implements HpkeKdf {
    private final String macAlgorithm;

    public HkdfHpkeKdf(String str) {
        this.macAlgorithm = str;
    }

    private byte[] expand(byte[] bArr, byte[] bArr2, int i2) throws GeneralSecurityException {
        Mac engineFactory = EngineFactory.MAC.getInstance(this.macAlgorithm);
        if (i2 > engineFactory.getMacLength() * 255) {
            throw new GeneralSecurityException("size too large");
        }
        byte[] bArr3 = new byte[i2];
        engineFactory.init(new SecretKeySpec(bArr, this.macAlgorithm));
        byte[] bArrDoFinal = new byte[0];
        int i3 = 1;
        int length = 0;
        while (true) {
            engineFactory.update(bArrDoFinal);
            engineFactory.update(bArr2);
            engineFactory.update((byte) i3);
            bArrDoFinal = engineFactory.doFinal();
            if (bArrDoFinal.length + length >= i2) {
                System.arraycopy(bArrDoFinal, 0, bArr3, length, i2 - length);
                return bArr3;
            }
            System.arraycopy(bArrDoFinal, 0, bArr3, length, bArrDoFinal.length);
            length += bArrDoFinal.length;
            i3++;
        }
    }

    private byte[] extract(byte[] bArr, byte[] bArr2) throws InvalidKeyException {
        Mac engineFactory = EngineFactory.MAC.getInstance(this.macAlgorithm);
        if (bArr2 == null || bArr2.length == 0) {
            engineFactory.init(new SecretKeySpec(new byte[engineFactory.getMacLength()], this.macAlgorithm));
        } else {
            engineFactory.init(new SecretKeySpec(bArr2, this.macAlgorithm));
        }
        return engineFactory.doFinal(bArr);
    }

    @Override // com.google.crypto.tink.hybrid.internal.HpkeKdf
    public byte[] extractAndExpand(byte[] bArr, byte[] bArr2, String str, byte[] bArr3, String str2, byte[] bArr4, int i2) {
        return expand(extract(HpkeUtil.labelIkm(str, bArr2, bArr4), bArr), HpkeUtil.labelInfo(str2, bArr3, bArr4, i2), i2);
    }

    @Override // com.google.crypto.tink.hybrid.internal.HpkeKdf
    public byte[] getKdfId() throws GeneralSecurityException {
        String str = this.macAlgorithm;
        str.getClass();
        switch (str) {
            case "HmacSha256":
                return HpkeUtil.HKDF_SHA256_KDF_ID;
            case "HmacSha384":
                return HpkeUtil.HKDF_SHA384_KDF_ID;
            case "HmacSha512":
                return HpkeUtil.HKDF_SHA512_KDF_ID;
            default:
                throw new GeneralSecurityException("Could not determine HPKE KDF ID");
        }
    }

    public int getMacLength() {
        return Mac.getInstance(this.macAlgorithm).getMacLength();
    }

    @Override // com.google.crypto.tink.hybrid.internal.HpkeKdf
    public byte[] labeledExpand(byte[] bArr, byte[] bArr2, String str, byte[] bArr3, int i2) {
        return expand(bArr, HpkeUtil.labelInfo(str, bArr2, bArr3, i2), i2);
    }

    @Override // com.google.crypto.tink.hybrid.internal.HpkeKdf
    public byte[] labeledExtract(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return extract(HpkeUtil.labelIkm(str, bArr2, bArr3), bArr);
    }
}
