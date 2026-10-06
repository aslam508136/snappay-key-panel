package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.Internal;

/* JADX INFO: loaded from: classes.dex */
public enum EllipticCurveType implements Internal.EnumLite {
    UNKNOWN_CURVE(0),
    NIST_P256(2),
    NIST_P384(3),
    NIST_P521(4),
    CURVE25519(5),
    UNRECOGNIZED(-1);

    public static final int CURVE25519_VALUE = 5;
    public static final int NIST_P256_VALUE = 2;
    public static final int NIST_P384_VALUE = 3;
    public static final int NIST_P521_VALUE = 4;
    public static final int UNKNOWN_CURVE_VALUE = 0;
    private static final Internal.EnumLiteMap<EllipticCurveType> internalValueMap = new Internal.EnumLiteMap<EllipticCurveType>() { // from class: com.google.crypto.tink.proto.EllipticCurveType.1
        @Override // com.google.crypto.tink.shaded.protobuf.Internal.EnumLiteMap
        public EllipticCurveType findValueByNumber(int i2) {
            return EllipticCurveType.forNumber(i2);
        }
    };
    private final int value;

    public static final class EllipticCurveTypeVerifier implements Internal.EnumVerifier {
        static final Internal.EnumVerifier INSTANCE = new EllipticCurveTypeVerifier();

        private EllipticCurveTypeVerifier() {
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Internal.EnumVerifier
        public boolean isInRange(int i2) {
            return EllipticCurveType.forNumber(i2) != null;
        }
    }

    EllipticCurveType(int i2) {
        this.value = i2;
    }

    public static EllipticCurveType forNumber(int i2) {
        if (i2 == 0) {
            return UNKNOWN_CURVE;
        }
        if (i2 == 2) {
            return NIST_P256;
        }
        if (i2 == 3) {
            return NIST_P384;
        }
        if (i2 == 4) {
            return NIST_P521;
        }
        if (i2 != 5) {
            return null;
        }
        return CURVE25519;
    }

    public static Internal.EnumLiteMap<EllipticCurveType> internalGetValueMap() {
        return internalValueMap;
    }

    public static Internal.EnumVerifier internalGetVerifier() {
        return EllipticCurveTypeVerifier.INSTANCE;
    }

    @Deprecated
    public static EllipticCurveType valueOf(int i2) {
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
