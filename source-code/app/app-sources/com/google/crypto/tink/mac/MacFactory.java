package com.google.crypto.tink.mac;

import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.Mac;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class MacFactory {
    private MacFactory() {
    }

    @Deprecated
    public static Mac getPrimitive(KeysetHandle keysetHandle) {
        MacWrapper.register();
        return (Mac) keysetHandle.getPrimitive(Mac.class);
    }
}
