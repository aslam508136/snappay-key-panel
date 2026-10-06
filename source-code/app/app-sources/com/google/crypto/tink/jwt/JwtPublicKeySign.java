package com.google.crypto.tink.jwt;

import com.google.errorprone.annotations.Immutable;

/* JADX INFO: loaded from: classes.dex */
@Immutable
public interface JwtPublicKeySign {
    String signAndEncode(RawJwt rawJwt);
}
