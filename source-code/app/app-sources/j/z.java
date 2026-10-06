package j;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ActionMode;
import android.view.DragEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassifier;
import android.widget.EditText;
import com.snapay.app.R;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class z extends EditText implements x.l {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final s f1498b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final v0 f1499c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final s0 f1500d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a0.k f1501e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.editTextStyle);
        s2.a(context);
        r2.a(this, getContext());
        s sVar = new s(this);
        this.f1498b = sVar;
        sVar.d(attributeSet, R.attr.editTextStyle);
        v0 v0Var = new v0(this);
        this.f1499c = v0Var;
        v0Var.d(attributeSet, R.attr.editTextStyle);
        v0Var.b();
        this.f1500d = new s0(this);
        this.f1501e = new a0.k();
    }

    @Override // x.l
    public final x.c a(x.c cVar) {
        this.f1501e.getClass();
        return a0.k.a(this, cVar);
    }

    @Override // android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        s sVar = this.f1498b;
        if (sVar != null) {
            sVar.a();
        }
        v0 v0Var = this.f1499c;
        if (v0Var != null) {
            v0Var.b();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        s sVar = this.f1498b;
        if (sVar != null) {
            return sVar.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        s sVar = this.f1498b;
        if (sVar != null) {
            return sVar.c();
        }
        return null;
    }

    @Override // android.widget.EditText, android.widget.TextView
    public Editable getText() {
        return Build.VERSION.SDK_INT >= 28 ? super.getText() : getEditableText();
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        s0 s0Var;
        return (Build.VERSION.SDK_INT >= 28 || (s0Var = this.f1500d) == null) ? super.getTextClassifier() : s0Var.b();
    }

    @Override // android.widget.TextView, android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection bVar;
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        this.f1499c.getClass();
        v0.f(this, inputConnectionOnCreateInputConnection, editorInfo);
        androidx.lifecycle.i.P(inputConnectionOnCreateInputConnection, editorInfo, this);
        WeakHashMap weakHashMap = x.u.f2012a;
        String[] strArr = (String[]) getTag(R.id.tag_on_receive_content_mime_types);
        if (inputConnectionOnCreateInputConnection == null || strArr == null) {
            return inputConnectionOnCreateInputConnection;
        }
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 25) {
            editorInfo.contentMimeTypes = strArr;
        } else {
            if (editorInfo.extras == null) {
                editorInfo.extras = new Bundle();
            }
            editorInfo.extras.putStringArray("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES", strArr);
            editorInfo.extras.putStringArray("android.support.v13.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES", strArr);
        }
        h.a aVar = new h.a(this, 5);
        if (i2 >= 25) {
            bVar = new z.a(inputConnectionOnCreateInputConnection, aVar);
        } else {
            String[] strArr2 = androidx.lifecycle.i.O;
            if (i2 >= 25) {
                String[] strArr3 = editorInfo.contentMimeTypes;
                if (strArr3 != null) {
                    strArr2 = strArr3;
                }
            } else {
                Bundle bundle = editorInfo.extras;
                if (bundle != null) {
                    String[] stringArray = bundle.getStringArray("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES");
                    if (stringArray == null) {
                        stringArray = editorInfo.extras.getStringArray("android.support.v13.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES");
                    }
                    if (stringArray != null) {
                        strArr2 = stringArray;
                    }
                }
            }
            if (strArr2.length == 0) {
                return inputConnectionOnCreateInputConnection;
            }
            bVar = new z.b(inputConnectionOnCreateInputConnection, aVar);
        }
        return bVar;
    }

    /* JADX WARN: Code duplicated, block: B:25:0x005b  */
    @Override // android.widget.TextView, android.view.View
    public final boolean onDragEvent(DragEvent dragEvent) {
        boolean z2;
        Activity activity;
        if (Build.VERSION.SDK_INT < 24 || dragEvent.getLocalState() != null) {
            z2 = false;
        } else {
            WeakHashMap weakHashMap = x.u.f2012a;
            if (((String[]) getTag(R.id.tag_on_receive_content_mime_types)) == null) {
                z2 = false;
            } else {
                Context context = getContext();
                while (true) {
                    if (!(context instanceof ContextWrapper)) {
                        activity = null;
                        break;
                    }
                    if (context instanceof Activity) {
                        activity = (Activity) context;
                        break;
                    }
                    context = ((ContextWrapper) context).getBaseContext();
                }
                if (activity == null) {
                    Log.i("ReceiveContent", "Can't handle drop: no activity: view=" + this);
                } else if (dragEvent.getAction() != 1 && dragEvent.getAction() == 3) {
                    i0.a(dragEvent, this, activity);
                    z2 = true;
                }
                z2 = false;
            }
        }
        if (z2) {
            return true;
        }
        return super.onDragEvent(dragEvent);
    }

    @Override // android.widget.EditText, android.widget.TextView
    public final boolean onTextContextMenuItem(int i2) {
        int i3 = 0;
        if (i2 == 16908322 || i2 == 16908337) {
            WeakHashMap weakHashMap = x.u.f2012a;
            if (((String[]) getTag(R.id.tag_on_receive_content_mime_types)) != null) {
                ClipboardManager clipboardManager = (ClipboardManager) getContext().getSystemService("clipboard");
                ClipData primaryClip = clipboardManager == null ? null : clipboardManager.getPrimaryClip();
                if (primaryClip != null && primaryClip.getItemCount() > 0) {
                    x.c cVar = new x.c(primaryClip, 1);
                    cVar.f1974d = i2 != 16908322 ? 1 : 0;
                    x.u.c(this, new x.c(cVar));
                }
                i3 = 1;
            }
        }
        if (i3 != 0) {
            return true;
        }
        return super.onTextContextMenuItem(i2);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        s sVar = this.f1498b;
        if (sVar != null) {
            sVar.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        s sVar = this.f1498b;
        if (sVar != null) {
            sVar.f(i2);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(androidx.lifecycle.i.l0(callback, this));
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        s sVar = this.f1498b;
        if (sVar != null) {
            sVar.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        s sVar = this.f1498b;
        if (sVar != null) {
            sVar.i(mode);
        }
    }

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i2) {
        super.setTextAppearance(context, i2);
        v0 v0Var = this.f1499c;
        if (v0Var != null) {
            v0Var.e(context, i2);
        }
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier) {
        s0 s0Var;
        if (Build.VERSION.SDK_INT >= 28 || (s0Var = this.f1500d) == null) {
            super.setTextClassifier(textClassifier);
        } else {
            s0Var.f1408b = textClassifier;
        }
    }
}
