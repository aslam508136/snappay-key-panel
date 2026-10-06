package com.google.crypto.tink.mac;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public interface ChunkedMacVerification {
    void update(ByteBuffer byteBuffer);

    void verifyMac();
}
