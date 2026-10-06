package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.subtle.Bytes;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

/* JADX INFO: loaded from: classes.dex */
@Immutable
final class NistCurvesHpkeKem implements HpkeKem {
    private final EllipticCurves.CurveType curve;
    private final HkdfHpkeKdf hkdf;

    /* JADX INFO: renamed from: com.google.crypto.tink.hybrid.internal.NistCurvesHpkeKem$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType;

        static {
            int[] iArr = new int[EllipticCurves.CurveType.values().length];
            $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType = iArr;
            try {
                iArr[EllipticCurves.CurveType.NIST_P256.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType[EllipticCurves.CurveType.NIST_P384.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType[EllipticCurves.CurveType.NIST_P521.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private NistCurvesHpkeKem(HkdfHpkeKdf hkdfHpkeKdf, EllipticCurves.CurveType curveType) {
        this.hkdf = hkdfHpkeKdf;
        this.curve = curveType;
    }

    private byte[] deriveKemSharedSecret(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        byte[] bArrConcat = Bytes.concat(bArr2, bArr3);
        byte[] bArrKemSuiteId = HpkeUtil.kemSuiteId(getKemId());
        HkdfHpkeKdf hkdfHpkeKdf = this.hkdf;
        return hkdfHpkeKdf.extractAndExpand(null, bArr, "eae_prk", bArrConcat, "shared_secret", bArrKemSuiteId, hkdfHpkeKdf.getMacLength());
    }

    public static NistCurvesHpkeKem fromCurve(EllipticCurves.CurveType curveType) throws GeneralSecurityException {
        int i2 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType[curveType.ordinal()];
        if (i2 == 1) {
            return new NistCurvesHpkeKem(new HkdfHpkeKdf("HmacSha256"), EllipticCurves.CurveType.NIST_P256);
        }
        if (i2 == 2) {
            return new NistCurvesHpkeKem(new HkdfHpkeKdf("HmacSha384"), EllipticCurves.CurveType.NIST_P384);
        }
        if (i2 == 3) {
            return new NistCurvesHpkeKem(new HkdfHpkeKdf("HmacSha512"), EllipticCurves.CurveType.NIST_P521);
        }
        throw new GeneralSecurityException("invalid curve type: " + curveType);
    }

    @Override // com.google.crypto.tink.hybrid.internal.HpkeKem
    public byte[] decapsulate(byte[] bArr, HpkeKemPrivateKey hpkeKemPrivateKey) {
        return deriveKemSharedSecret(EllipticCurves.computeSharedSecret(EllipticCurves.getEcPrivateKey(this.curve, hpkeKemPrivateKey.getSerializedPrivate().toByteArray()), EllipticCurves.getEcPublicKey(this.curve, EllipticCurves.PointFormatType.UNCOMPRESSED, bArr)), bArr, hpkeKemPrivateKey.getSerializedPublic().toByteArray());
    }

    @Override // com.google.crypto.tink.hybrid.internal.HpkeKem
    public HpkeKemEncapOutput encapsulate(byte[] bArr) {
        return encapsulate(bArr, EllipticCurves.generateKeyPair(this.curve));
    }

    @Override // com.google.crypto.tink.hybrid.internal.HpkeKem
    public byte[] getKemId() throws GeneralSecurityException {
        int i2 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType[this.curve.ordinal()];
        if (i2 == 1) {
            return HpkeUtil.P256_HKDF_SHA256_KEM_ID;
        }
        if (i2 == 2) {
            return HpkeUtil.P384_HKDF_SHA384_KEM_ID;
        }
        if (i2 == 3) {
            return HpkeUtil.P521_HKDF_SHA512_KEM_ID;
        }
        throw new GeneralSecurityException("Could not determine HPKE KEM ID");
    }

    public HpkeKemEncapOutput encapsulate(byte[] bArr, KeyPair keyPair) throws GeneralSecurityException {
        EllipticCurves.CurveType curveType = this.curve;
        EllipticCurves.PointFormatType pointFormatType = EllipticCurves.PointFormatType.UNCOMPRESSED;
        byte[] bArrComputeSharedSecret = EllipticCurves.computeSharedSecret((ECPrivateKey) keyPair.getPrivate(), EllipticCurves.getEcPublicKey(curveType, pointFormatType, bArr));
        byte[] bArrPointEncode = EllipticCurves.pointEncode(this.curve, pointFormatType, ((ECPublicKey) keyPair.getPublic()).getW());
        return new HpkeKemEncapOutput(deriveKemSharedSecret(bArrComputeSharedSecret, bArrPointEncode, bArr), bArrPointEncode);
    }
}
