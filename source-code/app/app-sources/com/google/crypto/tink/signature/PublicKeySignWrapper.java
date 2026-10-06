package com.google.crypto.tink.signature;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.MonitoringUtil;
import com.google.crypto.tink.internal.MutableMonitoringRegistry;
import com.google.crypto.tink.monitoring.MonitoringClient;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes.dex */
public class PublicKeySignWrapper implements PrimitiveWrapper<PublicKeySign, PublicKeySign> {
    private static final byte[] FORMAT_VERSION = {0};
    private static final PublicKeySignWrapper WRAPPER = new PublicKeySignWrapper();

    public static class WrappedPublicKeySign implements PublicKeySign {
        private final MonitoringClient.Logger logger;
        private final PrimitiveSet<PublicKeySign> primitives;

        public WrappedPublicKeySign(PrimitiveSet<PublicKeySign> primitiveSet) {
            this.primitives = primitiveSet;
            this.logger = primitiveSet.hasAnnotations() ? MutableMonitoringRegistry.globalInstance().getMonitoringClient().createLogger(MonitoringUtil.getMonitoringKeysetInfo(primitiveSet), "public_key_sign", "sign") : MonitoringUtil.DO_NOTHING_LOGGER;
        }

        @Override // com.google.crypto.tink.PublicKeySign
        public byte[] sign(byte[] bArr) throws GeneralSecurityException {
            if (this.primitives.getPrimary().getOutputPrefixType().equals(OutputPrefixType.LEGACY)) {
                bArr = Bytes.concat(bArr, PublicKeySignWrapper.FORMAT_VERSION);
            }
            try {
                byte[] bArrConcat = Bytes.concat(this.primitives.getPrimary().getIdentifier(), this.primitives.getPrimary().getPrimitive().sign(bArr));
                this.logger.log(this.primitives.getPrimary().getKeyId(), bArr.length);
                return bArrConcat;
            } catch (GeneralSecurityException e2) {
                this.logger.logFailure();
                throw e2;
            }
        }
    }

    public static void register() {
        Registry.registerPrimitiveWrapper(WRAPPER);
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<PublicKeySign> getInputPrimitiveClass() {
        return PublicKeySign.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<PublicKeySign> getPrimitiveClass() {
        return PublicKeySign.class;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public PublicKeySign wrap(PrimitiveSet<PublicKeySign> primitiveSet) {
        return new WrappedPublicKeySign(primitiveSet);
    }
}
