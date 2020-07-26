package com.f.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 */
public class MD5Utils {

    private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static ThreadLocal<MessageDigest> MESSAGE_DIGEST_LOCAL = new ThreadLocal<MessageDigest>() {
        @Override
        protected MessageDigest initialValue() {
            try {
                return MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                return null;
            }
        }
    };



    public static String MD5(byte[] bytes) throws NoSuchAlgorithmException {
        try {
            MessageDigest messageDigest = MESSAGE_DIGEST_LOCAL.get();
            if (messageDigest != null) {
                return encodeHexString(messageDigest.digest(bytes));
            }
            throw new NoSuchAlgorithmException("MessageDigest get MD5 instance error");
        } finally {
            MESSAGE_DIGEST_LOCAL.remove();
        }
    }

    public static String MD5(String value,String encode) {
        try {
            return MD5(value.getBytes(encode));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static String MD5(String value) {
        return MD5(value,"UTF-8");
    }

    /**
     * 将一个字节数组转化为可见的字符串
     */
     public static String encodeHexString(byte[] bytes) {
        int l = bytes.length;

        char[] out = new char[l << 1];

        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS_LOWER[(0xF0 & bytes[i]) >>> 4];
            out[j++] = DIGITS_LOWER[0x0F & bytes[i]];
        }

        return new String(out);
    }

}
