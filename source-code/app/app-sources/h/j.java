package h;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Build;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import i.q;
import i.r;
import i.w;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class j {
    public CharSequence A;
    public CharSequence B;
    public final /* synthetic */ k E;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Menu f915a;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f922h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f923i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f924j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public CharSequence f925k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public CharSequence f926l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f927m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public char f928n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f929o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public char f930p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f931q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f932r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f933s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f934t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f935u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f936v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f937w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public String f938x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public String f939y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public r f940z;
    public ColorStateList C = null;
    public PorterDuff.Mode D = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f916b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f917c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f918d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f919e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f920f = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f921g = true;

    public j(k kVar, Menu menu) {
        this.E = kVar;
        this.f915a = menu;
    }

    public final Object a(String str, Class[] clsArr, Object[] objArr) {
        try {
            Constructor<?> constructor = Class.forName(str, false, this.E.f945c.getClassLoader()).getConstructor(clsArr);
            constructor.setAccessible(true);
            return constructor.newInstance(objArr);
        } catch (Exception e2) {
            Log.w("SupportMenuInflater", "Cannot instantiate class: " + str, e2);
            return null;
        }
    }

    public final void b(MenuItem menuItem) {
        boolean z2 = false;
        menuItem.setChecked(this.f933s).setVisible(this.f934t).setEnabled(this.f935u).setCheckable(this.f932r >= 1).setTitleCondensed(this.f926l).setIcon(this.f927m);
        int i2 = this.f936v;
        if (i2 >= 0) {
            menuItem.setShowAsAction(i2);
        }
        String str = this.f939y;
        k kVar = this.E;
        if (str != null) {
            if (kVar.f945c.isRestricted()) {
                throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
            }
            if (kVar.f946d == null) {
                kVar.f946d = k.a(kVar.f945c);
            }
            menuItem.setOnMenuItemClickListener(new i(kVar.f946d, this.f939y));
        }
        if (this.f932r >= 2) {
            if (menuItem instanceof q) {
                q qVar = (q) menuItem;
                qVar.f1110x = (qVar.f1110x & (-5)) | 4;
            } else if (menuItem instanceof w) {
                w wVar = (w) menuItem;
                try {
                    Method method = wVar.f1122e;
                    t.b bVar = wVar.f1121d;
                    if (method == null) {
                        wVar.f1122e = bVar.getClass().getDeclaredMethod("setExclusiveCheckable", Boolean.TYPE);
                    }
                    wVar.f1122e.invoke(bVar, Boolean.TRUE);
                } catch (Exception e2) {
                    Log.w("MenuItemWrapper", "Error while calling setExclusiveCheckable", e2);
                }
            }
        }
        String str2 = this.f938x;
        if (str2 != null) {
            menuItem.setActionView((View) a(str2, k.f941e, kVar.f943a));
            z2 = true;
        }
        int i3 = this.f937w;
        if (i3 > 0) {
            if (z2) {
                Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
            } else {
                menuItem.setActionView(i3);
            }
        }
        r rVar = this.f940z;
        if (rVar != null) {
            if (menuItem instanceof t.b) {
                ((t.b) menuItem).a(rVar);
            } else {
                Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
            }
        }
        CharSequence charSequence = this.A;
        boolean z3 = menuItem instanceof t.b;
        if (z3) {
            ((t.b) menuItem).setContentDescription(charSequence);
        } else if (Build.VERSION.SDK_INT >= 26) {
            menuItem.setContentDescription(charSequence);
        }
        CharSequence charSequence2 = this.B;
        if (z3) {
            ((t.b) menuItem).setTooltipText(charSequence2);
        } else if (Build.VERSION.SDK_INT >= 26) {
            menuItem.setTooltipText(charSequence2);
        }
        char c2 = this.f928n;
        int i4 = this.f929o;
        if (z3) {
            ((t.b) menuItem).setAlphabeticShortcut(c2, i4);
        } else if (Build.VERSION.SDK_INT >= 26) {
            menuItem.setAlphabeticShortcut(c2, i4);
        }
        char c3 = this.f930p;
        int i5 = this.f931q;
        if (z3) {
            ((t.b) menuItem).setNumericShortcut(c3, i5);
        } else if (Build.VERSION.SDK_INT >= 26) {
            menuItem.setNumericShortcut(c3, i5);
        }
        PorterDuff.Mode mode = this.D;
        if (mode != null) {
            if (z3) {
                ((t.b) menuItem).setIconTintMode(mode);
            } else if (Build.VERSION.SDK_INT >= 26) {
                menuItem.setIconTintMode(mode);
            }
        }
        ColorStateList colorStateList = this.C;
        if (colorStateList != null) {
            if (z3) {
                ((t.b) menuItem).setIconTintList(colorStateList);
            } else if (Build.VERSION.SDK_INT >= 26) {
                menuItem.setIconTintList(colorStateList);
            }
        }
    }
}
