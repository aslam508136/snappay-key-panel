package com.google.crypto.tink.subtle;

import com.google.crypto.tink.config.internal.TinkFipsUtil;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
public final class AesCtrHmacStreaming extends NonceBasedStreamingAead {
    public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS;
    private static final int HMAC_KEY_SIZE_IN_BYTES = 32;
    private static final int NONCE_PREFIX_IN_BYTES = 7;
    private static final int NONCE_SIZE_IN_BYTES = 16;
    private final int ciphertextSegmentSize;
    private final int firstSegmentOffset;
    private final String hkdfAlgo;
    private final byte[] ikm;
    private final int keySizeInBytes;
    private final int plaintextSegmentSize;
    private final String tagAlgo;
    private final int tagSizeInBytes;

    public class AesCtrHmacStreamDecrypter implements StreamSegmentDecrypter {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private Cipher cipher;
        private SecretKeySpec hmacKeySpec;
        private SecretKeySpec keySpec;
        private Mac mac;
        private byte[] noncePrefix;

        public AesCtrHmacStreamDecrypter() {
        }

        @Override // com.google.crypto.tink.subtle.StreamSegmentDecrypter
        public synchronized void decryptSegment(ByteBuffer byteBuffer, int i2, boolean z2, ByteBuffer byteBuffer2) {
            int iPosition = byteBuffer.position();
            byte[] bArrNonceForSegment = AesCtrHmacStreaming.this.nonceForSegment(this.noncePrefix, i2, z2);
            int iRemaining = byteBuffer.remaining();
            if (iRemaining < AesCtrHmacStreaming.this.tagSizeInBytes) {
                throw new GeneralSecurityException("Ciphertext too short");
            }
            int i3 = (iRemaining - AesCtrHmacStreaming.this.tagSizeInBytes) + iPosition;
            ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
            byteBufferDuplicate.limit(i3);
            ByteBuffer byteBufferDuplicate2 = byteBuffer.duplicate();
            byteBufferDuplicate2.position(i3);
            this.mac.init(this.hmacKeySpec);
            this.mac.update(bArrNonceForSegment);
            this.mac.update(byteBufferDuplicate);
            byte[] bArrCopyOf = Arrays.copyOf(this.mac.doFinal(), AesCtrHmacStreaming.this.tagSizeInBytes);
            byte[] bArr = new byte[AesCtrHmacStreaming.this.tagSizeInBytes];
            byteBufferDuplicate2.get(bArr);
            if (!Bytes.equal(bArr, bArrCopyOf)) {
                throw new GeneralSecurityException("Tag mismatch");
            }
            byteBuffer.limit(i3);
            this.cipher.init(1, this.keySpec, new IvParameterSpec(bArrNonceForSegment));
            this.cipher.doFinal(byteBuffer, byteBuffer2);
        }

        @Override // com.google.crypto.tink.subtle.StreamSegmentDecrypter
        public synchronized void init(ByteBuffer byteBuffer, byte[] bArr) {
            if (byteBuffer.remaining() != AesCtrHmacStreaming.this.getHeaderLength()) {
                throw new InvalidAlgorithmParameterException("Invalid header length");
            }
            if (byteBuffer.get() != AesCtrHmacStreaming.this.getHeaderLength()) {
                throw new GeneralSecurityException("Invalid ciphertext");
            }
            this.noncePrefix = new byte[7];
            byte[] bArr2 = new byte[AesCtrHmacStreaming.this.keySizeInBytes];
            byteBuffer.get(bArr2);
            byteBuffer.get(this.noncePrefix);
            byte[] bArrDeriveKeyMaterial = AesCtrHmacStreaming.this.deriveKeyMaterial(bArr2, bArr);
            this.keySpec = AesCtrHmacStreaming.this.deriveKeySpec(bArrDeriveKeyMaterial);
            this.hmacKeySpec = AesCtrHmacStreaming.this.deriveHmacKeySpec(bArrDeriveKeyMaterial);
            this.cipher = AesCtrHmacStreaming.cipherInstance();
            this.mac = AesCtrHmacStreaming.this.macInstance();
        }
    }

    public class AesCtrHmacStreamEncrypter implements StreamSegmentEncrypter {
        private final Cipher cipher = AesCtrHmacStreaming.cipherInstance();
        private long encryptedSegments;
        private ByteBuffer header;
        private final SecretKeySpec hmacKeySpec;
        private final SecretKeySpec keySpec;
        private final Mac mac;
        private final byte[] noncePrefix;

