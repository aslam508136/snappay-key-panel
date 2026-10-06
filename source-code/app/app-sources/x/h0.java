package x;

import android.view.DisplayCutout;
import android.view.WindowInsets;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class h0 extends g0 {
    public h0(l0 l0Var, WindowInsets windowInsets) {
        super(l0Var, windowInsets);
    }

    @Override // x.k0
    public l0 a() {
        return l0.c(this.f1985c.consumeDisplayCutout(), null);
    }

    @Override // x.k0
    public d e() {
        DisplayCutout displayCutout = this.f1985c.getDisplayCutout();
        if (displayCutout == null) {
            return null;
        }
        return new d(displayCutout);
    }

    @Override // x.f0, x.k0
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof h0)) {
            return false;
        }
        h0 h0Var = (h0) obj;
        return Objects.equals(this.f1985c, h0Var.f1985c) && Objects.equals(this.f1987e, h0Var.f1987e);
    }

    @Override // x.k0
    public int hashCode() {
        return this.f1985c.hashCode();
    }
}
