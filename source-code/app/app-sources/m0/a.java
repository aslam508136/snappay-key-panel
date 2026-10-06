package m0;

import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AutoCompleteTextView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import d.f0;
import j.t0;
import j.y;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class a implements a0.f, z.d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static a f1640d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static a f1641e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f1642a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f1643b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object f1644c;

    public a() {
        this.f1642a = null;
        this.f1643b = null;
        this.f1644c = null;
        v();
        try {
            Method declaredMethod = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", new Class[0]);
            this.f1642a = declaredMethod;
            declaredMethod.setAccessible(true);
        } catch (NoSuchMethodException unused) {
        }
        try {
            Method declaredMethod2 = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", new Class[0]);
            this.f1643b = declaredMethod2;
            declaredMethod2.setAccessible(true);
        } catch (NoSuchMethodException unused2) {
        }
        try {
            Method method = AutoCompleteTextView.class.getMethod("ensureImeVisible", Boolean.TYPE);
            this.f1644c = method;
            method.setAccessible(true);
        } catch (NoSuchMethodException unused3) {
        }
    }

    public static synchronized a f(Context context) {
        if (f1640d == null) {
            f1640d = new a(context);
        }
        return f1640d;
    }

    public static a u(Context context, AttributeSet attributeSet, int[] iArr, int i2) {
        return new a(context, context.obtainStyledAttributes(attributeSet, iArr, i2, 0));
    }

    public static void v() {
        if (Build.VERSION.SDK_INT >= 29) {
            throw new UnsupportedClassVersionError("This function can only be used for API Level < 29.");
        }
    }

    @Override // z.d
    public final void a() {
    }

    @Override // z.d
    public final Uri b() {
        return (Uri) this.f1644c;
    }

    @Override // z.d
    public final ClipDescription c() {
        return (ClipDescription) this.f1643b;
    }

    @Override // z.d
    public final Object d() {
        return null;
    }

    @Override // z.d
    public final Uri e() {
        return (Uri) this.f1642a;
    }

    public final boolean g(int i2, boolean z2) {
        return ((TypedArray) this.f1643b).getBoolean(i2, z2);
    }

    public final ColorStateList h(int i2) {
        int resourceId;
        ColorStateList colorStateListB;
        Object obj = this.f1643b;
        return (!((TypedArray) obj).hasValue(i2) || (resourceId = ((TypedArray) obj).getResourceId(i2, 0)) == 0 || (colorStateListB = e.b.b((Context) this.f1642a, resourceId)) == null) ? ((TypedArray) obj).getColorStateList(i2) : colorStateListB;
    }

    public final int i(int i2, int i3) {
        return ((TypedArray) this.f1643b).getDimensionPixelOffset(i2, i3);
    }

    public final int j(int i2, int i3) {
        return ((TypedArray) this.f1643b).getDimensionPixelSize(i2, i3);
    }

    public final Drawable k(int i2) {
        int resourceId;
        Object obj = this.f1643b;
        return (!((TypedArray) obj).hasValue(i2) || (resourceId = ((TypedArray) obj).getResourceId(i2, 0)) == 0) ? ((TypedArray) obj).getDrawable(i2) : e.b.c((Context) this.f1642a, resourceId);
    }

    public final Drawable l(int i2) {
        int resourceId;
        Drawable drawableG;
        if (!((TypedArray) this.f1643b).hasValue(i2) || (resourceId = ((TypedArray) this.f1643b).getResourceId(i2, 0)) == 0) {
            return null;
        }
        y yVarA = y.a();
        Context context = (Context) this.f1642a;
        synchronized (yVarA) {
            drawableG = yVarA.f1497a.g(context, resourceId, true);
        }
        return drawableG;
    }

    public final Typeface m(int i2, int i3, t0 t0Var) {
        String str;
        int resourceId = ((TypedArray) this.f1643b).getResourceId(i2, 0);
        if (resourceId == 0) {
            return null;
        }
        if (((TypedValue) this.f1644c) == null) {
            this.f1644c = new TypedValue();
        }
        Context context = (Context) this.f1642a;
        TypedValue typedValue = (TypedValue) this.f1644c;
        if (context.isRestricted()) {
            return null;
        }
        Resources resources = context.getResources();
        resources.getValue(resourceId, typedValue, true);
        CharSequence charSequence = typedValue.string;
        if (charSequence == null) {
            throw new Resources.NotFoundException("Resource \"" + resources.getResourceName(resourceId) + "\" (" + Integer.toHexString(resourceId) + ") is not a Font: " + typedValue);
        }
        String string = charSequence.toString();
        if (string.startsWith("res/")) {
            m.e eVar = r.d.f1897b;
            Typeface typefaceI = (Typeface) eVar.a(r.d.b(resources, resourceId, i3));
            if (typefaceI != null) {
                t0Var.b(typefaceI);
            } else {
                try {
                    if (string.toLowerCase().endsWith(".xml")) {
                        q.b bVarV = androidx.lifecycle.i.V(resources.getXml(resourceId), resources);
                        if (bVarV != null) {
                            return r.d.a(context, bVarV, resources, resourceId, i3, t0Var);
                        }
                        Log.e("ResourcesCompat", "Failed to find font-family tag");
                        t0Var.a();
                        return null;
                    }
                    typefaceI = r.d.f1896a.i(context, resources, resourceId, string, i3);
                    if (typefaceI != null) {
                        eVar.b(r.d.b(resources, resourceId, i3), typefaceI);
                    }
                    if (typefaceI != null) {
                        t0Var.b(typefaceI);
                    } else {
                        t0Var.a();
                    }
                } catch (IOException e2) {
                    e = e2;
                    str = "Failed to read xml resource ";
                    Log.e("ResourcesCompat", str.concat(string), e);
                    t0Var.a();
                    return null;
                } catch (XmlPullParserException e3) {
                    e = e3;
                    str = "Failed to parse xml resource ";
                    Log.e("ResourcesCompat", str.concat(string), e);
                    t0Var.a();
                    return null;
                }
            }
            return typefaceI;
        }
        t0Var.a();
        return null;
    }

    public final int n(int i2, int i3) {
        return ((TypedArray) this.f1643b).getInt(i2, i3);
    }

    public final ArrayList o() {
        String string = ((SharedPreferences) this.f1643b).getString("logs", "");
        ArrayList arrayList = new ArrayList();
        if (!string.isEmpty()) {
            for (String str : string.split("\\|\\|\\|")) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public final int p(int i2, int i3) {
        return ((TypedArray) this.f1643b).getResourceId(i2, i3);
    }

    public final String q(int i2) {
        return ((TypedArray) this.f1643b).getString(i2);
    }

    public final CharSequence r(int i2) {
        return ((TypedArray) this.f1643b).getText(i2);
    }

    public final boolean s(int i2) {
        return ((TypedArray) this.f1643b).hasValue(i2);
    }

    public final void t(String str) {
        String str2 = ((SimpleDateFormat) this.f1644c).format(new Date()) + "  " + str;
        ArrayList arrayListO = o();
        arrayListO.add(0, str2);
        int size = arrayListO.size();
        List listSubList = arrayListO;
        if (size > 100) {
            listSubList = arrayListO.subList(0, 100);
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < listSubList.size(); i2++) {
            if (i2 > 0) {
                sb.append("|||");
            }
            sb.append((String) listSubList.get(i2));
        }
        ((SharedPreferences) this.f1643b).edit().putString("logs", sb.toString()).apply();
        LocalBroadcastManager.getInstance((Context) this.f1642a).sendBroadcast(new Intent("com.snapay.app.LOG_UPDATED"));
    }

    public final void w() {
        ((TypedArray) this.f1643b).recycle();
    }

    public a(Context context) {
        this.f1644c = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        Context applicationContext = context.getApplicationContext();
        this.f1642a = applicationContext;
        this.f1643b = applicationContext.getSharedPreferences("AppLogs", 0);
    }

    public a(Context context, TypedArray typedArray) {
        this.f1642a = context;
        this.f1643b = typedArray;
    }

    public a(Context context, LocationManager locationManager) {
        this.f1644c = new f0();
        this.f1642a = context;
        this.f1643b = locationManager;
    }

    public a(Uri uri, ClipDescription clipDescription, Uri uri2) {
        this.f1642a = uri;
        this.f1643b = clipDescription;
        this.f1644c = uri2;
    }

    public a(d.i iVar, View view, View view2) {
        this.f1644c = iVar;
        this.f1642a = view;
        this.f1643b = view2;
    }
}
