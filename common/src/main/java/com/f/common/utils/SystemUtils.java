package com.f.common.utils;

/**
 * @Description:
 * @author: feng
 * @date: 2020-07-17
 */
public class SystemUtils {

    public static void addShutdownHook(Runnable runnable) {
        Runtime.getRuntime().addShutdownHook(new Thread(runnable));
    }

    public static boolean isLinux() {
        return System.getProperty("os.name").toLowerCase().contains("linux");
    }

    public static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }

}
