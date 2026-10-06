package com.google.crypto.tink.util;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.concurrent.GuardedBy;
import org.joda.time.Instant;

/* JADX INFO: loaded from: classes.dex */
public class KeysDownloader {
    private final Executor backgroundExecutor;

    @GuardedBy("instanceStateLock")
    private long cacheExpirationDurationInMillis;

    @GuardedBy("instanceStateLock")
    private String cachedData;

    @GuardedBy("instanceStateLock")
    private long cachedTimeInMillis;
    private final Object fetchDataLock;
    private final HttpTransport httpTransport;
    private final Object instanceStateLock;

    @GuardedBy("instanceStateLock")
    private Runnable pendingRefreshRunnable;
    private final String url;
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final NetHttpTransport DEFAULT_HTTP_TRANSPORT = new NetHttpTransport.Builder().build();
    private static final Executor DEFAULT_BACKGROUND_EXECUTOR = Executors.newCachedThreadPool();
    private static final Pattern MAX_AGE_PATTERN = Pattern.compile("\\s*max-age\\s*=\\s*(\\d+)\\s*");

    public static class Builder {
        private String url;
        private HttpTransport httpTransport = KeysDownloader.DEFAULT_HTTP_TRANSPORT;
        private Executor executor = KeysDownloader.DEFAULT_BACKGROUND_EXECUTOR;

        public KeysDownloader build() {
            if (this.url != null) {
                return new KeysDownloader(this.executor, this.httpTransport, this.url);
            }
            throw new IllegalArgumentException("must provide a url with {#setUrl}");
        }

