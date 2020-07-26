package com.f.filescan.support;

import com.f.filescan.constant.FileScanEvent;
import com.f.filescan.exception.FileScanException;
import com.f.filescan.utils.FileScannerUtils;
import com.f.filescan.ScanFile;
import com.f.filescan.IFileScanner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件扫描
 * @Description:
 * @author: feng
 * @date: 2020-07-21
 */
class SimpleFileScanner implements IFileScanner {

    /**
     * 扫描路径
     * @param path 路径
     * @return
     */
    @Override
    public List<ScanFile> scan(String path) {

        // 触发路径扫描前事件
        ListenerManager.event(FileScanEvent.BEFORE_SCAN_PATH,path);
        List<ScanFile> list = null;
        try {
            list = new ArrayList<>();
            File f = new File(path);
            if(f.exists() && f.isDirectory()){
                File[] files = f.listFiles();
                if(FileScannerUtils.isNotEmpity(files)){
                    for(File file : files){
                        ScanFile scanFile = new ScanFile(file);
                        // 文件夹时 ， 进行递归扫描
                        if(file.isDirectory()){
                            scanFile.setChildren(scan(file.getPath()));
                        }
                        list.add(scanFile);
                    }
                }
            }
        } catch (Exception e) {
            if(e instanceof FileScanException){
                throw e;
            }else{
                throw new FileScanException("文件扫描错误："+path,e);
            }
        }

        // 触发路径扫描后事件
        ListenerManager.event(FileScanEvent.AFTER_SCAN_PATH,path,list);
        return list;
    }
}
