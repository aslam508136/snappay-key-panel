package r;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import j.s;
import j.s0;
import j.t0;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import m.j;

/* JADX INFO: loaded from: classes.dex */
public abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final h.a f1896a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final m.e f1897b;

    /* JADX WARN: Code duplicated, block: B:22:0x003e  */
    static {
        h.a eVar;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 29) {
            eVar = new i();
        } else if (i2 >= 28) {
            eVar = new h();
        } else if (i2 >= 26) {
            eVar = new g();
        } else if (i2 < 24) {
            eVar = new e();
        } else {
            Method method = f.f1905f;
            if (method == null) {
                Log.w("TypefaceCompatApi24Impl", "Unable to collect necessary private methods.Fallback to legacy implementation.");
            }
            if (method != null) {
                eVar = new f();
            } else {
                eVar = new e();
            }
        }
        f1896a = eVar;
        f1897b = new m.e(16);
    }

    /* JADX WARN: Code duplicated, block: B:14:0x002c  */
    public static Typeface a(Context context, q.b bVar, Resources resources, int i2, int i3, t0 t0Var) {
        Typeface typefaceF;
        Typeface typefaceCreate;
        Typeface typeface;
        if (bVar instanceof q.e) {
            q.e eVar = (q.e) bVar;
            String str = eVar.f1850d;
            int i4 = 0;
            typefaceF = null;
            if (str == null || str.isEmpty()) {
                typefaceCreate = null;
            } else {
                typefaceCreate = Typeface.create(str, 0);
                Typeface typefaceCreate2 = Typeface.create(Typeface.DEFAULT, 0);
                if (typefaceCreate == null || typefaceCreate.equals(typefaceCreate2)) {
                    typefaceCreate = null;
                }
            }
            if (typefaceCreate != null) {
                t0Var.b(typefaceCreate);
                return typefaceCreate;
            }
            int i5 = 1;
            boolean z2 = eVar.f1849c == 0;
            int i6 = eVar.f1848b;
            Handler handler = new Handler(Looper.getMainLooper());
            h.a aVar = new h.a(t0Var);
            s sVar = eVar.f1847a;
            s0 s0Var = new s0(aVar, handler);
            if (z2) {
                m.e eVar2 = u.g.f1941a;
                String str2 = ((String) sVar.f1406g) + "-" + i3;
                typeface = (Typeface) u.g.f1941a.a(str2);
                if (typeface != null) {
                    ((Handler) s0Var.f1408b).post(new u.a(s0Var, aVar, typeface, i4));
                    typefaceF = typeface;
                } else if (i6 == -1) {
                    u.f fVarA = u.g.a(str2, context, sVar, i3);
                    s0Var.c(fVarA);
                    typefaceF = fVarA.f1939a;
                } else {
                    try {
                        try {
                            try {
                                try {
                                    u.f fVar = (u.f) u.g.f1942b.submit(new u.d(str2, context, sVar, i3, 0)).get(i6, TimeUnit.MILLISECONDS);
                                    s0Var.c(fVar);
                                    typefaceF = fVar.f1939a;
                                } catch (TimeoutException unused) {
                                    throw new InterruptedException("timeout");
                                }
                            } catch (InterruptedException e2) {
                                throw e2;
                            }
                        } catch (ExecutionException e3) {
                            throw new RuntimeException(e3);
                        }
                    } catch (InterruptedException unused2) {
                        ((Handler) s0Var.f1408b).post(new u.b((h.a) s0Var.f1407a, -3));
                    }
                }
            } else {
                m.e eVar3 = u.g.f1941a;
                String str3 = ((String) sVar.f1406g) + "-" + i3;
                typeface = (Typeface) u.g.f1941a.a(str3);
                if (typeface != null) {
                    ((Handler) s0Var.f1408b).post(new u.a(s0Var, aVar, typeface, i4));
                    typefaceF = typeface;
                } else {
                    u.e eVar4 = new u.e(s0Var, i4);
                    synchronized (u.g.f1943c) {
                        j jVar = u.g.f1944d;
                        ArrayList arrayList = (ArrayList) jVar.getOrDefault(str3, null);
                        if (arrayList != null) {
                            arrayList.add(eVar4);
                        } else {
                            ArrayList arrayList2 = new ArrayList();
                            arrayList2.add(eVar4);
                            jVar.put(str3, arrayList2);
                            u.g.f1942b.execute(new u.a(Looper.myLooper() == null ? new Handler(Looper.getMainLooper()) : new Handler(), new u.d(str3, context, sVar, i3, 1), new u.e(str3, i5), 2));
                        }
                    }
                }
            }
        } else {
            typefaceF = f1896a.f(context, (q.c) bVar, resources, i3);
            if (typefaceF != null) {
                t0Var.b(typefaceF);
            } else {
                t0Var.a();
            }
        }
        if (typefaceF != null) {
            f1897b.b(b(resources, i2, i3), typefaceF);
        }
        return typefaceF;
    }

    public static String b(Resources resources, int i2, int i3) {
        return resources.getResourcePackageName(i2) + "-" + i2 + "-" + i3;
    }
}
