package x;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class n extends o {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2001d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n(int i2, int i3) {
        super(i2, Boolean.class, 28);
        this.f2001d = i3;
    }

    public final Boolean b(View view) {
        switch (this.f2001d) {
            case 0:
                return Boolean.valueOf(view.isScreenReaderFocusable());
            default:
                return Boolean.valueOf(view.isAccessibilityHeading());
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ n(int i2, int i3, int i4, int i5) {
        super(i2, CharSequence.class, i4);
        this.f2001d = i5;
    }
}
