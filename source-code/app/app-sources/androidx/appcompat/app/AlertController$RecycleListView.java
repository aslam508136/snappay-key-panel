package androidx.appcompat.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ListView;
import c.a;

/* JADX INFO: loaded from: classes.dex */
public class AlertController$RecycleListView extends ListView {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f80b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f81c;

    public AlertController$RecycleListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.f499s);
        this.f81c = typedArrayObtainStyledAttributes.getDimensionPixelOffset(0, -1);
        this.f80b = typedArrayObtainStyledAttributes.getDimensionPixelOffset(1, -1);
    }
}
