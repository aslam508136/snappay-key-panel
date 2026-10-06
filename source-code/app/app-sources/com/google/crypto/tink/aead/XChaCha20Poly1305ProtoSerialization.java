package com.google.crypto.tink.aead;

import com.google.crypto.tink.AccessesPartialKey;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.internal.KeyParser;
import com.google.crypto.tink.internal.KeySerializer;
import com.google.crypto.tink.internal.MutableSerializationRegistry;
import com.google.crypto.tink.internal.ParametersParser;
import com.google.crypto.tink.internal.ParametersSerializer;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.internal.ProtoParametersSerialization;
import com.google.crypto.tink.internal.Util;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.proto.XChaCha20Poly1305KeyFormat;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.util.Bytes;
import com.google.crypto.tink.util.SecretBytes;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
@AccessesPartialKey
final class XChaCha20Poly1305ProtoSerialization {
    private static final KeyParser<ProtoKeySerialization> KEY_PARSER;
    private static final KeySerializer<XChaCha20Poly1305Key, ProtoKeySerialization> KEY_SERIALIZER;
    private static final ParametersParser<ProtoParametersSerialization> PARAMETERS_PARSER;
    private static final ParametersSerializer<XChaCha20Poly1305Parameters, ProtoParametersSerialization> PARAMETERS_SERIALIZER;
    private static final String TYPE_URL = "type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key";
    private static final Bytes TYPE_URL_BYTES;

    /* JADX INFO: renamed from: com.google.crypto.tink.aead.XChaCha20Poly1305ProtoSerialization$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType;

        static {
            int[] iArr = new int[OutputPrefixType.values().length];
            $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType = iArr;
            try {
                iArr[OutputPrefixType.TINK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.CRUNCHY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.LEGACY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.RAW.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    static {
        Bytes bytesFromPrintableAscii = Util.toBytesFromPrintableAscii(TYPE_URL);
        TYPE_URL_BYTES = bytesFromPrintableAscii;
        PARAMETERS_SERIALIZER = ParametersSerializer.create(new a(16), XChaCha20Poly1305Parameters.class, ProtoParametersSerialization.class);
        PARAMETERS_PARSER = ParametersParser.create(new a(17), bytesFromPrintableAscii, ProtoParametersSerialization.class);
        KEY_SERIALIZER = KeySerializer.create(new a(18), XChaCha20Poly1305Key.class, ProtoKeySerialization.class);
        KEY_PARSER = KeyParser.create(new a(19), bytesFromPrintableAscii, ProtoKeySerialization.class);
    }

    private XChaCha20Poly1305ProtoSerialization() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static XChaCha20Poly1305Key parseKey(ProtoKeySerialization protoKeySerialization, @Nullable SecretKeyAccess secretKeyAccess) throws GeneralSecurityException {
        if (!protoKeySerialization.getTypeUrl().equals(TYPE_URL)) {
            throw new IllegalArgumentException("Wrong type URL in call to XChaCha20Poly1305Parameters.parseParameters");
        }
        try {
            com.google.crypto.tink.proto.XChaCha20Poly1305Key from = com.google.crypto.tink.proto.XChaCha20Poly1305Key.parseFrom(protoKeySerialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            if (from.getVersion() == 0) {
                return XChaCha20Poly1305Key.create(toVariant(protoKeySerialization.getOutputPrefixType()), SecretBytes.copyFrom(from.getKeyValue().toByteArray(), SecretKeyAccess.requireAccess(secretKeyAccess)), protoKeySerialization.getIdRequirementOrNull());
            }
            throw new GeneralSecurityException("Only version 0 keys are accepted");
        } catch (InvalidProtocolBufferException unused) {
            throw new GeneralSecurityException("Parsing XChaCha20Poly1305Key failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static XChaCha20Poly1305Parameters parseParameters(ProtoParametersSerialization protoParametersSerialization) throws GeneralSecurityException {
        if (!protoParametersSerialization.getKeyTemplate().getTypeUrl().equals(TYPE_URL)) {
            throw new IllegalArgumentException("Wrong type URL in call to XChaCha20Poly1305Parameters.parseParameters: " + protoParametersSerialization.getKeyTemplate().getTypeUrl());
        }
        try {
            XChaCha20Poly1305KeyFormat.parseFrom(protoParametersSerialization.getKeyTemplate().getValue(), ExtensionRegistryLite.getEmptyRegistry());
            return XChaCha20Poly1305Parameters.create(toVariant(protoParametersSerialization.getKeyTemplate().getOutputPrefixType()));
        } catch (InvalidProtocolBufferException e2) {
            throw new GeneralSecurityException("Parsing XChaCha20Poly1305Parameters failed: ", e2);
        }
    }

    public static void register() {
        register(MutableSerializationRegistry.globalInstance());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ProtoKeySerialization serializeKey(XChaCha20Poly1305Key xChaCha20Poly1305Key, @Nullable SecretKeyAccess secretKeyAccess) {
        return ProtoKeySerialization.create(TYPE_URL, com.google.crypto.tink.proto.XChaCha20Poly1305Key.newBuilder().setKeyValue(ByteString.copyFrom(xChaCha20Poly1305Key.getKeyBytes().toByteArray(SecretKeyAccess.requireAccess(secretKeyAccess)))).build().toByteString(), KeyData.KeyMaterialType.SYMMETRIC, toProtoOutputPrefixType(xChaCha20Poly1305Key.getParameters().getVariant()), xChaCha20Poly1305Key.getIdRequirementOrNull());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ProtoParametersSerialization serializeParameters(XChaCha20Poly1305Parameters xChaCha20Poly1305Parameters) {
        return ProtoParametersSerialization.create(KeyTemplate.newBuilder().setTypeUrl(TYPE_URL).setValue(XChaCha20Poly1305KeyFormat.getDefaultInstance().toByteString()).setOutputPrefixType(toProtoOutputPrefixType(xChaCha20Poly1305Parameters.getVariant())).build());
    }

    private static OutputPrefixType toProtoOutputPrefixType(XChaCha20Poly1305Parameters.Variant variant) throws GeneralSecurityException {
        if (XChaCha20Poly1305Parameters.Variant.TINK.equals(variant)) {
            return OutputPrefixType.TINK;
        }
        if (XChaCha20Poly1305Parameters.Variant.CRUNCHY.equals(variant)) {
            return OutputPrefixType.CRUNCHY;
        }
        if (XChaCha20Poly1305Parameters.Variant.NO_PREFIX.equals(variant)) {
            return OutputPrefixType.RAW;
        }
        throw new GeneralSecurityException("Unable to serialize variant: " + variant);
    }

    private static XChaCha20Poly1305Parameters.Variant toVariant(OutputPrefixType outputPrefixType) throws GeneralSecurityException {
        int i2 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[outputPrefixType.ordinal()];
        if (i2 == 1) {
            return XChaCha20Poly1305Parameters.Variant.TINK;
        }
        if (i2 == 2 || i2 == 3) {
            return XChaCha20Poly1305Parameters.Variant.CRUNCHY;
        }
        if (i2 == 4) {
            return XChaCha20Poly1305Parameters.Variant.NO_PREFIX;
        }
        throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + outputPrefixType.getNumber());
    }

    public static void register(MutableSerializationRegistry mutableSerializationRegistry) {
        mutableSerializationRegistry.registerParametersSerializer(PARAMETERS_SERIALIZER);
        mutableSerializationRegistry.registerParametersParser(PARAMETERS_PARSER);
        mutableSerializationRegistry.registerKeySerializer(KEY_SERIALIZER);
        mutableSerializationRegistry.registerKeyParser(KEY_PARSER);
    }
}
