package com.f.filescan;



import java.util.List;

/**
 * 输出接口
 */
public interface IFileScanOuter {
    /**
     * 输出
     * @param files 文件集合
     */
    void output(List<ScanFile> files);
}
