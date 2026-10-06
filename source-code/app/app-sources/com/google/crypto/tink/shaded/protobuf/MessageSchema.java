package com.google.crypto.tink.shaded.protobuf;

import androidx.security.crypto.MasterKey;
import com.google.crypto.tink.aead.internal.InsecureNonceXChaCha20;
import com.google.crypto.tink.subtle.Base64;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes.dex */
@CheckReturnValue
final class MessageSchema<T> implements Schema<T> {
    private static final int ENFORCE_UTF8_MASK = 536870912;
    private static final int FIELD_TYPE_MASK = 267386880;
    private static final int INTS_PER_FIELD = 3;
    private static final int NO_PRESENCE_SENTINEL = 1048575;
    private static final int OFFSET_BITS = 20;
    private static final int OFFSET_MASK = 1048575;
    static final int ONEOF_TYPE_OFFSET = 51;
    private static final int REQUIRED_MASK = 268435456;
    private final int[] buffer;
    private final int checkInitializedCount;
    private final MessageLite defaultInstance;
    private final ExtensionSchema<?> extensionSchema;
    private final boolean hasExtensions;
    private final int[] intArray;
    private final ListFieldSchema listFieldSchema;
    private final boolean lite;
    private final MapFieldSchema mapFieldSchema;
    private final int maxFieldNumber;
    private final int minFieldNumber;
    private final NewInstanceSchema newInstanceSchema;
    private final Object[] objects;
    private final boolean proto3;
    private final int repeatedFieldOffsetStart;
    private final UnknownFieldSchema<?, ?> unknownFieldSchema;
    private final boolean useCachedSizeField;
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    private static final Unsafe UNSAFE = UnsafeUtil.getUnsafe();

