package com.google.crypto.tink.subtle;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public interface StreamSegmentDecrypter {
    void decryptSegment(ByteBuffer byteBuffer, int i2, boolean z2, ByteBuffer byteBuffer2);

    void init(ByteBuffer byteBuffer, byte[] bArr);
}
