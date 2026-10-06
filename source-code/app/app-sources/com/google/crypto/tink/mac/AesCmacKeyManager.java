package com.google.crypto.tink.mac;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Mac;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.MutablePrimitiveRegistry;
import com.google.crypto.tink.internal.PrimitiveConstructor;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.AesCmacKeyFormat;
import com.google.crypto.tink.proto.AesCmacParams;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.subtle.PrfAesCmac;
import com.google.crypto.tink.subtle.PrfMac;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class AesCmacKeyManager extends KeyTypeManager<com.google.crypto.tink.proto.AesCmacKey> {
    private static final PrimitiveConstructor<AesCmacKey, ChunkedMac> CHUNKED_MAC_PRIMITIVE_CONSTRUCTOR = PrimitiveConstructor.create(new a(8), AesCmacKey.class, ChunkedMac.class);
    private static final int KEY_SIZE_IN_BYTES = 32;
    private static final int MAX_TAG_SIZE_IN_BYTES = 16;
    private static final int MIN_TAG_SIZE_IN_BYTES = 10;
    private static final int VERSION = 0;

    public AesCmacKeyManager() {
        super(com.google.crypto.tink.proto.AesCmacKey.class, new PrimitiveFactory<Mac, com.google.crypto.tink.proto.AesCmacKey>(Mac.class) { // from class: com.google.crypto.tink.mac.AesCmacKeyManager.1
            @Override // com.google.crypto.tink.internal.PrimitiveFactory
            public Mac getPrimitive(com.google.crypto.tink.proto.AesCmacKey aesCmacKey) {
                return new PrfMac(new PrfAesCmac(aesCmacKey.getKeyValue().toByteArray()), aesCmacKey.getParams().getTagSize());
            }
        });
    }

    public static final KeyTemplate aes256CmacTemplate() {
        return KeyTemplate.create(new AesCmacKeyManager().getKeyType(), AesCmacKeyFormat.newBuilder().setKeySize(32).setParams(AesCmacParams.newBuilder().setTagSize(16).build()).build().toByteArray(), KeyTemplate.OutputPrefixType.TINK);
    }

    public static final KeyTemplate rawAes256CmacTemplate() {
        return KeyTemplate.create(new AesCmacKeyManager().getKeyType(), AesCmacKeyFormat.newBuilder().setKeySize(32).setParams(AesCmacParams.newBuilder().setTagSize(16).build()).build().toByteArray(), KeyTemplate.OutputPrefixType.RAW);
    }

    public static void register(boolean z2) {
        Registry.registerKeyManager(new AesCmacKeyManager(), z2);
        AesCmacProtoSerialization.register();
        MutablePrimitiveRegistry.globalInstance().registerPrimitiveConstructor(CHUNKED_MAC_PRIMITIVE_CONSTRUCTOR);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void validateParams(AesCmacParams aesCmacParams) throws GeneralSecurityException {
        if (aesCmacParams.getTagSize() < 10) {
            throw new GeneralSecurityException("tag size too short");
        }
        if (aesCmacParams.getTagSize() > 16) {
            throw new GeneralSecurityException("tag size too long");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void validateSize(int i2) throws GeneralSecurityException {
        if (i2 != 32) {
            throw new GeneralSecurityException("AesCmacKey size wrong, must be 32 bytes");
        }
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesCmacKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory<AesCmacKeyFormat, com.google.crypto.tink.proto.AesCmacKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<AesCmacKeyFormat, com.google.crypto.tink.proto.AesCmacKey>(AesCmacKeyFormat.class) { // from class: com.google.crypto.tink.mac.AesCmacKeyManager.2
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public com.google.crypto.tink.proto.AesCmacKey createKey(AesCmacKeyFormat aesCmacKeyFormat) {
                return com.google.crypto.tink.proto.AesCmacKey.newBuilder().setVersion(0).setKeyValue(ByteString.copyFrom(Random.randBytes(aesCmacKeyFormat.getKeySize()))).setParams(aesCmacKeyFormat.getParams()).build();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<AesCmacKeyFormat>> keyFormats() {
                HashMap map = new HashMap();
                AesCmacKeyFormat aesCmacKeyFormatBuild = AesCmacKeyFormat.newBuilder().setKeySize(32).setParams(AesCmacParams.newBuilder().setTagSize(16).build()).build();
                KeyTemplate.OutputPrefixType outputPrefixType = KeyTemplate.OutputPrefixType.TINK;
                map.put("AES_CMAC", new KeyTypeManager.KeyFactory.KeyFormat(aesCmacKeyFormatBuild, outputPrefixType));
                map.put("AES256_CMAC", new KeyTypeManager.KeyFactory.KeyFormat(AesCmacKeyFormat.newBuilder().setKeySize(32).setParams(AesCmacParams.newBuilder().setTagSize(16).build()).build(), outputPrefixType));
                map.put("AES256_CMAC_RAW", new KeyTypeManager.KeyFactory.KeyFormat(AesCmacKeyFormat.newBuilder().setKeySize(32).setParams(AesCmacParams.newBuilder().setTagSize(16).build()).build(), KeyTemplate.OutputPrefixType.RAW));
                return Collections.unmodifiableMap(map);
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public AesCmacKeyFormat parseKeyFormat(ByteString byteString) {
                return AesCmacKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public void validateKeyFormat(AesCmacKeyFormat aesCmacKeyFormat) throws GeneralSecurityException {
                AesCmacKeyManager.validateParams(aesCmacKeyFormat.getParams());
                AesCmacKeyManager.validateSize(aesCmacKeyFormat.getKeySize());
            }
        };
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public com.google.crypto.tink.proto.AesCmacKey parseKey(ByteString byteString) {
        return com.google.crypto.tink.proto.AesCmacKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(com.google.crypto.tink.proto.AesCmacKey aesCmacKey) throws GeneralSecurityException {
        Validators.validateVersion(aesCmacKey.getVersion(), getVersion());
        validateSize(aesCmacKey.getKeyValue().size());
        validateParams(aesCmacKey.getParams());
    }
}
