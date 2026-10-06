package f;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.StateSet;
import g0.o;
import j.a2;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class e extends j implements s.a {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public b f764q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public b.a f765r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f766s = -1;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f767t = -1;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f768u;

    public e(b bVar, Resources resources) {
        d(new b(bVar, this, resources));
        onStateChange(getState());
        jumpToCurrentState();
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static e e(Context context, Resources.Theme theme, Resources resources, AttributeSet attributeSet, XmlResourceParser xmlResourceParser) throws Throwable {
        int depth;
        Context context2;
        int next;
        int next2;
        Resources.Theme theme2 = theme;
        Resources resources2 = resources;
        AttributeSet attributeSet2 = attributeSet;
        XmlResourceParser xmlResourceParser2 = xmlResourceParser;
        String name = xmlResourceParser.getName();
        if (!name.equals("animated-selector")) {
            throw new XmlPullParserException(xmlResourceParser.getPositionDescription() + ": invalid animated-selector tag " + name);
        }
        e eVar = new e(null, null);
        TypedArray typedArrayO = androidx.lifecycle.i.O(resources2, theme2, attributeSet2, g.a.f812a);
        int i2 = 1;
        eVar.setVisible(typedArrayO.getBoolean(1, true), true);
        b bVar = eVar.f764q;
        bVar.f774d |= typedArrayO.getChangingConfigurations();
        int i3 = 2;
        bVar.f779i = typedArrayO.getBoolean(2, bVar.f779i);
        int i4 = 3;
        bVar.f782l = typedArrayO.getBoolean(3, bVar.f782l);
        bVar.f795y = typedArrayO.getInt(4, bVar.f795y);
        bVar.f796z = typedArrayO.getInt(5, bVar.f796z);
        boolean z2 = false;
        eVar.setDither(typedArrayO.getBoolean(0, bVar.f793w));
        g gVar = eVar.f798b;
        if (resources2 != null) {
            gVar.f772b = resources2;
            int i5 = resources.getDisplayMetrics().densityDpi;
            if (i5 == 0) {
                i5 = 160;
            }
            int i6 = gVar.f773c;
            gVar.f773c = i5;
            if (i6 != i5) {
                gVar.f783m = false;
                gVar.f780j = false;
            }
        } else {
            gVar.getClass();
        }
        typedArrayO.recycle();
        int depth2 = xmlResourceParser.getDepth() + 1;
        Context context3 = context;
        Resources.Theme theme3 = theme2;
        while (true) {
            int next3 = xmlResourceParser.next();
            if (next3 == i2 || ((depth = xmlResourceParser.getDepth()) < depth2 && next3 == i4)) {
                break;
            }
            if (next3 == i3 && depth <= depth2) {
                if (xmlResourceParser.getName().equals("item")) {
                    TypedArray typedArrayO2 = androidx.lifecycle.i.O(resources2, theme3, attributeSet2, g.a.f813b);
                    int resourceId = typedArrayO2.getResourceId(z2 ? 1 : 0, z2 ? 1 : 0);
                    int resourceId2 = typedArrayO2.getResourceId(i2, -1);
                    Drawable drawableF = resourceId2 > 0 ? a2.d().f(context3, resourceId2) : null;
                    typedArrayO2.recycle();
                    int attributeCount = attributeSet.getAttributeCount();
                    int[] iArr = new int[attributeCount];
                    int i7 = 0;
                    for (int i8 = 0; i8 < attributeCount; i8++) {
                        int attributeNameResource = attributeSet2.getAttributeNameResource(i8);
                        if (attributeNameResource != 0 && attributeNameResource != 16842960 && attributeNameResource != 16843161) {
                            int i9 = i7 + 1;
                            if (!attributeSet2.getAttributeBooleanValue(i8, z2)) {
                                attributeNameResource = -attributeNameResource;
                            }
                            iArr[i7] = attributeNameResource;
                            i7 = i9;
                        }
                    }
                    int[] iArrTrimStateSet = StateSet.trimStateSet(iArr, i7);
                    if (drawableF == null) {
                        do {
                            next2 = xmlResourceParser.next();
                        } while (next2 == 4);
                        if (next2 != 2) {
                            throw new XmlPullParserException(xmlResourceParser.getPositionDescription() + ": <item> tag requires a 'drawable' attribute or child tag defining a drawable");
                        }
                        if (xmlResourceParser.getName().equals("vector")) {
                            drawableF = new o();
                            drawableF.inflate(resources2, xmlResourceParser2, attributeSet2, theme3);
                        } else {
                            drawableF = Drawable.createFromXmlInner(resources2, xmlResourceParser2, attributeSet2, theme3);
                        }
                    }
                    if (drawableF == null) {
                        throw new XmlPullParserException(xmlResourceParser.getPositionDescription() + ": <item> tag requires a 'drawable' attribute or child tag defining a drawable");
                    }
                    b bVar2 = eVar.f764q;
                    int iA = bVar2.a(drawableF);
                    bVar2.H[iA] = iArrTrimStateSet;
                    bVar2.J.d(iA, Integer.valueOf(resourceId));
                } else {
                    if (xmlResourceParser.getName().equals("transition")) {
                        TypedArray typedArrayO3 = androidx.lifecycle.i.O(resources2, theme3, attributeSet2, g.a.f814c);
                        int resourceId3 = typedArrayO3.getResourceId(2, -1);
                        int resourceId4 = typedArrayO3.getResourceId(1, -1);
                        int resourceId5 = typedArrayO3.getResourceId(z2 ? 1 : 0, -1);
                        Drawable drawableF2 = resourceId5 > 0 ? a2.d().f(context3, resourceId5) : null;
                        boolean z3 = typedArrayO3.getBoolean(3, z2);
                        typedArrayO3.recycle();
                        if (drawableF2 == null) {
                            do {
                                next = xmlResourceParser.next();
                            } while (next == 4);
                            if (next != 2) {
                                throw new XmlPullParserException(xmlResourceParser.getPositionDescription() + ": <transition> tag requires a 'drawable' attribute or child tag defining a drawable");
                            }
                            if (xmlResourceParser.getName().equals("animated-vector")) {
                                context2 = context;
                                drawableF2 = new g0.c(context2);
                                drawableF2.inflate(resources2, xmlResourceParser2, attributeSet2, theme2);
                            } else {
                                context2 = context;
                                drawableF2 = Drawable.createFromXmlInner(resources2, xmlResourceParser2, attributeSet2, theme3);
                            }
                        } else {
                            context2 = context;
                        }
                        if (drawableF2 == null) {
                            throw new XmlPullParserException(xmlResourceParser.getPositionDescription() + ": <transition> tag requires a 'drawable' attribute or child tag defining a drawable");
                        }
                        if (resourceId3 == -1 || resourceId4 == -1) {
                            throw new XmlPullParserException(xmlResourceParser.getPositionDescription() + ": <transition> tag requires 'fromId' & 'toId' attributes");
                        }
                        b bVar3 = eVar.f764q;
                        int iA2 = bVar3.a(drawableF2);
                        long j2 = resourceId3;
                        long j3 = resourceId4;
                        long j4 = j3 | (j2 << 32);
                        long j5 = z3 ? 8589934592L : 0L;
                        long j6 = iA2;
                        bVar3.I.a(Long.valueOf(j6 | j5), j4);
                        if (z3) {
                            bVar3.I.a(Long.valueOf(j6 | 4294967296L | j5), (j3 << 32) | j2);
                        }
                    } else {
                        context2 = context;
                    }
                    theme3 = theme;
                    context3 = context2;
                }
                theme2 = theme;
                resources2 = resources;
                attributeSet2 = attributeSet;
                xmlResourceParser2 = xmlResourceParser;
                i2 = 1;
                z2 = false;
                i3 = 2;
                i4 = 3;
            }
        }
        eVar.onStateChange(eVar.getState());
        return eVar;
    }

    @Override // f.h
    public final void d(b bVar) {
        this.f798b = bVar;
        int i2 = this.f804h;
        if (i2 >= 0) {
            Drawable drawableD = bVar.d(i2);
            this.f800d = drawableD;
            if (drawableD != null) {
                b(drawableD);
            }
        }
        this.f801e = null;
        if (bVar instanceof i) {
            this.f810o = bVar;
        }
        if (bVar instanceof b) {
            this.f764q = bVar;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isStateful() {
        return true;
    }

    @Override // f.h, android.graphics.drawable.Drawable
    public final void jumpToCurrentState() {
        super.jumpToCurrentState();
        b.a aVar = this.f765r;
        if (aVar != null) {
            aVar.h();
            this.f765r = null;
            c(this.f766s);
            this.f766s = -1;
            this.f767t = -1;
        }
    }

    @Override // f.j, f.h, android.graphics.drawable.Drawable
    public final Drawable mutate() {
        if (!this.f768u) {
            super.mutate();
            this.f764q.e();
            this.f768u = true;
        }
        return this;
    }

    /* JADX WARN: Code duplicated, block: B:34:0x0069  */
    /* JADX WARN: Code duplicated, block: B:35:0x006e  */
    /* JADX WARN: Code duplicated, block: B:37:0x0080  */
    /* JADX WARN: Code duplicated, block: B:38:0x0082  */
    /* JADX WARN: Code duplicated, block: B:45:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:47:0x00d3  */
    /* JADX WARN: Code duplicated, block: B:48:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:51:0x00df  */
    /* JADX WARN: Code duplicated, block: B:53:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:54:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:56:0x0104  */
    /* JADX WARN: Code duplicated, block: B:58:0x0108  */
    /* JADX WARN: Code duplicated, block: B:59:0x0110  */
    /* JADX WARN: Code duplicated, block: B:61:0x0114  */
    @Override // f.j, android.graphics.drawable.Drawable
    public final boolean onStateChange(int[] iArr) {
        b bVar;
        int iIntValue;
        int iIntValue2;
        boolean z2;
        long j2;
        int iLongValue;
        boolean z3;
        Object obj;
        b.a aVar;
        boolean z4;
        b bVar2 = this.f764q;
        int[][] iArr2 = bVar2.H;
        int i2 = bVar2.f778h;
        boolean z5 = false;
        int i3 = 0;
        while (true) {
            if (i3 >= i2) {
                i3 = -1;
                break;
            }
            if (StateSet.stateSetMatches(iArr2[i3], iArr)) {
                break;
            }
            i3++;
        }
        if (i3 < 0) {
            int[] iArr3 = StateSet.WILD_CARD;
            int[][] iArr4 = bVar2.H;
            int i4 = bVar2.f778h;
            i3 = 0;
            while (true) {
                if (i3 >= i4) {
                    i3 = -1;
                    break;
                }
                if (StateSet.stateSetMatches(iArr4[i3], iArr3)) {
                    break;
                }
                i3++;
            }
        }
        int i5 = this.f804h;
        if (i3 != i5) {
            b.a aVar2 = this.f765r;
            if (aVar2 == null) {
                this.f765r = null;
                this.f767t = -1;
                this.f766s = -1;
                bVar = this.f764q;
                if (i5 < 0) {
                    bVar.getClass();
                    iIntValue = 0;
                } else {
                    iIntValue = ((Integer) bVar.J.c(i5, 0)).intValue();
                }
                if (i3 < 0) {
                    iIntValue2 = 0;
                } else {
                    iIntValue2 = ((Integer) bVar.J.c(i3, 0)).intValue();
                }
                if (iIntValue2 != 0 && iIntValue != 0) {
                    int i6 = b.K;
                    j2 = ((long) iIntValue2) | (((long) iIntValue) << 32);
                    iLongValue = (int) ((Long) bVar.I.d(-1L, j2)).longValue();
                    if (iLongValue >= 0) {
                        if ((((Long) bVar.I.d(-1L, j2)).longValue() & 8589934592L) != 0) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        c(iLongValue);
                        obj = this.f800d;
                        if (obj instanceof AnimationDrawable) {
                            if ((((Long) bVar.I.d(-1L, j2)).longValue() & 4294967296L) != 0) {
                                z4 = true;
                            } else {
                                z4 = false;
                            }
                            aVar = new c((AnimationDrawable) obj, z4, z3);
                        } else if (obj instanceof g0.c) {
                            aVar = new a((g0.c) obj, 1);
                        } else if (obj instanceof Animatable) {
                            aVar = new a((Animatable) obj, 0);
                        }
                        aVar.g();
                        this.f765r = aVar;
                        this.f767t = i5;
                        this.f766s = i3;
                        z2 = true;
                    }
                }
                z2 = false;
            } else {
                if (i3 != this.f766s) {
                    if (i3 == this.f767t && aVar2.d()) {
                        aVar2.f();
                        this.f766s = this.f767t;
                        this.f767t = i3;
                    } else {
                        i5 = this.f766s;
                        aVar2.h();
                        this.f765r = null;
                        this.f767t = -1;
                        this.f766s = -1;
                        bVar = this.f764q;
                        if (i5 < 0) {
                            bVar.getClass();
                            iIntValue = 0;
                        } else {
                            iIntValue = ((Integer) bVar.J.c(i5, 0)).intValue();
                        }
                        if (i3 < 0) {
                            iIntValue2 = 0;
                        } else {
                            iIntValue2 = ((Integer) bVar.J.c(i3, 0)).intValue();
                        }
                        if (iIntValue2 != 0) {
                            int i7 = b.K;
                            j2 = ((long) iIntValue2) | (((long) iIntValue) << 32);
                            iLongValue = (int) ((Long) bVar.I.d(-1L, j2)).longValue();
                            if (iLongValue >= 0) {
                                if ((((Long) bVar.I.d(-1L, j2)).longValue() & 8589934592L) != 0) {
                                    z3 = true;
                                } else {
                                    z3 = false;
                                }
                                c(iLongValue);
                                obj = this.f800d;
                                if (obj instanceof AnimationDrawable) {
                                    if ((((Long) bVar.I.d(-1L, j2)).longValue() & 4294967296L) != 0) {
                                        z4 = true;
                                    } else {
                                        z4 = false;
                                    }
                                    aVar = new c((AnimationDrawable) obj, z4, z3);
                                } else if (obj instanceof g0.c) {
                                    aVar = new a((g0.c) obj, 1);
                                } else if (obj instanceof Animatable) {
                                    aVar = new a((Animatable) obj, 0);
                                }
                                aVar.g();
                                this.f765r = aVar;
                                this.f767t = i5;
                                this.f766s = i3;
                            }
                        }
                        z2 = false;
                    }
                }
                z2 = true;
            }
            if (z2 || c(i3)) {
                z5 = true;
            }
        }
        Drawable drawable = this.f800d;
        return drawable != null ? z5 | drawable.setState(iArr) : z5;
    }

    @Override // f.h, android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z2, boolean z3) {
        boolean visible = super.setVisible(z2, z3);
        b.a aVar = this.f765r;
        if (aVar != null && (visible || z3)) {
            if (z2) {
                aVar.g();
            } else {
                jumpToCurrentState();
            }
        }
        return visible;
    }
}
