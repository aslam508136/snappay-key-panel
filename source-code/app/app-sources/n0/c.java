package n0;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.snapay.app.SnapPayAccessibilityService;
import java.util.Iterator;
import java.util.List;
import m0.j;
import o0.g;
import q0.d;

/* JADX INFO: loaded from: classes.dex */
public final class c extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g f1726a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f1727b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final q0.b f1728c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final d f1729d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final p0.b f1730e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final p0.d f1731f;

    public c(Context context, AccessibilityService accessibilityService) {
        this.f1726a = g.a(context);
        this.f1728c = new q0.b(context, accessibilityService);
        this.f1729d = new d(context, accessibilityService);
        this.f1730e = new p0.b(context, accessibilityService);
        this.f1731f = new p0.d(context, accessibilityService);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:101:0x0222  */
    /* JADX WARN: Code duplicated, block: B:102:0x022b  */
    /* JADX WARN: Code duplicated, block: B:107:0x0247  */
    /* JADX WARN: Code duplicated, block: B:119:0x0265  */
    /* JADX WARN: Code duplicated, block: B:122:0x0270  */
    /* JADX WARN: Code duplicated, block: B:124:0x0273  */
    /* JADX WARN: Code duplicated, block: B:126:0x0285  */
    /* JADX WARN: Code duplicated, block: B:127:0x028e  */
    /* JADX WARN: Code duplicated, block: B:130:0x02d6  */
    /* JADX WARN: Code duplicated, block: B:134:0x02ef  */
    /* JADX WARN: Code duplicated, block: B:188:0x03bf A[PHI: r0
  0x03bf: PHI (r0v219 java.util.List<android.view.accessibility.AccessibilityNodeInfo>) = 
  (r0v209 java.util.List<android.view.accessibility.AccessibilityNodeInfo>)
  (r0v205 java.util.List<android.view.accessibility.AccessibilityNodeInfo>)
  (r0v204 java.util.List<android.view.accessibility.AccessibilityNodeInfo>)
 binds: [B:187:0x03bd, B:174:0x039b, B:169:0x038e] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:193:0x03f7  */
    /* JADX WARN: Code duplicated, block: B:195:0x03fb  */
    /* JADX WARN: Code duplicated, block: B:206:0x0425 A[EDGE_INSN: B:206:0x0425->B:207:0x0429 BREAK  A[LOOP:2: B:200:0x040b->B:735:?]] */
    /* JADX WARN: Code duplicated, block: B:208:0x042b  */
    /* JADX WARN: Code duplicated, block: B:209:0x0457  */
    /* JADX WARN: Code duplicated, block: B:211:0x045b  */
    /* JADX WARN: Code duplicated, block: B:215:0x0465  */
    /* JADX WARN: Code duplicated, block: B:227:0x04b3  */
    /* JADX WARN: Code duplicated, block: B:230:0x04ba  */
    /* JADX WARN: Code duplicated, block: B:232:0x04c0  */
    /* JADX WARN: Code duplicated, block: B:234:0x04f2  */
    /* JADX WARN: Code duplicated, block: B:236:0x0500  */
    /* JADX WARN: Code duplicated, block: B:237:0x0502  */
    /* JADX WARN: Code duplicated, block: B:239:0x0505  */
    /* JADX WARN: Code duplicated, block: B:241:0x051d  */
    /* JADX WARN: Code duplicated, block: B:242:0x0526  */
    /* JADX WARN: Code duplicated, block: B:247:0x0542  */
    /* JADX WARN: Code duplicated, block: B:272:0x05e6  */
    /* JADX WARN: Code duplicated, block: B:274:0x05ea  */
    /* JADX WARN: Code duplicated, block: B:278:0x05f4  */
    /* JADX WARN: Code duplicated, block: B:287:0x0618 A[EDGE_INSN: B:287:0x0618->B:288:0x061e BREAK  A[LOOP:3: B:281:0x05fe->B:738:?]] */
    /* JADX WARN: Code duplicated, block: B:289:0x0620  */
    /* JADX WARN: Code duplicated, block: B:339:0x06f4 A[PHI: r7
  0x06f4: PHI (r7v22 java.util.List<android.view.accessibility.AccessibilityNodeInfo>) = 
  (r7v17 java.util.List<android.view.accessibility.AccessibilityNodeInfo>)
  (r7v13 java.util.List<android.view.accessibility.AccessibilityNodeInfo>)
  (r7v12 java.util.List<android.view.accessibility.AccessibilityNodeInfo>)
 binds: [B:338:0x06f2, B:325:0x06d0, B:320:0x06c3] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:361:0x0763  */
    /* JADX WARN: Code duplicated, block: B:48:0x00ee A[PHI: r4
  0x00ee: PHI (r4v85 java.util.List<android.view.accessibility.AccessibilityNodeInfo>) = 
  (r4v80 java.util.List<android.view.accessibility.AccessibilityNodeInfo>)
  (r4v76 java.util.List<android.view.accessibility.AccessibilityNodeInfo>)
  (r4v75 java.util.List<android.view.accessibility.AccessibilityNodeInfo>)
 binds: [B:47:0x00ec, B:34:0x00ca, B:29:0x00bd] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:53:0x0110  */
    /* JADX WARN: Code duplicated, block: B:55:0x0114  */
    /* JADX WARN: Code duplicated, block: B:573:0x0a2f  */
    /* JADX WARN: Code duplicated, block: B:646:0x0b72  */
    /* JADX WARN: Code duplicated, block: B:648:0x0b77  */
    /* JADX WARN: Code duplicated, block: B:654:0x0b85  */
    /* JADX WARN: Code duplicated, block: B:666:0x0bd3  */
    /* JADX WARN: Code duplicated, block: B:669:0x0bda  */
    /* JADX WARN: Code duplicated, block: B:66:0x013e A[EDGE_INSN: B:66:0x013e->B:67:0x0142 BREAK  A[LOOP:0: B:60:0x0124->B:729:?]] */
    /* JADX WARN: Code duplicated, block: B:671:0x0be0  */
    /* JADX WARN: Code duplicated, block: B:673:0x0c10  */
    /* JADX WARN: Code duplicated, block: B:675:0x0c1e  */
    /* JADX WARN: Code duplicated, block: B:676:0x0c20  */
    /* JADX WARN: Code duplicated, block: B:678:0x0c23  */
    /* JADX WARN: Code duplicated, block: B:682:0x0c51  */
    /* JADX WARN: Code duplicated, block: B:68:0x0144  */
    /* JADX WARN: Code duplicated, block: B:69:0x0158  */
    /* JADX WARN: Code duplicated, block: B:703:0x0ce3  */
    /* JADX WARN: Code duplicated, block: B:705:0x0ce7  */
    /* JADX WARN: Code duplicated, block: B:709:0x0cf1  */
    /* JADX WARN: Code duplicated, block: B:718:0x0d15 A[EDGE_INSN: B:718:0x0d15->B:719:0x0d1b BREAK  A[LOOP:6: B:712:0x0cfb->B:754:?]] */
    /* JADX WARN: Code duplicated, block: B:71:0x015c  */
    /* JADX WARN: Code duplicated, block: B:720:0x0d1d  */
    /* JADX WARN: Code duplicated, block: B:758:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:75:0x0166  */
    /* JADX WARN: Code duplicated, block: B:87:0x01b6  */
    /* JADX WARN: Code duplicated, block: B:90:0x01bf  */
    /* JADX WARN: Code duplicated, block: B:92:0x01c5  */
    /* JADX WARN: Code duplicated, block: B:94:0x01f7  */
    /* JADX WARN: Code duplicated, block: B:96:0x0205  */
    /* JADX WARN: Code duplicated, block: B:97:0x0207  */
    /* JADX WARN: Code duplicated, block: B:99:0x020a  */
    /* JADX WARN: Instruction removed from duplicated block: B:208:0x042b, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:232:0x04c0, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:289:0x0620, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:671:0x0be0, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:678:0x0c23, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:92:0x01c5, please report this as an issue */
    @Override // n0.a
    public final void a(AccessibilityEvent accessibilityEvent, AccessibilityNodeInfo accessibilityNodeInfo) {
        List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByText;
        boolean zM;
        String str;
        boolean z2;
        List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByText2;
        AccessibilityNodeInfo accessibilityNodeInfoE;
        boolean z3;
        Rect rect;
        boolean zL;
        boolean zM2;
        boolean z4;
        boolean z5;
        List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByText3;
        boolean zG;
        String str2;
        boolean z6;
        List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByText4;
        AccessibilityNodeInfo accessibilityNodeInfoC;
        boolean z7;
        Rect rect2;
        long jCurrentTimeMillis;
        String str3;
        List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByText5;
        boolean zG2;
        boolean z8;
        boolean z9;
        boolean zI;
        String str4;
        List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByText6;
        boolean z10;
        long jCurrentTimeMillis2;
        AccessibilityService accessibilityService;
        List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByText7;
        AccessibilityNodeInfo accessibilityNodeInfoD;
        boolean z11;
        Rect rect3;
        long jCurrentTimeMillis3;
        String str5;
        List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByText8;
        boolean zI2;
        boolean z12;
        boolean z13;
        if (this.f1727b && accessibilityNodeInfo != null) {
            String str6 = this.f1726a.f1782d;
            String str7 = this.f1726a.f1781c;
            String string = "Clicked Pay ₹";
            if ("UPI_AUTOPAY".equals(str6)) {
                if ("ONE_TIME".equals(str7)) {
                    q0.b bVar = this.f1728c;
                    if (bVar.f1860c.f1780b && !bVar.f1866i && "ONE_TIME".equals(bVar.f1860c.f1781c) && "UPI_AUTOPAY".equals(bVar.f1860c.f1782d)) {
                        if (bVar.f1862e || !bVar.f1867j) {
                            if (bVar.f1862e) {
                                if (bVar.f1862e && !bVar.f1863f) {
                                    listFindAccessibilityNodeInfosByText7 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Pay ₹");
                                    if (listFindAccessibilityNodeInfosByText7 == null && !listFindAccessibilityNodeInfosByText7.isEmpty()) {
                                        Iterator<AccessibilityNodeInfo> it = listFindAccessibilityNodeInfosByText7.iterator();
                                        if (it.hasNext()) {
                                            AccessibilityNodeInfo next = it.next();
                                            CharSequence text = next.getText();
                                            if (text != null) {
                                                bVar.f("Pay amount button text: \"" + ((Object) text) + "\"");
                                            }
                                            Rect rect4 = new Rect();
                                            next.getBoundsInScreen(rect4);
                                            if (!q0.b.b(next)) {
                                                bVar.e(rect4.centerX(), rect4.centerY());
                                            }
                                            q0.b.g(listFindAccessibilityNodeInfosByText7);
                                        } else {
                                            q0.b.g(listFindAccessibilityNodeInfosByText7);
                                            if (q0.b.i(accessibilityNodeInfo, "pay ₹")) {
                                                accessibilityNodeInfoD = q0.b.d(accessibilityNodeInfo);
                                                if (accessibilityNodeInfoD != null) {
                                                    bVar.f("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoD.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoD.getContentDescription()) + "\"");
                                                    rect3 = new Rect();
                                                    accessibilityNodeInfoD.getBoundsInScreen(rect3);
                                                    if (!q0.b.b(accessibilityNodeInfoD)) {
                                                        bVar.e(rect3.centerX(), rect3.centerY());
                                                    }
                                                    accessibilityNodeInfoD.recycle();
                                                } else {
                                                    z11 = false;
                                                }
                                            }
                                        }
                                        z11 = true;
                                    } else if (q0.b.i(accessibilityNodeInfo, "pay ₹")) {
                                        z11 = true;
                                    } else {
                                        accessibilityNodeInfoD = q0.b.d(accessibilityNodeInfo);
                                        if (accessibilityNodeInfoD != null) {
                                            bVar.f("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoD.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoD.getContentDescription()) + "\"");
                                            rect3 = new Rect();
                                            accessibilityNodeInfoD.getBoundsInScreen(rect3);
                                            if (!q0.b.b(accessibilityNodeInfoD)) {
                                                bVar.e(rect3.centerX(), rect3.centerY());
                                            }
                                            accessibilityNodeInfoD.recycle();
                                            z11 = true;
                                        } else {
                                            z11 = false;
                                        }
                                    }
                                    if (z11) {
                                        bVar.f1863f = true;
                                        bVar.f1870m = System.currentTimeMillis();
                                        StringBuilder sb = new StringBuilder("Pay ₹ button clicked (+");
                                        if (bVar.f1868k > 0) {
                                            jCurrentTimeMillis3 = System.currentTimeMillis() - bVar.f1868k;
                                        } else {
                                            jCurrentTimeMillis3 = 0;
                                        }
                                        sb.append(jCurrentTimeMillis3);
                                        sb.append("ms since start)");
                                        bVar.f(sb.toString());
                                        str5 = "Clicked Pay ₹";
                                    }
                                }
                                if (bVar.f1862e && bVar.f1863f && !bVar.f1864g && !bVar.f1865h && (str4 = bVar.f1860c.f1783e) != null && !str4.isEmpty()) {
                                    listFindAccessibilityNodeInfosByText6 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Enter your PIN");
                                    if (listFindAccessibilityNodeInfosByText6 != null || listFindAccessibilityNodeInfosByText6.isEmpty()) {
                                        z10 = false;
                                    } else {
                                        q0.b.g(listFindAccessibilityNodeInfosByText6);
                                        z10 = true;
                                    }
                                    if (z10) {
                                        bVar.f1865h = true;
                                        StringBuilder sb2 = new StringBuilder("PIN screen detected (+");
                                        if (bVar.f1868k > 0) {
                                            jCurrentTimeMillis2 = System.currentTimeMillis() - bVar.f1868k;
                                        } else {
                                            jCurrentTimeMillis2 = 0;
                                        }
                                        sb2.append(jCurrentTimeMillis2);
                                        sb2.append("ms) — entering ");
                                        sb2.append(str4.length());
                                        sb2.append("-digit PIN");
                                        bVar.f(sb2.toString());
                                        bVar.a("Entering UPI PIN");
                                        m0.g gVar = new m0.g(bVar, 5);
                                        bVar.f("Entering PIN: " + str4.length() + " digits");
                                        accessibilityService = bVar.f1859b;
                                        if (accessibilityService instanceof SnapPayAccessibilityService) {
                                            ((SnapPayAccessibilityService) accessibilityService).f565b = true;
                                        }
                                        new Thread(new j(bVar, str4, gVar, 4)).start();
                                        return;
                                    }
                                }
                                if (bVar.f1864g || bVar.f1866i) {
                                    return;
                                }
                                List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByText9 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Done");
                                if (listFindAccessibilityNodeInfosByText9 != null && !listFindAccessibilityNodeInfosByText9.isEmpty()) {
                                    Iterator<AccessibilityNodeInfo> it2 = listFindAccessibilityNodeInfosByText9.iterator();
                                    while (true) {
                                        if (!it2.hasNext()) {
                                            q0.b.g(listFindAccessibilityNodeInfosByText9);
                                            zI = q0.b.i(accessibilityNodeInfo, "done");
                                            break;
                                        } else if (q0.b.k(it2.next())) {
                                            q0.b.g(listFindAccessibilityNodeInfosByText9);
                                            zI = true;
                                            break;
                                        }
                                    }
                                } else {
                                    zI = q0.b.i(accessibilityNodeInfo, "done");
                                    break;
                                }
                                if (!zI) {
                                    return;
                                }
                                bVar.f1866i = true;
                                bVar.f("Done clicked — deactivating autopay (one-time mode)");
                                bVar.a("Payment done");
                            } else {
                                listFindAccessibilityNodeInfosByText8 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Pay Now");
                                if (listFindAccessibilityNodeInfosByText8 != null && !listFindAccessibilityNodeInfosByText8.isEmpty()) {
                                    Iterator<AccessibilityNodeInfo> it3 = listFindAccessibilityNodeInfosByText8.iterator();
                                    while (true) {
                                        if (!it3.hasNext()) {
                                            q0.b.g(listFindAccessibilityNodeInfosByText8);
                                            zI2 = q0.b.i(accessibilityNodeInfo, "pay now");
                                            break;
                                        } else if (q0.b.k(it3.next())) {
                                            q0.b.g(listFindAccessibilityNodeInfosByText8);
                                            zI2 = true;
                                            break;
                                        }
                                    }
                                } else {
                                    zI2 = q0.b.i(accessibilityNodeInfo, "pay now");
                                    break;
                                }
                                if (zI2) {
                                    if (bVar.f1862e) {
                                        listFindAccessibilityNodeInfosByText7 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Pay ₹");
                                        if (listFindAccessibilityNodeInfosByText7 == null) {
                                            if (q0.b.i(accessibilityNodeInfo, "pay ₹")) {
                                                accessibilityNodeInfoD = q0.b.d(accessibilityNodeInfo);
                                                if (accessibilityNodeInfoD != null) {
                                                    bVar.f("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoD.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoD.getContentDescription()) + "\"");
                                                    rect3 = new Rect();
                                                    accessibilityNodeInfoD.getBoundsInScreen(rect3);
                                                    if (!q0.b.b(accessibilityNodeInfoD)) {
                                                        bVar.e(rect3.centerX(), rect3.centerY());
                                                    }
                                                    accessibilityNodeInfoD.recycle();
                                                    z11 = true;
                                                } else {
                                                    z11 = false;
                                                }
                                            } else {
                                                z11 = true;
                                            }
                                        } else if (q0.b.i(accessibilityNodeInfo, "pay ₹")) {
                                            accessibilityNodeInfoD = q0.b.d(accessibilityNodeInfo);
                                            if (accessibilityNodeInfoD != null) {
                                                bVar.f("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoD.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoD.getContentDescription()) + "\"");
                                                rect3 = new Rect();
                                                accessibilityNodeInfoD.getBoundsInScreen(rect3);
                                                if (!q0.b.b(accessibilityNodeInfoD)) {
                                                    bVar.e(rect3.centerX(), rect3.centerY());
                                                }
                                                accessibilityNodeInfoD.recycle();
                                                z11 = true;
                                            } else {
                                                z11 = false;
                                            }
                                        } else {
                                            z11 = true;
                                        }
                                        if (z11) {
                                            bVar.f1863f = true;
                                            bVar.f1870m = System.currentTimeMillis();
                                            StringBuilder sb3 = new StringBuilder("Pay ₹ button clicked (+");
                                            if (bVar.f1868k > 0) {
                                                jCurrentTimeMillis3 = System.currentTimeMillis() - bVar.f1868k;
                                            } else {
                                                jCurrentTimeMillis3 = 0;
                                            }
                                            sb3.append(jCurrentTimeMillis3);
                                            sb3.append("ms since start)");
                                            bVar.f(sb3.toString());
                                            str5 = "Clicked Pay ₹";
                                        }
                                    }
                                    if (bVar.f1862e) {
                                        listFindAccessibilityNodeInfosByText6 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Enter your PIN");
                                        if (listFindAccessibilityNodeInfosByText6 != null) {
                                            z10 = false;
                                        } else {
                                            z10 = false;
                                        }
                                        if (z10) {
                                            bVar.f1865h = true;
                                            StringBuilder sb4 = new StringBuilder("PIN screen detected (+");
                                            if (bVar.f1868k > 0) {
                                                jCurrentTimeMillis2 = System.currentTimeMillis() - bVar.f1868k;
                                            } else {
                                                jCurrentTimeMillis2 = 0;
                                            }
                                            sb4.append(jCurrentTimeMillis2);
                                            sb4.append("ms) — entering ");
                                            sb4.append(str4.length());
                                            sb4.append("-digit PIN");
                                            bVar.f(sb4.toString());
                                            bVar.a("Entering UPI PIN");
                                            m0.g gVar2 = new m0.g(bVar, 5);
                                            bVar.f("Entering PIN: " + str4.length() + " digits");
                                            accessibilityService = bVar.f1859b;
                                            if (accessibilityService instanceof SnapPayAccessibilityService) {
                                                ((SnapPayAccessibilityService) accessibilityService).f565b = true;
                                            }
                                            new Thread(new j(bVar, str4, gVar2, 4)).start();
                                            return;
                                        }
                                    }
                                    if (bVar.f1864g) {
                                        return;
                                    } else {
                                        return;
                                    }
                                }
                                bVar.f1862e = true;
                                long jCurrentTimeMillis4 = System.currentTimeMillis();
                                bVar.f1868k = jCurrentTimeMillis4;
                                bVar.f1869l = jCurrentTimeMillis4;
                                bVar.f("Pay Now clicked — payment flow started");
                                str5 = "Clicked Pay Now";
                            }
                            bVar.a(str5);
                            return;
                        }
                        bVar.f1867j = false;
                        List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByText10 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Pay Now");
                        if ((listFindAccessibilityNodeInfosByText10 == null || listFindAccessibilityNodeInfosByText10.isEmpty()) && ((listFindAccessibilityNodeInfosByText10 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Pay ₹")) == null || listFindAccessibilityNodeInfosByText10.isEmpty())) {
                            List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByText11 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Enter your PIN");
                            if (listFindAccessibilityNodeInfosByText11 == null || listFindAccessibilityNodeInfosByText11.isEmpty()) {
                                z12 = false;
                            } else {
                                q0.b.g(listFindAccessibilityNodeInfosByText11);
                                z12 = true;
                            }
                            if (z12) {
                                z13 = true;
                            } else {
                                listFindAccessibilityNodeInfosByText10 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Done");
                                if (listFindAccessibilityNodeInfosByText10 == null || listFindAccessibilityNodeInfosByText10.isEmpty()) {
                                    z13 = false;
                                } else {
                                    q0.b.g(listFindAccessibilityNodeInfosByText10);
                                    z13 = true;
                                }
                            }
                        } else {
                            q0.b.g(listFindAccessibilityNodeInfosByText10);
                            z13 = true;
                        }
                        if (!z13) {
                            if (bVar.f1862e) {
                                if (bVar.f1862e) {
                                    listFindAccessibilityNodeInfosByText7 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Pay ₹");
                                    if (listFindAccessibilityNodeInfosByText7 == null) {
                                        if (q0.b.i(accessibilityNodeInfo, "pay ₹")) {
                                            accessibilityNodeInfoD = q0.b.d(accessibilityNodeInfo);
                                            if (accessibilityNodeInfoD != null) {
                                                bVar.f("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoD.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoD.getContentDescription()) + "\"");
                                                rect3 = new Rect();
                                                accessibilityNodeInfoD.getBoundsInScreen(rect3);
                                                if (!q0.b.b(accessibilityNodeInfoD)) {
                                                    bVar.e(rect3.centerX(), rect3.centerY());
                                                }
                                                accessibilityNodeInfoD.recycle();
                                                z11 = true;
                                            } else {
                                                z11 = false;
                                            }
                                        } else {
                                            z11 = true;
                                        }
                                    } else if (q0.b.i(accessibilityNodeInfo, "pay ₹")) {
                                        accessibilityNodeInfoD = q0.b.d(accessibilityNodeInfo);
                                        if (accessibilityNodeInfoD != null) {
                                            bVar.f("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoD.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoD.getContentDescription()) + "\"");
                                            rect3 = new Rect();
                                            accessibilityNodeInfoD.getBoundsInScreen(rect3);
                                            if (!q0.b.b(accessibilityNodeInfoD)) {
                                                bVar.e(rect3.centerX(), rect3.centerY());
                                            }
                                            accessibilityNodeInfoD.recycle();
                                            z11 = true;
                                        } else {
                                            z11 = false;
                                        }
                                    } else {
                                        z11 = true;
                                    }
                                    if (z11) {
                                        bVar.f1863f = true;
                                        bVar.f1870m = System.currentTimeMillis();
                                        StringBuilder sb5 = new StringBuilder("Pay ₹ button clicked (+");
                                        if (bVar.f1868k > 0) {
                                            jCurrentTimeMillis3 = System.currentTimeMillis() - bVar.f1868k;
                                        } else {
                                            jCurrentTimeMillis3 = 0;
                                        }
                                        sb5.append(jCurrentTimeMillis3);
                                        sb5.append("ms since start)");
                                        bVar.f(sb5.toString());
                                        str5 = "Clicked Pay ₹";
                                    }
                                }
                                if (bVar.f1862e) {
                                    listFindAccessibilityNodeInfosByText6 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Enter your PIN");
                                    if (listFindAccessibilityNodeInfosByText6 != null) {
                                        z10 = false;
                                    } else {
                                        z10 = false;
                                    }
                                    if (z10) {
                                        bVar.f1865h = true;
                                        StringBuilder sb6 = new StringBuilder("PIN screen detected (+");
                                        if (bVar.f1868k > 0) {
                                            jCurrentTimeMillis2 = System.currentTimeMillis() - bVar.f1868k;
                                        } else {
                                            jCurrentTimeMillis2 = 0;
                                        }
                                        sb6.append(jCurrentTimeMillis2);
                                        sb6.append("ms) — entering ");
                                        sb6.append(str4.length());
                                        sb6.append("-digit PIN");
                                        bVar.f(sb6.toString());
                                        bVar.a("Entering UPI PIN");
                                        m0.g gVar3 = new m0.g(bVar, 5);
                                        bVar.f("Entering PIN: " + str4.length() + " digits");
                                        accessibilityService = bVar.f1859b;
                                        if (accessibilityService instanceof SnapPayAccessibilityService) {
                                            ((SnapPayAccessibilityService) accessibilityService).f565b = true;
                                        }
                                        new Thread(new j(bVar, str4, gVar3, 4)).start();
                                        return;
                                    }
                                }
                                if (bVar.f1864g) {
                                    return;
                                } else {
                                    return;
                                }
                            }
                            listFindAccessibilityNodeInfosByText8 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Pay Now");
                            if (listFindAccessibilityNodeInfosByText8 != null) {
                                zI2 = q0.b.i(accessibilityNodeInfo, "pay now");
                                break;
                            } else {
                                zI2 = q0.b.i(accessibilityNodeInfo, "pay now");
                                break;
                            }
                            if (zI2) {
                                if (bVar.f1862e) {
                                    listFindAccessibilityNodeInfosByText7 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Pay ₹");
                                    if (listFindAccessibilityNodeInfosByText7 == null) {
                                        if (q0.b.i(accessibilityNodeInfo, "pay ₹")) {
                                            accessibilityNodeInfoD = q0.b.d(accessibilityNodeInfo);
                                            if (accessibilityNodeInfoD != null) {
                                                bVar.f("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoD.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoD.getContentDescription()) + "\"");
                                                rect3 = new Rect();
                                                accessibilityNodeInfoD.getBoundsInScreen(rect3);
                                                if (!q0.b.b(accessibilityNodeInfoD)) {
                                                    bVar.e(rect3.centerX(), rect3.centerY());
                                                }
                                                accessibilityNodeInfoD.recycle();
                                                z11 = true;
                                            } else {
                                                z11 = false;
                                            }
                                        } else {
                                            z11 = true;
                                        }
                                    } else if (q0.b.i(accessibilityNodeInfo, "pay ₹")) {
                                        accessibilityNodeInfoD = q0.b.d(accessibilityNodeInfo);
                                        if (accessibilityNodeInfoD != null) {
                                            bVar.f("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoD.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoD.getContentDescription()) + "\"");
                                            rect3 = new Rect();
                                            accessibilityNodeInfoD.getBoundsInScreen(rect3);
                                            if (!q0.b.b(accessibilityNodeInfoD)) {
                                                bVar.e(rect3.centerX(), rect3.centerY());
                                            }
                                            accessibilityNodeInfoD.recycle();
                                            z11 = true;
                                        } else {
                                            z11 = false;
                                        }
                                    } else {
                                        z11 = true;
                                    }
                                    if (z11) {
                                        bVar.f1863f = true;
                                        bVar.f1870m = System.currentTimeMillis();
                                        StringBuilder sb7 = new StringBuilder("Pay ₹ button clicked (+");
                                        if (bVar.f1868k > 0) {
                                            jCurrentTimeMillis3 = System.currentTimeMillis() - bVar.f1868k;
                                        } else {
                                            jCurrentTimeMillis3 = 0;
                                        }
                                        sb7.append(jCurrentTimeMillis3);
                                        sb7.append("ms since start)");
                                        bVar.f(sb7.toString());
                                        str5 = "Clicked Pay ₹";
                                    }
                                }
                                if (bVar.f1862e) {
                                    listFindAccessibilityNodeInfosByText6 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Enter your PIN");
                                    if (listFindAccessibilityNodeInfosByText6 != null) {
                                        z10 = false;
                                    } else {
                                        z10 = false;
                                    }
                                    if (z10) {
                                        bVar.f1865h = true;
                                        StringBuilder sb8 = new StringBuilder("PIN screen detected (+");
                                        if (bVar.f1868k > 0) {
                                            jCurrentTimeMillis2 = System.currentTimeMillis() - bVar.f1868k;
                                        } else {
                                            jCurrentTimeMillis2 = 0;
                                        }
                                        sb8.append(jCurrentTimeMillis2);
                                        sb8.append("ms) — entering ");
                                        sb8.append(str4.length());
                                        sb8.append("-digit PIN");
                                        bVar.f(sb8.toString());
                                        bVar.a("Entering UPI PIN");
                                        m0.g gVar4 = new m0.g(bVar, 5);
                                        bVar.f("Entering PIN: " + str4.length() + " digits");
                                        accessibilityService = bVar.f1859b;
                                        if (accessibilityService instanceof SnapPayAccessibilityService) {
                                            ((SnapPayAccessibilityService) accessibilityService).f565b = true;
                                        }
                                        new Thread(new j(bVar, str4, gVar4, 4)).start();
                                        return;
                                    }
                                }
                                if (bVar.f1864g) {
                                    return;
                                } else {
                                    return;
                                }
                            }
                            bVar.f1862e = true;
                            long jCurrentTimeMillis5 = System.currentTimeMillis();
                            bVar.f1868k = jCurrentTimeMillis5;
                            bVar.f1869l = jCurrentTimeMillis5;
                            bVar.f("Pay Now clicked — payment flow started");
                            str5 = "Clicked Pay Now";
                            bVar.a(str5);
                            return;
                        }
                        Intent intent = new Intent("com.snapay.app.BOT_STATUS");
                        intent.putExtra("status", "ERROR:Wrong screen! Restart PhonePe & AutoPay Dubara Activate Kiiye");
                        Context context = bVar.f1858a;
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                        m0.d.b(context).e();
                        bVar.c();
                        return;
                    }
                    return;
                }
                if ("PAY_ALL".equals(str7)) {
                    d dVar = this.f1729d;
                    if (dVar.f1876c.f1780b && "PAY_ALL".equals(dVar.f1876c.f1781c) && "UPI_AUTOPAY".equals(dVar.f1876c.f1782d)) {
                        if (!dVar.f1878e && dVar.f1883j) {
                            dVar.f1883j = false;
                            List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByText12 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Pay Now");
                            if ((listFindAccessibilityNodeInfosByText12 == null || listFindAccessibilityNodeInfosByText12.isEmpty()) && ((listFindAccessibilityNodeInfosByText12 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Pay ₹")) == null || listFindAccessibilityNodeInfosByText12.isEmpty())) {
                                List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByText13 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Enter your PIN");
                                if (listFindAccessibilityNodeInfosByText13 == null || listFindAccessibilityNodeInfosByText13.isEmpty()) {
                                    z8 = false;
                                } else {
                                    d.f(listFindAccessibilityNodeInfosByText13);
                                    z8 = true;
                                }
                                if (z8) {
                                    z9 = true;
                                } else {
                                    listFindAccessibilityNodeInfosByText12 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Done");
                                    if (listFindAccessibilityNodeInfosByText12 == null || listFindAccessibilityNodeInfosByText12.isEmpty()) {
                                        z9 = false;
                                    } else {
                                        d.f(listFindAccessibilityNodeInfosByText12);
                                        z9 = true;
                                    }
                                }
                            } else {
                                d.f(listFindAccessibilityNodeInfosByText12);
                                z9 = true;
                            }
                            if (z9) {
                                Intent intent2 = new Intent("com.snapay.app.BOT_STATUS");
                                intent2.putExtra("status", "ERROR:Wrong screen! Restart PhonePe & AutoPay Dubara Activate Kiiye");
                                Context context2 = dVar.f1874a;
                                LocalBroadcastManager.getInstance(context2).sendBroadcast(intent2);
                                m0.d.b(context2).e();
                                dVar.f1876c.f(false);
                                LocalBroadcastManager.getInstance(dVar.f1874a).sendBroadcast(new Intent("com.snapay.app.AUTOPAY_DEACTIVATED"));
                            } else if (dVar.f1878e) {
                                if (dVar.f1878e) {
                                    listFindAccessibilityNodeInfosByText4 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Pay ₹");
                                    if (listFindAccessibilityNodeInfosByText4 == null) {
                                        if (d.g(accessibilityNodeInfo, "pay ₹")) {
                                            z7 = true;
                                        } else {
                                            accessibilityNodeInfoC = d.c(accessibilityNodeInfo);
                                            if (accessibilityNodeInfoC != null) {
                                                dVar.e("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoC.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoC.getContentDescription()) + "\"");
                                                rect2 = new Rect();
                                                accessibilityNodeInfoC.getBoundsInScreen(rect2);
                                                if (!d.b(accessibilityNodeInfoC)) {
                                                    dVar.d(rect2.centerX(), rect2.centerY());
                                                }
                                                accessibilityNodeInfoC.recycle();
                                                z7 = true;
                                            } else {
                                                z7 = false;
                                            }
                                        }
                                    } else if (d.g(accessibilityNodeInfo, "pay ₹")) {
                                        accessibilityNodeInfoC = d.c(accessibilityNodeInfo);
                                        if (accessibilityNodeInfoC != null) {
                                            dVar.e("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoC.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoC.getContentDescription()) + "\"");
                                            rect2 = new Rect();
                                            accessibilityNodeInfoC.getBoundsInScreen(rect2);
                                            if (!d.b(accessibilityNodeInfoC)) {
                                                dVar.d(rect2.centerX(), rect2.centerY());
                                            }
                                            accessibilityNodeInfoC.recycle();
                                            z7 = true;
                                        } else {
                                            z7 = false;
                                        }
                                    } else {
                                        z7 = true;
                                    }
                                    if (z7) {
                                        dVar.f1879f = true;
                                        dVar.f1886m = System.currentTimeMillis();
                                        StringBuilder sb9 = new StringBuilder("Pay ₹ button clicked (+");
                                        if (dVar.f1884k > 0) {
                                            jCurrentTimeMillis = System.currentTimeMillis() - dVar.f1884k;
                                        } else {
                                            jCurrentTimeMillis = 0;
                                        }
                                        sb9.append(jCurrentTimeMillis);
                                        sb9.append("ms)");
                                        dVar.e(sb9.toString());
                                        str3 = "Clicked Pay ₹";
                                        dVar.a(str3);
                                    }
                                }
                                if (!dVar.f1878e) {
                                    if (dVar.f1880g) {
                                        listFindAccessibilityNodeInfosByText3 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Done");
                                        if (listFindAccessibilityNodeInfosByText3 != null) {
                                            zG = d.g(accessibilityNodeInfo, "done");
                                            break;
                                        } else {
                                            zG = d.g(accessibilityNodeInfo, "done");
                                            break;
                                        }
                                        if (zG) {
                                            dVar.f1882i = true;
                                            dVar.e("Done clicked — resetting for cycle #" + dVar.f1887n);
                                            dVar.a("Clicked Done");
                                            dVar.f1878e = false;
                                            dVar.f1879f = false;
                                            dVar.f1880g = false;
                                            dVar.f1881h = false;
                                            dVar.f1882i = false;
                                            dVar.f1884k = 0L;
                                            dVar.f1885l = 0L;
                                            dVar.f1886m = 0L;
                                        }
                                    }
                                } else if (dVar.f1880g) {
                                    listFindAccessibilityNodeInfosByText3 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Done");
                                    if (listFindAccessibilityNodeInfosByText3 != null) {
                                        zG = d.g(accessibilityNodeInfo, "done");
                                        break;
                                    } else {
                                        zG = d.g(accessibilityNodeInfo, "done");
                                        break;
                                    }
                                    if (zG) {
                                        dVar.f1882i = true;
                                        dVar.e("Done clicked — resetting for cycle #" + dVar.f1887n);
                                        dVar.a("Clicked Done");
                                        dVar.f1878e = false;
                                        dVar.f1879f = false;
                                        dVar.f1880g = false;
                                        dVar.f1881h = false;
                                        dVar.f1882i = false;
                                        dVar.f1884k = 0L;
                                        dVar.f1885l = 0L;
                                        dVar.f1886m = 0L;
                                    }
                                }
                            } else {
                                listFindAccessibilityNodeInfosByText5 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Pay Now");
                                if (listFindAccessibilityNodeInfosByText5 != null) {
                                    zG2 = d.g(accessibilityNodeInfo, "pay now");
                                    break;
                                } else {
                                    zG2 = d.g(accessibilityNodeInfo, "pay now");
                                    break;
                                }
                                if (zG2) {
                                    dVar.f1878e = true;
                                    long jCurrentTimeMillis6 = System.currentTimeMillis();
                                    dVar.f1884k = jCurrentTimeMillis6;
                                    dVar.f1885l = jCurrentTimeMillis6;
                                    dVar.f1887n++;
                                    dVar.e("Pay Now clicked — cycle #" + dVar.f1887n + " started");
                                    str3 = "Clicked Pay Now";
                                } else {
                                    if (dVar.f1878e) {
                                        listFindAccessibilityNodeInfosByText4 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Pay ₹");
                                        if (listFindAccessibilityNodeInfosByText4 == null) {
                                            if (d.g(accessibilityNodeInfo, "pay ₹")) {
                                                accessibilityNodeInfoC = d.c(accessibilityNodeInfo);
                                                if (accessibilityNodeInfoC != null) {
                                                    dVar.e("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoC.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoC.getContentDescription()) + "\"");
                                                    rect2 = new Rect();
                                                    accessibilityNodeInfoC.getBoundsInScreen(rect2);
                                                    if (!d.b(accessibilityNodeInfoC)) {
                                                        dVar.d(rect2.centerX(), rect2.centerY());
                                                    }
                                                    accessibilityNodeInfoC.recycle();
                                                    z7 = true;
                                                } else {
                                                    z7 = false;
                                                }
                                            } else {
                                                z7 = true;
                                            }
                                        } else if (d.g(accessibilityNodeInfo, "pay ₹")) {
                                            accessibilityNodeInfoC = d.c(accessibilityNodeInfo);
                                            if (accessibilityNodeInfoC != null) {
                                                dVar.e("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoC.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoC.getContentDescription()) + "\"");
                                                rect2 = new Rect();
                                                accessibilityNodeInfoC.getBoundsInScreen(rect2);
                                                if (!d.b(accessibilityNodeInfoC)) {
                                                    dVar.d(rect2.centerX(), rect2.centerY());
                                                }
                                                accessibilityNodeInfoC.recycle();
                                                z7 = true;
                                            } else {
                                                z7 = false;
                                            }
                                        } else {
                                            z7 = true;
                                        }
                                        if (z7) {
                                            dVar.f1879f = true;
                                            dVar.f1886m = System.currentTimeMillis();
                                            StringBuilder sb10 = new StringBuilder("Pay ₹ button clicked (+");
                                            if (dVar.f1884k > 0) {
                                                jCurrentTimeMillis = System.currentTimeMillis() - dVar.f1884k;
                                            } else {
                                                jCurrentTimeMillis = 0;
                                            }
                                            sb10.append(jCurrentTimeMillis);
                                            sb10.append("ms)");
                                            dVar.e(sb10.toString());
                                            str3 = "Clicked Pay ₹";
                                        }
                                    }
                                    if (!dVar.f1878e) {
                                        if (dVar.f1880g) {
                                            listFindAccessibilityNodeInfosByText3 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Done");
                                            if (listFindAccessibilityNodeInfosByText3 != null) {
                                                zG = d.g(accessibilityNodeInfo, "done");
                                                break;
                                            } else {
                                                zG = d.g(accessibilityNodeInfo, "done");
                                                break;
                                            }
                                            if (zG) {
                                                dVar.f1882i = true;
                                                dVar.e("Done clicked — resetting for cycle #" + dVar.f1887n);
                                                dVar.a("Clicked Done");
                                                dVar.f1878e = false;
                                                dVar.f1879f = false;
                                                dVar.f1880g = false;
                                                dVar.f1881h = false;
                                                dVar.f1882i = false;
                                                dVar.f1884k = 0L;
                                                dVar.f1885l = 0L;
                                                dVar.f1886m = 0L;
                                            }
                                        }
                                    } else if (dVar.f1880g) {
                                        listFindAccessibilityNodeInfosByText3 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Done");
                                        if (listFindAccessibilityNodeInfosByText3 != null) {
                                            zG = d.g(accessibilityNodeInfo, "done");
                                            break;
                                        } else {
                                            zG = d.g(accessibilityNodeInfo, "done");
                                            break;
                                        }
                                        if (zG) {
                                            dVar.f1882i = true;
                                            dVar.e("Done clicked — resetting for cycle #" + dVar.f1887n);
                                            dVar.a("Clicked Done");
                                            dVar.f1878e = false;
                                            dVar.f1879f = false;
                                            dVar.f1880g = false;
                                            dVar.f1881h = false;
                                            dVar.f1882i = false;
                                            dVar.f1884k = 0L;
                                            dVar.f1885l = 0L;
                                            dVar.f1886m = 0L;
                                        }
                                    }
                                }
                                dVar.a(str3);
                            }
                        } else if (dVar.f1878e) {
                            if (dVar.f1878e && !dVar.f1879f) {
                                listFindAccessibilityNodeInfosByText4 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Pay ₹");
                                if (listFindAccessibilityNodeInfosByText4 == null && !listFindAccessibilityNodeInfosByText4.isEmpty()) {
                                    Iterator<AccessibilityNodeInfo> it4 = listFindAccessibilityNodeInfosByText4.iterator();
                                    if (it4.hasNext()) {
                                        AccessibilityNodeInfo next2 = it4.next();
                                        CharSequence text2 = next2.getText();
                                        if (text2 != null) {
                                            dVar.e("Pay amount button text: \"" + ((Object) text2) + "\"");
                                        }
                                        Rect rect5 = new Rect();
                                        next2.getBoundsInScreen(rect5);
                                        if (!d.b(next2)) {
                                            dVar.d(rect5.centerX(), rect5.centerY());
                                        }
                                        d.f(listFindAccessibilityNodeInfosByText4);
                                    } else {
                                        d.f(listFindAccessibilityNodeInfosByText4);
                                        if (d.g(accessibilityNodeInfo, "pay ₹")) {
                                            accessibilityNodeInfoC = d.c(accessibilityNodeInfo);
                                            if (accessibilityNodeInfoC != null) {
                                                dVar.e("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoC.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoC.getContentDescription()) + "\"");
                                                rect2 = new Rect();
                                                accessibilityNodeInfoC.getBoundsInScreen(rect2);
                                                if (!d.b(accessibilityNodeInfoC)) {
                                                    dVar.d(rect2.centerX(), rect2.centerY());
                                                }
                                                accessibilityNodeInfoC.recycle();
                                            } else {
                                                z7 = false;
                                            }
                                        }
                                    }
                                    z7 = true;
                                } else if (d.g(accessibilityNodeInfo, "pay ₹")) {
                                    accessibilityNodeInfoC = d.c(accessibilityNodeInfo);
                                    if (accessibilityNodeInfoC != null) {
                                        dVar.e("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoC.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoC.getContentDescription()) + "\"");
                                        rect2 = new Rect();
                                        accessibilityNodeInfoC.getBoundsInScreen(rect2);
                                        if (!d.b(accessibilityNodeInfoC)) {
                                            dVar.d(rect2.centerX(), rect2.centerY());
                                        }
                                        accessibilityNodeInfoC.recycle();
                                        z7 = true;
                                    } else {
                                        z7 = false;
                                    }
                                } else {
                                    z7 = true;
                                }
                                if (z7) {
                                    dVar.f1879f = true;
                                    dVar.f1886m = System.currentTimeMillis();
                                    StringBuilder sb11 = new StringBuilder("Pay ₹ button clicked (+");
                                    if (dVar.f1884k > 0) {
                                        jCurrentTimeMillis = System.currentTimeMillis() - dVar.f1884k;
                                    } else {
                                        jCurrentTimeMillis = 0;
                                    }
                                    sb11.append(jCurrentTimeMillis);
                                    sb11.append("ms)");
                                    dVar.e(sb11.toString());
                                    str3 = "Clicked Pay ₹";
                                    dVar.a(str3);
                                }
                            }
                            if (!dVar.f1878e && dVar.f1879f && !dVar.f1880g && !dVar.f1881h && (str2 = dVar.f1876c.f1783e) != null && !str2.isEmpty()) {
                                List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByText14 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Enter your PIN");
                                if (listFindAccessibilityNodeInfosByText14 == null || listFindAccessibilityNodeInfosByText14.isEmpty()) {
                                    z6 = false;
                                } else {
                                    d.f(listFindAccessibilityNodeInfosByText14);
                                    z6 = true;
                                }
                                if (z6) {
                                    dVar.f1881h = true;
                                    StringBuilder sb12 = new StringBuilder("PIN screen detected (+");
                                    sb12.append(dVar.f1884k > 0 ? System.currentTimeMillis() - dVar.f1884k : 0L);
                                    sb12.append("ms) — entering ");
                                    sb12.append(str2.length());
                                    sb12.append("-digit PIN");
                                    dVar.e(sb12.toString());
                                    dVar.a("Entering UPI PIN");
                                    m0.g gVar5 = new m0.g(dVar, 6);
                                    dVar.e("Entering PIN: " + str2.length() + " digits");
                                    AccessibilityService accessibilityService2 = dVar.f1875b;
                                    if (accessibilityService2 instanceof SnapPayAccessibilityService) {
                                        ((SnapPayAccessibilityService) accessibilityService2).f565b = true;
                                    }
                                    new Thread(new j(dVar, str2, gVar5, 5)).start();
                                } else if (dVar.f1880g) {
                                    listFindAccessibilityNodeInfosByText3 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Done");
                                    if (listFindAccessibilityNodeInfosByText3 != null) {
                                        zG = d.g(accessibilityNodeInfo, "done");
                                        break;
                                    } else {
                                        zG = d.g(accessibilityNodeInfo, "done");
                                        break;
                                    }
                                    if (zG) {
                                        dVar.f1882i = true;
                                        dVar.e("Done clicked — resetting for cycle #" + dVar.f1887n);
                                        dVar.a("Clicked Done");
                                        dVar.f1878e = false;
                                        dVar.f1879f = false;
                                        dVar.f1880g = false;
                                        dVar.f1881h = false;
                                        dVar.f1882i = false;
                                        dVar.f1884k = 0L;
                                        dVar.f1885l = 0L;
                                        dVar.f1886m = 0L;
                                    }
                                }
                            } else if (dVar.f1880g && !dVar.f1882i) {
                                listFindAccessibilityNodeInfosByText3 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Done");
                                if (listFindAccessibilityNodeInfosByText3 != null && !listFindAccessibilityNodeInfosByText3.isEmpty()) {
                                    Iterator<AccessibilityNodeInfo> it5 = listFindAccessibilityNodeInfosByText3.iterator();
                                    while (true) {
                                        if (!it5.hasNext()) {
                                            d.f(listFindAccessibilityNodeInfosByText3);
                                            zG = d.g(accessibilityNodeInfo, "done");
                                            break;
                                        } else if (d.i(it5.next())) {
                                            d.f(listFindAccessibilityNodeInfosByText3);
                                            zG = true;
                                            break;
                                        }
                                    }
                                } else {
                                    zG = d.g(accessibilityNodeInfo, "done");
                                    break;
                                }
                                if (zG) {
                                    dVar.f1882i = true;
                                    dVar.e("Done clicked — resetting for cycle #" + dVar.f1887n);
                                    dVar.a("Clicked Done");
                                    dVar.f1878e = false;
                                    dVar.f1879f = false;
                                    dVar.f1880g = false;
                                    dVar.f1881h = false;
                                    dVar.f1882i = false;
                                    dVar.f1884k = 0L;
                                    dVar.f1885l = 0L;
                                    dVar.f1886m = 0L;
                                }
                            }
                        } else {
                            listFindAccessibilityNodeInfosByText5 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Pay Now");
                            if (listFindAccessibilityNodeInfosByText5 != null && !listFindAccessibilityNodeInfosByText5.isEmpty()) {
                                Iterator<AccessibilityNodeInfo> it6 = listFindAccessibilityNodeInfosByText5.iterator();
                                while (true) {
                                    if (!it6.hasNext()) {
                                        d.f(listFindAccessibilityNodeInfosByText5);
                                        zG2 = d.g(accessibilityNodeInfo, "pay now");
                                        break;
                                    } else if (d.i(it6.next())) {
                                        d.f(listFindAccessibilityNodeInfosByText5);
                                        zG2 = true;
                                        break;
                                    }
                                }
                            } else {
                                zG2 = d.g(accessibilityNodeInfo, "pay now");
                                break;
                            }
                            if (zG2) {
                                dVar.f1878e = true;
                                long jCurrentTimeMillis7 = System.currentTimeMillis();
                                dVar.f1884k = jCurrentTimeMillis7;
                                dVar.f1885l = jCurrentTimeMillis7;
                                dVar.f1887n++;
                                dVar.e("Pay Now clicked — cycle #" + dVar.f1887n + " started");
                                str3 = "Clicked Pay Now";
                            } else {
                                if (dVar.f1878e) {
                                    listFindAccessibilityNodeInfosByText4 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Pay ₹");
                                    if (listFindAccessibilityNodeInfosByText4 == null) {
                                        if (d.g(accessibilityNodeInfo, "pay ₹")) {
                                            accessibilityNodeInfoC = d.c(accessibilityNodeInfo);
                                            if (accessibilityNodeInfoC != null) {
                                                dVar.e("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoC.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoC.getContentDescription()) + "\"");
                                                rect2 = new Rect();
                                                accessibilityNodeInfoC.getBoundsInScreen(rect2);
                                                if (!d.b(accessibilityNodeInfoC)) {
                                                    dVar.d(rect2.centerX(), rect2.centerY());
                                                }
                                                accessibilityNodeInfoC.recycle();
                                                z7 = true;
                                            } else {
                                                z7 = false;
                                            }
                                        } else {
                                            z7 = true;
                                        }
                                    } else if (d.g(accessibilityNodeInfo, "pay ₹")) {
                                        accessibilityNodeInfoC = d.c(accessibilityNodeInfo);
                                        if (accessibilityNodeInfoC != null) {
                                            dVar.e("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoC.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoC.getContentDescription()) + "\"");
                                            rect2 = new Rect();
                                            accessibilityNodeInfoC.getBoundsInScreen(rect2);
                                            if (!d.b(accessibilityNodeInfoC)) {
                                                dVar.d(rect2.centerX(), rect2.centerY());
                                            }
                                            accessibilityNodeInfoC.recycle();
                                            z7 = true;
                                        } else {
                                            z7 = false;
                                        }
                                    } else {
                                        z7 = true;
                                    }
                                    if (z7) {
                                        dVar.f1879f = true;
                                        dVar.f1886m = System.currentTimeMillis();
                                        StringBuilder sb13 = new StringBuilder("Pay ₹ button clicked (+");
                                        if (dVar.f1884k > 0) {
                                            jCurrentTimeMillis = System.currentTimeMillis() - dVar.f1884k;
                                        } else {
                                            jCurrentTimeMillis = 0;
                                        }
                                        sb13.append(jCurrentTimeMillis);
                                        sb13.append("ms)");
                                        dVar.e(sb13.toString());
                                        str3 = "Clicked Pay ₹";
                                    }
                                }
                                if (!dVar.f1878e) {
                                    if (dVar.f1880g) {
                                        listFindAccessibilityNodeInfosByText3 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Done");
                                        if (listFindAccessibilityNodeInfosByText3 != null) {
                                            zG = d.g(accessibilityNodeInfo, "done");
                                            break;
                                        } else {
                                            zG = d.g(accessibilityNodeInfo, "done");
                                            break;
                                        }
                                        if (zG) {
                                            dVar.f1882i = true;
                                            dVar.e("Done clicked — resetting for cycle #" + dVar.f1887n);
                                            dVar.a("Clicked Done");
                                            dVar.f1878e = false;
                                            dVar.f1879f = false;
                                            dVar.f1880g = false;
                                            dVar.f1881h = false;
                                            dVar.f1882i = false;
                                            dVar.f1884k = 0L;
                                            dVar.f1885l = 0L;
                                            dVar.f1886m = 0L;
                                        }
                                    }
                                } else if (dVar.f1880g) {
                                    listFindAccessibilityNodeInfosByText3 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Done");
                                    if (listFindAccessibilityNodeInfosByText3 != null) {
                                        zG = d.g(accessibilityNodeInfo, "done");
                                        break;
                                    } else {
                                        zG = d.g(accessibilityNodeInfo, "done");
                                        break;
                                    }
                                    if (zG) {
                                        dVar.f1882i = true;
                                        dVar.e("Done clicked — resetting for cycle #" + dVar.f1887n);
                                        dVar.a("Clicked Done");
                                        dVar.f1878e = false;
                                        dVar.f1879f = false;
                                        dVar.f1880g = false;
                                        dVar.f1881h = false;
                                        dVar.f1882i = false;
                                        dVar.f1884k = 0L;
                                        dVar.f1885l = 0L;
                                        dVar.f1886m = 0L;
                                    }
                                }
                            }
                            dVar.a(str3);
                        }
                    }
                }
            } else if ("QR_SCANNER".equals(str6)) {
                if ("ONE_TIME".equals(str7)) {
                    p0.b bVar2 = this.f1730e;
                    if (bVar2.f1807c.f1780b && !bVar2.f1814j && "ONE_TIME".equals(bVar2.f1807c.f1781c) && "QR_SCANNER".equals(bVar2.f1807c.f1782d)) {
                        if (bVar2.f1809e || bVar2.f1812h || !p0.b.k(accessibilityNodeInfo)) {
                            if (!bVar2.f1809e && bVar2.f1815k) {
                                bVar2.f1815k = false;
                                List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByText15 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Proceed To Pay");
                                if ((listFindAccessibilityNodeInfosByText15 == null || listFindAccessibilityNodeInfosByText15.isEmpty()) && ((listFindAccessibilityNodeInfosByText15 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Pay ₹")) == null || listFindAccessibilityNodeInfosByText15.isEmpty())) {
                                    List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByText16 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Enter your PIN");
                                    if (listFindAccessibilityNodeInfosByText16 == null || listFindAccessibilityNodeInfosByText16.isEmpty()) {
                                        z4 = false;
                                    } else {
                                        p0.b.i(listFindAccessibilityNodeInfosByText16);
                                        z4 = true;
                                    }
                                    if (z4) {
                                        z5 = true;
                                    } else {
                                        listFindAccessibilityNodeInfosByText15 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Done");
                                        if (listFindAccessibilityNodeInfosByText15 == null || listFindAccessibilityNodeInfosByText15.isEmpty()) {
                                            z5 = false;
                                        } else {
                                            p0.b.i(listFindAccessibilityNodeInfosByText15);
                                            z5 = true;
                                        }
                                    }
                                } else {
                                    p0.b.i(listFindAccessibilityNodeInfosByText15);
                                    z5 = true;
                                }
                                if (z5) {
                                    Intent intent3 = new Intent("com.snapay.app.BOT_STATUS");
                                    intent3.putExtra("status", "ERROR:Wrong screen! Restart PhonePe & AutoPay Dubara Activate Kiiye");
                                    Context context3 = bVar2.f1805a;
                                    LocalBroadcastManager.getInstance(context3).sendBroadcast(intent3);
                                    m0.d.b(context3).e();
                                }
                                bVar2.c();
                            }
                            if (bVar2.f1809e) {
                                int i2 = 2;
                                if (bVar2.f1809e || bVar2.f1810f || bVar2.f1811g || bVar2.f1812h) {
                                    if (bVar2.f1809e && bVar2.f1810f && !bVar2.f1811g) {
                                        listFindAccessibilityNodeInfosByText2 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Pay ₹");
                                        if (listFindAccessibilityNodeInfosByText2 == null && !listFindAccessibilityNodeInfosByText2.isEmpty()) {
                                            Iterator<AccessibilityNodeInfo> it7 = listFindAccessibilityNodeInfosByText2.iterator();
                                            if (it7.hasNext()) {
                                                AccessibilityNodeInfo next3 = it7.next();
                                                CharSequence text3 = next3.getText();
                                                if (text3 != null) {
                                                    bVar2.h("Pay amount button text: \"" + ((Object) text3) + "\"");
                                                }
                                                Rect rect6 = new Rect();
                                                next3.getBoundsInScreen(rect6);
                                                if (!p0.b.b(next3)) {
                                                    bVar2.f(rect6.centerX(), rect6.centerY());
                                                }
                                                p0.b.i(listFindAccessibilityNodeInfosByText2);
                                            } else {
                                                p0.b.i(listFindAccessibilityNodeInfosByText2);
                                                if (p0.b.m(accessibilityNodeInfo, "pay ₹", null)) {
                                                    accessibilityNodeInfoE = p0.b.e(accessibilityNodeInfo);
                                                    if (accessibilityNodeInfoE != null) {
                                                        bVar2.h("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoE.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoE.getContentDescription()) + "\"");
                                                        rect = new Rect();
                                                        accessibilityNodeInfoE.getBoundsInScreen(rect);
                                                        if (!p0.b.b(accessibilityNodeInfoE)) {
                                                            bVar2.f(rect.centerX(), rect.centerY());
                                                        }
                                                        accessibilityNodeInfoE.recycle();
                                                    } else {
                                                        z3 = false;
                                                    }
                                                }
                                            }
                                            z3 = true;
                                        } else if (p0.b.m(accessibilityNodeInfo, "pay ₹", null)) {
                                            z3 = true;
                                        } else {
                                            accessibilityNodeInfoE = p0.b.e(accessibilityNodeInfo);
                                            if (accessibilityNodeInfoE != null) {
                                                bVar2.h("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoE.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoE.getContentDescription()) + "\"");
                                                rect = new Rect();
                                                accessibilityNodeInfoE.getBoundsInScreen(rect);
                                                if (!p0.b.b(accessibilityNodeInfoE)) {
                                                    bVar2.f(rect.centerX(), rect.centerY());
                                                }
                                                accessibilityNodeInfoE.recycle();
                                                z3 = true;
                                            } else {
                                                z3 = false;
                                            }
                                        }
                                        if (z3) {
                                            bVar2.f1811g = true;
                                            bVar2.f1818n = System.currentTimeMillis();
                                            bVar2.h("Pay ₹ button clicked (+" + bVar2.d() + "ms since start)");
                                        }
                                    }
                                    if (!bVar2.f1809e && bVar2.f1811g && !bVar2.f1812h && !bVar2.f1813i && (str = bVar2.f1807c.f1784f) != null && !str.isEmpty()) {
                                        List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByText17 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Enter your PIN");
                                        if (listFindAccessibilityNodeInfosByText17 == null || listFindAccessibilityNodeInfosByText17.isEmpty()) {
                                            z2 = false;
                                        } else {
                                            p0.b.i(listFindAccessibilityNodeInfosByText17);
                                            z2 = true;
                                        }
                                        if (z2) {
                                            bVar2.f1813i = true;
                                            bVar2.h("PIN screen detected (+" + bVar2.d() + "ms) — entering " + str.length() + "-digit PIN");
                                            bVar2.a("Entering UPI PIN");
                                            m0.g gVar6 = new m0.g(bVar2, 3);
                                            bVar2.h("Entering PIN: " + str.length() + " digits");
                                            AccessibilityService accessibilityService3 = bVar2.f1806b;
                                            if (accessibilityService3 instanceof SnapPayAccessibilityService) {
                                                ((SnapPayAccessibilityService) accessibilityService3).f565b = true;
                                            }
                                            new Thread(new j(bVar2, str, gVar6, i2)).start();
                                        } else if (bVar2.f1812h) {
                                            listFindAccessibilityNodeInfosByText = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Done");
                                            if (listFindAccessibilityNodeInfosByText != null) {
                                                zM = p0.b.m(accessibilityNodeInfo, "done", null);
                                                break;
                                            } else {
                                                zM = p0.b.m(accessibilityNodeInfo, "done", null);
                                                break;
                                            }
                                            if (zM) {
                                                bVar2.f1814j = true;
                                                bVar2.h("Done clicked — deactivating autopay (one-time mode)");
                                                bVar2.a("Payment done");
                                                bVar2.c();
                                            }
                                        }
                                    } else if (bVar2.f1812h && !bVar2.f1814j) {
                                        listFindAccessibilityNodeInfosByText = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Done");
                                        if (listFindAccessibilityNodeInfosByText != null && !listFindAccessibilityNodeInfosByText.isEmpty()) {
                                            Iterator<AccessibilityNodeInfo> it8 = listFindAccessibilityNodeInfosByText.iterator();
                                            while (true) {
                                                if (!it8.hasNext()) {
                                                    p0.b.i(listFindAccessibilityNodeInfosByText);
                                                    zM = p0.b.m(accessibilityNodeInfo, "done", null);
                                                    break;
                                                } else if (p0.b.o(it8.next())) {
                                                    p0.b.i(listFindAccessibilityNodeInfosByText);
                                                    zM = true;
                                                    break;
                                                }
                                            }
                                        } else {
                                            zM = p0.b.m(accessibilityNodeInfo, "done", null);
                                            break;
                                        }
                                        if (zM) {
                                            bVar2.f1814j = true;
                                            bVar2.h("Done clicked — deactivating autopay (one-time mode)");
                                            bVar2.a("Payment done");
                                            bVar2.c();
                                        }
                                    }
                                } else {
                                    String str8 = bVar2.f1807c.f1785g;
                                    if ("NONE".equals(str8)) {
                                        bVar2.f1810f = true;
                                        bVar2.h("No preferred bank set — skipping bank selection");
                                        if (bVar2.f1809e) {
                                            listFindAccessibilityNodeInfosByText2 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Pay ₹");
                                            if (listFindAccessibilityNodeInfosByText2 == null) {
                                                if (p0.b.m(accessibilityNodeInfo, "pay ₹", null)) {
                                                    accessibilityNodeInfoE = p0.b.e(accessibilityNodeInfo);
                                                    if (accessibilityNodeInfoE != null) {
                                                        bVar2.h("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoE.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoE.getContentDescription()) + "\"");
                                                        rect = new Rect();
                                                        accessibilityNodeInfoE.getBoundsInScreen(rect);
                                                        if (!p0.b.b(accessibilityNodeInfoE)) {
                                                            bVar2.f(rect.centerX(), rect.centerY());
                                                        }
                                                        accessibilityNodeInfoE.recycle();
                                                        z3 = true;
                                                    } else {
                                                        z3 = false;
                                                    }
                                                } else {
                                                    z3 = true;
                                                }
                                            } else if (p0.b.m(accessibilityNodeInfo, "pay ₹", null)) {
                                                accessibilityNodeInfoE = p0.b.e(accessibilityNodeInfo);
                                                if (accessibilityNodeInfoE != null) {
                                                    bVar2.h("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoE.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoE.getContentDescription()) + "\"");
                                                    rect = new Rect();
                                                    accessibilityNodeInfoE.getBoundsInScreen(rect);
                                                    if (!p0.b.b(accessibilityNodeInfoE)) {
                                                        bVar2.f(rect.centerX(), rect.centerY());
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
                                                bVar2.f1811g = true;
                                                bVar2.f1818n = System.currentTimeMillis();
                                                bVar2.h("Pay ₹ button clicked (+" + bVar2.d() + "ms since start)");
                                            }
                                        }
                                        if (!bVar2.f1809e) {
                                            if (bVar2.f1812h) {
                                                listFindAccessibilityNodeInfosByText = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Done");
                                                if (listFindAccessibilityNodeInfosByText != null) {
                                                    zM = p0.b.m(accessibilityNodeInfo, "done", null);
                                                    break;
                                                } else {
                                                    zM = p0.b.m(accessibilityNodeInfo, "done", null);
                                                    break;
                                                }
                                                if (zM) {
                                                    bVar2.f1814j = true;
                                                    bVar2.h("Done clicked — deactivating autopay (one-time mode)");
                                                    bVar2.a("Payment done");
                                                    bVar2.c();
                                                }
                                            }
                                        } else if (bVar2.f1812h) {
                                            listFindAccessibilityNodeInfosByText = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Done");
                                            if (listFindAccessibilityNodeInfosByText != null) {
                                                zM = p0.b.m(accessibilityNodeInfo, "done", null);
                                                break;
                                            } else {
                                                zM = p0.b.m(accessibilityNodeInfo, "done", null);
                                                break;
                                            }
                                            if (zM) {
                                                bVar2.f1814j = true;
                                                bVar2.h("Done clicked — deactivating autopay (one-time mode)");
                                                bVar2.a("Payment done");
                                                bVar2.c();
                                            }
                                        }
                                    } else {
                                        if ("OTHER".equals(str8)) {
                                            str8 = bVar2.f1807c.f1786h;
                                        }
                                        if (str8 == null || "NONE".equals(str8)) {
                                            zL = false;
                                        } else {
                                            String upperCase = str8.toUpperCase();
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
                                                    strConcat = str8.concat(" Bank");
                                                    break;
                                            }
                                            List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByText18 = accessibilityNodeInfo.findAccessibilityNodeInfosByText(strConcat);
                                            if (listFindAccessibilityNodeInfosByText18 == null || listFindAccessibilityNodeInfosByText18.isEmpty()) {
                                                zL = p0.b.l(accessibilityNodeInfo, strConcat);
                                            } else {
                                                Iterator<AccessibilityNodeInfo> it9 = listFindAccessibilityNodeInfosByText18.iterator();
                                                while (true) {
                                                    if (it9.hasNext()) {
                                                        AccessibilityNodeInfo next4 = it9.next();
                                                        CharSequence text4 = next4.getText();
                                                        if (text4 != null && text4.toString().contains(strConcat) && !p0.b.g(next4) && p0.b.o(next4)) {
                                                            p0.b.i(listFindAccessibilityNodeInfosByText18);
                                                            zL = true;
                                                        }
                                                    } else {
                                                        p0.b.i(listFindAccessibilityNodeInfosByText18);
                                                        zL = p0.b.l(accessibilityNodeInfo, strConcat);
                                                    }
                                                }
                                            }
                                        }
                                        if (zL) {
                                            bVar2.f1810f = true;
                                            bVar2.h("Bank selected: " + str8 + " (+" + bVar2.d() + "ms)");
                                            StringBuilder sb14 = new StringBuilder("Selected bank: ");
                                            sb14.append(str8);
                                            string = sb14.toString();
                                        } else {
                                            bVar2.h("Waiting for bank sheet — looking for: " + str8);
                                        }
                                    }
                                }
                            } else {
                                List<AccessibilityNodeInfo> listFindAccessibilityNodeInfosByText19 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Proceed To Pay");
                                if (listFindAccessibilityNodeInfosByText19 != null && !listFindAccessibilityNodeInfosByText19.isEmpty()) {
                                    Iterator<AccessibilityNodeInfo> it10 = listFindAccessibilityNodeInfosByText19.iterator();
                                    while (true) {
                                        if (!it10.hasNext()) {
                                            p0.b.i(listFindAccessibilityNodeInfosByText19);
                                            zM2 = p0.b.m(accessibilityNodeInfo, "proceed", "pay");
                                            break;
                                        } else if (p0.b.o(it10.next())) {
                                            p0.b.i(listFindAccessibilityNodeInfosByText19);
                                            zM2 = true;
                                            break;
                                        }
                                    }
                                } else {
                                    zM2 = p0.b.m(accessibilityNodeInfo, "proceed", "pay");
                                    break;
                                }
                                if (zM2) {
                                    bVar2.f1809e = true;
                                    long jCurrentTimeMillis8 = System.currentTimeMillis();
                                    bVar2.f1816l = jCurrentTimeMillis8;
                                    bVar2.f1817m = jCurrentTimeMillis8;
                                    bVar2.h("Proceed To Pay clicked — payment flow started");
                                    string = "Clicked Proceed To Pay";
                                } else {
                                    int i3 = 2;
                                    if (bVar2.f1809e) {
                                        if (bVar2.f1809e) {
                                            listFindAccessibilityNodeInfosByText2 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Pay ₹");
                                            if (listFindAccessibilityNodeInfosByText2 == null) {
                                                if (p0.b.m(accessibilityNodeInfo, "pay ₹", null)) {
                                                    accessibilityNodeInfoE = p0.b.e(accessibilityNodeInfo);
                                                    if (accessibilityNodeInfoE != null) {
                                                        bVar2.h("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoE.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoE.getContentDescription()) + "\"");
                                                        rect = new Rect();
                                                        accessibilityNodeInfoE.getBoundsInScreen(rect);
                                                        if (!p0.b.b(accessibilityNodeInfoE)) {
                                                            bVar2.f(rect.centerX(), rect.centerY());
                                                        }
                                                        accessibilityNodeInfoE.recycle();
                                                        z3 = true;
                                                    } else {
                                                        z3 = false;
                                                    }
                                                } else {
                                                    z3 = true;
                                                }
                                            } else if (p0.b.m(accessibilityNodeInfo, "pay ₹", null)) {
                                                accessibilityNodeInfoE = p0.b.e(accessibilityNodeInfo);
                                                if (accessibilityNodeInfoE != null) {
                                                    bVar2.h("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoE.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoE.getContentDescription()) + "\"");
                                                    rect = new Rect();
                                                    accessibilityNodeInfoE.getBoundsInScreen(rect);
                                                    if (!p0.b.b(accessibilityNodeInfoE)) {
                                                        bVar2.f(rect.centerX(), rect.centerY());
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
                                                bVar2.f1811g = true;
                                                bVar2.f1818n = System.currentTimeMillis();
                                                bVar2.h("Pay ₹ button clicked (+" + bVar2.d() + "ms since start)");
                                            }
                                        }
                                        if (!bVar2.f1809e) {
                                            if (bVar2.f1812h) {
                                                listFindAccessibilityNodeInfosByText = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Done");
                                                if (listFindAccessibilityNodeInfosByText != null) {
                                                    zM = p0.b.m(accessibilityNodeInfo, "done", null);
                                                    break;
                                                } else {
                                                    zM = p0.b.m(accessibilityNodeInfo, "done", null);
                                                    break;
                                                }
                                                if (zM) {
                                                    bVar2.f1814j = true;
                                                    bVar2.h("Done clicked — deactivating autopay (one-time mode)");
                                                    bVar2.a("Payment done");
                                                    bVar2.c();
                                                }
                                            }
                                        } else if (bVar2.f1812h) {
                                            listFindAccessibilityNodeInfosByText = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Done");
                                            if (listFindAccessibilityNodeInfosByText != null) {
                                                zM = p0.b.m(accessibilityNodeInfo, "done", null);
                                                break;
                                            } else {
                                                zM = p0.b.m(accessibilityNodeInfo, "done", null);
                                                break;
                                            }
                                            if (zM) {
                                                bVar2.f1814j = true;
                                                bVar2.h("Done clicked — deactivating autopay (one-time mode)");
                                                bVar2.a("Payment done");
                                                bVar2.c();
                                            }
                                        }
                                    } else {
                                        if (bVar2.f1809e) {
                                            listFindAccessibilityNodeInfosByText2 = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Pay ₹");
                                            if (listFindAccessibilityNodeInfosByText2 == null) {
                                                if (p0.b.m(accessibilityNodeInfo, "pay ₹", null)) {
                                                    accessibilityNodeInfoE = p0.b.e(accessibilityNodeInfo);
                                                    if (accessibilityNodeInfoE != null) {
                                                        bVar2.h("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoE.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoE.getContentDescription()) + "\"");
                                                        rect = new Rect();
                                                        accessibilityNodeInfoE.getBoundsInScreen(rect);
                                                        if (!p0.b.b(accessibilityNodeInfoE)) {
                                                            bVar2.f(rect.centerX(), rect.centerY());
                                                        }
                                                        accessibilityNodeInfoE.recycle();
                                                        z3 = true;
                                                    } else {
                                                        z3 = false;
                                                    }
                                                } else {
                                                    z3 = true;
                                                }
                                            } else if (p0.b.m(accessibilityNodeInfo, "pay ₹", null)) {
                                                accessibilityNodeInfoE = p0.b.e(accessibilityNodeInfo);
                                                if (accessibilityNodeInfoE != null) {
                                                    bVar2.h("Pay button found via scan: text=\"" + ((Object) accessibilityNodeInfoE.getText()) + "\" desc=\"" + ((Object) accessibilityNodeInfoE.getContentDescription()) + "\"");
                                                    rect = new Rect();
                                                    accessibilityNodeInfoE.getBoundsInScreen(rect);
                                                    if (!p0.b.b(accessibilityNodeInfoE)) {
                                                        bVar2.f(rect.centerX(), rect.centerY());
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
                                                bVar2.f1811g = true;
                                                bVar2.f1818n = System.currentTimeMillis();
                                                bVar2.h("Pay ₹ button clicked (+" + bVar2.d() + "ms since start)");
                                            }
                                        }
                                        if (!bVar2.f1809e) {
                                            if (bVar2.f1812h) {
                                                listFindAccessibilityNodeInfosByText = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Done");
                                                if (listFindAccessibilityNodeInfosByText != null) {
                                                    zM = p0.b.m(accessibilityNodeInfo, "done", null);
                                                    break;
                                                } else {
                                                    zM = p0.b.m(accessibilityNodeInfo, "done", null);
                                                    break;
                                                }
                                                if (zM) {
                                                    bVar2.f1814j = true;
                                                    bVar2.h("Done clicked — deactivating autopay (one-time mode)");
                                                    bVar2.a("Payment done");
                                                    bVar2.c();
                                                }
                                            }
                                        } else if (bVar2.f1812h) {
                                            listFindAccessibilityNodeInfosByText = accessibilityNodeInfo.findAccessibilityNodeInfosByText("Done");
                                            if (listFindAccessibilityNodeInfosByText != null) {
                                                zM = p0.b.m(accessibilityNodeInfo, "done", null);
                                                break;
                                            } else {
                                                zM = p0.b.m(accessibilityNodeInfo, "done", null);
                                                break;
                                            }
                                            if (zM) {
                                                bVar2.f1814j = true;
                                                bVar2.h("Done clicked — deactivating autopay (one-time mode)");
                                                bVar2.a("Payment done");
                                                bVar2.c();
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            bVar2.h("QR scanner opened");
                            string = "Opening QR scanner";
                        }
                        bVar2.a(string);
                    }
                } else if ("PAY_ALL".equals(str7)) {
                    this.f1731f.g(accessibilityNodeInfo);
                }
            }
        }
    }

    @Override // n0.a
    public final void b() {
        this.f1727b = true;
        this.f1728c.h();
        d dVar = this.f1729d;
        dVar.f1878e = false;
        dVar.f1879f = false;
        dVar.f1880g = false;
        dVar.f1881h = false;
        dVar.f1882i = false;
        dVar.f1884k = 0L;
        dVar.f1885l = 0L;
        dVar.f1886m = 0L;
        dVar.f1883j = true;
        dVar.f1887n = 0;
        dVar.f1877d.removeCallbacksAndMessages(null);
        this.f1730e.j();
        p0.d dVar2 = this.f1731f;
        dVar2.f1826e = false;
        dVar2.f1827f = false;
        dVar2.f1828g = false;
        dVar2.f1829h = false;
        dVar2.f1830i = false;
        dVar2.f1831j = false;
        dVar2.f1833l = 0L;
        dVar2.f1834m = 0L;
        dVar2.f1835n = 0L;
        dVar2.f1832k = true;
        dVar2.f1836o = 0;
        dVar2.f1825d.removeCallbacksAndMessages(null);
    }

    @Override // n0.a
    public final void c() {
        this.f1727b = false;
        this.f1728c.h();
        d dVar = this.f1729d;
        dVar.f1878e = false;
        dVar.f1879f = false;
        dVar.f1880g = false;
        dVar.f1881h = false;
        dVar.f1882i = false;
        dVar.f1884k = 0L;
        dVar.f1885l = 0L;
        dVar.f1886m = 0L;
        dVar.f1883j = true;
        dVar.f1887n = 0;
        dVar.f1877d.removeCallbacksAndMessages(null);
        this.f1730e.j();
        p0.d dVar2 = this.f1731f;
        dVar2.f1826e = false;
        dVar2.f1827f = false;
        dVar2.f1828g = false;
        dVar2.f1829h = false;
        dVar2.f1830i = false;
        dVar2.f1831j = false;
        dVar2.f1833l = 0L;
        dVar2.f1834m = 0L;
        dVar2.f1835n = 0L;
        dVar2.f1832k = true;
        dVar2.f1836o = 0;
        dVar2.f1825d.removeCallbacksAndMessages(null);
    }
}
