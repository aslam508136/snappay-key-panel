package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.errorprone.annotations.Immutable;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
@Immutable
public final class RsaSsaPssVerifyJce implements PublicKeyVerify {
    public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    private final Enums.HashType mgf1Hash;
    private final RSAPublicKey publicKey;
    private final int saltLength;
    private final Enums.HashType sigHash;

    public RsaSsaPssVerifyJce(RSAPublicKey rSAPublicKey, Enums.HashType hashType, Enums.HashType hashType2, int i2) throws GeneralSecurityException {
        if (!FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use RSA PSS in FIPS-mode, as BoringCrypto module is not available.");
        }
        Validators.validateSignatureHash(hashType);
        Validators.validateRsaModulusSize(rSAPublicKey.getModulus().bitLength());
        Validators.validateRsaPublicExponent(rSAPublicKey.getPublicExponent());
        this.publicKey = rSAPublicKey;
        this.sigHash = hashType;
        this.mgf1Hash = hashType2;
        this.saltLength = i2;
    }

    private void emsaPssVerify(byte[] bArr, byte[] bArr2, int i2) throws GeneralSecurityException {
        Validators.validateSignatureHash(this.sigHash);
        MessageDigest engineFactory = EngineFactory.MESSAGE_DIGEST.getInstance(SubtleUtil.toDigestAlgo(this.sigHash));
        byte[] bArrDigest = engineFactory.digest(bArr);
        int digestLength = engineFactory.getDigestLength();
        int length = bArr2.length;
        if (length < this.saltLength + digestLength + 2) {
            throw new GeneralSecurityException("inconsistent");
        }
        if (bArr2[bArr2.length - 1] != -68) {
            throw new GeneralSecurityException("inconsistent");
        }
        int i3 = length - digestLength;
        int i4 = i3 - 1;
        byte[] bArrCopyOf = Arrays.copyOf(bArr2, i4);
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr2, bArrCopyOf.length, bArrCopyOf.length + digestLength);
        int i5 = 0;
        while (true) {
            int i6 = i4;
            MessageDigest messageDigest = engineFactory;
            byte[] bArr3 = bArrDigest;
            long j2 = (((long) length) * 8) - ((long) i2);
            if (i5 < j2) {
                if (((bArrCopyOf[i5 / 8] >> (7 - (i5 % 8))) & 1) != 0) {
                    throw new GeneralSecurityException("inconsistent");
                }
                i5++;
                i4 = i6;
                engineFactory = messageDigest;
                bArrDigest = bArr3;
            } else {
                byte[] bArrMgf1 = SubtleUtil.mgf1(bArrCopyOfRange, i6, this.mgf1Hash);
                int length2 = bArrMgf1.length;
                byte[] bArr4 = new byte[length2];
                for (int i7 = 0; i7 < length2; i7++) {
                    bArr4[i7] = (byte) (bArrMgf1[i7] ^ bArrCopyOf[i7]);
                }
                for (int i8 = 0; i8 <= j2; i8++) {
                    int i9 = i8 / 8;
                    bArr4[i9] = (byte) ((~(1 << (7 - (i8 % 8)))) & bArr4[i9]);
                }
                int i10 = 0;
                while (true) {
                    int i11 = this.saltLength;
                    if (i10 >= (i3 - i11) - 2) {
                        if (bArr4[(i3 - i11) - 2] != 1) {
                            throw new GeneralSecurityException("inconsistent");
                        }
                        byte[] bArrCopyOfRange2 = Arrays.copyOfRange(bArr4, length2 - i11, length2);
                        int i12 = digestLength + 8;
                        byte[] bArr5 = new byte[this.saltLength + i12];
                        System.arraycopy(bArr3, 0, bArr5, 8, bArr3.length);
                        System.arraycopy(bArrCopyOfRange2, 0, bArr5, i12, bArrCopyOfRange2.length);
                        if (!Bytes.equal(messageDigest.digest(bArr5), bArrCopyOfRange)) {
                            throw new GeneralSecurityException("inconsistent");
                        }
                        return;
                    }
                    if (bArr4[i10] != 0) {
                        throw new GeneralSecurityException("inconsistent");
                    }
                    i10++;
                }
            }
        }
    }

    @Override // com.google.crypto.tink.PublicKeyVerify
    public void verify(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        BigInteger publicExponent = this.publicKey.getPublicExponent();
        BigInteger modulus = this.publicKey.getModulus();
        int iBitLength = (modulus.bitLength() + 7) / 8;
        int iBitLength2 = ((modulus.bitLength() - 1) + 7) / 8;
        if (iBitLength != bArr.length) {
            throw new GeneralSecurityException("invalid signature's length");
        }
        BigInteger bigIntegerBytes2Integer = SubtleUtil.bytes2Integer(bArr);
        if (bigIntegerBytes2Integer.compareTo(modulus) >= 0) {
            throw new GeneralSecurityException("signature out of range");
        }
        emsaPssVerify(bArr2, SubtleUtil.integer2Bytes(bigIntegerBytes2Integer.modPow(publicExponent, modulus), iBitLength2), modulus.bitLength() - 1);
    }
}
