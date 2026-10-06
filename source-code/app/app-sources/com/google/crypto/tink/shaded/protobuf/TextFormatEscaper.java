package com.google.crypto.tink.shaded.protobuf;

/* JADX INFO: loaded from: classes.dex */
final class TextFormatEscaper {

    public interface ByteSequence {
        byte byteAt(int i2);

        int size();
    }

    private TextFormatEscaper() {
    }

    public static String escapeBytes(final ByteString byteString) {
        return escapeBytes(new ByteSequence() { // from class: com.google.crypto.tink.shaded.protobuf.TextFormatEscaper.1
            @Override // com.google.crypto.tink.shaded.protobuf.TextFormatEscaper.ByteSequence
            public byte byteAt(int i2) {
                return byteString.byteAt(i2);
            }

            @Override // com.google.crypto.tink.shaded.protobuf.TextFormatEscaper.ByteSequence
            public int size() {
                return byteString.size();
            }
        });
    }

    public static String escapeDoubleQuotesAndBackslashes(String str) {
        return str.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    public static String escapeText(String str) {
        return escapeBytes(ByteString.copyFromUtf8(str));
    }

    public static String escapeBytes(ByteSequence byteSequence) {
        String str;
        StringBuilder sb = new StringBuilder(byteSequence.size());
        for (int i2 = 0; i2 < byteSequence.size(); i2++) {
            int iByteAt = byteSequence.byteAt(i2);
            if (iByteAt == 34) {
                str = "\\\"";
            } else if (iByteAt == 39) {
                str = "\\'";
            } else if (iByteAt != 92) {
                switch (iByteAt) {
                    case 7:
                        str = "\\a";
                        break;
                    case 8:
                        str = "\\b";
                        break;
                    case 9:
                        str = "\\t";
                        break;
                    case 10:
                        str = "\\n";
                        break;
                    case 11:
                        str = "\\v";
                        break;
                    case 12:
                        str = "\\f";
                        break;
                    case TYPE_UINT32_VALUE:
                        str = "\\r";
                        break;
                    default:
                        if (iByteAt < 32 || iByteAt > 126) {
                            sb.append('\\');
                            sb.append((char) (((iByteAt >>> 6) & 3) + 48));
                            sb.append((char) (((iByteAt >>> 3) & 7) + 48));
                            iByteAt = (iByteAt & 7) + 48;
                        }
                        sb.append((char) iByteAt);
                        continue;
                        break;
                }
            } else {
                str = "\\\\";
            }
            sb.append(str);
        }
        return sb.toString();
    }

    public static String escapeBytes(final byte[] bArr) {
        return escapeBytes(new ByteSequence() { // from class: com.google.crypto.tink.shaded.protobuf.TextFormatEscaper.2
            @Override // com.google.crypto.tink.shaded.protobuf.TextFormatEscaper.ByteSequence
            public byte byteAt(int i2) {
                return bArr[i2];
            }

            @Override // com.google.crypto.tink.shaded.protobuf.TextFormatEscaper.ByteSequence
            public int size() {
                return bArr.length;
            }
        });
    }
}
