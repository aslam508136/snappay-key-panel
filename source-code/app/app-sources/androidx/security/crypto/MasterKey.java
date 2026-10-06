package androidx.security.crypto;

import a0.c;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/* JADX INFO: loaded from: classes.dex */
public final class MasterKey {
    public static final int DEFAULT_AES_GCM_MASTER_KEY_SIZE = 256;
    private static final int DEFAULT_AUTHENTICATION_VALIDITY_DURATION_SECONDS = 300;
    public static final String DEFAULT_MASTER_KEY_ALIAS = "_androidx_security_master_key_";
    static final String KEYSTORE_PATH_URI = "android-keystore://";
    private final String mKeyAlias;
    private final KeyGenParameterSpec mKeyGenParameterSpec;

    /* JADX INFO: renamed from: androidx.security.crypto.MasterKey$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$security$crypto$MasterKey$KeyScheme;

        static {
            int[] iArr = new int[KeyScheme.values().length];
            $SwitchMap$androidx$security$crypto$MasterKey$KeyScheme = iArr;
            try {
                iArr[KeyScheme.AES256_GCM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public static class Api23Impl {
        private Api23Impl() {
        }

        public static int getUserAuthenticationValidityDurationSeconds(KeyGenParameterSpec keyGenParameterSpec) {
            return keyGenParameterSpec.getUserAuthenticationValidityDurationSeconds();
        }

        public static boolean isUserAuthenticationRequired(KeyGenParameterSpec keyGenParameterSpec) {
            return keyGenParameterSpec.isUserAuthenticationRequired();
        }
    }

    public static class Api28Impl {
        private Api28Impl() {
        }

        public static boolean isStrongBoxBacked(KeyGenParameterSpec keyGenParameterSpec) {
            return keyGenParameterSpec.isStrongBoxBacked();
        }
    }

    public static final class Builder {
        boolean mAuthenticationRequired;
        final Context mContext;
        final String mKeyAlias;
        KeyGenParameterSpec mKeyGenParameterSpec;
        KeyScheme mKeyScheme;
        boolean mRequestStrongBoxBacked;
        int mUserAuthenticationValidityDurationSeconds;

        public static class Api23Impl {

            public static class Api28Impl {
                private Api28Impl() {
                }

                public static void setIsStrongBoxBacked(KeyGenParameterSpec.Builder builder) {
                    builder.setIsStrongBoxBacked(true);
                }
            }

            public static class Api30Impl {
                private Api30Impl() {
                }

                public static void setUserAuthenticationParameters(KeyGenParameterSpec.Builder builder, int i2, int i3) {
                    builder.setUserAuthenticationParameters(i2, i3);
                }
            }

            private Api23Impl() {
            }

            public static MasterKey build(Builder builder) {
                KeyScheme keyScheme = builder.mKeyScheme;
                if (keyScheme == null && builder.mKeyGenParameterSpec == null) {
                    throw new IllegalArgumentException("build() called before setKeyGenParameterSpec or setKeyScheme.");
                }
                if (keyScheme == KeyScheme.AES256_GCM) {
                    KeyGenParameterSpec.Builder keySize = new KeyGenParameterSpec.Builder(builder.mKeyAlias, 3).setBlockModes("GCM").setEncryptionPaddings("NoPadding").setKeySize(MasterKey.DEFAULT_AES_GCM_MASTER_KEY_SIZE);
                    if (builder.mAuthenticationRequired) {
                        keySize.setUserAuthenticationRequired(true);
                        if (Build.VERSION.SDK_INT >= 30) {
                            Api30Impl.setUserAuthenticationParameters(keySize, builder.mUserAuthenticationValidityDurationSeconds, 3);
                        } else {
                            keySize.setUserAuthenticationValidityDurationSeconds(builder.mUserAuthenticationValidityDurationSeconds);
                        }
                    }
                    if (Build.VERSION.SDK_INT >= 28 && builder.mRequestStrongBoxBacked && builder.mContext.getPackageManager().hasSystemFeature("android.hardware.strongbox_keystore")) {
                        Api28Impl.setIsStrongBoxBacked(keySize);
                    }
                    builder.mKeyGenParameterSpec = keySize.build();
                }
                KeyGenParameterSpec keyGenParameterSpec = builder.mKeyGenParameterSpec;
                if (keyGenParameterSpec != null) {
                    return new MasterKey(MasterKeys.getOrCreate(keyGenParameterSpec), builder.mKeyGenParameterSpec);
                }
                throw new NullPointerException("KeyGenParameterSpec was null after build() check");
            }

            public static String getKeystoreAlias(KeyGenParameterSpec keyGenParameterSpec) {
                return keyGenParameterSpec.getKeystoreAlias();
            }
        }

        public Builder(Context context) {
            this(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS);
        }

        public MasterKey build() {
            return Build.VERSION.SDK_INT >= 23 ? Api23Impl.build(this) : new MasterKey(this.mKeyAlias, null);
        }

        public Builder setKeyGenParameterSpec(KeyGenParameterSpec keyGenParameterSpec) {
            if (this.mKeyScheme != null) {
                throw new IllegalArgumentException("KeyGenParamSpec set after setting a KeyScheme");
            }
            if (this.mKeyAlias.equals(Api23Impl.getKeystoreAlias(keyGenParameterSpec))) {
                this.mKeyGenParameterSpec = keyGenParameterSpec;
                return this;
            }
            throw new IllegalArgumentException("KeyGenParamSpec's key alias does not match provided alias (" + this.mKeyAlias + " vs " + Api23Impl.getKeystoreAlias(keyGenParameterSpec));
        }

        public Builder setKeyScheme(KeyScheme keyScheme) {
            if (AnonymousClass1.$SwitchMap$androidx$security$crypto$MasterKey$KeyScheme[keyScheme.ordinal()] != 1) {
                throw new IllegalArgumentException("Unsupported scheme: " + keyScheme);
            }
            if (Build.VERSION.SDK_INT >= 23 && this.mKeyGenParameterSpec != null) {
                throw new IllegalArgumentException("KeyScheme set after setting a KeyGenParamSpec");
            }
            this.mKeyScheme = keyScheme;
            return this;
        }

        public Builder setRequestStrongBoxBacked(boolean z2) {
            this.mRequestStrongBoxBacked = z2;
            return this;
        }

        public Builder setUserAuthenticationRequired(boolean z2) {
            return setUserAuthenticationRequired(z2, MasterKey.getDefaultAuthenticationValidityDurationSeconds());
        }

        public Builder(Context context, String str) {
            this.mContext = context.getApplicationContext();
            this.mKeyAlias = str;
        }

        public Builder setUserAuthenticationRequired(boolean z2, int i2) {
            this.mAuthenticationRequired = z2;
            this.mUserAuthenticationValidityDurationSeconds = i2;
            return this;
        }
    }

    public enum KeyScheme {
        AES256_GCM
    }

    public MasterKey(String str, Object obj) {
        this.mKeyAlias = str;
        this.mKeyGenParameterSpec = Build.VERSION.SDK_INT >= 23 ? c.j(obj) : null;
    }

    @SuppressLint({"MethodNameUnits"})
    public static int getDefaultAuthenticationValidityDurationSeconds() {
        return DEFAULT_AUTHENTICATION_VALIDITY_DURATION_SECONDS;
    }

    public String getKeyAlias() {
        return this.mKeyAlias;
    }

    @SuppressLint({"MethodNameUnits"})
    public int getUserAuthenticationValidityDurationSeconds() {
        KeyGenParameterSpec keyGenParameterSpec;
        if (Build.VERSION.SDK_INT >= 23 && (keyGenParameterSpec = this.mKeyGenParameterSpec) != null) {
            return Api23Impl.getUserAuthenticationValidityDurationSeconds(keyGenParameterSpec);
        }
        return 0;
    }

    public boolean isKeyStoreBacked() {
        if (Build.VERSION.SDK_INT < 23) {
            return false;
        }
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            return keyStore.containsAlias(this.mKeyAlias);
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException unused) {
            return false;
        }
    }

    public boolean isStrongBoxBacked() {
        KeyGenParameterSpec keyGenParameterSpec;
        if (Build.VERSION.SDK_INT < 28 || (keyGenParameterSpec = this.mKeyGenParameterSpec) == null) {
            return false;
        }
        return Api28Impl.isStrongBoxBacked(keyGenParameterSpec);
    }

    public boolean isUserAuthenticationRequired() {
        KeyGenParameterSpec keyGenParameterSpec;
        return Build.VERSION.SDK_INT >= 23 && (keyGenParameterSpec = this.mKeyGenParameterSpec) != null && Api23Impl.isUserAuthenticationRequired(keyGenParameterSpec);
    }

    public String toString() {
        return "MasterKey{keyAlias=" + this.mKeyAlias + ", isKeyStoreBacked=" + isKeyStoreBacked() + "}";
    }
}
