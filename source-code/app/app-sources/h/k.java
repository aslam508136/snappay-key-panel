package h;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.SubMenu;
import i.r;
import j.g1;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class k extends MenuInflater {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Class[] f941e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Class[] f942f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object[] f943a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object[] f944b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Context f945c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object f946d;

    static {
        Class[] clsArr = {Context.class};
        f941e = clsArr;
        f942f = clsArr;
    }

    public k(Context context) {
        super(context);
        this.f945c = context;
        Object[] objArr = {context};
        this.f943a = objArr;
        this.f944b = objArr;
    }

    public static Object a(Context context) {
        return (!(context instanceof Activity) && (context instanceof ContextWrapper)) ? a(((ContextWrapper) context).getBaseContext()) : context;
    }

    /* JADX WARN: Code duplicated, block: B:45:0x00fd  */
    /* JADX WARN: Multi-variable type inference failed */
    public final void b(XmlResourceParser xmlResourceParser, AttributeSet attributeSet, Menu menu) throws XmlPullParserException, IOException {
        int i2;
        r rVar;
        ColorStateList colorStateList;
        j jVar = new j(this, menu);
        int eventType = xmlResourceParser.getEventType();
        do {
            i2 = 2;
            if (eventType == 2) {
                String name = xmlResourceParser.getName();
                if (!name.equals("menu")) {
                    throw new RuntimeException("Expecting menu, got ".concat(name));
                }
                eventType = xmlResourceParser.next();
                break;
            }
            eventType = xmlResourceParser.next();
        } while (eventType != 1);
        boolean z2 = false;
        boolean z3 = false;
        String str = null;
        while (!z2) {
            if (eventType == 1) {
                throw new RuntimeException("Unexpected end of document");
            }
            Menu menu2 = jVar.f915a;
            if (eventType != i2) {
                if (eventType == 3) {
                    String name2 = xmlResourceParser.getName();
                    if (z3 && name2.equals(str)) {
                        z3 = false;
                        str = null;
                    } else if (name2.equals("group")) {
                        jVar.f916b = 0;
                        jVar.f917c = 0;
                        jVar.f918d = 0;
                        jVar.f919e = 0;
                        jVar.f920f = true;
                        jVar.f921g = true;
                    } else if (name2.equals("item")) {
                        if (!jVar.f922h) {
                            r rVar2 = jVar.f940z;
                            if (rVar2 == null || !rVar2.f1113a.hasSubMenu()) {
                                jVar.f922h = true;
                                jVar.b(menu2.add(jVar.f916b, jVar.f923i, jVar.f924j, jVar.f925k));
                            } else {
                                jVar.f922h = true;
                                jVar.b(menu2.addSubMenu(jVar.f916b, jVar.f923i, jVar.f924j, jVar.f925k).getItem());
                            }
                        }
                    } else if (name2.equals("menu")) {
                        z2 = true;
                    }
                }
            } else if (!z3) {
                String name3 = xmlResourceParser.getName();
                boolean zEquals = name3.equals("group");
                k kVar = jVar.E;
                if (zEquals) {
                    TypedArray typedArrayObtainStyledAttributes = kVar.f945c.obtainStyledAttributes(attributeSet, c.a.f495o);
                    jVar.f916b = typedArrayObtainStyledAttributes.getResourceId(1, 0);
                    jVar.f917c = typedArrayObtainStyledAttributes.getInt(3, 0);
                    jVar.f918d = typedArrayObtainStyledAttributes.getInt(4, 0);
                    jVar.f919e = typedArrayObtainStyledAttributes.getInt(5, 0);
                    jVar.f920f = typedArrayObtainStyledAttributes.getBoolean(2, true);
                    jVar.f921g = typedArrayObtainStyledAttributes.getBoolean(0, true);
                    typedArrayObtainStyledAttributes.recycle();
                } else if (name3.equals("item")) {
                    Context context = kVar.f945c;
                    m0.a aVar = new m0.a(context, context.obtainStyledAttributes(attributeSet, c.a.f496p));
                    jVar.f923i = aVar.p(2, 0);
                    jVar.f924j = (aVar.n(5, jVar.f917c) & (-65536)) | (aVar.n(6, jVar.f918d) & 65535);
                    jVar.f925k = aVar.r(7);
                    jVar.f926l = aVar.r(8);
                    jVar.f927m = aVar.p(0, 0);
                    String strQ = aVar.q(9);
                    jVar.f928n = strQ == null ? (char) 0 : strQ.charAt(0);
                    jVar.f929o = aVar.n(16, 4096);
                    String strQ2 = aVar.q(10);
                    jVar.f930p = strQ2 == null ? (char) 0 : strQ2.charAt(0);
                    jVar.f931q = aVar.n(20, 4096);
                    jVar.f932r = aVar.s(11) ? aVar.g(11, false) : jVar.f919e;
                    jVar.f933s = aVar.g(3, false);
                    jVar.f934t = aVar.g(4, jVar.f920f);
                    jVar.f935u = aVar.g(1, jVar.f921g);
                    jVar.f936v = aVar.n(21, -1);
                    jVar.f939y = aVar.q(12);
                    jVar.f937w = aVar.p(13, 0);
                    jVar.f938x = aVar.q(15);
                    String strQ3 = aVar.q(14);
                    boolean z4 = strQ3 != null;
                    if (z4 && jVar.f937w == 0 && jVar.f938x == null) {
                        rVar = (r) jVar.a(strQ3, f942f, kVar.f944b);
                    } else {
                        if (z4) {
                            Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                        }
                        rVar = null;
                    }
                    jVar.f940z = rVar;
                    jVar.A = aVar.r(17);
                    jVar.B = aVar.r(22);
                    if (aVar.s(19)) {
                        jVar.D = g1.c(aVar.n(19, -1), jVar.D);
                        colorStateList = null;
                    } else {
                        colorStateList = null;
                        jVar.D = null;
                    }
                    if (aVar.s(18)) {
                        jVar.C = aVar.h(18);
                    } else {
                        jVar.C = colorStateList;
                    }
                    aVar.w();
                    jVar.f922h = false;
                } else if (name3.equals("menu")) {
                    jVar.f922h = true;
                    SubMenu subMenuAddSubMenu = menu2.addSubMenu(jVar.f916b, jVar.f923i, jVar.f924j, jVar.f925k);
                    jVar.b(subMenuAddSubMenu.getItem());
                    b(xmlResourceParser, attributeSet, subMenuAddSubMenu);
                } else {
                    str = name3;
                    z3 = true;
                }
            }
            eventType = xmlResourceParser.next();
            i2 = 2;
        }
    }

    @Override // android.view.MenuInflater
    public final void inflate(int i2, Menu menu) {
        if (!(menu instanceof t.a)) {
            super.inflate(i2, menu);
            return;
        }
        XmlResourceParser layout = null;
        try {
            try {
                try {
                    layout = this.f945c.getResources().getLayout(i2);
                    b(layout, Xml.asAttributeSet(layout), menu);
                    layout.close();
                } catch (XmlPullParserException e2) {
                    throw new InflateException("Error inflating menu XML", e2);
                }
            } catch (IOException e3) {
                throw new InflateException("Error inflating menu XML", e3);
            }
        } catch (Throwable th) {
            if (layout != null) {
                layout.close();
            }
            throw th;
        }
    }
}
