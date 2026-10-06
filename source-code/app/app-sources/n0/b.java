package n0;

import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

/* JADX INFO: loaded from: classes.dex */
public final class b extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f1725a = false;

    @Override // n0.a
    public final void a(AccessibilityEvent accessibilityEvent, AccessibilityNodeInfo accessibilityNodeInfo) {
        if (!this.f1725a) {
            Log.d("NaviHandler", "Autopay not active, ignoring event");
        } else {
            if (accessibilityNodeInfo == null) {
                return;
            }
            Log.d("NaviHandler", "Handling Navi event: " + accessibilityEvent.getEventType());
        }
    }

    @Override // n0.a
    public final void b() {
        this.f1725a = true;
        Log.d("NaviHandler", "Navi autopay activated");
    }

    @Override // n0.a
    public final void c() {
        this.f1725a = false;
        Log.d("NaviHandler", "Navi autopay deactivated");
    }
}
