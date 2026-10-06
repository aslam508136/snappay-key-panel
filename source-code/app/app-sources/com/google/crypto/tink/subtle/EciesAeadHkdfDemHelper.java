package com.google.crypto.tink.subtle;

import com.google.crypto.tink.hybrid.subtle.AeadOrDaead;

/* JADX INFO: loaded from: classes.dex */
public interface EciesAeadHkdfDemHelper {
    AeadOrDaead getAeadOrDaead(byte[] bArr);

    int getSymmetricKeySizeInBytes();
}
