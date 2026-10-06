package j;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class n2 extends c0.b {
    public static final Parcelable.Creator<n2> CREATOR = new m2(0);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1337c;

    public n2(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.f1337c = ((Boolean) parcel.readValue(null)).booleanValue();
    }

    public final String toString() {
        return "SearchView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " isIconified=" + this.f1337c + "}";
    }

    @Override // c0.b, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.f508a, i2);
        parcel.writeValue(Boolean.valueOf(this.f1337c));
    }
}
