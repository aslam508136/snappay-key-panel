package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.Internal;

/* JADX INFO: loaded from: classes.dex */
public enum EcPointFormat implements Internal.EnumLite {
    UNKNOWN_FORMAT(0),
    UNCOMPRESSED(1),
    COMPRESSED(2),
    DO_NOT_USE_CRUNCHY_UNCOMPRESSED(3),
    UNRECOGNIZED(-1);

    public static final int COMPRESSED_VALUE = 2;
    public static final int DO_NOT_USE_CRUNCHY_UNCOMPRESSED_VALUE = 3;
    public static final int UNCOMPRESSED_VALUE = 1;
    public static final int UNKNOWN_FORMAT_VALUE = 0;
    private static final Internal.EnumLiteMap<EcPointFormat> internalValueMap = new Internal.EnumLiteMap<EcPointFormat>() { // from class: com.google.crypto.tink.proto.EcPointFormat.1
        @Override // com.google.crypto.tink.shaded.protobuf.Internal.EnumLiteMap
        public EcPointFormat findValueByNumber(int i2) {
            return EcPointFormat.forNumber(i2);
        }
    };
    private final int value;

    public static final class EcPointFormatVerifier implements Internal.EnumVerifier {
        static final Internal.EnumVerifier INSTANCE = new EcPointFormatVerifier();

        private EcPointFormatVerifier() {
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Internal.EnumVerifier
        public boolean isInRange(int i2) {
            return EcPointFormat.forNumber(i2) != null;
        }
    }

    EcPointFormat(int i2) {
        this.value = i2;
    }

    public static EcPointFormat forNumber(int i2) {
        if (i2 == 0) {
            return UNKNOWN_FORMAT;
        }
        if (i2 == 1) {
            return UNCOMPRESSED;
        }
        if (i2 == 2) {
            return COMPRESSED;
        }
        if (i2 != 3) {
            return null;
        }
        return DO_NOT_USE_CRUNCHY_UNCOMPRESSED;
    }

    public static Internal.EnumLiteMap<EcPointFormat> internalGetValueMap() {
        return internalValueMap;
    }

    public static Internal.EnumVerifier internalGetVerifier() {
        return EcPointFormatVerifier.INSTANCE;
    }

    @Deprecated
    public static EcPointFormat valueOf(int i2) {
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
