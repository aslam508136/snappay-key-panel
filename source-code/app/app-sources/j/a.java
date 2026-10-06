package j;

import android.view.View;
import androidx.appcompat.widget.ActionBarContextView;

/* JADX INFO: loaded from: classes.dex */
public final class a implements x.z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f1142a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1143b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ ActionBarContextView f1144c;

    public a(ActionBarContextView actionBarContextView) {
        this.f1144c = actionBarContextView;
    }

    @Override // x.z
    public final void a() {
        if (this.f1142a) {
            return;
        }
        ActionBarContextView actionBarContextView = this.f1144c;
        actionBarContextView.f126g = null;
        super/*android.view.ViewGroup*/.setVisibility(this.f1143b);
    }

    @Override // x.z
    public final void b(View view) {
        this.f1142a = true;
    }

    @Override // x.z
    public final void c() {
        super/*android.view.ViewGroup*/.setVisibility(0);
        this.f1142a = false;
    }
}
