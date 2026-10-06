package com.google.crypto.tink.shaded.protobuf;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
final class MessageLiteToString {
    private static final String BUILDER_LIST_SUFFIX = "OrBuilderList";
    private static final String BYTES_SUFFIX = "Bytes";
    private static final char[] INDENT_BUFFER;
    private static final String LIST_SUFFIX = "List";
    private static final String MAP_SUFFIX = "Map";

    static {
        char[] cArr = new char[80];
        INDENT_BUFFER = cArr;
        Arrays.fill(cArr, ' ');
    }

    private MessageLiteToString() {
    }

    private static void indent(int i2, StringBuilder sb) {
        while (i2 > 0) {
            char[] cArr = INDENT_BUFFER;
            int length = i2 > cArr.length ? cArr.length : i2;
            sb.append(cArr, 0, length);
            i2 -= length;
        }
    }

    private static boolean isDefaultValue(Object obj) {
        Object obj2;
        if (obj instanceof Boolean) {
            return !((Boolean) obj).booleanValue();
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue() == 0;
        }
        if (obj instanceof Float) {
            return Float.floatToRawIntBits(((Float) obj).floatValue()) == 0;
        }
        if (obj instanceof Double) {
            return Double.doubleToRawLongBits(((Double) obj).doubleValue()) == 0;
        }
        if (obj instanceof String) {
            obj2 = "";
        } else {
            if (!(obj instanceof ByteString)) {
                if (obj instanceof MessageLite) {
                    return obj == ((MessageLite) obj).getDefaultInstanceForType();
                }
                return (obj instanceof java.lang.Enum) && ((java.lang.Enum) obj).ordinal() == 0;
            }
            obj2 = ByteString.EMPTY;
        }
        return obj.equals(obj2);
    }

