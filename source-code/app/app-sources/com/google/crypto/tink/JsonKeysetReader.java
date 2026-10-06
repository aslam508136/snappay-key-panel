package com.google.crypto.tink;

import com.google.crypto.tink.internal.JsonParser;
import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.proto.KeysetInfo;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.subtle.Base64;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.InlineMe;
import i0.b;
import i0.e;
import i0.f;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Path;

/* JADX INFO: loaded from: classes.dex */
public final class JsonKeysetReader implements KeysetReader {
    private static final long MAX_KEY_ID = 4294967295L;
    private static final long MIN_KEY_ID = -2147483648L;
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final InputStream inputStream;
    private boolean urlSafeBase64 = false;

    private JsonKeysetReader(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    private EncryptedKeyset encryptedKeysetFromJson(e eVar) {
        validateEncryptedKeyset(eVar);
        byte[] bArrUrlSafeDecode = this.urlSafeBase64 ? Base64.urlSafeDecode(eVar.n("encryptedKeyset").i()) : Base64.decode(eVar.n("encryptedKeyset").i());
        return (eVar.o("keysetInfo") ? EncryptedKeyset.newBuilder().setEncryptedKeyset(ByteString.copyFrom(bArrUrlSafeDecode)).setKeysetInfo(keysetInfoFromJson((e) eVar.f1139a.get("keysetInfo"))) : EncryptedKeyset.newBuilder().setEncryptedKeyset(ByteString.copyFrom(bArrUrlSafeDecode))).build();
    }

    private static int getKeyId(b bVar) throws IOException {
        try {
            long parsedNumberAsLongOrThrow = JsonParser.getParsedNumberAsLongOrThrow(bVar);
            if (parsedNumberAsLongOrThrow > MAX_KEY_ID || parsedNumberAsLongOrThrow < MIN_KEY_ID) {
                throw new IOException("invalid key id");
            }
            return (int) bVar.g();
        } catch (NumberFormatException e2) {
            throw new IOException(e2);
        }
    }

    private static KeyData.KeyMaterialType getKeyMaterialType(String str) {
        str.getClass();
        switch (str) {
            case "REMOTE":
                return KeyData.KeyMaterialType.REMOTE;
            case "SYMMETRIC":
                return KeyData.KeyMaterialType.SYMMETRIC;
            case "ASYMMETRIC_PRIVATE":
                return KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE;
            case "ASYMMETRIC_PUBLIC":
                return KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC;
            default:
                throw new f("unknown key material type: ".concat(str));
        }
    }

    private static OutputPrefixType getOutputPrefixType(String str) {
        str.getClass();
        switch (str) {
            case "LEGACY":
                return OutputPrefixType.LEGACY;
            case "RAW":
                return OutputPrefixType.RAW;
            case "TINK":
                return OutputPrefixType.TINK;
            case "CRUNCHY":
                return OutputPrefixType.CRUNCHY;
            default:
                throw new f("unknown output prefix type: ".concat(str));
        }
    }

    private static KeyStatusType getStatus(String str) {
        str.getClass();
        switch (str) {
            case "ENABLED":
                return KeyStatusType.ENABLED;
            case "DESTROYED":
                return KeyStatusType.DESTROYED;
            case "DISABLED":
                return KeyStatusType.DISABLED;
            default:
                throw new f("unknown status: ".concat(str));
        }
    }

    private KeyData keyDataFromJson(e eVar) {
        validateKeyData(eVar);
        return KeyData.newBuilder().setTypeUrl(eVar.n("typeUrl").i()).setValue(ByteString.copyFrom(this.urlSafeBase64 ? Base64.urlSafeDecode(eVar.n("value").i()) : Base64.decode(eVar.n("value").i()))).setKeyMaterialType(getKeyMaterialType(eVar.n("keyMaterialType").i())).build();
    }

    private Keyset.Key keyFromJson(e eVar) {
        validateKey(eVar);
        return Keyset.Key.newBuilder().setStatus(getStatus(eVar.n("status").i())).setKeyId(getKeyId(eVar.n("keyId"))).setOutputPrefixType(getOutputPrefixType(eVar.n("outputPrefixType").i())).setKeyData(keyDataFromJson((e) eVar.f1139a.get("keyData"))).build();
    }

    private static KeysetInfo.KeyInfo keyInfoFromJson(e eVar) {
        return KeysetInfo.KeyInfo.newBuilder().setStatus(getStatus(eVar.n("status").i())).setKeyId(getKeyId(eVar.n("keyId"))).setOutputPrefixType(getOutputPrefixType(eVar.n("outputPrefixType").i())).setTypeUrl(eVar.n("typeUrl").i()).build();
    }

    private Keyset keysetFromJson(e eVar) {
        validateKeyset(eVar);
        Keyset.Builder builderNewBuilder = Keyset.newBuilder();
        if (eVar.o("primaryKeyId")) {
            builderNewBuilder.setPrimaryKeyId(getKeyId(eVar.n("primaryKeyId")));
        }
        i0.a aVar = (i0.a) eVar.f1139a.get("key");
        for (int i2 = 0; i2 < aVar.size(); i2++) {
            builderNewBuilder.addKey(keyFromJson(aVar.l(i2).e()));
        }
        return builderNewBuilder.build();
    }

    private static KeysetInfo keysetInfoFromJson(e eVar) {
        KeysetInfo.Builder builderNewBuilder = KeysetInfo.newBuilder();
        if (eVar.o("primaryKeyId")) {
            builderNewBuilder.setPrimaryKeyId(getKeyId(eVar.n("primaryKeyId")));
        }
        if (eVar.o("keyInfo")) {
            i0.a aVar = (i0.a) eVar.f1139a.get("keyInfo");
            for (int i2 = 0; i2 < aVar.size(); i2++) {
                builderNewBuilder.addKeyInfo(keyInfoFromJson(aVar.l(i2).e()));
            }
        }
        return builderNewBuilder.build();
    }

    private static void validateEncryptedKeyset(e eVar) {
        if (!eVar.o("encryptedKeyset")) {
            throw new f("invalid encrypted keyset");
        }
    }

    private static void validateKey(e eVar) {
        if (!eVar.o("keyData") || !eVar.o("status") || !eVar.o("keyId") || !eVar.o("outputPrefixType")) {
            throw new f("invalid key");
        }
    }

    private static void validateKeyData(e eVar) {
        if (!eVar.o("typeUrl") || !eVar.o("value") || !eVar.o("keyMaterialType")) {
            throw new f("invalid keyData");
        }
    }

    private static void validateKeyset(e eVar) {
        if (!eVar.o("key") || ((i0.a) eVar.f1139a.get("key")).size() == 0) {
            throw new f("invalid keyset");
        }
    }

    public static JsonKeysetReader withBytes(byte[] bArr) {
        return new JsonKeysetReader(new ByteArrayInputStream(bArr));
    }

    @InlineMe(imports = {"com.google.crypto.tink.JsonKeysetReader", "java.io.FileInputStream"}, replacement = "JsonKeysetReader.withInputStream(new FileInputStream(file))")
    @Deprecated
    public static JsonKeysetReader withFile(File file) {
        return withInputStream(new FileInputStream(file));
    }

    public static JsonKeysetReader withInputStream(InputStream inputStream) {
        return new JsonKeysetReader(inputStream);
    }

    @InlineMe(imports = {"com.google.crypto.tink.JsonKeysetReader"}, replacement = "JsonKeysetReader.withString(input.toString())")
    @Deprecated
    public static JsonKeysetReader withJsonObject(Object obj) {
        return withString(obj.toString());
    }

    @InlineMe(imports = {"com.google.crypto.tink.JsonKeysetReader", "java.io.File", "java.io.FileInputStream"}, replacement = "JsonKeysetReader.withInputStream(new FileInputStream(new File(path)))")
    @Deprecated
    public static JsonKeysetReader withPath(String str) {
        return withInputStream(new FileInputStream(new File(str)));
    }

    public static JsonKeysetReader withString(String str) {
        return new JsonKeysetReader(new ByteArrayInputStream(str.getBytes(UTF_8)));
    }

    @Override // com.google.crypto.tink.KeysetReader
    public Keyset read() throws IOException {
        try {
            try {
                Keyset keysetKeysetFromJson = keysetFromJson(JsonParser.parse(new String(Util.readAll(this.inputStream), UTF_8)).e());
                InputStream inputStream = this.inputStream;
                if (inputStream != null) {
                    inputStream.close();
                }
                return keysetKeysetFromJson;
            } catch (Throwable th) {
                InputStream inputStream2 = this.inputStream;
                if (inputStream2 != null) {
                    inputStream2.close();
                }
                throw th;
            }
        } catch (f | IllegalStateException e2) {
            throw new IOException(e2);
        }
    }

    @Override // com.google.crypto.tink.KeysetReader
    public EncryptedKeyset readEncrypted() throws IOException {
        try {
            try {
                EncryptedKeyset encryptedKeysetEncryptedKeysetFromJson = encryptedKeysetFromJson(JsonParser.parse(new String(Util.readAll(this.inputStream), UTF_8)).e());
                InputStream inputStream = this.inputStream;
                if (inputStream != null) {
                    inputStream.close();
                }
                return encryptedKeysetEncryptedKeysetFromJson;
            } catch (Throwable th) {
                InputStream inputStream2 = this.inputStream;
                if (inputStream2 != null) {
                    inputStream2.close();
                }
                throw th;
            }
        } catch (f | IllegalStateException e2) {
            throw new IOException(e2);
        }
    }

    @CanIgnoreReturnValue
    public JsonKeysetReader withUrlSafeBase64() {
        this.urlSafeBase64 = true;
        return this;
    }

    @InlineMe(imports = {"com.google.crypto.tink.JsonKeysetReader", "java.io.FileInputStream"}, replacement = "JsonKeysetReader.withInputStream(new FileInputStream(path.toFile()))")
    @Deprecated
    public static JsonKeysetReader withPath(Path path) {
        return withInputStream(new FileInputStream(path.toFile()));
    }
}
