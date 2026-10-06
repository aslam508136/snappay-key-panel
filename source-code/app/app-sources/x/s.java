package x;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class s {
    public static void a(View view, Context context, int[] iArr, AttributeSet attributeSet, TypedArray typedArray, int i2, int i3) {
        view.saveAttributeDataForStyleable(context, iArr, attributeSet, typedArray, i2, i3);
    }
}
