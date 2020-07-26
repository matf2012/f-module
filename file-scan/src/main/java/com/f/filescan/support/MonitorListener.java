package com.f.filescan.support;

import com.f.filescan.FileScanProperty;
import com.f.filescan.ScanFile;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @author: feng
 * @date: 2020-07-21
 */
class MonitorListener extends DefaultListener {
    private Date start;
    private long fileCount = 0;
    private long dirCount = 0;
    private long scanCount = 0;
    private Date end;

    @Override
    public boolean beforeScan(FileScanProperty property) {
        this.start = new Date();
        this.fileCount = 0;
        this.scanCount = 0;
        this.end = null;
        return super.beforeScan(property);
    }

    @Override
    public boolean afterScan(FileScanProperty property, List<ScanFile> list) {
        this.end = new Date();
        return super.afterScan(property, list);
    }

    @Override
    public boolean scanPath(String path) {
        dirCount();
        return super.scanPath(path);
    }

    @Override
    public boolean scanPath(String path, List<ScanFile> list) {
        scanCount();
        fileCount(list.size());
        return super.scanPath(path, list);
    }

    protected synchronized void dirCount(){
        dirCount++;
    }
    protected synchronized void fileCount(int num){
        fileCount+=num;
    }

    protected synchronized void scanCount(){
        scanCount++;
    }

    protected void printResult(){
        System.out.println("扫描结束：");
        System.out.println("总用时："+getSecond(end)+"秒");
        System.out.println("共扫描文件夹："+dirCount);
        System.out.println("共扫描文件："+fileCount);
    }

    protected void printProgress(){
        System.out.println("扫描进度："+scanCount+"/"+fileCount+"(用时："+getSecond()+"秒)");
    }

    protected long getSecond(Date end){
        return (end.getTime()-start.getTime())/1000;
    }
    protected long getSecond(){
        return (new Date().getTime()-start.getTime())/1000;
    }

    @Override
    public int order() {
        return 1;
    }

    public boolean isEnd(){
        return this.scanCount == this.dirCount;
    }

    public long count(){
        return dirCount + fileCount;
    }
}
