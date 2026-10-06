package h;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputContentInfo;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import com.google.crypto.tink.shaded.protobuf.Reader;
import com.snapay.app.MainActivity;
import com.snapay.app.SplashActivity;
import i.a0;
import i.h0;
import j.d3;
import j.o;
import j.q;
import j.t0;
import j.u1;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;
import m0.x;
import x.u;

/* JADX INFO: loaded from: classes.dex */
public class a implements u1, a0, i.m, q, x {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f891b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f892c;

    public a(int i2) {
        this.f891b = 8;
        this.f892c = new ConcurrentHashMap();
    }

    public static Object j(Object[] objArr, int i2, o oVar) {
        int i3;
        boolean z2;
        int i4 = (i2 & 1) == 0 ? 400 : 700;
        boolean z3 = (i2 & 2) != 0;
        Object obj = null;
        int i5 = Reader.READ_DONE;
        for (Object obj2 : objArr) {
            int i6 = oVar.f1338b;
            switch (i6) {
                case 0:
                    i3 = ((u.h) obj2).f1947c;
                    break;
                default:
                    i3 = ((q.d) obj2).f1842b;
                    break;
            }
            int iAbs = Math.abs(i3 - i4) * 2;
            switch (i6) {
                case 0:
                    z2 = ((u.h) obj2).f1948d;
                    break;
                default:
                    z2 = ((q.d) obj2).f1843c;
                    break;
            }
            int i7 = iAbs + (z2 == z3 ? 0 : 1);
            if (obj == null || i5 > i7) {
                obj = obj2;
                i5 = i7;
            }
        }
        return obj;
    }

    @Override // i.a0
    public final void a(i.o oVar, boolean z2) {
        if (oVar instanceof h0) {
            oVar.k().c(false);
        }
        a0 a0Var = ((j.m) this.f892c).f1298f;
        if (a0Var != null) {
            a0Var.a(oVar, z2);
        }
    }

    @Override // i.m
    public final void b(i.o oVar) {
        i.m mVar = ((ActionMenuView) this.f892c).f172w;
        if (mVar != null) {
            mVar.b(oVar);
        }
    }

    @Override // i.a0
    public final boolean c(i.o oVar) {
        Object obj = this.f892c;
        if (oVar == ((j.m) obj).f1296d) {
            return false;
        }
        ((h0) oVar).A.getClass();
        ((j.m) obj).getClass();
        a0 a0Var = ((j.m) obj).f1298f;
        if (a0Var != null) {
            return a0Var.c(oVar);
        }
        return false;
    }

    @Override // i.m
    public final boolean d(i.o oVar, MenuItem menuItem) {
        Object obj = this.f892c;
        if (((ActionMenuView) obj).B == null) {
            return false;
        }
        ((Toolbar) ((a) ((ActionMenuView) obj).B).f892c).getClass();
        return false;
    }

