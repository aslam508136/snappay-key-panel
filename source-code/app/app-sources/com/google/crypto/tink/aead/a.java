package com.google.crypto.tink.aead;

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

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f509a;

    public /* synthetic */ a(int i2) {
        this.f509a = i2;
    }

    @Override // com.google.crypto.tink.internal.KeyParser.KeyParsingFunction
    public final Key parseKey(Serialization serialization, SecretKeyAccess secretKeyAccess) {
        switch (this.f509a) {
            case 3:
                return AesEaxProtoSerialization.parseKey((ProtoKeySerialization) serialization, secretKeyAccess);
            case 7:
                return AesGcmProtoSerialization.parseKey((ProtoKeySerialization) serialization, secretKeyAccess);
            case 11:
                return AesGcmSivProtoSerialization.parseKey((ProtoKeySerialization) serialization, secretKeyAccess);
            case TYPE_SFIXED32_VALUE:
                return ChaCha20Poly1305ProtoSerialization.parseKey((ProtoKeySerialization) serialization, secretKeyAccess);
            default:
                return XChaCha20Poly1305ProtoSerialization.parseKey((ProtoKeySerialization) serialization, secretKeyAccess);
        }
    }

    @Override // com.google.crypto.tink.internal.ParametersParser.ParametersParsingFunction
    public final Parameters parseParameters(Serialization serialization) {
        switch (this.f509a) {
            case 1:
                return AesEaxProtoSerialization.parseParameters((ProtoParametersSerialization) serialization);
            case 5:
                return AesGcmProtoSerialization.parseParameters((ProtoParametersSerialization) serialization);
            case 9:
                return AesGcmSivProtoSerialization.parseParameters((ProtoParametersSerialization) serialization);
            case TYPE_UINT32_VALUE:
                return ChaCha20Poly1305ProtoSerialization.parseParameters((ProtoParametersSerialization) serialization);
            default:
                return XChaCha20Poly1305ProtoSerialization.parseParameters((ProtoParametersSerialization) serialization);
        }
    }

    @Override // com.google.crypto.tink.internal.KeySerializer.KeySerializationFunction
    public final Serialization serializeKey(Key key, SecretKeyAccess secretKeyAccess) {
        switch (this.f509a) {
            case 2:
                return AesEaxProtoSerialization.serializeKey((AesEaxKey) key, secretKeyAccess);
            case 6:
                return AesGcmProtoSerialization.serializeKey((AesGcmKey) key, secretKeyAccess);
            case 10:
                return AesGcmSivProtoSerialization.serializeKey((AesGcmSivKey) key, secretKeyAccess);
            case TYPE_ENUM_VALUE:
                return ChaCha20Poly1305ProtoSerialization.serializeKey((ChaCha20Poly1305Key) key, secretKeyAccess);
            default:
                return XChaCha20Poly1305ProtoSerialization.serializeKey((XChaCha20Poly1305Key) key, secretKeyAccess);
        }
    }

    @Override // com.google.crypto.tink.internal.ParametersSerializer.ParametersSerializationFunction
    public final Serialization serializeParameters(Parameters parameters) {
        switch (this.f509a) {
            case 0:
                return AesEaxProtoSerialization.serializeParameters((AesEaxParameters) parameters);
            case 4:
                return AesGcmProtoSerialization.serializeParameters((AesGcmParameters) parameters);
            case 8:
                return AesGcmSivProtoSerialization.serializeParameters((AesGcmSivParameters) parameters);
            case 12:
                return ChaCha20Poly1305ProtoSerialization.serializeParameters((ChaCha20Poly1305Parameters) parameters);
            default:
                return XChaCha20Poly1305ProtoSerialization.serializeParameters((XChaCha20Poly1305Parameters) parameters);
        }
    }
}
