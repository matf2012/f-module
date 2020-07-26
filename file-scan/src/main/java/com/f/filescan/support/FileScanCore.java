package com.f.filescan.support;

import com.f.filescan.FileScanProperty;
import com.f.filescan.IFileScanner;
import com.f.filescan.ScanFile;
import com.f.filescan.constant.FileScanEvent;
import com.f.filescan.utils.ThreadUtils;

import java.util.List;

/**
 * 文件读取核心处理类
 * @Description:
 * @author: feng
 * @date: 2020-07-21
 */
public class FileScanCore {

    private FileScanProperty property;
    private IFileScanner scanner;
    private MonitorListener monitorListener;


    /**
     * 扫描核心类
     * @param property 配置信息
     */
    public FileScanCore(FileScanProperty property) {
        this.property = property;
        if(this.property.isParallel()){
            this.scanner = new ParallelFileScanner();
        }else{
            this.scanner = new SimpleFileScanner();
        }

        monitorListener = new MonitorListener();
        ListenerManager.register(monitorListener);
        // 初始化完成监听
        ListenerManager.event(FileScanEvent.INIT,property);
    }

    /**
     * 扫描
     */
    public void scan(){
        // 触发扫描前事件
        ListenerManager.event(FileScanEvent.BEFORE_SCAN,property);
        List<ScanFile> files = null;
        try {
            // 开始文件夹扫描
            files = scanner.scan(property.getBasePath());

            long scanCount = -1;
            // 多线程扫描时，进行监控
            while (property.isParallel()){
                monitorListener.printProgress();
                if(monitorListener.isEnd()){
                    if(monitorListener.count() == scanCount){
                        ThreadUtils.shutdown();
                        break;
                    }else{
                        scanCount = monitorListener.count();
                    }
                }
                ThreadUtils.sleep(100);
            }
        } catch (Exception e) {
            // 触发异常事件
            ListenerManager.event(FileScanEvent.ERROR,property,e);
        }
        // 扫描结束事件
        ListenerManager.event(FileScanEvent.AFTER_SCAN,property,files);
        monitorListener.printResult();
    }


}
