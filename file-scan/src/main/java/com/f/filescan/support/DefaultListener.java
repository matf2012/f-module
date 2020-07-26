package com.f.filescan.support;

import com.f.filescan.FileScanProperty;
import com.f.filescan.ScanFile;
import com.f.filescan.constant.FileScanConstant;
import com.f.filescan.IFileScanListener;

import java.util.List;

/**
 * 默认监听器，用于监听器继承使用，没有具体实现内容
 * @Description:
 * @author: feng
 * @date: 2020-07-21
 */
public class DefaultListener implements IFileScanListener {

    @Override
    public boolean scanPath(String path) {
        return true;
    }

    @Override
    public boolean scanPath(String path, List<ScanFile> list) {
        return true;
    }

    @Override
    public boolean beforeScan(FileScanProperty property) {
        return true;
    }

    @Override
    public boolean afterScan(FileScanProperty property, List<ScanFile> list) {
        return true;
    }


    @Override
    public int order() {
        return FileScanConstant.FILTER_ORDER;
    }

    @Override
    public boolean init(FileScanProperty property) {
        return true;
    }

    @Override
    public boolean error(FileScanProperty property, Exception exception) {
        return true;
    }
}
