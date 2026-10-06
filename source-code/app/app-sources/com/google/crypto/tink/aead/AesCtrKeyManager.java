package com.google.crypto.tink.aead;

import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.AesCtrKey;
import com.google.crypto.tink.proto.AesCtrKeyFormat;
import com.google.crypto.tink.proto.AesCtrParams;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.subtle.AesCtrJceCipher;
import com.google.crypto.tink.subtle.IndCpaCipher;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes.dex */
public class AesCtrKeyManager extends KeyTypeManager<AesCtrKey> {
    private static final int MIN_IV_SIZE_IN_BYTES = 12;

    public AesCtrKeyManager() {
        super(AesCtrKey.class, new PrimitiveFactory<IndCpaCipher, AesCtrKey>(IndCpaCipher.class) { // from class: com.google.crypto.tink.aead.AesCtrKeyManager.1
            @Override // com.google.crypto.tink.internal.PrimitiveFactory
            public IndCpaCipher getPrimitive(AesCtrKey aesCtrKey) {
                return new AesCtrJceCipher(aesCtrKey.getKeyValue().toByteArray(), aesCtrKey.getParams().getIvSize());
            }
        });
    }

    public static void register(boolean z2) {
        Registry.registerKeyManager(new AesCtrKeyManager(), z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void validateParams(AesCtrParams aesCtrParams) throws GeneralSecurityException {
        if (aesCtrParams.getIvSize() < 12 || aesCtrParams.getIvSize() > 16) {
            throw new GeneralSecurityException("invalid IV size");
        }
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesCtrKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory<AesCtrKeyFormat, AesCtrKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<AesCtrKeyFormat, AesCtrKey>(AesCtrKeyFormat.class) { // from class: com.google.crypto.tink.aead.AesCtrKeyManager.2
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public AesCtrKey createKey(AesCtrKeyFormat aesCtrKeyFormat) {
                return AesCtrKey.newBuilder().setParams(aesCtrKeyFormat.getParams()).setKeyValue(ByteString.copyFrom(Random.randBytes(aesCtrKeyFormat.getKeySize()))).setVersion(AesCtrKeyManager.this.getVersion()).build();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public AesCtrKeyFormat parseKeyFormat(ByteString byteString) {
                return AesCtrKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public void validateKeyFormat(AesCtrKeyFormat aesCtrKeyFormat) throws GeneralSecurityException {
                Validators.validateAesKeySize(aesCtrKeyFormat.getKeySize());
                AesCtrKeyManager.this.validateParams(aesCtrKeyFormat.getParams());
            }
        };
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public AesCtrKey parseKey(ByteString byteString) {
        return AesCtrKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(AesCtrKey aesCtrKey) {
        Validators.validateVersion(aesCtrKey.getVersion(), getVersion());
        Validators.validateAesKeySize(aesCtrKey.getKeyValue().size());
        validateParams(aesCtrKey.getParams());
    }
}
