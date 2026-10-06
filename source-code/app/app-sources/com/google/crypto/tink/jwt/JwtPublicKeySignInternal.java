package com.google.crypto.tink.jwt;

import com.google.errorprone.annotations.Immutable;
import java.util.Optional;

/* JADX INFO: loaded from: classes.dex */
@Immutable
public interface JwtPublicKeySignInternal {
    String signAndEncodeWithKid(RawJwt rawJwt, Optional<String> optional);
}
