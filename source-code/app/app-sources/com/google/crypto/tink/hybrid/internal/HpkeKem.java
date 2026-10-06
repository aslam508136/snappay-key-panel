package com.google.crypto.tink.hybrid.internal;

import com.google.errorprone.annotations.Immutable;

/* JADX INFO: loaded from: classes.dex */
@Immutable
interface HpkeKem {
    byte[] decapsulate(byte[] bArr, HpkeKemPrivateKey hpkeKemPrivateKey);

    HpkeKemEncapOutput encapsulate(byte[] bArr);

    byte[] getKemId();
}
