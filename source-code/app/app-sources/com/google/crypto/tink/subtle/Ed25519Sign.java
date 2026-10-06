package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class Ed25519Sign implements PublicKeySign {
    public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS;
    public static final int SECRET_KEY_LEN = 32;
    private final byte[] hashedPrivateKey;
    private final byte[] publicKey;

    public static final class KeyPair {
        private final byte[] privateKey;
        private final byte[] publicKey;

        private KeyPair(byte[] bArr, byte[] bArr2) {
            this.publicKey = bArr;
            this.privateKey = bArr2;
        }

        public static KeyPair newKeyPair() {
            return newKeyPairFromSeed(Random.randBytes(32));
        }

        public static KeyPair newKeyPairFromSeed(byte[] bArr) {
            if (bArr.length == 32) {
                return new KeyPair(Ed25519.scalarMultWithBaseToBytes(Ed25519.getHashedScalar(bArr)), bArr);
            }
            throw new IllegalArgumentException(String.format("Given secret seed length is not %s", 32));
        }

        public byte[] getPrivateKey() {
            byte[] bArr = this.privateKey;
            return Arrays.copyOf(bArr, bArr.length);
        }

        public byte[] getPublicKey() {
            byte[] bArr = this.publicKey;
            return Arrays.copyOf(bArr, bArr.length);
        }
    }

    public Ed25519Sign(byte[] bArr) throws GeneralSecurityException {
        if (!FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use Ed25519 in FIPS-mode.");
        }
        if (bArr.length != 32) {
            throw new IllegalArgumentException(String.format("Given private key's length is not %s", 32));
        }
        byte[] hashedScalar = Ed25519.getHashedScalar(bArr);
        this.hashedPrivateKey = hashedScalar;
        this.publicKey = Ed25519.scalarMultWithBaseToBytes(hashedScalar);
    }

    @Override // com.google.crypto.tink.PublicKeySign
    public byte[] sign(byte[] bArr) {
        return Ed25519.sign(bArr, this.publicKey, this.hashedPrivateKey);
    }
}
