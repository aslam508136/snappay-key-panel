package i;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class o implements t.a {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final int[] f1059y = {1, 4, 5, 3, 2, 0};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f1060a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Resources f1061b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1062c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1063d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public m f1064e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayList f1065f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ArrayList f1066g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1067h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ArrayList f1068i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final ArrayList f1069j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f1070k;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public CharSequence f1072m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Drawable f1073n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public View f1074o;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public q f1081v;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f1083x;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f1071l = 0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f1075p = false;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f1076q = false;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f1077r = false;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f1078s = false;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final ArrayList f1079t = new ArrayList();

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final CopyOnWriteArrayList f1080u = new CopyOnWriteArrayList();

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f1082w = false;

    public o(Context context) {
        boolean zShouldShowMenuShortcutsWhenKeyboardPresent;
        boolean z2 = false;
        this.f1060a = context;
        Resources resources = context.getResources();
        this.f1061b = resources;
        this.f1065f = new ArrayList();
        this.f1066g = new ArrayList();
        this.f1067h = true;
        this.f1068i = new ArrayList();
        this.f1069j = new ArrayList();
        this.f1070k = true;
        if (resources.getConfiguration().keyboard != 1) {
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            int i2 = x.v.f2016a;
            if (Build.VERSION.SDK_INT >= 28) {
                zShouldShowMenuShortcutsWhenKeyboardPresent = viewConfiguration.shouldShowMenuShortcutsWhenKeyboardPresent();
            } else {
                Resources resources2 = context.getResources();
                int identifier = resources2.getIdentifier("config_showMenuShortcutsWhenKeyboardPresent", "bool", "android");
                zShouldShowMenuShortcutsWhenKeyboardPresent = identifier != 0 && resources2.getBoolean(identifier);
            }
            if (zShouldShowMenuShortcutsWhenKeyboardPresent) {
                z2 = true;
            }
        }
        this.f1063d = z2;
    }

    public final q a(int i2, int i3, int i4, CharSequence charSequence) {
        int i5;
        int i6 = ((-65536) & i4) >> 16;
        if (i6 < 0 || i6 >= 6) {
            throw new IllegalArgumentException("order does not contain a valid category.");
        }
        int i7 = (f1059y[i6] << 16) | (65535 & i4);
        q qVar = new q(this, i2, i3, i4, i7, charSequence, this.f1071l);
        ArrayList arrayList = this.f1065f;
        int size = arrayList.size();
        do {
            size--;
            if (size < 0) {
                i5 = 0;
            }
            arrayList.add(i5, qVar);
            p(true);
            return qVar;
        } while (((q) arrayList.get(size)).f1090d > i7);
        i5 = size + 1;
        arrayList.add(i5, qVar);
        p(true);
        return qVar;
    }

    @Override // android.view.Menu
    public final MenuItem add(int i2) {
        return a(0, 0, 0, this.f1061b.getString(i2));
    }

    @Override // android.view.Menu
    public final int addIntentOptions(int i2, int i3, int i4, ComponentName componentName, Intent[] intentArr, Intent intent, int i5, MenuItem[] menuItemArr) {
        int i6;
        PackageManager packageManager = this.f1060a.getPackageManager();
        List<ResolveInfo> listQueryIntentActivityOptions = packageManager.queryIntentActivityOptions(componentName, intentArr, intent, 0);
        int size = listQueryIntentActivityOptions != null ? listQueryIntentActivityOptions.size() : 0;
        if ((i5 & 1) == 0) {
            removeGroup(i2);
        }
        for (int i7 = 0; i7 < size; i7++) {
            ResolveInfo resolveInfo = listQueryIntentActivityOptions.get(i7);
            int i8 = resolveInfo.specificIndex;
            Intent intent2 = new Intent(i8 < 0 ? intent : intentArr[i8]);
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            intent2.setComponent(new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name));
            q qVarA = a(i2, i3, i4, resolveInfo.loadLabel(packageManager));
            qVarA.setIcon(resolveInfo.loadIcon(packageManager));
            qVarA.f1093g = intent2;
            if (menuItemArr != null && (i6 = resolveInfo.specificIndex) >= 0) {
                menuItemArr[i6] = qVarA;
            }
        }
        return size;
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i2) {
        return addSubMenu(0, 0, 0, this.f1061b.getString(i2));
    }

    public final void b(b0 b0Var, Context context) {
        this.f1080u.add(new WeakReference(b0Var));
        b0Var.e(context, this);
        this.f1070k = true;
    }

    public final void c(boolean z2) {
        if (this.f1078s) {
            return;
        }
        this.f1078s = true;
        CopyOnWriteArrayList<WeakReference> copyOnWriteArrayList = this.f1080u;
        for (WeakReference weakReference : copyOnWriteArrayList) {
            b0 b0Var = (b0) weakReference.get();
            if (b0Var == null) {
                copyOnWriteArrayList.remove(weakReference);
            } else {
                b0Var.a(this, z2);
            }
        }
        this.f1078s = false;
    }

    @Override // android.view.Menu
    public final void clear() {
        q qVar = this.f1081v;
        if (qVar != null) {
            d(qVar);
        }
        this.f1065f.clear();
        p(true);
    }

    public final void clearHeader() {
        this.f1073n = null;
        this.f1072m = null;
        this.f1074o = null;
        p(false);
    }

    @Override // android.view.Menu
    public final void close() {
        c(true);
    }

    public boolean d(q qVar) {
        CopyOnWriteArrayList<WeakReference> copyOnWriteArrayList = this.f1080u;
        boolean zC = false;
        if (!copyOnWriteArrayList.isEmpty() && this.f1081v == qVar) {
            w();
            for (WeakReference weakReference : copyOnWriteArrayList) {
                b0 b0Var = (b0) weakReference.get();
                if (b0Var != null) {
                    zC = b0Var.c(qVar);
                    if (zC) {
                        break;
                    }
                } else {
                    copyOnWriteArrayList.remove(weakReference);
                }
            }
            v();
            if (zC) {
                this.f1081v = null;
            }
        }
        return zC;
    }

    public boolean e(o oVar, MenuItem menuItem) {
        m mVar = this.f1064e;
        return mVar != null && mVar.d(oVar, menuItem);
    }

    public boolean f(q qVar) {
        CopyOnWriteArrayList<WeakReference> copyOnWriteArrayList = this.f1080u;
        boolean zH = false;
        if (copyOnWriteArrayList.isEmpty()) {
            return false;
        }
        w();
        for (WeakReference weakReference : copyOnWriteArrayList) {
            b0 b0Var = (b0) weakReference.get();
            if (b0Var != null) {
                zH = b0Var.h(qVar);
                if (zH) {
                    break;
                }
            } else {
                copyOnWriteArrayList.remove(weakReference);
            }
        }
        v();
        if (zH) {
            this.f1081v = qVar;
        }
        return zH;
    }

    @Override // android.view.Menu
    public final MenuItem findItem(int i2) {
        MenuItem menuItemFindItem;
        int size = size();
        for (int i3 = 0; i3 < size; i3++) {
            q qVar = (q) this.f1065f.get(i3);
            if (qVar.f1087a == i2) {
                return qVar;
            }
            if (qVar.hasSubMenu() && (menuItemFindItem = qVar.f1101o.findItem(i2)) != null) {
                return menuItemFindItem;
            }
        }
        return null;
    }

    public final q g(int i2, KeyEvent keyEvent) {
        ArrayList arrayList = this.f1079t;
        arrayList.clear();
        h(arrayList, i2, keyEvent);
        if (arrayList.isEmpty()) {
            return null;
        }
        int metaState = keyEvent.getMetaState();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        keyEvent.getKeyData(keyData);
        int size = arrayList.size();
        if (size == 1) {
            return (q) arrayList.get(0);
        }
        boolean zN = n();
        for (int i3 = 0; i3 < size; i3++) {
            q qVar = (q) arrayList.get(i3);
            char c2 = zN ? qVar.f1096j : qVar.f1094h;
            char[] cArr = keyData.meta;
            if ((c2 == cArr[0] && (metaState & 2) == 0) || ((c2 == cArr[2] && (metaState & 2) != 0) || (zN && c2 == '\b' && i2 == 67))) {
                return qVar;
            }
        }
        return null;
    }

    @Override // android.view.Menu
    public final MenuItem getItem(int i2) {
        return (MenuItem) this.f1065f.get(i2);
    }

    /* JADX WARN: Code duplicated, block: B:37:0x007a  */
    public final void h(ArrayList arrayList, int i2, KeyEvent keyEvent) {
        boolean zN = n();
        int modifiers = keyEvent.getModifiers();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        if (keyEvent.getKeyData(keyData) || i2 == 67) {
            ArrayList arrayList2 = this.f1065f;
            int size = arrayList2.size();
            for (int i3 = 0; i3 < size; i3++) {
                q qVar = (q) arrayList2.get(i3);
                if (qVar.hasSubMenu()) {
                    qVar.f1101o.h(arrayList, i2, keyEvent);
                }
                char c2 = zN ? qVar.f1096j : qVar.f1094h;
                if (((modifiers & 69647) == ((zN ? qVar.f1097k : qVar.f1095i) & 69647)) && c2 != 0) {
                    char[] cArr = keyData.meta;
                    if (c2 != cArr[0] && c2 != cArr[2]) {
                        if (zN && c2 == '\b') {
                            if (i2 == 67) {
                            }
                        }
                    }
                    if (qVar.isEnabled()) {
                        arrayList.add(qVar);
                    }
                }
            }
        }
    }

    @Override // android.view.Menu
    public final boolean hasVisibleItems() {
        if (this.f1083x) {
            return true;
        }
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            if (((q) this.f1065f.get(i2)).isVisible()) {
                return true;
            }
        }
        return false;
    }

    public final void i() {
        ArrayList arrayListL = l();
        if (this.f1070k) {
            CopyOnWriteArrayList<WeakReference> copyOnWriteArrayList = this.f1080u;
            boolean zD = false;
            for (WeakReference weakReference : copyOnWriteArrayList) {
                b0 b0Var = (b0) weakReference.get();
                if (b0Var == null) {
                    copyOnWriteArrayList.remove(weakReference);
                } else {
                    zD |= b0Var.d();
                }
            }
            ArrayList arrayList = this.f1068i;
            ArrayList arrayList2 = this.f1069j;
            arrayList.clear();
            arrayList2.clear();
            if (zD) {
                int size = arrayListL.size();
                for (int i2 = 0; i2 < size; i2++) {
                    q qVar = (q) arrayListL.get(i2);
                    if (qVar.f()) {
                        arrayList.add(qVar);
                    } else {
                        arrayList2.add(qVar);
                    }
                }
            } else {
                arrayList2.addAll(l());
            }
            this.f1070k = false;
        }
    }

    @Override // android.view.Menu
    public final boolean isShortcutKey(int i2, KeyEvent keyEvent) {
        return g(i2, keyEvent) != null;
    }

    public String j() {
        return "android:menu:actionviewstates";
    }

    public o k() {
        return this;
    }

    public final ArrayList l() {
        boolean z2 = this.f1067h;
        ArrayList arrayList = this.f1066g;
        if (!z2) {
            return arrayList;
        }
        arrayList.clear();
        ArrayList arrayList2 = this.f1065f;
        int size = arrayList2.size();
        for (int i2 = 0; i2 < size; i2++) {
            q qVar = (q) arrayList2.get(i2);
            if (qVar.isVisible()) {
                arrayList.add(qVar);
            }
        }
        this.f1067h = false;
        this.f1070k = true;
        return arrayList;
    }

    public boolean m() {
        return this.f1082w;
    }

    public boolean n() {
        return this.f1062c;
    }

    public boolean o() {
        return this.f1063d;
    }

    public final void p(boolean z2) {
        if (this.f1075p) {
            this.f1076q = true;
            if (z2) {
                this.f1077r = true;
                return;
            }
            return;
        }
        if (z2) {
            this.f1067h = true;
            this.f1070k = true;
        }
        CopyOnWriteArrayList<WeakReference> copyOnWriteArrayList = this.f1080u;
        if (copyOnWriteArrayList.isEmpty()) {
            return;
        }
        w();
        for (WeakReference weakReference : copyOnWriteArrayList) {
            b0 b0Var = (b0) weakReference.get();
            if (b0Var == null) {
                copyOnWriteArrayList.remove(weakReference);
            } else {
                b0Var.i();
            }
        }
        v();
    }

    @Override // android.view.Menu
    public final boolean performIdentifierAction(int i2, int i3) {
        return q(findItem(i2), null, i3);
    }

    @Override // android.view.Menu
    public final boolean performShortcut(int i2, KeyEvent keyEvent, int i3) {
        q qVarG = g(i2, keyEvent);
        boolean zQ = qVarG != null ? q(qVarG, null, i3) : false;
        if ((i3 & 2) != 0) {
            c(true);
        }
        return zQ;
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0040  */
    /* JADX WARN: Code duplicated, block: B:32:0x0051  */
    /* JADX WARN: Code duplicated, block: B:35:0x0058  */
    /* JADX WARN: Code duplicated, block: B:38:0x0061  */
    /* JADX WARN: Code duplicated, block: B:45:0x006f  */
    /* JADX WARN: Code duplicated, block: B:47:0x0073  */
    /* JADX WARN: Code duplicated, block: B:50:0x007c  */
    /* JADX WARN: Code duplicated, block: B:53:0x008e  */
    /* JADX WARN: Code duplicated, block: B:57:0x00a1 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:58:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:62:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:69:0x00cd A[PHI: r1
  0x00cd: PHI (r1v11 boolean) = (r1v10 boolean), (r1v9 boolean), (r1v13 boolean) binds: [B:68:0x00cb, B:43:0x006c, B:36:0x005d] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:75:0x00c3 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:76:0x00c5 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:77:0x00bf A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:79:0x00ab A[SYNTHETIC] */
    public final boolean q(MenuItem menuItem, b0 b0Var, int i2) {
        r rVar;
        boolean zExpandActionView;
        r rVar2;
        boolean z2;
        h0 h0Var;
        CopyOnWriteArrayList<WeakReference> copyOnWriteArrayList;
        b0 b0Var2;
        q qVar = (q) menuItem;
        boolean zK = false;
        if (qVar == null || !qVar.isEnabled()) {
            return false;
        }
        MenuItem.OnMenuItemClickListener onMenuItemClickListener = qVar.f1102p;
        if (onMenuItemClickListener == null || !onMenuItemClickListener.onMenuItemClick(qVar)) {
            o oVar = qVar.f1100n;
            if (oVar.e(oVar, qVar)) {
                zExpandActionView = true;
            } else {
                Intent intent = qVar.f1093g;
                if (intent != null) {
                    try {
                        oVar.f1060a.startActivity(intent);
                    } catch (ActivityNotFoundException e2) {
                        Log.e("MenuItemImpl", "Can't find activity to handle intent; ignoring", e2);
                        rVar = qVar.A;
                        if (rVar == null) {
                        }
                        zExpandActionView = false;
                        rVar2 = qVar.A;
                        if (rVar2 == null) {
                            z2 = false;
                        } else {
                            z2 = false;
                        }
                        if (qVar.e()) {
                            zExpandActionView |= qVar.expandActionView();
                            if (zExpandActionView) {
                                c(true);
                            }
                        } else if (qVar.hasSubMenu()) {
                            if ((i2 & 4) == 0) {
                                c(false);
                            }
                            if (!qVar.hasSubMenu()) {
                                h0 h0Var2 = new h0(this.f1060a, this, qVar);
                                qVar.f1101o = h0Var2;
                                h0Var2.setHeaderTitle(qVar.f1091e);
                            }
                            h0Var = qVar.f1101o;
                            if (z2) {
                                rVar2.f1114b.getClass();
                                rVar2.f1113a.onPrepareSubMenu(h0Var);
                            }
                            copyOnWriteArrayList = this.f1080u;
                            if (!copyOnWriteArrayList.isEmpty()) {
                                if (b0Var != null) {
                                }
                                for (WeakReference weakReference : copyOnWriteArrayList) {
                                    b0Var2 = (b0) weakReference.get();
                                    if (b0Var2 == null) {
                                        copyOnWriteArrayList.remove(weakReference);
                                    } else if (!zK) {
                                        zK = b0Var2.k(h0Var);
                                    }
                                }
                            }
                            zExpandActionView |= zK;
                            if (!zExpandActionView) {
                                c(true);
                            }
                        } else {
                            if ((i2 & 4) == 0) {
                                c(false);
                            }
                            if (!qVar.hasSubMenu()) {
                                h0 h0Var3 = new h0(this.f1060a, this, qVar);
                                qVar.f1101o = h0Var3;
                                h0Var3.setHeaderTitle(qVar.f1091e);
                            }
                            h0Var = qVar.f1101o;
                            if (z2) {
                                rVar2.f1114b.getClass();
                                rVar2.f1113a.onPrepareSubMenu(h0Var);
                            }
                            copyOnWriteArrayList = this.f1080u;
                            if (!copyOnWriteArrayList.isEmpty()) {
                                zK = b0Var != null ? b0Var.k(h0Var) : false;
                                while (r8.hasNext()) {
                                    b0Var2 = (b0) weakReference.get();
                                    if (b0Var2 == null) {
                                        copyOnWriteArrayList.remove(weakReference);
                                    } else if (!zK) {
                                        zK = b0Var2.k(h0Var);
                                    }
                                }
                            }
                            zExpandActionView |= zK;
                            if (!zExpandActionView) {
                                c(true);
                            }
                        }
                        return zExpandActionView;
                    }
                    zExpandActionView = true;
                } else {
                    rVar = qVar.A;
                    if (rVar == null && rVar.f1113a.onPerformDefaultAction()) {
                        zExpandActionView = true;
                    } else {
                        zExpandActionView = false;
                    }
                }
            }
        } else {
            zExpandActionView = true;
        }
        rVar2 = qVar.A;
        if (rVar2 == null && rVar2.f1113a.hasSubMenu()) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (qVar.e()) {
            zExpandActionView |= qVar.expandActionView();
            if (zExpandActionView) {
                c(true);
            }
        } else if (qVar.hasSubMenu() || z2) {
            if ((i2 & 4) == 0) {
                c(false);
            }
            if (!qVar.hasSubMenu()) {
                h0 h0Var4 = new h0(this.f1060a, this, qVar);
                qVar.f1101o = h0Var4;
                h0Var4.setHeaderTitle(qVar.f1091e);
            }
            h0Var = qVar.f1101o;
            if (z2) {
                rVar2.f1114b.getClass();
                rVar2.f1113a.onPrepareSubMenu(h0Var);
            }
            copyOnWriteArrayList = this.f1080u;
            if (!copyOnWriteArrayList.isEmpty()) {
                if (b0Var != null) {
                }
                while (r8.hasNext()) {
                    b0Var2 = (b0) weakReference.get();
                    if (b0Var2 == null) {
                        copyOnWriteArrayList.remove(weakReference);
                    } else if (!zK) {
                        zK = b0Var2.k(h0Var);
                    }
                }
            }
            zExpandActionView |= zK;
            if (!zExpandActionView) {
                c(true);
            }
        } else if ((i2 & 1) == 0) {
            c(true);
        }
        return zExpandActionView;
    }

    public final void r(b0 b0Var) {
        CopyOnWriteArrayList<WeakReference> copyOnWriteArrayList = this.f1080u;
        for (WeakReference weakReference : copyOnWriteArrayList) {
            b0 b0Var2 = (b0) weakReference.get();
            if (b0Var2 == null || b0Var2 == b0Var) {
                copyOnWriteArrayList.remove(weakReference);
            }
        }
    }

    @Override // android.view.Menu
    public final void removeGroup(int i2) {
        ArrayList arrayList;
        int size = size();
        int i3 = 0;
        int i4 = 0;
        while (true) {
            arrayList = this.f1065f;
            if (i4 >= size) {
                i4 = -1;
                break;
            } else if (((q) arrayList.get(i4)).f1088b == i2) {
                break;
            } else {
                i4++;
            }
        }
        if (i4 >= 0) {
            int size2 = arrayList.size() - i4;
            while (true) {
                int i5 = i3 + 1;
                if (i3 >= size2 || ((q) arrayList.get(i4)).f1088b != i2) {
                    break;
                }
                if (i4 >= 0 && i4 < arrayList.size()) {
                    arrayList.remove(i4);
                }
                i3 = i5;
            }
            p(true);
        }
    }

    @Override // android.view.Menu
    public final void removeItem(int i2) {
        ArrayList arrayList;
        int size = size();
        int i3 = 0;
        while (true) {
            arrayList = this.f1065f;
            if (i3 >= size) {
                i3 = -1;
                break;
            } else if (((q) arrayList.get(i3)).f1087a == i2) {
                break;
            } else {
                i3++;
            }
        }
        if (i3 < 0 || i3 >= arrayList.size()) {
            return;
        }
        arrayList.remove(i3);
        p(true);
    }

    public final void s(Bundle bundle) {
        MenuItem menuItemFindItem;
        if (bundle == null) {
            return;
        }
        SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray(j());
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = getItem(i2);
            View actionView = item.getActionView();
            if (actionView != null && actionView.getId() != -1) {
                actionView.restoreHierarchyState(sparseParcelableArray);
            }
            if (item.hasSubMenu()) {
                ((h0) item.getSubMenu()).s(bundle);
            }
        }
        int i3 = bundle.getInt("android:menu:expandedactionview");
        if (i3 <= 0 || (menuItemFindItem = findItem(i3)) == null) {
            return;
        }
        menuItemFindItem.expandActionView();
    }

    @Override // android.view.Menu
    public final void setGroupCheckable(int i2, boolean z2, boolean z3) {
        ArrayList arrayList = this.f1065f;
        int size = arrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            q qVar = (q) arrayList.get(i3);
            if (qVar.f1088b == i2) {
                qVar.f1110x = (qVar.f1110x & (-5)) | (z3 ? 4 : 0);
                qVar.setCheckable(z2);
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupDividerEnabled(boolean z2) {
        this.f1082w = z2;
    }

    @Override // android.view.Menu
    public final void setGroupEnabled(int i2, boolean z2) {
        ArrayList arrayList = this.f1065f;
        int size = arrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            q qVar = (q) arrayList.get(i3);
            if (qVar.f1088b == i2) {
                qVar.setEnabled(z2);
            }
        }
    }

    @Override // android.view.Menu
    public final void setGroupVisible(int i2, boolean z2) {
        ArrayList arrayList = this.f1065f;
        int size = arrayList.size();
        boolean z3 = false;
        for (int i3 = 0; i3 < size; i3++) {
            q qVar = (q) arrayList.get(i3);
            if (qVar.f1088b == i2) {
                int i4 = qVar.f1110x;
                int i5 = (i4 & (-9)) | (z2 ? 0 : 8);
                qVar.f1110x = i5;
                if (i4 != i5) {
                    z3 = true;
                }
            }
        }
        if (z3) {
            p(true);
        }
    }

    @Override // android.view.Menu
    public void setQwertyMode(boolean z2) {
        this.f1062c = z2;
        p(false);
    }

    @Override // android.view.Menu
    public final int size() {
        return this.f1065f.size();
    }

    public final void t(Bundle bundle) {
        int size = size();
        SparseArray<? extends Parcelable> sparseArray = null;
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = getItem(i2);
            View actionView = item.getActionView();
            if (actionView != null && actionView.getId() != -1) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray<>();
                }
                actionView.saveHierarchyState(sparseArray);
                if (item.isActionViewExpanded()) {
                    bundle.putInt("android:menu:expandedactionview", item.getItemId());
                }
            }
            if (item.hasSubMenu()) {
                ((h0) item.getSubMenu()).t(bundle);
            }
        }
        if (sparseArray != null) {
            bundle.putSparseParcelableArray(j(), sparseArray);
        }
    }

    public final void u(int i2, CharSequence charSequence, int i3, Drawable drawable, View view) {
        if (view != null) {
            this.f1074o = view;
            this.f1072m = null;
            this.f1073n = null;
        } else {
            if (i2 > 0) {
                this.f1072m = this.f1061b.getText(i2);
            } else if (charSequence != null) {
                this.f1072m = charSequence;
            }
            if (i3 > 0) {
                Object obj = o.a.f1732a;
                this.f1073n = this.f1060a.getDrawable(i3);
            } else if (drawable != null) {
                this.f1073n = drawable;
            }
            this.f1074o = null;
        }
        p(false);
    }

    public final void v() {
        this.f1075p = false;
        if (this.f1076q) {
            this.f1076q = false;
            p(this.f1077r);
        }
    }

    public final void w() {
        if (this.f1075p) {
            return;
        }
        this.f1075p = true;
        this.f1076q = false;
        this.f1077r = false;
    }

    @Override // android.view.Menu
    public final MenuItem add(int i2, int i3, int i4, int i5) {
        return a(i2, i3, i4, this.f1061b.getString(i5));
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i2, int i3, int i4, int i5) {
        return addSubMenu(i2, i3, i4, this.f1061b.getString(i5));
    }

    @Override // android.view.Menu
    public final MenuItem add(int i2, int i3, int i4, CharSequence charSequence) {
        return a(i2, i3, i4, charSequence);
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i2, int i3, int i4, CharSequence charSequence) {
        q qVarA = a(i2, i3, i4, charSequence);
        h0 h0Var = new h0(this.f1060a, this, qVarA);
        qVarA.f1101o = h0Var;
        h0Var.setHeaderTitle(qVarA.f1091e);
        return h0Var;
    }

    @Override // android.view.Menu
    public final MenuItem add(CharSequence charSequence) {
        return a(0, 0, 0, charSequence);
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }
}
