package com.google.crypto.tink.prf;

import com.google.errorprone.annotations.Immutable;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@Immutable
public abstract class PrfSet {
    public byte[] computePrimary(byte[] bArr, int i2) {
        return getPrfs().get(Integer.valueOf(getPrimaryId())).compute(bArr, i2);
    }

    public abstract Map<Integer, Prf> getPrfs();

    public abstract int getPrimaryId();
}
