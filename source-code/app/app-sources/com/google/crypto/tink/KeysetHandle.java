package com.google.crypto.tink;

import com.google.crypto.tink.annotations.Alpha;
import com.google.crypto.tink.internal.LegacyProtoKey;
import com.google.crypto.tink.internal.LegacyProtoParameters;
import com.google.crypto.tink.internal.MutableSerializationRegistry;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.internal.ProtoParametersSerialization;
import com.google.crypto.tink.internal.TinkBugException;
import com.google.crypto.tink.monitoring.MonitoringAnnotations;
import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.proto.KeysetInfo;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.tinkkey.KeyAccess;
import com.google.crypto.tink.tinkkey.KeyHandle;
import com.google.crypto.tink.tinkkey.internal.InternalKeyHandle;
import com.google.crypto.tink.tinkkey.internal.ProtoKey;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class KeysetHandle {
    private final MonitoringAnnotations annotations;
    private final List<Entry> entries;
    private final Keyset keyset;

    /* JADX INFO: renamed from: com.google.crypto.tink.KeysetHandle$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$KeyStatusType;

        static {
            int[] iArr = new int[KeyStatusType.values().length];
            $SwitchMap$com$google$crypto$tink$proto$KeyStatusType = iArr;
            try {
                iArr[KeyStatusType.ENABLED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$KeyStatusType[KeyStatusType.DISABLED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$KeyStatusType[KeyStatusType.DESTROYED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static final class Builder {
        private final List<Entry> entries = new ArrayList();

        public static final class Entry {

            @Nullable
            private Builder builder;
            private boolean isPrimary;

            @Nullable
            private final Key key;
            private KeyStatus keyStatus;

            @Nullable
            private final Parameters parameters;
            private KeyIdStrategy strategy;

            private Entry(Key key) {
                this.keyStatus = KeyStatus.ENABLED;
                this.strategy = null;
                this.builder = null;
                this.key = key;
                this.parameters = null;
            }

            public KeyStatus getStatus() {
                return this.keyStatus;
            }

            public boolean isPrimary() {
                return this.isPrimary;
            }

            @CanIgnoreReturnValue
            public Entry makePrimary() {
                Builder builder = this.builder;
                if (builder != null) {
                    builder.clearPrimary();
                }
                this.isPrimary = true;
                return this;
            }

            @CanIgnoreReturnValue
            public Entry setStatus(KeyStatus keyStatus) {
                this.keyStatus = keyStatus;
                return this;
            }

            @CanIgnoreReturnValue
            public Entry withFixedId(int i2) {
                this.strategy = KeyIdStrategy.fixedId(i2);
                return this;
            }

            @CanIgnoreReturnValue
            public Entry withRandomId() {
                this.strategy = KeyIdStrategy.randomId();
                return this;
            }

            public /* synthetic */ Entry(Key key, AnonymousClass1 anonymousClass1) {
                this(key);
            }

            private Entry(Parameters parameters) {
                this.keyStatus = KeyStatus.ENABLED;
                this.strategy = null;
                this.builder = null;
                this.key = null;
                this.parameters = parameters;
            }

            public /* synthetic */ Entry(Parameters parameters, AnonymousClass1 anonymousClass1) {
                this(parameters);
            }
        }

        public static class KeyIdStrategy {
            private static final KeyIdStrategy RANDOM_ID = new KeyIdStrategy();
            private final int fixedId;

            private KeyIdStrategy() {
                this.fixedId = 0;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static KeyIdStrategy fixedId(int i2) {
                return new KeyIdStrategy(i2);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public int getFixedId() {
                return this.fixedId;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static KeyIdStrategy randomId() {
                return RANDOM_ID;
            }

            private KeyIdStrategy(int i2) {
                this.fixedId = i2;
            }
        }

        private static void checkIdAssignments(List<Entry> list) throws GeneralSecurityException {
            for (int i2 = 0; i2 < list.size() - 1; i2++) {
                if (list.get(i2).strategy == KeyIdStrategy.RANDOM_ID && list.get(i2 + 1).strategy != KeyIdStrategy.RANDOM_ID) {
                    throw new GeneralSecurityException("Entries with 'withRandomId()' may only be followed by other entries with 'withRandomId()'.");
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPrimary() {
            Iterator<Entry> it = this.entries.iterator();
            while (it.hasNext()) {
                it.next().isPrimary = false;
            }
        }

        private static Keyset.Key createKeyFromParameters(Parameters parameters, int i2, KeyStatusType keyStatusType) {
            ProtoParametersSerialization serialization = parameters instanceof LegacyProtoParameters ? ((LegacyProtoParameters) parameters).getSerialization() : (ProtoParametersSerialization) MutableSerializationRegistry.globalInstance().serializeParameters(parameters, ProtoParametersSerialization.class);
            return Keyset.Key.newBuilder().setKeyId(i2).setStatus(keyStatusType).setKeyData(Registry.newKeyData(serialization.getKeyTemplate())).setOutputPrefixType(serialization.getKeyTemplate().getOutputPrefixType()).build();
        }

        private static Keyset.Key createKeysetKeyFromBuilderEntry(Entry entry, int i2) throws GeneralSecurityException {
            if (entry.key == null) {
                return createKeyFromParameters(entry.parameters, i2, KeysetHandle.serializeStatus(entry.getStatus()));
            }
            ProtoKeySerialization serialization = entry.key instanceof LegacyProtoKey ? ((LegacyProtoKey) entry.key).getSerialization(InsecureSecretKeyAccess.get()) : (ProtoKeySerialization) MutableSerializationRegistry.globalInstance().serializeKey(entry.key, ProtoKeySerialization.class, InsecureSecretKeyAccess.get());
            Integer idRequirementOrNull = serialization.getIdRequirementOrNull();
            if (idRequirementOrNull == null || idRequirementOrNull.intValue() == i2) {
                return KeysetHandle.toKeysetKey(i2, KeysetHandle.serializeStatus(entry.getStatus()), serialization);
            }
            throw new GeneralSecurityException("Wrong ID set for key with ID requirement");
        }

        private static int getNextIdFromBuilderEntry(Entry entry, Set<Integer> set) throws GeneralSecurityException {
            if (entry.strategy != null) {
                return entry.strategy == KeyIdStrategy.RANDOM_ID ? randomIdNotInSet(set) : entry.strategy.getFixedId();
            }
            throw new GeneralSecurityException("No ID was set (with withFixedId or withRandomId)");
        }

        private static int randomIdNotInSet(Set<Integer> set) {
            int iRandKeyId = 0;
            while (true) {
                if (iRandKeyId != 0 && !set.contains(Integer.valueOf(iRandKeyId))) {
                    return iRandKeyId;
                }
                iRandKeyId = com.google.crypto.tink.internal.Util.randKeyId();
            }
        }

        @CanIgnoreReturnValue
        public Builder addEntry(Entry entry) {
            if (entry.builder != null) {
                throw new IllegalStateException("Entry has already been added to a KeysetHandle.Builder");
            }
            if (entry.isPrimary) {
                clearPrimary();
            }
            entry.builder = this;
            this.entries.add(entry);
            return this;
        }

        public KeysetHandle build() throws GeneralSecurityException {
            Keyset.Builder builderNewBuilder = Keyset.newBuilder();
            checkIdAssignments(this.entries);
            HashSet hashSet = new HashSet();
            Integer numValueOf = null;
            for (Entry entry : this.entries) {
                if (entry.keyStatus == null) {
                    throw new GeneralSecurityException("Key Status not set.");
                }
                int nextIdFromBuilderEntry = getNextIdFromBuilderEntry(entry, hashSet);
                if (hashSet.contains(Integer.valueOf(nextIdFromBuilderEntry))) {
                    throw new GeneralSecurityException("Id " + nextIdFromBuilderEntry + " is used twice in the keyset");
                }
                hashSet.add(Integer.valueOf(nextIdFromBuilderEntry));
                builderNewBuilder.addKey(createKeysetKeyFromBuilderEntry(entry, nextIdFromBuilderEntry));
                if (entry.isPrimary) {
                    if (numValueOf != null) {
                        throw new GeneralSecurityException("Two primaries were set");
                    }
                    numValueOf = Integer.valueOf(nextIdFromBuilderEntry);
                }
            }
            if (numValueOf == null) {
                throw new GeneralSecurityException("No primary was set");
            }
            builderNewBuilder.setPrimaryKeyId(numValueOf.intValue());
            return KeysetHandle.fromKeyset(builderNewBuilder.build());
        }

        @CanIgnoreReturnValue
        public Builder deleteAt(int i2) {
            this.entries.remove(i2);
            return this;
        }

        public Entry getAt(int i2) {
            return this.entries.get(i2);
        }

        @CanIgnoreReturnValue
        @Deprecated
        public Entry removeAt(int i2) {
            return this.entries.remove(i2);
        }

        public int size() {
            return this.entries.size();
        }
    }

    @Immutable
    @Alpha
    public static final class Entry {
        private final int id;
        private final boolean isPrimary;
        private final Key key;
        private final KeyStatus keyStatus;

        private Entry(Key key, KeyStatus keyStatus, int i2, boolean z2) {
            this.key = key;
            this.keyStatus = keyStatus;
            this.id = i2;
            this.isPrimary = z2;
        }

        public int getId() {
            return this.id;
        }

        public Key getKey() {
            return this.key;
        }

        public KeyStatus getStatus() {
            return this.keyStatus;
        }

        public boolean isPrimary() {
            return this.isPrimary;
        }

        public /* synthetic */ Entry(Key key, KeyStatus keyStatus, int i2, boolean z2, AnonymousClass1 anonymousClass1) {
            this(key, keyStatus, i2, z2);
        }
    }

    private KeysetHandle(Keyset keyset, List<Entry> list) {
        this.keyset = keyset;
        this.entries = list;
        this.annotations = MonitoringAnnotations.EMPTY;
    }

    private static void assertEnoughEncryptedKeyMaterial(EncryptedKeyset encryptedKeyset) throws GeneralSecurityException {
        if (encryptedKeyset == null || encryptedKeyset.getEncryptedKeyset().size() == 0) {
            throw new GeneralSecurityException("empty keyset");
        }
    }

    private static void assertEnoughKeyMaterial(Keyset keyset) throws GeneralSecurityException {
        if (keyset == null || keyset.getKeyCount() <= 0) {
            throw new GeneralSecurityException("empty keyset");
        }
    }

    private static void assertNoSecretKeyMaterial(Keyset keyset) throws GeneralSecurityException {
        for (Keyset.Key key : keyset.getKeyList()) {
            if (key.getKeyData().getKeyMaterialType() == KeyData.KeyMaterialType.UNKNOWN_KEYMATERIAL || key.getKeyData().getKeyMaterialType() == KeyData.KeyMaterialType.SYMMETRIC || key.getKeyData().getKeyMaterialType() == KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE) {
                throw new GeneralSecurityException(String.format("keyset contains key material of type %s for type url %s", key.getKeyData().getKeyMaterialType().name(), key.getKeyData().getTypeUrl()));
            }
        }
    }

    @Deprecated
    public static final KeysetHandle createFromKey(KeyHandle keyHandle, KeyAccess keyAccess) {
        KeysetManager keysetManagerAdd = KeysetManager.withEmptyKeyset().add(keyHandle);
        keysetManagerAdd.setPrimary(keysetManagerAdd.getKeysetHandle().getKeysetInfo().getKeyInfo(0).getKeyId());
        return keysetManagerAdd.getKeysetHandle();
    }

    private static KeyData createPublicKeyData(KeyData keyData) throws GeneralSecurityException {
        if (keyData.getKeyMaterialType() != KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE) {
            throw new GeneralSecurityException("The keyset contains a non-private key");
        }
        KeyData publicKeyData = Registry.getPublicKeyData(keyData.getTypeUrl(), keyData.getValue());
        validate(publicKeyData);
        return publicKeyData;
    }

    private static Keyset decrypt(EncryptedKeyset encryptedKeyset, Aead aead, byte[] bArr) throws GeneralSecurityException {
        try {
            Keyset from = Keyset.parseFrom(aead.decrypt(encryptedKeyset.getEncryptedKeyset().toByteArray(), bArr), ExtensionRegistryLite.getEmptyRegistry());
            assertEnoughKeyMaterial(from);
            return from;
        } catch (InvalidProtocolBufferException unused) {
            throw new GeneralSecurityException("invalid keyset, corrupted key material");
        }
    }

    private static EncryptedKeyset encrypt(Keyset keyset, Aead aead, byte[] bArr) throws GeneralSecurityException {
        byte[] bArrEncrypt = aead.encrypt(keyset.toByteArray(), bArr);
        try {
            if (Keyset.parseFrom(aead.decrypt(bArrEncrypt, bArr), ExtensionRegistryLite.getEmptyRegistry()).equals(keyset)) {
                return EncryptedKeyset.newBuilder().setEncryptedKeyset(ByteString.copyFrom(bArrEncrypt)).setKeysetInfo(Util.getKeysetInfo(keyset)).build();
            }
            throw new GeneralSecurityException("cannot encrypt keyset");
        } catch (InvalidProtocolBufferException unused) {
            throw new GeneralSecurityException("invalid keyset, corrupted key material");
        }
    }

    private Entry entryByIndex(int i2) {
        if (this.entries.get(i2) != null) {
            return this.entries.get(i2);
        }
        throw new IllegalStateException("Keyset-Entry at position i has wrong status or key parsing failed");
    }

    public static final KeysetHandle fromKeyset(Keyset keyset) throws GeneralSecurityException {
        assertEnoughKeyMaterial(keyset);
        return new KeysetHandle(keyset, getEntriesFromKeyset(keyset));
    }

    public static final KeysetHandle fromKeysetAndAnnotations(Keyset keyset, MonitoringAnnotations monitoringAnnotations) throws GeneralSecurityException {
        assertEnoughKeyMaterial(keyset);
        return new KeysetHandle(keyset, getEntriesFromKeyset(keyset), monitoringAnnotations);
    }

    public static Builder.Entry generateEntryFromParameters(Parameters parameters) {
        return new Builder.Entry(parameters, (AnonymousClass1) null);
    }

    public static Builder.Entry generateEntryFromParametersName(String str) throws GeneralSecurityException {
        if (Registry.keyTemplateMap().containsKey(str)) {
            return new Builder.Entry(MutableSerializationRegistry.globalInstance().parseParametersWithLegacyFallback(ProtoParametersSerialization.create(Registry.keyTemplateMap().get(str).getProto())), (AnonymousClass1) null);
        }
        throw new GeneralSecurityException("cannot find key template: " + str);
    }

    public static final KeysetHandle generateNew(KeyTemplate keyTemplate) {
        return newBuilder().addEntry(generateEntryFromParameters(new LegacyProtoParameters(ProtoParametersSerialization.create(keyTemplate.getProto()))).makePrimary().withRandomId()).build();
    }

    private static List<Entry> getEntriesFromKeyset(Keyset keyset) {
        ArrayList arrayList = new ArrayList(keyset.getKeyCount());
        for (Keyset.Key key : keyset.getKeyList()) {
            int keyId = key.getKeyId();
            try {
                arrayList.add(new Entry(MutableSerializationRegistry.globalInstance().parseKeyWithLegacyFallback(toProtoKeySerialization(key), InsecureSecretKeyAccess.get()), parseStatus(key.getStatus()), keyId, keyId == keyset.getPrimaryKeyId(), null));
            } catch (GeneralSecurityException unused) {
                arrayList.add(null);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    @Nullable
    private <B> B getFullPrimitiveOrNull(Key key, Class<B> cls) {
        try {
            return (B) Registry.getFullPrimitive(key, cls);
        } catch (GeneralSecurityException unused) {
            return null;
        }
    }

    @Nullable
    private static <B> B getLegacyPrimitiveOrNull(Keyset.Key key, Class<B> cls) throws GeneralSecurityException {
        try {
            return (B) Registry.getPrimitive(key.getKeyData(), cls);
        } catch (GeneralSecurityException e2) {
            if (e2.getMessage().contains("No key manager found for key type ") || e2.getMessage().contains(" not supported by key manager of type ")) {
                return null;
            }
            throw e2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <B, P> P getPrimitiveWithKnownInputPrimitive(Class<P> cls, Class<B> cls2) throws GeneralSecurityException {
        Util.validateKeyset(this.keyset);
        PrimitiveSet.Builder builderNewBuilder = PrimitiveSet.newBuilder(cls2);
        builderNewBuilder.setAnnotations(this.annotations);
        for (int i2 = 0; i2 < size(); i2++) {
            Keyset.Key key = this.keyset.getKey(i2);
            if (key.getStatus().equals(KeyStatusType.ENABLED)) {
                Object legacyPrimitiveOrNull = getLegacyPrimitiveOrNull(key, cls2);
                Object fullPrimitiveOrNull = this.entries.get(i2) != null ? getFullPrimitiveOrNull(this.entries.get(i2).getKey(), cls2) : null;
                if (key.getKeyId() == this.keyset.getPrimaryKeyId()) {
                    builderNewBuilder.addPrimaryFullPrimitiveAndOptionalPrimitive(fullPrimitiveOrNull, legacyPrimitiveOrNull, key);
                } else {
                    builderNewBuilder.addFullPrimitiveAndOptionalPrimitive(fullPrimitiveOrNull, legacyPrimitiveOrNull, key);
                }
            }
        }
        return (P) Registry.wrap(builderNewBuilder.build(), cls);
    }

    public static Builder.Entry importKey(Key key) {
        Builder.Entry entry = new Builder.Entry(key, (AnonymousClass1) null);
        Integer idRequirementOrNull = key.getIdRequirementOrNull();
        if (idRequirementOrNull != null) {
            entry.withFixedId(idRequirementOrNull.intValue());
        }
        return entry;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    private static KeyStatus parseStatus(KeyStatusType keyStatusType) throws GeneralSecurityException {
        int i2 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$KeyStatusType[keyStatusType.ordinal()];
        if (i2 == 1) {
            return KeyStatus.ENABLED;
        }
        if (i2 == 2) {
            return KeyStatus.DISABLED;
        }
        if (i2 == 3) {
            return KeyStatus.DESTROYED;
        }
        throw new GeneralSecurityException("Unknown key status");
    }

    public static final KeysetHandle read(KeysetReader keysetReader, Aead aead) {
        return readWithAssociatedData(keysetReader, aead, new byte[0]);
    }

    public static final KeysetHandle readNoSecret(KeysetReader keysetReader) throws GeneralSecurityException {
        try {
            Keyset keyset = keysetReader.read();
            assertNoSecretKeyMaterial(keyset);
            return fromKeyset(keyset);
        } catch (InvalidProtocolBufferException unused) {
            throw new GeneralSecurityException("invalid keyset");
        }
    }

    public static final KeysetHandle readWithAssociatedData(KeysetReader keysetReader, Aead aead, byte[] bArr) throws GeneralSecurityException {
        EncryptedKeyset encrypted = keysetReader.readEncrypted();
        assertEnoughEncryptedKeyMaterial(encrypted);
        return fromKeyset(decrypt(encrypted, aead, bArr));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static KeyStatusType serializeStatus(KeyStatus keyStatus) {
        if (KeyStatus.ENABLED.equals(keyStatus)) {
            return KeyStatusType.ENABLED;
        }
        if (KeyStatus.DISABLED.equals(keyStatus)) {
            return KeyStatusType.DISABLED;
        }
        if (KeyStatus.DESTROYED.equals(keyStatus)) {
            return KeyStatusType.DESTROYED;
        }
        throw new IllegalStateException("Unknown key status");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Keyset.Key toKeysetKey(int i2, KeyStatusType keyStatusType, ProtoKeySerialization protoKeySerialization) {
        return Keyset.Key.newBuilder().setKeyData(KeyData.newBuilder().setTypeUrl(protoKeySerialization.getTypeUrl()).setValue(protoKeySerialization.getValue()).setKeyMaterialType(protoKeySerialization.getKeyMaterialType())).setStatus(keyStatusType).setKeyId(i2).setOutputPrefixType(protoKeySerialization.getOutputPrefixType()).build();
    }

    private static ProtoKeySerialization toProtoKeySerialization(Keyset.Key key) {
        try {
            return ProtoKeySerialization.create(key.getKeyData().getTypeUrl(), key.getKeyData().getValue(), key.getKeyData().getKeyMaterialType(), key.getOutputPrefixType(), key.getOutputPrefixType() == OutputPrefixType.RAW ? null : Integer.valueOf(key.getKeyId()));
        } catch (GeneralSecurityException e2) {
            throw new TinkBugException("Creating a protokey serialization failed", e2);
        }
    }

    private static void validate(KeyData keyData) {
        Registry.getPrimitive(keyData);
    }

    public Entry getAt(int i2) {
        if (i2 >= 0 && i2 < size()) {
            return entryByIndex(i2);
        }
        throw new IndexOutOfBoundsException("Invalid index " + i2 + " for keyset of size " + size());
    }

    @Deprecated
    public List<KeyHandle> getKeys() {
        ArrayList arrayList = new ArrayList();
        for (Keyset.Key key : this.keyset.getKeyList()) {
            arrayList.add(new InternalKeyHandle(new ProtoKey(key.getKeyData(), KeyTemplate.fromProto(key.getOutputPrefixType())), key.getStatus(), key.getKeyId()));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public Keyset getKeyset() {
        return this.keyset;
    }

    public KeysetInfo getKeysetInfo() {
        return Util.getKeysetInfo(this.keyset);
    }

    public Entry getPrimary() {
        for (int i2 = 0; i2 < this.keyset.getKeyCount(); i2++) {
            if (this.keyset.getKey(i2).getKeyId() == this.keyset.getPrimaryKeyId()) {
                Entry entryEntryByIndex = entryByIndex(i2);
                if (entryEntryByIndex.getStatus() == KeyStatus.ENABLED) {
                    return entryEntryByIndex;
                }
                throw new IllegalStateException("Keyset has primary which isn't enabled");
            }
        }
        throw new IllegalStateException("Keyset has no primary");
    }

    public <P> P getPrimitive(Class<P> cls) throws GeneralSecurityException {
        Class<?> inputPrimitive = Registry.getInputPrimitive(cls);
        if (inputPrimitive != null) {
            return (P) getPrimitiveWithKnownInputPrimitive(cls, inputPrimitive);
        }
        throw new GeneralSecurityException("No wrapper found for ".concat(cls.getName()));
    }

    public KeysetHandle getPublicKeysetHandle() throws GeneralSecurityException {
        if (this.keyset == null) {
            throw new GeneralSecurityException("cleartext keyset is not available");
        }
        Keyset.Builder builderNewBuilder = Keyset.newBuilder();
        for (Keyset.Key key : this.keyset.getKeyList()) {
            builderNewBuilder.addKey(key.toBuilder().setKeyData(createPublicKeyData(key.getKeyData())).build());
        }
        builderNewBuilder.setPrimaryKeyId(this.keyset.getPrimaryKeyId());
        return fromKeyset(builderNewBuilder.build());
    }

    @Deprecated
    public KeyHandle primaryKey() throws GeneralSecurityException {
        int primaryKeyId = this.keyset.getPrimaryKeyId();
        for (Keyset.Key key : this.keyset.getKeyList()) {
            if (key.getKeyId() == primaryKeyId) {
                return new InternalKeyHandle(new ProtoKey(key.getKeyData(), KeyTemplate.fromProto(key.getOutputPrefixType())), key.getStatus(), key.getKeyId());
            }
        }
        throw new GeneralSecurityException("No primary key found in keyset.");
    }

    public int size() {
        return this.keyset.getKeyCount();
    }

    public String toString() {
        return getKeysetInfo().toString();
    }

    public void write(KeysetWriter keysetWriter, Aead aead) {
        writeWithAssociatedData(keysetWriter, aead, new byte[0]);
    }

    public void writeNoSecret(KeysetWriter keysetWriter) throws GeneralSecurityException {
        assertNoSecretKeyMaterial(this.keyset);
        keysetWriter.write(this.keyset);
    }

    public void writeWithAssociatedData(KeysetWriter keysetWriter, Aead aead, byte[] bArr) {
        keysetWriter.write(encrypt(this.keyset, aead, bArr));
    }

    private KeysetHandle(Keyset keyset, List<Entry> list, MonitoringAnnotations monitoringAnnotations) {
        this.keyset = keyset;
        this.entries = list;
        this.annotations = monitoringAnnotations;
    }

    @Deprecated
    public static final KeysetHandle generateNew(com.google.crypto.tink.proto.KeyTemplate keyTemplate) {
        return newBuilder().addEntry(generateEntryFromParameters(new LegacyProtoParameters(ProtoParametersSerialization.create(keyTemplate))).makePrimary().withRandomId()).build();
    }

    public static Builder newBuilder(KeysetHandle keysetHandle) {
        Builder builder = new Builder();
        for (int i2 = 0; i2 < keysetHandle.size(); i2++) {
            Entry entryEntryByIndex = keysetHandle.entryByIndex(i2);
            Builder.Entry entryWithFixedId = importKey(entryEntryByIndex.getKey()).withFixedId(entryEntryByIndex.getId());
            entryWithFixedId.setStatus(entryEntryByIndex.getStatus());
            if (entryEntryByIndex.isPrimary()) {
                entryWithFixedId.makePrimary();
            }
            builder.addEntry(entryWithFixedId);
        }
        return builder;
    }

    @Deprecated
    public static final KeysetHandle readNoSecret(byte[] bArr) throws GeneralSecurityException {
        try {
            Keyset from = Keyset.parseFrom(bArr, ExtensionRegistryLite.getEmptyRegistry());
            assertNoSecretKeyMaterial(from);
            return fromKeyset(from);
        } catch (InvalidProtocolBufferException unused) {
            throw new GeneralSecurityException("invalid keyset");
        }
    }
}
