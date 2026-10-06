package d;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Message;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.appcompat.app.AlertController$RecycleListView;

/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f704a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f705b;

    public j(int i2, u.h[] hVarArr) {
        this.f704a = i2;
        this.f705b = hVarArr;
    }

    public final k a() {
        f fVar = (f) this.f705b;
        k kVar = new k(fVar.f628a, this.f704a);
        View view = fVar.f632e;
        i iVar = kVar.f708d;
        int i2 = 0;
        if (view != null) {
            iVar.f671t = view;
        } else {
            CharSequence charSequence = fVar.f631d;
            if (charSequence != null) {
                iVar.f656e = charSequence;
                TextView textView = iVar.f669r;
                if (textView != null) {
                    textView.setText(charSequence);
                }
            }
            Drawable drawable = fVar.f630c;
            if (drawable != null) {
                iVar.f667p = drawable;
                iVar.f666o = 0;
                ImageView imageView = iVar.f668q;
                if (imageView != null) {
                    imageView.setVisibility(0);
                    iVar.f668q.setImageDrawable(drawable);
                }
            }
        }
        CharSequence charSequence2 = fVar.f633f;
        if (charSequence2 != null) {
            iVar.f657f = charSequence2;
            TextView textView2 = iVar.f670s;
            if (textView2 != null) {
                textView2.setText(charSequence2);
            }
        }
        CharSequence charSequence3 = fVar.f634g;
        if (charSequence3 != null) {
            DialogInterface.OnClickListener onClickListener = fVar.f635h;
            Message messageObtainMessage = onClickListener != null ? iVar.B.obtainMessage(-1, onClickListener) : null;
            iVar.f660i = charSequence3;
            iVar.f661j = messageObtainMessage;
            iVar.f662k = null;
        }
        if (fVar.f638k != null) {
            AlertController$RecycleListView alertController$RecycleListView = (AlertController$RecycleListView) fVar.f629b.inflate(iVar.f675x, (ViewGroup) null);
            int i3 = fVar.f640m ? iVar.f676y : iVar.f677z;
            ListAdapter hVar = fVar.f638k;
            if (hVar == null) {
                hVar = new h(fVar.f628a, i3);
            }
            iVar.f672u = hVar;
            iVar.f673v = fVar.f641n;
            if (fVar.f639l != null) {
                alertController$RecycleListView.setOnItemClickListener(new e(fVar, iVar, i2));
            }
            if (fVar.f640m) {
                alertController$RecycleListView.setChoiceMode(1);
            }
            iVar.f658g = alertController$RecycleListView;
        }
        kVar.setCancelable(fVar.f636i);
        if (fVar.f636i) {
            kVar.setCanceledOnTouchOutside(true);
        }
        fVar.getClass();
        kVar.setOnCancelListener(null);
        fVar.getClass();
        kVar.setOnDismissListener(null);
        DialogInterface.OnKeyListener onKeyListener = fVar.f637j;
        if (onKeyListener != null) {
            kVar.setOnKeyListener(onKeyListener);
        }
        return kVar;
    }

    public j(Context context) {
        int iM = k.m(context, 0);
        this.f705b = new f(new ContextThemeWrapper(context, k.m(context, iM)));
        this.f704a = iM;
    }
}
