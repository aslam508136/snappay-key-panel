package com.google.crypto.tink.monitoring;

import com.google.crypto.tink.annotations.Alpha;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@Immutable
@Alpha
public final class MonitoringAnnotations {
    public static final MonitoringAnnotations EMPTY = newBuilder().build();
    private final Map<String, String> entries;

    public static final class Builder {
        private HashMap<String, String> builderEntries = new HashMap<>();

        @CanIgnoreReturnValue
        public Builder add(String str, String str2) {
            HashMap<String, String> map = this.builderEntries;
            if (map == null) {
                throw new IllegalStateException("add cannot be called after build()");
            }
            map.put(str, str2);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder addAll(Map<String, String> map) {
            HashMap<String, String> map2 = this.builderEntries;
            if (map2 == null) {
                throw new IllegalStateException("addAll cannot be called after build()");
            }
            map2.putAll(map);
            return this;
        }

        public MonitoringAnnotations build() {
            if (this.builderEntries == null) {
                throw new IllegalStateException("cannot call build() twice");
            }
            MonitoringAnnotations monitoringAnnotations = new MonitoringAnnotations(Collections.unmodifiableMap(this.builderEntries));
            this.builderEntries = null;
            return monitoringAnnotations;
        }
    }

    private MonitoringAnnotations(Map<String, String> map) {
        this.entries = map;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (obj instanceof MonitoringAnnotations) {
            return this.entries.equals(((MonitoringAnnotations) obj).entries);
        }
        return false;
    }

    public int hashCode() {
        return this.entries.hashCode();
    }

    public Map<String, String> toMap() {
        return this.entries;
    }

    public String toString() {
        return this.entries.toString();
    }
}
