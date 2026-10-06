package com.google.crypto.tink;

import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.proto.OutputPrefixType;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes.dex */
public final class CryptoFormat {
    public static final int LEGACY_PREFIX_SIZE = 5;
    public static final byte LEGACY_START_BYTE = 0;
    public static final int NON_RAW_PREFIX_SIZE = 5;
    public static final byte[] RAW_PREFIX = new byte[0];
    public static final int RAW_PREFIX_SIZE = 0;
    public static final int TINK_PREFIX_SIZE = 5;
    public static final byte TINK_START_BYTE = 1;

    /* JADX INFO: renamed from: com.google.crypto.tink.CryptoFormat$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType;

        static {
            int[] iArr = new int[OutputPrefixType.values().length];
            $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType = iArr;
            try {
                iArr[OutputPrefixType.LEGACY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.CRUNCHY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.TINK.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.RAW.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private CryptoFormat() {
    }

    public static byte[] getOutputPrefix(Keyset.Key key) throws GeneralSecurityException {
        ByteBuffer byteBufferPut;
        int i2 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[key.getOutputPrefixType().ordinal()];
        if (i2 == 1 || i2 == 2) {
            byteBufferPut = ByteBuffer.allocate(5).put((byte) 0);
        } else {
            if (i2 != 3) {
                if (i2 == 4) {
                    return RAW_PREFIX;
                }
                throw new GeneralSecurityException("unknown output prefix type");
            }
            byteBufferPut = ByteBuffer.allocate(5).put((byte) 1);
        }
        return byteBufferPut.putInt(key.getKeyId()).array();
    }
}
