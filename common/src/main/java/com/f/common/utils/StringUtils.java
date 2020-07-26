package com.f.common.utils;

import java.util.Collection;
import java.util.Locale;

/**
 * @Description:
 * @author: feng
 * @date: 2020-07-17
 */
public class StringUtils {

    public static final String EMPTY = "";
    /**
     * 是否为空或空白字符
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否不为空或空白字符
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 是否不为空字符串
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !StringUtils.isEmpty(str);
    }

    /**
     * 是否为空字符串
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }


    /**
     * 为空字符串返回默认值，否则返回本身
     * @param str
     * @param defaultStr
     * @return
     */
    public static String defaultIfEmpty(String str, String defaultStr) {
        return StringUtils.isEmpty(str) ? defaultStr : str;
    }

    /**
     * 比对两个字符串是否相等
     * 避免空字符串空指针问题
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equals(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equals(str2);
    }

    /**
     * 去除字符串两端空白字符
     * @param str
     * @return
     */
    public static String trim(final String str) {
        return str == null ? null : str.trim();
    }


    /**
     * 字符串拼接
     * @param collection
     * @param separator
     * @return
     */
    public static String join(Collection collection, String separator) {
        if (collection == null) {
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder();
        Object[] objects = collection.toArray();

        for (int i = 0; i < collection.size() - 1; i++) {
            stringBuilder.append(objects[i].toString()).append(separator);
        }

        if (collection.size() > 0) {
            stringBuilder.append(objects[collection.size() - 1]);
        }

        return stringBuilder.toString();
    }


    private static String hex(char ch) {
        return Integer.toHexString(ch).toUpperCase(Locale.ENGLISH);
    }

}
