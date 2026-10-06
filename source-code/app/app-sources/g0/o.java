package g0;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import java.io.IOException;
import java.util.ArrayDeque;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class o extends f {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final PorterDuff.Mode f882k = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public m f883c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public PorterDuffColorFilter f884d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ColorFilter f885e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f886f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f887g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final float[] f888h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Matrix f889i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Rect f890j;

    public o() {
        this.f887g = true;
        this.f888h = new float[9];
        this.f889i = new Matrix();
        this.f890j = new Rect();
        this.f883c = new m();
    }

    public final PorterDuffColorFilter a(ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean canApplyTheme() {
        Drawable drawable = this.f825b;
        if (drawable == null) {
            return false;
        }
        drawable.canApplyTheme();
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:40:0x00c1  */
    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Paint paint;
        Drawable drawable = this.f825b;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        Rect rect = this.f890j;
        copyBounds(rect);
        if (rect.width() <= 0 || rect.height() <= 0) {
            return;
        }
        ColorFilter colorFilter = this.f885e;
        if (colorFilter == null) {
            colorFilter = this.f884d;
        }
        Matrix matrix = this.f889i;
        canvas.getMatrix(matrix);
        float[] fArr = this.f888h;
        matrix.getValues(fArr);
        float fAbs = Math.abs(fArr[0]);
        float fAbs2 = Math.abs(fArr[4]);
        float fAbs3 = Math.abs(fArr[1]);
        float fAbs4 = Math.abs(fArr[3]);
        if (fAbs3 != 0.0f || fAbs4 != 0.0f) {
            fAbs = 1.0f;
            fAbs2 = 1.0f;
        }
        int iWidth = (int) (rect.width() * fAbs);
        int iHeight = (int) (rect.height() * fAbs2);
        int iMin = Math.min(2048, iWidth);
        int iMin2 = Math.min(2048, iHeight);
        if (iMin <= 0 || iMin2 <= 0) {
            return;
        }
        int iSave = canvas.save();
        canvas.translate(rect.left, rect.top);
        if (isAutoMirrored() && androidx.lifecycle.i.y(this) == 1) {
            canvas.translate(rect.width(), 0.0f);
            canvas.scale(-1.0f, 1.0f);
        }
        rect.offsetTo(0, 0);
        m mVar = this.f883c;
        Bitmap bitmap = mVar.f874f;
        if (bitmap == null) {
            mVar.f874f = Bitmap.createBitmap(iMin, iMin2, Bitmap.Config.ARGB_8888);
            mVar.f879k = true;
        } else {
            if (!(iMin == bitmap.getWidth() && iMin2 == mVar.f874f.getHeight())) {
                mVar.f874f = Bitmap.createBitmap(iMin, iMin2, Bitmap.Config.ARGB_8888);
                mVar.f879k = true;
            }
        }
        if (this.f887g) {
            m mVar2 = this.f883c;
            if (!(!mVar2.f879k && mVar2.f875g == mVar2.f871c && mVar2.f876h == mVar2.f872d && mVar2.f878j == mVar2.f873e && mVar2.f877i == mVar2.f870b.getRootAlpha())) {
                m mVar3 = this.f883c;
                mVar3.f874f.eraseColor(0);
                Canvas canvas2 = new Canvas(mVar3.f874f);
                l lVar = mVar3.f870b;
                lVar.a(lVar.f860g, l.f853p, canvas2, iMin, iMin2);
                m mVar4 = this.f883c;
                mVar4.f875g = mVar4.f871c;
                mVar4.f876h = mVar4.f872d;
                mVar4.f877i = mVar4.f870b.getRootAlpha();
                mVar4.f878j = mVar4.f873e;
                mVar4.f879k = false;
            }
        } else {
            m mVar5 = this.f883c;
            mVar5.f874f.eraseColor(0);
            Canvas canvas3 = new Canvas(mVar5.f874f);
            l lVar2 = mVar5.f870b;
            lVar2.a(lVar2.f860g, l.f853p, canvas3, iMin, iMin2);
        }
        m mVar6 = this.f883c;
        if ((mVar6.f870b.getRootAlpha() < 255) || colorFilter != null) {
            if (mVar6.f880l == null) {
                Paint paint2 = new Paint();
                mVar6.f880l = paint2;
                paint2.setFilterBitmap(true);
            }
            mVar6.f880l.setAlpha(mVar6.f870b.getRootAlpha());
            mVar6.f880l.setColorFilter(colorFilter);
            paint = mVar6.f880l;
        } else {
            paint = null;
        }
        canvas.drawBitmap(mVar6.f874f, (Rect) null, rect, paint);
        canvas.restoreToCount(iSave);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        Drawable drawable = this.f825b;
        return drawable != null ? drawable.getAlpha() : this.f883c.f870b.getRootAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getChangingConfigurations() {
        Drawable drawable = this.f825b;
        return drawable != null ? drawable.getChangingConfigurations() : super.getChangingConfigurations() | this.f883c.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public final ColorFilter getColorFilter() {
        Drawable drawable = this.f825b;
        return drawable != null ? drawable.getColorFilter() : this.f885e;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        if (this.f825b != null && Build.VERSION.SDK_INT >= 24) {
            return new n(this.f825b.getConstantState());
        }
        this.f883c.f869a = getChangingConfigurations();
        return this.f883c;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        Drawable drawable = this.f825b;
        return drawable != null ? drawable.getIntrinsicHeight() : (int) this.f883c.f870b.f862i;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        Drawable drawable = this.f825b;
        return drawable != null ? drawable.getIntrinsicWidth() : (int) this.f883c.f870b.f861h;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        Drawable drawable = this.f825b;
        if (drawable != null) {
            return drawable.getOpacity();
        }
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public final void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        Drawable drawable = this.f825b;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet);
        } else {
            inflate(resources, xmlPullParser, attributeSet, null);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void invalidateSelf() {
        Drawable drawable = this.f825b;
        if (drawable != null) {
            drawable.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isAutoMirrored() {
        Drawable drawable = this.f825b;
        return drawable != null ? drawable.isAutoMirrored() : this.f883c.f873e;
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isStateful() {
        ColorStateList colorStateList;
        Drawable drawable = this.f825b;
        if (drawable != null) {
            return drawable.isStateful();
        }
        if (!super.isStateful()) {
            m mVar = this.f883c;
            if (mVar != null) {
                l lVar = mVar.f870b;
                if (lVar.f867n == null) {
                    lVar.f867n = Boolean.valueOf(lVar.f860g.a());
                }
                if (lVar.f867n.booleanValue() || ((colorStateList = this.f883c.f871c) != null && colorStateList.isStateful())) {
                }
            }
            return false;
        }
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable mutate() {
        Drawable drawable = this.f825b;
        if (drawable != null) {
            drawable.mutate();
            return this;
        }
        if (!this.f886f && super.mutate() == this) {
            this.f883c = new m(this.f883c);
            this.f886f = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        Drawable drawable = this.f825b;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onStateChange(int[] iArr) {
        boolean z2;
        PorterDuff.Mode mode;
        Drawable drawable = this.f825b;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        m mVar = this.f883c;
        ColorStateList colorStateList = mVar.f871c;
        if (colorStateList == null || (mode = mVar.f872d) == null) {
            z2 = false;
        } else {
            this.f884d = a(colorStateList, mode);
            invalidateSelf();
            z2 = true;
        }
        l lVar = mVar.f870b;
        if (lVar.f867n == null) {
            lVar.f867n = Boolean.valueOf(lVar.f860g.a());
        }
        if (lVar.f867n.booleanValue()) {
            boolean zB = mVar.f870b.f860g.b(iArr);
            mVar.f879k |= zB;
            if (zB) {
                invalidateSelf();
                return true;
            }
        }
        return z2;
    }

    @Override // android.graphics.drawable.Drawable
    public final void scheduleSelf(Runnable runnable, long j2) {
        Drawable drawable = this.f825b;
        if (drawable != null) {
            drawable.scheduleSelf(runnable, j2);
        } else {
            super.scheduleSelf(runnable, j2);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i2) {
        Drawable drawable = this.f825b;
        if (drawable != null) {
            drawable.setAlpha(i2);
        } else if (this.f883c.f870b.getRootAlpha() != i2) {
            this.f883c.f870b.setRootAlpha(i2);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAutoMirrored(boolean z2) {
        Drawable drawable = this.f825b;
        if (drawable != null) {
            drawable.setAutoMirrored(z2);
        } else {
            this.f883c.f873e = z2;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f825b;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.f885e = colorFilter;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTint(int i2) {
        Drawable drawable = this.f825b;
        if (drawable != null) {
            androidx.lifecycle.i.d0(drawable, i2);
        } else {
            setTintList(ColorStateList.valueOf(i2));
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f825b;
        if (drawable != null) {
            drawable.setTintList(colorStateList);
            return;
        }
        m mVar = this.f883c;
        if (mVar.f871c != colorStateList) {
            mVar.f871c = colorStateList;
            this.f884d = a(colorStateList, mVar.f872d);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f825b;
        if (drawable != null) {
            drawable.setTintMode(mode);
            return;
        }
        m mVar = this.f883c;
        if (mVar.f872d != mode) {
            mVar.f872d = mode;
            this.f884d = a(mVar.f871c, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z2, boolean z3) {
        Drawable drawable = this.f825b;
        return drawable != null ? drawable.setVisible(z2, z3) : super.setVisible(z2, z3);
    }

    @Override // android.graphics.drawable.Drawable
    public final void unscheduleSelf(Runnable runnable) {
        Drawable drawable = this.f825b;
        if (drawable != null) {
            drawable.unscheduleSelf(runnable);
        } else {
            super.unscheduleSelf(runnable);
        }
    }

    public o(m mVar) {
        this.f887g = true;
        this.f888h = new float[9];
        this.f889i = new Matrix();
        this.f890j = new Rect();
        this.f883c = mVar;
        this.f884d = a(mVar.f871c, mVar.f872d);
    }

    @Override // android.graphics.drawable.Drawable
    public final void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        ColorStateList colorStateListO;
        int i2;
        Drawable drawable = this.f825b;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet, theme);
            return;
        }
        m mVar = this.f883c;
        mVar.f870b = new l();
        TypedArray typedArrayO = androidx.lifecycle.i.O(resources, theme, attributeSet, androidx.lifecycle.i.f441m);
        m mVar2 = this.f883c;
        l lVar = mVar2.f870b;
        int iB = androidx.lifecycle.i.B(typedArrayO, xmlPullParser, "tintMode", 6, -1);
        PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
        if (iB == 3) {
            mode = PorterDuff.Mode.SRC_OVER;
        } else if (iB != 5) {
            if (iB != 9) {
                switch (iB) {
                    case TYPE_ENUM_VALUE:
                        mode = PorterDuff.Mode.MULTIPLY;
                        break;
                    case TYPE_SFIXED32_VALUE:
                        mode = PorterDuff.Mode.SCREEN;
                        break;
                    case 16:
                        mode = PorterDuff.Mode.ADD;
                        break;
                }
            } else {
                mode = PorterDuff.Mode.SRC_ATOP;
            }
        }
        mVar2.f872d = mode;
        int i3 = 1;
        if (androidx.lifecycle.i.I(xmlPullParser, "tint")) {
            TypedValue typedValue = new TypedValue();
            typedArrayO.getValue(1, typedValue);
            int i4 = typedValue.type;
            if (i4 == 2) {
                throw new UnsupportedOperationException("Failed to resolve attribute at index 1: " + typedValue);
            }
            if (i4 < 28 || i4 > 31) {
                Resources resources2 = typedArrayO.getResources();
                try {
                    colorStateListO = androidx.lifecycle.i.o(resources2, resources2.getXml(typedArrayO.getResourceId(1, 0)), theme);
                } catch (Exception e2) {
                    Log.e("CSLCompat", "Failed to inflate ColorStateList.", e2);
                    colorStateListO = null;
                }
            } else {
                colorStateListO = ColorStateList.valueOf(typedValue.data);
            }
        } else {
            colorStateListO = null;
        }
        if (colorStateListO != null) {
            mVar2.f871c = colorStateListO;
        }
        boolean z2 = mVar2.f873e;
        if (androidx.lifecycle.i.I(xmlPullParser, "autoMirrored")) {
            z2 = typedArrayO.getBoolean(5, z2);
        }
        mVar2.f873e = z2;
        lVar.f863j = androidx.lifecycle.i.A(typedArrayO, xmlPullParser, "viewportWidth", 7, lVar.f863j);
        float fA = androidx.lifecycle.i.A(typedArrayO, xmlPullParser, "viewportHeight", 8, lVar.f864k);
        lVar.f864k = fA;
        if (lVar.f863j <= 0.0f) {
            throw new XmlPullParserException(typedArrayO.getPositionDescription() + "<vector> tag requires viewportWidth > 0");
        }
        if (fA <= 0.0f) {
            throw new XmlPullParserException(typedArrayO.getPositionDescription() + "<vector> tag requires viewportHeight > 0");
        }
        lVar.f861h = typedArrayO.getDimension(3, lVar.f861h);
        float dimension = typedArrayO.getDimension(2, lVar.f862i);
        lVar.f862i = dimension;
        if (lVar.f861h <= 0.0f) {
            throw new XmlPullParserException(typedArrayO.getPositionDescription() + "<vector> tag requires width > 0");
        }
        if (dimension <= 0.0f) {
            throw new XmlPullParserException(typedArrayO.getPositionDescription() + "<vector> tag requires height > 0");
        }
        lVar.setAlpha(androidx.lifecycle.i.A(typedArrayO, xmlPullParser, "alpha", 4, lVar.getAlpha()));
        String string = typedArrayO.getString(0);
        if (string != null) {
            lVar.f866m = string;
            lVar.f868o.put(string, lVar);
        }
        typedArrayO.recycle();
        mVar.f869a = getChangingConfigurations();
        mVar.f879k = true;
        m mVar3 = this.f883c;
        l lVar2 = mVar3.f870b;
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.push(lVar2.f860g);
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth() + 1;
        boolean z3 = true;
        for (int i5 = 3; eventType != i3 && (xmlPullParser.getDepth() >= depth || eventType != i5); i5 = 3) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                i iVar = (i) arrayDeque.peek();
                boolean zEquals = "path".equals(name);
                i2 = depth;
                m.b bVar = lVar2.f868o;
                if (zEquals) {
                    h hVar = new h();
                    TypedArray typedArrayO2 = androidx.lifecycle.i.O(resources, theme, attributeSet, androidx.lifecycle.i.f443o);
                    if (androidx.lifecycle.i.I(xmlPullParser, "pathData")) {
                        String string2 = typedArrayO2.getString(0);
                        if (string2 != null) {
                            hVar.f850b = string2;
                        }
                        String string3 = typedArrayO2.getString(2);
                        if (string3 != null) {
                            hVar.f849a = androidx.lifecycle.i.r(string3);
                        }
                        hVar.f828g = androidx.lifecycle.i.z(typedArrayO2, xmlPullParser, theme, "fillColor", 1);
                        hVar.f830i = androidx.lifecycle.i.A(typedArrayO2, xmlPullParser, "fillAlpha", 12, hVar.f830i);
                        int iB2 = androidx.lifecycle.i.B(typedArrayO2, xmlPullParser, "strokeLineCap", 8, -1);
                        Paint.Cap cap = hVar.f834m;
                        if (iB2 == 0) {
                            cap = Paint.Cap.BUTT;
                        } else if (iB2 == 1) {
                            cap = Paint.Cap.ROUND;
                        } else if (iB2 == 2) {
                            cap = Paint.Cap.SQUARE;
                        }
                        hVar.f834m = cap;
                        int iB3 = androidx.lifecycle.i.B(typedArrayO2, xmlPullParser, "strokeLineJoin", 9, -1);
                        Paint.Join join = hVar.f835n;
                        if (iB3 == 0) {
                            join = Paint.Join.MITER;
                        } else if (iB3 == 1) {
                            join = Paint.Join.ROUND;
                        } else if (iB3 == 2) {
                            join = Paint.Join.BEVEL;
                        }
                        hVar.f835n = join;
                        hVar.f836o = androidx.lifecycle.i.A(typedArrayO2, xmlPullParser, "strokeMiterLimit", 10, hVar.f836o);
                        hVar.f826e = androidx.lifecycle.i.z(typedArrayO2, xmlPullParser, theme, "strokeColor", 3);
                        hVar.f829h = androidx.lifecycle.i.A(typedArrayO2, xmlPullParser, "strokeAlpha", 11, hVar.f829h);
                        hVar.f827f = androidx.lifecycle.i.A(typedArrayO2, xmlPullParser, "strokeWidth", 4, hVar.f827f);
                        hVar.f832k = androidx.lifecycle.i.A(typedArrayO2, xmlPullParser, "trimPathEnd", 6, hVar.f832k);
                        hVar.f833l = androidx.lifecycle.i.A(typedArrayO2, xmlPullParser, "trimPathOffset", 7, hVar.f833l);
                        hVar.f831j = androidx.lifecycle.i.A(typedArrayO2, xmlPullParser, "trimPathStart", 5, hVar.f831j);
                        hVar.f851c = androidx.lifecycle.i.B(typedArrayO2, xmlPullParser, "fillType", 13, hVar.f851c);
                    }
                    typedArrayO2.recycle();
                    iVar.f838b.add(hVar);
                    if (hVar.getPathName() != null) {
                        bVar.put(hVar.getPathName(), hVar);
                    }
                    mVar3.f869a |= hVar.f852d;
                    z3 = false;
                } else {
                    lVar2 = lVar2;
                    if ("clip-path".equals(name)) {
                        g gVar = new g();
                        if (androidx.lifecycle.i.I(xmlPullParser, "pathData")) {
                            TypedArray typedArrayO3 = androidx.lifecycle.i.O(resources, theme, attributeSet, androidx.lifecycle.i.f444p);
                            String string4 = typedArrayO3.getString(0);
                            if (string4 != null) {
                                gVar.f850b = string4;
                            }
                            String string5 = typedArrayO3.getString(1);
                            if (string5 != null) {
                                gVar.f849a = androidx.lifecycle.i.r(string5);
                            }
                            gVar.f851c = androidx.lifecycle.i.B(typedArrayO3, xmlPullParser, "fillType", 2, 0);
                            typedArrayO3.recycle();
                        }
                        iVar.f838b.add(gVar);
                        if (gVar.getPathName() != null) {
                            bVar.put(gVar.getPathName(), gVar);
                        }
                        mVar3.f869a = gVar.f852d | mVar3.f869a;
                    } else if ("group".equals(name)) {
                        i iVar2 = new i();
                        TypedArray typedArrayO4 = androidx.lifecycle.i.O(resources, theme, attributeSet, androidx.lifecycle.i.f442n);
                        iVar2.f839c = androidx.lifecycle.i.A(typedArrayO4, xmlPullParser, "rotation", 5, iVar2.f839c);
                        iVar2.f840d = typedArrayO4.getFloat(1, iVar2.f840d);
                        iVar2.f841e = typedArrayO4.getFloat(2, iVar2.f841e);
                        iVar2.f842f = androidx.lifecycle.i.A(typedArrayO4, xmlPullParser, "scaleX", 3, iVar2.f842f);
                        iVar2.f843g = androidx.lifecycle.i.A(typedArrayO4, xmlPullParser, "scaleY", 4, iVar2.f843g);
                        iVar2.f844h = androidx.lifecycle.i.A(typedArrayO4, xmlPullParser, "translateX", 6, iVar2.f844h);
                        iVar2.f845i = androidx.lifecycle.i.A(typedArrayO4, xmlPullParser, "translateY", 7, iVar2.f845i);
                        String string6 = typedArrayO4.getString(0);
                        if (string6 != null) {
                            iVar2.f848l = string6;
                        }
                        iVar2.c();
                        typedArrayO4.recycle();
                        iVar.f838b.add(iVar2);
                        arrayDeque.push(iVar2);
                        if (iVar2.getGroupName() != null) {
                            bVar.put(iVar2.getGroupName(), iVar2);
                        }
                        mVar3.f869a = iVar2.f847k | mVar3.f869a;
                    }
                }
            } else {
                lVar2 = lVar2;
                i2 = depth;
                if (eventType == 3 && "group".equals(xmlPullParser.getName())) {
                    arrayDeque.pop();
                }
            }
            eventType = xmlPullParser.next();
            depth = i2;
            lVar2 = lVar2;
            i3 = 1;
        }
        if (z3) {
            throw new XmlPullParserException("no path defined");
        }
        this.f884d = a(mVar.f871c, mVar.f872d);
    }
}
