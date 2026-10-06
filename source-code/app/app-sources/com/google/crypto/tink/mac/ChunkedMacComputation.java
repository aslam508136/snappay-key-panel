package com.google.crypto.tink.mac;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public interface ChunkedMacComputation {
    byte[] computeMac();

    void update(ByteBuffer byteBuffer);
}
