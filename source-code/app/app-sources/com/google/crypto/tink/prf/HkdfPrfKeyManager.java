package com.google.crypto.tink.prf;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.HkdfPrfKeyFormat;
import com.google.crypto.tink.proto.HkdfPrfParams;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.subtle.Enums;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import com.google.crypto.tink.subtle.prf.HkdfStreamingPrf;
import com.google.crypto.tink.subtle.prf.PrfImpl;
import com.google.crypto.tink.subtle.prf.StreamingPrf;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class HkdfPrfKeyManager extends KeyTypeManager<com.google.crypto.tink.proto.HkdfPrfKey> {
    private static final int MIN_KEY_SIZE = 32;

    /* JADX INFO: renamed from: com.google.crypto.tink.prf.HkdfPrfKeyManager$4, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass4 {
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
                $SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA384.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA512.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public HkdfPrfKeyManager() {
        super(com.google.crypto.tink.proto.HkdfPrfKey.class, new PrimitiveFactory<StreamingPrf, com.google.crypto.tink.proto.HkdfPrfKey>(StreamingPrf.class) { // from class: com.google.crypto.tink.prf.HkdfPrfKeyManager.1
            @Override // com.google.crypto.tink.internal.PrimitiveFactory
            public StreamingPrf getPrimitive(com.google.crypto.tink.proto.HkdfPrfKey hkdfPrfKey) {
                return new HkdfStreamingPrf(HkdfPrfKeyManager.convertHash(hkdfPrfKey.getParams().getHash()), hkdfPrfKey.getKeyValue().toByteArray(), hkdfPrfKey.getParams().getSalt().toByteArray());
            }
        }, new PrimitiveFactory<Prf, com.google.crypto.tink.proto.HkdfPrfKey>(Prf.class) { // from class: com.google.crypto.tink.prf.HkdfPrfKeyManager.2
            @Override // com.google.crypto.tink.internal.PrimitiveFactory
            public Prf getPrimitive(com.google.crypto.tink.proto.HkdfPrfKey hkdfPrfKey) {
                return PrfImpl.wrap(new HkdfStreamingPrf(HkdfPrfKeyManager.convertHash(hkdfPrfKey.getParams().getHash()), hkdfPrfKey.getKeyValue().toByteArray(), hkdfPrfKey.getParams().getSalt().toByteArray()));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Enums.HashType convertHash(HashType hashType) throws GeneralSecurityException {
        int i2 = AnonymousClass4.$SwitchMap$com$google$crypto$tink$proto$HashType[hashType.ordinal()];
        if (i2 == 1) {
            return Enums.HashType.SHA1;
        }
        if (i2 == 2) {
            return Enums.HashType.SHA256;
        }
        if (i2 == 3) {
            return Enums.HashType.SHA384;
        }
        if (i2 == 4) {
            return Enums.HashType.SHA512;
        }
        throw new GeneralSecurityException("HashType " + hashType.name() + " not known in");
    }

    public static final KeyTemplate hkdfSha256Template() {
        return KeyTemplate.create(staticKeyType(), HkdfPrfKeyFormat.newBuilder().setKeySize(32).setParams(HkdfPrfParams.newBuilder().setHash(HashType.SHA256)).build().toByteArray(), KeyTemplate.OutputPrefixType.RAW);
    }

    public static void register(boolean z2) {
        Registry.registerKeyManager(new HkdfPrfKeyManager(), z2);
        HkdfPrfProtoSerialization.register();
    }

    public static String staticKeyType() {
        return new HkdfPrfKeyManager().getKeyType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void validateKeySize(int i2) throws GeneralSecurityException {
        if (i2 < 32) {
            throw new GeneralSecurityException("Invalid HkdfPrfKey/HkdfPrfKeyFormat: Key size too short");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void validateParams(HkdfPrfParams hkdfPrfParams) throws GeneralSecurityException {
        if (hkdfPrfParams.getHash() != HashType.SHA256 && hkdfPrfParams.getHash() != HashType.SHA512) {
            throw new GeneralSecurityException("Invalid HkdfPrfKey/HkdfPrfKeyFormat: Unsupported hash");
        }
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.HkdfPrfKey";
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory<HkdfPrfKeyFormat, com.google.crypto.tink.proto.HkdfPrfKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<HkdfPrfKeyFormat, com.google.crypto.tink.proto.HkdfPrfKey>(HkdfPrfKeyFormat.class) { // from class: com.google.crypto.tink.prf.HkdfPrfKeyManager.3
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public com.google.crypto.tink.proto.HkdfPrfKey createKey(HkdfPrfKeyFormat hkdfPrfKeyFormat) {
                return com.google.crypto.tink.proto.HkdfPrfKey.newBuilder().setKeyValue(ByteString.copyFrom(Random.randBytes(hkdfPrfKeyFormat.getKeySize()))).setVersion(HkdfPrfKeyManager.this.getVersion()).setParams(hkdfPrfKeyFormat.getParams()).build();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<HkdfPrfKeyFormat>> keyFormats() {
                HashMap map = new HashMap();
                map.put("HKDF_SHA256", new KeyTypeManager.KeyFactory.KeyFormat(HkdfPrfKeyFormat.newBuilder().setKeySize(32).setParams(HkdfPrfParams.newBuilder().setHash(HashType.SHA256)).build(), KeyTemplate.OutputPrefixType.RAW));
                return Collections.unmodifiableMap(map);
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public HkdfPrfKeyFormat parseKeyFormat(ByteString byteString) {
                return HkdfPrfKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public void validateKeyFormat(HkdfPrfKeyFormat hkdfPrfKeyFormat) throws GeneralSecurityException {
                HkdfPrfKeyManager.validateKeySize(hkdfPrfKeyFormat.getKeySize());
                HkdfPrfKeyManager.validateParams(hkdfPrfKeyFormat.getParams());
            }
        };
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public com.google.crypto.tink.proto.HkdfPrfKey parseKey(ByteString byteString) {
        return com.google.crypto.tink.proto.HkdfPrfKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(com.google.crypto.tink.proto.HkdfPrfKey hkdfPrfKey) throws GeneralSecurityException {
        Validators.validateVersion(hkdfPrfKey.getVersion(), getVersion());
        validateKeySize(hkdfPrfKey.getKeyValue().size());
        validateParams(hkdfPrfKey.getParams());
    }
}
