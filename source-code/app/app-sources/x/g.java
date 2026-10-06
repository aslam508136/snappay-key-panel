package x;

import android.view.View;
import android.view.ViewParent;

/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ViewParent f1988a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ViewParent f1989b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final View f1990c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1991d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int[] f1992e;

    public g(View view) {
        this.f1990c = view;
    }

    public final boolean a(int i2, int i3, int i4, int i5, int[] iArr, int i6, int[] iArr2) {
        ViewParent viewParentB;
        int i7;
        int i8;
        int[] iArr3;
        if (!this.f1991d || (viewParentB = b(i6)) == null) {
            return false;
        }
        if (i2 == 0 && i3 == 0 && i4 == 0 && i5 == 0) {
            if (iArr != null) {
                iArr[0] = 0;
                iArr[1] = 0;
            }
            return false;
        }
        View view = this.f1990c;
        if (iArr != null) {
            view.getLocationInWindow(iArr);
            i7 = iArr[0];
            i8 = iArr[1];
        } else {
            i7 = 0;
            i8 = 0;
        }
        if (iArr2 == null) {
            if (this.f1992e == null) {
                this.f1992e = new int[2];
            }
            int[] iArr4 = this.f1992e;
            iArr4[0] = 0;
            iArr4[1] = 0;
            iArr3 = iArr4;
        } else {
            iArr3 = iArr2;
        }
        androidx.lifecycle.i.T(viewParentB, this.f1990c, i2, i3, i4, i5, i6, iArr3);
        if (iArr != null) {
            view.getLocationInWindow(iArr);
            iArr[0] = iArr[0] - i7;
            iArr[1] = iArr[1] - i8;
        }
        return true;
    }

    public final ViewParent b(int i2) {
        if (i2 == 0) {
            return this.f1988a;
        }
        if (i2 != 1) {
            return null;
        }
        return this.f1989b;
    }
}
