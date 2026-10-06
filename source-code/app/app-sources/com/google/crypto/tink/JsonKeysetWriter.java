package com.google.crypto.tink;

import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.proto.KeysetInfo;
import com.google.crypto.tink.subtle.Base64;
import com.google.errorprone.annotations.InlineMe;
import i0.e;
import i0.f;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class JsonKeysetWriter implements KeysetWriter {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final OutputStream outputStream;

    private JsonKeysetWriter(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    private e toJson(EncryptedKeyset encryptedKeyset) {
        e eVar = new e();
        eVar.l("encryptedKeyset", Base64.encode(encryptedKeyset.getEncryptedKeyset().toByteArray()));
        eVar.j("keysetInfo", toJson(encryptedKeyset.getKeysetInfo()));
        return eVar;
    }

    private long toUnsignedLong(int i2) {
        return ((long) i2) & 4294967295L;
    }

    @InlineMe(imports = {"com.google.crypto.tink.JsonKeysetWriter", "java.io.FileOutputStream"}, replacement = "JsonKeysetWriter.withOutputStream(new FileOutputStream(file))")
    @Deprecated
    public static KeysetWriter withFile(File file) {
        return withOutputStream(new FileOutputStream(file));
    }

    public static KeysetWriter withOutputStream(OutputStream outputStream) {
        return new JsonKeysetWriter(outputStream);
    }

    @InlineMe(imports = {"com.google.crypto.tink.JsonKeysetWriter", "java.io.File", "java.io.FileOutputStream"}, replacement = "JsonKeysetWriter.withOutputStream(new FileOutputStream(new File(path)))")
    @Deprecated
    public static KeysetWriter withPath(String str) {
        return withOutputStream(new FileOutputStream(new File(str)));
    }

    @Override // com.google.crypto.tink.KeysetWriter
    public void write(EncryptedKeyset encryptedKeyset) throws IOException {
        OutputStream outputStream = this.outputStream;
        String string = toJson(encryptedKeyset).toString();
        Charset charset = UTF_8;
        outputStream.write(string.getBytes(charset));
        this.outputStream.write(System.lineSeparator().getBytes(charset));
        this.outputStream.close();
    }

    private e toJson(KeyData keyData) {
        e eVar = new e();
        eVar.l("typeUrl", keyData.getTypeUrl());
        eVar.l("value", Base64.encode(keyData.getValue().toByteArray()));
        eVar.l("keyMaterialType", keyData.getKeyMaterialType().name());
        return eVar;
    }

    @InlineMe(imports = {"com.google.crypto.tink.JsonKeysetWriter", "java.io.FileOutputStream"}, replacement = "JsonKeysetWriter.withOutputStream(new FileOutputStream(path.toFile()))")
    @Deprecated
    public static KeysetWriter withPath(Path path) {
        return withOutputStream(new FileOutputStream(path.toFile()));
    }

    @Override // com.google.crypto.tink.KeysetWriter
    public void write(Keyset keyset) throws IOException {
        try {
            try {
                OutputStream outputStream = this.outputStream;
                String string = toJson(keyset).toString();
                Charset charset = UTF_8;
                outputStream.write(string.getBytes(charset));
                this.outputStream.write(System.lineSeparator().getBytes(charset));
                this.outputStream.close();
            } catch (f e2) {
                throw new IOException(e2);
            }
        } catch (Throwable th) {
            this.outputStream.close();
            throw th;
        }
    }

    private e toJson(Keyset.Key key) {
        e eVar = new e();
        eVar.j("keyData", toJson(key.getKeyData()));
        eVar.l("status", key.getStatus().name());
        eVar.k("keyId", Long.valueOf(toUnsignedLong(key.getKeyId())));
        eVar.l("outputPrefixType", key.getOutputPrefixType().name());
        return eVar;
    }

    private e toJson(Keyset keyset) {
        e eVar = new e();
        eVar.k("primaryKeyId", Long.valueOf(toUnsignedLong(keyset.getPrimaryKeyId())));
        i0.a aVar = new i0.a();
        Iterator<Keyset.Key> it = keyset.getKeyList().iterator();
        while (it.hasNext()) {
            aVar.j(toJson(it.next()));
        }
        eVar.j("key", aVar);
        return eVar;
    }

    private e toJson(KeysetInfo.KeyInfo keyInfo) {
        e eVar = new e();
        eVar.l("typeUrl", keyInfo.getTypeUrl());
        eVar.l("status", keyInfo.getStatus().name());
        eVar.k("keyId", Long.valueOf(toUnsignedLong(keyInfo.getKeyId())));
        eVar.l("outputPrefixType", keyInfo.getOutputPrefixType().name());
        return eVar;
    }

    private e toJson(KeysetInfo keysetInfo) {
        e eVar = new e();
        eVar.k("primaryKeyId", Long.valueOf(toUnsignedLong(keysetInfo.getPrimaryKeyId())));
        i0.a aVar = new i0.a();
        Iterator<KeysetInfo.KeyInfo> it = keysetInfo.getKeyInfoList().iterator();
        while (it.hasNext()) {
            aVar.j(toJson(it.next()));
        }
        eVar.j("keyInfo", aVar);
        return eVar;
    }
}
