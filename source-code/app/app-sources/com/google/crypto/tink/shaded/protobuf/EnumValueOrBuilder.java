package com.google.crypto.tink.shaded.protobuf;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface EnumValueOrBuilder extends MessageLiteOrBuilder {
    String getName();

    ByteString getNameBytes();

    int getNumber();

    Option getOptions(int i2);

    int getOptionsCount();

    List<Option> getOptionsList();
}