    /* JADX INFO: renamed from: com.google.crypto.tink.shaded.protobuf.MessageSchema$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            $SwitchMap$com$google$protobuf$WireFormat$FieldType = iArr;
            try {
                iArr[WireFormat.FieldType.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BYTES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.DOUBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED32.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED64.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FLOAT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.ENUM.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT32.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT32.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT64.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT64.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.MESSAGE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT32.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT64.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.STRING.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    private MessageSchema(int[] iArr, Object[] objArr, int i2, int i3, MessageLite messageLite, boolean z2, boolean z3, int[] iArr2, int i4, int i5, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        this.buffer = iArr;
        this.objects = objArr;
        this.minFieldNumber = i2;
        this.maxFieldNumber = i3;
        this.lite = messageLite instanceof GeneratedMessageLite;
        this.proto3 = z2;
        this.hasExtensions = extensionSchema != null && extensionSchema.hasExtensions(messageLite);
        this.useCachedSizeField = z3;
        this.intArray = iArr2;
        this.checkInitializedCount = i4;
        this.repeatedFieldOffsetStart = i5;
        this.newInstanceSchema = newInstanceSchema;
        this.listFieldSchema = listFieldSchema;
        this.unknownFieldSchema = unknownFieldSchema;
        this.extensionSchema = extensionSchema;
        this.defaultInstance = messageLite;
        this.mapFieldSchema = mapFieldSchema;
    }

    private boolean arePresentForEquals(T t2, T t3, int i2) {
        return isFieldPresent(t2, i2) == isFieldPresent(t3, i2);
    }

    private static <T> boolean booleanAt(T t2, long j2) {
        return UnsafeUtil.getBoolean(t2, j2);
    }

    private static void checkMutable(Object obj) {
        if (isMutable(obj)) {
            return;
        }
        throw new IllegalArgumentException("Mutating immutable message: " + obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <K, V> int decodeMapEntry(byte[] bArr, int i2, int i3, MapEntryLite.Metadata<K, V> metadata, Map<K, V> map, ArrayDecoders.Registers registers) throws InvalidProtocolBufferException {
        int iDecodeVarint32;
        int iDecodeVarint33 = ArrayDecoders.decodeVarint32(bArr, i2, registers);
        int i4 = registers.int1;
        if (i4 < 0 || i4 > i3 - iDecodeVarint33) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        int i5 = iDecodeVarint33 + i4;
        Object obj = metadata.defaultKey;
        Object obj2 = metadata.defaultValue;
        while (iDecodeVarint33 < i5) {
            int i6 = iDecodeVarint33 + 1;
            int i7 = bArr[iDecodeVarint33];
            if (i7 < 0) {
                iDecodeVarint32 = ArrayDecoders.decodeVarint32(i7, bArr, i6, registers);
                i7 = registers.int1;
            } else {
                iDecodeVarint32 = i6;
            }
            int i8 = i7 >>> 3;
            int i9 = i7 & 7;
            if (i8 != 1) {
                if (i8 == 2 && i9 == metadata.valueType.getWireType()) {
                    iDecodeVarint33 = decodeMapEntryValue(bArr, iDecodeVarint32, i3, metadata.valueType, metadata.defaultValue.getClass(), registers);
                    obj2 = registers.object1;
                } else {
                    iDecodeVarint33 = ArrayDecoders.skipField(i7, bArr, iDecodeVarint32, i3, registers);
                }
            } else if (i9 == metadata.keyType.getWireType()) {
                iDecodeVarint33 = decodeMapEntryValue(bArr, iDecodeVarint32, i3, metadata.keyType, null, registers);
                obj = registers.object1;
            } else {
                iDecodeVarint33 = ArrayDecoders.skipField(i7, bArr, iDecodeVarint32, i3, registers);
            }
        }
        if (iDecodeVarint33 != i5) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        map.put(obj, obj2);
        return i5;
    }

    private int decodeMapEntryValue(byte[] bArr, int i2, int i3, WireFormat.FieldType fieldType, Class<?> cls, ArrayDecoders.Registers registers) {
        int iDecodeVarint64;
        Object objValueOf;
        Object objValueOf2;
        Object objValueOf3;
        int iDecodeZigZag32;
        long jDecodeZigZag64;
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[fieldType.ordinal()]) {
            case 1:
                iDecodeVarint64 = ArrayDecoders.decodeVarint64(bArr, i2, registers);
                objValueOf = Boolean.valueOf(registers.long1 != 0);
                registers.object1 = objValueOf;
                return iDecodeVarint64;
            case 2:
                return ArrayDecoders.decodeBytes(bArr, i2, registers);
            case 3:
                objValueOf2 = Double.valueOf(ArrayDecoders.decodeDouble(bArr, i2));
                registers.object1 = objValueOf2;
                return i2 + 8;
            case 4:
            case 5:
                objValueOf3 = Integer.valueOf(ArrayDecoders.decodeFixed32(bArr, i2));
                registers.object1 = objValueOf3;
                return i2 + 4;
            case 6:
            case 7:
                objValueOf2 = Long.valueOf(ArrayDecoders.decodeFixed64(bArr, i2));
                registers.object1 = objValueOf2;
                return i2 + 8;
            case 8:
                objValueOf3 = Float.valueOf(ArrayDecoders.decodeFloat(bArr, i2));
                registers.object1 = objValueOf3;
                return i2 + 4;
            case 9:
            case 10:
            case 11:
                iDecodeVarint64 = ArrayDecoders.decodeVarint32(bArr, i2, registers);
                iDecodeZigZag32 = registers.int1;
                objValueOf = Integer.valueOf(iDecodeZigZag32);
                registers.object1 = objValueOf;
                return iDecodeVarint64;
            case 12:
            case TYPE_UINT32_VALUE:
                iDecodeVarint64 = ArrayDecoders.decodeVarint64(bArr, i2, registers);
                jDecodeZigZag64 = registers.long1;
                objValueOf = Long.valueOf(jDecodeZigZag64);
                registers.object1 = objValueOf;
                return iDecodeVarint64;
            case TYPE_ENUM_VALUE:
                return ArrayDecoders.decodeMessageField(Protobuf.getInstance().schemaFor((Class) cls), bArr, i2, i3, registers);
            case TYPE_SFIXED32_VALUE:
                iDecodeVarint64 = ArrayDecoders.decodeVarint32(bArr, i2, registers);
                iDecodeZigZag32 = CodedInputStream.decodeZigZag32(registers.int1);
                objValueOf = Integer.valueOf(iDecodeZigZag32);
                registers.object1 = objValueOf;
                return iDecodeVarint64;
            case 16:
                iDecodeVarint64 = ArrayDecoders.decodeVarint64(bArr, i2, registers);
                jDecodeZigZag64 = CodedInputStream.decodeZigZag64(registers.long1);
                objValueOf = Long.valueOf(jDecodeZigZag64);
                registers.object1 = objValueOf;
                return iDecodeVarint64;
            case TYPE_SINT32_VALUE:
                return ArrayDecoders.decodeStringRequireUtf8(bArr, i2, registers);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private static <T> double doubleAt(T t2, long j2) {
        return UnsafeUtil.getDouble(t2, j2);
    }

    private <UT, UB> UB filterMapUnknownEnumValues(Object obj, int i2, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema, Object obj2) {
        Internal.EnumVerifier enumFieldVerifier;
        int iNumberAt = numberAt(i2);
        Object object = UnsafeUtil.getObject(obj, offset(typeAndOffsetAt(i2)));
        return (object == null || (enumFieldVerifier = getEnumFieldVerifier(i2)) == null) ? ub : (UB) filterUnknownEnumMap(i2, iNumberAt, this.mapFieldSchema.forMutableMapData(object), enumFieldVerifier, ub, unknownFieldSchema, obj2);
    }

    private <K, V, UT, UB> UB filterUnknownEnumMap(int i2, int i3, Map<K, V> map, Internal.EnumVerifier enumVerifier, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema, Object obj) {
        MapEntryLite.Metadata<?, ?> metadataForMapMetadata = this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i2));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (!enumVerifier.isInRange(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = unknownFieldSchema.getBuilderFromMessage(obj);
                }
                ByteString.CodedBuilder codedBuilderNewCodedBuilder = ByteString.newCodedBuilder(MapEntryLite.computeSerializedSize(metadataForMapMetadata, next.getKey(), next.getValue()));
                try {
                    MapEntryLite.writeTo(codedBuilderNewCodedBuilder.getCodedOutput(), metadataForMapMetadata, next.getKey(), next.getValue());
                    unknownFieldSchema.addLengthDelimited(ub, i3, codedBuilderNewCodedBuilder.build());
                    it.remove();
                } catch (IOException e2) {
                    throw new RuntimeException(e2);
                }
            }
        }
        return ub;
    }

    private static <T> float floatAt(T t2, long j2) {
        return UnsafeUtil.getFloat(t2, j2);
    }

    private Internal.EnumVerifier getEnumFieldVerifier(int i2) {
        return (Internal.EnumVerifier) this.objects[((i2 / 3) * 2) + 1];
    }

    private Object getMapFieldDefaultEntry(int i2) {
        return this.objects[(i2 / 3) * 2];
    }

    private Schema getMessageFieldSchema(int i2) {
        int i3 = (i2 / 3) * 2;
        Schema schema = (Schema) this.objects[i3];
        if (schema != null) {
            return schema;
        }
        Schema<T> schemaSchemaFor = Protobuf.getInstance().schemaFor((Class) this.objects[i3 + 1]);
        this.objects[i3] = schemaSchemaFor;
        return schemaSchemaFor;
    }

    public static UnknownFieldSetLite getMutableUnknownFields(Object obj) {
        GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) obj;
        UnknownFieldSetLite unknownFieldSetLite = generatedMessageLite.unknownFields;
        if (unknownFieldSetLite != UnknownFieldSetLite.getDefaultInstance()) {
            return unknownFieldSetLite;
        }
        UnknownFieldSetLite unknownFieldSetLiteNewInstance = UnknownFieldSetLite.newInstance();
        generatedMessageLite.unknownFields = unknownFieldSetLiteNewInstance;
        return unknownFieldSetLiteNewInstance;
    }

    /* JADX WARN: Code duplicated, block: B:149:0x022f A[PHI: r3
  0x022f: PHI (r3v97 int) = 
  (r3v60 int)
  (r3v63 int)
  (r3v66 int)
  (r3v69 int)
  (r3v72 int)
  (r3v75 int)
  (r3v78 int)
  (r3v81 int)
  (r3v84 int)
  (r3v87 int)
  (r3v90 int)
  (r3v93 int)
  (r3v96 int)
  (r3v101 int)
 binds: [B:148:0x022d, B:143:0x021c, B:138:0x020b, B:133:0x01fa, B:128:0x01e9, B:123:0x01d8, B:118:0x01c7, B:113:0x01b5, B:108:0x01a3, B:103:0x0191, B:98:0x017f, B:93:0x016d, B:88:0x015b, B:83:0x0149] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:166:0x02ee  */
    /* JADX WARN: Code duplicated, block: B:177:0x031e  */
    /* JADX WARN: Code duplicated, block: B:180:0x0328  */
    /* JADX WARN: Code duplicated, block: B:191:0x034f  */
    /* JADX WARN: Code duplicated, block: B:194:0x035f  */
    /* JADX WARN: Code duplicated, block: B:199:0x0379 A[PHI: r3
  0x0379: PHI (r3v126 java.lang.Object) = (r3v14 java.lang.Object), (r3v134 java.lang.Object) binds: [B:198:0x0377, B:52:0x00cb] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:200:0x0380 A[PHI: r3
  0x0380: PHI (r3v130 java.lang.Object) = (r3v14 java.lang.Object), (r3v134 java.lang.Object) binds: [B:198:0x0377, B:52:0x00cb] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:204:0x038c  */
    /* JADX WARN: Code duplicated, block: B:207:0x0396  */
    /* JADX WARN: Code duplicated, block: B:210:0x03a0  */
    /* JADX WARN: Code duplicated, block: B:225:0x03d0  */
    /* JADX WARN: Code duplicated, block: B:228:0x03d9  */
    private int getSerializedSizeProto2(T t2) {
        int i2;
        int i3;
        long jOneofLongAt;
        long jOneofLongAt2;
        int iOneofIntAt;
        Object object;
        int iOneofIntAt2;
        int iOneofIntAt3;
        int iOneofIntAt4;
        long jOneofLongAt3;
        int iComputeSizeFixed64List;
        int iComputeSizeFixed64ListNoTag;
        int iComputeBytesSize;
        Unsafe unsafe = UNSAFE;
        int i4 = 1048575;
        int i5 = 0;
        int iComputeUInt32SizeNoTag = 0;
        int i6 = 1048575;
        int i7 = 0;
        while (i5 < this.buffer.length) {
            int iTypeAndOffsetAt = typeAndOffsetAt(i5);
            int iNumberAt = numberAt(i5);
            int iType = type(iTypeAndOffsetAt);
            if (iType <= 17) {
                i2 = this.buffer[i5 + 2];
                int i8 = i4 & i2;
                i3 = 1 << (i2 >>> OFFSET_BITS);
                if (i8 != i6) {
                    i7 = unsafe.getInt(t2, i8);
                    i6 = i8;
                }
            } else {
                i2 = (!this.useCachedSizeField || iType < FieldType.DOUBLE_LIST_PACKED.id() || iType > FieldType.SINT64_LIST_PACKED.id()) ? 0 : i4 & this.buffer[i5 + 2];
                i3 = 0;
            }
            long jOffset = offset(iTypeAndOffsetAt);
            int i9 = i6;
            int i10 = i7;
            switch (iType) {
                case 0:
                    if ((i10 & i3) != 0) {
                        iComputeSizeFixed64List = CodedOutputStream.computeDoubleSize(iNumberAt, 0.0d);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 1:
                    if ((i10 & i3) != 0) {
                        iComputeSizeFixed64List = CodedOutputStream.computeFloatSize(iNumberAt, 0.0f);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 2:
                    if ((i10 & i3) != 0) {
                        jOneofLongAt = unsafe.getLong(t2, jOffset);
                        iComputeSizeFixed64List = CodedOutputStream.computeInt64Size(iNumberAt, jOneofLongAt);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 3:
                    if ((i10 & i3) != 0) {
                        jOneofLongAt2 = unsafe.getLong(t2, jOffset);
                        iComputeSizeFixed64List = CodedOutputStream.computeUInt64Size(iNumberAt, jOneofLongAt2);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 4:
                    if ((i10 & i3) != 0) {
                        iOneofIntAt = unsafe.getInt(t2, jOffset);
                        iComputeSizeFixed64List = CodedOutputStream.computeInt32Size(iNumberAt, iOneofIntAt);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 5:
                    if ((i10 & i3) != 0) {
                        iComputeSizeFixed64List = CodedOutputStream.computeFixed64Size(iNumberAt, 0L);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 6:
                    if ((i10 & i3) != 0) {
                        iComputeSizeFixed64List = CodedOutputStream.computeFixed32Size(iNumberAt, 0);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 7:
                    if ((i10 & i3) != 0) {
                        iComputeSizeFixed64List = CodedOutputStream.computeBoolSize(iNumberAt, true);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 8:
                    if ((i10 & i3) != 0) {
                        object = unsafe.getObject(t2, jOffset);
                        if (object instanceof ByteString) {
                            iComputeBytesSize = CodedOutputStream.computeBytesSize(iNumberAt, (ByteString) object);
                        } else {
                            iComputeBytesSize = CodedOutputStream.computeStringSize(iNumberAt, (String) object);
                        }
                        iComputeUInt32SizeNoTag += iComputeBytesSize;
                    }
                    break;
                case 9:
                    if ((i10 & i3) != 0) {
                        iComputeSizeFixed64List = SchemaUtil.computeSizeMessage(iNumberAt, unsafe.getObject(t2, jOffset), getMessageFieldSchema(i5));
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 10:
                    if ((i10 & i3) != 0) {
                        iComputeSizeFixed64List = CodedOutputStream.computeBytesSize(iNumberAt, (ByteString) unsafe.getObject(t2, jOffset));
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 11:
                    if ((i10 & i3) != 0) {
                        iOneofIntAt2 = unsafe.getInt(t2, jOffset);
                        iComputeSizeFixed64List = CodedOutputStream.computeUInt32Size(iNumberAt, iOneofIntAt2);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 12:
                    if ((i10 & i3) != 0) {
                        iOneofIntAt3 = unsafe.getInt(t2, jOffset);
                        iComputeSizeFixed64List = CodedOutputStream.computeEnumSize(iNumberAt, iOneofIntAt3);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case TYPE_UINT32_VALUE:
                    if ((i10 & i3) != 0) {
                        iComputeSizeFixed64List = CodedOutputStream.computeSFixed32Size(iNumberAt, 0);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case TYPE_ENUM_VALUE:
                    if ((i10 & i3) != 0) {
                        iComputeSizeFixed64List = CodedOutputStream.computeSFixed64Size(iNumberAt, 0L);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case TYPE_SFIXED32_VALUE:
                    if ((i10 & i3) != 0) {
                        iOneofIntAt4 = unsafe.getInt(t2, jOffset);
                        iComputeSizeFixed64List = CodedOutputStream.computeSInt32Size(iNumberAt, iOneofIntAt4);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 16:
                    if ((i10 & i3) != 0) {
                        jOneofLongAt3 = unsafe.getLong(t2, jOffset);
                        iComputeSizeFixed64List = CodedOutputStream.computeSInt64Size(iNumberAt, jOneofLongAt3);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case TYPE_SINT32_VALUE:
                    if ((i10 & i3) != 0) {
                        iComputeSizeFixed64List = CodedOutputStream.computeGroupSize(iNumberAt, (MessageLite) unsafe.getObject(t2, jOffset), getMessageFieldSchema(i5));
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case TYPE_SINT64_VALUE:
                case 23:
                case 32:
                    iComputeSizeFixed64List = SchemaUtil.computeSizeFixed64List(iNumberAt, (List) unsafe.getObject(t2, jOffset), false);
                    iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    break;
                case Base64.Encoder.LINE_GROUPS /* 19 */:
                case InsecureNonceXChaCha20.NONCE_SIZE_IN_BYTES /* 24 */:
                case 31:
                    iComputeSizeFixed64List = SchemaUtil.computeSizeFixed32List(iNumberAt, (List) unsafe.getObject(t2, jOffset), false);
                    iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    break;
                case OFFSET_BITS /* 20 */:
                    iComputeSizeFixed64List = SchemaUtil.computeSizeInt64List(iNumberAt, (List) unsafe.getObject(t2, jOffset), false);
                    iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    break;
                case 21:
                    iComputeSizeFixed64List = SchemaUtil.computeSizeUInt64List(iNumberAt, (List) unsafe.getObject(t2, jOffset), false);
                    iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    break;
                case 22:
                    iComputeSizeFixed64List = SchemaUtil.computeSizeInt32List(iNumberAt, (List) unsafe.getObject(t2, jOffset), false);
                    iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    break;
                case 25:
                    iComputeSizeFixed64List = SchemaUtil.computeSizeBoolList(iNumberAt, (List) unsafe.getObject(t2, jOffset), false);
                    iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    break;
                case 26:
                    iComputeSizeFixed64List = SchemaUtil.computeSizeStringList(iNumberAt, (List) unsafe.getObject(t2, jOffset));
                    iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    break;
                case 27:
                    iComputeSizeFixed64List = SchemaUtil.computeSizeMessageList(iNumberAt, (List) unsafe.getObject(t2, jOffset), getMessageFieldSchema(i5));
                    iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    break;
                case 28:
                    iComputeSizeFixed64List = SchemaUtil.computeSizeByteStringList(iNumberAt, (List) unsafe.getObject(t2, jOffset));
                    iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    break;
                case 29:
                    iComputeSizeFixed64List = SchemaUtil.computeSizeUInt32List(iNumberAt, (List) unsafe.getObject(t2, jOffset), false);
                    iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    break;
                case 30:
                    iComputeSizeFixed64List = SchemaUtil.computeSizeEnumList(iNumberAt, (List) unsafe.getObject(t2, jOffset), false);
                    iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    break;
                case 33:
                    iComputeSizeFixed64List = SchemaUtil.computeSizeSInt32List(iNumberAt, (List) unsafe.getObject(t2, jOffset), false);
                    iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    break;
                case 34:
                    iComputeSizeFixed64List = SchemaUtil.computeSizeSInt64List(iNumberAt, (List) unsafe.getObject(t2, jOffset), false);
                    iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    break;
                case 35:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t2, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, i2, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeUInt32SizeNoTag += CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag) + CodedOutputStream.computeTagSize(iNumberAt) + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 36:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t2, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, i2, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeUInt32SizeNoTag += CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag) + CodedOutputStream.computeTagSize(iNumberAt) + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 37:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeInt64ListNoTag((List) unsafe.getObject(t2, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, i2, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeUInt32SizeNoTag += CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag) + CodedOutputStream.computeTagSize(iNumberAt) + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 38:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeUInt64ListNoTag((List) unsafe.getObject(t2, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, i2, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeUInt32SizeNoTag += CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag) + CodedOutputStream.computeTagSize(iNumberAt) + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 39:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeInt32ListNoTag((List) unsafe.getObject(t2, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, i2, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeUInt32SizeNoTag += CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag) + CodedOutputStream.computeTagSize(iNumberAt) + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 40:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t2, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, i2, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeUInt32SizeNoTag += CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag) + CodedOutputStream.computeTagSize(iNumberAt) + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 41:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t2, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, i2, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeUInt32SizeNoTag += CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag) + CodedOutputStream.computeTagSize(iNumberAt) + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 42:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeBoolListNoTag((List) unsafe.getObject(t2, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, i2, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeUInt32SizeNoTag += CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag) + CodedOutputStream.computeTagSize(iNumberAt) + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 43:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeUInt32ListNoTag((List) unsafe.getObject(t2, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, i2, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeUInt32SizeNoTag += CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag) + CodedOutputStream.computeTagSize(iNumberAt) + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 44:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeEnumListNoTag((List) unsafe.getObject(t2, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, i2, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeUInt32SizeNoTag += CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag) + CodedOutputStream.computeTagSize(iNumberAt) + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 45:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t2, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, i2, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeUInt32SizeNoTag += CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag) + CodedOutputStream.computeTagSize(iNumberAt) + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 46:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t2, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, i2, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeUInt32SizeNoTag += CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag) + CodedOutputStream.computeTagSize(iNumberAt) + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 47:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeSInt32ListNoTag((List) unsafe.getObject(t2, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, i2, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeUInt32SizeNoTag += CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag) + CodedOutputStream.computeTagSize(iNumberAt) + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 48:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeSInt64ListNoTag((List) unsafe.getObject(t2, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, i2, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeUInt32SizeNoTag += CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag) + CodedOutputStream.computeTagSize(iNumberAt) + iComputeSizeFixed64ListNoTag;
                    }
                    break;
                case 49:
                    iComputeSizeFixed64List = SchemaUtil.computeSizeGroupList(iNumberAt, (List) unsafe.getObject(t2, jOffset), getMessageFieldSchema(i5));
                    iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    break;
                case 50:
                    iComputeSizeFixed64List = this.mapFieldSchema.getSerializedSize(iNumberAt, unsafe.getObject(t2, jOffset), getMapFieldDefaultEntry(i5));
                    iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    break;
                case ONEOF_TYPE_OFFSET /* 51 */:
                    if (isOneofPresent(t2, iNumberAt, i5)) {
                        iComputeSizeFixed64List = CodedOutputStream.computeDoubleSize(iNumberAt, 0.0d);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 52:
                    if (isOneofPresent(t2, iNumberAt, i5)) {
                        iComputeSizeFixed64List = CodedOutputStream.computeFloatSize(iNumberAt, 0.0f);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 53:
                    if (isOneofPresent(t2, iNumberAt, i5)) {
                        jOneofLongAt = oneofLongAt(t2, jOffset);
                        iComputeSizeFixed64List = CodedOutputStream.computeInt64Size(iNumberAt, jOneofLongAt);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 54:
                    if (isOneofPresent(t2, iNumberAt, i5)) {
                        jOneofLongAt2 = oneofLongAt(t2, jOffset);
                        iComputeSizeFixed64List = CodedOutputStream.computeUInt64Size(iNumberAt, jOneofLongAt2);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 55:
                    if (isOneofPresent(t2, iNumberAt, i5)) {
                        iOneofIntAt = oneofIntAt(t2, jOffset);
                        iComputeSizeFixed64List = CodedOutputStream.computeInt32Size(iNumberAt, iOneofIntAt);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 56:
                    if (isOneofPresent(t2, iNumberAt, i5)) {
                        iComputeSizeFixed64List = CodedOutputStream.computeFixed64Size(iNumberAt, 0L);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 57:
                    if (isOneofPresent(t2, iNumberAt, i5)) {
                        iComputeSizeFixed64List = CodedOutputStream.computeFixed32Size(iNumberAt, 0);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 58:
                    if (isOneofPresent(t2, iNumberAt, i5)) {
                        iComputeSizeFixed64List = CodedOutputStream.computeBoolSize(iNumberAt, true);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 59:
                    if (isOneofPresent(t2, iNumberAt, i5)) {
                        object = unsafe.getObject(t2, jOffset);
                        if (object instanceof ByteString) {
                            iComputeBytesSize = CodedOutputStream.computeBytesSize(iNumberAt, (ByteString) object);
                        } else {
                            iComputeBytesSize = CodedOutputStream.computeStringSize(iNumberAt, (String) object);
                        }
                        iComputeUInt32SizeNoTag += iComputeBytesSize;
                    }
                    break;
                case 60:
                    if (isOneofPresent(t2, iNumberAt, i5)) {
                        iComputeSizeFixed64List = SchemaUtil.computeSizeMessage(iNumberAt, unsafe.getObject(t2, jOffset), getMessageFieldSchema(i5));
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 61:
                    if (isOneofPresent(t2, iNumberAt, i5)) {
                        iComputeSizeFixed64List = CodedOutputStream.computeBytesSize(iNumberAt, (ByteString) unsafe.getObject(t2, jOffset));
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 62:
                    if (isOneofPresent(t2, iNumberAt, i5)) {
                        iOneofIntAt2 = oneofIntAt(t2, jOffset);
                        iComputeSizeFixed64List = CodedOutputStream.computeUInt32Size(iNumberAt, iOneofIntAt2);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 63:
                    if (isOneofPresent(t2, iNumberAt, i5)) {
                        iOneofIntAt3 = oneofIntAt(t2, jOffset);
                        iComputeSizeFixed64List = CodedOutputStream.computeEnumSize(iNumberAt, iOneofIntAt3);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 64:
                    if (isOneofPresent(t2, iNumberAt, i5)) {
                        iComputeSizeFixed64List = CodedOutputStream.computeSFixed32Size(iNumberAt, 0);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 65:
                    if (isOneofPresent(t2, iNumberAt, i5)) {
                        iComputeSizeFixed64List = CodedOutputStream.computeSFixed64Size(iNumberAt, 0L);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 66:
                    if (isOneofPresent(t2, iNumberAt, i5)) {
                        iOneofIntAt4 = oneofIntAt(t2, jOffset);
                        iComputeSizeFixed64List = CodedOutputStream.computeSInt32Size(iNumberAt, iOneofIntAt4);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 67:
                    if (isOneofPresent(t2, iNumberAt, i5)) {
                        jOneofLongAt3 = oneofLongAt(t2, jOffset);
                        iComputeSizeFixed64List = CodedOutputStream.computeSInt64Size(iNumberAt, jOneofLongAt3);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 68:
                    if (isOneofPresent(t2, iNumberAt, i5)) {
                        iComputeSizeFixed64List = CodedOutputStream.computeGroupSize(iNumberAt, (MessageLite) unsafe.getObject(t2, jOffset), getMessageFieldSchema(i5));
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
            }
            i5 += 3;
            i4 = 1048575;
            i6 = i9;
            i7 = i10;
        }
        int unknownFieldsSerializedSize = iComputeUInt32SizeNoTag + getUnknownFieldsSerializedSize(this.unknownFieldSchema, t2);
        return this.hasExtensions ? unknownFieldsSerializedSize + this.extensionSchema.getExtensions(t2).getSerializedSize() : unknownFieldsSerializedSize;
    }

    /* JADX WARN: Code duplicated, block: B:141:0x0205 A[PHI: r5
  0x0205: PHI (r5v42 int) = 
  (r5v5 int)
  (r5v8 int)
  (r5v11 int)
  (r5v14 int)
  (r5v17 int)
  (r5v20 int)
  (r5v23 int)
  (r5v26 int)
  (r5v29 int)
  (r5v32 int)
  (r5v35 int)
  (r5v38 int)
  (r5v41 int)
  (r5v46 int)
 binds: [B:140:0x0203, B:135:0x01f2, B:130:0x01e1, B:125:0x01d0, B:120:0x01bf, B:115:0x01ae, B:110:0x019d, B:105:0x018b, B:100:0x0179, B:95:0x0167, B:90:0x0155, B:85:0x0143, B:80:0x0131, B:75:0x011f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:158:0x02a3  */
    /* JADX WARN: Code duplicated, block: B:169:0x02d7  */
    /* JADX WARN: Code duplicated, block: B:172:0x02e2  */
    /* JADX WARN: Code duplicated, block: B:183:0x030b  */
    /* JADX WARN: Code duplicated, block: B:186:0x031c  */
    /* JADX WARN: Code duplicated, block: B:192:0x0339 A[PHI: r4
  0x0339: PHI (r4v87 java.lang.Object) = (r4v19 java.lang.Object), (r4v96 java.lang.Object) binds: [B:191:0x0337, B:44:0x00a3] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:193:0x0340 A[PHI: r4
  0x0340: PHI (r4v92 java.lang.Object) = (r4v19 java.lang.Object), (r4v96 java.lang.Object) binds: [B:191:0x0337, B:44:0x00a3] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:197:0x0350  */
    /* JADX WARN: Code duplicated, block: B:200:0x035b  */
    /* JADX WARN: Code duplicated, block: B:203:0x0366  */
    /* JADX WARN: Code duplicated, block: B:218:0x039e  */
    /* JADX WARN: Code duplicated, block: B:221:0x03a9  */
    private int getSerializedSizeProto3(T t2) {
        long jOneofLongAt;
        long jOneofLongAt2;
        int iOneofIntAt;
        Object object;
        int iOneofIntAt2;
        int iOneofIntAt3;
        int iOneofIntAt4;
        long jOneofLongAt3;
        int iComputeSizeFixed64List;
        int iComputeSizeFixed64ListNoTag;
        int iComputeBytesSize;
        Unsafe unsafe = UNSAFE;
        int iComputeUInt32SizeNoTag = 0;
        for (int i2 = 0; i2 < this.buffer.length; i2 += 3) {
            int iTypeAndOffsetAt = typeAndOffsetAt(i2);
            int iType = type(iTypeAndOffsetAt);
            int iNumberAt = numberAt(i2);
            long jOffset = offset(iTypeAndOffsetAt);
            int i3 = (iType < FieldType.DOUBLE_LIST_PACKED.id() || iType > FieldType.SINT64_LIST_PACKED.id()) ? 0 : this.buffer[i2 + 2] & 1048575;
            switch (iType) {
                case 0:
                    if (isFieldPresent(t2, i2)) {
                        iComputeSizeFixed64List = CodedOutputStream.computeDoubleSize(iNumberAt, 0.0d);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 1:
                    if (isFieldPresent(t2, i2)) {
                        iComputeSizeFixed64List = CodedOutputStream.computeFloatSize(iNumberAt, 0.0f);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 2:
                    if (isFieldPresent(t2, i2)) {
                        jOneofLongAt = UnsafeUtil.getLong(t2, jOffset);
                        iComputeSizeFixed64List = CodedOutputStream.computeInt64Size(iNumberAt, jOneofLongAt);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 3:
                    if (isFieldPresent(t2, i2)) {
                        jOneofLongAt2 = UnsafeUtil.getLong(t2, jOffset);
                        iComputeSizeFixed64List = CodedOutputStream.computeUInt64Size(iNumberAt, jOneofLongAt2);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 4:
                    if (isFieldPresent(t2, i2)) {
                        iOneofIntAt = UnsafeUtil.getInt(t2, jOffset);
                        iComputeSizeFixed64List = CodedOutputStream.computeInt32Size(iNumberAt, iOneofIntAt);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 5:
                    if (isFieldPresent(t2, i2)) {
                        iComputeSizeFixed64List = CodedOutputStream.computeFixed64Size(iNumberAt, 0L);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 6:
                    if (isFieldPresent(t2, i2)) {
                        iComputeSizeFixed64List = CodedOutputStream.computeFixed32Size(iNumberAt, 0);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 7:
                    if (isFieldPresent(t2, i2)) {
                        iComputeSizeFixed64List = CodedOutputStream.computeBoolSize(iNumberAt, true);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 8:
                    if (isFieldPresent(t2, i2)) {
                        object = UnsafeUtil.getObject(t2, jOffset);
                        if (object instanceof ByteString) {
                            iComputeBytesSize = CodedOutputStream.computeBytesSize(iNumberAt, (ByteString) object);
                        } else {
                            iComputeBytesSize = CodedOutputStream.computeStringSize(iNumberAt, (String) object);
                        }
                        iComputeUInt32SizeNoTag = iComputeBytesSize + iComputeUInt32SizeNoTag;
                    }
                    break;
                case 9:
                    if (isFieldPresent(t2, i2)) {
                        iComputeSizeFixed64List = SchemaUtil.computeSizeMessage(iNumberAt, UnsafeUtil.getObject(t2, jOffset), getMessageFieldSchema(i2));
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 10:
                    if (isFieldPresent(t2, i2)) {
                        iComputeSizeFixed64List = CodedOutputStream.computeBytesSize(iNumberAt, (ByteString) UnsafeUtil.getObject(t2, jOffset));
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 11:
                    if (isFieldPresent(t2, i2)) {
                        iOneofIntAt2 = UnsafeUtil.getInt(t2, jOffset);
                        iComputeSizeFixed64List = CodedOutputStream.computeUInt32Size(iNumberAt, iOneofIntAt2);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 12:
                    if (isFieldPresent(t2, i2)) {
                        iOneofIntAt3 = UnsafeUtil.getInt(t2, jOffset);
                        iComputeSizeFixed64List = CodedOutputStream.computeEnumSize(iNumberAt, iOneofIntAt3);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case TYPE_UINT32_VALUE:
                    if (isFieldPresent(t2, i2)) {
                        iComputeSizeFixed64List = CodedOutputStream.computeSFixed32Size(iNumberAt, 0);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case TYPE_ENUM_VALUE:
                    if (isFieldPresent(t2, i2)) {
                        iComputeSizeFixed64List = CodedOutputStream.computeSFixed64Size(iNumberAt, 0L);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case TYPE_SFIXED32_VALUE:
                    if (isFieldPresent(t2, i2)) {
                        iOneofIntAt4 = UnsafeUtil.getInt(t2, jOffset);
                        iComputeSizeFixed64List = CodedOutputStream.computeSInt32Size(iNumberAt, iOneofIntAt4);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 16:
                    if (isFieldPresent(t2, i2)) {
                        jOneofLongAt3 = UnsafeUtil.getLong(t2, jOffset);
                        iComputeSizeFixed64List = CodedOutputStream.computeSInt64Size(iNumberAt, jOneofLongAt3);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case TYPE_SINT32_VALUE:
                    if (isFieldPresent(t2, i2)) {
                        iComputeSizeFixed64List = CodedOutputStream.computeGroupSize(iNumberAt, (MessageLite) UnsafeUtil.getObject(t2, jOffset), getMessageFieldSchema(i2));
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case TYPE_SINT64_VALUE:
                case 23:
                case 32:
                    iComputeSizeFixed64List = SchemaUtil.computeSizeFixed64List(iNumberAt, listAt(t2, jOffset), false);
                    iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    break;
                case Base64.Encoder.LINE_GROUPS /* 19 */:
                case InsecureNonceXChaCha20.NONCE_SIZE_IN_BYTES /* 24 */:
                case 31:
                    iComputeSizeFixed64List = SchemaUtil.computeSizeFixed32List(iNumberAt, listAt(t2, jOffset), false);
                    iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    break;
                case OFFSET_BITS /* 20 */:
                    iComputeSizeFixed64List = SchemaUtil.computeSizeInt64List(iNumberAt, listAt(t2, jOffset), false);
                    iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    break;
                case 21:
                    iComputeSizeFixed64List = SchemaUtil.computeSizeUInt64List(iNumberAt, listAt(t2, jOffset), false);
                    iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    break;
                case 22:
                    iComputeSizeFixed64List = SchemaUtil.computeSizeInt32List(iNumberAt, listAt(t2, jOffset), false);
                    iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    break;
                case 25:
                    iComputeSizeFixed64List = SchemaUtil.computeSizeBoolList(iNumberAt, listAt(t2, jOffset), false);
                    iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    break;
                case 26:
                    iComputeSizeFixed64List = SchemaUtil.computeSizeStringList(iNumberAt, listAt(t2, jOffset));
                    iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    break;
                case 27:
                    iComputeSizeFixed64List = SchemaUtil.computeSizeMessageList(iNumberAt, listAt(t2, jOffset), getMessageFieldSchema(i2));
                    iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    break;
                case 28:
                    iComputeSizeFixed64List = SchemaUtil.computeSizeByteStringList(iNumberAt, listAt(t2, jOffset));
                    iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    break;
                case 29:
                    iComputeSizeFixed64List = SchemaUtil.computeSizeUInt32List(iNumberAt, listAt(t2, jOffset), false);
                    iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    break;
                case 30:
                    iComputeSizeFixed64List = SchemaUtil.computeSizeEnumList(iNumberAt, listAt(t2, jOffset), false);
                    iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    break;
                case 33:
                    iComputeSizeFixed64List = SchemaUtil.computeSizeSInt32List(iNumberAt, listAt(t2, jOffset), false);
                    iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    break;
                case 34:
                    iComputeSizeFixed64List = SchemaUtil.computeSizeSInt64List(iNumberAt, listAt(t2, jOffset), false);
                    iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    break;
                case 35:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t2, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, i3, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag) + CodedOutputStream.computeTagSize(iNumberAt) + iComputeSizeFixed64ListNoTag + iComputeUInt32SizeNoTag;
                    }
                    break;
                case 36:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t2, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, i3, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag) + CodedOutputStream.computeTagSize(iNumberAt) + iComputeSizeFixed64ListNoTag + iComputeUInt32SizeNoTag;
                    }
                    break;
                case 37:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeInt64ListNoTag((List) unsafe.getObject(t2, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, i3, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag) + CodedOutputStream.computeTagSize(iNumberAt) + iComputeSizeFixed64ListNoTag + iComputeUInt32SizeNoTag;
                    }
                    break;
                case 38:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeUInt64ListNoTag((List) unsafe.getObject(t2, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, i3, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag) + CodedOutputStream.computeTagSize(iNumberAt) + iComputeSizeFixed64ListNoTag + iComputeUInt32SizeNoTag;
                    }
                    break;
                case 39:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeInt32ListNoTag((List) unsafe.getObject(t2, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, i3, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag) + CodedOutputStream.computeTagSize(iNumberAt) + iComputeSizeFixed64ListNoTag + iComputeUInt32SizeNoTag;
                    }
                    break;
                case 40:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t2, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, i3, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag) + CodedOutputStream.computeTagSize(iNumberAt) + iComputeSizeFixed64ListNoTag + iComputeUInt32SizeNoTag;
                    }
                    break;
                case 41:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t2, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, i3, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag) + CodedOutputStream.computeTagSize(iNumberAt) + iComputeSizeFixed64ListNoTag + iComputeUInt32SizeNoTag;
                    }
                    break;
                case 42:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeBoolListNoTag((List) unsafe.getObject(t2, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, i3, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag) + CodedOutputStream.computeTagSize(iNumberAt) + iComputeSizeFixed64ListNoTag + iComputeUInt32SizeNoTag;
                    }
                    break;
                case 43:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeUInt32ListNoTag((List) unsafe.getObject(t2, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, i3, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag) + CodedOutputStream.computeTagSize(iNumberAt) + iComputeSizeFixed64ListNoTag + iComputeUInt32SizeNoTag;
                    }
                    break;
                case 44:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeEnumListNoTag((List) unsafe.getObject(t2, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, i3, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag) + CodedOutputStream.computeTagSize(iNumberAt) + iComputeSizeFixed64ListNoTag + iComputeUInt32SizeNoTag;
                    }
                    break;
                case 45:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(t2, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, i3, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag) + CodedOutputStream.computeTagSize(iNumberAt) + iComputeSizeFixed64ListNoTag + iComputeUInt32SizeNoTag;
                    }
                    break;
                case 46:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(t2, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, i3, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag) + CodedOutputStream.computeTagSize(iNumberAt) + iComputeSizeFixed64ListNoTag + iComputeUInt32SizeNoTag;
                    }
                    break;
                case 47:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeSInt32ListNoTag((List) unsafe.getObject(t2, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, i3, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag) + CodedOutputStream.computeTagSize(iNumberAt) + iComputeSizeFixed64ListNoTag + iComputeUInt32SizeNoTag;
                    }
                    break;
                case 48:
                    iComputeSizeFixed64ListNoTag = SchemaUtil.computeSizeSInt64ListNoTag((List) unsafe.getObject(t2, jOffset));
                    if (iComputeSizeFixed64ListNoTag > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(t2, i3, iComputeSizeFixed64ListNoTag);
                        }
                        iComputeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(iComputeSizeFixed64ListNoTag) + CodedOutputStream.computeTagSize(iNumberAt) + iComputeSizeFixed64ListNoTag + iComputeUInt32SizeNoTag;
                    }
                    break;
                case 49:
                    iComputeSizeFixed64List = SchemaUtil.computeSizeGroupList(iNumberAt, listAt(t2, jOffset), getMessageFieldSchema(i2));
                    iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    break;
                case 50:
                    iComputeSizeFixed64List = this.mapFieldSchema.getSerializedSize(iNumberAt, UnsafeUtil.getObject(t2, jOffset), getMapFieldDefaultEntry(i2));
                    iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    break;
                case ONEOF_TYPE_OFFSET /* 51 */:
                    if (isOneofPresent(t2, iNumberAt, i2)) {
                        iComputeSizeFixed64List = CodedOutputStream.computeDoubleSize(iNumberAt, 0.0d);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 52:
                    if (isOneofPresent(t2, iNumberAt, i2)) {
                        iComputeSizeFixed64List = CodedOutputStream.computeFloatSize(iNumberAt, 0.0f);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 53:
                    if (isOneofPresent(t2, iNumberAt, i2)) {
                        jOneofLongAt = oneofLongAt(t2, jOffset);
                        iComputeSizeFixed64List = CodedOutputStream.computeInt64Size(iNumberAt, jOneofLongAt);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 54:
                    if (isOneofPresent(t2, iNumberAt, i2)) {
                        jOneofLongAt2 = oneofLongAt(t2, jOffset);
                        iComputeSizeFixed64List = CodedOutputStream.computeUInt64Size(iNumberAt, jOneofLongAt2);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 55:
                    if (isOneofPresent(t2, iNumberAt, i2)) {
                        iOneofIntAt = oneofIntAt(t2, jOffset);
                        iComputeSizeFixed64List = CodedOutputStream.computeInt32Size(iNumberAt, iOneofIntAt);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 56:
                    if (isOneofPresent(t2, iNumberAt, i2)) {
                        iComputeSizeFixed64List = CodedOutputStream.computeFixed64Size(iNumberAt, 0L);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 57:
                    if (isOneofPresent(t2, iNumberAt, i2)) {
                        iComputeSizeFixed64List = CodedOutputStream.computeFixed32Size(iNumberAt, 0);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 58:
                    if (isOneofPresent(t2, iNumberAt, i2)) {
                        iComputeSizeFixed64List = CodedOutputStream.computeBoolSize(iNumberAt, true);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 59:
                    if (isOneofPresent(t2, iNumberAt, i2)) {
                        object = UnsafeUtil.getObject(t2, jOffset);
                        if (object instanceof ByteString) {
                            iComputeBytesSize = CodedOutputStream.computeBytesSize(iNumberAt, (ByteString) object);
                        } else {
                            iComputeBytesSize = CodedOutputStream.computeStringSize(iNumberAt, (String) object);
                        }
                        iComputeUInt32SizeNoTag = iComputeBytesSize + iComputeUInt32SizeNoTag;
                    }
                    break;
                case 60:
                    if (isOneofPresent(t2, iNumberAt, i2)) {
                        iComputeSizeFixed64List = SchemaUtil.computeSizeMessage(iNumberAt, UnsafeUtil.getObject(t2, jOffset), getMessageFieldSchema(i2));
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 61:
                    if (isOneofPresent(t2, iNumberAt, i2)) {
                        iComputeSizeFixed64List = CodedOutputStream.computeBytesSize(iNumberAt, (ByteString) UnsafeUtil.getObject(t2, jOffset));
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 62:
                    if (isOneofPresent(t2, iNumberAt, i2)) {
                        iOneofIntAt2 = oneofIntAt(t2, jOffset);
                        iComputeSizeFixed64List = CodedOutputStream.computeUInt32Size(iNumberAt, iOneofIntAt2);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 63:
                    if (isOneofPresent(t2, iNumberAt, i2)) {
                        iOneofIntAt3 = oneofIntAt(t2, jOffset);
                        iComputeSizeFixed64List = CodedOutputStream.computeEnumSize(iNumberAt, iOneofIntAt3);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 64:
                    if (isOneofPresent(t2, iNumberAt, i2)) {
                        iComputeSizeFixed64List = CodedOutputStream.computeSFixed32Size(iNumberAt, 0);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 65:
                    if (isOneofPresent(t2, iNumberAt, i2)) {
                        iComputeSizeFixed64List = CodedOutputStream.computeSFixed64Size(iNumberAt, 0L);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 66:
                    if (isOneofPresent(t2, iNumberAt, i2)) {
                        iOneofIntAt4 = oneofIntAt(t2, jOffset);
                        iComputeSizeFixed64List = CodedOutputStream.computeSInt32Size(iNumberAt, iOneofIntAt4);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 67:
                    if (isOneofPresent(t2, iNumberAt, i2)) {
                        jOneofLongAt3 = oneofLongAt(t2, jOffset);
                        iComputeSizeFixed64List = CodedOutputStream.computeSInt64Size(iNumberAt, jOneofLongAt3);
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
                case 68:
                    if (isOneofPresent(t2, iNumberAt, i2)) {
                        iComputeSizeFixed64List = CodedOutputStream.computeGroupSize(iNumberAt, (MessageLite) UnsafeUtil.getObject(t2, jOffset), getMessageFieldSchema(i2));
                        iComputeUInt32SizeNoTag += iComputeSizeFixed64List;
                    }
                    break;
            }
        }
        return iComputeUInt32SizeNoTag + getUnknownFieldsSerializedSize(this.unknownFieldSchema, t2);
    }

    private <UT, UB> int getUnknownFieldsSerializedSize(UnknownFieldSchema<UT, UB> unknownFieldSchema, T t2) {
        return unknownFieldSchema.getSerializedSize(unknownFieldSchema.getFromMessage(t2));
    }

    private static <T> int intAt(T t2, long j2) {
        return UnsafeUtil.getInt(t2, j2);
    }

    private static boolean isEnforceUtf8(int i2) {
        return (i2 & ENFORCE_UTF8_MASK) != 0;
    }

    private boolean isFieldPresent(T t2, int i2) {
        int iPresenceMaskAndOffsetAt = presenceMaskAndOffsetAt(i2);
        long j2 = 1048575 & iPresenceMaskAndOffsetAt;
        if (j2 != 1048575) {
            return (UnsafeUtil.getInt(t2, j2) & (1 << (iPresenceMaskAndOffsetAt >>> OFFSET_BITS))) != 0;
        }
        int iTypeAndOffsetAt = typeAndOffsetAt(i2);
        long jOffset = offset(iTypeAndOffsetAt);
        switch (type(iTypeAndOffsetAt)) {
            case 0:
                return Double.doubleToRawLongBits(UnsafeUtil.getDouble(t2, jOffset)) != 0;
            case 1:
                return Float.floatToRawIntBits(UnsafeUtil.getFloat(t2, jOffset)) != 0;
            case 2:
                return UnsafeUtil.getLong(t2, jOffset) != 0;
            case 3:
                return UnsafeUtil.getLong(t2, jOffset) != 0;
            case 4:
                return UnsafeUtil.getInt(t2, jOffset) != 0;
            case 5:
                return UnsafeUtil.getLong(t2, jOffset) != 0;
            case 6:
                return UnsafeUtil.getInt(t2, jOffset) != 0;
            case 7:
                return UnsafeUtil.getBoolean(t2, jOffset);
            case 8:
                Object object = UnsafeUtil.getObject(t2, jOffset);
                if (object instanceof String) {
                    return !((String) object).isEmpty();
                }
                if (object instanceof ByteString) {
                    return !ByteString.EMPTY.equals(object);
                }
                throw new IllegalArgumentException();
            case 9:
                return UnsafeUtil.getObject(t2, jOffset) != null;
            case 10:
                return !ByteString.EMPTY.equals(UnsafeUtil.getObject(t2, jOffset));
            case 11:
                return UnsafeUtil.getInt(t2, jOffset) != 0;
            case 12:
                return UnsafeUtil.getInt(t2, jOffset) != 0;
            case TYPE_UINT32_VALUE:
                return UnsafeUtil.getInt(t2, jOffset) != 0;
            case TYPE_ENUM_VALUE:
                return UnsafeUtil.getLong(t2, jOffset) != 0;
            case TYPE_SFIXED32_VALUE:
                return UnsafeUtil.getInt(t2, jOffset) != 0;
            case 16:
                return UnsafeUtil.getLong(t2, jOffset) != 0;
            case TYPE_SINT32_VALUE:
                return UnsafeUtil.getObject(t2, jOffset) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <N> boolean isListInitialized(Object obj, int i2, int i3) {
        List list = (List) UnsafeUtil.getObject(obj, offset(i2));
        if (list.isEmpty()) {
            return true;
        }
        Schema messageFieldSchema = getMessageFieldSchema(i3);
        for (int i4 = 0; i4 < list.size(); i4++) {
            if (!messageFieldSchema.isInitialized(list.get(i4))) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8, types: [com.google.crypto.tink.shaded.protobuf.Schema] */
    private boolean isMapInitialized(T t2, int i2, int i3) {
        Map<?, ?> mapForMapData = this.mapFieldSchema.forMapData(UnsafeUtil.getObject(t2, offset(i2)));
        if (mapForMapData.isEmpty()) {
            return true;
        }
        if (this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i3)).valueType.getJavaType() != WireFormat.JavaType.MESSAGE) {
            return true;
        }
        ?? SchemaFor = 0;
        for (Object obj : mapForMapData.values()) {
            if (SchemaFor == 0) {
                SchemaFor = SchemaFor;
                SchemaFor = Protobuf.getInstance().schemaFor((Class) obj.getClass());
            }
            SchemaFor = SchemaFor;
            if (!SchemaFor.isInitialized(obj)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isMutable(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof GeneratedMessageLite) {
            return ((GeneratedMessageLite) obj).isMutable();
        }
        return true;
    }

    private boolean isOneofCaseEqual(T t2, T t3, int i2) {
        long jPresenceMaskAndOffsetAt = presenceMaskAndOffsetAt(i2) & 1048575;
        return UnsafeUtil.getInt(t2, jPresenceMaskAndOffsetAt) == UnsafeUtil.getInt(t3, jPresenceMaskAndOffsetAt);
    }

    private boolean isOneofPresent(T t2, int i2, int i3) {
        return UnsafeUtil.getInt(t2, (long) (presenceMaskAndOffsetAt(i3) & 1048575)) == i2;
    }

    private static boolean isRequired(int i2) {
        return (i2 & REQUIRED_MASK) != 0;
    }

    private static List<?> listAt(Object obj, long j2) {
        return (List) UnsafeUtil.getObject(obj, j2);
    }

    private static <T> long longAt(T t2, long j2) {
        return UnsafeUtil.getLong(t2, j2);
    }

    /* JADX WARN: Code duplicated, block: B:190:0x05bd A[Catch: all -> 0x0615, TRY_LEAVE, TryCatch #6 {all -> 0x0615, blocks: (B:177:0x058e, B:188:0x05b7, B:190:0x05bd, B:200:0x05e5, B:201:0x05ea), top: B:233:0x058e }] */
    /* JADX WARN: Code duplicated, block: B:195:0x05ca A[LOOP:3: B:193:0x05c6->B:195:0x05ca, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:197:0x05df  */
    /* JADX WARN: Code duplicated, block: B:199:0x05e3 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:200:0x05e5 A[Catch: all -> 0x0615, TRY_ENTER, TryCatch #6 {all -> 0x0615, blocks: (B:177:0x058e, B:188:0x05b7, B:190:0x05bd, B:200:0x05e5, B:201:0x05ea), top: B:233:0x058e }] */
    /* JADX WARN: Code duplicated, block: B:206:0x05f7 A[LOOP:4: B:204:0x05f3->B:206:0x05f7, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:208:0x060c  */
    /* JADX WARN: Code duplicated, block: B:222:0x0627 A[LOOP:2: B:220:0x0623->B:222:0x0627, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:224:0x063c  */
    /* JADX WARN: Code duplicated, block: B:254:0x05c3 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:255:0x05f0 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:268:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:269:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    private <UT, UB, ET extends FieldSet.FieldDescriptorLite<ET>> void mergeFromHelper(UnknownFieldSchema<UT, UB> unknownFieldSchema, ExtensionSchema<ET> extensionSchema, T t2, Reader reader, ExtensionRegistryLite extensionRegistryLite) throws Throwable {
        T t3;
        int i2;
        Object objFilterMapUnknownEnumValues;
        T t4;
        Object mutableExtensions;
        int i3;
        Object objFilterMapUnknownEnumValues2;
        int i4;
        Object objFilterMapUnknownEnumValues3;
        MessageLite messageLite;
        MessageLite messageLite2;
        List<Double> listMutableListAt;
        List<Float> listMutableListAt2;
        List<Long> listMutableListAt3;
        List<Long> listMutableListAt4;
        List<Integer> listMutableListAt5;
        List<Long> listMutableListAt6;
        List<Integer> listMutableListAt7;
        List<Boolean> listMutableListAt8;
        List<Integer> listMutableListAt9;
        List<Integer> listMutableListAt10;
        Internal.EnumVerifier enumFieldVerifier;
        List<Integer> listMutableListAt11;
        List<Long> listMutableListAt12;
        List<Integer> listMutableListAt13;
        List<Long> listMutableListAt14;
        UnknownFieldSchema unknownFieldSchema2 = unknownFieldSchema;
        T t5 = t2;
        ExtensionRegistryLite extensionRegistryLite2 = extensionRegistryLite;
        Object builderFromMessage = null;
        Object obj = null;
        while (true) {
            try {
                int fieldNumber = reader.getFieldNumber();
                int iPositionForFieldNumber = positionForFieldNumber(fieldNumber);
                if (iPositionForFieldNumber >= 0) {
                    t3 = t5;
                    try {
                        int iTypeAndOffsetAt = typeAndOffsetAt(iPositionForFieldNumber);
                        try {
                            switch (type(iTypeAndOffsetAt)) {
                                case 0:
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    UnsafeUtil.putDouble(t3, offset(iTypeAndOffsetAt), reader.readDouble());
                                    setFieldPresent(t3, iPositionForFieldNumber);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 1:
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    UnsafeUtil.putFloat(t3, offset(iTypeAndOffsetAt), reader.readFloat());
                                    setFieldPresent(t3, iPositionForFieldNumber);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 2:
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    UnsafeUtil.putLong(t3, offset(iTypeAndOffsetAt), reader.readInt64());
                                    setFieldPresent(t3, iPositionForFieldNumber);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 3:
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    UnsafeUtil.putLong(t3, offset(iTypeAndOffsetAt), reader.readUInt64());
                                    setFieldPresent(t3, iPositionForFieldNumber);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 4:
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    UnsafeUtil.putInt(t3, offset(iTypeAndOffsetAt), reader.readInt32());
                                    setFieldPresent(t3, iPositionForFieldNumber);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 5:
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    UnsafeUtil.putLong(t3, offset(iTypeAndOffsetAt), reader.readFixed64());
                                    setFieldPresent(t3, iPositionForFieldNumber);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 6:
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    UnsafeUtil.putInt(t3, offset(iTypeAndOffsetAt), reader.readFixed32());
                                    setFieldPresent(t3, iPositionForFieldNumber);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 7:
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    UnsafeUtil.putBoolean(t3, offset(iTypeAndOffsetAt), reader.readBool());
                                    setFieldPresent(t3, iPositionForFieldNumber);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 8:
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    readString(t3, iTypeAndOffsetAt, reader);
                                    setFieldPresent(t3, iPositionForFieldNumber);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 9:
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    messageLite2 = (MessageLite) mutableMessageFieldForMerge(t3, iPositionForFieldNumber);
                                    reader.mergeMessageField(messageLite2, getMessageFieldSchema(iPositionForFieldNumber), extensionRegistryLite2);
                                    storeMessageField(t3, iPositionForFieldNumber, messageLite2);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 10:
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    UnsafeUtil.putObject(t3, offset(iTypeAndOffsetAt), reader.readBytes());
                                    setFieldPresent(t3, iPositionForFieldNumber);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 11:
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    UnsafeUtil.putInt(t3, offset(iTypeAndOffsetAt), reader.readUInt32());
                                    setFieldPresent(t3, iPositionForFieldNumber);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 12:
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    int i5 = reader.readEnum();
                                    Internal.EnumVerifier enumFieldVerifier2 = getEnumFieldVerifier(iPositionForFieldNumber);
                                    if (enumFieldVerifier2 == null || enumFieldVerifier2.isInRange(i5)) {
                                        UnsafeUtil.putInt(t3, offset(iTypeAndOffsetAt), i5);
                                        setFieldPresent(t3, iPositionForFieldNumber);
                                        builderFromMessage = builderFromMessage;
                                    } else {
                                        builderFromMessage = SchemaUtil.storeUnknownEnum(t3, fieldNumber, i5, builderFromMessage, unknownFieldSchema2);
                                    }
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case TYPE_UINT32_VALUE:
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    UnsafeUtil.putInt(t3, offset(iTypeAndOffsetAt), reader.readSFixed32());
                                    setFieldPresent(t3, iPositionForFieldNumber);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case TYPE_ENUM_VALUE:
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    UnsafeUtil.putLong(t3, offset(iTypeAndOffsetAt), reader.readSFixed64());
                                    setFieldPresent(t3, iPositionForFieldNumber);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case TYPE_SFIXED32_VALUE:
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    UnsafeUtil.putInt(t3, offset(iTypeAndOffsetAt), reader.readSInt32());
                                    setFieldPresent(t3, iPositionForFieldNumber);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 16:
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    UnsafeUtil.putLong(t3, offset(iTypeAndOffsetAt), reader.readSInt64());
                                    setFieldPresent(t3, iPositionForFieldNumber);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case TYPE_SINT32_VALUE:
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    messageLite2 = (MessageLite) mutableMessageFieldForMerge(t3, iPositionForFieldNumber);
                                    reader.mergeGroupField(messageLite2, getMessageFieldSchema(iPositionForFieldNumber), extensionRegistryLite2);
                                    storeMessageField(t3, iPositionForFieldNumber, messageLite2);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case TYPE_SINT64_VALUE:
                                    listMutableListAt = this.listFieldSchema.mutableListAt(t3, offset(iTypeAndOffsetAt));
                                    reader.readDoubleList(listMutableListAt);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case Base64.Encoder.LINE_GROUPS /* 19 */:
                                    listMutableListAt2 = this.listFieldSchema.mutableListAt(t3, offset(iTypeAndOffsetAt));
                                    reader.readFloatList(listMutableListAt2);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case OFFSET_BITS /* 20 */:
                                    listMutableListAt3 = this.listFieldSchema.mutableListAt(t3, offset(iTypeAndOffsetAt));
                                    reader.readInt64List(listMutableListAt3);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 21:
                                    listMutableListAt4 = this.listFieldSchema.mutableListAt(t3, offset(iTypeAndOffsetAt));
                                    reader.readUInt64List(listMutableListAt4);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 22:
                                    listMutableListAt5 = this.listFieldSchema.mutableListAt(t3, offset(iTypeAndOffsetAt));
                                    reader.readInt32List(listMutableListAt5);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 23:
                                    listMutableListAt6 = this.listFieldSchema.mutableListAt(t3, offset(iTypeAndOffsetAt));
                                    reader.readFixed64List(listMutableListAt6);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case InsecureNonceXChaCha20.NONCE_SIZE_IN_BYTES /* 24 */:
                                    listMutableListAt7 = this.listFieldSchema.mutableListAt(t3, offset(iTypeAndOffsetAt));
                                    reader.readFixed32List(listMutableListAt7);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 25:
                                    listMutableListAt8 = this.listFieldSchema.mutableListAt(t3, offset(iTypeAndOffsetAt));
                                    reader.readBoolList(listMutableListAt8);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 26:
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    readStringList(t3, iTypeAndOffsetAt, reader);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 27:
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    readMessageList(t2, iTypeAndOffsetAt, reader, getMessageFieldSchema(iPositionForFieldNumber), extensionRegistryLite);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 28:
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    reader.readBytesList(this.listFieldSchema.mutableListAt(t3, offset(iTypeAndOffsetAt)));
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 29:
                                    listMutableListAt9 = this.listFieldSchema.mutableListAt(t3, offset(iTypeAndOffsetAt));
                                    reader.readUInt32List(listMutableListAt9);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 30:
                                    listMutableListAt10 = this.listFieldSchema.mutableListAt(t3, offset(iTypeAndOffsetAt));
                                    reader.readEnumList(listMutableListAt10);
                                    enumFieldVerifier = getEnumFieldVerifier(iPositionForFieldNumber);
                                    builderFromMessage = SchemaUtil.filterUnknownEnumList(t2, fieldNumber, listMutableListAt10, enumFieldVerifier, builderFromMessage, unknownFieldSchema);
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 31:
                                    listMutableListAt11 = this.listFieldSchema.mutableListAt(t3, offset(iTypeAndOffsetAt));
                                    reader.readSFixed32List(listMutableListAt11);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 32:
                                    listMutableListAt12 = this.listFieldSchema.mutableListAt(t3, offset(iTypeAndOffsetAt));
                                    reader.readSFixed64List(listMutableListAt12);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 33:
                                    listMutableListAt13 = this.listFieldSchema.mutableListAt(t3, offset(iTypeAndOffsetAt));
                                    reader.readSInt32List(listMutableListAt13);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 34:
                                    listMutableListAt14 = this.listFieldSchema.mutableListAt(t3, offset(iTypeAndOffsetAt));
                                    reader.readSInt64List(listMutableListAt14);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 35:
                                    listMutableListAt = this.listFieldSchema.mutableListAt(t3, offset(iTypeAndOffsetAt));
                                    reader.readDoubleList(listMutableListAt);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 36:
                                    listMutableListAt2 = this.listFieldSchema.mutableListAt(t3, offset(iTypeAndOffsetAt));
                                    reader.readFloatList(listMutableListAt2);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 37:
                                    listMutableListAt3 = this.listFieldSchema.mutableListAt(t3, offset(iTypeAndOffsetAt));
                                    reader.readInt64List(listMutableListAt3);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 38:
                                    listMutableListAt4 = this.listFieldSchema.mutableListAt(t3, offset(iTypeAndOffsetAt));
                                    reader.readUInt64List(listMutableListAt4);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 39:
                                    listMutableListAt5 = this.listFieldSchema.mutableListAt(t3, offset(iTypeAndOffsetAt));
                                    reader.readInt32List(listMutableListAt5);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 40:
                                    listMutableListAt6 = this.listFieldSchema.mutableListAt(t3, offset(iTypeAndOffsetAt));
                                    reader.readFixed64List(listMutableListAt6);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 41:
                                    listMutableListAt7 = this.listFieldSchema.mutableListAt(t3, offset(iTypeAndOffsetAt));
                                    reader.readFixed32List(listMutableListAt7);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 42:
                                    listMutableListAt8 = this.listFieldSchema.mutableListAt(t3, offset(iTypeAndOffsetAt));
                                    reader.readBoolList(listMutableListAt8);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 43:
                                    listMutableListAt9 = this.listFieldSchema.mutableListAt(t3, offset(iTypeAndOffsetAt));
                                    reader.readUInt32List(listMutableListAt9);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 44:
                                    listMutableListAt10 = this.listFieldSchema.mutableListAt(t3, offset(iTypeAndOffsetAt));
                                    reader.readEnumList(listMutableListAt10);
                                    enumFieldVerifier = getEnumFieldVerifier(iPositionForFieldNumber);
                                    builderFromMessage = SchemaUtil.filterUnknownEnumList(t2, fieldNumber, listMutableListAt10, enumFieldVerifier, builderFromMessage, unknownFieldSchema);
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 45:
                                    listMutableListAt11 = this.listFieldSchema.mutableListAt(t3, offset(iTypeAndOffsetAt));
                                    reader.readSFixed32List(listMutableListAt11);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 46:
                                    listMutableListAt12 = this.listFieldSchema.mutableListAt(t3, offset(iTypeAndOffsetAt));
                                    reader.readSFixed64List(listMutableListAt12);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 47:
                                    listMutableListAt13 = this.listFieldSchema.mutableListAt(t3, offset(iTypeAndOffsetAt));
                                    reader.readSInt32List(listMutableListAt13);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 48:
                                    listMutableListAt14 = this.listFieldSchema.mutableListAt(t3, offset(iTypeAndOffsetAt));
                                    reader.readSInt64List(listMutableListAt14);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 49:
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    readGroupList(t2, offset(iTypeAndOffsetAt), reader, getMessageFieldSchema(iPositionForFieldNumber), extensionRegistryLite);
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 50:
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    try {
                                        mergeMap(t2, iPositionForFieldNumber, getMapFieldDefaultEntry(iPositionForFieldNumber), extensionRegistryLite, reader);
                                        unknownFieldSchema2 = unknownFieldSchema2;
                                        builderFromMessage = builderFromMessage;
                                    } catch (InvalidProtocolBufferException.InvalidWireTypeException unused) {
                                        unknownFieldSchema2 = unknownFieldSchema2;
                                        builderFromMessage = builderFromMessage;
                                        if (unknownFieldSchema2.shouldDiscardUnknownFields(reader)) {
                                            if (builderFromMessage == null) {
                                                builderFromMessage = unknownFieldSchema2.getBuilderFromMessage(t3);
                                            }
                                            if (!unknownFieldSchema2.mergeOneFieldFrom(builderFromMessage, reader)) {
                                                objFilterMapUnknownEnumValues2 = builderFromMessage;
                                                for (i3 = this.checkInitializedCount; i3 < this.repeatedFieldOffsetStart; i3++) {
                                                    objFilterMapUnknownEnumValues2 = filterMapUnknownEnumValues(t2, this.intArray[i3], objFilterMapUnknownEnumValues2, unknownFieldSchema, t2);
                                                }
                                                if (objFilterMapUnknownEnumValues2 != null) {
                                                    unknownFieldSchema2.setBuilderToMessage(t3, objFilterMapUnknownEnumValues2);
                                                    return;
                                                }
                                                return;
                                            }
                                        } else if (!reader.skipField()) {
                                            objFilterMapUnknownEnumValues3 = builderFromMessage;
                                            for (i4 = this.checkInitializedCount; i4 < this.repeatedFieldOffsetStart; i4++) {
                                                objFilterMapUnknownEnumValues3 = filterMapUnknownEnumValues(t2, this.intArray[i4], objFilterMapUnknownEnumValues3, unknownFieldSchema, t2);
                                            }
                                            if (objFilterMapUnknownEnumValues3 != null) {
                                                unknownFieldSchema2.setBuilderToMessage(t3, objFilterMapUnknownEnumValues3);
                                                return;
                                            }
                                            return;
                                        }
                                    } catch (Throwable th) {
                                        th = th;
                                        unknownFieldSchema2 = unknownFieldSchema2;
                                        builderFromMessage = builderFromMessage;
                                        objFilterMapUnknownEnumValues = builderFromMessage;
                                        for (i2 = this.checkInitializedCount; i2 < this.repeatedFieldOffsetStart; i2++) {
                                            objFilterMapUnknownEnumValues = filterMapUnknownEnumValues(t2, this.intArray[i2], objFilterMapUnknownEnumValues, unknownFieldSchema, t2);
                                        }
                                        if (objFilterMapUnknownEnumValues != null) {
                                            unknownFieldSchema2.setBuilderToMessage(t3, objFilterMapUnknownEnumValues);
                                        }
                                        throw th;
                                    }
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case ONEOF_TYPE_OFFSET /* 51 */:
                                    UnsafeUtil.putObject(t3, offset(iTypeAndOffsetAt), Double.valueOf(reader.readDouble()));
                                    setOneofPresent(t3, fieldNumber, iPositionForFieldNumber);
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 52:
                                    UnsafeUtil.putObject(t3, offset(iTypeAndOffsetAt), Float.valueOf(reader.readFloat()));
                                    setOneofPresent(t3, fieldNumber, iPositionForFieldNumber);
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 53:
                                    UnsafeUtil.putObject(t3, offset(iTypeAndOffsetAt), Long.valueOf(reader.readInt64()));
                                    setOneofPresent(t3, fieldNumber, iPositionForFieldNumber);
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 54:
                                    UnsafeUtil.putObject(t3, offset(iTypeAndOffsetAt), Long.valueOf(reader.readUInt64()));
                                    setOneofPresent(t3, fieldNumber, iPositionForFieldNumber);
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 55:
                                    UnsafeUtil.putObject(t3, offset(iTypeAndOffsetAt), Integer.valueOf(reader.readInt32()));
                                    setOneofPresent(t3, fieldNumber, iPositionForFieldNumber);
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 56:
                                    UnsafeUtil.putObject(t3, offset(iTypeAndOffsetAt), Long.valueOf(reader.readFixed64()));
                                    setOneofPresent(t3, fieldNumber, iPositionForFieldNumber);
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 57:
                                    UnsafeUtil.putObject(t3, offset(iTypeAndOffsetAt), Integer.valueOf(reader.readFixed32()));
                                    setOneofPresent(t3, fieldNumber, iPositionForFieldNumber);
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 58:
                                    UnsafeUtil.putObject(t3, offset(iTypeAndOffsetAt), Boolean.valueOf(reader.readBool()));
                                    setOneofPresent(t3, fieldNumber, iPositionForFieldNumber);
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 59:
                                    readString(t3, iTypeAndOffsetAt, reader);
                                    setOneofPresent(t3, fieldNumber, iPositionForFieldNumber);
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 60:
                                    messageLite = (MessageLite) mutableOneofMessageFieldForMerge(t3, fieldNumber, iPositionForFieldNumber);
                                    reader.mergeMessageField(messageLite, getMessageFieldSchema(iPositionForFieldNumber), extensionRegistryLite2);
                                    storeOneofMessageField(t3, fieldNumber, iPositionForFieldNumber, messageLite);
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 61:
                                    UnsafeUtil.putObject(t3, offset(iTypeAndOffsetAt), reader.readBytes());
                                    setOneofPresent(t3, fieldNumber, iPositionForFieldNumber);
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 62:
                                    UnsafeUtil.putObject(t3, offset(iTypeAndOffsetAt), Integer.valueOf(reader.readUInt32()));
                                    setOneofPresent(t3, fieldNumber, iPositionForFieldNumber);
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 63:
                                    int i6 = reader.readEnum();
                                    Internal.EnumVerifier enumFieldVerifier3 = getEnumFieldVerifier(iPositionForFieldNumber);
                                    if (enumFieldVerifier3 == null || enumFieldVerifier3.isInRange(i6)) {
                                        UnsafeUtil.putObject(t3, offset(iTypeAndOffsetAt), Integer.valueOf(i6));
                                        setOneofPresent(t3, fieldNumber, iPositionForFieldNumber);
                                        builderFromMessage = builderFromMessage;
                                        extensionRegistryLite2 = extensionRegistryLite2;
                                        unknownFieldSchema2 = unknownFieldSchema2;
                                        builderFromMessage = builderFromMessage;
                                    } else {
                                        builderFromMessage = SchemaUtil.storeUnknownEnum(t3, fieldNumber, i6, builderFromMessage, unknownFieldSchema2);
                                        extensionRegistryLite2 = extensionRegistryLite2;
                                        unknownFieldSchema2 = unknownFieldSchema2;
                                    }
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 64:
                                    UnsafeUtil.putObject(t3, offset(iTypeAndOffsetAt), Integer.valueOf(reader.readSFixed32()));
                                    setOneofPresent(t3, fieldNumber, iPositionForFieldNumber);
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 65:
                                    UnsafeUtil.putObject(t3, offset(iTypeAndOffsetAt), Long.valueOf(reader.readSFixed64()));
                                    setOneofPresent(t3, fieldNumber, iPositionForFieldNumber);
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 66:
                                    UnsafeUtil.putObject(t3, offset(iTypeAndOffsetAt), Integer.valueOf(reader.readSInt32()));
                                    setOneofPresent(t3, fieldNumber, iPositionForFieldNumber);
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 67:
                                    UnsafeUtil.putObject(t3, offset(iTypeAndOffsetAt), Long.valueOf(reader.readSInt64()));
                                    setOneofPresent(t3, fieldNumber, iPositionForFieldNumber);
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    builderFromMessage = builderFromMessage;
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                case 68:
                                    try {
                                        messageLite = (MessageLite) mutableOneofMessageFieldForMerge(t3, fieldNumber, iPositionForFieldNumber);
                                        reader.mergeGroupField(messageLite, getMessageFieldSchema(iPositionForFieldNumber), extensionRegistryLite2);
                                        storeOneofMessageField(t3, fieldNumber, iPositionForFieldNumber, messageLite);
                                        builderFromMessage = builderFromMessage;
                                        extensionRegistryLite2 = extensionRegistryLite2;
                                        unknownFieldSchema2 = unknownFieldSchema2;
                                        builderFromMessage = builderFromMessage;
                                    } catch (InvalidProtocolBufferException.InvalidWireTypeException unused2) {
                                        extensionRegistryLite2 = extensionRegistryLite2;
                                        unknownFieldSchema2 = unknownFieldSchema2;
                                        if (unknownFieldSchema2.shouldDiscardUnknownFields(reader)) {
                                            if (builderFromMessage == null) {
                                                builderFromMessage = unknownFieldSchema2.getBuilderFromMessage(t3);
                                            }
                                            if (!unknownFieldSchema2.mergeOneFieldFrom(builderFromMessage, reader)) {
                                                objFilterMapUnknownEnumValues2 = builderFromMessage;
                                                while (i3 < this.repeatedFieldOffsetStart) {
                                                    objFilterMapUnknownEnumValues2 = filterMapUnknownEnumValues(t2, this.intArray[i3], objFilterMapUnknownEnumValues2, unknownFieldSchema, t2);
                                                }
                                                if (objFilterMapUnknownEnumValues2 != null) {
                                                    unknownFieldSchema2.setBuilderToMessage(t3, objFilterMapUnknownEnumValues2);
                                                    return;
                                                }
                                                return;
                                            }
                                        } else if (!reader.skipField()) {
                                            objFilterMapUnknownEnumValues3 = builderFromMessage;
                                            while (i4 < this.repeatedFieldOffsetStart) {
                                                objFilterMapUnknownEnumValues3 = filterMapUnknownEnumValues(t2, this.intArray[i4], objFilterMapUnknownEnumValues3, unknownFieldSchema, t2);
                                            }
                                            if (objFilterMapUnknownEnumValues3 != null) {
                                                unknownFieldSchema2.setBuilderToMessage(t3, objFilterMapUnknownEnumValues3);
                                                return;
                                            }
                                            return;
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                        unknownFieldSchema2 = unknownFieldSchema2;
                                        objFilterMapUnknownEnumValues = builderFromMessage;
                                        while (i2 < this.repeatedFieldOffsetStart) {
                                            objFilterMapUnknownEnumValues = filterMapUnknownEnumValues(t2, this.intArray[i2], objFilterMapUnknownEnumValues, unknownFieldSchema, t2);
                                        }
                                        if (objFilterMapUnknownEnumValues != null) {
                                            unknownFieldSchema2.setBuilderToMessage(t3, objFilterMapUnknownEnumValues);
                                        }
                                        throw th;
                                    }
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                                default:
                                    builderFromMessage = builderFromMessage;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    if (builderFromMessage == null) {
                                        try {
                                            builderFromMessage = unknownFieldSchema2.getBuilderFromMessage(t3);
                                        } catch (InvalidProtocolBufferException.InvalidWireTypeException unused3) {
                                            builderFromMessage = builderFromMessage;
                                            if (unknownFieldSchema2.shouldDiscardUnknownFields(reader)) {
                                                if (builderFromMessage == null) {
                                                    builderFromMessage = unknownFieldSchema2.getBuilderFromMessage(t3);
                                                }
                                                if (!unknownFieldSchema2.mergeOneFieldFrom(builderFromMessage, reader)) {
                                                    objFilterMapUnknownEnumValues2 = builderFromMessage;
                                                    while (i3 < this.repeatedFieldOffsetStart) {
                                                        objFilterMapUnknownEnumValues2 = filterMapUnknownEnumValues(t2, this.intArray[i3], objFilterMapUnknownEnumValues2, unknownFieldSchema, t2);
                                                    }
                                                    if (objFilterMapUnknownEnumValues2 != null) {
                                                        unknownFieldSchema2.setBuilderToMessage(t3, objFilterMapUnknownEnumValues2);
                                                        return;
                                                    }
                                                    return;
                                                }
                                            } else if (!reader.skipField()) {
                                                objFilterMapUnknownEnumValues3 = builderFromMessage;
                                                while (i4 < this.repeatedFieldOffsetStart) {
                                                    objFilterMapUnknownEnumValues3 = filterMapUnknownEnumValues(t2, this.intArray[i4], objFilterMapUnknownEnumValues3, unknownFieldSchema, t2);
                                                }
                                                if (objFilterMapUnknownEnumValues3 != null) {
                                                    unknownFieldSchema2.setBuilderToMessage(t3, objFilterMapUnknownEnumValues3);
                                                    return;
                                                }
                                                return;
                                            }
                                            t5 = t3;
                                            extensionRegistryLite2 = extensionRegistryLite2;
                                            unknownFieldSchema2 = unknownFieldSchema2;
                                        } catch (Throwable th3) {
                                            th = th3;
                                            builderFromMessage = builderFromMessage;
                                        }
                                    } else {
                                        builderFromMessage = builderFromMessage;
                                    }
                                    try {
                                        try {
                                            if (!unknownFieldSchema2.mergeOneFieldFrom(builderFromMessage, reader)) {
                                                Object objFilterMapUnknownEnumValues4 = builderFromMessage;
                                                for (int i7 = this.checkInitializedCount; i7 < this.repeatedFieldOffsetStart; i7++) {
                                                    objFilterMapUnknownEnumValues4 = filterMapUnknownEnumValues(t2, this.intArray[i7], objFilterMapUnknownEnumValues4, unknownFieldSchema, t2);
                                                }
                                                if (objFilterMapUnknownEnumValues4 != null) {
                                                    unknownFieldSchema2.setBuilderToMessage(t3, objFilterMapUnknownEnumValues4);
                                                    return;
                                                }
                                                return;
                                            }
                                        } catch (Throwable th4) {
                                            th = th4;
                                        }
                                        break;
                                    } catch (InvalidProtocolBufferException.InvalidWireTypeException unused4) {
                                        if (unknownFieldSchema2.shouldDiscardUnknownFields(reader)) {
                                            if (builderFromMessage == null) {
                                                builderFromMessage = unknownFieldSchema2.getBuilderFromMessage(t3);
                                            }
                                            if (!unknownFieldSchema2.mergeOneFieldFrom(builderFromMessage, reader)) {
                                                objFilterMapUnknownEnumValues2 = builderFromMessage;
                                                while (i3 < this.repeatedFieldOffsetStart) {
                                                    objFilterMapUnknownEnumValues2 = filterMapUnknownEnumValues(t2, this.intArray[i3], objFilterMapUnknownEnumValues2, unknownFieldSchema, t2);
                                                }
                                                if (objFilterMapUnknownEnumValues2 != null) {
                                                    unknownFieldSchema2.setBuilderToMessage(t3, objFilterMapUnknownEnumValues2);
                                                    return;
                                                }
                                                return;
                                            }
                                        } else if (!reader.skipField()) {
                                            objFilterMapUnknownEnumValues3 = builderFromMessage;
                                            while (i4 < this.repeatedFieldOffsetStart) {
                                                objFilterMapUnknownEnumValues3 = filterMapUnknownEnumValues(t2, this.intArray[i4], objFilterMapUnknownEnumValues3, unknownFieldSchema, t2);
                                            }
                                            if (objFilterMapUnknownEnumValues3 != null) {
                                                unknownFieldSchema2.setBuilderToMessage(t3, objFilterMapUnknownEnumValues3);
                                                return;
                                            }
                                            return;
                                        }
                                    }
                                    t5 = t3;
                                    extensionRegistryLite2 = extensionRegistryLite2;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    break;
                            }
                        } catch (InvalidProtocolBufferException.InvalidWireTypeException unused5) {
                        }
                    } catch (Throwable th5) {
                        th = th5;
                    }
                } else {
                    if (fieldNumber == Integer.MAX_VALUE) {
                        Object objFilterMapUnknownEnumValues5 = builderFromMessage;
                        for (int i8 = this.checkInitializedCount; i8 < this.repeatedFieldOffsetStart; i8++) {
                            objFilterMapUnknownEnumValues5 = filterMapUnknownEnumValues(t2, this.intArray[i8], objFilterMapUnknownEnumValues5, unknownFieldSchema, t2);
                        }
                        if (objFilterMapUnknownEnumValues5 != null) {
                            unknownFieldSchema2.setBuilderToMessage(t5, objFilterMapUnknownEnumValues5);
                            return;
                        }
                        return;
                    }
                    try {
                        Object objFindExtensionByNumber = !this.hasExtensions ? null : extensionSchema.findExtensionByNumber(extensionRegistryLite2, this.defaultInstance, fieldNumber);
                        if (objFindExtensionByNumber != null) {
                            if (obj == null) {
                                try {
                                    mutableExtensions = extensionSchema.getMutableExtensions(t2);
                                } catch (Throwable th6) {
                                    th = th6;
                                    unknownFieldSchema2 = unknownFieldSchema2;
                                    t3 = t5;
                                    objFilterMapUnknownEnumValues = builderFromMessage;
                                    while (i2 < this.repeatedFieldOffsetStart) {
                                        objFilterMapUnknownEnumValues = filterMapUnknownEnumValues(t2, this.intArray[i2], objFilterMapUnknownEnumValues, unknownFieldSchema, t2);
                                    }
                                    if (objFilterMapUnknownEnumValues != null) {
                                        unknownFieldSchema2.setBuilderToMessage(t3, objFilterMapUnknownEnumValues);
                                    }
                                    throw th;
                                }
                            } else {
                                mutableExtensions = obj;
                            }
                            t4 = t5;
                            try {
                                builderFromMessage = extensionSchema.parseExtension(t2, reader, objFindExtensionByNumber, extensionRegistryLite, mutableExtensions, builderFromMessage, unknownFieldSchema);
                                obj = mutableExtensions;
                            } catch (Throwable th7) {
                                th = th7;
                                t3 = t4;
                                unknownFieldSchema2 = unknownFieldSchema2;
                                objFilterMapUnknownEnumValues = builderFromMessage;
                                while (i2 < this.repeatedFieldOffsetStart) {
                                    objFilterMapUnknownEnumValues = filterMapUnknownEnumValues(t2, this.intArray[i2], objFilterMapUnknownEnumValues, unknownFieldSchema, t2);
                                }
                                if (objFilterMapUnknownEnumValues != null) {
                                    unknownFieldSchema2.setBuilderToMessage(t3, objFilterMapUnknownEnumValues);
                                }
                                throw th;
                            }
                        } else {
                            t4 = t5;
                            if (!unknownFieldSchema2.shouldDiscardUnknownFields(reader)) {
                                if (builderFromMessage == null) {
                                    builderFromMessage = unknownFieldSchema2.getBuilderFromMessage(t4);
                                }
                                if (!unknownFieldSchema2.mergeOneFieldFrom(builderFromMessage, reader)) {
                                }
                            } else if (!reader.skipField()) {
                            }
                        }
                        t5 = t4;
                    } catch (Throwable th8) {
                        th = th8;
                        t3 = t5;
                    }
                }
            } catch (Throwable th9) {
                th = th9;
            }
            objFilterMapUnknownEnumValues = builderFromMessage;
            while (i2 < this.repeatedFieldOffsetStart) {
                objFilterMapUnknownEnumValues = filterMapUnknownEnumValues(t2, this.intArray[i2], objFilterMapUnknownEnumValues, unknownFieldSchema, t2);
            }
            if (objFilterMapUnknownEnumValues != null) {
                unknownFieldSchema2.setBuilderToMessage(t3, objFilterMapUnknownEnumValues);
            }
            throw th;
        }
        int i9 = this.checkInitializedCount;
        Object objFilterMapUnknownEnumValues6 = builderFromMessage;
        while (i9 < this.repeatedFieldOffsetStart) {
            objFilterMapUnknownEnumValues6 = filterMapUnknownEnumValues(t2, this.intArray[i9], objFilterMapUnknownEnumValues6, unknownFieldSchema, t2);
            i9++;
            t4 = t4;
        }
        T t6 = t4;
        if (objFilterMapUnknownEnumValues6 != null) {
            unknownFieldSchema2.setBuilderToMessage(t6, objFilterMapUnknownEnumValues6);
        }
    }

    private final <K, V> void mergeMap(Object obj, int i2, Object obj2, ExtensionRegistryLite extensionRegistryLite, Reader reader) {
        long jOffset = offset(typeAndOffsetAt(i2));
        Object object = UnsafeUtil.getObject(obj, jOffset);
        if (object == null) {
            object = this.mapFieldSchema.newMapField(obj2);
            UnsafeUtil.putObject(obj, jOffset, object);
        } else if (this.mapFieldSchema.isImmutable(object)) {
            Object objNewMapField = this.mapFieldSchema.newMapField(obj2);
            this.mapFieldSchema.mergeFrom(objNewMapField, object);
            UnsafeUtil.putObject(obj, jOffset, objNewMapField);
            object = objNewMapField;
        }
        reader.readMap(this.mapFieldSchema.forMutableMapData(object), this.mapFieldSchema.forMapMetadata(obj2), extensionRegistryLite);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void mergeMessage(T t2, T t3, int i2) {
        if (isFieldPresent(t3, i2)) {
            long jOffset = offset(typeAndOffsetAt(i2));
            Unsafe unsafe = UNSAFE;
            Object object = unsafe.getObject(t3, jOffset);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + numberAt(i2) + " is present but null: " + t3);
            }
            Schema messageFieldSchema = getMessageFieldSchema(i2);
            if (!isFieldPresent(t2, i2)) {
                if (isMutable(object)) {
                    Object objNewInstance = messageFieldSchema.newInstance();
                    messageFieldSchema.mergeFrom(objNewInstance, object);
                    unsafe.putObject(t2, jOffset, objNewInstance);
                } else {
                    unsafe.putObject(t2, jOffset, object);
                }
                setFieldPresent(t2, i2);
                return;
            }
            Object object2 = unsafe.getObject(t2, jOffset);
            if (!isMutable(object2)) {
                Object objNewInstance2 = messageFieldSchema.newInstance();
                messageFieldSchema.mergeFrom(objNewInstance2, object2);
                unsafe.putObject(t2, jOffset, objNewInstance2);
                object2 = objNewInstance2;
            }
            messageFieldSchema.mergeFrom(object2, object);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void mergeOneofMessage(T t2, T t3, int i2) {
        int iNumberAt = numberAt(i2);
        if (isOneofPresent(t3, iNumberAt, i2)) {
            long jOffset = offset(typeAndOffsetAt(i2));
            Unsafe unsafe = UNSAFE;
            Object object = unsafe.getObject(t3, jOffset);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + numberAt(i2) + " is present but null: " + t3);
            }
            Schema messageFieldSchema = getMessageFieldSchema(i2);
            if (!isOneofPresent(t2, iNumberAt, i2)) {
                if (isMutable(object)) {
                    Object objNewInstance = messageFieldSchema.newInstance();
                    messageFieldSchema.mergeFrom(objNewInstance, object);
                    unsafe.putObject(t2, jOffset, objNewInstance);
                } else {
                    unsafe.putObject(t2, jOffset, object);
                }
                setOneofPresent(t2, iNumberAt, i2);
                return;
            }
            Object object2 = unsafe.getObject(t2, jOffset);
            if (!isMutable(object2)) {
                Object objNewInstance2 = messageFieldSchema.newInstance();
                messageFieldSchema.mergeFrom(objNewInstance2, object2);
                unsafe.putObject(t2, jOffset, objNewInstance2);
                object2 = objNewInstance2;
            }
            messageFieldSchema.mergeFrom(object2, object);
        }
    }

    private void mergeSingleField(T t2, T t3, int i2) {
        int iTypeAndOffsetAt = typeAndOffsetAt(i2);
        long jOffset = offset(iTypeAndOffsetAt);
        int iNumberAt = numberAt(i2);
        switch (type(iTypeAndOffsetAt)) {
            case 0:
                if (isFieldPresent(t3, i2)) {
                    UnsafeUtil.putDouble(t2, jOffset, UnsafeUtil.getDouble(t3, jOffset));
                    setFieldPresent(t2, i2);
                }
                break;
            case 1:
                if (isFieldPresent(t3, i2)) {
                    UnsafeUtil.putFloat(t2, jOffset, UnsafeUtil.getFloat(t3, jOffset));
                    setFieldPresent(t2, i2);
                }
                break;
            case 2:
                if (!isFieldPresent(t3, i2)) {
                }
                UnsafeUtil.putLong(t2, jOffset, UnsafeUtil.getLong(t3, jOffset));
                setFieldPresent(t2, i2);
                break;
            case 3:
                if (!isFieldPresent(t3, i2)) {
                }
                UnsafeUtil.putLong(t2, jOffset, UnsafeUtil.getLong(t3, jOffset));
                setFieldPresent(t2, i2);
                break;
            case 4:
                if (!isFieldPresent(t3, i2)) {
                }
                UnsafeUtil.putInt(t2, jOffset, UnsafeUtil.getInt(t3, jOffset));
                setFieldPresent(t2, i2);
                break;
            case 5:
                if (!isFieldPresent(t3, i2)) {
                }
                UnsafeUtil.putLong(t2, jOffset, UnsafeUtil.getLong(t3, jOffset));
                setFieldPresent(t2, i2);
                break;
            case 6:
                if (!isFieldPresent(t3, i2)) {
                }
                UnsafeUtil.putInt(t2, jOffset, UnsafeUtil.getInt(t3, jOffset));
                setFieldPresent(t2, i2);
                break;
            case 7:
                if (isFieldPresent(t3, i2)) {
                    UnsafeUtil.putBoolean(t2, jOffset, UnsafeUtil.getBoolean(t3, jOffset));
                    setFieldPresent(t2, i2);
                }
                break;
            case 8:
                if (!isFieldPresent(t3, i2)) {
                }
                UnsafeUtil.putObject(t2, jOffset, UnsafeUtil.getObject(t3, jOffset));
                setFieldPresent(t2, i2);
                break;
            case 9:
            case TYPE_SINT32_VALUE:
                mergeMessage(t2, t3, i2);
                break;
            case 10:
                if (!isFieldPresent(t3, i2)) {
                }
                UnsafeUtil.putObject(t2, jOffset, UnsafeUtil.getObject(t3, jOffset));
                setFieldPresent(t2, i2);
                break;
            case 11:
                if (!isFieldPresent(t3, i2)) {
                }
                UnsafeUtil.putInt(t2, jOffset, UnsafeUtil.getInt(t3, jOffset));
                setFieldPresent(t2, i2);
                break;
            case 12:
                if (!isFieldPresent(t3, i2)) {
                }
                UnsafeUtil.putInt(t2, jOffset, UnsafeUtil.getInt(t3, jOffset));
                setFieldPresent(t2, i2);
                break;
            case TYPE_UINT32_VALUE:
                if (!isFieldPresent(t3, i2)) {
                }
                UnsafeUtil.putInt(t2, jOffset, UnsafeUtil.getInt(t3, jOffset));
                setFieldPresent(t2, i2);
                break;
            case TYPE_ENUM_VALUE:
                if (!isFieldPresent(t3, i2)) {
                }
                UnsafeUtil.putLong(t2, jOffset, UnsafeUtil.getLong(t3, jOffset));
                setFieldPresent(t2, i2);
                break;
            case TYPE_SFIXED32_VALUE:
                if (!isFieldPresent(t3, i2)) {
                }
                UnsafeUtil.putInt(t2, jOffset, UnsafeUtil.getInt(t3, jOffset));
                setFieldPresent(t2, i2);
                break;
            case 16:
                if (!isFieldPresent(t3, i2)) {
                }
                UnsafeUtil.putLong(t2, jOffset, UnsafeUtil.getLong(t3, jOffset));
                setFieldPresent(t2, i2);
                break;
            case TYPE_SINT64_VALUE:
            case Base64.Encoder.LINE_GROUPS /* 19 */:
            case OFFSET_BITS /* 20 */:
            case 21:
            case 22:
            case 23:
            case InsecureNonceXChaCha20.NONCE_SIZE_IN_BYTES /* 24 */:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
                this.listFieldSchema.mergeListsAt(t2, t3, jOffset);
                break;
            case 50:
                SchemaUtil.mergeMap(this.mapFieldSchema, t2, t3, jOffset);
                break;
            case ONEOF_TYPE_OFFSET /* 51 */:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
                if (!isOneofPresent(t3, iNumberAt, i2)) {
                }
                UnsafeUtil.putObject(t2, jOffset, UnsafeUtil.getObject(t3, jOffset));
                setOneofPresent(t2, iNumberAt, i2);
                break;
            case 60:
            case 68:
                mergeOneofMessage(t2, t3, i2);
                break;
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
                if (!isOneofPresent(t3, iNumberAt, i2)) {
                }
                UnsafeUtil.putObject(t2, jOffset, UnsafeUtil.getObject(t3, jOffset));
                setOneofPresent(t2, iNumberAt, i2);
                break;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Object mutableMessageFieldForMerge(T t2, int i2) {
        Schema messageFieldSchema = getMessageFieldSchema(i2);
        long jOffset = offset(typeAndOffsetAt(i2));
        if (!isFieldPresent(t2, i2)) {
            return messageFieldSchema.newInstance();
        }
        Object object = UNSAFE.getObject(t2, jOffset);
        if (isMutable(object)) {
            return object;
        }
        Object objNewInstance = messageFieldSchema.newInstance();
        if (object != null) {
            messageFieldSchema.mergeFrom(objNewInstance, object);
        }
        return objNewInstance;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Object mutableOneofMessageFieldForMerge(T t2, int i2, int i3) {
        Schema messageFieldSchema = getMessageFieldSchema(i3);
        if (!isOneofPresent(t2, i2, i3)) {
            return messageFieldSchema.newInstance();
        }
        Object object = UNSAFE.getObject(t2, offset(typeAndOffsetAt(i3)));
        if (isMutable(object)) {
            return object;
        }
        Object objNewInstance = messageFieldSchema.newInstance();
        if (object != null) {
            messageFieldSchema.mergeFrom(objNewInstance, object);
        }
        return objNewInstance;
    }

    public static <T> MessageSchema<T> newSchema(Class<T> cls, MessageInfo messageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        return messageInfo instanceof RawMessageInfo ? newSchemaForRawMessageInfo((RawMessageInfo) messageInfo, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema) : newSchemaForMessageInfo((StructuralMessageInfo) messageInfo, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    public static <T> MessageSchema<T> newSchemaForMessageInfo(StructuralMessageInfo structuralMessageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        int fieldNumber;
        int fieldNumber2;
        boolean z2 = structuralMessageInfo.getSyntax() == ProtoSyntax.PROTO3;
        FieldInfo[] fields = structuralMessageInfo.getFields();
        if (fields.length == 0) {
            fieldNumber = 0;
            fieldNumber2 = 0;
        } else {
            fieldNumber = fields[0].getFieldNumber();
            fieldNumber2 = fields[fields.length - 1].getFieldNumber();
        }
        int length = fields.length;
        int[] iArr = new int[length * 3];
        Object[] objArr = new Object[length * 2];
        int i2 = 0;
        int i3 = 0;
        for (FieldInfo fieldInfo : fields) {
            if (fieldInfo.getType() == FieldType.MAP) {
                i2++;
            } else if (fieldInfo.getType().id() >= 18 && fieldInfo.getType().id() <= 49) {
                i3++;
            }
        }
        int[] iArr2 = i2 > 0 ? new int[i2] : null;
        int[] iArr3 = i3 > 0 ? new int[i3] : null;
        int[] checkInitialized = structuralMessageInfo.getCheckInitialized();
        if (checkInitialized == null) {
            checkInitialized = EMPTY_INT_ARRAY;
        }
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i4 < fields.length) {
            FieldInfo fieldInfo2 = fields[i4];
            int fieldNumber3 = fieldInfo2.getFieldNumber();
            storeFieldData(fieldInfo2, iArr, i5, objArr);
            if (i6 < checkInitialized.length && checkInitialized[i6] == fieldNumber3) {
                checkInitialized[i6] = i5;
                i6++;
            }
            if (fieldInfo2.getType() == FieldType.MAP) {
                iArr2[i7] = i5;
                i7++;
            } else {
                if (fieldInfo2.getType().id() >= 18 && fieldInfo2.getType().id() <= 49) {
                    iArr3[i8] = (int) UnsafeUtil.objectFieldOffset(fieldInfo2.getField());
                    i8++;
                }
                i4++;
                i5 += 3;
            }
            i4++;
            i5 += 3;
        }
        if (iArr2 == null) {
            iArr2 = EMPTY_INT_ARRAY;
        }
        if (iArr3 == null) {
            iArr3 = EMPTY_INT_ARRAY;
        }
        int[] iArr4 = new int[checkInitialized.length + iArr2.length + iArr3.length];
        System.arraycopy(checkInitialized, 0, iArr4, 0, checkInitialized.length);
        System.arraycopy(iArr2, 0, iArr4, checkInitialized.length, iArr2.length);
        System.arraycopy(iArr3, 0, iArr4, checkInitialized.length + iArr2.length, iArr3.length);
        return new MessageSchema<>(iArr, objArr, fieldNumber, fieldNumber2, structuralMessageInfo.getDefaultInstance(), z2, true, iArr4, checkInitialized.length, checkInitialized.length + iArr2.length, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    /* JADX WARN: Code duplicated, block: B:122:0x024d  */
    /* JADX WARN: Code duplicated, block: B:123:0x0250  */
    /* JADX WARN: Code duplicated, block: B:126:0x0268  */
    /* JADX WARN: Code duplicated, block: B:127:0x026b  */
    /* JADX WARN: Code duplicated, block: B:161:0x0319  */
    /* JADX WARN: Code duplicated, block: B:162:0x031b  */
    /* JADX WARN: Code duplicated, block: B:164:0x031e  */
    /* JADX WARN: Code duplicated, block: B:179:0x036b  */
    /* JADX WARN: Code duplicated, block: B:182:0x0378  */
    public static <T> MessageSchema<T> newSchemaForRawMessageInfo(RawMessageInfo rawMessageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        int i2;
        int iCharAt;
        int iCharAt2;
        int iCharAt3;
        int iCharAt4;
        int iCharAt5;
        int[] iArr;
        int i3;
        int i4;
        int i5;
        char cCharAt;
        int i6;
        char cCharAt2;
        int i7;
        char cCharAt3;
        int i8;
        char cCharAt4;
        int i9;
        char cCharAt5;
        int i10;
        char cCharAt6;
        int i11;
        char cCharAt7;
        int i12;
        char cCharAt8;
        int i13;
        int i14;
        int i15;
        int i16;
        int iObjectFieldOffset;
        boolean z2;
        int iObjectFieldOffset2;
        int i17;
        int i18;
        java.lang.reflect.Field fieldReflectField;
        char cCharAt9;
        int i19;
        int i20;
        int i21;
        Object obj;
        java.lang.reflect.Field fieldReflectField2;
        int i22;
        Object obj2;
        java.lang.reflect.Field fieldReflectField3;
        int i23;
        char cCharAt10;
        int i24;
        char cCharAt11;
        int i25;
        char cCharAt12;
        int i26;
        char cCharAt13;
        boolean z3 = rawMessageInfo.getSyntax() == ProtoSyntax.PROTO3;
        String stringInfo = rawMessageInfo.getStringInfo();
        int length = stringInfo.length();
        char c2 = 55296;
        if (stringInfo.charAt(0) >= 55296) {
            int i27 = 1;
            while (true) {
                i2 = i27 + 1;
                if (stringInfo.charAt(i27) < 55296) {
                    break;
                }
                i27 = i2;
            }
        } else {
            i2 = 1;
        }
        int i28 = i2 + 1;
        int iCharAt6 = stringInfo.charAt(i2);
        if (iCharAt6 >= 55296) {
            int i29 = iCharAt6 & 8191;
            int i30 = 13;
            while (true) {
                i26 = i28 + 1;
                cCharAt13 = stringInfo.charAt(i28);
                if (cCharAt13 < 55296) {
                    break;
                }
                i29 |= (cCharAt13 & 8191) << i30;
                i30 += 13;
                i28 = i26;
            }
            iCharAt6 = i29 | (cCharAt13 << i30);
            i28 = i26;
        }
        if (iCharAt6 == 0) {
            iArr = EMPTY_INT_ARRAY;
            i4 = 0;
            iCharAt = 0;
            iCharAt2 = 0;
            iCharAt3 = 0;
            iCharAt4 = 0;
            iCharAt5 = 0;
            i3 = 0;
        } else {
            int i31 = i28 + 1;
            int iCharAt7 = stringInfo.charAt(i28);
            if (iCharAt7 >= 55296) {
                int i32 = iCharAt7 & 8191;
                int i33 = 13;
                while (true) {
                    i12 = i31 + 1;
                    cCharAt8 = stringInfo.charAt(i31);
                    if (cCharAt8 < 55296) {
                        break;
                    }
                    i32 |= (cCharAt8 & 8191) << i33;
                    i33 += 13;
                    i31 = i12;
                }
                iCharAt7 = i32 | (cCharAt8 << i33);
                i31 = i12;
            }
            int i34 = i31 + 1;
            int iCharAt8 = stringInfo.charAt(i31);
            if (iCharAt8 >= 55296) {
                int i35 = iCharAt8 & 8191;
                int i36 = 13;
                while (true) {
                    i11 = i34 + 1;
                    cCharAt7 = stringInfo.charAt(i34);
                    if (cCharAt7 < 55296) {
                        break;
                    }
                    i35 |= (cCharAt7 & 8191) << i36;
                    i36 += 13;
                    i34 = i11;
                }
                iCharAt8 = i35 | (cCharAt7 << i36);
                i34 = i11;
            }
            int i37 = i34 + 1;
            iCharAt = stringInfo.charAt(i34);
            if (iCharAt >= 55296) {
                int i38 = iCharAt & 8191;
                int i39 = 13;
                while (true) {
                    i10 = i37 + 1;
                    cCharAt6 = stringInfo.charAt(i37);
                    if (cCharAt6 < 55296) {
                        break;
                    }
                    i38 |= (cCharAt6 & 8191) << i39;
                    i39 += 13;
                    i37 = i10;
                }
                iCharAt = i38 | (cCharAt6 << i39);
                i37 = i10;
            }
            int i40 = i37 + 1;
            iCharAt2 = stringInfo.charAt(i37);
            if (iCharAt2 >= 55296) {
                int i41 = iCharAt2 & 8191;
                int i42 = 13;
                while (true) {
                    i9 = i40 + 1;
                    cCharAt5 = stringInfo.charAt(i40);
                    if (cCharAt5 < 55296) {
                        break;
                    }
                    i41 |= (cCharAt5 & 8191) << i42;
                    i42 += 13;
                    i40 = i9;
                }
                iCharAt2 = i41 | (cCharAt5 << i42);
                i40 = i9;
            }
            int i43 = i40 + 1;
            iCharAt3 = stringInfo.charAt(i40);
            if (iCharAt3 >= 55296) {
                int i44 = iCharAt3 & 8191;
                int i45 = 13;
                while (true) {
                    i8 = i43 + 1;
                    cCharAt4 = stringInfo.charAt(i43);
                    if (cCharAt4 < 55296) {
                        break;
                    }
                    i44 |= (cCharAt4 & 8191) << i45;
                    i45 += 13;
                    i43 = i8;
                }
                iCharAt3 = i44 | (cCharAt4 << i45);
                i43 = i8;
            }
            int i46 = i43 + 1;
            iCharAt4 = stringInfo.charAt(i43);
            if (iCharAt4 >= 55296) {
                int i47 = iCharAt4 & 8191;
                int i48 = 13;
                while (true) {
                    i7 = i46 + 1;
                    cCharAt3 = stringInfo.charAt(i46);
                    if (cCharAt3 < 55296) {
                        break;
                    }
                    i47 |= (cCharAt3 & 8191) << i48;
                    i48 += 13;
                    i46 = i7;
                }
                iCharAt4 = i47 | (cCharAt3 << i48);
                i46 = i7;
            }
            int i49 = i46 + 1;
            int iCharAt9 = stringInfo.charAt(i46);
            if (iCharAt9 >= 55296) {
                int i50 = iCharAt9 & 8191;
                int i51 = 13;
                while (true) {
                    i6 = i49 + 1;
                    cCharAt2 = stringInfo.charAt(i49);
                    if (cCharAt2 < 55296) {
                        break;
                    }
                    i50 |= (cCharAt2 & 8191) << i51;
                    i51 += 13;
                    i49 = i6;
                }
                iCharAt9 = i50 | (cCharAt2 << i51);
                i49 = i6;
            }
            int i52 = i49 + 1;
            iCharAt5 = stringInfo.charAt(i49);
            if (iCharAt5 >= 55296) {
                int i53 = iCharAt5 & 8191;
                int i54 = 13;
                while (true) {
                    i5 = i52 + 1;
                    cCharAt = stringInfo.charAt(i52);
                    if (cCharAt < 55296) {
                        break;
                    }
                    i53 |= (cCharAt & 8191) << i54;
                    i54 += 13;
                    i52 = i5;
                }
                iCharAt5 = i53 | (cCharAt << i54);
                i52 = i5;
            }
            iArr = new int[iCharAt5 + iCharAt4 + iCharAt9];
            i3 = (iCharAt7 * 2) + iCharAt8;
            i4 = iCharAt7;
            i28 = i52;
        }
        Unsafe unsafe = UNSAFE;
        Object[] objects = rawMessageInfo.getObjects();
        Class<?> cls = rawMessageInfo.getDefaultInstance().getClass();
        int[] iArr2 = new int[iCharAt3 * 3];
        Object[] objArr = new Object[iCharAt3 * 2];
        int i55 = iCharAt5 + iCharAt4;
        int i56 = iCharAt5;
        int i57 = i55;
        int i58 = 0;
        int i59 = 0;
        while (i28 < length) {
            int i60 = i28 + 1;
            int iCharAt10 = stringInfo.charAt(i28);
            if (iCharAt10 >= c2) {
                int i61 = iCharAt10 & 8191;
                int i62 = i60;
                int i63 = 13;
                while (true) {
                    i25 = i62 + 1;
                    cCharAt12 = stringInfo.charAt(i62);
                    if (cCharAt12 < c2) {
                        break;
                    }
                    i61 |= (cCharAt12 & 8191) << i63;
                    i63 += 13;
                    i62 = i25;
                }
                iCharAt10 = i61 | (cCharAt12 << i63);
                i13 = i25;
            } else {
                i13 = i60;
            }
            int i64 = i13 + 1;
            int iCharAt11 = stringInfo.charAt(i13);
            if (iCharAt11 >= c2) {
                int i65 = iCharAt11 & 8191;
                int i66 = i64;
                int i67 = 13;
                while (true) {
                    i24 = i66 + 1;
                    cCharAt11 = stringInfo.charAt(i66);
                    i14 = length;
                    if (cCharAt11 < 55296) {
                        break;
                    }
                    i65 |= (cCharAt11 & 8191) << i67;
                    i67 += 13;
                    i66 = i24;
                    length = i14;
                }
                iCharAt11 = i65 | (cCharAt11 << i67);
                i15 = i24;
            } else {
                i14 = length;
                i15 = i64;
            }
            int i68 = iCharAt11 & 255;
            int i69 = iCharAt5;
            if ((iCharAt11 & 1024) != 0) {
                iArr[i58] = i59;
                i58++;
            }
            int i70 = i58;
            if (i68 >= ONEOF_TYPE_OFFSET) {
                int i71 = i15 + 1;
                int iCharAt12 = stringInfo.charAt(i15);
                char c3 = 55296;
                if (iCharAt12 >= 55296) {
                    int i72 = iCharAt12 & 8191;
                    int i73 = 13;
                    while (true) {
                        i23 = i71 + 1;
                        cCharAt10 = stringInfo.charAt(i71);
                        if (cCharAt10 < c3) {
                            break;
                        }
                        i72 |= (cCharAt10 & 8191) << i73;
                        i73 += 13;
                        i71 = i23;
                        c3 = 55296;
                    }
                    iCharAt12 = i72 | (cCharAt10 << i73);
                    i71 = i23;
                }
                int i74 = i68 - 51;
                int i75 = i71;
                if (i74 == 9 || i74 == 17) {
                    i20 = i3 + 1;
                    objArr[((i59 / 3) * 2) + 1] = objects[i3];
                } else {
                    if (i74 == 12 && !z3) {
                        i20 = i3 + 1;
                        objArr[((i59 / 3) * 2) + 1] = objects[i3];
                    }
                    i21 = iCharAt12 * 2;
                    obj = objects[i21];
                    if (obj instanceof java.lang.reflect.Field) {
                        fieldReflectField2 = (java.lang.reflect.Field) obj;
                    } else {
                        fieldReflectField2 = reflectField(cls, (String) obj);
                        objects[i21] = fieldReflectField2;
                    }
                    int iObjectFieldOffset3 = (int) unsafe.objectFieldOffset(fieldReflectField2);
                    i22 = i21 + 1;
                    obj2 = objects[i22];
                    if (obj2 instanceof java.lang.reflect.Field) {
                        fieldReflectField3 = (java.lang.reflect.Field) obj2;
                    } else {
                        fieldReflectField3 = reflectField(cls, (String) obj2);
                        objects[i22] = fieldReflectField3;
                    }
                    iObjectFieldOffset2 = (int) unsafe.objectFieldOffset(fieldReflectField3);
                    z3 = z3;
                    objArr = objArr;
                    i17 = i75;
                    iObjectFieldOffset = iObjectFieldOffset3;
                    i18 = 0;
                }
                i3 = i20;
                i21 = iCharAt12 * 2;
                obj = objects[i21];
                if (obj instanceof java.lang.reflect.Field) {
                    fieldReflectField2 = (java.lang.reflect.Field) obj;
                } else {
                    fieldReflectField2 = reflectField(cls, (String) obj);
                    objects[i21] = fieldReflectField2;
                }
                int iObjectFieldOffset4 = (int) unsafe.objectFieldOffset(fieldReflectField2);
                i22 = i21 + 1;
                obj2 = objects[i22];
                if (obj2 instanceof java.lang.reflect.Field) {
                    fieldReflectField3 = (java.lang.reflect.Field) obj2;
                } else {
                    fieldReflectField3 = reflectField(cls, (String) obj2);
                    objects[i22] = fieldReflectField3;
                }
                iObjectFieldOffset2 = (int) unsafe.objectFieldOffset(fieldReflectField3);
                z3 = z3;
                objArr = objArr;
                i17 = i75;
                iObjectFieldOffset = iObjectFieldOffset4;
                i18 = 0;
            } else {
                int i76 = i3 + 1;
                java.lang.reflect.Field fieldReflectField4 = reflectField(cls, (String) objects[i3]);
                if (i68 == 9 || i68 == 17) {
                    objArr[((i59 / 3) * 2) + 1] = fieldReflectField4.getType();
                } else {
                    if (i68 == 27 || i68 == 49) {
                        i19 = i76 + 1;
                        objArr[((i59 / 3) * 2) + 1] = objects[i76];
                    } else if (i68 == 12 || i68 == 30 || i68 == 44) {
                        if (!z3) {
                            i19 = i76 + 1;
                            objArr[((i59 / 3) * 2) + 1] = objects[i76];
                        }
                    } else if (i68 == 50) {
                        int i77 = i56 + 1;
                        iArr[i56] = i59;
                        int i78 = (i59 / 3) * 2;
                        int i79 = i76 + 1;
                        objArr[i78] = objects[i76];
                        if ((iCharAt11 & 2048) != 0) {
                            i76 = i79 + 1;
                            objArr[i78 + 1] = objects[i79];
                            i56 = i77;
                        } else {
                            i56 = i77;
                            i16 = i79;
                        }
                        iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldReflectField4);
                        int i80 = i16;
                        if ((iCharAt11 & 4096) == 4096) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if (z2 || i68 > 17) {
                            iObjectFieldOffset2 = 1048575;
                            i17 = i15;
                            i18 = 0;
                        } else {
                            int i81 = i15 + 1;
                            int iCharAt13 = stringInfo.charAt(i15);
                            if (iCharAt13 >= 55296) {
                                int i82 = iCharAt13 & 8191;
                                int i83 = 13;
                                while (true) {
                                    i17 = i81 + 1;
                                    cCharAt9 = stringInfo.charAt(i81);
                                    if (cCharAt9 < 55296) {
                                        break;
                                    }
                                    i82 |= (cCharAt9 & 8191) << i83;
                                    i83 += 13;
                                    i81 = i17;
                                }
                                iCharAt13 = i82 | (cCharAt9 << i83);
                            } else {
                                i17 = i81;
                            }
                            int i84 = (iCharAt13 / 32) + (i4 * 2);
                            Object obj3 = objects[i84];
                            if (obj3 instanceof java.lang.reflect.Field) {
                                fieldReflectField = (java.lang.reflect.Field) obj3;
                            } else {
                                fieldReflectField = reflectField(cls, (String) obj3);
                                objects[i84] = fieldReflectField;
                            }
                            iObjectFieldOffset2 = (int) unsafe.objectFieldOffset(fieldReflectField);
                            i18 = iCharAt13 % 32;
                        }
                        if (i68 >= 18 && i68 <= 49) {
                            iArr[i57] = iObjectFieldOffset;
                            i57++;
                        }
                        i3 = i80;
                    }
                    i16 = i19;
                    iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldReflectField4);
                    int i85 = i16;
                    if ((iCharAt11 & 4096) == 4096) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (z2) {
                        iObjectFieldOffset2 = 1048575;
                        i17 = i15;
                        i18 = 0;
                    } else {
                        iObjectFieldOffset2 = 1048575;
                        i17 = i15;
                        i18 = 0;
                    }
                    if (i68 >= 18) {
                        iArr[i57] = iObjectFieldOffset;
                        i57++;
                    }
                    i3 = i85;
                }
                i16 = i76;
                iObjectFieldOffset = (int) unsafe.objectFieldOffset(fieldReflectField4);
                int i86 = i16;
                if ((iCharAt11 & 4096) == 4096) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (z2) {
                    iObjectFieldOffset2 = 1048575;
                    i17 = i15;
                    i18 = 0;
                } else {
                    iObjectFieldOffset2 = 1048575;
                    i17 = i15;
                    i18 = 0;
                }
                if (i68 >= 18) {
                    iArr[i57] = iObjectFieldOffset;
                    i57++;
                }
                i3 = i86;
            }
            int i87 = i59 + 1;
            iArr2[i59] = iCharAt10;
            int i88 = i87 + 1;
            iArr2[i87] = ((iCharAt11 & MasterKey.DEFAULT_AES_GCM_MASTER_KEY_SIZE) != 0 ? REQUIRED_MASK : 0) | ((iCharAt11 & 512) != 0 ? ENFORCE_UTF8_MASK : 0) | (i68 << OFFSET_BITS) | iObjectFieldOffset;
            i59 = i88 + 1;
            iArr2[i88] = (i18 << OFFSET_BITS) | iObjectFieldOffset2;
            z3 = z3;
            iCharAt = iCharAt;
            iCharAt5 = i69;
            objArr = objArr;
            length = i14;
            i28 = i17;
            i58 = i70;
            iCharAt2 = iCharAt2;
            c2 = 55296;
        }
        return new MessageSchema<>(iArr2, objArr, iCharAt, iCharAt2, rawMessageInfo.getDefaultInstance(), z3, false, iArr, iCharAt5, i55, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    private int numberAt(int i2) {
        return this.buffer[i2];
    }

    private static long offset(int i2) {
        return i2 & 1048575;
    }

    private static <T> boolean oneofBooleanAt(T t2, long j2) {
        return ((Boolean) UnsafeUtil.getObject(t2, j2)).booleanValue();
    }

    private static <T> double oneofDoubleAt(T t2, long j2) {
        return ((Double) UnsafeUtil.getObject(t2, j2)).doubleValue();
    }

    private static <T> float oneofFloatAt(T t2, long j2) {
        return ((Float) UnsafeUtil.getObject(t2, j2)).floatValue();
    }

    private static <T> int oneofIntAt(T t2, long j2) {
        return ((Integer) UnsafeUtil.getObject(t2, j2)).intValue();
    }

    private static <T> long oneofLongAt(T t2, long j2) {
        return ((Long) UnsafeUtil.getObject(t2, j2)).longValue();
    }

    private <K, V> int parseMapField(T t2, byte[] bArr, int i2, int i3, int i4, long j2, ArrayDecoders.Registers registers) {
        Unsafe unsafe = UNSAFE;
        Object mapFieldDefaultEntry = getMapFieldDefaultEntry(i4);
        Object object = unsafe.getObject(t2, j2);
        if (this.mapFieldSchema.isImmutable(object)) {
            Object objNewMapField = this.mapFieldSchema.newMapField(mapFieldDefaultEntry);
            this.mapFieldSchema.mergeFrom(objNewMapField, object);
            unsafe.putObject(t2, j2, objNewMapField);
            object = objNewMapField;
        }
        return decodeMapEntry(bArr, i2, i3, this.mapFieldSchema.forMapMetadata(mapFieldDefaultEntry), this.mapFieldSchema.forMutableMapData(object), registers);
    }

    private int parseOneofField(T t2, byte[] bArr, int i2, int i3, int i4, int i5, int i6, int i7, int i8, long j2, int i9, ArrayDecoders.Registers registers) throws InvalidProtocolBufferException {
        Object objValueOf;
        Object objValueOf2;
        int iDecodeVarint64;
        long jDecodeZigZag64;
        int iDecodeZigZag32;
        Object objValueOf3;
        Object objMutableOneofMessageFieldForMerge;
        int iMergeMessageField;
        Unsafe unsafe = UNSAFE;
        long j3 = this.buffer[i9 + 2] & 1048575;
        switch (i8) {
            case ONEOF_TYPE_OFFSET /* 51 */:
                if (i6 != 1) {
                    return i2;
                }
                objValueOf = Double.valueOf(ArrayDecoders.decodeDouble(bArr, i2));
                unsafe.putObject(t2, j2, objValueOf);
                iDecodeVarint64 = i2 + 8;
                unsafe.putInt(t2, j3, i5);
                return iDecodeVarint64;
            case 52:
                if (i6 != 5) {
                    return i2;
                }
                objValueOf2 = Float.valueOf(ArrayDecoders.decodeFloat(bArr, i2));
                unsafe.putObject(t2, j2, objValueOf2);
                iDecodeVarint64 = i2 + 4;
                unsafe.putInt(t2, j3, i5);
                return iDecodeVarint64;
            case 53:
            case 54:
                if (i6 != 0) {
                    return i2;
                }
                iDecodeVarint64 = ArrayDecoders.decodeVarint64(bArr, i2, registers);
                jDecodeZigZag64 = registers.long1;
                objValueOf3 = Long.valueOf(jDecodeZigZag64);
                unsafe.putObject(t2, j2, objValueOf3);
                unsafe.putInt(t2, j3, i5);
                return iDecodeVarint64;
            case 55:
            case 62:
                if (i6 != 0) {
                    return i2;
                }
                iDecodeVarint64 = ArrayDecoders.decodeVarint32(bArr, i2, registers);
                iDecodeZigZag32 = registers.int1;
                objValueOf3 = Integer.valueOf(iDecodeZigZag32);
                unsafe.putObject(t2, j2, objValueOf3);
                unsafe.putInt(t2, j3, i5);
                return iDecodeVarint64;
            case 56:
            case 65:
                if (i6 != 1) {
                    return i2;
                }
                objValueOf = Long.valueOf(ArrayDecoders.decodeFixed64(bArr, i2));
                unsafe.putObject(t2, j2, objValueOf);
                iDecodeVarint64 = i2 + 8;
                unsafe.putInt(t2, j3, i5);
                return iDecodeVarint64;
            case 57:
            case 64:
                if (i6 != 5) {
                    return i2;
                }
                objValueOf2 = Integer.valueOf(ArrayDecoders.decodeFixed32(bArr, i2));
                unsafe.putObject(t2, j2, objValueOf2);
                iDecodeVarint64 = i2 + 4;
                unsafe.putInt(t2, j3, i5);
                return iDecodeVarint64;
            case 58:
                if (i6 != 0) {
                    return i2;
                }
                iDecodeVarint64 = ArrayDecoders.decodeVarint64(bArr, i2, registers);
                objValueOf3 = Boolean.valueOf(registers.long1 != 0);
                unsafe.putObject(t2, j2, objValueOf3);
                unsafe.putInt(t2, j3, i5);
                return iDecodeVarint64;
            case 59:
                if (i6 != 2) {
                    return i2;
                }
                iDecodeVarint64 = ArrayDecoders.decodeVarint32(bArr, i2, registers);
                int i10 = registers.int1;
                if (i10 == 0) {
                    objValueOf3 = "";
                    unsafe.putObject(t2, j2, objValueOf3);
                } else {
                    if ((i7 & ENFORCE_UTF8_MASK) != 0 && !Utf8.isValidUtf8(bArr, iDecodeVarint64, iDecodeVarint64 + i10)) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    unsafe.putObject(t2, j2, new String(bArr, iDecodeVarint64, i10, Internal.UTF_8));
                    iDecodeVarint64 += i10;
                }
                unsafe.putInt(t2, j3, i5);
                return iDecodeVarint64;
            case 60:
                if (i6 != 2) {
                    return i2;
                }
                objMutableOneofMessageFieldForMerge = mutableOneofMessageFieldForMerge(t2, i5, i9);
                iMergeMessageField = ArrayDecoders.mergeMessageField(objMutableOneofMessageFieldForMerge, getMessageFieldSchema(i9), bArr, i2, i3, registers);
                storeOneofMessageField(t2, i5, i9, objMutableOneofMessageFieldForMerge);
                return iMergeMessageField;
            case 61:
                if (i6 != 2) {
                    return i2;
                }
                iDecodeVarint64 = ArrayDecoders.decodeBytes(bArr, i2, registers);
                objValueOf3 = registers.object1;
                unsafe.putObject(t2, j2, objValueOf3);
                unsafe.putInt(t2, j3, i5);
                return iDecodeVarint64;
            case 63:
                if (i6 != 0) {
                    return i2;
                }
                int iDecodeVarint32 = ArrayDecoders.decodeVarint32(bArr, i2, registers);
                int i11 = registers.int1;
                Internal.EnumVerifier enumFieldVerifier = getEnumFieldVerifier(i9);
                if (enumFieldVerifier == null || enumFieldVerifier.isInRange(i11)) {
                    unsafe.putObject(t2, j2, Integer.valueOf(i11));
                    unsafe.putInt(t2, j3, i5);
                } else {
                    getMutableUnknownFields(t2).storeField(i4, Long.valueOf(i11));
                }
                return iDecodeVarint32;
            case 66:
                if (i6 != 0) {
                    return i2;
                }
                iDecodeVarint64 = ArrayDecoders.decodeVarint32(bArr, i2, registers);
                iDecodeZigZag32 = CodedInputStream.decodeZigZag32(registers.int1);
                objValueOf3 = Integer.valueOf(iDecodeZigZag32);
                unsafe.putObject(t2, j2, objValueOf3);
                unsafe.putInt(t2, j3, i5);
                return iDecodeVarint64;
            case 67:
                if (i6 != 0) {
                    return i2;
                }
                iDecodeVarint64 = ArrayDecoders.decodeVarint64(bArr, i2, registers);
                jDecodeZigZag64 = CodedInputStream.decodeZigZag64(registers.long1);
                objValueOf3 = Long.valueOf(jDecodeZigZag64);
                unsafe.putObject(t2, j2, objValueOf3);
                unsafe.putInt(t2, j3, i5);
                return iDecodeVarint64;
            case 68:
                if (i6 != 3) {
                    return i2;
                }
                objMutableOneofMessageFieldForMerge = mutableOneofMessageFieldForMerge(t2, i5, i9);
                iMergeMessageField = ArrayDecoders.mergeGroupField(objMutableOneofMessageFieldForMerge, getMessageFieldSchema(i9), bArr, i2, i3, (i4 & (-8)) | 4, registers);
                storeOneofMessageField(t2, i5, i9, objMutableOneofMessageFieldForMerge);
                return iMergeMessageField;
            default:
                return i2;
        }
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0298 A[PHI: r0 r19 r22 r26 r28
  0x0298: PHI (r0v15 int) = (r0v10 int), (r0v13 int), (r0v17 int) binds: [B:111:0x02fc, B:106:0x02db, B:99:0x0296] A[DONT_GENERATE, DONT_INLINE]
  0x0298: PHI (r19v2 int) = (r19v0 int), (r19v0 int), (r19v3 int) binds: [B:111:0x02fc, B:106:0x02db, B:99:0x0296] A[DONT_GENERATE, DONT_INLINE]
  0x0298: PHI (r22v2 int) = (r22v0 int), (r22v0 int), (r22v3 int) binds: [B:111:0x02fc, B:106:0x02db, B:99:0x0296] A[DONT_GENERATE, DONT_INLINE]
  0x0298: PHI (r26v3 int) = (r26v1 int), (r26v1 int), (r26v4 int) binds: [B:111:0x02fc, B:106:0x02db, B:99:0x0296] A[DONT_GENERATE, DONT_INLINE]
  0x0298: PHI (r28v3 sun.misc.Unsafe) = (r28v1 sun.misc.Unsafe), (r28v1 sun.misc.Unsafe), (r28v4 sun.misc.Unsafe) binds: [B:111:0x02fc, B:106:0x02db, B:99:0x0296] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:113:0x02ff A[PHI: r0 r19 r22 r26 r28
  0x02ff: PHI (r0v14 int) = (r0v10 int), (r0v13 int), (r0v17 int) binds: [B:111:0x02fc, B:106:0x02db, B:99:0x0296] A[DONT_GENERATE, DONT_INLINE]
  0x02ff: PHI (r19v1 int) = (r19v0 int), (r19v0 int), (r19v3 int) binds: [B:111:0x02fc, B:106:0x02db, B:99:0x0296] A[DONT_GENERATE, DONT_INLINE]
  0x02ff: PHI (r22v1 int) = (r22v0 int), (r22v0 int), (r22v3 int) binds: [B:111:0x02fc, B:106:0x02db, B:99:0x0296] A[DONT_GENERATE, DONT_INLINE]
  0x02ff: PHI (r26v2 int) = (r26v1 int), (r26v1 int), (r26v4 int) binds: [B:111:0x02fc, B:106:0x02db, B:99:0x0296] A[DONT_GENERATE, DONT_INLINE]
  0x02ff: PHI (r28v2 sun.misc.Unsafe) = (r28v1 sun.misc.Unsafe), (r28v1 sun.misc.Unsafe), (r28v4 sun.misc.Unsafe) binds: [B:111:0x02fc, B:106:0x02db, B:99:0x0296] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Failed to find 'out' block for switch in B:26:0x0087. Please report as an issue. */
    @CanIgnoreReturnValue
    private int parseProto3Message(T t2, byte[] bArr, int i2, int i3, ArrayDecoders.Registers registers) throws InvalidProtocolBufferException {
        int i4;
        int iDecodeVarint32;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int iDecodeBytes;
        this = this;
        t2 = t2;
        bArr = bArr;
        i3 = i3;
        registers = registers;
        checkMutable(t2);
        Unsafe unsafe = UNSAFE;
        int i14 = -1;
        int oneofField = i2;
        int i15 = -1;
        int i16 = 0;
        int i17 = 0;
        int i18 = 1048575;
        while (oneofField < i3) {
            int i19 = oneofField + 1;
            byte b2 = bArr[oneofField];
            if (b2 < 0) {
                iDecodeVarint32 = ArrayDecoders.decodeVarint32(b2, bArr, i19, registers);
                i4 = registers.int1;
            } else {
                i4 = b2;
                iDecodeVarint32 = i19;
            }
            int i20 = i4 >>> 3;
            int i21 = i4 & 7;
            int iPositionForFieldNumber = i20 > i15 ? this.positionForFieldNumber(i20, i16 / 3) : this.positionForFieldNumber(i20);
            if (iPositionForFieldNumber == i14) {
                i10 = iDecodeVarint32;
                i5 = i20;
                i8 = 0;
            } else {
                int i22 = this.buffer[iPositionForFieldNumber + 1];
                int iType = type(i22);
                long jOffset = offset(i22);
                if (iType <= 17) {
                    int i23 = this.buffer[iPositionForFieldNumber + 2];
                    int i24 = 1 << (i23 >>> OFFSET_BITS);
                    int i25 = 1048575;
                    int i26 = i23 & 1048575;
                    if (i26 != i18) {
                        if (i18 != 1048575) {
                            unsafe.putInt(t2, i18, i17);
                            i25 = 1048575;
                        }
                        if (i26 != i25) {
                            i17 = unsafe.getInt(t2, i26);
                        }
                        i18 = i26;
                    }
                    switch (iType) {
                        case 0:
                            i5 = i20;
                            i11 = iPositionForFieldNumber;
                            i12 = iDecodeVarint32;
                            i13 = i17;
                            if (i21 == 1) {
                                UnsafeUtil.putDouble(t2, jOffset, ArrayDecoders.decodeDouble(bArr, i12));
                                oneofField = i12 + 8;
                                i17 = i13 | i24;
                                i16 = i11;
                            }
                            i17 = i13;
                            i8 = i11;
                            i10 = i12;
                            break;
                        case 1:
                            i5 = i20;
                            registers = registers;
                            i11 = iPositionForFieldNumber;
                            i12 = iDecodeVarint32;
                            i13 = i17;
                            if (i21 == 5) {
                                UnsafeUtil.putFloat(t2, jOffset, ArrayDecoders.decodeFloat(bArr, i12));
                                oneofField = i12 + 4;
                                i17 = i13 | i24;
                                i16 = i11;
                            }
                            i17 = i13;
                            i8 = i11;
                            i10 = i12;
                            break;
                        case 2:
                        case 3:
                            i5 = i20;
                            registers = registers;
                            i11 = iPositionForFieldNumber;
                            i12 = iDecodeVarint32;
                            i13 = i17;
                            if (i21 == 0) {
                                int iDecodeVarint64 = ArrayDecoders.decodeVarint64(bArr, i12, registers);
                                unsafe.putLong(t2, jOffset, registers.long1);
                                i17 = i13 | i24;
                                i16 = i11;
                                oneofField = iDecodeVarint64;
                            }
                            i17 = i13;
                            i8 = i11;
                            i10 = i12;
                            break;
                        case 4:
                        case 11:
                            i5 = i20;
                            registers = registers;
                            i11 = iPositionForFieldNumber;
                            i12 = iDecodeVarint32;
                            i13 = i17;
                            if (i21 == 0) {
                                oneofField = ArrayDecoders.decodeVarint32(bArr, i12, registers);
                                unsafe.putInt(t2, jOffset, registers.int1);
                                i17 = i13 | i24;
                                i16 = i11;
                            }
                            i17 = i13;
                            i8 = i11;
                            i10 = i12;
                            break;
                        case 5:
                        case TYPE_ENUM_VALUE:
                            i5 = i20;
                            i11 = iPositionForFieldNumber;
                            i13 = i17;
                            if (i21 == 1) {
                                i12 = iDecodeVarint32;
                                unsafe.putLong(t2, jOffset, ArrayDecoders.decodeFixed64(bArr, iDecodeVarint32));
                                oneofField = i12 + 8;
                                i17 = i13 | i24;
                                i16 = i11;
                            }
                            i12 = iDecodeVarint32;
                            i17 = i13;
                            i8 = i11;
                            i10 = i12;
                            break;
                        case 6:
                        case TYPE_UINT32_VALUE:
                            i5 = i20;
                            registers = registers;
                            i11 = iPositionForFieldNumber;
                            i13 = i17;
                            if (i21 == 5) {
                                unsafe.putInt(t2, jOffset, ArrayDecoders.decodeFixed32(bArr, iDecodeVarint32));
                                oneofField = iDecodeVarint32 + 4;
                                i17 = i13 | i24;
                                i16 = i11;
                            }
                            i12 = iDecodeVarint32;
                            i17 = i13;
                            i8 = i11;
                            i10 = i12;
                            break;
                        case 7:
                            i5 = i20;
                            registers = registers;
                            i11 = iPositionForFieldNumber;
                            i13 = i17;
                            if (i21 == 0) {
                                int iDecodeVarint65 = ArrayDecoders.decodeVarint64(bArr, iDecodeVarint32, registers);
                                UnsafeUtil.putBoolean(t2, jOffset, registers.long1 != 0);
                                i17 = i13 | i24;
                                oneofField = iDecodeVarint65;
                                i16 = i11;
                            }
                            i12 = iDecodeVarint32;
                            i17 = i13;
                            i8 = i11;
                            i10 = i12;
                            break;
                        case 8:
                            i5 = i20;
                            registers = registers;
                            i11 = iPositionForFieldNumber;
                            i13 = i17;
                            if (i21 == 2) {
                                oneofField = (ENFORCE_UTF8_MASK & i22) == 0 ? ArrayDecoders.decodeString(bArr, iDecodeVarint32, registers) : ArrayDecoders.decodeStringRequireUtf8(bArr, iDecodeVarint32, registers);
                                unsafe.putObject(t2, jOffset, registers.object1);
                                i17 = i13 | i24;
                                i16 = i11;
                            }
                            i12 = iDecodeVarint32;
                            i17 = i13;
                            i8 = i11;
                            i10 = i12;
                            break;
                        case 9:
                            i5 = i20;
                            registers = registers;
                            i11 = iPositionForFieldNumber;
                            if (i21 == 2) {
                                Object objMutableMessageFieldForMerge = this.mutableMessageFieldForMerge(t2, i11);
                                oneofField = ArrayDecoders.mergeMessageField(objMutableMessageFieldForMerge, this.getMessageFieldSchema(i11), bArr, iDecodeVarint32, i3, registers);
                                this.storeMessageField(t2, i11, objMutableMessageFieldForMerge);
                                i17 |= i24;
                                i16 = i11;
                            }
                            i12 = iDecodeVarint32;
                            i13 = i17;
                            i17 = i13;
                            i8 = i11;
                            i10 = i12;
                            break;
                        case 10:
                            i5 = i20;
                            registers = registers;
                            i11 = iPositionForFieldNumber;
                            if (i21 == 2) {
                                iDecodeBytes = ArrayDecoders.decodeBytes(bArr, iDecodeVarint32, registers);
                                unsafe.putObject(t2, jOffset, registers.object1);
                                i17 |= i24;
                                oneofField = iDecodeBytes;
                                i16 = i11;
                            }
                            i12 = iDecodeVarint32;
                            i13 = i17;
                            i17 = i13;
                            i8 = i11;
                            i10 = i12;
                            break;
                        case 12:
                            i5 = i20;
                            registers = registers;
                            i11 = iPositionForFieldNumber;
                            if (i21 == 0) {
                                iDecodeBytes = ArrayDecoders.decodeVarint32(bArr, iDecodeVarint32, registers);
                                unsafe.putInt(t2, jOffset, registers.int1);
                                i17 |= i24;
                                oneofField = iDecodeBytes;
                                i16 = i11;
                            }
                            i12 = iDecodeVarint32;
                            i13 = i17;
                            i17 = i13;
                            i8 = i11;
                            i10 = i12;
                            break;
                        case TYPE_SFIXED32_VALUE:
                            i5 = i20;
                            registers = registers;
                            i11 = iPositionForFieldNumber;
                            if (i21 == 0) {
                                oneofField = ArrayDecoders.decodeVarint32(bArr, iDecodeVarint32, registers);
                                unsafe.putInt(t2, jOffset, CodedInputStream.decodeZigZag32(registers.int1));
                                i17 |= i24;
                                i16 = i11;
                            }
                            i12 = iDecodeVarint32;
                            i13 = i17;
                            i17 = i13;
                            i8 = i11;
                            i10 = i12;
                            break;
                        case 16:
                            if (i21 == 0) {
                                registers = registers;
                                int iDecodeVarint66 = ArrayDecoders.decodeVarint64(bArr, iDecodeVarint32, registers);
                                i5 = i20;
                                unsafe.putLong(t2, jOffset, CodedInputStream.decodeZigZag64(registers.long1));
                                i17 |= i24;
                                i16 = iPositionForFieldNumber;
                                oneofField = iDecodeVarint66;
                                break;
                            }
                        default:
                            i5 = i20;
                            i11 = iPositionForFieldNumber;
                            i12 = iDecodeVarint32;
                            i13 = i17;
                            i17 = i13;
                            i8 = i11;
                            i10 = i12;
                            break;
                    }
                    i14 = -1;
                } else {
                    i5 = i20;
                    int i27 = i17;
                    registers = registers;
                    int i28 = iDecodeVarint32;
                    if (iType != 27) {
                        if (iType <= 49) {
                            i6 = i27;
                            i8 = iPositionForFieldNumber;
                            unsafe = unsafe;
                            i7 = i18;
                            oneofField = parseRepeatedField(t2, bArr, i28, i3, i4, i5, i21, iPositionForFieldNumber, i22, iType, jOffset, registers);
                            if (oneofField != i28) {
                                i15 = i5;
                                i16 = i8;
                                i18 = i7;
                                i17 = i6;
                            } else {
                                i10 = oneofField;
                                i18 = i7;
                                i17 = i6;
                                oneofField = ArrayDecoders.decodeUnknownField(i4, bArr, i10, i3, getMutableUnknownFields(t2), registers);
                                i15 = i5;
                                i16 = i8;
                            }
                        } else {
                            i6 = i27;
                            i7 = i18;
                            i8 = iPositionForFieldNumber;
                            unsafe = unsafe;
                            i9 = i28;
                            if (iType == 50) {
                                if (i21 == 2) {
                                    oneofField = parseMapField(t2, bArr, i9, i3, i8, jOffset, registers);
                                    if (oneofField != i9) {
                                        i15 = i5;
                                        i16 = i8;
                                        i18 = i7;
                                        i17 = i6;
                                    } else {
                                        i10 = oneofField;
                                    }
                                }
                                i18 = i7;
                                i17 = i6;
                                oneofField = ArrayDecoders.decodeUnknownField(i4, bArr, i10, i3, getMutableUnknownFields(t2), registers);
                                i15 = i5;
                                i16 = i8;
                            } else {
                                oneofField = parseOneofField(t2, bArr, i9, i3, i4, i5, i21, i22, iType, jOffset, i8, registers);
                                if (oneofField != i9) {
                                    i15 = i5;
                                    i16 = i8;
                                    i18 = i7;
                                    i17 = i6;
                                } else {
                                    i10 = oneofField;
                                    i18 = i7;
                                    i17 = i6;
                                    oneofField = ArrayDecoders.decodeUnknownField(i4, bArr, i10, i3, getMutableUnknownFields(t2), registers);
                                    i15 = i5;
                                    i16 = i8;
                                }
                            }
                        }
                        unsafe = unsafe;
                        i14 = -1;
                    } else if (i21 == 2) {
                        Internal.ProtobufList protobufListMutableCopyWithCapacity2 = (Internal.ProtobufList) unsafe.getObject(t2, jOffset);
                        if (!protobufListMutableCopyWithCapacity2.isModifiable()) {
                            int size = protobufListMutableCopyWithCapacity2.size();
                            protobufListMutableCopyWithCapacity2 = protobufListMutableCopyWithCapacity2.mutableCopyWithCapacity2(size == 0 ? 10 : size * 2);
                            unsafe.putObject(t2, jOffset, protobufListMutableCopyWithCapacity2);
                        }
                        oneofField = ArrayDecoders.decodeMessageList(this.getMessageFieldSchema(iPositionForFieldNumber), i4, bArr, i28, i3, protobufListMutableCopyWithCapacity2, registers);
                        i16 = iPositionForFieldNumber;
                        i17 = i27;
                    } else {
                        i7 = i18;
                        i8 = iPositionForFieldNumber;
                        unsafe = unsafe;
                        i9 = i28;
                        i6 = i27;
                    }
                    i10 = i9;
                    i18 = i7;
                    i17 = i6;
                    oneofField = ArrayDecoders.decodeUnknownField(i4, bArr, i10, i3, getMutableUnknownFields(t2), registers);
                    i15 = i5;
                    i16 = i8;
                    unsafe = unsafe;
                    i14 = -1;
                }
                i15 = i5;
                i14 = -1;
            }
            oneofField = ArrayDecoders.decodeUnknownField(i4, bArr, i10, i3, getMutableUnknownFields(t2), registers);
            i15 = i5;
            i16 = i8;
            unsafe = unsafe;
            i14 = -1;
        }
        int i29 = i17;
        Unsafe unsafe2 = unsafe;
        if (i18 != 1048575) {
            unsafe2.putInt(t2, i18, i29);
        }
        if (oneofField == i3) {
            return oneofField;
        }
        throw InvalidProtocolBufferException.parseFailure();
    }

    private int parseRepeatedField(T t2, byte[] bArr, int i2, int i3, int i4, int i5, int i6, int i7, long j2, int i8, long j3, ArrayDecoders.Registers registers) throws InvalidProtocolBufferException {
        int iDecodeVarint32List;
        Unsafe unsafe = UNSAFE;
        Internal.ProtobufList protobufListMutableCopyWithCapacity2 = (Internal.ProtobufList) unsafe.getObject(t2, j3);
        if (!protobufListMutableCopyWithCapacity2.isModifiable()) {
            int size = protobufListMutableCopyWithCapacity2.size();
            protobufListMutableCopyWithCapacity2 = protobufListMutableCopyWithCapacity2.mutableCopyWithCapacity2(size == 0 ? 10 : size * 2);
            unsafe.putObject(t2, j3, protobufListMutableCopyWithCapacity2);
        }
        switch (i8) {
            case TYPE_SINT64_VALUE:
            case 35:
                if (i6 == 2) {
                    return ArrayDecoders.decodePackedDoubleList(bArr, i2, protobufListMutableCopyWithCapacity2, registers);
                }
                return i6 == 1 ? ArrayDecoders.decodeDoubleList(i4, bArr, i2, i3, protobufListMutableCopyWithCapacity2, registers) : i2;
            case Base64.Encoder.LINE_GROUPS /* 19 */:
            case 36:
                if (i6 == 2) {
                    return ArrayDecoders.decodePackedFloatList(bArr, i2, protobufListMutableCopyWithCapacity2, registers);
                }
                return i6 == 5 ? ArrayDecoders.decodeFloatList(i4, bArr, i2, i3, protobufListMutableCopyWithCapacity2, registers) : i2;
            case OFFSET_BITS /* 20 */:
            case 21:
            case 37:
            case 38:
                if (i6 == 2) {
                    return ArrayDecoders.decodePackedVarint64List(bArr, i2, protobufListMutableCopyWithCapacity2, registers);
                }
                return i6 == 0 ? ArrayDecoders.decodeVarint64List(i4, bArr, i2, i3, protobufListMutableCopyWithCapacity2, registers) : i2;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i6 == 2) {
                    return ArrayDecoders.decodePackedVarint32List(bArr, i2, protobufListMutableCopyWithCapacity2, registers);
                }
                return i6 == 0 ? ArrayDecoders.decodeVarint32List(i4, bArr, i2, i3, protobufListMutableCopyWithCapacity2, registers) : i2;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i6 == 2) {
                    return ArrayDecoders.decodePackedFixed64List(bArr, i2, protobufListMutableCopyWithCapacity2, registers);
                }
                return i6 == 1 ? ArrayDecoders.decodeFixed64List(i4, bArr, i2, i3, protobufListMutableCopyWithCapacity2, registers) : i2;
            case InsecureNonceXChaCha20.NONCE_SIZE_IN_BYTES /* 24 */:
            case 31:
            case 41:
            case 45:
                if (i6 == 2) {
                    return ArrayDecoders.decodePackedFixed32List(bArr, i2, protobufListMutableCopyWithCapacity2, registers);
                }
                return i6 == 5 ? ArrayDecoders.decodeFixed32List(i4, bArr, i2, i3, protobufListMutableCopyWithCapacity2, registers) : i2;
            case 25:
            case 42:
                if (i6 == 2) {
                    return ArrayDecoders.decodePackedBoolList(bArr, i2, protobufListMutableCopyWithCapacity2, registers);
                }
                return i6 == 0 ? ArrayDecoders.decodeBoolList(i4, bArr, i2, i3, protobufListMutableCopyWithCapacity2, registers) : i2;
            case 26:
                if (i6 != 2) {
                    return i2;
                }
                long j4 = j2 & 536870912;
                Internal.ProtobufList protobufList = protobufListMutableCopyWithCapacity2;
                return j4 == 0 ? ArrayDecoders.decodeStringList(i4, bArr, i2, i3, protobufList, registers) : ArrayDecoders.decodeStringListRequireUtf8(i4, bArr, i2, i3, protobufList, registers);
            case 27:
                return i6 == 2 ? ArrayDecoders.decodeMessageList(getMessageFieldSchema(i7), i4, bArr, i2, i3, protobufListMutableCopyWithCapacity2, registers) : i2;
            case 28:
                return i6 == 2 ? ArrayDecoders.decodeBytesList(i4, bArr, i2, i3, protobufListMutableCopyWithCapacity2, registers) : i2;
            case 30:
            case 44:
                if (i6 == 2) {
                    iDecodeVarint32List = ArrayDecoders.decodePackedVarint32List(bArr, i2, protobufListMutableCopyWithCapacity2, registers);
                } else {
                    if (i6 != 0) {
                        return i2;
                    }
                    iDecodeVarint32List = ArrayDecoders.decodeVarint32List(i4, bArr, i2, i3, protobufListMutableCopyWithCapacity2, registers);
                }
                SchemaUtil.filterUnknownEnumList((Object) t2, i5, (List<Integer>) protobufListMutableCopyWithCapacity2, getEnumFieldVerifier(i7), (Object) null, (UnknownFieldSchema<UT, Object>) this.unknownFieldSchema);
                return iDecodeVarint32List;
            case 33:
            case 47:
                if (i6 == 2) {
                    return ArrayDecoders.decodePackedSInt32List(bArr, i2, protobufListMutableCopyWithCapacity2, registers);
                }
                return i6 == 0 ? ArrayDecoders.decodeSInt32List(i4, bArr, i2, i3, protobufListMutableCopyWithCapacity2, registers) : i2;
            case 34:
            case 48:
                if (i6 == 2) {
                    return ArrayDecoders.decodePackedSInt64List(bArr, i2, protobufListMutableCopyWithCapacity2, registers);
                }
                return i6 == 0 ? ArrayDecoders.decodeSInt64List(i4, bArr, i2, i3, protobufListMutableCopyWithCapacity2, registers) : i2;
            case 49:
                return i6 == 3 ? ArrayDecoders.decodeGroupList(getMessageFieldSchema(i7), i4, bArr, i2, i3, protobufListMutableCopyWithCapacity2, registers) : i2;
            default:
                return i2;
        }
    }

    private int positionForFieldNumber(int i2) {
        if (i2 < this.minFieldNumber || i2 > this.maxFieldNumber) {
            return -1;
        }
        return slowPositionForFieldNumber(i2, 0);
    }

    private int presenceMaskAndOffsetAt(int i2) {
        return this.buffer[i2 + 2];
    }

    private <E> void readGroupList(Object obj, long j2, Reader reader, Schema<E> schema, ExtensionRegistryLite extensionRegistryLite) {
        reader.readGroupList(this.listFieldSchema.mutableListAt(obj, j2), schema, extensionRegistryLite);
    }

    private <E> void readMessageList(Object obj, int i2, Reader reader, Schema<E> schema, ExtensionRegistryLite extensionRegistryLite) {
        reader.readMessageList(this.listFieldSchema.mutableListAt(obj, offset(i2)), schema, extensionRegistryLite);
    }

    private void readString(Object obj, int i2, Reader reader) {
        long jOffset;
        Object bytes;
        if (isEnforceUtf8(i2)) {
            jOffset = offset(i2);
            bytes = reader.readStringRequireUtf8();
        } else if (this.lite) {
            jOffset = offset(i2);
            bytes = reader.readString();
        } else {
            jOffset = offset(i2);
            bytes = reader.readBytes();
        }
        UnsafeUtil.putObject(obj, jOffset, bytes);
    }

    private void readStringList(Object obj, int i2, Reader reader) {
        if (isEnforceUtf8(i2)) {
            reader.readStringListRequireUtf8(this.listFieldSchema.mutableListAt(obj, offset(i2)));
        } else {
            reader.readStringList(this.listFieldSchema.mutableListAt(obj, offset(i2)));
        }
    }

    private static java.lang.reflect.Field reflectField(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            java.lang.reflect.Field[] declaredFields = cls.getDeclaredFields();
            for (java.lang.reflect.Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    private void setFieldPresent(T t2, int i2) {
        int iPresenceMaskAndOffsetAt = presenceMaskAndOffsetAt(i2);
        long j2 = 1048575 & iPresenceMaskAndOffsetAt;
        if (j2 == 1048575) {
            return;
        }
        UnsafeUtil.putInt(t2, j2, (1 << (iPresenceMaskAndOffsetAt >>> OFFSET_BITS)) | UnsafeUtil.getInt(t2, j2));
    }

    private void setOneofPresent(T t2, int i2, int i3) {
        UnsafeUtil.putInt(t2, presenceMaskAndOffsetAt(i3) & 1048575, i2);
    }

    private int slowPositionForFieldNumber(int i2, int i3) {
        int length = (this.buffer.length / 3) - 1;
        while (i3 <= length) {
            int i4 = (length + i3) >>> 1;
            int i5 = i4 * 3;
            int iNumberAt = numberAt(i5);
            if (i2 == iNumberAt) {
                return i5;
            }
            if (i2 < iNumberAt) {
                length = i4 - 1;
            } else {
                i3 = i4 + 1;
            }
        }
        return -1;
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0075  */
    /* JADX WARN: Code duplicated, block: B:23:0x0078  */
    /* JADX WARN: Code duplicated, block: B:26:0x007f  */
    /* JADX WARN: Code duplicated, block: B:29:0x0099  */
    /* JADX WARN: Code duplicated, block: B:31:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:32:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:34:0x00b0  */
    /* JADX WARN: Code duplicated, block: B:35:0x00b9 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:36:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:37:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:39:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:42:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:45:? A[RETURN, SYNTHETIC] */
    private static void storeFieldData(FieldInfo fieldInfo, int[] iArr, int i2, Object[] objArr) {
        int iObjectFieldOffset;
        int iId;
        java.lang.reflect.Field cachedSizeField;
        int iObjectFieldOffset2;
        int iNumberOfTrailingZeros;
        int i3;
        Class<?> messageFieldClass;
        int i4;
        OneofInfo oneof = fieldInfo.getOneof();
        if (oneof == null) {
            FieldType type = fieldInfo.getType();
            iObjectFieldOffset = (int) UnsafeUtil.objectFieldOffset(fieldInfo.getField());
            iId = type.id();
            if (type.isList() || type.isMap()) {
                if (fieldInfo.getCachedSizeField() == null) {
                    iObjectFieldOffset2 = 0;
                } else {
                    cachedSizeField = fieldInfo.getCachedSizeField();
                }
                iNumberOfTrailingZeros = 0;
            } else {
                java.lang.reflect.Field presenceField = fieldInfo.getPresenceField();
                iObjectFieldOffset2 = presenceField == null ? 1048575 : (int) UnsafeUtil.objectFieldOffset(presenceField);
                iNumberOfTrailingZeros = Integer.numberOfTrailingZeros(fieldInfo.getPresenceMask());
            }
            iArr[i2] = fieldInfo.getFieldNumber();
            int i5 = i2 + 1;
            if (fieldInfo.isEnforceUtf8()) {
                i3 = ENFORCE_UTF8_MASK;
            } else {
                i3 = 0;
            }
            iArr[i5] = (fieldInfo.isRequired() ? REQUIRED_MASK : 0) | i3 | (iId << OFFSET_BITS) | iObjectFieldOffset;
            iArr[i2 + 2] = iObjectFieldOffset2 | (iNumberOfTrailingZeros << OFFSET_BITS);
            messageFieldClass = fieldInfo.getMessageFieldClass();
            if (fieldInfo.getMapDefaultEntry() != null) {
                if (messageFieldClass != null) {
                    objArr[((i2 / 3) * 2) + 1] = messageFieldClass;
                    return;
                } else {
                    if (fieldInfo.getEnumVerifier() != null) {
                        objArr[((i2 / 3) * 2) + 1] = fieldInfo.getEnumVerifier();
                        return;
                    }
                    return;
                }
            }
            i4 = (i2 / 3) * 2;
            objArr[i4] = fieldInfo.getMapDefaultEntry();
            if (messageFieldClass != null) {
                objArr[i4 + 1] = messageFieldClass;
            } else if (fieldInfo.getEnumVerifier() != null) {
                objArr[i4 + 1] = fieldInfo.getEnumVerifier();
            }
        }
        iId = fieldInfo.getType().id() + ONEOF_TYPE_OFFSET;
        iObjectFieldOffset = (int) UnsafeUtil.objectFieldOffset(oneof.getValueField());
        cachedSizeField = oneof.getCaseField();
        iObjectFieldOffset2 = (int) UnsafeUtil.objectFieldOffset(cachedSizeField);
        iNumberOfTrailingZeros = 0;
        iArr[i2] = fieldInfo.getFieldNumber();
        int i6 = i2 + 1;
        if (fieldInfo.isEnforceUtf8()) {
            i3 = ENFORCE_UTF8_MASK;
        } else {
            i3 = 0;
        }
        iArr[i6] = (fieldInfo.isRequired() ? REQUIRED_MASK : 0) | i3 | (iId << OFFSET_BITS) | iObjectFieldOffset;
        iArr[i2 + 2] = iObjectFieldOffset2 | (iNumberOfTrailingZeros << OFFSET_BITS);
        messageFieldClass = fieldInfo.getMessageFieldClass();
        if (fieldInfo.getMapDefaultEntry() != null) {
            if (messageFieldClass != null) {
                objArr[((i2 / 3) * 2) + 1] = messageFieldClass;
                return;
            } else {
                if (fieldInfo.getEnumVerifier() != null) {
                    objArr[((i2 / 3) * 2) + 1] = fieldInfo.getEnumVerifier();
                    return;
                }
                return;
            }
        }
        i4 = (i2 / 3) * 2;
        objArr[i4] = fieldInfo.getMapDefaultEntry();
        if (messageFieldClass != null) {
            objArr[i4 + 1] = messageFieldClass;
        } else if (fieldInfo.getEnumVerifier() != null) {
            objArr[i4 + 1] = fieldInfo.getEnumVerifier();
        }
    }

    private void storeMessageField(T t2, int i2, Object obj) {
        UNSAFE.putObject(t2, offset(typeAndOffsetAt(i2)), obj);
        setFieldPresent(t2, i2);
    }

    private void storeOneofMessageField(T t2, int i2, int i3, Object obj) {
        UNSAFE.putObject(t2, offset(typeAndOffsetAt(i3)), obj);
        setOneofPresent(t2, i2, i3);
    }

    private static int type(int i2) {
        return (i2 & FIELD_TYPE_MASK) >>> OFFSET_BITS;
    }

    private int typeAndOffsetAt(int i2) {
        return this.buffer[i2 + 1];
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0021  */
    private void writeFieldsInAscendingOrderProto2(T t2, Writer writer) {
        Iterator it;
        Map.Entry<?, ?> entry;
        int i2;
        if (this.hasExtensions) {
            FieldSet<T> extensions = this.extensionSchema.getExtensions(t2);
            if (extensions.isEmpty()) {
                it = null;
                entry = null;
            } else {
                it = extensions.iterator();
                entry = (Map.Entry) it.next();
            }
        } else {
            it = null;
            entry = null;
        }
        int length = this.buffer.length;
        Unsafe unsafe = UNSAFE;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 1048575;
        int i6 = 0;
        while (i4 < length) {
            int iTypeAndOffsetAt = typeAndOffsetAt(i4);
            int iNumberAt = numberAt(i4);
            int iType = type(iTypeAndOffsetAt);
            if (iType <= 17) {
                int i7 = this.buffer[i4 + 2];
                int i8 = i7 & i3;
                if (i8 != i5) {
                    i6 = unsafe.getInt(t2, i8);
                    i5 = i8;
                }
                i2 = 1 << (i7 >>> OFFSET_BITS);
            } else {
                i2 = 0;
            }
            while (entry != null && this.extensionSchema.extensionNumber(entry) <= iNumberAt) {
                this.extensionSchema.serializeExtension(writer, entry);
                entry = it.hasNext() ? (Map.Entry) it.next() : null;
            }
            long jOffset = offset(iTypeAndOffsetAt);
            switch (iType) {
                case 0:
                    if ((i2 & i6) != 0) {
                        writer.writeDouble(iNumberAt, doubleAt(t2, jOffset));
                        continue;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 1:
                    if ((i2 & i6) != 0) {
                        writer.writeFloat(iNumberAt, floatAt(t2, jOffset));
                    } else {
                        continue;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 2:
                    if ((i2 & i6) != 0) {
                        writer.writeInt64(iNumberAt, unsafe.getLong(t2, jOffset));
                    } else {
                        continue;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 3:
                    if ((i2 & i6) != 0) {
                        writer.writeUInt64(iNumberAt, unsafe.getLong(t2, jOffset));
                    } else {
                        continue;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 4:
                    if ((i2 & i6) != 0) {
                        writer.writeInt32(iNumberAt, unsafe.getInt(t2, jOffset));
                    } else {
                        continue;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 5:
                    if ((i2 & i6) != 0) {
                        writer.writeFixed64(iNumberAt, unsafe.getLong(t2, jOffset));
                    } else {
                        continue;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 6:
                    if ((i2 & i6) != 0) {
                        writer.writeFixed32(iNumberAt, unsafe.getInt(t2, jOffset));
                    } else {
                        continue;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 7:
                    if ((i2 & i6) != 0) {
                        writer.writeBool(iNumberAt, booleanAt(t2, jOffset));
                    } else {
                        continue;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 8:
                    if ((i2 & i6) != 0) {
                        writeString(iNumberAt, unsafe.getObject(t2, jOffset), writer);
                    } else {
                        continue;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 9:
                    if ((i2 & i6) != 0) {
                        writer.writeMessage(iNumberAt, unsafe.getObject(t2, jOffset), getMessageFieldSchema(i4));
                    } else {
                        continue;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 10:
                    if ((i2 & i6) != 0) {
                        writer.writeBytes(iNumberAt, (ByteString) unsafe.getObject(t2, jOffset));
                    } else {
                        continue;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 11:
                    if ((i2 & i6) != 0) {
                        writer.writeUInt32(iNumberAt, unsafe.getInt(t2, jOffset));
                    } else {
                        continue;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 12:
                    if ((i2 & i6) != 0) {
                        writer.writeEnum(iNumberAt, unsafe.getInt(t2, jOffset));
                    } else {
                        continue;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case TYPE_UINT32_VALUE:
                    if ((i2 & i6) != 0) {
                        writer.writeSFixed32(iNumberAt, unsafe.getInt(t2, jOffset));
                    } else {
                        continue;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case TYPE_ENUM_VALUE:
                    if ((i2 & i6) != 0) {
                        writer.writeSFixed64(iNumberAt, unsafe.getLong(t2, jOffset));
                    } else {
                        continue;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case TYPE_SFIXED32_VALUE:
                    if ((i2 & i6) != 0) {
                        writer.writeSInt32(iNumberAt, unsafe.getInt(t2, jOffset));
                    } else {
                        continue;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 16:
                    if ((i2 & i6) != 0) {
                        writer.writeSInt64(iNumberAt, unsafe.getLong(t2, jOffset));
                    } else {
                        continue;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case TYPE_SINT32_VALUE:
                    if ((i2 & i6) != 0) {
                        writer.writeGroup(iNumberAt, unsafe.getObject(t2, jOffset), getMessageFieldSchema(i4));
                    } else {
                        continue;
                    }
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case TYPE_SINT64_VALUE:
                    SchemaUtil.writeDoubleList(numberAt(i4), (List) unsafe.getObject(t2, jOffset), writer, false);
                    continue;
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case Base64.Encoder.LINE_GROUPS /* 19 */:
                    SchemaUtil.writeFloatList(numberAt(i4), (List) unsafe.getObject(t2, jOffset), writer, false);
                    continue;
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case OFFSET_BITS /* 20 */:
                    SchemaUtil.writeInt64List(numberAt(i4), (List) unsafe.getObject(t2, jOffset), writer, false);
                    continue;
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 21:
                    SchemaUtil.writeUInt64List(numberAt(i4), (List) unsafe.getObject(t2, jOffset), writer, false);
                    continue;
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 22:
                    SchemaUtil.writeInt32List(numberAt(i4), (List) unsafe.getObject(t2, jOffset), writer, false);
                    continue;
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 23:
                    SchemaUtil.writeFixed64List(numberAt(i4), (List) unsafe.getObject(t2, jOffset), writer, false);
                    continue;
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case InsecureNonceXChaCha20.NONCE_SIZE_IN_BYTES /* 24 */:
                    SchemaUtil.writeFixed32List(numberAt(i4), (List) unsafe.getObject(t2, jOffset), writer, false);
                    continue;
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 25:
                    SchemaUtil.writeBoolList(numberAt(i4), (List) unsafe.getObject(t2, jOffset), writer, false);
                    continue;
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 26:
                    SchemaUtil.writeStringList(numberAt(i4), (List) unsafe.getObject(t2, jOffset), writer);
                    break;
                case 27:
                    SchemaUtil.writeMessageList(numberAt(i4), (List) unsafe.getObject(t2, jOffset), writer, getMessageFieldSchema(i4));
                    break;
                case 28:
                    SchemaUtil.writeBytesList(numberAt(i4), (List) unsafe.getObject(t2, jOffset), writer);
                    break;
                case 29:
                    SchemaUtil.writeUInt32List(numberAt(i4), (List) unsafe.getObject(t2, jOffset), writer, false);
                    continue;
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 30:
                    SchemaUtil.writeEnumList(numberAt(i4), (List) unsafe.getObject(t2, jOffset), writer, false);
                    continue;
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 31:
                    SchemaUtil.writeSFixed32List(numberAt(i4), (List) unsafe.getObject(t2, jOffset), writer, false);
                    continue;
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 32:
                    SchemaUtil.writeSFixed64List(numberAt(i4), (List) unsafe.getObject(t2, jOffset), writer, false);
                    continue;
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 33:
                    SchemaUtil.writeSInt32List(numberAt(i4), (List) unsafe.getObject(t2, jOffset), writer, false);
                    continue;
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 34:
                    SchemaUtil.writeSInt64List(numberAt(i4), (List) unsafe.getObject(t2, jOffset), writer, false);
                    continue;
                    i4 += 3;
                    i3 = 1048575;
                    break;
                case 35:
                    SchemaUtil.writeDoubleList(numberAt(i4), (List) unsafe.getObject(t2, jOffset), writer, true);
                    break;
                case 36:
                    SchemaUtil.writeFloatList(numberAt(i4), (List) unsafe.getObject(t2, jOffset), writer, true);
                    break;
                case 37:
                    SchemaUtil.writeInt64List(numberAt(i4), (List) unsafe.getObject(t2, jOffset), writer, true);
                    break;
                case 38:
                    SchemaUtil.writeUInt64List(numberAt(i4), (List) unsafe.getObject(t2, jOffset), writer, true);
                    break;
                case 39:
                    SchemaUtil.writeInt32List(numberAt(i4), (List) unsafe.getObject(t2, jOffset), writer, true);
                    break;
                case 40:
                    SchemaUtil.writeFixed64List(numberAt(i4), (List) unsafe.getObject(t2, jOffset), writer, true);
                    break;
                case 41:
                    SchemaUtil.writeFixed32List(numberAt(i4), (List) unsafe.getObject(t2, jOffset), writer, true);
                    break;
                case 42:
                    SchemaUtil.writeBoolList(numberAt(i4), (List) unsafe.getObject(t2, jOffset), writer, true);
                    break;
                case 43:
                    SchemaUtil.writeUInt32List(numberAt(i4), (List) unsafe.getObject(t2, jOffset), writer, true);
                    break;
                case 44:
                    SchemaUtil.writeEnumList(numberAt(i4), (List) unsafe.getObject(t2, jOffset), writer, true);
                    break;
                case 45:
                    SchemaUtil.writeSFixed32List(numberAt(i4), (List) unsafe.getObject(t2, jOffset), writer, true);
                    break;
                case 46:
                    SchemaUtil.writeSFixed64List(numberAt(i4), (List) unsafe.getObject(t2, jOffset), writer, true);
                    break;
                case 47:
                    SchemaUtil.writeSInt32List(numberAt(i4), (List) unsafe.getObject(t2, jOffset), writer, true);
                    break;
                case 48:
                    SchemaUtil.writeSInt64List(numberAt(i4), (List) unsafe.getObject(t2, jOffset), writer, true);
                    break;
                case 49:
                    SchemaUtil.writeGroupList(numberAt(i4), (List) unsafe.getObject(t2, jOffset), writer, getMessageFieldSchema(i4));
                    break;
                case 50:
                    writeMapHelper(writer, iNumberAt, unsafe.getObject(t2, jOffset), i4);
                    break;
                case ONEOF_TYPE_OFFSET /* 51 */:
                    if (isOneofPresent(t2, iNumberAt, i4)) {
                        writer.writeDouble(iNumberAt, oneofDoubleAt(t2, jOffset));
                    }
                    break;
                case 52:
                    if (isOneofPresent(t2, iNumberAt, i4)) {
                        writer.writeFloat(iNumberAt, oneofFloatAt(t2, jOffset));
                    }
                    break;
                case 53:
                    if (isOneofPresent(t2, iNumberAt, i4)) {
                        writer.writeInt64(iNumberAt, oneofLongAt(t2, jOffset));
                    }
                    break;
                case 54:
                    if (isOneofPresent(t2, iNumberAt, i4)) {
                        writer.writeUInt64(iNumberAt, oneofLongAt(t2, jOffset));
                    }
                    break;
                case 55:
                    if (isOneofPresent(t2, iNumberAt, i4)) {
                        writer.writeInt32(iNumberAt, oneofIntAt(t2, jOffset));
                    }
                    break;
                case 56:
                    if (isOneofPresent(t2, iNumberAt, i4)) {
                        writer.writeFixed64(iNumberAt, oneofLongAt(t2, jOffset));
                    }
                    break;
                case 57:
                    if (isOneofPresent(t2, iNumberAt, i4)) {
                        writer.writeFixed32(iNumberAt, oneofIntAt(t2, jOffset));
                    }
                    break;
                case 58:
                    if (isOneofPresent(t2, iNumberAt, i4)) {
                        writer.writeBool(iNumberAt, oneofBooleanAt(t2, jOffset));
                    }
                    break;
                case 59:
                    if (isOneofPresent(t2, iNumberAt, i4)) {
                        writeString(iNumberAt, unsafe.getObject(t2, jOffset), writer);
                    }
                    break;
                case 60:
                    if (isOneofPresent(t2, iNumberAt, i4)) {
                        writer.writeMessage(iNumberAt, unsafe.getObject(t2, jOffset), getMessageFieldSchema(i4));
                    }
                    break;
                case 61:
                    if (isOneofPresent(t2, iNumberAt, i4)) {
                        writer.writeBytes(iNumberAt, (ByteString) unsafe.getObject(t2, jOffset));
                    }
                    break;
                case 62:
                    if (isOneofPresent(t2, iNumberAt, i4)) {
                        writer.writeUInt32(iNumberAt, oneofIntAt(t2, jOffset));
                    }
                    break;
                case 63:
                    if (isOneofPresent(t2, iNumberAt, i4)) {
                        writer.writeEnum(iNumberAt, oneofIntAt(t2, jOffset));
                    }
                    break;
                case 64:
                    if (isOneofPresent(t2, iNumberAt, i4)) {
                        writer.writeSFixed32(iNumberAt, oneofIntAt(t2, jOffset));
                    }
                    break;
                case 65:
                    if (isOneofPresent(t2, iNumberAt, i4)) {
                        writer.writeSFixed64(iNumberAt, oneofLongAt(t2, jOffset));
                    }
                    break;
                case 66:
                    if (isOneofPresent(t2, iNumberAt, i4)) {
                        writer.writeSInt32(iNumberAt, oneofIntAt(t2, jOffset));
                    }
                    break;
                case 67:
                    if (isOneofPresent(t2, iNumberAt, i4)) {
                        writer.writeSInt64(iNumberAt, oneofLongAt(t2, jOffset));
                    }
                    break;
                case 68:
                    if (isOneofPresent(t2, iNumberAt, i4)) {
                        writer.writeGroup(iNumberAt, unsafe.getObject(t2, jOffset), getMessageFieldSchema(i4));
                    }
                    break;
            }
            i4 += 3;
            i3 = 1048575;
        }
        while (entry != null) {
            this.extensionSchema.serializeExtension(writer, entry);
            entry = it.hasNext() ? (Map.Entry) it.next() : null;
        }
        writeUnknownInMessageTo(this.unknownFieldSchema, t2, writer);
    }

    /* JADX WARN: Code duplicated, block: B:110:0x03d0  */
    /* JADX WARN: Code duplicated, block: B:137:0x0459  */
    /* JADX WARN: Code duplicated, block: B:140:0x046e  */
    /* JADX WARN: Code duplicated, block: B:143:0x0485  */
    /* JADX WARN: Code duplicated, block: B:7:0x001c  */
    private void writeFieldsInAscendingOrderProto3(T t2, Writer writer) {
        Iterator it;
        Map.Entry<?, ?> entry;
        double dDoubleAt;
        float fFloatAt;
        long jLongAt;
        long jLongAt2;
        int iIntAt;
        long jLongAt3;
        int iIntAt2;
        boolean zBooleanAt;
        int iIntAt3;
        int iIntAt4;
        int iIntAt5;
        long jLongAt4;
        int iIntAt6;
        long jLongAt5;
        if (this.hasExtensions) {
            FieldSet<T> extensions = this.extensionSchema.getExtensions(t2);
            if (extensions.isEmpty()) {
                it = null;
                entry = null;
            } else {
                it = extensions.iterator();
                entry = (Map.Entry) it.next();
            }
        } else {
            it = null;
            entry = null;
        }
        int length = this.buffer.length;
        for (int i2 = 0; i2 < length; i2 += 3) {
            int iTypeAndOffsetAt = typeAndOffsetAt(i2);
            int iNumberAt = numberAt(i2);
            while (entry != null && this.extensionSchema.extensionNumber(entry) <= iNumberAt) {
                this.extensionSchema.serializeExtension(writer, entry);
                entry = it.hasNext() ? (Map.Entry) it.next() : null;
            }
            switch (type(iTypeAndOffsetAt)) {
                case 0:
                    if (isFieldPresent(t2, i2)) {
                        dDoubleAt = doubleAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeDouble(iNumberAt, dDoubleAt);
                    }
                    break;
                case 1:
                    if (isFieldPresent(t2, i2)) {
                        fFloatAt = floatAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeFloat(iNumberAt, fFloatAt);
                    }
                    break;
                case 2:
                    if (isFieldPresent(t2, i2)) {
                        jLongAt = longAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeInt64(iNumberAt, jLongAt);
                    }
                    break;
                case 3:
                    if (isFieldPresent(t2, i2)) {
                        jLongAt2 = longAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeUInt64(iNumberAt, jLongAt2);
                    }
                    break;
                case 4:
                    if (isFieldPresent(t2, i2)) {
                        iIntAt = intAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeInt32(iNumberAt, iIntAt);
                    }
                    break;
                case 5:
                    if (isFieldPresent(t2, i2)) {
                        jLongAt3 = longAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeFixed64(iNumberAt, jLongAt3);
                    }
                    break;
                case 6:
                    if (isFieldPresent(t2, i2)) {
                        iIntAt2 = intAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeFixed32(iNumberAt, iIntAt2);
                    }
                    break;
                case 7:
                    if (isFieldPresent(t2, i2)) {
                        zBooleanAt = booleanAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeBool(iNumberAt, zBooleanAt);
                    }
                    break;
                case 8:
                    if (isFieldPresent(t2, i2)) {
                        writeString(iNumberAt, UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer);
                    }
                    break;
                case 9:
                    if (isFieldPresent(t2, i2)) {
                        writer.writeMessage(iNumberAt, UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), getMessageFieldSchema(i2));
                    }
                    break;
                case 10:
                    if (isFieldPresent(t2, i2)) {
                        writer.writeBytes(iNumberAt, (ByteString) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 11:
                    if (isFieldPresent(t2, i2)) {
                        iIntAt3 = intAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeUInt32(iNumberAt, iIntAt3);
                    }
                    break;
                case 12:
                    if (isFieldPresent(t2, i2)) {
                        iIntAt4 = intAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeEnum(iNumberAt, iIntAt4);
                    }
                    break;
                case TYPE_UINT32_VALUE:
                    if (isFieldPresent(t2, i2)) {
                        iIntAt5 = intAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeSFixed32(iNumberAt, iIntAt5);
                    }
                    break;
                case TYPE_ENUM_VALUE:
                    if (isFieldPresent(t2, i2)) {
                        jLongAt4 = longAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeSFixed64(iNumberAt, jLongAt4);
                    }
                    break;
                case TYPE_SFIXED32_VALUE:
                    if (isFieldPresent(t2, i2)) {
                        iIntAt6 = intAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeSInt32(iNumberAt, iIntAt6);
                    }
                    break;
                case 16:
                    if (isFieldPresent(t2, i2)) {
                        jLongAt5 = longAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeSInt64(iNumberAt, jLongAt5);
                    }
                    break;
                case TYPE_SINT32_VALUE:
                    if (isFieldPresent(t2, i2)) {
                        writer.writeGroup(iNumberAt, UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), getMessageFieldSchema(i2));
                    }
                    break;
                case TYPE_SINT64_VALUE:
                    SchemaUtil.writeDoubleList(numberAt(i2), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case Base64.Encoder.LINE_GROUPS /* 19 */:
                    SchemaUtil.writeFloatList(numberAt(i2), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case OFFSET_BITS /* 20 */:
                    SchemaUtil.writeInt64List(numberAt(i2), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 21:
                    SchemaUtil.writeUInt64List(numberAt(i2), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 22:
                    SchemaUtil.writeInt32List(numberAt(i2), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 23:
                    SchemaUtil.writeFixed64List(numberAt(i2), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case InsecureNonceXChaCha20.NONCE_SIZE_IN_BYTES /* 24 */:
                    SchemaUtil.writeFixed32List(numberAt(i2), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 25:
                    SchemaUtil.writeBoolList(numberAt(i2), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 26:
                    SchemaUtil.writeStringList(numberAt(i2), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer);
                    break;
                case 27:
                    SchemaUtil.writeMessageList(numberAt(i2), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, getMessageFieldSchema(i2));
                    break;
                case 28:
                    SchemaUtil.writeBytesList(numberAt(i2), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer);
                    break;
                case 29:
                    SchemaUtil.writeUInt32List(numberAt(i2), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 30:
                    SchemaUtil.writeEnumList(numberAt(i2), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 31:
                    SchemaUtil.writeSFixed32List(numberAt(i2), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 32:
                    SchemaUtil.writeSFixed64List(numberAt(i2), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 33:
                    SchemaUtil.writeSInt32List(numberAt(i2), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 34:
                    SchemaUtil.writeSInt64List(numberAt(i2), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 35:
                    SchemaUtil.writeDoubleList(numberAt(i2), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 36:
                    SchemaUtil.writeFloatList(numberAt(i2), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 37:
                    SchemaUtil.writeInt64List(numberAt(i2), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 38:
                    SchemaUtil.writeUInt64List(numberAt(i2), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 39:
                    SchemaUtil.writeInt32List(numberAt(i2), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 40:
                    SchemaUtil.writeFixed64List(numberAt(i2), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 41:
                    SchemaUtil.writeFixed32List(numberAt(i2), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 42:
                    SchemaUtil.writeBoolList(numberAt(i2), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 43:
                    SchemaUtil.writeUInt32List(numberAt(i2), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 44:
                    SchemaUtil.writeEnumList(numberAt(i2), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 45:
                    SchemaUtil.writeSFixed32List(numberAt(i2), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 46:
                    SchemaUtil.writeSFixed64List(numberAt(i2), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 47:
                    SchemaUtil.writeSInt32List(numberAt(i2), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 48:
                    SchemaUtil.writeSInt64List(numberAt(i2), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 49:
                    SchemaUtil.writeGroupList(numberAt(i2), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, getMessageFieldSchema(i2));
                    break;
                case 50:
                    writeMapHelper(writer, iNumberAt, UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), i2);
                    break;
                case ONEOF_TYPE_OFFSET /* 51 */:
                    if (isOneofPresent(t2, iNumberAt, i2)) {
                        dDoubleAt = oneofDoubleAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeDouble(iNumberAt, dDoubleAt);
                    }
                    break;
                case 52:
                    if (isOneofPresent(t2, iNumberAt, i2)) {
                        fFloatAt = oneofFloatAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeFloat(iNumberAt, fFloatAt);
                    }
                    break;
                case 53:
                    if (isOneofPresent(t2, iNumberAt, i2)) {
                        jLongAt = oneofLongAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeInt64(iNumberAt, jLongAt);
                    }
                    break;
                case 54:
                    if (isOneofPresent(t2, iNumberAt, i2)) {
                        jLongAt2 = oneofLongAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeUInt64(iNumberAt, jLongAt2);
                    }
                    break;
                case 55:
                    if (isOneofPresent(t2, iNumberAt, i2)) {
                        iIntAt = oneofIntAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeInt32(iNumberAt, iIntAt);
                    }
                    break;
                case 56:
                    if (isOneofPresent(t2, iNumberAt, i2)) {
                        jLongAt3 = oneofLongAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeFixed64(iNumberAt, jLongAt3);
                    }
                    break;
                case 57:
                    if (isOneofPresent(t2, iNumberAt, i2)) {
                        iIntAt2 = oneofIntAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeFixed32(iNumberAt, iIntAt2);
                    }
                    break;
                case 58:
                    if (isOneofPresent(t2, iNumberAt, i2)) {
                        zBooleanAt = oneofBooleanAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeBool(iNumberAt, zBooleanAt);
                    }
                    break;
                case 59:
                    if (isOneofPresent(t2, iNumberAt, i2)) {
                        writeString(iNumberAt, UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer);
                    }
                    break;
                case 60:
                    if (isOneofPresent(t2, iNumberAt, i2)) {
                        writer.writeMessage(iNumberAt, UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), getMessageFieldSchema(i2));
                    }
                    break;
                case 61:
                    if (isOneofPresent(t2, iNumberAt, i2)) {
                        writer.writeBytes(iNumberAt, (ByteString) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 62:
                    if (isOneofPresent(t2, iNumberAt, i2)) {
                        iIntAt3 = oneofIntAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeUInt32(iNumberAt, iIntAt3);
                    }
                    break;
                case 63:
                    if (isOneofPresent(t2, iNumberAt, i2)) {
                        iIntAt4 = oneofIntAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeEnum(iNumberAt, iIntAt4);
                    }
                    break;
                case 64:
                    if (isOneofPresent(t2, iNumberAt, i2)) {
                        iIntAt5 = oneofIntAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeSFixed32(iNumberAt, iIntAt5);
                    }
                    break;
                case 65:
                    if (isOneofPresent(t2, iNumberAt, i2)) {
                        jLongAt4 = oneofLongAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeSFixed64(iNumberAt, jLongAt4);
                    }
                    break;
                case 66:
                    if (isOneofPresent(t2, iNumberAt, i2)) {
                        iIntAt6 = oneofIntAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeSInt32(iNumberAt, iIntAt6);
                    }
                    break;
                case 67:
                    if (isOneofPresent(t2, iNumberAt, i2)) {
                        jLongAt5 = oneofLongAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeSInt64(iNumberAt, jLongAt5);
                    }
                    break;
                case 68:
                    if (isOneofPresent(t2, iNumberAt, i2)) {
                        writer.writeGroup(iNumberAt, UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), getMessageFieldSchema(i2));
                    }
                    break;
            }
        }
        while (entry != null) {
            this.extensionSchema.serializeExtension(writer, entry);
            entry = it.hasNext() ? (Map.Entry) it.next() : null;
        }
        writeUnknownInMessageTo(this.unknownFieldSchema, t2, writer);
    }

    /* JADX WARN: Code duplicated, block: B:110:0x03d6  */
    /* JADX WARN: Code duplicated, block: B:137:0x045f  */
    /* JADX WARN: Code duplicated, block: B:140:0x0474  */
    /* JADX WARN: Code duplicated, block: B:143:0x048b  */
    /* JADX WARN: Code duplicated, block: B:7:0x0021  */
    private void writeFieldsInDescendingOrder(T t2, Writer writer) {
        Iterator itDescendingIterator;
        Map.Entry<?, ?> entry;
        double dDoubleAt;
        float fFloatAt;
        long jLongAt;
        long jLongAt2;
        int iIntAt;
        long jLongAt3;
        int iIntAt2;
        boolean zBooleanAt;
        int iIntAt3;
        int iIntAt4;
        int iIntAt5;
        long jLongAt4;
        int iIntAt6;
        long jLongAt5;
        writeUnknownInMessageTo(this.unknownFieldSchema, t2, writer);
        if (this.hasExtensions) {
            FieldSet<T> extensions = this.extensionSchema.getExtensions(t2);
            if (extensions.isEmpty()) {
                itDescendingIterator = null;
                entry = null;
            } else {
                itDescendingIterator = extensions.descendingIterator();
                entry = (Map.Entry) itDescendingIterator.next();
            }
        } else {
            itDescendingIterator = null;
            entry = null;
        }
        for (int length = this.buffer.length - 3; length >= 0; length -= 3) {
            int iTypeAndOffsetAt = typeAndOffsetAt(length);
            int iNumberAt = numberAt(length);
            while (entry != null && this.extensionSchema.extensionNumber(entry) > iNumberAt) {
                this.extensionSchema.serializeExtension(writer, entry);
                entry = itDescendingIterator.hasNext() ? (Map.Entry) itDescendingIterator.next() : null;
            }
            switch (type(iTypeAndOffsetAt)) {
                case 0:
                    if (isFieldPresent(t2, length)) {
                        dDoubleAt = doubleAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeDouble(iNumberAt, dDoubleAt);
                    }
                    break;
                case 1:
                    if (isFieldPresent(t2, length)) {
                        fFloatAt = floatAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeFloat(iNumberAt, fFloatAt);
                    }
                    break;
                case 2:
                    if (isFieldPresent(t2, length)) {
                        jLongAt = longAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeInt64(iNumberAt, jLongAt);
                    }
                    break;
                case 3:
                    if (isFieldPresent(t2, length)) {
                        jLongAt2 = longAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeUInt64(iNumberAt, jLongAt2);
                    }
                    break;
                case 4:
                    if (isFieldPresent(t2, length)) {
                        iIntAt = intAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeInt32(iNumberAt, iIntAt);
                    }
                    break;
                case 5:
                    if (isFieldPresent(t2, length)) {
                        jLongAt3 = longAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeFixed64(iNumberAt, jLongAt3);
                    }
                    break;
                case 6:
                    if (isFieldPresent(t2, length)) {
                        iIntAt2 = intAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeFixed32(iNumberAt, iIntAt2);
                    }
                    break;
                case 7:
                    if (isFieldPresent(t2, length)) {
                        zBooleanAt = booleanAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeBool(iNumberAt, zBooleanAt);
                    }
                    break;
                case 8:
                    if (isFieldPresent(t2, length)) {
                        writeString(iNumberAt, UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer);
                    }
                    break;
                case 9:
                    if (isFieldPresent(t2, length)) {
                        writer.writeMessage(iNumberAt, UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), getMessageFieldSchema(length));
                    }
                    break;
                case 10:
                    if (isFieldPresent(t2, length)) {
                        writer.writeBytes(iNumberAt, (ByteString) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 11:
                    if (isFieldPresent(t2, length)) {
                        iIntAt3 = intAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeUInt32(iNumberAt, iIntAt3);
                    }
                    break;
                case 12:
                    if (isFieldPresent(t2, length)) {
                        iIntAt4 = intAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeEnum(iNumberAt, iIntAt4);
                    }
                    break;
                case TYPE_UINT32_VALUE:
                    if (isFieldPresent(t2, length)) {
                        iIntAt5 = intAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeSFixed32(iNumberAt, iIntAt5);
                    }
                    break;
                case TYPE_ENUM_VALUE:
                    if (isFieldPresent(t2, length)) {
                        jLongAt4 = longAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeSFixed64(iNumberAt, jLongAt4);
                    }
                    break;
                case TYPE_SFIXED32_VALUE:
                    if (isFieldPresent(t2, length)) {
                        iIntAt6 = intAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeSInt32(iNumberAt, iIntAt6);
                    }
                    break;
                case 16:
                    if (isFieldPresent(t2, length)) {
                        jLongAt5 = longAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeSInt64(iNumberAt, jLongAt5);
                    }
                    break;
                case TYPE_SINT32_VALUE:
                    if (isFieldPresent(t2, length)) {
                        writer.writeGroup(iNumberAt, UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), getMessageFieldSchema(length));
                    }
                    break;
                case TYPE_SINT64_VALUE:
                    SchemaUtil.writeDoubleList(numberAt(length), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case Base64.Encoder.LINE_GROUPS /* 19 */:
                    SchemaUtil.writeFloatList(numberAt(length), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case OFFSET_BITS /* 20 */:
                    SchemaUtil.writeInt64List(numberAt(length), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 21:
                    SchemaUtil.writeUInt64List(numberAt(length), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 22:
                    SchemaUtil.writeInt32List(numberAt(length), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 23:
                    SchemaUtil.writeFixed64List(numberAt(length), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case InsecureNonceXChaCha20.NONCE_SIZE_IN_BYTES /* 24 */:
                    SchemaUtil.writeFixed32List(numberAt(length), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 25:
                    SchemaUtil.writeBoolList(numberAt(length), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 26:
                    SchemaUtil.writeStringList(numberAt(length), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer);
                    break;
                case 27:
                    SchemaUtil.writeMessageList(numberAt(length), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, getMessageFieldSchema(length));
                    break;
                case 28:
                    SchemaUtil.writeBytesList(numberAt(length), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer);
                    break;
                case 29:
                    SchemaUtil.writeUInt32List(numberAt(length), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 30:
                    SchemaUtil.writeEnumList(numberAt(length), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 31:
                    SchemaUtil.writeSFixed32List(numberAt(length), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 32:
                    SchemaUtil.writeSFixed64List(numberAt(length), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 33:
                    SchemaUtil.writeSInt32List(numberAt(length), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 34:
                    SchemaUtil.writeSInt64List(numberAt(length), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, false);
                    break;
                case 35:
                    SchemaUtil.writeDoubleList(numberAt(length), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 36:
                    SchemaUtil.writeFloatList(numberAt(length), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 37:
                    SchemaUtil.writeInt64List(numberAt(length), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 38:
                    SchemaUtil.writeUInt64List(numberAt(length), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 39:
                    SchemaUtil.writeInt32List(numberAt(length), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 40:
                    SchemaUtil.writeFixed64List(numberAt(length), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 41:
                    SchemaUtil.writeFixed32List(numberAt(length), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 42:
                    SchemaUtil.writeBoolList(numberAt(length), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 43:
                    SchemaUtil.writeUInt32List(numberAt(length), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 44:
                    SchemaUtil.writeEnumList(numberAt(length), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 45:
                    SchemaUtil.writeSFixed32List(numberAt(length), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 46:
                    SchemaUtil.writeSFixed64List(numberAt(length), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 47:
                    SchemaUtil.writeSInt32List(numberAt(length), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 48:
                    SchemaUtil.writeSInt64List(numberAt(length), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, true);
                    break;
                case 49:
                    SchemaUtil.writeGroupList(numberAt(length), (List) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer, getMessageFieldSchema(length));
                    break;
                case 50:
                    writeMapHelper(writer, iNumberAt, UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), length);
                    break;
                case ONEOF_TYPE_OFFSET /* 51 */:
                    if (isOneofPresent(t2, iNumberAt, length)) {
                        dDoubleAt = oneofDoubleAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeDouble(iNumberAt, dDoubleAt);
                    }
                    break;
                case 52:
                    if (isOneofPresent(t2, iNumberAt, length)) {
                        fFloatAt = oneofFloatAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeFloat(iNumberAt, fFloatAt);
                    }
                    break;
                case 53:
                    if (isOneofPresent(t2, iNumberAt, length)) {
                        jLongAt = oneofLongAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeInt64(iNumberAt, jLongAt);
                    }
                    break;
                case 54:
                    if (isOneofPresent(t2, iNumberAt, length)) {
                        jLongAt2 = oneofLongAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeUInt64(iNumberAt, jLongAt2);
                    }
                    break;
                case 55:
                    if (isOneofPresent(t2, iNumberAt, length)) {
                        iIntAt = oneofIntAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeInt32(iNumberAt, iIntAt);
                    }
                    break;
                case 56:
                    if (isOneofPresent(t2, iNumberAt, length)) {
                        jLongAt3 = oneofLongAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeFixed64(iNumberAt, jLongAt3);
                    }
                    break;
                case 57:
                    if (isOneofPresent(t2, iNumberAt, length)) {
                        iIntAt2 = oneofIntAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeFixed32(iNumberAt, iIntAt2);
                    }
                    break;
                case 58:
                    if (isOneofPresent(t2, iNumberAt, length)) {
                        zBooleanAt = oneofBooleanAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeBool(iNumberAt, zBooleanAt);
                    }
                    break;
                case 59:
                    if (isOneofPresent(t2, iNumberAt, length)) {
                        writeString(iNumberAt, UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), writer);
                    }
                    break;
                case 60:
                    if (isOneofPresent(t2, iNumberAt, length)) {
                        writer.writeMessage(iNumberAt, UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), getMessageFieldSchema(length));
                    }
                    break;
                case 61:
                    if (isOneofPresent(t2, iNumberAt, length)) {
                        writer.writeBytes(iNumberAt, (ByteString) UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)));
                    }
                    break;
                case 62:
                    if (isOneofPresent(t2, iNumberAt, length)) {
                        iIntAt3 = oneofIntAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeUInt32(iNumberAt, iIntAt3);
                    }
                    break;
                case 63:
                    if (isOneofPresent(t2, iNumberAt, length)) {
                        iIntAt4 = oneofIntAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeEnum(iNumberAt, iIntAt4);
                    }
                    break;
                case 64:
                    if (isOneofPresent(t2, iNumberAt, length)) {
                        iIntAt5 = oneofIntAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeSFixed32(iNumberAt, iIntAt5);
                    }
                    break;
                case 65:
                    if (isOneofPresent(t2, iNumberAt, length)) {
                        jLongAt4 = oneofLongAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeSFixed64(iNumberAt, jLongAt4);
                    }
                    break;
                case 66:
                    if (isOneofPresent(t2, iNumberAt, length)) {
                        iIntAt6 = oneofIntAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeSInt32(iNumberAt, iIntAt6);
                    }
                    break;
                case 67:
                    if (isOneofPresent(t2, iNumberAt, length)) {
                        jLongAt5 = oneofLongAt(t2, offset(iTypeAndOffsetAt));
                        writer.writeSInt64(iNumberAt, jLongAt5);
                    }
                    break;
                case 68:
                    if (isOneofPresent(t2, iNumberAt, length)) {
                        writer.writeGroup(iNumberAt, UnsafeUtil.getObject(t2, offset(iTypeAndOffsetAt)), getMessageFieldSchema(length));
                    }
                    break;
            }
        }
        while (entry != null) {
            this.extensionSchema.serializeExtension(writer, entry);
            entry = itDescendingIterator.hasNext() ? (Map.Entry) itDescendingIterator.next() : null;
        }
    }

    private <K, V> void writeMapHelper(Writer writer, int i2, Object obj, int i3) {
        if (obj != null) {
            writer.writeMap(i2, this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i3)), this.mapFieldSchema.forMapData(obj));
        }
    }

    private void writeString(int i2, Object obj, Writer writer) {
        if (obj instanceof String) {
            writer.writeString(i2, (String) obj);
        } else {
            writer.writeBytes(i2, (ByteString) obj);
        }
    }

    private <UT, UB> void writeUnknownInMessageTo(UnknownFieldSchema<UT, UB> unknownFieldSchema, T t2, Writer writer) {
        unknownFieldSchema.writeTo(unknownFieldSchema.getFromMessage(t2), writer);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public boolean equals(T t2, T t3) {
        int length = this.buffer.length;
        for (int i2 = 0; i2 < length; i2 += 3) {
            if (!equals(t2, t3, i2)) {
                return false;
            }
        }
        if (!this.unknownFieldSchema.getFromMessage(t2).equals(this.unknownFieldSchema.getFromMessage(t3))) {
            return false;
        }
        if (this.hasExtensions) {
            return this.extensionSchema.getExtensions(t2).equals(this.extensionSchema.getExtensions(t3));
        }
        return true;
    }

    public int getSchemaSize() {
        return this.buffer.length * 3;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public int getSerializedSize(T t2) {
        return this.proto3 ? getSerializedSizeProto3(t2) : getSerializedSizeProto2(t2);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:33:0x005f  */
    /* JADX WARN: Code duplicated, block: B:48:0x0091  */
    /* JADX WARN: Code duplicated, block: B:54:0x00a6  */
    /* JADX WARN: Code duplicated, block: B:64:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:68:0x00e0 A[PHI: r3
  0x00e0: PHI (r3v12 java.lang.Object) = (r3v10 java.lang.Object), (r3v13 java.lang.Object) binds: [B:67:0x00de, B:62:0x00cc] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:70:0x00e8  */
    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public int hashCode(T t2) {
        int i2;
        double dOneofDoubleAt;
        float fOneofFloatAt;
        long jDoubleToLongBits;
        int i3;
        int iOneofIntAt;
        boolean zOneofBooleanAt;
        int iHashCode;
        Object object;
        Object object2;
        int length = this.buffer.length;
        int i4 = 0;
        for (int i5 = 0; i5 < length; i5 += 3) {
            int iTypeAndOffsetAt = typeAndOffsetAt(i5);
            int iNumberAt = numberAt(i5);
            long jOffset = offset(iTypeAndOffsetAt);
            int iHashCode2 = 37;
            switch (type(iTypeAndOffsetAt)) {
                case 0:
                    i2 = i4 * 53;
                    dOneofDoubleAt = UnsafeUtil.getDouble(t2, jOffset);
                    jDoubleToLongBits = Double.doubleToLongBits(dOneofDoubleAt);
                    iHashCode = Internal.hashLong(jDoubleToLongBits);
                    i4 = iHashCode + i2;
                    break;
                case 1:
                    i2 = i4 * 53;
                    fOneofFloatAt = UnsafeUtil.getFloat(t2, jOffset);
                    iHashCode = Float.floatToIntBits(fOneofFloatAt);
                    i4 = iHashCode + i2;
                    break;
                case 2:
                case 3:
                case 5:
                case TYPE_ENUM_VALUE:
                case 16:
                    i2 = i4 * 53;
                    jDoubleToLongBits = UnsafeUtil.getLong(t2, jOffset);
                    iHashCode = Internal.hashLong(jDoubleToLongBits);
                    i4 = iHashCode + i2;
                    break;
                case 4:
                case 6:
                case 11:
                case 12:
                case TYPE_UINT32_VALUE:
                case TYPE_SFIXED32_VALUE:
                    i3 = i4 * 53;
                    iOneofIntAt = UnsafeUtil.getInt(t2, jOffset);
                    i4 = i3 + iOneofIntAt;
                    break;
                case 7:
                    i2 = i4 * 53;
                    zOneofBooleanAt = UnsafeUtil.getBoolean(t2, jOffset);
                    iHashCode = Internal.hashBoolean(zOneofBooleanAt);
                    i4 = iHashCode + i2;
                    break;
                case 8:
                    i2 = i4 * 53;
                    iHashCode = ((String) UnsafeUtil.getObject(t2, jOffset)).hashCode();
                    i4 = iHashCode + i2;
                    break;
                case 9:
                    object = UnsafeUtil.getObject(t2, jOffset);
                    if (object != null) {
                        iHashCode2 = object.hashCode();
                    }
                    i4 = (i4 * 53) + iHashCode2;
                    break;
                case 10:
                case TYPE_SINT64_VALUE:
                case Base64.Encoder.LINE_GROUPS /* 19 */:
                case OFFSET_BITS /* 20 */:
                case 21:
                case 22:
                case 23:
                case InsecureNonceXChaCha20.NONCE_SIZE_IN_BYTES /* 24 */:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                case 50:
                    i2 = i4 * 53;
                    object2 = UnsafeUtil.getObject(t2, jOffset);
                    iHashCode = object2.hashCode();
                    i4 = iHashCode + i2;
                    break;
                case TYPE_SINT32_VALUE:
                    object = UnsafeUtil.getObject(t2, jOffset);
                    if (object != null) {
                        iHashCode2 = object.hashCode();
                    }
                    i4 = (i4 * 53) + iHashCode2;
                    break;
                case ONEOF_TYPE_OFFSET /* 51 */:
                    if (isOneofPresent(t2, iNumberAt, i5)) {
                        i2 = i4 * 53;
                        dOneofDoubleAt = oneofDoubleAt(t2, jOffset);
                        jDoubleToLongBits = Double.doubleToLongBits(dOneofDoubleAt);
                        iHashCode = Internal.hashLong(jDoubleToLongBits);
                        i4 = iHashCode + i2;
                    }
                    break;
                case 52:
                    if (isOneofPresent(t2, iNumberAt, i5)) {
                        i2 = i4 * 53;
                        fOneofFloatAt = oneofFloatAt(t2, jOffset);
                        iHashCode = Float.floatToIntBits(fOneofFloatAt);
                        i4 = iHashCode + i2;
                    }
                    break;
                case 53:
                    if (isOneofPresent(t2, iNumberAt, i5)) {
                        i2 = i4 * 53;
                        jDoubleToLongBits = oneofLongAt(t2, jOffset);
                        iHashCode = Internal.hashLong(jDoubleToLongBits);
                        i4 = iHashCode + i2;
                    }
                    break;
                case 54:
                    if (isOneofPresent(t2, iNumberAt, i5)) {
                        i2 = i4 * 53;
                        jDoubleToLongBits = oneofLongAt(t2, jOffset);
                        iHashCode = Internal.hashLong(jDoubleToLongBits);
                        i4 = iHashCode + i2;
                    }
                    break;
                case 55:
                    if (isOneofPresent(t2, iNumberAt, i5)) {
                        i3 = i4 * 53;
                        iOneofIntAt = oneofIntAt(t2, jOffset);
                        i4 = i3 + iOneofIntAt;
                    }
                    break;
                case 56:
                    if (isOneofPresent(t2, iNumberAt, i5)) {
                        i2 = i4 * 53;
                        jDoubleToLongBits = oneofLongAt(t2, jOffset);
                        iHashCode = Internal.hashLong(jDoubleToLongBits);
                        i4 = iHashCode + i2;
                    }
                    break;
                case 57:
                    if (isOneofPresent(t2, iNumberAt, i5)) {
                        i3 = i4 * 53;
                        iOneofIntAt = oneofIntAt(t2, jOffset);
                        i4 = i3 + iOneofIntAt;
                    }
                    break;
                case 58:
                    if (isOneofPresent(t2, iNumberAt, i5)) {
                        i2 = i4 * 53;
                        zOneofBooleanAt = oneofBooleanAt(t2, jOffset);
                        iHashCode = Internal.hashBoolean(zOneofBooleanAt);
                        i4 = iHashCode + i2;
                    }
                    break;
                case 59:
                    if (isOneofPresent(t2, iNumberAt, i5)) {
                        i2 = i4 * 53;
                        iHashCode = ((String) UnsafeUtil.getObject(t2, jOffset)).hashCode();
                        i4 = iHashCode + i2;
                    }
                    break;
                case 60:
                    if (isOneofPresent(t2, iNumberAt, i5)) {
                        object2 = UnsafeUtil.getObject(t2, jOffset);
                        i2 = i4 * 53;
                        iHashCode = object2.hashCode();
                        i4 = iHashCode + i2;
                    }
                    break;
                case 61:
                    if (isOneofPresent(t2, iNumberAt, i5)) {
                        i2 = i4 * 53;
                        object2 = UnsafeUtil.getObject(t2, jOffset);
                        iHashCode = object2.hashCode();
                        i4 = iHashCode + i2;
                    }
                    break;
                case 62:
                    if (isOneofPresent(t2, iNumberAt, i5)) {
                        i3 = i4 * 53;
                        iOneofIntAt = oneofIntAt(t2, jOffset);
                        i4 = i3 + iOneofIntAt;
                    }
                    break;
                case 63:
                    if (isOneofPresent(t2, iNumberAt, i5)) {
                        i3 = i4 * 53;
                        iOneofIntAt = oneofIntAt(t2, jOffset);
                        i4 = i3 + iOneofIntAt;
                    }
                    break;
                case 64:
                    if (isOneofPresent(t2, iNumberAt, i5)) {
                        i3 = i4 * 53;
                        iOneofIntAt = oneofIntAt(t2, jOffset);
                        i4 = i3 + iOneofIntAt;
                    }
                    break;
                case 65:
                    if (isOneofPresent(t2, iNumberAt, i5)) {
                        i2 = i4 * 53;
                        jDoubleToLongBits = oneofLongAt(t2, jOffset);
                        iHashCode = Internal.hashLong(jDoubleToLongBits);
                        i4 = iHashCode + i2;
                    }
                    break;
                case 66:
                    if (isOneofPresent(t2, iNumberAt, i5)) {
                        i3 = i4 * 53;
                        iOneofIntAt = oneofIntAt(t2, jOffset);
                        i4 = i3 + iOneofIntAt;
                    }
                    break;
                case 67:
                    if (isOneofPresent(t2, iNumberAt, i5)) {
                        i2 = i4 * 53;
                        jDoubleToLongBits = oneofLongAt(t2, jOffset);
                        iHashCode = Internal.hashLong(jDoubleToLongBits);
                        i4 = iHashCode + i2;
                    }
                    break;
                case 68:
                    if (isOneofPresent(t2, iNumberAt, i5)) {
                        object2 = UnsafeUtil.getObject(t2, jOffset);
                        i2 = i4 * 53;
                        iHashCode = object2.hashCode();
                        i4 = iHashCode + i2;
                    }
                    break;
            }
        }
        int iHashCode3 = this.unknownFieldSchema.getFromMessage(t2).hashCode() + (i4 * 53);
        return this.hasExtensions ? (iHashCode3 * 53) + this.extensionSchema.getExtensions(t2).hashCode() : iHashCode3;
    }

    /* JADX WARN: Code duplicated, block: B:39:0x008b  */
    /* JADX WARN: Code duplicated, block: B:58:0x0091 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:63:0x00ac A[SYNTHETIC] */
    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public final boolean isInitialized(T t2) {
        int i2;
        int i3;
        int i4 = 1048575;
        int i5 = 0;
        int i6 = 0;
        while (i6 < this.checkInitializedCount) {
            int i7 = this.intArray[i6];
            int iNumberAt = numberAt(i7);
            int iTypeAndOffsetAt = typeAndOffsetAt(i7);
            int i8 = this.buffer[i7 + 2];
            int i9 = i8 & 1048575;
            int i10 = 1 << (i8 >>> OFFSET_BITS);
            if (i9 != i4) {
                if (i9 != 1048575) {
                    i5 = UNSAFE.getInt(t2, i9);
                }
                i3 = i5;
                i2 = i9;
            } else {
                i2 = i4;
                i3 = i5;
            }
            if (isRequired(iTypeAndOffsetAt) && !isFieldPresent(t2, i7, i2, i3, i10)) {
                return false;
            }
            int iType = type(iTypeAndOffsetAt);
            if (iType == 9 || iType == 17) {
                if (isFieldPresent(t2, i7, i2, i3, i10) && !isInitialized(t2, iTypeAndOffsetAt, getMessageFieldSchema(i7))) {
                    return false;
                }
            } else if (iType == 27) {
                if (!isListInitialized(t2, iTypeAndOffsetAt, i7)) {
                    return false;
                }
            } else if (iType == 60 || iType == 68) {
                if (isOneofPresent(t2, iNumberAt, i7) && !isInitialized(t2, iTypeAndOffsetAt, getMessageFieldSchema(i7))) {
                    return false;
                }
            } else if (iType != 49) {
                if (iType == 50 && !isMapInitialized(t2, iTypeAndOffsetAt, i7)) {
                    return false;
                }
            } else if (!isListInitialized(t2, iTypeAndOffsetAt, i7)) {
                return false;
            }
            i6++;
            i4 = i2;
            i5 = i3;
        }
        return !this.hasExtensions || this.extensionSchema.getExtensions(t2).isInitialized();
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0049  */
    /* JADX WARN: Code duplicated, block: B:20:0x004f  */
    /* JADX WARN: Code duplicated, block: B:31:0x005c A[SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public void makeImmutable(T t2) {
        if (isMutable(t2)) {
            if (t2 instanceof GeneratedMessageLite) {
                GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) t2;
                generatedMessageLite.clearMemoizedSerializedSize();
                generatedMessageLite.clearMemoizedHashCode();
                generatedMessageLite.markImmutable();
            }
            int length = this.buffer.length;
            for (int i2 = 0; i2 < length; i2 += 3) {
                int iTypeAndOffsetAt = typeAndOffsetAt(i2);
                long jOffset = offset(iTypeAndOffsetAt);
                int iType = type(iTypeAndOffsetAt);
                if (iType != 9) {
                    switch (iType) {
                        case TYPE_SINT32_VALUE:
                            if (isFieldPresent(t2, i2)) {
                                getMessageFieldSchema(i2).makeImmutable(UNSAFE.getObject(t2, jOffset));
                            }
                            break;
                        case TYPE_SINT64_VALUE:
                        case Base64.Encoder.LINE_GROUPS /* 19 */:
                        case OFFSET_BITS /* 20 */:
                        case 21:
                        case 22:
                        case 23:
                        case InsecureNonceXChaCha20.NONCE_SIZE_IN_BYTES /* 24 */:
                        case 25:
                        case 26:
                        case 27:
                        case 28:
                        case 29:
                        case 30:
                        case 31:
                        case 32:
                        case 33:
                        case 34:
                        case 35:
                        case 36:
                        case 37:
                        case 38:
                        case 39:
                        case 40:
                        case 41:
                        case 42:
                        case 43:
                        case 44:
                        case 45:
                        case 46:
                        case 47:
                        case 48:
                        case 49:
                            this.listFieldSchema.makeImmutableListAt(t2, jOffset);
                            break;
                        case 50:
                            Unsafe unsafe = UNSAFE;
                            Object object = unsafe.getObject(t2, jOffset);
                            if (object != null) {
                                unsafe.putObject(t2, jOffset, this.mapFieldSchema.toImmutable(object));
                            }
                            break;
                    }
                } else if (isFieldPresent(t2, i2)) {
                    getMessageFieldSchema(i2).makeImmutable(UNSAFE.getObject(t2, jOffset));
                }
            }
            this.unknownFieldSchema.makeImmutable(t2);
            if (this.hasExtensions) {
                this.extensionSchema.makeImmutable(t2);
            }
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public void mergeFrom(T t2, Reader reader, ExtensionRegistryLite extensionRegistryLite) throws Throwable {
        extensionRegistryLite.getClass();
        checkMutable(t2);
        mergeFromHelper(this.unknownFieldSchema, this.extensionSchema, t2, reader, extensionRegistryLite);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public T newInstance() {
        return (T) this.newInstanceSchema.newInstance(this.defaultInstance);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:24:0x008e. Please report as an issue. */
    @CanIgnoreReturnValue
    public int parseProto2Message(T t2, byte[] bArr, int i2, int i3, int i4, ArrayDecoders.Registers registers) {
        Unsafe unsafe;
        MessageSchema<T> messageSchema;
        int i5;
        int i6;
        int i7;
        int i8;
        T t3;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        byte[] bArr2;
        int iDecodeVarint64;
        long jDecodeZigZag64;
        int iDecodeZigZag32;
        long j2;
        long j3;
        int i20;
        int i21;
        MessageSchema<T> messageSchema2 = this;
        T t4 = t2;
        byte[] bArr3 = bArr;
        int i22 = i3;
        i4 = i4;
        ArrayDecoders.Registers registers2 = registers;
        checkMutable(t2);
        Unsafe unsafe2 = UNSAFE;
        int iDecodeUnknownField = i2;
        int i23 = -1;
        int i24 = 0;
        int i25 = 0;
        int i26 = 0;
        int i27 = 1048575;
        while (true) {
            if (iDecodeUnknownField < i22) {
                int i28 = iDecodeUnknownField + 1;
                byte b2 = bArr3[iDecodeUnknownField];
                if (b2 < 0) {
                    int iDecodeVarint32 = ArrayDecoders.decodeVarint32(b2, bArr3, i28, registers2);
                    i9 = registers2.int1;
                    i28 = iDecodeVarint32;
                } else {
                    i9 = b2;
                }
                int i29 = i9 >>> 3;
                int i30 = i9 & 7;
                int iPositionForFieldNumber = i29 > i23 ? messageSchema2.positionForFieldNumber(i29, i24 / 3) : messageSchema2.positionForFieldNumber(i29);
                if (iPositionForFieldNumber == -1) {
                    i10 = i29;
                    i11 = i28;
                    i6 = i9;
                    i12 = i26;
                    i13 = i27;
                    unsafe = unsafe2;
                    i4 = i4;
                    i14 = 0;
                } else {
                    int i31 = messageSchema2.buffer[iPositionForFieldNumber + 1];
                    int iType = type(i31);
                    long jOffset = offset(i31);
                    int i32 = i9;
                    if (iType <= 17) {
                        int i33 = messageSchema2.buffer[iPositionForFieldNumber + 2];
                        int i34 = 1 << (i33 >>> OFFSET_BITS);
                        int i35 = i33 & 1048575;
                        if (i35 != i27) {
                            if (i27 != 1048575) {
                                unsafe2.putInt(t4, i27, i26);
                            }
                            i16 = i35;
                            i15 = unsafe2.getInt(t4, i35);
                        } else {
                            i15 = i26;
                            i16 = i27;
                        }
                        switch (iType) {
                            case 0:
                                bArr2 = bArr;
                                i10 = i29;
                                i19 = iPositionForFieldNumber;
                                i17 = i16;
                                i18 = i32;
                                if (i30 == 1) {
                                    UnsafeUtil.putDouble(t4, jOffset, ArrayDecoders.decodeDouble(bArr2, i28));
                                    iDecodeUnknownField = i28 + 8;
                                    i26 = i15 | i34;
                                    i24 = i19;
                                    i25 = i18;
                                    i23 = i10;
                                    i27 = i17;
                                    bArr3 = bArr2;
                                } else {
                                    i13 = i17;
                                    i4 = i4;
                                    i11 = i28;
                                    i14 = i19;
                                    unsafe = unsafe2;
                                    i12 = i15;
                                    i6 = i18;
                                }
                                break;
                            case 1:
                                bArr2 = bArr;
                                i10 = i29;
                                i19 = iPositionForFieldNumber;
                                i17 = i16;
                                i18 = i32;
                                if (i30 == 5) {
                                    UnsafeUtil.putFloat(t4, jOffset, ArrayDecoders.decodeFloat(bArr2, i28));
                                    iDecodeUnknownField = i28 + 4;
                                    i26 = i15 | i34;
                                    i24 = i19;
                                    i25 = i18;
                                    i23 = i10;
                                    i27 = i17;
                                    bArr3 = bArr2;
                                } else {
                                    i13 = i17;
                                    i4 = i4;
                                    i11 = i28;
                                    i14 = i19;
                                    unsafe = unsafe2;
                                    i12 = i15;
                                    i6 = i18;
                                }
                                break;
                            case 2:
                            case 3:
                                bArr2 = bArr;
                                i10 = i29;
                                i19 = iPositionForFieldNumber;
                                i17 = i16;
                                i18 = i32;
                                if (i30 == 0) {
                                    iDecodeVarint64 = ArrayDecoders.decodeVarint64(bArr2, i28, registers2);
                                    jDecodeZigZag64 = registers2.long1;
                                    unsafe2.putLong(t2, jOffset, jDecodeZigZag64);
                                    i26 = i15 | i34;
                                    i24 = i19;
                                    iDecodeUnknownField = iDecodeVarint64;
                                    i25 = i18;
                                    i23 = i10;
                                    i27 = i17;
                                    bArr3 = bArr2;
                                } else {
                                    i13 = i17;
                                    i4 = i4;
                                    i11 = i28;
                                    i14 = i19;
                                    unsafe = unsafe2;
                                    i12 = i15;
                                    i6 = i18;
                                }
                                break;
                            case 4:
                            case 11:
                                bArr2 = bArr;
                                i10 = i29;
                                i19 = iPositionForFieldNumber;
                                i17 = i16;
                                i18 = i32;
                                if (i30 == 0) {
                                    iDecodeUnknownField = ArrayDecoders.decodeVarint32(bArr2, i28, registers2);
                                    iDecodeZigZag32 = registers2.int1;
                                    j2 = jOffset;
                                    unsafe2.putInt(t4, j2, iDecodeZigZag32);
                                    i26 = i15 | i34;
                                    i24 = i19;
                                    i25 = i18;
                                    i23 = i10;
                                    i27 = i17;
                                    bArr3 = bArr2;
                                } else {
                                    i13 = i17;
                                    i4 = i4;
                                    i11 = i28;
                                    i14 = i19;
                                    unsafe = unsafe2;
                                    i12 = i15;
                                    i6 = i18;
                                }
                                break;
                            case 5:
                            case TYPE_ENUM_VALUE:
                                bArr2 = bArr;
                                i10 = i29;
                                i19 = iPositionForFieldNumber;
                                i17 = i16;
                                i18 = i32;
                                if (i30 == 1) {
                                    unsafe2.putLong(t2, jOffset, ArrayDecoders.decodeFixed64(bArr2, i28));
                                    iDecodeUnknownField = i28 + 8;
                                    i26 = i15 | i34;
                                    i24 = i19;
                                    i25 = i18;
                                    i23 = i10;
                                    i27 = i17;
                                    bArr3 = bArr2;
                                } else {
                                    i13 = i17;
                                    i4 = i4;
                                    i11 = i28;
                                    i14 = i19;
                                    unsafe = unsafe2;
                                    i12 = i15;
                                    i6 = i18;
                                }
                                break;
                            case 6:
                            case TYPE_UINT32_VALUE:
                                bArr2 = bArr;
                                i10 = i29;
                                i19 = iPositionForFieldNumber;
                                i17 = i16;
                                i18 = i32;
                                if (i30 == 5) {
                                    unsafe2.putInt(t4, jOffset, ArrayDecoders.decodeFixed32(bArr2, i28));
                                    iDecodeUnknownField = i28 + 4;
                                    i26 = i15 | i34;
                                    i24 = i19;
                                    i25 = i18;
                                    i23 = i10;
                                    i27 = i17;
                                    bArr3 = bArr2;
                                } else {
                                    i13 = i17;
                                    i4 = i4;
                                    i11 = i28;
                                    i14 = i19;
                                    unsafe = unsafe2;
                                    i12 = i15;
                                    i6 = i18;
                                }
                                break;
                            case 7:
                                bArr2 = bArr;
                                i10 = i29;
                                i19 = iPositionForFieldNumber;
                                i17 = i16;
                                i18 = i32;
                                if (i30 == 0) {
                                    iDecodeUnknownField = ArrayDecoders.decodeVarint64(bArr2, i28, registers2);
                                    UnsafeUtil.putBoolean(t4, jOffset, registers2.long1 != 0);
                                    i26 = i15 | i34;
                                    i24 = i19;
                                    i25 = i18;
                                    i23 = i10;
                                    i27 = i17;
                                    bArr3 = bArr2;
                                } else {
                                    i13 = i17;
                                    i4 = i4;
                                    i11 = i28;
                                    i14 = i19;
                                    unsafe = unsafe2;
                                    i12 = i15;
                                    i6 = i18;
                                }
                                break;
                            case 8:
                                bArr2 = bArr;
                                i10 = i29;
                                i19 = iPositionForFieldNumber;
                                i17 = i16;
                                i18 = i32;
                                j3 = jOffset;
                                if (i30 == 2) {
                                    iDecodeUnknownField = (ENFORCE_UTF8_MASK & i31) == 0 ? ArrayDecoders.decodeString(bArr2, i28, registers2) : ArrayDecoders.decodeStringRequireUtf8(bArr2, i28, registers2);
                                    unsafe2.putObject(t4, j3, registers2.object1);
                                    i26 = i15 | i34;
                                    i24 = i19;
                                    i25 = i18;
                                    i23 = i10;
                                    i27 = i17;
                                    bArr3 = bArr2;
                                } else {
                                    i13 = i17;
                                    i4 = i4;
                                    i11 = i28;
                                    i14 = i19;
                                    unsafe = unsafe2;
                                    i12 = i15;
                                    i6 = i18;
                                }
                                break;
                            case 9:
                                bArr2 = bArr;
                                i10 = i29;
                                i19 = iPositionForFieldNumber;
                                i17 = i16;
                                i18 = i32;
                                if (i30 == 2) {
                                    Object objMutableMessageFieldForMerge = messageSchema2.mutableMessageFieldForMerge(t4, i19);
                                    iDecodeUnknownField = ArrayDecoders.mergeMessageField(objMutableMessageFieldForMerge, messageSchema2.getMessageFieldSchema(i19), bArr, i28, i3, registers);
                                    messageSchema2.storeMessageField(t4, i19, objMutableMessageFieldForMerge);
                                    i26 = i15 | i34;
                                    i24 = i19;
                                    i25 = i18;
                                    i23 = i10;
                                    i27 = i17;
                                    bArr3 = bArr2;
                                } else {
                                    i13 = i17;
                                    i4 = i4;
                                    i11 = i28;
                                    i14 = i19;
                                    unsafe = unsafe2;
                                    i12 = i15;
                                    i6 = i18;
                                }
                                break;
                            case 10:
                                bArr2 = bArr;
                                i10 = i29;
                                i19 = iPositionForFieldNumber;
                                i17 = i16;
                                i18 = i32;
                                j3 = jOffset;
                                if (i30 == 2) {
                                    iDecodeUnknownField = ArrayDecoders.decodeBytes(bArr2, i28, registers2);
                                    unsafe2.putObject(t4, j3, registers2.object1);
                                    i26 = i15 | i34;
                                    i24 = i19;
                                    i25 = i18;
                                    i23 = i10;
                                    i27 = i17;
                                    bArr3 = bArr2;
                                } else {
                                    i13 = i17;
                                    i4 = i4;
                                    i11 = i28;
                                    i14 = i19;
                                    unsafe = unsafe2;
                                    i12 = i15;
                                    i6 = i18;
                                }
                                break;
                            case 12:
                                bArr2 = bArr;
                                i10 = i29;
                                i19 = iPositionForFieldNumber;
                                i17 = i16;
                                i18 = i32;
                                j2 = jOffset;
                                if (i30 == 0) {
                                    iDecodeUnknownField = ArrayDecoders.decodeVarint32(bArr2, i28, registers2);
                                    iDecodeZigZag32 = registers2.int1;
                                    Internal.EnumVerifier enumFieldVerifier = messageSchema2.getEnumFieldVerifier(i19);
                                    if (enumFieldVerifier == null || enumFieldVerifier.isInRange(iDecodeZigZag32)) {
                                        unsafe2.putInt(t4, j2, iDecodeZigZag32);
                                        i26 = i15 | i34;
                                        i24 = i19;
                                        i25 = i18;
                                        i23 = i10;
                                        i27 = i17;
                                    } else {
                                        getMutableUnknownFields(t2).storeField(i18, Long.valueOf(iDecodeZigZag32));
                                        i24 = i19;
                                        i26 = i15;
                                        i25 = i18;
                                        i23 = i10;
                                        i27 = i17;
                                        i4 = i4;
                                    }
                                    bArr3 = bArr2;
                                } else {
                                    i13 = i17;
                                    i4 = i4;
                                    i11 = i28;
                                    i14 = i19;
                                    unsafe = unsafe2;
                                    i12 = i15;
                                    i6 = i18;
                                }
                                break;
                            case TYPE_SFIXED32_VALUE:
                                bArr2 = bArr;
                                i10 = i29;
                                i19 = iPositionForFieldNumber;
                                i17 = i16;
                                i18 = i32;
                                j2 = jOffset;
                                if (i30 == 0) {
                                    iDecodeUnknownField = ArrayDecoders.decodeVarint32(bArr2, i28, registers2);
                                    iDecodeZigZag32 = CodedInputStream.decodeZigZag32(registers2.int1);
                                    unsafe2.putInt(t4, j2, iDecodeZigZag32);
                                    i26 = i15 | i34;
                                    i24 = i19;
                                    i25 = i18;
                                    i23 = i10;
                                    i27 = i17;
                                    bArr3 = bArr2;
                                } else {
                                    i13 = i17;
                                    i4 = i4;
                                    i11 = i28;
                                    i14 = i19;
                                    unsafe = unsafe2;
                                    i12 = i15;
                                    i6 = i18;
                                }
                                break;
                            case 16:
                                i10 = i29;
                                i19 = iPositionForFieldNumber;
                                i17 = i16;
                                i18 = i32;
                                bArr2 = bArr;
                                if (i30 == 0) {
                                    iDecodeVarint64 = ArrayDecoders.decodeVarint64(bArr2, i28, registers2);
                                    jDecodeZigZag64 = CodedInputStream.decodeZigZag64(registers2.long1);
                                    unsafe2.putLong(t2, jOffset, jDecodeZigZag64);
                                    i26 = i15 | i34;
                                    i24 = i19;
                                    iDecodeUnknownField = iDecodeVarint64;
                                    i25 = i18;
                                    i23 = i10;
                                    i27 = i17;
                                    bArr3 = bArr2;
                                } else {
                                    i13 = i17;
                                    i4 = i4;
                                    i11 = i28;
                                    i14 = i19;
                                    unsafe = unsafe2;
                                    i12 = i15;
                                    i6 = i18;
                                }
                                break;
                            case TYPE_SINT32_VALUE:
                                if (i30 == 3) {
                                    Object objMutableMessageFieldForMerge2 = messageSchema2.mutableMessageFieldForMerge(t4, iPositionForFieldNumber);
                                    iDecodeUnknownField = ArrayDecoders.mergeGroupField(objMutableMessageFieldForMerge2, messageSchema2.getMessageFieldSchema(iPositionForFieldNumber), bArr, i28, i3, (i29 << 3) | 4, registers);
                                    messageSchema2.storeMessageField(t4, iPositionForFieldNumber, objMutableMessageFieldForMerge2);
                                    i26 = i15 | i34;
                                    i27 = i16;
                                    i4 = i4;
                                    i24 = iPositionForFieldNumber;
                                    i25 = i32;
                                    i23 = i29;
                                    bArr3 = bArr;
                                } else {
                                    i10 = i29;
                                    i17 = i16;
                                    i18 = i32;
                                    i19 = iPositionForFieldNumber;
                                    i13 = i17;
                                    i4 = i4;
                                    i11 = i28;
                                    i14 = i19;
                                    unsafe = unsafe2;
                                    i12 = i15;
                                    i6 = i18;
                                }
                                break;
                            default:
                                i10 = i29;
                                i19 = iPositionForFieldNumber;
                                i17 = i16;
                                i18 = i32;
                                i13 = i17;
                                i4 = i4;
                                i11 = i28;
                                i14 = i19;
                                unsafe = unsafe2;
                                i12 = i15;
                                i6 = i18;
                                break;
                        }
                    } else {
                        i10 = i29;
                        i13 = i27;
                        i12 = i26;
                        if (iType == 27) {
                            if (i30 == 2) {
                                Internal.ProtobufList protobufListMutableCopyWithCapacity2 = (Internal.ProtobufList) unsafe2.getObject(t4, jOffset);
                                if (!protobufListMutableCopyWithCapacity2.isModifiable()) {
                                    int size = protobufListMutableCopyWithCapacity2.size();
                                    protobufListMutableCopyWithCapacity2 = protobufListMutableCopyWithCapacity2.mutableCopyWithCapacity2(size == 0 ? 10 : size * 2);
                                    unsafe2.putObject(t4, jOffset, protobufListMutableCopyWithCapacity2);
                                }
                                iDecodeUnknownField = ArrayDecoders.decodeMessageList(messageSchema2.getMessageFieldSchema(iPositionForFieldNumber), i32, bArr, i28, i3, protobufListMutableCopyWithCapacity2, registers);
                                i24 = iPositionForFieldNumber;
                                i25 = i32;
                                i27 = i13;
                                i26 = i12;
                                i23 = i10;
                                bArr3 = bArr;
                                i4 = i4;
                            } else {
                                i20 = i28;
                                unsafe = unsafe2;
                                i14 = iPositionForFieldNumber;
                                i21 = i32;
                                i11 = i20;
                                i6 = i21;
                            }
                        } else if (iType <= 49) {
                            int i36 = i28;
                            unsafe = unsafe2;
                            i14 = iPositionForFieldNumber;
                            i21 = i32;
                            iDecodeUnknownField = parseRepeatedField(t2, bArr, i28, i3, i32, i10, i30, iPositionForFieldNumber, i31, iType, jOffset, registers);
                            if (iDecodeUnknownField != i36) {
                                messageSchema2 = this;
                                t4 = t2;
                                bArr3 = bArr;
                                i22 = i3;
                                i4 = i4;
                                registers2 = registers;
                                i27 = i13;
                                i26 = i12;
                                i24 = i14;
                                i25 = i21;
                                i23 = i10;
                                unsafe2 = unsafe;
                            } else {
                                i11 = iDecodeUnknownField;
                                i6 = i21;
                            }
                        } else {
                            i20 = i28;
                            unsafe = unsafe2;
                            i14 = iPositionForFieldNumber;
                            i21 = i32;
                            if (iType != 50) {
                                iDecodeUnknownField = parseOneofField(t2, bArr, i20, i3, i21, i10, i30, i31, iType, jOffset, i14, registers);
                                if (iDecodeUnknownField != i20) {
                                    messageSchema2 = this;
                                    t4 = t2;
                                    bArr3 = bArr;
                                    i22 = i3;
                                    i4 = i4;
                                    registers2 = registers;
                                    i27 = i13;
                                    i26 = i12;
                                    i24 = i14;
                                    i25 = i21;
                                    i23 = i10;
                                    unsafe2 = unsafe;
                                } else {
                                    i11 = iDecodeUnknownField;
                                    i6 = i21;
                                }
                            } else if (i30 == 2) {
                                iDecodeUnknownField = parseMapField(t2, bArr, i20, i3, i14, jOffset, registers);
                                if (iDecodeUnknownField != i20) {
                                    messageSchema2 = this;
                                    t4 = t2;
                                    bArr3 = bArr;
                                    i22 = i3;
                                    i4 = i4;
                                    registers2 = registers;
                                    i27 = i13;
                                    i26 = i12;
                                    i24 = i14;
                                    i25 = i21;
                                    i23 = i10;
                                    unsafe2 = unsafe;
                                } else {
                                    i11 = iDecodeUnknownField;
                                    i6 = i21;
                                }
                            } else {
                                i11 = i20;
                                i6 = i21;
                            }
                        }
                    }
                }
                if (i6 != i4 || i4 == 0) {
                    iDecodeUnknownField = (!this.hasExtensions || registers.extensionRegistry == ExtensionRegistryLite.getEmptyRegistry()) ? ArrayDecoders.decodeUnknownField(i6, bArr, i11, i3, getMutableUnknownFields(t2), registers) : ArrayDecoders.decodeExtensionOrUnknownField(i6, bArr, i11, i3, t2, this.defaultInstance, this.unknownFieldSchema, registers);
                    t4 = t2;
                    bArr3 = bArr;
                    i22 = i3;
                    i25 = i6;
                    messageSchema2 = this;
                    registers2 = registers;
                    i27 = i13;
                    i26 = i12;
                    i24 = i14;
                    i23 = i10;
                    unsafe2 = unsafe;
                    i4 = i4;
                } else {
                    i8 = 1048575;
                    messageSchema = this;
                    i5 = i11;
                    i7 = i13;
                    i26 = i12;
                }
            } else {
                int i37 = i27;
                unsafe = unsafe2;
                i4 = i4;
                messageSchema = messageSchema2;
                i5 = iDecodeUnknownField;
                i6 = i25;
                i7 = i37;
                i8 = 1048575;
            }
        }
        if (i7 != i8) {
            t3 = t2;
            unsafe.putInt(t3, i7, i26);
        } else {
            t3 = t2;
        }
        UnknownFieldSetLite unknownFieldSetLite = null;
        for (int i38 = messageSchema.checkInitializedCount; i38 < messageSchema.repeatedFieldOffsetStart; i38++) {
            unknownFieldSetLite = (UnknownFieldSetLite) filterMapUnknownEnumValues(t2, messageSchema.intArray[i38], unknownFieldSetLite, messageSchema.unknownFieldSchema, t2);
        }
        if (unknownFieldSetLite != null) {
            messageSchema.unknownFieldSchema.setBuilderToMessage(t3, unknownFieldSetLite);
        }
        if (i4 == 0) {
            if (i5 != i3) {
                throw InvalidProtocolBufferException.parseFailure();
            }
        } else if (i5 > i3 || i6 != i4) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        return i5;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public void writeTo(T t2, Writer writer) {
        if (writer.fieldOrder() == Writer.FieldOrder.DESCENDING) {
            writeFieldsInDescendingOrder(t2, writer);
        } else if (this.proto3) {
            writeFieldsInAscendingOrderProto3(t2, writer);
        } else {
            writeFieldsInAscendingOrderProto2(t2, writer);
        }
    }

    private boolean equals(T t2, T t3, int i2) {
        int iTypeAndOffsetAt = typeAndOffsetAt(i2);
        long jOffset = offset(iTypeAndOffsetAt);
        switch (type(iTypeAndOffsetAt)) {
            case 0:
                return arePresentForEquals(t2, t3, i2) && Double.doubleToLongBits(UnsafeUtil.getDouble(t2, jOffset)) == Double.doubleToLongBits(UnsafeUtil.getDouble(t3, jOffset));
            case 1:
                return arePresentForEquals(t2, t3, i2) && Float.floatToIntBits(UnsafeUtil.getFloat(t2, jOffset)) == Float.floatToIntBits(UnsafeUtil.getFloat(t3, jOffset));
            case 2:
                return arePresentForEquals(t2, t3, i2) && UnsafeUtil.getLong(t2, jOffset) == UnsafeUtil.getLong(t3, jOffset);
            case 3:
                return arePresentForEquals(t2, t3, i2) && UnsafeUtil.getLong(t2, jOffset) == UnsafeUtil.getLong(t3, jOffset);
            case 4:
                return arePresentForEquals(t2, t3, i2) && UnsafeUtil.getInt(t2, jOffset) == UnsafeUtil.getInt(t3, jOffset);
            case 5:
                return arePresentForEquals(t2, t3, i2) && UnsafeUtil.getLong(t2, jOffset) == UnsafeUtil.getLong(t3, jOffset);
            case 6:
                return arePresentForEquals(t2, t3, i2) && UnsafeUtil.getInt(t2, jOffset) == UnsafeUtil.getInt(t3, jOffset);
            case 7:
                return arePresentForEquals(t2, t3, i2) && UnsafeUtil.getBoolean(t2, jOffset) == UnsafeUtil.getBoolean(t3, jOffset);
            case 8:
                return arePresentForEquals(t2, t3, i2) && SchemaUtil.safeEquals(UnsafeUtil.getObject(t2, jOffset), UnsafeUtil.getObject(t3, jOffset));
            case 9:
                return arePresentForEquals(t2, t3, i2) && SchemaUtil.safeEquals(UnsafeUtil.getObject(t2, jOffset), UnsafeUtil.getObject(t3, jOffset));
            case 10:
                return arePresentForEquals(t2, t3, i2) && SchemaUtil.safeEquals(UnsafeUtil.getObject(t2, jOffset), UnsafeUtil.getObject(t3, jOffset));
            case 11:
                return arePresentForEquals(t2, t3, i2) && UnsafeUtil.getInt(t2, jOffset) == UnsafeUtil.getInt(t3, jOffset);
            case 12:
                return arePresentForEquals(t2, t3, i2) && UnsafeUtil.getInt(t2, jOffset) == UnsafeUtil.getInt(t3, jOffset);
            case TYPE_UINT32_VALUE:
                return arePresentForEquals(t2, t3, i2) && UnsafeUtil.getInt(t2, jOffset) == UnsafeUtil.getInt(t3, jOffset);
            case TYPE_ENUM_VALUE:
                return arePresentForEquals(t2, t3, i2) && UnsafeUtil.getLong(t2, jOffset) == UnsafeUtil.getLong(t3, jOffset);
            case TYPE_SFIXED32_VALUE:
                return arePresentForEquals(t2, t3, i2) && UnsafeUtil.getInt(t2, jOffset) == UnsafeUtil.getInt(t3, jOffset);
            case 16:
                return arePresentForEquals(t2, t3, i2) && UnsafeUtil.getLong(t2, jOffset) == UnsafeUtil.getLong(t3, jOffset);
            case TYPE_SINT32_VALUE:
                return arePresentForEquals(t2, t3, i2) && SchemaUtil.safeEquals(UnsafeUtil.getObject(t2, jOffset), UnsafeUtil.getObject(t3, jOffset));
            case TYPE_SINT64_VALUE:
            case Base64.Encoder.LINE_GROUPS /* 19 */:
            case OFFSET_BITS /* 20 */:
            case 21:
            case 22:
            case 23:
            case InsecureNonceXChaCha20.NONCE_SIZE_IN_BYTES /* 24 */:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
                return SchemaUtil.safeEquals(UnsafeUtil.getObject(t2, jOffset), UnsafeUtil.getObject(t3, jOffset));
            case ONEOF_TYPE_OFFSET /* 51 */:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
                return isOneofCaseEqual(t2, t3, i2) && SchemaUtil.safeEquals(UnsafeUtil.getObject(t2, jOffset), UnsafeUtil.getObject(t3, jOffset));
            default:
                return true;
        }
    }

    private boolean isFieldPresent(T t2, int i2, int i3, int i4, int i5) {
        if (i3 == 1048575) {
            return isFieldPresent(t2, i2);
        }
        return (i4 & i5) != 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean isInitialized(Object obj, int i2, Schema schema) {
        return schema.isInitialized(UnsafeUtil.getObject(obj, offset(i2)));
    }

    private int positionForFieldNumber(int i2, int i3) {
        if (i2 < this.minFieldNumber || i2 > this.maxFieldNumber) {
            return -1;
        }
        return slowPositionForFieldNumber(i2, i3);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public void mergeFrom(T t2, T t3) {
        checkMutable(t2);
        t3.getClass();
        for (int i2 = 0; i2 < this.buffer.length; i2 += 3) {
            mergeSingleField(t2, t3, i2);
        }
        SchemaUtil.mergeUnknownFields(this.unknownFieldSchema, t2, t3);
        if (this.hasExtensions) {
            SchemaUtil.mergeExtensions(this.extensionSchema, t2, t3);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public void mergeFrom(T t2, byte[] bArr, int i2, int i3, ArrayDecoders.Registers registers) throws InvalidProtocolBufferException {
        if (this.proto3) {
            parseProto3Message(t2, bArr, i2, i3, registers);
        } else {
            parseProto2Message(t2, bArr, i2, i3, 0, registers);
        }
    }
}
