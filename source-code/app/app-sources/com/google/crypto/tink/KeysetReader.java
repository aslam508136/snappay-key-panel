package com.google.crypto.tink;

import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.Keyset;

/* JADX INFO: loaded from: classes.dex */
public interface KeysetReader {
    Keyset read();

    EncryptedKeyset readEncrypted();
}
