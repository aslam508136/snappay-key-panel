package com.google.crypto.tink.shaded.protobuf;

import java.io.InputStream;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
final class RopeByteString extends ByteString {
    static final int[] minLengthByDepth = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040, 1346269, 2178309, 3524578, 5702887, 9227465, 14930352, 24157817, 39088169, 63245986, 102334155, 165580141, 267914296, 433494437, 701408733, 1134903170, 1836311903, Reader.READ_DONE};
    private static final long serialVersionUID = 1;
    private final ByteString left;
    private final int leftLength;
    private final ByteString right;
    private final int totalLength;
    private final int treeDepth;

    public static class Balancer {
        private final ArrayDeque<ByteString> prefixesStack;

        private Balancer() {
            this.prefixesStack = new ArrayDeque<>();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public ByteString balance(ByteString byteString, ByteString byteString2) {
            doBalance(byteString);
            doBalance(byteString2);
            ByteString byteStringPop = this.prefixesStack.pop();
            while (!this.prefixesStack.isEmpty()) {
                byteStringPop = new RopeByteString(this.prefixesStack.pop(), byteStringPop);
            }
            return byteStringPop;
        }

        private void doBalance(ByteString byteString) {
            if (byteString.isBalanced()) {
                insert(byteString);
                return;
            }
            if (!(byteString instanceof RopeByteString)) {
                throw new IllegalArgumentException("Has a new type of ByteString been created? Found " + byteString.getClass());
            }
            RopeByteString ropeByteString = (RopeByteString) byteString;
            doBalance(ropeByteString.left);
            doBalance(ropeByteString.right);
        }

        private int getDepthBinForLength(int i2) {
            int iBinarySearch = Arrays.binarySearch(RopeByteString.minLengthByDepth, i2);
            return iBinarySearch < 0 ? (-(iBinarySearch + 1)) - 1 : iBinarySearch;
        }

        private void insert(ByteString byteString) {
            int depthBinForLength = getDepthBinForLength(byteString.size());
            int iMinLength = RopeByteString.minLength(depthBinForLength + 1);
            if (this.prefixesStack.isEmpty() || this.prefixesStack.peek().size() >= iMinLength) {
                this.prefixesStack.push(byteString);
                return;
            }
            int iMinLength2 = RopeByteString.minLength(depthBinForLength);
            ByteString byteStringPop = this.prefixesStack.pop();
            while (true) {
                if (this.prefixesStack.isEmpty() || this.prefixesStack.peek().size() >= iMinLength2) {
                    break;
                } else {
                    byteStringPop = new RopeByteString(this.prefixesStack.pop(), byteStringPop);
                }
            }
            RopeByteString ropeByteString = new RopeByteString(byteStringPop, byteString);
            while (!this.prefixesStack.isEmpty()) {
                if (this.prefixesStack.peek().size() >= RopeByteString.minLength(getDepthBinForLength(ropeByteString.size()) + 1)) {
                    break;
                } else {
                    ropeByteString = new RopeByteString(this.prefixesStack.pop(), ropeByteString);
                }
            }
            this.prefixesStack.push(ropeByteString);
        }
    }

    public static final class PieceIterator implements Iterator<ByteString.LeafByteString> {
        private final ArrayDeque<RopeByteString> breadCrumbs;
        private ByteString.LeafByteString next;

        private PieceIterator(ByteString byteString) {
            ByteString.LeafByteString leafByLeft;
            if (byteString instanceof RopeByteString) {
                RopeByteString ropeByteString = (RopeByteString) byteString;
                ArrayDeque<RopeByteString> arrayDeque = new ArrayDeque<>(ropeByteString.getTreeDepth());
                this.breadCrumbs = arrayDeque;
                arrayDeque.push(ropeByteString);
                leafByLeft = getLeafByLeft(ropeByteString.left);
            } else {
                this.breadCrumbs = null;
                leafByLeft = (ByteString.LeafByteString) byteString;
            }
            this.next = leafByLeft;
        }

        private ByteString.LeafByteString getLeafByLeft(ByteString byteString) {
            while (byteString instanceof RopeByteString) {
                RopeByteString ropeByteString = (RopeByteString) byteString;
                this.breadCrumbs.push(ropeByteString);
                byteString = ropeByteString.left;
            }
            return (ByteString.LeafByteString) byteString;
        }

        private ByteString.LeafByteString getNextNonEmptyLeaf() {
            ByteString.LeafByteString leafByLeft;
            do {
                ArrayDeque<RopeByteString> arrayDeque = this.breadCrumbs;
                if (arrayDeque == null || arrayDeque.isEmpty()) {
                    return null;
                }
                leafByLeft = getLeafByLeft(this.breadCrumbs.pop().right);
            } while (leafByLeft.isEmpty());
            return leafByLeft;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.next != null;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public ByteString.LeafByteString next() {
            ByteString.LeafByteString leafByteString = this.next;
            if (leafByteString == null) {
                throw new NoSuchElementException();
            }
            this.next = getNextNonEmptyLeaf();
            return leafByteString;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public class RopeInputStream extends InputStream {
        private ByteString.LeafByteString currentPiece;
        private int currentPieceIndex;
        private int currentPieceOffsetInRope;
        private int currentPieceSize;
        private int mark;
        private PieceIterator pieceIterator;

        public RopeInputStream() {
            initialize();
        }

        private void advanceIfCurrentPieceFullyRead() {
            if (this.currentPiece != null) {
                int i2 = this.currentPieceIndex;
                int i3 = this.currentPieceSize;
                if (i2 == i3) {
                    this.currentPieceOffsetInRope += i3;
                    int size = 0;
                    this.currentPieceIndex = 0;
                    if (this.pieceIterator.hasNext()) {
                        ByteString.LeafByteString next = this.pieceIterator.next();
                        this.currentPiece = next;
                        size = next.size();
                    } else {
                        this.currentPiece = null;
                    }
                    this.currentPieceSize = size;
                }
            }
        }

        private int availableInternal() {
            return RopeByteString.this.size() - (this.currentPieceOffsetInRope + this.currentPieceIndex);
        }

        private void initialize() {
            PieceIterator pieceIterator = new PieceIterator(RopeByteString.this);
            this.pieceIterator = pieceIterator;
            ByteString.LeafByteString next = pieceIterator.next();
            this.currentPiece = next;
            this.currentPieceSize = next.size();
            this.currentPieceIndex = 0;
            this.currentPieceOffsetInRope = 0;
        }

        private int readSkipInternal(byte[] bArr, int i2, int i3) {
            int i4 = i3;
            while (i4 > 0) {
                advanceIfCurrentPieceFullyRead();
                if (this.currentPiece == null) {
                    break;
                }
                int iMin = Math.min(this.currentPieceSize - this.currentPieceIndex, i4);
                if (bArr != null) {
                    this.currentPiece.copyTo(bArr, this.currentPieceIndex, i2, iMin);
                    i2 += iMin;
                }
                this.currentPieceIndex += iMin;
                i4 -= iMin;
            }
            return i3 - i4;
        }

        @Override // java.io.InputStream
        public int available() {
            return availableInternal();
        }

        @Override // java.io.InputStream
        public void mark(int i2) {
            this.mark = this.currentPieceOffsetInRope + this.currentPieceIndex;
        }

        @Override // java.io.InputStream
        public boolean markSupported() {
            return true;
        }

        @Override // java.io.InputStream
        public int read() {
            advanceIfCurrentPieceFullyRead();
            ByteString.LeafByteString leafByteString = this.currentPiece;
            if (leafByteString == null) {
                return -1;
            }
            int i2 = this.currentPieceIndex;
            this.currentPieceIndex = i2 + 1;
            return leafByteString.byteAt(i2) & 255;
        }

        @Override // java.io.InputStream
        public synchronized void reset() {
            initialize();
            readSkipInternal(null, 0, this.mark);
        }

        @Override // java.io.InputStream
        public long skip(long j2) {
            if (j2 < 0) {
                throw new IndexOutOfBoundsException();
            }
            if (j2 > 2147483647L) {
                j2 = 2147483647L;
            }
            return readSkipInternal(null, 0, (int) j2);
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i2, int i3) {
            bArr.getClass();
            if (i2 < 0 || i3 < 0 || i3 > bArr.length - i2) {
                throw new IndexOutOfBoundsException();
            }
            int skipInternal = readSkipInternal(bArr, i2, i3);
            if (skipInternal != 0) {
                return skipInternal;
            }
            if (i3 > 0 || availableInternal() == 0) {
                return -1;
            }
            return skipInternal;
        }
    }

    private RopeByteString(ByteString byteString, ByteString byteString2) {
        this.left = byteString;
        this.right = byteString2;
        int size = byteString.size();
        this.leftLength = size;
        this.totalLength = byteString2.size() + size;
        this.treeDepth = Math.max(byteString.getTreeDepth(), byteString2.getTreeDepth()) + 1;
    }

    public static ByteString concatenate(ByteString byteString, ByteString byteString2) {
        if (byteString2.size() == 0) {
            return byteString;
        }
        if (byteString.size() == 0) {
            return byteString2;
        }
        int size = byteString2.size() + byteString.size();
        if (size < 128) {
            return concatenateBytes(byteString, byteString2);
        }
        if (byteString instanceof RopeByteString) {
            RopeByteString ropeByteString = (RopeByteString) byteString;
            if (byteString2.size() + ropeByteString.right.size() < 128) {
                return new RopeByteString(ropeByteString.left, concatenateBytes(ropeByteString.right, byteString2));
            }
            if (ropeByteString.left.getTreeDepth() > ropeByteString.right.getTreeDepth() && ropeByteString.getTreeDepth() > byteString2.getTreeDepth()) {
                return new RopeByteString(ropeByteString.left, new RopeByteString(ropeByteString.right, byteString2));
            }
        }
        return size >= minLength(Math.max(byteString.getTreeDepth(), byteString2.getTreeDepth()) + 1) ? new RopeByteString(byteString, byteString2) : new Balancer().balance(byteString, byteString2);
    }

    private static ByteString concatenateBytes(ByteString byteString, ByteString byteString2) {
        int size = byteString.size();
        int size2 = byteString2.size();
        byte[] bArr = new byte[size + size2];
        byteString.copyTo(bArr, 0, 0, size);
        byteString2.copyTo(bArr, 0, size, size2);
        return ByteString.wrap(bArr);
    }

    private boolean equalsFragments(ByteString byteString) {
        ByteString.LeafByteString next;
        PieceIterator pieceIterator = new PieceIterator(this);
        ByteString.LeafByteString next2 = pieceIterator.next();
        PieceIterator pieceIterator2 = new PieceIterator(byteString);
        ByteString.LeafByteString next3 = pieceIterator2.next();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            int size = next2.size() - i2;
            int size2 = next3.size() - i3;
            int iMin = Math.min(size, size2);
            if (!(i2 == 0 ? next2.equalsRange(next3, i3, iMin) : next3.equalsRange(next2, i2, iMin))) {
                return false;
            }
            i4 += iMin;
            int i5 = this.totalLength;
            if (i4 >= i5) {
                if (i4 == i5) {
                    return true;
                }
                throw new IllegalStateException();
            }
            if (iMin == size) {
                next = pieceIterator.next();
                i2 = 0;
            } else {
                i2 += iMin;
            }
            if (iMin == size2) {
                next2 = next2;
                next2 = next;
                next3 = pieceIterator2.next();
                i3 = 0;
            } else {
                next2 = next2;
                next2 = next;
                i3 += iMin;
            }
        }
    }

    public static int minLength(int i2) {
        int[] iArr = minLengthByDepth;
        return i2 >= iArr.length ? Reader.READ_DONE : iArr[i2];
    }

    public static RopeByteString newInstanceForTest(ByteString byteString, ByteString byteString2) {
        return new RopeByteString(byteString, byteString2);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("RopeByteStream instances are not to be serialized directly");
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public ByteBuffer asReadOnlyByteBuffer() {
        return ByteBuffer.wrap(toByteArray()).asReadOnlyBuffer();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public List<ByteBuffer> asReadOnlyByteBufferList() {
        ArrayList arrayList = new ArrayList();
        PieceIterator pieceIterator = new PieceIterator(this);
        while (pieceIterator.hasNext()) {
            arrayList.add(pieceIterator.next().asReadOnlyByteBuffer());
        }
        return arrayList;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public byte byteAt(int i2) {
        ByteString.checkIndex(i2, this.totalLength);
        return internalByteAt(i2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public void copyTo(ByteBuffer byteBuffer) {
        this.left.copyTo(byteBuffer);
        this.right.copyTo(byteBuffer);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public void copyToInternal(byte[] bArr, int i2, int i3, int i4) {
        ByteString byteString;
        int i5 = i2 + i4;
        int i6 = this.leftLength;
        if (i5 <= i6) {
            byteString = this.left;
        } else {
            if (i2 < i6) {
                int i7 = i6 - i2;
                this.left.copyToInternal(bArr, i2, i3, i7);
                this.right.copyToInternal(bArr, 0, i3 + i7, i4 - i7);
                return;
            }
            byteString = this.right;
            i2 -= i6;
        }
        byteString.copyToInternal(bArr, i2, i3, i4);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ByteString)) {
            return false;
        }
        ByteString byteString = (ByteString) obj;
        if (this.totalLength != byteString.size()) {
            return false;
        }
        if (this.totalLength == 0) {
            return true;
        }
        int iPeekCachedHashCode = peekCachedHashCode();
        int iPeekCachedHashCode2 = byteString.peekCachedHashCode();
        if (iPeekCachedHashCode == 0 || iPeekCachedHashCode2 == 0 || iPeekCachedHashCode == iPeekCachedHashCode2) {
            return equalsFragments(byteString);
        }
        return false;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public int getTreeDepth() {
        return this.treeDepth;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public byte internalByteAt(int i2) {
        int i3 = this.leftLength;
        return i2 < i3 ? this.left.internalByteAt(i2) : this.right.internalByteAt(i2 - i3);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public boolean isBalanced() {
        return this.totalLength >= minLength(this.treeDepth);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public boolean isValidUtf8() {
        int iPartialIsValidUtf8 = this.left.partialIsValidUtf8(0, 0, this.leftLength);
        ByteString byteString = this.right;
        return byteString.partialIsValidUtf8(iPartialIsValidUtf8, 0, byteString.size()) == 0;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString, java.lang.Iterable
    /* JADX INFO: renamed from: iterator */
    public Iterator<Byte> iterator2() {
        return new ByteString.AbstractByteIterator() { // from class: com.google.crypto.tink.shaded.protobuf.RopeByteString.1
            ByteString.ByteIterator current = nextPiece();
            final PieceIterator pieces;

            {
                this.pieces = new PieceIterator(RopeByteString.this);
            }

            /* JADX WARN: Type inference failed for: r0v5, types: [com.google.crypto.tink.shaded.protobuf.ByteString$ByteIterator] */
            private ByteString.ByteIterator nextPiece() {
                if (this.pieces.hasNext()) {
                    return this.pieces.next().iterator2();
                }
                return null;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.current != null;
            }

            @Override // com.google.crypto.tink.shaded.protobuf.ByteString.ByteIterator
            public byte nextByte() {
                ByteString.ByteIterator byteIterator = this.current;
                if (byteIterator == null) {
                    throw new NoSuchElementException();
                }
                byte bNextByte = byteIterator.nextByte();
                if (!this.current.hasNext()) {
                    this.current = nextPiece();
                }
                return bNextByte;
            }
        };
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public CodedInputStream newCodedInput() {
        return CodedInputStream.newInstance((Iterable<ByteBuffer>) asReadOnlyByteBufferList(), true);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public InputStream newInput() {
        return new RopeInputStream();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public int partialHash(int i2, int i3, int i4) {
        int i5 = i3 + i4;
        int i6 = this.leftLength;
        if (i5 <= i6) {
            return this.left.partialHash(i2, i3, i4);
        }
        if (i3 >= i6) {
            return this.right.partialHash(i2, i3 - i6, i4);
        }
        int i7 = i6 - i3;
        return this.right.partialHash(this.left.partialHash(i2, i3, i7), 0, i4 - i7);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public int partialIsValidUtf8(int i2, int i3, int i4) {
        int i5 = i3 + i4;
        int i6 = this.leftLength;
        if (i5 <= i6) {
            return this.left.partialIsValidUtf8(i2, i3, i4);
        }
        if (i3 >= i6) {
            return this.right.partialIsValidUtf8(i2, i3 - i6, i4);
        }
        int i7 = i6 - i3;
        return this.right.partialIsValidUtf8(this.left.partialIsValidUtf8(i2, i3, i7), 0, i4 - i7);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public int size() {
        return this.totalLength;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public ByteString substring(int i2, int i3) {
        int iCheckRange = ByteString.checkRange(i2, i3, this.totalLength);
        if (iCheckRange == 0) {
            return ByteString.EMPTY;
        }
        if (iCheckRange == this.totalLength) {
            return this;
        }
        int i4 = this.leftLength;
        if (i3 <= i4) {
            return this.left.substring(i2, i3);
        }
        return i2 >= i4 ? this.right.substring(i2 - i4, i3 - i4) : new RopeByteString(this.left.substring(i2), this.right.substring(0, i3 - this.leftLength));
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public String toStringInternal(Charset charset) {
        return new String(toByteArray(), charset);
    }

    public Object writeReplace() {
        return ByteString.wrap(toByteArray());
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public void writeTo(ByteOutput byteOutput) {
        this.left.writeTo(byteOutput);
        this.right.writeTo(byteOutput);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public void writeToInternal(OutputStream outputStream, int i2, int i3) {
        ByteString byteString;
        int i4 = i2 + i3;
        int i5 = this.leftLength;
        if (i4 <= i5) {
            byteString = this.left;
        } else {
            if (i2 < i5) {
                int i6 = i5 - i2;
                this.left.writeToInternal(outputStream, i2, i6);
                this.right.writeToInternal(outputStream, 0, i3 - i6);
                return;
            }
            byteString = this.right;
            i2 -= i5;
        }
        byteString.writeToInternal(outputStream, i2, i3);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public void writeToReverse(ByteOutput byteOutput) {
        this.right.writeToReverse(byteOutput);
        this.left.writeToReverse(byteOutput);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.ByteString
    public void writeTo(OutputStream outputStream) {
        this.left.writeTo(outputStream);
        this.right.writeTo(outputStream);
    }
}
