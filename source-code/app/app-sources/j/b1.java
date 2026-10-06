package j;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;
import com.google.crypto.tink.shaded.protobuf.Reader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class b1 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final RectF f1175l = new RectF();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final ConcurrentHashMap f1176m = new ConcurrentHashMap();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1177a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f1178b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f1179c = -1.0f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f1180d = -1.0f;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f1181e = -1.0f;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int[] f1182f = new int[0];

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1183g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public TextPaint f1184h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final TextView f1185i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Context f1186j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final a1 f1187k;

    static {
        new ConcurrentHashMap();
    }

    public b1(TextView textView) {
        this.f1185i = textView;
        this.f1186j = textView.getContext();
        int i2 = Build.VERSION.SDK_INT;
        this.f1187k = i2 >= 29 ? new z0() : i2 >= 23 ? new y0() : new a1();
    }

    public static int[] b(int[] iArr) {
        int length = iArr.length;
        if (length == 0) {
            return iArr;
        }
        Arrays.sort(iArr);
        ArrayList arrayList = new ArrayList();
        for (int i2 : iArr) {
            if (i2 > 0 && Collections.binarySearch(arrayList, Integer.valueOf(i2)) < 0) {
                arrayList.add(Integer.valueOf(i2));
            }
        }
        if (length == arrayList.size()) {
            return iArr;
        }
        int size = arrayList.size();
        int[] iArr2 = new int[size];
        for (int i3 = 0; i3 < size; i3++) {
            iArr2[i3] = ((Integer) arrayList.get(i3)).intValue();
        }
        return iArr2;
    }

    public static Method d(String str) {
        try {
            ConcurrentHashMap concurrentHashMap = f1176m;
            Method declaredMethod = (Method) concurrentHashMap.get(str);
            if (declaredMethod == null && (declaredMethod = TextView.class.getDeclaredMethod(str, new Class[0])) != null) {
                declaredMethod.setAccessible(true);
                concurrentHashMap.put(str, declaredMethod);
            }
            return declaredMethod;
        } catch (Exception e2) {
            Log.w("ACTVAutoSizeHelper", "Failed to retrieve TextView#" + str + "() method", e2);
            return null;
        }
    }

    public static Object e(Object obj, String str, Object obj2) {
        try {
            return d(str).invoke(obj, new Object[0]);
        } catch (Exception e2) {
            Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#" + str + "() method", e2);
            return obj2;
        }
    }

    public final void a() {
        if (i() && this.f1177a != 0) {
            if (this.f1178b) {
                if (this.f1185i.getMeasuredHeight() <= 0 || this.f1185i.getMeasuredWidth() <= 0) {
                    return;
                }
                int measuredWidth = this.f1187k.b(this.f1185i) ? 1048576 : (this.f1185i.getMeasuredWidth() - this.f1185i.getTotalPaddingLeft()) - this.f1185i.getTotalPaddingRight();
                int height = (this.f1185i.getHeight() - this.f1185i.getCompoundPaddingBottom()) - this.f1185i.getCompoundPaddingTop();
                if (measuredWidth <= 0 || height <= 0) {
                    return;
                }
                RectF rectF = f1175l;
                synchronized (rectF) {
                    rectF.setEmpty();
                    rectF.right = measuredWidth;
                    rectF.bottom = height;
                    float fC = c(rectF);
                    if (fC != this.f1185i.getTextSize()) {
                        f(0, fC);
                    }
                }
            }
            this.f1178b = true;
        }
    }

    public final int c(RectF rectF) {
        byte b2;
        StaticLayout staticLayout;
        CharSequence transformation;
        int length = this.f1182f.length;
        if (length == 0) {
            throw new IllegalStateException("No available text sizes to choose from.");
        }
        int i2 = length - 1;
        int i3 = 1;
        int i4 = 0;
        while (i3 <= i2) {
            int i5 = (i3 + i2) / 2;
            int i6 = this.f1182f[i5];
            TextView textView = this.f1185i;
            CharSequence text = textView.getText();
            TransformationMethod transformationMethod = textView.getTransformationMethod();
            if (transformationMethod != null && (transformation = transformationMethod.getTransformation(text, textView)) != null) {
                text = transformation;
            }
            int i7 = Build.VERSION.SDK_INT;
            int maxLines = textView.getMaxLines();
            TextPaint textPaint = this.f1184h;
            if (textPaint == null) {
                this.f1184h = new TextPaint();
            } else {
                textPaint.reset();
            }
            this.f1184h.set(textView.getPaint());
            this.f1184h.setTextSize(i6);
            Layout.Alignment alignment = (Layout.Alignment) e(textView, "getLayoutAlignment", Layout.Alignment.ALIGN_NORMAL);
            int iRound = Math.round(rectF.right);
            if (i7 >= 23) {
                StaticLayout.Builder builderObtain = StaticLayout.Builder.obtain(text, 0, text.length(), this.f1184h, iRound);
                builderObtain.setAlignment(alignment).setLineSpacing(textView.getLineSpacingExtra(), textView.getLineSpacingMultiplier()).setIncludePad(textView.getIncludeFontPadding()).setBreakStrategy(textView.getBreakStrategy()).setHyphenationFrequency(textView.getHyphenationFrequency()).setMaxLines(maxLines == -1 ? Reader.READ_DONE : maxLines);
                try {
                    this.f1187k.a(builderObtain, textView);
                } catch (ClassCastException unused) {
                    Log.w("ACTVAutoSizeHelper", "Failed to obtain TextDirectionHeuristic, auto size may be incorrect");
                }
                staticLayout = builderObtain.build();
                b2 = -1;
            } else {
                b2 = -1;
                staticLayout = new StaticLayout(text, this.f1184h, iRound, alignment, textView.getLineSpacingMultiplier(), textView.getLineSpacingExtra(), textView.getIncludeFontPadding());
            }
            if ((maxLines == b2 || (staticLayout.getLineCount() <= maxLines && staticLayout.getLineEnd(staticLayout.getLineCount() - 1) == text.length())) && ((float) staticLayout.getHeight()) <= rectF.bottom) {
                int i8 = i5 + 1;
                i4 = i3;
                i3 = i8;
            } else {
                i4 = i5 - 1;
                i2 = i4;
            }
        }
        return this.f1182f[i4];
    }

    public final void f(int i2, float f2) {
        Context context = this.f1186j;
        float fApplyDimension = TypedValue.applyDimension(i2, f2, (context == null ? Resources.getSystem() : context.getResources()).getDisplayMetrics());
        TextView textView = this.f1185i;
        if (fApplyDimension != textView.getPaint().getTextSize()) {
            textView.getPaint().setTextSize(fApplyDimension);
            boolean zIsInLayout = textView.isInLayout();
            if (textView.getLayout() != null) {
                this.f1178b = false;
                try {
                    Method methodD = d("nullLayouts");
                    if (methodD != null) {
                        methodD.invoke(textView, new Object[0]);
                    }
                } catch (Exception e2) {
                    Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#nullLayouts() method", e2);
                }
                if (zIsInLayout) {
                    textView.forceLayout();
                } else {
                    textView.requestLayout();
                }
                textView.invalidate();
            }
        }
    }

    public final boolean g() {
        if (i() && this.f1177a == 1) {
            if (!this.f1183g || this.f1182f.length == 0) {
                int iFloor = ((int) Math.floor((this.f1181e - this.f1180d) / this.f1179c)) + 1;
                int[] iArr = new int[iFloor];
                for (int i2 = 0; i2 < iFloor; i2++) {
                    iArr[i2] = Math.round((i2 * this.f1179c) + this.f1180d);
                }
                this.f1182f = b(iArr);
            }
            this.f1178b = true;
        } else {
            this.f1178b = false;
        }
        return this.f1178b;
    }

    public final boolean h() {
        int[] iArr = this.f1182f;
        int length = iArr.length;
        boolean z2 = length > 0;
        this.f1183g = z2;
        if (z2) {
            this.f1177a = 1;
            this.f1180d = iArr[0];
            this.f1181e = iArr[length - 1];
            this.f1179c = -1.0f;
        }
        return z2;
    }

    public final boolean i() {
        return !(this.f1185i instanceof z);
    }

    public final void j(float f2, float f3, float f4) {
        if (f2 <= 0.0f) {
            throw new IllegalArgumentException("Minimum auto-size text size (" + f2 + "px) is less or equal to (0px)");
        }
        if (f3 <= f2) {
            throw new IllegalArgumentException("Maximum auto-size text size (" + f3 + "px) is less or equal to minimum auto-size text size (" + f2 + "px)");
        }
        if (f4 <= 0.0f) {
            throw new IllegalArgumentException("The auto-size step granularity (" + f4 + "px) is less or equal to (0px)");
        }
        this.f1177a = 1;
        this.f1180d = f2;
        this.f1181e = f3;
        this.f1179c = f4;
        this.f1183g = false;
    }
}
