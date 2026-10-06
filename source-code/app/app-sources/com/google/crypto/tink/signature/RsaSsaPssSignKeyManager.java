package com.google.crypto.tink.signature;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.RsaSsaPssKeyFormat;
import com.google.crypto.tink.proto.RsaSsaPssParams;
import com.google.crypto.tink.proto.RsaSsaPssPrivateKey;
import com.google.crypto.tink.proto.RsaSsaPssPublicKey;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.signature.internal.SigUtil;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.RsaSsaPssSignJce;
import com.google.crypto.tink.subtle.SelfKeyTestValidators;
import com.google.crypto.tink.subtle.Validators;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
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
public final class RsaSsaPssSignKeyManager extends PrivateKeyTypeManager<RsaSsaPssPrivateKey, RsaSsaPssPublicKey> {
    public RsaSsaPssSignKeyManager() {
        super(RsaSsaPssPrivateKey.class, RsaSsaPssPublicKey.class, new PrimitiveFactory<PublicKeySign, RsaSsaPssPrivateKey>(PublicKeySign.class) { // from class: com.google.crypto.tink.signature.RsaSsaPssSignKeyManager.1
            @Override // com.google.crypto.tink.internal.PrimitiveFactory
            public PublicKeySign getPrimitive(RsaSsaPssPrivateKey rsaSsaPssPrivateKey) {
                KeyFactory engineFactory = EngineFactory.KEY_FACTORY.getInstance("RSA");
                RSAPrivateCrtKey rSAPrivateCrtKey = (RSAPrivateCrtKey) engineFactory.generatePrivate(new RSAPrivateCrtKeySpec(new BigInteger(1, rsaSsaPssPrivateKey.getPublicKey().getN().toByteArray()), new BigInteger(1, rsaSsaPssPrivateKey.getPublicKey().getE().toByteArray()), new BigInteger(1, rsaSsaPssPrivateKey.getD().toByteArray()), new BigInteger(1, rsaSsaPssPrivateKey.getP().toByteArray()), new BigInteger(1, rsaSsaPssPrivateKey.getQ().toByteArray()), new BigInteger(1, rsaSsaPssPrivateKey.getDp().toByteArray()), new BigInteger(1, rsaSsaPssPrivateKey.getDq().toByteArray()), new BigInteger(1, rsaSsaPssPrivateKey.getCrt().toByteArray())));
                RsaSsaPssParams params = rsaSsaPssPrivateKey.getPublicKey().getParams();
                SelfKeyTestValidators.validateRsaSsaPss(rSAPrivateCrtKey, (RSAPublicKey) engineFactory.generatePublic(new RSAPublicKeySpec(new BigInteger(1, rsaSsaPssPrivateKey.getPublicKey().getN().toByteArray()), new BigInteger(1, rsaSsaPssPrivateKey.getPublicKey().getE().toByteArray()))), SigUtil.toHashType(params.getSigHash()), SigUtil.toHashType(params.getMgf1Hash()), params.getSaltLength());
                return new RsaSsaPssSignJce(rSAPrivateCrtKey, SigUtil.toHashType(params.getSigHash()), SigUtil.toHashType(params.getMgf1Hash()), params.getSaltLength());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static RsaSsaPssKeyFormat createKeyFormat(HashType hashType, HashType hashType2, int i2, int i3, BigInteger bigInteger) {
        return RsaSsaPssKeyFormat.newBuilder().setParams(RsaSsaPssParams.newBuilder().setSigHash(hashType).setMgf1Hash(hashType2).setSaltLength(i2).build()).setModulusSizeInBits(i3).setPublicExponent(ByteString.copyFrom(bigInteger.toByteArray())).build();
    }

    private static KeyTemplate createKeyTemplate(HashType hashType, HashType hashType2, int i2, int i3, BigInteger bigInteger, KeyTemplate.OutputPrefixType outputPrefixType) {
        return KeyTemplate.create(new RsaSsaPssSignKeyManager().getKeyType(), createKeyFormat(hashType, hashType2, i2, i3, bigInteger).toByteArray(), outputPrefixType);
    }

    public static final KeyTemplate rawRsa3072PssSha256F4Template() {
        HashType hashType = HashType.SHA256;
        return createKeyTemplate(hashType, hashType, 32, 3072, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.RAW);
    }

    public static final KeyTemplate rawRsa4096PssSha512F4Template() {
        HashType hashType = HashType.SHA512;
        return createKeyTemplate(hashType, hashType, 64, 4096, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.RAW);
    }

    public static void registerPair(boolean z2) {
        Registry.registerAsymmetricKeyManagers(new RsaSsaPssSignKeyManager(), new RsaSsaPssVerifyKeyManager(), z2);
    }

    public static final KeyTemplate rsa3072PssSha256F4Template() {
        HashType hashType = HashType.SHA256;
        return createKeyTemplate(hashType, hashType, 32, 3072, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.TINK);
    }

    public static final KeyTemplate rsa4096PssSha512F4Template() {
        HashType hashType = HashType.SHA512;
        return createKeyTemplate(hashType, hashType, 64, 4096, RSAKeyGenParameterSpec.F4, KeyTemplate.OutputPrefixType.TINK);
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.RsaSsaPssPrivateKey";
    }

    @Override // com.google.crypto.tink.internal.PrivateKeyTypeManager
    public RsaSsaPssPublicKey getPublicKey(RsaSsaPssPrivateKey rsaSsaPssPrivateKey) {
        return rsaSsaPssPrivateKey.getPublicKey();
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory<RsaSsaPssKeyFormat, RsaSsaPssPrivateKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<RsaSsaPssKeyFormat, RsaSsaPssPrivateKey>(RsaSsaPssKeyFormat.class) { // from class: com.google.crypto.tink.signature.RsaSsaPssSignKeyManager.2
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public RsaSsaPssPrivateKey createKey(RsaSsaPssKeyFormat rsaSsaPssKeyFormat) throws GeneralSecurityException {
                RsaSsaPssParams params = rsaSsaPssKeyFormat.getParams();
                Validators.validateRsaModulusSize(rsaSsaPssKeyFormat.getModulusSizeInBits());
                Validators.validateSignatureHash(SigUtil.toHashType(params.getSigHash()));
                KeyPairGenerator engineFactory = EngineFactory.KEY_PAIR_GENERATOR.getInstance("RSA");
                engineFactory.initialize(new RSAKeyGenParameterSpec(rsaSsaPssKeyFormat.getModulusSizeInBits(), new BigInteger(1, rsaSsaPssKeyFormat.getPublicExponent().toByteArray())));
                KeyPair keyPairGenerateKeyPair = engineFactory.generateKeyPair();
                RSAPublicKey rSAPublicKey = (RSAPublicKey) keyPairGenerateKeyPair.getPublic();
                RSAPrivateCrtKey rSAPrivateCrtKey = (RSAPrivateCrtKey) keyPairGenerateKeyPair.getPrivate();
                return RsaSsaPssPrivateKey.newBuilder().setVersion(RsaSsaPssSignKeyManager.this.getVersion()).setPublicKey(RsaSsaPssPublicKey.newBuilder().setVersion(RsaSsaPssSignKeyManager.this.getVersion()).setParams(params).setE(ByteString.copyFrom(rSAPublicKey.getPublicExponent().toByteArray())).setN(ByteString.copyFrom(rSAPublicKey.getModulus().toByteArray())).build()).setD(ByteString.copyFrom(rSAPrivateCrtKey.getPrivateExponent().toByteArray())).setP(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeP().toByteArray())).setQ(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeQ().toByteArray())).setDp(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeExponentP().toByteArray())).setDq(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeExponentQ().toByteArray())).setCrt(ByteString.copyFrom(rSAPrivateCrtKey.getCrtCoefficient().toByteArray())).build();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<RsaSsaPssKeyFormat>> keyFormats() {
                HashMap map = new HashMap();
                HashType hashType = HashType.SHA256;
                RsaSsaPssKeyFormat rsaSsaPssKeyFormatCreateKeyFormat = RsaSsaPssSignKeyManager.createKeyFormat(hashType, hashType, 32, 3072, RSAKeyGenParameterSpec.F4);
                KeyTemplate.OutputPrefixType outputPrefixType = KeyTemplate.OutputPrefixType.TINK;
                map.put("RSA_SSA_PSS_3072_SHA256_F4", new KeyTypeManager.KeyFactory.KeyFormat(rsaSsaPssKeyFormatCreateKeyFormat, outputPrefixType));
                RsaSsaPssKeyFormat rsaSsaPssKeyFormatCreateKeyFormat2 = RsaSsaPssSignKeyManager.createKeyFormat(hashType, hashType, 32, 3072, RSAKeyGenParameterSpec.F4);
                KeyTemplate.OutputPrefixType outputPrefixType2 = KeyTemplate.OutputPrefixType.RAW;
                map.put("RSA_SSA_PSS_3072_SHA256_F4_RAW", new KeyTypeManager.KeyFactory.KeyFormat(rsaSsaPssKeyFormatCreateKeyFormat2, outputPrefixType2));
                map.put("RSA_SSA_PSS_3072_SHA256_SHA256_32_F4", new KeyTypeManager.KeyFactory.KeyFormat(RsaSsaPssSignKeyManager.createKeyFormat(hashType, hashType, 32, 3072, RSAKeyGenParameterSpec.F4), outputPrefixType));
                HashType hashType2 = HashType.SHA512;
                map.put("RSA_SSA_PSS_4096_SHA512_F4", new KeyTypeManager.KeyFactory.KeyFormat(RsaSsaPssSignKeyManager.createKeyFormat(hashType2, hashType2, 64, 4096, RSAKeyGenParameterSpec.F4), outputPrefixType));
                map.put("RSA_SSA_PSS_4096_SHA512_F4_RAW", new KeyTypeManager.KeyFactory.KeyFormat(RsaSsaPssSignKeyManager.createKeyFormat(hashType2, hashType2, 64, 4096, RSAKeyGenParameterSpec.F4), outputPrefixType2));
                map.put("RSA_SSA_PSS_4096_SHA512_SHA512_64_F4", new KeyTypeManager.KeyFactory.KeyFormat(RsaSsaPssSignKeyManager.createKeyFormat(hashType2, hashType2, 64, 4096, RSAKeyGenParameterSpec.F4), outputPrefixType));
                return Collections.unmodifiableMap(map);
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public RsaSsaPssKeyFormat parseKeyFormat(ByteString byteString) {
                return RsaSsaPssKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public void validateKeyFormat(RsaSsaPssKeyFormat rsaSsaPssKeyFormat) throws GeneralSecurityException {
                SigUtil.validateRsaSsaPssParams(rsaSsaPssKeyFormat.getParams());
                Validators.validateRsaModulusSize(rsaSsaPssKeyFormat.getModulusSizeInBits());
                Validators.validateRsaPublicExponent(new BigInteger(1, rsaSsaPssKeyFormat.getPublicExponent().toByteArray()));
            }
        };
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public RsaSsaPssPrivateKey parseKey(ByteString byteString) {
        return RsaSsaPssPrivateKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(RsaSsaPssPrivateKey rsaSsaPssPrivateKey) throws GeneralSecurityException {
        Validators.validateVersion(rsaSsaPssPrivateKey.getVersion(), getVersion());
        Validators.validateRsaModulusSize(new BigInteger(1, rsaSsaPssPrivateKey.getPublicKey().getN().toByteArray()).bitLength());
        Validators.validateRsaPublicExponent(new BigInteger(1, rsaSsaPssPrivateKey.getPublicKey().getE().toByteArray()));
        SigUtil.validateRsaSsaPssParams(rsaSsaPssPrivateKey.getPublicKey().getParams());
    }
}
