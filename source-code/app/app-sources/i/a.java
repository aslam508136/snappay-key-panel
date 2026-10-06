package i;

import android.R;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class a implements t.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public CharSequence f968a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public CharSequence f969b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Intent f970c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public char f971d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public char f973f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Drawable f975h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Context f976i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public CharSequence f977j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public CharSequence f978k;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f972e = 4096;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f974g = 4096;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ColorStateList f979l = null;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public PorterDuff.Mode f980m = null;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f981n = false;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f982o = false;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f983p = 16;

    public a(Context context, CharSequence charSequence) {
        this.f976i = context;
        this.f968a = charSequence;
    }

    @Override // t.b
    public final t.b a(r rVar) {
        throw new UnsupportedOperationException();
    }

    @Override // t.b
    public final r b() {
        return null;
    }

    public final void c() {
        Drawable drawable = this.f975h;
        if (drawable != null) {
            if (this.f981n || this.f982o) {
                Drawable drawableK0 = androidx.lifecycle.i.k0(drawable);
                this.f975h = drawableK0;
                Drawable drawableMutate = drawableK0.mutate();
                this.f975h = drawableMutate;
                if (this.f981n) {
                    drawableMutate.setTintList(this.f979l);
                }
                if (this.f982o) {
                    this.f975h.setTintMode(this.f980m);
                }
            }
        }
    }

    @Override // android.view.MenuItem
    public final boolean collapseActionView() {
        return false;
    }

    @Override // android.view.MenuItem
    public final boolean expandActionView() {
        return false;
    }

    @Override // android.view.MenuItem
    public final ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public final View getActionView() {
        return null;
    }

    @Override // t.b, android.view.MenuItem
    public final int getAlphabeticModifiers() {
        return this.f974g;
    }

    @Override // android.view.MenuItem
    public final char getAlphabeticShortcut() {
        return this.f973f;
    }

    @Override // t.b, android.view.MenuItem
    public final CharSequence getContentDescription() {
        return this.f977j;
    }

    @Override // android.view.MenuItem
    public final int getGroupId() {
        return 0;
    }

    @Override // android.view.MenuItem
    public final Drawable getIcon() {
        return this.f975h;
    }

    @Override // t.b, android.view.MenuItem
    public final ColorStateList getIconTintList() {
        return this.f979l;
    }

    @Override // t.b, android.view.MenuItem
    public final PorterDuff.Mode getIconTintMode() {
        return this.f980m;
    }

    @Override // android.view.MenuItem
    public final Intent getIntent() {
        return this.f970c;
    }

    @Override // android.view.MenuItem
    public final int getItemId() {
        return R.id.home;
    }

    @Override // android.view.MenuItem
    public final ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    @Override // t.b, android.view.MenuItem
    public final int getNumericModifiers() {
        return this.f972e;
    }

    @Override // android.view.MenuItem
    public final char getNumericShortcut() {
        return this.f971d;
    }

    @Override // android.view.MenuItem
    public final int getOrder() {
        return 0;
    }

    @Override // android.view.MenuItem
    public final SubMenu getSubMenu() {
        return null;
    }

    @Override // android.view.MenuItem
    public final CharSequence getTitle() {
        return this.f968a;
    }

    @Override // android.view.MenuItem
    public final CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f969b;
        return charSequence != null ? charSequence : this.f968a;
    }

    @Override // t.b, android.view.MenuItem
    public final CharSequence getTooltipText() {
        return this.f978k;
    }

    @Override // android.view.MenuItem
    public final boolean hasSubMenu() {
        return false;
    }

    @Override // android.view.MenuItem
    public final boolean isActionViewExpanded() {
        return false;
    }

    @Override // android.view.MenuItem
    public final boolean isCheckable() {
        return (this.f983p & 1) != 0;
    }

    @Override // android.view.MenuItem
    public final boolean isChecked() {
        return (this.f983p & 2) != 0;
    }

    @Override // android.view.MenuItem
    public final boolean isEnabled() {
        return (this.f983p & 16) != 0;
    }

    @Override // android.view.MenuItem
    public final boolean isVisible() {
        return (this.f983p & 8) == 0;
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionView(int i2) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public final MenuItem setAlphabeticShortcut(char c2) {
        this.f973f = Character.toLowerCase(c2);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setCheckable(boolean z2) {
        this.f983p = (z2 ? 1 : 0) | (this.f983p & (-2));
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setChecked(boolean z2) {
        this.f983p = (z2 ? 2 : 0) | (this.f983p & (-3));
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setContentDescription(CharSequence charSequence) {
        this.f977j = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setEnabled(boolean z2) {
        this.f983p = (z2 ? 16 : 0) | (this.f983p & (-17));
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIcon(int i2) {
        Object obj = o.a.f1732a;
        this.f975h = this.f976i.getDrawable(i2);
        c();
        return this;
    }

    @Override // t.b, android.view.MenuItem
    public final MenuItem setIconTintList(ColorStateList colorStateList) {
        this.f979l = colorStateList;
        this.f981n = true;
        c();
        return this;
    }

    @Override // t.b, android.view.MenuItem
    public final MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.f980m = mode;
        this.f982o = true;
        c();
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIntent(Intent intent) {
        this.f970c = intent;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setNumericShortcut(char c2) {
        this.f971d = c2;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public final MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setShortcut(char c2, char c3) {
        this.f971d = c2;
        this.f973f = Character.toLowerCase(c3);
        return this;
    }

    @Override // android.view.MenuItem
    public final void setShowAsAction(int i2) {
    }

    @Override // android.view.MenuItem
    public final MenuItem setShowAsActionFlags(int i2) {
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitle(int i2) {
        this.f968a = this.f976i.getResources().getString(i2);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f969b = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTooltipText(CharSequence charSequence) {
        this.f978k = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setVisible(boolean z2) {
        this.f983p = (this.f983p & 8) | (z2 ? 0 : 8);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionView(View view) {
        throw new UnsupportedOperationException();
    }

    @Override // t.b, android.view.MenuItem
    public final MenuItem setAlphabeticShortcut(char c2, int i2) {
        this.f973f = Character.toLowerCase(c2);
        this.f974g = KeyEvent.normalizeMetaState(i2);
        return this;
    }

    @Override // t.b, android.view.MenuItem
    public final t.b setContentDescription(CharSequence charSequence) {
        this.f977j = charSequence;
        return this;
    }

    @Override // t.b, android.view.MenuItem
    public final MenuItem setNumericShortcut(char c2, int i2) {
        this.f971d = c2;
        this.f972e = KeyEvent.normalizeMetaState(i2);
        return this;
    }

    @Override // t.b, android.view.MenuItem
    public final MenuItem setShortcut(char c2, char c3, int i2, int i3) {
        this.f971d = c2;
        this.f972e = KeyEvent.normalizeMetaState(i2);
        this.f973f = Character.toLowerCase(c3);
        this.f974g = KeyEvent.normalizeMetaState(i3);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitle(CharSequence charSequence) {
        this.f968a = charSequence;
        return this;
    }

    @Override // t.b, android.view.MenuItem
    public final t.b setTooltipText(CharSequence charSequence) {
        this.f978k = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIcon(Drawable drawable) {
        this.f975h = drawable;
        c();
        return this;
    }
}
