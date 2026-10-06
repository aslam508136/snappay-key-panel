package e;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import androidx.lifecycle.i;
import j.a2;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ThreadLocal f752a = new ThreadLocal();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final WeakHashMap f753b = new WeakHashMap(0);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object f754c = new Object();

    public static void a(Context context, int i2, ColorStateList colorStateList) {
        synchronized (f754c) {
            WeakHashMap weakHashMap = f753b;
            SparseArray sparseArray = (SparseArray) weakHashMap.get(context);
            if (sparseArray == null) {
                sparseArray = new SparseArray();
                weakHashMap.put(context, sparseArray);
            }
            sparseArray.append(i2, new a(colorStateList, context.getResources().getConfiguration()));
        }
    }

    public static ColorStateList b(Context context, int i2) {
        ColorStateList colorStateListO;
        ColorStateList colorStateList;
        a aVar;
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getColorStateList(i2);
        }
        synchronized (f754c) {
            SparseArray sparseArray = (SparseArray) f753b.get(context);
            colorStateListO = null;
            if (sparseArray == null || sparseArray.size() <= 0 || (aVar = (a) sparseArray.get(i2)) == null) {
                colorStateList = null;
            } else if (aVar.f751b.equals(context.getResources().getConfiguration())) {
                colorStateList = aVar.f750a;
            } else {
                sparseArray.remove(i2);
                colorStateList = null;
            }
        }
        if (colorStateList != null) {
            return colorStateList;
        }
        Resources resources = context.getResources();
        ThreadLocal threadLocal = f752a;
        TypedValue typedValue = (TypedValue) threadLocal.get();
        if (typedValue == null) {
            typedValue = new TypedValue();
            threadLocal.set(typedValue);
        }
        resources.getValue(i2, typedValue, true);
        int i3 = typedValue.type;
        if (!(i3 >= 28 && i3 <= 31)) {
            Resources resources2 = context.getResources();
            try {
                colorStateListO = i.o(resources2, resources2.getXml(i2), context.getTheme());
            } catch (Exception e2) {
                Log.e("AppCompatResources", "Failed to inflate ColorStateList, leaving it to the framework", e2);
            }
        }
        if (colorStateListO != null) {
            a(context, i2, colorStateListO);
            return colorStateListO;
        }
        Object obj = o.a.f1732a;
        return Build.VERSION.SDK_INT >= 23 ? context.getColorStateList(i2) : context.getResources().getColorStateList(i2);
    }

    public static Drawable c(Context context, int i2) {
        return a2.d().f(context, i2);
    }
}
