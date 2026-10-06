package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Parcelable;
import android.util.Log;
import androidx.versionedparcelable.CustomVersionedParcelable;
import j.x0;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes.dex */
public class IconCompat extends CustomVersionedParcelable {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final PorterDuff.Mode f252k = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f254b;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public String f262j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f253a = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f255c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Parcelable f256d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f257e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f258f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ColorStateList f259g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public PorterDuff.Mode f260h = f252k;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f261i = null;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:47:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:50:0x0109  */
    public final String toString() {
        String str;
        int height;
        int iIntValue;
        int i2;
        if (this.f253a == -1) {
            return String.valueOf(this.f254b);
        }
        StringBuilder sb = new StringBuilder("Icon(typ=");
        switch (this.f253a) {
            case 1:
                str = "BITMAP";
                break;
            case 2:
                str = "RESOURCE";
                break;
            case 3:
                str = "DATA";
                break;
            case 4:
                str = "URI";
                break;
            case 5:
                str = "BITMAP_MASKABLE";
                break;
            case 6:
                str = "URI_MASKABLE";
                break;
            default:
                str = "UNKNOWN";
                break;
        }
        sb.append(str);
        switch (this.f253a) {
            case 1:
            case 5:
                sb.append(" size=");
                sb.append(((Bitmap) this.f254b).getWidth());
                sb.append("x");
                height = ((Bitmap) this.f254b).getHeight();
                sb.append(height);
                if (this.f259g != null) {
                    sb.append(" tint=");
                    sb.append(this.f259g);
                }
                if (this.f260h != f252k) {
                    sb.append(" mode=");
                    sb.append(this.f260h);
                }
                sb.append(")");
                return sb.toString();
            case 2:
                sb.append(" pkg=");
                sb.append(this.f262j);
                sb.append(" id=");
                Object[] objArr = new Object[1];
                int i3 = this.f253a;
                if (i3 == -1 && (i2 = Build.VERSION.SDK_INT) >= 23) {
                    Icon iconE = x0.e(this.f254b);
                    if (i2 >= 28) {
                        iIntValue = iconE.getResId();
                    } else {
                        try {
                            iIntValue = ((Integer) iconE.getClass().getMethod("getResId", new Class[0]).invoke(iconE, new Object[0])).intValue();
                        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
                            Log.e("IconCompat", "Unable to get icon resource", e2);
                            iIntValue = 0;
                        }
                    }
                    break;
                } else {
                    if (i3 != 2) {
                        throw new IllegalStateException("called getResId() on " + this);
                    }
                    iIntValue = this.f257e;
                }
                objArr[0] = Integer.valueOf(iIntValue);
                sb.append(String.format("0x%08x", objArr));
                if (this.f259g != null) {
                    sb.append(" tint=");
                    sb.append(this.f259g);
                }
                if (this.f260h != f252k) {
                    sb.append(" mode=");
                    sb.append(this.f260h);
                }
                sb.append(")");
                return sb.toString();
            case 3:
                sb.append(" len=");
                sb.append(this.f257e);
                if (this.f258f != 0) {
                    sb.append(" off=");
                    height = this.f258f;
                    sb.append(height);
                }
                if (this.f259g != null) {
                    sb.append(" tint=");
                    sb.append(this.f259g);
                }
                if (this.f260h != f252k) {
                    sb.append(" mode=");
                    sb.append(this.f260h);
                }
                sb.append(")");
                return sb.toString();
            case 4:
            case 6:
                sb.append(" uri=");
                sb.append(this.f254b);
                if (this.f259g != null) {
                    sb.append(" tint=");
                    sb.append(this.f259g);
                }
                if (this.f260h != f252k) {
                    sb.append(" mode=");
                    sb.append(this.f260h);
                }
                sb.append(")");
                return sb.toString();
            default:
                if (this.f259g != null) {
                    sb.append(" tint=");
                    sb.append(this.f259g);
                }
                if (this.f260h != f252k) {
                    sb.append(" mode=");
                    sb.append(this.f260h);
                }
                sb.append(")");
                return sb.toString();
        }
    }
}
