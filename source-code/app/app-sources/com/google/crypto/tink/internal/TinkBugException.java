package com.google.crypto.tink.internal;

/* JADX INFO: loaded from: classes.dex */
public final class TinkBugException extends RuntimeException {
    public TinkBugException(String str) {
        super(str);
    }

    public TinkBugException(String str, Throwable th) {
        super(str, th);
    }

    public TinkBugException(Throwable th) {
        super(th);
    }
}
