package com.f.filescan.support;

import com.f.filescan.utils.FileScannerUtils;

import java.util.List;

/**
 * 扫描文件控制台输出类
 * @Description:
 * @author: feng
 * @date: 2020-07-21
 */
class ConsoleOuter extends BaseStringOuter{

    @Override
    protected void outputString(List<String> list) {
        if(FileScannerUtils.isNotEmpity(list)){
            for(String line : list){
                System.out.println(line);
            }
        }
    }
}
