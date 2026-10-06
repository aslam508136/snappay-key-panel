package com.google.crypto.tink.subtle;

import com.google.crypto.tink.DeterministicAead;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.mac.internal.AesUtil;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.util.Arrays;
import java.util.Collection;
import javax.crypto.AEADBadTagException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
public final class AesSiv implements DeterministicAead {
    private final byte[] aesCtrKey;
    private final PrfAesCmac cmacForS2V;
    public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS;
    private static final Collection<Integer> KEY_SIZES = Arrays.asList(64);
    private static final byte[] BLOCK_ZERO = new byte[16];
    private static final byte[] BLOCK_ONE = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};

    public AesSiv(byte[] bArr) throws GeneralSecurityException {
        if (!FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use AES-SIV in FIPS-mode.");
        }
        if (KEY_SIZES.contains(Integer.valueOf(bArr.length))) {
            byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, 0, bArr.length / 2);
            this.aesCtrKey = Arrays.copyOfRange(bArr, bArr.length / 2, bArr.length);
            this.cmacForS2V = new PrfAesCmac(bArrCopyOfRange);
        } else {
            throw new InvalidKeyException("invalid key size: " + bArr.length + " bytes; key must have 64 bytes");
        }
    }

    private byte[] s2v(byte[]... bArr) throws GeneralSecurityException {
        if (bArr.length == 0) {
            return this.cmacForS2V.compute(BLOCK_ONE, 16);
        }
        byte[] bArrCompute = this.cmacForS2V.compute(BLOCK_ZERO, 16);
        for (int i2 = 0; i2 < bArr.length - 1; i2++) {
            byte[] bArr2 = bArr[i2];
            if (bArr2 == null) {
                bArr2 = new byte[0];
            }
            bArrCompute = Bytes.xor(AesUtil.dbl(bArrCompute), this.cmacForS2V.compute(bArr2, 16));
        }
        byte[] bArr3 = bArr[bArr.length - 1];
        return this.cmacForS2V.compute(bArr3.length >= 16 ? Bytes.xorEnd(bArr3, bArrCompute) : Bytes.xor(AesUtil.cmacPad(bArr3), AesUtil.dbl(bArrCompute)), 16);
    }

    @Override // com.google.crypto.tink.DeterministicAead
    public byte[] decryptDeterministically(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length < 16) {
            throw new GeneralSecurityException("Ciphertext too short.");
        }
        Cipher engineFactory = EngineFactory.CIPHER.getInstance("AES/CTR/NoPadding");
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, 0, 16);
        byte[] bArr3 = (byte[]) bArrCopyOfRange.clone();
        bArr3[8] = (byte) (bArr3[8] & 127);
        bArr3[12] = (byte) (bArr3[12] & 127);
        engineFactory.init(2, new SecretKeySpec(this.aesCtrKey, "AES"), new IvParameterSpec(bArr3));
        byte[] bArrCopyOfRange2 = Arrays.copyOfRange(bArr, 16, bArr.length);
        byte[] bArrDoFinal = engineFactory.doFinal(bArrCopyOfRange2);
        if (bArrCopyOfRange2.length == 0 && bArrDoFinal == null && SubtleUtil.isAndroid()) {
            bArrDoFinal = new byte[0];
        }
        if (Bytes.equal(bArrCopyOfRange, s2v(bArr2, bArrDoFinal))) {
            return bArrDoFinal;
        }
        throw new AEADBadTagException("Integrity check failed.");
    }

    @Override // com.google.crypto.tink.DeterministicAead
    public byte[] encryptDeterministically(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length > 2147483631) {
            throw new GeneralSecurityException("plaintext too long");
        }
        Cipher engineFactory = EngineFactory.CIPHER.getInstance("AES/CTR/NoPadding");
        byte[] bArrS2v = s2v(bArr2, bArr);
        byte[] bArr3 = (byte[]) bArrS2v.clone();
        bArr3[8] = (byte) (bArr3[8] & 127);
        bArr3[12] = (byte) (bArr3[12] & 127);
        engineFactory.init(1, new SecretKeySpec(this.aesCtrKey, "AES"), new IvParameterSpec(bArr3));
        return Bytes.concat(bArrS2v, engineFactory.doFinal(bArr));
    }
}
