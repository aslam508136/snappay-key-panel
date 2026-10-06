package com.google.crypto.tink.aead;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Objects;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class AesGcmSivParameters extends AeadParameters {
    private final int keySizeBytes;
    private final Variant variant;

    public static final class Builder {

        @Nullable
        private Integer keySizeBytes;
        private Variant variant;

        private Builder() {
            this.keySizeBytes = null;
            this.variant = Variant.NO_PREFIX;
        }

        public AesGcmSivParameters build() throws GeneralSecurityException {
            Integer num = this.keySizeBytes;
            if (num == null) {
                throw new GeneralSecurityException("Key size is not set");
            }
            if (this.variant != null) {
                return new AesGcmSivParameters(num.intValue(), this.variant);
            }
            throw new GeneralSecurityException("Variant is not set");
        }

        @CanIgnoreReturnValue
        public Builder setKeySizeBytes(int i2) throws InvalidAlgorithmParameterException {
            if (i2 != 16 && i2 != 32) {
                throw new InvalidAlgorithmParameterException(String.format("Invalid key size %d; only 16-byte and 32-byte AES keys are supported", Integer.valueOf(i2)));
            }
            this.keySizeBytes = Integer.valueOf(i2);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setVariant(Variant variant) {
            this.variant = variant;
            return this;
        }
    }

    @Immutable
    public static final class Variant {
        private final String name;
        public static final Variant TINK = new Variant("TINK");
        public static final Variant CRUNCHY = new Variant("CRUNCHY");
        public static final Variant NO_PREFIX = new Variant("NO_PREFIX");

        private Variant(String str) {
            this.name = str;
        }

        public String toString() {
            return this.name;
        }
    }

    private AesGcmSivParameters(int i2, Variant variant) {
        this.keySizeBytes = i2;
        this.variant = variant;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AesGcmSivParameters)) {
            return false;
        }
        AesGcmSivParameters aesGcmSivParameters = (AesGcmSivParameters) obj;
        return aesGcmSivParameters.getKeySizeBytes() == getKeySizeBytes() && aesGcmSivParameters.getVariant() == getVariant();
    }

    public int getKeySizeBytes() {
        return this.keySizeBytes;
    }

    public Variant getVariant() {
        return this.variant;
    }

    @Override // com.google.crypto.tink.Parameters
    public boolean hasIdRequirement() {
        return this.variant != Variant.NO_PREFIX;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.keySizeBytes), this.variant);
    }

    public String toString() {
        return "AesGcmSiv Parameters (variant: " + this.variant + ", " + this.keySizeBytes + "-byte key)";
    }
}
