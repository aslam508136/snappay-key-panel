package com.google.crypto.tink;

import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
final class KeyManagerRegistry {
    private static final Logger logger = Logger.getLogger(KeyManagerRegistry.class.getName());
    private final ConcurrentMap<String, KeyManagerContainer> keyManagerMap;

    public interface KeyManagerContainer {
        Class<?> getImplementingClass();

        <P> KeyManager<P> getKeyManager(Class<P> cls);

        KeyManager<?> getUntypedKeyManager();

        MessageLite parseKey(ByteString byteString);

        Class<?> publicKeyManagerClassOrNull();

        Set<Class<?>> supportedPrimitives();
    }

    public KeyManagerRegistry() {
        this.keyManagerMap = new ConcurrentHashMap();
    }

    private static <T> T checkNotNull(T t2) {
        t2.getClass();
        return t2;
    }

    private static <P> KeyManagerContainer createContainerFor(final KeyManager<P> keyManager) {
        return new KeyManagerContainer() { // from class: com.google.crypto.tink.KeyManagerRegistry.1
            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public Class<?> getImplementingClass() {
                return keyManager.getClass();
            }

            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public <Q> KeyManager<Q> getKeyManager(Class<Q> cls) {
                if (keyManager.getPrimitiveClass().equals(cls)) {
                    return keyManager;
                }
                throw new InternalError("This should never be called, as we always first check supportedPrimitives.");
            }

            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public KeyManager<?> getUntypedKeyManager() {
                return keyManager;
            }

            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public MessageLite parseKey(ByteString byteString) {
                return null;
            }

            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public Class<?> publicKeyManagerClassOrNull() {
                return null;
            }

            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public Set<Class<?>> supportedPrimitives() {
                return Collections.singleton(keyManager.getPrimitiveClass());
            }
        };
    }

    private static <KeyProtoT extends MessageLite, PublicKeyProtoT extends MessageLite> KeyManagerContainer createPrivateKeyContainerFor(final PrivateKeyTypeManager<KeyProtoT, PublicKeyProtoT> privateKeyTypeManager, final KeyTypeManager<PublicKeyProtoT> keyTypeManager) {
        return new KeyManagerContainer() { // from class: com.google.crypto.tink.KeyManagerRegistry.3
            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public Class<?> getImplementingClass() {
                return privateKeyTypeManager.getClass();
            }

            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public <Q> KeyManager<Q> getKeyManager(Class<Q> cls) throws GeneralSecurityException {
                try {
                    return new PrivateKeyManagerImpl(privateKeyTypeManager, keyTypeManager, cls);
                } catch (IllegalArgumentException e2) {
                    throw new GeneralSecurityException("Primitive type not supported", e2);
                }
            }

            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public KeyManager<?> getUntypedKeyManager() {
                PrivateKeyTypeManager privateKeyTypeManager2 = privateKeyTypeManager;
                return new PrivateKeyManagerImpl(privateKeyTypeManager2, keyTypeManager, privateKeyTypeManager2.firstSupportedPrimitiveClass());
            }

            /* JADX WARN: Type inference failed for: r2v1, types: [com.google.crypto.tink.shaded.protobuf.MessageLite] */
            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public MessageLite parseKey(ByteString byteString) {
                ?? key = privateKeyTypeManager.parseKey(byteString);
                privateKeyTypeManager.validateKey(key);
                return key;
            }

            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public Class<?> publicKeyManagerClassOrNull() {
                return keyTypeManager.getClass();
            }

            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public Set<Class<?>> supportedPrimitives() {
                return privateKeyTypeManager.supportedPrimitives();
            }
        };
    }

    private synchronized KeyManagerContainer getKeyManagerContainerOrThrow(String str) {
        if (!this.keyManagerMap.containsKey(str)) {
            throw new GeneralSecurityException("No key manager found for key type " + str);
        }
        return this.keyManagerMap.get(str);
    }

    private <P> KeyManager<P> getKeyManagerInternal(String str, Class<P> cls) throws GeneralSecurityException {
        KeyManagerContainer keyManagerContainerOrThrow = getKeyManagerContainerOrThrow(str);
        if (cls == null) {
            return (KeyManager<P>) keyManagerContainerOrThrow.getUntypedKeyManager();
        }
        if (keyManagerContainerOrThrow.supportedPrimitives().contains(cls)) {
            return keyManagerContainerOrThrow.getKeyManager(cls);
        }
        throw new GeneralSecurityException("Primitive type " + cls.getName() + " not supported by key manager of type " + keyManagerContainerOrThrow.getImplementingClass() + ", supported primitives: " + toCommaSeparatedString(keyManagerContainerOrThrow.supportedPrimitives()));
    }

    private synchronized <P> void registerKeyManagerContainer(KeyManagerContainer keyManagerContainer, boolean z2) {
        String keyType = keyManagerContainer.getUntypedKeyManager().getKeyType();
        KeyManagerContainer keyManagerContainer2 = this.keyManagerMap.get(keyType);
        if (keyManagerContainer2 != null && !keyManagerContainer2.getImplementingClass().equals(keyManagerContainer.getImplementingClass())) {
            logger.warning("Attempted overwrite of a registered key manager for key type " + keyType);
            throw new GeneralSecurityException(String.format("typeUrl (%s) is already registered with %s, cannot be re-registered with %s", keyType, keyManagerContainer2.getImplementingClass().getName(), keyManagerContainer.getImplementingClass().getName()));
        }
        if (z2) {
            this.keyManagerMap.put(keyType, keyManagerContainer);
        } else {
            this.keyManagerMap.putIfAbsent(keyType, keyManagerContainer);
        }
    }

