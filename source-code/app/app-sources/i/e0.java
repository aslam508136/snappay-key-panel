package i;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

/* JADX INFO: loaded from: classes.dex */
public class e0 extends d implements Menu {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final t.a f990d;

    public e0(Context context, t.a aVar) {
        super(context);
        if (aVar == null) {
            throw new IllegalArgumentException("Wrapped Object can not be null.");
        }
        this.f990d = aVar;
    }

    @Override // android.view.Menu
    public final MenuItem add(int i2) {
        return m(((o) this.f990d).add(i2));
    }

    @Override // android.view.Menu
    public final int addIntentOptions(int i2, int i3, int i4, ComponentName componentName, Intent[] intentArr, Intent intent, int i5, MenuItem[] menuItemArr) {
        MenuItem[] menuItemArr2 = menuItemArr != null ? new MenuItem[menuItemArr.length] : null;
        int iAddIntentOptions = ((o) this.f990d).addIntentOptions(i2, i3, i4, componentName, intentArr, intent, i5, menuItemArr2);
        if (menuItemArr2 != null) {
            int length = menuItemArr2.length;
            for (int i6 = 0; i6 < length; i6++) {
                menuItemArr[i6] = m(menuItemArr2[i6]);
            }
        }
        return iAddIntentOptions;
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i2) {
        return ((o) this.f990d).addSubMenu(i2);
    }

    @Override // android.view.Menu
    public final void clear() {
        m.j jVar = (m.j) this.f986b;
        if (jVar != null) {
            jVar.clear();
        }
        m.j jVar2 = (m.j) this.f987c;
        if (jVar2 != null) {
            jVar2.clear();
        }
        ((o) this.f990d).clear();
    }

    @Override // android.view.Menu
    public final void close() {
        ((o) this.f990d).c(true);
    }

    @Override // android.view.Menu
    public final MenuItem findItem(int i2) {
        return m(((o) this.f990d).findItem(i2));
    }

    @Override // android.view.Menu
    public final MenuItem getItem(int i2) {
        return m(((o) this.f990d).getItem(i2));
    }

    @Override // android.view.Menu
    public final boolean hasVisibleItems() {
        return ((o) this.f990d).hasVisibleItems();
    }

    @Override // android.view.Menu
    public final boolean isShortcutKey(int i2, KeyEvent keyEvent) {
        return ((o) this.f990d).isShortcutKey(i2, keyEvent);
    }

    @Override // android.view.Menu
    public final boolean performIdentifierAction(int i2, int i3) {
        return ((o) this.f990d).performIdentifierAction(i2, i3);
    }

    @Override // android.view.Menu
    public final boolean performShortcut(int i2, KeyEvent keyEvent, int i3) {
        return ((o) this.f990d).performShortcut(i2, keyEvent, i3);
    }

    @Override // android.view.Menu
    public final void removeGroup(int i2) {
        if (((m.j) this.f986b) != null) {
            int i3 = 0;
            while (true) {
                m.j jVar = (m.j) this.f986b;
                if (i3 >= jVar.f1635c) {
                    break;
                }
                if (((t.b) jVar.h(i3)).getGroupId() == i2) {
                    ((m.j) this.f986b).i(i3);
                    i3--;
                }
                i3++;
            }
        }
        ((o) this.f990d).removeGroup(i2);
    }

    @Override // android.view.Menu
    public final void removeItem(int i2) {
        if (((m.j) this.f986b) != null) {
            int i3 = 0;
            while (true) {
                m.j jVar = (m.j) this.f986b;
                if (i3 >= jVar.f1635c) {
                    break;
                }
                if (((t.b) jVar.h(i3)).getItemId() == i2) {
                    ((m.j) this.f986b).i(i3);
                    break;
                }
                i3++;
            }
        }
        ((o) this.f990d).removeItem(i2);
    }

    @Override // android.view.Menu
    public final void setGroupCheckable(int i2, boolean z2, boolean z3) {
        ((o) this.f990d).setGroupCheckable(i2, z2, z3);
    }

    @Override // android.view.Menu
    public final void setGroupEnabled(int i2, boolean z2) {
        ((o) this.f990d).setGroupEnabled(i2, z2);
    }

    @Override // android.view.Menu
    public final void setGroupVisible(int i2, boolean z2) {
        ((o) this.f990d).setGroupVisible(i2, z2);
    }

    @Override // android.view.Menu
    public final void setQwertyMode(boolean z2) {
        this.f990d.setQwertyMode(z2);
    }

    @Override // android.view.Menu
    public final int size() {
        return ((o) this.f990d).size();
    }

    @Override // android.view.Menu
    public final MenuItem add(int i2, int i3, int i4, int i5) {
        return m(((o) this.f990d).add(i2, i3, i4, i5));
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i2, int i3, int i4, int i5) {
        return ((o) this.f990d).addSubMenu(i2, i3, i4, i5);
    }

    @Override // android.view.Menu
    public final MenuItem add(int i2, int i3, int i4, CharSequence charSequence) {
        return m(((o) this.f990d).a(i2, i3, i4, charSequence));
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i2, int i3, int i4, CharSequence charSequence) {
        return ((o) this.f990d).addSubMenu(i2, i3, i4, charSequence);
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(CharSequence charSequence) {
        return ((o) this.f990d).addSubMenu(0, 0, 0, charSequence);
    }

    @Override // android.view.Menu
    public final MenuItem add(CharSequence charSequence) {
        return m(((o) this.f990d).a(0, 0, 0, charSequence));
    }
}
