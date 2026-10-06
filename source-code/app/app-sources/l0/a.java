package l0;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class a implements Closeable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Reader f1560a;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f1568i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f1569j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public String f1570k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int[] f1571l;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String[] f1573n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int[] f1574o;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f1561b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final char[] f1562c = new char[1024];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1563d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1564e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1565f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1566g = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1567h = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f1572m = 0 + 1;

    public a(Reader reader) {
        int[] iArr = new int[32];
        this.f1571l = iArr;
        iArr[0] = 6;
        this.f1573n = new String[32];
        this.f1574o = new int[32];
        if (reader == null) {
            throw new NullPointerException("in == null");
        }
        this.f1560a = reader;
    }

    public void a() throws IOException {
        int iD = this.f1567h;
        if (iD == 0) {
            iD = d();
        }
        if (iD == 3) {
            u(1);
            this.f1574o[this.f1572m - 1] = 0;
            this.f1567h = 0;
        } else {
            throw new IllegalStateException("Expected BEGIN_ARRAY but was " + t() + k());
        }
    }

    public void b() throws IOException {
        int iD = this.f1567h;
        if (iD == 0) {
            iD = d();
        }
        if (iD == 1) {
            u(3);
            this.f1567h = 0;
        } else {
            throw new IllegalStateException("Expected BEGIN_OBJECT but was " + t() + k());
        }
    }

    public final void c() throws d {
        if (this.f1561b) {
            return;
        }
        w("Use JsonReader.setLenient(true) to accept malformed JSON");
        throw null;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f1567h = 0;
        this.f1571l[0] = 8;
        this.f1572m = 1;
        this.f1560a.close();
    }

    /* JADX WARN: Code duplicated, block: B:115:0x0180 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:116:0x0181  */
    /* JADX WARN: Code duplicated, block: B:119:0x0190  */
    /* JADX WARN: Code duplicated, block: B:122:0x0195  */
    /* JADX WARN: Code duplicated, block: B:125:0x019f  */
    /* JADX WARN: Code duplicated, block: B:126:0x01a4 A[PHI: r1 r3
  0x01a4: PHI (r1v53 int) = (r1v52 int), (r1v73 int) binds: [B:118:0x018e, B:125:0x019f] A[DONT_GENERATE, DONT_INLINE]
  0x01a4: PHI (r3v11 int) = (r3v10 int), (r3v13 int) binds: [B:118:0x018e, B:125:0x019f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:128:0x01ac  */
    /* JADX WARN: Code duplicated, block: B:130:0x01b0  */
    /* JADX WARN: Code duplicated, block: B:170:0x0215  */
    /* JADX WARN: Code duplicated, block: B:171:0x0217  */
    /* JADX WARN: Code duplicated, block: B:182:0x0238 A[DONT_INVERT, PHI: r1
  0x0238: PHI (r1v61 char) = (r1v60 char), (r1v64 char) binds: [B:169:0x0213, B:181:0x0237] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:183:0x023a  */
    /* JADX WARN: Code duplicated, block: B:197:0x025f  */
    /* JADX WARN: Code duplicated, block: B:199:0x0266  */
    /* JADX WARN: Code duplicated, block: B:202:0x026b  */
    /* JADX WARN: Code duplicated, block: B:208:0x027c A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:209:0x027d  */
    /* JADX WARN: Code duplicated, block: B:211:0x0287  */
    /* JADX WARN: Code duplicated, block: B:212:0x028e  */
    /* JADX WARN: Code duplicated, block: B:221:0x02a2  */
    /* JADX WARN: Code duplicated, block: B:223:0x02a5  */
    /* JADX WARN: Code duplicated, block: B:229:0x02ba  */
    /* JADX WARN: Code duplicated, block: B:230:0x02c0  */
    /* JADX WARN: Code duplicated, block: B:245:0x02ef A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:246:0x02f1  */
    /* JADX WARN: Code duplicated, block: B:248:0x02f5  */
    /* JADX WARN: Code duplicated, block: B:250:0x0304  */
    /* JADX WARN: Code duplicated, block: B:251:0x0307  */
    /* JADX WARN: Code duplicated, block: B:253:0x030c  */
    /* JADX WARN: Code duplicated, block: B:255:0x030f  */
    /* JADX WARN: Code duplicated, block: B:257:0x0313  */
    /* JADX WARN: Code duplicated, block: B:259:0x0317  */
    /* JADX WARN: Code duplicated, block: B:260:0x031d  */
    /* JADX WARN: Code duplicated, block: B:269:0x0279 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:270:0x0279 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:271:0x0212 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:54:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:68:0x00ed A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:69:0x00ef  */
    /* JADX WARN: Code duplicated, block: B:70:0x00f1 A[ADDED_TO_REGION] */
    public final int d() throws IOException {
        int iO;
        int i2;
        int iO2;
        int iO3;
        String str;
        String str2;
        int i3;
        char c2;
        int i4;
        int i5;
        long j2;
        int i6;
        char c3;
        boolean z2;
        char c4;
        long j3;
        int i7;
        char c5;
        int[] iArr = this.f1571l;
        int i8 = this.f1572m;
        int i9 = iArr[i8 - 1];
        char[] cArr = this.f1562c;
        if (i9 == 1) {
            iArr[i8 - 1] = 2;
        } else {
            if (i9 != 2) {
                if (i9 == 3 || i9 == 5) {
                    iArr[i8 - 1] = 4;
                    if (i9 != 5 || (iO2 = o(true)) == 44) {
                        iO = o(true);
                        if (iO != 34) {
                            i2 = 13;
                        } else if (iO != 39) {
                            c();
                            i2 = 12;
                        } else {
                            if (iO != 125) {
                                if (i9 != 5) {
                                    this.f1567h = 2;
                                    return 2;
                                }
                                w("Expected name");
                                throw null;
                            }
                            c();
                            this.f1563d--;
                            if (j((char) iO)) {
                                w("Expected name");
                                throw null;
                            }
                            i2 = 14;
                        }
                    } else if (iO2 == 59) {
                        c();
                        iO = o(true);
                        if (iO != 34) {
                            i2 = 13;
                        } else if (iO != 39) {
                            c();
                            i2 = 12;
                        } else {
                            if (iO != 125) {
                                if (i9 != 5) {
                                    this.f1567h = 2;
                                    return 2;
                                }
                                w("Expected name");
                                throw null;
                            }
                            c();
                            this.f1563d--;
                            if (j((char) iO)) {
                                w("Expected name");
                                throw null;
                            }
                            i2 = 14;
                        }
                    } else {
                        if (iO2 != 125) {
                            w("Unterminated object");
                            throw null;
                        }
                        i2 = 2;
                    }
                } else if (i9 == 4) {
                    iArr[i8 - 1] = 5;
                    int iO4 = o(true);
                    if (iO4 != 58) {
                        if (iO4 != 61) {
                            w("Expected ':'");
                            throw null;
                        }
                        c();
                        if (this.f1563d < this.f1564e || g(1)) {
                            int i10 = this.f1563d;
                            if (cArr[i10] == '>') {
                                this.f1563d = i10 + 1;
                            }
                        }
                    }
                } else if (i9 == 6) {
                    if (this.f1561b) {
                        o(true);
                        int i11 = this.f1563d - 1;
                        this.f1563d = i11;
                        if ((i11 + 5 <= this.f1564e || g(5)) && cArr[i11] == ')' && cArr[i11 + 1] == ']' && cArr[i11 + 2] == '}' && cArr[i11 + 3] == '\'') {
                            if (cArr[i11 + 4] == '\n') {
                                this.f1563d += 5;
                            }
                        }
                    }
                    this.f1571l[this.f1572m - 1] = 7;
                    iO3 = o(true);
                    if (iO3 != 34) {
                        i2 = 9;
                    } else if (iO3 == 39) {
                        if (iO3 == 44 && iO3 != 59) {
                            if (iO3 == 91) {
                                this.f1567h = 3;
                                return 3;
                            }
                            if (iO3 != 93) {
                                if (iO3 == 123) {
                                    this.f1567h = 1;
                                    return 1;
                                }
                                int i12 = this.f1563d - 1;
                                this.f1563d = i12;
                                char c6 = cArr[i12];
                                if (c6 == 't' || c6 == 'T') {
                                    str = "true";
                                    str2 = "TRUE";
                                    i3 = 5;
                                } else if (c6 == 'f' || c6 == 'F') {
                                    str = "false";
                                    str2 = "FALSE";
                                    i3 = 6;
                                } else {
                                    if (c6 != 'n' && c6 != 'N') {
                                        i3 = 0;
                                        break;
                                    }
                                    str = "null";
                                    str2 = "NULL";
                                    i3 = 7;
                                    if (i3 != 0) {
                                        return i3;
                                    }
                                    i4 = this.f1563d;
                                    i5 = this.f1564e;
                                    j2 = 0;
                                    long j4 = 0;
                                    i6 = 0;
                                    c3 = 0;
                                    boolean z3 = false;
                                    z2 = true;
                                    while (true) {
                                        if (i4 + i6 != i5) {
                                            c4 = cArr[i4 + i6];
                                            if (c4 == '+') {
                                                if (c4 != 'E' || c4 == 'e') {
                                                    j3 = j2;
                                                    if (c3 != 2 || c3 == 4) {
                                                        c3 = 5;
                                                        i6++;
                                                        j2 = j3;
                                                    }
                                                } else if (c4 == '-') {
                                                    j3 = j2;
                                                    if (c3 == 0) {
                                                        c3 = 1;
                                                        z3 = true;
                                                    } else {
                                                        if (c3 != 5) {
                                                        }
                                                        c3 = 6;
                                                    }
                                                    i6++;
                                                    j2 = j3;
                                                } else if (c4 == '.') {
                                                    j3 = j2;
                                                    if (c3 == 2) {
                                                        c3 = 3;
                                                        i6++;
                                                        j2 = j3;
                                                    }
                                                } else if (c4 >= '0' && c4 <= '9') {
                                                    if (c3 == 1 || c3 == 0) {
                                                        j4 = -(c4 - '0');
                                                        c3 = 2;
                                                    } else if (c3 == 2) {
                                                        if (j4 != j2) {
                                                            long j5 = (10 * j4) - ((long) (c4 - '0'));
                                                            z2 = (j4 > -922337203685477580L || (j4 == -922337203685477580L && j5 < j4)) & z2;
                                                            j4 = j5;
                                                        }
                                                    } else if (c3 == 3) {
                                                        c3 = 4;
                                                    } else if (c3 == 5 || c3 == 6) {
                                                        c3 = 7;
                                                    }
                                                    j3 = 0;
                                                    i6++;
                                                    j2 = j3;
                                                } else if (!j(c4)) {
                                                    c5 = 2;
                                                    if (c3 != 2) {
                                                        if (c3 != c5 || c3 == 4 || c3 == 7) {
                                                            this.f1569j = i6;
                                                            i7 = 16;
                                                        }
                                                    } else if (z2 || ((j4 == Long.MIN_VALUE && !z3) || (j4 == 0 && z3))) {
                                                        c5 = 2;
                                                        if (c3 != c5) {
                                                        }
                                                        this.f1569j = i6;
                                                        i7 = 16;
                                                    } else {
                                                        if (!z3) {
                                                            j4 = -j4;
                                                        }
                                                        this.f1568i = j4;
                                                        this.f1563d += i6;
                                                        i7 = 15;
                                                    }
                                                    this.f1567h = i7;
                                                }
                                                if (i7 != 0) {
                                                    return i7;
                                                }
                                                if (!j(cArr[this.f1563d])) {
                                                    w("Expected value");
                                                    throw null;
                                                }
                                                c();
                                                i2 = 10;
                                            } else {
                                                j3 = j2;
                                                if (c3 != 5) {
                                                }
                                                c3 = 6;
                                                i6++;
                                                j2 = j3;
                                            }
                                        } else if (i6 != cArr.length) {
                                            if (!g(i6 + 1)) {
                                                i4 = this.f1563d;
                                                i5 = this.f1564e;
                                                c4 = cArr[i4 + i6];
                                                if (c4 == '+') {
                                                    j3 = j2;
                                                    if (c3 != 5) {
                                                    }
                                                    c3 = 6;
                                                    i6++;
                                                    j2 = j3;
                                                } else if (c4 != 'E') {
                                                    j3 = j2;
                                                    if (c3 != 2) {
                                                    }
                                                    c3 = 5;
                                                    i6++;
                                                    j2 = j3;
                                                } else {
                                                    j3 = j2;
                                                    if (c3 != 2) {
                                                    }
                                                    c3 = 5;
                                                    i6++;
                                                    j2 = j3;
                                                }
                                            }
                                            c5 = 2;
                                            if (c3 != 2) {
                                                if (c3 != c5) {
                                                }
                                                this.f1569j = i6;
                                                i7 = 16;
                                            } else {
                                                if (z2) {
                                                }
                                                c5 = 2;
                                                if (c3 != c5) {
                                                }
                                                this.f1569j = i6;
                                                i7 = 16;
                                            }
                                            this.f1567h = i7;
                                            if (i7 != 0) {
                                                return i7;
                                            }
                                            if (!j(cArr[this.f1563d])) {
                                                w("Expected value");
                                                throw null;
                                            }
                                            c();
                                            i2 = 10;
                                        }
                                        i7 = 0;
                                        if (i7 != 0) {
                                            return i7;
                                        }
                                        if (!j(cArr[this.f1563d])) {
                                            w("Expected value");
                                            throw null;
                                        }
                                        c();
                                        i2 = 10;
                                    }
                                }
                                int length = str.length();
                                int i13 = 1;
                                while (true) {
                                    if (i13 >= length) {
                                        if ((this.f1563d + length >= this.f1564e && !g(length + 1)) || !j(cArr[this.f1563d + length])) {
                                            this.f1563d += length;
                                            this.f1567h = i3;
                                            break;
                                        }
                                        break;
                                    }
                                    if ((this.f1563d + i13 < this.f1564e || g(i13 + 1)) && ((c2 = cArr[this.f1563d + i13]) == str.charAt(i13) || c2 == str2.charAt(i13))) {
                                        i13++;
                                    }
                                    i3 = 0;
                                    break;
                                }
                                if (i3 != 0) {
                                    return i3;
                                }
                                i4 = this.f1563d;
                                i5 = this.f1564e;
                                j2 = 0;
                                long j6 = 0;
                                i6 = 0;
                                c3 = 0;
                                boolean z4 = false;
                                z2 = true;
                                while (true) {
                                    if (i4 + i6 != i5) {
                                        c4 = cArr[i4 + i6];
                                        if (c4 == '+') {
                                            j3 = j2;
                                            if (c3 != 5) {
                                            }
                                            c3 = 6;
                                            i6++;
                                            j2 = j3;
                                        } else if (c4 != 'E') {
                                            j3 = j2;
                                            if (c3 != 2) {
                                            }
                                            c3 = 5;
                                            i6++;
                                            j2 = j3;
                                        } else {
                                            j3 = j2;
                                            if (c3 != 2) {
                                            }
                                            c3 = 5;
                                            i6++;
                                            j2 = j3;
                                        }
                                    } else if (i6 != cArr.length) {
                                        if (!g(i6 + 1)) {
                                            i4 = this.f1563d;
                                            i5 = this.f1564e;
                                            c4 = cArr[i4 + i6];
                                            if (c4 == '+') {
                                                j3 = j2;
                                                if (c3 != 5) {
                                                }
                                                c3 = 6;
                                                i6++;
                                                j2 = j3;
                                            } else if (c4 != 'E') {
                                                j3 = j2;
                                                if (c3 != 2) {
                                                }
                                                c3 = 5;
                                                i6++;
                                                j2 = j3;
                                            } else {
                                                j3 = j2;
                                                if (c3 != 2) {
                                                }
                                                c3 = 5;
                                                i6++;
                                                j2 = j3;
                                            }
                                        }
                                        c5 = 2;
                                        if (c3 != 2) {
                                            if (c3 != c5) {
                                            }
                                            this.f1569j = i6;
                                            i7 = 16;
                                        } else {
                                            if (z2) {
                                            }
                                            c5 = 2;
                                            if (c3 != c5) {
                                            }
                                            this.f1569j = i6;
                                            i7 = 16;
                                        }
                                        this.f1567h = i7;
                                        if (i7 != 0) {
                                            return i7;
                                        }
                                        if (!j(cArr[this.f1563d])) {
                                            w("Expected value");
                                            throw null;
                                        }
                                        c();
                                        i2 = 10;
                                    }
                                    i7 = 0;
                                    if (i7 != 0) {
                                        return i7;
                                    }
                                    if (!j(cArr[this.f1563d])) {
                                        w("Expected value");
                                        throw null;
                                    }
                                    c();
                                    i2 = 10;
                                }
                            } else if (i9 == 1) {
                                i2 = 4;
                            }
                        }
                        if (i9 == 1 && i9 != 2) {
                            w("Unexpected value");
                            throw null;
                        }
                        c();
                        this.f1563d--;
                        i2 = 7;
                    } else {
                        c();
                        i2 = 8;
                    }
                } else if (i9 != 7) {
                    if (i9 == 8) {
                        throw new IllegalStateException("JsonReader is closed");
                    }
                    iO3 = o(true);
                    if (iO3 != 34) {
                        i2 = 9;
                    } else if (iO3 == 39) {
                        c();
                        i2 = 8;
                    } else if (iO3 == 44) {
                        if (i9 == 1) {
                        }
                        c();
                        this.f1563d--;
                        i2 = 7;
                    } else {
                        if (i9 == 1) {
                        }
                        c();
                        this.f1563d--;
                        i2 = 7;
                    }
                } else if (o(false) == -1) {
                    i2 = 17;
                } else {
                    c();
                    this.f1563d--;
                    iO3 = o(true);
                    if (iO3 != 34) {
                        i2 = 9;
                    } else if (iO3 == 39) {
                        c();
                        i2 = 8;
                    } else if (iO3 == 44) {
                        if (i9 == 1) {
                        }
                        c();
                        this.f1563d--;
                        i2 = 7;
                    } else {
                        if (i9 == 1) {
                        }
                        c();
                        this.f1563d--;
                        i2 = 7;
                    }
                }
                this.f1567h = i2;
                return i2;
            }
            int iO5 = o(true);
            if (iO5 != 44) {
                if (iO5 != 59) {
                    if (iO5 == 93) {
                        this.f1567h = 4;
                        return 4;
                    }
                    w("Unterminated array");
                    throw null;
                }
                c();
            }
        }
        iO3 = o(true);
        if (iO3 != 34) {
            i2 = 9;
        } else if (iO3 == 39) {
            c();
            i2 = 8;
        } else if (iO3 == 44) {
            if (i9 == 1) {
            }
            c();
            this.f1563d--;
            i2 = 7;
        } else {
            if (i9 == 1) {
            }
            c();
            this.f1563d--;
            i2 = 7;
        }
        this.f1567h = i2;
        return i2;
    }

    public void e() throws IOException {
        int iD = this.f1567h;
        if (iD == 0) {
            iD = d();
        }
        if (iD != 4) {
            throw new IllegalStateException("Expected END_ARRAY but was " + t() + k());
        }
        int i2 = this.f1572m - 1;
        this.f1572m = i2;
        int[] iArr = this.f1574o;
        int i3 = i2 - 1;
        iArr[i3] = iArr[i3] + 1;
        this.f1567h = 0;
    }

    public void f() throws IOException {
        int iD = this.f1567h;
        if (iD == 0) {
            iD = d();
        }
        if (iD != 2) {
            throw new IllegalStateException("Expected END_OBJECT but was " + t() + k());
        }
        int i2 = this.f1572m - 1;
        this.f1572m = i2;
        this.f1573n[i2] = null;
        int[] iArr = this.f1574o;
        int i3 = i2 - 1;
        iArr[i3] = iArr[i3] + 1;
        this.f1567h = 0;
    }

    public final boolean g(int i2) throws IOException {
        int i3;
        int i4;
        int i5 = this.f1566g;
        int i6 = this.f1563d;
        this.f1566g = i5 - i6;
        int i7 = this.f1564e;
        char[] cArr = this.f1562c;
        if (i7 != i6) {
            int i8 = i7 - i6;
            this.f1564e = i8;
            System.arraycopy(cArr, i6, cArr, 0, i8);
        } else {
            this.f1564e = 0;
        }
        this.f1563d = 0;
        do {
            int i9 = this.f1564e;
            int i10 = this.f1560a.read(cArr, i9, cArr.length - i9);
            if (i10 == -1) {
                return false;
            }
            i3 = this.f1564e + i10;
            this.f1564e = i3;
            if (this.f1565f == 0 && (i4 = this.f1566g) == 0 && i3 > 0 && cArr[0] == 65279) {
                this.f1563d++;
                this.f1566g = i4 + 1;
                i2++;
            }
        } while (i3 < i2);
        return true;
    }

    public String h() {
        StringBuilder sb = new StringBuilder("$");
        int i2 = this.f1572m;
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = this.f1571l[i3];
            if (i4 == 1 || i4 == 2) {
                sb.append('[');
                sb.append(this.f1574o[i3]);
                sb.append(']');
            } else if (i4 == 3 || i4 == 4 || i4 == 5) {
                sb.append('.');
                String str = this.f1573n[i3];
                if (str != null) {
                    sb.append(str);
                }
            }
        }
        return sb.toString();
    }

    public boolean i() throws IOException {
        int iD = this.f1567h;
        if (iD == 0) {
            iD = d();
        }
        return (iD == 2 || iD == 4) ? false : true;
    }

    public final boolean j(char c2) throws d {
        if (c2 == '\t' || c2 == '\n' || c2 == '\f' || c2 == '\r' || c2 == ' ') {
            return false;
        }
        if (c2 != '#') {
            if (c2 == ',') {
                return false;
            }
            if (c2 != '/' && c2 != '=') {
                if (c2 == '{' || c2 == '}' || c2 == ':') {
                    return false;
                }
                if (c2 != ';') {
                    switch (c2) {
                        case '[':
                        case ']':
                            return false;
                        case '\\':
                            break;
                        default:
                            return true;
                    }
                }
            }
        }
        c();
        return false;
    }

    public final String k() {
        return " at line " + (this.f1565f + 1) + " column " + ((this.f1563d - this.f1566g) + 1) + " path " + h();
    }

    public boolean l() throws IOException {
        int iD = this.f1567h;
        if (iD == 0) {
            iD = d();
        }
        if (iD == 5) {
            this.f1567h = 0;
            int[] iArr = this.f1574o;
            int i2 = this.f1572m - 1;
            iArr[i2] = iArr[i2] + 1;
            return true;
        }
        if (iD != 6) {
            throw new IllegalStateException("Expected a boolean but was " + t() + k());
        }
        this.f1567h = 0;
        int[] iArr2 = this.f1574o;
        int i3 = this.f1572m - 1;
        iArr2[i3] = iArr2[i3] + 1;
        return false;
    }

    public int m() throws IOException {
        String strQ;
        int iD = this.f1567h;
        if (iD == 0) {
            iD = d();
        }
        if (iD == 15) {
            long j2 = this.f1568i;
            int i2 = (int) j2;
            if (j2 != i2) {
                throw new NumberFormatException("Expected an int but was " + this.f1568i + k());
            }
            this.f1567h = 0;
            int[] iArr = this.f1574o;
            int i3 = this.f1572m - 1;
            iArr[i3] = iArr[i3] + 1;
            return i2;
        }
        if (iD == 16) {
            this.f1570k = new String(this.f1562c, this.f1563d, this.f1569j);
            this.f1563d += this.f1569j;
        } else {
            if (iD != 8 && iD != 9 && iD != 10) {
                throw new IllegalStateException("Expected an int but was " + t() + k());
            }
            if (iD == 10) {
                strQ = s();
            } else {
                strQ = q(iD == 8 ? '\'' : '\"');
            }
            this.f1570k = strQ;
            try {
                int i4 = Integer.parseInt(this.f1570k);
                this.f1567h = 0;
                int[] iArr2 = this.f1574o;
                int i5 = this.f1572m - 1;
                iArr2[i5] = iArr2[i5] + 1;
                return i4;
            } catch (NumberFormatException unused) {
            }
        }
        this.f1567h = 11;
        double d2 = Double.parseDouble(this.f1570k);
        int i6 = (int) d2;
        if (i6 != d2) {
            throw new NumberFormatException("Expected an int but was " + this.f1570k + k());
        }
        this.f1570k = null;
        this.f1567h = 0;
        int[] iArr3 = this.f1574o;
        int i7 = this.f1572m - 1;
        iArr3[i7] = iArr3[i7] + 1;
        return i6;
    }

    public String n() throws IOException {
        char c2;
        String strQ;
        int iD = this.f1567h;
        if (iD == 0) {
            iD = d();
        }
        if (iD == 14) {
            strQ = s();
        } else {
            if (iD == 12) {
                c2 = '\'';
            } else {
                if (iD != 13) {
                    throw new IllegalStateException("Expected a name but was " + t() + k());
                }
                c2 = '\"';
            }
            strQ = q(c2);
        }
        this.f1567h = 0;
        this.f1573n[this.f1572m - 1] = strQ;
        return strQ;
    }

    public final int o(boolean z2) throws IOException {
        boolean z3;
        int i2;
        char[] cArr;
        char c2;
        while (true) {
            int i3 = this.f1563d;
            while (true) {
                int i4 = this.f1564e;
                while (true) {
                    z3 = true;
                    if (i3 == i4) {
                        this.f1563d = i3;
                        if (!g(1)) {
                            if (!z2) {
                                return -1;
                            }
                            throw new EOFException("End of input" + k());
                        }
                        i3 = this.f1563d;
                        i4 = this.f1564e;
                    }
                    i2 = i3 + 1;
                    cArr = this.f1562c;
                    c2 = cArr[i3];
                    if (c2 == '\n') {
                        this.f1565f++;
                        this.f1566g = i2;
                    } else if (c2 == ' ' || c2 == '\r' || c2 == '\t') {
                    }
                    i3 = i2;
                }
                this.f1563d = i2;
                if (c2 != '/') {
                    if (c2 != '#') {
                        return c2;
                    }
                    c();
                    break;
                }
                if (i2 == i4) {
                    this.f1563d = i2 - 1;
                    boolean zG = g(2);
                    this.f1563d++;
                    if (!zG) {
                        return c2;
                    }
                }
                c();
                int i5 = this.f1563d;
                char c3 = cArr[i5];
                if (c3 != '*') {
                    if (c3 == '/') {
                        this.f1563d = i5 + 1;
                        break;
                    }
                    return c2;
                }
                this.f1563d = i5 + 1;
                while (true) {
                    int i6 = 0;
                    if (this.f1563d + 2 > this.f1564e && !g(2)) {
                        z3 = false;
                        break;
                    }
                    int i7 = this.f1563d;
                    if (cArr[i7] != '\n') {
                        while (true) {
                            if (i6 >= 2) {
                                break;
                            }
                            if (cArr[this.f1563d + i6] != "*/".charAt(i6)) {
                                break;
                            }
                            i6++;
                        }
                    } else {
                        this.f1565f++;
                        this.f1566g = i7 + 1;
                    }
                    this.f1563d++;
                }
                if (!z3) {
                    w("Unterminated comment");
                    throw null;
                }
                i3 = this.f1563d + 2;
            }
            v();
        }
    }

    public void p() throws IOException {
        int iD = this.f1567h;
        if (iD == 0) {
            iD = d();
        }
        if (iD != 7) {
            throw new IllegalStateException("Expected null but was " + t() + k());
        }
        this.f1567h = 0;
        int[] iArr = this.f1574o;
        int i2 = this.f1572m - 1;
        iArr[i2] = iArr[i2] + 1;
    }

    public final String q(char c2) throws d {
        int i2;
        int i3;
        StringBuilder sb = null;
        while (true) {
            int i4 = this.f1563d;
            int i5 = this.f1564e;
            int i6 = i4;
            while (true) {
                char[] cArr = this.f1562c;
                if (i6 >= i5) {
                    if (sb == null) {
                        sb = new StringBuilder(Math.max((i6 - i4) * 2, 16));
                    }
                    sb.append(cArr, i4, i6 - i4);
                    this.f1563d = i6;
                    if (g(1)) {
                        break;
                    }
                    w("Unterminated string");
                    throw null;
                }
                int i7 = i6 + 1;
                char c3 = cArr[i6];
                if (c3 == c2) {
                    this.f1563d = i7;
                    int i8 = (i7 - i4) - 1;
                    if (sb == null) {
                        return new String(cArr, i4, i8);
                    }
                    sb.append(cArr, i4, i8);
                    return sb.toString();
                }
                char c4 = '\n';
                if (c3 == '\\') {
                    this.f1563d = i7;
                    int i9 = (i7 - i4) - 1;
                    if (sb == null) {
                        sb = new StringBuilder(Math.max((i9 + 1) * 2, 16));
                    }
                    sb.append(cArr, i4, i9);
                    if (this.f1563d == this.f1564e && !g(1)) {
                        w("Unterminated escape sequence");
                        throw null;
                    }
                    int i10 = this.f1563d;
                    int i11 = i10 + 1;
                    this.f1563d = i11;
                    char c5 = cArr[i10];
                    if (c5 != '\n') {
                        if (c5 != '\"' && c5 != '\'' && c5 != '/' && c5 != '\\') {
                            if (c5 == 'b') {
                                c4 = '\b';
                            } else if (c5 == 'f') {
                                c4 = '\f';
                            } else if (c5 != 'n') {
                                if (c5 == 'r') {
                                    c4 = '\r';
                                } else if (c5 == 't') {
                                    c4 = '\t';
                                } else {
                                    if (c5 != 'u') {
                                        w("Invalid escape sequence");
                                        throw null;
                                    }
                                    if (i11 + 4 > this.f1564e && !g(4)) {
                                        w("Unterminated escape sequence");
                                        throw null;
                                    }
                                    int i12 = this.f1563d;
                                    int i13 = i12 + 4;
                                    char c6 = 0;
                                    while (i12 < i13) {
                                        char c7 = cArr[i12];
                                        char c8 = (char) (c6 << 4);
                                        if (c7 < '0' || c7 > '9') {
                                            if (c7 >= 'a' && c7 <= 'f') {
                                                i2 = c7 - 'a';
                                            } else {
                                                if (c7 < 'A' || c7 > 'F') {
                                                    throw new NumberFormatException("\\u".concat(new String(cArr, this.f1563d, 4)));
                                                }
                                                i2 = c7 - 'A';
                                            }
                                            i3 = i2 + 10;
                                        } else {
                                            i3 = c7 - '0';
                                        }
                                        c6 = (char) (i3 + c8);
                                        i12++;
                                    }
                                    this.f1563d += 4;
                                    c4 = c6;
                                }
                            }
                        }
                        sb.append(c4);
                        break;
                    }
                    this.f1565f++;
                    this.f1566g = i11;
                    c4 = c5;
                    sb.append(c4);
                    break;
                    break;
                }
                if (c3 == '\n') {
                    this.f1565f++;
                    this.f1566g = i7;
                }
                i6 = i7;
            }
        }
    }

    public String r() throws IOException {
        String str;
        char c2;
        int iD = this.f1567h;
        if (iD == 0) {
            iD = d();
        }
        if (iD == 10) {
            str = s();
        } else {
            if (iD == 8) {
                c2 = '\'';
            } else if (iD == 9) {
                c2 = '\"';
            } else if (iD == 11) {
                str = this.f1570k;
                this.f1570k = null;
            } else if (iD == 15) {
                str = Long.toString(this.f1568i);
            } else {
                if (iD != 16) {
                    throw new IllegalStateException("Expected a string but was " + t() + k());
                }
                str = new String(this.f1562c, this.f1563d, this.f1569j);
                this.f1563d += this.f1569j;
            }
            str = q(c2);
        }
        this.f1567h = 0;
        int[] iArr = this.f1574o;
        int i2 = this.f1572m - 1;
        iArr[i2] = iArr[i2] + 1;
        return str;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:32:0x0044. Please report as an issue. */
    public final String s() throws d {
        String string;
        StringBuilder sb = null;
        int i2 = 0;
        while (true) {
            int i3 = 0;
            while (true) {
                int i4 = this.f1563d;
                int i5 = i4 + i3;
                int i6 = this.f1564e;
                char[] cArr = this.f1562c;
                if (i5 < i6) {
                    char c2 = cArr[i4 + i3];
                    if (c2 != '\t' && c2 != '\n' && c2 != '\f' && c2 != '\r' && c2 != ' ') {
                        if (c2 != '#') {
                            if (c2 != ',') {
                                if (c2 != '/' && c2 != '=') {
                                    if (c2 != '{' && c2 != '}' && c2 != ':') {
                                        if (c2 != ';') {
                                            switch (c2) {
                                                case '[':
                                                case ']':
                                                    break;
                                                case '\\':
                                                    break;
                                                default:
                                                    i3++;
                                                    break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        c();
                    }
                    i2 = i3;
                } else if (i3 >= cArr.length) {
                    if (sb == null) {
                        sb = new StringBuilder(Math.max(i3, 16));
                    }
                    sb.append(cArr, this.f1563d, i3);
                    this.f1563d += i3;
                    if (!g(1)) {
                    }
                } else if (!g(i3 + 1)) {
                    i2 = i3;
                }
                if (sb == null) {
                    string = new String(cArr, this.f1563d, i2);
                } else {
                    sb.append(cArr, this.f1563d, i2);
                    string = sb.toString();
                }
                this.f1563d += i2;
                return string;
            }
        }
    }

    public b t() throws IOException {
        int iD = this.f1567h;
        if (iD == 0) {
            iD = d();
        }
        switch (iD) {
            case 1:
                return b.BEGIN_OBJECT;
            case 2:
                return b.END_OBJECT;
            case 3:
                return b.BEGIN_ARRAY;
            case 4:
                return b.END_ARRAY;
            case 5:
            case 6:
                return b.BOOLEAN;
            case 7:
                return b.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return b.STRING;
            case 12:
            case TYPE_UINT32_VALUE:
            case TYPE_ENUM_VALUE:
                return b.NAME;
            case TYPE_SFIXED32_VALUE:
            case 16:
                return b.NUMBER;
            case TYPE_SINT32_VALUE:
                return b.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    public String toString() {
        return getClass().getSimpleName() + k();
    }

    public final void u(int i2) {
        int i3 = this.f1572m;
        int[] iArr = this.f1571l;
        if (i3 == iArr.length) {
            int i4 = i3 * 2;
            this.f1571l = Arrays.copyOf(iArr, i4);
            this.f1574o = Arrays.copyOf(this.f1574o, i4);
            this.f1573n = (String[]) Arrays.copyOf(this.f1573n, i4);
        }
        int[] iArr2 = this.f1571l;
        int i5 = this.f1572m;
        this.f1572m = i5 + 1;
        iArr2[i5] = i2;
    }

    public final void v() {
        char c2;
        do {
            if (this.f1563d >= this.f1564e && !g(1)) {
                return;
            }
            int i2 = this.f1563d;
            int i3 = i2 + 1;
            this.f1563d = i3;
            c2 = this.f1562c[i2];
            if (c2 == '\n') {
                this.f1565f++;
                this.f1566g = i3;
                return;
            }
        } while (c2 != '\r');
    }

    public final void w(String str) throws d {
        throw new d(str + k());
    }
}
