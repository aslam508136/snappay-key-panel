package com.google.crypto.tink.signature;

import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.PublicKeySign;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class PublicKeySignFactory {
    private PublicKeySignFactory() {
    }

    @Deprecated
    public static PublicKeySign getPrimitive(KeysetHandle keysetHandle) {
        PublicKeySignWrapper.register();
        return (PublicKeySign) keysetHandle.getPrimitive(PublicKeySign.class);
    }
}
