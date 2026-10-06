package com.google.crypto.tink.internal;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes.dex */
public final class KeyTemplateProtoConverter {

    /* JADX INFO: renamed from: com.google.crypto.tink.internal.KeyTemplateProtoConverter$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$KeyTemplate$OutputPrefixType;
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType;

        static {
            int[] iArr = new int[KeyTemplate.OutputPrefixType.values().length];
            $SwitchMap$com$google$crypto$tink$KeyTemplate$OutputPrefixType = iArr;
            try {
                iArr[KeyTemplate.OutputPrefixType.TINK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$KeyTemplate$OutputPrefixType[KeyTemplate.OutputPrefixType.LEGACY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$KeyTemplate$OutputPrefixType[KeyTemplate.OutputPrefixType.RAW.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$KeyTemplate$OutputPrefixType[KeyTemplate.OutputPrefixType.CRUNCHY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[OutputPrefixType.values().length];
            $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType = iArr2;
            try {
                iArr2[OutputPrefixType.TINK.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.LEGACY.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.RAW.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.CRUNCHY.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    private KeyTemplateProtoConverter() {
    }

    public static KeyTemplate fromByteArray(byte[] bArr) throws GeneralSecurityException {
        try {
            return fromProto(com.google.crypto.tink.proto.KeyTemplate.parseFrom(bArr, ExtensionRegistryLite.getEmptyRegistry()));
        } catch (InvalidProtocolBufferException e2) {
            throw new GeneralSecurityException("invalid key template", e2);
        }
    }

    public static KeyTemplate fromProto(com.google.crypto.tink.proto.KeyTemplate keyTemplate) {
        return KeyTemplate.create(keyTemplate.getTypeUrl(), keyTemplate.getValue().toByteArray(), prefixFromProto(keyTemplate.getOutputPrefixType()));
    }

    public static KeyTemplate.OutputPrefixType prefixFromProto(OutputPrefixType outputPrefixType) throws GeneralSecurityException {
        int i2 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[outputPrefixType.ordinal()];
        if (i2 == 1) {
            return KeyTemplate.OutputPrefixType.TINK;
        }
        if (i2 == 2) {
            return KeyTemplate.OutputPrefixType.LEGACY;
        }
        if (i2 == 3) {
            return KeyTemplate.OutputPrefixType.RAW;
        }
        if (i2 == 4) {
            return KeyTemplate.OutputPrefixType.CRUNCHY;
        }
        throw new GeneralSecurityException("Unknown output prefix type");
    }

    private static OutputPrefixType prefixToProto(KeyTemplate.OutputPrefixType outputPrefixType) throws GeneralSecurityException {
        int i2 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$KeyTemplate$OutputPrefixType[outputPrefixType.ordinal()];
        if (i2 == 1) {
            return OutputPrefixType.TINK;
        }
        if (i2 == 2) {
            return OutputPrefixType.LEGACY;
        }
        if (i2 == 3) {
            return OutputPrefixType.RAW;
        }
        if (i2 == 4) {
            return OutputPrefixType.CRUNCHY;
        }
        throw new GeneralSecurityException("Unknown output prefix type");
    }

    public static byte[] toByteArray(KeyTemplate keyTemplate) {
        return toProto(keyTemplate).toByteArray();
    }

    public static com.google.crypto.tink.proto.KeyTemplate toProto(KeyTemplate keyTemplate) {
        return com.google.crypto.tink.proto.KeyTemplate.newBuilder().setTypeUrl(keyTemplate.getTypeUrl()).setValue(ByteString.copyFrom(keyTemplate.getValue())).setOutputPrefixType(prefixToProto(keyTemplate.getOutputPrefixType())).build();
    }
}
