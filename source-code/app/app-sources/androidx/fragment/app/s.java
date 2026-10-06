package androidx.fragment.app;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class s implements Parcelable {
    public static final Parcelable.Creator<s> CREATOR = new androidx.activity.result.a(5);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ArrayList f373a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ArrayList f374b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b[] f375c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f376d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f377e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayList f378f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ArrayList f379g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ArrayList f380h;

    public s() {
        this.f377e = null;
        this.f378f = new ArrayList();
        this.f379g = new ArrayList();
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeTypedList(this.f373a);
        parcel.writeStringList(this.f374b);
        parcel.writeTypedArray(this.f375c, i2);
        parcel.writeInt(this.f376d);
        parcel.writeString(this.f377e);
        parcel.writeStringList(this.f378f);
        parcel.writeTypedList(this.f379g);
        parcel.writeTypedList(this.f380h);
    }

    public s(Parcel parcel) {
        this.f377e = null;
        this.f378f = new ArrayList();
        this.f379g = new ArrayList();
        this.f373a = parcel.createTypedArrayList(v.CREATOR);
        this.f374b = parcel.createStringArrayList();
        this.f375c = (b[]) parcel.createTypedArray(b.CREATOR);
        this.f376d = parcel.readInt();
        this.f377e = parcel.readString();
        this.f378f = parcel.createStringArrayList();
        this.f379g = parcel.createTypedArrayList(Bundle.CREATOR);
        this.f380h = parcel.createTypedArrayList(q.CREATOR);
    }
}
