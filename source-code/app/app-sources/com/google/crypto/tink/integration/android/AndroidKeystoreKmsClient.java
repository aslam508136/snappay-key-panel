package com.google.crypto.tink.integration.android;

import a0.c;
import android.os.Build;
import android.util.Log;
import androidx.security.crypto.MasterKey;
import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KmsClient;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Arrays;
import java.util.Locale;
import javax.annotation.concurrent.GuardedBy;
import javax.crypto.KeyGenerator;

/* JADX INFO: loaded from: classes.dex */
public final class AndroidKeystoreKmsClient implements KmsClient {
    private static final int MAX_WAIT_TIME_MILLISECONDS_BEFORE_RETRY = 40;
    public static final String PREFIX = "android-keystore://";
    private static final String TAG = "AndroidKeystoreKmsClient";
    private static final Object keyCreationLock = new Object();

    @GuardedBy("this")
    private KeyStore keyStore;
    private final String keyUri;

    public static final class Builder {
        KeyStore keyStore;
        String keyUri = null;

        public Builder() {
            this.keyStore = null;
            if (!AndroidKeystoreKmsClient.isAtLeastM()) {
                throw new IllegalStateException("need Android Keystore on Android M or newer");
            }
            try {
                KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
                this.keyStore = keyStore;
                keyStore.load(null);
            } catch (IOException | GeneralSecurityException e2) {
                throw new IllegalStateException(e2);
            }
        }

        public AndroidKeystoreKmsClient build() {
            return new AndroidKeystoreKmsClient(this);
        }

        @CanIgnoreReturnValue
        public Builder setKeyStore(KeyStore keyStore) {
            if (keyStore == null) {
                throw new IllegalArgumentException("val cannot be null");
            }
            this.keyStore = keyStore;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setKeyUri(String str) {
            if (str == null || !str.toLowerCase(Locale.US).startsWith(AndroidKeystoreKmsClient.PREFIX)) {
                throw new IllegalArgumentException("val must start with android-keystore://");
            }
            this.keyUri = str;
            return this;
        }
    }

    public AndroidKeystoreKmsClient() {
        this(new Builder());
    }

    public static boolean generateKeyIfNotExist(String str) {
        AndroidKeystoreKmsClient androidKeystoreKmsClient = new AndroidKeystoreKmsClient();
        synchronized (keyCreationLock) {
            if (androidKeystoreKmsClient.hasKey(str)) {
                return false;
            }
            generateNewAesGcmKeyWithoutExistenceCheck(str);
            return true;
        }
    }

    public static void generateNewAeadKey(String str) {
        AndroidKeystoreKmsClient androidKeystoreKmsClient = new AndroidKeystoreKmsClient();
        synchronized (keyCreationLock) {
            if (androidKeystoreKmsClient.hasKey(str)) {
                throw new IllegalArgumentException(String.format("cannot generate a new key %s because it already exists; please delete it with deleteKey() and try again", str));
            }
            generateNewAesGcmKeyWithoutExistenceCheck(str);
        }
    }

    public static void generateNewAesGcmKeyWithoutExistenceCheck(String str) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException {
        String strValidateKmsKeyUriAndRemovePrefix = Validators.validateKmsKeyUriAndRemovePrefix(PREFIX, str);
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "AndroidKeyStore");
        keyGenerator.init(c.h(strValidateKmsKeyUriAndRemovePrefix).setKeySize(MasterKey.DEFAULT_AES_GCM_MASTER_KEY_SIZE).setBlockModes("GCM").setEncryptionPaddings("NoPadding").build());
        keyGenerator.generateKey();
    }

    public static Aead getOrGenerateNewAeadKey(String str) {
        AndroidKeystoreKmsClient androidKeystoreKmsClient = new AndroidKeystoreKmsClient();
        synchronized (keyCreationLock) {
            if (!androidKeystoreKmsClient.hasKey(str)) {
                generateNewAesGcmKeyWithoutExistenceCheck(str);
            }
        }
        return androidKeystoreKmsClient.getAead(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isAtLeastM() {
        return Build.VERSION.SDK_INT >= 23;
    }

    private static void sleepRandomAmount() {
        try {
            Thread.sleep((int) (Math.random() * 40.0d));
        } catch (InterruptedException unused) {
        }
    }

    private static Aead validateAead(Aead aead) throws KeyStoreException {
        byte[] bArrRandBytes = Random.randBytes(10);
        byte[] bArr = new byte[0];
        if (Arrays.equals(bArrRandBytes, aead.decrypt(aead.encrypt(bArrRandBytes, bArr), bArr))) {
            return aead;
        }
        throw new KeyStoreException("cannot use Android Keystore: encryption/decryption of non-empty message and empty aad returns an incorrect result");
    }

    public synchronized void deleteKey(String str) {
        this.keyStore.deleteEntry(Validators.validateKmsKeyUriAndRemovePrefix(PREFIX, str));
    }

    @Override // com.google.crypto.tink.KmsClient
    public synchronized boolean doesSupport(String str) {
        String str2 = this.keyUri;
        if (str2 == null || !str2.equals(str)) {
            return this.keyUri == null && str.toLowerCase(Locale.US).startsWith(PREFIX);
        }
        return true;
    }

    @Override // com.google.crypto.tink.KmsClient
    public synchronized Aead getAead(String str) {
        String str2 = this.keyUri;
        if (str2 != null && !str2.equals(str)) {
            throw new GeneralSecurityException(String.format("this client is bound to %s, cannot load keys bound to %s", this.keyUri, str));
        }
        return validateAead(new AndroidKeystoreAesGcm(Validators.validateKmsKeyUriAndRemovePrefix(PREFIX, str), this.keyStore));
    }

    public synchronized boolean hasKey(String str) {
        String strValidateKmsKeyUriAndRemovePrefix;
        strValidateKmsKeyUriAndRemovePrefix = Validators.validateKmsKeyUriAndRemovePrefix(PREFIX, str);
        try {
        } catch (NullPointerException unused) {
            Log.w(TAG, "Keystore is temporarily unavailable, wait, reinitialize Keystore and try again.");
            try {
                sleepRandomAmount();
                KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
                this.keyStore = keyStore;
                keyStore.load(null);
                return this.keyStore.containsAlias(strValidateKmsKeyUriAndRemovePrefix);
            } catch (IOException e2) {
                throw new GeneralSecurityException(e2);
            }
        }
        return this.keyStore.containsAlias(strValidateKmsKeyUriAndRemovePrefix);
    }

    @Override // com.google.crypto.tink.KmsClient
    public KmsClient withCredentials(String str) {
        return new AndroidKeystoreKmsClient();
    }

    @Override // com.google.crypto.tink.KmsClient
    public KmsClient withDefaultCredentials() {
        return new AndroidKeystoreKmsClient();
    }

    private AndroidKeystoreKmsClient(Builder builder) {
        this.keyUri = builder.keyUri;
        this.keyStore = builder.keyStore;
    }

    @Deprecated
    public AndroidKeystoreKmsClient(String str) {
        this(new Builder().setKeyUri(str));
    }
}
