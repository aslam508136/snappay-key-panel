package h;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.LayoutInflater;
import com.snapay.app.R;

/* JADX INFO: loaded from: classes.dex */
public final class e extends ContextWrapper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f895a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Resources.Theme f896b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public LayoutInflater f897c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Configuration f898d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Resources f899e;

    public e(Context context, int i2) {
        super(context);
        this.f895a = i2;
    }

    public final void a(Configuration configuration) {
        if (this.f899e != null) {
            throw new IllegalStateException("getResources() or getAssets() has already been called");
        }
        if (this.f898d != null) {
            throw new IllegalStateException("Override configuration has already been set");
        }
        this.f898d = new Configuration(configuration);
    }

    @Override // android.content.ContextWrapper
    public final void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    public final void b() {
        if (this.f896b == null) {
            this.f896b = getResources().newTheme();
            Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.f896b.setTo(theme);
            }
        }
        this.f896b.applyStyle(this.f895a, true);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final AssetManager getAssets() {
        return getResources().getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final Resources getResources() {
        if (this.f899e == null) {
            Configuration configuration = this.f898d;
            this.f899e = configuration == null ? super.getResources() : createConfigurationContext(configuration).getResources();
        }
        return this.f899e;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final Object getSystemService(String str) {
        if (!"layout_inflater".equals(str)) {
            return getBaseContext().getSystemService(str);
        }
        if (this.f897c == null) {
            this.f897c = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.f897c;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final Resources.Theme getTheme() {
        Resources.Theme theme = this.f896b;
        if (theme != null) {
            return theme;
        }
        if (this.f895a == 0) {
            this.f895a = R.style.Theme_AppCompat_Light;
        }
        b();
        return this.f896b;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final void setTheme(int i2) {
        if (this.f895a != i2) {
            this.f895a = i2;
            b();
        }
    }
}
