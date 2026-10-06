package i;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class q implements t.b {
    public r A;
    public MenuItem.OnActionExpandListener B;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f1087a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1088b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1089c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f1090d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public CharSequence f1091e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public CharSequence f1092f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Intent f1093g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public char f1094h;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public char f1096j;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Drawable f1098l;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final o f1100n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public h0 f1101o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public MenuItem.OnMenuItemClickListener f1102p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public CharSequence f1103q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public CharSequence f1104r;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f1111y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public View f1112z;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f1095i = 4096;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f1097k = 4096;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f1099m = 0;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public ColorStateList f1105s = null;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public PorterDuff.Mode f1106t = null;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f1107u = false;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f1108v = false;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f1109w = false;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f1110x = 16;
    public boolean C = false;

    public q(o oVar, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6) {
        this.f1100n = oVar;
        this.f1087a = i3;
        this.f1088b = i2;
        this.f1089c = i4;
        this.f1090d = i5;
        this.f1091e = charSequence;
        this.f1111y = i6;
    }

    public static void c(StringBuilder sb, int i2, int i3, String str) {
        if ((i2 & i3) == i3) {
            sb.append(str);
        }
    }

    @Override // t.b
    public final t.b a(r rVar) {
        r rVar2 = this.A;
        if (rVar2 != null) {
            rVar2.getClass();
        }
        this.f1112z = null;
        this.A = rVar;
        this.f1100n.p(true);
        r rVar3 = this.A;
        if (rVar3 != null) {
            rVar3.d(new h.a(this, 2));
        }
        return this;
    }

    @Override // t.b
    public final r b() {
        return this.A;
    }

    @Override // android.view.MenuItem
    public final boolean collapseActionView() {
        if ((this.f1111y & 8) == 0) {
            return false;
        }
        if (this.f1112z == null) {
            return true;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.B;
        if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionCollapse(this)) {
            return this.f1100n.d(this);
        }
        return false;
    }

    public final Drawable d(Drawable drawable) {
        if (drawable != null && this.f1109w && (this.f1107u || this.f1108v)) {
            drawable = androidx.lifecycle.i.k0(drawable).mutate();
            if (this.f1107u) {
                drawable.setTintList(this.f1105s);
            }
            if (this.f1108v) {
                drawable.setTintMode(this.f1106t);
            }
            this.f1109w = false;
        }
        return drawable;
    }

    public final boolean e() {
        r rVar;
        if ((this.f1111y & 8) == 0) {
            return false;
        }
        if (this.f1112z == null && (rVar = this.A) != null) {
            this.f1112z = rVar.b(this);
        }
        return this.f1112z != null;
    }

    @Override // android.view.MenuItem
    public final boolean expandActionView() {
        if (!e()) {
            return false;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.B;
        if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionExpand(this)) {
            return this.f1100n.f(this);
        }
        return false;
    }

    public final boolean f() {
        return (this.f1110x & 32) == 32;
    }

    public final void g(boolean z2) {
        this.f1110x = z2 ? this.f1110x | 32 : this.f1110x & (-33);
    }

    @Override // android.view.MenuItem
    public final ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }

    @Override // android.view.MenuItem
    public final View getActionView() {
        View view = this.f1112z;
        if (view != null) {
            return view;
        }
        r rVar = this.A;
        if (rVar == null) {
            return null;
        }
        View viewB = rVar.b(this);
        this.f1112z = viewB;
        return viewB;
    }

    @Override // t.b, android.view.MenuItem
    public final int getAlphabeticModifiers() {
        return this.f1097k;
    }

    @Override // android.view.MenuItem
    public final char getAlphabeticShortcut() {
        return this.f1096j;
    }

    @Override // t.b, android.view.MenuItem
    public final CharSequence getContentDescription() {
        return this.f1103q;
    }

    @Override // android.view.MenuItem
    public final int getGroupId() {
        return this.f1088b;
    }

    @Override // android.view.MenuItem
    public final Drawable getIcon() {
        Drawable drawable = this.f1098l;
        if (drawable != null) {
            return d(drawable);
        }
        int i2 = this.f1099m;
        if (i2 == 0) {
            return null;
        }
        Drawable drawableC = e.b.c(this.f1100n.f1060a, i2);
        this.f1099m = 0;
        this.f1098l = drawableC;
        return d(drawableC);
    }

    @Override // t.b, android.view.MenuItem
    public final ColorStateList getIconTintList() {
        return this.f1105s;
    }

    @Override // t.b, android.view.MenuItem
    public final PorterDuff.Mode getIconTintMode() {
        return this.f1106t;
    }

    @Override // android.view.MenuItem
    public final Intent getIntent() {
        return this.f1093g;
    }

    @Override // android.view.MenuItem
    public final int getItemId() {
        return this.f1087a;
    }

    @Override // android.view.MenuItem
    public final ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    @Override // t.b, android.view.MenuItem
    public final int getNumericModifiers() {
        return this.f1095i;
    }

    @Override // android.view.MenuItem
    public final char getNumericShortcut() {
        return this.f1094h;
    }

    @Override // android.view.MenuItem
    public final int getOrder() {
        return this.f1089c;
    }

    @Override // android.view.MenuItem
    public final SubMenu getSubMenu() {
        return this.f1101o;
    }

    @Override // android.view.MenuItem
    public final CharSequence getTitle() {
        return this.f1091e;
    }

    @Override // android.view.MenuItem
    public final CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f1092f;
        return charSequence != null ? charSequence : this.f1091e;
    }

    @Override // t.b, android.view.MenuItem
    public final CharSequence getTooltipText() {
        return this.f1104r;
    }

    @Override // android.view.MenuItem
    public final boolean hasSubMenu() {
        return this.f1101o != null;
    }

    @Override // android.view.MenuItem
    public final boolean isActionViewExpanded() {
        return this.C;
    }

    @Override // android.view.MenuItem
    public final boolean isCheckable() {
        return (this.f1110x & 1) == 1;
    }

    @Override // android.view.MenuItem
    public final boolean isChecked() {
        return (this.f1110x & 2) == 2;
    }

    @Override // android.view.MenuItem
    public final boolean isEnabled() {
        return (this.f1110x & 16) != 0;
    }

    @Override // android.view.MenuItem
    public final boolean isVisible() {
        r rVar = this.A;
        if (rVar == null || !rVar.c()) {
            return (this.f1110x & 8) == 0;
        }
        return (this.f1110x & 8) == 0 && this.A.a();
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionView(int i2) {
        int i3;
        o oVar = this.f1100n;
        Context context = oVar.f1060a;
        View viewInflate = LayoutInflater.from(context).inflate(i2, (ViewGroup) new LinearLayout(context), false);
        this.f1112z = viewInflate;
        this.A = null;
        if (viewInflate != null && viewInflate.getId() == -1 && (i3 = this.f1087a) > 0) {
            viewInflate.setId(i3);
        }
        oVar.f1070k = true;
        oVar.p(true);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setAlphabeticShortcut(char c2) {
        if (this.f1096j == c2) {
            return this;
        }
        this.f1096j = Character.toLowerCase(c2);
        this.f1100n.p(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setCheckable(boolean z2) {
        int i2 = this.f1110x;
        int i3 = (z2 ? 1 : 0) | (i2 & (-2));
        this.f1110x = i3;
        if (i2 != i3) {
            this.f1100n.p(false);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setChecked(boolean z2) {
        int i2 = this.f1110x;
        int i3 = i2 & 4;
        o oVar = this.f1100n;
        if (i3 != 0) {
            oVar.getClass();
            ArrayList arrayList = oVar.f1065f;
            int size = arrayList.size();
            oVar.w();
            for (int i4 = 0; i4 < size; i4++) {
                q qVar = (q) arrayList.get(i4);
                if (qVar.f1088b == this.f1088b) {
                    if (((qVar.f1110x & 4) != 0) && qVar.isCheckable()) {
                        boolean z3 = qVar == this;
                        int i5 = qVar.f1110x;
                        int i6 = (z3 ? 2 : 0) | (i5 & (-3));
                        qVar.f1110x = i6;
                        if (i5 != i6) {
                            qVar.f1100n.p(false);
                        }
                    }
                }
            }
            oVar.v();
        } else {
            int i7 = (z2 ? 2 : 0) | (i2 & (-3));
            this.f1110x = i7;
            if (i2 != i7) {
                oVar.p(false);
            }
        }
        return this;
    }

    @Override // android.view.MenuItem
    public final /* bridge */ /* synthetic */ MenuItem setContentDescription(CharSequence charSequence) {
        setContentDescription(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setEnabled(boolean z2) {
        this.f1110x = z2 ? this.f1110x | 16 : this.f1110x & (-17);
        this.f1100n.p(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIcon(int i2) {
        this.f1098l = null;
        this.f1099m = i2;
        this.f1109w = true;
        this.f1100n.p(false);
        return this;
    }

    @Override // t.b, android.view.MenuItem
    public final MenuItem setIconTintList(ColorStateList colorStateList) {
        this.f1105s = colorStateList;
        this.f1107u = true;
        this.f1109w = true;
        this.f1100n.p(false);
        return this;
    }

    @Override // t.b, android.view.MenuItem
    public final MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.f1106t = mode;
        this.f1108v = true;
        this.f1109w = true;
        this.f1100n.p(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIntent(Intent intent) {
        this.f1093g = intent;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setNumericShortcut(char c2) {
        if (this.f1094h == c2) {
            return this;
        }
        this.f1094h = c2;
        this.f1100n.p(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        this.B = onActionExpandListener;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.f1102p = onMenuItemClickListener;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setShortcut(char c2, char c3) {
        this.f1094h = c2;
        this.f1096j = Character.toLowerCase(c3);
        this.f1100n.p(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final void setShowAsAction(int i2) {
        int i3 = i2 & 3;
        if (i3 != 0 && i3 != 1 && i3 != 2) {
            throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
        }
        this.f1111y = i2;
        o oVar = this.f1100n;
        oVar.f1070k = true;
        oVar.p(true);
    }

    @Override // android.view.MenuItem
    public final MenuItem setShowAsActionFlags(int i2) {
        setShowAsAction(i2);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitle(int i2) {
        setTitle(this.f1100n.f1060a.getString(i2));
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f1092f = charSequence;
        this.f1100n.p(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final /* bridge */ /* synthetic */ MenuItem setTooltipText(CharSequence charSequence) {
        setTooltipText(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setVisible(boolean z2) {
        int i2 = this.f1110x;
        int i3 = (z2 ? 0 : 8) | (i2 & (-9));
        this.f1110x = i3;
        if (i2 != i3) {
            o oVar = this.f1100n;
            oVar.f1067h = true;
            oVar.p(true);
        }
        return this;
    }

    public final String toString() {
        CharSequence charSequence = this.f1091e;
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }

    @Override // t.b, android.view.MenuItem
    public final MenuItem setAlphabeticShortcut(char c2, int i2) {
        if (this.f1096j == c2 && this.f1097k == i2) {
            return this;
        }
        this.f1096j = Character.toLowerCase(c2);
        this.f1097k = KeyEvent.normalizeMetaState(i2);
        this.f1100n.p(false);
        return this;
    }

    @Override // t.b, android.view.MenuItem
    public final t.b setContentDescription(CharSequence charSequence) {
        this.f1103q = charSequence;
        this.f1100n.p(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIcon(Drawable drawable) {
        this.f1099m = 0;
        this.f1098l = drawable;
        this.f1109w = true;
        this.f1100n.p(false);
        return this;
    }

    @Override // t.b, android.view.MenuItem
    public final MenuItem setNumericShortcut(char c2, int i2) {
        if (this.f1094h == c2 && this.f1095i == i2) {
            return this;
        }
        this.f1094h = c2;
        this.f1095i = KeyEvent.normalizeMetaState(i2);
        this.f1100n.p(false);
        return this;
    }

    @Override // t.b, android.view.MenuItem
    public final MenuItem setShortcut(char c2, char c3, int i2, int i3) {
        this.f1094h = c2;
        this.f1095i = KeyEvent.normalizeMetaState(i2);
        this.f1096j = Character.toLowerCase(c3);
        this.f1097k = KeyEvent.normalizeMetaState(i3);
        this.f1100n.p(false);
        return this;
    }

    @Override // t.b, android.view.MenuItem
    public final t.b setTooltipText(CharSequence charSequence) {
        this.f1104r = charSequence;
        this.f1100n.p(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitle(CharSequence charSequence) {
        this.f1091e = charSequence;
        this.f1100n.p(false);
        h0 h0Var = this.f1101o;
        if (h0Var != null) {
            h0Var.setHeaderTitle(charSequence);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionView(View view) {
        int i2;
        this.f1112z = view;
        this.A = null;
        if (view != null && view.getId() == -1 && (i2 = this.f1087a) > 0) {
            view.setId(i2);
        }
        o oVar = this.f1100n;
        oVar.f1070k = true;
        oVar.p(true);
        return this;
    }
}
