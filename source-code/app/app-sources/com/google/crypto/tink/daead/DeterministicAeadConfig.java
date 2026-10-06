package com.google.crypto.tink.daead;

import com.google.crypto.tink.config.TinkFips;
import com.google.crypto.tink.proto.RegistryConfig;
import com.google.errorprone.annotations.InlineMe;
import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes.dex */
public final class DeterministicAeadConfig {
    public static final String AES_SIV_TYPE_URL = new AesSivKeyManager().getKeyType();

    @Deprecated
    public static final RegistryConfig TINK_1_1_0 = RegistryConfig.getDefaultInstance();

    @Deprecated
    public static final RegistryConfig LATEST = RegistryConfig.getDefaultInstance();

    static {
        try {
            register();
        } catch (GeneralSecurityException e2) {
            throw new ExceptionInInitializerError(e2);
        }
    }

    private DeterministicAeadConfig() {
    }

    @InlineMe(imports = {"com.google.crypto.tink.daead.DeterministicAeadConfig"}, replacement = "DeterministicAeadConfig.register()")
    @Deprecated
    public static void init() {
        register();
    }

    public static void register() {
        DeterministicAeadWrapper.register();
        if (TinkFips.useOnlyFips()) {
            return;
        }
        AesSivKeyManager.register(true);
    }
}
