package com.google.crypto.tink.monitoring;

import com.google.crypto.tink.annotations.Alpha;

/* JADX INFO: loaded from: classes.dex */
@Alpha
public interface MonitoringClient {

    public interface Logger {
        void log(int i2, long j2);

        void logFailure();
    }

    Logger createLogger(MonitoringKeysetInfo monitoringKeysetInfo, String str, String str2);
}
