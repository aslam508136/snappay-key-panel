package i;

import android.view.CollapsibleActionView;
import android.view.View;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public final class t extends FrameLayout implements h.d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final CollapsibleActionView f1116b;

    /* JADX WARN: Multi-variable type inference failed */
    public t(View view) {
        super(view.getContext());
        this.f1116b = (CollapsibleActionView) view;
        addView(view);
    }

    @Override // h.d
    public final void b() {
        this.f1116b.onActionViewExpanded();
    }

    @Override // h.d
    public final void d() {
        this.f1116b.onActionViewCollapsed();
    }
}
