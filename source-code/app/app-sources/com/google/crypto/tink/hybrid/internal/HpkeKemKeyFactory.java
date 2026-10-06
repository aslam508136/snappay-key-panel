package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.proto.HpkePrivateKey;
import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes.dex */
final class HpkeKemKeyFactory {

    /* JADX INFO: renamed from: com.google.crypto.tink.hybrid.internal.HpkeKemKeyFactory$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
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

    private HpkeKemKeyFactory() {
    }

    public static HpkeKemPrivateKey createPrivate(HpkePrivateKey hpkePrivateKey) throws GeneralSecurityException {
        int i2 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$HpkeKem[hpkePrivateKey.getPublicKey().getParams().getKem().ordinal()];
        if (i2 == 1) {
            return X25519HpkeKemPrivateKey.fromBytes(hpkePrivateKey.getPrivateKey().toByteArray());
        }
        if (i2 == 2 || i2 == 3 || i2 == 4) {
            return NistCurvesHpkeKemPrivateKey.fromBytes(hpkePrivateKey.getPrivateKey().toByteArray(), hpkePrivateKey.getPublicKey().getPublicKey().toByteArray(), HpkeUtil.nistHpkeKemToCurve(hpkePrivateKey.getPublicKey().getParams().getKem()));
        }
        throw new GeneralSecurityException("Unrecognized HPKE KEM identifier");
    }
}
