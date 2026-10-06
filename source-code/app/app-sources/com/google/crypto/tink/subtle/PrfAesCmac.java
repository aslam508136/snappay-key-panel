package com.google.crypto.tink.subtle;

import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.mac.internal.AesUtil;
import com.google.crypto.tink.prf.Prf;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
@Immutable
public final class PrfAesCmac implements Prf {
    public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS;
    private final SecretKey keySpec;
    private byte[] subKey1;
    private byte[] subKey2;

    public PrfAesCmac(byte[] bArr) throws GeneralSecurityException {
        Validators.validateAesKeySize(bArr.length);
        this.keySpec = new SecretKeySpec(bArr, "AES");
        generateSubKeys();
    }

    private void generateSubKeys() throws GeneralSecurityException {
        Cipher cipherInstance = instance();
        cipherInstance.init(1, this.keySpec);
        byte[] bArrDbl = AesUtil.dbl(cipherInstance.doFinal(new byte[16]));
        this.subKey1 = bArrDbl;
        this.subKey2 = AesUtil.dbl(bArrDbl);
    }

    private static Cipher instance() throws GeneralSecurityException {
        if (FIPS.isCompatible()) {
            return EngineFactory.CIPHER.getInstance("AES/ECB/NoPadding");
        }
        throw new GeneralSecurityException("Can not use AES-CMAC in FIPS-mode.");
    }

    @Override // com.google.crypto.tink.prf.Prf
    public byte[] compute(byte[] bArr, int i2) throws GeneralSecurityException {
        if (i2 > 16) {
            throw new InvalidAlgorithmParameterException("outputLength too large, max is 16 bytes");
        }
        Cipher cipherInstance = instance();
        cipherInstance.init(1, this.keySpec);
        int iMax = Math.max(1, (int) Math.ceil(((double) bArr.length) / 16.0d));
        byte[] bArrXor = iMax * 16 == bArr.length ? Bytes.xor(bArr, (iMax - 1) * 16, this.subKey1, 0, 16) : Bytes.xor(AesUtil.cmacPad(Arrays.copyOfRange(bArr, (iMax - 1) * 16, bArr.length)), this.subKey2);
        byte[] bArrDoFinal = new byte[16];
        for (int i3 = 0; i3 < iMax - 1; i3++) {
            bArrDoFinal = cipherInstance.doFinal(Bytes.xor(bArrDoFinal, 0, bArr, i3 * 16, 16));
        }
        return Arrays.copyOf(cipherInstance.doFinal(Bytes.xor(bArrXor, bArrDoFinal)), i2);
    }
}
