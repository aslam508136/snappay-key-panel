package com.google.crypto.tink.jwt;

import androidx.activity.c;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import i0.a;
import i0.b;
import i0.d;
import i0.e;
import i0.f;
import i0.g;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
@Immutable
public final class RawJwt {
    private static final long MAX_TIMESTAMP_VALUE = 253402300799L;
    private final e payload;
    private final Optional<String> typeHeader;

    public static final class Builder {
        private final e payload;
        private Optional<String> typeHeader;
        private boolean withoutExpiration;

        private Builder() {
            this.typeHeader = Optional.empty();
            this.withoutExpiration = false;
            this.payload = new e();
        }

        private void setTimestampClaim(String str, Instant instant) {
            long epochSecond = instant.getEpochSecond();
            if (epochSecond > RawJwt.MAX_TIMESTAMP_VALUE || epochSecond < 0) {
                throw new IllegalArgumentException(c.a("timestamp of claim ", str, " is out of range"));
            }
            this.payload.j(str, new g(Long.valueOf(epochSecond)));
        }

        @CanIgnoreReturnValue
        public Builder addAudience(String str) {
            a aVar;
            if (!JsonUtil.isValidString(str)) {
                throw new IllegalArgumentException("invalid string");
            }
            if (this.payload.o("aud")) {
                b bVarN = this.payload.n("aud");
                bVarN.getClass();
                if (!(bVarN instanceof a)) {
                    throw new IllegalArgumentException("addAudience can't be used together with setAudience");
                }
                aVar = bVarN.d();
            } else {
                aVar = new a();
            }
            aVar.k(str);
            this.payload.j("aud", aVar);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder addBooleanClaim(String str, boolean z2) {
            JwtNames.validate(str);
            this.payload.j(str, new g(Boolean.valueOf(z2)));
            return this;
        }

        @CanIgnoreReturnValue
        public Builder addJsonArrayClaim(String str, String str2) {
            JwtNames.validate(str);
            this.payload.j(str, JsonUtil.parseJsonArray(str2));
            return this;
        }

        @CanIgnoreReturnValue
        public Builder addJsonObjectClaim(String str, String str2) {
            JwtNames.validate(str);
            this.payload.j(str, JsonUtil.parseJson(str2));
            return this;
        }

        @CanIgnoreReturnValue
        public Builder addNullClaim(String str) {
            JwtNames.validate(str);
            this.payload.j(str, d.f1138a);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder addNumberClaim(String str, double d2) {
            JwtNames.validate(str);
            this.payload.j(str, new g(Double.valueOf(d2)));
            return this;
        }

        @CanIgnoreReturnValue
        public Builder addStringClaim(String str, String str2) {
            if (!JsonUtil.isValidString(str2)) {
                throw new IllegalArgumentException();
            }
            JwtNames.validate(str);
            this.payload.j(str, new g(str2));
            return this;
        }

        public RawJwt build() {
            return new RawJwt(this);
        }

        @CanIgnoreReturnValue
        public Builder setAudience(String str) {
            if (this.payload.o("aud")) {
                b bVarN = this.payload.n("aud");
                bVarN.getClass();
                if (bVarN instanceof a) {
                    throw new IllegalArgumentException("setAudience can't be used together with setAudiences or addAudience");
                }
            }
            if (!JsonUtil.isValidString(str)) {
                throw new IllegalArgumentException("invalid string");
            }
            this.payload.j("aud", new g(str));
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setAudiences(List<String> list) {
            if (this.payload.o("aud")) {
                b bVarN = this.payload.n("aud");
                bVarN.getClass();
                if (!(bVarN instanceof a)) {
                    throw new IllegalArgumentException("setAudiences can't be used together with setAudience");
                }
            }
            if (list.isEmpty()) {
                throw new IllegalArgumentException("audiences must not be empty");
            }
            a aVar = new a();
            for (String str : list) {
                if (!JsonUtil.isValidString(str)) {
                    throw new IllegalArgumentException("invalid string");
                }
                aVar.k(str);
            }
            this.payload.j("aud", aVar);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setExpiration(Instant instant) {
            setTimestampClaim("exp", instant);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setIssuedAt(Instant instant) {
            setTimestampClaim("iat", instant);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setIssuer(String str) {
            if (!JsonUtil.isValidString(str)) {
                throw new IllegalArgumentException();
            }
            this.payload.j("iss", new g(str));
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setJwtId(String str) {
            if (!JsonUtil.isValidString(str)) {
                throw new IllegalArgumentException();
            }
            this.payload.j("jti", new g(str));
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setNotBefore(Instant instant) {
            setTimestampClaim("nbf", instant);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setSubject(String str) {
            if (!JsonUtil.isValidString(str)) {
                throw new IllegalArgumentException();
            }
            this.payload.j("sub", new g(str));
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setTypeHeader(String str) {
            this.typeHeader = Optional.of(str);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder withoutExpiration() {
            this.withoutExpiration = true;
            return this;
        }
    }

    private RawJwt(Builder builder) {
        if (!builder.payload.o("exp") && !builder.withoutExpiration) {
            throw new IllegalArgumentException("neither setExpiration() nor withoutExpiration() was called");
        }
        if (builder.payload.o("exp") && builder.withoutExpiration) {
            throw new IllegalArgumentException("setExpiration() and withoutExpiration() must not be called together");
        }
        this.typeHeader = builder.typeHeader;
        this.payload = builder.payload.a();
    }

    public static RawJwt fromJsonPayload(Optional<String> optional, String str) {
        return new RawJwt(optional, str);
    }

    private Instant getInstant(String str) throws JwtInvalidException {
        if (!this.payload.o(str)) {
            throw new JwtInvalidException(c.a("claim ", str, " does not exist"));
        }
        b bVarN = this.payload.n(str);
        bVarN.getClass();
        if (!(bVarN instanceof g) || !(this.payload.n(str).f().f1140a instanceof Number)) {
            throw new JwtInvalidException(c.a("claim ", str, " is not a timestamp"));
        }
        try {
            return Instant.ofEpochMilli((long) (this.payload.n(str).f().c() * 1000.0d));
        } catch (NumberFormatException e2) {
            throw new JwtInvalidException("claim " + str + " is not a timestamp: " + e2);
        }
    }

    private String getStringClaimInternal(String str) throws JwtInvalidException {
        if (!this.payload.o(str)) {
            throw new JwtInvalidException(c.a("claim ", str, " does not exist"));
        }
        b bVarN = this.payload.n(str);
        bVarN.getClass();
        if ((bVarN instanceof g) && (this.payload.n(str).f().f1140a instanceof String)) {
            return this.payload.n(str).i();
        }
        throw new JwtInvalidException(c.a("claim ", str, " is not a string"));
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    private void validateAudienceClaim() throws JwtInvalidException {
        if (this.payload.o("aud")) {
            b bVarN = this.payload.n("aud");
            bVarN.getClass();
            if (!((bVarN instanceof g) && (this.payload.n("aud").f().f1140a instanceof String)) && getAudiences().size() < 1) {
                throw new JwtInvalidException("invalid JWT payload: claim aud is present but empty.");
            }
        }
    }

    private void validateStringClaim(String str) throws JwtInvalidException {
        if (this.payload.o(str)) {
            b bVarN = this.payload.n(str);
            bVarN.getClass();
            if (!(bVarN instanceof g) || !(this.payload.n(str).f().f1140a instanceof String)) {
                throw new JwtInvalidException(c.a("invalid JWT payload: claim ", str, " is not a string."));
            }
        }
    }

    private void validateTimestampClaim(String str) throws JwtInvalidException {
        if (this.payload.o(str)) {
            b bVarN = this.payload.n(str);
            bVarN.getClass();
            if (!(bVarN instanceof g) || !(this.payload.n(str).f().f1140a instanceof Number)) {
                throw new JwtInvalidException(c.a("invalid JWT payload: claim ", str, " is not a number."));
            }
            double dC = this.payload.n(str).f().c();
            if (dC > 2.53402300799E11d || dC < 0.0d) {
                throw new JwtInvalidException(c.a("invalid JWT payload: claim ", str, " has an invalid timestamp"));
            }
        }
    }

    public Set<String> customClaimNames() {
        HashSet hashSet = new HashSet();
        Iterator it = ((j0.c) this.payload.f1139a.keySet()).iterator();
        while (((j0.d) it).hasNext()) {
            String str = (String) ((j0.b) it).next();
            if (!JwtNames.isRegisteredName(str)) {
                hashSet.add(str);
            }
        }
        return Collections.unmodifiableSet(hashSet);
    }

    public List<String> getAudiences() throws JwtInvalidException {
        if (!hasAudiences()) {
            throw new JwtInvalidException("claim aud does not exist");
        }
        b bVarN = this.payload.n("aud");
        bVarN.getClass();
        if (bVarN instanceof g) {
            if (bVarN.f().f1140a instanceof String) {
                return Collections.unmodifiableList(Arrays.asList(bVarN.i()));
            }
            throw new JwtInvalidException(String.format("invalid audience: got %s; want a string", bVarN));
        }
        if (!(bVarN instanceof a)) {
            throw new JwtInvalidException("claim aud is not a string or a JSON array");
        }
        a aVarD = bVarN.d();
        ArrayList arrayList = new ArrayList(aVarD.size());
        for (int i2 = 0; i2 < aVarD.size(); i2++) {
            b bVarL = aVarD.l(i2);
            bVarL.getClass();
            if (!(bVarL instanceof g) || !(aVarD.l(i2).f().f1140a instanceof String)) {
                throw new JwtInvalidException(String.format("invalid audience: got %s; want a string", aVarD.l(i2)));
            }
            arrayList.add(aVarD.l(i2).i());
        }
        return Collections.unmodifiableList(arrayList);
    }

    public Boolean getBooleanClaim(String str) throws JwtInvalidException {
        JwtNames.validate(str);
        if (!this.payload.o(str)) {
            throw new JwtInvalidException(c.a("claim ", str, " does not exist"));
        }
        b bVarN = this.payload.n(str);
        bVarN.getClass();
        if ((bVarN instanceof g) && (this.payload.n(str).f().f1140a instanceof Boolean)) {
            return Boolean.valueOf(this.payload.n(str).b());
        }
        throw new JwtInvalidException(c.a("claim ", str, " is not a boolean"));
    }

    public Instant getExpiration() {
        return getInstant("exp");
    }

    public Instant getIssuedAt() {
        return getInstant("iat");
    }

    public String getIssuer() {
        return getStringClaimInternal("iss");
    }

    public String getJsonArrayClaim(String str) throws JwtInvalidException {
        JwtNames.validate(str);
        if (!this.payload.o(str)) {
            throw new JwtInvalidException(c.a("claim ", str, " does not exist"));
        }
        b bVarN = this.payload.n(str);
        bVarN.getClass();
        if (bVarN instanceof a) {
            return this.payload.n(str).d().toString();
        }
        throw new JwtInvalidException(c.a("claim ", str, " is not a JSON array"));
    }

    public String getJsonObjectClaim(String str) throws JwtInvalidException {
        JwtNames.validate(str);
        if (!this.payload.o(str)) {
            throw new JwtInvalidException(c.a("claim ", str, " does not exist"));
        }
        b bVarN = this.payload.n(str);
        bVarN.getClass();
        if (bVarN instanceof e) {
            return this.payload.n(str).e().toString();
        }
        throw new JwtInvalidException(c.a("claim ", str, " is not a JSON object"));
    }

    public String getJsonPayload() {
        return this.payload.toString();
    }

    public String getJwtId() {
        return getStringClaimInternal("jti");
    }

    public Instant getNotBefore() {
        return getInstant("nbf");
    }

    public Double getNumberClaim(String str) throws JwtInvalidException {
        JwtNames.validate(str);
        if (!this.payload.o(str)) {
            throw new JwtInvalidException(c.a("claim ", str, " does not exist"));
        }
        b bVarN = this.payload.n(str);
        bVarN.getClass();
        if ((bVarN instanceof g) && (this.payload.n(str).f().f1140a instanceof Number)) {
            return Double.valueOf(this.payload.n(str).c());
        }
        throw new JwtInvalidException(c.a("claim ", str, " is not a number"));
    }

    public String getStringClaim(String str) {
        JwtNames.validate(str);
        return getStringClaimInternal(str);
    }

    public String getSubject() {
        return getStringClaimInternal("sub");
    }

    public String getTypeHeader() throws JwtInvalidException {
        if (this.typeHeader.isPresent()) {
            return (String) this.typeHeader.get();
        }
        throw new JwtInvalidException("type header is not set");
    }

    public boolean hasAudiences() {
        return this.payload.o("aud");
    }

    public boolean hasBooleanClaim(String str) {
        JwtNames.validate(str);
        if (this.payload.o(str)) {
            b bVarN = this.payload.n(str);
            bVarN.getClass();
            if ((bVarN instanceof g) && (this.payload.n(str).f().f1140a instanceof Boolean)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasExpiration() {
        return this.payload.o("exp");
    }

    public boolean hasIssuedAt() {
        return this.payload.o("iat");
    }

    public boolean hasIssuer() {
        return this.payload.o("iss");
    }

    public boolean hasJsonArrayClaim(String str) {
        JwtNames.validate(str);
        if (this.payload.o(str)) {
            b bVarN = this.payload.n(str);
            bVarN.getClass();
            if (bVarN instanceof a) {
                return true;
            }
        }
        return false;
    }

    public boolean hasJsonObjectClaim(String str) {
        JwtNames.validate(str);
        if (this.payload.o(str)) {
            b bVarN = this.payload.n(str);
            bVarN.getClass();
            if (bVarN instanceof e) {
                return true;
            }
        }
        return false;
    }

    public boolean hasJwtId() {
        return this.payload.o("jti");
    }

    public boolean hasNotBefore() {
        return this.payload.o("nbf");
    }

    public boolean hasNumberClaim(String str) {
        JwtNames.validate(str);
        if (this.payload.o(str)) {
            b bVarN = this.payload.n(str);
            bVarN.getClass();
            if ((bVarN instanceof g) && (this.payload.n(str).f().f1140a instanceof Number)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasStringClaim(String str) {
        JwtNames.validate(str);
        if (this.payload.o(str)) {
            b bVarN = this.payload.n(str);
            bVarN.getClass();
            if ((bVarN instanceof g) && (this.payload.n(str).f().f1140a instanceof String)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasSubject() {
        return this.payload.o("sub");
    }

    public boolean hasTypeHeader() {
        return this.typeHeader.isPresent();
    }

    public boolean isNullClaim(String str) {
        JwtNames.validate(str);
        try {
            return d.f1138a.equals(this.payload.n(str));
        } catch (f unused) {
            return false;
        }
    }

    public String toString() {
        e eVar = new e();
        if (this.typeHeader.isPresent()) {
            eVar.j("typ", new g((String) this.typeHeader.get()));
        }
        return eVar + "." + this.payload;
    }

    private RawJwt(Optional<String> optional, String str) throws JwtInvalidException {
        this.typeHeader = optional;
        this.payload = JsonUtil.parseJson(str);
        validateStringClaim("iss");
        validateStringClaim("sub");
        validateStringClaim("jti");
        validateTimestampClaim("exp");
        validateTimestampClaim("nbf");
        validateTimestampClaim("iat");
        validateAudienceClaim();
    }
}
