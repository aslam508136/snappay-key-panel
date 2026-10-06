package com.google.crypto.tink.subtle;

import com.google.crypto.tink.Mac;
import com.google.crypto.tink.prf.Prf;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;

/* JADX INFO: loaded from: classes.dex */
@Immutable
public class PrfMac implements Mac {
    static final int MIN_TAG_SIZE_IN_BYTES = 10;
    private final int tagSize;
    private final Prf wrappedPrf;

    public PrfMac(Prf prf, int i2) throws InvalidAlgorithmParameterException {
        this.wrappedPrf = prf;
        this.tagSize = i2;
        if (i2 < 10) {
            throw new InvalidAlgorithmParameterException("tag size too small, need at least 10 bytes");
        }
        prf.compute(new byte[0], i2);
    }

    @Override // com.google.crypto.tink.Mac
    public byte[] computeMac(byte[] bArr) {
        return this.wrappedPrf.compute(bArr, this.tagSize);
    }

    @Override // com.google.crypto.tink.Mac
    public void verifyMac(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (!Bytes.equal(computeMac(bArr2), bArr)) {
            throw new GeneralSecurityException("invalid MAC");
        }
    }
}
