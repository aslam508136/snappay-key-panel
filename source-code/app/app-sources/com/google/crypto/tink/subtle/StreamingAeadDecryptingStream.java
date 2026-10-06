package com.google.crypto.tink.subtle;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
class StreamingAeadDecryptingStream extends FilterInputStream {
    private static final int PLAINTEXT_SEGMENT_EXTRA_SIZE = 16;
    private final byte[] aad;
    private final ByteBuffer ciphertextSegment;
    private final int ciphertextSegmentSize;
    private final StreamSegmentDecrypter decrypter;
    private boolean decryptionErrorOccured;
    private boolean endOfCiphertext;
    private boolean endOfPlaintext;
    private final int firstCiphertextSegmentSize;
    private final int headerLength;
    private boolean headerRead;
    private final ByteBuffer plaintextSegment;
    private int segmentNr;

    public StreamingAeadDecryptingStream(NonceBasedStreamingAead nonceBasedStreamingAead, InputStream inputStream, byte[] bArr) {
        super(inputStream);
        this.decrypter = nonceBasedStreamingAead.newStreamSegmentDecrypter();
        this.headerLength = nonceBasedStreamingAead.getHeaderLength();
        this.aad = Arrays.copyOf(bArr, bArr.length);
        int ciphertextSegmentSize = nonceBasedStreamingAead.getCiphertextSegmentSize();
        this.ciphertextSegmentSize = ciphertextSegmentSize;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(ciphertextSegmentSize + 1);
        this.ciphertextSegment = byteBufferAllocate;
        byteBufferAllocate.limit(0);
        this.firstCiphertextSegmentSize = ciphertextSegmentSize - nonceBasedStreamingAead.getCiphertextOffset();
        ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(nonceBasedStreamingAead.getPlaintextSegmentSize() + 16);
        this.plaintextSegment = byteBufferAllocate2;
        byteBufferAllocate2.limit(0);
        this.headerRead = false;
        this.endOfCiphertext = false;
        this.endOfPlaintext = false;
        this.segmentNr = 0;
        this.decryptionErrorOccured = false;
    }

    private void loadSegment() throws IOException {
        byte b2;
        while (!this.endOfCiphertext && this.ciphertextSegment.remaining() > 0) {
            int i2 = ((FilterInputStream) this).in.read(this.ciphertextSegment.array(), this.ciphertextSegment.position(), this.ciphertextSegment.remaining());
            if (i2 > 0) {
                ByteBuffer byteBuffer = this.ciphertextSegment;
                byteBuffer.position(byteBuffer.position() + i2);
            } else if (i2 == -1) {
                this.endOfCiphertext = true;
            } else if (i2 == 0) {
                throw new IOException("Could not read bytes from the ciphertext stream");
            }
        }
        if (this.endOfCiphertext) {
            b2 = 0;
        } else {
            ByteBuffer byteBuffer2 = this.ciphertextSegment;
            b2 = byteBuffer2.get(byteBuffer2.position() - 1);
            ByteBuffer byteBuffer3 = this.ciphertextSegment;
            byteBuffer3.position(byteBuffer3.position() - 1);
        }
        this.ciphertextSegment.flip();
        this.plaintextSegment.clear();
        try {
            this.decrypter.decryptSegment(this.ciphertextSegment, this.segmentNr, this.endOfCiphertext, this.plaintextSegment);
            this.segmentNr++;
            this.plaintextSegment.flip();
            this.ciphertextSegment.clear();
            if (this.endOfCiphertext) {
                return;
            }
            this.ciphertextSegment.clear();
            this.ciphertextSegment.limit(this.ciphertextSegmentSize + 1);
            this.ciphertextSegment.put(b2);
        } catch (GeneralSecurityException e2) {
            setDecryptionErrorOccured();
            throw new IOException(e2.getMessage() + "\n" + toString() + "\nsegmentNr:" + this.segmentNr + " endOfCiphertext:" + this.endOfCiphertext, e2);
        }
    }

    private void readHeader() throws IOException {
        if (this.headerRead) {
            setDecryptionErrorOccured();
            throw new IOException("Decryption failed.");
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(this.headerLength);
        while (byteBufferAllocate.remaining() > 0) {
            int i2 = ((FilterInputStream) this).in.read(byteBufferAllocate.array(), byteBufferAllocate.position(), byteBufferAllocate.remaining());
            if (i2 == -1) {
                setDecryptionErrorOccured();
                throw new IOException("Ciphertext is too short");
            }
            if (i2 == 0) {
                throw new IOException("Could not read bytes from the ciphertext stream");
            }
            byteBufferAllocate.position(byteBufferAllocate.position() + i2);
        }
        byteBufferAllocate.flip();
        try {
            this.decrypter.init(byteBufferAllocate, this.aad);
            this.headerRead = true;
        } catch (GeneralSecurityException e2) {
            throw new IOException(e2);
        }
    }

    private void setDecryptionErrorOccured() {
        this.decryptionErrorOccured = true;
        this.plaintextSegment.limit(0);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int available() {
        return this.plaintextSegment.remaining();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        super.close();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int i2) {
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        byte[] bArr = new byte[1];
        int i2 = read(bArr, 0, 1);
        if (i2 == 1) {
            return bArr[0] & 255;
        }
        if (i2 == -1) {
            return i2;
        }
        throw new IOException("Reading failed");
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j2) {
        int i2;
        long j3 = this.ciphertextSegmentSize;
        if (j2 <= 0) {
            return 0L;
        }
        int iMin = (int) Math.min(j3, j2);
        byte[] bArr = new byte[iMin];
        long j4 = j2;
        while (j4 > 0 && (i2 = read(bArr, 0, (int) Math.min(iMin, j4))) > 0) {
            j4 -= (long) i2;
        }
        return j2 - j4;
    }

    public synchronized String toString() {
        return "StreamingAeadDecryptingStream\nsegmentNr:" + this.segmentNr + "\nciphertextSegmentSize:" + this.ciphertextSegmentSize + "\nheaderRead:" + this.headerRead + "\nendOfCiphertext:" + this.endOfCiphertext + "\nendOfPlaintext:" + this.endOfPlaintext + "\ndecryptionErrorOccured:" + this.decryptionErrorOccured + "\nciphertextSgement position:" + this.ciphertextSegment.position() + " limit:" + this.ciphertextSegment.limit() + "\nplaintextSegment position:" + this.plaintextSegment.position() + " limit:" + this.plaintextSegment.limit();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read(byte[] bArr, int i2, int i3) {
        if (this.decryptionErrorOccured) {
            throw new IOException("Decryption failed.");
        }
        if (!this.headerRead) {
            readHeader();
            this.ciphertextSegment.clear();
            this.ciphertextSegment.limit(this.firstCiphertextSegmentSize + 1);
        }
        if (this.endOfPlaintext) {
            return -1;
        }
        int i4 = 0;
        while (i4 < i3) {
            if (this.plaintextSegment.remaining() == 0) {
                if (this.endOfCiphertext) {
                    this.endOfPlaintext = true;
                    break;
                }
                loadSegment();
            }
            int iMin = Math.min(this.plaintextSegment.remaining(), i3 - i4);
            this.plaintextSegment.get(bArr, i4 + i2, iMin);
            i4 += iMin;
        }
        if (i4 == 0 && this.endOfPlaintext) {
            return -1;
        }
        return i4;
    }
}
