package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.Internal;

/* JADX INFO: loaded from: classes.dex */
public enum EcdsaSignatureEncoding implements Internal.EnumLite {
    UNKNOWN_ENCODING(0),
    IEEE_P1363(1),
    DER(2),
    UNRECOGNIZED(-1);

    public static final int DER_VALUE = 2;
    public static final int IEEE_P1363_VALUE = 1;
    public static final int UNKNOWN_ENCODING_VALUE = 0;
    private static final Internal.EnumLiteMap<EcdsaSignatureEncoding> internalValueMap = new Internal.EnumLiteMap<EcdsaSignatureEncoding>() { // from class: com.google.crypto.tink.proto.EcdsaSignatureEncoding.1
        @Override // com.google.crypto.tink.shaded.protobuf.Internal.EnumLiteMap
        public EcdsaSignatureEncoding findValueByNumber(int i2) {
            return EcdsaSignatureEncoding.forNumber(i2);
        }
    };
    private final int value;

    public static final class EcdsaSignatureEncodingVerifier implements Internal.EnumVerifier {
        static final Internal.EnumVerifier INSTANCE = new EcdsaSignatureEncodingVerifier();

        private EcdsaSignatureEncodingVerifier() {
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Internal.EnumVerifier
        public boolean isInRange(int i2) {
            return EcdsaSignatureEncoding.forNumber(i2) != null;
        }
    }

    EcdsaSignatureEncoding(int i2) {
        this.value = i2;
    }

    public static EcdsaSignatureEncoding forNumber(int i2) {
        if (i2 == 0) {
            return UNKNOWN_ENCODING;
        }
        if (i2 == 1) {
            return IEEE_P1363;
        }
        if (i2 != 2) {
            return null;
        }
        return DER;
    }

    public static Internal.EnumLiteMap<EcdsaSignatureEncoding> internalGetValueMap() {
        return internalValueMap;
    }

    public static Internal.EnumVerifier internalGetVerifier() {
        return EcdsaSignatureEncodingVerifier.INSTANCE;
    }

    @Deprecated
    public static EcdsaSignatureEncoding valueOf(int i2) {
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
