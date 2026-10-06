package j;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class z2 extends c0.b {
    public static final Parcelable.Creator<z2> CREATOR = new m2(1);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1502c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1503d;

    public z2(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.f1502c = parcel.readInt();
        this.f1503d = parcel.readInt() != 0;
    }

    @Override // c0.b, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.f508a, i2);
        parcel.writeInt(this.f1502c);
        parcel.writeInt(this.f1503d ? 1 : 0);
    }
}
