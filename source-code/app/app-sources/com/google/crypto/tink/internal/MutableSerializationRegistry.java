package com.google.crypto.tink.internal;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.SecretKeyAccess;
import java.security.GeneralSecurityException;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class MutableSerializationRegistry {
    private static final MutableSerializationRegistry GLOBAL_INSTANCE = new MutableSerializationRegistry();
    private final AtomicReference<SerializationRegistry> registry = new AtomicReference<>(new SerializationRegistry.Builder().build());

    public static MutableSerializationRegistry globalInstance() {
        return GLOBAL_INSTANCE;
    }

    public <SerializationT extends Serialization> boolean hasParserForKey(SerializationT serializationt) {
        return this.registry.get().hasParserForKey(serializationt);
    }

    public <SerializationT extends Serialization> boolean hasParserForParameters(SerializationT serializationt) {
        return this.registry.get().hasParserForParameters(serializationt);
    }

    public <KeyT extends Key, SerializationT extends Serialization> boolean hasSerializerForKey(KeyT keyt, Class<SerializationT> cls) {
        return this.registry.get().hasSerializerForKey(keyt, cls);
    }

    public <ParametersT extends Parameters, SerializationT extends Serialization> boolean hasSerializerForParameters(ParametersT parameterst, Class<SerializationT> cls) {
        return this.registry.get().hasSerializerForParameters(parameterst, cls);
    }

    public <SerializationT extends Serialization> Key parseKey(SerializationT serializationt, @Nullable SecretKeyAccess secretKeyAccess) {
        return this.registry.get().parseKey(serializationt, secretKeyAccess);
    }

    public Key parseKeyWithLegacyFallback(ProtoKeySerialization protoKeySerialization, SecretKeyAccess secretKeyAccess) {
        if (secretKeyAccess == null) {
            throw new NullPointerException("access cannot be null");
        }
        if (hasParserForKey(protoKeySerialization)) {
            return parseKey(protoKeySerialization, secretKeyAccess);
        }
        try {
            return new LegacyProtoKey(protoKeySerialization, secretKeyAccess);
        } catch (GeneralSecurityException e2) {
            throw new TinkBugException("Creating a LegacyProtoKey failed", e2);
        }
    }

    public <SerializationT extends Serialization> Parameters parseParameters(SerializationT serializationt) {
        return this.registry.get().parseParameters(serializationt);
    }

    public Parameters parseParametersWithLegacyFallback(ProtoParametersSerialization protoParametersSerialization) {
        try {
            return parseParameters(protoParametersSerialization);
        } catch (GeneralSecurityException unused) {
            return new LegacyProtoParameters(protoParametersSerialization);
        }
    }

    public synchronized <SerializationT extends Serialization> void registerKeyParser(KeyParser<SerializationT> keyParser) {
        this.registry.set(new SerializationRegistry.Builder(this.registry.get()).registerKeyParser(keyParser).build());
    }

    public synchronized <KeyT extends Key, SerializationT extends Serialization> void registerKeySerializer(KeySerializer<KeyT, SerializationT> keySerializer) {
        this.registry.set(new SerializationRegistry.Builder(this.registry.get()).registerKeySerializer(keySerializer).build());
    }

    public synchronized <SerializationT extends Serialization> void registerParametersParser(ParametersParser<SerializationT> parametersParser) {
        this.registry.set(new SerializationRegistry.Builder(this.registry.get()).registerParametersParser(parametersParser).build());
    }

    public synchronized <ParametersT extends Parameters, SerializationT extends Serialization> void registerParametersSerializer(ParametersSerializer<ParametersT, SerializationT> parametersSerializer) {
        this.registry.set(new SerializationRegistry.Builder(this.registry.get()).registerParametersSerializer(parametersSerializer).build());
    }

    public <KeyT extends Key, SerializationT extends Serialization> SerializationT serializeKey(KeyT keyt, Class<SerializationT> cls, @Nullable SecretKeyAccess secretKeyAccess) {
        return (SerializationT) this.registry.get().serializeKey(keyt, cls, secretKeyAccess);
    }

    public <ParametersT extends Parameters, SerializationT extends Serialization> SerializationT serializeParameters(ParametersT parameterst, Class<SerializationT> cls) {
        return (SerializationT) this.registry.get().serializeParameters(parameterst, cls);
    }
}