    private static String toCommaSeparatedString(Set<Class<?>> set) {
        StringBuilder sb = new StringBuilder();
        boolean z2 = true;
        for (Class<?> cls : set) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append(cls.getCanonicalName());
            z2 = false;
        }
        return sb.toString();
    }

    @Deprecated
    public <P> KeyManager<P> getKeyManager(String str) {
        return getKeyManagerInternal(str, null);
    }

    public KeyManager<?> getUntypedKeyManager(String str) {
        return getKeyManagerContainerOrThrow(str).getUntypedKeyManager();
    }

    public boolean isEmpty() {
        return this.keyManagerMap.isEmpty();
    }

    public MessageLite parseKeyData(KeyData keyData) {
        return getKeyManagerContainerOrThrow(keyData.getTypeUrl()).parseKey(keyData.getValue());
    }

    public synchronized <KeyProtoT extends MessageLite, PublicKeyProtoT extends MessageLite> void registerAsymmetricKeyManagers(PrivateKeyTypeManager<KeyProtoT, PublicKeyProtoT> privateKeyTypeManager, KeyTypeManager<PublicKeyProtoT> keyTypeManager) {
        Class<?> clsPublicKeyManagerClassOrNull;
        TinkFipsUtil.AlgorithmFipsCompatibility algorithmFipsCompatibilityFipsStatus = privateKeyTypeManager.fipsStatus();
        TinkFipsUtil.AlgorithmFipsCompatibility algorithmFipsCompatibilityFipsStatus2 = keyTypeManager.fipsStatus();
        if (!algorithmFipsCompatibilityFipsStatus.isCompatible()) {
            throw new GeneralSecurityException("failed to register key manager " + privateKeyTypeManager.getClass() + " as it is not FIPS compatible.");
        }
        if (!algorithmFipsCompatibilityFipsStatus2.isCompatible()) {
            throw new GeneralSecurityException("failed to register key manager " + keyTypeManager.getClass() + " as it is not FIPS compatible.");
        }
        String keyType = privateKeyTypeManager.getKeyType();
        String keyType2 = keyTypeManager.getKeyType();
        if (this.keyManagerMap.containsKey(keyType) && this.keyManagerMap.get(keyType).publicKeyManagerClassOrNull() != null && (clsPublicKeyManagerClassOrNull = this.keyManagerMap.get(keyType).publicKeyManagerClassOrNull()) != null && !clsPublicKeyManagerClassOrNull.getName().equals(keyTypeManager.getClass().getName())) {
            logger.warning("Attempted overwrite of a registered key manager for key type " + keyType + " with inconsistent public key type " + keyType2);
            throw new GeneralSecurityException(String.format("public key manager corresponding to %s is already registered with %s, cannot be re-registered with %s", privateKeyTypeManager.getClass().getName(), clsPublicKeyManagerClassOrNull.getName(), keyTypeManager.getClass().getName()));
        }
        registerKeyManagerContainer(createPrivateKeyContainerFor(privateKeyTypeManager, keyTypeManager), true);
        registerKeyManagerContainer(createContainerFor(keyTypeManager), false);
    }

    public synchronized <P> void registerKeyManager(KeyManager<P> keyManager) {
        if (!TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS.isCompatible()) {
            throw new GeneralSecurityException("Registering key managers is not supported in FIPS mode");
        }
        registerKeyManagerContainer(createContainerFor(keyManager), false);
    }

    public boolean typeUrlExists(String str) {
        return this.keyManagerMap.containsKey(str);
    }

    public KeyManagerRegistry(KeyManagerRegistry keyManagerRegistry) {
        this.keyManagerMap = new ConcurrentHashMap(keyManagerRegistry.keyManagerMap);
    }

    private static <KeyProtoT extends MessageLite> KeyManagerContainer createContainerFor(final KeyTypeManager<KeyProtoT> keyTypeManager) {
        return new KeyManagerContainer() { // from class: com.google.crypto.tink.KeyManagerRegistry.2
            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public Class<?> getImplementingClass() {
                return keyTypeManager.getClass();
            }

            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public <Q> KeyManager<Q> getKeyManager(Class<Q> cls) throws GeneralSecurityException {
                try {
                    return new KeyManagerImpl(keyTypeManager, cls);
                } catch (IllegalArgumentException e2) {
                    throw new GeneralSecurityException("Primitive type not supported", e2);
                }
            }

            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public KeyManager<?> getUntypedKeyManager() {
                KeyTypeManager keyTypeManager2 = keyTypeManager;
                return new KeyManagerImpl(keyTypeManager2, keyTypeManager2.firstSupportedPrimitiveClass());
            }

            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public MessageLite parseKey(ByteString byteString) {
                MessageLite key = keyTypeManager.parseKey(byteString);
                keyTypeManager.validateKey(key);
                return key;
            }

            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public Class<?> publicKeyManagerClassOrNull() {
                return null;
            }

            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public Set<Class<?>> supportedPrimitives() {
                return keyTypeManager.supportedPrimitives();
            }
        };
    }

    public <P> KeyManager<P> getKeyManager(String str, Class<P> cls) {
        return getKeyManagerInternal(str, (Class) checkNotNull(cls));
    }

    public synchronized <KeyProtoT extends MessageLite> void registerKeyManager(KeyTypeManager<KeyProtoT> keyTypeManager) {
        if (!keyTypeManager.fipsStatus().isCompatible()) {
            throw new GeneralSecurityException("failed to register key manager " + keyTypeManager.getClass() + " as it is not FIPS compatible.");
        }
        registerKeyManagerContainer(createContainerFor(keyTypeManager), false);
    }
}
