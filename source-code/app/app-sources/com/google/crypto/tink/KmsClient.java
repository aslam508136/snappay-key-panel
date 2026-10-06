package com.google.crypto.tink;

/* JADX INFO: loaded from: classes.dex */
public interface KmsClient {
    boolean doesSupport(String str);

    Aead getAead(String str);

    KmsClient withCredentials(String str);

    KmsClient withDefaultCredentials();
}
