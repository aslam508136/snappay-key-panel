package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.internal.BigIntegerEncoding;
import com.google.crypto.tink.proto.HpkePublicKey;
import com.google.crypto.tink.subtle.Bytes;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes.dex */
@ThreadSafe
final class HpkeContext {
    private static final byte[] EMPTY_IKM = new byte[0];
    private final HpkeAead aead;
    private final byte[] baseNonce;
    private final byte[] encapsulatedKey;
    private final byte[] key;
    private final BigInteger maxSequenceNumber;

    @GuardedBy("this")
    private BigInteger sequenceNumber = BigInteger.ZERO;

    private HpkeContext(byte[] bArr, byte[] bArr2, byte[] bArr3, BigInteger bigInteger, HpkeAead hpkeAead) {
        this.encapsulatedKey = bArr;
        this.key = bArr2;
        this.baseNonce = bArr3;
        this.maxSequenceNumber = bigInteger;
        this.aead = hpkeAead;
    }

    @GuardedBy("this")
    private byte[] computeNonce() {
        return Bytes.xor(this.baseNonce, BigIntegerEncoding.toBigEndianBytesOfFixedLength(this.sequenceNumber, this.aead.getNonceLength()));
    }

    private synchronized byte[] computeNonceAndIncrementSequenceNumber() {
        byte[] bArrComputeNonce;
        bArrComputeNonce = computeNonce();
        incrementSequenceNumber();
        return bArrComputeNonce;
    }

    public static HpkeContext createContext(byte[] bArr, byte[] bArr2, HpkeKem hpkeKem, HpkeKdf hpkeKdf, HpkeAead hpkeAead, byte[] bArr3) throws GeneralSecurityException {
        byte[] bArrHpkeSuiteId = HpkeUtil.hpkeSuiteId(hpkeKem.getKemId(), hpkeKdf.getKdfId(), hpkeAead.getAeadId());
        byte[] bArr4 = HpkeUtil.EMPTY_SALT;
        byte[] bArr5 = EMPTY_IKM;
        byte[] bArrConcat = Bytes.concat(HpkeUtil.BASE_MODE, hpkeKdf.labeledExtract(bArr4, bArr5, "psk_id_hash", bArrHpkeSuiteId), hpkeKdf.labeledExtract(bArr4, bArr3, "info_hash", bArrHpkeSuiteId));
        byte[] bArrLabeledExtract = hpkeKdf.labeledExtract(bArr2, bArr5, "secret", bArrHpkeSuiteId);
        return new HpkeContext(bArr, hpkeKdf.labeledExpand(bArrLabeledExtract, bArrConcat, "key", bArrHpkeSuiteId, hpkeAead.getKeyLength()), hpkeKdf.labeledExpand(bArrLabeledExtract, bArrConcat, "base_nonce", bArrHpkeSuiteId, hpkeAead.getNonceLength()), maxSequenceNumber(hpkeAead.getNonceLength()), hpkeAead);
    }

    public static HpkeContext createRecipientContext(byte[] bArr, HpkeKemPrivateKey hpkeKemPrivateKey, HpkeKem hpkeKem, HpkeKdf hpkeKdf, HpkeAead hpkeAead, byte[] bArr2) {
        return createContext(bArr, hpkeKem.decapsulate(bArr, hpkeKemPrivateKey), hpkeKem, hpkeKdf, hpkeAead, bArr2);
    }

    public static HpkeContext createSenderContext(HpkePublicKey hpkePublicKey, HpkeKem hpkeKem, HpkeKdf hpkeKdf, HpkeAead hpkeAead, byte[] bArr) {
        HpkeKemEncapOutput hpkeKemEncapOutputEncapsulate = hpkeKem.encapsulate(hpkePublicKey.getPublicKey().toByteArray());
        return createContext(hpkeKemEncapOutputEncapsulate.getEncapsulatedKey(), hpkeKemEncapOutputEncapsulate.getSharedSecret(), hpkeKem, hpkeKdf, hpkeAead, bArr);
    }

    @GuardedBy("this")
    private void incrementSequenceNumber() throws GeneralSecurityException {
        if (this.sequenceNumber.compareTo(this.maxSequenceNumber) >= 0) {
            throw new GeneralSecurityException("message limit reached");
        }
        this.sequenceNumber = this.sequenceNumber.add(BigInteger.ONE);
    }

    private static BigInteger maxSequenceNumber(int i2) {
        BigInteger bigInteger = BigInteger.ONE;
        return bigInteger.shiftLeft(i2 * 8).subtract(bigInteger);
    }

    public byte[] getBaseNonce() {
        return this.baseNonce;
    }

    public byte[] getEncapsulatedKey() {
        return this.encapsulatedKey;
    }

    public byte[] getKey() {
        return this.key;
    }

    public byte[] open(byte[] bArr, byte[] bArr2) {
        return this.aead.open(this.key, computeNonceAndIncrementSequenceNumber(), bArr, bArr2);
    }

    public byte[] seal(byte[] bArr, byte[] bArr2) {
        return this.aead.seal(this.key, computeNonceAndIncrementSequenceNumber(), bArr, bArr2);
    }
}
