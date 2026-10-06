package com.google.crypto.tink.jwt;

import com.google.crypto.tink.internal.JsonParser;
import i0.a;
import i0.e;
import i0.f;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
final class JsonUtil {
    private JsonUtil() {
    }

    public static boolean isValidString(String str) {
        return JsonParser.isValidString(str);
    }

    public static e parseJson(String str) throws JwtInvalidException {
        try {
            return JsonParser.parse(str).e();
        } catch (f | IOException | IllegalStateException e2) {
            throw new JwtInvalidException("invalid JSON: " + e2);
        }
    }

    public static a parseJsonArray(String str) throws JwtInvalidException {
        try {
            return JsonParser.parse(str).d();
        } catch (f | IOException | IllegalStateException e2) {
            throw new JwtInvalidException("invalid JSON: " + e2);
        }
    }
}
