package com.google.crypto.tink.shaded.protobuf;

import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@CheckReturnValue
interface Writer {

    public enum FieldOrder {
        ASCENDING,
        DESCENDING
    }

    FieldOrder fieldOrder();

    void writeBool(int i2, boolean z2);

    void writeBoolList(int i2, List<Boolean> list, boolean z2);

    void writeBytes(int i2, ByteString byteString);

    void writeBytesList(int i2, List<ByteString> list);

    void writeDouble(int i2, double d2);

    void writeDoubleList(int i2, List<Double> list, boolean z2);

    @Deprecated
    void writeEndGroup(int i2);

    void writeEnum(int i2, int i3);

    void writeEnumList(int i2, List<Integer> list, boolean z2);

    void writeFixed32(int i2, int i3);

    void writeFixed32List(int i2, List<Integer> list, boolean z2);

    void writeFixed64(int i2, long j2);

    void writeFixed64List(int i2, List<Long> list, boolean z2);

    void writeFloat(int i2, float f2);

    void writeFloatList(int i2, List<Float> list, boolean z2);

    @Deprecated
    void writeGroup(int i2, Object obj);

    @Deprecated
    void writeGroup(int i2, Object obj, Schema schema);

    @Deprecated
    void writeGroupList(int i2, List<?> list);

    @Deprecated
    void writeGroupList(int i2, List<?> list, Schema schema);

    void writeInt32(int i2, int i3);

    void writeInt32List(int i2, List<Integer> list, boolean z2);

    void writeInt64(int i2, long j2);

    void writeInt64List(int i2, List<Long> list, boolean z2);

    <K, V> void writeMap(int i2, MapEntryLite.Metadata<K, V> metadata, Map<K, V> map);

    void writeMessage(int i2, Object obj);

    void writeMessage(int i2, Object obj, Schema schema);

    void writeMessageList(int i2, List<?> list);

    void writeMessageList(int i2, List<?> list, Schema schema);

    void writeMessageSetItem(int i2, Object obj);

    void writeSFixed32(int i2, int i3);

    void writeSFixed32List(int i2, List<Integer> list, boolean z2);

    void writeSFixed64(int i2, long j2);

    void writeSFixed64List(int i2, List<Long> list, boolean z2);

    void writeSInt32(int i2, int i3);

    void writeSInt32List(int i2, List<Integer> list, boolean z2);

    void writeSInt64(int i2, long j2);

    void writeSInt64List(int i2, List<Long> list, boolean z2);

    @Deprecated
    void writeStartGroup(int i2);

    void writeString(int i2, String str);

    void writeStringList(int i2, List<String> list);

    void writeUInt32(int i2, int i3);

    void writeUInt32List(int i2, List<Integer> list, boolean z2);

    void writeUInt64(int i2, long j2);

    void writeUInt64List(int i2, List<Long> list, boolean z2);
}
