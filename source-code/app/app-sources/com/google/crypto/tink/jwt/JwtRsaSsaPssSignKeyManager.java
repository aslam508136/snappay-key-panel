package com.google.crypto.tink.jwt;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.JwtRsaSsaPssAlgorithm;
import com.google.crypto.tink.proto.JwtRsaSsaPssKeyFormat;
import com.google.crypto.tink.proto.JwtRsaSsaPssPrivateKey;
import com.google.crypto.tink.proto.JwtRsaSsaPssPublicKey;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.Enums;
import com.google.crypto.tink.subtle.RsaSsaPssSignJce;
import com.google.crypto.tink.subtle.SelfKeyTestValidators;
import com.google.crypto.tink.subtle.Validators;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
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
import java.util.Optional;

/* JADX INFO: loaded from: classes.dex */
public final class JwtRsaSsaPssSignKeyManager extends PrivateKeyTypeManager<JwtRsaSsaPssPrivateKey, JwtRsaSsaPssPublicKey> {

    public static class JwtPublicKeySignFactory extends PrimitiveFactory<JwtPublicKeySignInternal, JwtRsaSsaPssPrivateKey> {
        public JwtPublicKeySignFactory() {
            super(JwtPublicKeySignInternal.class);
        }

        @Override // com.google.crypto.tink.internal.PrimitiveFactory
        public JwtPublicKeySignInternal getPrimitive(JwtRsaSsaPssPrivateKey jwtRsaSsaPssPrivateKey) throws GeneralSecurityException {
            RSAPrivateCrtKey rSAPrivateCrtKeyCreatePrivateKey = JwtRsaSsaPssSignKeyManager.createPrivateKey(jwtRsaSsaPssPrivateKey);
            JwtRsaSsaPssSignKeyManager.selfTestKey(rSAPrivateCrtKeyCreatePrivateKey, jwtRsaSsaPssPrivateKey);
            JwtRsaSsaPssAlgorithm algorithm = jwtRsaSsaPssPrivateKey.getPublicKey().getAlgorithm();
            Enums.HashType hashTypeHashForPssAlgorithm = JwtRsaSsaPssVerifyKeyManager.hashForPssAlgorithm(algorithm);
            final RsaSsaPssSignJce rsaSsaPssSignJce = new RsaSsaPssSignJce(rSAPrivateCrtKeyCreatePrivateKey, hashTypeHashForPssAlgorithm, hashTypeHashForPssAlgorithm, JwtRsaSsaPssVerifyKeyManager.saltLengthForPssAlgorithm(algorithm));
            final String strName = algorithm.name();
            final Optional optionalOf = jwtRsaSsaPssPrivateKey.getPublicKey().hasCustomKid() ? Optional.of(jwtRsaSsaPssPrivateKey.getPublicKey().getCustomKid().getValue()) : Optional.empty();
            return new JwtPublicKeySignInternal() { // from class: com.google.crypto.tink.jwt.JwtRsaSsaPssSignKeyManager.JwtPublicKeySignFactory.1
                @Override // com.google.crypto.tink.jwt.JwtPublicKeySignInternal
                public String signAndEncodeWithKid(RawJwt rawJwt, Optional<String> optional) throws JwtInvalidException {
                    if (optionalOf.isPresent()) {
                        if (optional.isPresent()) {
                            throw new JwtInvalidException("custom_kid can only be set for RAW keys.");
                        }
                        optional = optionalOf;
                    }
                    String strCreateUnsignedCompact = JwtFormat.createUnsignedCompact(strName, optional, rawJwt);
                    return JwtFormat.createSignedCompact(strCreateUnsignedCompact, rsaSsaPssSignJce.sign(strCreateUnsignedCompact.getBytes(StandardCharsets.US_ASCII)));
                }
            };
        }
    }

