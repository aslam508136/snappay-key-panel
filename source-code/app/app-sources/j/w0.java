package j;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes.dex */
public class w0 extends TextView implements a0.l, a0.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s f1479b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final v0 f1480c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final s0 f1481d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1482e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Future f1483f;

    public w0(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textViewStyle);
    }

    @Override // android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        s sVar = this.f1479b;
        if (sVar != null) {
            sVar.a();
        }
        v0 v0Var = this.f1480c;
        if (v0Var != null) {
            v0Var.b();
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        if (a0.b.f11a) {
            return super.getAutoSizeMaxTextSize();
        }
        v0 v0Var = this.f1480c;
        if (v0Var != null) {
            return Math.round(v0Var.f1462i.f1181e);
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        if (a0.b.f11a) {
            return super.getAutoSizeMinTextSize();
        }
        v0 v0Var = this.f1480c;
        if (v0Var != null) {
            return Math.round(v0Var.f1462i.f1180d);
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        if (a0.b.f11a) {
            return super.getAutoSizeStepGranularity();
        }
        v0 v0Var = this.f1480c;
        if (v0Var != null) {
            return Math.round(v0Var.f1462i.f1179c);
        }
        return -1;
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        if (a0.b.f11a) {
            return super.getAutoSizeTextAvailableSizes();
        }
        v0 v0Var = this.f1480c;
        return v0Var != null ? v0Var.f1462i.f1182f : new int[0];
    }

    @Override // android.widget.TextView
    @SuppressLint({"WrongConstant"})
    public int getAutoSizeTextType() {
        if (a0.b.f11a) {
            return super.getAutoSizeTextType() == 1 ? 1 : 0;
        }
        v0 v0Var = this.f1480c;
        if (v0Var != null) {
            return v0Var.f1462i.f1177a;
        }
        return 0;
    }

    @Override // android.widget.TextView
    public int getFirstBaselineToTopHeight() {
        return getPaddingTop() - getPaint().getFontMetricsInt().top;
    }

    @Override // android.widget.TextView
    public int getLastBaselineToBottomHeight() {
        return getPaddingBottom() + getPaint().getFontMetricsInt().bottom;
    }

    public ColorStateList getSupportBackgroundTintList() {
        s sVar = this.f1479b;
        if (sVar != null) {
            return sVar.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        s sVar = this.f1479b;
        if (sVar != null) {
            return sVar.c();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        t2 t2Var = this.f1480c.f1461h;
        if (t2Var != null) {
            return t2Var.f1442a;
        }
        return null;
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        t2 t2Var = this.f1480c.f1461h;
        if (t2Var != null) {
            return t2Var.f1443b;
        }
        return null;
    }

    @Override // android.widget.TextView
    public CharSequence getText() {
        Future future = this.f1483f;
        if (future != null) {
            try {
                this.f1483f = null;
                androidx.activity.c.b(future.get());
                if (Build.VERSION.SDK_INT >= 29) {
                    throw null;
                }
                androidx.lifecycle.i.H(this);
                throw null;
            } catch (InterruptedException | ExecutionException unused) {
            }
        }
        return super.getText();
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        s0 s0Var;
        return (Build.VERSION.SDK_INT >= 28 || (s0Var = this.f1481d) == null) ? super.getTextClassifier() : s0Var.b();
    }

    public v.a getTextMetricsParamsCompat() {
        return androidx.lifecycle.i.H(this);
    }

    @Override // android.widget.TextView, android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        this.f1480c.getClass();
        v0.f(this, inputConnectionOnCreateInputConnection, editorInfo);
        androidx.lifecycle.i.P(inputConnectionOnCreateInputConnection, editorInfo, this);
        return inputConnectionOnCreateInputConnection;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        v0 v0Var = this.f1480c;
        if (v0Var == null || a0.b.f11a) {
            return;
        }
        v0Var.f1462i.a();
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i2, int i3) {
        Future future = this.f1483f;
        if (future != null) {
            try {
                this.f1483f = null;
                androidx.activity.c.b(future.get());
                if (Build.VERSION.SDK_INT >= 29) {
                    throw null;
                }
                androidx.lifecycle.i.H(this);
                throw null;
            } catch (InterruptedException | ExecutionException unused) {
            }
        }
        super.onMeasure(i2, i3);
    }

    @Override // android.widget.TextView
    public final void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        super.onTextChanged(charSequence, i2, i3, i4);
        v0 v0Var = this.f1480c;
        if (v0Var == null || a0.b.f11a) {
            return;
        }
        b1 b1Var = v0Var.f1462i;
        if (b1Var.i() && b1Var.f1177a != 0) {
            b1Var.a();
        }
    }

    @Override // android.widget.TextView
    public final void setAutoSizeTextTypeUniformWithConfiguration(int i2, int i3, int i4, int i5) {
        if (a0.b.f11a) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i2, i3, i4, i5);
            return;
        }
        v0 v0Var = this.f1480c;
        if (v0Var != null) {
            v0Var.g(i2, i3, i4, i5);
        }
    }

    @Override // android.widget.TextView
    public final void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i2) {
        if (a0.b.f11a) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i2);
            return;
        }
        v0 v0Var = this.f1480c;
        if (v0Var != null) {
            v0Var.h(iArr, i2);
        }
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int i2) {
        if (a0.b.f11a) {
            super.setAutoSizeTextTypeWithDefaults(i2);
            return;
        }
        v0 v0Var = this.f1480c;
        if (v0Var != null) {
            v0Var.i(i2);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        s sVar = this.f1479b;
        if (sVar != null) {
            sVar.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        s sVar = this.f1479b;
        if (sVar != null) {
            sVar.f(i2);
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        v0 v0Var = this.f1480c;
        if (v0Var != null) {
            v0Var.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        v0 v0Var = this.f1480c;
        if (v0Var != null) {
            v0Var.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelativeWithIntrinsicBounds(int i2, int i3, int i4, int i5) {
        Context context = getContext();
        setCompoundDrawablesRelativeWithIntrinsicBounds(i2 != 0 ? e.b.c(context, i2) : null, i3 != 0 ? e.b.c(context, i3) : null, i4 != 0 ? e.b.c(context, i4) : null, i5 != 0 ? e.b.c(context, i5) : null);
        v0 v0Var = this.f1480c;
        if (v0Var != null) {
            v0Var.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesWithIntrinsicBounds(int i2, int i3, int i4, int i5) {
        Context context = getContext();
        setCompoundDrawablesWithIntrinsicBounds(i2 != 0 ? e.b.c(context, i2) : null, i3 != 0 ? e.b.c(context, i3) : null, i4 != 0 ? e.b.c(context, i4) : null, i5 != 0 ? e.b.c(context, i5) : null);
        v0 v0Var = this.f1480c;
        if (v0Var != null) {
            v0Var.b();
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(androidx.lifecycle.i.l0(callback, this));
    }

    @Override // android.widget.TextView
    public void setFirstBaselineToTopHeight(int i2) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setFirstBaselineToTopHeight(i2);
        } else {
            androidx.lifecycle.i.Y(this, i2);
        }
    }

    @Override // android.widget.TextView
    public void setLastBaselineToBottomHeight(int i2) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setLastBaselineToBottomHeight(i2);
        } else {
            androidx.lifecycle.i.Z(this, i2);
        }
    }

    @Override // android.widget.TextView
    public void setLineHeight(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException();
        }
        int fontMetricsInt = getPaint().getFontMetricsInt(null);
        if (i2 != fontMetricsInt) {
            setLineSpacing(i2 - fontMetricsInt, 1.0f);
        }
    }

    public void setPrecomputedText(v.b bVar) {
        if (Build.VERSION.SDK_INT >= 29) {
            throw null;
        }
        androidx.lifecycle.i.H(this);
        throw null;
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        s sVar = this.f1479b;
        if (sVar != null) {
            sVar.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        s sVar = this.f1479b;
        if (sVar != null) {
            sVar.i(mode);
        }
    }

    @Override // a0.l
    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        v0 v0Var = this.f1480c;
        if (v0Var.f1461h == null) {
            v0Var.f1461h = new t2();
        }
        t2 t2Var = v0Var.f1461h;
        t2Var.f1442a = colorStateList;
        t2Var.f1445d = colorStateList != null;
        v0Var.f1455b = t2Var;
        v0Var.f1456c = t2Var;
        v0Var.f1457d = t2Var;
        v0Var.f1458e = t2Var;
        v0Var.f1459f = t2Var;
        v0Var.f1460g = t2Var;
        v0Var.b();
    }

    @Override // a0.l
    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        v0 v0Var = this.f1480c;
        if (v0Var.f1461h == null) {
            v0Var.f1461h = new t2();
        }
        t2 t2Var = v0Var.f1461h;
        t2Var.f1443b = mode;
        t2Var.f1444c = mode != null;
        v0Var.f1455b = t2Var;
        v0Var.f1456c = t2Var;
        v0Var.f1457d = t2Var;
        v0Var.f1458e = t2Var;
        v0Var.f1459f = t2Var;
        v0Var.f1460g = t2Var;
        v0Var.b();
    }

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i2) {
        super.setTextAppearance(context, i2);
        v0 v0Var = this.f1480c;
        if (v0Var != null) {
            v0Var.e(context, i2);
        }
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier) {
        s0 s0Var;
        if (Build.VERSION.SDK_INT >= 28 || (s0Var = this.f1481d) == null) {
            super.setTextClassifier(textClassifier);
        } else {
            s0Var.f1408b = textClassifier;
        }
    }

    public void setTextFuture(Future<v.b> future) {
        this.f1483f = future;
        if (future != null) {
            requestLayout();
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0032  */
    public void setTextMetricsParamsCompat(v.a aVar) {
        int i2;
        int i3 = Build.VERSION.SDK_INT;
        TextDirectionHeuristic textDirectionHeuristic = aVar.f1954b;
        if (textDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_RTL || textDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_LTR) {
            i2 = 1;
        } else if (textDirectionHeuristic == TextDirectionHeuristics.ANYRTL_LTR) {
            i2 = 2;
        } else if (textDirectionHeuristic == TextDirectionHeuristics.LTR) {
            i2 = 3;
        } else if (textDirectionHeuristic == TextDirectionHeuristics.RTL) {
            i2 = 4;
        } else if (textDirectionHeuristic == TextDirectionHeuristics.LOCALE) {
            i2 = 5;
        } else if (textDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_LTR) {
            i2 = 6;
        } else if (textDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_RTL) {
            i2 = 7;
        } else {
            i2 = 1;
        }
        setTextDirection(i2);
        TextPaint textPaint = aVar.f1953a;
        if (i3 >= 23) {
            getPaint().set(textPaint);
            setBreakStrategy(aVar.f1955c);
            setHyphenationFrequency(aVar.f1956d);
        } else {
            float textScaleX = textPaint.getTextScaleX();
            getPaint().set(textPaint);
            if (textScaleX == getTextScaleX()) {
                setTextScaleX((textScaleX / 2.0f) + 1.0f);
            }
            setTextScaleX(textScaleX);
        }
    }

    @Override // android.widget.TextView
    public final void setTextSize(int i2, float f2) {
        boolean z2 = a0.b.f11a;
        if (z2) {
            super.setTextSize(i2, f2);
            return;
        }
        v0 v0Var = this.f1480c;
        if (v0Var == null || z2) {
            return;
        }
        b1 b1Var = v0Var.f1462i;
        if (b1Var.i() && b1Var.f1177a != 0) {
            return;
        }
        b1Var.f(i2, f2);
    }

    @Override // android.widget.TextView
    public final void setTypeface(Typeface typeface, int i2) {
        Typeface typefaceCreate;
        if (this.f1482e) {
            return;
        }
        if (typeface == null || i2 <= 0) {
            typefaceCreate = null;
        } else {
            Context context = getContext();
            h.a aVar = r.d.f1896a;
            if (context == null) {
                throw new IllegalArgumentException("Context cannot be null");
            }
            typefaceCreate = Typeface.create(typeface, i2);
        }
        this.f1482e = true;
        if (typefaceCreate != null) {
            typeface = typefaceCreate;
        }
        try {
            super.setTypeface(typeface, i2);
        } finally {
            this.f1482e = false;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w0(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        s2.a(context);
        this.f1482e = false;
        r2.a(this, getContext());
        s sVar = new s(this);
        this.f1479b = sVar;
        sVar.d(attributeSet, i2);
        v0 v0Var = new v0(this);
        this.f1480c = v0Var;
        v0Var.d(attributeSet, i2);
        v0Var.b();
        this.f1481d = new s0(this);
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        v0 v0Var = this.f1480c;
        if (v0Var != null) {
            v0Var.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        v0 v0Var = this.f1480c;
        if (v0Var != null) {
            v0Var.b();
        }
    }
}
