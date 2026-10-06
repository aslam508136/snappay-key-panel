package j;

import android.text.StaticLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class z0 extends y0 {
    @Override // j.y0, j.a1
    public void a(StaticLayout.Builder builder, TextView textView) {
        builder.setTextDirection(textView.getTextDirectionHeuristic());
    }

    @Override // j.a1
    public boolean b(TextView textView) {
        return textView.isHorizontallyScrollable();
    }
}
