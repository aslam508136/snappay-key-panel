package com.google.crypto.tink.internal;

import com.google.crypto.tink.util.Bytes;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Objects;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class Util {
    public static final Charset UTF_8 = Charset.forName("UTF-8");

    private Util() {
    }

    @Nullable
    public static Integer getAndroidApiLevel() {
        if (isAndroid()) {
            return BuildDispatchedCode.getApiLevel();
        }
        return null;
    }

    public static boolean isAndroid() {
        return Objects.equals(System.getProperty("java.vendor"), "The Android Project");
    }

    public static int randKeyId() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bArr = new byte[4];
        int i2 = 0;
        while (i2 == 0) {
            secureRandom.nextBytes(bArr);
            i2 = ((bArr[0] & 127) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8) | (bArr[3] & 255);
        }
        return i2;
    }

    private static final byte toByteFromPrintableAscii(char c2) {
        if (c2 >= '!' && c2 <= '~') {
            return (byte) c2;
        }
        throw new TinkBugException("Not a printable ASCII character: " + c2);
    }

    public static final Bytes toBytesFromPrintableAscii(String str) {
        byte[] bArr = new byte[str.length()];
        for (int i2 = 0; i2 < str.length(); i2++) {
            bArr[i2] = toByteFromPrintableAscii(str.charAt(i2));
        }
        return Bytes.copyFrom(bArr);
    }
}
