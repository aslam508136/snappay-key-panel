package com.google.crypto.tink;

import com.google.crypto.tink.annotations.Alpha;
import com.google.crypto.tink.monitoring.MonitoringAnnotations;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import java.security.GeneralSecurityException;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class CleartextKeysetHandle {
    private CleartextKeysetHandle() {
    }

    public static KeysetHandle fromKeyset(Keyset keyset) {
        return KeysetHandle.fromKeyset(keyset);
    }

    public static Keyset getKeyset(KeysetHandle keysetHandle) {
        return keysetHandle.getKeyset();
    }

    @Deprecated
    public static final KeysetHandle parseFrom(byte[] bArr) throws GeneralSecurityException {
        try {
            return KeysetHandle.fromKeyset(Keyset.parseFrom(bArr, ExtensionRegistryLite.getEmptyRegistry()));
        } catch (InvalidProtocolBufferException unused) {
            throw new GeneralSecurityException("invalid keyset");
        }
    }

    public static KeysetHandle read(KeysetReader keysetReader) {
        return KeysetHandle.fromKeyset(keysetReader.read());
    }

    public static void write(KeysetHandle keysetHandle, KeysetWriter keysetWriter) {
        keysetWriter.write(keysetHandle.getKeyset());
    }

    @Alpha
    public static KeysetHandle read(KeysetReader keysetReader, Map<String, String> map) {
        return KeysetHandle.fromKeysetAndAnnotations(keysetReader.read(), MonitoringAnnotations.newBuilder().addAll(map).build());
    }
}
