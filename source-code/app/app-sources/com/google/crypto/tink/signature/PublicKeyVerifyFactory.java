package com.google.crypto.tink.signature;

import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.PublicKeyVerify;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class PublicKeyVerifyFactory {
    private PublicKeyVerifyFactory() {
    }

    @Deprecated
    public static PublicKeyVerify getPrimitive(KeysetHandle keysetHandle) {
        PublicKeyVerifyWrapper.register();
        return (PublicKeyVerify) keysetHandle.getPrimitive(PublicKeyVerify.class);
    }
}
