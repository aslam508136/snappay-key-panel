package j;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.LocaleList;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class v0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextView f1454a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public t2 f1455b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public t2 f1456c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public t2 f1457d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public t2 f1458e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public t2 f1459f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public t2 f1460g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public t2 f1461h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final b1 f1462i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f1463j = 0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f1464k = -1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Typeface f1465l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f1466m;

    public v0(TextView textView) {
        this.f1454a = textView;
        this.f1462i = new b1(textView);
    }

    public static t2 c(Context context, y yVar, int i2) {
        ColorStateList colorStateListI;
        synchronized (yVar) {
            colorStateListI = yVar.f1497a.i(context, i2);
        }
        if (colorStateListI == null) {
            return null;
        }
        t2 t2Var = new t2();
        t2Var.f1445d = true;
        t2Var.f1442a = colorStateListI;
        return t2Var;
    }

    public static void f(TextView textView, InputConnection inputConnection, EditorInfo editorInfo) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 30 || inputConnection == null) {
            return;
        }
        CharSequence text = textView.getText();
        if (i2 >= 30) {
            editorInfo.setInitialSurroundingSubText(text, 0);
            return;
        }
        text.getClass();
        if (i2 >= 30) {
            editorInfo.setInitialSurroundingSubText(text, 0);
            return;
        }
        int i3 = editorInfo.initialSelStart;
        int i4 = editorInfo.initialSelEnd;
        int i5 = i3 > i4 ? i4 + 0 : i3 + 0;
        int i6 = i3 > i4 ? i3 - 0 : i4 + 0;
        int length = text.length();
        if (i5 >= 0 && i6 <= length) {
            int i7 = editorInfo.inputType & 4095;
            if (!(i7 == 129 || i7 == 225 || i7 == 18)) {
                if (length <= 2048) {
                    androidx.lifecycle.i.c0(editorInfo, text, i5, i6);
                    return;
                }
                int i8 = i6 - i5;
                int i9 = i8 > 1024 ? 0 : i8;
                int i10 = 2048 - i9;
                int iMin = Math.min(text.length() - i6, i10 - Math.min(i5, (int) (((double) i10) * 0.8d)));
                int iMin2 = Math.min(i5, i10 - iMin);
                int i11 = i5 - iMin2;
                if (Character.isLowSurrogate(text.charAt(i11))) {
                    i11++;
                    iMin2--;
                }
                if (Character.isHighSurrogate(text.charAt((i6 + iMin) - 1))) {
                    iMin--;
                }
                CharSequence charSequenceConcat = i9 != i8 ? TextUtils.concat(text.subSequence(i11, i11 + iMin2), text.subSequence(i6, iMin + i6)) : text.subSequence(i11, iMin2 + i9 + iMin + i11);
                int i12 = iMin2 + 0;
                androidx.lifecycle.i.c0(editorInfo, charSequenceConcat, i12, i9 + i12);
                return;
            }
        }
        androidx.lifecycle.i.c0(editorInfo, null, 0, 0);
    }

    public final void a(Drawable drawable, t2 t2Var) {
        if (drawable == null || t2Var == null) {
            return;
        }
        y.d(drawable, t2Var, this.f1454a.getDrawableState());
    }

    public final void b() {
        t2 t2Var = this.f1455b;
        TextView textView = this.f1454a;
        if (t2Var != null || this.f1456c != null || this.f1457d != null || this.f1458e != null) {
            Drawable[] compoundDrawables = textView.getCompoundDrawables();
            a(compoundDrawables[0], this.f1455b);
            a(compoundDrawables[1], this.f1456c);
            a(compoundDrawables[2], this.f1457d);
            a(compoundDrawables[3], this.f1458e);
        }
        if (this.f1459f == null && this.f1460g == null) {
            return;
        }
        Drawable[] compoundDrawablesRelative = textView.getCompoundDrawablesRelative();
        a(compoundDrawablesRelative[0], this.f1459f);
        a(compoundDrawablesRelative[2], this.f1460g);
    }

    /* JADX WARN: Code duplicated, block: B:45:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:46:0x0104  */
    /* JADX WARN: Code duplicated, block: B:51:0x0117  */
    /* JADX WARN: Multi-variable type inference failed */
    public final void d(AttributeSet attributeSet, int i2) {
        boolean zG;
        boolean z2;
        ColorStateList colorStateListH;
        ColorStateList colorStateListH2;
        ColorStateList colorStateListH3;
        String strQ;
        String strQ2;
        Paint.FontMetricsInt fontMetricsInt;
        int i3;
        int resourceId;
        int i4;
        int i5;
        int i6;
        TextView textView = this.f1454a;
        Context context = textView.getContext();
        y yVarA = y.a();
        int[] iArr = c.a.f488h;
        m0.a aVarU = m0.a.u(context, attributeSet, iArr, i2);
        x.u.d(textView, textView.getContext(), iArr, attributeSet, (TypedArray) aVarU.f1643b, i2);
        int iP = aVarU.p(0, -1);
        if (aVarU.s(3)) {
            this.f1455b = c(context, yVarA, aVarU.p(3, 0));
        }
        if (aVarU.s(1)) {
            this.f1456c = c(context, yVarA, aVarU.p(1, 0));
        }
        if (aVarU.s(4)) {
            this.f1457d = c(context, yVarA, aVarU.p(4, 0));
        }
        if (aVarU.s(2)) {
            this.f1458e = c(context, yVarA, aVarU.p(2, 0));
        }
        int i7 = Build.VERSION.SDK_INT;
        if (aVarU.s(5)) {
            this.f1459f = c(context, yVarA, aVarU.p(5, 0));
        }
        if (aVarU.s(6)) {
            this.f1460g = c(context, yVarA, aVarU.p(6, 0));
        }
        aVarU.w();
        boolean z3 = textView.getTransformationMethod() instanceof PasswordTransformationMethod;
        int[] iArr2 = c.a.f502v;
        if (iP != -1) {
            m0.a aVar = new m0.a(context, context.obtainStyledAttributes(iP, iArr2));
            if (z3 || !aVar.s(14)) {
                zG = false;
                z2 = false;
            } else {
                zG = aVar.g(14, false);
                z2 = true;
            }
            j(context, aVar);
            if (i7 < 23) {
                colorStateListH = aVar.s(3) ? aVar.h(3) : null;
                if (aVar.s(4)) {
                    colorStateListH2 = aVar.h(4);
                    i6 = 5;
                } else {
                    i6 = 5;
                    colorStateListH2 = null;
                }
                if (aVar.s(i6)) {
                    colorStateListH3 = aVar.h(i6);
                    i4 = 15;
                }
                if (aVar.s(i4)) {
                    strQ = aVar.q(i4);
                    i5 = 26;
                } else {
                    i5 = 26;
                    strQ = null;
                }
                if (i7 >= i5 || !aVar.s(13)) {
                    strQ2 = null;
                } else {
                    strQ2 = aVar.q(13);
                }
                aVar.w();
            } else {
                colorStateListH = null;
                colorStateListH2 = null;
            }
            i4 = 15;
            colorStateListH3 = null;
            if (aVar.s(i4)) {
                strQ = aVar.q(i4);
                i5 = 26;
            } else {
                i5 = 26;
                strQ = null;
            }
            if (i7 >= i5) {
                strQ2 = null;
            } else {
                strQ2 = null;
            }
            aVar.w();
        } else {
            zG = false;
            z2 = false;
            colorStateListH = null;
            colorStateListH2 = null;
            colorStateListH3 = null;
            strQ = null;
            strQ2 = null;
        }
        m0.a aVar2 = new m0.a(context, context.obtainStyledAttributes(attributeSet, iArr2, i2, 0));
        if (!z3 && aVar2.s(14)) {
            zG = aVar2.g(14, false);
            z2 = true;
        }
        if (i7 < 23) {
            if (aVar2.s(3)) {
                colorStateListH = aVar2.h(3);
            }
            if (aVar2.s(4)) {
                colorStateListH2 = aVar2.h(4);
            }
            if (aVar2.s(5)) {
                colorStateListH3 = aVar2.h(5);
            }
        }
        ColorStateList colorStateList = colorStateListH;
        ColorStateList colorStateList2 = colorStateListH2;
        ColorStateList colorStateList3 = colorStateListH3;
        if (aVar2.s(15)) {
            strQ = aVar2.q(15);
        }
        String str = strQ;
        if (i7 >= 26 && aVar2.s(13)) {
            strQ2 = aVar2.q(13);
        }
        String str2 = strQ2;
        if (i7 >= 28 && aVar2.s(0) && aVar2.j(0, -1) == 0) {
            textView.setTextSize(0, 0.0f);
        }
        j(context, aVar2);
        aVar2.w();
        if (colorStateList != null) {
            textView.setTextColor(colorStateList);
        }
        if (colorStateList2 != null) {
            textView.setHintTextColor(colorStateList2);
        }
        if (colorStateList3 != null) {
            textView.setLinkTextColor(colorStateList3);
        }
        if (!z3 && z2) {
            textView.setAllCaps(zG);
        }
        Typeface typeface = this.f1465l;
        if (typeface != null) {
            if (this.f1464k == -1) {
                textView.setTypeface(typeface, this.f1463j);
            } else {
                textView.setTypeface(typeface);
            }
        }
        if (str2 != null) {
            textView.setFontVariationSettings(str2);
        }
        if (str != null) {
            if (i7 >= 24) {
                textView.setTextLocales(LocaleList.forLanguageTags(str));
            } else {
                textView.setTextLocale(Locale.forLanguageTag(str.substring(0, str.indexOf(44))));
            }
        }
        int[] iArr3 = c.a.f489i;
        b1 b1Var = this.f1462i;
        Context context2 = b1Var.f1186j;
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, iArr3, i2, 0);
        TextView textView2 = b1Var.f1185i;
        x.u.d(textView2, textView2.getContext(), iArr3, attributeSet, typedArrayObtainStyledAttributes, i2);
        if (typedArrayObtainStyledAttributes.hasValue(5)) {
            b1Var.f1177a = typedArrayObtainStyledAttributes.getInt(5, 0);
        }
        float dimension = typedArrayObtainStyledAttributes.hasValue(4) ? typedArrayObtainStyledAttributes.getDimension(4, -1.0f) : -1.0f;
        float dimension2 = typedArrayObtainStyledAttributes.hasValue(2) ? typedArrayObtainStyledAttributes.getDimension(2, -1.0f) : -1.0f;
        float dimension3 = typedArrayObtainStyledAttributes.hasValue(1) ? typedArrayObtainStyledAttributes.getDimension(1, -1.0f) : -1.0f;
        if (typedArrayObtainStyledAttributes.hasValue(3) && (resourceId = typedArrayObtainStyledAttributes.getResourceId(3, 0)) > 0) {
            TypedArray typedArrayObtainTypedArray = typedArrayObtainStyledAttributes.getResources().obtainTypedArray(resourceId);
            int length = typedArrayObtainTypedArray.length();
            int[] iArr4 = new int[length];
            if (length > 0) {
                for (int i8 = 0; i8 < length; i8++) {
                    iArr4[i8] = typedArrayObtainTypedArray.getDimensionPixelSize(i8, -1);
                }
                b1Var.f1182f = b1.b(iArr4);
                b1Var.h();
            }
            typedArrayObtainTypedArray.recycle();
        }
        typedArrayObtainStyledAttributes.recycle();
        if (!b1Var.i()) {
            b1Var.f1177a = 0;
        } else if (b1Var.f1177a == 1) {
            if (!b1Var.f1183g) {
                DisplayMetrics displayMetrics = context2.getResources().getDisplayMetrics();
                if (dimension2 == -1.0f) {
                    i3 = 2;
                    dimension2 = TypedValue.applyDimension(2, 12.0f, displayMetrics);
                } else {
                    i3 = 2;
                }
                if (dimension3 == -1.0f) {
                    dimension3 = TypedValue.applyDimension(i3, 112.0f, displayMetrics);
                }
                if (dimension == -1.0f) {
                    dimension = 1.0f;
                }
                b1Var.j(dimension2, dimension3, dimension);
            }
            b1Var.g();
        }
        if (a0.b.f11a && b1Var.f1177a != 0) {
            int[] iArr5 = b1Var.f1182f;
            if (iArr5.length > 0) {
                if (textView.getAutoSizeStepGranularity() != -1.0f) {
                    textView.setAutoSizeTextTypeUniformWithConfiguration(Math.round(b1Var.f1180d), Math.round(b1Var.f1181e), Math.round(b1Var.f1179c), 0);
                } else {
                    textView.setAutoSizeTextTypeUniformWithPresetSizes(iArr5, 0);
                }
            }
        }
        m0.a aVar3 = new m0.a(context, context.obtainStyledAttributes(attributeSet, iArr3));
        int iP2 = aVar3.p(8, -1);
        Drawable drawableB = iP2 != -1 ? yVarA.b(context, iP2) : null;
        int iP3 = aVar3.p(13, -1);
        Drawable drawableB2 = iP3 != -1 ? yVarA.b(context, iP3) : null;
        int iP4 = aVar3.p(9, -1);
        Drawable drawableB3 = iP4 != -1 ? yVarA.b(context, iP4) : null;
        int iP5 = aVar3.p(6, -1);
        Drawable drawableB4 = iP5 != -1 ? yVarA.b(context, iP5) : null;
        int iP6 = aVar3.p(10, -1);
        Drawable drawableB5 = iP6 != -1 ? yVarA.b(context, iP6) : null;
        int iP7 = aVar3.p(7, -1);
        Drawable drawableB6 = iP7 != -1 ? yVarA.b(context, iP7) : null;
        if (drawableB5 != null || drawableB6 != null) {
            Drawable[] compoundDrawablesRelative = textView.getCompoundDrawablesRelative();
            if (drawableB5 == null) {
                drawableB5 = compoundDrawablesRelative[0];
            }
            if (drawableB2 == null) {
                drawableB2 = compoundDrawablesRelative[1];
            }
            if (drawableB6 == null) {
                drawableB6 = compoundDrawablesRelative[2];
            }
            if (drawableB4 == null) {
                drawableB4 = compoundDrawablesRelative[3];
            }
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawableB5, drawableB2, drawableB6, drawableB4);
        } else if (drawableB != null || drawableB2 != null || drawableB3 != null || drawableB4 != null) {
            Drawable[] compoundDrawablesRelative2 = textView.getCompoundDrawablesRelative();
            Drawable drawable = compoundDrawablesRelative2[0];
            if (drawable == null && compoundDrawablesRelative2[2] == null) {
                Drawable[] compoundDrawables = textView.getCompoundDrawables();
                if (drawableB == null) {
                    drawableB = compoundDrawables[0];
                }
                if (drawableB2 == null) {
                    drawableB2 = compoundDrawables[1];
                }
                if (drawableB3 == null) {
                    drawableB3 = compoundDrawables[2];
                }
                if (drawableB4 == null) {
                    drawableB4 = compoundDrawables[3];
                }
                textView.setCompoundDrawablesWithIntrinsicBounds(drawableB, drawableB2, drawableB3, drawableB4);
            } else {
                if (drawableB2 == null) {
                    drawableB2 = compoundDrawablesRelative2[1];
                }
                Drawable drawable2 = compoundDrawablesRelative2[2];
                if (drawableB4 == null) {
                    drawableB4 = compoundDrawablesRelative2[3];
                }
                textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawableB2, drawable2, drawableB4);
            }
        }
        if (aVar3.s(11)) {
            ColorStateList colorStateListH4 = aVar3.h(11);
            if (Build.VERSION.SDK_INT >= 24) {
                textView.setCompoundDrawableTintList(colorStateListH4);
            } else if (textView instanceof a0.l) {
                ((a0.l) textView).setSupportCompoundDrawablesTintList(colorStateListH4);
            }
        }
        if (aVar3.s(12)) {
            fontMetricsInt = null;
            PorterDuff.Mode modeC = g1.c(aVar3.n(12, -1), null);
            if (Build.VERSION.SDK_INT >= 24) {
                textView.setCompoundDrawableTintMode(modeC);
            } else if (textView instanceof a0.l) {
                ((a0.l) textView).setSupportCompoundDrawablesTintMode(modeC);
            }
        } else {
            fontMetricsInt = null;
        }
        int iJ = aVar3.j(14, -1);
        int iJ2 = aVar3.j(17, -1);
        int iJ3 = aVar3.j(18, -1);
        aVar3.w();
        if (iJ != -1) {
            androidx.lifecycle.i.Y(textView, iJ);
        }
        if (iJ2 != -1) {
            androidx.lifecycle.i.Z(textView, iJ2);
        }
        if (iJ3 != -1) {
            if (iJ3 < 0) {
                throw new IllegalArgumentException();
            }
            int fontMetricsInt2 = textView.getPaint().getFontMetricsInt(fontMetricsInt);
            if (iJ3 != fontMetricsInt2) {
                textView.setLineSpacing(iJ3 - fontMetricsInt2, 1.0f);
            }
        }
    }

    public final void e(Context context, int i2) {
        String strQ;
        ColorStateList colorStateListH;
        ColorStateList colorStateListH2;
        ColorStateList colorStateListH3;
        m0.a aVar = new m0.a(context, context.obtainStyledAttributes(i2, c.a.f502v));
        boolean zS = aVar.s(14);
        TextView textView = this.f1454a;
        if (zS) {
            textView.setAllCaps(aVar.g(14, false));
        }
        int i3 = Build.VERSION.SDK_INT;
        if (i3 < 23) {
            if (aVar.s(3) && (colorStateListH3 = aVar.h(3)) != null) {
                textView.setTextColor(colorStateListH3);
            }
            if (aVar.s(5) && (colorStateListH2 = aVar.h(5)) != null) {
                textView.setLinkTextColor(colorStateListH2);
            }
            if (aVar.s(4) && (colorStateListH = aVar.h(4)) != null) {
                textView.setHintTextColor(colorStateListH);
            }
        }
        if (aVar.s(0) && aVar.j(0, -1) == 0) {
            textView.setTextSize(0, 0.0f);
        }
        j(context, aVar);
        if (i3 >= 26 && aVar.s(13) && (strQ = aVar.q(13)) != null) {
            textView.setFontVariationSettings(strQ);
        }
        aVar.w();
        Typeface typeface = this.f1465l;
        if (typeface != null) {
            textView.setTypeface(typeface, this.f1463j);
        }
    }

    public final void g(int i2, int i3, int i4, int i5) {
        b1 b1Var = this.f1462i;
        if (b1Var.i()) {
            DisplayMetrics displayMetrics = b1Var.f1186j.getResources().getDisplayMetrics();
            b1Var.j(TypedValue.applyDimension(i5, i2, displayMetrics), TypedValue.applyDimension(i5, i3, displayMetrics), TypedValue.applyDimension(i5, i4, displayMetrics));
            if (b1Var.g()) {
                b1Var.a();
            }
        }
    }

    public final void h(int[] iArr, int i2) {
        b1 b1Var = this.f1462i;
        if (b1Var.i()) {
            int length = iArr.length;
            if (length > 0) {
                int[] iArrCopyOf = new int[length];
                if (i2 == 0) {
                    iArrCopyOf = Arrays.copyOf(iArr, length);
                } else {
                    DisplayMetrics displayMetrics = b1Var.f1186j.getResources().getDisplayMetrics();
                    for (int i3 = 0; i3 < length; i3++) {
                        iArrCopyOf[i3] = Math.round(TypedValue.applyDimension(i2, iArr[i3], displayMetrics));
                    }
                }
                b1Var.f1182f = b1.b(iArrCopyOf);
                if (!b1Var.h()) {
                    throw new IllegalArgumentException("None of the preset sizes is valid: " + Arrays.toString(iArr));
                }
            } else {
                b1Var.f1183g = false;
            }
            if (b1Var.g()) {
                b1Var.a();
            }
        }
    }

    public final void i(int i2) {
        b1 b1Var = this.f1462i;
        if (b1Var.i()) {
            if (i2 == 0) {
                b1Var.f1177a = 0;
                b1Var.f1180d = -1.0f;
                b1Var.f1181e = -1.0f;
                b1Var.f1179c = -1.0f;
                b1Var.f1182f = new int[0];
                b1Var.f1178b = false;
                return;
            }
            if (i2 != 1) {
                throw new IllegalArgumentException("Unknown auto-size text type: " + i2);
            }
            DisplayMetrics displayMetrics = b1Var.f1186j.getResources().getDisplayMetrics();
            b1Var.j(TypedValue.applyDimension(2, 12.0f, displayMetrics), TypedValue.applyDimension(2, 112.0f, displayMetrics), 1.0f);
            if (b1Var.g()) {
                b1Var.a();
            }
        }
    }

    public final void j(Context context, m0.a aVar) {
        String strQ;
        Typeface typefaceCreate;
        Typeface typeface;
        this.f1463j = aVar.n(2, this.f1463j);
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 28) {
            int iN = aVar.n(11, -1);
            this.f1464k = iN;
            if (iN != -1) {
                this.f1463j = (this.f1463j & 2) | 0;
            }
        }
        if (!aVar.s(10) && !aVar.s(12)) {
            if (aVar.s(1)) {
                this.f1466m = false;
                int iN2 = aVar.n(1, 1);
                if (iN2 == 1) {
                    typeface = Typeface.SANS_SERIF;
                } else if (iN2 == 2) {
                    typeface = Typeface.SERIF;
                } else if (iN2 != 3) {
                    return;
                } else {
                    typeface = Typeface.MONOSPACE;
                }
                this.f1465l = typeface;
                return;
            }
            return;
        }
        this.f1465l = null;
        int i3 = aVar.s(12) ? 12 : 10;
        int i4 = this.f1464k;
        int i5 = this.f1463j;
        if (!context.isRestricted()) {
            try {
                Typeface typefaceM = aVar.m(i3, this.f1463j, new t0(this, i4, i5, new WeakReference(this.f1454a)));
                if (typefaceM != null) {
                    if (i2 >= 28 && this.f1464k != -1) {
                        typefaceM = Typeface.create(Typeface.create(typefaceM, 0), this.f1464k, (this.f1463j & 2) != 0);
                    }
                    this.f1465l = typefaceM;
                }
                this.f1466m = this.f1465l == null;
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            }
        }
        if (this.f1465l != null || (strQ = aVar.q(i3)) == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 28 || this.f1464k == -1) {
            typefaceCreate = Typeface.create(strQ, this.f1463j);
        } else {
            typefaceCreate = Typeface.create(Typeface.create(strQ, 0), this.f1464k, (this.f1463j & 2) != 0);
        }
        this.f1465l = typefaceCreate;
    }
}
