package h;

import android.view.View;
import j.a3;
import x.z;

/* JADX INFO: loaded from: classes.dex */
public final class l extends b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f947a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f948b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f949c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f950d;

    public l(m mVar) {
        this.f947a = 0;
        this.f950d = mVar;
        this.f948b = false;
        this.f949c = 0;
    }

    @Override // x.z
    public final void a() {
        int i2 = this.f947a;
        Object obj = this.f950d;
        switch (i2) {
            case 0:
                int i3 = this.f949c + 1;
                this.f949c = i3;
                m mVar = (m) obj;
                if (i3 == mVar.f951a.size()) {
                    z zVar = mVar.f954d;
                    if (zVar != null) {
                        zVar.a();
                    }
                    this.f949c = 0;
                    this.f948b = false;
                    mVar.f955e = false;
                }
                break;
            default:
                if (!this.f948b) {
                    ((a3) obj).f1157a.setVisibility(this.f949c);
                }
                break;
        }
    }

    @Override // b.a, x.z
    public final void b(View view) {
        switch (this.f947a) {
            case 1:
                this.f948b = true;
                break;
        }
    }

    @Override // b.a, x.z
    public final void c() {
        int i2 = this.f947a;
        Object obj = this.f950d;
        switch (i2) {
            case 0:
                if (!this.f948b) {
                    this.f948b = true;
                    z zVar = ((m) obj).f954d;
                    if (zVar != null) {
                        zVar.c();
                    }
                    break;
                }
                break;
            default:
                ((a3) obj).f1157a.setVisibility(0);
                break;
        }
    }

    public l(a3 a3Var, int i2) {
        this.f947a = 1;
        this.f950d = a3Var;
        this.f949c = i2;
        this.f948b = false;
    }
}
