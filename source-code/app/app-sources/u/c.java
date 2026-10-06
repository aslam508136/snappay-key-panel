package u;

import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public final class c implements Comparator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1931a;

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.f1931a) {
            case 0:
                byte[] bArr = (byte[]) obj;
                byte[] bArr2 = (byte[]) obj2;
                if (bArr.length != bArr2.length) {
                    return bArr.length - bArr2.length;
                }
                for (int i2 = 0; i2 < bArr.length; i2++) {
                    byte b2 = bArr[i2];
                    byte b3 = bArr2[i2];
                    if (b2 != b3) {
                        return b2 - b3;
                    }
                }
                return 0;
            default:
                return ((Comparable) obj).compareTo((Comparable) obj2);
        }
    }
}
