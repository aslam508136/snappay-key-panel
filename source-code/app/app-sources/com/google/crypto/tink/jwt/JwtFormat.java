package com.google.crypto.tink.jwt;

import androidx.activity.c;
import com.google.crypto.tink.internal.Util;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.subtle.Base64;
import i0.b;
import i0.e;
import i0.g;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Optional;

/* JADX INFO: loaded from: classes.dex */
final class JwtFormat {

    public static class Parts {
        String header;
        String payload;
        byte[] signatureOrMac;
        String unsignedCompact;

        public Parts(String str, byte[] bArr, String str2, String str3) {
            this.unsignedCompact = str;
            this.signatureOrMac = bArr;
            this.header = str2;
            this.payload = str3;
        }
    }

    private JwtFormat() {
    }

    public static String createHeader(String str, Optional<String> optional, Optional<String> optional2) throws InvalidAlgorithmParameterException {
        validateAlgorithm(str);
        e eVar = new e();
        if (optional2.isPresent()) {
            eVar.l("kid", (String) optional2.get());
        }
        eVar.l("alg", str);
        if (optional.isPresent()) {
            eVar.l("typ", (String) optional.get());
        }
        return Base64.urlSafeEncode(eVar.toString().getBytes(Util.UTF_8));
    }

    public static String createSignedCompact(String str, byte[] bArr) {
        return str + "." + encodeSignature(bArr);
    }

    public static String createUnsignedCompact(String str, Optional<String> optional, RawJwt rawJwt) {
        String jsonPayload = rawJwt.getJsonPayload();
        return createHeader(str, rawJwt.hasTypeHeader() ? Optional.of(rawJwt.getTypeHeader()) : Optional.empty(), optional) + "." + encodePayload(jsonPayload);
    }

    public static String decodeHeader(String str) throws JwtInvalidException {
        byte[] bArrStrictUrlSafeDecode = strictUrlSafeDecode(str);
        validateUtf8(bArrStrictUrlSafeDecode);
        return new String(bArrStrictUrlSafeDecode, Util.UTF_8);
    }

    public static String decodePayload(String str) throws JwtInvalidException {
        byte[] bArrStrictUrlSafeDecode = strictUrlSafeDecode(str);
        validateUtf8(bArrStrictUrlSafeDecode);
        return new String(bArrStrictUrlSafeDecode, Util.UTF_8);
    }

    public static byte[] decodeSignature(String str) {
        return strictUrlSafeDecode(str);
    }

    public static String encodePayload(String str) {
        return Base64.urlSafeEncode(str.getBytes(Util.UTF_8));
    }

    public static String encodeSignature(byte[] bArr) {
        return Base64.urlSafeEncode(bArr);
    }

    public static Optional<Integer> getKeyId(String str) {
        byte[] bArrUrlSafeDecode = Base64.urlSafeDecode(str);
        return bArrUrlSafeDecode.length != 4 ? Optional.empty() : Optional.of(Integer.valueOf(ByteBuffer.wrap(bArrUrlSafeDecode).getInt()));
    }

    public static Optional<String> getKid(int i2, OutputPrefixType outputPrefixType) throws JwtInvalidException {
        if (outputPrefixType == OutputPrefixType.RAW) {
            return Optional.empty();
        }
        if (outputPrefixType == OutputPrefixType.TINK) {
            return Optional.of(Base64.urlSafeEncode(ByteBuffer.allocate(4).putInt(i2).array()));
        }
        throw new JwtInvalidException("unsupported output prefix type");
    }

    private static String getStringHeader(e eVar, String str) throws JwtInvalidException {
        if (!eVar.o(str)) {
            throw new JwtInvalidException(c.a("header ", str, " does not exist"));
        }
        b bVarN = eVar.n(str);
        bVarN.getClass();
        if ((bVarN instanceof g) && (eVar.n(str).f().f1140a instanceof String)) {
            return eVar.n(str).i();
        }
        throw new JwtInvalidException(c.a("header ", str, " is not a string"));
    }

    public static Optional<String> getTypeHeader(e eVar) {
        return eVar.o("typ") ? Optional.of(getStringHeader(eVar, "typ")) : Optional.empty();
    }

