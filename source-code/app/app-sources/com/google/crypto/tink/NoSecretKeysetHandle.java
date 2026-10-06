package com.google.crypto.tink;

import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class NoSecretKeysetHandle {
    private NoSecretKeysetHandle() {
    }

    @Deprecated
    public static final KeysetHandle parseFrom(byte[] bArr) throws GeneralSecurityException {
        try {
            Keyset from = Keyset.parseFrom(bArr, ExtensionRegistryLite.getEmptyRegistry());
            validate(from);
            return KeysetHandle.fromKeyset(from);
        } catch (InvalidProtocolBufferException unused) {
            throw new GeneralSecurityException("invalid keyset");
        }
    }

    public static final KeysetHandle read(KeysetReader keysetReader) throws GeneralSecurityException {
        Keyset keyset = keysetReader.read();
        validate(keyset);
        return KeysetHandle.fromKeyset(keyset);
    }

    private static void validate(Keyset keyset) throws GeneralSecurityException {
        for (Keyset.Key key : keyset.getKeyList()) {
            if (key.getKeyData().getKeyMaterialType() == KeyData.KeyMaterialType.UNKNOWN_KEYMATERIAL || key.getKeyData().getKeyMaterialType() == KeyData.KeyMaterialType.SYMMETRIC || key.getKeyData().getKeyMaterialType() == KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE) {
                throw new GeneralSecurityException("keyset contains secret key material");
            }
        }
    }
}
