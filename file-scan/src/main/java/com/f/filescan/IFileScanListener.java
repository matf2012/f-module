package com.f.filescan;


import java.util.List;


/**
 * 扫描监听接口
 * @Description:
 * @author: feng
 * @date: 2020-07-21
 */
public interface IFileScanListener {
    boolean scanPath(String path);
    boolean scanPath(String path, List<ScanFile> list);

    boolean beforeScan(FileScanProperty property);
    boolean afterScan(FileScanProperty property,List<ScanFile> list);

    boolean init(FileScanProperty property);

    int order();

    boolean error(FileScanProperty property, Exception exception);
}
