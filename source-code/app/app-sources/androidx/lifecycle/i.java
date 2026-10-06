package androidx.lifecycle;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AppOpsManager;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.icu.text.DecimalFormatSymbols;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.os.StrictMode;
import android.os.Trace;
import android.provider.Settings;
import android.text.SpannableStringBuilder;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.Log;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.util.StateSet;
import android.util.TypedValue;
import android.util.Xml;
import android.view.ActionMode;
import android.view.InflateException;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import j.c3;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicReference;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public abstract class i {
    public static boolean A = false;
    public static Field C = null;
    public static boolean D = false;
    public static Method E = null;
    public static boolean F = false;
    public static Field G = null;
    public static boolean H = false;
    public static long I = 0;
    public static Method J = null;
    public static boolean K = false;
    public static Method L = null;
    public static boolean M = false;
    public static Field N = null;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Field f429a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f430b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Class f431c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static boolean f432d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static Field f433e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static boolean f434f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static Field f435g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static boolean f436h = false;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static Method f437i = null;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static boolean f438j = false;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static Method f439k = null;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static boolean f440l = false;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static volatile boolean f454z = false;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final int[] f441m = {R.attr.name, R.attr.tint, R.attr.height, R.attr.width, R.attr.alpha, R.attr.autoMirrored, R.attr.tintMode, R.attr.viewportWidth, R.attr.viewportHeight};

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final int[] f442n = {R.attr.name, R.attr.pivotX, R.attr.pivotY, R.attr.scaleX, R.attr.scaleY, R.attr.rotation, R.attr.translateX, R.attr.translateY};

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final int[] f443o = {R.attr.name, R.attr.fillColor, R.attr.pathData, R.attr.strokeColor, R.attr.strokeWidth, R.attr.trimPathStart, R.attr.trimPathEnd, R.attr.trimPathOffset, R.attr.strokeLineCap, R.attr.strokeLineJoin, R.attr.strokeMiterLimit, R.attr.strokeAlpha, R.attr.fillAlpha, R.attr.fillType};

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final int[] f444p = {R.attr.name, R.attr.pathData, R.attr.fillType};

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final int[] f445q = {R.attr.drawable};

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final int[] f446r = {R.attr.name, R.attr.animation};

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final int[] f447s = {R.attr.interpolator, R.attr.duration, R.attr.startOffset, R.attr.repeatCount, R.attr.repeatMode, R.attr.valueFrom, R.attr.valueTo, R.attr.valueType};

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final int[] f448t = {R.attr.ordering};

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final int[] f449u = {R.attr.valueFrom, R.attr.valueTo, R.attr.valueType, R.attr.propertyName};

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final int[] f450v = {R.attr.value, R.attr.interpolator, R.attr.valueType, R.attr.fraction};

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final int[] f451w = {R.attr.propertyName, R.attr.pathData, R.attr.propertyXName, R.attr.propertyYName};

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final int[] f452x = new int[0];

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final Object[] f453y = new Object[0];
    public static final u.c B = new u.c(0);
    public static final String[] O = new String[0];

    public i() {
        new AtomicReference();
    }

    public static float A(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i2, float f2) {
        return !I(xmlPullParser, str) ? f2 : typedArray.getFloat(i2, f2);
    }

    public static int B(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i2, int i3) {
        return !I(xmlPullParser, str) ? i3 : typedArray.getInt(i2, i3);
    }

    public static String C(TypedArray typedArray, XmlResourceParser xmlResourceParser, String str, int i2) {
        if (I(xmlResourceParser, str)) {
            return typedArray.getString(i2);
        }
        return null;
    }

    public static PropertyValuesHolder D(TypedArray typedArray, int i2, int i3, int i4, String str) {
        int color;
        int color2;
        int color3;
        PropertyValuesHolder propertyValuesHolderOfFloat;
        PropertyValuesHolder propertyValuesHolderOfObject;
        TypedValue typedValuePeekValue = typedArray.peekValue(i3);
        boolean z2 = typedValuePeekValue != null;
        int i5 = z2 ? typedValuePeekValue.type : 0;
        TypedValue typedValuePeekValue2 = typedArray.peekValue(i4);
        boolean z3 = typedValuePeekValue2 != null;
        int i6 = z3 ? typedValuePeekValue2.type : 0;
        if (i2 == 4) {
            i2 = ((z2 && K(i5)) || (z3 && K(i6))) ? 3 : 0;
        }
        boolean z4 = i2 == 0;
        PropertyValuesHolder propertyValuesHolderOfInt = null;
        if (i2 == 2) {
            String string = typedArray.getString(i3);
            String string2 = typedArray.getString(i4);
            r.c[] cVarArrR = r(string);
            r.c[] cVarArrR2 = r(string2);
            if (cVarArrR == null && cVarArrR2 == null) {
                return null;
            }
            if (cVarArrR == null) {
                if (cVarArrR2 != null) {
                    return PropertyValuesHolder.ofObject(str, new g0.d(), cVarArrR2);
                }
                return null;
            }
            g0.d dVar = new g0.d();
            if (cVarArrR2 == null) {
                propertyValuesHolderOfObject = PropertyValuesHolder.ofObject(str, dVar, cVarArrR);
            } else {
                if (!f(cVarArrR, cVarArrR2)) {
                    throw new InflateException(" Can't morph from " + string + " to " + string2);
                }
                propertyValuesHolderOfObject = PropertyValuesHolder.ofObject(str, dVar, cVarArrR, cVarArrR2);
            }
            return propertyValuesHolderOfObject;
        }
        g0.e eVar = i2 == 3 ? g0.e.f824a : null;
        if (z4) {
            if (z2) {
                float dimension = i5 == 5 ? typedArray.getDimension(i3, 0.0f) : typedArray.getFloat(i3, 0.0f);
                if (z3) {
                    propertyValuesHolderOfFloat = PropertyValuesHolder.ofFloat(str, dimension, i6 == 5 ? typedArray.getDimension(i4, 0.0f) : typedArray.getFloat(i4, 0.0f));
                } else {
                    propertyValuesHolderOfFloat = PropertyValuesHolder.ofFloat(str, dimension);
                }
            } else {
                propertyValuesHolderOfFloat = PropertyValuesHolder.ofFloat(str, i6 == 5 ? typedArray.getDimension(i4, 0.0f) : typedArray.getFloat(i4, 0.0f));
            }
            propertyValuesHolderOfInt = propertyValuesHolderOfFloat;
        } else if (z2) {
            if (i5 == 5) {
                color2 = (int) typedArray.getDimension(i3, 0.0f);
            } else {
                color2 = K(i5) ? typedArray.getColor(i3, 0) : typedArray.getInt(i3, 0);
            }
            if (z3) {
                if (i6 == 5) {
                    color3 = (int) typedArray.getDimension(i4, 0.0f);
                } else {
                    color3 = K(i6) ? typedArray.getColor(i4, 0) : typedArray.getInt(i4, 0);
                }
                propertyValuesHolderOfInt = PropertyValuesHolder.ofInt(str, color2, color3);
            } else {
                propertyValuesHolderOfInt = PropertyValuesHolder.ofInt(str, color2);
            }
        } else if (z3) {
            if (i6 == 5) {
                color = (int) typedArray.getDimension(i4, 0.0f);
            } else {
                color = K(i6) ? typedArray.getColor(i4, 0) : typedArray.getInt(i4, 0);
            }
            propertyValuesHolderOfInt = PropertyValuesHolder.ofInt(str, color);
        }
        if (propertyValuesHolderOfInt == null || eVar == null) {
            return propertyValuesHolderOfInt;
        }
        propertyValuesHolderOfInt.setEvaluator(eVar);
        return propertyValuesHolderOfInt;
    }

    public static Intent E(Activity activity) {
        Intent parentActivityIntent = activity.getParentActivityIntent();
        if (parentActivityIntent != null) {
            return parentActivityIntent;
        }
        try {
            String strF = F(activity, activity.getComponentName());
            if (strF == null) {
                return null;
            }
            ComponentName componentName = new ComponentName(activity, strF);
            try {
                return F(activity, componentName) == null ? Intent.makeMainActivity(componentName) : new Intent().setComponent(componentName);
            } catch (PackageManager.NameNotFoundException unused) {
                Log.e("NavUtils", "getParentActivityIntent: bad parentActivityName '" + strF + "' in manifest");
                return null;
            }
        } catch (PackageManager.NameNotFoundException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public static String F(Context context, ComponentName componentName) throws PackageManager.NameNotFoundException {
        int i2;
        String string;
        PackageManager packageManager = context.getPackageManager();
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 29) {
            i2 = 269222528;
        } else {
            i2 = i3 >= 24 ? 787072 : 640;
        }
        ActivityInfo activityInfo = packageManager.getActivityInfo(componentName, i2);
        String str = activityInfo.parentActivityName;
        if (str != null) {
            return str;
        }
        Bundle bundle = activityInfo.metaData;
        if (bundle == null || (string = bundle.getString("android.support.PARENT_ACTIVITY")) == null) {
            return null;
        }
        if (string.charAt(0) != '.') {
            return string;
        }
        return context.getPackageName() + string;
    }

    public static File G(Context context) {
        File cacheDir = context.getCacheDir();
        if (cacheDir == null) {
            return null;
        }
        String str = ".font" + Process.myPid() + "-" + Process.myTid() + "-";
        for (int i2 = 0; i2 < 100; i2++) {
            File file = new File(cacheDir, str + i2);
            try {
                if (file.createNewFile()) {
                    return file;
                }
            } catch (IOException unused) {
            }
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:15:0x003a  */
    /* JADX WARN: Code duplicated, block: B:30:0x0074  */
    /* JADX WARN: Code duplicated, block: B:32:0x007a  */
    /* JADX WARN: Code duplicated, block: B:34:0x0080  */
    public static v.a H(TextView textView) {
        int breakStrategy;
        int hyphenationFrequency;
        TextDirectionHeuristic textDirectionHeuristic;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 28) {
            return new v.a(textView.getTextMetricsParams());
        }
        TextPaint textPaint = new TextPaint(textView.getPaint());
        if (Build.VERSION.SDK_INT >= 23) {
            breakStrategy = 1;
            hyphenationFrequency = 1;
        } else {
            breakStrategy = 0;
            hyphenationFrequency = 0;
        }
        TextDirectionHeuristic textDirectionHeuristic2 = TextDirectionHeuristics.FIRSTSTRONG_LTR;
        if (i2 >= 23) {
            breakStrategy = textView.getBreakStrategy();
            hyphenationFrequency = textView.getHyphenationFrequency();
        }
        if (textView.getTransformationMethod() instanceof PasswordTransformationMethod) {
            textDirectionHeuristic = TextDirectionHeuristics.LTR;
        } else if (i2 < 28 || (textView.getInputType() & 15) != 3) {
            boolean z2 = textView.getLayoutDirection() == 1;
            switch (textView.getTextDirection()) {
                case 2:
                    textDirectionHeuristic = TextDirectionHeuristics.ANYRTL_LTR;
                    break;
                case 3:
                    textDirectionHeuristic = TextDirectionHeuristics.LTR;
                    break;
                case 4:
                    textDirectionHeuristic = TextDirectionHeuristics.RTL;
                    break;
                case 5:
                    textDirectionHeuristic = TextDirectionHeuristics.LOCALE;
                    break;
                case 6:
                    textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_LTR;
                    break;
                case 7:
                    textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_RTL;
                    break;
                default:
                    if (!z2) {
                        textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_LTR;
                    } else {
                        textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_RTL;
                    }
                    break;
            }
        } else {
            byte directionality = Character.getDirectionality(DecimalFormatSymbols.getInstance(textView.getTextLocale()).getDigitStrings()[0].codePointAt(0));
            if (directionality == 1 || directionality == 2) {
                textDirectionHeuristic = TextDirectionHeuristics.RTL;
            } else {
                textDirectionHeuristic = TextDirectionHeuristics.LTR;
            }
        }
        return new v.a(textPaint, textDirectionHeuristic, breakStrategy, hyphenationFrequency);
    }

    public static boolean I(XmlPullParser xmlPullParser, String str) {
        return xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", str) != null;
    }

    public static boolean J(Context context) {
        int i2;
        String string;
        String str = context.getPackageName() + "/com.snapay.app.SnapPayAccessibilityService";
        try {
            i2 = Settings.Secure.getInt(context.getContentResolver(), "accessibility_enabled");
        } catch (Settings.SettingNotFoundException e2) {
            e2.printStackTrace();
            i2 = 0;
        }
        TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(':');
        if (i2 == 1 && (string = Settings.Secure.getString(context.getContentResolver(), "enabled_accessibility_services")) != null) {
            simpleStringSplitter.setString(string);
            while (simpleStringSplitter.hasNext()) {
                if (simpleStringSplitter.next().equalsIgnoreCase(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean K(int i2) {
        return i2 >= 28 && i2 <= 31;
    }

    public static boolean L() {
        try {
            if (J == null) {
                return Trace.isEnabled();
            }
        } catch (NoClassDefFoundError | NoSuchMethodError unused) {
        }
        try {
            if (J == null) {
                I = Trace.class.getField("TRACE_TAG_APP").getLong(null);
                J = Trace.class.getMethod("isTagEnabled", Long.TYPE);
            }
            return ((Boolean) J.invoke(null, Long.valueOf(I))).booleanValue();
        } catch (Exception e2) {
            if (!(e2 instanceof InvocationTargetException)) {
                Log.v("Trace", "Unable to call isTagEnabled via reflection", e2);
                return false;
            }
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            throw new RuntimeException(cause);
        }
    }

    public static ValueAnimator M(Context context, Resources resources, Resources.Theme theme, AttributeSet attributeSet, ObjectAnimator objectAnimator, XmlResourceParser xmlResourceParser) {
        ValueAnimator valueAnimator;
        TypedArray typedArray;
        TypedArray typedArray2;
        ValueAnimator valueAnimator2;
        TypedArray typedArrayO = O(resources, theme, attributeSet, f447s);
        TypedArray typedArrayO2 = O(resources, theme, attributeSet, f451w);
        ValueAnimator valueAnimator3 = objectAnimator == null ? new ValueAnimator() : objectAnimator;
        long jB = B(typedArrayO, xmlResourceParser, "duration", 1, 300);
        int resourceId = 0;
        long jB2 = B(typedArrayO, xmlResourceParser, "startOffset", 2, 0);
        int iB = B(typedArrayO, xmlResourceParser, "valueType", 7, 4);
        if (I(xmlResourceParser, "valueFrom") && I(xmlResourceParser, "valueTo")) {
            if (iB == 4) {
                TypedValue typedValuePeekValue = typedArrayO.peekValue(5);
                boolean z2 = typedValuePeekValue != null;
                int i2 = z2 ? typedValuePeekValue.type : 0;
                TypedValue typedValuePeekValue2 = typedArrayO.peekValue(6);
                boolean z3 = typedValuePeekValue2 != null;
                iB = ((z2 && K(i2)) || (z3 && K(z3 ? typedValuePeekValue2.type : 0))) ? 3 : 0;
            }
            PropertyValuesHolder propertyValuesHolderD = D(typedArrayO, iB, 5, 6, "");
            if (propertyValuesHolderD != null) {
                valueAnimator3.setValues(propertyValuesHolderD);
            }
        }
        valueAnimator3.setDuration(jB);
        valueAnimator3.setStartDelay(jB2);
        valueAnimator3.setRepeatCount(B(typedArrayO, xmlResourceParser, "repeatCount", 3, 0));
        valueAnimator3.setRepeatMode(B(typedArrayO, xmlResourceParser, "repeatMode", 4, 1));
        if (typedArrayO2 != null) {
            ObjectAnimator objectAnimator2 = (ObjectAnimator) valueAnimator3;
            String strC = C(typedArrayO2, xmlResourceParser, "pathData", 1);
            if (strC != null) {
                String strC2 = C(typedArrayO2, xmlResourceParser, "propertyXName", 2);
                String strC3 = C(typedArrayO2, xmlResourceParser, "propertyYName", 3);
                if (strC2 == null && strC3 == null) {
                    throw new InflateException(typedArrayO2.getPositionDescription() + " propertyXName or propertyYName is needed for PathData");
                }
                Path pathS = s(strC);
                PathMeasure pathMeasure = new PathMeasure(pathS, false);
                ArrayList arrayList = new ArrayList();
                arrayList.add(Float.valueOf(0.0f));
                float length = 0.0f;
                do {
                    length += pathMeasure.getLength();
                    arrayList.add(Float.valueOf(length));
                } while (pathMeasure.nextContour());
                PathMeasure pathMeasure2 = new PathMeasure(pathS, false);
                int iMin = Math.min(100, ((int) (length / 0.5f)) + 1);
                float[] fArr = new float[iMin];
                float[] fArr2 = new float[iMin];
                float[] fArr3 = new float[2];
                float f2 = length / (iMin - 1);
                valueAnimator = valueAnimator3;
                typedArray = typedArrayO;
                int i3 = 0;
                float f3 = 0.0f;
                while (true) {
                    if (resourceId >= iMin) {
                        break;
                    }
                    int i4 = iMin;
                    pathMeasure2.getPosTan(f3 - ((Float) arrayList.get(i3)).floatValue(), fArr3, null);
                    fArr[resourceId] = fArr3[0];
                    fArr2[resourceId] = fArr3[1];
                    f3 += f2;
                    int i5 = i3 + 1;
                    if (i5 < arrayList.size() && f3 > ((Float) arrayList.get(i5)).floatValue()) {
                        pathMeasure2.nextContour();
                        i3 = i5;
                    }
                    resourceId++;
                    iMin = i4;
                }
                PropertyValuesHolder propertyValuesHolderOfFloat = strC2 != null ? PropertyValuesHolder.ofFloat(strC2, fArr) : null;
                PropertyValuesHolder propertyValuesHolderOfFloat2 = strC3 != null ? PropertyValuesHolder.ofFloat(strC3, fArr2) : null;
                if (propertyValuesHolderOfFloat == null) {
                    resourceId = 0;
                    objectAnimator2.setValues(propertyValuesHolderOfFloat2);
                } else {
                    resourceId = 0;
                    if (propertyValuesHolderOfFloat2 == null) {
                        objectAnimator2.setValues(propertyValuesHolderOfFloat);
                    } else {
                        objectAnimator2.setValues(propertyValuesHolderOfFloat, propertyValuesHolderOfFloat2);
                    }
                }
            } else {
                valueAnimator = valueAnimator3;
                typedArray = typedArrayO;
                objectAnimator2.setPropertyName(C(typedArrayO2, xmlResourceParser, "propertyName", 0));
            }
        } else {
            valueAnimator = valueAnimator3;
            typedArray = typedArrayO;
        }
        if (I(xmlResourceParser, "interpolator")) {
            typedArray2 = typedArray;
            resourceId = typedArray2.getResourceId(resourceId, resourceId);
        } else {
            typedArray2 = typedArray;
        }
        if (resourceId > 0) {
            valueAnimator2 = valueAnimator;
            valueAnimator2.setInterpolator(AnimationUtils.loadInterpolator(context, resourceId));
        } else {
            valueAnimator2 = valueAnimator;
        }
        typedArray2.recycle();
        if (typedArrayO2 != null) {
            typedArrayO2.recycle();
        }
        return valueAnimator2;
    }

    public static MappedByteBuffer N(Context context, Uri uri) {
        try {
            ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r", null);
            if (parcelFileDescriptorOpenFileDescriptor == null) {
                if (parcelFileDescriptorOpenFileDescriptor != null) {
                    parcelFileDescriptorOpenFileDescriptor.close();
                }
                return null;
            }
            try {
                FileInputStream fileInputStream = new FileInputStream(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor());
                try {
                    FileChannel channel = fileInputStream.getChannel();
                    MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0L, channel.size());
                    fileInputStream.close();
                    parcelFileDescriptorOpenFileDescriptor.close();
                    return map;
                } catch (Throwable th) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                try {
                    parcelFileDescriptorOpenFileDescriptor.close();
                } catch (Throwable th4) {
                    th3.addSuppressed(th4);
                }
                throw th3;
            }
        } catch (IOException unused) {
            return null;
        }
    }

    public static TypedArray O(Resources resources, Resources.Theme theme, AttributeSet attributeSet, int[] iArr) {
        return theme == null ? resources.obtainAttributes(attributeSet, iArr) : theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }

    public static void P(InputConnection inputConnection, EditorInfo editorInfo, View view) {
        if (inputConnection == null || editorInfo.hintText != null) {
            return;
        }
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
        }
    }

    public static boolean Q(ViewParent viewParent, View view, float f2, float f3, boolean z2) {
        try {
            return viewParent.onNestedFling(view, f2, f3, z2);
        } catch (AbstractMethodError e2) {
            Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onNestedFling", e2);
            return false;
        }
    }

    public static boolean R(ViewParent viewParent, View view, float f2, float f3) {
        try {
            return viewParent.onNestedPreFling(view, f2, f3);
        } catch (AbstractMethodError e2) {
            Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onNestedPreFling", e2);
            return false;
        }
    }

    public static void S(ViewParent viewParent, View view, int i2, int i3, int[] iArr, int i4) {
        if (viewParent instanceof x.h) {
            ((x.h) viewParent).c(view, i2, i3, iArr, i4);
            return;
        }
        if (i4 == 0) {
            try {
                viewParent.onNestedPreScroll(view, i2, i3, iArr);
            } catch (AbstractMethodError e2) {
                Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onNestedPreScroll", e2);
            }
        }
    }

    public static void T(ViewParent viewParent, View view, int i2, int i3, int i4, int i5, int i6, int[] iArr) {
        if (viewParent instanceof x.i) {
            ((x.i) viewParent).d(view, i2, i3, i4, i5, i6, iArr);
            return;
        }
        iArr[0] = iArr[0] + i4;
        iArr[1] = iArr[1] + i5;
        if (viewParent instanceof x.h) {
            ((x.h) viewParent).e(view, i2, i3, i4, i5, i6);
            return;
        }
        if (i6 == 0) {
            try {
                viewParent.onNestedScroll(view, i2, i3, i4, i5);
            } catch (AbstractMethodError e2) {
                Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onNestedScroll", e2);
            }
        }
    }

    public static i0.b U(l0.a aVar) {
        boolean z2;
        try {
            try {
                aVar.t();
                try {
                    return (i0.b) k0.f.f1544a.read(aVar);
                } catch (EOFException e2) {
                    e = e2;
                    z2 = false;
                    if (z2) {
                        return i0.d.f1138a;
                    }
                    throw new i0.c(e);
                }
            } catch (EOFException e3) {
                e = e3;
                z2 = true;
            }
        } catch (l0.d e4) {
            throw new i0.c(e4);
        } catch (IOException e5) {
            throw new i0.c(e5);
        } catch (NumberFormatException e6) {
            throw new i0.c(e6);
        }
    }

    public static q.b V(XmlResourceParser xmlResourceParser, Resources resources) throws XmlPullParserException, IOException {
        int next;
        do {
            next = xmlResourceParser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next != 2) {
            throw new XmlPullParserException("No start tag found");
        }
        xmlResourceParser.require(2, null, "font-family");
        if (xmlResourceParser.getName().equals("font-family")) {
            TypedArray typedArrayObtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlResourceParser), n.a.f1721b);
            String string = typedArrayObtainAttributes.getString(0);
            String string2 = typedArrayObtainAttributes.getString(4);
            String string3 = typedArrayObtainAttributes.getString(5);
            int resourceId = typedArrayObtainAttributes.getResourceId(1, 0);
            int integer = typedArrayObtainAttributes.getInteger(2, 1);
            int integer2 = typedArrayObtainAttributes.getInteger(3, 500);
            String string4 = typedArrayObtainAttributes.getString(6);
            typedArrayObtainAttributes.recycle();
            if (string != null && string2 != null && string3 != null) {
                while (xmlResourceParser.next() != 3) {
                    j0(xmlResourceParser);
                }
                return new q.e(new j.s(string, string2, string3, W(resources, resourceId)), integer, integer2, string4);
            }
            ArrayList arrayList = new ArrayList();
            while (xmlResourceParser.next() != 3) {
                if (xmlResourceParser.getEventType() == 2) {
                    if (xmlResourceParser.getName().equals("font")) {
                        TypedArray typedArrayObtainAttributes2 = resources.obtainAttributes(Xml.asAttributeSet(xmlResourceParser), n.a.f1722c);
                        int i2 = typedArrayObtainAttributes2.getInt(typedArrayObtainAttributes2.hasValue(8) ? 8 : 1, 400);
                        boolean z2 = 1 == typedArrayObtainAttributes2.getInt(typedArrayObtainAttributes2.hasValue(6) ? 6 : 2, 0);
                        int i3 = typedArrayObtainAttributes2.hasValue(9) ? 9 : 3;
                        String string5 = typedArrayObtainAttributes2.getString(typedArrayObtainAttributes2.hasValue(7) ? 7 : 4);
                        int i4 = typedArrayObtainAttributes2.getInt(i3, 0);
                        int i5 = typedArrayObtainAttributes2.hasValue(5) ? 5 : 0;
                        int resourceId2 = typedArrayObtainAttributes2.getResourceId(i5, 0);
                        String string6 = typedArrayObtainAttributes2.getString(i5);
                        typedArrayObtainAttributes2.recycle();
                        while (xmlResourceParser.next() != 3) {
                            j0(xmlResourceParser);
                        }
                        arrayList.add(new q.d(string6, i2, z2, string5, i4, resourceId2));
                    } else {
                        j0(xmlResourceParser);
                    }
                }
            }
            if (!arrayList.isEmpty()) {
                return new q.c((q.d[]) arrayList.toArray(new q.d[arrayList.size()]));
            }
        } else {
            j0(xmlResourceParser);
        }
        return null;
    }

    public static List W(Resources resources, int i2) {
        if (i2 == 0) {
            return Collections.emptyList();
        }
        TypedArray typedArrayObtainTypedArray = resources.obtainTypedArray(i2);
        try {
            if (typedArrayObtainTypedArray.length() == 0) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            if (typedArrayObtainTypedArray.getType(0) == 1) {
                for (int i3 = 0; i3 < typedArrayObtainTypedArray.length(); i3++) {
                    int resourceId = typedArrayObtainTypedArray.getResourceId(i3, 0);
                    if (resourceId != 0) {
                        String[] stringArray = resources.getStringArray(resourceId);
                        ArrayList arrayList2 = new ArrayList();
                        for (String str : stringArray) {
                            arrayList2.add(Base64.decode(str, 0));
                        }
                        arrayList.add(arrayList2);
                    }
                }
            } else {
                String[] stringArray2 = resources.getStringArray(i2);
                ArrayList arrayList3 = new ArrayList();
                for (String str2 : stringArray2) {
                    arrayList3.add(Base64.decode(str2, 0));
                }
                arrayList.add(arrayList3);
            }
            return arrayList;
        } finally {
            typedArrayObtainTypedArray.recycle();
        }
    }

    public static void Y(TextView textView, int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException();
        }
        if (Build.VERSION.SDK_INT >= 28) {
            textView.setFirstBaselineToTopHeight(i2);
            return;
        }
        Paint.FontMetricsInt fontMetricsInt = textView.getPaint().getFontMetricsInt();
        int i3 = textView.getIncludeFontPadding() ? fontMetricsInt.top : fontMetricsInt.ascent;
        if (i2 > Math.abs(i3)) {
            textView.setPadding(textView.getPaddingLeft(), i2 + i3, textView.getPaddingRight(), textView.getPaddingBottom());
        }
    }

    public static void Z(TextView textView, int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException();
        }
        Paint.FontMetricsInt fontMetricsInt = textView.getPaint().getFontMetricsInt();
        int i3 = textView.getIncludeFontPadding() ? fontMetricsInt.bottom : fontMetricsInt.descent;
        if (i2 > Math.abs(i3)) {
            textView.setPadding(textView.getPaddingLeft(), textView.getPaddingTop(), textView.getPaddingRight(), i2 - i3);
        }
    }

    public static boolean a0(Drawable drawable, int i2) {
        if (Build.VERSION.SDK_INT >= 23) {
            return drawable.setLayoutDirection(i2);
        }
        if (!f438j) {
            try {
                Method declaredMethod = Drawable.class.getDeclaredMethod("setLayoutDirection", Integer.TYPE);
                f437i = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (NoSuchMethodException e2) {
                Log.i("DrawableCompat", "Failed to retrieve setLayoutDirection(int) method", e2);
            }
            f438j = true;
        }
        Method method = f437i;
        if (method != null) {
            try {
                method.invoke(drawable, Integer.valueOf(i2));
                return true;
            } catch (Exception e3) {
                Log.i("DrawableCompat", "Failed to invoke setLayoutDirection(int) via reflection", e3);
                f437i = null;
            }
        }
        return false;
    }

    public static void b0(PopupWindow popupWindow, boolean z2) {
        if (Build.VERSION.SDK_INT >= 23) {
            popupWindow.setOverlapAnchor(z2);
            return;
        }
        if (!H) {
            try {
                Field declaredField = PopupWindow.class.getDeclaredField("mOverlapAnchor");
                G = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException e2) {
                Log.i("PopupWindowCompatApi21", "Could not fetch mOverlapAnchor field from PopupWindow", e2);
            }
            H = true;
        }
        Field field = G;
        if (field != null) {
            try {
                field.set(popupWindow, Boolean.valueOf(z2));
            } catch (IllegalAccessException e3) {
                Log.i("PopupWindowCompatApi21", "Could not set overlap anchor field in PopupWindow", e3);
            }
        }
    }

    public static void c(d.n nVar, LinearLayout linearLayout, String str, String str2, int i2, boolean z2, int i3) {
        LinearLayout linearLayout2 = new LinearLayout(nVar);
        linearLayout2.setOrientation(0);
        linearLayout2.setGravity(16);
        int i4 = i3 * 40;
        int i5 = i3 * 24;
        linearLayout2.setPadding(i4, i5, i4, i5);
        TextView textView = new TextView(nVar);
        textView.setText("●");
        textView.setTextColor(i2);
        textView.setTextSize(12.0f);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.rightMargin = i3 * 16;
        textView.setLayoutParams(layoutParams);
        linearLayout2.addView(textView);
        TextView textView2 = new TextView(nVar);
        textView2.setText(str);
        textView2.setTextColor(Color.parseColor("#333355"));
        textView2.setTextSize(14.0f);
        textView2.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        linearLayout2.addView(textView2);
        TextView textView3 = new TextView(nVar);
        textView3.setText(str2);
        textView3.setTextColor(Color.parseColor("#333355"));
        textView3.setTextSize(14.0f);
        if (z2) {
            textView3.setTypeface(null, 1);
        }
        linearLayout2.addView(textView3);
        linearLayout.addView(linearLayout2);
    }

    public static void c0(EditorInfo editorInfo, CharSequence charSequence, int i2, int i3) {
        if (editorInfo.extras == null) {
            editorInfo.extras = new Bundle();
        }
        editorInfo.extras.putCharSequence("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SURROUNDING_TEXT", charSequence != null ? new SpannableStringBuilder(charSequence) : null);
        editorInfo.extras.putInt("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_HEAD", i2);
        editorInfo.extras.putInt("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_END", i3);
    }

    public static int d(int i2, int i3, int[] iArr) {
        int i4 = i2 - 1;
        int i5 = 0;
        while (i5 <= i4) {
            int i6 = (i5 + i4) >>> 1;
            int i7 = iArr[i6];
            if (i7 < i3) {
                i5 = i6 + 1;
            } else {
                if (i7 <= i3) {
                    return i6;
                }
                i4 = i6 - 1;
            }
        }
        return ~i5;
    }

    public static void d0(Drawable drawable, int i2) {
        drawable.setTint(i2);
    }

    public static int e(long[] jArr, int i2, long j2) {
        int i3 = i2 - 1;
        int i4 = 0;
        while (i4 <= i3) {
            int i5 = (i4 + i3) >>> 1;
            long j3 = jArr[i5];
            if (j3 < j2) {
                i4 = i5 + 1;
            } else {
                if (j3 <= j2) {
                    return i5;
                }
                i3 = i5 - 1;
            }
        }
        return ~i4;
    }

    public static void e0(Drawable drawable, ColorStateList colorStateList) {
        drawable.setTintList(colorStateList);
    }

    public static boolean f(r.c[] cVarArr, r.c[] cVarArr2) {
        if (cVarArr == null || cVarArr2 == null || cVarArr.length != cVarArr2.length) {
            return false;
        }
        for (int i2 = 0; i2 < cVarArr.length; i2++) {
            r.c cVar = cVarArr[i2];
            char c2 = cVar.f1894a;
            r.c cVar2 = cVarArr2[i2];
            if (c2 != cVar2.f1894a || cVar.f1895b.length != cVar2.f1895b.length) {
                return false;
            }
        }
        return true;
    }

    public static void f0(Drawable drawable, PorterDuff.Mode mode) {
        drawable.setTintMode(mode);
    }

    public static void g(Activity activity, h.a aVar) {
        if (f454z) {
            return;
        }
        new Thread(new m0.b(activity, aVar, 2)).start();
    }

    public static void g0(View view, CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 26) {
            view.setTooltipText(charSequence);
            return;
        }
        c3 c3Var = c3.f1206j;
        if (c3Var != null && c3Var.f1208a == view) {
            c3.b(null);
        }
        if (!TextUtils.isEmpty(charSequence)) {
            new c3(view, charSequence);
            return;
        }
        c3 c3Var2 = c3.f1207k;
        if (c3Var2 != null && c3Var2.f1208a == view) {
            c3Var2.a();
        }
        view.setOnLongClickListener(null);
        view.setLongClickable(false);
        view.setOnHoverListener(null);
    }

    public static int h(Context context, String str) {
        int iMyPid = Process.myPid();
        int iMyUid = Process.myUid();
        String packageName = context.getPackageName();
        if (context.checkPermission(str, iMyPid, iMyUid) == -1) {
            return -1;
        }
        int i2 = Build.VERSION.SDK_INT;
        String strPermissionToOp = i2 >= 23 ? AppOpsManager.permissionToOp(str) : null;
        if (strPermissionToOp != null) {
            if (packageName == null) {
                String[] packagesForUid = context.getPackageManager().getPackagesForUid(iMyUid);
                if (packagesForUid == null || packagesForUid.length <= 0) {
                    return -1;
                }
                packageName = packagesForUid[0];
            }
            if ((i2 >= 23 ? ((AppOpsManager) context.getSystemService(AppOpsManager.class)).noteProxyOpNoThrow(strPermissionToOp, packageName) : 1) != 0) {
                return -2;
            }
        }
        return 0;
    }

    public static void h0(PopupWindow popupWindow, int i2) {
        if (Build.VERSION.SDK_INT >= 23) {
            popupWindow.setWindowLayoutType(i2);
            return;
        }
        if (!F) {
            try {
                Method declaredMethod = PopupWindow.class.getDeclaredMethod("setWindowLayoutType", Integer.TYPE);
                E = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (Exception unused) {
            }
            F = true;
        }
        Method method = E;
        if (method != null) {
            try {
                method.invoke(popupWindow, Integer.valueOf(i2));
            } catch (Exception unused2) {
            }
        }
    }

    public static boolean i(Signature[] signatureArr) {
        if (signatureArr != null && signatureArr.length != 0) {
            try {
                for (Signature signature : signatureArr) {
                    byte[] bArrDigest = MessageDigest.getInstance("SHA-256").digest(signature.toByteArray());
                    StringBuilder sb = new StringBuilder();
                    for (int i2 = 0; i2 < bArrDigest.length; i2++) {
                        sb.append(String.format("%02X", Byte.valueOf(bArrDigest[i2])));
                        if (i2 < bArrDigest.length - 1) {
                            sb.append(":");
                        }
                    }
                    if ("DD:EA:F7:1C:49:51:DD:35:9F:F2:E2:B0:99:00:7A:A8:50:FF:32:7B:C9:24:B1:0C:D1:3A:54:D5:59:29:A0:BB".equalsIgnoreCase(sb.toString())) {
                        return true;
                    }
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public static void i0(final d.n nVar, final String str, String str2, final String str3) {
        String str4;
        int i2;
        f454z = true;
        int i3 = (int) nVar.getResources().getDisplayMetrics().density;
        try {
            str4 = "v" + nVar.getPackageManager().getPackageInfo(nVar.getPackageName(), 0).versionName;
        } catch (Exception unused) {
            str4 = "v1.0";
        }
        String str5 = str4;
        FrameLayout frameLayout = new FrameLayout(nVar);
        frameLayout.setBackgroundColor(Color.parseColor("#ECEEFF"));
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        ScrollView scrollView = new ScrollView(nVar);
        scrollView.setBackgroundColor(Color.parseColor("#ECEEFF"));
        scrollView.setFillViewport(false);
        scrollView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        LinearLayout linearLayout = new LinearLayout(nVar);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(1);
        int i4 = i3 * 24;
        int i5 = i3 * 48;
        linearLayout.setPadding(i4, i5, i4, i5);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        scrollView.addView(linearLayout);
        frameLayout.addView(scrollView);
        FrameLayout frameLayout2 = new FrameLayout(nVar);
        int i6 = i3 * 80;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i6, i6);
        layoutParams.gravity = 1;
        int i7 = i3 * 12;
        layoutParams.bottomMargin = i7;
        frameLayout2.setLayoutParams(layoutParams);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(-1);
        gradientDrawable.setCornerRadius(i3 * 18);
        frameLayout2.setBackground(gradientDrawable);
        int i8 = i3 * 4;
        float f2 = i8;
        frameLayout2.setElevation(f2);
        ImageView imageView = new ImageView(nVar);
        imageView.setImageResource(com.snapay.app.R.drawable.snapay);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        int i9 = i3 * 56;
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(i9, i9);
        layoutParams2.gravity = 17;
        imageView.setLayoutParams(layoutParams2);
        frameLayout2.addView(imageView);
        linearLayout.addView(frameLayout2);
        TextView textView = new TextView(nVar);
        textView.setText("SnapPay");
        textView.setTextColor(Color.parseColor("#1C1B3A"));
        textView.setTextSize(20.0f);
        textView.setTypeface(null, 1);
        textView.setGravity(17);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams3.bottomMargin = i8;
        textView.setLayoutParams(layoutParams3);
        linearLayout.addView(textView);
        TextView textView2 = new TextView(nVar);
        textView2.setText("—  Automated Payment Assistant  —");
        textView2.setTextColor(Color.parseColor("#9090bb"));
        textView2.setTextSize(12.0f);
        textView2.setGravity(17);
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams4.bottomMargin = i4;
        textView2.setLayoutParams(layoutParams4);
        linearLayout.addView(textView2);
        FrameLayout frameLayout3 = new FrameLayout(nVar);
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-1, i3 * 190);
        layoutParams5.bottomMargin = i4;
        frameLayout3.setLayoutParams(layoutParams5);
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setColor(Color.parseColor("#DDE0FF"));
        gradientDrawable2.setCornerRadius(i4);
        frameLayout3.setBackground(gradientDrawable2);
        LinearLayout linearLayout2 = new LinearLayout(nVar);
        linearLayout2.setOrientation(1);
        linearLayout2.setGravity(17);
        int i10 = i5;
        FrameLayout.LayoutParams layoutParams6 = new FrameLayout.LayoutParams(i3 * 90, i3 * 148);
        layoutParams6.gravity = 17;
        linearLayout2.setLayoutParams(layoutParams6);
        GradientDrawable gradientDrawable3 = new GradientDrawable();
        gradientDrawable3.setColor(0);
        gradientDrawable3.setStroke(i3 * 3, Color.parseColor("#8B7FD4"));
        int i11 = i3 * 14;
        gradientDrawable3.setCornerRadius(i11);
        linearLayout2.setBackground(gradientDrawable3);
        FrameLayout frameLayout4 = new FrameLayout(nVar);
        int i12 = i3 * 52;
        LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(i12, i12);
        layoutParams7.gravity = 1;
        int i13 = i3 * 8;
        layoutParams7.bottomMargin = i13;
        frameLayout4.setLayoutParams(layoutParams7);
        GradientDrawable gradientDrawable4 = new GradientDrawable();
        gradientDrawable4.setShape(1);
        gradientDrawable4.setColor(Color.parseColor("#7B72D4"));
        frameLayout4.setBackground(gradientDrawable4);
        TextView textView3 = new TextView(nVar);
        textView3.setText("↓");
        textView3.setTextColor(-1);
        textView3.setTextSize(20.0f);
        textView3.setTypeface(null, 1);
        textView3.setGravity(17);
        textView3.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        frameLayout4.addView(textView3);
        linearLayout2.addView(frameLayout4);
        View view = new View(nVar);
        LinearLayout.LayoutParams layoutParams8 = new LinearLayout.LayoutParams(i9, i3 * 5);
        layoutParams8.gravity = 1;
        view.setLayoutParams(layoutParams8);
        GradientDrawable gradientDrawable5 = new GradientDrawable();
        gradientDrawable5.setColor(Color.parseColor("#8B7FD4"));
        gradientDrawable5.setCornerRadius(f2);
        view.setBackground(gradientDrawable5);
        linearLayout2.addView(view);
        frameLayout3.addView(linearLayout2);
        FrameLayout frameLayout5 = new FrameLayout(nVar);
        int i14 = i3 * 36;
        FrameLayout.LayoutParams layoutParams9 = new FrameLayout.LayoutParams(i14, i14);
        layoutParams9.gravity = 8388661;
        int i15 = i3 * 16;
        layoutParams9.topMargin = i15;
        layoutParams9.rightMargin = i15;
        frameLayout5.setLayoutParams(layoutParams9);
        GradientDrawable gradientDrawable6 = new GradientDrawable();
        gradientDrawable6.setShape(1);
        gradientDrawable6.setColor(Color.parseColor("#7B6CF6"));
        frameLayout5.setBackground(gradientDrawable6);
        TextView textView4 = new TextView(nVar);
        textView4.setText("!");
        textView4.setTextColor(-1);
        textView4.setTextSize(14.0f);
        textView4.setTypeface(null, 1);
        textView4.setGravity(17);
        textView4.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        frameLayout5.addView(textView4);
        frameLayout3.addView(frameLayout5);
        linearLayout.addView(frameLayout3);
        TextView textView5 = new TextView(nVar);
        textView5.setText("Update Required");
        textView5.setTextColor(Color.parseColor("#1C1B3A"));
        textView5.setTextSize(24.0f);
        textView5.setTypeface(null, 1);
        textView5.setGravity(17);
        LinearLayout.LayoutParams layoutParams10 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams10.bottomMargin = i3 * 10;
        textView5.setLayoutParams(layoutParams10);
        linearLayout.addView(textView5);
        String strConcat = (str2 == null || str2.isEmpty()) ? "A new version of SnapPay is available with performance improvements, bug fixes, and enhanced security.\nPlease update to continue." : str2.concat("\n\nPlease update to continue.");
        TextView textView6 = new TextView(nVar);
        textView6.setText(strConcat);
        textView6.setTextColor(Color.parseColor("#6666aa"));
        textView6.setTextSize(13.0f);
        textView6.setGravity(17);
        textView6.setLineSpacing(f2, 1.0f);
        LinearLayout.LayoutParams layoutParams11 = new LinearLayout.LayoutParams(-1, -2);
        int i16 = i3 * 20;
        layoutParams11.bottomMargin = i16;
        textView6.setLayoutParams(layoutParams11);
        linearLayout.addView(textView6);
        LinearLayout linearLayout3 = new LinearLayout(nVar);
        linearLayout3.setOrientation(1);
        LinearLayout.LayoutParams layoutParams12 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams12.bottomMargin = i16;
        linearLayout3.setLayoutParams(layoutParams12);
        GradientDrawable gradientDrawable7 = new GradientDrawable();
        gradientDrawable7.setColor(-1);
        gradientDrawable7.setCornerRadius(i15);
        linearLayout3.setBackground(gradientDrawable7);
        int i17 = i3 * 6;
        linearLayout3.setPadding(0, i17, 0, i17);
        int i18 = i17;
        int i19 = 17;
        c(nVar, linearLayout3, "Current Version", str5, Color.parseColor("#ccccee"), false, i3);
        View view2 = new View(nVar);
        LinearLayout.LayoutParams layoutParams13 = new LinearLayout.LayoutParams(-1, 1);
        layoutParams13.setMargins(i14, 0, i14, 0);
        view2.setLayoutParams(layoutParams13);
        view2.setBackgroundColor(Color.parseColor("#eeeeff"));
        linearLayout3.addView(view2);
        c(nVar, linearLayout3, "Latest Version", "v" + str, Color.parseColor("#5B4FCF"), true, i3);
        linearLayout.addView(linearLayout3);
        final ProgressBar progressBar = new ProgressBar(nVar, null, R.attr.progressBarStyleHorizontal);
        LinearLayout.LayoutParams layoutParams14 = new LinearLayout.LayoutParams(-1, i13);
        layoutParams14.bottomMargin = i8;
        progressBar.setLayoutParams(layoutParams14);
        progressBar.setMax(100);
        progressBar.setVisibility(8);
        linearLayout.addView(progressBar);
        final TextView textView7 = new TextView(nVar);
        textView7.setTextColor(Color.parseColor("#5B4FCF"));
        textView7.setTextSize(12.0f);
        textView7.setGravity(17);
        int i20 = -2;
        LinearLayout.LayoutParams layoutParams15 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams15.bottomMargin = i7;
        textView7.setLayoutParams(layoutParams15);
        textView7.setVisibility(8);
        linearLayout.addView(textView7);
        final Button button = new Button(nVar);
        button.setText("↓   UPDATE NOW");
        button.setTextColor(-1);
        button.setTextSize(14.0f);
        button.setTypeface(null, 1);
        button.setLetterSpacing(0.08f);
        GradientDrawable gradientDrawable8 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{Color.parseColor("#4A3FC0"), Color.parseColor("#6A5FE0")});
        gradientDrawable8.setCornerRadius(i3 * 50);
        button.setBackground(gradientDrawable8);
        LinearLayout.LayoutParams layoutParams16 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams16.bottomMargin = i11;
        button.setPadding(0, i11, 0, i11);
        button.setLayoutParams(layoutParams16);
        linearLayout.addView(button);
        TextView textView8 = new TextView(nVar);
        textView8.setText("🛡  You must update to continue using SnapPay.");
        textView8.setTextColor(Color.parseColor("#9999bb"));
        textView8.setTextSize(11.0f);
        textView8.setGravity(17);
        LinearLayout.LayoutParams layoutParams17 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams17.bottomMargin = i15;
        textView8.setLayoutParams(layoutParams17);
        linearLayout.addView(textView8);
        LinearLayout linearLayout4 = new LinearLayout(nVar);
        linearLayout4.setOrientation(0);
        linearLayout4.setGravity(17);
        LinearLayout.LayoutParams layoutParams18 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams18.bottomMargin = i15;
        linearLayout4.setLayoutParams(layoutParams18);
        int i21 = 3;
        int[][] iArr = {new int[]{com.snapay.app.R.drawable.ic_shield_check}, new int[]{com.snapay.app.R.drawable.ic_lightning}, new int[]{com.snapay.app.R.drawable.ic_shield}};
        String[] strArr = {"Secure", "Fast", "Reliable"};
        int i22 = 0;
        while (i22 < i21) {
            LinearLayout linearLayout5 = new LinearLayout(nVar);
            linearLayout5.setOrientation(1);
            linearLayout5.setGravity(i19);
            linearLayout5.setLayoutParams(new LinearLayout.LayoutParams(0, i20, 1.0f));
            ImageView imageView2 = new ImageView(nVar);
            imageView2.setImageResource(iArr[i22][0]);
            imageView2.setColorFilter(Color.parseColor("#9B92D0"), PorterDuff.Mode.SRC_IN);
            int i23 = i3 * 28;
            LinearLayout.LayoutParams layoutParams19 = new LinearLayout.LayoutParams(i23, i23);
            int i24 = i18;
            layoutParams19.bottomMargin = i24;
            int[][] iArr2 = iArr;
            layoutParams19.gravity = 1;
            imageView2.setLayoutParams(layoutParams19);
            imageView2.setScaleType(ImageView.ScaleType.FIT_CENTER);
            linearLayout5.addView(imageView2);
            TextView textView9 = new TextView(nVar);
            textView9.setText(strArr[i22]);
            textView9.setTextColor(Color.parseColor("#9B92D0"));
            textView9.setTextSize(12.0f);
            textView9.setGravity(17);
            linearLayout5.addView(textView9);
            linearLayout4.addView(linearLayout5);
            if (i22 < 2) {
                View view3 = new View(nVar);
                i2 = i10;
                view3.setLayoutParams(new ViewGroup.LayoutParams(i3 * 1, i2));
                view3.setBackgroundColor(Color.parseColor("#3A2F7A"));
                linearLayout4.addView(view3);
            } else {
                i2 = i10;
            }
            i22++;
            i10 = i2;
            iArr = iArr2;
            i20 = -2;
            i21 = 3;
            i18 = i24;
            i19 = 17;
        }
        linearLayout.addView(linearLayout4);
        TextView textView10 = new TextView(nVar);
        textView10.setText("Powered by SnapPay");
        textView10.setTextColor(Color.parseColor("#6A5FE0"));
        textView10.setTextSize(12.0f);
        textView10.setGravity(17);
        linearLayout.addView(textView10);
        nVar.setContentView(frameLayout);
        button.setOnClickListener(new View.OnClickListener() { // from class: m0.s
            @Override // android.view.View.OnClickListener
            public final void onClick(View view4) {
                final String str6 = str3;
                final String str7 = str;
                if (androidx.lifecycle.i.A) {
                    return;
                }
                androidx.lifecycle.i.A = true;
                final Button button2 = button;
                button2.setEnabled(false);
                button2.setText("Preparing download...");
                final ProgressBar progressBar2 = progressBar;
                progressBar2.setVisibility(0);
                final TextView textView11 = textView7;
                textView11.setVisibility(0);
                textView11.setText("Starting download...");
                int i25 = Build.VERSION.SDK_INT;
                final Activity activity = nVar;
                if (i25 >= 26 && !activity.getPackageManager().canRequestPackageInstalls()) {
                    d.j jVar = new d.j(activity);
                    Object obj = jVar.f705b;
                    ((d.f) obj).f631d = "Allow Installation";
                    d.f fVar = (d.f) obj;
                    fVar.f633f = "SnapPay needs permission to install updates.\n\nTap 'Open Settings' → enable 'Install unknown apps' for SnapPay → come back and tap Update Now again.";
                    fVar.f636i = false;
                    DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: m0.t
                        @Override // android.content.DialogInterface.OnClickListener
                        public final void onClick(DialogInterface dialogInterface, int i26) {
                            StringBuilder sb = new StringBuilder("package:");
                            Activity activity2 = activity;
                            sb.append(activity2.getPackageName());
                            Intent intent = new Intent("android.settings.MANAGE_UNKNOWN_APP_SOURCES", Uri.parse(sb.toString()));
                            intent.addFlags(268435456);
                            activity2.startActivity(intent);
                            Button button3 = button2;
                            button3.setEnabled(true);
                            button3.setText("↓   UPDATE NOW");
                            progressBar2.setVisibility(8);
                            textView11.setText("Grant permission then tap Update Now");
                        }
                    };
                    fVar.f634g = "Open Settings";
                    fVar.f635h = onClickListener;
                    jVar.a().show();
                    return;
                }
                try {
                    final File file = new File(activity.getCacheDir(), "SnapPay-v" + str7 + ".apk");
                    if (file.exists()) {
                        file.delete();
                    }
                    new Thread(new Runnable() { // from class: m0.u
                        @Override // java.lang.Runnable
                        public final void run() {
                            boolean z2;
                            String str8 = str6;
                            final File file2 = file;
                            final Activity activity2 = activity;
                            final ProgressBar progressBar3 = progressBar2;
                            final TextView textView12 = textView11;
                            final Button button3 = button2;
                            final String str9 = str7;
                            try {
                                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str8).openConnection();
                                httpURLConnection.setConnectTimeout(15000);
                                httpURLConnection.setReadTimeout(60000);
                                int contentLength = httpURLConnection.getContentLength();
                                InputStream inputStream = httpURLConnection.getInputStream();
                                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                                byte[] bArr = new byte[8192];
                                long j2 = 0;
                                while (true) {
                                    int i26 = inputStream.read(bArr);
                                    if (i26 != -1) {
                                        fileOutputStream.write(bArr, 0, i26);
                                        final long j3 = j2 + ((long) i26);
                                        if (contentLength > 0) {
                                            final long j4 = contentLength;
                                            final int i27 = (int) ((100 * j3) / j4);
                                            activity2.runOnUiThread(new Runnable() { // from class: m0.v
                                                @Override // java.lang.Runnable
                                                public final void run() {
                                                    ProgressBar progressBar4 = progressBar3;
                                                    int i28 = i27;
                                                    progressBar4.setProgress(i28);
                                                    textView12.setText("Downloading... " + i28 + "% (" + ((j3 / 1024) / 1024) + "/" + ((j4 / 1024) / 1024) + " MB)");
                                                }
                                            });
                                        }
                                        j2 = j3;
                                        bArr = bArr;
                                    } else {
                                        fileOutputStream.close();
                                        inputStream.close();
                                        z2 = false;
                                        try {
                                            activity2.runOnUiThread(new Runnable(textView12, activity2, file2, button3, progressBar3, str9) { // from class: m0.w

                                                /* JADX INFO: renamed from: a, reason: collision with root package name */
                                                public final /* synthetic */ TextView f1715a;

                                                /* JADX INFO: renamed from: b, reason: collision with root package name */
                                                public final /* synthetic */ Activity f1716b;

                                                /* JADX INFO: renamed from: c, reason: collision with root package name */
                                                public final /* synthetic */ File f1717c;

                                                /* JADX INFO: renamed from: d, reason: collision with root package name */
                                                public final /* synthetic */ Button f1718d;

                                                /* JADX INFO: renamed from: e, reason: collision with root package name */
                                                public final /* synthetic */ ProgressBar f1719e;

                                                /* JADX WARN: Code duplicated, block: B:19:0x0051  */
                                                /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
                                                    jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:18:0x0050
                                                    	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1478)
                                                    	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
                                                    	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
                                                    	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
                                                    */
                                                @Override // java.lang.Runnable
                                                public final void run() {
                                                    /*
                                                        r7 = this;
                                                        android.app.Activity r0 = r7.f1716b
                                                        java.io.File r1 = r7.f1717c
                                                        android.widget.TextView r2 = r7.f1715a
                                                        java.lang.String r3 = "Verifying update signature..."
                                                        r2.setText(r3)
                                                        r3 = 0
                                                        int r4 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Exception -> L50
                                                        r5 = 28
                                                        if (r4 < r5) goto L36
                                                        android.content.pm.PackageManager r4 = r0.getPackageManager()     // Catch: java.lang.Exception -> L50
                                                        java.lang.String r5 = r1.getAbsolutePath()     // Catch: java.lang.Exception -> L50
                                                        r6 = 134217728(0x8000000, float:3.85186E-34)
                                                        android.content.pm.PackageInfo r4 = r4.getPackageArchiveInfo(r5, r6)     // Catch: java.lang.Exception -> L50
                                                        if (r4 == 0) goto L51
                                                        android.content.pm.SigningInfo r5 = a0.h.d(r4)     // Catch: java.lang.Exception -> L50
                                                        if (r5 != 0) goto L29
                                                        goto L51
                                                    L29:
                                                        android.content.pm.SigningInfo r4 = a0.h.d(r4)     // Catch: java.lang.Exception -> L50
                                                        android.content.pm.Signature[] r4 = a0.h.w(r4)     // Catch: java.lang.Exception -> L50
                                                        boolean r4 = androidx.lifecycle.i.i(r4)     // Catch: java.lang.Exception -> L50
                                                        goto L52
                                                    L36:
                                                        android.content.pm.PackageManager r4 = r0.getPackageManager()     // Catch: java.lang.Exception -> L50
                                                        java.lang.String r5 = r1.getAbsolutePath()     // Catch: java.lang.Exception -> L50
                                                        r6 = 64
                                                        android.content.pm.PackageInfo r4 = r4.getPackageArchiveInfo(r5, r6)     // Catch: java.lang.Exception -> L50
                                                        if (r4 == 0) goto L51
                                                        android.content.pm.Signature[] r4 = r4.signatures     // Catch: java.lang.Exception -> L50
                                                        if (r4 != 0) goto L4b
                                                        goto L51
                                                    L4b:
                                                        boolean r4 = androidx.lifecycle.i.i(r4)     // Catch: java.lang.Exception -> L50
                                                        goto L52
                                                    L50:
                                                    L51:
                                                        r4 = 0
                                                    L52:
                                                        android.widget.ProgressBar r5 = r7.f1719e
                                                        if (r4 != 0) goto L71
                                                        r1.delete()
                                                        androidx.lifecycle.i.A = r3
                                                        java.lang.String r0 = "⚠ Update signature invalid. Aborting for safety."
                                                        r2.setText(r0)
                                                        android.widget.Button r0 = r7.f1718d
                                                        r1 = 1
                                                        r0.setEnabled(r1)
                                                        java.lang.String r1 = "↓   UPDATE NOW"
                                                        r0.setText(r1)
                                                        r0 = 8
                                                        r5.setVisibility(r0)
                                                        goto La9
                                                    L71:
                                                        r3 = 100
                                                        r5.setProgress(r3)
                                                        java.lang.String r3 = "Installing..."
                                                        r2.setText(r3)
                                                        java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> La9
                                                        r2.<init>()     // Catch: java.lang.Exception -> La9
                                                        java.lang.String r3 = r0.getPackageName()     // Catch: java.lang.Exception -> La9
                                                        r2.append(r3)     // Catch: java.lang.Exception -> La9
                                                        java.lang.String r3 = ".provider"
                                                        r2.append(r3)     // Catch: java.lang.Exception -> La9
                                                        java.lang.String r2 = r2.toString()     // Catch: java.lang.Exception -> La9
                                                        android.net.Uri r1 = androidx.core.content.FileProvider.b(r0, r2, r1)     // Catch: java.lang.Exception -> La9
                                                        android.content.Intent r2 = new android.content.Intent     // Catch: java.lang.Exception -> La9
                                                        java.lang.String r3 = "android.intent.action.VIEW"
                                                        r2.<init>(r3)     // Catch: java.lang.Exception -> La9
                                                        java.lang.String r3 = "application/vnd.android.package-archive"
                                                        r2.setDataAndType(r1, r3)     // Catch: java.lang.Exception -> La9
                                                        r1 = 268435457(0x10000001, float:2.5243552E-29)
                                                        r2.addFlags(r1)     // Catch: java.lang.Exception -> La9
                                                        r0.startActivity(r2)     // Catch: java.lang.Exception -> La9
                                                    La9:
                                                        return
                                                    */
                                                    throw new UnsupportedOperationException("Method not decompiled: m0.w.run():void");
                                                }
                                            });
                                            return;
                                        } catch (Exception unused2) {
                                        }
                                    }
                                    file2.delete();
                                    androidx.lifecycle.i.A = z2;
                                    activity2.runOnUiThread(new j(textView12, button3, progressBar3, 1));
                                    return;
                                }
                            } catch (Exception unused3) {
                                z2 = false;
                            }
                        }
                    }).start();
                } catch (Exception e2) {
                    androidx.lifecycle.i.A = false;
                    textView11.setText("Error: " + e2.getMessage());
                    button2.setEnabled(true);
                    button2.setText("↓   UPDATE NOW");
                }
            }
        });
    }

    public static void j(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static void j0(XmlResourceParser xmlResourceParser) throws XmlPullParserException, IOException {
        int i2 = 1;
        while (i2 > 0) {
            int next = xmlResourceParser.next();
            if (next == 2) {
                i2++;
            } else if (next == 3) {
                i2--;
            }
        }
    }

    public static float[] k(float[] fArr, int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException();
        }
        int length = fArr.length;
        if (length < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i3 = i2 - 0;
        int iMin = Math.min(i3, length - 0);
        float[] fArr2 = new float[i3];
        System.arraycopy(fArr, 0, fArr2, 0, iMin);
        return fArr2;
    }

    public static Drawable k0(Drawable drawable) {
        return (Build.VERSION.SDK_INT < 23 && !(drawable instanceof s.a)) ? new s.d(drawable) : drawable;
    }

    public static boolean l(File file, Resources resources, int i2) throws Throwable {
        InputStream inputStreamOpenRawResource;
        try {
            inputStreamOpenRawResource = resources.openRawResource(i2);
            try {
                boolean zM = m(file, inputStreamOpenRawResource);
                j(inputStreamOpenRawResource);
                return zM;
            } catch (Throwable th) {
                th = th;
                j(inputStreamOpenRawResource);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStreamOpenRawResource = null;
        }
    }

    public static ActionMode.Callback l0(ActionMode.Callback callback, TextView textView) {
        int i2 = Build.VERSION.SDK_INT;
        return (i2 < 26 || i2 > 27 || (callback instanceof a0.j)) ? callback : new a0.j(callback, textView);
    }

    public static boolean m(File file, InputStream inputStream) throws Throwable {
        FileOutputStream fileOutputStream;
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(file, false);
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int i2 = inputStream.read(bArr);
                        if (i2 == -1) {
                            j(fileOutputStream);
                            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskWrites);
                            return true;
                        }
                        fileOutputStream.write(bArr, 0, i2);
                    }
                } catch (IOException e2) {
                    e = e2;
                    fileOutputStream2 = fileOutputStream;
                    Log.e("TypefaceCompatUtil", "Error copying resource contents to temp file: " + e.getMessage());
                    j(fileOutputStream2);
                    StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskWrites);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    j(fileOutputStream);
                    StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskWrites);
                    throw th;
                }
            } catch (IOException e3) {
                e = e3;
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = fileOutputStream2;
        }
    }

    /* JADX WARN: Code duplicated, block: B:163:0x030a  */
    public static Animator n(Context context, Resources resources, Resources.Theme theme, XmlResourceParser xmlResourceParser, AttributeSet attributeSet, AnimatorSet animatorSet, int i2) throws XmlPullParserException, IOException {
        PropertyValuesHolder[] propertyValuesHolderArr;
        AttributeSet attributeSet2;
        String str;
        PropertyValuesHolder propertyValuesHolderD;
        int size;
        int i3;
        float f2;
        Keyframe keyframeOfFloat;
        Animator animatorM;
        Resources resources2 = resources;
        Resources.Theme theme2 = theme;
        int depth = xmlResourceParser.getDepth();
        Animator animator = null;
        ArrayList arrayList = null;
        while (true) {
            int next = xmlResourceParser.next();
            int i4 = 3;
            boolean z2 = false;
            if (next == 3 && xmlResourceParser.getDepth() <= depth) {
                break;
            }
            int i5 = 1;
            if (next == 1) {
                break;
            }
            int i6 = 2;
            if (next == 2) {
                String name = xmlResourceParser.getName();
                if (name.equals("objectAnimator")) {
                    ObjectAnimator objectAnimator = new ObjectAnimator();
                    M(context, resources, theme, attributeSet, objectAnimator, xmlResourceParser);
                    animatorM = objectAnimator;
                } else {
                    if (name.equals("animator")) {
                        animatorM = M(context, resources, theme, attributeSet, null, xmlResourceParser);
                    } else if (name.equals("set")) {
                        AnimatorSet animatorSet2 = new AnimatorSet();
                        TypedArray typedArrayO = O(resources2, theme2, attributeSet, f448t);
                        n(context, resources, theme, xmlResourceParser, attributeSet, animatorSet2, B(typedArrayO, xmlResourceParser, "ordering", 0, 0));
                        typedArrayO.recycle();
                        animator = animatorSet2;
                    } else {
                        String str2 = "propertyValuesHolder";
                        if (!name.equals("propertyValuesHolder")) {
                            throw new RuntimeException("Unknown animator name: " + xmlResourceParser.getName());
                        }
                        AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xmlResourceParser);
                        ArrayList arrayList2 = null;
                        while (true) {
                            int eventType = xmlResourceParser.getEventType();
                            if (eventType == i4 || eventType == i5) {
                                break;
                            }
                            if (eventType != i6) {
                                xmlResourceParser.next();
                            } else {
                                if (xmlResourceParser.getName().equals(str2)) {
                                    TypedArray typedArrayO2 = O(resources2, theme2, attributeSetAsAttributeSet, f449u);
                                    String strC = C(typedArrayO2, xmlResourceParser, "propertyName", i4);
                                    int iB = B(typedArrayO2, xmlResourceParser, "valueType", i6, 4);
                                    int i7 = iB;
                                    ArrayList arrayList3 = null;
                                    while (true) {
                                        int next2 = xmlResourceParser.next();
                                        attributeSet2 = attributeSetAsAttributeSet;
                                        if (next2 == i4 || next2 == 1) {
                                            break;
                                        }
                                        if (xmlResourceParser.getName().equals("keyframe")) {
                                            int[] iArr = f450v;
                                            if (i7 == 4) {
                                                TypedArray typedArrayO3 = O(resources2, theme2, Xml.asAttributeSet(xmlResourceParser), iArr);
                                                TypedValue typedValuePeekValue = !I(xmlResourceParser, "value") ? null : typedArrayO3.peekValue(0);
                                                i7 = ((typedValuePeekValue != null) && K(typedValuePeekValue.type)) ? 3 : 0;
                                                typedArrayO3.recycle();
                                            }
                                            TypedArray typedArrayO4 = O(resources2, theme2, Xml.asAttributeSet(xmlResourceParser), iArr);
                                            float fA = A(typedArrayO4, xmlResourceParser, "fraction", 3, -1.0f);
                                            TypedValue typedValuePeekValue2 = !I(xmlResourceParser, "value") ? null : typedArrayO4.peekValue(0);
                                            boolean z3 = typedValuePeekValue2 != null;
                                            int i8 = i7 == 4 ? (z3 && K(typedValuePeekValue2.type)) ? 3 : 0 : i7;
                                            if (!z3) {
                                                keyframeOfFloat = i8 == 0 ? Keyframe.ofFloat(fA) : Keyframe.ofInt(fA);
                                            } else if (i8 != 0) {
                                                keyframeOfFloat = (i8 == 1 || i8 == 3) ? Keyframe.ofInt(fA, B(typedArrayO4, xmlResourceParser, "value", 0, 0)) : null;
                                            } else {
                                                keyframeOfFloat = Keyframe.ofFloat(fA, A(typedArrayO4, xmlResourceParser, "value", 0, 0.0f));
                                            }
                                            int resourceId = !I(xmlResourceParser, "interpolator") ? 0 : typedArrayO4.getResourceId(1, 0);
                                            if (resourceId > 0) {
                                                keyframeOfFloat.setInterpolator(AnimationUtils.loadInterpolator(context, resourceId));
                                            }
                                            typedArrayO4.recycle();
                                            ArrayList arrayList4 = arrayList3;
                                            if (keyframeOfFloat != null) {
                                                if (arrayList4 == null) {
                                                    arrayList4 = new ArrayList();
                                                }
                                                arrayList4.add(keyframeOfFloat);
                                                arrayList3 = arrayList4;
                                            }
                                            xmlResourceParser.next();
                                        }
                                        resources2 = resources;
                                        theme2 = theme;
                                        attributeSetAsAttributeSet = attributeSet2;
                                        str2 = str2;
                                        i4 = 3;
                                    }
                                    str = str2;
                                    ArrayList arrayList5 = arrayList3;
                                    if (arrayList5 == null || (size = arrayList5.size()) <= 0) {
                                        i4 = 3;
                                        propertyValuesHolderD = null;
                                    } else {
                                        Keyframe keyframe = (Keyframe) arrayList5.get(0);
                                        Keyframe keyframe2 = (Keyframe) arrayList5.get(size - 1);
                                        float fraction = keyframe2.getFraction();
                                        if (fraction < 1.0f) {
                                            if (fraction < 0.0f) {
                                                keyframe2.setFraction(1.0f);
                                            } else {
                                                arrayList5.add(arrayList5.size(), q(keyframe2, 1.0f));
                                                size++;
                                            }
                                        }
                                        float fraction2 = keyframe.getFraction();
                                        if (fraction2 != 0.0f) {
                                            if (fraction2 < 0.0f) {
                                                keyframe.setFraction(0.0f);
                                            } else {
                                                arrayList5.add(0, q(keyframe, 0.0f));
                                                size++;
                                            }
                                        }
                                        Keyframe[] keyframeArr = new Keyframe[size];
                                        arrayList5.toArray(keyframeArr);
                                        int i9 = 0;
                                        while (i9 < size) {
                                            Keyframe keyframe3 = keyframeArr[i9];
                                            if (keyframe3.getFraction() >= 0.0f) {
                                                i3 = size;
                                            } else {
                                                if (i9 == 0) {
                                                    f2 = 0.0f;
                                                } else {
                                                    int i10 = size - 1;
                                                    if (i9 == i10) {
                                                        f2 = 1.0f;
                                                    } else {
                                                        int i11 = i9 + 1;
                                                        int i12 = i9;
                                                        while (i11 < i10 && keyframeArr[i11].getFraction() < 0.0f) {
                                                            int i13 = i11;
                                                            i11++;
                                                            i12 = i13;
                                                        }
                                                        float fraction3 = (keyframeArr[i12 + 1].getFraction() - keyframeArr[i9 - 1].getFraction()) / ((i12 - i9) + 2);
                                                        int i14 = i9;
                                                        while (true) {
                                                            i3 = size;
                                                            if (i14 <= i12) {
                                                                keyframeArr[i14].setFraction(keyframeArr[i14 - 1].getFraction() + fraction3);
                                                                i14++;
                                                                size = i3;
                                                                i12 = i12;
                                                            }
                                                        }
                                                    }
                                                }
                                                keyframe3.setFraction(f2);
                                                i3 = size;
                                            }
                                            i9++;
                                            size = i3;
                                        }
                                        propertyValuesHolderD = PropertyValuesHolder.ofKeyframe(strC, keyframeArr);
                                        i4 = 3;
                                        if (i7 == 3) {
                                            propertyValuesHolderD.setEvaluator(g0.e.f824a);
                                        }
                                    }
                                    if (propertyValuesHolderD == null) {
                                        propertyValuesHolderD = D(typedArrayO2, iB, 0, 1, strC);
                                    }
                                    if (propertyValuesHolderD != null) {
                                        if (arrayList2 == null) {
                                            arrayList2 = new ArrayList();
                                        }
                                        arrayList2.add(propertyValuesHolderD);
                                    }
                                    typedArrayO2.recycle();
                                } else {
                                    attributeSet2 = attributeSetAsAttributeSet;
                                    str = str2;
                                }
                                xmlResourceParser.next();
                                resources2 = resources;
                                theme2 = theme;
                                attributeSetAsAttributeSet = attributeSet2;
                                str2 = str;
                                i5 = 1;
                                i6 = 2;
                            }
                        }
                        if (arrayList2 != null) {
                            int size2 = arrayList2.size();
                            propertyValuesHolderArr = new PropertyValuesHolder[size2];
                            for (int i15 = 0; i15 < size2; i15++) {
                                propertyValuesHolderArr[i15] = (PropertyValuesHolder) arrayList2.get(i15);
                            }
                        } else {
                            propertyValuesHolderArr = null;
                        }
                        if (propertyValuesHolderArr != null && (animator instanceof ValueAnimator)) {
                            ((ValueAnimator) animator).setValues(propertyValuesHolderArr);
                        }
                        z2 = true;
                        animator = animator;
                    }
                    if (animatorSet != null && !z2) {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(animator);
                    }
                    resources2 = resources;
                    theme2 = theme;
                }
                animator = animatorM;
                if (animatorSet != null) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(animator);
                }
                resources2 = resources;
                theme2 = theme;
            }
        }
        if (animatorSet != null && arrayList != null) {
            Animator[] animatorArr = new Animator[arrayList.size()];
            Iterator it = arrayList.iterator();
            int i16 = 0;
            while (it.hasNext()) {
                animatorArr[i16] = (Animator) it.next();
                i16++;
            }
            if (i2 == 0) {
                animatorSet.playTogether(animatorArr);
            } else {
                animatorSet.playSequentially(animatorArr);
            }
        }
        return animator;
    }

    public static ColorStateList o(Resources resources, XmlResourceParser xmlResourceParser, Resources.Theme theme) throws XmlPullParserException, IOException {
        int next;
        AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xmlResourceParser);
        do {
            next = xmlResourceParser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next == 2) {
            return p(resources, xmlResourceParser, attributeSetAsAttributeSet, theme);
        }
        throw new XmlPullParserException("No start tag found");
    }

    public static ColorStateList p(Resources resources, XmlResourceParser xmlResourceParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        int depth;
        String name = xmlResourceParser.getName();
        if (!name.equals("selector")) {
            throw new XmlPullParserException(xmlResourceParser.getPositionDescription() + ": invalid color state list tag " + name);
        }
        int i2 = 1;
        int depth2 = xmlResourceParser.getDepth() + 1;
        Object[] objArr = new int[20][];
        int[] iArr = new int[20];
        int i3 = 0;
        while (true) {
            int next = xmlResourceParser.next();
            if (next == i2 || ((depth = xmlResourceParser.getDepth()) < depth2 && next == 3)) {
                break;
            }
            if (next == 2 && depth <= depth2 && xmlResourceParser.getName().equals("item")) {
                int[] iArr2 = n.a.f1720a;
                TypedArray typedArrayObtainAttributes = theme == null ? resources.obtainAttributes(attributeSet, iArr2) : theme.obtainStyledAttributes(attributeSet, iArr2, 0, 0);
                int color = typedArrayObtainAttributes.getColor(0, -65281);
                float f2 = 1.0f;
                if (typedArrayObtainAttributes.hasValue(i2)) {
                    f2 = typedArrayObtainAttributes.getFloat(i2, 1.0f);
                } else if (typedArrayObtainAttributes.hasValue(2)) {
                    f2 = typedArrayObtainAttributes.getFloat(2, 1.0f);
                }
                typedArrayObtainAttributes.recycle();
                int attributeCount = attributeSet.getAttributeCount();
                int[] iArr3 = new int[attributeCount];
                int i4 = 0;
                for (int i5 = 0; i5 < attributeCount; i5++) {
                    int attributeNameResource = attributeSet.getAttributeNameResource(i5);
                    if (attributeNameResource != 16843173 && attributeNameResource != 16843551 && attributeNameResource != com.snapay.app.R.attr.alpha) {
                        int i6 = i4 + 1;
                        if (!attributeSet.getAttributeBooleanValue(i5, false)) {
                            attributeNameResource = -attributeNameResource;
                        }
                        iArr3[i4] = attributeNameResource;
                        i4 = i6;
                    }
                }
                int[] iArrTrimStateSet = StateSet.trimStateSet(iArr3, i4);
                int iRound = (Math.round(Color.alpha(color) * f2) << 24) | (16777215 & color);
                int i7 = i3 + 1;
                if (i7 > iArr.length) {
                    int[] iArr4 = new int[i3 <= 4 ? 8 : i3 * 2];
                    System.arraycopy(iArr, 0, iArr4, 0, i3);
                    iArr = iArr4;
                }
                iArr[i3] = iRound;
                if (i7 > objArr.length) {
                    Object[] objArr2 = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), i3 > 4 ? i3 * 2 : 8);
                    System.arraycopy(objArr, 0, objArr2, 0, i3);
                    objArr = objArr2;
                }
                objArr[i3] = iArrTrimStateSet;
                objArr = (int[][]) objArr;
                i3 = i7;
            }
            i2 = 1;
        }
        int[] iArr5 = new int[i3];
        int[][] iArr6 = new int[i3][];
        System.arraycopy(iArr, 0, iArr5, 0, i3);
        System.arraycopy(objArr, 0, iArr6, 0, i3);
        return new ColorStateList(iArr6, iArr5);
    }

    public static Keyframe q(Keyframe keyframe, float f2) {
        if (keyframe.getType() == Float.TYPE) {
            return Keyframe.ofFloat(f2);
        }
        return keyframe.getType() == Integer.TYPE ? Keyframe.ofInt(f2) : Keyframe.ofObject(f2);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0032  */
    /* JADX WARN: Code duplicated, block: B:21:0x0048  */
    /* JADX WARN: Code duplicated, block: B:49:0x0098 A[Catch: NumberFormatException -> 0x00b9, LOOP:3: B:29:0x006d->B:49:0x0098, LOOP_END, TryCatch #0 {NumberFormatException -> 0x00b9, blocks: (B:26:0x005a, B:29:0x006d, B:31:0x0073, B:36:0x0081, B:49:0x0098, B:51:0x009d, B:54:0x00ad, B:56:0x00b1), top: B:71:0x005a }] */
    /* JADX WARN: Code duplicated, block: B:51:0x009d A[Catch: NumberFormatException -> 0x00b9, TryCatch #0 {NumberFormatException -> 0x00b9, blocks: (B:26:0x005a, B:29:0x006d, B:31:0x0073, B:36:0x0081, B:49:0x0098, B:51:0x009d, B:54:0x00ad, B:56:0x00b1), top: B:71:0x005a }] */
    /* JADX WARN: Code duplicated, block: B:53:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:54:0x00ad A[Catch: NumberFormatException -> 0x00b9, TryCatch #0 {NumberFormatException -> 0x00b9, blocks: (B:26:0x005a, B:29:0x006d, B:31:0x0073, B:36:0x0081, B:49:0x0098, B:51:0x009d, B:54:0x00ad, B:56:0x00b1), top: B:71:0x005a }] */
    /* JADX WARN: Code duplicated, block: B:61:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:75:0x00d6 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:84:0x0097 A[SYNTHETIC] */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x008b, code lost:
    
        if (r13 == false) goto L42;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static r.c[] r(String str) {
        String strTrim;
        float[] fArrK;
        int i2;
        if (str == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int i3 = 0;
        int i4 = 1;
        int i5 = 0;
        while (i4 < str.length()) {
            while (i4 < str.length()) {
                char cCharAt = str.charAt(i4);
                if ((cCharAt - 'Z') * (cCharAt - 'A') > 0) {
                    if ((cCharAt - 'z') * (cCharAt - 'a') > 0) {
                        continue;
                    } else if (cCharAt != 'e' && cCharAt != 'E') {
                        strTrim = str.substring(i5, i4).trim();
                        if (strTrim.length() <= 0) {
                            if (strTrim.charAt(i3) != 'z' || strTrim.charAt(i3) == 'Z') {
                                fArrK = new float[i3];
                            } else {
                                try {
                                    float[] fArr = new float[strTrim.length()];
                                    int length = strTrim.length();
                                    int i6 = 0;
                                    for (int i7 = 1; i7 < length; i7 = i2) {
                                        boolean z2 = false;
                                        boolean z3 = false;
                                        boolean z4 = false;
                                        boolean z5 = false;
                                        i2 = i7;
                                        while (i2 < strTrim.length()) {
                                            char cCharAt2 = strTrim.charAt(i2);
                                            if (cCharAt2 != ' ') {
                                                if (cCharAt2 == 'E' || cCharAt2 == 'e') {
                                                    z5 = true;
                                                } else {
                                                    switch (cCharAt2) {
                                                        case '-':
                                                            if (i2 != i7) {
                                                            }
                                                            break;
                                                        case '.':
                                                            if (z4) {
                                                                z3 = true;
                                                            } else {
                                                                z4 = true;
                                                            }
                                                            break;
                                                    }
                                                    z5 = false;
                                                }
                                                if (z2) {
                                                    if (i7 < i2) {
                                                        fArr[i6] = Float.parseFloat(strTrim.substring(i7, i2));
                                                        i6++;
                                                    }
                                                    if (!z3) {
                                                        i2++;
                                                    }
                                                } else {
                                                    i2++;
                                                }
                                            }
                                            z2 = true;
                                            z5 = false;
                                            if (z2) {
                                                if (i7 < i2) {
                                                    fArr[i6] = Float.parseFloat(strTrim.substring(i7, i2));
                                                    i6++;
                                                }
                                                if (!z3) {
                                                    i2++;
                                                }
                                            } else {
                                                i2++;
                                            }
                                        }
                                        if (i7 < i2) {
                                            fArr[i6] = Float.parseFloat(strTrim.substring(i7, i2));
                                            i6++;
                                        }
                                        if (!z3) {
                                            i2++;
                                        }
                                    }
                                    fArrK = k(fArr, i6);
                                    i3 = 0;
                                } catch (NumberFormatException e2) {
                                    throw new RuntimeException(androidx.activity.c.a("error in parsing \"", strTrim, "\""), e2);
                                }
                            }
                            arrayList.add(new r.c(strTrim.charAt(i3), fArrK));
                        }
                        i5 = i4;
                        i4++;
                        i3 = 0;
                    }
                } else if (cCharAt != 'e') {
                    continue;
                }
                i4++;
            }
            strTrim = str.substring(i5, i4).trim();
            if (strTrim.length() <= 0) {
                if (strTrim.charAt(i3) != 'z') {
                    fArrK = new float[i3];
                } else {
                    fArrK = new float[i3];
                }
                arrayList.add(new r.c(strTrim.charAt(i3), fArrK));
            }
            i5 = i4;
            i4++;
            i3 = 0;
        }
        if (i4 - i5 == 1 && i5 < str.length()) {
            arrayList.add(new r.c(str.charAt(i5), new float[0]));
        }
        return (r.c[]) arrayList.toArray(new r.c[arrayList.size()]);
    }

    public static Path s(String str) {
        Path path = new Path();
        r.c[] cVarArrR = r(str);
        if (cVarArrR == null) {
            return null;
        }
        try {
            r.c.b(cVarArrR, path);
            return path;
        } catch (RuntimeException e2) {
            throw new RuntimeException("Error in parsing " + str, e2);
        }
    }

    public static r.c[] t(r.c[] cVarArr) {
        if (cVarArr == null) {
            return null;
        }
        r.c[] cVarArr2 = new r.c[cVarArr.length];
        for (int i2 = 0; i2 < cVarArr.length; i2++) {
            cVarArr2[i2] = new r.c(cVarArr[i2]);
        }
        return cVarArr2;
    }

    public static boolean u(View view, KeyEvent keyEvent) {
        WeakReference weakReference;
        int iIndexOfKey;
        WeakHashMap weakHashMap = x.u.f2012a;
        if (Build.VERSION.SDK_INT < 28) {
            ArrayList arrayList = x.t.f2008d;
            x.t tVar = (x.t) view.getTag(com.snapay.app.R.id.tag_unhandled_key_event_manager);
            if (tVar == null) {
                tVar = new x.t();
                view.setTag(com.snapay.app.R.id.tag_unhandled_key_event_manager, tVar);
            }
            WeakReference weakReference2 = tVar.f2011c;
            if (weakReference2 == null || weakReference2.get() != keyEvent) {
                tVar.f2011c = new WeakReference(keyEvent);
                if (tVar.f2010b == null) {
                    tVar.f2010b = new SparseArray();
                }
                SparseArray sparseArray = tVar.f2010b;
                if (keyEvent.getAction() != 1 || (iIndexOfKey = sparseArray.indexOfKey(keyEvent.getKeyCode())) < 0) {
                    weakReference = null;
                } else {
                    weakReference = (WeakReference) sparseArray.valueAt(iIndexOfKey);
                    sparseArray.removeAt(iIndexOfKey);
                }
                if (weakReference == null) {
                    weakReference = (WeakReference) sparseArray.get(keyEvent.getKeyCode());
                }
                if (weakReference != null) {
                    View view2 = (View) weakReference.get();
                    if (view2 == null || !view2.isAttachedToWindow()) {
                        return true;
                    }
                    x.t.b(view2);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean v(x.e eVar, View view, Window.Callback callback, KeyEvent keyEvent) {
        DialogInterface.OnKeyListener onKeyListener;
        boolean zBooleanValue = false;
        if (eVar == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 28) {
            return eVar.d(keyEvent);
        }
        if (callback instanceof Activity) {
            Activity activity = (Activity) callback;
            activity.onUserInteraction();
            Window window = activity.getWindow();
            if (window.hasFeature(8)) {
                ActionBar actionBar = activity.getActionBar();
                if (keyEvent.getKeyCode() == 82 && actionBar != null) {
                    if (!K) {
                        try {
                            L = actionBar.getClass().getMethod("onMenuKeyEvent", KeyEvent.class);
                        } catch (NoSuchMethodException unused) {
                        }
                        K = true;
                    }
                    Method method = L;
                    if (method != null) {
                        try {
                            zBooleanValue = ((Boolean) method.invoke(actionBar, keyEvent)).booleanValue();
                        } catch (IllegalAccessException | InvocationTargetException unused2) {
                        }
                    }
                    if (zBooleanValue) {
                        return true;
                    }
                }
            }
            if (window.superDispatchKeyEvent(keyEvent)) {
                return true;
            }
            View decorView = window.getDecorView();
            if (x.u.b(decorView, keyEvent)) {
                return true;
            }
            return keyEvent.dispatch(activity, decorView != null ? decorView.getKeyDispatcherState() : null, activity);
        }
        if (!(callback instanceof Dialog)) {
            return (view != null && x.u.b(view, keyEvent)) || eVar.d(keyEvent);
        }
        Dialog dialog = (Dialog) callback;
        if (!M) {
            try {
                Field declaredField = Dialog.class.getDeclaredField("mOnKeyListener");
                N = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused3) {
            }
            M = true;
        }
        Field field = N;
        if (field != null) {
            try {
                onKeyListener = (DialogInterface.OnKeyListener) field.get(dialog);
            } catch (IllegalAccessException unused4) {
                onKeyListener = null;
            }
        } else {
            onKeyListener = null;
        }
        if (onKeyListener != null && onKeyListener.onKey(dialog, keyEvent.getKeyCode(), keyEvent)) {
            return true;
        }
        Window window2 = dialog.getWindow();
        if (window2.superDispatchKeyEvent(keyEvent)) {
            return true;
        }
        View decorView2 = window2.getDecorView();
        if (x.u.b(decorView2, keyEvent)) {
            return true;
        }
        return keyEvent.dispatch(dialog, decorView2 != null ? decorView2.getKeyDispatcherState() : null, dialog);
    }

    public static void w(Object obj) {
        LongSparseArray longSparseArray;
        if (!f432d) {
            try {
                f431c = Class.forName("android.content.res.ThemedResourceCache");
            } catch (ClassNotFoundException e2) {
                Log.e("ResourcesFlusher", "Could not find ThemedResourceCache class", e2);
            }
            f432d = true;
        }
        Class cls = f431c;
        if (cls == null) {
            return;
        }
        if (!f434f) {
            try {
                Field declaredField = cls.getDeclaredField("mUnthemedEntries");
                f433e = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException e3) {
                Log.e("ResourcesFlusher", "Could not retrieve ThemedResourceCache#mUnthemedEntries field", e3);
            }
            f434f = true;
        }
        Field field = f433e;
        if (field == null) {
            return;
        }
        try {
            longSparseArray = (LongSparseArray) field.get(obj);
        } catch (IllegalAccessException e4) {
            Log.e("ResourcesFlusher", "Could not retrieve value from ThemedResourceCache#mUnthemedEntries", e4);
            longSparseArray = null;
        }
        if (longSparseArray != null) {
            longSparseArray.clear();
        }
    }

    /* JADX WARN: Code duplicated, block: B:66:0x018e  */
    public static d.j x(Context context, j.s sVar) throws Throwable {
        Cursor cursor;
        ArrayList arrayList;
        boolean z2;
        PackageManager packageManager = context.getPackageManager();
        Resources resources = context.getResources();
        String str = (String) sVar.f1402c;
        ProviderInfo providerInfoResolveContentProvider = packageManager.resolveContentProvider(str, 0);
        if (providerInfoResolveContentProvider == null) {
            throw new PackageManager.NameNotFoundException("No package found for authority: " + str);
        }
        String str2 = providerInfoResolveContentProvider.packageName;
        String str3 = (String) sVar.f1403d;
        if (!str2.equals(str3)) {
            throw new PackageManager.NameNotFoundException("Found content provider " + str + ", but package was not " + str3);
        }
        Signature[] signatureArr = packageManager.getPackageInfo(providerInfoResolveContentProvider.packageName, 64).signatures;
        ArrayList arrayList2 = new ArrayList();
        for (Signature signature : signatureArr) {
            arrayList2.add(signature.toByteArray());
        }
        u.c cVar = B;
        Collections.sort(arrayList2, cVar);
        List listW = (List) sVar.f1405f;
        if (listW == null) {
            listW = W(resources, sVar.f1401b);
        }
        int i2 = 0;
        while (true) {
            if (i2 >= listW.size()) {
                providerInfoResolveContentProvider = null;
                break;
            }
            ArrayList arrayList3 = new ArrayList((Collection) listW.get(i2));
            Collections.sort(arrayList3, cVar);
            if (arrayList2.size() != arrayList3.size()) {
                z2 = false;
                break;
            }
            int i3 = 0;
            while (true) {
                if (i3 >= arrayList2.size()) {
                    z2 = true;
                    break;
                }
                if (!Arrays.equals((byte[]) arrayList2.get(i3), (byte[]) arrayList3.get(i3))) {
                    z2 = false;
                    break;
                }
                i3++;
            }
            if (z2) {
                break;
            }
            i2++;
        }
        if (providerInfoResolveContentProvider == null) {
            return new d.j(1, null);
        }
        String str4 = providerInfoResolveContentProvider.authority;
        ArrayList arrayList4 = new ArrayList();
        Uri uriBuild = new Uri.Builder().scheme("content").authority(str4).build();
        Uri uriBuild2 = new Uri.Builder().scheme("content").authority(str4).appendPath("file").build();
        try {
            Cursor cursorQuery = context.getContentResolver().query(uriBuild, new String[]{"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"}, "query = ?", new String[]{(String) sVar.f1404e}, null, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.getCount() > 0) {
                        int columnIndex = cursorQuery.getColumnIndex("result_code");
                        arrayList = new ArrayList();
                        int columnIndex2 = cursorQuery.getColumnIndex("_id");
                        int columnIndex3 = cursorQuery.getColumnIndex("file_id");
                        int columnIndex4 = cursorQuery.getColumnIndex("font_ttc_index");
                        int columnIndex5 = cursorQuery.getColumnIndex("font_weight");
                        int columnIndex6 = cursorQuery.getColumnIndex("font_italic");
                        while (cursorQuery.moveToNext()) {
                            arrayList.add(new u.h(columnIndex3 == -1 ? ContentUris.withAppendedId(uriBuild, cursorQuery.getLong(columnIndex2)) : ContentUris.withAppendedId(uriBuild2, cursorQuery.getLong(columnIndex3)), columnIndex4 != -1 ? cursorQuery.getInt(columnIndex4) : 0, columnIndex5 != -1 ? cursorQuery.getInt(columnIndex5) : 400, columnIndex6 != -1 && cursorQuery.getInt(columnIndex6) == 1, columnIndex != -1 ? cursorQuery.getInt(columnIndex) : 0));
                        }
                    } else {
                        arrayList = arrayList4;
                    }
                } catch (Throwable th) {
                    th = th;
                    cursor = cursorQuery;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } else {
                arrayList = arrayList4;
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return new d.j(0, (u.h[]) arrayList.toArray(new u.h[0]));
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
        }
    }

    public static int y(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 23) {
            return drawable.getLayoutDirection();
        }
        if (!f440l) {
            try {
                Method declaredMethod = Drawable.class.getDeclaredMethod("getLayoutDirection", new Class[0]);
                f439k = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (NoSuchMethodException e2) {
                Log.i("DrawableCompat", "Failed to retrieve getLayoutDirection() method", e2);
            }
            f440l = true;
        }
        Method method = f439k;
        if (method != null) {
            try {
                return ((Integer) method.invoke(drawable, new Object[0])).intValue();
            } catch (Exception e3) {
                Log.i("DrawableCompat", "Failed to invoke getLayoutDirection() via reflection", e3);
                f439k = null;
            }
        }
        return 0;
    }

    public static q.a z(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme, String str, int i2) {
        q.a aVarA;
        if (I(xmlPullParser, str)) {
            TypedValue typedValue = new TypedValue();
            typedArray.getValue(i2, typedValue);
            int i3 = typedValue.type;
            if (i3 >= 28 && i3 <= 31) {
                return new q.a(null, null, typedValue.data);
            }
            try {
                aVarA = q.a.a(typedArray.getResources(), typedArray.getResourceId(i2, 0), theme);
            } catch (Exception e2) {
                Log.e("ComplexColorCompat", "Failed to inflate ComplexColor.", e2);
                aVarA = null;
            }
            if (aVarA != null) {
                return aVarA;
            }
        }
        return new q.a(null, null, 0);
    }

    public abstract void X(k kVar);

    public abstract void b(k kVar);
}
