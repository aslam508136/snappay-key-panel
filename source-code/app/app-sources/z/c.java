package z;

import android.content.ClipDescription;
import android.net.Uri;
import android.view.inputmethod.InputContentInfo;

/* JADX INFO: loaded from: classes.dex */
public final class c implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InputContentInfo f2033a;

    public c(Uri uri, ClipDescription clipDescription, Uri uri2) {
        this.f2033a = new InputContentInfo(uri, clipDescription, uri2);
    }

    @Override // z.d
    public final void a() {
        this.f2033a.requestPermission();
    }

    @Override // z.d
    public final Uri b() {
        return this.f2033a.getLinkUri();
    }

    @Override // z.d
    public final ClipDescription c() {
        return this.f2033a.getDescription();
    }

    @Override // z.d
    public final Object d() {
        return this.f2033a;
    }

    @Override // z.d
    public final Uri e() {
        return this.f2033a.getContentUri();
    }

    public c(Object obj) {
        this.f2033a = (InputContentInfo) obj;
    }
}
