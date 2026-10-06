package com.google.crypto.tink.prf;

import com.google.crypto.tink.config.TinkFips;

/* JADX INFO: loaded from: classes.dex */
public final class PrfConfig {
    public static final String PRF_TYPE_URL = new HkdfPrfKeyManager().getKeyType();

    private PrfConfig() {
    }

    public static void register() {
        PrfSetWrapper.register();
        HmacPrfKeyManager.register(true);
        if (TinkFips.useOnlyFips()) {
            return;
        }
        AesCmacPrfKeyManager.register(true);
        HkdfPrfKeyManager.register(true);
    }
}
