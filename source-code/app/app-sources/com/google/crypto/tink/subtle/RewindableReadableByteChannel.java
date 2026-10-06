package com.google.crypto.tink.subtle;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: classes.dex */
public final class RewindableReadableByteChannel implements ReadableByteChannel {

    @GuardedBy("this")
    final ReadableByteChannel baseChannel;

    @GuardedBy("this")
    ByteBuffer buffer = null;

    @GuardedBy("this")
    boolean canRewind = true;

    @GuardedBy("this")
    boolean directRead = false;

    public RewindableReadableByteChannel(ReadableByteChannel readableByteChannel) {
        this.baseChannel = readableByteChannel;
    }

    private synchronized void setBufferLimit(int i2) {
        if (this.buffer.capacity() < i2) {
            int iPosition = this.buffer.position();
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(Math.max(this.buffer.capacity() * 2, i2));
            this.buffer.rewind();
            byteBufferAllocate.put(this.buffer);
            byteBufferAllocate.position(iPosition);
            this.buffer = byteBufferAllocate;
        }
        this.buffer.limit(i2);
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        this.canRewind = false;
        this.directRead = true;
        this.baseChannel.close();
    }

    public synchronized void disableRewinding() {
        this.canRewind = false;
    }

    @Override // java.nio.channels.Channel
    public synchronized boolean isOpen() {
        return this.baseChannel.isOpen();
    }

    @Override // java.nio.channels.ReadableByteChannel
    public synchronized int read(ByteBuffer byteBuffer) {
        if (this.directRead) {
            return this.baseChannel.read(byteBuffer);
        }
        int iRemaining = byteBuffer.remaining();
        if (iRemaining == 0) {
            return 0;
        }
        ByteBuffer byteBuffer2 = this.buffer;
        if (byteBuffer2 == null) {
            if (!this.canRewind) {
                this.directRead = true;
                return this.baseChannel.read(byteBuffer);
            }
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(iRemaining);
            this.buffer = byteBufferAllocate;
            int i2 = this.baseChannel.read(byteBufferAllocate);
            this.buffer.flip();
            if (i2 > 0) {
                byteBuffer.put(this.buffer);
            }
            return i2;
        }
        if (byteBuffer2.remaining() >= iRemaining) {
            int iLimit = this.buffer.limit();
            ByteBuffer byteBuffer3 = this.buffer;
            byteBuffer3.limit(byteBuffer3.position() + iRemaining);
            byteBuffer.put(this.buffer);
            this.buffer.limit(iLimit);
            if (!this.canRewind && !this.buffer.hasRemaining()) {
                this.buffer = null;
                this.directRead = true;
            }
            return iRemaining;
        }
        int iRemaining2 = this.buffer.remaining();
        int iPosition = this.buffer.position();
        int iLimit2 = this.buffer.limit();
        setBufferLimit((iRemaining - iRemaining2) + iLimit2);
        this.buffer.position(iLimit2);
        int i3 = this.baseChannel.read(this.buffer);
        this.buffer.flip();
        this.buffer.position(iPosition);
        byteBuffer.put(this.buffer);
        if (iRemaining2 == 0 && i3 < 0) {
            return -1;
        }
        int iPosition2 = this.buffer.position() - iPosition;
        if (!this.canRewind && !this.buffer.hasRemaining()) {
            this.buffer = null;
            this.directRead = true;
        }
        return iPosition2;
    }

    public synchronized void rewind() {
        if (!this.canRewind) {
            throw new IOException("Cannot rewind anymore.");
        }
        ByteBuffer byteBuffer = this.buffer;
        if (byteBuffer != null) {
            byteBuffer.position(0);
        }
    }
}