        public AesCtrHmacStreamEncrypter(byte[] bArr) {
            this.encryptedSegments = 0L;
            this.mac = AesCtrHmacStreaming.this.macInstance();
            this.encryptedSegments = 0L;
            byte[] bArrRandomSalt = AesCtrHmacStreaming.this.randomSalt();
            byte[] bArrRandomNonce = AesCtrHmacStreaming.this.randomNonce();
            this.noncePrefix = bArrRandomNonce;
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(AesCtrHmacStreaming.this.getHeaderLength());
            this.header = byteBufferAllocate;
            byteBufferAllocate.put((byte) AesCtrHmacStreaming.this.getHeaderLength());
            this.header.put(bArrRandomSalt);
            this.header.put(bArrRandomNonce);
            this.header.flip();
            byte[] bArrDeriveKeyMaterial = AesCtrHmacStreaming.this.deriveKeyMaterial(bArrRandomSalt, bArr);
            this.keySpec = AesCtrHmacStreaming.this.deriveKeySpec(bArrDeriveKeyMaterial);
            this.hmacKeySpec = AesCtrHmacStreaming.this.deriveHmacKeySpec(bArrDeriveKeyMaterial);
        }

        @Override // com.google.crypto.tink.subtle.StreamSegmentEncrypter
        public synchronized void encryptSegment(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, boolean z2, ByteBuffer byteBuffer3) {
            int iPosition = byteBuffer3.position();
            byte[] bArrNonceForSegment = AesCtrHmacStreaming.this.nonceForSegment(this.noncePrefix, this.encryptedSegments, z2);
            this.cipher.init(1, this.keySpec, new IvParameterSpec(bArrNonceForSegment));
            this.encryptedSegments++;
            this.cipher.update(byteBuffer, byteBuffer3);
            this.cipher.doFinal(byteBuffer2, byteBuffer3);
            ByteBuffer byteBufferDuplicate = byteBuffer3.duplicate();
            byteBufferDuplicate.flip();
            byteBufferDuplicate.position(iPosition);
            this.mac.init(this.hmacKeySpec);
            this.mac.update(bArrNonceForSegment);
            this.mac.update(byteBufferDuplicate);
            byteBuffer3.put(this.mac.doFinal(), 0, AesCtrHmacStreaming.this.tagSizeInBytes);
        }

        @Override // com.google.crypto.tink.subtle.StreamSegmentEncrypter
        public ByteBuffer getHeader() {
            return this.header.asReadOnlyBuffer();
        }

        @Override // com.google.crypto.tink.subtle.StreamSegmentEncrypter
        public synchronized void encryptSegment(ByteBuffer byteBuffer, boolean z2, ByteBuffer byteBuffer2) {
            int iPosition = byteBuffer2.position();
            byte[] bArrNonceForSegment = AesCtrHmacStreaming.this.nonceForSegment(this.noncePrefix, this.encryptedSegments, z2);
            this.cipher.init(1, this.keySpec, new IvParameterSpec(bArrNonceForSegment));
            this.encryptedSegments++;
            this.cipher.doFinal(byteBuffer, byteBuffer2);
            ByteBuffer byteBufferDuplicate = byteBuffer2.duplicate();
            byteBufferDuplicate.flip();
            byteBufferDuplicate.position(iPosition);
            this.mac.init(this.hmacKeySpec);
            this.mac.update(bArrNonceForSegment);
            this.mac.update(byteBufferDuplicate);
            byteBuffer2.put(this.mac.doFinal(), 0, AesCtrHmacStreaming.this.tagSizeInBytes);
        }
    }