    private static String pascalCaseToSnakeCase(String str) {
        if (str.isEmpty()) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Character.toLowerCase(str.charAt(0)));
        for (int i2 = 1; i2 < str.length(); i2++) {
            char cCharAt = str.charAt(i2);
            if (Character.isUpperCase(cCharAt)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(cCharAt));
        }
        return sb.toString();
    }

    public static void printField(StringBuilder sb, int i2, String str, Object obj) {
        String strEscapeBytes;
        if (obj instanceof List) {
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                printField(sb, i2, str, it.next());
            }
            return;
        }
        if (obj instanceof Map) {
            Iterator it2 = ((Map) obj).entrySet().iterator();
            while (it2.hasNext()) {
                printField(sb, i2, str, (Map.Entry) it2.next());
            }
            return;
        }
        sb.append('\n');
        indent(i2, sb);
        sb.append(pascalCaseToSnakeCase(str));
        if (obj instanceof String) {
            sb.append(": \"");
            strEscapeBytes = TextFormatEscaper.escapeText((String) obj);
        } else {
            if (!(obj instanceof ByteString)) {
                if (obj instanceof GeneratedMessageLite) {
                    sb.append(" {");
                    reflectivePrintWithIndent((GeneratedMessageLite) obj, sb, i2 + 2);
                } else if (!(obj instanceof Map.Entry)) {
                    sb.append(": ");
                    sb.append(obj);
                    return;
                } else {
                    sb.append(" {");
                    Map.Entry entry = (Map.Entry) obj;
                    int i3 = i2 + 2;
                    printField(sb, i3, "key", entry.getKey());
                    printField(sb, i3, "value", entry.getValue());
                }
                sb.append("\n");
                indent(i2, sb);
                sb.append("}");
                return;
            }
            sb.append(": \"");
            strEscapeBytes = TextFormatEscaper.escapeBytes((ByteString) obj);
        }
        sb.append(strEscapeBytes);
        sb.append('\"');
    }

    /* JADX WARN: Code duplicated, block: B:63:0x0160  */
    /* JADX WARN: Code duplicated, block: B:65:0x0172  */
    /* JADX WARN: Code duplicated, block: B:67:0x017a  */
    /* JADX WARN: Code duplicated, block: B:69:0x0180  */
    /* JADX WARN: Code duplicated, block: B:70:0x0182  */
    /* JADX WARN: Code duplicated, block: B:71:0x0184  */
    /* JADX WARN: Code duplicated, block: B:73:0x0192  */
    /* JADX WARN: Code duplicated, block: B:96:0x0138 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:97:0x0138 A[SYNTHETIC] */
    private static void reflectivePrintWithIndent(MessageLite messageLite, StringBuilder sb, int i2) {
        int i3;
        java.lang.reflect.Method method;
        java.lang.reflect.Method method2;
        Object objInvokeOrDie;
        boolean zBooleanValue;
        java.lang.reflect.Method method3;
        String strSubstring;
        Object objInvokeOrDie2;
        java.lang.reflect.Method method4;
        HashSet hashSet = new HashSet();
        HashMap map = new HashMap();
        TreeMap treeMap = new TreeMap();
        java.lang.reflect.Method[] declaredMethods = messageLite.getClass().getDeclaredMethods();
        int length = declaredMethods.length;
        int i4 = 0;
        while (true) {
            i3 = 3;
            if (i4 >= length) {
                break;
            }
            java.lang.reflect.Method method5 = declaredMethods[i4];
            if (!Modifier.isStatic(method5.getModifiers()) && method5.getName().length() >= 3) {
                if (method5.getName().startsWith("set")) {
                    hashSet.add(method5.getName());
                } else if (Modifier.isPublic(method5.getModifiers()) && method5.getParameterTypes().length == 0) {
                    if (method5.getName().startsWith("has")) {
                        map.put(method5.getName(), method5);
                    } else if (method5.getName().startsWith("get")) {
                        treeMap.put(method5.getName(), method5);
                    }
                }
            }
            i4++;
        }
        for (Map.Entry entry : treeMap.entrySet()) {
            String strSubstring2 = ((String) entry.getKey()).substring(i3);
            if (!strSubstring2.endsWith(LIST_SUFFIX) || strSubstring2.endsWith(BUILDER_LIST_SUFFIX) || strSubstring2.equals(LIST_SUFFIX) || (method4 = (java.lang.reflect.Method) entry.getValue()) == null || !method4.getReturnType().equals(List.class)) {
                if (strSubstring2.endsWith(MAP_SUFFIX) && !strSubstring2.equals(MAP_SUFFIX) && (method3 = (java.lang.reflect.Method) entry.getValue()) != null && method3.getReturnType().equals(Map.class) && !method3.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method3.getModifiers())) {
                    strSubstring = strSubstring2.substring(0, strSubstring2.length() - 3);
                    objInvokeOrDie2 = GeneratedMessageLite.invokeOrDie(method3, messageLite, new Object[0]);
                } else if (hashSet.contains("set".concat(strSubstring2))) {
                    if (strSubstring2.endsWith(BYTES_SUFFIX)) {
                        if (!treeMap.containsKey("get" + strSubstring2.substring(0, strSubstring2.length() - 5))) {
                            method = (java.lang.reflect.Method) entry.getValue();
                            method2 = (java.lang.reflect.Method) map.get("has".concat(strSubstring2));
                            if (method != null) {
                                objInvokeOrDie = GeneratedMessageLite.invokeOrDie(method, messageLite, new Object[0]);
                                if (method2 == null) {
                                    zBooleanValue = ((Boolean) GeneratedMessageLite.invokeOrDie(method2, messageLite, new Object[0])).booleanValue();
                                } else if (isDefaultValue(objInvokeOrDie)) {
                                    zBooleanValue = false;
                                } else {
                                    zBooleanValue = true;
                                }
                                if (zBooleanValue) {
                                    printField(sb, i2, strSubstring2, objInvokeOrDie);
                                }
                            }
                        }
                    } else {
                        method = (java.lang.reflect.Method) entry.getValue();
                        method2 = (java.lang.reflect.Method) map.get("has".concat(strSubstring2));
                        if (method != null) {
                            objInvokeOrDie = GeneratedMessageLite.invokeOrDie(method, messageLite, new Object[0]);
                            if (method2 == null) {
                                zBooleanValue = ((Boolean) GeneratedMessageLite.invokeOrDie(method2, messageLite, new Object[0])).booleanValue();
                            } else if (isDefaultValue(objInvokeOrDie)) {
                                zBooleanValue = true;
                            } else {
                                zBooleanValue = false;
                            }
                            if (zBooleanValue) {
                                printField(sb, i2, strSubstring2, objInvokeOrDie);
                            }
                        }
                    }
                }
                i3 = 3;
            } else {
                strSubstring = strSubstring2.substring(0, strSubstring2.length() - 4);
                objInvokeOrDie2 = GeneratedMessageLite.invokeOrDie(method4, messageLite, new Object[0]);
            }
            printField(sb, i2, strSubstring, objInvokeOrDie2);
            i3 = 3;
        }
        if (messageLite instanceof GeneratedMessageLite.ExtendableMessage) {
            Iterator<Map.Entry<T, Object>> it = ((GeneratedMessageLite.ExtendableMessage) messageLite).extensions.iterator();
            while (it.hasNext()) {
                Map.Entry entry2 = (Map.Entry) it.next();
                printField(sb, i2, "[" + ((GeneratedMessageLite.ExtensionDescriptor) entry2.getKey()).getNumber() + "]", entry2.getValue());
            }
        }
        UnknownFieldSetLite unknownFieldSetLite = ((GeneratedMessageLite) messageLite).unknownFields;
        if (unknownFieldSetLite != null) {
            unknownFieldSetLite.printWithIndent(sb, i2);
        }
    }

    public static String toString(MessageLite messageLite, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        reflectivePrintWithIndent(messageLite, sb, 0);
        return sb.toString();
    }
}
