package j0;

import java.math.BigDecimal;

/* JADX INFO: loaded from: classes.dex */
public final class a extends Number {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1504a;

    public a(String str) {
        this.f1504a = str;
    }

    @Override // java.lang.Number
    public final double doubleValue() {
        return Double.parseDouble(this.f1504a);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        Object obj2 = ((a) obj).f1504a;
        String str = this.f1504a;
        return str == obj2 || str.equals(obj2);
    }

    @Override // java.lang.Number
    public final float floatValue() {
        return Float.parseFloat(this.f1504a);
    }

    public final int hashCode() {
        return this.f1504a.hashCode();
    }

    @Override // java.lang.Number
    public final int intValue() {
        String str = this.f1504a;
        try {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException unused) {
                return (int) Long.parseLong(str);
            }
        } catch (NumberFormatException unused2) {
            return new BigDecimal(str).intValue();
        }
    }

    @Override // java.lang.Number
    public final long longValue() {
        String str = this.f1504a;
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return new BigDecimal(str).longValue();
        }
    }

    public final String toString() {
        return this.f1504a;
    }
}
