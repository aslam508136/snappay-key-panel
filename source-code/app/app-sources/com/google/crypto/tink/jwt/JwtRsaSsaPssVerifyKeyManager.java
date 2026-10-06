package com.google.crypto.tink.jwt;

import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.JwtRsaSsaPssAlgorithm;
import com.google.crypto.tink.proto.JwtRsaSsaPssPublicKey;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.Enums;
import com.google.crypto.tink.subtle.RsaSsaPssVerifyJce;
import com.google.crypto.tink.subtle.Validators;
import i0.e;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Optional;

/* JADX INFO: loaded from: classes.dex */
class JwtRsaSsaPssVerifyKeyManager extends KeyTypeManager<JwtRsaSsaPssPublicKey> {

    /* JADX INFO: renamed from: com.google.crypto.tink.jwt.JwtRsaSsaPssVerifyKeyManager$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPssAlgorithm;

        static {
            int[] iArr = new int[JwtRsaSsaPssAlgorithm.values().length];
            $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPssAlgorithm = iArr;
            try {
                iArr[JwtRsaSsaPssAlgorithm.PS256.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPssAlgorithm[JwtRsaSsaPssAlgorithm.PS384.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPssAlgorithm[JwtRsaSsaPssAlgorithm.PS512.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public JwtRsaSsaPssVerifyKeyManager() {
        super(JwtRsaSsaPssPublicKey.class, new PrimitiveFactory<JwtPublicKeyVerifyInternal, JwtRsaSsaPssPublicKey>(JwtPublicKeyVerifyInternal.class) { // from class: com.google.crypto.tink.jwt.JwtRsaSsaPssVerifyKeyManager.1
            @Override // com.google.crypto.tink.internal.PrimitiveFactory
            public JwtPublicKeyVerifyInternal getPrimitive(JwtRsaSsaPssPublicKey jwtRsaSsaPssPublicKey) throws GeneralSecurityException {
                RSAPublicKey rSAPublicKeyCreatePublicKey = JwtRsaSsaPssVerifyKeyManager.createPublicKey(jwtRsaSsaPssPublicKey);
                Enums.HashType hashTypeHashForPssAlgorithm = JwtRsaSsaPssVerifyKeyManager.hashForPssAlgorithm(jwtRsaSsaPssPublicKey.getAlgorithm());
                final RsaSsaPssVerifyJce rsaSsaPssVerifyJce = new RsaSsaPssVerifyJce(rSAPublicKeyCreatePublicKey, hashTypeHashForPssAlgorithm, hashTypeHashForPssAlgorithm, JwtRsaSsaPssVerifyKeyManager.saltLengthForPssAlgorithm(jwtRsaSsaPssPublicKey.getAlgorithm()));
                final String strName = jwtRsaSsaPssPublicKey.getAlgorithm().name();
                final Optional optionalOf = jwtRsaSsaPssPublicKey.hasCustomKid() ? Optional.of(jwtRsaSsaPssPublicKey.getCustomKid().getValue()) : Optional.empty();
                return new JwtPublicKeyVerifyInternal() { // from class: com.google.crypto.tink.jwt.JwtRsaSsaPssVerifyKeyManager.1.1
                    @Override // com.google.crypto.tink.jwt.JwtPublicKeyVerifyInternal
                    public VerifiedJwt verifyAndDecodeWithKid(String str, JwtValidator jwtValidator, Optional<String> optional) throws GeneralSecurityException {
                        JwtFormat.Parts partsSplitSignedCompact = JwtFormat.splitSignedCompact(str);
                        rsaSsaPssVerifyJce.verify(partsSplitSignedCompact.signatureOrMac, partsSplitSignedCompact.unsignedCompact.getBytes(StandardCharsets.US_ASCII));
                        e json = JsonUtil.parseJson(partsSplitSignedCompact.header);
                        JwtFormat.validateHeader(strName, optional, optionalOf, json);
                        return jwtValidator.validate(RawJwt.fromJsonPayload(JwtFormat.getTypeHeader(json), partsSplitSignedCompact.payload));
                    }
                };
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final RSAPublicKey createPublicKey(JwtRsaSsaPssPublicKey jwtRsaSsaPssPublicKey) {
        return (RSAPublicKey) EngineFactory.KEY_FACTORY.getInstance("RSA").generatePublic(new RSAPublicKeySpec(new BigInteger(1, jwtRsaSsaPssPublicKey.getN().toByteArray()), new BigInteger(1, jwtRsaSsaPssPublicKey.getE().toByteArray())));
    }

    public static final Enums.HashType hashForPssAlgorithm(JwtRsaSsaPssAlgorithm jwtRsaSsaPssAlgorithm) throws GeneralSecurityException {
        int i2 = AnonymousClass2.$SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPssAlgorithm[jwtRsaSsaPssAlgorithm.ordinal()];
        if (i2 == 1) {
            return Enums.HashType.SHA256;
        }
        if (i2 == 2) {
            return Enums.HashType.SHA384;
        }
        if (i2 == 3) {
            return Enums.HashType.SHA512;
        }
        throw new GeneralSecurityException("unknown algorithm " + jwtRsaSsaPssAlgorithm.name());
    }

    public static final int saltLengthForPssAlgorithm(JwtRsaSsaPssAlgorithm jwtRsaSsaPssAlgorithm) throws GeneralSecurityException {
        int i2 = AnonymousClass2.$SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPssAlgorithm[jwtRsaSsaPssAlgorithm.ordinal()];
        if (i2 == 1) {
            return 32;
        }
        if (i2 == 2) {
            return 48;
        }
        if (i2 == 3) {
            return 64;
        }
        throw new GeneralSecurityException("unknown algorithm " + jwtRsaSsaPssAlgorithm.name());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.JwtRsaSsaPssPublicKey";
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
    public JwtRsaSsaPssPublicKey parseKey(ByteString byteString) {
        return JwtRsaSsaPssPublicKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(JwtRsaSsaPssPublicKey jwtRsaSsaPssPublicKey) throws GeneralSecurityException {
        Validators.validateVersion(jwtRsaSsaPssPublicKey.getVersion(), getVersion());
        Validators.validateRsaModulusSize(new BigInteger(1, jwtRsaSsaPssPublicKey.getN().toByteArray()).bitLength());
        Validators.validateRsaPublicExponent(new BigInteger(1, jwtRsaSsaPssPublicKey.getE().toByteArray()));
    }
}
