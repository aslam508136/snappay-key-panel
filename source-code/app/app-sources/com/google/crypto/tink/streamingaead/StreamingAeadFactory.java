package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.StreamingAead;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class StreamingAeadFactory {
    private StreamingAeadFactory() {
    }

    public static StreamingAead getPrimitive(KeysetHandle keysetHandle) {
        StreamingAeadWrapper.register();
        return (StreamingAead) keysetHandle.getPrimitive(StreamingAead.class);
    }
}
