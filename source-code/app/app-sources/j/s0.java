package j;

import android.os.Handler;
import android.view.textclassifier.TextClassificationManager;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class s0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f1407a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f1408b;

    public s0(int i2, int i3) {
        this.f1407a = new int[]{i2, i3};
        this.f1408b = new float[]{0.0f, 1.0f};
    }

    public final androidx.lifecycle.u a(Class cls) {
        androidx.lifecycle.u aVar;
        String canonicalName = cls.getCanonicalName();
        if (canonicalName == null) {
            throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
        }
        String strConcat = "androidx.lifecycle.ViewModelProvider.DefaultKey:".concat(canonicalName);
        androidx.lifecycle.u uVar = (androidx.lifecycle.u) ((androidx.lifecycle.w) this.f1408b).f460a.get(strConcat);
        if (!cls.isInstance(uVar)) {
            switch (((o) ((androidx.lifecycle.v) this.f1407a)).f1338b) {
                case 1:
                    aVar = new androidx.fragment.app.t(true);
                    break;
                default:
                    aVar = new e0.a();
                    break;
            }
            uVar = aVar;
            androidx.lifecycle.u uVar2 = (androidx.lifecycle.u) ((androidx.lifecycle.w) this.f1408b).f460a.put(strConcat, uVar);
            if (uVar2 != null) {
                uVar2.a();
            }
        }
        return uVar;
    }

    public final TextClassifier b() {
        Object obj = this.f1408b;
        if (((TextClassifier) obj) != null) {
            return (TextClassifier) obj;
        }
        TextClassificationManager textClassificationManagerE = com.google.crypto.tink.a.e(((TextView) this.f1407a).getContext().getSystemService(com.google.crypto.tink.a.i()));
        return textClassificationManagerE != null ? textClassificationManagerE.getTextClassifier() : TextClassifier.NO_OP;
    }

    public final void c(u.f fVar) {
        int i2 = fVar.f1940b;
        int i3 = 0;
        boolean z2 = i2 == 0;
        Object obj = this.f1407a;
        if (z2) {
            ((Handler) this.f1408b).post(new u.a(this, (h.a) obj, fVar.f1939a, i3));
        } else {
            ((Handler) this.f1408b).post(new u.b((h.a) obj, i2));
        }
    }

    public s0(int i2, int i3, int i4) {
        this.f1407a = new int[]{i2, i3, i4};
        this.f1408b = new float[]{0.0f, 0.5f, 1.0f};
    }

    public s0(TextView textView) {
        textView.getClass();
        this.f1407a = textView;
    }

    public s0(androidx.lifecycle.w wVar, o oVar) {
        this.f1407a = oVar;
        this.f1408b = wVar;
    }

    public s0(h.a aVar, Handler handler) {
        this.f1407a = aVar;
        this.f1408b = handler;
    }

    public s0(ArrayList arrayList, ArrayList arrayList2) {
        int size = arrayList.size();
        this.f1407a = new int[size];
        this.f1408b = new float[size];
        for (int i2 = 0; i2 < size; i2++) {
            ((int[]) this.f1407a)[i2] = ((Integer) arrayList.get(i2)).intValue();
            ((float[]) this.f1408b)[i2] = ((Float) arrayList2.get(i2)).floatValue();
        }
    }
}
