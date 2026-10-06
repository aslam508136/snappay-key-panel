package y;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class a extends ClickableSpan {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f2021a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c f2022b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f2023c;

    public a(int i2, c cVar, int i3) {
        this.f2021a = i2;
        this.f2022b = cVar;
        this.f2023c = i3;
    }

    @Override // android.text.style.ClickableSpan
    public final void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", this.f2021a);
        this.f2022b.f2030a.performAction(this.f2023c, bundle);
    }
}
