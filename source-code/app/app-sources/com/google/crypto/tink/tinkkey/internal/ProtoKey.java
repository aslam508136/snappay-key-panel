package com.google.crypto.tink.tinkkey.internal;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.tinkkey.TinkKey;
import com.google.errorprone.annotations.Immutable;

/* JADX INFO: loaded from: classes.dex */
@Immutable
public final class ProtoKey implements TinkKey {
    private final boolean hasSecret;
    private final KeyData keyData;
    private final KeyTemplate.OutputPrefixType outputPrefixType;

    public ProtoKey(KeyData keyData, KeyTemplate.OutputPrefixType outputPrefixType) {
        this.hasSecret = isSecret(keyData);
        this.keyData = keyData;
        this.outputPrefixType = outputPrefixType;
    }

    private static boolean isSecret(KeyData keyData) {
        return keyData.getKeyMaterialType() == KeyData.KeyMaterialType.UNKNOWN_KEYMATERIAL || keyData.getKeyMaterialType() == KeyData.KeyMaterialType.SYMMETRIC || keyData.getKeyMaterialType() == KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE;
    }

    @Override // com.google.crypto.tink.tinkkey.TinkKey
    public KeyTemplate getKeyTemplate() {
        throw new UnsupportedOperationException();
    }

    public KeyTemplate.OutputPrefixType getOutputPrefixType() {
        return this.outputPrefixType;
    }

    public KeyData getProtoKey() {
        return this.keyData;
    }

    @Override // com.google.crypto.tink.tinkkey.TinkKey
    public boolean hasSecret() {
        return this.hasSecret;
    }
}
