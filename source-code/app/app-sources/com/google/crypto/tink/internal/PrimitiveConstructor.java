package com.google.crypto.tink.internal;

import com.google.crypto.tink.Key;

/* JADX INFO: loaded from: classes.dex */
public abstract class PrimitiveConstructor<KeyT extends Key, PrimitiveT> {
    private final Class<KeyT> keyClass;
    private final Class<PrimitiveT> primitiveClass;

    public interface PrimitiveConstructionFunction<KeyT extends Key, PrimitiveT> {
        PrimitiveT constructPrimitive(KeyT keyt);
    }

    private PrimitiveConstructor(Class<KeyT> cls, Class<PrimitiveT> cls2) {
        this.keyClass = cls;
        this.primitiveClass = cls2;
    }

    public static <KeyT extends Key, PrimitiveT> PrimitiveConstructor<KeyT, PrimitiveT> create(final PrimitiveConstructionFunction<KeyT, PrimitiveT> primitiveConstructionFunction, Class<KeyT> cls, Class<PrimitiveT> cls2) {
        return (PrimitiveConstructor<KeyT, PrimitiveT>) new PrimitiveConstructor<KeyT, PrimitiveT>(cls, cls2) { // from class: com.google.crypto.tink.internal.PrimitiveConstructor.1
            @Override // com.google.crypto.tink.internal.PrimitiveConstructor
            public PrimitiveT constructPrimitive(KeyT keyt) {
                return (PrimitiveT) primitiveConstructionFunction.constructPrimitive(keyt);
            }
        };
    }

    public abstract PrimitiveT constructPrimitive(KeyT keyt);

    public Class<KeyT> getKeyClass() {
        return this.keyClass;
    }

    public Class<PrimitiveT> getPrimitiveClass() {
        return this.primitiveClass;
    }
}
