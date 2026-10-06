package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.Config;
import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class HybridDecryptConfig {
    private HybridDecryptConfig() {
    }

    @Deprecated
    public static void registerStandardKeyTypes() throws GeneralSecurityException {
        Config.register(HybridConfig.TINK_1_0_0);
    }
}
