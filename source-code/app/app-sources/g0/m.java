package g0;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public final class m extends Drawable.ConstantState {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f869a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public l f870b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ColorStateList f871c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public PorterDuff.Mode f872d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f873e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Bitmap f874f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ColorStateList f875g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public PorterDuff.Mode f876h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f877i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f878j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f879k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Paint f880l;

    public m() {
        this.f871c = null;
        this.f872d = o.f882k;
        this.f870b = new l();
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public int getChangingConfigurations() {
        return this.f869a;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable() {
        return new o(this);
    }

    public m(m mVar) {
        this.f871c = null;
        this.f872d = o.f882k;
        if (mVar != null) {
            this.f869a = mVar.f869a;
            l lVar = new l(mVar.f870b);
            this.f870b = lVar;
            if (mVar.f870b.f858e != null) {
                lVar.f858e = new Paint(mVar.f870b.f858e);
            }
            if (mVar.f870b.f857d != null) {
                this.f870b.f857d = new Paint(mVar.f870b.f857d);
            }
            this.f871c = mVar.f871c;
            this.f872d = mVar.f872d;
            this.f873e = mVar.f873e;
        }
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable(Resources resources) {
        return new o(this);
    }
}
