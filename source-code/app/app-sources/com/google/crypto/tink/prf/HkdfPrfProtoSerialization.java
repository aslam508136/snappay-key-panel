package com.google.crypto.tink.prf;

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
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.HkdfPrfKeyFormat;
import com.google.crypto.tink.proto.HkdfPrfParams;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.util.Bytes;
import com.google.crypto.tink.util.SecretBytes;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
@AccessesPartialKey
final class HkdfPrfProtoSerialization {
    private static final KeyParser<ProtoKeySerialization> KEY_PARSER;
    private static final KeySerializer<HkdfPrfKey, ProtoKeySerialization> KEY_SERIALIZER;
    private static final ParametersParser<ProtoParametersSerialization> PARAMETERS_PARSER;
    private static final ParametersSerializer<HkdfPrfParameters, ProtoParametersSerialization> PARAMETERS_SERIALIZER;
    private static final String TYPE_URL = "type.googleapis.com/google.crypto.tink.HkdfPrfKey";
    private static final Bytes TYPE_URL_BYTES;

    /* JADX INFO: renamed from: com.google.crypto.tink.prf.HkdfPrfProtoSerialization$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$HashType;

        static {
            int[] iArr = new int[HashType.values().length];
            $SwitchMap$com$google$crypto$tink$proto$HashType = iArr;
            try {
                iArr[HashType.SHA1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA224.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA256.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA384.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA512.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    static {
        Bytes bytesFromPrintableAscii = Util.toBytesFromPrintableAscii(TYPE_URL);
        TYPE_URL_BYTES = bytesFromPrintableAscii;
        PARAMETERS_SERIALIZER = ParametersSerializer.create(new a(), HkdfPrfParameters.class, ProtoParametersSerialization.class);
        PARAMETERS_PARSER = ParametersParser.create(new a(), bytesFromPrintableAscii, ProtoParametersSerialization.class);
        KEY_SERIALIZER = KeySerializer.create(new a(), HkdfPrfKey.class, ProtoKeySerialization.class);
        KEY_PARSER = KeyParser.create(new a(), bytesFromPrintableAscii, ProtoKeySerialization.class);
    }

    private HkdfPrfProtoSerialization() {
    }

    private static HkdfPrfParams getProtoParams(HkdfPrfParameters hkdfPrfParameters) {
        HkdfPrfParams.Builder hash = HkdfPrfParams.newBuilder().setHash(toProtoHashType(hkdfPrfParameters.getHashType()));
        if (hkdfPrfParameters.getSalt() != null && hkdfPrfParameters.getSalt().size() > 0) {
            hash.setSalt(ByteString.copyFrom(hkdfPrfParameters.getSalt().toByteArray()));
        }
        return hash.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static HkdfPrfKey parseKey(ProtoKeySerialization protoKeySerialization, @Nullable SecretKeyAccess secretKeyAccess) throws GeneralSecurityException {
        if (!protoKeySerialization.getTypeUrl().equals(TYPE_URL)) {
            throw new IllegalArgumentException("Wrong type URL in call to HkdfPrfProtoSerialization.parseKey");
        }
        try {
            com.google.crypto.tink.proto.HkdfPrfKey from = com.google.crypto.tink.proto.HkdfPrfKey.parseFrom(protoKeySerialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            if (from.getVersion() != 0) {
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            }
            if (protoKeySerialization.getIdRequirementOrNull() != null) {
                throw new GeneralSecurityException("ID requirement must be null.");
            }
            return HkdfPrfKey.builder().setParameters(HkdfPrfParameters.builder().setKeySizeBytes(from.getKeyValue().size()).setHashType(toHashType(from.getParams().getHash())).setSalt(Bytes.copyFrom(from.getParams().getSalt().toByteArray())).build()).setKeyBytes(SecretBytes.copyFrom(from.getKeyValue().toByteArray(), SecretKeyAccess.requireAccess(secretKeyAccess))).build();
        } catch (InvalidProtocolBufferException | IllegalArgumentException unused) {
            throw new GeneralSecurityException("Parsing HkdfPrfKey failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static HkdfPrfParameters parseParameters(ProtoParametersSerialization protoParametersSerialization) throws GeneralSecurityException {
        if (!protoParametersSerialization.getKeyTemplate().getTypeUrl().equals(TYPE_URL)) {
            throw new IllegalArgumentException("Wrong type URL in call to HkdfPrfProtoSerialization.parseParameters: " + protoParametersSerialization.getKeyTemplate().getTypeUrl());
        }
        try {
            HkdfPrfKeyFormat from = HkdfPrfKeyFormat.parseFrom(protoParametersSerialization.getKeyTemplate().getValue(), ExtensionRegistryLite.getEmptyRegistry());
            if (from.getVersion() == 0) {
                if (protoParametersSerialization.getKeyTemplate().getOutputPrefixType() == OutputPrefixType.RAW) {
                    return HkdfPrfParameters.builder().setKeySizeBytes(from.getKeySize()).setHashType(toHashType(from.getParams().getHash())).setSalt(Bytes.copyFrom(from.getParams().getSalt().toByteArray())).build();
                }
                throw new GeneralSecurityException("Parsing HkdfPrfParameters failed: only RAW output prefix type is accepted");
            }
            throw new GeneralSecurityException("Parsing HkdfPrfParameters failed: unknown Version " + from.getVersion());
        } catch (InvalidProtocolBufferException e2) {
            throw new GeneralSecurityException("Parsing HkdfPrfParameters failed: ", e2);
        }
    }

    public static void register() {
        register(MutableSerializationRegistry.globalInstance());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ProtoKeySerialization serializeKey(HkdfPrfKey hkdfPrfKey, @Nullable SecretKeyAccess secretKeyAccess) {
        return ProtoKeySerialization.create(TYPE_URL, com.google.crypto.tink.proto.HkdfPrfKey.newBuilder().setParams(getProtoParams(hkdfPrfKey.getParameters())).setKeyValue(ByteString.copyFrom(hkdfPrfKey.getKeyBytes().toByteArray(SecretKeyAccess.requireAccess(secretKeyAccess)))).build().toByteString(), KeyData.KeyMaterialType.SYMMETRIC, OutputPrefixType.RAW, hkdfPrfKey.getIdRequirementOrNull());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ProtoParametersSerialization serializeParameters(HkdfPrfParameters hkdfPrfParameters) {
        return ProtoParametersSerialization.create(KeyTemplate.newBuilder().setTypeUrl(TYPE_URL).setValue(HkdfPrfKeyFormat.newBuilder().setParams(getProtoParams(hkdfPrfParameters)).setKeySize(hkdfPrfParameters.getKeySizeBytes()).build().toByteString()).setOutputPrefixType(OutputPrefixType.RAW).build());
    }

    private static HkdfPrfParameters.HashType toHashType(HashType hashType) throws GeneralSecurityException {
        int i2 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$HashType[hashType.ordinal()];
        if (i2 == 1) {
            return HkdfPrfParameters.HashType.SHA1;
        }
        if (i2 == 2) {
            return HkdfPrfParameters.HashType.SHA224;
        }
        if (i2 == 3) {
            return HkdfPrfParameters.HashType.SHA256;
        }
        if (i2 == 4) {
            return HkdfPrfParameters.HashType.SHA384;
        }
        if (i2 == 5) {
            return HkdfPrfParameters.HashType.SHA512;
        }
        throw new GeneralSecurityException("Unable to parse HashType: " + hashType.getNumber());
    }

    private static HashType toProtoHashType(HkdfPrfParameters.HashType hashType) throws GeneralSecurityException {
        if (HkdfPrfParameters.HashType.SHA1.equals(hashType)) {
            return HashType.SHA1;
        }
        if (HkdfPrfParameters.HashType.SHA224.equals(hashType)) {
            return HashType.SHA224;
        }
        if (HkdfPrfParameters.HashType.SHA256.equals(hashType)) {
            return HashType.SHA256;
        }
        if (HkdfPrfParameters.HashType.SHA384.equals(hashType)) {
            return HashType.SHA384;
        }
        if (HkdfPrfParameters.HashType.SHA512.equals(hashType)) {
            return HashType.SHA512;
        }
        throw new GeneralSecurityException("Unable to serialize HashType " + hashType);
    }

    public static void register(MutableSerializationRegistry mutableSerializationRegistry) {
        mutableSerializationRegistry.registerParametersSerializer(PARAMETERS_SERIALIZER);
        mutableSerializationRegistry.registerParametersParser(PARAMETERS_PARSER);
        mutableSerializationRegistry.registerKeySerializer(KEY_SERIALIZER);
        mutableSerializationRegistry.registerKeyParser(KEY_PARSER);
    }
}
