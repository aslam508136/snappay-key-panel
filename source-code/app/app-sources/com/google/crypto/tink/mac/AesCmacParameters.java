package com.google.crypto.tink.mac;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Objects;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class AesCmacParameters extends MacParameters {
    private final int keySizeBytes;
    private final int tagSizeBytes;
    private final Variant variant;

    public static final class Builder {

        @Nullable
        private Integer keySizeBytes;

        @Nullable
        private Integer tagSizeBytes;
        private Variant variant;

        private Builder() {
            this.keySizeBytes = null;
            this.tagSizeBytes = null;
            this.variant = Variant.NO_PREFIX;
        }

        public AesCmacParameters build() throws GeneralSecurityException {
            Integer num = this.keySizeBytes;
            if (num == null) {
                throw new GeneralSecurityException("key size not set");
            }
            if (this.tagSizeBytes == null) {
                throw new GeneralSecurityException("tag size not set");
            }
            if (this.variant != null) {
                return new AesCmacParameters(num.intValue(), this.tagSizeBytes.intValue(), this.variant);
            }
            throw new GeneralSecurityException("variant not set");
        }

        @CanIgnoreReturnValue
        public Builder setKeySizeBytes(int i2) throws InvalidAlgorithmParameterException {
            if (i2 != 16 && i2 != 32) {
                throw new InvalidAlgorithmParameterException(String.format("Invalid key size %d; only 128-bit and 256-bit AES keys are supported", Integer.valueOf(i2 * 8)));
            }
            this.keySizeBytes = Integer.valueOf(i2);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setTagSizeBytes(int i2) throws GeneralSecurityException {
            if (i2 >= 10 && 16 >= i2) {
                this.tagSizeBytes = Integer.valueOf(i2);
                return this;
            }
            throw new GeneralSecurityException("Invalid tag size for AesCmacParameters: " + i2);
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
        public static final Variant LEGACY = new Variant("LEGACY");
        public static final Variant NO_PREFIX = new Variant("NO_PREFIX");

        private Variant(String str) {
            this.name = str;
        }

        public String toString() {
            return this.name;
        }
    }

    private AesCmacParameters(int i2, int i3, Variant variant) {
        this.keySizeBytes = i2;
        this.tagSizeBytes = i3;
        this.variant = variant;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AesCmacParameters)) {
            return false;
        }
        AesCmacParameters aesCmacParameters = (AesCmacParameters) obj;
        return aesCmacParameters.getKeySizeBytes() == getKeySizeBytes() && aesCmacParameters.getTotalTagSizeBytes() == getTotalTagSizeBytes() && aesCmacParameters.getVariant() == getVariant();
    }

    public int getCryptographicTagSizeBytes() {
        return this.tagSizeBytes;
    }

    public int getKeySizeBytes() {
        return this.keySizeBytes;
    }

    public int getTotalTagSizeBytes() {
        Variant variant = this.variant;
        if (variant == Variant.NO_PREFIX) {
            return getCryptographicTagSizeBytes();
        }
        if (variant == Variant.TINK || variant == Variant.CRUNCHY || variant == Variant.LEGACY) {
            return getCryptographicTagSizeBytes() + 5;
        }
        throw new IllegalStateException("Unknown variant");
    }

    public Variant getVariant() {
        return this.variant;
    }

    @Override // com.google.crypto.tink.Parameters
    public boolean hasIdRequirement() {
        return this.variant != Variant.NO_PREFIX;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.keySizeBytes), Integer.valueOf(this.tagSizeBytes), this.variant);
    }

    public String toString() {
        return "AES-CMAC Parameters (variant: " + this.variant + ", " + this.tagSizeBytes + "-byte tags, and " + this.keySizeBytes + "-byte key)";
    }
}
