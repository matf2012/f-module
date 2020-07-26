package com.f.filescan;

import com.f.filescan.constant.FileScanConstant;
import com.f.filescan.utils.FileScannerUtils;

import java.io.File;
import java.util.List;

/**
 * 扫描文件类
 * @Description:
 * @author: feng
 * @date: 2020-07-21
 */
public class ScanFile{
    private File file;
    private String message;
    private String path;
    private String type;
    private String name;
    private Long size = 0L;
    private Integer count = 0;
    private List<ScanFile> children;

    public ScanFile(File file) {
        this.initFile(file);
    }

    public ScanFile initFile(File file){
        if(file != null && file.exists()){
            this.file = file;
            this.path = file.getPath();
            this.type = file.isDirectory()? FileScanConstant.SCAN_FILE_TYPE_DIR:FileScanConstant.SCAN_FILE_TYPE_FILE;
            this.name = file.getName();
            if(file.isFile()){
                this.size = file.length();
            }
        }
        return this;
    }

    public Long size(){
        Long size = this.size;
        if(this.file != null && this.size == 0){
            if(isDir()){
                if(FileScannerUtils.isNotEmpity(children)){
                    for(ScanFile scan : children){
                        size += scan.size();
                    }
                }
                this.size = size;
            }
        }
        return size;
    }

    public Integer count(){
        if(isDir() && this.count == 0){
            if(FileScannerUtils.isNotEmpity(children)){
                this.count = this.children.size();
                for(ScanFile scan : children){
                    this.count += scan.count();
                }
            }
        }
        return this.count;
    }

    protected boolean isDir(){
        return FileScanConstant.SCAN_FILE_TYPE_DIR.equals(this.type);
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public List<ScanFile> getChildren() {
        return children;
    }

    public void setChildren(List<ScanFile> children) {
        this.children = children;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

}
