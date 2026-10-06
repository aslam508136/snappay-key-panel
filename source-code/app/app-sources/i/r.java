package i;

import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ActionProvider f1113a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ w f1114b;

    public r(w wVar, ActionProvider actionProvider) {
        this.f1114b = wVar;
        this.f1113a = actionProvider;
    }

    public abstract /* bridge */ /* synthetic */ boolean a();

    public abstract View b(MenuItem menuItem);

    public abstract /* bridge */ /* synthetic */ boolean c();

    public abstract void d(h.a aVar);
}
