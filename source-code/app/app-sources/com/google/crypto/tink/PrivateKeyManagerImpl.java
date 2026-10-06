package com.google.crypto.tink;

import com.google.crypto.tink.annotations.Alpha;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes.dex */
@Alpha
class PrivateKeyManagerImpl<PrimitiveT, KeyProtoT extends MessageLite, PublicKeyProtoT extends MessageLite> extends KeyManagerImpl<PrimitiveT, KeyProtoT> implements PrivateKeyManager<PrimitiveT> {
    private final PrivateKeyTypeManager<KeyProtoT, PublicKeyProtoT> privateKeyManager;
    private final KeyTypeManager<PublicKeyProtoT> publicKeyManager;

    public PrivateKeyManagerImpl(PrivateKeyTypeManager<KeyProtoT, PublicKeyProtoT> privateKeyTypeManager, KeyTypeManager<PublicKeyProtoT> keyTypeManager, Class<PrimitiveT> cls) {
        super(privateKeyTypeManager, cls);
        this.privateKeyManager = privateKeyTypeManager;
        this.publicKeyManager = keyTypeManager;
    }

    @Override // com.google.crypto.tink.PrivateKeyManager
    public KeyData getPublicKeyData(ByteString byteString) throws GeneralSecurityException {
        try {
            KeyProtoT key = this.privateKeyManager.parseKey(byteString);
            this.privateKeyManager.validateKey(key);
            MessageLite publicKey = this.privateKeyManager.getPublicKey(key);
            this.publicKeyManager.validateKey(publicKey);
            return KeyData.newBuilder().setTypeUrl(this.publicKeyManager.getKeyType()).setValue(publicKey.toByteString()).setKeyMaterialType(this.publicKeyManager.keyMaterialType()).build();
        } catch (InvalidProtocolBufferException e2) {
            throw new GeneralSecurityException("expected serialized proto of type ", e2);
        }
    }
}
