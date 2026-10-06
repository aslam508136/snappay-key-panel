package c0;

import android.os.Parcel;
import android.os.Parcelable;
import j.m2;

/* JADX INFO: loaded from: classes.dex */
public abstract class b implements Parcelable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Parcelable f508a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f507b = new a();
    public static final Parcelable.Creator<b> CREATOR = new m2(2);

    public b() {
        this.f508a = null;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.f508a, i2);
    }

    public b(Parcel parcel, ClassLoader classLoader) {
        Parcelable parcelable = parcel.readParcelable(classLoader);
        this.f508a = parcelable == null ? f507b : parcelable;
    }

    public b(Parcelable parcelable) {
        if (parcelable == null) {
            throw new IllegalArgumentException("superState must not be null");
        }
        this.f508a = parcelable == f507b ? null : parcelable;
    }
}
