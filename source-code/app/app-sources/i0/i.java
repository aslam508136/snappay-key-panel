package i0;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class i {
    public final Object fromJson(Reader reader) {
        return read(new l0.a(reader));
    }

    public final Object fromJsonTree(b bVar) {
        try {
            return read(new k0.b(bVar));
        } catch (IOException e2) {
            throw new c(e2);
        }
    }

    public final i nullSafe() {
        return new h(this);
    }

    public abstract Object read(l0.a aVar);

    public final String toJson(Object obj) {
        StringWriter stringWriter = new StringWriter();
        try {
            toJson(stringWriter, obj);
            return stringWriter.toString();
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }

    public final b toJsonTree(Object obj) {
        try {
            k0.d dVar = new k0.d();
            write(dVar, obj);
            ArrayList arrayList = dVar.f1540i;
            if (arrayList.isEmpty()) {
                return dVar.f1542k;
            }
            throw new IllegalStateException("Expected one JSON element but was " + arrayList);
        } catch (IOException e2) {
            throw new c(e2);
        }
    }

    public abstract void write(l0.c cVar, Object obj);

    public final Object fromJson(String str) {
        return fromJson(new StringReader(str));
    }

    public final void toJson(Writer writer, Object obj) {
        write(new l0.c(writer), obj);
    }
}
