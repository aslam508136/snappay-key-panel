package j;

import androidx.appcompat.widget.ActionBarOverlayLayout;

/* JADX INFO: loaded from: classes.dex */
public final class e implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1227a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ActionBarOverlayLayout f1228b;

    public /* synthetic */ e(ActionBarOverlayLayout actionBarOverlayLayout, int i2) {
        this.f1227a = i2;
        this.f1228b = actionBarOverlayLayout;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i2 = this.f1227a;
        ActionBarOverlayLayout actionBarOverlayLayout = this.f1228b;
        switch (i2) {
            case 0:
                actionBarOverlayLayout.h();
                actionBarOverlayLayout.f163x = actionBarOverlayLayout.f144e.animate().translationY(0.0f).setListener(actionBarOverlayLayout.f164y);
                break;
            default:
                actionBarOverlayLayout.h();
                actionBarOverlayLayout.f163x = actionBarOverlayLayout.f144e.animate().translationY(-actionBarOverlayLayout.f144e.getHeight()).setListener(actionBarOverlayLayout.f164y);
                break;
        }
    }
}
