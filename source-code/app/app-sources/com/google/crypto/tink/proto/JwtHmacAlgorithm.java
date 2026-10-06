package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.Internal;

/* JADX INFO: loaded from: classes.dex */
public enum JwtHmacAlgorithm implements Internal.EnumLite {
    HS_UNKNOWN(0),
    HS256(1),
    HS384(2),
    HS512(3),
    UNRECOGNIZED(-1);

    public static final int HS256_VALUE = 1;
    public static final int HS384_VALUE = 2;
    public static final int HS512_VALUE = 3;
    public static final int HS_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<JwtHmacAlgorithm> internalValueMap = new Internal.EnumLiteMap<JwtHmacAlgorithm>() { // from class: com.google.crypto.tink.proto.JwtHmacAlgorithm.1
        @Override // com.google.crypto.tink.shaded.protobuf.Internal.EnumLiteMap
        public JwtHmacAlgorithm findValueByNumber(int i2) {
            return JwtHmacAlgorithm.forNumber(i2);
        }
    };
    private final int value;

    public static final class JwtHmacAlgorithmVerifier implements Internal.EnumVerifier {
        static final Internal.EnumVerifier INSTANCE = new JwtHmacAlgorithmVerifier();

        private JwtHmacAlgorithmVerifier() {
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Internal.EnumVerifier
        public boolean isInRange(int i2) {
            return JwtHmacAlgorithm.forNumber(i2) != null;
        }
    }

    JwtHmacAlgorithm(int i2) {
        this.value = i2;
    }

    public static JwtHmacAlgorithm forNumber(int i2) {
        if (i2 == 0) {
            return HS_UNKNOWN;
        }
        if (i2 == 1) {
            return HS256;
        }
        if (i2 == 2) {
            return HS384;
        }
        if (i2 != 3) {
            return null;
        }
        return HS512;
    }

    public static Internal.EnumLiteMap<JwtHmacAlgorithm> internalGetValueMap() {
        return internalValueMap;
    }

    public static Internal.EnumVerifier internalGetVerifier() {
        return JwtHmacAlgorithmVerifier.INSTANCE;
    }

    @Deprecated
    public static JwtHmacAlgorithm valueOf(int i2) {
        return forNumber(i2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Internal.EnumLite
    public final int getNumber() {
        if (this != UNRECOGNIZED) {
            return this.value;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }
}
