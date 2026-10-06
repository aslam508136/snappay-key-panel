package com.google.crypto.tink.subtle.prf;

import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.Enums;
import com.google.errorprone.annotations.Immutable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
@Immutable
public class HkdfStreamingPrf implements StreamingPrf {
    private final Enums.HashType hashType;
    private final byte[] ikm;
    private final byte[] salt;

    /* JADX INFO: renamed from: com.google.crypto.tink.subtle.prf.HkdfStreamingPrf$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$subtle$Enums$HashType;

        static {
            int[] iArr = new int[Enums.HashType.values().length];
            $SwitchMap$com$google$crypto$tink$subtle$Enums$HashType = iArr;
            try {
                iArr[Enums.HashType.SHA1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$subtle$Enums$HashType[Enums.HashType.SHA256.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$subtle$Enums$HashType[Enums.HashType.SHA384.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$crypto$tink$subtle$Enums$HashType[Enums.HashType.SHA512.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public class HkdfInputStream extends InputStream {
        private ByteBuffer buffer;
        private int ctr = -1;
        private final byte[] input;
        private Mac mac;
        private byte[] prk;

        public HkdfInputStream(byte[] bArr) {
            this.input = Arrays.copyOf(bArr, bArr.length);
        }

        private void initialize() throws InvalidKeyException, IOException {
            Mac mac;
            SecretKeySpec secretKeySpec;
            try {
                this.mac = EngineFactory.MAC.getInstance(HkdfStreamingPrf.getJavaxHmacName(HkdfStreamingPrf.this.hashType));
                if (HkdfStreamingPrf.this.salt == null || HkdfStreamingPrf.this.salt.length == 0) {
                    mac = this.mac;
                    secretKeySpec = new SecretKeySpec(new byte[this.mac.getMacLength()], HkdfStreamingPrf.getJavaxHmacName(HkdfStreamingPrf.this.hashType));
                } else {
                    mac = this.mac;
                    secretKeySpec = new SecretKeySpec(HkdfStreamingPrf.this.salt, HkdfStreamingPrf.getJavaxHmacName(HkdfStreamingPrf.this.hashType));
                }
                mac.init(secretKeySpec);
                this.mac.update(HkdfStreamingPrf.this.ikm);
                this.prk = this.mac.doFinal();
                ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(0);
                this.buffer = byteBufferAllocateDirect;
                byteBufferAllocateDirect.mark();
                this.ctr = 0;
            } catch (GeneralSecurityException e2) {
                throw new IOException("Creating HMac failed", e2);
            }
        }

        private void updateBuffer() throws InvalidKeyException {
            this.mac.init(new SecretKeySpec(this.prk, HkdfStreamingPrf.getJavaxHmacName(HkdfStreamingPrf.this.hashType)));
            this.buffer.reset();
            this.mac.update(this.buffer);
            this.mac.update(this.input);
            int i2 = this.ctr + 1;
            this.ctr = i2;
            this.mac.update((byte) i2);
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(this.mac.doFinal());
            this.buffer = byteBufferWrap;
            byteBufferWrap.mark();
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            byte[] bArr = new byte[1];
            int i2 = read(bArr, 0, 1);
            if (i2 == 1) {
                return bArr[0] & 255;
            }
            if (i2 == -1) {
                return i2;
            }
            throw new IOException("Reading failed");
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr) {
            return read(bArr, 0, bArr.length);
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i2, int i3) throws IOException {
            try {
                if (this.ctr == -1) {
                    initialize();
                }
                int i4 = 0;
                while (i4 < i3) {
                    if (!this.buffer.hasRemaining()) {
                        if (this.ctr == 255) {
                            return i4;
                        }
                        updateBuffer();
                    }
                    int iMin = Math.min(i3 - i4, this.buffer.remaining());
                    this.buffer.get(bArr, i2, iMin);
                    i2 += iMin;
                    i4 += iMin;
                }
                return i4;
            } catch (GeneralSecurityException e2) {
                this.mac = null;
                throw new IOException("HkdfInputStream failed", e2);
            }
        }
    }

    public HkdfStreamingPrf(Enums.HashType hashType, byte[] bArr, byte[] bArr2) {
        this.hashType = hashType;
        this.ikm = Arrays.copyOf(bArr, bArr.length);
        this.salt = Arrays.copyOf(bArr2, bArr2.length);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getJavaxHmacName(Enums.HashType hashType) throws GeneralSecurityException {
        int i2 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$subtle$Enums$HashType[hashType.ordinal()];
        if (i2 == 1) {
            return "HmacSha1";
        }
        if (i2 == 2) {
            return "HmacSha256";
        }
        if (i2 == 3) {
            return "HmacSha384";
        }
        if (i2 == 4) {
            return "HmacSha512";
        }
        throw new GeneralSecurityException("No getJavaxHmacName for given hash " + hashType + " known");
    }

    @Override // com.google.crypto.tink.subtle.prf.StreamingPrf
    public InputStream computePrf(byte[] bArr) {
        return new HkdfInputStream(bArr);
    }
}
