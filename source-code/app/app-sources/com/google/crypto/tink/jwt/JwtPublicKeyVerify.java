package com.google.crypto.tink.jwt;

import com.google.errorprone.annotations.Immutable;

/* JADX INFO: loaded from: classes.dex */
@Immutable
public interface JwtPublicKeyVerify {
    VerifiedJwt verifyAndDecode(String str, JwtValidator jwtValidator);
}
