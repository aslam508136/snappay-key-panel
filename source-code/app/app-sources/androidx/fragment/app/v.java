package androidx.fragment.app;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class v implements Parcelable {
    public static final Parcelable.Creator<v> CREATOR = new androidx.activity.result.a(6);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f385a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f386b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f387c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f388d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f389e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f390f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f391g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f392h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f393i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Bundle f394j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final boolean f395k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f396l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Bundle f397m;

    public v(Parcel parcel) {
        this.f385a = parcel.readString();
        this.f386b = parcel.readString();
        this.f387c = parcel.readInt() != 0;
        this.f388d = parcel.readInt();
        this.f389e = parcel.readInt();
        this.f390f = parcel.readString();
        this.f391g = parcel.readInt() != 0;
        this.f392h = parcel.readInt() != 0;
        this.f393i = parcel.readInt() != 0;
        this.f394j = parcel.readBundle();
        this.f395k = parcel.readInt() != 0;
        this.f397m = parcel.readBundle();
        this.f396l = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentState{");
        sb.append(this.f385a);
        sb.append(" (");
        sb.append(this.f386b);
        sb.append(")}:");
        if (this.f387c) {
            sb.append(" fromLayout");
        }
        int i2 = this.f389e;
        if (i2 != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(i2));
        }
        String str = this.f390f;
        if (str != null && !str.isEmpty()) {
            sb.append(" tag=");
            sb.append(str);
        }
        if (this.f391g) {
            sb.append(" retainInstance");
        }
        if (this.f392h) {
            sb.append(" removing");
        }
        if (this.f393i) {
            sb.append(" detached");
        }
        if (this.f395k) {
            sb.append(" hidden");
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f385a);
        parcel.writeString(this.f386b);
        parcel.writeInt(this.f387c ? 1 : 0);
        parcel.writeInt(this.f388d);
        parcel.writeInt(this.f389e);
        parcel.writeString(this.f390f);
        parcel.writeInt(this.f391g ? 1 : 0);
        parcel.writeInt(this.f392h ? 1 : 0);
        parcel.writeInt(this.f393i ? 1 : 0);
        parcel.writeBundle(this.f394j);
        parcel.writeInt(this.f395k ? 1 : 0);
        parcel.writeBundle(this.f397m);
        parcel.writeInt(this.f396l);
    }
}
