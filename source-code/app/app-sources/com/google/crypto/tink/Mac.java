package com.google.crypto.tink;

/* JADX INFO: loaded from: classes.dex */
public interface Mac {
    byte[] computeMac(byte[] bArr);

    void verifyMac(byte[] bArr, byte[] bArr2);
}
