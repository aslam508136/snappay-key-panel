package x;

import android.os.Build;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f2002a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Class f2003b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f2004c;

    public o(int i2, Class cls, int i3) {
        this.f2002a = i2;
        this.f2003b = cls;
        this.f2004c = i3;
    }

    public final Object a(View view) {
        if (!(Build.VERSION.SDK_INT >= this.f2004c)) {
            Object tag = view.getTag(this.f2002a);
            if (this.f2003b.isInstance(tag)) {
                return tag;
            }
            return null;
        }
        n nVar = (n) this;
        int i2 = nVar.f2001d;
        switch (i2) {
            case 0:
                return nVar.b(view);
            case 1:
                switch (i2) {
                    case 1:
                        return view.getAccessibilityPaneTitle();
                    default:
                        return view.getStateDescription();
                }
            case 2:
                switch (i2) {
                    case 1:
                        return view.getAccessibilityPaneTitle();
                    default:
                        return view.getStateDescription();
                }
            default:
                return nVar.b(view);
        }
    }
}
