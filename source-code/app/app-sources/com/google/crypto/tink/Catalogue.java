package com.google.crypto.tink;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public interface Catalogue<P> {
    KeyManager<P> getKeyManager(String str, String str2, int i2);

    PrimitiveWrapper<?, P> getPrimitiveWrapper();
}
