package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.KmsClients;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KmsAeadKey;
import com.google.crypto.tink.proto.KmsAeadKeyFormat;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes.dex */
public class KmsAeadKeyManager extends KeyTypeManager<KmsAeadKey> {
    public KmsAeadKeyManager() {
        super(KmsAeadKey.class, new PrimitiveFactory<Aead, KmsAeadKey>(Aead.class) { // from class: com.google.crypto.tink.aead.KmsAeadKeyManager.1
            @Override // com.google.crypto.tink.internal.PrimitiveFactory
            public Aead getPrimitive(KmsAeadKey kmsAeadKey) {
                String keyUri = kmsAeadKey.getParams().getKeyUri();
                return KmsClients.get(keyUri).getAead(keyUri);
            }
        });
    }

    public static KmsAeadKeyFormat createKeyFormat(String str) {
        return KmsAeadKeyFormat.newBuilder().setKeyUri(str).build();
    }

    public static KeyTemplate createKeyTemplate(String str) {
        return KeyTemplate.create(new KmsAeadKeyManager().getKeyType(), createKeyFormat(str).toByteArray(), KeyTemplate.OutputPrefixType.RAW);
    }

    public static void register(boolean z2) {
        Registry.registerKeyManager(new KmsAeadKeyManager(), z2);
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.KmsAeadKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory<KmsAeadKeyFormat, KmsAeadKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<KmsAeadKeyFormat, KmsAeadKey>(KmsAeadKeyFormat.class) { // from class: com.google.crypto.tink.aead.KmsAeadKeyManager.2
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public KmsAeadKey createKey(KmsAeadKeyFormat kmsAeadKeyFormat) {
                return KmsAeadKey.newBuilder().setParams(kmsAeadKeyFormat).setVersion(KmsAeadKeyManager.this.getVersion()).build();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public KmsAeadKeyFormat parseKeyFormat(ByteString byteString) {
                return KmsAeadKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public void validateKeyFormat(KmsAeadKeyFormat kmsAeadKeyFormat) {
            }
        };
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.REMOTE;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KmsAeadKey parseKey(ByteString byteString) {
        return KmsAeadKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(KmsAeadKey kmsAeadKey) throws GeneralSecurityException {
        Validators.validateVersion(kmsAeadKey.getVersion(), getVersion());
    }
}
