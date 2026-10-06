package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.MonitoringUtil;
import com.google.crypto.tink.internal.MutableMonitoringRegistry;
import com.google.crypto.tink.monitoring.MonitoringClient;
import com.google.crypto.tink.monitoring.MonitoringKeysetInfo;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public class AeadWrapper implements PrimitiveWrapper<Aead, Aead> {
    private static final Logger logger = Logger.getLogger(AeadWrapper.class.getName());
    private static final AeadWrapper WRAPPER = new AeadWrapper();

    public static class WrappedAead implements Aead {
        private final MonitoringClient.Logger decLogger;
        private final MonitoringClient.Logger encLogger;
        private final PrimitiveSet<Aead> pSet;

        private WrappedAead(PrimitiveSet<Aead> primitiveSet) {
            MonitoringClient.Logger loggerCreateLogger;
            this.pSet = primitiveSet;
            if (primitiveSet.hasAnnotations()) {
                MonitoringClient monitoringClient = MutableMonitoringRegistry.globalInstance().getMonitoringClient();
                MonitoringKeysetInfo monitoringKeysetInfo = MonitoringUtil.getMonitoringKeysetInfo(primitiveSet);
                this.encLogger = monitoringClient.createLogger(monitoringKeysetInfo, "aead", "encrypt");
                loggerCreateLogger = monitoringClient.createLogger(monitoringKeysetInfo, "aead", "decrypt");
            } else {
                loggerCreateLogger = MonitoringUtil.DO_NOTHING_LOGGER;
                this.encLogger = loggerCreateLogger;
            }
            this.decLogger = loggerCreateLogger;
        }

        @Override // com.google.crypto.tink.Aead
        public byte[] decrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
            if (bArr.length > 5) {
                byte[] bArrCopyOf = Arrays.copyOf(bArr, 5);
                byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, 5, bArr.length);
                for (PrimitiveSet.Entry<Aead> entry : this.pSet.getPrimitive(bArrCopyOf)) {
                    try {
                        byte[] bArrDecrypt = entry.getPrimitive().decrypt(bArrCopyOfRange, bArr2);
                        this.decLogger.log(entry.getKeyId(), bArrCopyOfRange.length);
                        return bArrDecrypt;
                    } catch (GeneralSecurityException e2) {
                        AeadWrapper.logger.info("ciphertext prefix matches a key, but cannot decrypt: " + e2);
                    }
                }
            }
            for (PrimitiveSet.Entry<Aead> entry2 : this.pSet.getRawPrimitives()) {
                try {
                    byte[] bArrDecrypt2 = entry2.getPrimitive().decrypt(bArr, bArr2);
                    this.decLogger.log(entry2.getKeyId(), bArr.length);
                    return bArrDecrypt2;
                } catch (GeneralSecurityException unused) {
                }
            }
            this.decLogger.logFailure();
            throw new GeneralSecurityException("decryption failed");
        }

        @Override // com.google.crypto.tink.Aead
        public byte[] encrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
            try {
                byte[] bArrConcat = Bytes.concat(this.pSet.getPrimary().getIdentifier(), this.pSet.getPrimary().getPrimitive().encrypt(bArr, bArr2));
                this.encLogger.log(this.pSet.getPrimary().getKeyId(), bArr.length);
                return bArrConcat;
            } catch (GeneralSecurityException e2) {
                this.encLogger.logFailure();
                throw e2;
            }
        }
    }

    public static void register() {
        Registry.registerPrimitiveWrapper(WRAPPER);
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<Aead> getInputPrimitiveClass() {
        return Aead.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<Aead> getPrimitiveClass() {
        return Aead.class;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Aead wrap(PrimitiveSet<Aead> primitiveSet) {
        return new WrappedAead(primitiveSet);
    }
}
