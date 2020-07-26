package com.f.common.utils;

import java.nio.charset.Charset;

/**
 * @Description:
 * @author: feng
 * @date: 2020-07-17
 */
public class ByteUtils {

    public static final String ENCODE = "UTF-8";

    public static final byte[] EMPTY = new byte[0];

    public static byte[] toBytes(String s) {
        if (s == null) {
            return EMPTY;
        }
        return s.getBytes(Charset.forName(ENCODE));
    }

    public static byte[] toBytes(Object s) {
        if (s == null) {
            return EMPTY;
        }
        return toBytes(String.valueOf(s));
    }

    public static String toString(byte[] bytes) {
        if (bytes == null) {
            return StringUtils.EMPTY;
        }
        return new String(bytes, Charset.forName(ENCODE));
    }

    public static boolean isEmpty(byte[] data) {
        return data == null || data.length == 0;
    }

    public static boolean isNotEmpty(byte[] data) {
        return !isEmpty(data);
    }

}
