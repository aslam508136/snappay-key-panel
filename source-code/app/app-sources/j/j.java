package j;

import android.app.Application;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class j implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1265a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f1266b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f1267c;

    public /* synthetic */ j(Object obj, Object obj2, int i2) {
        this.f1265a = i2;
        this.f1267c = obj;
        this.f1266b = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        i.m mVar;
        boolean z2 = false;
        int i2 = this.f1265a;
        Object obj = this.f1266b;
        Object obj2 = this.f1267c;
        switch (i2) {
            case 0:
                m mVar2 = (m) obj2;
                i.o oVar = mVar2.f1296d;
                if (oVar != null && (mVar = oVar.f1064e) != null) {
                    mVar.b(oVar);
                }
                View view = (View) mVar2.f1301i;
                if (view != null && view.getWindowToken() != null) {
                    h hVar = (h) obj;
                    if (hVar.b()) {
                        z2 = true;
                    } else if (hVar.f1130f != null) {
                        hVar.d(0, 0, false, false);
                        z2 = true;
                    }
                    if (z2) {
                        mVar2.f1312t = hVar;
                    }
                }
                mVar2.f1314v = null;
                return;
            case 1:
                ((o.b) obj).f1733a = obj2;
                return;
            case 2:
                ((Application) obj).unregisterActivityLifecycleCallbacks((o.b) obj2);
                return;
            case 3:
                try {
                    Method method = o.c.f1742d;
                    if (method != null) {
                        method.invoke(obj, obj2, Boolean.FALSE, "AppCompat recreation");
                    } else {
                        o.c.f1743e.invoke(obj, obj2, Boolean.FALSE);
                    }
                    return;
                } catch (RuntimeException e2) {
                    if (e2.getClass() == RuntimeException.class && e2.getMessage() != null && e2.getMessage().startsWith("Unable to stop")) {
                        throw e2;
                    }
                    return;
                } catch (Throwable th) {
                    Log.e("ActivityRecreator", "Exception while invoking performStopActivity", th);
                    return;
                }
            default:
                ((t0) obj2).c((Typeface) obj);
                return;
        }
    }

    public /* synthetic */ j(Object obj, Object obj2, int i2, int i3) {
        this.f1265a = i2;
        this.f1266b = obj;
        this.f1267c = obj2;
    }
}
