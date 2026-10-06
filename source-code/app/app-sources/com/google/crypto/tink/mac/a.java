package com.google.crypto.tink.mac;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.internal.KeyParser;
import com.google.crypto.tink.internal.KeySerializer;
import com.google.crypto.tink.internal.ParametersParser;
import com.google.crypto.tink.internal.ParametersSerializer;
import com.google.crypto.tink.internal.PrimitiveConstructor;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.internal.ProtoParametersSerialization;
import com.google.crypto.tink.internal.Serialization;
import com.google.crypto.tink.mac.internal.ChunkedAesCmacImpl;
import com.google.crypto.tink.mac.internal.ChunkedHmacImpl;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a implements ParametersSerializer.ParametersSerializationFunction, ParametersParser.ParametersParsingFunction, KeySerializer.KeySerializationFunction, KeyParser.KeyParsingFunction, PrimitiveConstructor.PrimitiveConstructionFunction {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f513a;

    public /* synthetic */ a(int i2) {
        this.f513a = i2;
    }

    @Override // com.google.crypto.tink.internal.PrimitiveConstructor.PrimitiveConstructionFunction
    public final Object constructPrimitive(Key key) {
        switch (this.f513a) {
            case 8:
                return new ChunkedAesCmacImpl((AesCmacKey) key);
            default:
                return new ChunkedHmacImpl((HmacKey) key);
        }
    }

    @Override // com.google.crypto.tink.internal.KeyParser.KeyParsingFunction
    public final Key parseKey(Serialization serialization, SecretKeyAccess secretKeyAccess) {
        switch (this.f513a) {
            case 3:
                return AesCmacProtoSerialization.parseKey((ProtoKeySerialization) serialization, secretKeyAccess);
            default:
                return HmacProtoSerialization.parseKey((ProtoKeySerialization) serialization, secretKeyAccess);
        }
    }

    @Override // com.google.crypto.tink.internal.ParametersParser.ParametersParsingFunction
    public final Parameters parseParameters(Serialization serialization) {
        switch (this.f513a) {
            case 1:
                return AesCmacProtoSerialization.parseParameters((ProtoParametersSerialization) serialization);
            default:
                return HmacProtoSerialization.parseParameters((ProtoParametersSerialization) serialization);
        }
    }

    @Override // com.google.crypto.tink.internal.KeySerializer.KeySerializationFunction
    public final Serialization serializeKey(Key key, SecretKeyAccess secretKeyAccess) {
        switch (this.f513a) {
            case 2:
                return AesCmacProtoSerialization.serializeKey((AesCmacKey) key, secretKeyAccess);
            default:
                return HmacProtoSerialization.serializeKey((HmacKey) key, secretKeyAccess);
        }
    }

    @Override // com.google.crypto.tink.internal.ParametersSerializer.ParametersSerializationFunction
    public final Serialization serializeParameters(Parameters parameters) {
        switch (this.f513a) {
            case 0:
                return AesCmacProtoSerialization.serializeParameters((AesCmacParameters) parameters);
            default:
                return HmacProtoSerialization.serializeParameters((HmacParameters) parameters);
        }
    }
}
