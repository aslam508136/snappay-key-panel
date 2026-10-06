package k0;

import com.google.crypto.tink.aead.internal.InsecureNonceXChaCha20;
import com.google.crypto.tink.subtle.Base64;
import i0.g;
import i0.i;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Currency;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/* JADX INFO: loaded from: classes.dex */
public final class e extends i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1543a;

    public /* synthetic */ e(int i2) {
        this.f1543a = i2;
    }

    public final Number a(l0.a aVar) throws IOException {
        l0.b bVar = l0.b.NULL;
        switch (this.f1543a) {
            case 22:
                if (aVar.t() == bVar) {
                    aVar.p();
                    return null;
                }
                try {
                    return Byte.valueOf((byte) aVar.m());
                } catch (NumberFormatException e2) {
                    throw new i0.c(e2);
                }
            case 23:
                if (aVar.t() == bVar) {
                    aVar.p();
                    return null;
                }
                try {
                    return Short.valueOf((short) aVar.m());
                } catch (NumberFormatException e3) {
                    throw new i0.c(e3);
                }
            default:
                if (aVar.t() == bVar) {
                    aVar.p();
                    return null;
                }
                try {
                    return Integer.valueOf(aVar.m());
                } catch (NumberFormatException e4) {
                    throw new i0.c(e4);
                }
        }
    }

    public final void b(l0.c cVar, Number number) throws IOException {
        switch (this.f1543a) {
            case 22:
                cVar.n(number);
                break;
            case 23:
                cVar.n(number);
                break;
            default:
                cVar.n(number);
                break;
        }
    }

    @Override // i0.i
    public final i0.b read(l0.a aVar) throws IOException {
        if (aVar instanceof b) {
            b bVar = (b) aVar;
            l0.b bVarT = bVar.t();
            l0.b bVar2 = l0.b.NAME;
            if (bVarT == bVar2 || bVarT == l0.b.END_ARRAY || bVarT == l0.b.END_OBJECT || bVarT == l0.b.END_DOCUMENT) {
                throw new IllegalStateException("Unexpected " + bVarT + " when reading a JsonElement.");
            }
            i0.b bVar3 = (i0.b) bVar.y();
            if (bVar.t() == bVar2) {
                bVar.n();
                bVar.f1536r[bVar.f1535q - 2] = "null";
            } else {
                bVar.z();
                int i2 = bVar.f1535q;
                if (i2 > 0) {
                    bVar.f1536r[i2 - 1] = "null";
                }
            }
            int i3 = bVar.f1535q;
            if (i3 > 0) {
                int[] iArr = bVar.f1537s;
                int i4 = i3 - 1;
                iArr[i4] = iArr[i4] + 1;
            }
            return bVar3;
        }
        int iOrdinal = aVar.t().ordinal();
        if (iOrdinal == 0) {
            i0.a aVar2 = new i0.a();
            aVar.a();
            while (aVar.i()) {
                aVar2.j(read(aVar));
            }
            aVar.e();
            return aVar2;
        }
        if (iOrdinal == 2) {
            i0.e eVar = new i0.e();
            aVar.b();
            while (aVar.i()) {
                eVar.j(aVar.n(), read(aVar));
            }
            aVar.f();
            return eVar;
        }
        if (iOrdinal == 5) {
            return new g(aVar.r());
        }
        if (iOrdinal == 6) {
            return new g(new j0.a(aVar.r()));
        }
        if (iOrdinal == 7) {
            return new g(Boolean.valueOf(aVar.l()));
        }
        if (iOrdinal != 8) {
            throw new IllegalArgumentException();
        }
        aVar.p();
        return i0.d.f1138a;
    }

    public final void write(l0.c cVar, i0.b bVar) throws IOException {
        if (bVar == null || (bVar instanceof i0.d)) {
            cVar.h();
            return;
        }
        if (bVar instanceof g) {
            g gVarF = bVar.f();
            Serializable serializable = gVarF.f1140a;
            if (serializable instanceof Number) {
                cVar.n(gVarF.h());
                return;
            } else if (serializable instanceof Boolean) {
                cVar.p(gVarF.b());
                return;
            } else {
                cVar.o(gVarF.i());
                return;
            }
        }
        if (bVar instanceof i0.a) {
            cVar.b();
            Iterator it = bVar.d().iterator();
            while (it.hasNext()) {
                write(cVar, (i0.b) it.next());
            }
            cVar.e();
            return;
        }
        if (!(bVar instanceof i0.e)) {
            throw new IllegalArgumentException("Couldn't write " + bVar.getClass());
        }
        cVar.c();
        Iterator it2 = ((j0.c) bVar.e().f1139a.entrySet()).iterator();
        while (((j0.d) it2).hasNext()) {
            Map.Entry entry = (Map.Entry) ((j0.b) it2).next();
            cVar.g((String) entry.getKey());
            write(cVar, (i0.b) entry.getValue());
        }
        cVar.f();
    }

    /* JADX WARN: Code duplicated, block: B:49:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:50:0x00af  */
    @Override // i0.i
    public final Object read(l0.a aVar) throws IOException {
        boolean zL;
        l0.b bVar = l0.b.NULL;
        int i2 = this.f1543a;
        switch (i2) {
            case 0:
                ArrayList arrayList = new ArrayList();
                aVar.a();
                while (aVar.i()) {
                    try {
                        arrayList.add(Integer.valueOf(aVar.m()));
                    } catch (NumberFormatException e2) {
                        throw new i0.c(e2);
                    }
                }
                aVar.e();
                int size = arrayList.size();
                AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(size);
                for (int i3 = 0; i3 < size; i3++) {
                    atomicIntegerArray.set(i3, ((Integer) arrayList.get(i3)).intValue());
                }
                return atomicIntegerArray;
            case 1:
            case 2:
            case 3:
            case 6:
            case 7:
            case 21:
            default:
                return new AtomicBoolean(aVar.l());
            case 4:
                if (aVar.t() == bVar) {
                    aVar.p();
                    return null;
                }
                String strR = aVar.r();
                if (strR.length() == 1) {
                    return Character.valueOf(strR.charAt(0));
                }
                throw new i0.c("Expecting character, got: ".concat(strR));
            case 5:
                l0.b bVarT = aVar.t();
                if (bVarT != bVar) {
                    return bVarT == l0.b.BOOLEAN ? Boolean.toString(aVar.l()) : aVar.r();
                }
                aVar.p();
                return null;
            case 8:
                if (aVar.t() != bVar) {
                    return new StringBuilder(aVar.r());
                }
                aVar.p();
                return null;
            case 9:
                if (aVar.t() != bVar) {
                    return new StringBuffer(aVar.r());
                }
                aVar.p();
                return null;
            case 10:
                throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
            case 11:
                if (aVar.t() == bVar) {
                    aVar.p();
                    return null;
                }
                String strR2 = aVar.r();
                if ("null".equals(strR2)) {
                    return null;
                }
                return new URL(strR2);
            case 12:
                if (aVar.t() == bVar) {
                    aVar.p();
                    return null;
                }
                try {
                    String strR3 = aVar.r();
                    if ("null".equals(strR3)) {
                        return null;
                    }
                    return new URI(strR3);
                } catch (URISyntaxException e3) {
                    throw new i0.c(e3);
                }
            case TYPE_UINT32_VALUE:
                if (aVar.t() != bVar) {
                    return InetAddress.getByName(aVar.r());
                }
                aVar.p();
                return null;
            case TYPE_ENUM_VALUE:
                if (aVar.t() != bVar) {
                    return UUID.fromString(aVar.r());
                }
                aVar.p();
                return null;
            case TYPE_SFIXED32_VALUE:
                return Currency.getInstance(aVar.r());
            case 16:
                if (aVar.t() == bVar) {
                    aVar.p();
                    return null;
                }
                aVar.b();
                int i4 = 0;
                int i5 = 0;
                int i6 = 0;
                int i7 = 0;
                int i8 = 0;
                int i9 = 0;
                while (aVar.t() != l0.b.END_OBJECT) {
                    String strN = aVar.n();
                    int iM = aVar.m();
                    if ("year".equals(strN)) {
                        i4 = iM;
                    } else if ("month".equals(strN)) {
                        i5 = iM;
                    } else if ("dayOfMonth".equals(strN)) {
                        i6 = iM;
                    } else if ("hourOfDay".equals(strN)) {
                        i7 = iM;
                    } else if ("minute".equals(strN)) {
                        i8 = iM;
                    } else if ("second".equals(strN)) {
                        i9 = iM;
                    }
                }
                aVar.f();
                return new GregorianCalendar(i4, i5, i6, i7, i8, i9);
            case TYPE_SINT32_VALUE:
                if (aVar.t() == bVar) {
                    aVar.p();
                    return null;
                }
                StringTokenizer stringTokenizer = new StringTokenizer(aVar.r(), "_");
                String strNextToken = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
                String strNextToken2 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
                String strNextToken3 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
                if (strNextToken2 == null && strNextToken3 == null) {
                    return new Locale(strNextToken);
                }
                return strNextToken3 == null ? new Locale(strNextToken, strNextToken2) : new Locale(strNextToken, strNextToken2, strNextToken3);
            case TYPE_SINT64_VALUE:
                return read(aVar);
            case Base64.Encoder.LINE_GROUPS /* 19 */:
                BitSet bitSet = new BitSet();
                aVar.a();
                l0.b bVarT2 = aVar.t();
                int i10 = 0;
                while (bVarT2 != l0.b.END_ARRAY) {
                    int iOrdinal = bVarT2.ordinal();
                    if (iOrdinal == 5) {
                        String strR4 = aVar.r();
                        try {
                            if (Integer.parseInt(strR4) != 0) {
                                zL = true;
                            } else {
                                zL = false;
                            }
                        } catch (NumberFormatException unused) {
                            throw new i0.c("Error: Expecting: bitset number value (1, 0), Found: " + strR4);
                        }
                    } else if (iOrdinal != 6) {
                        if (iOrdinal != 7) {
                            throw new i0.c("Invalid bitset value type: " + bVarT2);
                        }
                        zL = aVar.l();
                    } else if (aVar.m() != 0) {
                        zL = true;
                    } else {
                        zL = false;
                    }
                    if (zL) {
                        bitSet.set(i10);
                    }
                    i10++;
                    bVarT2 = aVar.t();
                }
                aVar.e();
                return bitSet;
            case 20:
                switch (i2) {
                    case 20:
                        l0.b bVarT3 = aVar.t();
                        if (bVarT3 != bVar) {
                            return Boolean.valueOf(bVarT3 == l0.b.STRING ? Boolean.parseBoolean(aVar.r()) : aVar.l());
                        }
                        aVar.p();
                        return null;
                    default:
                        if (aVar.t() != bVar) {
                            return Boolean.valueOf(aVar.r());
                        }
                        aVar.p();
                        return null;
                }
            case 22:
                return a(aVar);
            case 23:
                return a(aVar);
            case InsecureNonceXChaCha20.NONCE_SIZE_IN_BYTES /* 24 */:
                return a(aVar);
            case 25:
                try {
                    return new AtomicInteger(aVar.m());
                } catch (NumberFormatException e4) {
                    throw new i0.c(e4);
                }
        }
    }

    @Override // i0.i
    public final void write(l0.c cVar, Object obj) throws IOException {
        int i2 = this.f1543a;
        int i3 = 0;
        switch (i2) {
            case 0:
                AtomicIntegerArray atomicIntegerArray = (AtomicIntegerArray) obj;
                cVar.b();
                int length = atomicIntegerArray.length();
                while (i3 < length) {
                    cVar.l(atomicIntegerArray.get(i3));
                    i3++;
                }
                cVar.e();
                return;
            case 1:
            case 2:
            case 3:
            case 6:
            case 7:
            case 21:
            default:
                cVar.p(((AtomicBoolean) obj).get());
                return;
            case 4:
                Character ch = (Character) obj;
                cVar.o(ch != null ? String.valueOf(ch) : null);
                return;
            case 5:
                cVar.o((String) obj);
                return;
            case 8:
                StringBuilder sb = (StringBuilder) obj;
                cVar.o(sb != null ? sb.toString() : null);
                return;
            case 9:
                StringBuffer stringBuffer = (StringBuffer) obj;
                cVar.o(stringBuffer != null ? stringBuffer.toString() : null);
                return;
            case 10:
                throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + ((Class) obj).getName() + ". Forgot to register a type adapter?");
            case 11:
                URL url = (URL) obj;
                cVar.o(url != null ? url.toExternalForm() : null);
                return;
            case 12:
                URI uri = (URI) obj;
                cVar.o(uri != null ? uri.toASCIIString() : null);
                return;
            case TYPE_UINT32_VALUE:
                InetAddress inetAddress = (InetAddress) obj;
                cVar.o(inetAddress != null ? inetAddress.getHostAddress() : null);
                return;
            case TYPE_ENUM_VALUE:
                UUID uuid = (UUID) obj;
                cVar.o(uuid != null ? uuid.toString() : null);
                return;
            case TYPE_SFIXED32_VALUE:
                cVar.o(((Currency) obj).getCurrencyCode());
                return;
            case 16:
                Calendar calendar = (Calendar) obj;
                if (calendar == null) {
                    cVar.h();
                    return;
                }
                cVar.c();
                cVar.g("year");
                cVar.l(calendar.get(1));
                cVar.g("month");
                cVar.l(calendar.get(2));
                cVar.g("dayOfMonth");
                cVar.l(calendar.get(5));
                cVar.g("hourOfDay");
                cVar.l(calendar.get(11));
                cVar.g("minute");
                cVar.l(calendar.get(12));
                cVar.g("second");
                cVar.l(calendar.get(13));
                cVar.f();
                return;
            case TYPE_SINT32_VALUE:
                Locale locale = (Locale) obj;
                cVar.o(locale != null ? locale.toString() : null);
                return;
            case TYPE_SINT64_VALUE:
                write(cVar, (i0.b) obj);
                return;
            case Base64.Encoder.LINE_GROUPS /* 19 */:
                BitSet bitSet = (BitSet) obj;
                cVar.b();
                int length2 = bitSet.length();
                while (i3 < length2) {
                    cVar.l(bitSet.get(i3) ? 1L : 0L);
                    i3++;
                }
                cVar.e();
                return;
            case 20:
                Boolean bool = (Boolean) obj;
                switch (i2) {
                    case 20:
                        cVar.m(bool);
                        return;
                    default:
                        cVar.o(bool == null ? "null" : bool.toString());
                        return;
                }
            case 22:
                b(cVar, (Number) obj);
                return;
            case 23:
                b(cVar, (Number) obj);
                return;
            case InsecureNonceXChaCha20.NONCE_SIZE_IN_BYTES /* 24 */:
                b(cVar, (Number) obj);
                return;
            case 25:
                cVar.l(((AtomicInteger) obj).get());
                return;
        }
    }
}
