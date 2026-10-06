package com.google.crypto.tink.signature;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.RsaSsaPkcs1KeyFormat;
import com.google.crypto.tink.proto.RsaSsaPkcs1Params;
import com.google.crypto.tink.proto.RsaSsaPkcs1PrivateKey;
import com.google.crypto.tink.proto.RsaSsaPkcs1PublicKey;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.signature.internal.SigUtil;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.RsaSsaPkcs1SignJce;
import com.google.crypto.tink.subtle.SelfKeyTestValidators;
import com.google.crypto.tink.subtle.Validators;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAKeyGenParameterSpec;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class RsaSsaPkcs1SignKeyManager extends PrivateKeyTypeManager<RsaSsaPkcs1PrivateKey, RsaSsaPkcs1PublicKey> {
    public RsaSsaPkcs1SignKeyManager() {
        super(RsaSsaPkcs1PrivateKey.class, RsaSsaPkcs1PublicKey.class, new PrimitiveFactory<PublicKeySign, RsaSsaPkcs1PrivateKey>(PublicKeySign.class) { // from class: com.google.crypto.tink.signature.RsaSsaPkcs1SignKeyManager.1
            @Override // com.google.crypto.tink.internal.PrimitiveFactory
            public PublicKeySign getPrimitive(RsaSsaPkcs1PrivateKey rsaSsaPkcs1PrivateKey) {
                KeyFactory engineFactory = EngineFactory.KEY_FACTORY.getInstance("RSA");
                RSAPrivateCrtKey rSAPrivateCrtKey = (RSAPrivateCrtKey) engineFactory.generatePrivate(new RSAPrivateCrtKeySpec(new BigInteger(1, rsaSsaPkcs1PrivateKey.getPublicKey().getN().toByteArray()), new BigInteger(1, rsaSsaPkcs1PrivateKey.getPublicKey().getE().toByteArray()), new BigInteger(1, rsaSsaPkcs1PrivateKey.getD().toByteArray()), new BigInteger(1, rsaSsaPkcs1PrivateKey.getP().toByteArray()), new BigInteger(1, rsaSsaPkcs1PrivateKey.getQ().toByteArray()), new BigInteger(1, rsaSsaPkcs1PrivateKey.getDp().toByteArray()), new BigInteger(1, rsaSsaPkcs1PrivateKey.getDq().toByteArray()), new BigInteger(1, rsaSsaPkcs1PrivateKey.getCrt().toByteArray())));
                RsaSsaPkcs1Params params = rsaSsaPkcs1PrivateKey.getPublicKey().getParams();
                SelfKeyTestValidators.validateRsaSsaPkcs1(rSAPrivateCrtKey, (RSAPublicKey) engineFactory.generatePublic(new RSAPublicKeySpec(new BigInteger(1, rsaSsaPkcs1PrivateKey.getPublicKey().getN().toByteArray()), new BigInteger(1, rsaSsaPkcs1PrivateKey.getPublicKey().getE().toByteArray()))), SigUtil.toHashType(params.getHashType()));
                return new RsaSsaPkcs1SignJce(rSAPrivateCrtKey, SigUtil.toHashType(params.getHashType()));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static RsaSsaPkcs1KeyFormat createKeyFormat(HashType hashType, int i2, BigInteger bigInteger) {
        return RsaSsaPkcs1KeyFormat.newBuilder().setParams(RsaSsaPkcs1Params.newBuilder().setHashType(hashType).build()).setModulusSizeInBits(i2).setPublicExponent(ByteString.copyFrom(bigInteger.toByteArray())).build();
    }

    private static KeyTemplate createKeyTemplate(HashType hashType, int i2, BigInteger bigInteger, KeyTemplate.OutputPrefixType outputPrefixType) {
        return KeyTemplate.create(new RsaSsaPkcs1SignKeyManager().getKeyType(), createKeyFormat(hashType, i2, bigInteger).toByteArray(), outputPrefixType);
    }

    public static final KeyTemplate rawRsa3072SsaPkcs1Sha256F4Template() {
        return createKeyTemplate(HashType.SHA256, 3072, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.RAW);
    }

    public static final KeyTemplate rawRsa4096SsaPkcs1Sha512F4Template() {
        return createKeyTemplate(HashType.SHA512, 4096, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.RAW);
    }

    public static void registerPair(boolean z2) {
        Registry.registerAsymmetricKeyManagers(new RsaSsaPkcs1SignKeyManager(), new RsaSsaPkcs1VerifyKeyManager(), z2);
    }

    public static final KeyTemplate rsa3072SsaPkcs1Sha256F4Template() {
        return createKeyTemplate(HashType.SHA256, 3072, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.TINK);
    }

    public static final KeyTemplate rsa4096SsaPkcs1Sha512F4Template() {
        return createKeyTemplate(HashType.SHA512, 4096, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.TINK);
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public TinkFipsUtil.AlgorithmFipsCompatibility fipsStatus() {
        return TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.RsaSsaPkcs1PrivateKey";
    }

    @Override // com.google.crypto.tink.internal.PrivateKeyTypeManager
    public RsaSsaPkcs1PublicKey getPublicKey(RsaSsaPkcs1PrivateKey rsaSsaPkcs1PrivateKey) {
        return rsaSsaPkcs1PrivateKey.getPublicKey();
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory<RsaSsaPkcs1KeyFormat, RsaSsaPkcs1PrivateKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<RsaSsaPkcs1KeyFormat, RsaSsaPkcs1PrivateKey>(RsaSsaPkcs1KeyFormat.class) { // from class: com.google.crypto.tink.signature.RsaSsaPkcs1SignKeyManager.2
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public RsaSsaPkcs1PrivateKey createKey(RsaSsaPkcs1KeyFormat rsaSsaPkcs1KeyFormat) throws InvalidAlgorithmParameterException {
                RsaSsaPkcs1Params params = rsaSsaPkcs1KeyFormat.getParams();
                KeyPairGenerator engineFactory = EngineFactory.KEY_PAIR_GENERATOR.getInstance("RSA");
                engineFactory.initialize(new RSAKeyGenParameterSpec(rsaSsaPkcs1KeyFormat.getModulusSizeInBits(), new BigInteger(1, rsaSsaPkcs1KeyFormat.getPublicExponent().toByteArray())));
                KeyPair keyPairGenerateKeyPair = engineFactory.generateKeyPair();
                RSAPublicKey rSAPublicKey = (RSAPublicKey) keyPairGenerateKeyPair.getPublic();
                RSAPrivateCrtKey rSAPrivateCrtKey = (RSAPrivateCrtKey) keyPairGenerateKeyPair.getPrivate();
                return RsaSsaPkcs1PrivateKey.newBuilder().setVersion(RsaSsaPkcs1SignKeyManager.this.getVersion()).setPublicKey(RsaSsaPkcs1PublicKey.newBuilder().setVersion(RsaSsaPkcs1SignKeyManager.this.getVersion()).setParams(params).setE(ByteString.copyFrom(rSAPublicKey.getPublicExponent().toByteArray())).setN(ByteString.copyFrom(rSAPublicKey.getModulus().toByteArray())).build()).setD(ByteString.copyFrom(rSAPrivateCrtKey.getPrivateExponent().toByteArray())).setP(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeP().toByteArray())).setQ(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeQ().toByteArray())).setDp(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeExponentP().toByteArray())).setDq(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeExponentQ().toByteArray())).setCrt(ByteString.copyFrom(rSAPrivateCrtKey.getCrtCoefficient().toByteArray())).build();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<RsaSsaPkcs1KeyFormat>> keyFormats() {
                HashMap map = new HashMap();
                HashType hashType = HashType.SHA256;
                RsaSsaPkcs1KeyFormat rsaSsaPkcs1KeyFormatCreateKeyFormat = RsaSsaPkcs1SignKeyManager.createKeyFormat(hashType, 3072, RSAKeyGenParameterSpec.F4);
                KeyTemplate.OutputPrefixType outputPrefixType = KeyTemplate.OutputPrefixType.TINK;
                map.put("RSA_SSA_PKCS1_3072_SHA256_F4", new KeyTypeManager.KeyFactory.KeyFormat(rsaSsaPkcs1KeyFormatCreateKeyFormat, outputPrefixType));
                RsaSsaPkcs1KeyFormat rsaSsaPkcs1KeyFormatCreateKeyFormat2 = RsaSsaPkcs1SignKeyManager.createKeyFormat(hashType, 3072, RSAKeyGenParameterSpec.F4);
                KeyTemplate.OutputPrefixType outputPrefixType2 = KeyTemplate.OutputPrefixType.RAW;
                map.put("RSA_SSA_PKCS1_3072_SHA256_F4_RAW", new KeyTypeManager.KeyFactory.KeyFormat(rsaSsaPkcs1KeyFormatCreateKeyFormat2, outputPrefixType2));
                map.put("RSA_SSA_PKCS1_3072_SHA256_F4_WITHOUT_PREFIX", new KeyTypeManager.KeyFactory.KeyFormat(RsaSsaPkcs1SignKeyManager.createKeyFormat(hashType, 3072, RSAKeyGenParameterSpec.F4), outputPrefixType2));
                HashType hashType2 = HashType.SHA512;
                map.put("RSA_SSA_PKCS1_4096_SHA512_F4", new KeyTypeManager.KeyFactory.KeyFormat(RsaSsaPkcs1SignKeyManager.createKeyFormat(hashType2, 4096, RSAKeyGenParameterSpec.F4), outputPrefixType));
                map.put("RSA_SSA_PKCS1_4096_SHA512_F4_RAW", new KeyTypeManager.KeyFactory.KeyFormat(RsaSsaPkcs1SignKeyManager.createKeyFormat(hashType2, 4096, RSAKeyGenParameterSpec.F4), outputPrefixType2));
                return Collections.unmodifiableMap(map);
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public RsaSsaPkcs1KeyFormat parseKeyFormat(ByteString byteString) {
                return RsaSsaPkcs1KeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public void validateKeyFormat(RsaSsaPkcs1KeyFormat rsaSsaPkcs1KeyFormat) throws GeneralSecurityException {
                SigUtil.validateRsaSsaPkcs1Params(rsaSsaPkcs1KeyFormat.getParams());
                Validators.validateRsaModulusSize(rsaSsaPkcs1KeyFormat.getModulusSizeInBits());
                Validators.validateRsaPublicExponent(new BigInteger(1, rsaSsaPkcs1KeyFormat.getPublicExponent().toByteArray()));
            }
        };
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public RsaSsaPkcs1PrivateKey parseKey(ByteString byteString) {
        return RsaSsaPkcs1PrivateKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(RsaSsaPkcs1PrivateKey rsaSsaPkcs1PrivateKey) throws GeneralSecurityException {
        Validators.validateVersion(rsaSsaPkcs1PrivateKey.getVersion(), getVersion());
        Validators.validateRsaModulusSize(new BigInteger(1, rsaSsaPkcs1PrivateKey.getPublicKey().getN().toByteArray()).bitLength());
        Validators.validateRsaPublicExponent(new BigInteger(1, rsaSsaPkcs1PrivateKey.getPublicKey().getE().toByteArray()));
        SigUtil.validateRsaSsaPkcs1Params(rsaSsaPkcs1PrivateKey.getPublicKey().getParams());
    }
}
