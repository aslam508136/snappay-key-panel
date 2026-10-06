package com.google.crypto.tink.daead;

import com.google.crypto.tink.DeterministicAead;
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
public class DeterministicAeadWrapper implements PrimitiveWrapper<DeterministicAead, DeterministicAead> {
    private static final Logger logger = Logger.getLogger(DeterministicAeadWrapper.class.getName());
    private static final DeterministicAeadWrapper WRAPPER = new DeterministicAeadWrapper();

    public static class WrappedDeterministicAead implements DeterministicAead {
        private final MonitoringClient.Logger decLogger;
        private final MonitoringClient.Logger encLogger;
        private final PrimitiveSet<DeterministicAead> primitives;

        public WrappedDeterministicAead(PrimitiveSet<DeterministicAead> primitiveSet) {
            MonitoringClient.Logger loggerCreateLogger;
            this.primitives = primitiveSet;
            if (primitiveSet.hasAnnotations()) {
                MonitoringClient monitoringClient = MutableMonitoringRegistry.globalInstance().getMonitoringClient();
                MonitoringKeysetInfo monitoringKeysetInfo = MonitoringUtil.getMonitoringKeysetInfo(primitiveSet);
                this.encLogger = monitoringClient.createLogger(monitoringKeysetInfo, "daead", "encrypt");
                loggerCreateLogger = monitoringClient.createLogger(monitoringKeysetInfo, "daead", "decrypt");
            } else {
                loggerCreateLogger = MonitoringUtil.DO_NOTHING_LOGGER;
                this.encLogger = loggerCreateLogger;
            }
            this.decLogger = loggerCreateLogger;
        }

        @Override // com.google.crypto.tink.DeterministicAead
        public byte[] decryptDeterministically(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
            if (bArr.length > 5) {
                byte[] bArrCopyOf = Arrays.copyOf(bArr, 5);
                byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, 5, bArr.length);
                for (PrimitiveSet.Entry<DeterministicAead> entry : this.primitives.getPrimitive(bArrCopyOf)) {
                    try {
                        byte[] bArrDecryptDeterministically = entry.getPrimitive().decryptDeterministically(bArrCopyOfRange, bArr2);
                        this.decLogger.log(entry.getKeyId(), bArrCopyOfRange.length);
                        return bArrDecryptDeterministically;
                    } catch (GeneralSecurityException e2) {
                        DeterministicAeadWrapper.logger.info("ciphertext prefix matches a key, but cannot decrypt: " + e2);
                    }
                }
            }
            for (PrimitiveSet.Entry<DeterministicAead> entry2 : this.primitives.getRawPrimitives()) {
                try {
                    byte[] bArrDecryptDeterministically2 = entry2.getPrimitive().decryptDeterministically(bArr, bArr2);
                    this.decLogger.log(entry2.getKeyId(), bArr.length);
                    return bArrDecryptDeterministically2;
                } catch (GeneralSecurityException unused) {
                }
            }
            this.decLogger.logFailure();
            throw new GeneralSecurityException("decryption failed");
        }

        @Override // com.google.crypto.tink.DeterministicAead
        public byte[] encryptDeterministically(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
            try {
                byte[] bArrConcat = Bytes.concat(this.primitives.getPrimary().getIdentifier(), this.primitives.getPrimary().getPrimitive().encryptDeterministically(bArr, bArr2));
                this.encLogger.log(this.primitives.getPrimary().getKeyId(), bArr.length);
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
    public Class<DeterministicAead> getInputPrimitiveClass() {
        return DeterministicAead.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<DeterministicAead> getPrimitiveClass() {
        return DeterministicAead.class;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public DeterministicAead wrap(PrimitiveSet<DeterministicAead> primitiveSet) {
        return new WrappedDeterministicAead(primitiveSet);
    }
}
