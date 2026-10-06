package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeysetHandle;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class AeadFactory {
    private AeadFactory() {
    }

    @Deprecated
    public static Aead getPrimitive(KeysetHandle keysetHandle) {
        AeadWrapper.register();
        return (Aead) keysetHandle.getPrimitive(Aead.class);
    }
}
