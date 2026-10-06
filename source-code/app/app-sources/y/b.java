package y;

import android.R;
import android.os.Build;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.security.crypto.MasterKey;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b f2024b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final b f2025c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final b f2026d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final b f2027e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f2028a;

    static {
        new b(null, 1, null);
        new b(null, 2, null);
        new b(null, 4, null);
        new b(null, 8, null);
        new b(null, 16, null);
        new b(null, 32, null);
        new b(null, 64, null);
        new b(null, 128, null);
        new b(null, MasterKey.DEFAULT_AES_GCM_MASTER_KEY_SIZE, d.class);
        new b(null, 512, d.class);
        new b(null, 1024, e.class);
        new b(null, 2048, e.class);
        f2024b = new b(null, 4096, null);
        f2025c = new b(null, 8192, null);
        new b(null, 16384, null);
        new b(null, 32768, null);
        new b(null, 65536, null);
        new b(null, 131072, i.class);
        new b(null, 262144, null);
        new b(null, 524288, null);
        new b(null, 1048576, null);
        new b(null, 2097152, j.class);
        int i2 = Build.VERSION.SDK_INT;
        new b(i2 >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN : null, R.id.accessibilityActionShowOnScreen, null);
        new b(i2 >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION : null, R.id.accessibilityActionScrollToPosition, g.class);
        f2026d = new b(i2 >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP : null, R.id.accessibilityActionScrollUp, null);
        new b(i2 >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT : null, R.id.accessibilityActionScrollLeft, null);
        f2027e = new b(i2 >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN : null, R.id.accessibilityActionScrollDown, null);
        new b(i2 >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT : null, R.id.accessibilityActionScrollRight, null);
        new b(i2 >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_UP : null, R.id.accessibilityActionPageUp, null);
        new b(i2 >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_DOWN : null, R.id.accessibilityActionPageDown, null);
        new b(i2 >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_LEFT : null, R.id.accessibilityActionPageLeft, null);
        new b(i2 >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_RIGHT : null, R.id.accessibilityActionPageRight, null);
        new b(i2 >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK : null, R.id.accessibilityActionContextClick, null);
        new b(i2 >= 24 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS : null, R.id.accessibilityActionSetProgress, h.class);
        new b(i2 >= 26 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW : null, R.id.accessibilityActionMoveWindow, f.class);
        new b(i2 >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP : null, R.id.accessibilityActionShowTooltip, null);
        new b(i2 >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP : null, R.id.accessibilityActionHideTooltip, null);
        new b(i2 >= 30 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PRESS_AND_HOLD : null, R.id.accessibilityActionPressAndHold, null);
        new b(i2 >= 30 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_IME_ENTER : null, R.id.accessibilityActionImeEnter, null);
    }

    public b(Object obj, int i2, Class cls) {
        this.f2028a = obj == null ? new AccessibilityNodeInfo.AccessibilityAction(i2, null) : obj;
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof b)) {
            return false;
        }
        Object obj2 = ((b) obj).f2028a;
        Object obj3 = this.f2028a;
        if (obj3 == null) {
            return obj2 == null;
        }
        return obj3.equals(obj2);
    }

    public final int hashCode() {
        Object obj = this.f2028a;
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }
}
