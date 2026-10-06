package o;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import j.j;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Class f1739a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Field f1740b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Field f1741c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Method f1742d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Method f1743e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Method f1744f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Handler f1745g = new Handler(Looper.getMainLooper());

    static {
        Class<?> cls;
        Field declaredField;
        Field declaredField2;
        Method declaredMethod;
        Method declaredMethod2;
        Method method = null;
        try {
            cls = Class.forName("android.app.ActivityThread");
        } catch (Throwable unused) {
            cls = null;
        }
        f1739a = cls;
        try {
            declaredField = Activity.class.getDeclaredField("mMainThread");
            declaredField.setAccessible(true);
        } catch (Throwable unused2) {
            declaredField = null;
        }
        f1740b = declaredField;
        try {
            declaredField2 = Activity.class.getDeclaredField("mToken");
            declaredField2.setAccessible(true);
        } catch (Throwable unused3) {
            declaredField2 = null;
        }
        f1741c = declaredField2;
        Class cls2 = f1739a;
        if (cls2 == null) {
            declaredMethod = null;
        } else {
            try {
                declaredMethod = cls2.getDeclaredMethod("performStopActivity", IBinder.class, Boolean.TYPE, String.class);
                declaredMethod.setAccessible(true);
            } catch (Throwable unused4) {
                declaredMethod = null;
            }
        }
        f1742d = declaredMethod;
        Class cls3 = f1739a;
        if (cls3 == null) {
            declaredMethod2 = null;
        } else {
            try {
                declaredMethod2 = cls3.getDeclaredMethod("performStopActivity", IBinder.class, Boolean.TYPE);
                declaredMethod2.setAccessible(true);
            } catch (Throwable unused5) {
                declaredMethod2 = null;
            }
        }
        f1743e = declaredMethod2;
        Class cls4 = f1739a;
        int i2 = Build.VERSION.SDK_INT;
        if ((i2 == 26 || i2 == 27) && cls4 != null) {
            try {
                Class<?> cls5 = Boolean.TYPE;
                Method declaredMethod3 = cls4.getDeclaredMethod("requestRelaunchActivity", IBinder.class, List.class, List.class, Integer.TYPE, cls5, Configuration.class, Configuration.class, cls5, cls5);
                declaredMethod3.setAccessible(true);
                method = declaredMethod3;
            } catch (Throwable unused6) {
            }
        }
        f1744f = method;
    }

    public static boolean a(Activity activity) {
        Object obj;
        int i2 = Build.VERSION.SDK_INT;
        int i3 = 1;
        if (i2 >= 28) {
            activity.recreate();
            return true;
        }
        int i4 = 0;
        boolean z2 = i2 == 26 || i2 == 27;
        Method method = f1744f;
        if (z2 && method == null) {
            return false;
        }
        if (f1743e == null && f1742d == null) {
            return false;
        }
        try {
            Object obj2 = f1741c.get(activity);
            if (obj2 == null || (obj = f1740b.get(activity)) == null) {
                return false;
            }
            Application application = activity.getApplication();
            b bVar = new b(activity);
            application.registerActivityLifecycleCallbacks(bVar);
            Handler handler = f1745g;
            handler.post(new j(bVar, obj2, i3, i4));
            int i5 = 2;
            try {
                if (i2 == 26 || i2 == 27) {
                    Boolean bool = Boolean.FALSE;
                    method.invoke(obj, obj2, null, null, 0, bool, null, null, bool, bool);
                } else {
                    activity.recreate();
                }
                handler.post(new j(application, bVar, i5, i4));
                return true;
            } catch (Throwable th) {
                handler.post(new j(application, bVar, i5, i4));
                throw th;
            }
        } catch (Throwable unused) {
            return false;
        }
    }
}
