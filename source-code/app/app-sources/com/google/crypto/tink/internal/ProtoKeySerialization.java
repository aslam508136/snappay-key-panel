package com.google.crypto.tink.internal;

import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.util.Bytes;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Immutable
public final class ProtoKeySerialization implements Serialization {

    @Nullable
    private final Integer idRequirement;
    private final KeyData.KeyMaterialType keyMaterialType;
    private final Bytes objectIdentifier;
    private final OutputPrefixType outputPrefixType;
    private final String typeUrl;
    private final ByteString value;

    private ProtoKeySerialization(String str, ByteString byteString, KeyData.KeyMaterialType keyMaterialType, OutputPrefixType outputPrefixType, @Nullable Integer num) {
        this.typeUrl = str;
        this.objectIdentifier = Util.toBytesFromPrintableAscii(str);
        this.value = byteString;
        this.keyMaterialType = keyMaterialType;
        this.outputPrefixType = outputPrefixType;
        this.idRequirement = num;
    }

    public static ProtoKeySerialization create(String str, ByteString byteString, KeyData.KeyMaterialType keyMaterialType, OutputPrefixType outputPrefixType, @Nullable Integer num) throws GeneralSecurityException {
        if (outputPrefixType == OutputPrefixType.RAW) {
            if (num != null) {
                throw new GeneralSecurityException("Keys with output prefix type raw should not have an id requirement.");
            }
        } else if (num == null) {
            throw new GeneralSecurityException("Keys with output prefix type different from raw should have an id requirement.");
        }
        return new ProtoKeySerialization(str, byteString, keyMaterialType, outputPrefixType, num);
    }

    @Nullable
    public Integer getIdRequirementOrNull() {
        return this.idRequirement;
    }

    public KeyData.KeyMaterialType getKeyMaterialType() {
        return this.keyMaterialType;
    }

    @Override // com.google.crypto.tink.internal.Serialization
    public Bytes getObjectIdentifier() {
        return this.objectIdentifier;
    }

    public OutputPrefixType getOutputPrefixType() {
        return this.outputPrefixType;
    }

    public String getTypeUrl() {
        return this.typeUrl;
    }

    public ByteString getValue() {
        return this.value;
    }
}
