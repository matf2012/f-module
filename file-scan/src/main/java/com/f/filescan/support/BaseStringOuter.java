package com.f.filescan.support;

import com.f.filescan.IFileScanOuter;
import com.f.filescan.constant.FileScanConstant;
import com.f.filescan.utils.FileScannerUtils;
import com.f.filescan.ScanFile;

import java.util.ArrayList;
import java.util.List;

/**
 * 输出抽象处理类，
 * 主要是将文件内容转换成字符串数组
 * @Description:
 * @author: feng
 * @date: 2020-07-21
 */
abstract class BaseStringOuter implements IFileScanOuter {

    protected static final String LEVEL_SPACE="    ";

    @Override
    public void output(List<ScanFile> files) {

        // 调用子类输出
        outputString(convert(files,0));
    }

    /**
     * 将文件转换为字符集合
     * @param files
     * @param level
     * @return
     */
    protected List<String> convert(List<ScanFile> files,int level){
        List<String> list = new ArrayList<>();
        if(FileScannerUtils.isNotEmpity(files)){
            for(ScanFile file : files){
                list.add(convert(file,level));
                if(FileScannerUtils.isNotEmpity(file.getChildren())){
                    list.addAll(convert(file.getChildren(),level+1));
                }
            }
        }
        return list;
    }

    /**
     * 需要子类实现类，用于输出字符
     * @param list
     */
    protected abstract void outputString(List<String> list);

    /**
     * 将文件转换为字符串
     * @param file
     * @param level
     * @return
     */
    protected String convert(ScanFile file,int level){
        StringBuilder builder = new StringBuilder();
        builder.append(genLevelSpace(level));
        if(FileScanConstant.SCAN_FILE_TYPE_DIR.equals(file.getType())){
            builder.append(">");
        }
        builder.append(file.getName());
        builder.append(":");
        builder.append(file.size());

        if(FileScanConstant.SCAN_FILE_TYPE_DIR.equals(file.getType())){
            builder.append("[文件数量：");
            builder.append(file.count());
            builder.append("]");
        }
        return builder.toString();
    }

    /**
     * 层级缩进
     * @param level
     * @return
     */
    protected String genLevelSpace(int level){
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<level;i++){
            builder.append(LEVEL_SPACE);
        }
        return builder.toString();
    }

}
