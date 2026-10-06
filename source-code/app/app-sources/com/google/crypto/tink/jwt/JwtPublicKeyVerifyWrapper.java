package com.google.crypto.tink.jwt;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class JwtPublicKeyVerifyWrapper implements PrimitiveWrapper<JwtPublicKeyVerifyInternal, JwtPublicKeyVerify> {
    private static final JwtPublicKeyVerifyWrapper WRAPPER = new JwtPublicKeyVerifyWrapper();

    @Immutable
    public static class WrappedJwtPublicKeyVerify implements JwtPublicKeyVerify {
        private final PrimitiveSet<JwtPublicKeyVerifyInternal> primitives;

        public WrappedJwtPublicKeyVerify(PrimitiveSet<JwtPublicKeyVerifyInternal> primitiveSet) {
            this.primitives = primitiveSet;
        }

        @Override // com.google.crypto.tink.jwt.JwtPublicKeyVerify
        public VerifiedJwt verifyAndDecode(String str, JwtValidator jwtValidator) throws GeneralSecurityException {
            Iterator<List<PrimitiveSet.Entry<JwtPublicKeyVerifyInternal>>> it = this.primitives.getAll().iterator();
            GeneralSecurityException generalSecurityException = null;
            while (it.hasNext()) {
                for (PrimitiveSet.Entry<JwtPublicKeyVerifyInternal> entry : it.next()) {
                    try {
                        return entry.getPrimitive().verifyAndDecodeWithKid(str, jwtValidator, JwtFormat.getKid(entry.getKeyId(), entry.getOutputPrefixType()));
                    } catch (GeneralSecurityException e2) {
                        if (e2 instanceof JwtInvalidException) {
                            generalSecurityException = e2;
                        }
                    }
                }
            }
            if (generalSecurityException != null) {
                throw generalSecurityException;
            }
            throw new GeneralSecurityException("invalid JWT");
        }
    }

    public static void register() {
        Registry.registerPrimitiveWrapper(WRAPPER);
    }

    private static void validate(PrimitiveSet<JwtPublicKeyVerifyInternal> primitiveSet) throws GeneralSecurityException {
        Iterator<List<PrimitiveSet.Entry<JwtPublicKeyVerifyInternal>>> it = primitiveSet.getAll().iterator();
        while (it.hasNext()) {
            for (PrimitiveSet.Entry<JwtPublicKeyVerifyInternal> entry : it.next()) {
                if (entry.getOutputPrefixType() != OutputPrefixType.RAW && entry.getOutputPrefixType() != OutputPrefixType.TINK) {
                    throw new GeneralSecurityException("unsupported OutputPrefixType");
                }
            }
        }
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<JwtPublicKeyVerifyInternal> getInputPrimitiveClass() {
        return JwtPublicKeyVerifyInternal.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<JwtPublicKeyVerify> getPrimitiveClass() {
        return JwtPublicKeyVerify.class;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public JwtPublicKeyVerify wrap(PrimitiveSet<JwtPublicKeyVerifyInternal> primitiveSet) throws GeneralSecurityException {
        validate(primitiveSet);
        return new WrappedJwtPublicKeyVerify(primitiveSet);
    }
}
