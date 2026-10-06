package com.google.crypto.tink.jwt;

import com.google.errorprone.annotations.Immutable;

/* JADX INFO: loaded from: classes.dex */
@Immutable
public interface JwtMac {
    String computeMacAndEncode(RawJwt rawJwt);

    VerifiedJwt verifyMacAndDecode(String str, JwtValidator jwtValidator);
}
