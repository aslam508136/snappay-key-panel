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
class JwtMacWrapper implements PrimitiveWrapper<JwtMacInternal, JwtMac> {
    private static final JwtMacWrapper WRAPPER = new JwtMacWrapper();

    @Immutable
    public static class WrappedJwtMac implements JwtMac {
        private final PrimitiveSet<JwtMacInternal> primitives;

        private WrappedJwtMac(PrimitiveSet<JwtMacInternal> primitiveSet) {
            this.primitives = primitiveSet;
        }

        @Override // com.google.crypto.tink.jwt.JwtMac
        public String computeMacAndEncode(RawJwt rawJwt) throws JwtInvalidException {
            PrimitiveSet.Entry<JwtMacInternal> primary = this.primitives.getPrimary();
            return primary.getPrimitive().computeMacAndEncodeWithKid(rawJwt, JwtFormat.getKid(primary.getKeyId(), primary.getOutputPrefixType()));
        }

        @Override // com.google.crypto.tink.jwt.JwtMac
        public VerifiedJwt verifyMacAndDecode(String str, JwtValidator jwtValidator) throws GeneralSecurityException {
            Iterator<List<PrimitiveSet.Entry<JwtMacInternal>>> it = this.primitives.getAll().iterator();
            GeneralSecurityException generalSecurityException = null;
            while (it.hasNext()) {
                for (PrimitiveSet.Entry<JwtMacInternal> entry : it.next()) {
                    try {
                        return entry.getPrimitive().verifyMacAndDecodeWithKid(str, jwtValidator, JwtFormat.getKid(entry.getKeyId(), entry.getOutputPrefixType()));
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
            throw new GeneralSecurityException("invalid MAC");
        }
    }

    public static void register() {
        Registry.registerPrimitiveWrapper(WRAPPER);
    }

    private static void validate(PrimitiveSet<JwtMacInternal> primitiveSet) throws GeneralSecurityException {
        if (primitiveSet.getPrimary() == null) {
            throw new GeneralSecurityException("Primitive set has no primary.");
        }
        Iterator<List<PrimitiveSet.Entry<JwtMacInternal>>> it = primitiveSet.getAll().iterator();
        while (it.hasNext()) {
            for (PrimitiveSet.Entry<JwtMacInternal> entry : it.next()) {
                if (entry.getOutputPrefixType() != OutputPrefixType.RAW && entry.getOutputPrefixType() != OutputPrefixType.TINK) {
                    throw new GeneralSecurityException("unsupported OutputPrefixType");
                }
            }
        }
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<JwtMacInternal> getInputPrimitiveClass() {
        return JwtMacInternal.class;
    }

    @Override // com.google.crypto.tink.PrimitiveWrapper
    public Class<JwtMac> getPrimitiveClass() {
        return JwtMac.class;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.crypto.tink.PrimitiveWrapper
    public JwtMac wrap(PrimitiveSet<JwtMacInternal> primitiveSet) throws GeneralSecurityException {
        validate(primitiveSet);
        return new WrappedJwtMac(primitiveSet);
    }
}
