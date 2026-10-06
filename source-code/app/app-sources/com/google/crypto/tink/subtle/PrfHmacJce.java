package com.google.crypto.tink.subtle;

import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.prf.Prf;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Mac;

/* JADX INFO: loaded from: classes.dex */
@Immutable
public final class PrfHmacJce implements Prf {
    public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    static final int MIN_KEY_SIZE_IN_BYTES = 16;
    private final String algorithm;
    private final Key key;
    private final ThreadLocal<Mac> localMac;
    private final int maxOutputLength;

    public PrfHmacJce(String str, Key key) throws GeneralSecurityException {
        int i2;
        ThreadLocal<Mac> threadLocal = new ThreadLocal<Mac>() { // from class: com.google.crypto.tink.subtle.PrfHmacJce.1
            @Override // java.lang.ThreadLocal
            public Mac initialValue() {
                try {
                    Mac engineFactory = EngineFactory.MAC.getInstance(PrfHmacJce.this.algorithm);
                    engineFactory.init(PrfHmacJce.this.key);
                    return engineFactory;
                } catch (GeneralSecurityException e2) {
                    throw new IllegalStateException(e2);
                }
            }
        };
        this.localMac = threadLocal;
        if (!FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use HMAC in FIPS-mode, as BoringCrypto module is not available.");
        }
        this.algorithm = str;
        this.key = key;
        if (key.getEncoded().length < 16) {
            throw new InvalidAlgorithmParameterException("key size too small, need at least 16 bytes");
        }
        str.getClass();
        switch (str) {
            case "HMACSHA1":
                i2 = 20;
                break;
            case "HMACSHA224":
                i2 = 28;
                break;
            case "HMACSHA256":
                i2 = 32;
                break;
            case "HMACSHA384":
                i2 = 48;
                break;
            case "HMACSHA512":
                i2 = 64;
                break;
            default:
                throw new NoSuchAlgorithmException("unknown Hmac algorithm: ".concat(str));
        }
        this.maxOutputLength = i2;
        threadLocal.get();
    }

    @Override // com.google.crypto.tink.prf.Prf
    public byte[] compute(byte[] bArr, int i2) throws InvalidAlgorithmParameterException {
        if (i2 > this.maxOutputLength) {
            throw new InvalidAlgorithmParameterException("tag size too big");
        }
        this.localMac.get().update(bArr);
        return Arrays.copyOf(this.localMac.get().doFinal(), i2);
    }

    public int getMaxOutputLength() {
        return this.maxOutputLength;
    }
}
