package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.util.Bytes;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes.dex */
@Immutable
final class NistCurvesHpkeKemPrivateKey implements HpkeKemPrivateKey {
    private final Bytes privateKey;
    private final Bytes publicKey;

    private NistCurvesHpkeKemPrivateKey(byte[] bArr, byte[] bArr2) {
        this.privateKey = Bytes.copyFrom(bArr);
        this.publicKey = Bytes.copyFrom(bArr2);
    }

    public static NistCurvesHpkeKemPrivateKey fromBytes(byte[] bArr, byte[] bArr2, EllipticCurves.CurveType curveType) throws GeneralSecurityException {
        EllipticCurves.validatePublicKey(EllipticCurves.getEcPublicKey(curveType, EllipticCurves.PointFormatType.UNCOMPRESSED, bArr2), EllipticCurves.getEcPrivateKey(curveType, bArr));
        return new NistCurvesHpkeKemPrivateKey(bArr, bArr2);
    }

    @Override // com.google.crypto.tink.hybrid.internal.HpkeKemPrivateKey
    public Bytes getSerializedPrivate() {
        return this.privateKey;
    }

    @Override // com.google.crypto.tink.hybrid.internal.HpkeKemPrivateKey
    public Bytes getSerializedPublic() {
        return this.publicKey;
    }
}
