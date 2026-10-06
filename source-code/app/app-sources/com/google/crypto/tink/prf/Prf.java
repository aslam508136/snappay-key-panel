package com.google.crypto.tink.prf;

import com.google.errorprone.annotations.Immutable;

/* JADX INFO: loaded from: classes.dex */
@Immutable
public interface Prf {
    byte[] compute(byte[] bArr, int i2);
}
