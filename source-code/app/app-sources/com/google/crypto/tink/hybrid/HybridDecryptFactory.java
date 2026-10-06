package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.HybridDecrypt;
import com.google.crypto.tink.KeysetHandle;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class HybridDecryptFactory {
    private HybridDecryptFactory() {
    }

    @Deprecated
    public static HybridDecrypt getPrimitive(KeysetHandle keysetHandle) {
        HybridDecryptWrapper.register();
        return (HybridDecrypt) keysetHandle.getPrimitive(HybridDecrypt.class);
    }
}
