package com.google.crypto.tink;

import com.google.crypto.tink.internal.KeyStatusTypeProtoConverter;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.tinkkey.KeyAccess;
import com.google.crypto.tink.tinkkey.KeyHandle;
import com.google.crypto.tink.tinkkey.internal.ProtoKey;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.InlineMe;
import java.security.GeneralSecurityException;
import java.util.Iterator;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: classes.dex */
public final class KeysetManager {

    @GuardedBy("this")
    private final Keyset.Builder keysetBuilder;

    private KeysetManager(Keyset.Builder builder) {
        this.keysetBuilder = builder;
    }

    private synchronized Keyset.Key createKeysetKey(KeyData keyData, OutputPrefixType outputPrefixType) {
        int iNewKeyId;
        iNewKeyId = newKeyId();
        if (outputPrefixType == OutputPrefixType.UNKNOWN_PREFIX) {
            throw new GeneralSecurityException("unknown output prefix type");
        }
        return Keyset.Key.newBuilder().setKeyData(keyData).setKeyId(iNewKeyId).setStatus(KeyStatusType.ENABLED).setOutputPrefixType(outputPrefixType).build();
    }

    private synchronized boolean keyIdExists(int i2) {
        Iterator<Keyset.Key> it = this.keysetBuilder.getKeyList().iterator();
        while (it.hasNext()) {
            if (it.next().getKeyId() == i2) {
                return true;
            }
        }
        return false;
    }

    private synchronized Keyset.Key newKey(com.google.crypto.tink.proto.KeyTemplate keyTemplate) {
        return createKeysetKey(Registry.newKeyData(keyTemplate), keyTemplate.getOutputPrefixType());
    }

    private synchronized int newKeyId() {
        int iRandKeyId;
        do {
            iRandKeyId = com.google.crypto.tink.internal.Util.randKeyId();
        } while (keyIdExists(iRandKeyId));
        return iRandKeyId;
    }

    public static KeysetManager withEmptyKeyset() {
        return new KeysetManager(Keyset.newBuilder());
    }

    public static KeysetManager withKeysetHandle(KeysetHandle keysetHandle) {
        return new KeysetManager(keysetHandle.getKeyset().toBuilder());
    }

    @CanIgnoreReturnValue
    public synchronized KeysetManager add(KeyTemplate keyTemplate) {
        addNewKey(keyTemplate.getProto(), false);
        return this;
    }

    @CanIgnoreReturnValue
    @Deprecated
    public synchronized int addNewKey(com.google.crypto.tink.proto.KeyTemplate keyTemplate, boolean z2) {
        Keyset.Key keyNewKey;
        keyNewKey = newKey(keyTemplate);
        this.keysetBuilder.addKey(keyNewKey);
        if (z2) {
            this.keysetBuilder.setPrimaryKeyId(keyNewKey.getKeyId());
        }
        return keyNewKey.getKeyId();
    }

    @CanIgnoreReturnValue
    public synchronized KeysetManager delete(int i2) {
        if (i2 == this.keysetBuilder.getPrimaryKeyId()) {
            throw new GeneralSecurityException("cannot delete the primary key");
        }
        for (int i3 = 0; i3 < this.keysetBuilder.getKeyCount(); i3++) {
            if (this.keysetBuilder.getKey(i3).getKeyId() == i2) {
                this.keysetBuilder.removeKey(i3);
            }
        }
        throw new GeneralSecurityException("key not found: " + i2);
        return this;
    }

    @CanIgnoreReturnValue
    public synchronized KeysetManager destroy(int i2) {
        if (i2 == this.keysetBuilder.getPrimaryKeyId()) {
            throw new GeneralSecurityException("cannot destroy the primary key");
        }
        for (int i3 = 0; i3 < this.keysetBuilder.getKeyCount(); i3++) {
            Keyset.Key key = this.keysetBuilder.getKey(i3);
            if (key.getKeyId() == i2) {
                if (key.getStatus() != KeyStatusType.ENABLED && key.getStatus() != KeyStatusType.DISABLED && key.getStatus() != KeyStatusType.DESTROYED) {
                    throw new GeneralSecurityException("cannot destroy key with id " + i2);
                }
                this.keysetBuilder.setKey(i3, key.toBuilder().setStatus(KeyStatusType.DESTROYED).clearKeyData().build());
            }
        }
        throw new GeneralSecurityException("key not found: " + i2);
        return this;
    }

