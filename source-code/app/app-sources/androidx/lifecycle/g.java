package androidx.lifecycle;

/* JADX INFO: loaded from: classes.dex */
public enum g {
    ON_CREATE,
    ON_START,
    ON_RESUME,
    ON_PAUSE,
    ON_STOP,
    ON_DESTROY,
    ON_ANY;

    public final h a() {
        switch (f.f422b[ordinal()]) {
            case 1:
            case 2:
                return h.CREATED;
            case 3:
            case 4:
                return h.STARTED;
            case 5:
                return h.RESUMED;
            case 6:
                return h.DESTROYED;
            default:
                throw new IllegalArgumentException(this + " has no target state");
        }
    }
}
