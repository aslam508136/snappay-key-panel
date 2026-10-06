package x;

import android.content.ClipData;
import android.net.Uri;
import android.os.Bundle;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1971a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ClipData f1972b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1973c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1974d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Uri f1975e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Bundle f1976f;

    public c(ClipData clipData, int i2) {
        this.f1972b = clipData;
        this.f1973c = i2;
    }

    public final String toString() {
        String strValueOf;
        switch (this.f1971a) {
            case 1:
                StringBuilder sb = new StringBuilder("ContentInfoCompat{clip=");
                sb.append(this.f1972b);
                sb.append(", source=");
                int i2 = this.f1973c;
                if (i2 == 0) {
                    strValueOf = "SOURCE_APP";
                } else if (i2 == 1) {
                    strValueOf = "SOURCE_CLIPBOARD";
                } else if (i2 != 2) {
                    strValueOf = i2 != 3 ? String.valueOf(i2) : "SOURCE_DRAG_AND_DROP";
                } else {
                    strValueOf = "SOURCE_INPUT_METHOD";
                }
                sb.append(strValueOf);
                sb.append(", flags=");
                int i3 = this.f1974d;
                sb.append((i3 & 1) != 0 ? "FLAG_CONVERT_TO_PLAIN_TEXT" : String.valueOf(i3));
                sb.append(", linkUri=");
                sb.append(this.f1975e);
                sb.append(", extras=");
                sb.append(this.f1976f);
                sb.append("}");
                return sb.toString();
            default:
                return super.toString();
        }
    }

    public c(c cVar) {
        ClipData clipData = cVar.f1972b;
        clipData.getClass();
        this.f1972b = clipData;
        int i2 = cVar.f1973c;
        if (i2 < 0) {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too low)", "source", 0, 3));
        }
        if (i2 > 3) {
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too high)", "source", 0, 3));
        }
        this.f1973c = i2;
        int i3 = cVar.f1974d;
        if ((i3 & 1) == i3) {
            this.f1974d = i3;
            this.f1975e = cVar.f1975e;
            this.f1976f = cVar.f1976f;
        } else {
            throw new IllegalArgumentException("Requested flags 0x" + Integer.toHexString(i3) + ", but only 0x" + Integer.toHexString(1) + " are allowed");
        }
    }
}
