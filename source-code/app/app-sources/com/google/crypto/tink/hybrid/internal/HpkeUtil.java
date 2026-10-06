package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.internal.Util;
import com.google.crypto.tink.proto.HpkeParams;
import com.google.crypto.tink.subtle.Bytes;
import com.google.crypto.tink.subtle.EllipticCurves;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes.dex */
public final class HpkeUtil {
    private static final byte[] HPKE;
    private static final byte[] HPKE_V1;
    private static final byte[] KEM;
    public static final byte[] BASE_MODE = intToByteArray(1, 0);
    public static final byte[] X25519_HKDF_SHA256_KEM_ID = intToByteArray(2, 32);
    public static final byte[] P256_HKDF_SHA256_KEM_ID = intToByteArray(2, 16);
    public static final byte[] P384_HKDF_SHA384_KEM_ID = intToByteArray(2, 17);
    public static final byte[] P521_HKDF_SHA512_KEM_ID = intToByteArray(2, 18);
    public static final byte[] HKDF_SHA256_KDF_ID = intToByteArray(2, 1);
    public static final byte[] HKDF_SHA384_KDF_ID = intToByteArray(2, 2);
    public static final byte[] HKDF_SHA512_KDF_ID = intToByteArray(2, 3);
    public static final byte[] AES_128_GCM_AEAD_ID = intToByteArray(2, 1);
    public static final byte[] AES_256_GCM_AEAD_ID = intToByteArray(2, 2);
    public static final byte[] CHACHA20_POLY1305_AEAD_ID = intToByteArray(2, 3);
    public static final byte[] EMPTY_SALT = new byte[0];

    /* JADX INFO: renamed from: com.google.crypto.tink.hybrid.internal.HpkeUtil$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$HpkeKem;

        static {
            int[] iArr = new int[com.google.crypto.tink.proto.HpkeKem.values().length];
            $SwitchMap$com$google$crypto$tink$proto$HpkeKem = iArr;
            try {
                iArr[com.google.crypto.tink.proto.HpkeKem.DHKEM_P256_HKDF_SHA256.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HpkeKem[com.google.crypto.tink.proto.HpkeKem.DHKEM_P384_HKDF_SHA384.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HpkeKem[com.google.crypto.tink.proto.HpkeKem.DHKEM_P521_HKDF_SHA512.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    static {
        Charset charset = Util.UTF_8;
        KEM = "KEM".getBytes(charset);
        HPKE = "HPKE".getBytes(charset);
        HPKE_V1 = "HPKE-v1".getBytes(charset);
    }

    private HpkeUtil() {
    }

    public static byte[] hpkeSuiteId(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return Bytes.concat(HPKE, bArr, bArr2, bArr3);
    }

    public static byte[] intToByteArray(int i2, int i3) {
        byte[] bArr = new byte[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            bArr[i4] = (byte) ((i3 >> (((i2 - i4) - 1) * 8)) & 255);
        }
        return bArr;
    }

    public static byte[] kemSuiteId(byte[] bArr) {
        return Bytes.concat(KEM, bArr);
    }

    public static byte[] labelIkm(String str, byte[] bArr, byte[] bArr2) {
        return Bytes.concat(HPKE_V1, bArr2, str.getBytes(Util.UTF_8), bArr);
    }

    public static byte[] labelInfo(String str, byte[] bArr, byte[] bArr2, int i2) {
        return Bytes.concat(intToByteArray(2, i2), HPKE_V1, bArr2, str.getBytes(Util.UTF_8), bArr);
    }

    public static EllipticCurves.CurveType nistHpkeKemToCurve(com.google.crypto.tink.proto.HpkeKem hpkeKem) throws GeneralSecurityException {
        int i2 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$HpkeKem[hpkeKem.ordinal()];
        if (i2 == 1) {
            return EllipticCurves.CurveType.NIST_P256;
        }
        if (i2 == 2) {
            return EllipticCurves.CurveType.NIST_P384;
        }
        if (i2 == 3) {
            return EllipticCurves.CurveType.NIST_P521;
        }
        throw new GeneralSecurityException("Unrecognized NIST HPKE KEM identifier");
    }

    public static void validateParams(HpkeParams hpkeParams) throws GeneralSecurityException {
        if (hpkeParams.getKem() == com.google.crypto.tink.proto.HpkeKem.KEM_UNKNOWN || hpkeParams.getKem() == com.google.crypto.tink.proto.HpkeKem.UNRECOGNIZED) {
            throw new GeneralSecurityException("Invalid KEM param: " + hpkeParams.getKem().name());
        }
        if (hpkeParams.getKdf() == com.google.crypto.tink.proto.HpkeKdf.KDF_UNKNOWN || hpkeParams.getKdf() == com.google.crypto.tink.proto.HpkeKdf.UNRECOGNIZED) {
            throw new GeneralSecurityException("Invalid KDF param: " + hpkeParams.getKdf().name());
        }
        if (hpkeParams.getAead() == com.google.crypto.tink.proto.HpkeAead.AEAD_UNKNOWN || hpkeParams.getAead() == com.google.crypto.tink.proto.HpkeAead.UNRECOGNIZED) {
            throw new GeneralSecurityException("Invalid AEAD param: " + hpkeParams.getAead().name());
        }
    }
}
