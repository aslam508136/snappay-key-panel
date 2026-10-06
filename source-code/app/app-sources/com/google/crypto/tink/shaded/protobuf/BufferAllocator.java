package com.google.crypto.tink.shaded.protobuf;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
@CheckReturnValue
abstract class BufferAllocator {
    private static final BufferAllocator UNPOOLED = new BufferAllocator() { // from class: com.google.crypto.tink.shaded.protobuf.BufferAllocator.1
        @Override // com.google.crypto.tink.shaded.protobuf.BufferAllocator
        public AllocatedBuffer allocateDirectBuffer(int i2) {
            return AllocatedBuffer.wrap(ByteBuffer.allocateDirect(i2));
        }

        @Override // com.google.crypto.tink.shaded.protobuf.BufferAllocator
        public AllocatedBuffer allocateHeapBuffer(int i2) {
            return AllocatedBuffer.wrap(new byte[i2]);
        }
    };

    public static BufferAllocator unpooled() {
        return UNPOOLED;
    }

    public abstract AllocatedBuffer allocateDirectBuffer(int i2);

    public abstract AllocatedBuffer allocateHeapBuffer(int i2);
}
