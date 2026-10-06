package com.google.crypto.tink.aead;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Objects;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class AesEaxParameters extends AeadParameters {
    private final int ivSizeBytes;
    private final int keySizeBytes;
    private final int tagSizeBytes;
    private final Variant variant;

    public static final class Builder {

        @Nullable
        private Integer ivSizeBytes;

        @Nullable
        private Integer keySizeBytes;

        @Nullable
        private Integer tagSizeBytes;
        private Variant variant;

        private Builder() {
            this.keySizeBytes = null;
            this.ivSizeBytes = null;
            this.tagSizeBytes = null;
            this.variant = Variant.NO_PREFIX;
        }

        public AesEaxParameters build() throws GeneralSecurityException {
            Integer num = this.keySizeBytes;
            if (num == null) {
                throw new GeneralSecurityException("Key size is not set");
            }
            if (this.ivSizeBytes == null) {
                throw new GeneralSecurityException("IV size is not set");
            }
            if (this.variant == null) {
                throw new GeneralSecurityException("Variant is not set");
            }
            if (this.tagSizeBytes != null) {
                return new AesEaxParameters(num.intValue(), this.ivSizeBytes.intValue(), this.tagSizeBytes.intValue(), this.variant);
            }
            throw new GeneralSecurityException("Tag size is not set");
        }

        @CanIgnoreReturnValue
        public Builder setIvSizeBytes(int i2) throws GeneralSecurityException {
            if (i2 != 12 && i2 != 16) {
                throw new GeneralSecurityException(String.format("Invalid IV size in bytes %d; acceptable values have 12 or 16 bytes", Integer.valueOf(i2)));
            }
            this.ivSizeBytes = Integer.valueOf(i2);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setKeySizeBytes(int i2) throws InvalidAlgorithmParameterException {
            if (i2 != 16 && i2 != 24 && i2 != 32) {
                throw new InvalidAlgorithmParameterException(String.format("Invalid key size %d; only 16-byte, 24-byte and 32-byte AES keys are supported", Integer.valueOf(i2)));
            }
            this.keySizeBytes = Integer.valueOf(i2);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setTagSizeBytes(int i2) throws GeneralSecurityException {
            if (i2 < 0 || i2 > 16) {
                throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; value must be at most 16 bytes", Integer.valueOf(i2)));
            }
            this.tagSizeBytes = Integer.valueOf(i2);
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

    private AesEaxParameters(int i2, int i3, int i4, Variant variant) {
        this.keySizeBytes = i2;
        this.ivSizeBytes = i3;
        this.tagSizeBytes = i4;
        this.variant = variant;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AesEaxParameters)) {
            return false;
        }
        AesEaxParameters aesEaxParameters = (AesEaxParameters) obj;
        return aesEaxParameters.getKeySizeBytes() == getKeySizeBytes() && aesEaxParameters.getIvSizeBytes() == getIvSizeBytes() && aesEaxParameters.getTagSizeBytes() == getTagSizeBytes() && aesEaxParameters.getVariant() == getVariant();
    }

    public int getIvSizeBytes() {
        return this.ivSizeBytes;
    }

    public int getKeySizeBytes() {
        return this.keySizeBytes;
    }

    public int getTagSizeBytes() {
        return this.tagSizeBytes;
    }

    public Variant getVariant() {
        return this.variant;
    }

    @Override // com.google.crypto.tink.Parameters
    public boolean hasIdRequirement() {
        return this.variant != Variant.NO_PREFIX;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.keySizeBytes), Integer.valueOf(this.ivSizeBytes), Integer.valueOf(this.tagSizeBytes), this.variant);
    }

    public String toString() {
        return "AesEax Parameters (variant: " + this.variant + ", " + this.ivSizeBytes + "-byte IV, " + this.tagSizeBytes + "-byte tag, and " + this.keySizeBytes + "-byte key)";
    }
}
