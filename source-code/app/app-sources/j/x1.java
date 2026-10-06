package j;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class x1 implements z1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1493a;

    public /* synthetic */ x1(int i2) {
        this.f1493a = i2;
    }

    public final Drawable a(Context context, XmlResourceParser xmlResourceParser, AttributeSet attributeSet, Resources.Theme theme) throws Throwable {
        switch (this.f1493a) {
            case 0:
                try {
                    return f.e.e(context, theme, context.getResources(), attributeSet, xmlResourceParser);
                } catch (Exception e2) {
                    Log.e("AsldcInflateDelegate", "Exception while inflating <animated-selector>", e2);
                    return null;
                }
            case 1:
                try {
                    Resources resources = context.getResources();
                    g0.c cVar = new g0.c(context);
                    cVar.inflate(resources, xmlResourceParser, attributeSet, theme);
                    return cVar;
                } catch (Exception e3) {
                    Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", e3);
                    return null;
                }
            case 2:
                String classAttribute = attributeSet.getClassAttribute();
                if (classAttribute == null) {
                    return null;
                }
                try {
                    Drawable drawable = (Drawable) x1.class.getClassLoader().loadClass(classAttribute).asSubclass(Drawable.class).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                    drawable.inflate(context.getResources(), xmlResourceParser, attributeSet, theme);
                    return drawable;
                } catch (Exception e4) {
                    Log.e("DrawableDelegate", "Exception while inflating <drawable>", e4);
                    return null;
                }
            default:
                try {
                    Resources resources2 = context.getResources();
                    g0.o oVar = new g0.o();
                    oVar.inflate(resources2, xmlResourceParser, attributeSet, theme);
                    return oVar;
                } catch (Exception e5) {
                    Log.e("VdcInflateDelegate", "Exception while inflating <vector>", e5);
                    return null;
                }
        }
    }
}
