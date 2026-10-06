package com.google.crypto.tink.mac;

import com.google.errorprone.annotations.Immutable;

/* JADX INFO: loaded from: classes.dex */
@Immutable
public interface ChunkedMac {
    ChunkedMacComputation createComputation();

    ChunkedMacVerification createVerification(byte[] bArr);
}
