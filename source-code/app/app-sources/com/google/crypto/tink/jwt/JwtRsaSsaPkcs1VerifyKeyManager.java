package com.google.crypto.tink.jwt;

import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.JwtRsaSsaPkcs1Algorithm;
import com.google.crypto.tink.proto.JwtRsaSsaPkcs1PublicKey;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.Enums;
import com.google.crypto.tink.subtle.RsaSsaPkcs1VerifyJce;
import com.google.crypto.tink.subtle.Validators;
import i0.e;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Optional;

/* JADX INFO: loaded from: classes.dex */
class JwtRsaSsaPkcs1VerifyKeyManager extends KeyTypeManager<JwtRsaSsaPkcs1PublicKey> {

    /* JADX INFO: renamed from: com.google.crypto.tink.jwt.JwtRsaSsaPkcs1VerifyKeyManager$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPkcs1Algorithm;

        static {
            int[] iArr = new int[JwtRsaSsaPkcs1Algorithm.values().length];
            $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPkcs1Algorithm = iArr;
            try {
                iArr[JwtRsaSsaPkcs1Algorithm.RS256.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPkcs1Algorithm[JwtRsaSsaPkcs1Algorithm.RS384.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPkcs1Algorithm[JwtRsaSsaPkcs1Algorithm.RS512.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public JwtRsaSsaPkcs1VerifyKeyManager() {
        super(JwtRsaSsaPkcs1PublicKey.class, new PrimitiveFactory<JwtPublicKeyVerifyInternal, JwtRsaSsaPkcs1PublicKey>(JwtPublicKeyVerifyInternal.class) { // from class: com.google.crypto.tink.jwt.JwtRsaSsaPkcs1VerifyKeyManager.1
            @Override // com.google.crypto.tink.internal.PrimitiveFactory
            public JwtPublicKeyVerifyInternal getPrimitive(JwtRsaSsaPkcs1PublicKey jwtRsaSsaPkcs1PublicKey) {
                final RsaSsaPkcs1VerifyJce rsaSsaPkcs1VerifyJce = new RsaSsaPkcs1VerifyJce(JwtRsaSsaPkcs1VerifyKeyManager.createPublicKey(jwtRsaSsaPkcs1PublicKey), JwtRsaSsaPkcs1VerifyKeyManager.hashForPkcs1Algorithm(jwtRsaSsaPkcs1PublicKey.getAlgorithm()));
                final String strName = jwtRsaSsaPkcs1PublicKey.getAlgorithm().name();
                final Optional optionalOf = jwtRsaSsaPkcs1PublicKey.hasCustomKid() ? Optional.of(jwtRsaSsaPkcs1PublicKey.getCustomKid().getValue()) : Optional.empty();
                return new JwtPublicKeyVerifyInternal() { // from class: com.google.crypto.tink.jwt.JwtRsaSsaPkcs1VerifyKeyManager.1.1
                    @Override // com.google.crypto.tink.jwt.JwtPublicKeyVerifyInternal
                    public VerifiedJwt verifyAndDecodeWithKid(String str, JwtValidator jwtValidator, Optional<String> optional) throws GeneralSecurityException {
                        JwtFormat.Parts partsSplitSignedCompact = JwtFormat.splitSignedCompact(str);
                        rsaSsaPkcs1VerifyJce.verify(partsSplitSignedCompact.signatureOrMac, partsSplitSignedCompact.unsignedCompact.getBytes(StandardCharsets.US_ASCII));
                        e json = JsonUtil.parseJson(partsSplitSignedCompact.header);
                        JwtFormat.validateHeader(strName, optional, optionalOf, json);
                        return jwtValidator.validate(RawJwt.fromJsonPayload(JwtFormat.getTypeHeader(json), partsSplitSignedCompact.payload));
                    }
                };
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final RSAPublicKey createPublicKey(JwtRsaSsaPkcs1PublicKey jwtRsaSsaPkcs1PublicKey) {
        return (RSAPublicKey) EngineFactory.KEY_FACTORY.getInstance("RSA").generatePublic(new RSAPublicKeySpec(new BigInteger(1, jwtRsaSsaPkcs1PublicKey.getN().toByteArray()), new BigInteger(1, jwtRsaSsaPkcs1PublicKey.getE().toByteArray())));
    }

    public static Enums.HashType hashForPkcs1Algorithm(JwtRsaSsaPkcs1Algorithm jwtRsaSsaPkcs1Algorithm) throws GeneralSecurityException {
        int i2 = AnonymousClass2.$SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPkcs1Algorithm[jwtRsaSsaPkcs1Algorithm.ordinal()];
        if (i2 == 1) {
            return Enums.HashType.SHA256;
        }
        if (i2 == 2) {
            return Enums.HashType.SHA384;
        }
        if (i2 == 3) {
            return Enums.HashType.SHA512;
        }
        throw new GeneralSecurityException("unknown algorithm " + jwtRsaSsaPkcs1Algorithm.name());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.JwtRsaSsaPkcs1PublicKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public JwtRsaSsaPkcs1PublicKey parseKey(ByteString byteString) {
        return JwtRsaSsaPkcs1PublicKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(JwtRsaSsaPkcs1PublicKey jwtRsaSsaPkcs1PublicKey) throws GeneralSecurityException {
        Validators.validateVersion(jwtRsaSsaPkcs1PublicKey.getVersion(), getVersion());
        Validators.validateRsaModulusSize(new BigInteger(1, jwtRsaSsaPkcs1PublicKey.getN().toByteArray()).bitLength());
        Validators.validateRsaPublicExponent(new BigInteger(1, jwtRsaSsaPkcs1PublicKey.getE().toByteArray()));
    }
}
