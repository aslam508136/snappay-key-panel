package com.google.crypto.tink.daead;

import com.google.crypto.tink.DeterministicAead;
import com.google.crypto.tink.KeysetHandle;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class DeterministicAeadFactory {
    private DeterministicAeadFactory() {
    }

    @Deprecated
    public static DeterministicAead getPrimitive(KeysetHandle keysetHandle) {
        DeterministicAeadWrapper.register();
        return (DeterministicAead) keysetHandle.getPrimitive(DeterministicAead.class);
    }
}
