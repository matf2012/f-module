package com.f.filescan;

/**
 * 文件扫描属性类
 * @Description:
 * @author: feng
 * @date: 2020-07-21
 */
public class FileScanProperty {

    /**
     * 扫描路径
     */
    private String basePath;
    /**
     * 输出类型
     */
    private String outType;
    /**
     * 输出路径
     */
    private String outPath;
    /**
     * 是否排序
     */
    private boolean sort;

    /**
     * 是否并行
     */
    private boolean parallel;


    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getOutType() {
        return outType;
    }

    public void setOutType(String outType) {
        this.outType = outType;
    }

    public String getOutPath() {
        return outPath;
    }

    public void setOutPath(String outPath) {
        this.outPath = outPath;
    }

    public boolean isSort() {
        return sort;
    }

    public void setSort(boolean sort) {
        this.sort = sort;
    }

    public boolean isParallel() {
        return parallel;
    }

    public void setParallel(boolean parallel) {
        this.parallel = parallel;
    }
}
