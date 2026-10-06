package a0;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.Editable;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class j implements ActionMode.Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ActionMode.Callback f31a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final TextView f32b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Class f33c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Method f34d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f35e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f36f = false;

    public j(ActionMode.Callback callback, TextView textView) {
        this.f31a = callback;
        this.f32b = textView;
    }

    @Override // android.view.ActionMode.Callback
    public final boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        return this.f31a.onActionItemClicked(actionMode, menuItem);
    }

    @Override // android.view.ActionMode.Callback
    public final boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        return this.f31a.onCreateActionMode(actionMode, menu);
    }

    @Override // android.view.ActionMode.Callback
    public final void onDestroyActionMode(ActionMode actionMode) {
        this.f31a.onDestroyActionMode(actionMode);
    }

    /* JADX WARN: Code duplicated, block: B:42:0x00ce  */
    @Override // android.view.ActionMode.Callback
    public final boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        boolean z2;
        String str;
        TextView textView = this.f32b;
        Context context = textView.getContext();
        PackageManager packageManager = context.getPackageManager();
        if (!this.f36f) {
            this.f36f = true;
            try {
                Class<?> cls = Class.forName("com.android.internal.view.menu.MenuBuilder");
                this.f33c = cls;
                this.f34d = cls.getDeclaredMethod("removeItemAt", Integer.TYPE);
                this.f35e = true;
            } catch (ClassNotFoundException | NoSuchMethodException unused) {
                this.f33c = null;
                this.f34d = null;
                this.f35e = false;
            }
        }
        try {
            Method declaredMethod = (this.f35e && this.f33c.isInstance(menu)) ? this.f34d : menu.getClass().getDeclaredMethod("removeItemAt", Integer.TYPE);
            for (int size = menu.size() - 1; size >= 0; size--) {
                MenuItem item = menu.getItem(size);
                if (item.getIntent() != null && "android.intent.action.PROCESS_TEXT".equals(item.getIntent().getAction())) {
                    declaredMethod.invoke(menu, Integer.valueOf(size));
                }
            }
            ArrayList arrayList = new ArrayList();
            if (context instanceof Activity) {
                for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(new Intent().setAction("android.intent.action.PROCESS_TEXT").setType("text/plain"), 0)) {
                    if (context.getPackageName().equals(resolveInfo.activityInfo.packageName)) {
                        z2 = true;
                    } else {
                        ActivityInfo activityInfo = resolveInfo.activityInfo;
                        if (activityInfo.exported && ((str = activityInfo.permission) == null || context.checkSelfPermission(str) == 0)) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                    }
                    if (z2) {
                        arrayList.add(resolveInfo);
                    }
                }
            }
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                ResolveInfo resolveInfo2 = (ResolveInfo) arrayList.get(i2);
                MenuItem menuItemAdd = menu.add(0, 0, i2 + 100, resolveInfo2.loadLabel(packageManager));
                Intent intentPutExtra = new Intent().setAction("android.intent.action.PROCESS_TEXT").setType("text/plain").putExtra("android.intent.extra.PROCESS_TEXT_READONLY", !((textView instanceof Editable) && textView.onCheckIsTextEditor() && textView.isEnabled()));
                ActivityInfo activityInfo2 = resolveInfo2.activityInfo;
                menuItemAdd.setIntent(intentPutExtra.setClassName(activityInfo2.packageName, activityInfo2.name)).setShowAsAction(1);
            }
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused2) {
        }
        return this.f31a.onPrepareActionMode(actionMode, menu);
    }
}
