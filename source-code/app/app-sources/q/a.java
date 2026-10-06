package q;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.LinearGradient;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.Xml;
import androidx.lifecycle.i;
import j.s0;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Shader f1837a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ColorStateList f1838b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1839c;

    public a(Shader shader, ColorStateList colorStateList, int i2) {
        this.f1837a = shader;
        this.f1838b = colorStateList;
        this.f1839c = i2;
    }

    public static a a(Resources resources, int i2, Resources.Theme theme) {
        int next;
        float f2;
        float f3;
        Shader radialGradient;
        Shader.TileMode tileMode;
        Shader.TileMode tileMode2;
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
        name.getClass();
        if (!name.equals("gradient")) {
            if (name.equals("selector")) {
                ColorStateList colorStateListP = i.p(resources, xml, attributeSetAsAttributeSet, theme);
                return new a(null, colorStateListP, colorStateListP.getDefaultColor());
            }
            throw new XmlPullParserException(xml.getPositionDescription() + ": unsupported complex color tag " + name);
        }
        String name2 = xml.getName();
        if (!name2.equals("gradient")) {
            throw new XmlPullParserException(xml.getPositionDescription() + ": invalid gradient color tag " + name2);
        }
        TypedArray typedArrayO = i.O(resources, theme, attributeSetAsAttributeSet, n.a.f1723d);
        float fA = i.A(typedArrayO, xml, "startX", 8, 0.0f);
        float fA2 = i.A(typedArrayO, xml, "startY", 9, 0.0f);
        float fA3 = i.A(typedArrayO, xml, "endX", 10, 0.0f);
        float fA4 = i.A(typedArrayO, xml, "endY", 11, 0.0f);
        float fA5 = i.A(typedArrayO, xml, "centerX", 3, 0.0f);
        float fA6 = i.A(typedArrayO, xml, "centerY", 4, 0.0f);
        int iB = i.B(typedArrayO, xml, "type", 2, 0);
        int color = !i.I(xml, "startColor") ? 0 : typedArrayO.getColor(0, 0);
        boolean zI = i.I(xml, "centerColor");
        int color2 = !i.I(xml, "centerColor") ? 0 : typedArrayO.getColor(7, 0);
        int color3 = !i.I(xml, "endColor") ? 0 : typedArrayO.getColor(1, 0);
        int iB2 = i.B(typedArrayO, xml, "tileMode", 6, 0);
        float fA7 = i.A(typedArrayO, xml, "gradientRadius", 5, 0.0f);
        typedArrayO.recycle();
        int depth = xml.getDepth() + 1;
        ArrayList arrayList = new ArrayList(20);
        ArrayList arrayList2 = new ArrayList(20);
        while (true) {
            int next2 = xml.next();
            f2 = fA3;
            if (next2 == 1) {
                f3 = fA2;
                break;
            }
            int depth2 = xml.getDepth();
            f3 = fA2;
            if (depth2 < depth && next2 == 3) {
                break;
            }
            if (next2 == 2 && depth2 <= depth && xml.getName().equals("item")) {
                TypedArray typedArrayO2 = i.O(resources, theme, attributeSetAsAttributeSet, n.a.f1724e);
                boolean zHasValue = typedArrayO2.hasValue(0);
                boolean zHasValue2 = typedArrayO2.hasValue(1);
                if (!zHasValue || !zHasValue2) {
                    throw new XmlPullParserException(xml.getPositionDescription() + ": <item> tag requires a 'color' attribute and a 'offset' attribute!");
                }
                int color4 = typedArrayO2.getColor(0, 0);
                float f4 = typedArrayO2.getFloat(1, 0.0f);
                typedArrayO2.recycle();
                arrayList2.add(Integer.valueOf(color4));
                arrayList.add(Float.valueOf(f4));
            }
            fA3 = f2;
            fA2 = f3;
        }
        s0 s0Var = arrayList2.size() > 0 ? new s0(arrayList2, arrayList) : null;
        if (s0Var == null) {
            s0Var = zI ? new s0(color, color2, color3) : new s0(color, color3);
        }
        Object obj = s0Var.f1407a;
        if (iB != 1) {
            if (iB != 2) {
                int[] iArr = (int[]) obj;
                float[] fArr = (float[]) s0Var.f1408b;
                if (iB2 != 1) {
                    tileMode2 = iB2 != 2 ? Shader.TileMode.CLAMP : Shader.TileMode.MIRROR;
                } else {
                    tileMode2 = Shader.TileMode.REPEAT;
                }
                radialGradient = new LinearGradient(fA, f3, f2, fA4, iArr, fArr, tileMode2);
            } else {
                radialGradient = new SweepGradient(fA5, fA6, (int[]) obj, (float[]) s0Var.f1408b);
            }
        } else {
            if (fA7 <= 0.0f) {
                throw new XmlPullParserException("<gradient> tag requires 'gradientRadius' attribute with radial type");
            }
            int[] iArr2 = (int[]) obj;
            float[] fArr2 = (float[]) s0Var.f1408b;
            if (iB2 != 1) {
                tileMode = iB2 != 2 ? Shader.TileMode.CLAMP : Shader.TileMode.MIRROR;
            } else {
                tileMode = Shader.TileMode.REPEAT;
            }
            radialGradient = new RadialGradient(fA5, fA6, fA7, iArr2, fArr2, tileMode);
        }
        return new a(radialGradient, null, 0);
    }

    public final boolean b() {
        ColorStateList colorStateList;
        return this.f1837a == null && (colorStateList = this.f1838b) != null && colorStateList.isStateful();
    }
}
