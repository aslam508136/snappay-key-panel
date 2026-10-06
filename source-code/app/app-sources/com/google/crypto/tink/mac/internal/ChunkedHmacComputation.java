package com.google.crypto.tink.mac.internal;

import com.google.crypto.tink.AccessesPartialKey;
import com.google.crypto.tink.InsecureSecretKeyAccess;
import com.google.crypto.tink.mac.ChunkedMacComputation;
import com.google.crypto.tink.mac.HmacKey;
import com.google.crypto.tink.mac.HmacParameters;
import com.google.crypto.tink.subtle.Bytes;
import com.google.crypto.tink.subtle.EngineFactory;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
@AccessesPartialKey
final class ChunkedHmacComputation implements ChunkedMacComputation {
    private static final byte[] FORMAT_VERSION = {0};
    private boolean finalized = false;
    private final HmacKey key;
    private final Mac mac;

    public ChunkedHmacComputation(HmacKey hmacKey) throws InvalidKeyException {
        Mac engineFactory = EngineFactory.MAC.getInstance(composeAlgorithmName(hmacKey));
        this.mac = engineFactory;
        engineFactory.init(new SecretKeySpec(hmacKey.getKeyBytes().toByteArray(InsecureSecretKeyAccess.get()), "HMAC"));
        this.key = hmacKey;
    }

    private static String composeAlgorithmName(HmacKey hmacKey) {
        return "HMAC" + hmacKey.getParameters().getHashType();
    }

    @Override // com.google.crypto.tink.mac.ChunkedMacComputation
    public byte[] computeMac() {
        if (this.finalized) {
            throw new IllegalStateException("Cannot compute after already computing the MAC tag. Please create a new object.");
        }
        if (this.key.getParameters().getVariant() == HmacParameters.Variant.LEGACY) {
            update(ByteBuffer.wrap(FORMAT_VERSION));
        }
        this.finalized = true;
        return Bytes.concat(this.key.getOutputPrefix().toByteArray(), Arrays.copyOf(this.mac.doFinal(), this.key.getParameters().getCryptographicTagSizeBytes()));
    }

    @Override // com.google.crypto.tink.mac.ChunkedMacComputation
    public void update(ByteBuffer byteBuffer) {
        if (this.finalized) {
            throw new IllegalStateException("Cannot update after computing the MAC tag. Please create a new object.");
        }
        this.mac.update(byteBuffer);
    }
}
