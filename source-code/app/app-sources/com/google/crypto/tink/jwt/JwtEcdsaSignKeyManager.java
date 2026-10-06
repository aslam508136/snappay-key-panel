package com.google.crypto.tink.jwt;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.JwtEcdsaAlgorithm;
import com.google.crypto.tink.proto.JwtEcdsaKeyFormat;
import com.google.crypto.tink.proto.JwtEcdsaPrivateKey;
import com.google.crypto.tink.proto.JwtEcdsaPublicKey;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.subtle.EcdsaSignJce;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.SelfKeyTestValidators;
import com.google.crypto.tink.subtle.Validators;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPoint;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/* JADX INFO: loaded from: classes.dex */
public final class JwtEcdsaSignKeyManager extends PrivateKeyTypeManager<JwtEcdsaPrivateKey, JwtEcdsaPublicKey> {

    public static class JwtPublicKeySignFactory extends PrimitiveFactory<JwtPublicKeySignInternal, JwtEcdsaPrivateKey> {
        public JwtPublicKeySignFactory() {
            super(JwtPublicKeySignInternal.class);
        }

        private static final void selfTestKey(ECPrivateKey eCPrivateKey, JwtEcdsaPrivateKey jwtEcdsaPrivateKey) throws GeneralSecurityException {
            SelfKeyTestValidators.validateEcdsa(eCPrivateKey, EllipticCurves.getEcPublicKey(JwtEcdsaVerifyKeyManager.getCurve(jwtEcdsaPrivateKey.getPublicKey().getAlgorithm()), jwtEcdsaPrivateKey.getPublicKey().getX().toByteArray(), jwtEcdsaPrivateKey.getPublicKey().getY().toByteArray()), JwtEcdsaVerifyKeyManager.hashForEcdsaAlgorithm(jwtEcdsaPrivateKey.getPublicKey().getAlgorithm()), EllipticCurves.EcdsaEncoding.IEEE_P1363);
        }

        @Override // com.google.crypto.tink.internal.PrimitiveFactory
        public JwtPublicKeySignInternal getPrimitive(JwtEcdsaPrivateKey jwtEcdsaPrivateKey) throws GeneralSecurityException {
            ECPrivateKey ecPrivateKey = EllipticCurves.getEcPrivateKey(JwtEcdsaVerifyKeyManager.getCurve(jwtEcdsaPrivateKey.getPublicKey().getAlgorithm()), jwtEcdsaPrivateKey.getKeyValue().toByteArray());
            selfTestKey(ecPrivateKey, jwtEcdsaPrivateKey);
            JwtEcdsaAlgorithm algorithm = jwtEcdsaPrivateKey.getPublicKey().getAlgorithm();
            final EcdsaSignJce ecdsaSignJce = new EcdsaSignJce(ecPrivateKey, JwtEcdsaVerifyKeyManager.hashForEcdsaAlgorithm(algorithm), EllipticCurves.EcdsaEncoding.IEEE_P1363);
            final String strName = algorithm.name();
            final Optional optionalOf = jwtEcdsaPrivateKey.getPublicKey().hasCustomKid() ? Optional.of(jwtEcdsaPrivateKey.getPublicKey().getCustomKid().getValue()) : Optional.empty();
            return new JwtPublicKeySignInternal() { // from class: com.google.crypto.tink.jwt.JwtEcdsaSignKeyManager.JwtPublicKeySignFactory.1
                @Override // com.google.crypto.tink.jwt.JwtPublicKeySignInternal
                public String signAndEncodeWithKid(RawJwt rawJwt, Optional<String> optional) throws JwtInvalidException {
                    if (optionalOf.isPresent()) {
                        if (optional.isPresent()) {
                            throw new JwtInvalidException("custom_kid can only be set for RAW keys.");
                        }
                        optional = optionalOf;
                    }
                    String strCreateUnsignedCompact = JwtFormat.createUnsignedCompact(strName, optional, rawJwt);
                    return JwtFormat.createSignedCompact(strCreateUnsignedCompact, ecdsaSignJce.sign(strCreateUnsignedCompact.getBytes(StandardCharsets.US_ASCII)));
                }
            };
        }
    }

