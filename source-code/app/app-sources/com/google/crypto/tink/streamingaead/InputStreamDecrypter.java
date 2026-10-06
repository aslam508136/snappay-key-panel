package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.StreamingAead;
import com.google.crypto.tink.shaded.protobuf.Reader;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Iterator;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: classes.dex */
final class InputStreamDecrypter extends InputStream {
    byte[] associatedData;

    @GuardedBy("this")
    InputStream ciphertextStream;
    PrimitiveSet<StreamingAead> primitives;

    @GuardedBy("this")
    boolean attemptedMatching = false;

    @GuardedBy("this")
    InputStream matchingStream = null;

    public InputStreamDecrypter(PrimitiveSet<StreamingAead> primitiveSet, InputStream inputStream, byte[] bArr) {
        this.primitives = primitiveSet;
        if (inputStream.markSupported()) {
            this.ciphertextStream = inputStream;
        } else {
            this.ciphertextStream = new BufferedInputStream(inputStream);
        }
        this.ciphertextStream.mark(Reader.READ_DONE);
        this.associatedData = (byte[]) bArr.clone();
    }

    @GuardedBy("this")
    private void disableRewinding() {
        this.ciphertextStream.mark(0);
    }

    @GuardedBy("this")
    private void rewind() throws IOException {
        this.ciphertextStream.reset();
    }

    @Override // java.io.InputStream
    @GuardedBy("this")
    public synchronized int available() {
        InputStream inputStream = this.matchingStream;
        if (inputStream == null) {
            return 0;
        }
        return inputStream.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    @GuardedBy("this")
    public synchronized void close() {
        this.ciphertextStream.close();
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.InputStream
    @GuardedBy("this")
    public synchronized int read() {
        byte[] bArr = new byte[1];
        if (read(bArr) != 1) {
            return -1;
        }
        return bArr[0];
    }

    @Override // java.io.InputStream
    @GuardedBy("this")
    public synchronized int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    @GuardedBy("this")
    public synchronized int read(byte[] bArr, int i2, int i3) {
        if (i3 == 0) {
            return 0;
        }
        InputStream inputStream = this.matchingStream;
        if (inputStream != null) {
            return inputStream.read(bArr, i2, i3);
        }
        if (this.attemptedMatching) {
            throw new IOException("No matching key found for the ciphertext in the stream.");
        }
        this.attemptedMatching = true;
        Iterator<PrimitiveSet.Entry<StreamingAead>> it = this.primitives.getRawPrimitives().iterator();
        while (it.hasNext()) {
            try {
                InputStream inputStreamNewDecryptingStream = it.next().getPrimitive().newDecryptingStream(this.ciphertextStream, this.associatedData);
                int i4 = inputStreamNewDecryptingStream.read(bArr, i2, i3);
                if (i4 == 0) {
                    throw new IOException("Could not read bytes from the ciphertext stream");
                }
                this.matchingStream = inputStreamNewDecryptingStream;
                disableRewinding();
                return i4;
            } catch (IOException | GeneralSecurityException unused) {
                rewind();
            }
        }
        throw new IOException("No matching key found for the ciphertext in the stream.");
    }
}
