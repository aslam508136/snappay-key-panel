package androidx.fragment.app;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes.dex */
public final class p {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final m.j f343b = new m.j();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ r f344a;

    public p(r rVar) {
        this.f344a = rVar;
    }

    public static Class b(ClassLoader classLoader, String str) throws ClassNotFoundException {
        m.j jVar = f343b;
        m.j jVar2 = (m.j) jVar.getOrDefault(classLoader, null);
        if (jVar2 == null) {
            jVar2 = new m.j();
            jVar.put(classLoader, jVar2);
        }
        Class cls = (Class) jVar2.getOrDefault(str, null);
        if (cls != null) {
            return cls;
        }
        Class<?> cls2 = Class.forName(str, false, classLoader);
        jVar2.put(str, cls2);
        return cls2;
    }

    public static Class c(ClassLoader classLoader, String str) {
        try {
            return b(classLoader, str);
        } catch (ClassCastException e2) {
            throw new c(androidx.activity.c.a("Unable to instantiate fragment ", str, ": make sure class is a valid subclass of Fragment"), e2);
        } catch (ClassNotFoundException e3) {
            throw new c(androidx.activity.c.a("Unable to instantiate fragment ", str, ": make sure class name exists"), e3);
        }
    }

    public final void a(String str) {
        try {
            androidx.activity.c.b(c(this.f344a.f358l.f334b.getClassLoader(), str).getConstructor(new Class[0]).newInstance(new Object[0]));
        } catch (IllegalAccessException e2) {
            throw new c(androidx.activity.c.a("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e2);
        } catch (InstantiationException e3) {
            throw new c(androidx.activity.c.a("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e3);
        } catch (NoSuchMethodException e4) {
            throw new c(androidx.activity.c.a("Unable to instantiate fragment ", str, ": could not find Fragment constructor"), e4);
        } catch (InvocationTargetException e5) {
            throw new c(androidx.activity.c.a("Unable to instantiate fragment ", str, ": calling Fragment constructor caused an exception"), e5);
        }
    }
}
