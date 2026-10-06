package com.google.crypto.tink.shaded.protobuf;

/* JADX INFO: loaded from: classes.dex */
@CheckReturnValue
final class MessageSetSchema<T> implements Schema<T> {
    private final MessageLite defaultInstance;
    private final ExtensionSchema<?> extensionSchema;
    private final boolean hasExtensions;
    private final UnknownFieldSchema<?, ?> unknownFieldSchema;

    private MessageSetSchema(UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MessageLite messageLite) {
        this.unknownFieldSchema = unknownFieldSchema;
        this.hasExtensions = extensionSchema.hasExtensions(messageLite);
        this.extensionSchema = extensionSchema;
        this.defaultInstance = messageLite;
    }

    private <UT, UB> int getUnknownFieldsSerializedSize(UnknownFieldSchema<UT, UB> unknownFieldSchema, T t2) {
        return unknownFieldSchema.getSerializedSizeAsMessageSet(unknownFieldSchema.getFromMessage(t2));
    }

    private <UT, UB, ET extends FieldSet.FieldDescriptorLite<ET>> void mergeFromHelper(UnknownFieldSchema<UT, UB> unknownFieldSchema, ExtensionSchema<ET> extensionSchema, T t2, Reader reader, ExtensionRegistryLite extensionRegistryLite) {
        UB builderFromMessage = unknownFieldSchema.getBuilderFromMessage(t2);
        FieldSet<ET> mutableExtensions = extensionSchema.getMutableExtensions(t2);
        while (reader.getFieldNumber() != Integer.MAX_VALUE) {
            try {
                if (!parseMessageSetItemOrUnknownField(reader, extensionRegistryLite, extensionSchema, mutableExtensions, unknownFieldSchema, builderFromMessage)) {
                    return;
                }
            } finally {
                unknownFieldSchema.setBuilderToMessage(t2, builderFromMessage);
            }
        }
    }

