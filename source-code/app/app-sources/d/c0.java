package d;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class c0 implements View.OnClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final View f610b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f611c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Method f612d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Context f613e;

    public c0(View view, String str) {
        this.f610b = view;
        this.f611c = str;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        String str;
        Method method;
        if (this.f612d != null) {
            break;
        }
        View view2 = this.f610b;
        Context context = view2.getContext();
        while (true) {
            String str2 = this.f611c;
            if (context == null) {
                int id = view2.getId();
                if (id == -1) {
                    str = "";
                } else {
                    str = " with id '" + view2.getContext().getResources().getResourceEntryName(id) + "'";
                }
                throw new IllegalStateException("Could not find method " + str2 + "(View) in a parent or ancestor Context for android:onClick attribute defined on view " + view2.getClass() + str);
            }
            try {
                if (!context.isRestricted() && (method = context.getClass().getMethod(str2, View.class)) != null) {
                    this.f612d = method;
                    this.f613e = context;
                    break;
                }
            } catch (NoSuchMethodException unused) {
            }
            context = context instanceof ContextWrapper ? ((ContextWrapper) context).getBaseContext() : null;
        }
        try {
            this.f612d.invoke(this.f613e, view);
        } catch (IllegalAccessException e2) {
            throw new IllegalStateException("Could not execute non-public method for android:onClick", e2);
        } catch (InvocationTargetException e3) {
            throw new IllegalStateException("Could not execute method for android:onClick", e3);
        }
    }
}
