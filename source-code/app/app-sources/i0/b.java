package i0;

import java.io.IOException;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes.dex */
public abstract class b {
    public abstract b a();

    public boolean b() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public double c() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public final a d() {
        if (this instanceof a) {
            return (a) this;
        }
        throw new IllegalStateException("Not a JSON Array: " + this);
    }

    public final e e() {
        if (this instanceof e) {
            return (e) this;
        }
        throw new IllegalStateException("Not a JSON Object: " + this);
    }

    public final g f() {
        if (this instanceof g) {
            return (g) this;
        }
        throw new IllegalStateException("Not a JSON Primitive: " + this);
    }

    public long g() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Number h() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public String i() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public final String toString() {
        try {
            StringWriter stringWriter = new StringWriter();
            l0.c cVar = new l0.c(stringWriter);
            cVar.f1591e = true;
            k0.f.f1544a.write(cVar, (Object) this);
            return stringWriter.toString();
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }
}
