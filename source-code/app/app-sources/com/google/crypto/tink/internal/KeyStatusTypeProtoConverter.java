package com.google.crypto.tink.internal;

import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.tinkkey.KeyHandle;

/* JADX INFO: loaded from: classes.dex */
public final class KeyStatusTypeProtoConverter {

    /* JADX INFO: renamed from: com.google.crypto.tink.internal.KeyStatusTypeProtoConverter$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$KeyStatusType;
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$tinkkey$KeyHandle$KeyStatusType;

        static {
            int[] iArr = new int[KeyHandle.KeyStatusType.values().length];
            $SwitchMap$com$google$crypto$tink$tinkkey$KeyHandle$KeyStatusType = iArr;
            try {
                iArr[KeyHandle.KeyStatusType.ENABLED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$tinkkey$KeyHandle$KeyStatusType[KeyHandle.KeyStatusType.DISABLED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$tinkkey$KeyHandle$KeyStatusType[KeyHandle.KeyStatusType.DESTROYED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[KeyStatusType.values().length];
            $SwitchMap$com$google$crypto$tink$proto$KeyStatusType = iArr2;
            try {
                iArr2[KeyStatusType.ENABLED.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$KeyStatusType[KeyStatusType.DISABLED.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$KeyStatusType[KeyStatusType.DESTROYED.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private KeyStatusTypeProtoConverter() {
    }

    public static KeyHandle.KeyStatusType fromProto(KeyStatusType keyStatusType) {
        int i2 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$KeyStatusType[keyStatusType.ordinal()];
        if (i2 == 1) {
            return KeyHandle.KeyStatusType.ENABLED;
        }
        if (i2 == 2) {
            return KeyHandle.KeyStatusType.DISABLED;
        }
        if (i2 == 3) {
            return KeyHandle.KeyStatusType.DESTROYED;
        }
        throw new IllegalArgumentException("Unknown key status type.");
    }

    public static KeyStatusType toProto(KeyHandle.KeyStatusType keyStatusType) {
        int i2 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$tinkkey$KeyHandle$KeyStatusType[keyStatusType.ordinal()];
        if (i2 == 1) {
            return KeyStatusType.ENABLED;
        }
        if (i2 == 2) {
            return KeyStatusType.DISABLED;
        }
        if (i2 == 3) {
            return KeyStatusType.DESTROYED;
        }
        throw new IllegalArgumentException("Unknown key status type.");
    }
}
