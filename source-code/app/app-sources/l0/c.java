package l0;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class c implements Closeable, Flushable {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final String[] f1586h = new String[128];

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Writer f1587a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int[] f1588b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1589c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f1590d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1591e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f1592f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f1593g;

    static {
        for (int i2 = 0; i2 <= 31; i2++) {
            f1586h[i2] = String.format("\\u%04x", Integer.valueOf(i2));
        }
        String[] strArr = f1586h;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        String[] strArr2 = (String[]) strArr.clone();
        strArr2[60] = "\\u003c";
        strArr2[62] = "\\u003e";
        strArr2[38] = "\\u0026";
        strArr2[61] = "\\u003d";
        strArr2[39] = "\\u0027";
    }

    public c(Writer writer) {
        int[] iArr = new int[32];
        this.f1588b = iArr;
        this.f1589c = 0;
        if (iArr.length == 0) {
            this.f1588b = Arrays.copyOf(iArr, 0 * 2);
        }
        int[] iArr2 = this.f1588b;
        int i2 = this.f1589c;
        this.f1589c = i2 + 1;
        iArr2[i2] = 6;
        this.f1590d = ":";
        this.f1593g = true;
        if (writer == null) {
            throw new NullPointerException("out == null");
        }
        this.f1587a = writer;
    }

    public final void a() throws IOException {
        int i2 = i();
        int i3 = 2;
        if (i2 != 1) {
            Writer writer = this.f1587a;
            if (i2 == 2) {
                writer.append(',');
                return;
            }
            if (i2 == 4) {
                writer.append((CharSequence) this.f1590d);
                j(5);
                return;
            }
            i3 = 7;
            if (i2 != 6) {
                if (i2 != 7) {
                    throw new IllegalStateException("Nesting problem.");
                }
                if (!this.f1591e) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
            }
        }
        j(i3);
    }

    public void b() throws IOException {
        q();
        a();
        int i2 = this.f1589c;
        int[] iArr = this.f1588b;
        if (i2 == iArr.length) {
            this.f1588b = Arrays.copyOf(iArr, i2 * 2);
        }
        int[] iArr2 = this.f1588b;
        int i3 = this.f1589c;
        this.f1589c = i3 + 1;
        iArr2[i3] = 1;
        this.f1587a.write(91);
    }

    public void c() throws IOException {
        q();
        a();
        int i2 = this.f1589c;
        int[] iArr = this.f1588b;
        if (i2 == iArr.length) {
            this.f1588b = Arrays.copyOf(iArr, i2 * 2);
        }
        int[] iArr2 = this.f1588b;
        int i3 = this.f1589c;
        this.f1589c = i3 + 1;
        iArr2[i3] = 3;
        this.f1587a.write(123);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f1587a.close();
        int i2 = this.f1589c;
        if (i2 > 1 || (i2 == 1 && this.f1588b[i2 - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.f1589c = 0;
    }

    public final void d(int i2, int i3, char c2) throws IOException {
        int i4 = i();
        if (i4 != i3 && i4 != i2) {
            throw new IllegalStateException("Nesting problem.");
        }
        if (this.f1592f == null) {
            this.f1589c--;
            this.f1587a.write(c2);
        } else {
            throw new IllegalStateException("Dangling name: " + this.f1592f);
        }
    }

    public void e() throws IOException {
        d(1, 2, ']');
    }

    public void f() throws IOException {
        d(3, 5, '}');
    }

    public void flush() throws IOException {
        if (this.f1589c == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.f1587a.flush();
    }

    public void g(String str) {
        if (str == null) {
            throw new NullPointerException("name == null");
        }
        if (this.f1592f != null) {
            throw new IllegalStateException();
        }
        if (this.f1589c == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.f1592f = str;
    }

    public c h() throws IOException {
        if (this.f1592f != null) {
            if (!this.f1593g) {
                this.f1592f = null;
                return this;
            }
            q();
        }
        a();
        this.f1587a.write("null");
        return this;
    }

    public final int i() {
        int i2 = this.f1589c;
        if (i2 != 0) {
            return this.f1588b[i2 - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    public final void j(int i2) {
        this.f1588b[this.f1589c - 1] = i2;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x002d  */
    public final void k(String str) throws IOException {
        String str2;
        String[] strArr = f1586h;
        Writer writer = this.f1587a;
        writer.write(34);
        int length = str.length();
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            char cCharAt = str.charAt(i3);
            if (cCharAt < 128) {
                str2 = strArr[cCharAt];
                if (str2 != null) {
                    if (i2 < i3) {
                        writer.write(str, i2, i3 - i2);
                    }
                    writer.write(str2);
                    i2 = i3 + 1;
                }
            } else {
                if (cCharAt == 8232) {
                    str2 = "\\u2028";
                } else if (cCharAt == 8233) {
                    str2 = "\\u2029";
                }
                if (i2 < i3) {
                    writer.write(str, i2, i3 - i2);
                }
                writer.write(str2);
                i2 = i3 + 1;
            }
        }
        if (i2 < length) {
            writer.write(str, i2, length - i2);
        }
        writer.write(34);
    }

    public void l(long j2) throws IOException {
        q();
        a();
        this.f1587a.write(Long.toString(j2));
    }

    public void m(Boolean bool) throws IOException {
        if (bool == null) {
            h();
            return;
        }
        q();
        a();
        this.f1587a.write(bool.booleanValue() ? "true" : "false");
    }

    public void n(Number number) throws IOException {
        if (number == null) {
            h();
            return;
        }
        q();
        String string = number.toString();
        if (this.f1591e || !(string.equals("-Infinity") || string.equals("Infinity") || string.equals("NaN"))) {
            a();
            this.f1587a.append((CharSequence) string);
        } else {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + number);
        }
    }

    public void o(String str) throws IOException {
        if (str == null) {
            h();
            return;
        }
        q();
        a();
        k(str);
    }

    public void p(boolean z2) throws IOException {
        q();
        a();
        this.f1587a.write(z2 ? "true" : "false");
    }

    public final void q() throws IOException {
        if (this.f1592f != null) {
            int i2 = i();
            if (i2 == 5) {
                this.f1587a.write(44);
            } else if (i2 != 3) {
                throw new IllegalStateException("Nesting problem.");
            }
            j(4);
            k(this.f1592f);
            this.f1592f = null;
        }
    }
}
