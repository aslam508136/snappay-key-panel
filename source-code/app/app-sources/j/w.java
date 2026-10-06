package j;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.CompoundButton;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public final class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CompoundButton f1473a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ColorStateList f1474b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public PorterDuff.Mode f1475c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1476d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1477e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1478f;

    public w(CompoundButton compoundButton) {
        this.f1473a = compoundButton;
    }

    public final void a() {
        Drawable buttonDrawable;
        int i2 = Build.VERSION.SDK_INT;
        CompoundButton compoundButton = this.f1473a;
        if (i2 >= 23) {
            buttonDrawable = compoundButton.getButtonDrawable();
        } else {
            if (!androidx.lifecycle.i.D) {
                try {
                    Field declaredField = CompoundButton.class.getDeclaredField("mButtonDrawable");
                    androidx.lifecycle.i.C = declaredField;
                    declaredField.setAccessible(true);
                } catch (NoSuchFieldException e2) {
                    Log.i("CompoundButtonCompat", "Failed to retrieve mButtonDrawable field", e2);
                }
                androidx.lifecycle.i.D = true;
            }
            Field field = androidx.lifecycle.i.C;
            if (field != null) {
                try {
                    buttonDrawable = (Drawable) field.get(compoundButton);
                } catch (IllegalAccessException e3) {
                    Log.i("CompoundButtonCompat", "Failed to get button drawable via reflection", e3);
                    androidx.lifecycle.i.C = null;
                    buttonDrawable = null;
                }
            } else {
                buttonDrawable = null;
            }
        }
        if (buttonDrawable != null) {
            if (this.f1476d || this.f1477e) {
                Drawable drawableMutate = androidx.lifecycle.i.k0(buttonDrawable).mutate();
                if (this.f1476d) {
                    drawableMutate.setTintList(this.f1474b);
                }
                if (this.f1477e) {
                    drawableMutate.setTintMode(this.f1475c);
                }
                if (drawableMutate.isStateful()) {
                    drawableMutate.setState(compoundButton.getDrawableState());
                }
                compoundButton.setButtonDrawable(drawableMutate);
            }
        }
    }

    public final void b(AttributeSet attributeSet, int i2) {
        int iP;
        int iP2;
        CompoundButton compoundButton = this.f1473a;
        Context context = compoundButton.getContext();
        int[] iArr = c.a.f492l;
        m0.a aVarU = m0.a.u(context, attributeSet, iArr, i2);
        x.u.d(compoundButton, compoundButton.getContext(), iArr, attributeSet, (TypedArray) aVarU.f1643b, i2);
        boolean z2 = true;
        try {
            if (!aVarU.s(1) || (iP2 = aVarU.p(1, 0)) == 0) {
                z2 = false;
            } else {
                try {
                    compoundButton.setButtonDrawable(e.b.c(compoundButton.getContext(), iP2));
                } catch (Resources.NotFoundException unused) {
                    z2 = false;
                }
            }
            if (!z2 && aVarU.s(0) && (iP = aVarU.p(0, 0)) != 0) {
                compoundButton.setButtonDrawable(e.b.c(compoundButton.getContext(), iP));
            }
            if (aVarU.s(2)) {
                compoundButton.setButtonTintList(aVarU.h(2));
            }
            if (aVarU.s(3)) {
                compoundButton.setButtonTintMode(g1.c(aVarU.n(3, -1), null));
            }
        } finally {
            aVarU.w();
        }
    }
}
