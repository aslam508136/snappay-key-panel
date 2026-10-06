package com.google.crypto.tink.prf;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.internal.KeyParser;
import com.google.crypto.tink.internal.KeySerializer;
import com.google.crypto.tink.internal.ParametersParser;
import com.google.crypto.tink.internal.ParametersSerializer;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.internal.ProtoParametersSerialization;
import com.google.crypto.tink.internal.Serialization;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a implements ParametersSerializer.ParametersSerializationFunction, ParametersParser.ParametersParsingFunction, KeySerializer.KeySerializationFunction, KeyParser.KeyParsingFunction {
    @Override // com.google.crypto.tink.internal.KeyParser.KeyParsingFunction
    public final Key parseKey(Serialization serialization, SecretKeyAccess secretKeyAccess) {
        return HkdfPrfProtoSerialization.parseKey((ProtoKeySerialization) serialization, secretKeyAccess);
    }

    @Override // com.google.crypto.tink.internal.ParametersParser.ParametersParsingFunction
    public final Parameters parseParameters(Serialization serialization) {
        return HkdfPrfProtoSerialization.parseParameters((ProtoParametersSerialization) serialization);
    }

    @Override // com.google.crypto.tink.internal.KeySerializer.KeySerializationFunction
    public final Serialization serializeKey(Key key, SecretKeyAccess secretKeyAccess) {
        return HkdfPrfProtoSerialization.serializeKey((HkdfPrfKey) key, secretKeyAccess);
    }

    @Override // com.google.crypto.tink.internal.ParametersSerializer.ParametersSerializationFunction
    public final Serialization serializeParameters(Parameters parameters) {
        return HkdfPrfProtoSerialization.serializeParameters((HkdfPrfParameters) parameters);
    }
}
