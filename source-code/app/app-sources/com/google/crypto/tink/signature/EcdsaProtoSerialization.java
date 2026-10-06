package com.google.crypto.tink.signature;

import com.google.crypto.tink.AccessesPartialKey;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.internal.BigIntegerEncoding;
import com.google.crypto.tink.internal.KeyParser;
import com.google.crypto.tink.internal.KeySerializer;
import com.google.crypto.tink.internal.MutableSerializationRegistry;
import com.google.crypto.tink.internal.ParametersParser;
import com.google.crypto.tink.internal.ParametersSerializer;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.internal.ProtoParametersSerialization;
import com.google.crypto.tink.internal.Util;
import com.google.crypto.tink.proto.EcdsaKeyFormat;
import com.google.crypto.tink.proto.EcdsaParams;
import com.google.crypto.tink.proto.EcdsaSignatureEncoding;
import com.google.crypto.tink.proto.EllipticCurveType;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.util.Bytes;
import com.google.crypto.tink.util.SecretBigInteger;
import java.security.GeneralSecurityException;
import java.security.spec.ECPoint;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
@AccessesPartialKey
final class EcdsaProtoSerialization {
    private static final ParametersParser<ProtoParametersSerialization> PARAMETERS_PARSER;
    private static final ParametersSerializer<EcdsaParameters, ProtoParametersSerialization> PARAMETERS_SERIALIZER;
    private static final KeyParser<ProtoKeySerialization> PRIVATE_KEY_PARSER;
    private static final KeySerializer<EcdsaPrivateKey, ProtoKeySerialization> PRIVATE_KEY_SERIALIZER;
    private static final String PRIVATE_TYPE_URL = "type.googleapis.com/google.crypto.tink.EcdsaPrivateKey";
    private static final Bytes PRIVATE_TYPE_URL_BYTES;
    private static final KeyParser<ProtoKeySerialization> PUBLIC_KEY_PARSER;
    private static final KeySerializer<EcdsaPublicKey, ProtoKeySerialization> PUBLIC_KEY_SERIALIZER;
    private static final String PUBLIC_TYPE_URL = "type.googleapis.com/google.crypto.tink.EcdsaPublicKey";
    private static final Bytes PUBLIC_TYPE_URL_BYTES;

