package x;

import android.view.View;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final WeakReference f2020a;

    public y(View view) {
        this.f2020a = new WeakReference(view);
    }

    public final void a(float f2) {
        View view = (View) this.f2020a.get();
        if (view != null) {
            view.animate().alpha(f2);
        }
    }

    public final void b() {
        View view = (View) this.f2020a.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    public final void c(long j2) {
        View view = (View) this.f2020a.get();
        if (view != null) {
            view.animate().setDuration(j2);
        }
    }

    public final void d(z zVar) {
        View view = (View) this.f2020a.get();
        if (view != null) {
            if (zVar != null) {
                view.animate().setListener(new w(zVar, view));
            } else {
                view.animate().setListener(null);
            }
        }
    }

    public final void e(float f2) {
        View view = (View) this.f2020a.get();
        if (view != null) {
            view.animate().translationY(f2);
        }
    }
}
