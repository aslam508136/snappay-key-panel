package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.AbstractMessageLite;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.CodedInputStream;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.google.crypto.tink.shaded.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class JwtRsaSsaPkcs1PrivateKey extends GeneratedMessageLite<JwtRsaSsaPkcs1PrivateKey, Builder> implements JwtRsaSsaPkcs1PrivateKeyOrBuilder {
    public static final int CRT_FIELD_NUMBER = 8;
    private static final JwtRsaSsaPkcs1PrivateKey DEFAULT_INSTANCE;
    public static final int DP_FIELD_NUMBER = 6;
    public static final int DQ_FIELD_NUMBER = 7;
    public static final int D_FIELD_NUMBER = 3;
    private static volatile Parser<JwtRsaSsaPkcs1PrivateKey> PARSER = null;
    public static final int PUBLIC_KEY_FIELD_NUMBER = 2;
    public static final int P_FIELD_NUMBER = 4;
    public static final int Q_FIELD_NUMBER = 5;
    public static final int VERSION_FIELD_NUMBER = 1;
    private ByteString crt_;
    private ByteString d_;
    private ByteString dp_;
    private ByteString dq_;
    private ByteString p_;
    private JwtRsaSsaPkcs1PublicKey publicKey_;
    private ByteString q_;
    private int version_;

    /* JADX INFO: renamed from: com.google.crypto.tink.proto.JwtRsaSsaPkcs1PrivateKey$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public static final class Builder extends GeneratedMessageLite.Builder<JwtRsaSsaPkcs1PrivateKey, Builder> implements JwtRsaSsaPkcs1PrivateKeyOrBuilder {
        private Builder() {
            super(JwtRsaSsaPkcs1PrivateKey.DEFAULT_INSTANCE);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder, com.google.crypto.tink.shaded.protobuf.MessageLite.Builder
        public /* bridge */ /* synthetic */ MessageLite build() {
            return super.build();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder, com.google.crypto.tink.shaded.protobuf.MessageLite.Builder
        public /* bridge */ /* synthetic */ MessageLite buildPartial() {
            return super.buildPartial();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder, com.google.crypto.tink.shaded.protobuf.MessageLite.Builder
        public /* bridge */ /* synthetic */ MessageLite.Builder clear() {
            return super.clear();
        }

        public Builder clearCrt() {
            copyOnWrite();
            ((JwtRsaSsaPkcs1PrivateKey) this.instance).clearCrt();
            return this;
        }

        public Builder clearD() {
            copyOnWrite();
            ((JwtRsaSsaPkcs1PrivateKey) this.instance).clearD();
            return this;
        }

        public Builder clearDp() {
            copyOnWrite();
            ((JwtRsaSsaPkcs1PrivateKey) this.instance).clearDp();
            return this;
        }

        public Builder clearDq() {
            copyOnWrite();
            ((JwtRsaSsaPkcs1PrivateKey) this.instance).clearDq();
            return this;
        }

        public Builder clearP() {
            copyOnWrite();
            ((JwtRsaSsaPkcs1PrivateKey) this.instance).clearP();
            return this;
        }

        public Builder clearPublicKey() {
            copyOnWrite();
            ((JwtRsaSsaPkcs1PrivateKey) this.instance).clearPublicKey();
            return this;
        }

        public Builder clearQ() {
            copyOnWrite();
            ((JwtRsaSsaPkcs1PrivateKey) this.instance).clearQ();
            return this;
        }

        public Builder clearVersion() {
            copyOnWrite();
            ((JwtRsaSsaPkcs1PrivateKey) this.instance).clearVersion();
            return this;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder, com.google.crypto.tink.shaded.protobuf.AbstractMessageLite.Builder, com.google.crypto.tink.shaded.protobuf.MessageLite.Builder
        /* JADX INFO: renamed from: clone */
        public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mo0clone() {
            return super.mo0clone();
        }

        @Override // com.google.crypto.tink.proto.JwtRsaSsaPkcs1PrivateKeyOrBuilder
        public ByteString getCrt() {
            return ((JwtRsaSsaPkcs1PrivateKey) this.instance).getCrt();
        }

        @Override // com.google.crypto.tink.proto.JwtRsaSsaPkcs1PrivateKeyOrBuilder
        public ByteString getD() {
            return ((JwtRsaSsaPkcs1PrivateKey) this.instance).getD();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
        public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.google.crypto.tink.proto.JwtRsaSsaPkcs1PrivateKeyOrBuilder
        public ByteString getDp() {
            return ((JwtRsaSsaPkcs1PrivateKey) this.instance).getDp();
        }

        @Override // com.google.crypto.tink.proto.JwtRsaSsaPkcs1PrivateKeyOrBuilder
        public ByteString getDq() {
            return ((JwtRsaSsaPkcs1PrivateKey) this.instance).getDq();
        }

        @Override // com.google.crypto.tink.proto.JwtRsaSsaPkcs1PrivateKeyOrBuilder
        public ByteString getP() {
            return ((JwtRsaSsaPkcs1PrivateKey) this.instance).getP();
        }

        @Override // com.google.crypto.tink.proto.JwtRsaSsaPkcs1PrivateKeyOrBuilder
        public JwtRsaSsaPkcs1PublicKey getPublicKey() {
            return ((JwtRsaSsaPkcs1PrivateKey) this.instance).getPublicKey();
        }

        @Override // com.google.crypto.tink.proto.JwtRsaSsaPkcs1PrivateKeyOrBuilder
        public ByteString getQ() {
            return ((JwtRsaSsaPkcs1PrivateKey) this.instance).getQ();
        }

        @Override // com.google.crypto.tink.proto.JwtRsaSsaPkcs1PrivateKeyOrBuilder
        public int getVersion() {
            return ((JwtRsaSsaPkcs1PrivateKey) this.instance).getVersion();
        }

        @Override // com.google.crypto.tink.proto.JwtRsaSsaPkcs1PrivateKeyOrBuilder
        public boolean hasPublicKey() {
            return ((JwtRsaSsaPkcs1PrivateKey) this.instance).hasPublicKey();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder, com.google.crypto.tink.shaded.protobuf.AbstractMessageLite.Builder
        public /* bridge */ /* synthetic */ AbstractMessageLite.Builder internalMergeFrom(AbstractMessageLite abstractMessageLite) {
            return super.internalMergeFrom((GeneratedMessageLite) abstractMessageLite);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder, com.google.crypto.tink.shaded.protobuf.AbstractMessageLite.Builder, com.google.crypto.tink.shaded.protobuf.MessageLite.Builder
        public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return super.mergeFrom(codedInputStream, extensionRegistryLite);
        }

        public Builder mergePublicKey(JwtRsaSsaPkcs1PublicKey jwtRsaSsaPkcs1PublicKey) {
            copyOnWrite();
            ((JwtRsaSsaPkcs1PrivateKey) this.instance).mergePublicKey(jwtRsaSsaPkcs1PublicKey);
            return this;
        }

        public Builder setCrt(ByteString byteString) {
            copyOnWrite();
            ((JwtRsaSsaPkcs1PrivateKey) this.instance).setCrt(byteString);
            return this;
        }

        public Builder setD(ByteString byteString) {
            copyOnWrite();
            ((JwtRsaSsaPkcs1PrivateKey) this.instance).setD(byteString);
            return this;
        }

        public Builder setDp(ByteString byteString) {
            copyOnWrite();
            ((JwtRsaSsaPkcs1PrivateKey) this.instance).setDp(byteString);
            return this;
        }

        public Builder setDq(ByteString byteString) {
            copyOnWrite();
            ((JwtRsaSsaPkcs1PrivateKey) this.instance).setDq(byteString);
            return this;
        }

        public Builder setP(ByteString byteString) {
            copyOnWrite();
            ((JwtRsaSsaPkcs1PrivateKey) this.instance).setP(byteString);
            return this;
        }

        public Builder setPublicKey(JwtRsaSsaPkcs1PublicKey.Builder builder) {
            copyOnWrite();
            ((JwtRsaSsaPkcs1PrivateKey) this.instance).setPublicKey(builder.build());
            return this;
        }

        public Builder setQ(ByteString byteString) {
            copyOnWrite();
            ((JwtRsaSsaPkcs1PrivateKey) this.instance).setQ(byteString);
            return this;
        }

        public Builder setVersion(int i2) {
            copyOnWrite();
            ((JwtRsaSsaPkcs1PrivateKey) this.instance).setVersion(i2);
            return this;
        }

        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder, com.google.crypto.tink.shaded.protobuf.AbstractMessageLite.Builder, com.google.crypto.tink.shaded.protobuf.MessageLite.Builder
        /* JADX INFO: renamed from: clone */
        public /* bridge */ /* synthetic */ MessageLite.Builder mo0clone() {
            return super.mo0clone();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder, com.google.crypto.tink.shaded.protobuf.AbstractMessageLite.Builder, com.google.crypto.tink.shaded.protobuf.MessageLite.Builder
        public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(byte[] bArr, int i2, int i3) {
            return super.mergeFrom(bArr, i2, i3);
        }

        public Builder setPublicKey(JwtRsaSsaPkcs1PublicKey jwtRsaSsaPkcs1PublicKey) {
            copyOnWrite();
            ((JwtRsaSsaPkcs1PrivateKey) this.instance).setPublicKey(jwtRsaSsaPkcs1PublicKey);
            return this;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder, com.google.crypto.tink.shaded.protobuf.AbstractMessageLite.Builder
        /* JADX INFO: renamed from: clone */
        public /* bridge */ /* synthetic */ Object mo0clone() {
            return super.mo0clone();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder, com.google.crypto.tink.shaded.protobuf.AbstractMessageLite.Builder, com.google.crypto.tink.shaded.protobuf.MessageLite.Builder
        public /* bridge */ /* synthetic */ AbstractMessageLite.Builder mergeFrom(byte[] bArr, int i2, int i3, ExtensionRegistryLite extensionRegistryLite) {
            return super.mergeFrom(bArr, i2, i3, extensionRegistryLite);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.AbstractMessageLite.Builder, com.google.crypto.tink.shaded.protobuf.MessageLite.Builder
        public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(ByteString byteString) {
            return super.mergeFrom(byteString);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.AbstractMessageLite.Builder, com.google.crypto.tink.shaded.protobuf.MessageLite.Builder
        public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            return super.mergeFrom(byteString, extensionRegistryLite);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.AbstractMessageLite.Builder, com.google.crypto.tink.shaded.protobuf.MessageLite.Builder
        public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream codedInputStream) {
            return super.mergeFrom(codedInputStream);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder, com.google.crypto.tink.shaded.protobuf.AbstractMessageLite.Builder, com.google.crypto.tink.shaded.protobuf.MessageLite.Builder
        public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            return super.mergeFrom(codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.AbstractMessageLite.Builder, com.google.crypto.tink.shaded.protobuf.MessageLite.Builder
        public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(MessageLite messageLite) {
            return super.mergeFrom(messageLite);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.AbstractMessageLite.Builder, com.google.crypto.tink.shaded.protobuf.MessageLite.Builder
        public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(InputStream inputStream) {
            return super.mergeFrom(inputStream);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.AbstractMessageLite.Builder, com.google.crypto.tink.shaded.protobuf.MessageLite.Builder
        public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            return super.mergeFrom(inputStream, extensionRegistryLite);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.AbstractMessageLite.Builder, com.google.crypto.tink.shaded.protobuf.MessageLite.Builder
        public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] bArr) {
            return super.mergeFrom(bArr);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder, com.google.crypto.tink.shaded.protobuf.AbstractMessageLite.Builder, com.google.crypto.tink.shaded.protobuf.MessageLite.Builder
        public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] bArr, int i2, int i3) {
            return super.mergeFrom(bArr, i2, i3);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite.Builder, com.google.crypto.tink.shaded.protobuf.AbstractMessageLite.Builder, com.google.crypto.tink.shaded.protobuf.MessageLite.Builder
        public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] bArr, int i2, int i3, ExtensionRegistryLite extensionRegistryLite) {
            return super.mergeFrom(bArr, i2, i3, extensionRegistryLite);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.AbstractMessageLite.Builder, com.google.crypto.tink.shaded.protobuf.MessageLite.Builder
        public /* bridge */ /* synthetic */ MessageLite.Builder mergeFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return super.mergeFrom(bArr, extensionRegistryLite);
        }
    }

    static {
        JwtRsaSsaPkcs1PrivateKey jwtRsaSsaPkcs1PrivateKey = new JwtRsaSsaPkcs1PrivateKey();
        DEFAULT_INSTANCE = jwtRsaSsaPkcs1PrivateKey;
        GeneratedMessageLite.registerDefaultInstance(JwtRsaSsaPkcs1PrivateKey.class, jwtRsaSsaPkcs1PrivateKey);
    }

    private JwtRsaSsaPkcs1PrivateKey() {
        ByteString byteString = ByteString.EMPTY;
        this.d_ = byteString;
        this.p_ = byteString;
        this.q_ = byteString;
        this.dp_ = byteString;
        this.dq_ = byteString;
        this.crt_ = byteString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCrt() {
        this.crt_ = getDefaultInstance().getCrt();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearD() {
        this.d_ = getDefaultInstance().getD();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDp() {
        this.dp_ = getDefaultInstance().getDp();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDq() {
        this.dq_ = getDefaultInstance().getDq();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearP() {
        this.p_ = getDefaultInstance().getP();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPublicKey() {
        this.publicKey_ = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearQ() {
        this.q_ = getDefaultInstance().getQ();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearVersion() {
        this.version_ = 0;
    }

    public static JwtRsaSsaPkcs1PrivateKey getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergePublicKey(JwtRsaSsaPkcs1PublicKey jwtRsaSsaPkcs1PublicKey) {
        jwtRsaSsaPkcs1PublicKey.getClass();
        JwtRsaSsaPkcs1PublicKey jwtRsaSsaPkcs1PublicKey2 = this.publicKey_;
        if (jwtRsaSsaPkcs1PublicKey2 != null && jwtRsaSsaPkcs1PublicKey2 != JwtRsaSsaPkcs1PublicKey.getDefaultInstance()) {
            jwtRsaSsaPkcs1PublicKey = JwtRsaSsaPkcs1PublicKey.newBuilder(this.publicKey_).mergeFrom(jwtRsaSsaPkcs1PublicKey).buildPartial();
        }
        this.publicKey_ = jwtRsaSsaPkcs1PublicKey;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static JwtRsaSsaPkcs1PrivateKey parseDelimitedFrom(InputStream inputStream) {
        return (JwtRsaSsaPkcs1PrivateKey) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static JwtRsaSsaPkcs1PrivateKey parseFrom(ByteString byteString) {
        return (JwtRsaSsaPkcs1PrivateKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Parser<JwtRsaSsaPkcs1PrivateKey> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCrt(ByteString byteString) {
        byteString.getClass();
        this.crt_ = byteString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setD(ByteString byteString) {
        byteString.getClass();
        this.d_ = byteString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDp(ByteString byteString) {
        byteString.getClass();
        this.dp_ = byteString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDq(ByteString byteString) {
        byteString.getClass();
        this.dq_ = byteString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setP(ByteString byteString) {
        byteString.getClass();
        this.p_ = byteString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPublicKey(JwtRsaSsaPkcs1PublicKey jwtRsaSsaPkcs1PublicKey) {
        jwtRsaSsaPkcs1PublicKey.getClass();
        this.publicKey_ = jwtRsaSsaPkcs1PublicKey;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setQ(ByteString byteString) {
        byteString.getClass();
        this.q_ = byteString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVersion(int i2) {
        this.version_ = i2;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        AnonymousClass1 anonymousClass1 = null;
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new JwtRsaSsaPkcs1PrivateKey();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\b\u0000\u0000\u0001\b\b\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n\u0004\n\u0005\n\u0006\n\u0007\n\b\n", new Object[]{"version_", "publicKey_", "d_", "p_", "q_", "dp_", "dq_", "crt_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<JwtRsaSsaPkcs1PrivateKey> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (JwtRsaSsaPkcs1PrivateKey.class) {
                        defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                            PARSER = defaultInstanceBasedParser;
                        }
                        break;
                    }
                }
                return defaultInstanceBasedParser;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // com.google.crypto.tink.proto.JwtRsaSsaPkcs1PrivateKeyOrBuilder
    public ByteString getCrt() {
        return this.crt_;
    }

    @Override // com.google.crypto.tink.proto.JwtRsaSsaPkcs1PrivateKeyOrBuilder
    public ByteString getD() {
        return this.d_;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder
    public /* bridge */ /* synthetic */ MessageLite getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override // com.google.crypto.tink.proto.JwtRsaSsaPkcs1PrivateKeyOrBuilder
    public ByteString getDp() {
        return this.dp_;
    }

    @Override // com.google.crypto.tink.proto.JwtRsaSsaPkcs1PrivateKeyOrBuilder
    public ByteString getDq() {
        return this.dq_;
    }

    @Override // com.google.crypto.tink.proto.JwtRsaSsaPkcs1PrivateKeyOrBuilder
    public ByteString getP() {
        return this.p_;
    }

    @Override // com.google.crypto.tink.proto.JwtRsaSsaPkcs1PrivateKeyOrBuilder
    public JwtRsaSsaPkcs1PublicKey getPublicKey() {
        JwtRsaSsaPkcs1PublicKey jwtRsaSsaPkcs1PublicKey = this.publicKey_;
        return jwtRsaSsaPkcs1PublicKey == null ? JwtRsaSsaPkcs1PublicKey.getDefaultInstance() : jwtRsaSsaPkcs1PublicKey;
    }

    @Override // com.google.crypto.tink.proto.JwtRsaSsaPkcs1PrivateKeyOrBuilder
    public ByteString getQ() {
        return this.q_;
    }

    @Override // com.google.crypto.tink.proto.JwtRsaSsaPkcs1PrivateKeyOrBuilder
    public int getVersion() {
        return this.version_;
    }

    @Override // com.google.crypto.tink.proto.JwtRsaSsaPkcs1PrivateKeyOrBuilder
    public boolean hasPublicKey() {
        return this.publicKey_ != null;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLite
    public /* bridge */ /* synthetic */ MessageLite.Builder newBuilderForType() {
        return super.newBuilderForType();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite, com.google.crypto.tink.shaded.protobuf.MessageLite
    public /* bridge */ /* synthetic */ MessageLite.Builder toBuilder() {
        return super.toBuilder();
    }

    public static Builder newBuilder(JwtRsaSsaPkcs1PrivateKey jwtRsaSsaPkcs1PrivateKey) {
        return DEFAULT_INSTANCE.createBuilder(jwtRsaSsaPkcs1PrivateKey);
    }

    public static JwtRsaSsaPkcs1PrivateKey parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (JwtRsaSsaPkcs1PrivateKey) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static JwtRsaSsaPkcs1PrivateKey parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (JwtRsaSsaPkcs1PrivateKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static JwtRsaSsaPkcs1PrivateKey parseFrom(CodedInputStream codedInputStream) {
        return (JwtRsaSsaPkcs1PrivateKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static JwtRsaSsaPkcs1PrivateKey parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (JwtRsaSsaPkcs1PrivateKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static JwtRsaSsaPkcs1PrivateKey parseFrom(InputStream inputStream) {
        return (JwtRsaSsaPkcs1PrivateKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static JwtRsaSsaPkcs1PrivateKey parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (JwtRsaSsaPkcs1PrivateKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static JwtRsaSsaPkcs1PrivateKey parseFrom(ByteBuffer byteBuffer) {
        return (JwtRsaSsaPkcs1PrivateKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static JwtRsaSsaPkcs1PrivateKey parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (JwtRsaSsaPkcs1PrivateKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static JwtRsaSsaPkcs1PrivateKey parseFrom(byte[] bArr) {
        return (JwtRsaSsaPkcs1PrivateKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static JwtRsaSsaPkcs1PrivateKey parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (JwtRsaSsaPkcs1PrivateKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }
}
