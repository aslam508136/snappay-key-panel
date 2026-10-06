package com.google.crypto.tink.shaded.protobuf;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
final class Utf8 {
    private static final long ASCII_MASK_LONG = -9187201950435737472L;
    static final int COMPLETE = 0;
    static final int MALFORMED = -1;
    static final int MAX_BYTES_PER_CHAR = 3;
    private static final int UNSAFE_COUNT_ASCII_THRESHOLD = 16;
    private static final Processor processor;

    public static class DecodeUtil {
        private DecodeUtil() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void handleFourBytes(byte b2, byte b3, byte b4, byte b5, char[] cArr, int i2) throws InvalidProtocolBufferException {
            if (!isNotTrailingByte(b3)) {
                if ((((b3 + 112) + (b2 << 28)) >> 30) == 0 && !isNotTrailingByte(b4) && !isNotTrailingByte(b5)) {
                    int iTrailingByteValue = ((b2 & 7) << 18) | (trailingByteValue(b3) << 12) | (trailingByteValue(b4) << 6) | trailingByteValue(b5);
                    cArr[i2] = highSurrogate(iTrailingByteValue);
                    cArr[i2 + 1] = lowSurrogate(iTrailingByteValue);
                    return;
                }
            }
            throw InvalidProtocolBufferException.invalidUtf8();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void handleOneByte(byte b2, char[] cArr, int i2) {
            cArr[i2] = (char) b2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void handleThreeBytes(byte b2, byte b3, byte b4, char[] cArr, int i2) throws InvalidProtocolBufferException {
            if (isNotTrailingByte(b3) || ((b2 == -32 && b3 < -96) || ((b2 == -19 && b3 >= -96) || isNotTrailingByte(b4)))) {
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            cArr[i2] = (char) (((b2 & 15) << 12) | (trailingByteValue(b3) << 6) | trailingByteValue(b4));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void handleTwoBytes(byte b2, byte b3, char[] cArr, int i2) throws InvalidProtocolBufferException {
            if (b2 < -62 || isNotTrailingByte(b3)) {
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            cArr[i2] = (char) (((b2 & 31) << 6) | trailingByteValue(b3));
        }

        private static char highSurrogate(int i2) {
            return (char) ((i2 >>> 10) + 55232);
        }

        private static boolean isNotTrailingByte(byte b2) {
            return b2 > -65;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isOneByte(byte b2) {
            return b2 >= 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isThreeBytes(byte b2) {
            return b2 < -16;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isTwoBytes(byte b2) {
            return b2 < -32;
        }

        private static char lowSurrogate(int i2) {
            return (char) ((i2 & 1023) + 56320);
        }

        private static int trailingByteValue(byte b2) {
            return b2 & 63;
        }
    }

    public static abstract class Processor {
        public final String decodeUtf8(ByteBuffer byteBuffer, int i2, int i3) {
            if (byteBuffer.hasArray()) {
                return decodeUtf8(byteBuffer.array(), byteBuffer.arrayOffset() + i2, i3);
            }
            return byteBuffer.isDirect() ? decodeUtf8Direct(byteBuffer, i2, i3) : decodeUtf8Default(byteBuffer, i2, i3);
        }

        public abstract String decodeUtf8(byte[] bArr, int i2, int i3);

        public final String decodeUtf8Default(ByteBuffer byteBuffer, int i2, int i3) throws InvalidProtocolBufferException {
            if ((i2 | i3 | ((byteBuffer.limit() - i2) - i3)) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", Integer.valueOf(byteBuffer.limit()), Integer.valueOf(i2), Integer.valueOf(i3)));
            }
            int i4 = i2 + i3;
            char[] cArr = new char[i3];
            int i5 = 0;
            while (i2 < i4) {
                byte b2 = byteBuffer.get(i2);
                if (!DecodeUtil.isOneByte(b2)) {
                    break;
                }
                i2++;
                DecodeUtil.handleOneByte(b2, cArr, i5);
                i5++;
            }
            int i6 = i5;
            while (i2 < i4) {
                int i7 = i2 + 1;
                byte b3 = byteBuffer.get(i2);
                if (DecodeUtil.isOneByte(b3)) {
                    int i8 = i6 + 1;
                    DecodeUtil.handleOneByte(b3, cArr, i6);
                    while (i7 < i4) {
                        byte b4 = byteBuffer.get(i7);
                        if (!DecodeUtil.isOneByte(b4)) {
                            break;
                        }
                        i7++;
                        DecodeUtil.handleOneByte(b4, cArr, i8);
                        i8++;
                    }
                    i2 = i7;
                    i6 = i8;
                } else if (DecodeUtil.isTwoBytes(b3)) {
                    if (i7 >= i4) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    DecodeUtil.handleTwoBytes(b3, byteBuffer.get(i7), cArr, i6);
                    i2 = i7 + 1;
                    i6++;
                } else if (DecodeUtil.isThreeBytes(b3)) {
                    if (i7 >= i4 + Utf8.MALFORMED) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    int i9 = i7 + 1;
                    DecodeUtil.handleThreeBytes(b3, byteBuffer.get(i7), byteBuffer.get(i9), cArr, i6);
                    i2 = i9 + 1;
                    i6++;
                } else {
                    if (i7 >= i4 - 2) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    int i10 = i7 + 1;
                    byte b5 = byteBuffer.get(i7);
                    int i11 = i10 + 1;
                    DecodeUtil.handleFourBytes(b3, b5, byteBuffer.get(i10), byteBuffer.get(i11), cArr, i6);
                    i2 = i11 + 1;
                    i6 = i6 + 1 + 1;
                }
            }
            return new String(cArr, 0, i6);
        }

        public abstract String decodeUtf8Direct(ByteBuffer byteBuffer, int i2, int i3);

        public abstract int encodeUtf8(CharSequence charSequence, byte[] bArr, int i2, int i3);

        public final void encodeUtf8(CharSequence charSequence, ByteBuffer byteBuffer) {
            if (byteBuffer.hasArray()) {
                int iArrayOffset = byteBuffer.arrayOffset();
                byteBuffer.position(Utf8.encode(charSequence, byteBuffer.array(), byteBuffer.position() + iArrayOffset, byteBuffer.remaining()) - iArrayOffset);
            } else if (byteBuffer.isDirect()) {
                encodeUtf8Direct(charSequence, byteBuffer);
            } else {
                encodeUtf8Default(charSequence, byteBuffer);
            }
        }

        public final void encodeUtf8Default(CharSequence charSequence, ByteBuffer byteBuffer) {
            int length = charSequence.length();
            int iPosition = byteBuffer.position();
            int i2 = 0;
            while (i2 < length) {
                try {
                    char cCharAt = charSequence.charAt(i2);
                    if (cCharAt >= 128) {
                        break;
                    }
                    byteBuffer.put(iPosition + i2, (byte) cCharAt);
                    i2++;
                } catch (IndexOutOfBoundsException unused) {
                }
            }
            if (i2 == length) {
                byteBuffer.position(iPosition + i2);
                return;
            }
            iPosition += i2;
            while (i2 < length) {
                char cCharAt2 = charSequence.charAt(i2);
                if (cCharAt2 < 128) {
                    byteBuffer.put(iPosition, (byte) cCharAt2);
                } else if (cCharAt2 < 2048) {
                    int i3 = iPosition + 1;
                    try {
                        byteBuffer.put(iPosition, (byte) ((cCharAt2 >>> 6) | 192));
                        byteBuffer.put(i3, (byte) ((cCharAt2 & '?') | 128));
                        iPosition = i3;
                    } catch (IndexOutOfBoundsException unused2) {
                        iPosition = i3;
                    }
                } else {
                    if (cCharAt2 >= 55296 && 57343 >= cCharAt2) {
                        int i4 = i2 + 1;
                        if (i4 != length) {
                            try {
                                char cCharAt3 = charSequence.charAt(i4);
                                if (Character.isSurrogatePair(cCharAt2, cCharAt3)) {
                                    int codePoint = Character.toCodePoint(cCharAt2, cCharAt3);
                                    int i5 = iPosition + 1;
                                    try {
                                        byteBuffer.put(iPosition, (byte) ((codePoint >>> 18) | 240));
                                        iPosition = i5 + 1;
                                        byteBuffer.put(i5, (byte) (((codePoint >>> 12) & 63) | 128));
                                        i5 = iPosition + 1;
                                        byteBuffer.put(iPosition, (byte) (((codePoint >>> 6) & 63) | 128));
                                        byteBuffer.put(i5, (byte) ((codePoint & 63) | 128));
                                        iPosition = i5;
                                        i2 = i4;
                                    } catch (IndexOutOfBoundsException unused3) {
                                        iPosition = i5;
                                    }
                                } else {
                                    i2 = i4;
                                }
                            } catch (IndexOutOfBoundsException unused4) {
                            }
                            i2 = i4;
                            throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(i2) + " at index " + (Math.max(i2, (iPosition - byteBuffer.position()) + 1) + byteBuffer.position()));
                        }
                        throw new UnpairedSurrogateException(i2, length);
                    }
                    int i6 = iPosition + 1;
                    byteBuffer.put(iPosition, (byte) ((cCharAt2 >>> '\f') | 224));
                    iPosition = i6 + 1;
                    byteBuffer.put(i6, (byte) (((cCharAt2 >>> 6) & 63) | 128));
                    byteBuffer.put(iPosition, (byte) ((cCharAt2 & '?') | 128));
                }
                i2++;
                iPosition++;
            }
            byteBuffer.position(iPosition);
        }

        public abstract void encodeUtf8Direct(CharSequence charSequence, ByteBuffer byteBuffer);

        public final boolean isValidUtf8(ByteBuffer byteBuffer, int i2, int i3) {
            return partialIsValidUtf8(0, byteBuffer, i2, i3) == 0;
        }

        public final int partialIsValidUtf8(int i2, ByteBuffer byteBuffer, int i3, int i4) {
            if (!byteBuffer.hasArray()) {
                return byteBuffer.isDirect() ? partialIsValidUtf8Direct(i2, byteBuffer, i3, i4) : partialIsValidUtf8Default(i2, byteBuffer, i3, i4);
            }
            int iArrayOffset = byteBuffer.arrayOffset();
            return partialIsValidUtf8(i2, byteBuffer.array(), i3 + iArrayOffset, iArrayOffset + i4);
        }

        public abstract int partialIsValidUtf8(int i2, byte[] bArr, int i3, int i4);

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0017, code lost:
        
            if (r8.get(r9) > (-65)) goto L13;
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x004c, code lost:
        
            if (r8.get(r9) > (-65)) goto L32;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x008f, code lost:
        
            if (r8.get(r7) > (-65)) goto L53;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final int partialIsValidUtf8Default(int i2, ByteBuffer byteBuffer, int i3, int i4) {
            byte b2;
            int i5;
            int i6;
            if (i2 != 0) {
                if (i3 >= i4) {
                    return i2;
                }
                byte b3 = (byte) i2;
                if (b3 < -32) {
                    if (b3 >= -62) {
                        i6 = i3 + 1;
                    }
                    return Utf8.MALFORMED;
                }
                if (b3 < -16) {
                    byte b4 = (byte) (~(i2 >> 8));
                    if (b4 == 0) {
                        int i7 = i3 + 1;
                        byte b5 = byteBuffer.get(i3);
                        if (i7 >= i4) {
                            return Utf8.incompleteStateFor(b3, b5);
                        }
                        i3 = i7;
                        b4 = b5;
                    }
                    if (b4 <= -65 && ((b3 != -32 || b4 >= -96) && (b3 != -19 || b4 < -96))) {
                        i6 = i3 + 1;
                    }
                    return Utf8.MALFORMED;
                }
                byte b6 = (byte) (~(i2 >> 8));
                if (b6 == 0) {
                    i5 = i3 + 1;
                    b6 = byteBuffer.get(i3);
                    if (i5 >= i4) {
                        return Utf8.incompleteStateFor(b3, b6);
                    }
                    b2 = 0;
                } else {
                    b2 = (byte) (i2 >> 16);
                    i5 = i3;
                }
                if (b2 == 0) {
                    int i8 = i5 + 1;
                    byte b7 = byteBuffer.get(i5);
                    if (i8 >= i4) {
                        return Utf8.incompleteStateFor(b3, b6, b7);
                    }
                    b2 = b7;
                    i5 = i8;
                }
                if (b6 <= -65) {
                    if ((((b6 + 112) + (b3 << 28)) >> 30) == 0 && b2 <= -65) {
                        i3 = i5 + 1;
                    }
                }
                return Utf8.MALFORMED;
                i3 = i6;
            }
            return partialIsValidUtf8(byteBuffer, i3, i4);
        }

        public abstract int partialIsValidUtf8Direct(int i2, ByteBuffer byteBuffer, int i3, int i4);

        private static int partialIsValidUtf8(ByteBuffer byteBuffer, int i2, int i3) {
            int iEstimateConsecutiveAscii = i2 + Utf8.estimateConsecutiveAscii(byteBuffer, i2, i3);
            while (iEstimateConsecutiveAscii < i3) {
                int i4 = iEstimateConsecutiveAscii + 1;
                byte b2 = byteBuffer.get(iEstimateConsecutiveAscii);
                if (b2 < 0) {
                    if (b2 >= -32) {
                        if (b2 < -16) {
                            if (i4 < i3 + Utf8.MALFORMED) {
                                int i5 = i4 + 1;
                                byte b3 = byteBuffer.get(i4);
                                if (b3 > -65 || ((b2 == -32 && b3 < -96) || ((b2 == -19 && b3 >= -96) || byteBuffer.get(i5) > -65))) {
                                    return Utf8.MALFORMED;
                                }
                                iEstimateConsecutiveAscii = i5 + 1;
                            }
                        } else if (i4 < i3 - 2) {
                            int i6 = i4 + 1;
                            byte b4 = byteBuffer.get(i4);
                            if (b4 <= -65) {
                                if ((((b4 + 112) + (b2 << 28)) >> 30) == 0) {
                                    int i7 = i6 + 1;
                                    if (byteBuffer.get(i6) <= -65) {
                                        i4 = i7 + 1;
                                        if (byteBuffer.get(i7) > -65) {
                                        }
                                    }
                                }
                            }
                            return Utf8.MALFORMED;
                        }
                        return Utf8.incompleteStateFor(byteBuffer, b2, i4, i3 - i4);
                    }
                    if (i4 >= i3) {
                        return b2;
                    }
                    if (b2 < -62 || byteBuffer.get(i4) > -65) {
                        return Utf8.MALFORMED;
                    }
                    i4++;
                }
                iEstimateConsecutiveAscii = i4;
            }
            return 0;
        }

        public final boolean isValidUtf8(byte[] bArr, int i2, int i3) {
            return partialIsValidUtf8(0, bArr, i2, i3) == 0;
        }
    }

    public static final class SafeProcessor extends Processor {
        private static int partialIsValidUtf8NonAscii(byte[] bArr, int i2, int i3) {
            while (i2 < i3) {
                int i4 = i2 + 1;
                byte b2 = bArr[i2];
                if (b2 < 0) {
                    if (b2 < -32) {
                        if (i4 >= i3) {
                            return b2;
                        }
                        if (b2 >= -62) {
                            i2 = i4 + 1;
                            if (bArr[i4] > -65) {
                            }
                        }
                        return Utf8.MALFORMED;
                    }
                    if (b2 < -16) {
                        if (i4 >= i3 + Utf8.MALFORMED) {
                            return Utf8.incompleteStateFor(bArr, i4, i3);
                        }
                        int i5 = i4 + 1;
                        byte b3 = bArr[i4];
                        if (b3 <= -65 && ((b2 != -32 || b3 >= -96) && (b2 != -19 || b3 < -96))) {
                            i2 = i5 + 1;
                            if (bArr[i5] > -65) {
                            }
                        }
                        return Utf8.MALFORMED;
                    }
                    if (i4 >= i3 - 2) {
                        return Utf8.incompleteStateFor(bArr, i4, i3);
                    }
                    int i6 = i4 + 1;
                    byte b4 = bArr[i4];
                    if (b4 <= -65) {
                        if ((((b4 + 112) + (b2 << 28)) >> 30) == 0) {
                            int i7 = i6 + 1;
                            if (bArr[i6] <= -65) {
                                i4 = i7 + 1;
                                if (bArr[i7] > -65) {
                                }
                            }
                        }
                    }
                    return Utf8.MALFORMED;
                }
                i2 = i4;
            }
            return 0;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Utf8.Processor
        public String decodeUtf8(byte[] bArr, int i2, int i3) throws InvalidProtocolBufferException {
            if ((i2 | i3 | ((bArr.length - i2) - i3)) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(bArr.length), Integer.valueOf(i2), Integer.valueOf(i3)));
            }
            int i4 = i2 + i3;
            char[] cArr = new char[i3];
            int i5 = 0;
            while (i2 < i4) {
                byte b2 = bArr[i2];
                if (!DecodeUtil.isOneByte(b2)) {
                    break;
                }
                i2++;
                DecodeUtil.handleOneByte(b2, cArr, i5);
                i5++;
            }
            int i6 = i5;
            while (i2 < i4) {
                int i7 = i2 + 1;
                byte b3 = bArr[i2];
                if (DecodeUtil.isOneByte(b3)) {
                    int i8 = i6 + 1;
                    DecodeUtil.handleOneByte(b3, cArr, i6);
                    while (i7 < i4) {
                        byte b4 = bArr[i7];
                        if (!DecodeUtil.isOneByte(b4)) {
                            break;
                        }
                        i7++;
                        DecodeUtil.handleOneByte(b4, cArr, i8);
                        i8++;
                    }
                    i2 = i7;
                    i6 = i8;
                } else if (DecodeUtil.isTwoBytes(b3)) {
                    if (i7 >= i4) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    DecodeUtil.handleTwoBytes(b3, bArr[i7], cArr, i6);
                    i2 = i7 + 1;
                    i6++;
                } else if (DecodeUtil.isThreeBytes(b3)) {
                    if (i7 >= i4 + Utf8.MALFORMED) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    int i9 = i7 + 1;
                    DecodeUtil.handleThreeBytes(b3, bArr[i7], bArr[i9], cArr, i6);
                    i2 = i9 + 1;
                    i6++;
                } else {
                    if (i7 >= i4 - 2) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    int i10 = i7 + 1;
                    byte b5 = bArr[i7];
                    int i11 = i10 + 1;
                    DecodeUtil.handleFourBytes(b3, b5, bArr[i10], bArr[i11], cArr, i6);
                    i2 = i11 + 1;
                    i6 = i6 + 1 + 1;
                }
            }
            return new String(cArr, 0, i6);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Utf8.Processor
        public String decodeUtf8Direct(ByteBuffer byteBuffer, int i2, int i3) {
            return decodeUtf8Default(byteBuffer, i2, i3);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Utf8.Processor
        public int encodeUtf8(CharSequence charSequence, byte[] bArr, int i2, int i3) {
            int i4;
            int i5;
            int i6;
            char cCharAt;
            int length = charSequence.length();
            int i7 = i3 + i2;
            int i8 = 0;
            while (i8 < length && (i6 = i8 + i2) < i7 && (cCharAt = charSequence.charAt(i8)) < 128) {
                bArr[i6] = (byte) cCharAt;
                i8++;
            }
            if (i8 == length) {
                return i2 + length;
            }
            int i9 = i2 + i8;
            while (i8 < length) {
                char cCharAt2 = charSequence.charAt(i8);
                if (cCharAt2 >= 128 || i9 >= i7) {
                    if (cCharAt2 < 2048 && i9 <= i7 - 2) {
                        int i10 = i9 + 1;
                        bArr[i9] = (byte) ((cCharAt2 >>> 6) | 960);
                        i9 = i10 + 1;
                        bArr[i10] = (byte) ((cCharAt2 & '?') | 128);
                    } else {
                        if ((cCharAt2 >= 55296 && 57343 >= cCharAt2) || i9 > i7 - 3) {
                            if (i9 > i7 - 4) {
                                if (55296 <= cCharAt2 && cCharAt2 <= 57343 && ((i5 = i8 + 1) == charSequence.length() || !Character.isSurrogatePair(cCharAt2, charSequence.charAt(i5)))) {
                                    throw new UnpairedSurrogateException(i8, length);
                                }
                                throw new ArrayIndexOutOfBoundsException("Failed writing " + cCharAt2 + " at index " + i9);
                            }
                            int i11 = i8 + 1;
                            if (i11 != charSequence.length()) {
                                char cCharAt3 = charSequence.charAt(i11);
                                if (Character.isSurrogatePair(cCharAt2, cCharAt3)) {
                                    int codePoint = Character.toCodePoint(cCharAt2, cCharAt3);
                                    int i12 = i9 + 1;
                                    bArr[i9] = (byte) ((codePoint >>> 18) | 240);
                                    int i13 = i12 + 1;
                                    bArr[i12] = (byte) (((codePoint >>> 12) & 63) | 128);
                                    int i14 = i13 + 1;
                                    bArr[i13] = (byte) (((codePoint >>> 6) & 63) | 128);
                                    i9 = i14 + 1;
                                    bArr[i14] = (byte) ((codePoint & 63) | 128);
                                    i8 = i11;
                                } else {
                                    i8 = i11;
                                }
                            }
                            throw new UnpairedSurrogateException(i8 + Utf8.MALFORMED, length);
                        }
                        int i15 = i9 + 1;
                        bArr[i9] = (byte) ((cCharAt2 >>> '\f') | 480);
                        int i16 = i15 + 1;
                        bArr[i15] = (byte) (((cCharAt2 >>> 6) & 63) | 128);
                        i4 = i16 + 1;
                        bArr[i16] = (byte) ((cCharAt2 & '?') | 128);
                    }
                    i8++;
                } else {
                    i4 = i9 + 1;
                    bArr[i9] = (byte) cCharAt2;
                }
                i9 = i4;
                i8++;
            }
            return i9;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Utf8.Processor
        public void encodeUtf8Direct(CharSequence charSequence, ByteBuffer byteBuffer) {
            encodeUtf8Default(charSequence, byteBuffer);
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0015, code lost:
        
            if (r8[r9] > (-65)) goto L13;
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x0046, code lost:
        
            if (r8[r9] > (-65)) goto L32;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x0083, code lost:
        
            if (r8[r7] > (-65)) goto L53;
         */
        @Override // com.google.crypto.tink.shaded.protobuf.Utf8.Processor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public int partialIsValidUtf8(int i2, byte[] bArr, int i3, int i4) {
            byte b2;
            int i5;
            int i6;
            if (i2 != 0) {
                if (i3 >= i4) {
                    return i2;
                }
                byte b3 = (byte) i2;
                if (b3 < -32) {
                    if (b3 >= -62) {
                        i6 = i3 + 1;
                    }
                    return Utf8.MALFORMED;
                }
                if (b3 < -16) {
                    byte b4 = (byte) (~(i2 >> 8));
                    if (b4 == 0) {
                        int i7 = i3 + 1;
                        byte b5 = bArr[i3];
                        if (i7 >= i4) {
                            return Utf8.incompleteStateFor(b3, b5);
                        }
                        i3 = i7;
                        b4 = b5;
                    }
                    if (b4 <= -65 && ((b3 != -32 || b4 >= -96) && (b3 != -19 || b4 < -96))) {
                        i6 = i3 + 1;
                    }
                    return Utf8.MALFORMED;
                }
                byte b6 = (byte) (~(i2 >> 8));
                if (b6 == 0) {
                    i5 = i3 + 1;
                    b6 = bArr[i3];
                    if (i5 >= i4) {
                        return Utf8.incompleteStateFor(b3, b6);
                    }
                    b2 = 0;
                } else {
                    b2 = (byte) (i2 >> 16);
                    i5 = i3;
                }
                if (b2 == 0) {
                    int i8 = i5 + 1;
                    byte b7 = bArr[i5];
                    if (i8 >= i4) {
                        return Utf8.incompleteStateFor(b3, b6, b7);
                    }
                    b2 = b7;
                    i5 = i8;
                }
                if (b6 <= -65) {
                    if ((((b6 + 112) + (b3 << 28)) >> 30) == 0 && b2 <= -65) {
                        i3 = i5 + 1;
                    }
                }
                return Utf8.MALFORMED;
                i3 = i6;
            }
            return partialIsValidUtf8(bArr, i3, i4);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Utf8.Processor
        public int partialIsValidUtf8Direct(int i2, ByteBuffer byteBuffer, int i3, int i4) {
            return partialIsValidUtf8Default(i2, byteBuffer, i3, i4);
        }

        private static int partialIsValidUtf8(byte[] bArr, int i2, int i3) {
            while (i2 < i3 && bArr[i2] >= 0) {
                i2++;
            }
            if (i2 >= i3) {
                return 0;
            }
            return partialIsValidUtf8NonAscii(bArr, i2, i3);
        }
    }

    public static class UnpairedSurrogateException extends IllegalArgumentException {
        public UnpairedSurrogateException(int i2, int i3) {
            super("Unpaired surrogate at index " + i2 + " of " + i3);
        }
    }

    public static final class UnsafeProcessor extends Processor {
        public static boolean isAvailable() {
            return UnsafeUtil.hasUnsafeArrayOperations() && UnsafeUtil.hasUnsafeByteBufferOperations();
        }

        private static int unsafeEstimateConsecutiveAscii(long j2, int i2) {
            if (i2 < 16) {
                return 0;
            }
            int i3 = (int) ((-j2) & 7);
            int i4 = i3;
            while (i4 > 0) {
                long j3 = 1 + j2;
                if (UnsafeUtil.getByte(j2) < 0) {
                    return i3 - i4;
                }
                i4 += Utf8.MALFORMED;
                j2 = j3;
            }
            int i5 = i2 - i3;
            while (i5 >= 8 && (UnsafeUtil.getLong(j2) & Utf8.ASCII_MASK_LONG) == 0) {
                j2 += 8;
                i5 -= 8;
            }
            return i2 - i5;
        }

        private static int unsafeIncompleteStateFor(long j2, int i2, int i3) {
            if (i3 == 0) {
                return Utf8.incompleteStateFor(i2);
            }
            if (i3 == 1) {
                return Utf8.incompleteStateFor(i2, UnsafeUtil.getByte(j2));
            }
            if (i3 == 2) {
                return Utf8.incompleteStateFor(i2, UnsafeUtil.getByte(j2), UnsafeUtil.getByte(j2 + 1));
            }
            throw new AssertionError();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Utf8.Processor
        public String decodeUtf8(byte[] bArr, int i2, int i3) throws InvalidProtocolBufferException {
            Charset charset = Internal.UTF_8;
            String str = new String(bArr, i2, i3, charset);
            if (str.contains("�") && !Arrays.equals(str.getBytes(charset), Arrays.copyOfRange(bArr, i2, i3 + i2))) {
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            return str;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Utf8.Processor
        public String decodeUtf8Direct(ByteBuffer byteBuffer, int i2, int i3) throws InvalidProtocolBufferException {
            if ((i2 | i3 | ((byteBuffer.limit() - i2) - i3)) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", Integer.valueOf(byteBuffer.limit()), Integer.valueOf(i2), Integer.valueOf(i3)));
            }
            long jAddressOffset = UnsafeUtil.addressOffset(byteBuffer) + ((long) i2);
            long j2 = ((long) i3) + jAddressOffset;
            char[] cArr = new char[i3];
            int i4 = 0;
            while (jAddressOffset < j2) {
                byte b2 = UnsafeUtil.getByte(jAddressOffset);
                if (!DecodeUtil.isOneByte(b2)) {
                    break;
                }
                jAddressOffset++;
                DecodeUtil.handleOneByte(b2, cArr, i4);
                i4++;
            }
            while (true) {
                int i5 = i4;
                while (jAddressOffset < j2) {
                    long j3 = jAddressOffset + 1;
                    byte b3 = UnsafeUtil.getByte(jAddressOffset);
                    if (DecodeUtil.isOneByte(b3)) {
                        int i6 = i5 + 1;
                        DecodeUtil.handleOneByte(b3, cArr, i5);
                        while (j3 < j2) {
                            byte b4 = UnsafeUtil.getByte(j3);
                            if (!DecodeUtil.isOneByte(b4)) {
                                break;
                            }
                            j3++;
                            DecodeUtil.handleOneByte(b4, cArr, i6);
                            i6++;
                        }
                        i5 = i6;
                        jAddressOffset = j3;
                    } else if (DecodeUtil.isTwoBytes(b3)) {
                        if (j3 >= j2) {
                            throw InvalidProtocolBufferException.invalidUtf8();
                        }
                        jAddressOffset = j3 + 1;
                        DecodeUtil.handleTwoBytes(b3, UnsafeUtil.getByte(j3), cArr, i5);
                        i5++;
                    } else if (DecodeUtil.isThreeBytes(b3)) {
                        if (j3 >= j2 - 1) {
                            throw InvalidProtocolBufferException.invalidUtf8();
                        }
                        long j4 = j3 + 1;
                        DecodeUtil.handleThreeBytes(b3, UnsafeUtil.getByte(j3), UnsafeUtil.getByte(j4), cArr, i5);
                        i5++;
                        jAddressOffset = j4 + 1;
                    } else {
                        if (j3 >= j2 - 2) {
                            throw InvalidProtocolBufferException.invalidUtf8();
                        }
                        long j5 = j3 + 1;
                        byte b5 = UnsafeUtil.getByte(j3);
                        long j6 = j5 + 1;
                        byte b6 = UnsafeUtil.getByte(j5);
                        jAddressOffset = j6 + 1;
                        DecodeUtil.handleFourBytes(b3, b5, b6, UnsafeUtil.getByte(j6), cArr, i5);
                        i4 = i5 + 1 + 1;
                    }
                }
                return new String(cArr, 0, i5);
            }
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Utf8.Processor
        public int encodeUtf8(CharSequence charSequence, byte[] bArr, int i2, int i3) {
            char c2;
            long j2;
            long j3;
            long j4;
            int i4;
            char cCharAt;
            long j5 = i2;
            long j6 = ((long) i3) + j5;
            int length = charSequence.length();
            if (length > i3 || bArr.length - i3 < i2) {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(length + Utf8.MALFORMED) + " at index " + (i2 + i3));
            }
            int i5 = 0;
            while (true) {
                c2 = 128;
                j2 = 1;
                if (i5 >= length || (cCharAt = charSequence.charAt(i5)) >= 128) {
                    break;
                }
                UnsafeUtil.putByte(bArr, j5, (byte) cCharAt);
                i5++;
                j5 = 1 + j5;
            }
            if (i5 == length) {
                return (int) j5;
            }
            while (i5 < length) {
                char cCharAt2 = charSequence.charAt(i5);
                if (cCharAt2 >= c2 || j5 >= j6) {
                    if (cCharAt2 < 2048 && j5 <= j6 - 2) {
                        long j7 = j5 + j2;
                        UnsafeUtil.putByte(bArr, j5, (byte) ((cCharAt2 >>> 6) | 960));
                        UnsafeUtil.putByte(bArr, j7, (byte) ((cCharAt2 & '?') | 128));
                        j3 = j7 + j2;
                        j4 = j2;
                    } else {
                        if ((cCharAt2 >= 55296 && 57343 >= cCharAt2) || j5 > j6 - 3) {
                            if (j5 > j6 - 4) {
                                if (55296 <= cCharAt2 && cCharAt2 <= 57343 && ((i4 = i5 + 1) == length || !Character.isSurrogatePair(cCharAt2, charSequence.charAt(i4)))) {
                                    throw new UnpairedSurrogateException(i5, length);
                                }
                                throw new ArrayIndexOutOfBoundsException("Failed writing " + cCharAt2 + " at index " + j5);
                            }
                            int i6 = i5 + 1;
                            if (i6 != length) {
                                char cCharAt3 = charSequence.charAt(i6);
                                if (Character.isSurrogatePair(cCharAt2, cCharAt3)) {
                                    int codePoint = Character.toCodePoint(cCharAt2, cCharAt3);
                                    long j8 = j5 + 1;
                                    UnsafeUtil.putByte(bArr, j5, (byte) ((codePoint >>> 18) | 240));
                                    long j9 = j8 + 1;
                                    UnsafeUtil.putByte(bArr, j8, (byte) (((codePoint >>> 12) & 63) | 128));
                                    long j10 = j9 + 1;
                                    UnsafeUtil.putByte(bArr, j9, (byte) (((codePoint >>> 6) & 63) | 128));
                                    j4 = 1;
                                    j3 = j10 + 1;
                                    UnsafeUtil.putByte(bArr, j10, (byte) ((codePoint & 63) | 128));
                                    i5 = i6;
                                } else {
                                    i5 = i6;
                                }
                            }
                            throw new UnpairedSurrogateException(i5 + Utf8.MALFORMED, length);
                        }
                        long j11 = j5 + j2;
                        UnsafeUtil.putByte(bArr, j5, (byte) ((cCharAt2 >>> '\f') | 480));
                        long j12 = j11 + j2;
                        UnsafeUtil.putByte(bArr, j11, (byte) (((cCharAt2 >>> 6) & 63) | 128));
                        UnsafeUtil.putByte(bArr, j12, (byte) ((cCharAt2 & '?') | 128));
                        j3 = j12 + 1;
                        j4 = 1;
                    }
                    i5++;
                    c2 = 128;
                    long j13 = j4;
                    j5 = j3;
                    j2 = j13;
                } else {
                    long j14 = j5 + j2;
                    UnsafeUtil.putByte(bArr, j5, (byte) cCharAt2);
                    j4 = j2;
                    j3 = j14;
                }
                i5++;
                c2 = 128;
                long j15 = j4;
                j5 = j3;
                j2 = j15;
            }
            return (int) j5;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.Utf8.Processor
        public void encodeUtf8Direct(CharSequence charSequence, ByteBuffer byteBuffer) {
            char c2;
            int i2;
            long j2;
            int i3;
            char cCharAt;
            long jAddressOffset = UnsafeUtil.addressOffset(byteBuffer);
            long jPosition = ((long) byteBuffer.position()) + jAddressOffset;
            long jLimit = ((long) byteBuffer.limit()) + jAddressOffset;
            int length = charSequence.length();
            if (length > jLimit - jPosition) {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(length + Utf8.MALFORMED) + " at index " + byteBuffer.limit());
            }
            int i4 = 0;
            while (true) {
                c2 = 128;
                if (i4 >= length || (cCharAt = charSequence.charAt(i4)) >= 128) {
                    break;
                }
                UnsafeUtil.putByte(jPosition, (byte) cCharAt);
                i4++;
                jPosition++;
            }
            if (i4 == length) {
                i2 = (int) (jPosition - jAddressOffset);
            } else {
                while (i4 < length) {
                    char cCharAt2 = charSequence.charAt(i4);
                    if (cCharAt2 < c2 && jPosition < jLimit) {
                        UnsafeUtil.putByte(jPosition, (byte) cCharAt2);
                        jPosition++;
                        j2 = jAddressOffset;
                    } else if (cCharAt2 >= 2048 || jPosition > jLimit - 2) {
                        j2 = jAddressOffset;
                        if ((cCharAt2 >= 55296 && 57343 >= cCharAt2) || jPosition > jLimit - 3) {
                            if (jPosition > jLimit - 4) {
                                if (55296 <= cCharAt2 && cCharAt2 <= 57343 && ((i3 = i4 + 1) == length || !Character.isSurrogatePair(cCharAt2, charSequence.charAt(i3)))) {
                                    throw new UnpairedSurrogateException(i4, length);
                                }
                                throw new ArrayIndexOutOfBoundsException("Failed writing " + cCharAt2 + " at index " + jPosition);
                            }
                            int i5 = i4 + 1;
                            if (i5 != length) {
                                char cCharAt3 = charSequence.charAt(i5);
                                if (Character.isSurrogatePair(cCharAt2, cCharAt3)) {
                                    int codePoint = Character.toCodePoint(cCharAt2, cCharAt3);
                                    long j3 = jPosition + 1;
                                    UnsafeUtil.putByte(jPosition, (byte) ((codePoint >>> 18) | 240));
                                    long j4 = j3 + 1;
                                    UnsafeUtil.putByte(j3, (byte) (((codePoint >>> 12) & 63) | 128));
                                    long j5 = j4 + 1;
                                    UnsafeUtil.putByte(j4, (byte) (((codePoint >>> 6) & 63) | 128));
                                    long j6 = j5 + 1;
                                    UnsafeUtil.putByte(j5, (byte) ((codePoint & 63) | 128));
                                    i4 = i5;
                                    jPosition = j6;
                                } else {
                                    i4 = i5;
                                }
                            }
                            throw new UnpairedSurrogateException(i4 + Utf8.MALFORMED, length);
                        }
                        long j7 = jPosition + 1;
                        UnsafeUtil.putByte(jPosition, (byte) ((cCharAt2 >>> '\f') | 480));
                        long j8 = j7 + 1;
                        UnsafeUtil.putByte(j7, (byte) (((cCharAt2 >>> 6) & 63) | 128));
                        UnsafeUtil.putByte(j8, (byte) ((cCharAt2 & '?') | 128));
                        jPosition = j8 + 1;
                    } else {
                        j2 = jAddressOffset;
                        long j9 = jPosition + 1;
                        UnsafeUtil.putByte(jPosition, (byte) ((cCharAt2 >>> 6) | 960));
                        UnsafeUtil.putByte(j9, (byte) ((cCharAt2 & '?') | 128));
                        jPosition = j9 + 1;
                    }
                    i4++;
                    jAddressOffset = j2;
                    c2 = 128;
                }
                i2 = (int) (jPosition - jAddressOffset);
            }
            byteBuffer.position(i2);
        }

        /* JADX WARN: Code restructure failed: missing block: B:35:0x0059, code lost:
        
            if (com.google.crypto.tink.shaded.protobuf.UnsafeUtil.getByte(r13, r2) > (-65)) goto L38;
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x009e, code lost:
        
            if (com.google.crypto.tink.shaded.protobuf.UnsafeUtil.getByte(r13, r2) > (-65)) goto L59;
         */
        @Override // com.google.crypto.tink.shaded.protobuf.Utf8.Processor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public int partialIsValidUtf8(int i2, byte[] bArr, int i3, int i4) {
            long j2;
            byte b2 = 0;
            if ((i3 | i4 | (bArr.length - i4)) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("Array length=%d, index=%d, limit=%d", Integer.valueOf(bArr.length), Integer.valueOf(i3), Integer.valueOf(i4)));
            }
            long j3 = i3;
            long j4 = i4;
            if (i2 != 0) {
                if (j3 >= j4) {
                    return i2;
                }
                byte b3 = (byte) i2;
                if (b3 < -32) {
                    if (b3 >= -62) {
                        long j5 = 1 + j3;
                        if (UnsafeUtil.getByte(bArr, j3) <= -65) {
                            j3 = j5;
                        }
                    }
                    return Utf8.MALFORMED;
                }
                if (b3 < -16) {
                    byte b4 = (byte) (~(i2 >> 8));
                    if (b4 == 0) {
                        long j6 = j3 + 1;
                        b4 = UnsafeUtil.getByte(bArr, j3);
                        if (j6 >= j4) {
                            return Utf8.incompleteStateFor(b3, b4);
                        }
                        j3 = j6;
                    }
                    if (b4 <= -65 && ((b3 != -32 || b4 >= -96) && (b3 != -19 || b4 < -96))) {
                        j2 = j3 + 1;
                    }
                    return Utf8.MALFORMED;
                }
                byte b5 = (byte) (~(i2 >> 8));
                if (b5 == 0) {
                    long j7 = j3 + 1;
                    b5 = UnsafeUtil.getByte(bArr, j3);
                    if (j7 >= j4) {
                        return Utf8.incompleteStateFor(b3, b5);
                    }
                    j3 = j7;
                } else {
                    b2 = (byte) (i2 >> 16);
                }
                if (b2 == 0) {
                    long j8 = j3 + 1;
                    b2 = UnsafeUtil.getByte(bArr, j3);
                    if (j8 >= j4) {
                        return Utf8.incompleteStateFor(b3, b5, b2);
                    }
                    j3 = j8;
                }
                if (b5 <= -65 && (((b5 + 112) + (b3 << 28)) >> 30) == 0 && b2 <= -65) {
                    j2 = j3 + 1;
                }
                return Utf8.MALFORMED;
                j3 = j2;
            }
            return partialIsValidUtf8(bArr, j3, (int) (j4 - j3));
        }

        /* JADX WARN: Code restructure failed: missing block: B:35:0x0063, code lost:
        
            if (com.google.crypto.tink.shaded.protobuf.UnsafeUtil.getByte(r2) > (-65)) goto L38;
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x00a8, code lost:
        
            if (com.google.crypto.tink.shaded.protobuf.UnsafeUtil.getByte(r2) > (-65)) goto L59;
         */
        @Override // com.google.crypto.tink.shaded.protobuf.Utf8.Processor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public int partialIsValidUtf8Direct(int i2, ByteBuffer byteBuffer, int i3, int i4) {
            long j2;
            byte b2 = 0;
            if ((i3 | i4 | (byteBuffer.limit() - i4)) < 0) {
                throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", Integer.valueOf(byteBuffer.limit()), Integer.valueOf(i3), Integer.valueOf(i4)));
            }
            long jAddressOffset = UnsafeUtil.addressOffset(byteBuffer) + ((long) i3);
            long j3 = ((long) (i4 - i3)) + jAddressOffset;
            if (i2 != 0) {
                if (jAddressOffset >= j3) {
                    return i2;
                }
                byte b3 = (byte) i2;
                if (b3 < -32) {
                    if (b3 >= -62) {
                        long j4 = 1 + jAddressOffset;
                        if (UnsafeUtil.getByte(jAddressOffset) <= -65) {
                            jAddressOffset = j4;
                        }
                    }
                    return Utf8.MALFORMED;
                }
                if (b3 < -16) {
                    byte b4 = (byte) (~(i2 >> 8));
                    if (b4 == 0) {
                        long j5 = jAddressOffset + 1;
                        b4 = UnsafeUtil.getByte(jAddressOffset);
                        if (j5 >= j3) {
                            return Utf8.incompleteStateFor(b3, b4);
                        }
                        jAddressOffset = j5;
                    }
                    if (b4 <= -65 && ((b3 != -32 || b4 >= -96) && (b3 != -19 || b4 < -96))) {
                        j2 = jAddressOffset + 1;
                    }
                    return Utf8.MALFORMED;
                }
                byte b5 = (byte) (~(i2 >> 8));
                if (b5 == 0) {
                    long j6 = jAddressOffset + 1;
                    b5 = UnsafeUtil.getByte(jAddressOffset);
                    if (j6 >= j3) {
                        return Utf8.incompleteStateFor(b3, b5);
                    }
                    jAddressOffset = j6;
                } else {
                    b2 = (byte) (i2 >> 16);
                }
                if (b2 == 0) {
                    long j7 = jAddressOffset + 1;
                    b2 = UnsafeUtil.getByte(jAddressOffset);
                    if (j7 >= j3) {
                        return Utf8.incompleteStateFor(b3, b5, b2);
                    }
                    jAddressOffset = j7;
                }
                if (b5 <= -65 && (((b5 + 112) + (b3 << 28)) >> 30) == 0 && b2 <= -65) {
                    j2 = jAddressOffset + 1;
                }
                return Utf8.MALFORMED;
                jAddressOffset = j2;
            }
            return partialIsValidUtf8(jAddressOffset, (int) (j3 - jAddressOffset));
        }

        private static int partialIsValidUtf8(long j2, int i2) {
            long j3;
            int iUnsafeEstimateConsecutiveAscii = unsafeEstimateConsecutiveAscii(j2, i2);
            long j4 = j2 + ((long) iUnsafeEstimateConsecutiveAscii);
            int i3 = i2 - iUnsafeEstimateConsecutiveAscii;
            while (true) {
                byte b2 = 0;
                while (i3 > 0) {
                    long j5 = j4 + 1;
                    b2 = UnsafeUtil.getByte(j4);
                    if (b2 < 0) {
                        j4 = j5;
                        break;
                    }
                    i3 += Utf8.MALFORMED;
                    j4 = j5;
                }
                if (i3 == 0) {
                    return 0;
                }
                int i4 = i3 + Utf8.MALFORMED;
                if (b2 < -32) {
                    if (i4 == 0) {
                        return b2;
                    }
                    i3 = i4 + Utf8.MALFORMED;
                    if (b2 >= -62) {
                        j3 = 1 + j4;
                        if (UnsafeUtil.getByte(j4) > -65) {
                        }
                    }
                    return Utf8.MALFORMED;
                }
                if (b2 < -16) {
                    if (i4 < 2) {
                        return unsafeIncompleteStateFor(j4, b2, i4);
                    }
                    i3 = i4 - 2;
                    long j6 = j4 + 1;
                    byte b3 = UnsafeUtil.getByte(j4);
                    if (b3 <= -65 && ((b2 != -32 || b3 >= -96) && (b2 != -19 || b3 < -96))) {
                        j3 = 1 + j6;
                        if (UnsafeUtil.getByte(j6) > -65) {
                        }
                    }
                    return Utf8.MALFORMED;
                }
                if (i4 < 3) {
                    return unsafeIncompleteStateFor(j4, b2, i4);
                }
                i3 = i4 - 3;
                long j7 = j4 + 1;
                byte b4 = UnsafeUtil.getByte(j4);
                if (b4 <= -65) {
                    if ((((b4 + 112) + (b2 << 28)) >> 30) == 0) {
                        long j8 = j7 + 1;
                        if (UnsafeUtil.getByte(j7) <= -65) {
                            j3 = 1 + j8;
                            if (UnsafeUtil.getByte(j8) > -65) {
                            }
                        }
                    }
                }
                return Utf8.MALFORMED;
                j4 = j3;
            }
        }

        private static int unsafeEstimateConsecutiveAscii(byte[] bArr, long j2, int i2) {
            int i3 = 0;
            if (i2 < 16) {
                return 0;
            }
            int i4 = 8 - (((int) j2) & 7);
            while (i3 < i4) {
                long j3 = 1 + j2;
                if (UnsafeUtil.getByte(bArr, j2) < 0) {
                    return i3;
                }
                i3++;
                j2 = j3;
            }
            while (true) {
                int i5 = i3 + 8;
                if (i5 > i2 || (UnsafeUtil.getLong((Object) bArr, UnsafeUtil.BYTE_ARRAY_BASE_OFFSET + j2) & Utf8.ASCII_MASK_LONG) != 0) {
                    break;
                }
                j2 += 8;
                i3 = i5;
            }
            while (i3 < i2) {
                long j4 = j2 + 1;
                if (UnsafeUtil.getByte(bArr, j2) < 0) {
                    return i3;
                }
                i3++;
                j2 = j4;
            }
            return i2;
        }

        private static int unsafeIncompleteStateFor(byte[] bArr, int i2, long j2, int i3) {
            if (i3 == 0) {
                return Utf8.incompleteStateFor(i2);
            }
            if (i3 == 1) {
                return Utf8.incompleteStateFor(i2, UnsafeUtil.getByte(bArr, j2));
            }
            if (i3 == 2) {
                return Utf8.incompleteStateFor(i2, UnsafeUtil.getByte(bArr, j2), UnsafeUtil.getByte(bArr, j2 + 1));
            }
            throw new AssertionError();
        }

        private static int partialIsValidUtf8(byte[] bArr, long j2, int i2) {
            long j3;
            int iUnsafeEstimateConsecutiveAscii = unsafeEstimateConsecutiveAscii(bArr, j2, i2);
            int i3 = i2 - iUnsafeEstimateConsecutiveAscii;
            long j4 = j2 + ((long) iUnsafeEstimateConsecutiveAscii);
            while (true) {
                byte b2 = 0;
                while (i3 > 0) {
                    long j5 = j4 + 1;
                    b2 = UnsafeUtil.getByte(bArr, j4);
                    if (b2 < 0) {
                        j4 = j5;
                        break;
                    }
                    i3 += Utf8.MALFORMED;
                    j4 = j5;
                }
                if (i3 == 0) {
                    return 0;
                }
                int i4 = i3 + Utf8.MALFORMED;
                if (b2 < -32) {
                    if (i4 == 0) {
                        return b2;
                    }
                    i3 = i4 + Utf8.MALFORMED;
                    if (b2 >= -62) {
                        j3 = 1 + j4;
                        if (UnsafeUtil.getByte(bArr, j4) > -65) {
                        }
                    }
                    return Utf8.MALFORMED;
                }
                if (b2 < -16) {
                    if (i4 < 2) {
                        return unsafeIncompleteStateFor(bArr, b2, j4, i4);
                    }
                    i3 = i4 - 2;
                    long j6 = j4 + 1;
                    byte b3 = UnsafeUtil.getByte(bArr, j4);
                    if (b3 <= -65 && ((b2 != -32 || b3 >= -96) && (b2 != -19 || b3 < -96))) {
                        j3 = 1 + j6;
                        if (UnsafeUtil.getByte(bArr, j6) > -65) {
                        }
                    }
                    return Utf8.MALFORMED;
                }
                if (i4 < 3) {
                    return unsafeIncompleteStateFor(bArr, b2, j4, i4);
                }
                i3 = i4 - 3;
                long j7 = j4 + 1;
                byte b4 = UnsafeUtil.getByte(bArr, j4);
                if (b4 <= -65) {
                    if ((((b4 + 112) + (b2 << 28)) >> 30) == 0) {
                        long j8 = j7 + 1;
                        if (UnsafeUtil.getByte(bArr, j7) <= -65) {
                            j3 = 1 + j8;
                            if (UnsafeUtil.getByte(bArr, j8) > -65) {
                            }
                        }
                    }
                }
                return Utf8.MALFORMED;
                j4 = j3;
            }
        }
    }

    static {
        processor = (!UnsafeProcessor.isAvailable() || Android.isOnAndroidDevice()) ? new SafeProcessor() : new UnsafeProcessor();
    }

    private Utf8() {
    }

    public static String decodeUtf8(ByteBuffer byteBuffer, int i2, int i3) {
        return processor.decodeUtf8(byteBuffer, i2, i3);
    }

    public static int encode(CharSequence charSequence, byte[] bArr, int i2, int i3) {
        return processor.encodeUtf8(charSequence, bArr, i2, i3);
    }

    public static void encodeUtf8(CharSequence charSequence, ByteBuffer byteBuffer) {
        processor.encodeUtf8(charSequence, byteBuffer);
    }

    public static int encodedLength(CharSequence charSequence) {
        int length = charSequence.length();
        int i2 = 0;
        while (i2 < length && charSequence.charAt(i2) < 128) {
            i2++;
        }
        int iEncodedLengthGeneral = length;
        while (i2 < length) {
            char cCharAt = charSequence.charAt(i2);
            if (cCharAt >= 2048) {
                iEncodedLengthGeneral += encodedLengthGeneral(charSequence, i2);
                break;
            }
            iEncodedLengthGeneral += (127 - cCharAt) >>> 31;
            i2++;
        }
        if (iEncodedLengthGeneral >= length) {
            return iEncodedLengthGeneral;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (((long) iEncodedLengthGeneral) + 4294967296L));
    }

    private static int encodedLengthGeneral(CharSequence charSequence, int i2) {
        int length = charSequence.length();
        int i3 = 0;
        while (i2 < length) {
            char cCharAt = charSequence.charAt(i2);
            if (cCharAt < 2048) {
                i3 += (127 - cCharAt) >>> 31;
            } else {
                i3 += 2;
                if (55296 <= cCharAt && cCharAt <= 57343) {
                    if (Character.codePointAt(charSequence, i2) < 65536) {
                        throw new UnpairedSurrogateException(i2, length);
                    }
                    i2++;
                }
            }
            i2++;
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int estimateConsecutiveAscii(ByteBuffer byteBuffer, int i2, int i3) {
        int i4 = i3 - 7;
        int i5 = i2;
        while (i5 < i4 && (byteBuffer.getLong(i5) & ASCII_MASK_LONG) == 0) {
            i5 += 8;
        }
        return i5 - i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(int i2) {
        return i2 > -12 ? MALFORMED : i2;
    }

    public static boolean isValidUtf8(ByteBuffer byteBuffer) {
        return processor.isValidUtf8(byteBuffer, byteBuffer.position(), byteBuffer.remaining());
    }

    public static int partialIsValidUtf8(int i2, ByteBuffer byteBuffer, int i3, int i4) {
        return processor.partialIsValidUtf8(i2, byteBuffer, i3, i4);
    }

    public static String decodeUtf8(byte[] bArr, int i2, int i3) {
        return processor.decodeUtf8(bArr, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(int i2, int i3) {
        return (i2 > -12 || i3 > -65) ? MALFORMED : i2 ^ (i3 << 8);
    }

    public static boolean isValidUtf8(byte[] bArr) {
        return processor.isValidUtf8(bArr, 0, bArr.length);
    }

    public static int partialIsValidUtf8(int i2, byte[] bArr, int i3, int i4) {
        return processor.partialIsValidUtf8(i2, bArr, i3, i4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(int i2, int i3, int i4) {
        return (i2 > -12 || i3 > -65 || i4 > -65) ? MALFORMED : (i2 ^ (i3 << 8)) ^ (i4 << 16);
    }

    public static boolean isValidUtf8(byte[] bArr, int i2, int i3) {
        return processor.isValidUtf8(bArr, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(ByteBuffer byteBuffer, int i2, int i3, int i4) {
        if (i4 == 0) {
            return incompleteStateFor(i2);
        }
        if (i4 == 1) {
            return incompleteStateFor(i2, byteBuffer.get(i3));
        }
        if (i4 == 2) {
            return incompleteStateFor(i2, byteBuffer.get(i3), byteBuffer.get(i3 + 1));
        }
        throw new AssertionError();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int incompleteStateFor(byte[] bArr, int i2, int i3) {
        byte b2 = bArr[i2 + MALFORMED];
        int i4 = i3 - i2;
        if (i4 == 0) {
            return incompleteStateFor(b2);
        }
        if (i4 == 1) {
            return incompleteStateFor(b2, bArr[i2]);
        }
        if (i4 == 2) {
            return incompleteStateFor(b2, bArr[i2], bArr[i2 + 1]);
        }
        throw new AssertionError();
    }
}
