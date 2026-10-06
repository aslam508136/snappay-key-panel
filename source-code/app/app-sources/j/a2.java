package j;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import com.snapay.app.R;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class a2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static a2 f1148i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public WeakHashMap f1150a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public m.j f1151b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public m.k f1152c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final WeakHashMap f1153d = new WeakHashMap(0);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TypedValue f1154e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1155f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public x f1156g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final PorterDuff.Mode f1147h = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final y1 f1149j = new y1();

    public static synchronized a2 d() {
        if (f1148i == null) {
            a2 a2Var = new a2();
            f1148i = a2Var;
            j(a2Var);
        }
        return f1148i;
    }

    public static synchronized PorterDuffColorFilter h(int i2, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilter;
        y1 y1Var = f1149j;
        y1Var.getClass();
        int i3 = (i2 + 31) * 31;
        porterDuffColorFilter = (PorterDuffColorFilter) y1Var.a(Integer.valueOf(mode.hashCode() + i3));
        if (porterDuffColorFilter == null) {
            porterDuffColorFilter = new PorterDuffColorFilter(i2, mode);
        }
        return porterDuffColorFilter;
    }

    public static void j(a2 a2Var) {
        if (Build.VERSION.SDK_INT < 24) {
            a2Var.a("vector", new x1(3));
            a2Var.a("animated-vector", new x1(1));
            a2Var.a("animated-selector", new x1(0));
            a2Var.a("drawable", new x1(2));
        }
    }

    public final void a(String str, x1 x1Var) {
        if (this.f1151b == null) {
            this.f1151b = new m.j();
        }
        this.f1151b.put(str, x1Var);
    }

    public final synchronized void b(Context context, long j2, Drawable drawable) {
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (constantState != null) {
            m.d dVar = (m.d) this.f1153d.get(context);
            if (dVar == null) {
                dVar = new m.d();
                this.f1153d.put(context, dVar);
            }
            dVar.e(new WeakReference(constantState), j2);
        }
    }

    /* JADX WARN: Code duplicated, block: B:40:0x0104  */
    public final Drawable c(Context context, int i2) {
        LayerDrawable layerDrawable;
        int i3;
        BitmapDrawable bitmapDrawable;
        BitmapDrawable bitmapDrawable2;
        BitmapDrawable bitmapDrawable3;
        if (this.f1154e == null) {
            this.f1154e = new TypedValue();
        }
        TypedValue typedValue = this.f1154e;
        context.getResources().getValue(i2, typedValue, true);
        long j2 = (((long) typedValue.assetCookie) << 32) | ((long) typedValue.data);
        Drawable drawableE = e(context, j2);
        if (drawableE != null) {
            return drawableE;
        }
        if (this.f1156g == null) {
            layerDrawable = null;
        } else if (i2 == R.drawable.abc_cab_background_top_material) {
            layerDrawable = new LayerDrawable(new Drawable[]{f(context, R.drawable.abc_cab_background_internal_bg), f(context, R.drawable.abc_cab_background_top_mtrl_alpha)});
        } else {
            if (i2 == R.drawable.abc_ratingbar_material) {
                i3 = R.dimen.abc_star_big;
            } else if (i2 == R.drawable.abc_ratingbar_indicator_material) {
                i3 = R.dimen.abc_star_medium;
            } else if (i2 == R.drawable.abc_ratingbar_small_material) {
                i3 = R.dimen.abc_star_small;
            } else {
                layerDrawable = null;
            }
            int dimensionPixelSize = context.getResources().getDimensionPixelSize(i3);
            Drawable drawableF = f(context, R.drawable.abc_star_black_48dp);
            Drawable drawableF2 = f(context, R.drawable.abc_star_half_black_48dp);
            if ((drawableF instanceof BitmapDrawable) && drawableF.getIntrinsicWidth() == dimensionPixelSize && drawableF.getIntrinsicHeight() == dimensionPixelSize) {
                bitmapDrawable = (BitmapDrawable) drawableF;
                bitmapDrawable2 = new BitmapDrawable(bitmapDrawable.getBitmap());
            } else {
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(dimensionPixelSize, dimensionPixelSize, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmapCreateBitmap);
                drawableF.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
                drawableF.draw(canvas);
                bitmapDrawable = new BitmapDrawable(bitmapCreateBitmap);
                bitmapDrawable2 = new BitmapDrawable(bitmapCreateBitmap);
            }
            bitmapDrawable2.setTileModeX(Shader.TileMode.REPEAT);
            if ((drawableF2 instanceof BitmapDrawable) && drawableF2.getIntrinsicWidth() == dimensionPixelSize && drawableF2.getIntrinsicHeight() == dimensionPixelSize) {
                bitmapDrawable3 = (BitmapDrawable) drawableF2;
            } else {
                Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(dimensionPixelSize, dimensionPixelSize, Bitmap.Config.ARGB_8888);
                Canvas canvas2 = new Canvas(bitmapCreateBitmap2);
                drawableF2.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
                drawableF2.draw(canvas2);
                bitmapDrawable3 = new BitmapDrawable(bitmapCreateBitmap2);
            }
            layerDrawable = new LayerDrawable(new Drawable[]{bitmapDrawable, bitmapDrawable3, bitmapDrawable2});
            layerDrawable.setId(0, android.R.id.background);
            layerDrawable.setId(1, android.R.id.secondaryProgress);
            layerDrawable.setId(2, android.R.id.progress);
        }
        if (layerDrawable != null) {
            layerDrawable.setChangingConfigurations(typedValue.changingConfigurations);
            b(context, j2, layerDrawable);
        }
        return layerDrawable;
    }

    public final synchronized Drawable e(Context context, long j2) {
        m.d dVar = (m.d) this.f1153d.get(context);
        if (dVar == null) {
            return null;
        }
        WeakReference weakReference = (WeakReference) dVar.d(null, j2);
        if (weakReference != null) {
            Drawable.ConstantState constantState = (Drawable.ConstantState) weakReference.get();
            if (constantState != null) {
                return constantState.newDrawable(context.getResources());
            }
            int iE = androidx.lifecycle.i.e(dVar.f1609b, dVar.f1611d, j2);
            if (iE >= 0) {
                Object[] objArr = dVar.f1610c;
                Object obj = objArr[iE];
                Object obj2 = m.d.f1607e;
                if (obj != obj2) {
                    objArr[iE] = obj2;
                    dVar.f1608a = true;
                }
            }
        }
        return null;
    }

    public final synchronized Drawable f(Context context, int i2) {
        return g(context, i2, false);
    }

    /* JADX WARN: Code duplicated, block: B:58:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:92:0x019b  */
    /* JADX WARN: Code duplicated, block: B:93:0x019c A[Catch: all -> 0x00ce, TryCatch #1 {all -> 0x00ce, blocks: (B:3:0x0001, B:16:0x002d, B:18:0x0032, B:20:0x0038, B:22:0x003e, B:25:0x004c, B:29:0x005d, B:31:0x0061, B:32:0x0068, B:60:0x00ec, B:62:0x00f2, B:64:0x00fa, B:66:0x0100, B:68:0x0106, B:69:0x010a, B:76:0x011f, B:74:0x011b, B:78:0x0125, B:82:0x013c, B:89:0x0172, B:93:0x019c, B:100:0x01a9, B:35:0x0082, B:37:0x0086, B:39:0x0092, B:40:0x009a, B:45:0x00a6, B:47:0x00b9, B:49:0x00c5, B:52:0x00d1, B:53:0x00d8, B:55:0x00da, B:57:0x00e3, B:28:0x0056, B:6:0x0008, B:8:0x0013, B:10:0x0017, B:103:0x01ae, B:104:0x01b7), top: B:108:0x0001, inners: #0 }] */
    /* JADX WARN: Code duplicated, block: B:95:0x01a2 A[ADDED_TO_REGION] */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002b, code lost:
    
        if (((r0 instanceof g0.o) || "android.graphics.drawable.VectorDrawable".equals(r0.getClass().getName())) != false) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final synchronized Drawable g(Context context, int i2, boolean z2) {
        Drawable drawableMutate;
        int next;
        try {
            boolean z3 = false;
            if (!this.f1155f) {
                this.f1155f = true;
                Drawable drawableF = f(context, R.drawable.abc_vector_test);
                if (drawableF != null) {
                }
                this.f1155f = false;
                throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
            }
            m.j jVar = this.f1151b;
            Drawable drawable = null;
            mode = null;
            PorterDuff.Mode mode = null;
            if (jVar == null || jVar.isEmpty()) {
                drawableMutate = null;
            } else {
                m.k kVar = this.f1152c;
                if (kVar != null) {
                    String str = (String) kVar.c(i2, null);
                    if ("appcompat_skip_skip".equals(str) || (str != null && this.f1151b.getOrDefault(str, null) == null)) {
                        drawableMutate = null;
                    }
                } else {
                    this.f1152c = new m.k();
                }
                if (this.f1154e == null) {
                    this.f1154e = new TypedValue();
                }
                TypedValue typedValue = this.f1154e;
                Resources resources = context.getResources();
                resources.getValue(i2, typedValue, true);
                long j2 = (((long) typedValue.assetCookie) << 32) | ((long) typedValue.data);
                drawableMutate = e(context, j2);
                if (drawableMutate == null) {
                    CharSequence charSequence = typedValue.string;
                    if (charSequence != null && charSequence.toString().endsWith(".xml")) {
                        try {
                            XmlResourceParser xml = resources.getXml(i2);
                            AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xml);
                            do {
                                next = xml.next();
                                if (next == 2) {
                                    break;
                                }
                            } while (next != 1);
                            if (next != 2) {
                                throw new XmlPullParserException("No start tag found");
                            }
                            String name = xml.getName();
                            this.f1152c.a(i2, name);
                            z1 z1Var = (z1) this.f1151b.getOrDefault(name, null);
                            if (z1Var != null) {
                                drawableMutate = ((x1) z1Var).a(context, xml, attributeSetAsAttributeSet, context.getTheme());
                            }
                            if (drawableMutate != null) {
                                drawableMutate.setChangingConfigurations(typedValue.changingConfigurations);
                                b(context, j2, drawableMutate);
                            }
                        } catch (Exception e2) {
                            Log.e("ResourceManagerInternal", "Exception while inflating drawable", e2);
                        }
                    }
                    if (drawableMutate == null) {
                        this.f1152c.a(i2, "appcompat_skip_skip");
                    }
                }
            }
            if (drawableMutate == null) {
                drawableMutate = c(context, i2);
            }
            if (drawableMutate == null) {
                Object obj = o.a.f1732a;
                drawableMutate = context.getDrawable(i2);
            }
            if (drawableMutate != null) {
                ColorStateList colorStateListI = i(context, i2);
                if (colorStateListI != null) {
                    if (g1.a(drawableMutate)) {
                        drawableMutate = drawableMutate.mutate();
                    }
                    Drawable drawableK0 = androidx.lifecycle.i.k0(drawableMutate);
                    drawableK0.setTintList(colorStateListI);
                    if (this.f1156g != null && i2 == R.drawable.abc_switch_thumb_material) {
                        mode = PorterDuff.Mode.MULTIPLY;
                    }
                    if (mode != null) {
                        drawableK0.setTintMode(mode);
                    }
                    drawable = drawableK0;
                } else if (this.f1156g != null) {
                    if (i2 == R.drawable.abc_seekbar_track_material) {
                        LayerDrawable layerDrawable = (LayerDrawable) drawableMutate;
                        Drawable drawableFindDrawableByLayerId = layerDrawable.findDrawableByLayerId(android.R.id.background);
                        int iC = r2.c(context, R.attr.colorControlNormal);
                        PorterDuff.Mode mode2 = y.f1495b;
                        x.d(drawableFindDrawableByLayerId, iC, mode2);
                        x.d(layerDrawable.findDrawableByLayerId(android.R.id.secondaryProgress), r2.c(context, R.attr.colorControlNormal), mode2);
                        x.d(layerDrawable.findDrawableByLayerId(android.R.id.progress), r2.c(context, R.attr.colorControlActivated), mode2);
                    } else {
                        if (i2 == R.drawable.abc_ratingbar_material || i2 == R.drawable.abc_ratingbar_indicator_material || i2 == R.drawable.abc_ratingbar_small_material) {
                            LayerDrawable layerDrawable2 = (LayerDrawable) drawableMutate;
                            Drawable drawableFindDrawableByLayerId2 = layerDrawable2.findDrawableByLayerId(android.R.id.background);
                            int iB = r2.b(context, R.attr.colorControlNormal);
                            PorterDuff.Mode mode3 = y.f1495b;
                            x.d(drawableFindDrawableByLayerId2, iB, mode3);
                            x.d(layerDrawable2.findDrawableByLayerId(android.R.id.secondaryProgress), r2.c(context, R.attr.colorControlActivated), mode3);
                            x.d(layerDrawable2.findDrawableByLayerId(android.R.id.progress), r2.c(context, R.attr.colorControlActivated), mode3);
                        } else if (z3) {
                            if (!m(context, i2, drawableMutate)) {
                            }
                        }
                        drawable = drawableMutate;
                    }
                    z3 = true;
                    if (z3) {
                        if (!m(context, i2, drawableMutate)) {
                        }
                    }
                    drawable = drawableMutate;
                } else if (!m(context, i2, drawableMutate) || !z2) {
                    drawable = drawableMutate;
                }
                drawableMutate = drawable;
            }
            if (drawableMutate != null) {
                g1.b(drawableMutate);
            }
        } catch (Throwable th) {
            throw th;
        }
        return drawableMutate;
    }

    public final synchronized ColorStateList i(Context context, int i2) {
        ColorStateList colorStateList;
        m.k kVar;
        try {
            WeakHashMap weakHashMap = this.f1150a;
            ColorStateList colorStateListC = null;
            colorStateList = (weakHashMap == null || (kVar = (m.k) weakHashMap.get(context)) == null) ? null : (ColorStateList) kVar.c(i2, null);
            if (colorStateList == null) {
                x xVar = this.f1156g;
                if (xVar != null) {
                    colorStateListC = xVar.c(context, i2);
                }
                if (colorStateListC != null) {
                    if (this.f1150a == null) {
                        this.f1150a = new WeakHashMap();
                    }
                    m.k kVar2 = (m.k) this.f1150a.get(context);
                    if (kVar2 == null) {
                        kVar2 = new m.k();
                        this.f1150a.put(context, kVar2);
                    }
                    kVar2.a(i2, colorStateListC);
                }
                colorStateList = colorStateListC;
            }
        } catch (Throwable th) {
            throw th;
        }
        return colorStateList;
    }

    public final synchronized void k(Context context) {
        m.d dVar = (m.d) this.f1153d.get(context);
        if (dVar != null) {
            int i2 = dVar.f1611d;
            Object[] objArr = dVar.f1610c;
            for (int i3 = 0; i3 < i2; i3++) {
                objArr[i3] = null;
            }
            dVar.f1611d = 0;
            dVar.f1608a = false;
        }
    }

    public final synchronized void l(x xVar) {
        this.f1156g = xVar;
    }

    /* JADX WARN: Code duplicated, block: B:23:0x004d  */
    /* JADX WARN: Code duplicated, block: B:25:0x0053  */
    /* JADX WARN: Code duplicated, block: B:32:0x0068  */
    /* JADX WARN: Code duplicated, block: B:37:0x0070  */
    /* JADX WARN: Code duplicated, block: B:39:0x0073 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Code duplicated, block: B:41:0x005e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:44:? A[RETURN, SYNTHETIC] */
    public final boolean m(Context context, int i2, Drawable drawable) {
        int iRound;
        int i3;
        boolean z2;
        int i4;
        boolean z3;
        int iC;
        x xVar = this.f1156g;
        if (xVar == null) {
            return false;
        }
        PorterDuff.Mode mode = y.f1495b;
        if (x.a(xVar.f1487a, i2)) {
            i4 = R.attr.colorControlNormal;
        } else if (x.a(xVar.f1489c, i2)) {
            i4 = R.attr.colorControlActivated;
        } else {
            if (!x.a(xVar.f1490d, i2)) {
                if (i2 == R.drawable.abc_list_divider_mtrl_alpha) {
                    iRound = Math.round(40.8f);
                    i3 = android.R.attr.colorForeground;
                    z2 = true;
                } else if (i2 != R.drawable.abc_dialog_material_background) {
                    iRound = -1;
                    i3 = 0;
                    z2 = false;
                }
                if (z2) {
                    if (g1.a(drawable)) {
                        drawable = drawable.mutate();
                    }
                    iC = r2.c(context, i3);
                    synchronized (y.class) {
                        PorterDuffColorFilter porterDuffColorFilterH = h(iC, mode);
                    }
                    drawable.setColorFilter(porterDuffColorFilterH);
                    if (iRound != -1) {
                        drawable.setAlpha(iRound);
                    }
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (z3) {
                    return true;
                }
                return false;
            }
            mode = PorterDuff.Mode.MULTIPLY;
            i4 = android.R.attr.colorBackground;
        }
        i3 = i4;
        iRound = -1;
        z2 = true;
        if (z2) {
            if (g1.a(drawable)) {
                drawable = drawable.mutate();
            }
            iC = r2.c(context, i3);
            synchronized (y.class) {
                PorterDuffColorFilter porterDuffColorFilterH2 = h(iC, mode);
                drawable.setColorFilter(porterDuffColorFilterH2);
                if (iRound != -1) {
                    drawable.setAlpha(iRound);
                }
                z3 = true;
            }
        } else {
            z3 = false;
        }
        if (z3) {
            return true;
        }
        return false;
    }
}
