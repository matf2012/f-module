package com.f.common.utils;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Map;
import java.util.Objects;

/**
 * @Description:
 * @author: feng
 * @date: 2020-07-17
 */
public class MapUtils {

    /**
     * MAP 是否为空，NULL或无数据
     * @param map
     * @return
     */
    public static boolean isEmpty(Map map) {
        return (map == null || map.isEmpty());
    }

    /**
     * MAP 是否不为空
     * @param map
     * @return
     */
    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }

    public static boolean isNotEmpty(Dictionary coll) {
        return !isEmpty(coll);
    }

    public static boolean isEmpty(Dictionary coll) {
        return (coll == null || coll.isEmpty());
    }


    public static void putIfValNoNull(Map target, Object key, Object value) {
        Objects.requireNonNull(key, "key");
        if (value != null) {
            target.put(key, value);
        }
    }
    public static void putIfValNoEmpty(Map target, Object key, Object value) {
        Objects.requireNonNull(key, "key");
        if (value instanceof String) {
            if (StringUtils.isNotEmpty((String) value)) {
                target.put(key, value);
            }
            return;
        }
        if (value instanceof Collection) {
            if (CollectionUtils.isNotEmpty((Collection) value)) {
                target.put(key, value);
            }
            return;
        }
        if (value instanceof Map) {
            if (isNotEmpty((Map) value)) {
                target.put(key, value);
            }
            return;
        }
        if (value instanceof Dictionary) {
            if (isNotEmpty((Dictionary) value)) {
                target.put(key, value);
            }
            return;
        }
    }
}
