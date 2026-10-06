package r;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class g extends e {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Class f1907i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Constructor f1908j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Method f1909k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Method f1910l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Method f1911m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Method f1912n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final Method f1913o;

    public g() {
        Class<?> cls;
        Method method;
        Constructor<?> constructor;
        Method methodU;
        Method methodV;
        Method method2;
        Method methodW;
        try {
            cls = Class.forName("android.graphics.FontFamily");
            constructor = cls.getConstructor(new Class[0]);
            methodU = u(cls);
            methodV = v(cls);
            method2 = cls.getMethod("freeze", new Class[0]);
            method = cls.getMethod("abortCreation", new Class[0]);
            methodW = w(cls);
        } catch (ClassNotFoundException | NoSuchMethodException e2) {
            Log.e("TypefaceCompatApi26Impl", "Unable to collect necessary methods for class ".concat(e2.getClass().getName()), e2);
            cls = null;
            method = null;
            constructor = null;
            methodU = null;
            methodV = null;
            method2 = null;
            methodW = null;
        }
        this.f1907i = cls;
        this.f1908j = constructor;
        this.f1909k = methodU;
        this.f1910l = methodV;
        this.f1911m = method2;
        this.f1912n = method;
        this.f1913o = methodW;
    }

    public static Method u(Class cls) {
        Class<?> cls2 = Integer.TYPE;
        return cls.getMethod("addFontFromAssetManager", AssetManager.class, String.class, cls2, Boolean.TYPE, cls2, cls2, cls2, FontVariationAxis[].class);
    }

    public static Method v(Class cls) {
        Class<?> cls2 = Integer.TYPE;
        return cls.getMethod("addFontFromBuffer", ByteBuffer.class, cls2, FontVariationAxis[].class, cls2, cls2);
    }

    @Override // r.e, h.a
    public final Typeface f(Context context, q.c cVar, Resources resources, int i2) {
        Object objNewInstance;
        if (!t()) {
            return super.f(context, cVar, resources, i2);
        }
        try {
            objNewInstance = this.f1908j.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            objNewInstance = null;
        }
        if (objNewInstance == null) {
            return null;
        }
        for (q.d dVar : cVar.f1840a) {
            if (!q(context, objNewInstance, dVar.f1841a, dVar.f1845e, dVar.f1842b, dVar.f1843c ? 1 : 0, FontVariationAxis.fromFontVariationSettings(dVar.f1844d))) {
                try {
                    this.f1912n.invoke(objNewInstance, new Object[0]);
                } catch (IllegalAccessException | InvocationTargetException unused2) {
                }
                return null;
            }
        }
        if (s(objNewInstance)) {
            return r(objNewInstance);
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0064  */
    @Override // r.e, h.a
    public final Typeface g(Context context, u.h[] hVarArr, int i2) {
        Object objNewInstance;
        Typeface typefaceR;
        boolean zBooleanValue;
        if (hVarArr.length < 1) {
            return null;
        }
        if (!t()) {
            u.h hVarL = l(i2, hVarArr);
            try {
                ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(hVarL.f1945a, "r", null);
                if (parcelFileDescriptorOpenFileDescriptor == null) {
                    if (parcelFileDescriptorOpenFileDescriptor != null) {
                        parcelFileDescriptorOpenFileDescriptor.close();
                    }
                    return null;
                }
                try {
                    Typeface typefaceBuild = new Typeface.Builder(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor()).setWeight(hVarL.f1947c).setItalic(hVarL.f1948d).build();
                    parcelFileDescriptorOpenFileDescriptor.close();
                    return typefaceBuild;
                } catch (Throwable th) {
                    try {
                        parcelFileDescriptorOpenFileDescriptor.close();
                        throw th;
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                        throw th;
                    }
                }
            } catch (IOException unused) {
                return null;
            }
        }
        HashMap map = new HashMap();
        for (u.h hVar : hVarArr) {
            if (hVar.f1949e == 0) {
                Uri uri = hVar.f1945a;
                if (!map.containsKey(uri)) {
                    map.put(uri, androidx.lifecycle.i.N(context, uri));
                }
            }
        }
        Map mapUnmodifiableMap = Collections.unmodifiableMap(map);
        try {
            objNewInstance = this.f1908j.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused2) {
            objNewInstance = null;
        }
        if (objNewInstance == null) {
            return null;
        }
        int length = hVarArr.length;
        int i3 = 0;
        boolean z2 = false;
        while (true) {
            Method method = this.f1912n;
            if (i3 >= length) {
                if (!z2) {
                    try {
                        method.invoke(objNewInstance, new Object[0]);
                        return null;
                    } catch (IllegalAccessException | InvocationTargetException unused3) {
                        return null;
                    }
                }
                if (s(objNewInstance) && (typefaceR = r(objNewInstance)) != null) {
                    return Typeface.create(typefaceR, i2);
                }
                return null;
            }
            u.h hVar2 = hVarArr[i3];
            ByteBuffer byteBuffer = (ByteBuffer) mapUnmodifiableMap.get(hVar2.f1945a);
            if (byteBuffer != null) {
                try {
                    zBooleanValue = ((Boolean) this.f1910l.invoke(objNewInstance, byteBuffer, Integer.valueOf(hVar2.f1946b), null, Integer.valueOf(hVar2.f1947c), Integer.valueOf(hVar2.f1948d ? 1 : 0))).booleanValue();
                } catch (IllegalAccessException | InvocationTargetException unused4) {
                    zBooleanValue = false;
                }
                if (!zBooleanValue) {
                    try {
                        method.invoke(objNewInstance, new Object[0]);
                        return null;
                    } catch (IllegalAccessException | InvocationTargetException unused5) {
                        return null;
                    }
                }
                z2 = true;
            }
            i3++;
        }
    }

    @Override // h.a
    public final Typeface i(Context context, Resources resources, int i2, String str, int i3) {
        Object objNewInstance;
        if (!t()) {
            return super.i(context, resources, i2, str, i3);
        }
        try {
            objNewInstance = this.f1908j.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            objNewInstance = null;
        }
        if (objNewInstance == null) {
            return null;
        }
        if (!q(context, objNewInstance, str, 0, -1, -1, null)) {
            try {
                this.f1912n.invoke(objNewInstance, new Object[0]);
            } catch (IllegalAccessException | InvocationTargetException unused2) {
            }
            return null;
        }
        if (s(objNewInstance)) {
            return r(objNewInstance);
        }
        return null;
    }

    public final boolean q(Context context, Object obj, String str, int i2, int i3, int i4, FontVariationAxis[] fontVariationAxisArr) {
        try {
            return ((Boolean) this.f1909k.invoke(obj, context.getAssets(), str, 0, Boolean.FALSE, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), fontVariationAxisArr)).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    public Typeface r(Object obj) {
        try {
            Object objNewInstance = Array.newInstance((Class<?>) this.f1907i, 1);
            Array.set(objNewInstance, 0, obj);
            return (Typeface) this.f1913o.invoke(null, objNewInstance, -1, -1);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return null;
        }
    }

    public final boolean s(Object obj) {
        try {
            return ((Boolean) this.f1911m.invoke(obj, new Object[0])).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    public final boolean t() {
        Method method = this.f1909k;
        if (method == null) {
            Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        return method != null;
    }

    public Method w(Class cls) throws NoSuchMethodException {
        Class cls2 = Integer.TYPE;
        Method declaredMethod = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", Array.newInstance((Class<?>) cls, 1).getClass(), cls2, cls2);
        declaredMethod.setAccessible(true);
        return declaredMethod;
    }
}
