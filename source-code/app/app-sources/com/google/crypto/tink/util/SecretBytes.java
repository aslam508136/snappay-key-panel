package com.google.crypto.tink.util;

import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.annotations.Alpha;
import com.google.crypto.tink.subtle.Random;
import com.google.errorprone.annotations.Immutable;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
@Immutable
@Alpha
public final class SecretBytes {
    private final Bytes bytes;

    private SecretBytes(Bytes bytes) {
        this.bytes = bytes;
    }

    public static SecretBytes copyFrom(byte[] bArr, SecretKeyAccess secretKeyAccess) {
        if (secretKeyAccess != null) {
            return new SecretBytes(Bytes.copyFrom(bArr));
        }
        throw new NullPointerException("SecretKeyAccess required");
    }

    public static SecretBytes randomBytes(int i2) {
        return new SecretBytes(Bytes.copyFrom(Random.randBytes(i2)));
    }

    public boolean equalsSecretBytes(SecretBytes secretBytes) {
        return MessageDigest.isEqual(this.bytes.toByteArray(), secretBytes.bytes.toByteArray());
    }

    public int size() {
        return this.bytes.size();
    }

    public byte[] toByteArray(SecretKeyAccess secretKeyAccess) {
        if (secretKeyAccess != null) {
            return this.bytes.toByteArray();
        }
        throw new NullPointerException("SecretKeyAccess required");
    }
}
