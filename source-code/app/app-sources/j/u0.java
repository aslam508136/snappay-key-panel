package j;

import android.graphics.Typeface;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class u0 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ TextView f1449a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Typeface f1450b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f1451c;

    public u0(TextView textView, Typeface typeface, int i2) {
        this.f1449a = textView;
        this.f1450b = typeface;
        this.f1451c = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f1449a.setTypeface(this.f1450b, this.f1451c);
    }
}
