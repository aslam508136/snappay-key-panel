package x;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f1978a;

    public d(Object obj) {
        this.f1978a = obj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || d.class != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.f1978a, ((d) obj).f1978a);
    }

    public final int hashCode() {
        Object obj = this.f1978a;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public final String toString() {
        return "DisplayCutoutCompat{" + this.f1978a + "}";
    }
}
