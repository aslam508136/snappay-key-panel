package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.ECPrivateKey;

/* JADX INFO: loaded from: classes.dex */
@Immutable
public final class EcdsaSignJce implements PublicKeySign {
    public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    private final EllipticCurves.EcdsaEncoding encoding;
    private final ECPrivateKey privateKey;
    private final String signatureAlgorithm;

    public EcdsaSignJce(ECPrivateKey eCPrivateKey, Enums.HashType hashType, EllipticCurves.EcdsaEncoding ecdsaEncoding) throws GeneralSecurityException {
        if (!FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use ECDSA in FIPS-mode, as BoringCrypto is not available.");
        }
        this.privateKey = eCPrivateKey;
        this.signatureAlgorithm = SubtleUtil.toEcdsaAlgo(hashType);
        this.encoding = ecdsaEncoding;
    }

    @Override // com.google.crypto.tink.PublicKeySign
    public byte[] sign(byte[] bArr) throws SignatureException, InvalidKeyException {
        Signature engineFactory = EngineFactory.SIGNATURE.getInstance(this.signatureAlgorithm, EngineFactory.toProviderList("GmsCore_OpenSSL", "AndroidOpenSSL", "Conscrypt"));
        engineFactory.initSign(this.privateKey);
        engineFactory.update(bArr);
        byte[] bArrSign = engineFactory.sign();
        return this.encoding == EllipticCurves.EcdsaEncoding.IEEE_P1363 ? EllipticCurves.ecdsaDer2Ieee(bArrSign, EllipticCurves.fieldSizeInBytes(this.privateKey.getParams().getCurve()) * 2) : bArrSign;
    }
}