    public AesCtrHmacStreaming(byte[] bArr, String str, int i2, String str2, int i3, int i4, int i5) throws GeneralSecurityException {
        if (!FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use AES-CTR-HMAC streaming in FIPS-mode.");
        }
        validateParameters(bArr.length, i2, str2, i3, i4, i5);
        this.ikm = Arrays.copyOf(bArr, bArr.length);
        this.hkdfAlgo = str;
        this.keySizeInBytes = i2;
        this.tagAlgo = str2;
        this.tagSizeInBytes = i3;
        this.ciphertextSegmentSize = i4;
        this.firstSegmentOffset = i5;
        this.plaintextSegmentSize = i4 - i3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Cipher cipherInstance() {
        return EngineFactory.CIPHER.getInstance("AES/CTR/NoPadding");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SecretKeySpec deriveHmacKeySpec(byte[] bArr) {
        return new SecretKeySpec(bArr, this.keySizeInBytes, 32, this.tagAlgo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] deriveKeyMaterial(byte[] bArr, byte[] bArr2) {
        return Hkdf.computeHkdf(this.hkdfAlgo, this.ikm, bArr, bArr2, this.keySizeInBytes + 32);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SecretKeySpec deriveKeySpec(byte[] bArr) {
        return new SecretKeySpec(bArr, 0, this.keySizeInBytes, "AES");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Mac macInstance() {
        return EngineFactory.MAC.getInstance(this.tagAlgo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] nonceForSegment(byte[] bArr, long j2, boolean z2) throws GeneralSecurityException {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(16);
        byteBufferAllocate.order(ByteOrder.BIG_ENDIAN);
        byteBufferAllocate.put(bArr);
        SubtleUtil.putAsUnsigedInt(byteBufferAllocate, j2);
        byteBufferAllocate.put(z2 ? (byte) 1 : (byte) 0);
        byteBufferAllocate.putInt(0);
        return byteBufferAllocate.array();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] randomNonce() {
        return Random.randBytes(7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] randomSalt() {
        return Random.randBytes(this.keySizeInBytes);
    }

    private static void validateParameters(int i2, int i3, String str, int i4, int i5, int i6) throws InvalidAlgorithmParameterException {
        if (i2 < 16 || i2 < i3) {
            throw new InvalidAlgorithmParameterException("ikm too short, must be >= " + Math.max(16, i3));
        }
        Validators.validateAesKeySize(i3);
        if (i4 < 10) {
            throw new InvalidAlgorithmParameterException("tag size too small " + i4);
        }
        if ((str.equals("HmacSha1") && i4 > 20) || ((str.equals("HmacSha256") && i4 > 32) || (str.equals("HmacSha512") && i4 > 64))) {
            throw new InvalidAlgorithmParameterException("tag size too big");
        }
        if (((((i5 - i6) - i4) - i3) - 7) - 1 <= 0) {
            throw new InvalidAlgorithmParameterException("ciphertextSegmentSize too small");
        }
    }

    public long expectedCiphertextSize(long j2) {
        long ciphertextOffset = j2 + ((long) getCiphertextOffset());
        int i2 = this.plaintextSegmentSize;
        long j3 = (ciphertextOffset / ((long) i2)) * ((long) this.ciphertextSegmentSize);
        long j4 = ciphertextOffset % ((long) i2);
        return j4 > 0 ? j3 + j4 + ((long) this.tagSizeInBytes) : j3;
    }

    @Override // com.google.crypto.tink.subtle.NonceBasedStreamingAead
    public int getCiphertextOffset() {
        return getHeaderLength() + this.firstSegmentOffset;
    }

    @Override // com.google.crypto.tink.subtle.NonceBasedStreamingAead
    public int getCiphertextOverhead() {
        return this.tagSizeInBytes;
    }

    @Override // com.google.crypto.tink.subtle.NonceBasedStreamingAead
    public int getCiphertextSegmentSize() {
        return this.ciphertextSegmentSize;
    }

    public int getFirstSegmentOffset() {
        return this.firstSegmentOffset;
    }

    @Override // com.google.crypto.tink.subtle.NonceBasedStreamingAead
    public int getHeaderLength() {
        return this.keySizeInBytes + 1 + 7;
    }

    @Override // com.google.crypto.tink.subtle.NonceBasedStreamingAead
    public int getPlaintextSegmentSize() {
        return this.plaintextSegmentSize;
    }

    @Override // com.google.crypto.tink.subtle.NonceBasedStreamingAead, com.google.crypto.tink.StreamingAead
    public /* bridge */ /* synthetic */ ReadableByteChannel newDecryptingChannel(ReadableByteChannel readableByteChannel, byte[] bArr) {
        return super.newDecryptingChannel(readableByteChannel, bArr);
    }

    @Override // com.google.crypto.tink.subtle.NonceBasedStreamingAead, com.google.crypto.tink.StreamingAead
    public /* bridge */ /* synthetic */ InputStream newDecryptingStream(InputStream inputStream, byte[] bArr) {
        return super.newDecryptingStream(inputStream, bArr);
    }

    @Override // com.google.crypto.tink.subtle.NonceBasedStreamingAead, com.google.crypto.tink.StreamingAead
    public /* bridge */ /* synthetic */ WritableByteChannel newEncryptingChannel(WritableByteChannel writableByteChannel, byte[] bArr) {
        return super.newEncryptingChannel(writableByteChannel, bArr);
    }

    @Override // com.google.crypto.tink.subtle.NonceBasedStreamingAead, com.google.crypto.tink.StreamingAead
    public /* bridge */ /* synthetic */ OutputStream newEncryptingStream(OutputStream outputStream, byte[] bArr) {
        return super.newEncryptingStream(outputStream, bArr);
    }

    @Override // com.google.crypto.tink.subtle.NonceBasedStreamingAead, com.google.crypto.tink.StreamingAead
    public /* bridge */ /* synthetic */ SeekableByteChannel newSeekableDecryptingChannel(SeekableByteChannel seekableByteChannel, byte[] bArr) {
        return super.newSeekableDecryptingChannel(seekableByteChannel, bArr);
    }

    @Override // com.google.crypto.tink.subtle.NonceBasedStreamingAead
    public AesCtrHmacStreamDecrypter newStreamSegmentDecrypter() {
        return new AesCtrHmacStreamDecrypter();
    }

    @Override // com.google.crypto.tink.subtle.NonceBasedStreamingAead
    public AesCtrHmacStreamEncrypter newStreamSegmentEncrypter(byte[] bArr) {
        return new AesCtrHmacStreamEncrypter(bArr);
    }
}
