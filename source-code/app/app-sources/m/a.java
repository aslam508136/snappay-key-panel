package m;

/* JADX INFO: loaded from: classes.dex */
public final class a extends i.d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f1594d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f1595e;

    public /* synthetic */ a(Object obj, int i2) {
        this.f1594d = i2;
        this.f1595e = obj;
    }

    @Override // i.d
    public final void c() {
        int i2 = this.f1594d;
        Object obj = this.f1595e;
        switch (i2) {
            case 0:
                ((b) obj).clear();
                break;
            default:
                ((c) obj).clear();
                break;
        }
    }

    @Override // i.d
    public final Object d(int i2, int i3) {
        int i4 = this.f1594d;
        Object obj = this.f1595e;
        switch (i4) {
            case 0:
                return ((b) obj).f1634b[(i2 << 1) + i3];
            default:
                return ((c) obj).f1604b[i2];
        }
    }

    @Override // i.d
    public final b e() {
        switch (this.f1594d) {
            case 0:
                return (b) this.f1595e;
            default:
                throw new UnsupportedOperationException("not a map");
        }
    }

    @Override // i.d
    public final int f() {
        int i2 = this.f1594d;
        Object obj = this.f1595e;
        switch (i2) {
            case 0:
                return ((b) obj).f1635c;
            default:
                return ((c) obj).f1605c;
        }
    }

    @Override // i.d
    public final int g(Object obj) {
        int i2 = this.f1594d;
        Object obj2 = this.f1595e;
        switch (i2) {
            case 0:
                return ((b) obj2).e(obj);
            default:
                return ((c) obj2).indexOf(obj);
        }
    }

    @Override // i.d
    public final int h(Object obj) {
        int i2 = this.f1594d;
        Object obj2 = this.f1595e;
        switch (i2) {
            case 0:
                return ((b) obj2).g(obj);
            default:
                return ((c) obj2).indexOf(obj);
        }
    }

    @Override // i.d
    public final void i(Object obj, Object obj2) {
        int i2 = this.f1594d;
        Object obj3 = this.f1595e;
        switch (i2) {
            case 0:
                ((b) obj3).put(obj, obj2);
                break;
            default:
                ((c) obj3).add(obj);
                break;
        }
    }

    @Override // i.d
    public final void j(int i2) {
        int i3 = this.f1594d;
        Object obj = this.f1595e;
        switch (i3) {
            case 0:
                ((b) obj).i(i2);
                break;
            default:
                ((c) obj).e(i2);
                break;
        }
    }

    @Override // i.d
    public final Object k(int i2, Object obj) {
        switch (this.f1594d) {
            case 0:
                int i3 = (i2 << 1) + 1;
                Object[] objArr = ((b) this.f1595e).f1634b;
                Object obj2 = objArr[i3];
                objArr[i3] = obj;
                return obj2;
            default:
                throw new UnsupportedOperationException("not a map");
        }
    }
}
