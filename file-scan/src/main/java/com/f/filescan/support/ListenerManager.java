package com.f.filescan.support;

import com.f.filescan.constant.FileScanEvent;
import com.f.filescan.FileScanProperty;
import com.f.filescan.ScanFile;
import com.f.filescan.IFileScanListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 监听管理类
 * @Description:
 * @author: feng
 * @date: 2020-07-21
 */
public final class ListenerManager {

    /**
     * 监听器集合
     */
    private static List<IFileScanListener> listeners = new ArrayList<>();

    /**
     * 注册
     * @param listener
     * @return
     */
    public static boolean register(IFileScanListener listener) {
        boolean ret = listeners.contains(listener);
        if (!ret) {
            listeners.add(listener);
        }
        // 注册后进行排序
        listeners.sort(Comparator.comparingInt(IFileScanListener::order).reversed());
        return ret;
    }

    /**
     * 删除注册监听器
     * @param listener
     * @return
     */
    public static boolean remove(IFileScanListener listener) {
        return listeners.remove(listener);
    }

    /**
     * 事件处理
     * @param event
     * @param args
     */
    public static void event(FileScanEvent event, Object... args) {
        if (event != null && listeners.size() > 0) {
            // 参数处理
            String path = null;
            List<ScanFile> list = null;
            FileScanProperty property = null;
            Exception exception = null;
            if (args != null && args.length > 0) {
                for (Object arg : args) {
                    if (arg instanceof List) {
                        list = (List<ScanFile>) arg;
                    } else if (arg instanceof FileScanProperty) {
                        property = (FileScanProperty) arg;
                    } else if (arg instanceof String) {
                        path = (String) arg;
                    } else if (arg instanceof Exception) {
                        exception = (Exception) arg;
                    }
                }
            }

            // 执行监听器事件
            boolean flag = true;
            for (IFileScanListener listener : listeners) {
                switch (event) {
                    case BEFORE_SCAN_PATH:
                        flag = listener.scanPath(path);
                        break;
                    case AFTER_SCAN_PATH:
                        flag = listener.scanPath(path, list);
                        break;
                    case BEFORE_SCAN:
                        flag = listener.beforeScan(property);
                        break;
                    case AFTER_SCAN:
                        flag = listener.afterScan(property, list);
                        break;
                    case INIT:
                        flag = listener.init(property);
                        break;
                    case ERROR:
                        flag = listener.error(property,exception);
                }
                if(!flag){
                    break;
                }
            }
        }
    }
}
