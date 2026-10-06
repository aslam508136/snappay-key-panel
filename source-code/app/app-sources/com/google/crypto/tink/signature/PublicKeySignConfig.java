package com.google.crypto.tink.signature;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class PublicKeySignConfig {
    private PublicKeySignConfig() {
    }

    @Deprecated
    public static void registerStandardKeyTypes() {
        SignatureConfig.register();
    }
}
