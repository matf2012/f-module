package com.f.filescan.support;

import com.f.filescan.utils.FileUtils;

import java.io.File;
import java.util.List;

/**
 * 扫描结果文件输出类
 * @Description:
 * @author: feng
 * @date: 2020-07-21
 */
class FileOuter extends BaseStringOuter {

    /**
     * 输出路径
     */
    private String outPath;

    /**
     * 输出路径
     * @param outPath
     */
    public FileOuter(String outPath) {
        this.outPath = outPath;
    }

    @Override
    protected void outputString(List<String> list) {
        File outFile = new File(outPath);
        FileUtils.write(outFile,list);
    }

}
