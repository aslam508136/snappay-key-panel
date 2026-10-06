package com.google.crypto.tink.mac.internal;

import com.google.crypto.tink.AccessesPartialKey;
import com.google.crypto.tink.InsecureSecretKeyAccess;
import com.google.crypto.tink.mac.AesCmacKey;
import com.google.crypto.tink.mac.AesCmacParameters;
import com.google.crypto.tink.mac.ChunkedMacComputation;
import com.google.crypto.tink.subtle.Bytes;
import com.google.crypto.tink.subtle.EngineFactory;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
@AccessesPartialKey
final class ChunkedAesCmacComputation implements ChunkedMacComputation {
    private static final byte[] FORMAT_VERSION = {0};
    private final Cipher aes;
    private boolean finalized = false;
    private final AesCmacKey key;
    private final ByteBuffer localStash;
    private final byte[] subKey1;
    private final byte[] subKey2;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private final ByteBuffer f514x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private final ByteBuffer f515y;

    public ChunkedAesCmacComputation(AesCmacKey aesCmacKey) throws InvalidKeyException {
        this.key = aesCmacKey;
        Cipher engineFactory = EngineFactory.CIPHER.getInstance("AES/ECB/NoPadding");
        this.aes = engineFactory;
        engineFactory.init(1, new SecretKeySpec(aesCmacKey.getAesKey().toByteArray(InsecureSecretKeyAccess.get()), "AES"));
        byte[] bArrDbl = AesUtil.dbl(engineFactory.doFinal(new byte[16]));
        this.subKey1 = bArrDbl;
        this.subKey2 = AesUtil.dbl(bArrDbl);
        this.localStash = ByteBuffer.allocate(16);
        this.f514x = ByteBuffer.allocate(16);
        this.f515y = ByteBuffer.allocate(16);
    }

    private void munch(ByteBuffer byteBuffer) throws BadPaddingException, IllegalBlockSizeException, ShortBufferException {
        this.f515y.rewind();
        this.f514x.rewind();
        Bytes.xor(this.f515y, this.f514x, byteBuffer, 16);
        this.f515y.rewind();
        this.f514x.rewind();
        this.aes.doFinal(this.f515y, this.f514x);
    }

    @Override // com.google.crypto.tink.mac.ChunkedMacComputation
    public byte[] computeMac() throws BadPaddingException, IllegalBlockSizeException, ShortBufferException {
        if (this.finalized) {
            throw new IllegalStateException("Can not compute after computing the MAC tag. Please create a new object.");
        }
        if (this.key.getParameters().getVariant() == AesCmacParameters.Variant.LEGACY) {
            update(ByteBuffer.wrap(FORMAT_VERSION));
        }
        this.finalized = true;
        return Bytes.concat(this.key.getOutputPrefix().toByteArray(), Arrays.copyOf(this.aes.doFinal(Bytes.xor(this.localStash.remaining() > 0 ? Bytes.xor(AesUtil.cmacPad(Arrays.copyOf(this.localStash.array(), this.localStash.position())), this.subKey2) : Bytes.xor(this.localStash.array(), 0, this.subKey1, 0, 16), this.f514x.array())), this.key.getParameters().getCryptographicTagSizeBytes()));
    }

    @Override // com.google.crypto.tink.mac.ChunkedMacComputation
    public void update(ByteBuffer byteBuffer) throws BadPaddingException, IllegalBlockSizeException, ShortBufferException {
        if (this.finalized) {
            throw new IllegalStateException("Can not update after computing the MAC tag. Please create a new object.");
        }
        if (this.localStash.remaining() != 16) {
            int iMin = Math.min(this.localStash.remaining(), byteBuffer.remaining());
            for (int i2 = 0; i2 < iMin; i2++) {
                this.localStash.put(byteBuffer.get());
            }
        }
        if (this.localStash.remaining() == 0 && byteBuffer.remaining() > 0) {
            this.localStash.rewind();
            munch(this.localStash);
            this.localStash.rewind();
        }
        while (byteBuffer.remaining() > 16) {
            munch(byteBuffer);
        }
        this.localStash.put(byteBuffer);
    }
}
