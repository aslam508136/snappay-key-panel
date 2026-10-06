package h0;

import android.os.Parcel;
import android.util.SparseIntArray;

/* JADX INFO: loaded from: classes.dex */
public final class b extends a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final SparseIntArray f960d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Parcel f961e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f962f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f963g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f964h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f965i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f966j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f967k;

    public b(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "", new m.b(), new m.b(), new m.b());
    }

    @Override // h0.a
    public final b a() {
        Parcel parcel = this.f961e;
        int iDataPosition = parcel.dataPosition();
        int i2 = this.f966j;
        if (i2 == this.f962f) {
            i2 = this.f963g;
        }
        return new b(parcel, iDataPosition, i2, this.f964h + "  ", this.f957a, this.f958b, this.f959c);
    }

    @Override // h0.a
    public final boolean e(int i2) {
        while (this.f966j < this.f963g) {
            int i3 = this.f967k;
            if (i3 == i2) {
                return true;
            }
            if (String.valueOf(i3).compareTo(String.valueOf(i2)) > 0) {
                return false;
            }
            int i4 = this.f966j;
            Parcel parcel = this.f961e;
            parcel.setDataPosition(i4);
            int i5 = parcel.readInt();
            this.f967k = parcel.readInt();
            this.f966j += i5;
        }
        return this.f967k == i2;
    }

    @Override // h0.a
    public final void i(int i2) {
        int i3 = this.f965i;
        SparseIntArray sparseIntArray = this.f960d;
        Parcel parcel = this.f961e;
        if (i3 >= 0) {
            int i4 = sparseIntArray.get(i3);
            int iDataPosition = parcel.dataPosition();
            parcel.setDataPosition(i4);
            parcel.writeInt(iDataPosition - i4);
            parcel.setDataPosition(iDataPosition);
        }
        this.f965i = i2;
        sparseIntArray.put(i2, parcel.dataPosition());
        parcel.writeInt(0);
        parcel.writeInt(i2);
    }

    public b(Parcel parcel, int i2, int i3, String str, m.b bVar, m.b bVar2, m.b bVar3) {
        super(bVar, bVar2, bVar3);
        this.f960d = new SparseIntArray();
        this.f965i = -1;
        this.f967k = -1;
        this.f961e = parcel;
        this.f962f = i2;
        this.f963g = i3;
        this.f966j = i2;
        this.f964h = str;
    }
}
