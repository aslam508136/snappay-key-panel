package com.google.crypto.tink.subtle;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.Mac;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
public final class EncryptThenAuthenticate implements Aead {
    private final IndCpaCipher cipher;
    private final Mac mac;
    private final int macLength;

    public EncryptThenAuthenticate(IndCpaCipher indCpaCipher, Mac mac, int i2) {
        this.cipher = indCpaCipher;
        this.mac = mac;
        this.macLength = i2;
    }

    public static Aead newAesCtrHmac(byte[] bArr, int i2, String str, byte[] bArr2, int i3) {
        return new EncryptThenAuthenticate(new AesCtrJceCipher(bArr, i2), new PrfMac(new PrfHmacJce(str, new SecretKeySpec(bArr2, "HMAC")), i3), i3);
    }

    @Override // com.google.crypto.tink.Aead
    public byte[] decrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int length = bArr.length;
        int i2 = this.macLength;
        if (length < i2) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, 0, bArr.length - i2);
        byte[] bArrCopyOfRange2 = Arrays.copyOfRange(bArr, bArr.length - this.macLength, bArr.length);
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        this.mac.verifyMac(bArrCopyOfRange2, Bytes.concat(bArr2, bArrCopyOfRange, Arrays.copyOf(ByteBuffer.allocate(8).putLong(((long) bArr2.length) * 8).array(), 8)));
        return this.cipher.decrypt(bArrCopyOfRange);
    }

    @Override // com.google.crypto.tink.Aead
    public byte[] encrypt(byte[] bArr, byte[] bArr2) {
        byte[] bArrEncrypt = this.cipher.encrypt(bArr);
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        return Bytes.concat(bArrEncrypt, this.mac.computeMac(Bytes.concat(bArr2, bArrEncrypt, Arrays.copyOf(ByteBuffer.allocate(8).putLong(((long) bArr2.length) * 8).array(), 8))));
    }
}
