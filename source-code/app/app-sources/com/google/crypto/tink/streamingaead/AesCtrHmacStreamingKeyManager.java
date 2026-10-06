package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.StreamingAead;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.AesCtrHmacStreamingKey;
import com.google.crypto.tink.proto.AesCtrHmacStreamingKeyFormat;
import com.google.crypto.tink.proto.AesCtrHmacStreamingParams;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.HmacParams;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.subtle.AesCtrHmacStreaming;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class AesCtrHmacStreamingKeyManager extends KeyTypeManager<AesCtrHmacStreamingKey> {
    private static final int MIN_TAG_SIZE_IN_BYTES = 10;
    private static final int NONCE_PREFIX_IN_BYTES = 7;

    /* JADX INFO: renamed from: com.google.crypto.tink.streamingaead.AesCtrHmacStreamingKeyManager$3, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$HashType;

        static {
            int[] iArr = new int[HashType.values().length];
            $SwitchMap$com$google$crypto$tink$proto$HashType = iArr;
            try {
                iArr[HashType.SHA1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA256.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA512.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public AesCtrHmacStreamingKeyManager() {
        super(AesCtrHmacStreamingKey.class, new PrimitiveFactory<StreamingAead, AesCtrHmacStreamingKey>(StreamingAead.class) { // from class: com.google.crypto.tink.streamingaead.AesCtrHmacStreamingKeyManager.1
            @Override // com.google.crypto.tink.internal.PrimitiveFactory
            public StreamingAead getPrimitive(AesCtrHmacStreamingKey aesCtrHmacStreamingKey) {
                return new AesCtrHmacStreaming(aesCtrHmacStreamingKey.getKeyValue().toByteArray(), StreamingAeadUtil.toHmacAlgo(aesCtrHmacStreamingKey.getParams().getHkdfHashType()), aesCtrHmacStreamingKey.getParams().getDerivedKeySize(), StreamingAeadUtil.toHmacAlgo(aesCtrHmacStreamingKey.getParams().getHmacParams().getHash()), aesCtrHmacStreamingKey.getParams().getHmacParams().getTagSize(), aesCtrHmacStreamingKey.getParams().getCiphertextSegmentSize(), 0);
            }
        });
    }

    public static final KeyTemplate aes128CtrHmacSha2561MBTemplate() {
        HashType hashType = HashType.SHA256;
        return createKeyTemplate(16, hashType, 16, hashType, 32, 1048576);
    }

    public static final KeyTemplate aes128CtrHmacSha2564KBTemplate() {
        HashType hashType = HashType.SHA256;
        return createKeyTemplate(16, hashType, 16, hashType, 32, 4096);
    }

    public static final KeyTemplate aes256CtrHmacSha2561MBTemplate() {
        HashType hashType = HashType.SHA256;
        return createKeyTemplate(32, hashType, 32, hashType, 32, 1048576);
    }

    public static final KeyTemplate aes256CtrHmacSha2564KBTemplate() {
        HashType hashType = HashType.SHA256;
        return createKeyTemplate(32, hashType, 32, hashType, 32, 4096);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static AesCtrHmacStreamingKeyFormat createKeyFormat(int i2, HashType hashType, int i3, HashType hashType2, int i4, int i5) {
        return AesCtrHmacStreamingKeyFormat.newBuilder().setParams(AesCtrHmacStreamingParams.newBuilder().setCiphertextSegmentSize(i5).setDerivedKeySize(i3).setHkdfHashType(hashType).setHmacParams(HmacParams.newBuilder().setHash(hashType2).setTagSize(i4).build()).build()).setKeySize(i2).build();
    }

    private static KeyTemplate createKeyTemplate(int i2, HashType hashType, int i3, HashType hashType2, int i4, int i5) {
        return KeyTemplate.create(new AesCtrHmacStreamingKeyManager().getKeyType(), createKeyFormat(i2, hashType, i3, hashType2, i4, i5).toByteArray(), KeyTemplate.OutputPrefixType.RAW);
    }

    public static void register(boolean z2) {
        Registry.registerKeyManager(new AesCtrHmacStreamingKeyManager(), z2);
    }

    private static void validateHmacParams(HmacParams hmacParams) throws GeneralSecurityException {
        if (hmacParams.getTagSize() < 10) {
            throw new GeneralSecurityException("tag size too small");
        }
        int i2 = AnonymousClass3.$SwitchMap$com$google$crypto$tink$proto$HashType[hmacParams.getHash().ordinal()];
        if (i2 == 1) {
            if (hmacParams.getTagSize() > 20) {
                throw new GeneralSecurityException("tag size too big");
            }
        } else if (i2 == 2) {
            if (hmacParams.getTagSize() > 32) {
                throw new GeneralSecurityException("tag size too big");
            }
        } else {
            if (i2 != 3) {
                throw new GeneralSecurityException("unknown hash type");
            }
            if (hmacParams.getTagSize() > 64) {
                throw new GeneralSecurityException("tag size too big");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void validateParams(AesCtrHmacStreamingParams aesCtrHmacStreamingParams) throws GeneralSecurityException {
        Validators.validateAesKeySize(aesCtrHmacStreamingParams.getDerivedKeySize());
        if (aesCtrHmacStreamingParams.getHkdfHashType() != HashType.SHA1 && aesCtrHmacStreamingParams.getHkdfHashType() != HashType.SHA256 && aesCtrHmacStreamingParams.getHkdfHashType() != HashType.SHA512) {
            throw new GeneralSecurityException("Invalid HKDF hash type: " + aesCtrHmacStreamingParams.getHkdfHashType().getNumber());
        }
        if (aesCtrHmacStreamingParams.getHmacParams().getHash() == HashType.UNKNOWN_HASH) {
            throw new GeneralSecurityException("unknown HMAC hash type");
        }
        validateHmacParams(aesCtrHmacStreamingParams.getHmacParams());
        if (aesCtrHmacStreamingParams.getCiphertextSegmentSize() < aesCtrHmacStreamingParams.getHmacParams().getTagSize() + aesCtrHmacStreamingParams.getDerivedKeySize() + 2 + 7) {
            throw new GeneralSecurityException("ciphertext_segment_size must be at least (derived_key_size + tag_size + NONCE_PREFIX_IN_BYTES + 2)");
        }
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesCtrHmacStreamingKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory<AesCtrHmacStreamingKeyFormat, AesCtrHmacStreamingKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<AesCtrHmacStreamingKeyFormat, AesCtrHmacStreamingKey>(AesCtrHmacStreamingKeyFormat.class) { // from class: com.google.crypto.tink.streamingaead.AesCtrHmacStreamingKeyManager.2
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public AesCtrHmacStreamingKey createKey(AesCtrHmacStreamingKeyFormat aesCtrHmacStreamingKeyFormat) {
                return AesCtrHmacStreamingKey.newBuilder().setKeyValue(ByteString.copyFrom(Random.randBytes(aesCtrHmacStreamingKeyFormat.getKeySize()))).setParams(aesCtrHmacStreamingKeyFormat.getParams()).setVersion(AesCtrHmacStreamingKeyManager.this.getVersion()).build();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<AesCtrHmacStreamingKeyFormat>> keyFormats() {
                HashMap map = new HashMap();
                HashType hashType = HashType.SHA256;
                AesCtrHmacStreamingKeyFormat aesCtrHmacStreamingKeyFormatCreateKeyFormat = AesCtrHmacStreamingKeyManager.createKeyFormat(16, hashType, 16, hashType, 32, 4096);
                KeyTemplate.OutputPrefixType outputPrefixType = KeyTemplate.OutputPrefixType.RAW;
                map.put("AES128_CTR_HMAC_SHA256_4KB", new KeyTypeManager.KeyFactory.KeyFormat(aesCtrHmacStreamingKeyFormatCreateKeyFormat, outputPrefixType));
                map.put("AES128_CTR_HMAC_SHA256_1MB", new KeyTypeManager.KeyFactory.KeyFormat(AesCtrHmacStreamingKeyManager.createKeyFormat(16, hashType, 16, hashType, 32, 1048576), outputPrefixType));
                map.put("AES256_CTR_HMAC_SHA256_4KB", new KeyTypeManager.KeyFactory.KeyFormat(AesCtrHmacStreamingKeyManager.createKeyFormat(32, hashType, 32, hashType, 32, 4096), outputPrefixType));
                map.put("AES256_CTR_HMAC_SHA256_1MB", new KeyTypeManager.KeyFactory.KeyFormat(AesCtrHmacStreamingKeyManager.createKeyFormat(32, hashType, 32, hashType, 32, 1048576), outputPrefixType));
                return Collections.unmodifiableMap(map);
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public AesCtrHmacStreamingKeyFormat parseKeyFormat(ByteString byteString) {
                return AesCtrHmacStreamingKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public void validateKeyFormat(AesCtrHmacStreamingKeyFormat aesCtrHmacStreamingKeyFormat) throws GeneralSecurityException {
                if (aesCtrHmacStreamingKeyFormat.getKeySize() < 16) {
                    throw new GeneralSecurityException("key_size must be at least 16 bytes");
                }
                AesCtrHmacStreamingKeyManager.validateParams(aesCtrHmacStreamingKeyFormat.getParams());
            }
        };
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public AesCtrHmacStreamingKey parseKey(ByteString byteString) {
        return AesCtrHmacStreamingKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(AesCtrHmacStreamingKey aesCtrHmacStreamingKey) throws GeneralSecurityException {
        Validators.validateVersion(aesCtrHmacStreamingKey.getVersion(), getVersion());
        if (aesCtrHmacStreamingKey.getKeyValue().size() < 16) {
            throw new GeneralSecurityException("key_value must have at least 16 bytes");
        }
        if (aesCtrHmacStreamingKey.getKeyValue().size() < aesCtrHmacStreamingKey.getParams().getDerivedKeySize()) {
            throw new GeneralSecurityException("key_value must have at least as many bits as derived keys");
        }
        validateParams(aesCtrHmacStreamingKey.getParams());
    }
}
