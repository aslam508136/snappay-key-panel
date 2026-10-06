package j;

import android.R;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class p2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextView f1356a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final TextView f1357b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ImageView f1358c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ImageView f1359d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ImageView f1360e;

    public p2(View view) {
        this.f1356a = (TextView) view.findViewById(R.id.text1);
        this.f1357b = (TextView) view.findViewById(R.id.text2);
        this.f1358c = (ImageView) view.findViewById(R.id.icon1);
        this.f1359d = (ImageView) view.findViewById(R.id.icon2);
        this.f1360e = (ImageView) view.findViewById(com.snapay.app.R.id.edit_query);
    }
}
