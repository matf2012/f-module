package com.f.filescan.utils;

import java.util.Collection;

/**
 * @Description:
 * @author: feng
 * @date: 2020-07-21
 */
public class FileScannerUtils {



    public static  boolean isEmpity(Collection collection){
        return collection == null || collection.isEmpty();
    }
    public static  boolean isEmpity(Object[] array){
        return array == null || array.length == 0;
    }

    public static boolean isNotEmpity(Collection collection){
        return !isEmpity(collection);
    }
    public static boolean isNotEmpity(Object[] array){
        return !isEmpity(array);
    }
}
