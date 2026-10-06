package g0;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class c extends f implements Animatable {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final a f820c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Context f821d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final f.f f822e;

    public c(Context context) {
        f.f fVar = new f.f(this);
        this.f822e = fVar;
        this.f821d = context;
        this.f820c = new a(fVar);
    }

    @Override // g0.f, android.graphics.drawable.Drawable
    public final void applyTheme(Resources.Theme theme) {
        Drawable drawable = this.f825b;
        if (drawable != null) {
            drawable.applyTheme(theme);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean canApplyTheme() {
        Drawable drawable = this.f825b;
        if (drawable != null) {
            return drawable.canApplyTheme();
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Drawable drawable = this.f825b;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        a aVar = this.f820c;
        aVar.f815a.draw(canvas);
        if (aVar.f816b.isStarted()) {
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        Drawable drawable = this.f825b;
        return drawable != null ? drawable.getAlpha() : this.f820c.f815a.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getChangingConfigurations() {
        Drawable drawable = this.f825b;
        if (drawable != null) {
            return drawable.getChangingConfigurations();
        }
        int changingConfigurations = super.getChangingConfigurations();
        this.f820c.getClass();
        return changingConfigurations | 0;
    }

    @Override // android.graphics.drawable.Drawable
    public final ColorFilter getColorFilter() {
        Drawable drawable = this.f825b;
        return drawable != null ? drawable.getColorFilter() : this.f820c.f815a.getColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        if (this.f825b == null || Build.VERSION.SDK_INT < 24) {
            return null;
        }
        return new b(this.f825b.getConstantState());
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        Drawable drawable = this.f825b;
        return drawable != null ? drawable.getIntrinsicHeight() : this.f820c.f815a.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        Drawable drawable = this.f825b;
        return drawable != null ? drawable.getIntrinsicWidth() : this.f820c.f815a.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        Drawable drawable = this.f825b;
        return drawable != null ? drawable.getOpacity() : this.f820c.f815a.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    public final void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws Throwable {
        inflate(resources, xmlPullParser, attributeSet, null);
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isAutoMirrored() {
        Drawable drawable = this.f825b;
        return drawable != null ? drawable.isAutoMirrored() : this.f820c.f815a.isAutoMirrored();
    }

    @Override // android.graphics.drawable.Animatable
    public final boolean isRunning() {
        Drawable drawable = this.f825b;
        return drawable != null ? ((AnimatedVectorDrawable) drawable).isRunning() : this.f820c.f816b.isRunning();
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isStateful() {
        Drawable drawable = this.f825b;
        return drawable != null ? drawable.isStateful() : this.f820c.f815a.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable mutate() {
        Drawable drawable = this.f825b;
        if (drawable != null) {
            drawable.mutate();
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        Drawable drawable = this.f825b;
        if (drawable != null) {
            drawable.setBounds(rect);
        } else {
            this.f820c.f815a.setBounds(rect);
        }
    }

    @Override // g0.f, android.graphics.drawable.Drawable
    public final boolean onLevelChange(int i2) {
        Drawable drawable = this.f825b;
        return drawable != null ? drawable.setLevel(i2) : this.f820c.f815a.setLevel(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onStateChange(int[] iArr) {
        Drawable drawable = this.f825b;
        return drawable != null ? drawable.setState(iArr) : this.f820c.f815a.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i2) {
        Drawable drawable = this.f825b;
        if (drawable != null) {
            drawable.setAlpha(i2);
        } else {
            this.f820c.f815a.setAlpha(i2);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAutoMirrored(boolean z2) {
        Drawable drawable = this.f825b;
        if (drawable != null) {
            drawable.setAutoMirrored(z2);
        } else {
            this.f820c.f815a.setAutoMirrored(z2);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f825b;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.f820c.f815a.setColorFilter(colorFilter);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTint(int i2) {
        Drawable drawable = this.f825b;
        if (drawable != null) {
            androidx.lifecycle.i.d0(drawable, i2);
        } else {
            this.f820c.f815a.setTint(i2);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f825b;
        if (drawable != null) {
            androidx.lifecycle.i.e0(drawable, colorStateList);
        } else {
            this.f820c.f815a.setTintList(colorStateList);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f825b;
        if (drawable != null) {
            androidx.lifecycle.i.f0(drawable, mode);
        } else {
            this.f820c.f815a.setTintMode(mode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z2, boolean z3) {
        Drawable drawable = this.f825b;
        if (drawable != null) {
            return drawable.setVisible(z2, z3);
        }
        this.f820c.f815a.setVisible(z2, z3);
        return super.setVisible(z2, z3);
    }

    @Override // android.graphics.drawable.Animatable
    public final void start() {
        Drawable drawable = this.f825b;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).start();
            return;
        }
        a aVar = this.f820c;
        if (aVar.f816b.isStarted()) {
            return;
        }
        aVar.f816b.start();
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public final void stop() {
        Drawable drawable = this.f825b;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).stop();
        } else {
            this.f820c.f816b.end();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws Throwable {
        a aVar;
        Animator animatorN;
        o oVar;
        int next;
        Drawable drawable = this.f825b;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet, theme);
            return;
        }
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth() + 1;
        while (true) {
            aVar = this.f820c;
            if (eventType == 1 || (xmlPullParser.getDepth() < depth && eventType == 3)) {
                break;
            }
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                XmlResourceParser xmlResourceParser = null;
                if ("animated-vector".equals(name)) {
                    TypedArray typedArrayO = androidx.lifecycle.i.O(resources, theme, attributeSet, androidx.lifecycle.i.f445q);
                    int resourceId = typedArrayO.getResourceId(0, 0);
                    if (resourceId != 0) {
                        PorterDuff.Mode mode = o.f882k;
                        if (Build.VERSION.SDK_INT >= 24) {
                            oVar = new o();
                            oVar.f825b = resources.getDrawable(resourceId, theme);
                            new n(oVar.f825b.getConstantState());
                        } else {
                            try {
                                XmlResourceParser xml = resources.getXml(resourceId);
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
                                oVar = new o();
                                oVar.inflate(resources, xml, attributeSetAsAttributeSet, theme);
                            } catch (IOException e2) {
                                e = e2;
                                Log.e("VectorDrawableCompat", "parser error", e);
                                oVar = null;
                            } catch (XmlPullParserException e3) {
                                e = e3;
                                Log.e("VectorDrawableCompat", "parser error", e);
                                oVar = null;
                            }
                        }
                        oVar.f887g = false;
                        oVar.setCallback(this.f822e);
                        o oVar2 = aVar.f815a;
                        if (oVar2 != null) {
                            oVar2.setCallback(null);
                        }
                        aVar.f815a = oVar;
                    }
                    typedArrayO.recycle();
                } else if ("target".equals(name)) {
                    TypedArray typedArrayObtainAttributes = resources.obtainAttributes(attributeSet, androidx.lifecycle.i.f446r);
                    String string = typedArrayObtainAttributes.getString(0);
                    int resourceId2 = typedArrayObtainAttributes.getResourceId(1, 0);
                    if (resourceId2 != 0) {
                        Context context = this.f821d;
                        if (context == null) {
                            typedArrayObtainAttributes.recycle();
                            throw new IllegalStateException("Context can't be null when inflating animators");
                        }
                        if (Build.VERSION.SDK_INT >= 24) {
                            animatorN = AnimatorInflater.loadAnimator(context, resourceId2);
                        } else {
                            Resources resources2 = context.getResources();
                            Resources.Theme theme2 = context.getTheme();
                            try {
                                try {
                                    XmlResourceParser animation = resources2.getAnimation(resourceId2);
                                    try {
                                        animatorN = androidx.lifecycle.i.n(context, resources2, theme2, animation, Xml.asAttributeSet(animation), null, 0);
                                        animation.close();
                                    } catch (IOException e4) {
                                        e = e4;
                                        Resources.NotFoundException notFoundException = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(resourceId2));
                                        notFoundException.initCause(e);
                                        throw notFoundException;
                                    } catch (XmlPullParserException e5) {
                                        e = e5;
                                        Resources.NotFoundException notFoundException2 = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(resourceId2));
                                        notFoundException2.initCause(e);
                                        throw notFoundException2;
                                    } catch (Throwable th) {
                                        th = th;
                                        xmlResourceParser = animation;
                                        if (xmlResourceParser != null) {
                                            xmlResourceParser.close();
                                        }
                                        throw th;
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                }
                            } catch (IOException e6) {
                                e = e6;
                            } catch (XmlPullParserException e7) {
                                e = e7;
                            }
                        }
                        animatorN.setTarget(aVar.f815a.f883c.f870b.f868o.getOrDefault(string, null));
                        if (aVar.f817c == null) {
                            aVar.f817c = new ArrayList();
                            aVar.f818d = new m.b();
                        }
                        aVar.f817c.add(animatorN);
                        aVar.f818d.put(animatorN, string);
                    }
                    typedArrayObtainAttributes.recycle();
                } else {
                    continue;
                }
            }
            eventType = xmlPullParser.next();
        }
        if (aVar.f816b == null) {
            aVar.f816b = new AnimatorSet();
        }
        aVar.f816b.playTogether(aVar.f817c);
    }
}
