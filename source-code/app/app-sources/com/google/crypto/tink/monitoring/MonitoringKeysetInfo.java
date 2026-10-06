package com.google.crypto.tink.monitoring;

import com.google.crypto.tink.KeyStatus;
import com.google.crypto.tink.annotations.Alpha;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Immutable
@Alpha
public final class MonitoringKeysetInfo {
    private final MonitoringAnnotations annotations;
    private final List<Entry> entries;

    @Nullable
    private final Integer primaryKeyId;

    public static final class Builder {

        @Nullable
        private ArrayList<Entry> builderEntries = new ArrayList<>();
        private MonitoringAnnotations builderAnnotations = MonitoringAnnotations.EMPTY;

        @Nullable
        private Integer builderPrimaryKeyId = null;

        private boolean isKeyIdInEntries(int i2) {
            Iterator<Entry> it = this.builderEntries.iterator();
            while (it.hasNext()) {
                if (it.next().getKeyId() == i2) {
                    return true;
                }
            }
            return false;
        }

        @CanIgnoreReturnValue
        public Builder addEntry(KeyStatus keyStatus, int i2, String str, String str2) {
            ArrayList<Entry> arrayList = this.builderEntries;
            if (arrayList == null) {
                throw new IllegalStateException("addEntry cannot be called after build()");
            }
            arrayList.add(new Entry(keyStatus, i2, str, str2));
            return this;
        }

        public MonitoringKeysetInfo build() throws GeneralSecurityException {
            if (this.builderEntries == null) {
                throw new IllegalStateException("cannot call build() twice");
            }
            Integer num = this.builderPrimaryKeyId;
            if (num != null && !isKeyIdInEntries(num.intValue())) {
                throw new GeneralSecurityException("primary key ID is not present in entries");
            }
            MonitoringKeysetInfo monitoringKeysetInfo = new MonitoringKeysetInfo(this.builderAnnotations, Collections.unmodifiableList(this.builderEntries), this.builderPrimaryKeyId);
            this.builderEntries = null;
            return monitoringKeysetInfo;
        }

        @CanIgnoreReturnValue
        public Builder setAnnotations(MonitoringAnnotations monitoringAnnotations) {
            if (this.builderEntries == null) {
                throw new IllegalStateException("setAnnotations cannot be called after build()");
            }
            this.builderAnnotations = monitoringAnnotations;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setPrimaryKeyId(int i2) {
            if (this.builderEntries == null) {
                throw new IllegalStateException("setPrimaryKeyId cannot be called after build()");
            }
            this.builderPrimaryKeyId = Integer.valueOf(i2);
            return this;
        }
    }

    @Immutable
    public static final class Entry {
        private final int keyId;
        private final String keyPrefix;
        private final String keyType;
        private final KeyStatus status;

        private Entry(KeyStatus keyStatus, int i2, String str, String str2) {
            this.status = keyStatus;
            this.keyId = i2;
            this.keyType = str;
            this.keyPrefix = str2;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            return this.status == entry.status && this.keyId == entry.keyId && this.keyType.equals(entry.keyType) && this.keyPrefix.equals(entry.keyPrefix);
        }

        public int getKeyId() {
            return this.keyId;
        }

        public String getKeyPrefix() {
            return this.keyPrefix;
        }

        public String getKeyType() {
            return this.keyType;
        }

        public KeyStatus getStatus() {
            return this.status;
        }

        public int hashCode() {
            return Objects.hash(this.status, Integer.valueOf(this.keyId), this.keyType, this.keyPrefix);
        }

        public String toString() {
            return String.format("(status=%s, keyId=%s, keyType='%s', keyPrefix='%s')", this.status, Integer.valueOf(this.keyId), this.keyType, this.keyPrefix);
        }
    }

    private MonitoringKeysetInfo(MonitoringAnnotations monitoringAnnotations, List<Entry> list, Integer num) {
        this.annotations = monitoringAnnotations;
        this.entries = list;
        this.primaryKeyId = num;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MonitoringKeysetInfo)) {
            return false;
        }
        MonitoringKeysetInfo monitoringKeysetInfo = (MonitoringKeysetInfo) obj;
        return this.annotations.equals(monitoringKeysetInfo.annotations) && this.entries.equals(monitoringKeysetInfo.entries) && Objects.equals(this.primaryKeyId, monitoringKeysetInfo.primaryKeyId);
    }

    public MonitoringAnnotations getAnnotations() {
        return this.annotations;
    }

    public List<Entry> getEntries() {
        return this.entries;
    }

    @Nullable
    public Integer getPrimaryKeyId() {
        return this.primaryKeyId;
    }

    public int hashCode() {
        return Objects.hash(this.annotations, this.entries);
    }

    public String toString() {
        return String.format("(annotations=%s, entries=%s, primaryKeyId=%s)", this.annotations, this.entries, this.primaryKeyId);
    }
}
