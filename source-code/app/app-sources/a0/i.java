package a0;

import android.accessibilityservice.GestureDescription;
import android.graphics.Path;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class i {
    public static /* synthetic */ GestureDescription.Builder d() {
        return new GestureDescription.Builder();
    }

    public static /* synthetic */ GestureDescription.StrokeDescription f(Path path) {
        return new GestureDescription.StrokeDescription(path, 0L, 1L);
    }
}
