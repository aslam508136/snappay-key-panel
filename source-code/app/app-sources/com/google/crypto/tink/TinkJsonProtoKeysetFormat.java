package com.google.crypto.tink;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes.dex */
public final class TinkJsonProtoKeysetFormat {
    private TinkJsonProtoKeysetFormat() {
    }

    public static KeysetHandle parseEncryptedKeyset(String str, Aead aead, byte[] bArr) throws GeneralSecurityException {
        try {
            return KeysetHandle.readWithAssociatedData(JsonKeysetReader.withString(str), aead, bArr);
        } catch (IOException unused) {
            throw new GeneralSecurityException("Parse keyset failed");
        }
    }

    public static KeysetHandle parseKeyset(String str, SecretKeyAccess secretKeyAccess) throws GeneralSecurityException {
        if (secretKeyAccess == null) {
            throw new NullPointerException("SecretKeyAccess cannot be null");
        }
        try {
            return CleartextKeysetHandle.read(JsonKeysetReader.withString(str));
        } catch (IOException unused) {
            throw new GeneralSecurityException("Parse keyset failed");
        }
    }

    public static KeysetHandle parseKeysetWithoutSecret(String str) throws GeneralSecurityException {
        try {
            return KeysetHandle.readNoSecret(JsonKeysetReader.withString(str));
        } catch (IOException unused) {
            throw new GeneralSecurityException("Parse keyset failed");
        }
    }

    public static String serializeEncryptedKeyset(KeysetHandle keysetHandle, Aead aead, byte[] bArr) throws GeneralSecurityException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            keysetHandle.writeWithAssociatedData(JsonKeysetWriter.withOutputStream(byteArrayOutputStream), aead, bArr);
            return new String(byteArrayOutputStream.toByteArray(), com.google.crypto.tink.internal.Util.UTF_8);
        } catch (IOException unused) {
            throw new GeneralSecurityException("Serialize keyset failed");
        }
    }

    public static String serializeKeyset(KeysetHandle keysetHandle, SecretKeyAccess secretKeyAccess) throws GeneralSecurityException {
        if (secretKeyAccess == null) {
            throw new NullPointerException("SecretKeyAccess cannot be null");
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            CleartextKeysetHandle.write(keysetHandle, JsonKeysetWriter.withOutputStream(byteArrayOutputStream));
            return new String(byteArrayOutputStream.toByteArray(), com.google.crypto.tink.internal.Util.UTF_8);
        } catch (IOException unused) {
            throw new GeneralSecurityException("Serialize keyset failed");
        }
    }

    public static String serializeKeysetWithoutSecret(KeysetHandle keysetHandle) throws GeneralSecurityException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            keysetHandle.writeNoSecret(JsonKeysetWriter.withOutputStream(byteArrayOutputStream));
            return new String(byteArrayOutputStream.toByteArray(), com.google.crypto.tink.internal.Util.UTF_8);
        } catch (IOException unused) {
            throw new GeneralSecurityException("Serialize keyset failed");
        }
    }
}