    @CanIgnoreReturnValue
    public synchronized KeysetManager disable(int i2) {
        if (i2 == this.keysetBuilder.getPrimaryKeyId()) {
            throw new GeneralSecurityException("cannot disable the primary key");
        }
        for (int i3 = 0; i3 < this.keysetBuilder.getKeyCount(); i3++) {
            Keyset.Key key = this.keysetBuilder.getKey(i3);
            if (key.getKeyId() == i2) {
                if (key.getStatus() != KeyStatusType.ENABLED && key.getStatus() != KeyStatusType.DISABLED) {
                    throw new GeneralSecurityException("cannot disable key with id " + i2);
                }
                this.keysetBuilder.setKey(i3, key.toBuilder().setStatus(KeyStatusType.DISABLED).build());
            }
        }
        throw new GeneralSecurityException("key not found: " + i2);
        return this;
    }

    @CanIgnoreReturnValue
    public synchronized KeysetManager enable(int i2) {
        for (int i3 = 0; i3 < this.keysetBuilder.getKeyCount(); i3++) {
            Keyset.Key key = this.keysetBuilder.getKey(i3);
            if (key.getKeyId() == i2) {
                KeyStatusType status = key.getStatus();
                KeyStatusType keyStatusType = KeyStatusType.ENABLED;
                if (status != keyStatusType && key.getStatus() != KeyStatusType.DISABLED) {
                    throw new GeneralSecurityException("cannot enable key with id " + i2);
                }
                this.keysetBuilder.setKey(i3, key.toBuilder().setStatus(keyStatusType).build());
            }
        }
        throw new GeneralSecurityException("key not found: " + i2);
        return this;
    }

    public synchronized KeysetHandle getKeysetHandle() {
        return KeysetHandle.fromKeyset(this.keysetBuilder.build());
    }

    @CanIgnoreReturnValue
    @InlineMe(replacement = "this.setPrimary(keyId)")
    @Deprecated
    public synchronized KeysetManager promote(int i2) {
        return setPrimary(i2);
    }

    @CanIgnoreReturnValue
    @Deprecated
    public synchronized KeysetManager rotate(com.google.crypto.tink.proto.KeyTemplate keyTemplate) {
        addNewKey(keyTemplate, true);
        return this;
    }

    @CanIgnoreReturnValue
    public synchronized KeysetManager setPrimary(int i2) {
        for (int i3 = 0; i3 < this.keysetBuilder.getKeyCount(); i3++) {
            Keyset.Key key = this.keysetBuilder.getKey(i3);
            if (key.getKeyId() == i2) {
                if (!key.getStatus().equals(KeyStatusType.ENABLED)) {
                    throw new GeneralSecurityException("cannot set key as primary because it's not enabled: " + i2);
                }
                this.keysetBuilder.setPrimaryKeyId(i2);
            }
        }
        throw new GeneralSecurityException("key not found: " + i2);
        return this;
    }

    @CanIgnoreReturnValue
    @Deprecated
    public synchronized KeysetManager add(com.google.crypto.tink.proto.KeyTemplate keyTemplate) {
        addNewKey(keyTemplate, false);
        return this;
    }

    @CanIgnoreReturnValue
    public synchronized KeysetManager add(KeyHandle keyHandle) {
        try {
            try {
                ProtoKey protoKey = (ProtoKey) keyHandle.getKey(com.google.crypto.tink.tinkkey.SecretKeyAccess.insecureSecretAccess());
                if (keyIdExists(keyHandle.getId())) {
                    throw new GeneralSecurityException("Trying to add a key with an ID already contained in the keyset.");
                }
                this.keysetBuilder.addKey(Keyset.Key.newBuilder().setKeyData(protoKey.getProtoKey()).setKeyId(keyHandle.getId()).setStatus(KeyStatusTypeProtoConverter.toProto(keyHandle.getStatus())).setOutputPrefixType(KeyTemplate.toProto(protoKey.getOutputPrefixType())).build());
            } catch (ClassCastException e2) {
                throw new UnsupportedOperationException("KeyHandles which contain TinkKeys that are not ProtoKeys are not yet supported.", e2);
            }
        } catch (Throwable th) {
            throw th;
        }
        return this;
    }

    @CanIgnoreReturnValue
    public synchronized KeysetManager add(KeyHandle keyHandle, KeyAccess keyAccess) {
        return add(keyHandle);
    }
}
