package com.google.crypto.tink;

import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.Keyset;

/* JADX INFO: loaded from: classes.dex */
public interface KeysetWriter {
    void write(EncryptedKeyset encryptedKeyset);

    void write(Keyset keyset);
}
