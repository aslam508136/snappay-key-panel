package com.google.crypto.tink.mac;

import com.google.crypto.tink.annotations.Alpha;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Objects;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Alpha
public final class HmacParameters extends MacParameters {
    private final HashType hashType;
    private final int keySizeBytes;
    private final int tagSizeBytes;
    private final Variant variant;

    public static final class Builder {
        private HashType hashType;

        @Nullable
        private Integer keySizeBytes;

        @Nullable
        private Integer tagSizeBytes;
        private Variant variant;

        private Builder() {
            this.keySizeBytes = null;
            this.tagSizeBytes = null;
            this.hashType = null;
            this.variant = Variant.NO_PREFIX;
        }

        private static void validateTagSizeBytes(int i2, HashType hashType) throws GeneralSecurityException {
            if (i2 < 10) {
                throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; must be at least 10 bytes", Integer.valueOf(i2)));
            }
            if (hashType == HashType.SHA1) {
                if (i2 > 20) {
                    throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 20 bytes for SHA1", Integer.valueOf(i2)));
                }
                return;
            }
            if (hashType == HashType.SHA224) {
                if (i2 > 28) {
                    throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 28 bytes for SHA224", Integer.valueOf(i2)));
                }
                return;
            }
            if (hashType == HashType.SHA256) {
                if (i2 > 32) {
                    throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 32 bytes for SHA256", Integer.valueOf(i2)));
                }
            } else if (hashType == HashType.SHA384) {
                if (i2 > 48) {
                    throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 48 bytes for SHA384", Integer.valueOf(i2)));
                }
            } else {
                if (hashType != HashType.SHA512) {
                    throw new GeneralSecurityException("unknown hash type; must be SHA256, SHA384 or SHA512");
                }
                if (i2 > 64) {
                    throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 64 bytes for SHA512", Integer.valueOf(i2)));
                }
            }
        }

        public HmacParameters build() throws GeneralSecurityException {
            Integer num = this.keySizeBytes;
            if (num == null) {
                throw new GeneralSecurityException("key size is not set");
            }
            if (this.tagSizeBytes == null) {
                throw new GeneralSecurityException("tag size is not set");
            }
            if (this.hashType == null) {
                throw new GeneralSecurityException("hash type is not set");
            }
            if (this.variant == null) {
                throw new GeneralSecurityException("variant is not set");
            }
            if (num.intValue() < 16) {
                throw new InvalidAlgorithmParameterException(String.format("Invalid key size in bytes %d; must be at least 16 bytes", this.keySizeBytes));
            }
            validateTagSizeBytes(this.tagSizeBytes.intValue(), this.hashType);
            return new HmacParameters(this.keySizeBytes.intValue(), this.tagSizeBytes.intValue(), this.variant, this.hashType);
        }

        @CanIgnoreReturnValue
        public Builder setHashType(HashType hashType) {
            this.hashType = hashType;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setKeySizeBytes(int i2) {
            this.keySizeBytes = Integer.valueOf(i2);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setTagSizeBytes(int i2) {
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
    public static final class HashType {
        public static final HashType SHA1 = new HashType("SHA1");
        public static final HashType SHA224 = new HashType("SHA224");
        public static final HashType SHA256 = new HashType("SHA256");
        public static final HashType SHA384 = new HashType("SHA384");
        public static final HashType SHA512 = new HashType("SHA512");
        private final String name;

        private HashType(String str) {
            this.name = str;
        }

        public String toString() {
            return this.name;
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

    private HmacParameters(int i2, int i3, Variant variant, HashType hashType) {
        this.keySizeBytes = i2;
        this.tagSizeBytes = i3;
        this.variant = variant;
        this.hashType = hashType;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof HmacParameters)) {
            return false;
        }
        HmacParameters hmacParameters = (HmacParameters) obj;
        return hmacParameters.getKeySizeBytes() == getKeySizeBytes() && hmacParameters.getTotalTagSizeBytes() == getTotalTagSizeBytes() && hmacParameters.getVariant() == getVariant() && hmacParameters.getHashType() == getHashType();
    }

    public int getCryptographicTagSizeBytes() {
        return this.tagSizeBytes;
    }

    public HashType getHashType() {
        return this.hashType;
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
        return Objects.hash(Integer.valueOf(this.keySizeBytes), Integer.valueOf(this.tagSizeBytes), this.variant, this.hashType);
    }

    public String toString() {
        return "HMAC Parameters (variant: " + this.variant + ", hashType: " + this.hashType + ", " + this.tagSizeBytes + "-byte tags, and " + this.keySizeBytes + "-byte key)";
    }
}
