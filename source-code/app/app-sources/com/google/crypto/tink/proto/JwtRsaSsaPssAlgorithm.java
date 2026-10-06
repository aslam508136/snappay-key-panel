package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.Internal;

/* JADX INFO: loaded from: classes.dex */
public enum JwtRsaSsaPssAlgorithm implements Internal.EnumLite {
    PS_UNKNOWN(0),
    PS256(1),
    PS384(2),
    PS512(3),
    UNRECOGNIZED(-1);

    public static final int PS256_VALUE = 1;
    public static final int PS384_VALUE = 2;
    public static final int PS512_VALUE = 3;
    public static final int PS_UNKNOWN_VALUE = 0;
    private static final Internal.EnumLiteMap<JwtRsaSsaPssAlgorithm> internalValueMap = new Internal.EnumLiteMap<JwtRsaSsaPssAlgorithm>() { // from class: com.google.crypto.tink.proto.JwtRsaSsaPssAlgorithm.1
        @Override // com.google.crypto.tink.shaded.protobuf.Internal.EnumLiteMap
        public JwtRsaSsaPssAlgorithm findValueByNumber(int i2) {
            return JwtRsaSsaPssAlgorithm.forNumber(i2);
        }
    };
    private final int value;

    public static final class JwtRsaSsaPssAlgorithmVerifier implements Internal.EnumVerifier {
        static final Internal.EnumVerifier INSTANCE = new JwtRsaSsaPssAlgorithmVerifier();

        private JwtRsaSsaPssAlgorithmVerifier() {
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Internal.EnumVerifier
        public boolean isInRange(int i2) {
            return JwtRsaSsaPssAlgorithm.forNumber(i2) != null;
        }
    }

    JwtRsaSsaPssAlgorithm(int i2) {
        this.value = i2;
    }

    public static JwtRsaSsaPssAlgorithm forNumber(int i2) {
        if (i2 == 0) {
            return PS_UNKNOWN;
        }
        if (i2 == 1) {
            return PS256;
        }
        if (i2 == 2) {
            return PS384;
        }
        if (i2 != 3) {
            return null;
        }
        return PS512;
    }

    public static Internal.EnumLiteMap<JwtRsaSsaPssAlgorithm> internalGetValueMap() {
        return internalValueMap;
    }

    public static Internal.EnumVerifier internalGetVerifier() {
        return JwtRsaSsaPssAlgorithmVerifier.INSTANCE;
    }

    @Deprecated
    public static JwtRsaSsaPssAlgorithm valueOf(int i2) {
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
