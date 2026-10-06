package com.google.crypto.tink;

/* JADX INFO: loaded from: classes.dex */
public interface PrimitiveWrapper<B, P> {
    Class<B> getInputPrimitiveClass();

    Class<P> getPrimitiveClass();

    P wrap(PrimitiveSet<B> primitiveSet);
}
