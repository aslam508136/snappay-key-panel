package x;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewParent;
import com.snapay.app.R;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public abstract class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static WeakHashMap f2012a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Field f2013b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f2014c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final m f2015d;

    static {
        new AtomicInteger(1);
        f2012a = null;
        f2014c = false;
        f2015d = new m();
        new WeakHashMap();
    }

    public static y a(View view) {
        if (f2012a == null) {
            f2012a = new WeakHashMap();
        }
        y yVar = (y) f2012a.get(view);
        if (yVar != null) {
            return yVar;
        }
        y yVar2 = new y(view);
        f2012a.put(view, yVar2);
        return yVar2;
    }

    public static boolean b(View view, KeyEvent keyEvent) {
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        ArrayList arrayList = t.f2008d;
        t tVar = (t) view.getTag(R.id.tag_unhandled_key_event_manager);
        if (tVar == null) {
            tVar = new t();
            view.setTag(R.id.tag_unhandled_key_event_manager, tVar);
        }
        if (keyEvent.getAction() == 0) {
            WeakHashMap weakHashMap = tVar.f2009a;
            if (weakHashMap != null) {
                weakHashMap.clear();
            }
            ArrayList arrayList2 = t.f2008d;
            if (!arrayList2.isEmpty()) {
                synchronized (arrayList2) {
                    if (tVar.f2009a == null) {
                        tVar.f2009a = new WeakHashMap();
                    }
                    int size = arrayList2.size();
                    while (true) {
                        size--;
                        if (size < 0) {
                            break;
                        }
                        ArrayList arrayList3 = t.f2008d;
                        View view2 = (View) ((WeakReference) arrayList3.get(size)).get();
                        if (view2 == null) {
                            arrayList3.remove(size);
                        } else {
                            tVar.f2009a.put(view2, Boolean.TRUE);
                            for (ViewParent parent = view2.getParent(); parent instanceof View; parent = parent.getParent()) {
                                tVar.f2009a.put((View) parent, Boolean.TRUE);
                            }
                        }
                    }
                }
            }
        }
        View viewA = tVar.a(view);
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (viewA != null && !KeyEvent.isModifierKey(keyCode)) {
                if (tVar.f2010b == null) {
                    tVar.f2010b = new SparseArray();
                }
                tVar.f2010b.put(keyCode, new WeakReference(viewA));
            }
        }
        return viewA != null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static c c(View view, c cVar) {
        if (Log.isLoggable("ViewCompat", 3)) {
            Log.d("ViewCompat", "performReceiveContent: " + cVar + ", view=" + view.getClass().getSimpleName() + "[" + view.getId() + "]");
        }
        a0.k kVar = (a0.k) view.getTag(R.id.tag_on_receive_content_listener);
        l lVar = f2015d;
        if (kVar == null) {
            if (view instanceof l) {
                lVar = (l) view;
            }
            return lVar.a(cVar);
        }
        c cVarA = a0.k.a(view, cVar);
        if (cVarA == null) {
            return null;
        }
        if (view instanceof l) {
            lVar = (l) view;
        }
        return lVar.a(cVarA);
    }

    public static void d(View view, Context context, int[] iArr, AttributeSet attributeSet, TypedArray typedArray, int i2) {
        if (Build.VERSION.SDK_INT >= 29) {
            s.a(view, context, iArr, attributeSet, typedArray, i2, 0);
        }
    }
}
