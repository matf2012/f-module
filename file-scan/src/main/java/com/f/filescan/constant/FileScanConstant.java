package com.f.filescan.constant;

/**
 * 扫描常量
 * @Description:
 * @author: feng
 * @date: 2020-07-21
 */
public interface FileScanConstant {
    /**
     * 文件类型：文件夹
     */
    String SCAN_FILE_TYPE_DIR = "2";
    /**
     * 文件类型：文件
     */
    String SCAN_FILE_TYPE_FILE = "1";
    /**
     * 输出类型：控制台
     */
    String SCAN_FILE_OUTER_CONSOLE = "1";
    /**
     * 输出类型：文件
     */
    String SCAN_FILE_OUTER_FILE = "2";
    /**
     * 排序
     */
    String SCAN_FILE_SORT_TRUE = "1";
    /**
     * 并行
     */
    String SCAN_FILE_PARALLEL_TRUE = "1";

    /**
     * 监听器优先级（默认：50）
     */
    int FILTER_ORDER = 50;

    int THREAD_COUNT = 5;
}