    public JwtEcdsaSignKeyManager() {
        super(JwtEcdsaPrivateKey.class, JwtEcdsaPublicKey.class, new JwtPublicKeySignFactory());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static KeyTypeManager.KeyFactory.KeyFormat<JwtEcdsaKeyFormat> createKeyFormat(JwtEcdsaAlgorithm jwtEcdsaAlgorithm, KeyTemplate.OutputPrefixType outputPrefixType) {
        return new KeyTypeManager.KeyFactory.KeyFormat<>(JwtEcdsaKeyFormat.newBuilder().setAlgorithm(jwtEcdsaAlgorithm).build(), outputPrefixType);
    }

    public static void registerPair(boolean z2) {
        Registry.registerAsymmetricKeyManagers(new JwtEcdsaSignKeyManager(), new JwtEcdsaVerifyKeyManager(), z2);
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.JwtEcdsaPrivateKey";
    }

    @Override // com.google.crypto.tink.internal.PrivateKeyTypeManager
    public JwtEcdsaPublicKey getPublicKey(JwtEcdsaPrivateKey jwtEcdsaPrivateKey) {
        return jwtEcdsaPrivateKey.getPublicKey();
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory<JwtEcdsaKeyFormat, JwtEcdsaPrivateKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<JwtEcdsaKeyFormat, JwtEcdsaPrivateKey>(JwtEcdsaKeyFormat.class) { // from class: com.google.crypto.tink.jwt.JwtEcdsaSignKeyManager.1
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public JwtEcdsaPrivateKey createKey(JwtEcdsaKeyFormat jwtEcdsaKeyFormat) {
                JwtEcdsaAlgorithm algorithm = jwtEcdsaKeyFormat.getAlgorithm();
                KeyPair keyPairGenerateKeyPair = EllipticCurves.generateKeyPair(JwtEcdsaVerifyKeyManager.getCurve(jwtEcdsaKeyFormat.getAlgorithm()));
                ECPublicKey eCPublicKey = (ECPublicKey) keyPairGenerateKeyPair.getPublic();
                ECPrivateKey eCPrivateKey = (ECPrivateKey) keyPairGenerateKeyPair.getPrivate();
                ECPoint w2 = eCPublicKey.getW();
                return JwtEcdsaPrivateKey.newBuilder().setVersion(JwtEcdsaSignKeyManager.this.getVersion()).setPublicKey(JwtEcdsaPublicKey.newBuilder().setVersion(JwtEcdsaSignKeyManager.this.getVersion()).setAlgorithm(algorithm).setX(ByteString.copyFrom(w2.getAffineX().toByteArray())).setY(ByteString.copyFrom(w2.getAffineY().toByteArray())).build()).setKeyValue(ByteString.copyFrom(eCPrivateKey.getS().toByteArray())).build();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public JwtEcdsaPrivateKey deriveKey(JwtEcdsaKeyFormat jwtEcdsaKeyFormat, InputStream inputStream) {
                throw new UnsupportedOperationException();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<JwtEcdsaKeyFormat>> keyFormats() {
                HashMap map = new HashMap();
                JwtEcdsaAlgorithm jwtEcdsaAlgorithm = JwtEcdsaAlgorithm.ES256;
                KeyTemplate.OutputPrefixType outputPrefixType = KeyTemplate.OutputPrefixType.RAW;
                map.put("JWT_ES256_RAW", JwtEcdsaSignKeyManager.createKeyFormat(jwtEcdsaAlgorithm, outputPrefixType));
                KeyTemplate.OutputPrefixType outputPrefixType2 = KeyTemplate.OutputPrefixType.TINK;
                map.put("JWT_ES256", JwtEcdsaSignKeyManager.createKeyFormat(jwtEcdsaAlgorithm, outputPrefixType2));
                JwtEcdsaAlgorithm jwtEcdsaAlgorithm2 = JwtEcdsaAlgorithm.ES384;
                map.put("JWT_ES384_RAW", JwtEcdsaSignKeyManager.createKeyFormat(jwtEcdsaAlgorithm2, outputPrefixType));
                map.put("JWT_ES384", JwtEcdsaSignKeyManager.createKeyFormat(jwtEcdsaAlgorithm2, outputPrefixType2));
                JwtEcdsaAlgorithm jwtEcdsaAlgorithm3 = JwtEcdsaAlgorithm.ES512;
                map.put("JWT_ES512_RAW", JwtEcdsaSignKeyManager.createKeyFormat(jwtEcdsaAlgorithm3, outputPrefixType));
                map.put("JWT_ES512", JwtEcdsaSignKeyManager.createKeyFormat(jwtEcdsaAlgorithm3, outputPrefixType2));
                return Collections.unmodifiableMap(map);
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public JwtEcdsaKeyFormat parseKeyFormat(ByteString byteString) {
                return JwtEcdsaKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public void validateKeyFormat(JwtEcdsaKeyFormat jwtEcdsaKeyFormat) throws GeneralSecurityException {
                JwtEcdsaVerifyKeyManager.validateEcdsaAlgorithm(jwtEcdsaKeyFormat.getAlgorithm());
            }
        };
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public JwtEcdsaPrivateKey parseKey(ByteString byteString) {
        return JwtEcdsaPrivateKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(JwtEcdsaPrivateKey jwtEcdsaPrivateKey) throws GeneralSecurityException {
        Validators.validateVersion(jwtEcdsaPrivateKey.getVersion(), getVersion());
        JwtEcdsaVerifyKeyManager.validateEcdsaAlgorithm(jwtEcdsaPrivateKey.getPublicKey().getAlgorithm());
    }
}
