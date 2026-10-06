package com.google.crypto.tink.subtle;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public interface StreamSegmentEncrypter {
    void encryptSegment(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, boolean z2, ByteBuffer byteBuffer3);

    void encryptSegment(ByteBuffer byteBuffer, boolean z2, ByteBuffer byteBuffer2);

    ByteBuffer getHeader();
}
