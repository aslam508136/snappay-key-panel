package r;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.ParcelFileDescriptor;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class e extends h.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Class f1898d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static Constructor f1899e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static Method f1900f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static Method f1901g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static boolean f1902h = false;

    public e() {
        super(0);
    }

    public static boolean o(Object obj, String str, int i2, boolean z2) {
        p();
        try {
            return ((Boolean) f1900f.invoke(obj, str, Integer.valueOf(i2), Boolean.valueOf(z2))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static void p() {
        Class<?> cls;
        Method method;
        Constructor<?> constructor;
        Method method2;
        if (f1902h) {
            return;
        }
        f1902h = true;
        try {
            cls = Class.forName("android.graphics.FontFamily");
            constructor = cls.getConstructor(new Class[0]);
            method2 = cls.getMethod("addFontWeightStyle", String.class, Integer.TYPE, Boolean.TYPE);
            method = Typeface.class.getMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass());
        } catch (ClassNotFoundException | NoSuchMethodException e2) {
            Log.e("TypefaceCompatApi21Impl", e2.getClass().getName(), e2);
            cls = null;
            method = null;
            constructor = null;
            method2 = null;
        }
        f1899e = constructor;
        f1898d = cls;
        f1900f = method2;
        f1901g = method;
    }

    @Override // h.a
    public Typeface f(Context context, q.c cVar, Resources resources, int i2) {
        p();
        try {
            Object objNewInstance = f1899e.newInstance(new Object[0]);
            for (q.d dVar : cVar.f1840a) {
                File fileG = androidx.lifecycle.i.G(context);
                if (fileG == null) {
                    return null;
                }
                try {
                    if (!androidx.lifecycle.i.l(fileG, resources, dVar.f1846f)) {
                        fileG.delete();
                        return null;
                    }
                    boolean zO = o(objNewInstance, fileG.getPath(), dVar.f1842b, dVar.f1843c);
                    fileG.delete();
                    if (!zO) {
                        return null;
                    }
                } catch (RuntimeException unused) {
                    fileG.delete();
                    return null;
                } catch (Throwable th) {
                    fileG.delete();
                    throw th;
                }
            }
            p();
            try {
                Object objNewInstance2 = Array.newInstance((Class<?>) f1898d, 1);
                Array.set(objNewInstance2, 0, objNewInstance);
                return (Typeface) f1901g.invoke(null, objNewInstance2);
            } catch (IllegalAccessException | InvocationTargetException e2) {
                throw new RuntimeException(e2);
            }
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e3) {
            throw new RuntimeException(e3);
        }
    }

    @Override // h.a
    public Typeface g(Context context, u.h[] hVarArr, int i2) {
        File file;
        if (hVarArr.length < 1) {
            return null;
        }
        try {
            ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(l(i2, hVarArr).f1945a, "r", null);
            if (parcelFileDescriptorOpenFileDescriptor == null) {
                if (parcelFileDescriptorOpenFileDescriptor != null) {
                    parcelFileDescriptorOpenFileDescriptor.close();
                }
                return null;
            }
            try {
                try {
                    String str = Os.readlink("/proc/self/fd/" + parcelFileDescriptorOpenFileDescriptor.getFd());
                    file = OsConstants.S_ISREG(Os.stat(str).st_mode) ? new File(str) : null;
                } catch (ErrnoException unused) {
                }
                if (file != null && file.canRead()) {
                    Typeface typefaceCreateFromFile = Typeface.createFromFile(file);
                    parcelFileDescriptorOpenFileDescriptor.close();
                    return typefaceCreateFromFile;
                }
                FileInputStream fileInputStream = new FileInputStream(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor());
                try {
                    Typeface typefaceH = h(context, fileInputStream);
                    fileInputStream.close();
                    parcelFileDescriptorOpenFileDescriptor.close();
                    return typefaceH;
                } catch (Throwable th) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                try {
                    parcelFileDescriptorOpenFileDescriptor.close();
                } catch (Throwable th4) {
                    th3.addSuppressed(th4);
                }
                throw th3;
            }
        } catch (IOException unused2) {
            return null;
        }
    }
}
