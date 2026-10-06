package m0;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.snapay.app.MainActivity;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class i implements View.OnClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f1668b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f1669c;

    public /* synthetic */ i(Object obj, int i2) {
        this.f1668b = i2;
        this.f1669c = obj;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        String str;
        int i2 = this.f1668b;
        Object obj = this.f1669c;
        switch (i2) {
            case 0:
                MainActivity mainActivity = (MainActivity) obj;
                int i3 = MainActivity.s0;
                mainActivity.getClass();
                a aVarF = a.f(mainActivity);
                ((SharedPreferences) aVarF.f1643b).edit().remove("logs").apply();
                LocalBroadcastManager.getInstance((Context) aVarF.f1642a).sendBroadcast(new Intent("com.snapay.app.LOG_UPDATED"));
                mainActivity.q();
                break;
            case 1:
                MainActivity mainActivity2 = (MainActivity) obj;
                int i4 = MainActivity.s0;
                mainActivity2.getClass();
                ArrayList arrayListO = a.f(mainActivity2).o();
                if (arrayListO.isEmpty()) {
                    str = "No logs to copy";
                } else {
                    StringBuilder sb = new StringBuilder();
                    Iterator it = arrayListO.iterator();
                    while (it.hasNext()) {
                        sb.append((String) it.next());
                        sb.append("\n");
                    }
                    ((ClipboardManager) mainActivity2.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("SnapPay Logs", sb.toString().trim()));
                    str = "Logs copied";
                }
                Toast.makeText(mainActivity2, str, 0).show();
                break;
            default:
                d dVar = (d) obj;
                ((Handler) dVar.f1653c).post(new c(dVar, 1));
                break;
        }
    }
}
