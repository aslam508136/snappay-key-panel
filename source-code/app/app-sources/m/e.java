package m;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedHashMap f1612a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1613b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1614c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1615d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1616e;

    public e(int i2) {
        if (i2 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.f1614c = i2;
        this.f1612a = new LinkedHashMap(0, 0.75f, true);
    }

    public final Object a(Object obj) {
        if (obj == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            Object obj2 = this.f1612a.get(obj);
            if (obj2 != null) {
                this.f1615d++;
                return obj2;
            }
            this.f1616e++;
            return null;
        }
    }

    public final Object b(Object obj, Object obj2) {
        Object objPut;
        if (obj == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            this.f1613b++;
            objPut = this.f1612a.put(obj, obj2);
            if (objPut != null) {
                this.f1613b--;
            }
        }
        c(this.f1614c);
        return objPut;
    }

    public final void c(int i2) {
        while (true) {
            synchronized (this) {
                if (this.f1613b < 0 || (this.f1612a.isEmpty() && this.f1613b != 0)) {
                    break;
                }
                if (this.f1613b > i2 && !this.f1612a.isEmpty()) {
                    Map.Entry entry = (Map.Entry) this.f1612a.entrySet().iterator().next();
                    Object key = entry.getKey();
                    entry.getValue();
                    this.f1612a.remove(key);
                    this.f1613b--;
                }
                return;
            }
        }
        throw new IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
    }

    public final synchronized String toString() {
        int i2;
        int i3;
        i2 = this.f1615d;
        i3 = this.f1616e + i2;
        return String.format(Locale.US, "LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", Integer.valueOf(this.f1614c), Integer.valueOf(this.f1615d), Integer.valueOf(this.f1616e), Integer.valueOf(i3 != 0 ? (i2 * 100) / i3 : 0));
    }
}
