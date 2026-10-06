package q0;

import a0.i;
import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import java.util.Iterator;
import java.util.List;
import o0.g;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f1874a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AccessibilityService f1875b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final g f1876c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1878e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1879f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1880g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1881h = false;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1882i = false;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f1883j = true;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public long f1884k = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public long f1885l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f1886m = 0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f1887n = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Handler f1877d = new Handler(Looper.getMainLooper());

    public d(Context context, AccessibilityService accessibilityService) {
        this.f1874a = context;
        this.f1875b = accessibilityService;
        this.f1876c = g.a(context);
    }

    public static boolean b(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (accessibilityNodeInfo.isClickable() && accessibilityNodeInfo.isEnabled() && accessibilityNodeInfo.performAction(16)) {
            return true;
        }
        AccessibilityNodeInfo parent = accessibilityNodeInfo.getParent();
        while (parent != null) {
            if (parent.isClickable() && parent.isEnabled()) {
                boolean zPerformAction = parent.performAction(16);
                parent.recycle();
                return zPerformAction;
            }
            AccessibilityNodeInfo parent2 = parent.getParent();
            parent.recycle();
            parent = parent2;
        }
        return false;
    }

    public static AccessibilityNodeInfo c(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (accessibilityNodeInfo == null) {
            return null;
        }
        CharSequence text = accessibilityNodeInfo.getText();
        CharSequence contentDescription = accessibilityNodeInfo.getContentDescription();
        String lowerCase = text != null ? text.toString().toLowerCase() : "";
        String lowerCase2 = contentDescription != null ? contentDescription.toString().toLowerCase() : "";
        if ((lowerCase.startsWith("pay") && lowerCase.contains("₹")) || (lowerCase2.startsWith("pay") && lowerCase2.contains("₹"))) {
            return accessibilityNodeInfo;
        }
        int childCount = accessibilityNodeInfo.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            AccessibilityNodeInfo child = accessibilityNodeInfo.getChild(i2);
            if (child != null) {
                AccessibilityNodeInfo accessibilityNodeInfoC = c(child);
                child.recycle();
                if (accessibilityNodeInfoC != null) {
                    return accessibilityNodeInfoC;
                }
            }
        }
        return null;
    }

    public static void f(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            AccessibilityNodeInfo accessibilityNodeInfo = (AccessibilityNodeInfo) it.next();
            if (accessibilityNodeInfo != null) {
                accessibilityNodeInfo.recycle();
            }
        }
    }

    public static boolean g(AccessibilityNodeInfo accessibilityNodeInfo, String str) {
        if (accessibilityNodeInfo == null) {
            return false;
        }
        CharSequence[] charSequenceArr = {accessibilityNodeInfo.getText(), accessibilityNodeInfo.getContentDescription()};
        for (int i2 = 0; i2 < 2; i2++) {
            CharSequence charSequence = charSequenceArr[i2];
            if (charSequence != null && charSequence.toString().toLowerCase().contains(str) && i(accessibilityNodeInfo)) {
                return true;
            }
        }
        int childCount = accessibilityNodeInfo.getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            AccessibilityNodeInfo child = accessibilityNodeInfo.getChild(i3);
            if (child != null) {
                boolean zG = g(child, str);
                child.recycle();
                if (zG) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean i(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (accessibilityNodeInfo == null) {
            return false;
        }
        if (accessibilityNodeInfo.isClickable() && accessibilityNodeInfo.isEnabled() && accessibilityNodeInfo.performAction(16)) {
            return true;
        }
        AccessibilityNodeInfo parent = accessibilityNodeInfo.getParent();
        if (parent != null) {
            if (parent.isClickable() && parent.isEnabled() && parent.performAction(16)) {
                parent.recycle();
                return true;
            }
            AccessibilityNodeInfo parent2 = parent.getParent();
            if (parent2 != null) {
                if (parent2.isClickable() && parent2.isEnabled() && parent2.performAction(16)) {
                    parent2.recycle();
                    parent.recycle();
                    return true;
                }
                parent2.recycle();
            }
            parent.recycle();
        }
        return false;
    }

    public final void a(String str) {
        Intent intent = new Intent("com.snapay.app.BOT_STATUS");
        intent.putExtra("status", str);
        LocalBroadcastManager.getInstance(this.f1874a).sendBroadcast(intent);
    }

    public final void d(int i2, int i3) {
        Path path = new Path();
        path.moveTo(i2, i3);
        this.f1875b.dispatchGesture(i.d().addStroke(i.f(path)).build(), null, this.f1877d);
        e("Pay ₹ gesture tap at (" + i2 + ", " + i3 + ")");
    }

    public final void e(String str) {
        m0.a.f(this.f1874a).t("[UPI][All] " + str);
    }

    public final void h(String str, Runnable runnable) throws InterruptedException {
        AccessibilityService accessibilityService;
        StringBuilder sb;
        boolean zPerformAction;
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        Handler handler = this.f1877d;
        final int i2 = 0;
        handler.post(new Runnable(this) { // from class: q0.c

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ d f1872b;

            {
                this.f1872b = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                int i3 = i2;
                DisplayMetrics displayMetrics2 = displayMetrics;
                d dVar = this.f1872b;
                switch (i3) {
                    case 0:
                        ((WindowManager) dVar.f1875b.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics2);
                        break;
                    default:
                        ((WindowManager) dVar.f1875b.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics2);
                        break;
                }
            }
        });
        Thread.sleep(10L);
        int i3 = (int) (displayMetrics.heightPixels * 0.45f);
        int i4 = 0;
        while (true) {
            int length = str.length();
            accessibilityService = this.f1875b;
            if (i4 >= length) {
                break;
            }
            String strValueOf = String.valueOf(str.charAt(i4));
            boolean z2 = false;
            for (int i5 = 0; i5 < 10 && !z2; i5++) {
                AccessibilityNodeInfo rootInActiveWindow = accessibilityService.getRootInActiveWindow();
                if (rootInActiveWindow != null) {
                    List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByText = rootInActiveWindow.findAccessibilityNodeInfosByText(strValueOf);
                    rootInActiveWindow.recycle();
                    if (listFindAccessibilityNodeInfosByText != null) {
                        for (AccessibilityNodeInfo accessibilityNodeInfo : listFindAccessibilityNodeInfosByText) {
                            CharSequence text = accessibilityNodeInfo.getText();
                            if (text != null && text.toString().trim().equals(strValueOf)) {
                                Rect rect = new Rect();
                                accessibilityNodeInfo.getBoundsInScreen(rect);
                                if (rect.centerY() >= i3) {
                                    if (accessibilityNodeInfo.isClickable() && accessibilityNodeInfo.isEnabled()) {
                                        accessibilityNodeInfo.performAction(64);
                                        zPerformAction = accessibilityNodeInfo.performAction(16);
                                    } else {
                                        AccessibilityNodeInfo parent = accessibilityNodeInfo.getParent();
                                        while (true) {
                                            if (parent == null) {
                                                zPerformAction = false;
                                                break;
                                            }
                                            if (parent.isClickable() && parent.isEnabled()) {
                                                parent.performAction(64);
                                                boolean zPerformAction2 = parent.performAction(16);
                                                parent.recycle();
                                                zPerformAction = zPerformAction2;
                                                break;
                                            }
                                            AccessibilityNodeInfo parent2 = parent.getParent();
                                            parent.recycle();
                                            parent = parent2;
                                        }
                                    }
                                    z2 = zPerformAction;
                                }
                            }
                            accessibilityNodeInfo.recycle();
                            if (z2) {
                                break;
                            }
                        }
                    }
                }
                if (!z2) {
                    Thread.sleep(10L);
                }
            }
            if (z2) {
                sb = new StringBuilder("Tapped digit ");
                sb.append(strValueOf);
                e(sb.toString());
                int i6 = i4 + 1;
                Thread.sleep(i6 < str.length() && str.charAt(i6) == str.charAt(i4) ? 50L : 5L);
            } else {
                sb = new StringBuilder("Could not tap digit ");
                sb.append(strValueOf);
                e(sb.toString());
            }
            i4++;
        }
        final DisplayMetrics displayMetrics2 = new DisplayMetrics();
        final int i7 = 1;
        handler.post(new Runnable(this) { // from class: q0.c

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ d f1872b;

            {
                this.f1872b = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                int i8 = i7;
                DisplayMetrics displayMetrics3 = displayMetrics2;
                d dVar = this.f1872b;
                switch (i8) {
                    case 0:
                        ((WindowManager) dVar.f1875b.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics3);
                        break;
                    default:
                        ((WindowManager) dVar.f1875b.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics3);
                        break;
                }
            }
        });
        Thread.sleep(10L);
        int i8 = (int) (displayMetrics2.heightPixels * 0.45f);
        for (int i9 = 0; i9 < 6; i9++) {
            AccessibilityNodeInfo rootInActiveWindow2 = accessibilityService.getRootInActiveWindow();
            if (rootInActiveWindow2 != null) {
                List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByText2 = rootInActiveWindow2.findAccessibilityNodeInfosByText("Pay");
                if (listFindAccessibilityNodeInfosByText2 != null) {
                    for (AccessibilityNodeInfo accessibilityNodeInfo2 : listFindAccessibilityNodeInfosByText2) {
                        CharSequence text2 = accessibilityNodeInfo2.getText();
                        if (text2 != null && text2.toString().trim().equals("Pay")) {
                            Rect rect2 = new Rect();
                            accessibilityNodeInfo2.getBoundsInScreen(rect2);
                            if (rect2.centerY() >= i8) {
                                accessibilityNodeInfo2.performAction(16);
                                f(listFindAccessibilityNodeInfosByText2);
                                rootInActiveWindow2.recycle();
                                e("Clicked Pay button");
                                if (runnable != null) {
                                    handler.post(runnable);
                                    return;
                                }
                                return;
                            }
                        }
                        accessibilityNodeInfo2.recycle();
                    }
                    f(listFindAccessibilityNodeInfosByText2);
                }
                rootInActiveWindow2.recycle();
            }
            Thread.sleep(10L);
        }
        e("Pay button not found after retries");
    }
}
