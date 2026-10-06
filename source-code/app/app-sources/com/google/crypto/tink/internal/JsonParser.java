package com.google.crypto.tink.internal;

import i0.d;
import i0.e;
import i0.g;
import i0.i;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayDeque;
import javax.annotation.Nullable;
import l0.a;
import l0.b;
import l0.c;

/* JADX INFO: loaded from: classes.dex */
public final class JsonParser {
    private static final JsonElementTypeAdapter JSON_ELEMENT = new JsonElementTypeAdapter(null);

    /* JADX INFO: renamed from: com.google.crypto.tink.internal.JsonParser$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$gson$stream$JsonToken;

        static {
            int[] iArr = new int[b.values().length];
            $SwitchMap$com$google$gson$stream$JsonToken = iArr;
            try {
                iArr[0] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[2] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[5] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[6] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[7] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[8] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public static final class JsonElementTypeAdapter extends i {
        private static final int RECURSION_LIMIT = 100;

        private JsonElementTypeAdapter() {
        }

        public /* synthetic */ JsonElementTypeAdapter(AnonymousClass1 anonymousClass1) {
            this();
        }

        private i0.b readTerminal(a aVar, b bVar) throws IOException {
            int iOrdinal = bVar.ordinal();
            if (iOrdinal == 5) {
                String strR = aVar.r();
                if (JsonParser.isValidString(strR)) {
                    return new g(strR);
                }
                throw new IOException("illegal characters in string");
            }
            if (iOrdinal == 6) {
                return new g(new LazilyParsedNumber(aVar.r()));
            }
            if (iOrdinal == 7) {
                return new g(Boolean.valueOf(aVar.l()));
            }
            if (iOrdinal == 8) {
                aVar.p();
                return d.f1138a;
            }
            throw new IllegalStateException("Unexpected token: " + bVar);
        }

        @Nullable
        private i0.b tryBeginNesting(a aVar, b bVar) throws IOException {
            int iOrdinal = bVar.ordinal();
            if (iOrdinal == 0) {
                aVar.a();
                return new i0.a();
            }
            if (iOrdinal != 2) {
                return null;
            }
            aVar.b();
            return new e();
        }

        @Override // i0.i
        public i0.b read(a aVar) throws IOException {
            String strN;
            b bVarT = aVar.t();
            i0.b bVarTryBeginNesting = tryBeginNesting(aVar, bVarT);
            if (bVarTryBeginNesting == null) {
                return readTerminal(aVar, bVarT);
            }
            ArrayDeque arrayDeque = new ArrayDeque();
            while (true) {
                if (aVar.i()) {
                    if (bVarTryBeginNesting instanceof e) {
                        strN = aVar.n();
                        if (!JsonParser.isValidString(strN)) {
                            throw new IOException("illegal characters in string");
                        }
                    } else {
                        strN = null;
                    }
                    b bVarT2 = aVar.t();
                    i0.b bVarTryBeginNesting2 = tryBeginNesting(aVar, bVarT2);
                    boolean z2 = bVarTryBeginNesting2 != null;
                    if (bVarTryBeginNesting2 == null) {
                        bVarTryBeginNesting2 = readTerminal(aVar, bVarT2);
                    }
                    if (bVarTryBeginNesting instanceof i0.a) {
                        ((i0.a) bVarTryBeginNesting).j(bVarTryBeginNesting2);
                    } else {
                        e eVar = (e) bVarTryBeginNesting;
                        if (eVar.o(strN)) {
                            throw new IOException("duplicate key: " + strN);
                        }
                        eVar.j(strN, bVarTryBeginNesting2);
                    }
                    if (z2) {
                        arrayDeque.addLast(bVarTryBeginNesting);
                        if (arrayDeque.size() > RECURSION_LIMIT) {
                            throw new IOException("too many recursions");
                        }
                        bVarTryBeginNesting = bVarTryBeginNesting2;
                    } else {
                        continue;
                    }
                } else {
                    if (bVarTryBeginNesting instanceof i0.a) {
                        aVar.e();
                    } else {
                        aVar.f();
                    }
                    if (arrayDeque.isEmpty()) {
                        return bVarTryBeginNesting;
                    }
                    bVarTryBeginNesting = (i0.b) arrayDeque.removeLast();
                }
            }
        }

        @Override // i0.i
        public void write(c cVar, i0.b bVar) {
            throw new UnsupportedOperationException("write is not supported");
        }
    }

    public static final class LazilyParsedNumber extends Number {
        private final String value;

        public LazilyParsedNumber(String str) {
            this.value = str;
        }

        private void readObject(ObjectInputStream objectInputStream) throws NotSerializableException {
            throw new NotSerializableException("serialization is not supported");
        }

        private Object writeReplace() throws NotSerializableException {
            throw new NotSerializableException("serialization is not supported");
        }

        @Override // java.lang.Number
        public double doubleValue() {
            return Double.parseDouble(this.value);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof LazilyParsedNumber) {
                return this.value.equals(((LazilyParsedNumber) obj).value);
            }
            return false;
        }

        @Override // java.lang.Number
        public float floatValue() {
            return Float.parseFloat(this.value);
        }

        public int hashCode() {
            return this.value.hashCode();
        }

        @Override // java.lang.Number
        public int intValue() {
            try {
                try {
                    return Integer.parseInt(this.value);
                } catch (NumberFormatException unused) {
                    return (int) Long.parseLong(this.value);
                }
            } catch (NumberFormatException unused2) {
                return new BigDecimal(this.value).intValue();
            }
        }

        @Override // java.lang.Number
        public long longValue() {
            try {
                return Long.parseLong(this.value);
            } catch (NumberFormatException unused) {
                return new BigDecimal(this.value).longValue();
            }
        }

        public String toString() {
            return this.value;
        }
    }

    private JsonParser() {
    }

    public static long getParsedNumberAsLongOrThrow(i0.b bVar) {
        if (bVar.h() instanceof LazilyParsedNumber) {
            return Long.parseLong(bVar.h().toString());
        }
        throw new IllegalArgumentException("does not contain a parsed number.");
    }

    public static boolean isValidString(String str) {
        int length = str.length();
        int i2 = 0;
        while (i2 != length) {
            char cCharAt = str.charAt(i2);
            i2++;
            if (Character.isSurrogate(cCharAt)) {
                if (Character.isLowSurrogate(cCharAt) || i2 == length || !Character.isLowSurrogate(str.charAt(i2))) {
                    return false;
                }
                i2++;
            }
        }
        return true;
    }

    public static i0.b parse(String str) throws IOException {
        try {
            a aVar = new a(new StringReader(str));
            aVar.f1561b = false;
            return JSON_ELEMENT.read(aVar);
        } catch (NumberFormatException e2) {
            throw new IOException(e2);
        }
    }
}
