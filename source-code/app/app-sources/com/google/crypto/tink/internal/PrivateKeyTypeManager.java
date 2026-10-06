package com.google.crypto.tink.internal;

import com.google.crypto.tink.annotations.Alpha;
import com.google.crypto.tink.shaded.protobuf.MessageLite;

/* JADX INFO: loaded from: classes.dex */
@Alpha
public abstract class PrivateKeyTypeManager<KeyProtoT extends MessageLite, PublicKeyProtoT extends MessageLite> extends KeyTypeManager<KeyProtoT> {
    private final Class<PublicKeyProtoT> publicKeyClazz;

    @SafeVarargs
    public PrivateKeyTypeManager(Class<KeyProtoT> cls, Class<PublicKeyProtoT> cls2, PrimitiveFactory<?, KeyProtoT>... primitiveFactoryArr) {
        super(cls, primitiveFactoryArr);
        this.publicKeyClazz = cls2;
    }

    public abstract PublicKeyProtoT getPublicKey(KeyProtoT keyprotot);

    public final Class<PublicKeyProtoT> getPublicKeyClass() {
        return this.publicKeyClazz;
    }
}
