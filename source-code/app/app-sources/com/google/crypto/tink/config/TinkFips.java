package com.google.crypto.tink.config;

import com.google.crypto.tink.Registry;
import com.google.crypto.tink.config.internal.TinkFipsUtil;

/* JADX INFO: loaded from: classes.dex */
public final class TinkFips {
    private TinkFips() {
    }

    public static void restrictToFips() {
        Registry.restrictToFipsIfEmpty();
    }

    public static boolean useOnlyFips() {
        return TinkFipsUtil.useOnlyFips();
    }
}
