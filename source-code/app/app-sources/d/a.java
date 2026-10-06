package d;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public abstract class a extends ViewGroup.MarginLayoutParams {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f576a;

    public a() {
        super(-2, -2);
        this.f576a = 8388627;
    }

    public a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f576a = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, c.a.f482b);
        this.f576a = typedArrayObtainStyledAttributes.getInt(0, 0);
        typedArrayObtainStyledAttributes.recycle();
    }

    public a(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
        this.f576a = 0;
    }

    public a(a aVar) {
        super((ViewGroup.MarginLayoutParams) aVar);
        this.f576a = 0;
        this.f576a = aVar.f576a;
    }
}
