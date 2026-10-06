package f;

import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public final class f implements Drawable.Callback {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f769b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object f770c;

    public f() {
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void invalidateDrawable(Drawable drawable) {
        switch (this.f769b) {
            case 0:
                break;
            default:
                ((g0.c) this.f770c).invalidateSelf();
                break;
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j2) {
        switch (this.f769b) {
            case 0:
                Drawable.Callback callback = (Drawable.Callback) this.f770c;
                if (callback != null) {
                    callback.scheduleDrawable(drawable, runnable, j2);
                }
                break;
            default:
                ((g0.c) this.f770c).scheduleSelf(runnable, j2);
                break;
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        switch (this.f769b) {
            case 0:
                Drawable.Callback callback = (Drawable.Callback) this.f770c;
                if (callback != null) {
                    callback.unscheduleDrawable(drawable, runnable);
                }
                break;
            default:
                ((g0.c) this.f770c).unscheduleSelf(runnable);
                break;
        }
    }

    public f(g0.c cVar) {
        this.f770c = cVar;
    }
}
