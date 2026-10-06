package com.google.crypto.tink.signature;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.MonitoringUtil;
import com.google.crypto.tink.internal.MutableMonitoringRegistry;
import com.google.crypto.tink.monitoring.MonitoringClient;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
class PublicKeyVerifyWrapper implements PrimitiveWrapper<PublicKeyVerify, PublicKeyVerify> {
    private static final Logger logger = Logger.getLogger(PublicKeyVerifyWrapper.class.getName());
    private static final byte[] FORMAT_VERSION = {0};
    private static final PublicKeyVerifyWrapper WRAPPER = new PublicKeyVerifyWrapper();

    public static class WrappedPublicKeyVerify implements PublicKeyVerify {
        private final MonitoringClient.Logger monitoringLogger;
        private final PrimitiveSet<PublicKeyVerify> primitives;

        public WrappedPublicKeyVerify(PrimitiveSet<PublicKeyVerify> primitiveSet) {
            this.primitives = primitiveSet;
            this.monitoringLogger = primitiveSet.hasAnnotations() ? MutableMonitoringRegistry.globalInstance().getMonitoringClient().createLogger(MonitoringUtil.getMonitoringKeysetInfo(primitiveSet), "public_key_verify", "verify") : MonitoringUtil.DO_NOTHING_LOGGER;
        }

        @Override // com.google.crypto.tink.PublicKeyVerify
        public void verify(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
            if (bArr.length <= 5) {
                this.monitoringLogger.logFailure();
                throw new GeneralSecurityException("signature too short");
            }
            byte[] bArrCopyOf = Arrays.copyOf(bArr, 5);
            byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, 5, bArr.length);
            for (PrimitiveSet.Entry<PublicKeyVerify> entry : this.primitives.getPrimitive(bArrCopyOf)) {
                byte[] bArrConcat = entry.getOutputPrefixType().equals(OutputPrefixType.LEGACY) ? Bytes.concat(bArr2, PublicKeyVerifyWrapper.FORMAT_VERSION) : bArr2;
                try {
                    entry.getPrimitive().verify(bArrCopyOfRange, bArrConcat);
                    this.monitoringLogger.log(entry.getKeyId(), bArrConcat.length);
                    return;
                } catch (GeneralSecurityException e2) {
                    PublicKeyVerifyWrapper.logger.info("signature prefix matches a key, but cannot verify: " + e2);
                }
            }
            for (PrimitiveSet.Entry<PublicKeyVerify> entry2 : this.primitives.getRawPrimitives()) {
                try {
                    entry2.getPrimitive().verify(bArr, bArr2);
                    this.monitoringLogger.log(entry2.getKeyId(), bArr2.length);
                    return;
                } catch (GeneralSecurityException unused) {
                }
            }
            this.monitoringLogger.logFailure();
            throw new GeneralSecurityException("invalid signature");
        }
    }

    public static void register() {
        Registry.registerPrimitiveWrapper(WRAPPER);
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<PublicKeyVerify> getInputPrimitiveClass() {
        return PublicKeyVerify.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<PublicKeyVerify> getPrimitiveClass() {
        return PublicKeyVerify.class;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public PublicKeyVerify wrap(PrimitiveSet<PublicKeyVerify> primitiveSet) {
        return new WrappedPublicKeyVerify(primitiveSet);
    }
}
