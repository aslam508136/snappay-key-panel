package androidx.lifecycle;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f415a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Method f416b;

    public b(int i2, Method method) {
        this.f415a = i2;
        this.f416b = method;
        method.setAccessible(true);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f415a == bVar.f415a && this.f416b.getName().equals(bVar.f416b.getName());
    }

    public final int hashCode() {
        return this.f416b.getName().hashCode() + (this.f415a * 31);
    }
}
