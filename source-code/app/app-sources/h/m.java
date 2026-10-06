package h;

import android.view.View;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Iterator;
import x.y;
import x.z;

/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Interpolator f953c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public z f954d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f955e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f952b = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final l f956f = new l(this);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f951a = new ArrayList();

    public final void a() {
        if (this.f955e) {
            Iterator it = this.f951a.iterator();
            while (it.hasNext()) {
                ((y) it.next()).b();
            }
            this.f955e = false;
        }
    }

    public final void b() {
        View view;
        if (this.f955e) {
            return;
        }
        for (y yVar : this.f951a) {
            long j2 = this.f952b;
            if (j2 >= 0) {
                yVar.c(j2);
            }
            Interpolator interpolator = this.f953c;
            if (interpolator != null && (view = (View) yVar.f2020a.get()) != null) {
                view.animate().setInterpolator(interpolator);
            }
            if (this.f954d != null) {
                yVar.d(this.f956f);
            }
            View view2 = (View) yVar.f2020a.get();
            if (view2 != null) {
                view2.animate().start();
            }
        }
        this.f955e = true;
    }
}
