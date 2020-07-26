package com.f.filescan.support;

import com.f.filescan.utils.ThreadUtils;
import com.f.filescan.IFileScanner;
import com.f.filescan.ScanFile;
import com.f.filescan.constant.FileScanEvent;
import com.f.filescan.exception.FileScanException;
import com.f.filescan.utils.FileScannerUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Description:
 * @author: feng
 * @date: 2020-07-21
 */
public class ParallelFileScanner implements IFileScanner {

    @Override
    public List<ScanFile> scan(String path) {
        // 触发路径扫描前事件
        ListenerManager.event(FileScanEvent.BEFORE_SCAN_PATH,path);
        List<ScanFile> list = null;
        AtomicBoolean isEnd = new AtomicBoolean(false);
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
                            ThreadUtils.execute(()->{scanFile.setChildren(scan(file.getPath()));
                                isEnd.set(true);});

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

//        while(!isEnd.get()){
//            ThreadUtils.sleep(1000);
//        }
        // 触发路径扫描后事件
        ListenerManager.event(FileScanEvent.AFTER_SCAN_PATH,path,list);
        return list;
    }
}
