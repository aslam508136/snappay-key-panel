package com.google.crypto.tink.signature;

import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.signature.internal.SigUtil;
import com.google.crypto.tink.subtle.EcdsaVerifyJce;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes.dex */
class EcdsaVerifyKeyManager extends KeyTypeManager<com.google.crypto.tink.proto.EcdsaPublicKey> {
    public EcdsaVerifyKeyManager() {
        super(com.google.crypto.tink.proto.EcdsaPublicKey.class, new PrimitiveFactory<PublicKeyVerify, com.google.crypto.tink.proto.EcdsaPublicKey>(PublicKeyVerify.class) { // from class: com.google.crypto.tink.signature.EcdsaVerifyKeyManager.1
            @Override // com.google.crypto.tink.internal.PrimitiveFactory
            public PublicKeyVerify getPrimitive(com.google.crypto.tink.proto.EcdsaPublicKey ecdsaPublicKey) {
                return new EcdsaVerifyJce(EllipticCurves.getEcPublicKey(SigUtil.toCurveType(ecdsaPublicKey.getParams().getCurve()), ecdsaPublicKey.getX().toByteArray(), ecdsaPublicKey.getY().toByteArray()), SigUtil.toHashType(ecdsaPublicKey.getParams().getHashType()), SigUtil.toEcdsaEncoding(ecdsaPublicKey.getParams().getEncoding()));
            }
        });
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public TinkFipsUtil.AlgorithmFipsCompatibility fipsStatus() {
        return TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.EcdsaPublicKey";
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
    public com.google.crypto.tink.proto.EcdsaPublicKey parseKey(ByteString byteString) {
        return com.google.crypto.tink.proto.EcdsaPublicKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(com.google.crypto.tink.proto.EcdsaPublicKey ecdsaPublicKey) throws GeneralSecurityException {
        Validators.validateVersion(ecdsaPublicKey.getVersion(), getVersion());
        SigUtil.validateEcdsaParams(ecdsaPublicKey.getParams());
    }
}
