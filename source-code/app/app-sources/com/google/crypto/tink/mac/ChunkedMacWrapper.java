package com.google.crypto.tink.mac;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.errorprone.annotations.Immutable;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ChunkedMacWrapper implements PrimitiveWrapper<ChunkedMac, ChunkedMac> {
    private static final ChunkedMacWrapper WRAPPER = new ChunkedMacWrapper();

    @Immutable
    public static class WrappedChunkedMac implements ChunkedMac {
        private final PrimitiveSet<ChunkedMac> primitives;

        private WrappedChunkedMac(PrimitiveSet<ChunkedMac> primitiveSet) {
            this.primitives = primitiveSet;
        }

        private ChunkedMac getChunkedMac(PrimitiveSet.Entry<ChunkedMac> entry) {
            return entry.getFullPrimitive();
        }

        @Override // com.google.crypto.tink.mac.ChunkedMac
        public ChunkedMacComputation createComputation() {
            return getChunkedMac(this.primitives.getPrimary()).createComputation();
        }

        @Override // com.google.crypto.tink.mac.ChunkedMac
        public ChunkedMacVerification createVerification(byte[] bArr) {
            byte[] bArrCopyOf = Arrays.copyOf(bArr, 5);
            ArrayList arrayList = new ArrayList();
            Iterator<PrimitiveSet.Entry<ChunkedMac>> it = this.primitives.getPrimitive(bArrCopyOf).iterator();
            while (it.hasNext()) {
                arrayList.add(getChunkedMac(it.next()).createVerification(bArr));
            }
            Iterator<PrimitiveSet.Entry<ChunkedMac>> it2 = this.primitives.getRawPrimitives().iterator();
            while (it2.hasNext()) {
                arrayList.add(getChunkedMac(it2.next()).createVerification(bArr));
            }
            return new WrappedChunkedMacVerification(arrayList);
        }
    }

    public static class WrappedChunkedMacVerification implements ChunkedMacVerification {
        private final List<ChunkedMacVerification> verifications;

        private WrappedChunkedMacVerification(List<ChunkedMacVerification> list) {
            this.verifications = list;
        }

        @Override // com.google.crypto.tink.mac.ChunkedMacVerification
        public void update(ByteBuffer byteBuffer) {
            ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
            byteBufferDuplicate.mark();
            for (ChunkedMacVerification chunkedMacVerification : this.verifications) {
                byteBufferDuplicate.reset();
                chunkedMacVerification.update(byteBufferDuplicate);
            }
            byteBuffer.position(byteBuffer.limit());
        }

        @Override // com.google.crypto.tink.mac.ChunkedMacVerification
        public void verifyMac() throws GeneralSecurityException {
            GeneralSecurityException generalSecurityException = new GeneralSecurityException("MAC verification failed for all suitable keys in keyset");
            Iterator<ChunkedMacVerification> it = this.verifications.iterator();
            while (it.hasNext()) {
                try {
                    it.next().verifyMac();
                    return;
                } catch (GeneralSecurityException e2) {
                    generalSecurityException.addSuppressed(e2);
                }
            }
            throw generalSecurityException;
        }
    }

    private ChunkedMacWrapper() {
    }

    public static void register() {
        Registry.registerPrimitiveWrapper(WRAPPER);
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<ChunkedMac> getInputPrimitiveClass() {
        return ChunkedMac.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<ChunkedMac> getPrimitiveClass() {
        return ChunkedMac.class;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public ChunkedMac wrap(PrimitiveSet<ChunkedMac> primitiveSet) throws GeneralSecurityException {
        if (primitiveSet == null) {
            throw new GeneralSecurityException("primitive set must be non-null");
        }
        if (primitiveSet.getPrimary() == null) {
            throw new GeneralSecurityException("no primary in primitive set");
        }
        Iterator<List<PrimitiveSet.Entry<ChunkedMac>>> it = primitiveSet.getAll().iterator();
        while (it.hasNext()) {
            Iterator<PrimitiveSet.Entry<ChunkedMac>> it2 = it.next().iterator();
            while (it2.hasNext()) {
                it2.next().getFullPrimitive();
            }
        }
        return new WrappedChunkedMac(primitiveSet);
    }
}
