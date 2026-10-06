package j;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.AutoCompleteTextView;

/* JADX INFO: loaded from: classes.dex */
public class r extends AutoCompleteTextView {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f1380d = {R.attr.popupBackground};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s f1381b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final v0 f1382c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, com.snapay.app.R.attr.autoCompleteTextViewStyle);
        s2.a(context);
        r2.a(this, getContext());
        m0.a aVarU = m0.a.u(getContext(), attributeSet, f1380d, com.snapay.app.R.attr.autoCompleteTextViewStyle);
        if (aVarU.s(0)) {
            setDropDownBackgroundDrawable(aVarU.k(0));
        }
        aVarU.w();
        s sVar = new s(this);
        this.f1381b = sVar;
        sVar.d(attributeSet, com.snapay.app.R.attr.autoCompleteTextViewStyle);
        v0 v0Var = new v0(this);
        this.f1382c = v0Var;
        v0Var.d(attributeSet, com.snapay.app.R.attr.autoCompleteTextViewStyle);
        v0Var.b();
    }

    @Override // android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        s sVar = this.f1381b;
        if (sVar != null) {
            sVar.a();
        }
        v0 v0Var = this.f1382c;
        if (v0Var != null) {
            v0Var.b();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        s sVar = this.f1381b;
        if (sVar != null) {
            return sVar.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        s sVar = this.f1381b;
        if (sVar != null) {
            return sVar.c();
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        androidx.lifecycle.i.P(inputConnectionOnCreateInputConnection, editorInfo, this);
        return inputConnectionOnCreateInputConnection;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        s sVar = this.f1381b;
        if (sVar != null) {
            sVar.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        s sVar = this.f1381b;
        if (sVar != null) {
            sVar.f(i2);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(androidx.lifecycle.i.l0(callback, this));
    }

    @Override // android.widget.AutoCompleteTextView
    public void setDropDownBackgroundResource(int i2) {
        setDropDownBackgroundDrawable(e.b.c(getContext(), i2));
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        s sVar = this.f1381b;
        if (sVar != null) {
            sVar.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        s sVar = this.f1381b;
        if (sVar != null) {
            sVar.i(mode);
        }
    }

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i2) {
        super.setTextAppearance(context, i2);
        v0 v0Var = this.f1382c;
        if (v0Var != null) {
            v0Var.e(context, i2);
        }
    }
}
