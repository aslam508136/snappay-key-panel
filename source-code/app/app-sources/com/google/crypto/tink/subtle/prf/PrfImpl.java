package com.google.crypto.tink.subtle.prf;

import com.google.crypto.tink.prf.Prf;
import com.google.errorprone.annotations.Immutable;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes.dex */
@Immutable
public class PrfImpl implements Prf {
    private final StreamingPrf prfStreamer;

    private PrfImpl(StreamingPrf streamingPrf) {
        this.prfStreamer = streamingPrf;
    }

    private static byte[] readBytesFromStream(InputStream inputStream, int i2) throws GeneralSecurityException {
        try {
            byte[] bArr = new byte[i2];
            int i3 = 0;
            while (i3 < i2) {
                int i4 = inputStream.read(bArr, i3, i2 - i3);
                if (i4 <= 0) {
                    throw new GeneralSecurityException("Provided StreamingPrf terminated before providing requested number of bytes.");
                }
                i3 += i4;
            }
            return bArr;
        } catch (IOException e2) {
            throw new GeneralSecurityException(e2);
        }
    }

    public static PrfImpl wrap(StreamingPrf streamingPrf) {
        return new PrfImpl(streamingPrf);
    }

    @Override // com.google.crypto.tink.prf.Prf
    public byte[] compute(byte[] bArr, int i2) throws GeneralSecurityException {
        if (bArr == null) {
            throw new GeneralSecurityException("Invalid input provided.");
        }
        if (i2 > 0) {
            return readBytesFromStream(this.prfStreamer.computePrf(bArr), i2);
        }
        throw new GeneralSecurityException("Invalid outputLength specified.");
    }
}
