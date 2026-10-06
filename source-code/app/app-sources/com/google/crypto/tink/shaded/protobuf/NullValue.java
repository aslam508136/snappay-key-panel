package com.google.crypto.tink.shaded.protobuf;

/* JADX INFO: loaded from: classes.dex */
public enum NullValue implements Internal.EnumLite {
    NULL_VALUE(0),
    UNRECOGNIZED(-1);

    public static final int NULL_VALUE_VALUE = 0;
    private static final Internal.EnumLiteMap<NullValue> internalValueMap = new Internal.EnumLiteMap<NullValue>() { // from class: com.google.crypto.tink.shaded.protobuf.NullValue.1
        @Override // com.google.crypto.tink.shaded.protobuf.Internal.EnumLiteMap
        public NullValue findValueByNumber(int i2) {
            return NullValue.forNumber(i2);
        }
    };
    private final int value;

    public static final class NullValueVerifier implements Internal.EnumVerifier {
        static final Internal.EnumVerifier INSTANCE = new NullValueVerifier();

        private NullValueVerifier() {
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Internal.EnumVerifier
        public boolean isInRange(int i2) {
            return NullValue.forNumber(i2) != null;
        }
    }

    NullValue(int i2) {
        this.value = i2;
    }

    public static NullValue forNumber(int i2) {
        if (i2 != 0) {
            return null;
        }
        return NULL_VALUE;
    }

    public static Internal.EnumLiteMap<NullValue> internalGetValueMap() {
        return internalValueMap;
    }

    public static Internal.EnumVerifier internalGetVerifier() {
        return NullValueVerifier.INSTANCE;
    }

    @Deprecated
    public static NullValue valueOf(int i2) {
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
