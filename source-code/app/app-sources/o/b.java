package o;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import j.j;

/* JADX INFO: loaded from: classes.dex */
public final class b implements Application.ActivityLifecycleCallbacks {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object f1733a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Activity f1734b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1735c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1736d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1737e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1738f = false;

    public b(Activity activity) {
        this.f1734b = activity;
        this.f1735c = activity.hashCode();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        if (this.f1734b == activity) {
            this.f1734b = null;
            this.f1737e = true;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        if (!this.f1737e || this.f1738f || this.f1736d) {
            return;
        }
        Object obj = this.f1733a;
        int i2 = 0;
        try {
            Object obj2 = c.f1741c.get(activity);
            if (obj2 == obj && activity.hashCode() == this.f1735c) {
                c.f1745g.postAtFrontOfQueue(new j(c.f1740b.get(activity), obj2, 3, i2));
                i2 = 1;
            }
        } catch (Throwable th) {
            Log.e("ActivityRecreator", "Exception while fetching field values", th);
        }
        if (i2 != 0) {
            this.f1738f = true;
            this.f1733a = null;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
        if (this.f1734b == activity) {
            this.f1736d = true;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }
}