    /* JADX INFO: renamed from: com.google.crypto.tink.signature.EcdsaProtoSerialization$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$EcdsaSignatureEncoding;
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$EllipticCurveType;
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$HashType;
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType;

        static {
            int[] iArr = new int[EcdsaSignatureEncoding.values().length];
            $SwitchMap$com$google$crypto$tink$proto$EcdsaSignatureEncoding = iArr;
            try {
                iArr[EcdsaSignatureEncoding.IEEE_P1363.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$EcdsaSignatureEncoding[EcdsaSignatureEncoding.DER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[EllipticCurveType.values().length];
            $SwitchMap$com$google$crypto$tink$proto$EllipticCurveType = iArr2;
            try {
                iArr2[EllipticCurveType.NIST_P256.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$EllipticCurveType[EllipticCurveType.NIST_P384.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$EllipticCurveType[EllipticCurveType.NIST_P521.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr3 = new int[OutputPrefixType.values().length];
            $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType = iArr3;
            try {
                iArr3[OutputPrefixType.TINK.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.CRUNCHY.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.LEGACY.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.RAW.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            int[] iArr4 = new int[HashType.values().length];
            $SwitchMap$com$google$crypto$tink$proto$HashType = iArr4;
            try {
                iArr4[HashType.SHA256.ordinal()] = 1;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA384.ordinal()] = 2;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$HashType[HashType.SHA512.ordinal()] = 3;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    static {
        Bytes bytesFromPrintableAscii = Util.toBytesFromPrintableAscii(PRIVATE_TYPE_URL);
        PRIVATE_TYPE_URL_BYTES = bytesFromPrintableAscii;
        Bytes bytesFromPrintableAscii2 = Util.toBytesFromPrintableAscii(PUBLIC_TYPE_URL);
        PUBLIC_TYPE_URL_BYTES = bytesFromPrintableAscii2;
        PARAMETERS_SERIALIZER = ParametersSerializer.create(new a(0), EcdsaParameters.class, ProtoParametersSerialization.class);
        PARAMETERS_PARSER = ParametersParser.create(new a(1), bytesFromPrintableAscii, ProtoParametersSerialization.class);
        PUBLIC_KEY_SERIALIZER = KeySerializer.create(new a(2), EcdsaPublicKey.class, ProtoKeySerialization.class);
        PUBLIC_KEY_PARSER = KeyParser.create(new a(3), bytesFromPrintableAscii2, ProtoKeySerialization.class);
        PRIVATE_KEY_SERIALIZER = KeySerializer.create(new a(4), EcdsaPrivateKey.class, ProtoKeySerialization.class);
        PRIVATE_KEY_PARSER = KeyParser.create(new a(5), bytesFromPrintableAscii, ProtoKeySerialization.class);
    }

    private EcdsaProtoSerialization() {
    }

    private static int getEncodingLength(EcdsaParameters.CurveType curveType) throws GeneralSecurityException {
        if (EcdsaParameters.CurveType.NIST_P256.equals(curveType)) {
            return 33;
        }
        if (EcdsaParameters.CurveType.NIST_P384.equals(curveType)) {
            return 49;
        }
        if (EcdsaParameters.CurveType.NIST_P521.equals(curveType)) {
            return 67;
        }
        throw new GeneralSecurityException("Unable to serialize CurveType " + curveType);
    }

    private static EcdsaParams getProtoParams(EcdsaParameters ecdsaParameters) {
        return EcdsaParams.newBuilder().setHashType(toProtoHashType(ecdsaParameters.getHashType())).setCurve(toProtoCurveType(ecdsaParameters.getCurveType())).setEncoding(toProtoSignatureEncoding(ecdsaParameters.getSignatureEncoding())).build();
    }

    private static com.google.crypto.tink.proto.EcdsaPublicKey getProtoPublicKey(EcdsaPublicKey ecdsaPublicKey) throws GeneralSecurityException {
        int encodingLength = getEncodingLength(ecdsaPublicKey.getParameters().getCurveType());
        ECPoint publicPoint = ecdsaPublicKey.getPublicPoint();
        return com.google.crypto.tink.proto.EcdsaPublicKey.newBuilder().setParams(getProtoParams(ecdsaPublicKey.getParameters())).setX(ByteString.copyFrom(BigIntegerEncoding.toBigEndianBytesOfFixedLength(publicPoint.getAffineX(), encodingLength))).setY(ByteString.copyFrom(BigIntegerEncoding.toBigEndianBytesOfFixedLength(publicPoint.getAffineY(), encodingLength))).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static EcdsaParameters parseParameters(ProtoParametersSerialization protoParametersSerialization) throws GeneralSecurityException {
        if (!protoParametersSerialization.getKeyTemplate().getTypeUrl().equals(PRIVATE_TYPE_URL)) {
            throw new IllegalArgumentException("Wrong type URL in call to EcdsaProtoSerialization.parseParameters: " + protoParametersSerialization.getKeyTemplate().getTypeUrl());
        }
        try {
            EcdsaKeyFormat from = EcdsaKeyFormat.parseFrom(protoParametersSerialization.getKeyTemplate().getValue(), ExtensionRegistryLite.getEmptyRegistry());
            return EcdsaParameters.builder().setHashType(toHashType(from.getParams().getHashType())).setSignatureEncoding(toSignatureEncoding(from.getParams().getEncoding())).setCurveType(toCurveType(from.getParams().getCurve())).setVariant(toVariant(protoParametersSerialization.getKeyTemplate().getOutputPrefixType())).build();
        } catch (InvalidProtocolBufferException e2) {
            throw new GeneralSecurityException("Parsing EcdsaParameters failed: ", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static EcdsaPrivateKey parsePrivateKey(ProtoKeySerialization protoKeySerialization, @Nullable SecretKeyAccess secretKeyAccess) throws GeneralSecurityException {
        if (!protoKeySerialization.getTypeUrl().equals(PRIVATE_TYPE_URL)) {
            throw new IllegalArgumentException("Wrong type URL in call to EcdsaProtoSerialization.parsePrivateKey: " + protoKeySerialization.getTypeUrl());
        }
        try {
            com.google.crypto.tink.proto.EcdsaPrivateKey from = com.google.crypto.tink.proto.EcdsaPrivateKey.parseFrom(protoKeySerialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            if (from.getVersion() != 0) {
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            }
            com.google.crypto.tink.proto.EcdsaPublicKey publicKey = from.getPublicKey();
            return EcdsaPrivateKey.builder().setPublicKey(EcdsaPublicKey.builder().setParameters(EcdsaParameters.builder().setHashType(toHashType(publicKey.getParams().getHashType())).setSignatureEncoding(toSignatureEncoding(publicKey.getParams().getEncoding())).setCurveType(toCurveType(publicKey.getParams().getCurve())).setVariant(toVariant(protoKeySerialization.getOutputPrefixType())).build()).setPublicPoint(new ECPoint(BigIntegerEncoding.fromUnsignedBigEndianBytes(publicKey.getX().toByteArray()), BigIntegerEncoding.fromUnsignedBigEndianBytes(publicKey.getY().toByteArray()))).setIdRequirement(protoKeySerialization.getIdRequirementOrNull()).build()).setPrivateValue(SecretBigInteger.fromBigInteger(BigIntegerEncoding.fromUnsignedBigEndianBytes(from.getKeyValue().toByteArray()), SecretKeyAccess.requireAccess(secretKeyAccess))).build();
        } catch (InvalidProtocolBufferException | IllegalArgumentException unused) {
            throw new GeneralSecurityException("Parsing EcdsaPrivateKey failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static EcdsaPublicKey parsePublicKey(ProtoKeySerialization protoKeySerialization, @Nullable SecretKeyAccess secretKeyAccess) throws GeneralSecurityException {
        if (!protoKeySerialization.getTypeUrl().equals(PUBLIC_TYPE_URL)) {
            throw new IllegalArgumentException("Wrong type URL in call to EcdsaProtoSerialization.parsePublicKey: " + protoKeySerialization.getTypeUrl());
        }
        try {
            com.google.crypto.tink.proto.EcdsaPublicKey from = com.google.crypto.tink.proto.EcdsaPublicKey.parseFrom(protoKeySerialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            if (from.getVersion() != 0) {
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            }
            return EcdsaPublicKey.builder().setParameters(EcdsaParameters.builder().setHashType(toHashType(from.getParams().getHashType())).setSignatureEncoding(toSignatureEncoding(from.getParams().getEncoding())).setCurveType(toCurveType(from.getParams().getCurve())).setVariant(toVariant(protoKeySerialization.getOutputPrefixType())).build()).setPublicPoint(new ECPoint(BigIntegerEncoding.fromUnsignedBigEndianBytes(from.getX().toByteArray()), BigIntegerEncoding.fromUnsignedBigEndianBytes(from.getY().toByteArray()))).setIdRequirement(protoKeySerialization.getIdRequirementOrNull()).build();
        } catch (InvalidProtocolBufferException | IllegalArgumentException unused) {
            throw new GeneralSecurityException("Parsing EcdsaPublicKey failed");
        }
    }

    public static void register() {
        register(MutableSerializationRegistry.globalInstance());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ProtoParametersSerialization serializeParameters(EcdsaParameters ecdsaParameters) {
        return ProtoParametersSerialization.create(KeyTemplate.newBuilder().setTypeUrl(PRIVATE_TYPE_URL).setValue(EcdsaKeyFormat.newBuilder().setParams(getProtoParams(ecdsaParameters)).build().toByteString()).setOutputPrefixType(toProtoOutputPrefixType(ecdsaParameters.getVariant())).build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ProtoKeySerialization serializePrivateKey(EcdsaPrivateKey ecdsaPrivateKey, @Nullable SecretKeyAccess secretKeyAccess) throws GeneralSecurityException {
        return ProtoKeySerialization.create(PRIVATE_TYPE_URL, com.google.crypto.tink.proto.EcdsaPrivateKey.newBuilder().setPublicKey(getProtoPublicKey(ecdsaPrivateKey.getPublicKey())).setKeyValue(ByteString.copyFrom(BigIntegerEncoding.toBigEndianBytesOfFixedLength(ecdsaPrivateKey.getPrivateValue().getBigInteger(SecretKeyAccess.requireAccess(secretKeyAccess)), getEncodingLength(ecdsaPrivateKey.getParameters().getCurveType())))).build().toByteString(), KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE, toProtoOutputPrefixType(ecdsaPrivateKey.getParameters().getVariant()), ecdsaPrivateKey.getIdRequirementOrNull());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ProtoKeySerialization serializePublicKey(EcdsaPublicKey ecdsaPublicKey, @Nullable SecretKeyAccess secretKeyAccess) {
        return ProtoKeySerialization.create(PUBLIC_TYPE_URL, getProtoPublicKey(ecdsaPublicKey).toByteString(), KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC, toProtoOutputPrefixType(ecdsaPublicKey.getParameters().getVariant()), ecdsaPublicKey.getIdRequirementOrNull());
    }

    private static EcdsaParameters.CurveType toCurveType(EllipticCurveType ellipticCurveType) throws GeneralSecurityException {
        int i2 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$EllipticCurveType[ellipticCurveType.ordinal()];
        if (i2 == 1) {
            return EcdsaParameters.CurveType.NIST_P256;
        }
        if (i2 == 2) {
            return EcdsaParameters.CurveType.NIST_P384;
        }
        if (i2 == 3) {
            return EcdsaParameters.CurveType.NIST_P521;
        }
        throw new GeneralSecurityException("Unable to parse EllipticCurveType: " + ellipticCurveType.getNumber());
    }

    private static EcdsaParameters.HashType toHashType(HashType hashType) throws GeneralSecurityException {
        int i2 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$HashType[hashType.ordinal()];
        if (i2 == 1) {
            return EcdsaParameters.HashType.SHA256;
        }
        if (i2 == 2) {
            return EcdsaParameters.HashType.SHA384;
        }
        if (i2 == 3) {
            return EcdsaParameters.HashType.SHA512;
        }
        throw new GeneralSecurityException("Unable to parse HashType: " + hashType.getNumber());
    }

    private static EllipticCurveType toProtoCurveType(EcdsaParameters.CurveType curveType) throws GeneralSecurityException {
        if (EcdsaParameters.CurveType.NIST_P256.equals(curveType)) {
            return EllipticCurveType.NIST_P256;
        }
        if (EcdsaParameters.CurveType.NIST_P384.equals(curveType)) {
            return EllipticCurveType.NIST_P384;
        }
        if (EcdsaParameters.CurveType.NIST_P521.equals(curveType)) {
            return EllipticCurveType.NIST_P521;
        }
        throw new GeneralSecurityException("Unable to serialize CurveType " + curveType);
    }

    private static HashType toProtoHashType(EcdsaParameters.HashType hashType) throws GeneralSecurityException {
        if (EcdsaParameters.HashType.SHA256.equals(hashType)) {
            return HashType.SHA256;
        }
        if (EcdsaParameters.HashType.SHA384.equals(hashType)) {
            return HashType.SHA384;
        }
        if (EcdsaParameters.HashType.SHA512.equals(hashType)) {
            return HashType.SHA512;
        }
        throw new GeneralSecurityException("Unable to serialize HashType " + hashType);
    }

    private static OutputPrefixType toProtoOutputPrefixType(EcdsaParameters.Variant variant) throws GeneralSecurityException {
        if (EcdsaParameters.Variant.TINK.equals(variant)) {
            return OutputPrefixType.TINK;
        }
        if (EcdsaParameters.Variant.CRUNCHY.equals(variant)) {
            return OutputPrefixType.CRUNCHY;
        }
        if (EcdsaParameters.Variant.NO_PREFIX.equals(variant)) {
            return OutputPrefixType.RAW;
        }
        if (EcdsaParameters.Variant.LEGACY.equals(variant)) {
            return OutputPrefixType.LEGACY;
        }
        throw new GeneralSecurityException("Unable to serialize variant: " + variant);
    }

    private static EcdsaSignatureEncoding toProtoSignatureEncoding(EcdsaParameters.SignatureEncoding signatureEncoding) throws GeneralSecurityException {
        if (EcdsaParameters.SignatureEncoding.IEEE_P1363.equals(signatureEncoding)) {
            return EcdsaSignatureEncoding.IEEE_P1363;
        }
        if (EcdsaParameters.SignatureEncoding.DER.equals(signatureEncoding)) {
            return EcdsaSignatureEncoding.DER;
        }
        throw new GeneralSecurityException("Unable to serialize SignatureEncoding " + signatureEncoding);
    }

    private static EcdsaParameters.SignatureEncoding toSignatureEncoding(EcdsaSignatureEncoding ecdsaSignatureEncoding) throws GeneralSecurityException {
        int i2 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$EcdsaSignatureEncoding[ecdsaSignatureEncoding.ordinal()];
        if (i2 == 1) {
            return EcdsaParameters.SignatureEncoding.IEEE_P1363;
        }
        if (i2 == 2) {
            return EcdsaParameters.SignatureEncoding.DER;
        }
        throw new GeneralSecurityException("Unable to parse EcdsaSignatureEncoding: " + ecdsaSignatureEncoding.getNumber());
    }

    private static EcdsaParameters.Variant toVariant(OutputPrefixType outputPrefixType) throws GeneralSecurityException {
        int i2 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[outputPrefixType.ordinal()];
        if (i2 == 1) {
            return EcdsaParameters.Variant.TINK;
        }
        if (i2 == 2) {
            return EcdsaParameters.Variant.CRUNCHY;
        }
        if (i2 == 3) {
            return EcdsaParameters.Variant.LEGACY;
        }
        if (i2 == 4) {
            return EcdsaParameters.Variant.NO_PREFIX;
        }
        throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + outputPrefixType.getNumber());
    }

    public static void register(MutableSerializationRegistry mutableSerializationRegistry) {
        mutableSerializationRegistry.registerParametersSerializer(PARAMETERS_SERIALIZER);
        mutableSerializationRegistry.registerParametersParser(PARAMETERS_PARSER);
        mutableSerializationRegistry.registerKeySerializer(PUBLIC_KEY_SERIALIZER);
        mutableSerializationRegistry.registerKeyParser(PUBLIC_KEY_PARSER);
        mutableSerializationRegistry.registerKeySerializer(PRIVATE_KEY_SERIALIZER);
        mutableSerializationRegistry.registerKeyParser(PRIVATE_KEY_PARSER);
    }
}