    public static boolean isValidUrlsafeBase64Char(char c2) {
        return (c2 >= 'a' && c2 <= 'z') || (c2 >= 'A' && c2 <= 'Z') || ((c2 >= '0' && c2 <= '9') || c2 == '-' || c2 == '_');
    }

    public static Parts splitSignedCompact(String str) throws JwtInvalidException {
        validateASCII(str);
        int iLastIndexOf = str.lastIndexOf(46);
        if (iLastIndexOf < 0) {
            throw new JwtInvalidException("only tokens in JWS compact serialization format are supported");
        }
        String strSubstring = str.substring(0, iLastIndexOf);
        byte[] bArrDecodeSignature = decodeSignature(str.substring(iLastIndexOf + 1));
        int iIndexOf = strSubstring.indexOf(46);
        if (iIndexOf < 0) {
            throw new JwtInvalidException("only tokens in JWS compact serialization format are supported");
        }
        String strSubstring2 = strSubstring.substring(0, iIndexOf);
        String strSubstring3 = strSubstring.substring(iIndexOf + 1);
        if (strSubstring3.indexOf(46) <= 0) {
            return new Parts(strSubstring, bArrDecodeSignature, decodeHeader(strSubstring2), decodePayload(strSubstring3));
        }
        throw new JwtInvalidException("only tokens in JWS compact serialization format are supported");
    }

    public static byte[] strictUrlSafeDecode(String str) throws JwtInvalidException {
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (!isValidUrlsafeBase64Char(str.charAt(i2))) {
                throw new JwtInvalidException("invalid encoding");
            }
        }
        try {
            return Base64.urlSafeDecode(str);
        } catch (IllegalArgumentException e2) {
            throw new JwtInvalidException("invalid encoding: " + e2);
        }
    }

    public static void validateASCII(String str) throws JwtInvalidException {
        for (int i2 = 0; i2 < str.length(); i2++) {
            if ((str.charAt(i2) & 128) > 0) {
                throw new JwtInvalidException("Non ascii character");
            }
        }
    }

    private static void validateAlgorithm(String str) throws InvalidAlgorithmParameterException {
        str.getClass();
        switch (str) {
            case "ES256":
            case "ES384":
            case "ES512":
            case "HS256":
            case "HS384":
            case "HS512":
            case "PS256":
            case "PS384":
            case "PS512":
            case "RS256":
            case "RS384":
            case "RS512":
                return;
            default:
                throw new InvalidAlgorithmParameterException("invalid algorithm: ".concat(str));
        }
    }

    public static void validateHeader(String str, Optional<String> optional, Optional<String> optional2, e eVar) throws JwtInvalidException, InvalidAlgorithmParameterException {
        validateAlgorithm(str);
        String stringHeader = getStringHeader(eVar, "alg");
        if (!stringHeader.equals(str)) {
            throw new InvalidAlgorithmParameterException(String.format("invalid algorithm; expected %s, got %s", str, stringHeader));
        }
        if (eVar.o("crit")) {
            throw new JwtInvalidException("all tokens with crit headers are rejected");
        }
        if (optional.isPresent() && optional2.isPresent()) {
            throw new JwtInvalidException("custom_kid can only be set for RAW keys.");
        }
        boolean zO = eVar.o("kid");
        if (optional.isPresent()) {
            if (!zO) {
                throw new JwtInvalidException("missing kid in header");
            }
            validateKidInHeader((String) optional.get(), eVar);
        }
        if (optional2.isPresent() && zO) {
            validateKidInHeader((String) optional2.get(), eVar);
        }
    }

    private static void validateKidInHeader(String str, e eVar) throws JwtInvalidException {
        if (!getStringHeader(eVar, "kid").equals(str)) {
            throw new JwtInvalidException("invalid kid in header");
        }
    }

    public static void validateUtf8(byte[] bArr) throws JwtInvalidException {
        try {
            Util.UTF_8.newDecoder().decode(ByteBuffer.wrap(bArr));
        } catch (CharacterCodingException e2) {
            throw new JwtInvalidException(e2.getMessage());
        }
    }
}
