package z;

import android.os.Build;
import android.os.Bundle;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.view.inputmethod.InputContentInfo;

/* JADX INFO: loaded from: classes.dex */
public final class a extends InputConnectionWrapper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ h.a f2031a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(InputConnection inputConnection, h.a aVar) {
        super(inputConnection, false);
        this.f2031a = aVar;
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public final boolean commitContent(InputContentInfo inputContentInfo, int i2, Bundle bundle) {
        h.a aVar = null;
        if (inputContentInfo != null && Build.VERSION.SDK_INT >= 25) {
            aVar = new h.a(new c(inputContentInfo), 11);
        }
        if (this.f2031a.m(aVar, i2, bundle)) {
            return true;
        }
        return super.commitContent(inputContentInfo, i2, bundle);
    }
}
