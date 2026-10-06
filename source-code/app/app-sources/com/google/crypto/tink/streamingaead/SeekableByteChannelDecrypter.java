package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.StreamingAead;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.NonWritableChannelException;
import java.nio.channels.SeekableByteChannel;
import java.security.GeneralSecurityException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: classes.dex */
final class SeekableByteChannelDecrypter implements SeekableByteChannel {
    byte[] associatedData;

    @GuardedBy("this")
    long cachedPosition;

    @GuardedBy("this")
    SeekableByteChannel ciphertextChannel;

    @GuardedBy("this")
    long startingPosition;

    @GuardedBy("this")
    SeekableByteChannel attemptingChannel = null;

    @GuardedBy("this")
    SeekableByteChannel matchingChannel = null;
    Deque<StreamingAead> remainingPrimitives = new ArrayDeque();

    public SeekableByteChannelDecrypter(PrimitiveSet<StreamingAead> primitiveSet, SeekableByteChannel seekableByteChannel, byte[] bArr) {
        Iterator<PrimitiveSet.Entry<StreamingAead>> it = primitiveSet.getRawPrimitives().iterator();
        while (it.hasNext()) {
            this.remainingPrimitives.add(it.next().getPrimitive());
        }
        this.ciphertextChannel = seekableByteChannel;
        this.cachedPosition = -1L;
        this.startingPosition = seekableByteChannel.position();
        this.associatedData = (byte[]) bArr.clone();
    }

    @GuardedBy("this")
    private synchronized SeekableByteChannel nextAttemptingChannel() {
        SeekableByteChannel seekableByteChannelNewSeekableDecryptingChannel;
        while (!this.remainingPrimitives.isEmpty()) {
            this.ciphertextChannel.position(this.startingPosition);
            try {
                seekableByteChannelNewSeekableDecryptingChannel = this.remainingPrimitives.removeFirst().newSeekableDecryptingChannel(this.ciphertextChannel, this.associatedData);
                long j2 = this.cachedPosition;
                if (j2 >= 0) {
                    seekableByteChannelNewSeekableDecryptingChannel.position(j2);
                }
            } catch (GeneralSecurityException unused) {
            }
        }
        throw new IOException("No matching key found for the ciphertext in the stream.");
        return seekableByteChannelNewSeekableDecryptingChannel;
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    @GuardedBy("this")
    public synchronized void close() {
        this.ciphertextChannel.close();
    }

    @Override // java.nio.channels.Channel
    @GuardedBy("this")
    public synchronized boolean isOpen() {
        return this.ciphertextChannel.isOpen();
    }

    @Override // java.nio.channels.SeekableByteChannel
    @GuardedBy("this")
    public synchronized long position() {
        SeekableByteChannel seekableByteChannel = this.matchingChannel;
        if (seekableByteChannel != null) {
            return seekableByteChannel.position();
        }
        return this.cachedPosition;
    }

    @Override // java.nio.channels.SeekableByteChannel, java.nio.channels.ReadableByteChannel
    @GuardedBy("this")
    public synchronized int read(ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() == 0) {
            return 0;
        }
        SeekableByteChannel seekableByteChannel = this.matchingChannel;
        if (seekableByteChannel != null) {
            return seekableByteChannel.read(byteBuffer);
        }
        if (this.attemptingChannel == null) {
            this.attemptingChannel = nextAttemptingChannel();
        }
        while (true) {
            try {
                int i2 = this.attemptingChannel.read(byteBuffer);
                if (i2 == 0) {
                    return 0;
                }
                this.matchingChannel = this.attemptingChannel;
                this.attemptingChannel = null;
                return i2;
            } catch (IOException unused) {
                this.attemptingChannel = nextAttemptingChannel();
            }
            throw th;
            this.attemptingChannel = nextAttemptingChannel();
        }
    }

    @Override // java.nio.channels.SeekableByteChannel
    @GuardedBy("this")
    public synchronized long size() {
        SeekableByteChannel seekableByteChannel;
        seekableByteChannel = this.matchingChannel;
        if (seekableByteChannel == null) {
            throw new IOException("Cannot determine size before first read()-call.");
        }
        return seekableByteChannel.size();
    }

    @Override // java.nio.channels.SeekableByteChannel
    public SeekableByteChannel truncate(long j2) {
        throw new NonWritableChannelException();
    }

    @Override // java.nio.channels.SeekableByteChannel, java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) {
        throw new NonWritableChannelException();
    }

    @Override // java.nio.channels.SeekableByteChannel
    @CanIgnoreReturnValue
    @GuardedBy("this")
    public synchronized SeekableByteChannel position(long j2) {
        SeekableByteChannel seekableByteChannel = this.matchingChannel;
        if (seekableByteChannel != null) {
            seekableByteChannel.position(j2);
        } else {
            if (j2 < 0) {
                throw new IllegalArgumentException("Position must be non-negative");
            }
            this.cachedPosition = j2;
            SeekableByteChannel seekableByteChannel2 = this.attemptingChannel;
            if (seekableByteChannel2 != null) {
                seekableByteChannel2.position(j2);
            }
        }
        return this;
    }
}
