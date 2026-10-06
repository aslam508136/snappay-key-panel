package d;

import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import j.o0;
import j.r0;

/* JADX INFO: loaded from: classes.dex */
public final class e implements AdapterView.OnItemClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f621b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f622c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f623d;

    public /* synthetic */ e(Object obj, Object obj2, int i2) {
        this.f621b = i2;
        this.f623d = obj;
        this.f622c = obj2;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
        int i3 = this.f621b;
        Object obj = this.f623d;
        switch (i3) {
            case 0:
                f fVar = (f) obj;
                DialogInterface.OnClickListener onClickListener = fVar.f639l;
                i iVar = (i) this.f622c;
                onClickListener.onClick(iVar.f653b, i2);
                if (!fVar.f640m) {
                    iVar.f653b.dismiss();
                }
                break;
            default:
                o0 o0Var = (o0) obj;
                o0Var.H.setSelection(i2);
                r0 r0Var = o0Var.H;
                if (r0Var.getOnItemClickListener() != null) {
                    r0Var.performItemClick(view, i2, o0Var.E.getItemId(i2));
                }
                o0Var.dismiss();
                break;
        }
    }
}
