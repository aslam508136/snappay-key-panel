package j;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class m2 implements Parcelable.ClassLoaderCreator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1319a;

    public /* synthetic */ m2(int i2) {
        this.f1319a = i2;
    }

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        switch (this.f1319a) {
            case 0:
                return new n2(parcel, null);
            case 1:
                return new z2(parcel, null);
            default:
                if (parcel.readParcelable(null) == null) {
                    return c0.b.f507b;
                }
                throw new IllegalStateException("superState must be null");
        }
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i2) {
        switch (this.f1319a) {
            case 0:
                return new n2[i2];
            case 1:
                return new z2[i2];
            default:
                return new c0.b[i2];
        }
    }

    @Override // android.os.Parcelable.ClassLoaderCreator
    public final Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
        switch (this.f1319a) {
            case 0:
                return new n2(parcel, classLoader);
            case 1:
                return new z2(parcel, classLoader);
            default:
                if (parcel.readParcelable(classLoader) == null) {
                    return c0.b.f507b;
                }
                throw new IllegalStateException("superState must be null");
        }
    }
}
