package com.google.crypto.tink.subtle;

import java.security.GeneralSecurityException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
public final class Hkdf {
    private Hkdf() {
    }

    public static byte[] computeEciesHkdfSymmetricKey(byte[] bArr, byte[] bArr2, String str, byte[] bArr3, byte[] bArr4, int i2) {
        return computeHkdf(str, Bytes.concat(bArr, bArr2), bArr3, bArr4, i2);
    }

    public static byte[] computeHkdf(String str, byte[] bArr, byte[] bArr2, byte[] bArr3, int i2) throws GeneralSecurityException {
        Mac engineFactory = EngineFactory.MAC.getInstance(str);
        if (i2 > engineFactory.getMacLength() * 255) {
            throw new GeneralSecurityException("size too large");
        }
        if (bArr2 == null || bArr2.length == 0) {
            engineFactory.init(new SecretKeySpec(new byte[engineFactory.getMacLength()], str));
        } else {
            engineFactory.init(new SecretKeySpec(bArr2, str));
        }
        byte[] bArr4 = new byte[i2];
        engineFactory.init(new SecretKeySpec(engineFactory.doFinal(bArr), str));
        byte[] bArrDoFinal = new byte[0];
        int i3 = 1;
        int length = 0;
        while (true) {
            engineFactory.update(bArrDoFinal);
            engineFactory.update(bArr3);
            engineFactory.update((byte) i3);
            bArrDoFinal = engineFactory.doFinal();
            if (bArrDoFinal.length + length >= i2) {
                System.arraycopy(bArrDoFinal, 0, bArr4, length, i2 - length);
                return bArr4;
            }
            System.arraycopy(bArrDoFinal, 0, bArr4, length, bArrDoFinal.length);
            length += bArrDoFinal.length;
            i3++;
        }
    }
}