        @CanIgnoreReturnValue
        public Builder setExecutor(Executor executor) {
            this.executor = executor;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setHttpTransport(HttpTransport httpTransport) {
            this.httpTransport = httpTransport;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setUrl(String str) {
            this.url = str;
            return this;
        }
    }

    public KeysDownloader(Executor executor, HttpTransport httpTransport, String str) {
        validate(str);
        this.backgroundExecutor = executor;
        this.httpTransport = httpTransport;
        this.instanceStateLock = new Object();
        this.fetchDataLock = new Object();
        this.url = str;
        this.cachedTimeInMillis = Long.MIN_VALUE;
        this.cacheExpirationDurationInMillis = 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @CanIgnoreReturnValue
    @GuardedBy("fetchDataLock")
    public String fetchAndCacheData() throws IOException {
        long currentTimeInMillis = getCurrentTimeInMillis();
        HttpResponse httpResponseExecute = this.httpTransport.createRequestFactory().buildGetRequest(new GenericUrl(this.url)).execute();
        if (httpResponseExecute.getStatusCode() != 200) {
            throw new IOException("Unexpected status code = " + httpResponseExecute.getStatusCode());
        }
        InputStream content = httpResponseExecute.getContent();
        try {
            String str = readerToString(new InputStreamReader(content, UTF_8));
            content.close();
            synchronized (this.instanceStateLock) {
                this.cachedTimeInMillis = currentTimeInMillis;
                this.cacheExpirationDurationInMillis = getExpirationDurationInSeconds(httpResponseExecute.getHeaders()) * 1000;
                this.cachedData = str;
            }
            return str;
        } catch (Throwable th) {
            content.close();
            throw th;
        }
    }

    @GuardedBy("instanceStateLock")
    private boolean hasNonExpiredDataCached() {
        long currentTimeInMillis = getCurrentTimeInMillis();
        long j2 = this.cachedTimeInMillis;
        return ((((j2 + this.cacheExpirationDurationInMillis) > currentTimeInMillis ? 1 : ((j2 + this.cacheExpirationDurationInMillis) == currentTimeInMillis ? 0 : -1)) <= 0) || ((j2 > currentTimeInMillis ? 1 : (j2 == currentTimeInMillis ? 0 : -1)) > 0)) ? false : true;
    }

    private Runnable newRefreshRunnable() {
        return new Runnable() { // from class: com.google.crypto.tink.util.KeysDownloader.1
            @Override // java.lang.Runnable
            public void run() {
                synchronized (KeysDownloader.this.fetchDataLock) {
                    try {
                        KeysDownloader.this.fetchAndCacheData();
                        synchronized (KeysDownloader.this.instanceStateLock) {
                            if (KeysDownloader.this.pendingRefreshRunnable == this) {
                                KeysDownloader.this.pendingRefreshRunnable = null;
                            }
                        }
                    } catch (IOException unused) {
                        synchronized (KeysDownloader.this.instanceStateLock) {
                            if (KeysDownloader.this.pendingRefreshRunnable == this) {
                                KeysDownloader.this.pendingRefreshRunnable = null;
                            }
                        }
                    } catch (Throwable th) {
                        synchronized (KeysDownloader.this.instanceStateLock) {
                            if (KeysDownloader.this.pendingRefreshRunnable == this) {
                                KeysDownloader.this.pendingRefreshRunnable = null;
                            }
                            throw th;
                        }
                    }
                }
            }
        };
    }

    private static String readerToString(Reader reader) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();
        while (true) {
            int i2 = bufferedReader.read();
            if (i2 == -1) {
                return sb.toString();
            }
            sb.append((char) i2);
        }
    }

    @GuardedBy("instanceStateLock")
    private boolean shouldProactivelyRefreshDataInBackground() {
        return (this.cacheExpirationDurationInMillis / 2) + this.cachedTimeInMillis <= getCurrentTimeInMillis();
    }

    private static void validate(String str) {
        try {
            if (new URL(str).getProtocol().toLowerCase(Locale.US).equals("https")) {
            } else {
                throw new IllegalArgumentException("url must point to a HTTPS server");
            }
        } catch (MalformedURLException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public String download() {
        synchronized (this.instanceStateLock) {
            if (hasNonExpiredDataCached()) {
                if (shouldProactivelyRefreshDataInBackground()) {
                    refreshInBackground();
                }
                return this.cachedData;
            }
            synchronized (this.fetchDataLock) {
                synchronized (this.instanceStateLock) {
                    if (hasNonExpiredDataCached()) {
                        return this.cachedData;
                    }
                    return fetchAndCacheData();
                }
            }
        }
    }

    public long getCurrentTimeInMillis() {
        return Instant.now().getMillis();
    }

    public long getExpirationDurationInSeconds(HttpHeaders httpHeaders) {
        long jLongValue;
        if (httpHeaders.getCacheControl() == null) {
            jLongValue = 0;
            break;
        }
        String[] strArrSplit = httpHeaders.getCacheControl().split(",");
        int length = strArrSplit.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                jLongValue = 0;
                break;
            }
            Matcher matcher = MAX_AGE_PATTERN.matcher(strArrSplit[i2]);
            if (matcher.matches()) {
                jLongValue = Long.valueOf(matcher.group(1)).longValue();
                break;
            }
            i2++;
        }
        if (httpHeaders.getAge() != null) {
            jLongValue -= httpHeaders.getAge().longValue();
        }
        return Math.max(0L, jLongValue);
    }

    public HttpTransport getHttpTransport() {
        return this.httpTransport;
    }

    public String getUrl() {
        return this.url;
    }

    public void refreshInBackground() {
        Runnable runnableNewRefreshRunnable = newRefreshRunnable();
        synchronized (this.instanceStateLock) {
            if (this.pendingRefreshRunnable != null) {
                return;
            }
            this.pendingRefreshRunnable = runnableNewRefreshRunnable;
            try {
                this.backgroundExecutor.execute(runnableNewRefreshRunnable);
            } catch (Throwable th) {
                synchronized (this.instanceStateLock) {
                    if (this.pendingRefreshRunnable == runnableNewRefreshRunnable) {
                        this.pendingRefreshRunnable = null;
                    }
                    throw th;
                }
            }
        }
    }
}
