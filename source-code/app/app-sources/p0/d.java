package p0;

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
import com.snapay.app.SnapPayAccessibilityService;
import java.util.Iterator;
import java.util.List;
import m0.j;
import o0.g;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f1822a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AccessibilityService f1823b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final g f1824c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1826e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1827f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1828g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1829h = false;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1830i = false;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f1831j = false;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f1832k = true;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public long f1833l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f1834m = 0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f1835n = 0;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f1836o = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Handler f1825d = new Handler(Looper.getMainLooper());

    public d(Context context, AccessibilityService accessibilityService) {
        this.f1822a = context;
        this.f1823b = accessibilityService;
        this.f1824c = g.a(context);
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

    public static boolean d(AccessibilityNodeInfo accessibilityNodeInfo) {
        CharSequence contentDescription;
        if (accessibilityNodeInfo == null) {
            return false;
        }
        String string = accessibilityNodeInfo.getClassName() != null ? accessibilityNodeInfo.getClassName().toString() : "";
        if ((string.contains("ImageView") || string.contains("ImageButton") || string.contains("FloatingActionButton")) && (contentDescription = accessibilityNodeInfo.getContentDescription()) != null) {
            String lowerCase = contentDescription.toString().toLowerCase();
            if (lowerCase.contains("scan") || lowerCase.contains("qr") || lowerCase.contains("camera")) {
                if (accessibilityNodeInfo.isClickable() && accessibilityNodeInfo.isEnabled()) {
                    return accessibilityNodeInfo.performAction(16);
                }
                AccessibilityNodeInfo parent = accessibilityNodeInfo.getParent();
                if (parent != null && parent.isClickable() && parent.isEnabled()) {
                    boolean zPerformAction = parent.performAction(16);
                    parent.recycle();
                    return zPerformAction;
                }
            }
        }
        int childCount = accessibilityNodeInfo.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            AccessibilityNodeInfo child = accessibilityNodeInfo.getChild(i2);
            if (child != null) {
                boolean zD = d(child);
                child.recycle();
                if (zD) {
                    return true;
                }
            }
        }
        return false;
    }

    public static AccessibilityNodeInfo e(AccessibilityNodeInfo accessibilityNodeInfo) {
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
                AccessibilityNodeInfo accessibilityNodeInfoE = e(child);
                child.recycle();
                if (accessibilityNodeInfoE != null) {
                    return accessibilityNodeInfoE;
                }
            }
        }
        return null;
    }

    public static boolean h(AccessibilityNodeInfo accessibilityNodeInfo) {
        AccessibilityNodeInfo parent;
        if (accessibilityNodeInfo != null && (parent = accessibilityNodeInfo.getParent()) != null) {
            CharSequence text = parent.getText();
            CharSequence contentDescription = parent.getContentDescription();
            if ((text != null && text.toString().contains("Credit Card")) || (contentDescription != null && contentDescription.toString().contains("Credit Card"))) {
                parent.recycle();
                return true;
            }
            int childCount = parent.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                AccessibilityNodeInfo child = parent.getChild(i2);
                if (child != null) {
                    CharSequence text2 = child.getText();
                    if (text2 != null && text2.toString().contains("Credit Card")) {
                        child.recycle();
                        parent.recycle();
                        return true;
                    }
                    child.recycle();
                }
            }
            parent.recycle();
        }
        return false;
    }

    public static void j(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            AccessibilityNodeInfo accessibilityNodeInfo = (AccessibilityNodeInfo) it.next();
            if (accessibilityNodeInfo != null) {
                accessibilityNodeInfo.recycle();
            }
        }
    }

    public static boolean k(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (accessibilityNodeInfo == null) {
            return false;
        }
        CharSequence contentDescription = accessibilityNodeInfo.getContentDescription();
        if (contentDescription != null) {
            String lowerCase = contentDescription.toString().toLowerCase();
            if (lowerCase.contains("scan") || lowerCase.contains("qr")) {
                if (accessibilityNodeInfo.isClickable() && accessibilityNodeInfo.isEnabled()) {
                    return accessibilityNodeInfo.performAction(16);
                }
                AccessibilityNodeInfo parent = accessibilityNodeInfo.getParent();
                if (parent != null && parent.isClickable() && parent.isEnabled()) {
                    boolean zPerformAction = parent.performAction(16);
                    parent.recycle();
                    return zPerformAction;
                }
            }
        }
        int childCount = accessibilityNodeInfo.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            AccessibilityNodeInfo child = accessibilityNodeInfo.getChild(i2);
            if (child != null) {
                boolean zK = k(child);
                child.recycle();
                if (zK) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean l(AccessibilityNodeInfo accessibilityNodeInfo, String str) {
        if (accessibilityNodeInfo == null) {
            return false;
        }
        CharSequence text = accessibilityNodeInfo.getText();
        CharSequence contentDescription = accessibilityNodeInfo.getContentDescription();
        if (((text != null && text.toString().contains(str)) || (contentDescription != null && contentDescription.toString().contains(str))) && !h(accessibilityNodeInfo) && o(accessibilityNodeInfo)) {
            return true;
        }
        int childCount = accessibilityNodeInfo.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            AccessibilityNodeInfo child = accessibilityNodeInfo.getChild(i2);
            if (child != null) {
                boolean zL = l(child, str);
                child.recycle();
                if (zL) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean m(AccessibilityNodeInfo accessibilityNodeInfo, String str, String str2) {
        if (accessibilityNodeInfo == null) {
            return false;
        }
        CharSequence[] charSequenceArr = {accessibilityNodeInfo.getText(), accessibilityNodeInfo.getContentDescription()};
        for (int i2 = 0; i2 < 2; i2++) {
            CharSequence charSequence = charSequenceArr[i2];
            if (charSequence != null) {
                String lowerCase = charSequence.toString().toLowerCase();
                if (lowerCase.contains(str) && ((str2 == null || lowerCase.contains(str2)) && o(accessibilityNodeInfo))) {
                    return true;
                }
            }
        }
        int childCount = accessibilityNodeInfo.getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            AccessibilityNodeInfo child = accessibilityNodeInfo.getChild(i3);
            if (child != null) {
                boolean zM = m(child, str, str2);
                child.recycle();
                if (zM) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean o(AccessibilityNodeInfo accessibilityNodeInfo) {
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
        LocalBroadcastManager.getInstance(this.f1822a).sendBroadcast(intent);
    }

    public final long c() {
        if (this.f1833l > 0) {
            return System.currentTimeMillis() - this.f1833l;
        }
        return 0L;
    }

    public final void f(int i2, int i3) {
        Path path = new Path();
        path.moveTo(i2, i3);
        this.f1823b.dispatchGesture(i.d().addStroke(i.f(path)).build(), null, this.f1825d);
        i("Pay ₹ gesture tap at (" + i2 + ", " + i3 + ")");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:25:0x0049  */
    /* JADX WARN: Code duplicated, block: B:285:0x0401  */
    /* JADX WARN: Code duplicated, block: B:359:0x0540  */
    /* JADX WARN: Code duplicated, block: B:361:0x0545  */
    /* JADX WARN: Code duplicated, block: B:369:0x0559  */
    /* JADX WARN: Code duplicated, block: B:381:0x05a9  */
    /* JADX WARN: Code duplicated, block: B:384:0x05b2  */
    /* JADX WARN: Code duplicated, block: B:386:0x05b8  */
    /* JADX WARN: Code duplicated, block: B:388:0x05ea  */
    /* JADX WARN: Code duplicated, block: B:390:0x05f8  */
    /* JADX WARN: Code duplicated, block: B:391:0x05fa  */
    /* JADX WARN: Code duplicated, block: B:393:0x05fd  */
    /* JADX WARN: Code duplicated, block: B:398:0x0629  */
    /* JADX WARN: Code duplicated, block: B:410:0x0647  */
    /* JADX WARN: Code duplicated, block: B:413:0x0652  */
    /* JADX WARN: Code duplicated, block: B:415:0x0655  */
    /* JADX WARN: Code duplicated, block: B:417:0x06a7  */
    /* JADX WARN: Code duplicated, block: B:41:0x007b  */
    /* JADX WARN: Code duplicated, block: B:422:0x06bd  */
    /* JADX WARN: Code duplicated, block: B:44:0x0086  */
    /* JADX WARN: Code duplicated, block: B:456:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:47:0x008a  */
    /* JADX WARN: Code duplicated, block: B:49:0x0090  */
    /* JADX WARN: Code duplicated, block: B:51:0x0096 A[PHI: r0
  0x0096: PHI (r0v79 java.util.List<android.view.accessibility.AccessibilityNodeInfo>) = 
  (r0v70 java.util.List<android.view.accessibility.AccessibilityNodeInfo>)
  (r0v66 java.util.List<android.view.accessibility.AccessibilityNodeInfo>)
  (r0v65 java.util.List<android.view.accessibility.AccessibilityNodeInfo>)
 binds: [B:50:0x0094, B:37:0x0072, B:32:0x0065] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:52:0x0099  */
    /* JADX WARN: Code duplicated, block: B:55:0x009e  */
    /* JADX WARN: Code duplicated, block: B:59:0x00d6  */
    /* JADX WARN: Code duplicated, block: B:70:0x0100 A[EDGE_INSN: B:70:0x0100->B:71:0x0108 BREAK  A[LOOP:0: B:64:0x00e6->B:441:?]] */
    /* JADX WARN: Code duplicated, block: B:72:0x010a  */
    /* JADX WARN: Code duplicated, block: B:73:0x0135  */
    /* JADX WARN: Code duplicated, block: B:75:0x013b  */
    /* JADX WARN: Instruction removed from duplicated block: B:386:0x05b8, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:393:0x05fd, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:415:0x0655, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:72:0x010a, please report this as an issue */
    public final void g(AccessibilityNodeInfo accessibilityNodeInfo) {
        int i2;
        boolean zM;
        String str;
        List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByText;
        boolean z2;
        AccessibilityService accessibilityService;
        List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByText2;
        AccessibilityNodeInfo accessibilityNodeInfoE;
        boolean z3;
        Rect rect;
        String string;
        String str2;
        boolean zL;
        List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByText3;
        boolean zM2;
        List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByText4;
        List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByText5;
        boolean z4;
        boolean z5;
        if (accessibilityNodeInfo != null && this.f1824c.f1780b && "PAY_ALL".equals(this.f1824c.f1781c) && "QR_SCANNER".equals(this.f1824c.f1782d)) {
            if (this.f1826e || this.f1829h) {
                if (!this.f1826e && this.f1832k) {
                    this.f1832k = false;
                    listFindAccessibilityNodeInfosByText4 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Proceed To Pay");
                    if ((listFindAccessibilityNodeInfosByText4 != null || listFindAccessibilityNodeInfosByText4.isEmpty()) && ((listFindAccessibilityNodeInfosByText4 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Pay ₹")) == null || listFindAccessibilityNodeInfosByText4.isEmpty())) {
                        listFindAccessibilityNodeInfosByText5 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Enter your PIN");
                        if (listFindAccessibilityNodeInfosByText5 != null || listFindAccessibilityNodeInfosByText5.isEmpty()) {
                            z4 = false;
                        } else {
                            j(listFindAccessibilityNodeInfosByText5);
                            z4 = true;
                        }
                        if (z4) {
                            z5 = true;
                        } else {
                            listFindAccessibilityNodeInfosByText4 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Done");
                            if (listFindAccessibilityNodeInfosByText4 != null || listFindAccessibilityNodeInfosByText4.isEmpty()) {
                                z5 = false;
                            } else {
                                j(listFindAccessibilityNodeInfosByText4);
                                z5 = true;
                            }
                        }
                    } else {
                        j(listFindAccessibilityNodeInfosByText4);
                        z5 = true;
                    }
                    if (z5) {
                        Intent intent = new Intent("com.snapay.app.BOT_STATUS");
                        intent.putExtra("status", "ERROR:Wrong screen! Restart PhonePe & AutoPay Dubara Activate Kiiye");
                        Context context = this.f1822a;
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                        m0.d.b(context).e();
                        this.f1824c.f(false);
                        LocalBroadcastManager.getInstance(this.f1822a).sendBroadcast(new Intent("com.snapay.app.AUTOPAY_DEACTIVATED"));
                        return;
                    }
                }
                if (this.f1826e) {
                    i2 = 3;
                    if (this.f1826e || this.f1827f || this.f1828g || this.f1829h) {
                        if (this.f1826e && this.f1827f && !this.f1828g && !this.f1829h) {
                            listFindAccessibilityNodeInfosByText2 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Pay ₹");
                            if (listFindAccessibilityNodeInfosByText2 == null && !listFindAccessibilityNodeInfosByText2.isEmpty()) {
                                Iterator<AccessibilityNodeInfo> it = listFindAccessibilityNodeInfosByText2.iterator();
                                if (it.hasNext()) {
                                    AccessibilityNodeInfo next = it.next();
                                    CharSequence text = next.getText();
                                    if (text != null) {
                                        i("Pay amount button text: \"" + ((Object) text) + "\"");
                                    }
                                    Rect rect2 = new Rect();
                                    next.getBoundsInScreen(rect2);
                                    if (!b(next)) {
                                        f(rect2.centerX(), rect2.centerY());
                                    }
                                    j(listFindAccessibilityNodeInfosByText2);
                                } else {
                                    j(listFindAccessibilityNodeInfosByText2);
                                    if (m(accessibilityNodeInfo, "pay ₹", null)) {
                                        accessibilityNodeInfoE = e(accessibilityNodeInfo);
                                        if (accessibilityNodeInfoE != null) {
                                            i("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoE.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoE.getContentDescription()) + "\"");
                                            rect = new Rect();
                                            accessibilityNodeInfoE.getBoundsInScreen(rect);
                                            if (!b(accessibilityNodeInfoE)) {
                                                f(rect.centerX(), rect.centerY());
                                            }
                                            accessibilityNodeInfoE.recycle();
                                        } else {
                                            z3 = false;
                                        }
                                    }
                                }
                                z3 = true;
                            } else if (m(accessibilityNodeInfo, "pay ₹", null)) {
                                z3 = true;
                            } else {
                                accessibilityNodeInfoE = e(accessibilityNodeInfo);
                                if (accessibilityNodeInfoE != null) {
                                    i("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoE.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoE.getContentDescription()) + "\"");
                                    rect = new Rect();
                                    accessibilityNodeInfoE.getBoundsInScreen(rect);
                                    if (!b(accessibilityNodeInfoE)) {
                                        f(rect.centerX(), rect.centerY());
                                    }
                                    accessibilityNodeInfoE.recycle();
                                    z3 = true;
                                } else {
                                    z3 = false;
                                }
                            }
                            if (z3) {
                                this.f1828g = true;
                                this.f1835n = System.currentTimeMillis();
                                i("Pay ₹ button clicked (+" + c() + "ms since start)");
                                string = "Clicked Pay ₹";
                            }
                        }
                        if (this.f1826e && this.f1828g && !this.f1829h && !this.f1831j && (str = this.f1824c.f1784f) != null && !str.isEmpty()) {
                            listFindAccessibilityNodeInfosByText = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Enter your PIN");
                            if (listFindAccessibilityNodeInfosByText != null || listFindAccessibilityNodeInfosByText.isEmpty()) {
                                z2 = false;
                            } else {
                                j(listFindAccessibilityNodeInfosByText);
                                z2 = true;
                            }
                            if (z2) {
                                this.f1831j = true;
                                i("PIN screen detected (+" + c() + "ms) — entering " + str.length() + "-digit PIN");
                                a("Entering UPI PIN");
                                m0.g gVar = new m0.g(this, 4);
                                i("Entering PIN: " + str.length() + " digits");
                                accessibilityService = this.f1823b;
                                if (accessibilityService instanceof SnapPayAccessibilityService) {
                                    ((SnapPayAccessibilityService) accessibilityService).f565b = true;
                                }
                                new Thread(new j(this, str, gVar, i2)).start();
                                return;
                            }
                        }
                        if (this.f1829h || this.f1830i) {
                            return;
                        }
                        List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByText6 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Done");
                        if (listFindAccessibilityNodeInfosByText6 == null || listFindAccessibilityNodeInfosByText6.isEmpty()) {
                            zM = m(accessibilityNodeInfo, "done", null);
                        } else {
                            Iterator<AccessibilityNodeInfo> it2 = listFindAccessibilityNodeInfosByText6.iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    j(listFindAccessibilityNodeInfosByText6);
                                    zM = m(accessibilityNodeInfo, "done", null);
                                } else if (o(it2.next())) {
                                    j(listFindAccessibilityNodeInfosByText6);
                                    zM = true;
                                }
                            }
                        }
                        if (zM) {
                            this.f1830i = true;
                            i("Done clicked — cycle #" + this.f1836o + " complete (" + (System.currentTimeMillis() - this.f1833l) + "ms total)");
                            StringBuilder sb = new StringBuilder("Payment done #");
                            sb.append(this.f1836o);
                            a(sb.toString());
                            this.f1826e = false;
                            this.f1827f = false;
                            this.f1828g = false;
                            this.f1829h = false;
                            this.f1830i = false;
                            this.f1831j = false;
                            this.f1833l = 0L;
                            this.f1834m = 0L;
                            this.f1835n = 0L;
                            return;
                        }
                        return;
                    }
                    String str3 = this.f1824c.f1785g;
                    if ("NONE".equals(str3)) {
                        this.f1827f = true;
                        str2 = "No preferred bank set — skipping bank selection";
                    } else {
                        if ("OTHER".equals(str3)) {
                            str3 = this.f1824c.f1786h;
                        }
                        if (str3 == null || "NONE".equals(str3)) {
                            zL = false;
                        } else {
                            String upperCase = str3.toUpperCase();
                            upperCase.getClass();
                            String strConcat = "HSBC";
                            switch (upperCase) {
                                case "BARCLAYS":
                                    strConcat = "Barclays Bank";
                                    break;
                                case "SURYODAY":
                                    strConcat = "Suryoday Small Finance Bank";
                                    break;
                                case "EQUITAS":
                                    strConcat = "Equitas Small Finance Bank";
                                    break;
                                case "FEDERAL":
                                    strConcat = "Federal Bank";
                                    break;
                                case "FINCARE":
                                    strConcat = "Fincare Small Finance Bank";
                                    break;
                                case "AU":
                                    strConcat = "AU Small Finance Bank";
                                    break;
                                case "CB":
                                    strConcat = "Canara Bank";
                                    break;
                                case "DB":
                                    strConcat = "Deutsche Bank";
                                    break;
                                case "IB":
                                    strConcat = "Indian Bank";
                                    break;
                                case "APB":
                                    strConcat = "Airtel Payments Bank";
                                    break;
                                case "BOB":
                                    strConcat = "Bank of Baroda";
                                    break;
                                case "BOI":
                                    strConcat = "Bank of India";
                                    break;
                                case "BOM":
                                    strConcat = "Bank of Maharashtra";
                                    break;
                                case "CBI":
                                    strConcat = "Central Bank of India";
                                    break;
                                case "CSB":
                                    strConcat = "CSB Bank";
                                    break;
                                case "CUB":
                                    strConcat = "City Union Bank";
                                    break;
                                case "DCB":
                                    strConcat = "DCB Bank";
                                    break;
                                case "IOB":
                                    strConcat = "Indian Overseas Bank";
                                    break;
                                case "JIO":
                                    strConcat = "Jio Payments Bank";
                                    break;
                                case "KVB":
                                    strConcat = "Karur Vysya Bank";
                                    break;
                                case "PNB":
                                    strConcat = "Punjab National Bank";
                                    break;
                                case "PSB":
                                    strConcat = "Punjab & Sind Bank";
                                    break;
                                case "RBL":
                                    strConcat = "RBL Bank";
                                    break;
                                case "SBI":
                                    strConcat = "State Bank of India";
                                    break;
                                case "SCB":
                                    strConcat = "Standard Chartered Bank";
                                    break;
                                case "SIB":
                                    strConcat = "South Indian Bank";
                                    break;
                                case "TMB":
                                    strConcat = "Tamilnad Mercantile Bank";
                                    break;
                                case "UBI":
                                    strConcat = "Union Bank of India";
                                    break;
                                case "UCO":
                                    strConcat = "UCO Bank";
                                    break;
                                case "YES":
                                    strConcat = "Yes Bank";
                                    break;
                                case "AXIS":
                                    strConcat = "Axis Bank";
                                    break;
                                case "CITI":
                                    strConcat = "Citibank";
                                    break;
                                case "ESAF":
                                    strConcat = "ESAF Small Finance Bank";
                                    break;
                                case "FINO":
                                    strConcat = "Fino Payments Bank";
                                    break;
                                case "HDFC":
                                    strConcat = "HDFC Bank";
                                    break;
                                case "HSBC":
                                    break;
                                case "IDFC":
                                    strConcat = "IDFC First Bank";
                                    break;
                                case "IPPB":
                                    strConcat = "India Post Payments Bank";
                                    break;
                                case "JANA":
                                    strConcat = "Jana Small Finance Bank";
                                    break;
                                case "ICICI":
                                    strConcat = "ICICI Bank";
                                    break;
                                case "KOTAK":
                                    strConcat = "Kotak Mahindra Bank";
                                    break;
                                case "NESFB":
                                    strConcat = "North East Small Finance Bank";
                                    break;
                                case "PAYTM":
                                    strConcat = "Paytm Payments Bank";
                                    break;
                                case "UJJIVAN":
                                    strConcat = "Ujjivan Small Finance Bank";
                                    break;
                                case "BANDHAN":
                                    strConcat = "Bandhan Bank";
                                    break;
                                case "UTKARSH":
                                    strConcat = "Utkarsh Small Finance Bank";
                                    break;
                                case "INDUSIND":
                                    strConcat = "IndusInd Bank";
                                    break;
                                case "CAPITAL":
                                    strConcat = "Capital Small Finance Bank";
                                    break;
                                default:
                                    strConcat = str3.concat(" Bank");
                                    break;
                            }
                            List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByText7 = accessibilityNodeInfo.findAccessibilityNodeInfosByText(strConcat);
                            if (listFindAccessibilityNodeInfosByText7 == null || listFindAccessibilityNodeInfosByText7.isEmpty()) {
                                zL = l(accessibilityNodeInfo, strConcat);
                            } else {
                                Iterator<AccessibilityNodeInfo> it3 = listFindAccessibilityNodeInfosByText7.iterator();
                                while (true) {
                                    if (it3.hasNext()) {
                                        AccessibilityNodeInfo next2 = it3.next();
                                        CharSequence text2 = next2.getText();
                                        if (text2 != null && text2.toString().contains(strConcat) && !h(next2) && o(next2)) {
                                            j(listFindAccessibilityNodeInfosByText7);
                                            zL = true;
                                        }
                                    } else {
                                        j(listFindAccessibilityNodeInfosByText7);
                                        zL = l(accessibilityNodeInfo, strConcat);
                                    }
                                }
                            }
                        }
                        if (zL) {
                            this.f1827f = true;
                            i("Bank selected: " + str3 + " (+" + c() + "ms)");
                            StringBuilder sb2 = new StringBuilder("Selected bank: ");
                            sb2.append(str3);
                            string = sb2.toString();
                        } else {
                            str2 = "Waiting for bank sheet — looking for: " + str3;
                        }
                    }
                    i(str2);
                    if (this.f1826e) {
                        listFindAccessibilityNodeInfosByText2 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Pay ₹");
                        if (listFindAccessibilityNodeInfosByText2 == null) {
                            if (m(accessibilityNodeInfo, "pay ₹", null)) {
                                accessibilityNodeInfoE = e(accessibilityNodeInfo);
                                if (accessibilityNodeInfoE != null) {
                                    i("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoE.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoE.getContentDescription()) + "\"");
                                    rect = new Rect();
                                    accessibilityNodeInfoE.getBoundsInScreen(rect);
                                    if (!b(accessibilityNodeInfoE)) {
                                        f(rect.centerX(), rect.centerY());
                                    }
                                    accessibilityNodeInfoE.recycle();
                                    z3 = true;
                                } else {
                                    z3 = false;
                                }
                            } else {
                                z3 = true;
                            }
                        } else if (m(accessibilityNodeInfo, "pay ₹", null)) {
                            accessibilityNodeInfoE = e(accessibilityNodeInfo);
                            if (accessibilityNodeInfoE != null) {
                                i("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoE.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoE.getContentDescription()) + "\"");
                                rect = new Rect();
                                accessibilityNodeInfoE.getBoundsInScreen(rect);
                                if (!b(accessibilityNodeInfoE)) {
                                    f(rect.centerX(), rect.centerY());
                                }
                                accessibilityNodeInfoE.recycle();
                                z3 = true;
                            } else {
                                z3 = false;
                            }
                        } else {
                            z3 = true;
                        }
                        if (z3) {
                            this.f1828g = true;
                            this.f1835n = System.currentTimeMillis();
                            i("Pay ₹ button clicked (+" + c() + "ms since start)");
                            string = "Clicked Pay ₹";
                        }
                    }
                    if (this.f1826e) {
                        listFindAccessibilityNodeInfosByText = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Enter your PIN");
                        if (listFindAccessibilityNodeInfosByText != null) {
                            z2 = false;
                        } else {
                            z2 = false;
                        }
                        if (z2) {
                            this.f1831j = true;
                            i("PIN screen detected (+" + c() + "ms) — entering " + str.length() + "-digit PIN");
                            a("Entering UPI PIN");
                            m0.g gVar2 = new m0.g(this, 4);
                            i("Entering PIN: " + str.length() + " digits");
                            accessibilityService = this.f1823b;
                            if (accessibilityService instanceof SnapPayAccessibilityService) {
                                ((SnapPayAccessibilityService) accessibilityService).f565b = true;
                            }
                            new Thread(new j(this, str, gVar2, i2)).start();
                            return;
                        }
                    }
                    if (this.f1829h) {
                        return;
                    } else {
                        return;
                    }
                }
                listFindAccessibilityNodeInfosByText3 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Proceed To Pay");
                if (listFindAccessibilityNodeInfosByText3 != null && !listFindAccessibilityNodeInfosByText3.isEmpty()) {
                    Iterator<AccessibilityNodeInfo> it4 = listFindAccessibilityNodeInfosByText3.iterator();
                    while (true) {
                        if (!it4.hasNext()) {
                            j(listFindAccessibilityNodeInfosByText3);
                            zM2 = m(accessibilityNodeInfo, "proceed", "pay");
                            break;
                        } else if (o(it4.next())) {
                            j(listFindAccessibilityNodeInfosByText3);
                            zM2 = true;
                            break;
                        }
                    }
                } else {
                    zM2 = m(accessibilityNodeInfo, "proceed", "pay");
                    break;
                }
                if (zM2) {
                    i2 = 3;
                    if (this.f1826e) {
                        if (this.f1826e) {
                            listFindAccessibilityNodeInfosByText2 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Pay ₹");
                            if (listFindAccessibilityNodeInfosByText2 == null) {
                                if (m(accessibilityNodeInfo, "pay ₹", null)) {
                                    accessibilityNodeInfoE = e(accessibilityNodeInfo);
                                    if (accessibilityNodeInfoE != null) {
                                        i("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoE.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoE.getContentDescription()) + "\"");
                                        rect = new Rect();
                                        accessibilityNodeInfoE.getBoundsInScreen(rect);
                                        if (!b(accessibilityNodeInfoE)) {
                                            f(rect.centerX(), rect.centerY());
                                        }
                                        accessibilityNodeInfoE.recycle();
                                        z3 = true;
                                    } else {
                                        z3 = false;
                                    }
                                } else {
                                    z3 = true;
                                }
                            } else if (m(accessibilityNodeInfo, "pay ₹", null)) {
                                accessibilityNodeInfoE = e(accessibilityNodeInfo);
                                if (accessibilityNodeInfoE != null) {
                                    i("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoE.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoE.getContentDescription()) + "\"");
                                    rect = new Rect();
                                    accessibilityNodeInfoE.getBoundsInScreen(rect);
                                    if (!b(accessibilityNodeInfoE)) {
                                        f(rect.centerX(), rect.centerY());
                                    }
                                    accessibilityNodeInfoE.recycle();
                                    z3 = true;
                                } else {
                                    z3 = false;
                                }
                            } else {
                                z3 = true;
                            }
                            if (z3) {
                                this.f1828g = true;
                                this.f1835n = System.currentTimeMillis();
                                i("Pay ₹ button clicked (+" + c() + "ms since start)");
                                string = "Clicked Pay ₹";
                            }
                        }
                        if (this.f1826e) {
                            listFindAccessibilityNodeInfosByText = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Enter your PIN");
                            if (listFindAccessibilityNodeInfosByText != null) {
                                z2 = false;
                            } else {
                                z2 = false;
                            }
                            if (z2) {
                                this.f1831j = true;
                                i("PIN screen detected (+" + c() + "ms) — entering " + str.length() + "-digit PIN");
                                a("Entering UPI PIN");
                                m0.g gVar3 = new m0.g(this, 4);
                                i("Entering PIN: " + str.length() + " digits");
                                accessibilityService = this.f1823b;
                                if (accessibilityService instanceof SnapPayAccessibilityService) {
                                    ((SnapPayAccessibilityService) accessibilityService).f565b = true;
                                }
                                new Thread(new j(this, str, gVar3, i2)).start();
                                return;
                            }
                        }
                        if (this.f1829h) {
                            return;
                        } else {
                            return;
                        }
                    }
                    if (this.f1826e) {
                        listFindAccessibilityNodeInfosByText2 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Pay ₹");
                        if (listFindAccessibilityNodeInfosByText2 == null) {
                            if (m(accessibilityNodeInfo, "pay ₹", null)) {
                                accessibilityNodeInfoE = e(accessibilityNodeInfo);
                                if (accessibilityNodeInfoE != null) {
                                    i("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoE.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoE.getContentDescription()) + "\"");
                                    rect = new Rect();
                                    accessibilityNodeInfoE.getBoundsInScreen(rect);
                                    if (!b(accessibilityNodeInfoE)) {
                                        f(rect.centerX(), rect.centerY());
                                    }
                                    accessibilityNodeInfoE.recycle();
                                    z3 = true;
                                } else {
                                    z3 = false;
                                }
                            } else {
                                z3 = true;
                            }
                        } else if (m(accessibilityNodeInfo, "pay ₹", null)) {
                            accessibilityNodeInfoE = e(accessibilityNodeInfo);
                            if (accessibilityNodeInfoE != null) {
                                i("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoE.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoE.getContentDescription()) + "\"");
                                rect = new Rect();
                                accessibilityNodeInfoE.getBoundsInScreen(rect);
                                if (!b(accessibilityNodeInfoE)) {
                                    f(rect.centerX(), rect.centerY());
                                }
                                accessibilityNodeInfoE.recycle();
                                z3 = true;
                            } else {
                                z3 = false;
                            }
                        } else {
                            z3 = true;
                        }
                        if (z3) {
                            this.f1828g = true;
                            this.f1835n = System.currentTimeMillis();
                            i("Pay ₹ button clicked (+" + c() + "ms since start)");
                            string = "Clicked Pay ₹";
                        }
                    }
                    if (this.f1826e) {
                        listFindAccessibilityNodeInfosByText = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Enter your PIN");
                        if (listFindAccessibilityNodeInfosByText != null) {
                            z2 = false;
                        } else {
                            z2 = false;
                        }
                        if (z2) {
                            this.f1831j = true;
                            i("PIN screen detected (+" + c() + "ms) — entering " + str.length() + "-digit PIN");
                            a("Entering UPI PIN");
                            m0.g gVar4 = new m0.g(this, 4);
                            i("Entering PIN: " + str.length() + " digits");
                            accessibilityService = this.f1823b;
                            if (accessibilityService instanceof SnapPayAccessibilityService) {
                                ((SnapPayAccessibilityService) accessibilityService).f565b = true;
                            }
                            new Thread(new j(this, str, gVar4, i2)).start();
                            return;
                        }
                    }
                    if (this.f1829h) {
                        return;
                    } else {
                        return;
                    }
                }
                this.f1826e = true;
                long jCurrentTimeMillis = System.currentTimeMillis();
                this.f1833l = jCurrentTimeMillis;
                this.f1834m = jCurrentTimeMillis;
                this.f1836o++;
                i("Proceed To Pay clicked — cycle #" + this.f1836o + " started");
                string = "Clicked Proceed To Pay";
            } else {
                if (k(accessibilityNodeInfo) || d(accessibilityNodeInfo)) {
                    i("QR scanner opened");
                    string = "Opening QR scanner";
                } else {
                    if (!this.f1826e) {
                        this.f1832k = false;
                        listFindAccessibilityNodeInfosByText4 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Proceed To Pay");
                        if (listFindAccessibilityNodeInfosByText4 != null) {
                            listFindAccessibilityNodeInfosByText5 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Enter your PIN");
                            if (listFindAccessibilityNodeInfosByText5 != null) {
                                z4 = false;
                            } else {
                                z4 = false;
                            }
                            if (z4) {
                                listFindAccessibilityNodeInfosByText4 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Done");
                                if (listFindAccessibilityNodeInfosByText4 != null) {
                                }
                                z5 = false;
                            } else {
                                z5 = true;
                            }
                        } else {
                            listFindAccessibilityNodeInfosByText5 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Enter your PIN");
                            if (listFindAccessibilityNodeInfosByText5 != null) {
                                z4 = false;
                            } else {
                                z4 = false;
                            }
                            if (z4) {
                                listFindAccessibilityNodeInfosByText4 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Done");
                                if (listFindAccessibilityNodeInfosByText4 != null) {
                                }
                                z5 = false;
                            } else {
                                z5 = true;
                            }
                        }
                        if (z5) {
                            Intent intent2 = new Intent("com.snapay.app.BOT_STATUS");
                            intent2.putExtra("status", "ERROR:Wrong screen! Restart PhonePe & AutoPay Dubara Activate Kiiye");
                            Context context2 = this.f1822a;
                            LocalBroadcastManager.getInstance(context2).sendBroadcast(intent2);
                            m0.d.b(context2).e();
                            this.f1824c.f(false);
                            LocalBroadcastManager.getInstance(this.f1822a).sendBroadcast(new Intent("com.snapay.app.AUTOPAY_DEACTIVATED"));
                            return;
                        }
                    }
                    if (this.f1826e) {
                        i2 = 3;
                        if (this.f1826e) {
                            if (this.f1826e) {
                                listFindAccessibilityNodeInfosByText2 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Pay ₹");
                                if (listFindAccessibilityNodeInfosByText2 == null) {
                                    if (m(accessibilityNodeInfo, "pay ₹", null)) {
                                        accessibilityNodeInfoE = e(accessibilityNodeInfo);
                                        if (accessibilityNodeInfoE != null) {
                                            i("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoE.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoE.getContentDescription()) + "\"");
                                            rect = new Rect();
                                            accessibilityNodeInfoE.getBoundsInScreen(rect);
                                            if (!b(accessibilityNodeInfoE)) {
                                                f(rect.centerX(), rect.centerY());
                                            }
                                            accessibilityNodeInfoE.recycle();
                                            z3 = true;
                                        } else {
                                            z3 = false;
                                        }
                                    } else {
                                        z3 = true;
                                    }
                                } else if (m(accessibilityNodeInfo, "pay ₹", null)) {
                                    accessibilityNodeInfoE = e(accessibilityNodeInfo);
                                    if (accessibilityNodeInfoE != null) {
                                        i("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoE.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoE.getContentDescription()) + "\"");
                                        rect = new Rect();
                                        accessibilityNodeInfoE.getBoundsInScreen(rect);
                                        if (!b(accessibilityNodeInfoE)) {
                                            f(rect.centerX(), rect.centerY());
                                        }
                                        accessibilityNodeInfoE.recycle();
                                        z3 = true;
                                    } else {
                                        z3 = false;
                                    }
                                } else {
                                    z3 = true;
                                }
                                if (z3) {
                                    this.f1828g = true;
                                    this.f1835n = System.currentTimeMillis();
                                    i("Pay ₹ button clicked (+" + c() + "ms since start)");
                                    string = "Clicked Pay ₹";
                                }
                            }
                            if (this.f1826e) {
                                listFindAccessibilityNodeInfosByText = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Enter your PIN");
                                if (listFindAccessibilityNodeInfosByText != null) {
                                    z2 = false;
                                } else {
                                    z2 = false;
                                }
                                if (z2) {
                                    this.f1831j = true;
                                    i("PIN screen detected (+" + c() + "ms) — entering " + str.length() + "-digit PIN");
                                    a("Entering UPI PIN");
                                    m0.g gVar5 = new m0.g(this, 4);
                                    i("Entering PIN: " + str.length() + " digits");
                                    accessibilityService = this.f1823b;
                                    if (accessibilityService instanceof SnapPayAccessibilityService) {
                                        ((SnapPayAccessibilityService) accessibilityService).f565b = true;
                                    }
                                    new Thread(new j(this, str, gVar5, i2)).start();
                                    return;
                                }
                            }
                            if (this.f1829h) {
                                return;
                            } else {
                                return;
                            }
                        }
                        if (this.f1826e) {
                            listFindAccessibilityNodeInfosByText2 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Pay ₹");
                            if (listFindAccessibilityNodeInfosByText2 == null) {
                                if (m(accessibilityNodeInfo, "pay ₹", null)) {
                                    accessibilityNodeInfoE = e(accessibilityNodeInfo);
                                    if (accessibilityNodeInfoE != null) {
                                        i("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoE.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoE.getContentDescription()) + "\"");
                                        rect = new Rect();
                                        accessibilityNodeInfoE.getBoundsInScreen(rect);
                                        if (!b(accessibilityNodeInfoE)) {
                                            f(rect.centerX(), rect.centerY());
                                        }
                                        accessibilityNodeInfoE.recycle();
                                        z3 = true;
                                    } else {
                                        z3 = false;
                                    }
                                } else {
                                    z3 = true;
                                }
                            } else if (m(accessibilityNodeInfo, "pay ₹", null)) {
                                accessibilityNodeInfoE = e(accessibilityNodeInfo);
                                if (accessibilityNodeInfoE != null) {
                                    i("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoE.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoE.getContentDescription()) + "\"");
                                    rect = new Rect();
                                    accessibilityNodeInfoE.getBoundsInScreen(rect);
                                    if (!b(accessibilityNodeInfoE)) {
                                        f(rect.centerX(), rect.centerY());
                                    }
                                    accessibilityNodeInfoE.recycle();
                                    z3 = true;
                                } else {
                                    z3 = false;
                                }
                            } else {
                                z3 = true;
                            }
                            if (z3) {
                                this.f1828g = true;
                                this.f1835n = System.currentTimeMillis();
                                i("Pay ₹ button clicked (+" + c() + "ms since start)");
                                string = "Clicked Pay ₹";
                            }
                        }
                        if (this.f1826e) {
                            listFindAccessibilityNodeInfosByText = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Enter your PIN");
                            if (listFindAccessibilityNodeInfosByText != null) {
                                z2 = false;
                            } else {
                                z2 = false;
                            }
                            if (z2) {
                                this.f1831j = true;
                                i("PIN screen detected (+" + c() + "ms) — entering " + str.length() + "-digit PIN");
                                a("Entering UPI PIN");
                                m0.g gVar6 = new m0.g(this, 4);
                                i("Entering PIN: " + str.length() + " digits");
                                accessibilityService = this.f1823b;
                                if (accessibilityService instanceof SnapPayAccessibilityService) {
                                    ((SnapPayAccessibilityService) accessibilityService).f565b = true;
                                }
                                new Thread(new j(this, str, gVar6, i2)).start();
                                return;
                            }
                        }
                        if (this.f1829h) {
                            return;
                        } else {
                            return;
                        }
                    }
                    listFindAccessibilityNodeInfosByText3 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Proceed To Pay");
                    if (listFindAccessibilityNodeInfosByText3 != null) {
                        zM2 = m(accessibilityNodeInfo, "proceed", "pay");
                        break;
                    } else {
                        zM2 = m(accessibilityNodeInfo, "proceed", "pay");
                        break;
                    }
                    if (zM2) {
                        i2 = 3;
                        if (this.f1826e) {
                            if (this.f1826e) {
                                listFindAccessibilityNodeInfosByText2 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Pay ₹");
                                if (listFindAccessibilityNodeInfosByText2 == null) {
                                    if (m(accessibilityNodeInfo, "pay ₹", null)) {
                                        accessibilityNodeInfoE = e(accessibilityNodeInfo);
                                        if (accessibilityNodeInfoE != null) {
                                            i("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoE.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoE.getContentDescription()) + "\"");
                                            rect = new Rect();
                                            accessibilityNodeInfoE.getBoundsInScreen(rect);
                                            if (!b(accessibilityNodeInfoE)) {
                                                f(rect.centerX(), rect.centerY());
                                            }
                                            accessibilityNodeInfoE.recycle();
                                            z3 = true;
                                        } else {
                                            z3 = false;
                                        }
                                    } else {
                                        z3 = true;
                                    }
                                } else if (m(accessibilityNodeInfo, "pay ₹", null)) {
                                    accessibilityNodeInfoE = e(accessibilityNodeInfo);
                                    if (accessibilityNodeInfoE != null) {
                                        i("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoE.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoE.getContentDescription()) + "\"");
                                        rect = new Rect();
                                        accessibilityNodeInfoE.getBoundsInScreen(rect);
                                        if (!b(accessibilityNodeInfoE)) {
                                            f(rect.centerX(), rect.centerY());
                                        }
                                        accessibilityNodeInfoE.recycle();
                                        z3 = true;
                                    } else {
                                        z3 = false;
                                    }
                                } else {
                                    z3 = true;
                                }
                                if (z3) {
                                    this.f1828g = true;
                                    this.f1835n = System.currentTimeMillis();
                                    i("Pay ₹ button clicked (+" + c() + "ms since start)");
                                    string = "Clicked Pay ₹";
                                }
                            }
                            if (this.f1826e) {
                                listFindAccessibilityNodeInfosByText = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Enter your PIN");
                                if (listFindAccessibilityNodeInfosByText != null) {
                                    z2 = false;
                                } else {
                                    z2 = false;
                                }
                                if (z2) {
                                    this.f1831j = true;
                                    i("PIN screen detected (+" + c() + "ms) — entering " + str.length() + "-digit PIN");
                                    a("Entering UPI PIN");
                                    m0.g gVar7 = new m0.g(this, 4);
                                    i("Entering PIN: " + str.length() + " digits");
                                    accessibilityService = this.f1823b;
                                    if (accessibilityService instanceof SnapPayAccessibilityService) {
                                        ((SnapPayAccessibilityService) accessibilityService).f565b = true;
                                    }
                                    new Thread(new j(this, str, gVar7, i2)).start();
                                    return;
                                }
                            }
                            if (this.f1829h) {
                                return;
                            } else {
                                return;
                            }
                        }
                        if (this.f1826e) {
                            listFindAccessibilityNodeInfosByText2 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Pay ₹");
                            if (listFindAccessibilityNodeInfosByText2 == null) {
                                if (m(accessibilityNodeInfo, "pay ₹", null)) {
                                    accessibilityNodeInfoE = e(accessibilityNodeInfo);
                                    if (accessibilityNodeInfoE != null) {
                                        i("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoE.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoE.getContentDescription()) + "\"");
                                        rect = new Rect();
                                        accessibilityNodeInfoE.getBoundsInScreen(rect);
                                        if (!b(accessibilityNodeInfoE)) {
                                            f(rect.centerX(), rect.centerY());
                                        }
                                        accessibilityNodeInfoE.recycle();
                                        z3 = true;
                                    } else {
                                        z3 = false;
                                    }
                                } else {
                                    z3 = true;
                                }
                            } else if (m(accessibilityNodeInfo, "pay ₹", null)) {
                                accessibilityNodeInfoE = e(accessibilityNodeInfo);
                                if (accessibilityNodeInfoE != null) {
                                    i("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoE.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoE.getContentDescription()) + "\"");
                                    rect = new Rect();
                                    accessibilityNodeInfoE.getBoundsInScreen(rect);
                                    if (!b(accessibilityNodeInfoE)) {
                                        f(rect.centerX(), rect.centerY());
                                    }
                                    accessibilityNodeInfoE.recycle();
                                    z3 = true;
                                } else {
                                    z3 = false;
                                }
                            } else {
                                z3 = true;
                            }
                            if (z3) {
                                this.f1828g = true;
                                this.f1835n = System.currentTimeMillis();
                                i("Pay ₹ button clicked (+" + c() + "ms since start)");
                                string = "Clicked Pay ₹";
                            }
                        }
                        if (this.f1826e) {
                            listFindAccessibilityNodeInfosByText = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Enter your PIN");
                            if (listFindAccessibilityNodeInfosByText != null) {
                                z2 = false;
                            } else {
                                z2 = false;
                            }
                            if (z2) {
                                this.f1831j = true;
                                i("PIN screen detected (+" + c() + "ms) — entering " + str.length() + "-digit PIN");
                                a("Entering UPI PIN");
                                m0.g gVar8 = new m0.g(this, 4);
                                i("Entering PIN: " + str.length() + " digits");
                                accessibilityService = this.f1823b;
                                if (accessibilityService instanceof SnapPayAccessibilityService) {
                                    ((SnapPayAccessibilityService) accessibilityService).f565b = true;
                                }
                                new Thread(new j(this, str, gVar8, i2)).start();
                                return;
                            }
                        }
                        if (this.f1829h) {
                            return;
                        } else {
                            return;
                        }
                    }
                    this.f1826e = true;
                    long jCurrentTimeMillis2 = System.currentTimeMillis();
                    this.f1833l = jCurrentTimeMillis2;
                    this.f1834m = jCurrentTimeMillis2;
                    this.f1836o++;
                    i("Proceed To Pay clicked — cycle #" + this.f1836o + " started");
                    string = "Clicked Proceed To Pay";
                }
            }
            a(string);
        }
    }

    public final void i(String str) {
        m0.a.f(this.f1822a).t("[QR][All] " + str);
    }

    public final void n(String str, Runnable runnable) throws InterruptedException {
        AccessibilityService accessibilityService;
        StringBuilder sb;
        boolean zPerformAction;
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        Handler handler = this.f1825d;
        final int i2 = 0;
        handler.post(new Runnable(this) { // from class: p0.c

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ d f1820b;

            {
                this.f1820b = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                int i3 = i2;
                DisplayMetrics displayMetrics2 = displayMetrics;
                d dVar = this.f1820b;
                switch (i3) {
                    case 0:
                        ((WindowManager) dVar.f1823b.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics2);
                        break;
                    default:
                        ((WindowManager) dVar.f1823b.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics2);
                        break;
                }
            }
        });
        Thread.sleep(10L);
        int i3 = (int) (displayMetrics.heightPixels * 0.45f);
        int i4 = 0;
        while (true) {
            int length = str.length();
            accessibilityService = this.f1823b;
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
                i(sb.toString());
                int i6 = i4 + 1;
                Thread.sleep(i6 < str.length() && str.charAt(i6) == str.charAt(i4) ? 50L : 5L);
            } else {
                sb = new StringBuilder("Could not tap digit ");
                sb.append(strValueOf);
                i(sb.toString());
            }
            i4++;
        }
        final DisplayMetrics displayMetrics2 = new DisplayMetrics();
        final int i7 = 1;
        handler.post(new Runnable(this) { // from class: p0.c

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ d f1820b;

            {
                this.f1820b = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                int i8 = i7;
                DisplayMetrics displayMetrics3 = displayMetrics2;
                d dVar = this.f1820b;
                switch (i8) {
                    case 0:
                        ((WindowManager) dVar.f1823b.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics3);
                        break;
                    default:
                        ((WindowManager) dVar.f1823b.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics3);
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
                                j(listFindAccessibilityNodeInfosByText2);
                                rootInActiveWindow2.recycle();
                                i("Clicked Pay button");
                                if (runnable != null) {
                                    handler.post(runnable);
                                    return;
                                }
                                return;
                            }
                        }
                        accessibilityNodeInfo2.recycle();
                    }
                    j(listFindAccessibilityNodeInfosByText2);
                }
                rootInActiveWindow2.recycle();
            }
            Thread.sleep(10L);
        }
        i("Pay button not found after retries");
    }
}
