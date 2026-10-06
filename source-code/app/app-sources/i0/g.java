package i0;

import java.io.Serializable;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class g extends b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Serializable f1140a;

    public g(Boolean bool) {
        bool.getClass();
        this.f1140a = bool;
    }

    public static boolean j(g gVar) {
        Serializable serializable = gVar.f1140a;
        if (serializable instanceof Number) {
            Number number = (Number) serializable;
            if ((number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte)) {
                return true;
            }
        }
        return false;
    }

    @Override // i0.b
    public final b a() {
        return this;
    }

    @Override // i0.b
    public final boolean b() {
        Serializable serializable = this.f1140a;
        return serializable instanceof Boolean ? ((Boolean) serializable).booleanValue() : Boolean.parseBoolean(i());
    }

    @Override // i0.b
    public final double c() {
        return this.f1140a instanceof Number ? h().doubleValue() : Double.parseDouble(i());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || g.class != obj.getClass()) {
            return false;
        }
        g gVar = (g) obj;
        Serializable serializable = this.f1140a;
        Serializable serializable2 = gVar.f1140a;
        if (serializable == null) {
            return serializable2 == null;
        }
        if (j(this) && j(gVar)) {
            return h().longValue() == gVar.h().longValue();
        }
        if (!(serializable instanceof Number) || !(serializable2 instanceof Number)) {
            return serializable.equals(serializable2);
        }
        double dDoubleValue = h().doubleValue();
        double dDoubleValue2 = gVar.h().doubleValue();
        if (dDoubleValue != dDoubleValue2) {
            return Double.isNaN(dDoubleValue) && Double.isNaN(dDoubleValue2);
        }
        return true;
    }

    @Override // i0.b
    public final long g() {
        return this.f1140a instanceof Number ? h().longValue() : Long.parseLong(i());
    }

    @Override // i0.b
    public final Number h() {
        Serializable serializable = this.f1140a;
        return serializable instanceof String ? new j0.a((String) serializable) : (Number) serializable;
    }

    public final int hashCode() {
        long jDoubleToLongBits;
        Serializable serializable = this.f1140a;
        if (serializable == null) {
            return 31;
        }
        if (j(this)) {
            jDoubleToLongBits = h().longValue();
        } else {
            if (!(serializable instanceof Number)) {
                return serializable.hashCode();
            }
            jDoubleToLongBits = Double.doubleToLongBits(h().doubleValue());
        }
        return (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
    }

    @Override // i0.b
    public final String i() {
        Serializable serializable = this.f1140a;
        if (serializable instanceof Number) {
            return h().toString();
        }
        return serializable instanceof Boolean ? ((Boolean) serializable).toString() : (String) serializable;
    }

    public g(Number number) {
        number.getClass();
        this.f1140a = number;
    }

    public g(String str) {
        str.getClass();
        this.f1140a = str;
    }
}
