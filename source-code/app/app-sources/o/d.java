package o;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import androidx.lifecycle.h;
import androidx.lifecycle.i;
import androidx.lifecycle.l;
import androidx.lifecycle.n;
import androidx.lifecycle.s;
import x.e;

/* JADX INFO: loaded from: classes.dex */
public abstract class d extends Activity implements l, e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final n f1746b = new n(this);

    @Override // x.e
    public final boolean d(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !i.u(decorView, keyEvent)) {
            return i.v(this, decorView, this, keyEvent);
        }
        return true;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        View decorView = getWindow().getDecorView();
        if (decorView == null || !i.u(decorView, keyEvent)) {
            return super.dispatchKeyShortcutEvent(keyEvent);
        }
        return true;
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        s.c(this);
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        n nVar = this.f1746b;
        nVar.n0("markState");
        h hVar = h.CREATED;
        nVar.n0("setCurrentState");
        nVar.p0(hVar);
        super.onSaveInstanceState(bundle);
    }
}
