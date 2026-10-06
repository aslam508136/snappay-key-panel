package androidx.security.crypto;

import a0.c;
import android.security.keystore.KeyGenParameterSpec;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.ProviderException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import javax.crypto.KeyGenerator;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class MasterKeys {
    private static final String ANDROID_KEYSTORE = "AndroidKeyStore";
    static final int KEY_SIZE = 256;
    static final String MASTER_KEY_ALIAS = "_androidx_security_master_key_";
    public static final KeyGenParameterSpec AES256_GCM_SPEC = createAES256GCMKeyGenParameterSpec("_androidx_security_master_key_");
    private static final Object sLock = new Object();

    private MasterKeys() {
    }

    private static KeyGenParameterSpec createAES256GCMKeyGenParameterSpec(String str) {
        return c.h(str).setBlockModes("GCM").setEncryptionPaddings("NoPadding").setKeySize(MasterKey.DEFAULT_AES_GCM_MASTER_KEY_SIZE).build();
    }

    private static void generateKey(KeyGenParameterSpec keyGenParameterSpec) throws GeneralSecurityException {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", ANDROID_KEYSTORE);
            keyGenerator.init(keyGenParameterSpec);
            keyGenerator.generateKey();
        } catch (ProviderException e2) {
            throw new GeneralSecurityException(e2.getMessage(), e2);
        }
    }

    public static String getOrCreate(KeyGenParameterSpec keyGenParameterSpec) {
        validate(keyGenParameterSpec);
        synchronized (sLock) {
            if (!keyExists(keyGenParameterSpec.getKeystoreAlias())) {
                generateKey(keyGenParameterSpec);
            }
        }
        return keyGenParameterSpec.getKeystoreAlias();
    }

    private static boolean keyExists(String str) throws NoSuchAlgorithmException, IOException, KeyStoreException, CertificateException {
        KeyStore keyStore = KeyStore.getInstance(ANDROID_KEYSTORE);
        keyStore.load(null);
        return keyStore.containsAlias(str);
    }

    public static void validate(KeyGenParameterSpec keyGenParameterSpec) {
        if (keyGenParameterSpec.getKeySize() != 256) {
            throw new IllegalArgumentException("invalid key size, want 256 bits got " + keyGenParameterSpec.getKeySize() + " bits");
        }
        if (!Arrays.equals(keyGenParameterSpec.getBlockModes(), new String[]{"GCM"})) {
            throw new IllegalArgumentException("invalid block mode, want GCM got " + Arrays.toString(keyGenParameterSpec.getBlockModes()));
        }
        if (keyGenParameterSpec.getPurposes() != 3) {
            throw new IllegalArgumentException("invalid purposes mode, want PURPOSE_ENCRYPT | PURPOSE_DECRYPT got " + keyGenParameterSpec.getPurposes());
        }
        if (!Arrays.equals(keyGenParameterSpec.getEncryptionPaddings(), new String[]{"NoPadding"})) {
            throw new IllegalArgumentException("invalid padding mode, want NoPadding got " + Arrays.toString(keyGenParameterSpec.getEncryptionPaddings()));
        }
        if (keyGenParameterSpec.isUserAuthenticationRequired() && keyGenParameterSpec.getUserAuthenticationValidityDurationSeconds() < 1) {
            throw new IllegalArgumentException("per-operation authentication is not supported (UserAuthenticationValidityDurationSeconds must be >0)");
        }
    }
}
