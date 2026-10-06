package com.google.crypto.tink.prf;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.AesCmacPrfKey;
import com.google.crypto.tink.proto.AesCmacPrfKeyFormat;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.subtle.PrfAesCmac;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class AesCmacPrfKeyManager extends KeyTypeManager<AesCmacPrfKey> {
    private static final int KEY_SIZE_IN_BYTES = 32;
    private static final int VERSION = 0;

    public AesCmacPrfKeyManager() {
        super(AesCmacPrfKey.class, new PrimitiveFactory<Prf, AesCmacPrfKey>(Prf.class) { // from class: com.google.crypto.tink.prf.AesCmacPrfKeyManager.1
            @Override // com.google.crypto.tink.internal.PrimitiveFactory
            public Prf getPrimitive(AesCmacPrfKey aesCmacPrfKey) {
                return new PrfAesCmac(aesCmacPrfKey.getKeyValue().toByteArray());
            }
        });
    }

    public static final KeyTemplate aes256CmacTemplate() {
        return KeyTemplate.create(new AesCmacPrfKeyManager().getKeyType(), AesCmacPrfKeyFormat.newBuilder().setKeySize(32).build().toByteArray(), KeyTemplate.OutputPrefixType.RAW);
    }

    public static void register(boolean z2) {
        Registry.registerKeyManager(new AesCmacPrfKeyManager(), z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void validateSize(int i2) throws GeneralSecurityException {
        if (i2 != 32) {
            throw new GeneralSecurityException("AesCmacPrfKey size wrong, must be 32 bytes");
        }
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesCmacPrfKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory<AesCmacPrfKeyFormat, AesCmacPrfKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<AesCmacPrfKeyFormat, AesCmacPrfKey>(AesCmacPrfKeyFormat.class) { // from class: com.google.crypto.tink.prf.AesCmacPrfKeyManager.2
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public AesCmacPrfKey createKey(AesCmacPrfKeyFormat aesCmacPrfKeyFormat) {
                return AesCmacPrfKey.newBuilder().setVersion(0).setKeyValue(ByteString.copyFrom(Random.randBytes(aesCmacPrfKeyFormat.getKeySize()))).build();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<AesCmacPrfKeyFormat>> keyFormats() {
                HashMap map = new HashMap();
                AesCmacPrfKeyFormat aesCmacPrfKeyFormatBuild = AesCmacPrfKeyFormat.newBuilder().setKeySize(32).build();
                KeyTemplate.OutputPrefixType outputPrefixType = KeyTemplate.OutputPrefixType.RAW;
                map.put("AES256_CMAC_PRF", new KeyTypeManager.KeyFactory.KeyFormat(aesCmacPrfKeyFormatBuild, outputPrefixType));
                map.put("AES_CMAC_PRF", new KeyTypeManager.KeyFactory.KeyFormat(AesCmacPrfKeyFormat.newBuilder().setKeySize(32).build(), outputPrefixType));
                return Collections.unmodifiableMap(map);
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public AesCmacPrfKeyFormat parseKeyFormat(ByteString byteString) {
                return AesCmacPrfKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public void validateKeyFormat(AesCmacPrfKeyFormat aesCmacPrfKeyFormat) throws GeneralSecurityException {
                AesCmacPrfKeyManager.validateSize(aesCmacPrfKeyFormat.getKeySize());
            }
        };
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public AesCmacPrfKey parseKey(ByteString byteString) {
        return AesCmacPrfKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(AesCmacPrfKey aesCmacPrfKey) throws GeneralSecurityException {
        Validators.validateVersion(aesCmacPrfKey.getVersion(), getVersion());
        validateSize(aesCmacPrfKey.getKeyValue().size());
    }
}
