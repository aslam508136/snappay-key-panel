package com.snapay.app;

import android.accessibilityservice.AccessibilityService;
import android.content.IntentFilter;
import android.view.accessibility.AccessibilityEvent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import d.w;
import java.util.HashMap;
import n0.a;
import n0.b;
import n0.c;

/* JADX INFO: loaded from: classes.dex */
public class SnapPayAccessibilityService extends AccessibilityService {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile boolean f565b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public HashMap f566c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public w f567d;

    @Override // android.accessibilityservice.AccessibilityService
    public final void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent == null || this.f565b) {
            return;
        }
        int eventType = accessibilityEvent.getEventType();
        if (eventType == 32 || eventType == 2048) {
            a aVar = (a) this.f566c.get(accessibilityEvent.getPackageName() != null ? accessibilityEvent.getPackageName().toString() : "");
            if (aVar != null) {
                aVar.a(accessibilityEvent, getRootInActiveWindow());
            }
        }
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        this.f566c = new HashMap();
        this.f566c.put("com.phonepe.app", new c(this, this));
        this.f566c.put("in.navi", new b());
        this.f567d = new w(this, 1);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.snapay.app.AUTOPAY_ACTIVATED");
        intentFilter.addAction("com.snapay.app.AUTOPAY_DEACTIVATED");
        LocalBroadcastManager.getInstance(this).registerReceiver(this.f567d, intentFilter);
    }

    @Override // android.app.Service
    public final void onDestroy() {
        super.onDestroy();
        if (this.f567d != null) {
            try {
                LocalBroadcastManager.getInstance(this).unregisterReceiver(this.f567d);
            } catch (IllegalArgumentException unused) {
            }
        }
    }

    @Override // android.accessibilityservice.AccessibilityService
    public final void onInterrupt() {
    }

    @Override // android.accessibilityservice.AccessibilityService
    public final void onServiceConnected() {
        super.onServiceConnected();
    }
}
