package com.google.crypto.tink.signature;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class PublicKeyVerifyConfig {
    private PublicKeyVerifyConfig() {
    }

    @Deprecated
    public static void registerStandardKeyTypes() {
        SignatureConfig.register();
    }
}
