package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.HybridDecrypt;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.HpkeKeyFormat;
import com.google.crypto.tink.proto.HpkeParams;
import com.google.crypto.tink.proto.HpkePrivateKey;
import com.google.crypto.tink.proto.HpkePublicKey;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.Validators;
import com.google.crypto.tink.subtle.X25519;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class HpkePrivateKeyManager extends PrivateKeyTypeManager<HpkePrivateKey, HpkePublicKey> {

    /* JADX INFO: renamed from: com.google.crypto.tink.hybrid.internal.HpkePrivateKeyManager$3, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$HpkeKem;

        static {
            int[] iArr = new int[com.google.crypto.tink.proto.HpkeKem.values().length];
            $SwitchMap$com$google$crypto$tink$proto$HpkeKem = iArr;
            try {
                iArr[com.google.crypto.tink.proto.HpkeKem.DHKEM_X25519_HKDF_SHA256.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HpkeKem[com.google.crypto.tink.proto.HpkeKem.DHKEM_P256_HKDF_SHA256.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HpkeKem[com.google.crypto.tink.proto.HpkeKem.DHKEM_P384_HKDF_SHA384.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HpkeKem[com.google.crypto.tink.proto.HpkeKem.DHKEM_P521_HKDF_SHA512.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public HpkePrivateKeyManager() {
        super(HpkePrivateKey.class, HpkePublicKey.class, new PrimitiveFactory<HybridDecrypt, HpkePrivateKey>(HybridDecrypt.class) { // from class: com.google.crypto.tink.hybrid.internal.HpkePrivateKeyManager.1
            @Override // com.google.crypto.tink.internal.PrimitiveFactory
            public HybridDecrypt getPrimitive(HpkePrivateKey hpkePrivateKey) {
                return HpkeDecrypt.createHpkeDecrypt(hpkePrivateKey);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static KeyTypeManager.KeyFactory.KeyFormat<HpkeKeyFormat> createKeyFormat(com.google.crypto.tink.proto.HpkeKem hpkeKem, com.google.crypto.tink.proto.HpkeKdf hpkeKdf, com.google.crypto.tink.proto.HpkeAead hpkeAead, KeyTemplate.OutputPrefixType outputPrefixType) {
        return new KeyTypeManager.KeyFactory.KeyFormat<>(HpkeKeyFormat.newBuilder().setParams(HpkeParams.newBuilder().setKem(hpkeKem).setKdf(hpkeKdf).setAead(hpkeAead).build()).build(), outputPrefixType);
    }

    public static void registerPair(boolean z2) {
        Registry.registerAsymmetricKeyManagers(new HpkePrivateKeyManager(), new HpkePublicKeyManager(), z2);
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.HpkePrivateKey";
    }

    @Override // com.google.crypto.tink.internal.PrivateKeyTypeManager
    public HpkePublicKey getPublicKey(HpkePrivateKey hpkePrivateKey) {
        return hpkePrivateKey.getPublicKey();
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public int getVersion() {
        return 0;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyTypeManager.KeyFactory<HpkeKeyFormat, HpkePrivateKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<HpkeKeyFormat, HpkePrivateKey>(HpkeKeyFormat.class) { // from class: com.google.crypto.tink.hybrid.internal.HpkePrivateKeyManager.2
            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public HpkePrivateKey createKey(HpkeKeyFormat hpkeKeyFormat) throws GeneralSecurityException {
                byte[] bArrGeneratePrivateKey;
                byte[] bArrPublicFromPrivate;
                int i2 = AnonymousClass3.$SwitchMap$com$google$crypto$tink$proto$HpkeKem[hpkeKeyFormat.getParams().getKem().ordinal()];
                if (i2 == 1) {
                    bArrGeneratePrivateKey = X25519.generatePrivateKey();
                    bArrPublicFromPrivate = X25519.publicFromPrivate(bArrGeneratePrivateKey);
                } else {
                    if (i2 != 2 && i2 != 3 && i2 != 4) {
                        throw new GeneralSecurityException("Invalid KEM");
                    }
                    EllipticCurves.CurveType curveTypeNistHpkeKemToCurve = HpkeUtil.nistHpkeKemToCurve(hpkeKeyFormat.getParams().getKem());
                    KeyPair keyPairGenerateKeyPair = EllipticCurves.generateKeyPair(curveTypeNistHpkeKemToCurve);
                    bArrPublicFromPrivate = EllipticCurves.pointEncode(curveTypeNistHpkeKemToCurve, EllipticCurves.PointFormatType.UNCOMPRESSED, ((ECPublicKey) keyPairGenerateKeyPair.getPublic()).getW());
                    bArrGeneratePrivateKey = ((ECPrivateKey) keyPairGenerateKeyPair.getPrivate()).getS().toByteArray();
                }
                return HpkePrivateKey.newBuilder().setVersion(HpkePrivateKeyManager.this.getVersion()).setPublicKey(HpkePublicKey.newBuilder().setVersion(HpkePrivateKeyManager.this.getVersion()).setParams(hpkeKeyFormat.getParams()).setPublicKey(ByteString.copyFrom(bArrPublicFromPrivate)).build()).setPrivateKey(ByteString.copyFrom(bArrGeneratePrivateKey)).build();
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<HpkeKeyFormat>> keyFormats() {
                HashMap map = new HashMap();
                com.google.crypto.tink.proto.HpkeKem hpkeKem = com.google.crypto.tink.proto.HpkeKem.DHKEM_X25519_HKDF_SHA256;
                com.google.crypto.tink.proto.HpkeKdf hpkeKdf = com.google.crypto.tink.proto.HpkeKdf.HKDF_SHA256;
                com.google.crypto.tink.proto.HpkeAead hpkeAead = com.google.crypto.tink.proto.HpkeAead.AES_128_GCM;
                KeyTemplate.OutputPrefixType outputPrefixType = KeyTemplate.OutputPrefixType.TINK;
                map.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_128_GCM", HpkePrivateKeyManager.createKeyFormat(hpkeKem, hpkeKdf, hpkeAead, outputPrefixType));
                KeyTemplate.OutputPrefixType outputPrefixType2 = KeyTemplate.OutputPrefixType.RAW;
                map.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_128_GCM_RAW", HpkePrivateKeyManager.createKeyFormat(hpkeKem, hpkeKdf, hpkeAead, outputPrefixType2));
                com.google.crypto.tink.proto.HpkeAead hpkeAead2 = com.google.crypto.tink.proto.HpkeAead.AES_256_GCM;
                map.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_256_GCM", HpkePrivateKeyManager.createKeyFormat(hpkeKem, hpkeKdf, hpkeAead2, outputPrefixType));
                map.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_256_GCM_RAW", HpkePrivateKeyManager.createKeyFormat(hpkeKem, hpkeKdf, hpkeAead2, outputPrefixType2));
                com.google.crypto.tink.proto.HpkeAead hpkeAead3 = com.google.crypto.tink.proto.HpkeAead.CHACHA20_POLY1305;
                map.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_CHACHA20_POLY1305", HpkePrivateKeyManager.createKeyFormat(hpkeKem, hpkeKdf, hpkeAead3, outputPrefixType));
                map.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_CHACHA20_POLY1305_RAW", HpkePrivateKeyManager.createKeyFormat(hpkeKem, hpkeKdf, hpkeAead3, outputPrefixType2));
                com.google.crypto.tink.proto.HpkeKem hpkeKem2 = com.google.crypto.tink.proto.HpkeKem.DHKEM_P256_HKDF_SHA256;
                map.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_128_GCM", HpkePrivateKeyManager.createKeyFormat(hpkeKem2, hpkeKdf, hpkeAead, outputPrefixType));
                map.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_128_GCM_RAW", HpkePrivateKeyManager.createKeyFormat(hpkeKem2, hpkeKdf, hpkeAead, outputPrefixType2));
                map.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_256_GCM", HpkePrivateKeyManager.createKeyFormat(hpkeKem2, hpkeKdf, hpkeAead2, outputPrefixType));
                map.put("DHKEM_P256_HKDF_SHA256_HKDF_SHA256_AES_256_GCM_RAW", HpkePrivateKeyManager.createKeyFormat(hpkeKem2, hpkeKdf, hpkeAead2, outputPrefixType2));
                com.google.crypto.tink.proto.HpkeKem hpkeKem3 = com.google.crypto.tink.proto.HpkeKem.DHKEM_P384_HKDF_SHA384;
                com.google.crypto.tink.proto.HpkeKdf hpkeKdf2 = com.google.crypto.tink.proto.HpkeKdf.HKDF_SHA384;
                map.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_128_GCM", HpkePrivateKeyManager.createKeyFormat(hpkeKem3, hpkeKdf2, hpkeAead, outputPrefixType));
                map.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_128_GCM_RAW", HpkePrivateKeyManager.createKeyFormat(hpkeKem3, hpkeKdf2, hpkeAead, outputPrefixType2));
                map.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_256_GCM", HpkePrivateKeyManager.createKeyFormat(hpkeKem3, hpkeKdf2, hpkeAead2, outputPrefixType));
                map.put("DHKEM_P384_HKDF_SHA384_HKDF_SHA384_AES_256_GCM_RAW", HpkePrivateKeyManager.createKeyFormat(hpkeKem3, hpkeKdf2, hpkeAead2, outputPrefixType2));
                com.google.crypto.tink.proto.HpkeKem hpkeKem4 = com.google.crypto.tink.proto.HpkeKem.DHKEM_P521_HKDF_SHA512;
                com.google.crypto.tink.proto.HpkeKdf hpkeKdf3 = com.google.crypto.tink.proto.HpkeKdf.HKDF_SHA512;
                map.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_128_GCM", HpkePrivateKeyManager.createKeyFormat(hpkeKem4, hpkeKdf3, hpkeAead, outputPrefixType));
                map.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_128_GCM_RAW", HpkePrivateKeyManager.createKeyFormat(hpkeKem4, hpkeKdf3, hpkeAead, outputPrefixType2));
                map.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_256_GCM", HpkePrivateKeyManager.createKeyFormat(hpkeKem4, hpkeKdf3, hpkeAead2, outputPrefixType));
                map.put("DHKEM_P521_HKDF_SHA512_HKDF_SHA512_AES_256_GCM_RAW", HpkePrivateKeyManager.createKeyFormat(hpkeKem4, hpkeKdf3, hpkeAead2, outputPrefixType2));
                return Collections.unmodifiableMap(map);
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public HpkeKeyFormat parseKeyFormat(ByteString byteString) {
                return HpkeKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            @Override // com.google.crypto.tink.internal.KeyTypeManager.KeyFactory
            public void validateKeyFormat(HpkeKeyFormat hpkeKeyFormat) throws GeneralSecurityException {
                HpkeUtil.validateParams(hpkeKeyFormat.getParams());
            }
        };
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE;
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public HpkePrivateKey parseKey(ByteString byteString) {
        return HpkePrivateKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    @Override // com.google.crypto.tink.internal.KeyTypeManager
    public void validateKey(HpkePrivateKey hpkePrivateKey) throws GeneralSecurityException {
        if (hpkePrivateKey.getPrivateKey().isEmpty()) {
            throw new GeneralSecurityException("Private key is empty.");
        }
        if (!hpkePrivateKey.hasPublicKey()) {
            throw new GeneralSecurityException("Missing public key.");
        }
        Validators.validateVersion(hpkePrivateKey.getVersion(), getVersion());
        HpkeUtil.validateParams(hpkePrivateKey.getPublicKey().getParams());
    }
}
