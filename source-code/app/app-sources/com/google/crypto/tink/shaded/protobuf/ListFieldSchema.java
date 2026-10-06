package com.google.crypto.tink.shaded.protobuf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@CheckReturnValue
abstract class ListFieldSchema {
    private static final ListFieldSchema FULL_INSTANCE;
    private static final ListFieldSchema LITE_INSTANCE;

    public static final class ListFieldSchemaFull extends ListFieldSchema {
        private static final Class<?> UNMODIFIABLE_LIST_CLASS = Collections.unmodifiableList(Collections.emptyList()).getClass();

        private ListFieldSchemaFull() {
            super();
        }

        public static <E> List<E> getList(Object obj, long j2) {
            return (List) UnsafeUtil.getObject(obj, j2);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ListFieldSchema
        public void makeImmutableListAt(Object obj, long j2) {
            Object objUnmodifiableList;
            List list = (List) UnsafeUtil.getObject(obj, j2);
            if (list instanceof LazyStringList) {
                objUnmodifiableList = ((LazyStringList) list).getUnmodifiableView();
            } else {
                if (UNMODIFIABLE_LIST_CLASS.isAssignableFrom(list.getClass())) {
                    return;
                }
                if ((list instanceof PrimitiveNonBoxingCollection) && (list instanceof Internal.ProtobufList)) {
                    Internal.ProtobufList protobufList = (Internal.ProtobufList) list;
                    if (protobufList.isModifiable()) {
                        protobufList.makeImmutable();
                        return;
                    }
                    return;
                }
                objUnmodifiableList = Collections.unmodifiableList(list);
            }
            UnsafeUtil.putObject(obj, j2, objUnmodifiableList);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ListFieldSchema
        public <E> void mergeListsAt(Object obj, Object obj2, long j2) {
            List list = getList(obj2, j2);
            List listMutableListAt = mutableListAt(obj, j2, list.size());
            int size = listMutableListAt.size();
            int size2 = list.size();
            if (size > 0 && size2 > 0) {
                listMutableListAt.addAll(list);
            }
            if (size > 0) {
                list = listMutableListAt;
            }
            UnsafeUtil.putObject(obj, j2, list);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ListFieldSchema
        public <L> List<L> mutableListAt(Object obj, long j2) {
            return mutableListAt(obj, j2, 10);
        }

        private static <L> List<L> mutableListAt(Object obj, long j2, int i2) {
            List<L> listMutableCopyWithCapacity2;
            Object obj2;
            List<L> list = getList(obj, j2);
            if (!list.isEmpty()) {
                if (UNMODIFIABLE_LIST_CLASS.isAssignableFrom(list.getClass())) {
                    ArrayList arrayList = new ArrayList(list.size() + i2);
                    arrayList.addAll(list);
                    obj2 = arrayList;
                } else if (list instanceof UnmodifiableLazyStringList) {
                    LazyStringArrayList lazyStringArrayList = new LazyStringArrayList(list.size() + i2);
                    lazyStringArrayList.addAll((UnmodifiableLazyStringList) list);
                    obj2 = lazyStringArrayList;
                } else {
                    if (!(list instanceof PrimitiveNonBoxingCollection) || !(list instanceof Internal.ProtobufList)) {
                        return list;
                    }
                    Internal.ProtobufList protobufList = (Internal.ProtobufList) list;
                    if (protobufList.isModifiable()) {
                        return list;
                    }
                    listMutableCopyWithCapacity2 = protobufList.mutableCopyWithCapacity2(list.size() + i2);
                }
                UnsafeUtil.putObject(obj, j2, obj2);
                return (List<L>) obj2;
            }
            if (list instanceof LazyStringList) {
                listMutableCopyWithCapacity2 = new LazyStringArrayList(i2);
            } else {
                listMutableCopyWithCapacity2 = ((list instanceof PrimitiveNonBoxingCollection) && (list instanceof Internal.ProtobufList)) ? ((Internal.ProtobufList) list).mutableCopyWithCapacity2(i2) : new ArrayList<>(i2);
            }
            UnsafeUtil.putObject(obj, j2, listMutableCopyWithCapacity2);
            return listMutableCopyWithCapacity2;
        }
    }

    public static final class ListFieldSchemaLite extends ListFieldSchema {
        private ListFieldSchemaLite() {
            super();
        }

        public static <E> Internal.ProtobufList<E> getProtobufList(Object obj, long j2) {
            return (Internal.ProtobufList) UnsafeUtil.getObject(obj, j2);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ListFieldSchema
        public void makeImmutableListAt(Object obj, long j2) {
            getProtobufList(obj, j2).makeImmutable();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ListFieldSchema
        public <E> void mergeListsAt(Object obj, Object obj2, long j2) {
            Internal.ProtobufList protobufList = getProtobufList(obj, j2);
            Internal.ProtobufList protobufList2 = getProtobufList(obj2, j2);
            int size = protobufList.size();
            int size2 = protobufList2.size();
            if (size > 0 && size2 > 0) {
                if (!protobufList.isModifiable()) {
                    protobufList = protobufList.mutableCopyWithCapacity2(size2 + size);
                }
                protobufList.addAll(protobufList2);
            }
            if (size > 0) {
                protobufList2 = protobufList;
            }
            UnsafeUtil.putObject(obj, j2, protobufList2);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ListFieldSchema
        public <L> List<L> mutableListAt(Object obj, long j2) {
            Internal.ProtobufList protobufList = getProtobufList(obj, j2);
            if (protobufList.isModifiable()) {
                return protobufList;
            }
            int size = protobufList.size();
            Internal.ProtobufList protobufListMutableCopyWithCapacity2 = protobufList.mutableCopyWithCapacity2(size == 0 ? 10 : size * 2);
            UnsafeUtil.putObject(obj, j2, protobufListMutableCopyWithCapacity2);
            return protobufListMutableCopyWithCapacity2;
        }
    }

    static {
        FULL_INSTANCE = new ListFieldSchemaFull();
        LITE_INSTANCE = new ListFieldSchemaLite();
    }

    private ListFieldSchema() {
    }

    public static ListFieldSchema full() {
        return FULL_INSTANCE;
    }

    public static ListFieldSchema lite() {
        return LITE_INSTANCE;
    }

    public abstract void makeImmutableListAt(Object obj, long j2);

    public abstract <L> void mergeListsAt(Object obj, Object obj2, long j2);

    public abstract <L> List<L> mutableListAt(Object obj, long j2);
}
