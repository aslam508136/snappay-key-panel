package com.google.crypto.tink;

import com.google.crypto.tink.annotations.Alpha;
import com.google.errorprone.annotations.Immutable;

/* JADX INFO: loaded from: classes.dex */
@Immutable
@Alpha
public final class KeyStatus {
    private final String name;
    public static final KeyStatus ENABLED = new KeyStatus("ENABLED");
    public static final KeyStatus DISABLED = new KeyStatus("DISABLED");
    public static final KeyStatus DESTROYED = new KeyStatus("DESTROYED");

    private KeyStatus(String str) {
        this.name = str;
    }

    public String toString() {
        return this.name;
    }
}
