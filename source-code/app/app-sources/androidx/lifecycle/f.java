package androidx.lifecycle;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f421a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int[] f422b;

    static {
        int[] iArr = new int[g.values().length];
        f422b = iArr;
        try {
            iArr[g.ON_CREATE.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f422b[g.ON_STOP.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            f422b[g.ON_START.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            f422b[g.ON_PAUSE.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            f422b[g.ON_RESUME.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            f422b[g.ON_DESTROY.ordinal()] = 6;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            f422b[g.ON_ANY.ordinal()] = 7;
        } catch (NoSuchFieldError unused7) {
        }
        int[] iArr2 = new int[h.values().length];
        f421a = iArr2;
        try {
            iArr2[2] = 1;
        } catch (NoSuchFieldError unused8) {
        }
        try {
            f421a[3] = 2;
        } catch (NoSuchFieldError unused9) {
        }
        try {
            f421a[4] = 3;
        } catch (NoSuchFieldError unused10) {
        }
        try {
            f421a[0] = 4;
        } catch (NoSuchFieldError unused11) {
        }
        try {
            f421a[1] = 5;
        } catch (NoSuchFieldError unused12) {
        }
    }
}
