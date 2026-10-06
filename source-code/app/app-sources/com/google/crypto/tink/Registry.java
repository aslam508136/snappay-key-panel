package com.google.crypto.tink;

import androidx.activity.c;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.MutablePrimitiveRegistry;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class Registry {
    private static final Logger logger = Logger.getLogger(Registry.class.getName());
    private static final AtomicReference<KeyManagerRegistry> keyManagerRegistry = new AtomicReference<>(new KeyManagerRegistry());
    private static final ConcurrentMap<String, KeyDeriverContainer> keyDeriverMap = new ConcurrentHashMap();
    private static final ConcurrentMap<String, Boolean> newKeyAllowedMap = new ConcurrentHashMap();
    private static final ConcurrentMap<String, Catalogue<?>> catalogueMap = new ConcurrentHashMap();
    private static final ConcurrentMap<String, KeyTemplate> keyTemplateMap = new ConcurrentHashMap();

    public interface KeyDeriverContainer {
        KeyData deriveKey(ByteString byteString, InputStream inputStream);
    }

    private Registry() {
    }

    @Deprecated
    public static synchronized void addCatalogue(String str, Catalogue<?> catalogue) {
        try {
            if (str == null) {
                throw new IllegalArgumentException("catalogueName must be non-null.");
            }
            if (catalogue == null) {
                throw new IllegalArgumentException("catalogue must be non-null.");
            }
            ConcurrentMap<String, Catalogue<?>> concurrentMap = catalogueMap;
            Locale locale = Locale.US;
            if (concurrentMap.containsKey(str.toLowerCase(locale))) {
                if (!catalogue.getClass().getName().equals(concurrentMap.get(str.toLowerCase(locale)).getClass().getName())) {
                    logger.warning("Attempted overwrite of a catalogueName catalogue for name ".concat(str));
                    throw new GeneralSecurityException("catalogue for name " + str + " has been already registered");
                }
            }
            concurrentMap.put(str.toLowerCase(locale), catalogue);
        } catch (Throwable th) {
            throw th;
        }
    }

    private static <KeyProtoT extends MessageLite> KeyDeriverContainer createDeriverFor(final KeyTypeManager<KeyProtoT> keyTypeManager) {
        return new KeyDeriverContainer() { // from class: com.google.crypto.tink.Registry.1
            /* JADX WARN: Unknown type variable: KeyProtoT in type: com.google.crypto.tink.internal.KeyTypeManager$KeyFactory<KeyFormatProtoT extends com.google.crypto.tink.shaded.protobuf.MessageLite, KeyProtoT> */
            private <KeyFormatProtoT extends MessageLite> MessageLite deriveKeyWithFactory(ByteString byteString, InputStream inputStream, KeyTypeManager.KeyFactory<KeyFormatProtoT, KeyProtoT> keyFactory) throws GeneralSecurityException {
                try {
                    MessageLite keyFormat = keyFactory.parseKeyFormat(byteString);
                    keyFactory.validateKeyFormat(keyFormat);
                    return keyFactory.deriveKey(keyFormat, inputStream);
                } catch (InvalidProtocolBufferException e2) {
                    throw new GeneralSecurityException("parsing key format failed in deriveKey", e2);
                }
            }

            @Override // com.google.crypto.tink.Registry.KeyDeriverContainer
            public KeyData deriveKey(ByteString byteString, InputStream inputStream) throws GeneralSecurityException {
                return KeyData.newBuilder().setTypeUrl(keyTypeManager.getKeyType()).setValue(deriveKeyWithFactory(byteString, inputStream, keyTypeManager.keyFactory()).toByteString()).setKeyMaterialType(keyTypeManager.keyMaterialType()).build();
            }
        };
    }

    public static synchronized KeyData deriveKey(com.google.crypto.tink.proto.KeyTemplate keyTemplate, InputStream inputStream) {
        String typeUrl;
        ConcurrentMap<String, KeyDeriverContainer> concurrentMap;
        typeUrl = keyTemplate.getTypeUrl();
        concurrentMap = keyDeriverMap;
        if (!concurrentMap.containsKey(typeUrl)) {
            throw new GeneralSecurityException("No keymanager registered or key manager cannot derive keys for " + typeUrl);
        }
        return concurrentMap.get(typeUrl).deriveKey(keyTemplate.getValue(), inputStream);
    }

    private static synchronized <KeyProtoT extends MessageLite, KeyFormatProtoT extends MessageLite> void ensureKeyManagerInsertable(String str, Map<String, KeyTypeManager.KeyFactory.KeyFormat<KeyFormatProtoT>> map, boolean z2) {
        if (z2) {
            try {
                ConcurrentMap<String, Boolean> concurrentMap = newKeyAllowedMap;
                if (concurrentMap.containsKey(str) && !concurrentMap.get(str).booleanValue()) {
                    throw new GeneralSecurityException("New keys are already disallowed for key type " + str);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (z2) {
            if (keyManagerRegistry.get().typeUrlExists(str)) {
                for (Map.Entry<String, KeyTypeManager.KeyFactory.KeyFormat<KeyFormatProtoT>> entry : map.entrySet()) {
                    if (!keyTemplateMap.containsKey(entry.getKey())) {
                        throw new GeneralSecurityException("Attempted to register a new key template " + entry.getKey() + " from an existing key manager of type " + str);
                    }
                }
            } else {
                for (Map.Entry<String, KeyTypeManager.KeyFactory.KeyFormat<KeyFormatProtoT>> entry2 : map.entrySet()) {
                    if (keyTemplateMap.containsKey(entry2.getKey())) {
                        throw new GeneralSecurityException("Attempted overwrite of a registered key template " + entry2.getKey());
                    }
                }
            }
        }
    }

    @Deprecated
    public static Catalogue<?> getCatalogue(String str) throws GeneralSecurityException {
        StringBuilder sb;
        String str2;
        if (str == null) {
            throw new IllegalArgumentException("catalogueName must be non-null.");
        }
        ConcurrentMap<String, Catalogue<?>> concurrentMap = catalogueMap;
        Locale locale = Locale.US;
        Catalogue<?> catalogue = concurrentMap.get(str.toLowerCase(locale));
        if (catalogue != null) {
            return catalogue;
        }
        String string = String.format("no catalogue found for %s. ", str);
        if (str.toLowerCase(locale).startsWith("tinkaead")) {
            string = string + "Maybe call AeadConfig.register().";
        }
        if (str.toLowerCase(locale).startsWith("tinkdeterministicaead")) {
            sb = new StringBuilder();
            sb.append(string);
            str2 = "Maybe call DeterministicAeadConfig.register().";
        } else if (str.toLowerCase(locale).startsWith("tinkstreamingaead")) {
            sb = new StringBuilder();
            sb.append(string);
            str2 = "Maybe call StreamingAeadConfig.register().";
        } else if (str.toLowerCase(locale).startsWith("tinkhybriddecrypt") || str.toLowerCase(locale).startsWith("tinkhybridencrypt")) {
            sb = new StringBuilder();
            sb.append(string);
            str2 = "Maybe call HybridConfig.register().";
        } else if (str.toLowerCase(locale).startsWith("tinkmac")) {
            sb = new StringBuilder();
            sb.append(string);
            str2 = "Maybe call MacConfig.register().";
        } else {
            if (!str.toLowerCase(locale).startsWith("tinkpublickeysign") && !str.toLowerCase(locale).startsWith("tinkpublickeyverify")) {
                if (str.toLowerCase(locale).startsWith("tink")) {
                    sb = new StringBuilder();
                    sb.append(string);
                    str2 = "Maybe call TinkConfig.register().";
                }
                throw new GeneralSecurityException(string);
            }
            sb = new StringBuilder();
            sb.append(string);
            str2 = "Maybe call SignatureConfig.register().";
        }
        sb.append(str2);
        string = sb.toString();
        throw new GeneralSecurityException(string);
    }

    public static <KeyT extends Key, P> P getFullPrimitive(KeyT keyt, Class<P> cls) {
        return (P) MutablePrimitiveRegistry.globalInstance().getPrimitive(keyt, cls);
    }

    @Nullable
    public static Class<?> getInputPrimitive(Class<?> cls) {
        try {
            return MutablePrimitiveRegistry.globalInstance().getInputPrimitiveClass(cls);
        } catch (GeneralSecurityException unused) {
            return null;
        }
    }

    @Deprecated
    public static <P> KeyManager<P> getKeyManager(String str) {
        return keyManagerRegistry.get().getKeyManager(str);
    }

    @Deprecated
    public static <P> P getPrimitive(KeyData keyData) {
        return (P) getPrimitive(keyData.getTypeUrl(), keyData.getValue());
    }

    public static KeyData getPublicKeyData(String str, ByteString byteString) throws GeneralSecurityException {
        KeyManager keyManager = getKeyManager(str);
        if (keyManager instanceof PrivateKeyManager) {
            return ((PrivateKeyManager) keyManager).getPublicKeyData(byteString);
        }
        throw new GeneralSecurityException(c.a("manager for key type ", str, " is not a PrivateKeyManager"));
    }

    public static KeyManager<?> getUntypedKeyManager(String str) {
        return keyManagerRegistry.get().getUntypedKeyManager(str);
    }

    public static synchronized Map<String, KeyTemplate> keyTemplateMap() {
        return Collections.unmodifiableMap(keyTemplateMap);
    }

    public static synchronized List<String> keyTemplates() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        arrayList.addAll(keyTemplateMap.keySet());
        return Collections.unmodifiableList(arrayList);
    }

    public static synchronized MessageLite newKey(com.google.crypto.tink.proto.KeyTemplate keyTemplate) {
        KeyManager<?> untypedKeyManager;
        untypedKeyManager = getUntypedKeyManager(keyTemplate.getTypeUrl());
        if (!newKeyAllowedMap.get(keyTemplate.getTypeUrl()).booleanValue()) {
            throw new GeneralSecurityException("newKey-operation not permitted for key type " + keyTemplate.getTypeUrl());
        }
        return untypedKeyManager.newKey(keyTemplate.getValue());
    }

    public static synchronized KeyData newKeyData(KeyTemplate keyTemplate) {
        return newKeyData(keyTemplate.getProto());
    }

    public static MessageLite parseKeyData(KeyData keyData) {
        return keyManagerRegistry.get().parseKeyData(keyData);
    }

    public static synchronized <KeyProtoT extends MessageLite, PublicKeyProtoT extends MessageLite> void registerAsymmetricKeyManagers(PrivateKeyTypeManager<KeyProtoT, PublicKeyProtoT> privateKeyTypeManager, KeyTypeManager<PublicKeyProtoT> keyTypeManager, boolean z2) {
        try {
            if (privateKeyTypeManager == null || keyTypeManager == null) {
                throw new IllegalArgumentException("given key managers must be non-null.");
            }
            AtomicReference<KeyManagerRegistry> atomicReference = keyManagerRegistry;
            KeyManagerRegistry keyManagerRegistry2 = new KeyManagerRegistry(atomicReference.get());
            keyManagerRegistry2.registerAsymmetricKeyManagers(privateKeyTypeManager, keyTypeManager);
            String keyType = privateKeyTypeManager.getKeyType();
            String keyType2 = keyTypeManager.getKeyType();
            ensureKeyManagerInsertable(keyType, z2 ? privateKeyTypeManager.keyFactory().keyFormats() : Collections.emptyMap(), z2);
            ensureKeyManagerInsertable(keyType2, Collections.emptyMap(), false);
            if (!atomicReference.get().typeUrlExists(keyType)) {
                keyDeriverMap.put(keyType, createDeriverFor(privateKeyTypeManager));
                if (z2) {
                    registerKeyTemplates(privateKeyTypeManager.getKeyType(), privateKeyTypeManager.keyFactory().keyFormats());
                }
            }
            ConcurrentMap<String, Boolean> concurrentMap = newKeyAllowedMap;
            concurrentMap.put(keyType, Boolean.valueOf(z2));
            concurrentMap.put(keyType2, Boolean.FALSE);
            atomicReference.set(keyManagerRegistry2);
        } catch (Throwable th) {
            throw th;
        }
    }

    public static synchronized <P> void registerKeyManager(KeyManager<P> keyManager) {
        registerKeyManager((KeyManager) keyManager, true);
    }

    private static <KeyFormatProtoT extends MessageLite> void registerKeyTemplates(String str, Map<String, KeyTypeManager.KeyFactory.KeyFormat<KeyFormatProtoT>> map) {
        for (Map.Entry<String, KeyTypeManager.KeyFactory.KeyFormat<KeyFormatProtoT>> entry : map.entrySet()) {
            keyTemplateMap.put(entry.getKey(), KeyTemplate.create(str, entry.getValue().keyFormat.toByteArray(), entry.getValue().outputPrefixType));
        }
    }

    public static synchronized <B, P> void registerPrimitiveWrapper(PrimitiveWrapper<B, P> primitiveWrapper) {
        MutablePrimitiveRegistry.globalInstance().registerPrimitiveWrapper(primitiveWrapper);
    }

    public static synchronized void reset() {
        keyManagerRegistry.set(new KeyManagerRegistry());
        MutablePrimitiveRegistry.resetGlobalInstanceTestOnly();
        keyDeriverMap.clear();
        newKeyAllowedMap.clear();
        catalogueMap.clear();
        keyTemplateMap.clear();
    }

    public static synchronized void restrictToFipsIfEmpty() {
        if (TinkFipsUtil.useOnlyFips()) {
            return;
        }
        if (!keyManagerRegistry.get().isEmpty()) {
            throw new GeneralSecurityException("Could not enable FIPS mode as Registry is not empty.");
        }
        TinkFipsUtil.setFipsRestricted();
    }

    public static <P> P wrap(PrimitiveSet<P> primitiveSet) {
        return (P) wrap(primitiveSet, primitiveSet.getPrimitiveClass());
    }

    public static <P> KeyManager<P> getKeyManager(String str, Class<P> cls) {
        return keyManagerRegistry.get().getKeyManager(str, cls);
    }

    public static <P> P getPrimitive(KeyData keyData, Class<P> cls) {
        return (P) getPrimitive(keyData.getTypeUrl(), keyData.getValue(), cls);
    }

    public static synchronized MessageLite newKey(String str, MessageLite messageLite) {
        KeyManager keyManager;
        keyManager = getKeyManager(str);
        if (!newKeyAllowedMap.get(str).booleanValue()) {
            throw new GeneralSecurityException("newKey-operation not permitted for key type " + str);
        }
        return keyManager.newKey(messageLite);
    }

    public static synchronized KeyData newKeyData(com.google.crypto.tink.proto.KeyTemplate keyTemplate) {
        KeyManager<?> untypedKeyManager;
        untypedKeyManager = getUntypedKeyManager(keyTemplate.getTypeUrl());
        if (!newKeyAllowedMap.get(keyTemplate.getTypeUrl()).booleanValue()) {
            throw new GeneralSecurityException("newKey-operation not permitted for key type " + keyTemplate.getTypeUrl());
        }
        return untypedKeyManager.newKeyData(keyTemplate.getValue());
    }

    public static synchronized <P> void registerKeyManager(KeyManager<P> keyManager, boolean z2) {
        try {
            if (keyManager == null) {
                throw new IllegalArgumentException("key manager must be non-null.");
            }
            AtomicReference<KeyManagerRegistry> atomicReference = keyManagerRegistry;
            KeyManagerRegistry keyManagerRegistry2 = new KeyManagerRegistry(atomicReference.get());
            keyManagerRegistry2.registerKeyManager(keyManager);
            if (!TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS.isCompatible()) {
                throw new GeneralSecurityException("Registering key managers is not supported in FIPS mode");
            }
            String keyType = keyManager.getKeyType();
            ensureKeyManagerInsertable(keyType, Collections.emptyMap(), z2);
            newKeyAllowedMap.put(keyType, Boolean.valueOf(z2));
            atomicReference.set(keyManagerRegistry2);
        } catch (Throwable th) {
            throw th;
        }
    }

    public static <B, P> P wrap(PrimitiveSet<B> primitiveSet, Class<P> cls) {
        return (P) MutablePrimitiveRegistry.globalInstance().wrap(primitiveSet, cls);
    }

    @Deprecated
    public static <P> P getPrimitive(String str, ByteString byteString) {
        return keyManagerRegistry.get().getKeyManager(str).getPrimitive(byteString);
    }

    public static synchronized <KeyProtoT extends MessageLite> void registerKeyManager(KeyTypeManager<KeyProtoT> keyTypeManager, boolean z2) {
        try {
            if (keyTypeManager == null) {
                throw new IllegalArgumentException("key manager must be non-null.");
            }
            AtomicReference<KeyManagerRegistry> atomicReference = keyManagerRegistry;
            KeyManagerRegistry keyManagerRegistry2 = new KeyManagerRegistry(atomicReference.get());
            keyManagerRegistry2.registerKeyManager(keyTypeManager);
            String keyType = keyTypeManager.getKeyType();
            ensureKeyManagerInsertable(keyType, z2 ? keyTypeManager.keyFactory().keyFormats() : Collections.emptyMap(), z2);
            if (!atomicReference.get().typeUrlExists(keyType)) {
                keyDeriverMap.put(keyType, createDeriverFor(keyTypeManager));
                if (z2) {
                    registerKeyTemplates(keyType, keyTypeManager.keyFactory().keyFormats());
                }
            }
            newKeyAllowedMap.put(keyType, Boolean.valueOf(z2));
            atomicReference.set(keyManagerRegistry2);
        } catch (Throwable th) {
            throw th;
        }
    }

    public static <P> P getPrimitive(String str, ByteString byteString, Class<P> cls) {
        return keyManagerRegistry.get().getKeyManager(str, cls).getPrimitive(byteString);
    }

    @Deprecated
    public static synchronized <P> void registerKeyManager(String str, KeyManager<P> keyManager) {
        registerKeyManager(str, keyManager, true);
    }

    @Deprecated
    public static <P> P getPrimitive(String str, MessageLite messageLite) {
        return keyManagerRegistry.get().getKeyManager(str).getPrimitive(messageLite);
    }

    @Deprecated
    public static synchronized <P> void registerKeyManager(String str, KeyManager<P> keyManager, boolean z2) {
        try {
            if (keyManager == null) {
                throw new IllegalArgumentException("key manager must be non-null.");
            }
            if (!str.equals(keyManager.getKeyType())) {
                throw new GeneralSecurityException("Manager does not support key type " + str + ".");
            }
            registerKeyManager(keyManager, z2);
        } catch (Throwable th) {
            throw th;
        }
    }

    public static <P> P getPrimitive(String str, MessageLite messageLite, Class<P> cls) {
        return keyManagerRegistry.get().getKeyManager(str, cls).getPrimitive(messageLite);
    }

    @Deprecated
    public static <P> P getPrimitive(String str, byte[] bArr) {
        return (P) getPrimitive(str, ByteString.copyFrom(bArr));
    }

    public static <P> P getPrimitive(String str, byte[] bArr, Class<P> cls) {
        return (P) getPrimitive(str, ByteString.copyFrom(bArr), cls);
    }
}
