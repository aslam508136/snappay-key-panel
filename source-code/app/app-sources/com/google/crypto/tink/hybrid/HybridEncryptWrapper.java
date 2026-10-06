package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.HybridEncrypt;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.MonitoringUtil;
import com.google.crypto.tink.internal.MutableMonitoringRegistry;
import com.google.crypto.tink.monitoring.MonitoringClient;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes.dex */
public class HybridEncryptWrapper implements PrimitiveWrapper<HybridEncrypt, HybridEncrypt> {
    private static final HybridEncryptWrapper WRAPPER = new HybridEncryptWrapper();

    public static class WrappedHybridEncrypt implements HybridEncrypt {
        private final MonitoringClient.Logger encLogger;
        final PrimitiveSet<HybridEncrypt> primitives;

        public WrappedHybridEncrypt(PrimitiveSet<HybridEncrypt> primitiveSet) {
            this.primitives = primitiveSet;
            this.encLogger = primitiveSet.hasAnnotations() ? MutableMonitoringRegistry.globalInstance().getMonitoringClient().createLogger(MonitoringUtil.getMonitoringKeysetInfo(primitiveSet), "hybrid_encrypt", "encrypt") : MonitoringUtil.DO_NOTHING_LOGGER;
        }

        @Override // com.google.crypto.tink.HybridEncrypt
        public byte[] encrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
            if (this.primitives.getPrimary() == null) {
                this.encLogger.logFailure();
                throw new GeneralSecurityException("keyset without primary key");
            }
            try {
                byte[] bArrConcat = Bytes.concat(this.primitives.getPrimary().getIdentifier(), this.primitives.getPrimary().getPrimitive().encrypt(bArr, bArr2));
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
    public Class<HybridEncrypt> getInputPrimitiveClass() {
        return HybridEncrypt.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<HybridEncrypt> getPrimitiveClass() {
        return HybridEncrypt.class;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public HybridEncrypt wrap(PrimitiveSet<HybridEncrypt> primitiveSet) {
        return new WrappedHybridEncrypt(primitiveSet);
    }
}
