package com.google.crypto.tink;

import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class PrivilegedRegistry {
    private PrivilegedRegistry() {
    }

    public static KeyData deriveKey(com.google.crypto.tink.proto.KeyTemplate keyTemplate, InputStream inputStream) {
        return Registry.deriveKey(keyTemplate, inputStream);
    }

    public static MessageLite parseKeyData(KeyData keyData) {
        return Registry.parseKeyData(keyData);
    }
}
