package androidx.lifecycle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final HashMap f457a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final HashMap f458b = new HashMap();

    public static void a(Constructor constructor, Object obj) {
        try {
            androidx.activity.c.b(constructor.newInstance(obj));
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        } catch (InstantiationException e3) {
            throw new RuntimeException(e3);
        } catch (InvocationTargetException e4) {
            throw new RuntimeException(e4);
        }
    }

    public static String b(String str) {
        return str.replace(".", "_") + "_LifecycleAdapter";
    }

    /* JADX WARN: Code duplicated, block: B:59:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:64:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:67:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:70:0x00fd A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:71:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:75:0x0112  */
    /* JADX WARN: Code duplicated, block: B:86:0x0117 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:89:0x010d A[SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    public static int c(Class cls) {
        Constructor declaredConstructor;
        boolean zBooleanValue;
        int i2;
        List listSingletonList;
        boolean z2;
        HashMap map = f457a;
        Integer num = (Integer) map.get(cls);
        if (num != null) {
            return num.intValue();
        }
        int i3 = 1;
        if (cls.getCanonicalName() != null) {
            ArrayList arrayList = null;
            try {
                Package r4 = cls.getPackage();
                String canonicalName = cls.getCanonicalName();
                String name = r4 != null ? r4.getName() : "";
                if (!name.isEmpty()) {
                    canonicalName = canonicalName.substring(name.length() + 1);
                }
                String strB = b(canonicalName);
                if (!name.isEmpty()) {
                    strB = name + "." + strB;
                }
                declaredConstructor = Class.forName(strB).getDeclaredConstructor(cls);
                if (!declaredConstructor.isAccessible()) {
                    declaredConstructor.setAccessible(true);
                }
            } catch (ClassNotFoundException unused) {
                declaredConstructor = null;
            } catch (NoSuchMethodException e2) {
                throw new RuntimeException(e2);
            }
            HashMap map2 = f458b;
            if (declaredConstructor != null) {
                listSingletonList = Collections.singletonList(declaredConstructor);
            } else {
                c cVar = c.f417c;
                HashMap map3 = cVar.f419b;
                Boolean bool = (Boolean) map3.get(cls);
                if (bool != null) {
                    zBooleanValue = bool.booleanValue();
                } else {
                    try {
                        Method[] declaredMethods = cls.getDeclaredMethods();
                        int length = declaredMethods.length;
                        int i4 = 0;
                        while (true) {
                            if (i4 >= length) {
                                map3.put(cls, Boolean.FALSE);
                                zBooleanValue = false;
                                break;
                            }
                            if (((p) declaredMethods[i4].getAnnotation(p.class)) != null) {
                                cVar.a(cls, declaredMethods);
                                zBooleanValue = true;
                                break;
                            }
                            i4++;
                        }
                    } catch (NoClassDefFoundError e3) {
                        throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e3);
                    }
                }
                if (!zBooleanValue) {
                    Class superclass = cls.getSuperclass();
                    if (!(superclass != null && k.class.isAssignableFrom(superclass))) {
                        for (Class<?> cls2 : cls.getInterfaces()) {
                            if (cls2 == null && k.class.isAssignableFrom(cls2)) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            if (z2) {
                                if (c(cls2) == 1) {
                                    if (arrayList == null) {
                                        arrayList = new ArrayList();
                                    }
                                    arrayList.addAll((Collection) map2.get(cls2));
                                }
                            }
                        }
                        if (arrayList != null) {
                            listSingletonList = arrayList;
                        }
                    } else if (c(superclass) != 1) {
                        arrayList = new ArrayList((Collection) map2.get(superclass));
                        while (i2 < r7) {
                            if (cls2 == null) {
                                z2 = false;
                            } else {
                                z2 = false;
                            }
                            if (z2) {
                                if (c(cls2) == 1) {
                                    if (arrayList == null) {
                                        arrayList = new ArrayList();
                                    }
                                    arrayList.addAll((Collection) map2.get(cls2));
                                }
                            }
                        }
                        if (arrayList != null) {
                            listSingletonList = arrayList;
                        }
                    }
                }
            }
            map2.put(cls, listSingletonList);
            i3 = 2;
        }
        map.put(cls, Integer.valueOf(i3));
        return i3;
    }
}
