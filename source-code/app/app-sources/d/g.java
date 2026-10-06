package d;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class g extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final WeakReference f644a;

    public g(DialogInterface dialogInterface) {
        this.f644a = new WeakReference(dialogInterface);
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        int i2 = message.what;
        if (i2 == -3 || i2 == -2 || i2 == -1) {
            ((DialogInterface.OnClickListener) message.obj).onClick((DialogInterface) this.f644a.get(), message.what);
        } else {
            if (i2 != 1) {
                return;
            }
            ((DialogInterface) message.obj).dismiss();
        }
    }
}
