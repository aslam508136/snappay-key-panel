package d;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.snapay.app.R;
import j.a3;
import j.e3;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Objects;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class n extends androidx.fragment.app.h implements o {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public a0 f711n;

    public n() {
        this.f65e.f469b.b("androidx:appcompat", new l(this));
        j(new m(this));
    }

    private void l() {
        getWindow().getDecorView().setTag(R.id.view_tree_lifecycle_owner, this);
        getWindow().getDecorView().setTag(R.id.view_tree_view_model_store_owner, this);
        getWindow().getDecorView().setTag(R.id.view_tree_saved_state_registry_owner, this);
    }

    @Override // android.app.Activity
    public final void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        l();
        a0 a0Var = (a0) k();
        a0Var.r();
        ((ViewGroup) a0Var.f599v.findViewById(android.R.id.content)).addView(view, layoutParams);
        a0Var.f584g.f722b.onContentChanged();
    }

    /* JADX WARN: Code duplicated, block: B:100:0x01a3  */
    /* JADX WARN: Code duplicated, block: B:101:0x01a6  */
    /* JADX WARN: Code duplicated, block: B:105:0x01bd  */
    /* JADX WARN: Code duplicated, block: B:107:0x01c0  */
    /* JADX WARN: Code duplicated, block: B:109:0x01c3  */
    /* JADX WARN: Code duplicated, block: B:111:0x01cd  */
    /* JADX WARN: Code duplicated, block: B:112:0x01d1  */
    /* JADX WARN: Code duplicated, block: B:114:0x01d5  */
    /* JADX WARN: Code duplicated, block: B:123:0x01f6 A[Catch: all -> 0x020e, TRY_LEAVE, TryCatch #5 {, blocks: (B:116:0x01d8, B:118:0x01dc, B:122:0x01f4, B:123:0x01f6, B:125:0x01fa, B:131:0x020c, B:130:0x0203, B:121:0x01ed), top: B:145:0x01d8, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:139:0x01dc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:145:0x01d8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:149:0x01fa A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:16:0x0030  */
    /* JADX WARN: Code duplicated, block: B:20:0x003d  */
    /* JADX WARN: Code duplicated, block: B:23:0x0043  */
    /* JADX WARN: Code duplicated, block: B:25:0x006e  */
    /* JADX WARN: Code duplicated, block: B:28:0x007d  */
    /* JADX WARN: Code duplicated, block: B:30:0x0085  */
    /* JADX WARN: Code duplicated, block: B:33:0x008d  */
    /* JADX WARN: Code duplicated, block: B:36:0x0095  */
    /* JADX WARN: Code duplicated, block: B:39:0x009b  */
    /* JADX WARN: Code duplicated, block: B:41:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:42:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:44:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:47:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:50:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:53:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:56:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:59:0x00e5  */
    /* JADX WARN: Code duplicated, block: B:62:0x00ed  */
    /* JADX WARN: Code duplicated, block: B:65:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:68:0x0108  */
    /* JADX WARN: Code duplicated, block: B:71:0x0117  */
    /* JADX WARN: Code duplicated, block: B:74:0x0126  */
    /* JADX WARN: Code duplicated, block: B:77:0x012f  */
    /* JADX WARN: Code duplicated, block: B:79:0x013d  */
    /* JADX WARN: Code duplicated, block: B:82:0x0159  */
    /* JADX WARN: Code duplicated, block: B:85:0x0171  */
    /* JADX WARN: Code duplicated, block: B:88:0x0180  */
    /* JADX WARN: Code duplicated, block: B:91:0x018b  */
    /* JADX WARN: Code duplicated, block: B:94:0x0193  */
    /* JADX WARN: Code duplicated, block: B:97:0x019b  */
    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    public final void attachBaseContext(Context context) {
        int i2;
        Configuration configuration;
        Configuration configuration2;
        Configuration configuration3;
        h.e eVar;
        boolean z2;
        Resources.Theme theme;
        int i3;
        Method method;
        float f2;
        float f3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        int i24;
        int i25;
        int i26;
        int i27;
        int i28;
        int i29;
        int i30;
        int i31;
        int i32;
        int i33;
        int i34;
        int i35;
        int i36;
        int i37;
        int i38;
        int i39;
        LocaleList locales;
        LocaleList locales2;
        a0 a0Var = (a0) k();
        a0Var.J = true;
        int i40 = a0Var.N;
        if (i40 == -100) {
            i40 = -100;
        }
        int iX = a0Var.x(context, i40);
        if (a0.f580d0 && (context instanceof ContextThemeWrapper)) {
            try {
                ((ContextThemeWrapper) context).applyOverrideConfiguration(a0.o(context, iX, null));
            } catch (IllegalStateException unused) {
                if (context instanceof h.e) {
                    ((h.e) context).a(a0.o(context, iX, null));
                } else if (a0.f579c0) {
                    i2 = Build.VERSION.SDK_INT;
                    Configuration configuration4 = new Configuration();
                    configuration4.uiMode = -1;
                    configuration4.fontScale = 0.0f;
                    configuration = context.createConfigurationContext(configuration4).getResources().getConfiguration();
                    configuration2 = context.getResources().getConfiguration();
                    configuration.uiMode = configuration2.uiMode;
                    if (configuration.equals(configuration2)) {
                        configuration3 = null;
                    } else {
                        configuration3 = new Configuration();
                        configuration3.fontScale = 0.0f;
                        if (configuration.diff(configuration2) != 0) {
                            f2 = configuration.fontScale;
                            f3 = configuration2.fontScale;
                            if (f2 != f3) {
                                configuration3.fontScale = f3;
                            }
                            i4 = configuration.mcc;
                            i5 = configuration2.mcc;
                            if (i4 != i5) {
                                configuration3.mcc = i5;
                            }
                            i6 = configuration.mnc;
                            i7 = configuration2.mnc;
                            if (i6 != i7) {
                                configuration3.mnc = i7;
                            }
                            if (i2 >= 24) {
                                locales = configuration.getLocales();
                                locales2 = configuration2.getLocales();
                                if (!locales.equals(locales2)) {
                                    configuration3.setLocales(locales2);
                                    configuration3.locale = configuration2.locale;
                                }
                            } else if (!Objects.equals(configuration.locale, configuration2.locale)) {
                                configuration3.locale = configuration2.locale;
                            }
                            i8 = configuration.touchscreen;
                            i9 = configuration2.touchscreen;
                            if (i8 != i9) {
                                configuration3.touchscreen = i9;
                            }
                            i10 = configuration.keyboard;
                            i11 = configuration2.keyboard;
                            if (i10 != i11) {
                                configuration3.keyboard = i11;
                            }
                            i12 = configuration.keyboardHidden;
                            i13 = configuration2.keyboardHidden;
                            if (i12 != i13) {
                                configuration3.keyboardHidden = i13;
                            }
                            i14 = configuration.navigation;
                            i15 = configuration2.navigation;
                            if (i14 != i15) {
                                configuration3.navigation = i15;
                            }
                            i16 = configuration.navigationHidden;
                            i17 = configuration2.navigationHidden;
                            if (i16 != i17) {
                                configuration3.navigationHidden = i17;
                            }
                            i18 = configuration.orientation;
                            i19 = configuration2.orientation;
                            if (i18 != i19) {
                                configuration3.orientation = i19;
                            }
                            i20 = configuration.screenLayout & 15;
                            i21 = configuration2.screenLayout & 15;
                            if (i20 != i21) {
                                configuration3.screenLayout |= i21;
                            }
                            i22 = configuration.screenLayout & 192;
                            i23 = configuration2.screenLayout & 192;
                            if (i22 != i23) {
                                configuration3.screenLayout |= i23;
                            }
                            i24 = configuration.screenLayout & 48;
                            i25 = configuration2.screenLayout & 48;
                            if (i24 != i25) {
                                configuration3.screenLayout |= i25;
                            }
                            i26 = configuration.screenLayout & 768;
                            i27 = configuration2.screenLayout & 768;
                            if (i26 != i27) {
                                configuration3.screenLayout |= i27;
                            }
                            if (i2 >= 26) {
                                if ((configuration.colorMode & 3) != (configuration2.colorMode & 3)) {
                                    configuration3.colorMode |= configuration2.colorMode & 3;
                                }
                                if ((configuration.colorMode & 12) != (configuration2.colorMode & 12)) {
                                    configuration3.colorMode |= configuration2.colorMode & 12;
                                }
                            }
                            i28 = configuration.uiMode & 15;
                            i29 = configuration2.uiMode & 15;
                            if (i28 != i29) {
                                configuration3.uiMode |= i29;
                            }
                            i30 = configuration.uiMode & 48;
                            i31 = configuration2.uiMode & 48;
                            if (i30 != i31) {
                                configuration3.uiMode |= i31;
                            }
                            i32 = configuration.screenWidthDp;
                            i33 = configuration2.screenWidthDp;
                            if (i32 != i33) {
                                configuration3.screenWidthDp = i33;
                            }
                            i34 = configuration.screenHeightDp;
                            i35 = configuration2.screenHeightDp;
                            if (i34 != i35) {
                                configuration3.screenHeightDp = i35;
                            }
                            i36 = configuration.smallestScreenWidthDp;
                            i37 = configuration2.smallestScreenWidthDp;
                            if (i36 != i37) {
                                configuration3.smallestScreenWidthDp = i37;
                            }
                            i38 = configuration.densityDpi;
                            i39 = configuration2.densityDpi;
                            if (i38 != i39) {
                                configuration3.densityDpi = i39;
                            }
                        }
                    }
                    Configuration configurationO = a0.o(context, iX, configuration3);
                    eVar = new h.e(context, R.style.Theme_AppCompat_Empty);
                    eVar.a(configurationO);
                    if (context.getTheme() != null) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (z2) {
                        theme = eVar.getTheme();
                        i3 = Build.VERSION.SDK_INT;
                        if (i3 >= 29) {
                            theme.rebase();
                        } else if (i3 >= 23) {
                            synchronized (q.g.f1852a) {
                                if (q.g.f1854c) {
                                    method = q.g.f1853b;
                                    if (method != null) {
                                        method.invoke(theme, new Object[0]);
                                    }
                                } else {
                                    Method declaredMethod = Resources.Theme.class.getDeclaredMethod("rebase", new Class[0]);
                                    q.g.f1853b = declaredMethod;
                                    declaredMethod.setAccessible(true);
                                    q.g.f1854c = true;
                                    method = q.g.f1853b;
                                    if (method != null) {
                                        method.invoke(theme, new Object[0]);
                                    }
                                }
                            }
                        }
                    }
                    context = eVar;
                }
            }
        } else if (context instanceof h.e) {
            try {
                ((h.e) context).a(a0.o(context, iX, null));
            } catch (IllegalStateException unused2) {
                if (a0.f579c0) {
                    i2 = Build.VERSION.SDK_INT;
                    Configuration configuration5 = new Configuration();
                    configuration5.uiMode = -1;
                    configuration5.fontScale = 0.0f;
                    configuration = context.createConfigurationContext(configuration5).getResources().getConfiguration();
                    configuration2 = context.getResources().getConfiguration();
                    configuration.uiMode = configuration2.uiMode;
                    if (configuration.equals(configuration2)) {
                        configuration3 = new Configuration();
                        configuration3.fontScale = 0.0f;
                        if (configuration.diff(configuration2) != 0) {
                            f2 = configuration.fontScale;
                            f3 = configuration2.fontScale;
                            if (f2 != f3) {
                                configuration3.fontScale = f3;
                            }
                            i4 = configuration.mcc;
                            i5 = configuration2.mcc;
                            if (i4 != i5) {
                                configuration3.mcc = i5;
                            }
                            i6 = configuration.mnc;
                            i7 = configuration2.mnc;
                            if (i6 != i7) {
                                configuration3.mnc = i7;
                            }
                            if (i2 >= 24) {
                                locales = configuration.getLocales();
                                locales2 = configuration2.getLocales();
                                if (!locales.equals(locales2)) {
                                    configuration3.setLocales(locales2);
                                    configuration3.locale = configuration2.locale;
                                }
                            } else if (!Objects.equals(configuration.locale, configuration2.locale)) {
                                configuration3.locale = configuration2.locale;
                            }
                            i8 = configuration.touchscreen;
                            i9 = configuration2.touchscreen;
                            if (i8 != i9) {
                                configuration3.touchscreen = i9;
                            }
                            i10 = configuration.keyboard;
                            i11 = configuration2.keyboard;
                            if (i10 != i11) {
                                configuration3.keyboard = i11;
                            }
                            i12 = configuration.keyboardHidden;
                            i13 = configuration2.keyboardHidden;
                            if (i12 != i13) {
                                configuration3.keyboardHidden = i13;
                            }
                            i14 = configuration.navigation;
                            i15 = configuration2.navigation;
                            if (i14 != i15) {
                                configuration3.navigation = i15;
                            }
                            i16 = configuration.navigationHidden;
                            i17 = configuration2.navigationHidden;
                            if (i16 != i17) {
                                configuration3.navigationHidden = i17;
                            }
                            i18 = configuration.orientation;
                            i19 = configuration2.orientation;
                            if (i18 != i19) {
                                configuration3.orientation = i19;
                            }
                            i20 = configuration.screenLayout & 15;
                            i21 = configuration2.screenLayout & 15;
                            if (i20 != i21) {
                                configuration3.screenLayout |= i21;
                            }
                            i22 = configuration.screenLayout & 192;
                            i23 = configuration2.screenLayout & 192;
                            if (i22 != i23) {
                                configuration3.screenLayout |= i23;
                            }
                            i24 = configuration.screenLayout & 48;
                            i25 = configuration2.screenLayout & 48;
                            if (i24 != i25) {
                                configuration3.screenLayout |= i25;
                            }
                            i26 = configuration.screenLayout & 768;
                            i27 = configuration2.screenLayout & 768;
                            if (i26 != i27) {
                                configuration3.screenLayout |= i27;
                            }
                            if (i2 >= 26) {
                                if ((configuration.colorMode & 3) != (configuration2.colorMode & 3)) {
                                    configuration3.colorMode |= configuration2.colorMode & 3;
                                }
                                if ((configuration.colorMode & 12) != (configuration2.colorMode & 12)) {
                                    configuration3.colorMode |= configuration2.colorMode & 12;
                                }
                            }
                            i28 = configuration.uiMode & 15;
                            i29 = configuration2.uiMode & 15;
                            if (i28 != i29) {
                                configuration3.uiMode |= i29;
                            }
                            i30 = configuration.uiMode & 48;
                            i31 = configuration2.uiMode & 48;
                            if (i30 != i31) {
                                configuration3.uiMode |= i31;
                            }
                            i32 = configuration.screenWidthDp;
                            i33 = configuration2.screenWidthDp;
                            if (i32 != i33) {
                                configuration3.screenWidthDp = i33;
                            }
                            i34 = configuration.screenHeightDp;
                            i35 = configuration2.screenHeightDp;
                            if (i34 != i35) {
                                configuration3.screenHeightDp = i35;
                            }
                            i36 = configuration.smallestScreenWidthDp;
                            i37 = configuration2.smallestScreenWidthDp;
                            if (i36 != i37) {
                                configuration3.smallestScreenWidthDp = i37;
                            }
                            i38 = configuration.densityDpi;
                            i39 = configuration2.densityDpi;
                            if (i38 != i39) {
                                configuration3.densityDpi = i39;
                            }
                        }
                    } else {
                        configuration3 = null;
                    }
                    Configuration configurationO2 = a0.o(context, iX, configuration3);
                    eVar = new h.e(context, R.style.Theme_AppCompat_Empty);
                    eVar.a(configurationO2);
                    try {
                        if (context.getTheme() != null) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                    } catch (NullPointerException unused3) {
                    }
                    if (z2) {
                        theme = eVar.getTheme();
                        i3 = Build.VERSION.SDK_INT;
                        if (i3 >= 29) {
                            theme.rebase();
                        } else if (i3 >= 23) {
                            synchronized (q.g.f1852a) {
                                if (q.g.f1854c) {
                                    try {
                                        Method declaredMethod2 = Resources.Theme.class.getDeclaredMethod("rebase", new Class[0]);
                                        q.g.f1853b = declaredMethod2;
                                        declaredMethod2.setAccessible(true);
                                    } catch (NoSuchMethodException e2) {
                                        Log.i("ResourcesCompat", "Failed to retrieve rebase() method", e2);
                                    }
                                    q.g.f1854c = true;
                                    method = q.g.f1853b;
                                    if (method != null) {
                                        try {
                                            method.invoke(theme, new Object[0]);
                                        } catch (IllegalAccessException | InvocationTargetException e3) {
                                            Log.i("ResourcesCompat", "Failed to invoke rebase() method via reflection", e3);
                                            q.g.f1853b = null;
                                        }
                                    }
                                } else {
                                    method = q.g.f1853b;
                                    if (method != null) {
                                        method.invoke(theme, new Object[0]);
                                    }
                                }
                            }
                        }
                    }
                    context = eVar;
                }
            }
        } else if (a0.f579c0) {
            i2 = Build.VERSION.SDK_INT;
            Configuration configuration6 = new Configuration();
            configuration6.uiMode = -1;
            configuration6.fontScale = 0.0f;
            configuration = context.createConfigurationContext(configuration6).getResources().getConfiguration();
            configuration2 = context.getResources().getConfiguration();
            configuration.uiMode = configuration2.uiMode;
            if (configuration.equals(configuration2)) {
                configuration3 = new Configuration();
                configuration3.fontScale = 0.0f;
                if (configuration.diff(configuration2) != 0) {
                    f2 = configuration.fontScale;
                    f3 = configuration2.fontScale;
                    if (f2 != f3) {
                        configuration3.fontScale = f3;
                    }
                    i4 = configuration.mcc;
                    i5 = configuration2.mcc;
                    if (i4 != i5) {
                        configuration3.mcc = i5;
                    }
                    i6 = configuration.mnc;
                    i7 = configuration2.mnc;
                    if (i6 != i7) {
                        configuration3.mnc = i7;
                    }
                    if (i2 >= 24) {
                        locales = configuration.getLocales();
                        locales2 = configuration2.getLocales();
                        if (!locales.equals(locales2)) {
                            configuration3.setLocales(locales2);
                            configuration3.locale = configuration2.locale;
                        }
                    } else if (!Objects.equals(configuration.locale, configuration2.locale)) {
                        configuration3.locale = configuration2.locale;
                    }
                    i8 = configuration.touchscreen;
                    i9 = configuration2.touchscreen;
                    if (i8 != i9) {
                        configuration3.touchscreen = i9;
                    }
                    i10 = configuration.keyboard;
                    i11 = configuration2.keyboard;
                    if (i10 != i11) {
                        configuration3.keyboard = i11;
                    }
                    i12 = configuration.keyboardHidden;
                    i13 = configuration2.keyboardHidden;
                    if (i12 != i13) {
                        configuration3.keyboardHidden = i13;
                    }
                    i14 = configuration.navigation;
                    i15 = configuration2.navigation;
                    if (i14 != i15) {
                        configuration3.navigation = i15;
                    }
                    i16 = configuration.navigationHidden;
                    i17 = configuration2.navigationHidden;
                    if (i16 != i17) {
                        configuration3.navigationHidden = i17;
                    }
                    i18 = configuration.orientation;
                    i19 = configuration2.orientation;
                    if (i18 != i19) {
                        configuration3.orientation = i19;
                    }
                    i20 = configuration.screenLayout & 15;
                    i21 = configuration2.screenLayout & 15;
                    if (i20 != i21) {
                        configuration3.screenLayout |= i21;
                    }
                    i22 = configuration.screenLayout & 192;
                    i23 = configuration2.screenLayout & 192;
                    if (i22 != i23) {
                        configuration3.screenLayout |= i23;
                    }
                    i24 = configuration.screenLayout & 48;
                    i25 = configuration2.screenLayout & 48;
                    if (i24 != i25) {
                        configuration3.screenLayout |= i25;
                    }
                    i26 = configuration.screenLayout & 768;
                    i27 = configuration2.screenLayout & 768;
                    if (i26 != i27) {
                        configuration3.screenLayout |= i27;
                    }
                    if (i2 >= 26) {
                        if ((configuration.colorMode & 3) != (configuration2.colorMode & 3)) {
                            configuration3.colorMode |= configuration2.colorMode & 3;
                        }
                        if ((configuration.colorMode & 12) != (configuration2.colorMode & 12)) {
                            configuration3.colorMode |= configuration2.colorMode & 12;
                        }
                    }
                    i28 = configuration.uiMode & 15;
                    i29 = configuration2.uiMode & 15;
                    if (i28 != i29) {
                        configuration3.uiMode |= i29;
                    }
                    i30 = configuration.uiMode & 48;
                    i31 = configuration2.uiMode & 48;
                    if (i30 != i31) {
                        configuration3.uiMode |= i31;
                    }
                    i32 = configuration.screenWidthDp;
                    i33 = configuration2.screenWidthDp;
                    if (i32 != i33) {
                        configuration3.screenWidthDp = i33;
                    }
                    i34 = configuration.screenHeightDp;
                    i35 = configuration2.screenHeightDp;
                    if (i34 != i35) {
                        configuration3.screenHeightDp = i35;
                    }
                    i36 = configuration.smallestScreenWidthDp;
                    i37 = configuration2.smallestScreenWidthDp;
                    if (i36 != i37) {
                        configuration3.smallestScreenWidthDp = i37;
                    }
                    i38 = configuration.densityDpi;
                    i39 = configuration2.densityDpi;
                    if (i38 != i39) {
                        configuration3.densityDpi = i39;
                    }
                }
            } else {
                configuration3 = null;
            }
            Configuration configurationO3 = a0.o(context, iX, configuration3);
            eVar = new h.e(context, R.style.Theme_AppCompat_Empty);
            eVar.a(configurationO3);
            if (context.getTheme() != null) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                theme = eVar.getTheme();
                i3 = Build.VERSION.SDK_INT;
                if (i3 >= 29) {
                    theme.rebase();
                } else if (i3 >= 23) {
                    synchronized (q.g.f1852a) {
                        if (q.g.f1854c) {
                            Method declaredMethod3 = Resources.Theme.class.getDeclaredMethod("rebase", new Class[0]);
                            q.g.f1853b = declaredMethod3;
                            declaredMethod3.setAccessible(true);
                            q.g.f1854c = true;
                            method = q.g.f1853b;
                            if (method != null) {
                                method.invoke(theme, new Object[0]);
                            }
                        } else {
                            method = q.g.f1853b;
                            if (method != null) {
                                method.invoke(theme, new Object[0]);
                            }
                        }
                    }
                }
            }
            context = eVar;
        }
        super.attachBaseContext(context);
    }

    @Override // d.o
    public final void b() {
    }

    @Override // android.app.Activity
    public final void closeOptionsMenu() {
        ((a0) k()).w();
        if (getWindow().hasFeature(0)) {
            super.closeOptionsMenu();
        }
    }

    @Override // o.d, android.app.Activity, android.view.Window.Callback
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        keyEvent.getKeyCode();
        ((a0) k()).w();
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // d.o
    public final void f() {
    }

    @Override // android.app.Activity
    public final View findViewById(int i2) {
        a0 a0Var = (a0) k();
        a0Var.r();
        return a0Var.f583f.findViewById(i2);
    }

    @Override // d.o
    public final void g() {
    }

    @Override // android.app.Activity
    public final MenuInflater getMenuInflater() {
        a0 a0Var = (a0) k();
        if (a0Var.f587j == null) {
            a0Var.w();
            i0 i0Var = a0Var.f586i;
            a0Var.f587j = new h.k(i0Var != null ? i0Var.j() : a0Var.f582e);
        }
        return a0Var.f587j;
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public final Resources getResources() {
        int i2 = e3.f1234a;
        return super.getResources();
    }

    @Override // android.app.Activity
    public final void invalidateOptionsMenu() {
        a0 a0Var = (a0) k();
        a0Var.w();
        a0Var.U |= 1;
        if (a0Var.T) {
            return;
        }
        View decorView = a0Var.f583f.getDecorView();
        WeakHashMap weakHashMap = x.u.f2012a;
        decorView.postOnAnimation(a0Var.V);
        a0Var.T = true;
    }

    public final p k() {
        if (this.f711n == null) {
            m.c cVar = p.f712b;
            this.f711n = new a0(this, null, this, this);
        }
        return this.f711n;
    }

    @Override // androidx.fragment.app.h, android.app.Activity, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        a0 a0Var = (a0) k();
        if (a0Var.A && a0Var.f598u) {
            a0Var.w();
            i0 i0Var = a0Var.f586i;
            if (i0Var != null) {
                i0Var.m(i0Var.f680a.getResources().getBoolean(R.bool.abc_action_bar_embed_tabs));
            }
        }
        j.y yVarA = j.y.a();
        Context context = a0Var.f582e;
        synchronized (yVarA) {
            yVarA.f1497a.k(context);
        }
        a0Var.j(false);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final void onContentChanged() {
    }

    @Override // androidx.fragment.app.h, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        k().e();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i2, KeyEvent keyEvent) {
        Window window;
        if ((Build.VERSION.SDK_INT >= 26 || keyEvent.isCtrlPressed() || KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState()) || keyEvent.getRepeatCount() != 0 || KeyEvent.isModifierKey(keyEvent.getKeyCode()) || (window = getWindow()) == null || window.getDecorView() == null || !window.getDecorView().dispatchKeyShortcutEvent(keyEvent)) ? false : true) {
            return true;
        }
        return super.onKeyDown(i2, keyEvent);
    }

    @Override // androidx.fragment.app.h, android.app.Activity, android.view.Window.Callback
    public final boolean onMenuItemSelected(int i2, MenuItem menuItem) {
        Intent intentE;
        Intent intentMakeMainActivity;
        if (super.onMenuItemSelected(i2, menuItem)) {
            return true;
        }
        a0 a0Var = (a0) k();
        a0Var.w();
        i0 i0Var = a0Var.f586i;
        if (menuItem.getItemId() != 16908332 || i0Var == null || (((a3) i0Var.f684e).f1158b & 4) == 0 || (intentE = androidx.lifecycle.i.E(this)) == null) {
            return false;
        }
        if (!shouldUpRecreateTask(intentE)) {
            navigateUpTo(intentE);
            return true;
        }
        ArrayList arrayList = new ArrayList();
        Intent intentE2 = androidx.lifecycle.i.E(this);
        if (intentE2 == null) {
            intentE2 = androidx.lifecycle.i.E(this);
        }
        if (intentE2 != null) {
            ComponentName component = intentE2.getComponent();
            if (component == null) {
                component = intentE2.resolveActivity(getPackageManager());
            }
            int size = arrayList.size();
            while (true) {
                try {
                    String strF = androidx.lifecycle.i.F(this, component);
                    if (strF == null) {
                        intentMakeMainActivity = null;
                    } else {
                        ComponentName componentName = new ComponentName(component.getPackageName(), strF);
                        intentMakeMainActivity = androidx.lifecycle.i.F(this, componentName) == null ? Intent.makeMainActivity(componentName) : new Intent().setComponent(componentName);
                    }
                    if (intentMakeMainActivity == null) {
                        break;
                    }
                    arrayList.add(size, intentMakeMainActivity);
                    component = intentMakeMainActivity.getComponent();
                } catch (PackageManager.NameNotFoundException e2) {
                    Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
                    throw new IllegalArgumentException(e2);
                }
            }
            arrayList.add(intentE2);
        }
        if (arrayList.isEmpty()) {
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
        }
        Intent[] intentArr = (Intent[]) arrayList.toArray(new Intent[arrayList.size()]);
        intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
        Object obj = o.a.f1732a;
        startActivities(intentArr, null);
        try {
            finishAffinity();
            return true;
        } catch (IllegalStateException unused) {
            finish();
            return true;
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean onMenuOpened(int i2, Menu menu) {
        return super.onMenuOpened(i2, menu);
    }

    @Override // androidx.fragment.app.h, android.app.Activity, android.view.Window.Callback
    public final void onPanelClosed(int i2, Menu menu) {
        super.onPanelClosed(i2, menu);
    }

    @Override // android.app.Activity
    public final void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        ((a0) k()).r();
    }

    @Override // androidx.fragment.app.h, android.app.Activity
    public final void onPostResume() {
        super.onPostResume();
        a0 a0Var = (a0) k();
        a0Var.w();
        i0 i0Var = a0Var.f586i;
        if (i0Var != null) {
            i0Var.f699t = true;
        }
    }

    @Override // androidx.fragment.app.h, android.app.Activity
    public final void onStart() {
        super.onStart();
        a0 a0Var = (a0) k();
        a0Var.L = true;
        a0Var.j(true);
    }

    @Override // androidx.fragment.app.h, android.app.Activity
    public final void onStop() {
        super.onStop();
        a0 a0Var = (a0) k();
        a0Var.L = false;
        a0Var.w();
        i0 i0Var = a0Var.f586i;
        if (i0Var != null) {
            i0Var.f699t = false;
            h.m mVar = i0Var.f698s;
            if (mVar != null) {
                mVar.a();
            }
        }
    }

    @Override // android.app.Activity
    public final void onTitleChanged(CharSequence charSequence, int i2) {
        super.onTitleChanged(charSequence, i2);
        k().i(charSequence);
    }

    @Override // android.app.Activity
    public final void openOptionsMenu() {
        ((a0) k()).w();
        if (getWindow().hasFeature(0)) {
            super.openOptionsMenu();
        }
    }

    @Override // android.app.Activity
    public final void setContentView(int i2) {
        l();
        k().h(i2);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public final void setTheme(int i2) {
        super.setTheme(i2);
        ((a0) k()).O = i2;
    }

    @Override // androidx.activity.h, android.app.Activity
    public void setContentView(View view) {
        l();
        a0 a0Var = (a0) k();
        a0Var.r();
        ViewGroup viewGroup = (ViewGroup) a0Var.f599v.findViewById(android.R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        a0Var.f584g.f722b.onContentChanged();
    }

    @Override // android.app.Activity
    public final void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        l();
        a0 a0Var = (a0) k();
        a0Var.r();
        ViewGroup viewGroup = (ViewGroup) a0Var.f599v.findViewById(android.R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        a0Var.f584g.f722b.onContentChanged();
    }
}
