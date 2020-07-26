package com.f.filescan.support;

import com.f.filescan.FileScanProperty;
import com.f.filescan.constant.FileScanConstant;

/**
 * 属性构造器
 * @Description:
 * @author: feng
 * @date: 2020-07-21
 */
public class PropertyBuilder {
    private String basePath;
    private String outType;
    private String outPath;
    private String sort;
    private String parallel;

    public PropertyBuilder basePath(String val){
        this.basePath = val;
        return this;
    }

    public PropertyBuilder outType(String val){
        this.outType = val;
        return this;
    }

    public PropertyBuilder outPath(String val){
        this.outPath = val;
        return this;
    }

    public PropertyBuilder sort(String val){
        this.sort = val;
        return this;
    }
    public PropertyBuilder parallel(String val){
        this.parallel = val;
        return this;
    }

    /**
     * 构建
     * @return
     */
    public FileScanProperty build(){
        FileScanProperty property = new FileScanProperty();
        property.setBasePath(this.basePath);
        property.setOutType(this.outType);
        property.setOutPath(this.outPath);
        property.setSort(FileScanConstant.SCAN_FILE_SORT_TRUE.equals(this.sort));
        property.setParallel(FileScanConstant.SCAN_FILE_PARALLEL_TRUE.equals(this.parallel));
        return property;
    }
}
