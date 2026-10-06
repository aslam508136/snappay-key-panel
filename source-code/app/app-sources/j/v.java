package j;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;

/* JADX INFO: loaded from: classes.dex */
public final class v extends CheckedTextView {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f1452c = {R.attr.checkMark};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final v0 f1453b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.checkedTextViewStyle);
        s2.a(context);
        r2.a(this, getContext());
        v0 v0Var = new v0(this);
        this.f1453b = v0Var;
        v0Var.d(attributeSet, R.attr.checkedTextViewStyle);
        v0Var.b();
        m0.a aVarU = m0.a.u(getContext(), attributeSet, f1452c, R.attr.checkedTextViewStyle);
        setCheckMarkDrawable(aVarU.k(0));
        aVarU.w();
    }

    @Override // android.widget.CheckedTextView, android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        v0 v0Var = this.f1453b;
        if (v0Var != null) {
            v0Var.b();
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        androidx.lifecycle.i.P(inputConnectionOnCreateInputConnection, editorInfo, this);
        return inputConnectionOnCreateInputConnection;
    }

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(int i2) {
        setCheckMarkDrawable(e.b.c(getContext(), i2));
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(androidx.lifecycle.i.l0(callback, this));
    }

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i2) {
        super.setTextAppearance(context, i2);
        v0 v0Var = this.f1453b;
        if (v0Var != null) {
            v0Var.e(context, i2);
        }
    }
}
