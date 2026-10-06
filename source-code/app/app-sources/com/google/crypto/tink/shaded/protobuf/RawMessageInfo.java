package com.google.crypto.tink.shaded.protobuf;

/* JADX INFO: loaded from: classes.dex */
@CheckReturnValue
final class RawMessageInfo implements MessageInfo {
    private final MessageLite defaultInstance;
    private final int flags;
    private final String info;
    private final Object[] objects;

    public RawMessageInfo(MessageLite messageLite, String str, Object[] objArr) {
        char cCharAt;
        this.defaultInstance = messageLite;
        this.info = str;
        this.objects = objArr;
        int iCharAt = str.charAt(0);
        if (iCharAt >= 55296) {
            int i2 = iCharAt & 8191;
            int i3 = 13;
            int i4 = 1;
            while (true) {
                int i5 = i4 + 1;
                cCharAt = str.charAt(i4);
                if (cCharAt < 55296) {
                    break;
                }
                i2 |= (cCharAt & 8191) << i3;
                i3 += 13;
                i4 = i5;
            }
            iCharAt = i2 | (cCharAt << i3);
        }
        this.flags = iCharAt;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.MessageInfo
    public MessageLite getDefaultInstance() {
        return this.defaultInstance;
    }

    public Object[] getObjects() {
        return this.objects;
    }

    public String getStringInfo() {
        return this.info;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.MessageInfo
    public ProtoSyntax getSyntax() {
        return (this.flags & 1) == 1 ? ProtoSyntax.PROTO2 : ProtoSyntax.PROTO3;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.MessageInfo
    public boolean isMessageSetWireFormat() {
        return (this.flags & 2) == 2;
    }
}
