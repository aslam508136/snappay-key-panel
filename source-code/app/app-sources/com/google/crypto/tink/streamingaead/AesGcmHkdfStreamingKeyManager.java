package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.StreamingAead;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.AesGcmHkdfStreamingKey;
import com.google.crypto.tink.proto.AesGcmHkdfStreamingKeyFormat;
import com.google.crypto.tink.proto.AesGcmHkdfStreamingParams;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.subtle.AesGcmHkdfStreaming;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class AesGcmHkdfStreamingKeyManager extends KeyTypeManager<AesGcmHkdfStreamingKey> {
    private static final int NONCE_PREFIX_IN_BYTES = 7;
    private static final int TAG_SIZE_IN_BYTES = 16;

    public AesGcmHkdfStreamingKeyManager() {
        super(AesGcmHkdfStreamingKey.class, new PrimitiveFactory<StreamingAead, AesGcmHkdfStreamingKey>(StreamingAead.class) { // from class: com.google.crypto.tink.streamingaead.AesGcmHkdfStreamingKeyManager.1
            @Override // com.google.crypto.tink.internal.PrimitiveFactory
            public StreamingAead getPrimitive(AesGcmHkdfStreamingKey aesGcmHkdfStreamingKey) {
                return new AesGcmHkdfStreaming(aesGcmHkdfStreamingKey.getKeyValue().toByteArray(), StreamingAeadUtil.toHmacAlgo(aesGcmHkdfStreamingKey.getParams().getHkdfHashType()), aesGcmHkdfStreamingKey.getParams().getDerivedKeySize(), aesGcmHkdfStreamingKey.getParams().getCiphertextSegmentSize(), 0);
            }
        });
    }

    public static final KeyTemplate aes128GcmHkdf1MBTemplate() {
        return createKeyTemplate(16, HashType.SHA256, 16, 1048576);
    }

    public static final KeyTemplate aes128GcmHkdf4KBTemplate() {
        return createKeyTemplate(16, HashType.SHA256, 16, 4096);
    }

    public static final KeyTemplate aes256GcmHkdf1MBTemplate() {
        return createKeyTemplate(32, HashType.SHA256, 32, 1048576);
    }

    public static final KeyTemplate aes256GcmHkdf4KBTemplate() {
        return createKeyTemplate(32, HashType.SHA256, 32, 4096);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static AesGcmHkdfStreamingKeyFormat createKeyFormat(int i2, HashType hashType, int i3, int i4) {
        return AesGcmHkdfStreamingKeyFormat.newBuilder().setKeySize(i2).setParams(AesGcmHkdfStreamingParams.newBuilder().setCiphertextSegmentSize(i4).setDerivedKeySize(i3).setHkdfHashType(hashType).build()).build();
    }

    private static KeyTemplate createKeyTemplate(int i2, HashType hashType, int i3, int i4) {
        return KeyTemplate.create(new AesGcmHkdfStreamingKeyManager().getKeyType(), createKeyFormat(i2, hashType, i3, i4).toByteArray(), KeyTemplate.OutputPrefixType.RAW);
    }

    public static void register(boolean z2) {
        Registry.registerKeyManager(new AesGcmHkdfStreamingKeyManager(), z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void validateParams(AesGcmHkdfStreamingParams aesGcmHkdfStreamingParams) throws GeneralSecurityException {
        Validators.validateAesKeySize(aesGcmHkdfStreamingParams.getDerivedKeySize());
        if (aesGcmHkdfStreamingParams.getHkdfHashType() == HashType.UNKNOWN_HASH) {
            throw new GeneralSecurityException("unknown HKDF hash type");
        }
        if (aesGcmHkdfStreamingParams.getCiphertextSegmentSize() < aesGcmHkdfStreamingParams.getDerivedKeySize() + 7 + 16 + 2) {
            throw new GeneralSecurityException("ciphertext_segment_size must be at least (derived_key_size + NONCE_PREFIX_IN_BYTES + TAG_SIZE_IN_BYTES + 2)");
        }
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesGcmHkdfStreamingKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory<AesGcmHkdfStreamingKeyFormat, AesGcmHkdfStreamingKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<AesGcmHkdfStreamingKeyFormat, AesGcmHkdfStreamingKey>(AesGcmHkdfStreamingKeyFormat.class) { // from class: com.google.crypto.tink.streamingaead.AesGcmHkdfStreamingKeyManager.2
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public AesGcmHkdfStreamingKey createKey(AesGcmHkdfStreamingKeyFormat aesGcmHkdfStreamingKeyFormat) {
                return AesGcmHkdfStreamingKey.newBuilder().setKeyValue(ByteString.copyFrom(Random.randBytes(aesGcmHkdfStreamingKeyFormat.getKeySize()))).setParams(aesGcmHkdfStreamingKeyFormat.getParams()).setVersion(AesGcmHkdfStreamingKeyManager.this.getVersion()).build();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public AesGcmHkdfStreamingKey deriveKey(AesGcmHkdfStreamingKeyFormat aesGcmHkdfStreamingKeyFormat, InputStream inputStream) throws GeneralSecurityException {
                Validators.validateVersion(aesGcmHkdfStreamingKeyFormat.getVersion(), AesGcmHkdfStreamingKeyManager.this.getVersion());
                byte[] bArr = new byte[aesGcmHkdfStreamingKeyFormat.getKeySize()];
                try {
                    KeyTypeManager.KeyFactory.readFully(inputStream, bArr);
                    return AesGcmHkdfStreamingKey.newBuilder().setKeyValue(ByteString.copyFrom(bArr)).setParams(aesGcmHkdfStreamingKeyFormat.getParams()).setVersion(AesGcmHkdfStreamingKeyManager.this.getVersion()).build();
                } catch (IOException e2) {
                    throw new GeneralSecurityException("Reading pseudorandomness failed", e2);
                }
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<AesGcmHkdfStreamingKeyFormat>> keyFormats() {
                HashMap map = new HashMap();
                HashType hashType = HashType.SHA256;
                AesGcmHkdfStreamingKeyFormat aesGcmHkdfStreamingKeyFormatCreateKeyFormat = AesGcmHkdfStreamingKeyManager.createKeyFormat(16, hashType, 16, 4096);
                KeyTemplate.OutputPrefixType outputPrefixType = KeyTemplate.OutputPrefixType.RAW;
                map.put("AES128_GCM_HKDF_4KB", new KeyTypeManager.KeyFactory.KeyFormat(aesGcmHkdfStreamingKeyFormatCreateKeyFormat, outputPrefixType));
                map.put("AES128_GCM_HKDF_1MB", new KeyTypeManager.KeyFactory.KeyFormat(AesGcmHkdfStreamingKeyManager.createKeyFormat(16, hashType, 16, 1048576), outputPrefixType));
                map.put("AES256_GCM_HKDF_4KB", new KeyTypeManager.KeyFactory.KeyFormat(AesGcmHkdfStreamingKeyManager.createKeyFormat(32, hashType, 32, 4096), outputPrefixType));
                map.put("AES256_GCM_HKDF_1MB", new KeyTypeManager.KeyFactory.KeyFormat(AesGcmHkdfStreamingKeyManager.createKeyFormat(32, hashType, 32, 1048576), outputPrefixType));
                return Collections.unmodifiableMap(map);
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public AesGcmHkdfStreamingKeyFormat parseKeyFormat(ByteString byteString) {
                return AesGcmHkdfStreamingKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public void validateKeyFormat(AesGcmHkdfStreamingKeyFormat aesGcmHkdfStreamingKeyFormat) throws GeneralSecurityException {
                if (aesGcmHkdfStreamingKeyFormat.getKeySize() < 16) {
                    throw new GeneralSecurityException("key_size must be at least 16 bytes");
                }
                AesGcmHkdfStreamingKeyManager.validateParams(aesGcmHkdfStreamingKeyFormat.getParams());
            }
        };
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public AesGcmHkdfStreamingKey parseKey(ByteString byteString) {
        return AesGcmHkdfStreamingKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(AesGcmHkdfStreamingKey aesGcmHkdfStreamingKey) throws GeneralSecurityException {
        Validators.validateVersion(aesGcmHkdfStreamingKey.getVersion(), getVersion());
        validateParams(aesGcmHkdfStreamingKey.getParams());
    }
}
