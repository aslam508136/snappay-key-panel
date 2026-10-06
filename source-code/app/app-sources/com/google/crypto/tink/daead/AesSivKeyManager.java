package com.google.crypto.tink.daead;

import com.google.crypto.tink.DeterministicAead;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.AesSivKey;
import com.google.crypto.tink.proto.AesSivKeyFormat;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.subtle.AesSiv;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class AesSivKeyManager extends KeyTypeManager<AesSivKey> {
    private static final int KEY_SIZE_IN_BYTES = 64;

    public AesSivKeyManager() {
        super(AesSivKey.class, new PrimitiveFactory<DeterministicAead, AesSivKey>(DeterministicAead.class) { // from class: com.google.crypto.tink.daead.AesSivKeyManager.1
            @Override // com.google.crypto.tink.internal.PrimitiveFactory
            public DeterministicAead getPrimitive(AesSivKey aesSivKey) {
                return new AesSiv(aesSivKey.getKeyValue().toByteArray());
            }
        });
    }

    public static final KeyTemplate aes256SivTemplate() {
        return createKeyTemplate(64, KeyTemplate.OutputPrefixType.TINK);
    }

    private static KeyTemplate createKeyTemplate(int i2, KeyTemplate.OutputPrefixType outputPrefixType) {
        return KeyTemplate.create(new AesSivKeyManager().getKeyType(), AesSivKeyFormat.newBuilder().setKeySize(i2).build().toByteArray(), outputPrefixType);
    }

    public static final KeyTemplate rawAes256SivTemplate() {
        return createKeyTemplate(64, KeyTemplate.OutputPrefixType.RAW);
    }

    public static void register(boolean z2) {
        Registry.registerKeyManager(new AesSivKeyManager(), z2);
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesSivKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory<AesSivKeyFormat, AesSivKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<AesSivKeyFormat, AesSivKey>(AesSivKeyFormat.class) { // from class: com.google.crypto.tink.daead.AesSivKeyManager.2
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public AesSivKey createKey(AesSivKeyFormat aesSivKeyFormat) {
                return AesSivKey.newBuilder().setKeyValue(ByteString.copyFrom(Random.randBytes(aesSivKeyFormat.getKeySize()))).setVersion(AesSivKeyManager.this.getVersion()).build();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public AesSivKey deriveKey(AesSivKeyFormat aesSivKeyFormat, InputStream inputStream) throws GeneralSecurityException {
                Validators.validateVersion(aesSivKeyFormat.getVersion(), AesSivKeyManager.this.getVersion());
                byte[] bArr = new byte[64];
                try {
                    KeyTypeManager.KeyFactory.readFully(inputStream, bArr);
                    return AesSivKey.newBuilder().setKeyValue(ByteString.copyFrom(bArr)).setVersion(AesSivKeyManager.this.getVersion()).build();
                } catch (IOException e2) {
                    throw new GeneralSecurityException("Reading pseudorandomness failed", e2);
                }
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<AesSivKeyFormat>> keyFormats() {
                HashMap map = new HashMap();
                map.put("AES256_SIV", new KeyTypeManager.KeyFactory.KeyFormat(AesSivKeyFormat.newBuilder().setKeySize(64).build(), KeyTemplate.OutputPrefixType.TINK));
                map.put("AES256_SIV_RAW", new KeyTypeManager.KeyFactory.KeyFormat(AesSivKeyFormat.newBuilder().setKeySize(64).build(), KeyTemplate.OutputPrefixType.RAW));
                return Collections.unmodifiableMap(map);
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public AesSivKeyFormat parseKeyFormat(ByteString byteString) {
                return AesSivKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public void validateKeyFormat(AesSivKeyFormat aesSivKeyFormat) throws InvalidAlgorithmParameterException {
                if (aesSivKeyFormat.getKeySize() == 64) {
                    return;
                }
                throw new InvalidAlgorithmParameterException("invalid key size: " + aesSivKeyFormat.getKeySize() + ". Valid keys must have 64 bytes.");
            }
        };
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public AesSivKey parseKey(ByteString byteString) {
        return AesSivKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(AesSivKey aesSivKey) throws GeneralSecurityException {
        Validators.validateVersion(aesSivKey.getVersion(), getVersion());
        if (aesSivKey.getKeyValue().size() == 64) {
            return;
        }
        throw new InvalidKeyException("invalid key size: " + aesSivKey.getKeyValue().size() + ". Valid keys must have 64 bytes.");
    }
}
