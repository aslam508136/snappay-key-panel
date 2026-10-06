package r;

import android.graphics.Insets;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final b f1889e = new b(0, 0, 0, 0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f1890a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1891b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1892c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f1893d;

    public b(int i2, int i3, int i4, int i5) {
        this.f1890a = i2;
        this.f1891b = i3;
        this.f1892c = i4;
        this.f1893d = i5;
    }

    public static b a(int i2, int i3, int i4, int i5) {
        return (i2 == 0 && i3 == 0 && i4 == 0 && i5 == 0) ? f1889e : new b(i2, i3, i4, i5);
    }

    public final Insets b() {
        return Insets.of(this.f1890a, this.f1891b, this.f1892c, this.f1893d);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b.class != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        return this.f1893d == bVar.f1893d && this.f1890a == bVar.f1890a && this.f1892c == bVar.f1892c && this.f1891b == bVar.f1891b;
    }

    public final int hashCode() {
        return (((((this.f1890a * 31) + this.f1891b) * 31) + this.f1892c) * 31) + this.f1893d;
    }

    public final String toString() {
        return "Insets{left=" + this.f1890a + ", top=" + this.f1891b + ", right=" + this.f1892c + ", bottom=" + this.f1893d + '}';
    }
}
