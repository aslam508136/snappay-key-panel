package r;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;
import m.j;

/* JADX INFO: loaded from: classes.dex */
public final class f extends h.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Class f1903d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Constructor f1904e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Method f1905f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Method f1906g;

    static {
        Class<?> cls;
        Method method;
        Constructor<?> constructor;
        Method method2;
        try {
            cls = Class.forName("android.graphics.FontFamily");
            constructor = cls.getConstructor(new Class[0]);
            Class<?> cls2 = Integer.TYPE;
            method2 = cls.getMethod("addFontWeightStyle", ByteBuffer.class, cls2, List.class, cls2, Boolean.TYPE);
            method = Typeface.class.getMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass());
        } catch (ClassNotFoundException | NoSuchMethodException e2) {
            Log.e("TypefaceCompatApi24Impl", e2.getClass().getName(), e2);
            cls = null;
            method = null;
            constructor = null;
            method2 = null;
        }
        f1904e = constructor;
        f1903d = cls;
        f1905f = method2;
        f1906g = method;
    }

    public f() {
        super(0);
    }

    public static boolean o(Object obj, ByteBuffer byteBuffer, int i2, int i3, boolean z2) {
        try {
            return ((Boolean) f1905f.invoke(obj, byteBuffer, Integer.valueOf(i2), null, Integer.valueOf(i3), Boolean.valueOf(z2))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    @Override // h.a
    public final Typeface f(Context context, q.c cVar, Resources resources, int i2) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Object objNewInstance;
        MappedByteBuffer map;
        try {
            objNewInstance = f1904e.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            objNewInstance = null;
        }
        if (objNewInstance == null) {
            return null;
        }
        for (q.d dVar : cVar.f1840a) {
            int i3 = dVar.f1846f;
            File fileG = androidx.lifecycle.i.G(context);
            if (fileG == null) {
                map = null;
            } else {
                try {
                    if (androidx.lifecycle.i.l(fileG, resources, i3)) {
                        try {
                            FileInputStream fileInputStream = new FileInputStream(fileG);
                            try {
                                FileChannel channel = fileInputStream.getChannel();
                                map = channel.map(FileChannel.MapMode.READ_ONLY, 0L, channel.size());
                                fileInputStream.close();
                                fileG.delete();
                            } catch (Throwable th) {
                                try {
                                    fileInputStream.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                                throw th;
                            }
                        } catch (IOException unused2) {
                            map = null;
                        }
                    } else {
                        fileG.delete();
                        map = null;
                    }
                } catch (Throwable th3) {
                    fileG.delete();
                    throw th3;
                }
            }
            if (map == null || !o(objNewInstance, map, dVar.f1845e, dVar.f1842b, dVar.f1843c)) {
                return null;
            }
        }
        try {
            Object objNewInstance2 = Array.newInstance((Class<?>) f1903d, 1);
            Array.set(objNewInstance2, 0, objNewInstance);
            return (Typeface) f1906g.invoke(null, objNewInstance2);
        } catch (IllegalAccessException | InvocationTargetException unused3) {
            return null;
        }
    }

    @Override // h.a
    public final Typeface g(Context context, u.h[] hVarArr, int i2) {
        Object objNewInstance;
        Typeface typeface;
        try {
            objNewInstance = f1904e.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            objNewInstance = null;
        }
        if (objNewInstance == null) {
            return null;
        }
        j jVar = new j();
        for (u.h hVar : hVarArr) {
            Uri uri = hVar.f1945a;
            ByteBuffer byteBufferN = (ByteBuffer) jVar.getOrDefault(uri, null);
            if (byteBufferN == null) {
                byteBufferN = androidx.lifecycle.i.N(context, uri);
                jVar.put(uri, byteBufferN);
            }
            if (byteBufferN == null || !o(objNewInstance, byteBufferN, hVar.f1946b, hVar.f1947c, hVar.f1948d)) {
                return null;
            }
        }
        try {
            Object objNewInstance2 = Array.newInstance((Class<?>) f1903d, 1);
            Array.set(objNewInstance2, 0, objNewInstance);
            typeface = (Typeface) f1906g.invoke(null, objNewInstance2);
        } catch (IllegalAccessException | InvocationTargetException unused2) {
            typeface = null;
        }
        if (typeface == null) {
            return null;
        }
        return Typeface.create(typeface, i2);
    }
}
