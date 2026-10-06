package j;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;

/* JADX INFO: loaded from: classes.dex */
public final class d0 extends PopupWindow {
    public d0(Context context, AttributeSet attributeSet, int i2, int i3) {
        int resourceId;
        super(context, attributeSet, i2, i3);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, c.a.f498r, i2, i3);
        if (typedArrayObtainStyledAttributes.hasValue(2)) {
            androidx.lifecycle.i.b0(this, typedArrayObtainStyledAttributes.getBoolean(2, false));
        }
        setBackgroundDrawable((!typedArrayObtainStyledAttributes.hasValue(0) || (resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0)) == 0) ? typedArrayObtainStyledAttributes.getDrawable(0) : e.b.c(context, resourceId));
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.widget.PopupWindow
    public final void showAsDropDown(View view, int i2, int i3) {
        super.showAsDropDown(view, i2, i3);
    }

    @Override // android.widget.PopupWindow
    public final void update(View view, int i2, int i3, int i4, int i5) {
        super.update(view, i2, i3, i4, i5);
    }

    @Override // android.widget.PopupWindow
    public final void showAsDropDown(View view, int i2, int i3, int i4) {
        super.showAsDropDown(view, i2, i3, i4);
    }
}
