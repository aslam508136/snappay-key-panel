package com.google.crypto.tink.shaded.protobuf;

import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes.dex */
@CheckReturnValue
final class SchemaUtil {
    private static final int DEFAULT_LOOK_UP_START_NUMBER = 40;
    private static final Class<?> GENERATED_MESSAGE_CLASS = getGeneratedMessageClass();
    private static final UnknownFieldSchema<?, ?> PROTO2_UNKNOWN_FIELD_SET_SCHEMA = getUnknownFieldSetSchema(false);
    private static final UnknownFieldSchema<?, ?> PROTO3_UNKNOWN_FIELD_SET_SCHEMA = getUnknownFieldSetSchema(true);
    private static final UnknownFieldSchema<?, ?> UNKNOWN_FIELD_SET_LITE_SCHEMA = new UnknownFieldSetLiteSchema();

    private SchemaUtil() {
    }

    public static int computeSizeBoolList(int i2, List<?> list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (!z2) {
            return CodedOutputStream.computeBoolSize(i2, true) * size;
        }
        return CodedOutputStream.computeLengthDelimitedFieldSize(size) + CodedOutputStream.computeTagSize(i2);
    }

    public static int computeSizeBoolListNoTag(List<?> list) {
        return list.size();
    }

    public static int computeSizeByteStringList(int i2, List<ByteString> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iComputeTagSize = CodedOutputStream.computeTagSize(i2) * size;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iComputeTagSize += CodedOutputStream.computeBytesSizeNoTag(list.get(i3));
        }
        return iComputeTagSize;
    }

    public static int computeSizeEnumList(int i2, List<Integer> list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iComputeSizeEnumListNoTag = computeSizeEnumListNoTag(list);
        int iComputeTagSize = CodedOutputStream.computeTagSize(i2);
        return z2 ? CodedOutputStream.computeLengthDelimitedFieldSize(iComputeSizeEnumListNoTag) + iComputeTagSize : (iComputeTagSize * size) + iComputeSizeEnumListNoTag;
    }

    public static int computeSizeEnumListNoTag(List<Integer> list) {
        int iComputeEnumSizeNoTag;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) list;
            iComputeEnumSizeNoTag = 0;
            while (i2 < size) {
                iComputeEnumSizeNoTag += CodedOutputStream.computeEnumSizeNoTag(intArrayList.getInt(i2));
                i2++;
            }
        } else {
            iComputeEnumSizeNoTag = 0;
            while (i2 < size) {
                iComputeEnumSizeNoTag += CodedOutputStream.computeEnumSizeNoTag(list.get(i2).intValue());
                i2++;
            }
        }
        return iComputeEnumSizeNoTag;
    }

    public static int computeSizeFixed32List(int i2, List<?> list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (!z2) {
            return CodedOutputStream.computeFixed32Size(i2, 0) * size;
        }
        return CodedOutputStream.computeLengthDelimitedFieldSize(size * 4) + CodedOutputStream.computeTagSize(i2);
    }

    public static int computeSizeFixed32ListNoTag(List<?> list) {
        return list.size() * 4;
    }

    public static int computeSizeFixed64List(int i2, List<?> list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (!z2) {
            return CodedOutputStream.computeFixed64Size(i2, 0L) * size;
        }
        return CodedOutputStream.computeLengthDelimitedFieldSize(size * 8) + CodedOutputStream.computeTagSize(i2);
    }

    public static int computeSizeFixed64ListNoTag(List<?> list) {
        return list.size() * 8;
    }

    public static int computeSizeGroupList(int i2, List<MessageLite> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iComputeGroupSize = 0;
        for (int i3 = 0; i3 < size; i3++) {
            iComputeGroupSize += CodedOutputStream.computeGroupSize(i2, list.get(i3));
        }
        return iComputeGroupSize;
    }

    public static int computeSizeInt32List(int i2, List<Integer> list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iComputeSizeInt32ListNoTag = computeSizeInt32ListNoTag(list);
        int iComputeTagSize = CodedOutputStream.computeTagSize(i2);
        return z2 ? CodedOutputStream.computeLengthDelimitedFieldSize(iComputeSizeInt32ListNoTag) + iComputeTagSize : (iComputeTagSize * size) + iComputeSizeInt32ListNoTag;
    }

    public static int computeSizeInt32ListNoTag(List<Integer> list) {
        int iComputeInt32SizeNoTag;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) list;
            iComputeInt32SizeNoTag = 0;
            while (i2 < size) {
                iComputeInt32SizeNoTag += CodedOutputStream.computeInt32SizeNoTag(intArrayList.getInt(i2));
                i2++;
            }
        } else {
            iComputeInt32SizeNoTag = 0;
            while (i2 < size) {
                iComputeInt32SizeNoTag += CodedOutputStream.computeInt32SizeNoTag(list.get(i2).intValue());
                i2++;
            }
        }
        return iComputeInt32SizeNoTag;
    }

    public static int computeSizeInt64List(int i2, List<Long> list, boolean z2) {
        if (list.size() == 0) {
            return 0;
        }
        int iComputeSizeInt64ListNoTag = computeSizeInt64ListNoTag(list);
        if (z2) {
            return CodedOutputStream.computeLengthDelimitedFieldSize(iComputeSizeInt64ListNoTag) + CodedOutputStream.computeTagSize(i2);
        }
        return (CodedOutputStream.computeTagSize(i2) * list.size()) + iComputeSizeInt64ListNoTag;
    }

    public static int computeSizeInt64ListNoTag(List<Long> list) {
        int iComputeInt64SizeNoTag;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof LongArrayList) {
            LongArrayList longArrayList = (LongArrayList) list;
            iComputeInt64SizeNoTag = 0;
            while (i2 < size) {
                iComputeInt64SizeNoTag += CodedOutputStream.computeInt64SizeNoTag(longArrayList.getLong(i2));
                i2++;
            }
        } else {
            iComputeInt64SizeNoTag = 0;
            while (i2 < size) {
                iComputeInt64SizeNoTag += CodedOutputStream.computeInt64SizeNoTag(list.get(i2).longValue());
                i2++;
            }
        }
        return iComputeInt64SizeNoTag;
    }

    public static int computeSizeMessage(int i2, Object obj, Schema schema) {
        return obj instanceof LazyFieldLite ? CodedOutputStream.computeLazyFieldSize(i2, (LazyFieldLite) obj) : CodedOutputStream.computeMessageSize(i2, (MessageLite) obj, schema);
    }

    public static int computeSizeMessageList(int i2, List<?> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iComputeTagSize = CodedOutputStream.computeTagSize(i2) * size;
        for (int i3 = 0; i3 < size; i3++) {
            Object obj = list.get(i3);
            iComputeTagSize = (obj instanceof LazyFieldLite ? CodedOutputStream.computeLazyFieldSizeNoTag((LazyFieldLite) obj) : CodedOutputStream.computeMessageSizeNoTag((MessageLite) obj)) + iComputeTagSize;
        }
        return iComputeTagSize;
    }

    public static int computeSizeSInt32List(int i2, List<Integer> list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iComputeSizeSInt32ListNoTag = computeSizeSInt32ListNoTag(list);
        int iComputeTagSize = CodedOutputStream.computeTagSize(i2);
        return z2 ? CodedOutputStream.computeLengthDelimitedFieldSize(iComputeSizeSInt32ListNoTag) + iComputeTagSize : (iComputeTagSize * size) + iComputeSizeSInt32ListNoTag;
    }

    public static int computeSizeSInt32ListNoTag(List<Integer> list) {
        int iComputeSInt32SizeNoTag;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) list;
            iComputeSInt32SizeNoTag = 0;
            while (i2 < size) {
                iComputeSInt32SizeNoTag += CodedOutputStream.computeSInt32SizeNoTag(intArrayList.getInt(i2));
                i2++;
            }
        } else {
            iComputeSInt32SizeNoTag = 0;
            while (i2 < size) {
                iComputeSInt32SizeNoTag += CodedOutputStream.computeSInt32SizeNoTag(list.get(i2).intValue());
                i2++;
            }
        }
        return iComputeSInt32SizeNoTag;
    }

    public static int computeSizeSInt64List(int i2, List<Long> list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iComputeSizeSInt64ListNoTag = computeSizeSInt64ListNoTag(list);
        int iComputeTagSize = CodedOutputStream.computeTagSize(i2);
        return z2 ? CodedOutputStream.computeLengthDelimitedFieldSize(iComputeSizeSInt64ListNoTag) + iComputeTagSize : (iComputeTagSize * size) + iComputeSizeSInt64ListNoTag;
    }

    public static int computeSizeSInt64ListNoTag(List<Long> list) {
        int iComputeSInt64SizeNoTag;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof LongArrayList) {
            LongArrayList longArrayList = (LongArrayList) list;
            iComputeSInt64SizeNoTag = 0;
            while (i2 < size) {
                iComputeSInt64SizeNoTag += CodedOutputStream.computeSInt64SizeNoTag(longArrayList.getLong(i2));
                i2++;
            }
        } else {
            iComputeSInt64SizeNoTag = 0;
            while (i2 < size) {
                iComputeSInt64SizeNoTag += CodedOutputStream.computeSInt64SizeNoTag(list.get(i2).longValue());
                i2++;
            }
        }
        return iComputeSInt64SizeNoTag;
    }

    public static int computeSizeStringList(int i2, List<?> list) {
        int size = list.size();
        int i3 = 0;
        if (size == 0) {
            return 0;
        }
        int iComputeTagSize = CodedOutputStream.computeTagSize(i2) * size;
        if (list instanceof LazyStringList) {
            LazyStringList lazyStringList = (LazyStringList) list;
            while (i3 < size) {
                Object raw = lazyStringList.getRaw(i3);
                iComputeTagSize = (raw instanceof ByteString ? CodedOutputStream.computeBytesSizeNoTag((ByteString) raw) : CodedOutputStream.computeStringSizeNoTag((String) raw)) + iComputeTagSize;
                i3++;
            }
        } else {
            while (i3 < size) {
                Object obj = list.get(i3);
                iComputeTagSize = (obj instanceof ByteString ? CodedOutputStream.computeBytesSizeNoTag((ByteString) obj) : CodedOutputStream.computeStringSizeNoTag((String) obj)) + iComputeTagSize;
                i3++;
            }
        }
        return iComputeTagSize;
    }

    public static int computeSizeUInt32List(int i2, List<Integer> list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iComputeSizeUInt32ListNoTag = computeSizeUInt32ListNoTag(list);
        int iComputeTagSize = CodedOutputStream.computeTagSize(i2);
        return z2 ? CodedOutputStream.computeLengthDelimitedFieldSize(iComputeSizeUInt32ListNoTag) + iComputeTagSize : (iComputeTagSize * size) + iComputeSizeUInt32ListNoTag;
    }

    public static int computeSizeUInt32ListNoTag(List<Integer> list) {
        int iComputeUInt32SizeNoTag;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) list;
            iComputeUInt32SizeNoTag = 0;
            while (i2 < size) {
                iComputeUInt32SizeNoTag += CodedOutputStream.computeUInt32SizeNoTag(intArrayList.getInt(i2));
                i2++;
            }
        } else {
            iComputeUInt32SizeNoTag = 0;
            while (i2 < size) {
                iComputeUInt32SizeNoTag += CodedOutputStream.computeUInt32SizeNoTag(list.get(i2).intValue());
                i2++;
            }
        }
        return iComputeUInt32SizeNoTag;
    }

    public static int computeSizeUInt64List(int i2, List<Long> list, boolean z2) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iComputeSizeUInt64ListNoTag = computeSizeUInt64ListNoTag(list);
        int iComputeTagSize = CodedOutputStream.computeTagSize(i2);
        return z2 ? CodedOutputStream.computeLengthDelimitedFieldSize(iComputeSizeUInt64ListNoTag) + iComputeTagSize : (iComputeTagSize * size) + iComputeSizeUInt64ListNoTag;
    }

    public static int computeSizeUInt64ListNoTag(List<Long> list) {
        int iComputeUInt64SizeNoTag;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof LongArrayList) {
            LongArrayList longArrayList = (LongArrayList) list;
            iComputeUInt64SizeNoTag = 0;
            while (i2 < size) {
                iComputeUInt64SizeNoTag += CodedOutputStream.computeUInt64SizeNoTag(longArrayList.getLong(i2));
                i2++;
            }
        } else {
            iComputeUInt64SizeNoTag = 0;
            while (i2 < size) {
                iComputeUInt64SizeNoTag += CodedOutputStream.computeUInt64SizeNoTag(list.get(i2).longValue());
                i2++;
            }
        }
        return iComputeUInt64SizeNoTag;
    }

    @CanIgnoreReturnValue
    public static <UT, UB> UB filterUnknownEnumList(Object obj, int i2, List<Integer> list, Internal.EnumLiteMap<?> enumLiteMap, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema) {
        if (enumLiteMap == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i3 = 0;
            for (int i4 = 0; i4 < size; i4++) {
                int iIntValue = list.get(i4).intValue();
                if (enumLiteMap.findValueByNumber(iIntValue) != null) {
                    if (i4 != i3) {
                        list.set(i3, Integer.valueOf(iIntValue));
                    }
                    i3++;
                } else {
                    ub = (UB) storeUnknownEnum(obj, i2, iIntValue, ub, unknownFieldSchema);
                }
            }
            if (i3 != size) {
                list.subList(i3, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int iIntValue2 = it.next().intValue();
                if (enumLiteMap.findValueByNumber(iIntValue2) == null) {
                    ub = (UB) storeUnknownEnum(obj, i2, iIntValue2, ub, unknownFieldSchema);
                    it.remove();
                }
            }
        }
        return ub;
    }

    private static Class<?> getGeneratedMessageClass() {
        try {
            return Class.forName("com.google.crypto.tink.shaded.protobuf.GeneratedMessageV3");
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Object getMapDefaultEntry(Class<?> cls, String str) {
        try {
            java.lang.reflect.Field[] declaredFields = Class.forName(cls.getName() + "$" + toCamelCase(str, true) + "DefaultEntryHolder").getDeclaredFields();
            if (declaredFields.length == 1) {
                return UnsafeUtil.getStaticObject(declaredFields[0]);
            }
            throw new IllegalStateException("Unable to look up map field default entry holder class for " + str + " in " + cls.getName());
        } catch (Throwable th) {
            throw new RuntimeException(th);
        }
    }

    private static UnknownFieldSchema<?, ?> getUnknownFieldSetSchema(boolean z2) {
        try {
            Class<?> unknownFieldSetSchemaClass = getUnknownFieldSetSchemaClass();
            if (unknownFieldSetSchemaClass == null) {
                return null;
            }
            return (UnknownFieldSchema) unknownFieldSetSchemaClass.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z2));
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> getUnknownFieldSetSchemaClass() {
        try {
            return Class.forName("com.google.crypto.tink.shaded.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }

    public static <T, FT extends FieldSet.FieldDescriptorLite<FT>> void mergeExtensions(ExtensionSchema<FT> extensionSchema, T t2, T t3) {
        FieldSet<T> extensions = extensionSchema.getExtensions(t3);
        if (extensions.isEmpty()) {
            return;
        }
        extensionSchema.getMutableExtensions(t2).mergeFrom(extensions);
    }

    public static <T> void mergeMap(MapFieldSchema mapFieldSchema, T t2, T t3, long j2) {
        UnsafeUtil.putObject(t2, j2, mapFieldSchema.mergeFrom(UnsafeUtil.getObject(t2, j2), UnsafeUtil.getObject(t3, j2)));
    }

    public static <T, UT, UB> void mergeUnknownFields(UnknownFieldSchema<UT, UB> unknownFieldSchema, T t2, T t3) {
        unknownFieldSchema.setToMessage(t2, unknownFieldSchema.merge(unknownFieldSchema.getFromMessage(t2), unknownFieldSchema.getFromMessage(t3)));
    }

    public static UnknownFieldSchema<?, ?> proto2UnknownFieldSetSchema() {
        return PROTO2_UNKNOWN_FIELD_SET_SCHEMA;
    }

    public static UnknownFieldSchema<?, ?> proto3UnknownFieldSetSchema() {
        return PROTO3_UNKNOWN_FIELD_SET_SCHEMA;
    }

    public static void requireGeneratedMessage(Class<?> cls) {
        Class<?> cls2;
        if (!GeneratedMessageLite.class.isAssignableFrom(cls) && (cls2 = GENERATED_MESSAGE_CLASS) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessageV3 or GeneratedMessageLite");
        }
    }

    public static boolean safeEquals(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static boolean shouldUseTableSwitch(int i2, int i3, int i4) {
        if (i3 < DEFAULT_LOOK_UP_START_NUMBER) {
            return true;
        }
        long j2 = (((long) i3) - ((long) i2)) + 1;
        long j3 = i4;
        return j2 + 9 <= ((j3 + 3) * 3) + ((2 * j3) + 3);
    }

    @CanIgnoreReturnValue
    public static <UT, UB> UB storeUnknownEnum(Object obj, int i2, int i3, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema) {
        if (ub == null) {
            ub = unknownFieldSchema.getBuilderFromMessage(obj);
        }
        unknownFieldSchema.addVarint(ub, i2, i3);
        return ub;
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0022  */
    public static String toCamelCase(String str, boolean z2) {
        int i2;
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < str.length(); i3++) {
            char cCharAt = str.charAt(i3);
            if ('a' <= cCharAt && cCharAt <= 'z') {
                if (z2) {
                    i2 = cCharAt - ' ';
                    sb.append((char) i2);
                } else {
                    sb.append(cCharAt);
                }
                z2 = false;
            } else if ('A' > cCharAt || cCharAt > 'Z') {
                if ('0' <= cCharAt && cCharAt <= '9') {
                    sb.append(cCharAt);
                }
                z2 = true;
            } else {
                if (i3 != 0 || z2) {
                    sb.append(cCharAt);
                } else {
                    i2 = cCharAt + ' ';
                    sb.append((char) i2);
                }
                z2 = false;
            }
        }
        return sb.toString();
    }

    public static UnknownFieldSchema<?, ?> unknownFieldSetLiteSchema() {
        return UNKNOWN_FIELD_SET_LITE_SCHEMA;
    }

    public static void writeBool(int i2, boolean z2, Writer writer) {
        if (z2) {
            writer.writeBool(i2, true);
        }
    }

    public static void writeBoolList(int i2, List<Boolean> list, Writer writer, boolean z2) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeBoolList(i2, list, z2);
    }

    public static void writeBytes(int i2, ByteString byteString, Writer writer) {
        if (byteString == null || byteString.isEmpty()) {
            return;
        }
        writer.writeBytes(i2, byteString);
    }

    public static void writeBytesList(int i2, List<ByteString> list, Writer writer) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeBytesList(i2, list);
    }

    public static void writeDouble(int i2, double d2, Writer writer) {
        if (Double.doubleToRawLongBits(d2) != 0) {
            writer.writeDouble(i2, d2);
        }
    }

    public static void writeDoubleList(int i2, List<Double> list, Writer writer, boolean z2) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeDoubleList(i2, list, z2);
    }

    public static void writeEnum(int i2, int i3, Writer writer) {
        if (i3 != 0) {
            writer.writeEnum(i2, i3);
        }
    }

    public static void writeEnumList(int i2, List<Integer> list, Writer writer, boolean z2) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeEnumList(i2, list, z2);
    }

    public static void writeFixed32(int i2, int i3, Writer writer) {
        if (i3 != 0) {
            writer.writeFixed32(i2, i3);
        }
    }

    public static void writeFixed32List(int i2, List<Integer> list, Writer writer, boolean z2) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeFixed32List(i2, list, z2);
    }

    public static void writeFixed64(int i2, long j2, Writer writer) {
        if (j2 != 0) {
            writer.writeFixed64(i2, j2);
        }
    }

    public static void writeFixed64List(int i2, List<Long> list, Writer writer, boolean z2) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeFixed64List(i2, list, z2);
    }

    public static void writeFloat(int i2, float f2, Writer writer) {
        if (Float.floatToRawIntBits(f2) != 0) {
            writer.writeFloat(i2, f2);
        }
    }

    public static void writeFloatList(int i2, List<Float> list, Writer writer, boolean z2) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeFloatList(i2, list, z2);
    }

    public static void writeGroupList(int i2, List<?> list, Writer writer) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeGroupList(i2, list);
    }

    public static void writeInt32(int i2, int i3, Writer writer) {
        if (i3 != 0) {
            writer.writeInt32(i2, i3);
        }
    }

    public static void writeInt32List(int i2, List<Integer> list, Writer writer, boolean z2) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeInt32List(i2, list, z2);
    }

    public static void writeInt64(int i2, long j2, Writer writer) {
        if (j2 != 0) {
            writer.writeInt64(i2, j2);
        }
    }

    public static void writeInt64List(int i2, List<Long> list, Writer writer, boolean z2) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeInt64List(i2, list, z2);
    }

    public static void writeLazyFieldList(int i2, List<?> list, Writer writer) {
        if (list == null || list.isEmpty()) {
            return;
        }
        Iterator<?> it = list.iterator();
        while (it.hasNext()) {
            ((LazyFieldLite) it.next()).writeTo(writer, i2);
        }
    }

    public static void writeMessage(int i2, Object obj, Writer writer) {
        if (obj != null) {
            writer.writeMessage(i2, obj);
        }
    }

    public static void writeMessageList(int i2, List<?> list, Writer writer) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeMessageList(i2, list);
    }

    public static void writeSFixed32(int i2, int i3, Writer writer) {
        if (i3 != 0) {
            writer.writeSFixed32(i2, i3);
        }
    }

    public static void writeSFixed32List(int i2, List<Integer> list, Writer writer, boolean z2) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeSFixed32List(i2, list, z2);
    }

    public static void writeSFixed64(int i2, long j2, Writer writer) {
        if (j2 != 0) {
            writer.writeSFixed64(i2, j2);
        }
    }

    public static void writeSFixed64List(int i2, List<Long> list, Writer writer, boolean z2) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeSFixed64List(i2, list, z2);
    }

    public static void writeSInt32(int i2, int i3, Writer writer) {
        if (i3 != 0) {
            writer.writeSInt32(i2, i3);
        }
    }

    public static void writeSInt32List(int i2, List<Integer> list, Writer writer, boolean z2) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeSInt32List(i2, list, z2);
    }

    public static void writeSInt64(int i2, long j2, Writer writer) {
        if (j2 != 0) {
            writer.writeSInt64(i2, j2);
        }
    }

    public static void writeSInt64List(int i2, List<Long> list, Writer writer, boolean z2) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeSInt64List(i2, list, z2);
    }

    public static void writeString(int i2, Object obj, Writer writer) {
        if (obj instanceof String) {
            writeStringInternal(i2, (String) obj, writer);
        } else {
            writeBytes(i2, (ByteString) obj, writer);
        }
    }

    private static void writeStringInternal(int i2, String str, Writer writer) {
        if (str == null || str.isEmpty()) {
            return;
        }
        writer.writeString(i2, str);
    }

    public static void writeStringList(int i2, List<String> list, Writer writer) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeStringList(i2, list);
    }

    public static void writeUInt32(int i2, int i3, Writer writer) {
        if (i3 != 0) {
            writer.writeUInt32(i2, i3);
        }
    }

    public static void writeUInt32List(int i2, List<Integer> list, Writer writer, boolean z2) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeUInt32List(i2, list, z2);
    }

    public static void writeUInt64(int i2, long j2, Writer writer) {
        if (j2 != 0) {
            writer.writeUInt64(i2, j2);
        }
    }

    public static void writeUInt64List(int i2, List<Long> list, Writer writer, boolean z2) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeUInt64List(i2, list, z2);
    }

    public static int computeSizeGroupList(int i2, List<MessageLite> list, Schema schema) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iComputeGroupSize = 0;
        for (int i3 = 0; i3 < size; i3++) {
            iComputeGroupSize += CodedOutputStream.computeGroupSize(i2, list.get(i3), schema);
        }
        return iComputeGroupSize;
    }

    public static int computeSizeMessageList(int i2, List<?> list, Schema schema) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iComputeTagSize = CodedOutputStream.computeTagSize(i2) * size;
        for (int i3 = 0; i3 < size; i3++) {
            Object obj = list.get(i3);
            iComputeTagSize = (obj instanceof LazyFieldLite ? CodedOutputStream.computeLazyFieldSizeNoTag((LazyFieldLite) obj) : CodedOutputStream.computeMessageSizeNoTag((MessageLite) obj, schema)) + iComputeTagSize;
        }
        return iComputeTagSize;
    }

    @CanIgnoreReturnValue
    public static <UT, UB> UB filterUnknownEnumList(Object obj, int i2, List<Integer> list, Internal.EnumVerifier enumVerifier, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema) {
        if (enumVerifier == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i3 = 0;
            for (int i4 = 0; i4 < size; i4++) {
                int iIntValue = list.get(i4).intValue();
                if (enumVerifier.isInRange(iIntValue)) {
                    if (i4 != i3) {
                        list.set(i3, Integer.valueOf(iIntValue));
                    }
                    i3++;
                } else {
                    ub = (UB) storeUnknownEnum(obj, i2, iIntValue, ub, unknownFieldSchema);
                }
            }
            if (i3 != size) {
                list.subList(i3, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int iIntValue2 = it.next().intValue();
                if (!enumVerifier.isInRange(iIntValue2)) {
                    ub = (UB) storeUnknownEnum(obj, i2, iIntValue2, ub, unknownFieldSchema);
                    it.remove();
                }
            }
        }
        return ub;
    }

    public static boolean shouldUseTableSwitch(FieldInfo[] fieldInfoArr) {
        if (fieldInfoArr.length == 0) {
            return false;
        }
        return shouldUseTableSwitch(fieldInfoArr[0].getFieldNumber(), fieldInfoArr[fieldInfoArr.length - 1].getFieldNumber(), fieldInfoArr.length);
    }

    public static void writeGroupList(int i2, List<?> list, Writer writer, Schema schema) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeGroupList(i2, list, schema);
    }

    public static void writeMessageList(int i2, List<?> list, Writer writer, Schema schema) {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.writeMessageList(i2, list, schema);
    }
}
