package com.google.crypto.tink.integration.android;

import android.content.Context;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;
import com.google.crypto.tink.Aead;
import com.google.crypto.tink.BinaryKeysetReader;
import com.google.crypto.tink.CleartextKeysetHandle;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.KeysetManager;
import com.google.crypto.tink.KeysetWriter;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.subtle.Hex;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.InlineMe;
import java.io.CharConversionException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStoreException;
import java.security.ProviderException;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: classes.dex */
public final class AndroidKeysetManager {
    private static final String TAG = "AndroidKeysetManager";
    private static final Object lock = new Object();

    @GuardedBy("this")
    private KeysetManager keysetManager;
    private final Aead masterAead;
    private final KeysetWriter writer;

    /* JADX INFO: renamed from: com.google.crypto.tink.integration.android.AndroidKeysetManager$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType;

        static {
            int[] iArr = new int[OutputPrefixType.values().length];
            $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType = iArr;
            try {
                iArr[OutputPrefixType.TINK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.LEGACY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.RAW.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[OutputPrefixType.CRUNCHY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static final class Builder {

        @GuardedBy("this")
        private KeysetManager keysetManager;
        private Context context = null;
        private String keysetName = null;
        private String prefFileName = null;
        private String masterKeyUri = null;
        private Aead masterAead = null;
        private boolean useKeystore = true;
        private KeyTemplate keyTemplate = null;

        private KeysetManager generateKeysetAndWriteToPrefs() throws GeneralSecurityException {
            if (this.keyTemplate == null) {
                throw new GeneralSecurityException("cannot read or generate keyset");
            }
            KeysetManager keysetManagerAdd = KeysetManager.withEmptyKeyset().add(this.keyTemplate);
            KeysetManager primary = keysetManagerAdd.setPrimary(keysetManagerAdd.getKeysetHandle().getKeysetInfo().getKeyInfo(0).getKeyId());
            SharedPrefKeysetWriter sharedPrefKeysetWriter = new SharedPrefKeysetWriter(this.context, this.keysetName, this.prefFileName);
            if (this.masterAead != null) {
                primary.getKeysetHandle().write(sharedPrefKeysetWriter, this.masterAead);
            } else {
                CleartextKeysetHandle.write(primary.getKeysetHandle(), sharedPrefKeysetWriter);
            }
            return primary;
        }

        private static byte[] readKeysetFromPrefs(Context context, String str, String str2) throws CharConversionException {
            if (str == null) {
                throw new IllegalArgumentException("keysetName cannot be null");
            }
            Context applicationContext = context.getApplicationContext();
            try {
                String string = (str2 == null ? PreferenceManager.getDefaultSharedPreferences(applicationContext) : applicationContext.getSharedPreferences(str2, 0)).getString(str, null);
                if (string == null) {
                    return null;
                }
                return Hex.decode(string);
            } catch (ClassCastException | IllegalArgumentException unused) {
                throw new CharConversionException(String.format("can't read keyset; the pref value %s is not a valid hex string", str));
            }
        }

        private KeysetManager readKeysetInCleartext(byte[] bArr) {
            return KeysetManager.withKeysetHandle(CleartextKeysetHandle.read(BinaryKeysetReader.withBytes(bArr)));
        }

        private KeysetManager readMasterkeyDecryptAndParseKeyset(byte[] bArr) {
            try {
                this.masterAead = new AndroidKeystoreKmsClient().getAead(this.masterKeyUri);
                try {
                    return KeysetManager.withKeysetHandle(KeysetHandle.read(BinaryKeysetReader.withBytes(bArr), this.masterAead));
                } catch (IOException | GeneralSecurityException e2) {
                    try {
                        return readKeysetInCleartext(bArr);
                    } catch (IOException unused) {
                        throw e2;
                    }
                }
            } catch (GeneralSecurityException | ProviderException e3) {
                try {
                    KeysetManager keysetInCleartext = readKeysetInCleartext(bArr);
                    Log.w(AndroidKeysetManager.TAG, "cannot use Android Keystore, it'll be disabled", e3);
                    return keysetInCleartext;
                } catch (IOException unused2) {
                    throw e3;
                }
            }
        }

        private Aead readOrGenerateNewMasterKey() throws KeyStoreException {
            if (!AndroidKeysetManager.isAtLeastM()) {
                Log.w(AndroidKeysetManager.TAG, "Android Keystore requires at least Android M");
                return null;
            }
            AndroidKeystoreKmsClient androidKeystoreKmsClient = new AndroidKeystoreKmsClient();
            try {
                boolean zGenerateKeyIfNotExist = AndroidKeystoreKmsClient.generateKeyIfNotExist(this.masterKeyUri);
                try {
                    return androidKeystoreKmsClient.getAead(this.masterKeyUri);
                } catch (GeneralSecurityException | ProviderException e2) {
                    if (!zGenerateKeyIfNotExist) {
                        throw new KeyStoreException(String.format("the master key %s exists but is unusable", this.masterKeyUri), e2);
                    }
                    Log.w(AndroidKeysetManager.TAG, "cannot use Android Keystore, it'll be disabled", e2);
                    return null;
                }
            } catch (GeneralSecurityException | ProviderException e3) {
                Log.w(AndroidKeysetManager.TAG, "cannot use Android Keystore, it'll be disabled", e3);
                return null;
            }
        }

        public synchronized AndroidKeysetManager build() {
            KeysetManager keysetInCleartext;
            AndroidKeysetManager androidKeysetManager;
            if (this.keysetName == null) {
                throw new IllegalArgumentException("keysetName cannot be null");
            }
            synchronized (AndroidKeysetManager.lock) {
                byte[] keysetFromPrefs = readKeysetFromPrefs(this.context, this.keysetName, this.prefFileName);
                if (keysetFromPrefs == null) {
                    if (this.masterKeyUri != null) {
                        this.masterAead = readOrGenerateNewMasterKey();
                    }
                    keysetInCleartext = generateKeysetAndWriteToPrefs();
                } else {
                    keysetInCleartext = (this.masterKeyUri == null || !AndroidKeysetManager.isAtLeastM()) ? readKeysetInCleartext(keysetFromPrefs) : readMasterkeyDecryptAndParseKeyset(keysetFromPrefs);
                }
                this.keysetManager = keysetInCleartext;
                androidKeysetManager = new AndroidKeysetManager(this, null);
            }
            return androidKeysetManager;
        }

        @CanIgnoreReturnValue
        @Deprecated
        public Builder doNotUseKeystore() {
            this.masterKeyUri = null;
            this.useKeystore = false;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder withKeyTemplate(KeyTemplate keyTemplate) {
            this.keyTemplate = keyTemplate;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder withMasterKeyUri(String str) {
            if (!str.startsWith(AndroidKeystoreKmsClient.PREFIX)) {
                throw new IllegalArgumentException("key URI must start with android-keystore://");
            }
            if (!this.useKeystore) {
                throw new IllegalArgumentException("cannot call withMasterKeyUri() after calling doNotUseKeystore()");
            }
            this.masterKeyUri = str;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder withSharedPref(Context context, String str, String str2) {
            if (context == null) {
                throw new IllegalArgumentException("need an Android context");
            }
            if (str == null) {
                throw new IllegalArgumentException("need a keyset name");
            }
            this.context = context;
            this.keysetName = str;
            this.prefFileName = str2;
            return this;
        }

        @CanIgnoreReturnValue
        @Deprecated
        public Builder withKeyTemplate(com.google.crypto.tink.proto.KeyTemplate keyTemplate) {
            this.keyTemplate = KeyTemplate.create(keyTemplate.getTypeUrl(), keyTemplate.getValue().toByteArray(), AndroidKeysetManager.fromProto(keyTemplate.getOutputPrefixType()));
            return this;
        }
    }

    private AndroidKeysetManager(Builder builder) {
        this.writer = new SharedPrefKeysetWriter(builder.context, builder.keysetName, builder.prefFileName);
        this.masterAead = builder.masterAead;
        this.keysetManager = builder.keysetManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static KeyTemplate.OutputPrefixType fromProto(OutputPrefixType outputPrefixType) {
        int i2 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[outputPrefixType.ordinal()];
        if (i2 == 1) {
            return KeyTemplate.OutputPrefixType.TINK;
        }
        if (i2 == 2) {
            return KeyTemplate.OutputPrefixType.LEGACY;
        }
        if (i2 == 3) {
            return KeyTemplate.OutputPrefixType.RAW;
        }
        if (i2 == 4) {
            return KeyTemplate.OutputPrefixType.CRUNCHY;
        }
        throw new IllegalArgumentException("Unknown output prefix type");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isAtLeastM() {
        return Build.VERSION.SDK_INT >= 23;
    }

    private boolean shouldUseKeystore() {
        return this.masterAead != null && isAtLeastM();
    }

    private void write(KeysetManager keysetManager) throws GeneralSecurityException {
        try {
            if (shouldUseKeystore()) {
                keysetManager.getKeysetHandle().write(this.writer, this.masterAead);
            } else {
                CleartextKeysetHandle.write(keysetManager.getKeysetHandle(), this.writer);
            }
        } catch (IOException e2) {
            throw new GeneralSecurityException(e2);
        }
    }

    @CanIgnoreReturnValue
    @GuardedBy("this")
    public synchronized AndroidKeysetManager add(KeyTemplate keyTemplate) {
        KeysetManager keysetManagerAdd = this.keysetManager.add(keyTemplate);
        this.keysetManager = keysetManagerAdd;
        write(keysetManagerAdd);
        return this;
    }

    @CanIgnoreReturnValue
    public synchronized AndroidKeysetManager delete(int i2) {
        KeysetManager keysetManagerDelete = this.keysetManager.delete(i2);
        this.keysetManager = keysetManagerDelete;
        write(keysetManagerDelete);
        return this;
    }

    @CanIgnoreReturnValue
    public synchronized AndroidKeysetManager destroy(int i2) {
        KeysetManager keysetManagerDestroy = this.keysetManager.destroy(i2);
        this.keysetManager = keysetManagerDestroy;
        write(keysetManagerDestroy);
        return this;
    }

    @CanIgnoreReturnValue
    public synchronized AndroidKeysetManager disable(int i2) {
        KeysetManager keysetManagerDisable = this.keysetManager.disable(i2);
        this.keysetManager = keysetManagerDisable;
        write(keysetManagerDisable);
        return this;
    }

    @CanIgnoreReturnValue
    public synchronized AndroidKeysetManager enable(int i2) {
        KeysetManager keysetManagerEnable = this.keysetManager.enable(i2);
        this.keysetManager = keysetManagerEnable;
        write(keysetManagerEnable);
        return this;
    }

    public synchronized KeysetHandle getKeysetHandle() {
        return this.keysetManager.getKeysetHandle();
    }

    public synchronized boolean isUsingKeystore() {
        return shouldUseKeystore();
    }

    @CanIgnoreReturnValue
    @InlineMe(replacement = "this.setPrimary(keyId)")
    @Deprecated
    public synchronized AndroidKeysetManager promote(int i2) {
        return setPrimary(i2);
    }

    @CanIgnoreReturnValue
    @Deprecated
    public synchronized AndroidKeysetManager rotate(com.google.crypto.tink.proto.KeyTemplate keyTemplate) {
        KeysetManager keysetManagerRotate = this.keysetManager.rotate(keyTemplate);
        this.keysetManager = keysetManagerRotate;
        write(keysetManagerRotate);
        return this;
    }

    @CanIgnoreReturnValue
    public synchronized AndroidKeysetManager setPrimary(int i2) {
        KeysetManager primary = this.keysetManager.setPrimary(i2);
        this.keysetManager = primary;
        write(primary);
        return this;
    }

    public /* synthetic */ AndroidKeysetManager(Builder builder, AnonymousClass1 anonymousClass1) {
        this(builder);
    }

    @CanIgnoreReturnValue
    @GuardedBy("this")
    @Deprecated
    public synchronized AndroidKeysetManager add(com.google.crypto.tink.proto.KeyTemplate keyTemplate) {
        KeysetManager keysetManagerAdd = this.keysetManager.add(keyTemplate);
        this.keysetManager = keysetManagerAdd;
        write(keysetManagerAdd);
        return this;
    }
}
