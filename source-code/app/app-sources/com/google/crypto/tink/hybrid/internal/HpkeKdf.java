package com.google.crypto.tink.hybrid.internal;

import com.google.errorprone.annotations.Immutable;

/* JADX INFO: loaded from: classes.dex */
@Immutable
interface HpkeKdf {
    byte[] extractAndExpand(byte[] bArr, byte[] bArr2, String str, byte[] bArr3, String str2, byte[] bArr4, int i2);

    byte[] getKdfId();

    byte[] labeledExpand(byte[] bArr, byte[] bArr2, String str, byte[] bArr3, int i2);

    byte[] labeledExtract(byte[] bArr, byte[] bArr2, String str, byte[] bArr3);
}
