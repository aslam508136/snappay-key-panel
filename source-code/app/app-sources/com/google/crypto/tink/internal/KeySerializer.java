package com.google.crypto.tink.internal;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.internal.Serialization;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public abstract class KeySerializer<KeyT extends Key, SerializationT extends Serialization> {
    private final Class<KeyT> keyClass;
    private final Class<SerializationT> serializationClass;

    public interface KeySerializationFunction<KeyT extends Key, SerializationT extends Serialization> {
        SerializationT serializeKey(KeyT keyt, @Nullable SecretKeyAccess secretKeyAccess);
    }

    private KeySerializer(Class<KeyT> cls, Class<SerializationT> cls2) {
        this.keyClass = cls;
        this.serializationClass = cls2;
    }

    public static <KeyT extends Key, SerializationT extends Serialization> KeySerializer<KeyT, SerializationT> create(final KeySerializationFunction<KeyT, SerializationT> keySerializationFunction, Class<KeyT> cls, Class<SerializationT> cls2) {
        return (KeySerializer<KeyT, SerializationT>) new KeySerializer<KeyT, SerializationT>(cls, cls2) { // from class: com.google.crypto.tink.internal.KeySerializer.1
            @Override // com.google.crypto.tink.internal.KeySerializer
            public SerializationT serializeKey(KeyT keyt, @Nullable SecretKeyAccess secretKeyAccess) {
                return (SerializationT) keySerializationFunction.serializeKey(keyt, secretKeyAccess);
            }
        };
    }

    public Class<KeyT> getKeyClass() {
        return this.keyClass;
    }

    public Class<SerializationT> getSerializationClass() {
        return this.serializationClass;
    }

    public abstract SerializationT serializeKey(KeyT keyt, @Nullable SecretKeyAccess secretKeyAccess);
}
