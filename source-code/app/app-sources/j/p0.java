package j;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class p0 extends View.BaseSavedState {
    public static final Parcelable.Creator<p0> CREATOR = new androidx.activity.result.a(1);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f1353a;

    public p0(Parcel parcel) {
        super(parcel);
        this.f1353a = parcel.readByte() != 0;
    }

    @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeByte(this.f1353a ? (byte) 1 : (byte) 0);
    }
}
