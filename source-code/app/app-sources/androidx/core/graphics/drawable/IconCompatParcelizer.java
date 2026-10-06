package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Parcel;
import android.os.Parcelable;
import h0.a;
import h0.b;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public class IconCompatParcelizer {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static IconCompat read(a aVar) {
        Parcelable parcelable;
        IconCompat iconCompat = new IconCompat();
        iconCompat.f253a = aVar.f(iconCompat.f253a, 1);
        byte[] bArr = iconCompat.f255c;
        if (aVar.e(2)) {
            Parcel parcel = ((b) aVar).f961e;
            int i2 = parcel.readInt();
            if (i2 < 0) {
                bArr = null;
            } else {
                byte[] bArr2 = new byte[i2];
                parcel.readByteArray(bArr2);
                bArr = bArr2;
            }
        }
        iconCompat.f255c = bArr;
        iconCompat.f256d = aVar.g(iconCompat.f256d, 3);
        iconCompat.f257e = aVar.f(iconCompat.f257e, 4);
        iconCompat.f258f = aVar.f(iconCompat.f258f, 5);
        iconCompat.f259g = (ColorStateList) aVar.g(iconCompat.f259g, 6);
        String string = iconCompat.f261i;
        if (aVar.e(7)) {
            string = ((b) aVar).f961e.readString();
        }
        iconCompat.f261i = string;
        String string2 = iconCompat.f262j;
        if (aVar.e(8)) {
            string2 = ((b) aVar).f961e.readString();
        }
        iconCompat.f262j = string2;
        iconCompat.f260h = PorterDuff.Mode.valueOf(iconCompat.f261i);
        switch (iconCompat.f253a) {
            case -1:
                parcelable = iconCompat.f256d;
                if (parcelable == null) {
                    throw new IllegalArgumentException("Invalid icon");
                }
                iconCompat.f254b = parcelable;
                return iconCompat;
            case 0:
            default:
                return iconCompat;
            case 1:
            case 5:
                parcelable = iconCompat.f256d;
                if (parcelable != null) {
                    iconCompat.f254b = parcelable;
                } else {
                    byte[] bArr3 = iconCompat.f255c;
                    iconCompat.f254b = bArr3;
                    iconCompat.f253a = 3;
                    iconCompat.f257e = 0;
                    iconCompat.f258f = bArr3.length;
                }
                return iconCompat;
            case 2:
            case 4:
            case 6:
                String str = new String(iconCompat.f255c, Charset.forName("UTF-16"));
                iconCompat.f254b = str;
                if (iconCompat.f253a == 2 && iconCompat.f262j == null) {
                    iconCompat.f262j = str.split(":", -1)[0];
                }
                return iconCompat;
            case 3:
                iconCompat.f254b = iconCompat.f255c;
                return iconCompat;
        }
    }

    public static void write(IconCompat iconCompat, a aVar) {
        aVar.getClass();
        iconCompat.f261i = iconCompat.f260h.name();
        switch (iconCompat.f253a) {
            case -1:
            case 1:
            case 5:
                iconCompat.f256d = (Parcelable) iconCompat.f254b;
                break;
            case 2:
                iconCompat.f255c = ((String) iconCompat.f254b).getBytes(Charset.forName("UTF-16"));
                break;
            case 3:
                iconCompat.f255c = (byte[]) iconCompat.f254b;
                break;
            case 4:
            case 6:
                iconCompat.f255c = iconCompat.f254b.toString().getBytes(Charset.forName("UTF-16"));
                break;
        }
        int i2 = iconCompat.f253a;
        if (-1 != i2) {
            aVar.i(1);
            ((b) aVar).f961e.writeInt(i2);
        }
        byte[] bArr = iconCompat.f255c;
        if (bArr != null) {
            aVar.i(2);
            int length = bArr.length;
            Parcel parcel = ((b) aVar).f961e;
            parcel.writeInt(length);
            parcel.writeByteArray(bArr);
        }
        Parcelable parcelable = iconCompat.f256d;
        if (parcelable != null) {
            aVar.i(3);
            ((b) aVar).f961e.writeParcelable(parcelable, 0);
        }
        int i3 = iconCompat.f257e;
        if (i3 != 0) {
            aVar.i(4);
            ((b) aVar).f961e.writeInt(i3);
        }
        int i4 = iconCompat.f258f;
        if (i4 != 0) {
            aVar.i(5);
            ((b) aVar).f961e.writeInt(i4);
        }
        ColorStateList colorStateList = iconCompat.f259g;
        if (colorStateList != null) {
            aVar.i(6);
            ((b) aVar).f961e.writeParcelable(colorStateList, 0);
        }
        String str = iconCompat.f261i;
        if (str != null) {
            aVar.i(7);
            ((b) aVar).f961e.writeString(str);
        }
        String str2 = iconCompat.f262j;
        if (str2 != null) {
            aVar.i(8);
            ((b) aVar).f961e.writeString(str2);
        }
    }
}
