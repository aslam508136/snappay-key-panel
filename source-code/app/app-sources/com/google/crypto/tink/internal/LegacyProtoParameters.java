package com.google.crypto.tink.internal;

import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.errorprone.annotations.Immutable;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
@Immutable
public final class LegacyProtoParameters extends Parameters {
    private final ProtoParametersSerialization serialization;

    /* JADX INFO: renamed from: com.google.crypto.tink.internal.LegacyProtoParameters$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType;

        static {
            int[] iArr = new int[OutputPrefixType.values().length];
            $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType = iArr;
            try {
                iArr[OutputPrefixType.TINK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.LEGACY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.RAW.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.CRUNCHY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public LegacyProtoParameters(ProtoParametersSerialization protoParametersSerialization) {
        this.serialization = protoParametersSerialization;
    }

    private static String outputPrefixToString(OutputPrefixType outputPrefixType) {
        int i2 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[outputPrefixType.ordinal()];
        if (i2 == 1) {
            return "TINK";
        }
        if (i2 == 2) {
            return "LEGACY";
        }
        if (i2 != 3) {
            return i2 != 4 ? "UNKNOWN" : "CRUNCHY";
        }
        return "RAW";
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof LegacyProtoParameters)) {
            return false;
        }
        ProtoParametersSerialization protoParametersSerialization = ((LegacyProtoParameters) obj).serialization;
        return this.serialization.getKeyTemplate().getOutputPrefixType().equals(protoParametersSerialization.getKeyTemplate().getOutputPrefixType()) && this.serialization.getKeyTemplate().getTypeUrl().equals(protoParametersSerialization.getKeyTemplate().getTypeUrl()) && this.serialization.getKeyTemplate().getValue().equals(protoParametersSerialization.getKeyTemplate().getValue());
    }

    public ProtoParametersSerialization getSerialization() {
        return this.serialization;
    }

    @Override // com.google.crypto.tink.Parameters
    public boolean hasIdRequirement() {
        return this.serialization.getKeyTemplate().getOutputPrefixType() != OutputPrefixType.RAW;
    }

    public int hashCode() {
        return Objects.hash(this.serialization.getKeyTemplate(), this.serialization.getObjectIdentifier());
    }

    public String toString() {
        return String.format("(typeUrl=%s, outputPrefixType=%s)", this.serialization.getKeyTemplate().getTypeUrl(), outputPrefixToString(this.serialization.getKeyTemplate().getOutputPrefixType()));
    }
}
