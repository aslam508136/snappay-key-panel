package androidx.fragment.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class m implements LayoutInflater.Factory2 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final r f337b;

    public m(r rVar) {
        this.f337b = rVar;
    }

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        boolean zIsAssignableFrom;
        boolean zEquals = k.class.getName().equals(str);
        r rVar = this.f337b;
        if (zEquals) {
            return new k(context, attributeSet, rVar);
        }
        if (!"fragment".equals(str)) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue(null, "class");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, d0.a.f748a);
        if (attributeValue == null) {
            attributeValue = typedArrayObtainStyledAttributes.getString(0);
        }
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(1, -1);
        String string = typedArrayObtainStyledAttributes.getString(2);
        typedArrayObtainStyledAttributes.recycle();
        if (attributeValue != null) {
            try {
                zIsAssignableFrom = d.class.isAssignableFrom(p.b(context.getClassLoader(), attributeValue));
            } catch (ClassNotFoundException unused) {
                zIsAssignableFrom = false;
            }
            if (zIsAssignableFrom) {
                int id = view != null ? view.getId() : 0;
                if (id == -1 && resourceId == -1 && string == null) {
                    throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + attributeValue);
                }
                if (resourceId != -1) {
                    rVar.g();
                }
                if (string != null) {
                    w wVar = rVar.f349c;
                    ArrayList arrayList = wVar.f398a;
                    int size = arrayList.size();
                    while (true) {
                        size--;
                        if (size < 0) {
                            break;
                        }
                        androidx.activity.c.b(arrayList.get(size));
                    }
                    Iterator it = wVar.f399b.values().iterator();
                    while (it.hasNext()) {
                        androidx.activity.c.b(it.next());
                    }
                }
                if (id != -1) {
                    rVar.g();
                }
                p pVar = rVar.f360n;
                context.getClassLoader();
                pVar.a(attributeValue);
                throw null;
            }
        }
        return null;
    }

    @Override // android.view.LayoutInflater.Factory
    public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }
}
