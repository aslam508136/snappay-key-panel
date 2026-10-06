package androidx.lifecycle;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final c f417c = new c();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HashMap f418a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashMap f419b = new HashMap();

    public static void c(HashMap map, b bVar, g gVar, Class cls) {
        g gVar2 = (g) map.get(bVar);
        if (gVar2 == null || gVar == gVar2) {
            if (gVar2 == null) {
                map.put(bVar, gVar);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Method " + bVar.f416b.getName() + " in " + cls.getName() + " already declared with different @OnLifecycleEvent value: previous value " + gVar2 + ", new value " + gVar);
    }

    public final a a(Class cls, Method[] methodArr) {
        int i2;
        Class superclass = cls.getSuperclass();
        HashMap map = new HashMap();
        if (superclass != null) {
            map.putAll(b(superclass).f414b);
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            for (Map.Entry entry : b(cls2).f414b.entrySet()) {
                c(map, (b) entry.getKey(), (g) entry.getValue(), cls);
            }
        }
        if (methodArr == null) {
            try {
                methodArr = cls.getDeclaredMethods();
            } catch (NoClassDefFoundError e2) {
                throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e2);
            }
        }
        boolean z2 = false;
        for (Method method : methodArr) {
            p pVar = (p) method.getAnnotation(p.class);
            if (pVar != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length <= 0) {
                    i2 = 0;
                } else {
                    if (!parameterTypes[0].isAssignableFrom(l.class)) {
                        throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                    }
                    i2 = 1;
                }
                g gVarValue = pVar.value();
                if (parameterTypes.length > 1) {
                    if (!parameterTypes[1].isAssignableFrom(g.class)) {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    }
                    if (gVarValue != g.ON_ANY) {
                        throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                    }
                    i2 = 2;
                }
                if (parameterTypes.length > 2) {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }
                c(map, new b(i2, method), gVarValue, cls);
                z2 = true;
            }
        }
        a aVar = new a(map);
        this.f418a.put(cls, aVar);
        this.f419b.put(cls, Boolean.valueOf(z2));
        return aVar;
    }

    public final a b(Class cls) {
        a aVar = (a) this.f418a.get(cls);
        return aVar != null ? aVar : a(cls, null);
    }
}
