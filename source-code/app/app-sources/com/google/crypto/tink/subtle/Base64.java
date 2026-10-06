package com.google.crypto.tink.subtle;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public final class Base64 {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int CRLF = 4;
    public static final int DEFAULT = 0;
    public static final int NO_CLOSE = 16;
    public static final int NO_PADDING = 1;
    public static final int NO_WRAP = 2;
    public static final int URL_SAFE = 8;
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    public static abstract class Coder {
        public int op;
        public byte[] output;

        public abstract int maxOutputSize(int i2);

        public abstract boolean process(byte[] bArr, int i2, int i3, boolean z2);
    }

    public static class Decoder extends Coder {
        private final int[] alphabet;
        private int state;
        private int value;
        private static final int SKIP = -1;
        private static final int EQUALS = -2;
        private static final int[] DECODE = {SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, 62, SKIP, SKIP, SKIP, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, SKIP, SKIP, SKIP, EQUALS, SKIP, SKIP, SKIP, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP};
        private static final int[] DECODE_WEBSAFE = {SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, 62, SKIP, SKIP, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, SKIP, SKIP, SKIP, EQUALS, SKIP, SKIP, SKIP, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, SKIP, SKIP, SKIP, SKIP, 63, SKIP, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP, SKIP};

        public Decoder(int i2, byte[] bArr) {
            this.output = bArr;
            this.alphabet = (i2 & 8) == 0 ? DECODE : DECODE_WEBSAFE;
            this.state = 0;
            this.value = 0;
        }

        @Override // com.google.crypto.tink.subtle.Base64.Coder
        public int maxOutputSize(int i2) {
            return ((i2 * 3) / 4) + 10;
        }

        /* JADX WARN: Code duplicated, block: B:49:0x00d4  */
        @Override // com.google.crypto.tink.subtle.Base64.Coder
        public boolean process(byte[] bArr, int i2, int i3, boolean z2) {
            int i4 = this.state;
            if (i4 == 6) {
                return Base64.$assertionsDisabled;
            }
            int i5 = i3 + i2;
            int i6 = this.value;
            byte[] bArr2 = this.output;
            int[] iArr = this.alphabet;
            int i7 = i6;
            int i8 = 0;
            int i9 = i4;
            int i10 = i2;
            while (i10 < i5) {
                if (i9 == 0) {
                    while (true) {
                        int i11 = i10 + 4;
                        if (i11 > i5 || (i7 = (iArr[bArr[i10] & 255] << 18) | (iArr[bArr[i10 + 1] & 255] << 12) | (iArr[bArr[i10 + 2] & 255] << 6) | iArr[bArr[i10 + 3] & 255]) < 0) {
                            break;
                        }
                        bArr2[i8 + 2] = (byte) i7;
                        bArr2[i8 + 1] = (byte) (i7 >> 8);
                        bArr2[i8] = (byte) (i7 >> 16);
                        i8 += 3;
                        i10 = i11;
                    }
                    if (i10 >= i5) {
                        break;
                    }
                }
                int i12 = i10 + 1;
                int i13 = iArr[bArr[i10] & 255];
                if (i9 != 0) {
                    if (i9 != 1) {
                        if (i9 != 2) {
                            if (i9 != 3) {
                                if (i9 != 4) {
                                    if (i9 == 5 && i13 != SKIP) {
                                        this.state = 6;
                                        return Base64.$assertionsDisabled;
                                    }
                                } else if (i13 == EQUALS) {
                                    i9++;
                                } else if (i13 != SKIP) {
                                    this.state = 6;
                                    return Base64.$assertionsDisabled;
                                }
                            } else if (i13 >= 0) {
                                int i14 = i13 | (i7 << 6);
                                bArr2[i8 + 2] = (byte) i14;
                                bArr2[i8 + 1] = (byte) (i14 >> 8);
                                bArr2[i8] = (byte) (i14 >> 16);
                                i8 += 3;
                                i7 = i14;
                                i9 = 0;
                            } else if (i13 == EQUALS) {
                                bArr2[i8 + 1] = (byte) (i7 >> 2);
                                bArr2[i8] = (byte) (i7 >> 10);
                                i8 += 2;
                                i9 = 5;
                            } else if (i13 != SKIP) {
                                this.state = 6;
                                return Base64.$assertionsDisabled;
                            }
                        } else if (i13 >= 0) {
                            i13 |= i7 << 6;
                            i9++;
                            i7 = i13;
                        } else if (i13 == EQUALS) {
                            bArr2[i8] = (byte) (i7 >> 4);
                            i8++;
                            i9 = 4;
                        } else if (i13 != SKIP) {
                            this.state = 6;
                            return Base64.$assertionsDisabled;
                        }
                    } else if (i13 >= 0) {
                        i13 |= i7 << 6;
                        i9++;
                        i7 = i13;
                    } else if (i13 != SKIP) {
                        this.state = 6;
                        return Base64.$assertionsDisabled;
                    }
                } else if (i13 >= 0) {
                    i9++;
                    i7 = i13;
                } else if (i13 != SKIP) {
                    this.state = 6;
                    return Base64.$assertionsDisabled;
                }
                i10 = i12;
            }
            if (!z2) {
                this.state = i9;
                this.value = i7;
            } else {
                if (i9 == 1) {
                    this.state = 6;
                    return Base64.$assertionsDisabled;
                }
                if (i9 == 2) {
                    bArr2[i8] = (byte) (i7 >> 4);
                    i8++;
                } else if (i9 == 3) {
                    int i15 = i8 + 1;
                    bArr2[i8] = (byte) (i7 >> 10);
                    i8 = i15 + 1;
                    bArr2[i15] = (byte) (i7 >> 2);
                } else if (i9 == 4) {
                    this.state = 6;
                    return Base64.$assertionsDisabled;
                }
                this.state = i9;
            }
            this.op = i8;
            return true;
        }
    }

    public static class Encoder extends Coder {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final byte[] ENCODE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
        private static final byte[] ENCODE_WEBSAFE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
        public static final int LINE_GROUPS = 19;
        private final byte[] alphabet;
        private int count;
        public final boolean doCr;
        public final boolean doNewline;
        public final boolean doPadding;
        private final byte[] tail;
        int tailLen;

        public Encoder(int i2, byte[] bArr) {
            this.output = bArr;
            this.doPadding = (i2 & 1) == 0 ? true : $assertionsDisabled;
            boolean z2 = (i2 & 2) == 0 ? true : $assertionsDisabled;
            this.doNewline = z2;
            this.doCr = (i2 & 4) == 0 ? $assertionsDisabled : true;
            this.alphabet = (i2 & 8) == 0 ? ENCODE : ENCODE_WEBSAFE;
            this.tail = new byte[2];
            this.tailLen = 0;
            this.count = z2 ? 19 : -1;
        }

        @Override // com.google.crypto.tink.subtle.Base64.Coder
        public int maxOutputSize(int i2) {
            return ((i2 * 8) / 5) + 10;
        }

        /* JADX WARN: Code duplicated, block: B:12:0x0050  */
        /* JADX WARN: Code duplicated, block: B:27:0x0094  */
        /* JADX WARN: Code duplicated, block: B:31:0x00d8  */
        /* JADX WARN: Code duplicated, block: B:32:0x00dd A[EDGE_INSN: B:32:0x00dd->B:22:0x008a BREAK  A[LOOP:0: B:25:0x0090->B:33:0x00e4]] */
        /* JADX WARN: Code duplicated, block: B:33:0x00e4 A[LOOP:0: B:25:0x0090->B:33:0x00e4, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:88:0x00d4 A[SYNTHETIC] */
        /*  JADX ERROR: StackOverflowError in pass: RegionMakerVisitor
            java.lang.StackOverflowError
            	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:731)
            	at jadx.core.utils.BlockUtils.traverseSuccessorsUntil(BlockUtils.java:749)
            */
        @Override // com.google.crypto.tink.subtle.Base64.Coder
        @com.google.errorprone.annotations.CanIgnoreReturnValue
        public boolean process(byte[] r18, int r19, int r20, boolean r21) {
            /*
                Method dump skipped, instruction units count: 478
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.subtle.Base64.Encoder.process(byte[], int, int, boolean):boolean");
        }
    }

    private Base64() {
    }

    public static byte[] decode(String str) {
        return decode(str, 2);
    }

    public static String encode(byte[] bArr) {
        return encodeToString(bArr, 2);
    }

    public static String encodeToString(byte[] bArr, int i2) {
        try {
            return new String(encode(bArr, i2), "US-ASCII");
        } catch (UnsupportedEncodingException e2) {
            throw new AssertionError(e2);
        }
    }

    public static byte[] urlSafeDecode(String str) {
        return decode(str, 11);
    }

    public static String urlSafeEncode(byte[] bArr) {
        return encodeToString(bArr, 11);
    }

    public static byte[] decode(String str, int i2) {
        return decode(str.getBytes(UTF_8), i2);
    }

    public static byte[] encode(byte[] bArr, int i2) {
        return encode(bArr, 0, bArr.length, i2);
    }

    public static String encodeToString(byte[] bArr, int i2, int i3, int i4) {
        try {
            return new String(encode(bArr, i2, i3, i4), "US-ASCII");
        } catch (UnsupportedEncodingException e2) {
            throw new AssertionError(e2);
        }
    }

    public static byte[] decode(byte[] bArr, int i2) {
        return decode(bArr, 0, bArr.length, i2);
    }

    public static byte[] encode(byte[] bArr, int i2, int i3, int i4) {
        Encoder encoder = new Encoder(i4, null);
        int i5 = (i3 / 3) * 4;
        if (!encoder.doPadding) {
            int i6 = i3 % 3;
            if (i6 == 1) {
                i5 += 2;
            } else if (i6 == 2) {
                i5 += 3;
            }
        } else if (i3 % 3 > 0) {
            i5 += 4;
        }
        if (encoder.doNewline && i3 > 0) {
            i5 += (((i3 - 1) / 57) + 1) * (encoder.doCr ? 2 : 1);
        }
        encoder.output = new byte[i5];
        encoder.process(bArr, i2, i3, true);
        return encoder.output;
    }

    public static byte[] decode(byte[] bArr, int i2, int i3, int i4) {
        Decoder decoder = new Decoder(i4, new byte[(i3 * 3) / 4]);
        if (!decoder.process(bArr, i2, i3, true)) {
            throw new IllegalArgumentException("bad base-64");
        }
        int i5 = decoder.op;
        byte[] bArr2 = decoder.output;
        if (i5 == bArr2.length) {
            return bArr2;
        }
        byte[] bArr3 = new byte[i5];
        System.arraycopy(bArr2, 0, bArr3, 0, i5);
        return bArr3;
    }
}