    @Override // j.u1
    public final void e(i.o oVar, i.q qVar) {
        i.i iVar = (i.i) this.f892c;
        iVar.f1026h.removeCallbacksAndMessages(null);
        int size = iVar.f1028j.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                i2 = -1;
                break;
            } else if (oVar == ((i.h) iVar.f1028j.get(i2)).f1018b) {
                break;
            } else {
                i2++;
            }
        }
        if (i2 == -1) {
            return;
        }
        int i3 = i2 + 1;
        iVar.f1026h.postAtTime(new i.g(this, i3 < iVar.f1028j.size() ? (i.h) iVar.f1028j.get(i3) : null, qVar, oVar), oVar, SystemClock.uptimeMillis() + 200);
    }

    public Typeface f(Context context, q.c cVar, Resources resources, int i2) {
        long jLongValue;
        q.d dVar = (q.d) j(cVar.f1840a, i2, new o(1));
        if (dVar == null) {
            return null;
        }
        int i3 = dVar.f1846f;
        Typeface typefaceI = r.d.f1896a.i(context, resources, i3, dVar.f1841a, i2);
        if (typefaceI != null) {
            r.d.f1897b.b(r.d.b(resources, i3, i2), typefaceI);
        }
        if (typefaceI == null) {
            jLongValue = 0;
        } else {
            try {
                Field declaredField = Typeface.class.getDeclaredField("native_instance");
                declaredField.setAccessible(true);
                jLongValue = ((Number) declaredField.get(typefaceI)).longValue();
            } catch (IllegalAccessException | NoSuchFieldException e2) {
                Log.e("TypefaceCompatBaseImpl", "Could not retrieve font from family.", e2);
                jLongValue = 0;
            }
        }
        if (jLongValue != 0) {
            ((ConcurrentHashMap) this.f892c).put(Long.valueOf(jLongValue), cVar);
        }
        return typefaceI;
    }

    public Typeface g(Context context, u.h[] hVarArr, int i2) throws Throwable {
        InputStream inputStreamOpenInputStream;
        InputStream inputStream = null;
        if (hVarArr.length < 1) {
            return null;
        }
        try {
            inputStreamOpenInputStream = context.getContentResolver().openInputStream(l(i2, hVarArr).f1945a);
            try {
                Typeface typefaceH = h(context, inputStreamOpenInputStream);
                androidx.lifecycle.i.j(inputStreamOpenInputStream);
                return typefaceH;
            } catch (IOException unused) {
                androidx.lifecycle.i.j(inputStreamOpenInputStream);
                return null;
            } catch (Throwable th) {
                th = th;
                inputStream = inputStreamOpenInputStream;
                androidx.lifecycle.i.j(inputStream);
                throw th;
            }
        } catch (IOException unused2) {
            inputStreamOpenInputStream = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public Typeface h(Context context, InputStream inputStream) {
        File fileG = androidx.lifecycle.i.G(context);
        if (fileG == null) {
            return null;
        }
        try {
            if (androidx.lifecycle.i.m(fileG, inputStream)) {
                return Typeface.createFromFile(fileG.getPath());
            }
            return null;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            fileG.delete();
        }
    }

    public Typeface i(Context context, Resources resources, int i2, String str, int i3) {
        File fileG = androidx.lifecycle.i.G(context);
        if (fileG == null) {
            return null;
        }
        try {
            if (androidx.lifecycle.i.l(fileG, resources, i2)) {
                return Typeface.createFromFile(fileG.getPath());
            }
            return null;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            fileG.delete();
        }
    }

    @Override // j.u1
    public final void k(i.o oVar, MenuItem menuItem) {
        ((i.i) this.f892c).f1026h.removeCallbacksAndMessages(oVar);
    }

    public u.h l(int i2, u.h[] hVarArr) {
        return (u.h) j(hVarArr, i2, new o(0));
    }

    public final boolean m(a aVar, int i2, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 25 && (i2 & 1) != 0) {
            try {
                ((z.d) aVar.f892c).a();
                InputContentInfo inputContentInfoA = j.h0.a(((z.d) aVar.f892c).d());
                bundle = bundle == null ? new Bundle() : new Bundle(bundle);
                bundle.putParcelable("androidx.core.view.extra.INPUT_CONTENT_INFO", inputContentInfoA);
            } catch (Exception e2) {
                Log.w("ReceiveContent", "Can't insert content from IME; requestPermission() failed", e2);
                return false;
            }
        }
        ClipDescription clipDescriptionC = ((z.d) aVar.f892c).c();
        Object obj = aVar.f892c;
        x.c cVar = new x.c(new ClipData(clipDescriptionC, new ClipData.Item(((z.d) obj).e())), 2);
        cVar.f1975e = ((z.d) obj).b();
        cVar.f1976f = bundle;
        return u.c((View) this.f892c, new x.c(cVar)) == null;
    }

    public final void n() {
        int i2 = this.f891b;
        Object obj = this.f892c;
        switch (i2) {
            case TYPE_UINT32_VALUE:
                break;
            case TYPE_ENUM_VALUE:
                d3 d3Var = ((MainActivity) obj).f554q;
                break;
            default:
                SplashActivity splashActivity = (SplashActivity) obj;
                int i3 = SplashActivity.f568v;
                splashActivity.getClass();
                SharedPreferences sharedPreferencesA = m0.m.a(splashActivity);
                int i4 = 0;
                if (!sharedPreferencesA.getBoolean("is_logged_in", false)) {
                    splashActivity.f569o.setText("Loading...");
                    new Handler().postDelayed(new m0.o(splashActivity, i4), 500L);
                    break;
                } else {
                    String string = sharedPreferencesA.getString("access_key", "");
                    splashActivity.f569o.setText("Loading...");
                    if (!splashActivity.f575u) {
                        splashActivity.f575u = true;
                        splashActivity.f569o.setText("Loading...");
                        splashActivity.f574t = new Handler();
                        splashActivity.m(string);
                        break;
                    }
                }
                break;
        }
    }

    public a(Uri uri, ClipDescription clipDescription, Uri uri2) {
        this.f891b = 11;
        this.f892c = Build.VERSION.SDK_INT >= 25 ? new z.c(uri, clipDescription, uri2) : new m0.a(uri, clipDescription, uri2);
    }

    public a(t0 t0Var) {
        this.f891b = 7;
        this.f892c = t0Var;
    }

    public /* synthetic */ a(Object obj, int i2) {
        this.f891b = i2;
        this.f892c = obj;
    }
}
