package com.google.crypto.tink.hybrid.subtle;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.DeterministicAead;

/* JADX INFO: loaded from: classes.dex */
public class AeadOrDaead {
    private final Aead aead;
    private final DeterministicAead deterministicAead;

    public AeadOrDaead(Aead aead) {
        this.aead = aead;
        this.deterministicAead = null;
    }

    public byte[] decrypt(byte[] bArr, byte[] bArr2) {
        Aead aead = this.aead;
        return aead != null ? aead.decrypt(bArr, bArr2) : this.deterministicAead.decryptDeterministically(bArr, bArr2);
    }

    public byte[] encrypt(byte[] bArr, byte[] bArr2) {
        Aead aead = this.aead;
        return aead != null ? aead.encrypt(bArr, bArr2) : this.deterministicAead.encryptDeterministically(bArr, bArr2);
    }

    public AeadOrDaead(DeterministicAead deterministicAead) {
        this.aead = null;
        this.deterministicAead = deterministicAead;
    }
}
