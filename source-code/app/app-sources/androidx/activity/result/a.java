package androidx.activity.result;

import a0.g;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.q;
import androidx.fragment.app.s;
import androidx.fragment.app.v;
import androidx.versionedparcelable.ParcelImpl;
import j.p0;

/* JADX INFO: loaded from: classes.dex */
public final class a implements Parcelable.Creator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f73a;

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        switch (this.f73a) {
            case 0:
                return new b(parcel);
            case 1:
                return new p0(parcel);
            case 2:
                return new g(parcel);
            case 3:
                return new androidx.fragment.app.b(parcel);
            case 4:
                return new q(parcel);
            case 5:
                return new s(parcel);
            case 6:
                return new v(parcel);
            default:
                return new ParcelImpl(parcel);
        }
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i2) {
        switch (this.f73a) {
            case 0:
                return new b[i2];
            case 1:
                return new p0[i2];
            case 2:
                return new g[i2];
            case 3:
                return new androidx.fragment.app.b[i2];
            case 4:
                return new q[i2];
            case 5:
                return new s[i2];
            case 6:
                return new v[i2];
            default:
                return new ParcelImpl[i2];
        }
    }
}
