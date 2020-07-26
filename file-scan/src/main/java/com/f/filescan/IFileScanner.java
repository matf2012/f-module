package com.f.filescan;



import java.util.List;

/**
 * 扫描接口
 */
public interface IFileScanner {

    /**
     * 扫描
     * @param path 文件路径
     * @return 路径下文件集合
     */
    List<ScanFile> scan(String path);
}
