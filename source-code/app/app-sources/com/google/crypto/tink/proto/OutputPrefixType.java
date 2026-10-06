package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.Internal;

/* JADX INFO: loaded from: classes.dex */
public enum OutputPrefixType implements Internal.EnumLite {
    UNKNOWN_PREFIX(0),
    TINK(1),
    LEGACY(2),
    RAW(3),
    CRUNCHY(4),
    UNRECOGNIZED(-1);

    public static final int CRUNCHY_VALUE = 4;
    public static final int LEGACY_VALUE = 2;
    public static final int RAW_VALUE = 3;
    public static final int TINK_VALUE = 1;
    public static final int UNKNOWN_PREFIX_VALUE = 0;
    private static final Internal.EnumLiteMap<OutputPrefixType> internalValueMap = new Internal.EnumLiteMap<OutputPrefixType>() { // from class: com.google.crypto.tink.proto.OutputPrefixType.1
        @Override // com.google.crypto.tink.shaded.protobuf.Internal.EnumLiteMap
        public OutputPrefixType findValueByNumber(int i2) {
            return OutputPrefixType.forNumber(i2);
        }
    };
    private final int value;

    public static final class OutputPrefixTypeVerifier implements Internal.EnumVerifier {
        static final Internal.EnumVerifier INSTANCE = new OutputPrefixTypeVerifier();

        private OutputPrefixTypeVerifier() {
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Internal.EnumVerifier
        public boolean isInRange(int i2) {
            return OutputPrefixType.forNumber(i2) != null;
        }
    }

    OutputPrefixType(int i2) {
        this.value = i2;
    }

    public static OutputPrefixType forNumber(int i2) {
        if (i2 == 0) {
            return UNKNOWN_PREFIX;
        }
        if (i2 == 1) {
            return TINK;
        }
        if (i2 == 2) {
            return LEGACY;
        }
        if (i2 == 3) {
            return RAW;
        }
        if (i2 != 4) {
            return null;
        }
        return CRUNCHY;
    }

    public static Internal.EnumLiteMap<OutputPrefixType> internalGetValueMap() {
        return internalValueMap;
    }

    public static Internal.EnumVerifier internalGetVerifier() {
        return OutputPrefixTypeVerifier.INSTANCE;
    }

    @Deprecated
    public static OutputPrefixType valueOf(int i2) {
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