    public static <T> MessageSetSchema<T> newSchema(UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MessageLite messageLite) {
        return new MessageSetSchema<>(unknownFieldSchema, extensionSchema, messageLite);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <UT, UB, ET extends FieldSet.FieldDescriptorLite<ET>> boolean parseMessageSetItemOrUnknownField(Reader reader, ExtensionRegistryLite extensionRegistryLite, ExtensionSchema<ET> extensionSchema, FieldSet<ET> fieldSet, UnknownFieldSchema<UT, UB> unknownFieldSchema, UB ub) throws InvalidProtocolBufferException {
        int tag = reader.getTag();
        if (tag != WireFormat.MESSAGE_SET_ITEM_TAG) {
            if (WireFormat.getTagWireType(tag) != 2) {
                return reader.skipField();
            }
            Object objFindExtensionByNumber = extensionSchema.findExtensionByNumber(extensionRegistryLite, this.defaultInstance, WireFormat.getTagFieldNumber(tag));
            if (objFindExtensionByNumber == null) {
                return unknownFieldSchema.mergeOneFieldFrom(ub, reader);
            }
            extensionSchema.parseLengthPrefixedMessageSetItem(reader, objFindExtensionByNumber, extensionRegistryLite, fieldSet);
            return true;
        }
        Object objFindExtensionByNumber2 = null;
        ByteString bytes = null;
        int uInt32 = 0;
        while (reader.getFieldNumber() != Integer.MAX_VALUE) {
            int tag2 = reader.getTag();
            if (tag2 == WireFormat.MESSAGE_SET_TYPE_ID_TAG) {
                uInt32 = reader.readUInt32();
                objFindExtensionByNumber2 = extensionSchema.findExtensionByNumber(extensionRegistryLite, this.defaultInstance, uInt32);
            } else if (tag2 == WireFormat.MESSAGE_SET_MESSAGE_TAG) {
                if (objFindExtensionByNumber2 != null) {
                    extensionSchema.parseLengthPrefixedMessageSetItem(reader, objFindExtensionByNumber2, extensionRegistryLite, fieldSet);
                } else {
                    bytes = reader.readBytes();
                }
            } else if (!reader.skipField()) {
                break;
            }
        }
        if (reader.getTag() != WireFormat.MESSAGE_SET_ITEM_END_TAG) {
            throw InvalidProtocolBufferException.invalidEndTag();
        }
        if (bytes != null) {
            if (objFindExtensionByNumber2 != null) {
                extensionSchema.parseMessageSetItem(bytes, objFindExtensionByNumber2, extensionRegistryLite, fieldSet);
            } else {
                unknownFieldSchema.addLengthDelimited(ub, uInt32, bytes);
            }
        }
        return true;
    }

    private <UT, UB> void writeUnknownFieldsHelper(UnknownFieldSchema<UT, UB> unknownFieldSchema, T t2, Writer writer) {
        unknownFieldSchema.writeAsMessageSetTo(unknownFieldSchema.getFromMessage(t2), writer);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public boolean equals(T t2, T t3) {
        if (!this.unknownFieldSchema.getFromMessage(t2).equals(this.unknownFieldSchema.getFromMessage(t3))) {
            return false;
        }
        if (this.hasExtensions) {
            return this.extensionSchema.getExtensions(t2).equals(this.extensionSchema.getExtensions(t3));
        }
        return true;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public int getSerializedSize(T t2) {
        int unknownFieldsSerializedSize = getUnknownFieldsSerializedSize(this.unknownFieldSchema, t2) + 0;
        return this.hasExtensions ? unknownFieldsSerializedSize + this.extensionSchema.getExtensions(t2).getMessageSetSerializedSize() : unknownFieldsSerializedSize;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public int hashCode(T t2) {
        int iHashCode = this.unknownFieldSchema.getFromMessage(t2).hashCode();
        return this.hasExtensions ? (iHashCode * 53) + this.extensionSchema.getExtensions(t2).hashCode() : iHashCode;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public final boolean isInitialized(T t2) {
        return this.extensionSchema.getExtensions(t2).isInitialized();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public void makeImmutable(T t2) {
        this.unknownFieldSchema.makeImmutable(t2);
        this.extensionSchema.makeImmutable(t2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public void mergeFrom(T t2, Reader reader, ExtensionRegistryLite extensionRegistryLite) {
        mergeFromHelper(this.unknownFieldSchema, this.extensionSchema, t2, reader, extensionRegistryLite);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public T newInstance() {
        MessageLite messageLite = this.defaultInstance;
        return messageLite instanceof GeneratedMessageLite ? (T) ((GeneratedMessageLite) messageLite).newMutableInstance() : (T) messageLite.newBuilderForType().buildPartial();
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public void writeTo(T t2, Writer writer) {
        for (T t3 : this.extensionSchema.getExtensions(t2)) {
            FieldSet.FieldDescriptorLite fieldDescriptorLite = (FieldSet.FieldDescriptorLite) t3.getKey();
            if (fieldDescriptorLite.getLiteJavaType() != WireFormat.JavaType.MESSAGE || fieldDescriptorLite.isRepeated() || fieldDescriptorLite.isPacked()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            writer.writeMessageSetItem(fieldDescriptorLite.getNumber(), t3 instanceof LazyField.LazyEntry ? ((LazyField.LazyEntry) t3).getField().toByteString() : t3.getValue());
        }
        writeUnknownFieldsHelper(this.unknownFieldSchema, t2, writer);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public void mergeFrom(T t2, T t3) {
        SchemaUtil.mergeUnknownFields(this.unknownFieldSchema, t2, t3);
        if (this.hasExtensions) {
            SchemaUtil.mergeExtensions(this.extensionSchema, t2, t3);
        }
    }

    /* JADX WARN: Code duplicated, block: B:33:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:58:0x00cb A[EDGE_INSN: B:58:0x00cb->B:34:0x00cb BREAK  A[LOOP:1: B:18:0x006d->B:61:0x006d], SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.crypto.tink.shaded.protobuf.Schema
    public void mergeFrom(T t2, byte[] bArr, int i2, int i3, ArrayDecoders.Registers registers) throws InvalidProtocolBufferException {
        GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) t2;
        UnknownFieldSetLite unknownFieldSetLiteNewInstance = generatedMessageLite.unknownFields;
        if (unknownFieldSetLiteNewInstance == UnknownFieldSetLite.getDefaultInstance()) {
            unknownFieldSetLiteNewInstance = UnknownFieldSetLite.newInstance();
            generatedMessageLite.unknownFields = unknownFieldSetLiteNewInstance;
        }
        FieldSet<GeneratedMessageLite.ExtensionDescriptor> fieldSetEnsureExtensionsAreMutable = ((GeneratedMessageLite.ExtendableMessage) t2).ensureExtensionsAreMutable();
        GeneratedMessageLite.GeneratedExtension generatedExtension = null;
        while (i2 < i3) {
            int iDecodeVarint32 = ArrayDecoders.decodeVarint32(bArr, i2, registers);
            int i4 = registers.int1;
            if (i4 == WireFormat.MESSAGE_SET_ITEM_TAG) {
                int i5 = 0;
                ByteString byteString = null;
                while (iDecodeVarint32 < i3) {
                    iDecodeVarint32 = ArrayDecoders.decodeVarint32(bArr, iDecodeVarint32, registers);
                    int i6 = registers.int1;
                    int tagFieldNumber = WireFormat.getTagFieldNumber(i6);
                    int tagWireType = WireFormat.getTagWireType(i6);
                    if (tagFieldNumber != 2) {
                        if (tagFieldNumber == 3) {
                            if (generatedExtension != null) {
                                iDecodeVarint32 = ArrayDecoders.decodeMessageField(Protobuf.getInstance().schemaFor((Class) generatedExtension.getMessageDefaultInstance().getClass()), bArr, iDecodeVarint32, i3, registers);
                                fieldSetEnsureExtensionsAreMutable.setField(generatedExtension.descriptor, registers.object1);
                            } else if (tagWireType == 2) {
                                iDecodeVarint32 = ArrayDecoders.decodeBytes(bArr, iDecodeVarint32, registers);
                                byteString = (ByteString) registers.object1;
                            }
                        }
                        if (i6 == WireFormat.MESSAGE_SET_ITEM_END_TAG) {
                            break;
                        } else {
                            iDecodeVarint32 = ArrayDecoders.skipField(i6, bArr, iDecodeVarint32, i3, registers);
                        }
                    } else if (tagWireType == 0) {
                        iDecodeVarint32 = ArrayDecoders.decodeVarint32(bArr, iDecodeVarint32, registers);
                        i5 = registers.int1;
                        generatedExtension = (GeneratedMessageLite.GeneratedExtension) this.extensionSchema.findExtensionByNumber(registers.extensionRegistry, this.defaultInstance, i5);
                    } else {
                        if (i6 == WireFormat.MESSAGE_SET_ITEM_END_TAG) {
                            break;
                            break;
                        }
                        iDecodeVarint32 = ArrayDecoders.skipField(i6, bArr, iDecodeVarint32, i3, registers);
                    }
                }
                if (byteString != null) {
                    unknownFieldSetLiteNewInstance.storeField(WireFormat.makeTag(i5, 2), byteString);
                }
                i2 = iDecodeVarint32;
            } else if (WireFormat.getTagWireType(i4) == 2) {
                GeneratedMessageLite.GeneratedExtension generatedExtension2 = (GeneratedMessageLite.GeneratedExtension) this.extensionSchema.findExtensionByNumber(registers.extensionRegistry, this.defaultInstance, WireFormat.getTagFieldNumber(i4));
                if (generatedExtension2 != null) {
                    i2 = ArrayDecoders.decodeMessageField(Protobuf.getInstance().schemaFor((Class) generatedExtension2.getMessageDefaultInstance().getClass()), bArr, iDecodeVarint32, i3, registers);
                    fieldSetEnsureExtensionsAreMutable.setField(generatedExtension2.descriptor, registers.object1);
                } else {
                    i2 = ArrayDecoders.decodeUnknownField(i4, bArr, iDecodeVarint32, i3, unknownFieldSetLiteNewInstance, registers);
                }
                generatedExtension = generatedExtension2;
            } else {
                i2 = ArrayDecoders.skipField(i4, bArr, iDecodeVarint32, i3, registers);
            }
        }
        if (i2 != i3) {
            throw InvalidProtocolBufferException.parseFailure();
        }
    }
}
