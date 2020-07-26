package com.f.common.utils;

import com.sun.org.apache.regexp.internal.RE;

import java.util.UUID;

/**
 * @Description:
 * @author: feng
 * @date: 2020-07-25
 */
public class RandomUtils {

    public static String UUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
