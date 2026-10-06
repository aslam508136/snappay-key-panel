package com.google.crypto.tink;

import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.Keyset;
import com.google.errorprone.annotations.InlineMe;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public final class BinaryKeysetWriter implements KeysetWriter {
    private final OutputStream outputStream;

    private BinaryKeysetWriter(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @InlineMe(imports = {"com.google.crypto.tink.BinaryKeysetWriter", "java.io.FileOutputStream"}, replacement = "BinaryKeysetWriter.withOutputStream(new FileOutputStream(file))")
    @Deprecated
    public static KeysetWriter withFile(File file) {
        return withOutputStream(new FileOutputStream(file));
    }

    public static KeysetWriter withOutputStream(OutputStream outputStream) {
        return new BinaryKeysetWriter(outputStream);
    }

    @Override // com.google.crypto.tink.KeysetWriter
    public void write(EncryptedKeyset encryptedKeyset) throws IOException {
        try {
            encryptedKeyset.writeTo(this.outputStream);
        } finally {
            this.outputStream.close();
        }
    }

    @Override // com.google.crypto.tink.KeysetWriter
    public void write(Keyset keyset) throws IOException {
        try {
            keyset.writeTo(this.outputStream);
        } finally {
            this.outputStream.close();
        }
    }
}
