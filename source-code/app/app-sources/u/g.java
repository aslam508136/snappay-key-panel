package u;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import j.s;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public abstract class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final m.e f1941a = new m.e(16);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ThreadPoolExecutor f1942b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object f1943c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final m.j f1944d;

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 10000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque(), new j());
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        f1942b = threadPoolExecutor;
        f1943c = new Object();
        f1944d = new m.j();
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0037 A[EDGE_INSN: B:22:0x0037->B:27:0x0040 BREAK  A[LOOP:0: B:18:0x002d->B:24:0x003b]] */
    public static f a(String str, Context context, s sVar, int i2) {
        int i3;
        m.e eVar = f1941a;
        Typeface typeface = (Typeface) eVar.a(str);
        if (typeface != null) {
            return new f(typeface);
        }
        try {
            d.j jVarX = androidx.lifecycle.i.x(context, sVar);
            int i4 = 1;
            Object obj = jVarX.f705b;
            int i5 = jVarX.f704a;
            if (i5 == 0) {
                h[] hVarArr = (h[]) obj;
                if (hVarArr == null || hVarArr.length == 0) {
                    i3 = i4;
                    break;
                }
                int length = hVarArr.length;
                int i6 = 0;
                while (true) {
                    if (i6 >= length) {
                        i4 = 0;
                        i3 = i4;
                        break;
                    }
                    int i7 = hVarArr[i6].f1949e;
                    if (i7 != 0) {
                        if (i7 >= 0) {
                            i3 = i7;
                            break;
                        }
                        i3 = -3;
                        break;
                    }
                    i6++;
                }
            } else {
                if (i5 != 1) {
                    i3 = -3;
                    break;
                }
                i3 = -2;
            }
            if (i3 != 0) {
                return new f(i3);
            }
            Typeface typefaceG = r.d.f1896a.g(context, (h[]) obj, i2);
            if (typefaceG == null) {
                return new f(-3);
            }
            eVar.b(str, typefaceG);
            return new f(typefaceG);
        } catch (PackageManager.NameNotFoundException unused) {
            return new f(-1);
        }
    }
}