    public JwtRsaSsaPssSignKeyManager() {
        super(JwtRsaSsaPssPrivateKey.class, JwtRsaSsaPssPublicKey.class, new JwtPublicKeySignFactory());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static KeyTypeManager.KeyFactory.KeyFormat<JwtRsaSsaPssKeyFormat> createKeyFormat(JwtRsaSsaPssAlgorithm jwtRsaSsaPssAlgorithm, int i2, BigInteger bigInteger, KeyTemplate.OutputPrefixType outputPrefixType) {
        return new KeyTypeManager.KeyFactory.KeyFormat<>(JwtRsaSsaPssKeyFormat.newBuilder().setAlgorithm(jwtRsaSsaPssAlgorithm).setModulusSizeInBits(i2).setPublicExponent(ByteString.copyFrom(bigInteger.toByteArray())).build(), outputPrefixType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final RSAPrivateCrtKey createPrivateKey(JwtRsaSsaPssPrivateKey jwtRsaSsaPssPrivateKey) {
        return (RSAPrivateCrtKey) EngineFactory.KEY_FACTORY.getInstance("RSA").generatePrivate(new RSAPrivateCrtKeySpec(new BigInteger(1, jwtRsaSsaPssPrivateKey.getPublicKey().getN().toByteArray()), new BigInteger(1, jwtRsaSsaPssPrivateKey.getPublicKey().getE().toByteArray()), new BigInteger(1, jwtRsaSsaPssPrivateKey.getD().toByteArray()), new BigInteger(1, jwtRsaSsaPssPrivateKey.getP().toByteArray()), new BigInteger(1, jwtRsaSsaPssPrivateKey.getQ().toByteArray()), new BigInteger(1, jwtRsaSsaPssPrivateKey.getDp().toByteArray()), new BigInteger(1, jwtRsaSsaPssPrivateKey.getDq().toByteArray()), new BigInteger(1, jwtRsaSsaPssPrivateKey.getCrt().toByteArray())));
    }

    public static void registerPair(boolean z2) {
        Registry.registerAsymmetricKeyManagers(new JwtRsaSsaPssSignKeyManager(), new JwtRsaSsaPssVerifyKeyManager(), z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void selfTestKey(RSAPrivateCrtKey rSAPrivateCrtKey, JwtRsaSsaPssPrivateKey jwtRsaSsaPssPrivateKey) throws GeneralSecurityException {
        RSAPublicKey rSAPublicKey = (RSAPublicKey) EngineFactory.KEY_FACTORY.getInstance("RSA").generatePublic(new RSAPublicKeySpec(new BigInteger(1, jwtRsaSsaPssPrivateKey.getPublicKey().getN().toByteArray()), new BigInteger(1, jwtRsaSsaPssPrivateKey.getPublicKey().getE().toByteArray())));
        JwtRsaSsaPssAlgorithm algorithm = jwtRsaSsaPssPrivateKey.getPublicKey().getAlgorithm();
        Enums.HashType hashTypeHashForPssAlgorithm = JwtRsaSsaPssVerifyKeyManager.hashForPssAlgorithm(algorithm);
        SelfKeyTestValidators.validateRsaSsaPss(rSAPrivateCrtKey, rSAPublicKey, hashTypeHashForPssAlgorithm, hashTypeHashForPssAlgorithm, JwtRsaSsaPssVerifyKeyManager.saltLengthForPssAlgorithm(algorithm));
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.JwtRsaSsaPssPrivateKey";
    }

    @Override // com.google.crypto.tink.internal.PrivateKeyTypeManager
    public JwtRsaSsaPssPublicKey getPublicKey(JwtRsaSsaPssPrivateKey jwtRsaSsaPssPrivateKey) {
        return jwtRsaSsaPssPrivateKey.getPublicKey();
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory<JwtRsaSsaPssKeyFormat, JwtRsaSsaPssPrivateKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<JwtRsaSsaPssKeyFormat, JwtRsaSsaPssPrivateKey>(JwtRsaSsaPssKeyFormat.class) { // from class: com.google.crypto.tink.jwt.JwtRsaSsaPssSignKeyManager.1
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public JwtRsaSsaPssPrivateKey createKey(JwtRsaSsaPssKeyFormat jwtRsaSsaPssKeyFormat) throws InvalidAlgorithmParameterException {
                JwtRsaSsaPssAlgorithm algorithm = jwtRsaSsaPssKeyFormat.getAlgorithm();
                KeyPairGenerator engineFactory = EngineFactory.KEY_PAIR_GENERATOR.getInstance("RSA");
                engineFactory.initialize(new RSAKeyGenParameterSpec(jwtRsaSsaPssKeyFormat.getModulusSizeInBits(), new BigInteger(1, jwtRsaSsaPssKeyFormat.getPublicExponent().toByteArray())));
                KeyPair keyPairGenerateKeyPair = engineFactory.generateKeyPair();
                RSAPublicKey rSAPublicKey = (RSAPublicKey) keyPairGenerateKeyPair.getPublic();
                RSAPrivateCrtKey rSAPrivateCrtKey = (RSAPrivateCrtKey) keyPairGenerateKeyPair.getPrivate();
                return JwtRsaSsaPssPrivateKey.newBuilder().setVersion(JwtRsaSsaPssSignKeyManager.this.getVersion()).setPublicKey(JwtRsaSsaPssPublicKey.newBuilder().setVersion(JwtRsaSsaPssSignKeyManager.this.getVersion()).setAlgorithm(algorithm).setE(ByteString.copyFrom(rSAPublicKey.getPublicExponent().toByteArray())).setN(ByteString.copyFrom(rSAPublicKey.getModulus().toByteArray())).build()).setD(ByteString.copyFrom(rSAPrivateCrtKey.getPrivateExponent().toByteArray())).setP(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeP().toByteArray())).setQ(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeQ().toByteArray())).setDp(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeExponentP().toByteArray())).setDq(ByteString.copyFrom(rSAPrivateCrtKey.getPrimeExponentQ().toByteArray())).setCrt(ByteString.copyFrom(rSAPrivateCrtKey.getCrtCoefficient().toByteArray())).build();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public JwtRsaSsaPssPrivateKey deriveKey(JwtRsaSsaPssKeyFormat jwtRsaSsaPssKeyFormat, InputStream inputStream) {
                throw new UnsupportedOperationException();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<JwtRsaSsaPssKeyFormat>> keyFormats() {
                HashMap map = new HashMap();
                JwtRsaSsaPssAlgorithm jwtRsaSsaPssAlgorithm = JwtRsaSsaPssAlgorithm.PS256;
                BigInteger bigInteger = RSAKeyGenParameterSpec.F4;
                KeyTemplate.OutputPrefixType outputPrefixType = KeyTemplate.OutputPrefixType.RAW;
                map.put("JWT_PS256_2048_F4_RAW", JwtRsaSsaPssSignKeyManager.createKeyFormat(jwtRsaSsaPssAlgorithm, 2048, bigInteger, outputPrefixType));
                BigInteger bigInteger2 = RSAKeyGenParameterSpec.F4;
                KeyTemplate.OutputPrefixType outputPrefixType2 = KeyTemplate.OutputPrefixType.TINK;
                map.put("JWT_PS256_2048_F4", JwtRsaSsaPssSignKeyManager.createKeyFormat(jwtRsaSsaPssAlgorithm, 2048, bigInteger2, outputPrefixType2));
                map.put("JWT_PS256_3072_F4_RAW", JwtRsaSsaPssSignKeyManager.createKeyFormat(jwtRsaSsaPssAlgorithm, 3072, RSAKeyGenParameterSpec.F4, outputPrefixType));
                map.put("JWT_PS256_3072_F4", JwtRsaSsaPssSignKeyManager.createKeyFormat(jwtRsaSsaPssAlgorithm, 3072, RSAKeyGenParameterSpec.F4, outputPrefixType2));
                JwtRsaSsaPssAlgorithm jwtRsaSsaPssAlgorithm2 = JwtRsaSsaPssAlgorithm.PS384;
                map.put("JWT_PS384_3072_F4_RAW", JwtRsaSsaPssSignKeyManager.createKeyFormat(jwtRsaSsaPssAlgorithm2, 3072, RSAKeyGenParameterSpec.F4, outputPrefixType));
                map.put("JWT_PS384_3072_F4", JwtRsaSsaPssSignKeyManager.createKeyFormat(jwtRsaSsaPssAlgorithm2, 3072, RSAKeyGenParameterSpec.F4, outputPrefixType2));
                JwtRsaSsaPssAlgorithm jwtRsaSsaPssAlgorithm3 = JwtRsaSsaPssAlgorithm.PS512;
                map.put("JWT_PS512_4096_F4_RAW", JwtRsaSsaPssSignKeyManager.createKeyFormat(jwtRsaSsaPssAlgorithm3, 4096, RSAKeyGenParameterSpec.F4, outputPrefixType));
                map.put("JWT_PS512_4096_F4", JwtRsaSsaPssSignKeyManager.createKeyFormat(jwtRsaSsaPssAlgorithm3, 4096, RSAKeyGenParameterSpec.F4, outputPrefixType2));
                return Collections.unmodifiableMap(map);
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public JwtRsaSsaPssKeyFormat parseKeyFormat(ByteString byteString) {
                return JwtRsaSsaPssKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public void validateKeyFormat(JwtRsaSsaPssKeyFormat jwtRsaSsaPssKeyFormat) throws GeneralSecurityException {
                Validators.validateRsaModulusSize(jwtRsaSsaPssKeyFormat.getModulusSizeInBits());
                Validators.validateRsaPublicExponent(new BigInteger(1, jwtRsaSsaPssKeyFormat.getPublicExponent().toByteArray()));
            }
        };
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public JwtRsaSsaPssPrivateKey parseKey(ByteString byteString) {
        return JwtRsaSsaPssPrivateKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(JwtRsaSsaPssPrivateKey jwtRsaSsaPssPrivateKey) throws GeneralSecurityException {
        Validators.validateVersion(jwtRsaSsaPssPrivateKey.getVersion(), getVersion());
        Validators.validateRsaModulusSize(new BigInteger(1, jwtRsaSsaPssPrivateKey.getPublicKey().getN().toByteArray()).bitLength());
        Validators.validateRsaPublicExponent(new BigInteger(1, jwtRsaSsaPssPrivateKey.getPublicKey().getE().toByteArray()));
    }
}
