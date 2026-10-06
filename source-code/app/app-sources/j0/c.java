package j0;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class c extends AbstractSet {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1506a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ f f1507b;

    public /* synthetic */ c(f fVar, int i2) {
        this.f1506a = i2;
        this.f1507b = fVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        int i2 = this.f1506a;
        f fVar = this.f1507b;
        switch (i2) {
            case 0:
                fVar.clear();
                break;
            default:
                fVar.clear();
                break;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        int i2 = this.f1506a;
        f fVar = this.f1507b;
        switch (i2) {
            case 0:
                return (obj instanceof Map.Entry) && fVar.b((Map.Entry) obj) != null;
            default:
                return fVar.containsKey(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        switch (this.f1506a) {
            case 0:
                return new b(this);
            default:
                return new b(this, 0);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        e eVarA;
        e eVarB;
        int i2 = this.f1506a;
        f fVar = this.f1507b;
        switch (i2) {
            case 0:
                if (!(obj instanceof Map.Entry) || (eVarB = fVar.b((Map.Entry) obj)) == null) {
                    return false;
                }
                fVar.d(eVarB, true);
                return true;
            default:
                fVar.getClass();
                if (obj != null) {
                    try {
                        eVarA = fVar.a(obj, false);
                    } catch (ClassCastException unused) {
                        eVarA = null;
                    }
                    break;
                } else {
                    eVarA = null;
                }
                if (eVarA != null) {
                    fVar.d(eVarA, true);
                }
                return eVarA != null;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        int i2 = this.f1506a;
        f fVar = this.f1507b;
        switch (i2) {
            case 0:
                break;
            default:
                break;
        }
        return fVar.f1523c;
    }
}
