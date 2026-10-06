package com.google.crypto.tink.shaded.protobuf;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class UnsafeByteOperations {
    private UnsafeByteOperations() {
    }

    public static ByteString unsafeWrap(ByteBuffer byteBuffer) {
        return ByteString.wrap(byteBuffer);
    }

    public static void unsafeWriteTo(ByteString byteString, ByteOutput byteOutput) {
        byteString.writeTo(byteOutput);
    }

    public static ByteString unsafeWrap(byte[] bArr) {
        return ByteString.wrap(bArr);
    }

    public static ByteString unsafeWrap(byte[] bArr, int i2, int i3) {
        return ByteString.wrap(bArr, i2, i3);
    }
}
