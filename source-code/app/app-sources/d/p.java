package d;

import java.lang.ref.WeakReference;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class p {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final m.c f712b = new m.c();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object f713c = new Object();

    public static void f(p pVar) {
        synchronized (f713c) {
            Iterator it = f712b.iterator();
            while (it.hasNext()) {
                p pVar2 = (p) ((WeakReference) it.next()).get();
                if (pVar2 == pVar || pVar2 == null) {
                    it.remove();
                }
            }
        }
    }

    public abstract void a();

    public abstract void c();

    public abstract void e();

    public abstract boolean g(int i2);

    public abstract void h(int i2);

    public abstract void i(CharSequence charSequence);
}
