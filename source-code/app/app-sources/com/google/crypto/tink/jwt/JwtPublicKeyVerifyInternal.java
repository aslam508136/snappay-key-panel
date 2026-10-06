package com.google.crypto.tink.jwt;

import com.google.errorprone.annotations.Immutable;
import java.util.Optional;

/* JADX INFO: loaded from: classes.dex */
@Immutable
public interface JwtPublicKeyVerifyInternal {
    VerifiedJwt verifyAndDecodeWithKid(String str, JwtValidator jwtValidator, Optional<String> optional);
}
