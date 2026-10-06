package d;

import android.os.Message;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class b implements View.OnClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ i f604b;

    public b(i iVar) {
        this.f604b = iVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        Message messageObtain;
        Message message;
        i iVar = this.f604b;
        if (view != iVar.f659h || (message = iVar.f661j) == null) {
            if (view == iVar.f663l) {
                iVar.getClass();
            }
            if (view == iVar.f664m) {
                iVar.getClass();
            }
            messageObtain = null;
        } else {
            messageObtain = Message.obtain(message);
        }
        if (messageObtain != null) {
            messageObtain.sendToTarget();
        }
        iVar.B.obtainMessage(1, iVar.f653b).sendToTarget();
    }
}
