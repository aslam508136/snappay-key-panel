package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;

/* JADX INFO: loaded from: classes.dex */
@Immutable
public final class RsaSsaPkcs1SignJce implements PublicKeySign {
    public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    private final RSAPrivateCrtKey privateKey;
    private final RSAPublicKey publicKey;
    private final String signatureAlgorithm;

    public RsaSsaPkcs1SignJce(RSAPrivateCrtKey rSAPrivateCrtKey, Enums.HashType hashType) throws GeneralSecurityException {
        if (!FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use RSA PKCS1.5 in FIPS-mode, as BoringCrypto module is not available.");
        }
        Validators.validateSignatureHash(hashType);
        Validators.validateRsaModulusSize(rSAPrivateCrtKey.getModulus().bitLength());
        Validators.validateRsaPublicExponent(rSAPrivateCrtKey.getPublicExponent());
        this.privateKey = rSAPrivateCrtKey;
        this.signatureAlgorithm = SubtleUtil.toRsaSsaPkcs1Algo(hashType);
        this.publicKey = (RSAPublicKey) EngineFactory.KEY_FACTORY.getInstance("RSA").generatePublic(new RSAPublicKeySpec(rSAPrivateCrtKey.getModulus(), rSAPrivateCrtKey.getPublicExponent()));
    }

    @Override // com.google.crypto.tink.PublicKeySign
    public byte[] sign(byte[] bArr) throws SignatureException, InvalidKeyException {
        EngineFactory<EngineWrapper.TSignature, Signature> engineFactory = EngineFactory.SIGNATURE;
        Signature engineFactory2 = engineFactory.getInstance(this.signatureAlgorithm);
        engineFactory2.initSign(this.privateKey);
        engineFactory2.update(bArr);
        byte[] bArrSign = engineFactory2.sign();
        Signature engineFactory3 = engineFactory.getInstance(this.signatureAlgorithm);
        engineFactory3.initVerify(this.publicKey);
        engineFactory3.update(bArr);
        if (engineFactory3.verify(bArrSign)) {
            return bArrSign;
        }
        throw new RuntimeException("Security bug: RSA signature computation error");
    }
}
