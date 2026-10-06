package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class b implements Parcelable {
    public static final Parcelable.Creator<b> CREATOR = new androidx.activity.result.a(3);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int[] f305a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f306b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int[] f307c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int[] f308d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f309e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f310f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f311g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f312h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final CharSequence f313i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f314j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final CharSequence f315k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ArrayList f316l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ArrayList f317m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f318n;

    public b(Parcel parcel) {
        this.f305a = parcel.createIntArray();
        this.f306b = parcel.createStringArrayList();
        this.f307c = parcel.createIntArray();
        this.f308d = parcel.createIntArray();
        this.f309e = parcel.readInt();
        this.f310f = parcel.readString();
        this.f311g = parcel.readInt();
        this.f312h = parcel.readInt();
        this.f313i = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f314j = parcel.readInt();
        this.f315k = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f316l = parcel.createStringArrayList();
        this.f317m = parcel.createStringArrayList();
        this.f318n = parcel.readInt() != 0;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeIntArray(this.f305a);
        parcel.writeStringList(this.f306b);
        parcel.writeIntArray(this.f307c);
        parcel.writeIntArray(this.f308d);
        parcel.writeInt(this.f309e);
        parcel.writeString(this.f310f);
        parcel.writeInt(this.f311g);
        parcel.writeInt(this.f312h);
        TextUtils.writeToParcel(this.f313i, parcel, 0);
        parcel.writeInt(this.f314j);
        TextUtils.writeToParcel(this.f315k, parcel, 0);
        parcel.writeStringList(this.f316l);
        parcel.writeStringList(this.f317m);
        parcel.writeInt(this.f318n ? 1 : 0);
    }

    public b(a aVar) {
        int size = aVar.f288a.size();
        this.f305a = new int[size * 5];
        if (!aVar.f294g) {
            throw new IllegalStateException("Not on back stack");
        }
        this.f306b = new ArrayList(size);
        this.f307c = new int[size];
        this.f308d = new int[size];
        int i2 = 0;
        int i3 = 0;
        while (i2 < size) {
            x xVar = (x) aVar.f288a.get(i2);
            int i4 = i3 + 1;
            this.f305a[i3] = xVar.f400a;
            this.f306b.add(null);
            int[] iArr = this.f305a;
            int i5 = i4 + 1;
            iArr[i4] = xVar.f401b;
            int i6 = i5 + 1;
            iArr[i5] = xVar.f402c;
            int i7 = i6 + 1;
            iArr[i6] = xVar.f403d;
            iArr[i7] = xVar.f404e;
            this.f307c[i2] = xVar.f405f.ordinal();
            this.f308d[i2] = xVar.f406g.ordinal();
            i2++;
            i3 = i7 + 1;
        }
        this.f309e = aVar.f293f;
        this.f310f = aVar.f295h;
        this.f311g = aVar.f304q;
        this.f312h = aVar.f296i;
        this.f313i = aVar.f297j;
        this.f314j = aVar.f298k;
        this.f315k = aVar.f299l;
        this.f316l = aVar.f300m;
        this.f317m = aVar.f301n;
        this.f318n = aVar.f302o;
    }
}
