package com.google.crypto.tink.jwt;

import androidx.lifecycle.i;
import com.google.crypto.tink.Key;
import com.google.crypto.tink.KeyStatus;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.internal.LegacyProtoKey;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.proto.JwtEcdsaAlgorithm;
import com.google.crypto.tink.proto.JwtEcdsaPublicKey;
import com.google.crypto.tink.proto.JwtRsaSsaPkcs1Algorithm;
import com.google.crypto.tink.proto.JwtRsaSsaPkcs1PublicKey;
import com.google.crypto.tink.proto.JwtRsaSsaPssAlgorithm;
import com.google.crypto.tink.proto.JwtRsaSsaPssPublicKey;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.Base64;
import com.google.crypto.tink.tinkkey.KeyAccess;
import com.google.errorprone.annotations.InlineMe;
import i0.a;
import i0.b;
import i0.e;
import i0.f;
import i0.g;
import java.io.StringReader;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.Optional;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class JwkSetConverter {
    private static final String JWT_ECDSA_PUBLIC_KEY_URL = "type.googleapis.com/google.crypto.tink.JwtEcdsaPublicKey";
    private static final String JWT_RSA_SSA_PKCS1_PUBLIC_KEY_URL = "type.googleapis.com/google.crypto.tink.JwtRsaSsaPkcs1PublicKey";
    private static final String JWT_RSA_SSA_PSS_PUBLIC_KEY_URL = "type.googleapis.com/google.crypto.tink.JwtRsaSsaPssPublicKey";

    /* JADX INFO: renamed from: com.google.crypto.tink.jwt.JwkSetConverter$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm;
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPkcs1Algorithm;
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPssAlgorithm;

        static {
            int[] iArr = new int[JwtRsaSsaPssAlgorithm.values().length];
            $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPssAlgorithm = iArr;
            try {
                iArr[JwtRsaSsaPssAlgorithm.PS256.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPssAlgorithm[JwtRsaSsaPssAlgorithm.PS384.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPssAlgorithm[JwtRsaSsaPssAlgorithm.PS512.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[JwtRsaSsaPkcs1Algorithm.values().length];
            $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPkcs1Algorithm = iArr2;
            try {
                iArr2[JwtRsaSsaPkcs1Algorithm.RS256.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPkcs1Algorithm[JwtRsaSsaPkcs1Algorithm.RS384.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPkcs1Algorithm[JwtRsaSsaPkcs1Algorithm.RS512.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            int[] iArr3 = new int[JwtEcdsaAlgorithm.values().length];
            $SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm = iArr3;
            try {
                iArr3[JwtEcdsaAlgorithm.ES256.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm[JwtEcdsaAlgorithm.ES384.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm[JwtEcdsaAlgorithm.ES512.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    private JwkSetConverter() {
    }

    private static e convertJwtEcdsaKey(ProtoKeySerialization protoKeySerialization) throws GeneralSecurityException {
        String str;
        String str2;
        String value;
        try {
            JwtEcdsaPublicKey from = JwtEcdsaPublicKey.parseFrom(protoKeySerialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            int i2 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$JwtEcdsaAlgorithm[from.getAlgorithm().ordinal()];
            if (i2 == 1) {
                str = "ES256";
                str2 = "P-256";
            } else if (i2 == 2) {
                str = "ES384";
                str2 = "P-384";
            } else {
                if (i2 != 3) {
                    throw new GeneralSecurityException("unknown algorithm");
                }
                str = "ES512";
                str2 = "P-521";
            }
            e eVar = new e();
            eVar.l("kty", "EC");
            eVar.l("crv", str2);
            eVar.l("x", Base64.urlSafeEncode(from.getX().toByteArray()));
            eVar.l("y", Base64.urlSafeEncode(from.getY().toByteArray()));
            eVar.l("use", "sig");
            eVar.l("alg", str);
            a aVar = new a();
            aVar.k("verify");
            eVar.j("key_ops", aVar);
            Optional<String> kid = getKid(protoKeySerialization.getIdRequirementOrNull());
            if (!kid.isPresent()) {
                if (from.hasCustomKid()) {
                    value = from.getCustomKid().getValue();
                }
                return eVar;
            }
            value = (String) kid.get();
            eVar.l("kid", value);
            return eVar;
        } catch (InvalidProtocolBufferException e2) {
            throw new GeneralSecurityException("failed to parse value as JwtEcdsaPublicKey proto", e2);
        }
    }

    private static e convertJwtRsaSsaPkcs1(ProtoKeySerialization protoKeySerialization) throws GeneralSecurityException {
        String str;
        String value;
        try {
            JwtRsaSsaPkcs1PublicKey from = JwtRsaSsaPkcs1PublicKey.parseFrom(protoKeySerialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            int i2 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPkcs1Algorithm[from.getAlgorithm().ordinal()];
            if (i2 == 1) {
                str = "RS256";
            } else if (i2 == 2) {
                str = "RS384";
            } else {
                if (i2 != 3) {
                    throw new GeneralSecurityException("unknown algorithm");
                }
                str = "RS512";
            }
            e eVar = new e();
            eVar.l("kty", "RSA");
            eVar.l("n", Base64.urlSafeEncode(from.getN().toByteArray()));
            eVar.l("e", Base64.urlSafeEncode(from.getE().toByteArray()));
            eVar.l("use", "sig");
            eVar.l("alg", str);
            a aVar = new a();
            aVar.k("verify");
            eVar.j("key_ops", aVar);
            Optional<String> kid = getKid(protoKeySerialization.getIdRequirementOrNull());
            if (!kid.isPresent()) {
                if (from.hasCustomKid()) {
                    value = from.getCustomKid().getValue();
                }
                return eVar;
            }
            value = (String) kid.get();
            eVar.l("kid", value);
            return eVar;
        } catch (InvalidProtocolBufferException e2) {
            throw new GeneralSecurityException("failed to parse value as JwtRsaSsaPkcs1PublicKey proto", e2);
        }
    }

    private static e convertJwtRsaSsaPss(ProtoKeySerialization protoKeySerialization) throws GeneralSecurityException {
        String str;
        String value;
        try {
            JwtRsaSsaPssPublicKey from = JwtRsaSsaPssPublicKey.parseFrom(protoKeySerialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
            int i2 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$JwtRsaSsaPssAlgorithm[from.getAlgorithm().ordinal()];
            if (i2 == 1) {
                str = "PS256";
            } else if (i2 == 2) {
                str = "PS384";
            } else {
                if (i2 != 3) {
                    throw new GeneralSecurityException("unknown algorithm");
                }
                str = "PS512";
            }
            e eVar = new e();
            eVar.l("kty", "RSA");
            eVar.l("n", Base64.urlSafeEncode(from.getN().toByteArray()));
            eVar.l("e", Base64.urlSafeEncode(from.getE().toByteArray()));
            eVar.l("use", "sig");
            eVar.l("alg", str);
            a aVar = new a();
            aVar.k("verify");
            eVar.j("key_ops", aVar);
            Optional<String> kid = getKid(protoKeySerialization.getIdRequirementOrNull());
            if (!kid.isPresent()) {
                if (from.hasCustomKid()) {
                    value = from.getCustomKid().getValue();
                }
                return eVar;
            }
            value = (String) kid.get();
            eVar.l("kid", value);
            return eVar;
        } catch (InvalidProtocolBufferException e2) {
            throw new GeneralSecurityException("failed to parse value as JwtRsaSsaPssPublicKey proto", e2);
        }
    }

    private static ProtoKeySerialization convertToEcdsaKey(e eVar) throws GeneralSecurityException {
        JwtEcdsaAlgorithm jwtEcdsaAlgorithm;
        String stringItem = getStringItem(eVar, "alg");
        stringItem.getClass();
        switch (stringItem) {
            case "ES256":
                expectStringItem(eVar, "crv", "P-256");
                jwtEcdsaAlgorithm = JwtEcdsaAlgorithm.ES256;
                break;
            case "ES384":
                expectStringItem(eVar, "crv", "P-384");
                jwtEcdsaAlgorithm = JwtEcdsaAlgorithm.ES384;
                break;
            case "ES512":
                expectStringItem(eVar, "crv", "P-521");
                jwtEcdsaAlgorithm = JwtEcdsaAlgorithm.ES512;
                break;
            default:
                throw new GeneralSecurityException("Unknown Ecdsa Algorithm: " + getStringItem(eVar, "alg"));
        }
        if (eVar.o("d")) {
            throw new UnsupportedOperationException("importing ECDSA private keys is not implemented");
        }
        expectStringItem(eVar, "kty", "EC");
        validateUseIsSig(eVar);
        validateKeyOpsIsVerify(eVar);
        JwtEcdsaPublicKey.Builder y2 = JwtEcdsaPublicKey.newBuilder().setVersion(0).setAlgorithm(jwtEcdsaAlgorithm).setX(ByteString.copyFrom(Base64.urlSafeDecode(getStringItem(eVar, "x")))).setY(ByteString.copyFrom(Base64.urlSafeDecode(getStringItem(eVar, "y"))));
        if (eVar.o("kid")) {
            y2.setCustomKid(JwtEcdsaPublicKey.CustomKid.newBuilder().setValue(getStringItem(eVar, "kid")).build());
        }
        return ProtoKeySerialization.create(JWT_ECDSA_PUBLIC_KEY_URL, y2.build().toByteString(), KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC, OutputPrefixType.RAW, null);
    }

    private static ProtoKeySerialization convertToRsaSsaPkcs1Key(e eVar) throws GeneralSecurityException {
        JwtRsaSsaPkcs1Algorithm jwtRsaSsaPkcs1Algorithm;
        String stringItem = getStringItem(eVar, "alg");
        stringItem.getClass();
        switch (stringItem) {
            case "RS256":
                jwtRsaSsaPkcs1Algorithm = JwtRsaSsaPkcs1Algorithm.RS256;
                break;
            case "RS384":
                jwtRsaSsaPkcs1Algorithm = JwtRsaSsaPkcs1Algorithm.RS384;
                break;
            case "RS512":
                jwtRsaSsaPkcs1Algorithm = JwtRsaSsaPkcs1Algorithm.RS512;
                break;
            default:
                throw new GeneralSecurityException("Unknown Rsa Algorithm: " + getStringItem(eVar, "alg"));
        }
        if (eVar.o("p") || eVar.o("q") || eVar.o("dp") || eVar.o("dq") || eVar.o("d") || eVar.o("qi")) {
            throw new UnsupportedOperationException("importing RSA private keys is not implemented");
        }
        expectStringItem(eVar, "kty", "RSA");
        validateUseIsSig(eVar);
        validateKeyOpsIsVerify(eVar);
        JwtRsaSsaPkcs1PublicKey.Builder n2 = JwtRsaSsaPkcs1PublicKey.newBuilder().setVersion(0).setAlgorithm(jwtRsaSsaPkcs1Algorithm).setE(ByteString.copyFrom(Base64.urlSafeDecode(getStringItem(eVar, "e")))).setN(ByteString.copyFrom(Base64.urlSafeDecode(getStringItem(eVar, "n"))));
        if (eVar.o("kid")) {
            n2.setCustomKid(JwtRsaSsaPkcs1PublicKey.CustomKid.newBuilder().setValue(getStringItem(eVar, "kid")).build());
        }
        return ProtoKeySerialization.create(JWT_RSA_SSA_PKCS1_PUBLIC_KEY_URL, n2.build().toByteString(), KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC, OutputPrefixType.RAW, null);
    }

    private static ProtoKeySerialization convertToRsaSsaPssKey(e eVar) throws GeneralSecurityException {
        JwtRsaSsaPssAlgorithm jwtRsaSsaPssAlgorithm;
        String stringItem = getStringItem(eVar, "alg");
        stringItem.getClass();
        switch (stringItem) {
            case "PS256":
                jwtRsaSsaPssAlgorithm = JwtRsaSsaPssAlgorithm.PS256;
                break;
            case "PS384":
                jwtRsaSsaPssAlgorithm = JwtRsaSsaPssAlgorithm.PS384;
                break;
            case "PS512":
                jwtRsaSsaPssAlgorithm = JwtRsaSsaPssAlgorithm.PS512;
                break;
            default:
                throw new GeneralSecurityException("Unknown Rsa Algorithm: " + getStringItem(eVar, "alg"));
        }
        if (eVar.o("p") || eVar.o("q") || eVar.o("dq") || eVar.o("dq") || eVar.o("d") || eVar.o("qi")) {
            throw new UnsupportedOperationException("importing RSA private keys is not implemented");
        }
        expectStringItem(eVar, "kty", "RSA");
        validateUseIsSig(eVar);
        validateKeyOpsIsVerify(eVar);
        JwtRsaSsaPssPublicKey.Builder n2 = JwtRsaSsaPssPublicKey.newBuilder().setVersion(0).setAlgorithm(jwtRsaSsaPssAlgorithm).setE(ByteString.copyFrom(Base64.urlSafeDecode(getStringItem(eVar, "e")))).setN(ByteString.copyFrom(Base64.urlSafeDecode(getStringItem(eVar, "n"))));
        if (eVar.o("kid")) {
            n2.setCustomKid(JwtRsaSsaPssPublicKey.CustomKid.newBuilder().setValue(getStringItem(eVar, "kid")).build());
        }
        return ProtoKeySerialization.create(JWT_RSA_SSA_PSS_PUBLIC_KEY_URL, n2.build().toByteString(), KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC, OutputPrefixType.RAW, null);
    }

    private static void expectStringItem(e eVar, String str, String str2) throws GeneralSecurityException {
        String stringItem = getStringItem(eVar, str);
        if (stringItem.equals(str2)) {
            return;
        }
        throw new GeneralSecurityException("unexpected " + str + " value: " + stringItem);
    }

    @InlineMe(imports = {"com.google.crypto.tink.jwt.JwkSetConverter"}, replacement = "JwkSetConverter.fromPublicKeysetHandle(handle)")
    @Deprecated
    public static String fromKeysetHandle(KeysetHandle keysetHandle, KeyAccess keyAccess) {
        return fromPublicKeysetHandle(keysetHandle);
    }

    public static String fromPublicKeysetHandle(KeysetHandle keysetHandle) throws GeneralSecurityException {
        e eVarConvertJwtEcdsaKey;
        a aVar = new a();
        for (int i2 = 0; i2 < keysetHandle.size(); i2++) {
            KeysetHandle.Entry at = keysetHandle.getAt(i2);
            if (at.getStatus() == KeyStatus.ENABLED) {
                Key key = at.getKey();
                if (!(key instanceof LegacyProtoKey)) {
                    throw new GeneralSecurityException("only LegacyProtoKey is currently supported");
                }
                ProtoKeySerialization serialization = ((LegacyProtoKey) key).getSerialization(null);
                if (serialization.getOutputPrefixType() != OutputPrefixType.RAW && serialization.getOutputPrefixType() != OutputPrefixType.TINK) {
                    throw new GeneralSecurityException("only OutputPrefixType RAW and TINK are supported");
                }
                if (serialization.getKeyMaterialType() != KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC) {
                    throw new GeneralSecurityException("only public keys can be converted");
                }
                String typeUrl = serialization.getTypeUrl();
                typeUrl.getClass();
                switch (typeUrl) {
                    case "type.googleapis.com/google.crypto.tink.JwtEcdsaPublicKey":
                        eVarConvertJwtEcdsaKey = convertJwtEcdsaKey(serialization);
                        break;
                    case "type.googleapis.com/google.crypto.tink.JwtRsaSsaPkcs1PublicKey":
                        eVarConvertJwtEcdsaKey = convertJwtRsaSsaPkcs1(serialization);
                        break;
                    case "type.googleapis.com/google.crypto.tink.JwtRsaSsaPssPublicKey":
                        eVarConvertJwtEcdsaKey = convertJwtRsaSsaPss(serialization);
                        break;
                    default:
                        throw new GeneralSecurityException(String.format("key type %s is not supported", serialization.getTypeUrl()));
                }
                aVar.j(eVarConvertJwtEcdsaKey);
            }
        }
        e eVar = new e();
        eVar.j("keys", aVar);
        return eVar.toString();
    }

    private static Optional<String> getKid(@Nullable Integer num) {
        return num == null ? Optional.empty() : Optional.of(Base64.urlSafeEncode(ByteBuffer.allocate(4).putInt(num.intValue()).array()));
    }

    private static String getStringItem(e eVar, String str) throws GeneralSecurityException {
        if (!eVar.o(str)) {
            throw new GeneralSecurityException(str + " not found");
        }
        b bVarN = eVar.n(str);
        bVarN.getClass();
        if ((bVarN instanceof g) && (eVar.n(str).f().f1140a instanceof String)) {
            return eVar.n(str).i();
        }
        throw new GeneralSecurityException(str + " is not a string");
    }

    @InlineMe(imports = {"com.google.crypto.tink.jwt.JwkSetConverter"}, replacement = "JwkSetConverter.toPublicKeysetHandle(jwkSet)")
    @Deprecated
    public static KeysetHandle toKeysetHandle(String str, KeyAccess keyAccess) {
        return toPublicKeysetHandle(str);
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0088  */
    /* JADX WARN: Code duplicated, block: B:24:0x008d  */
    /* JADX WARN: Code duplicated, block: B:25:0x0092  */
    /* JADX WARN: Code duplicated, block: B:43:0x0070 A[SYNTHETIC] */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static KeysetHandle toPublicKeysetHandle(String str) throws GeneralSecurityException {
        ProtoKeySerialization protoKeySerializationConvertToEcdsaKey;
        try {
            l0.a aVar = new l0.a(new StringReader(str));
            aVar.f1561b = false;
            e eVarE = i.U(aVar).e();
            KeysetHandle.Builder builderNewBuilder = KeysetHandle.newBuilder();
            Iterator it = eVarE.n("keys").d().iterator();
            while (it.hasNext()) {
                e eVarE2 = ((b) it.next()).e();
                byte b2 = 2;
                String strSubstring = getStringItem(eVarE2, "alg").substring(0, 2);
                strSubstring.getClass();
                switch (strSubstring.hashCode()) {
                    case 2222:
                        if (strSubstring.equals("ES")) {
                            b2 = 0;
                        }
                        switch (b2) {
                            case 0:
                                protoKeySerializationConvertToEcdsaKey = convertToEcdsaKey(eVarE2);
                                break;
                            case 1:
                                protoKeySerializationConvertToEcdsaKey = convertToRsaSsaPssKey(eVarE2);
                                break;
                            case 2:
                                protoKeySerializationConvertToEcdsaKey = convertToRsaSsaPkcs1Key(eVarE2);
                                break;
                            default:
                                throw new GeneralSecurityException("unexpected alg value: " + getStringItem(eVarE2, "alg"));
                        }
                        builderNewBuilder.addEntry(KeysetHandle.importKey(new LegacyProtoKey(protoKeySerializationConvertToEcdsaKey, null)).withRandomId());
                        break;
                    case 2563:
                        if (strSubstring.equals("PS")) {
                            b2 = 1;
                        }
                        switch (b2) {
                            case 0:
                                protoKeySerializationConvertToEcdsaKey = convertToEcdsaKey(eVarE2);
                                break;
                            case 1:
                                protoKeySerializationConvertToEcdsaKey = convertToRsaSsaPssKey(eVarE2);
                                break;
                            case 2:
                                protoKeySerializationConvertToEcdsaKey = convertToRsaSsaPkcs1Key(eVarE2);
                                break;
                            default:
                                throw new GeneralSecurityException("unexpected alg value: " + getStringItem(eVarE2, "alg"));
                        }
                        builderNewBuilder.addEntry(KeysetHandle.importKey(new LegacyProtoKey(protoKeySerializationConvertToEcdsaKey, null)).withRandomId());
                        break;
                    case 2625:
                        if (!strSubstring.equals("RS")) {
                        }
                        switch (b2) {
                            case 0:
                                protoKeySerializationConvertToEcdsaKey = convertToEcdsaKey(eVarE2);
                                break;
                            case 1:
                                protoKeySerializationConvertToEcdsaKey = convertToRsaSsaPssKey(eVarE2);
                                break;
                            case 2:
                                protoKeySerializationConvertToEcdsaKey = convertToRsaSsaPkcs1Key(eVarE2);
                                break;
                            default:
                                throw new GeneralSecurityException("unexpected alg value: " + getStringItem(eVarE2, "alg"));
                        }
                        builderNewBuilder.addEntry(KeysetHandle.importKey(new LegacyProtoKey(protoKeySerializationConvertToEcdsaKey, null)).withRandomId());
                        break;
                }
                b2 = -1;
                switch (b2) {
                    case 0:
                        protoKeySerializationConvertToEcdsaKey = convertToEcdsaKey(eVarE2);
                        break;
                    case 1:
                        protoKeySerializationConvertToEcdsaKey = convertToRsaSsaPssKey(eVarE2);
                        break;
                    case 2:
                        protoKeySerializationConvertToEcdsaKey = convertToRsaSsaPkcs1Key(eVarE2);
                        break;
                    default:
                        throw new GeneralSecurityException("unexpected alg value: " + getStringItem(eVarE2, "alg"));
                }
                builderNewBuilder.addEntry(KeysetHandle.importKey(new LegacyProtoKey(protoKeySerializationConvertToEcdsaKey, null)).withRandomId());
            }
            if (builderNewBuilder.size() <= 0) {
                throw new GeneralSecurityException("empty keyset");
            }
            builderNewBuilder.getAt(0).makePrimary();
            return builderNewBuilder.build();
        } catch (f | IllegalStateException | StackOverflowError e2) {
            throw new GeneralSecurityException("JWK set is invalid JSON", e2);
        }
    }

    private static void validateKeyOpsIsVerify(e eVar) throws GeneralSecurityException {
        if (eVar.o("key_ops")) {
            b bVarN = eVar.n("key_ops");
            bVarN.getClass();
            if (!(bVarN instanceof a)) {
                throw new GeneralSecurityException("key_ops is not an array");
            }
            a aVarD = eVar.n("key_ops").d();
            if (aVarD.size() != 1) {
                throw new GeneralSecurityException("key_ops must contain exactly one element");
            }
            b bVarL = aVarD.l(0);
            bVarL.getClass();
            if (!(bVarL instanceof g) || !(aVarD.l(0).f().f1140a instanceof String)) {
                throw new GeneralSecurityException("key_ops is not a string");
            }
            if (aVarD.l(0).i().equals("verify")) {
                return;
            }
            throw new GeneralSecurityException("unexpected keyOps value: " + aVarD.l(0).i());
        }
    }

    private static void validateUseIsSig(e eVar) throws GeneralSecurityException {
        if (eVar.o("use")) {
            expectStringItem(eVar, "use", "sig");
        }
    }
}
