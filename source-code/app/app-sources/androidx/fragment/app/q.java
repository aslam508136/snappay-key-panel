package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class q implements Parcelable {
    public static final Parcelable.Creator<q> CREATOR = new androidx.activity.result.a(4);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f345a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f346b;

    public q(Parcel parcel) {
        this.f345a = parcel.readString();
        this.f346b = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f345a);
        parcel.writeInt(this.f346b);
    }
}
